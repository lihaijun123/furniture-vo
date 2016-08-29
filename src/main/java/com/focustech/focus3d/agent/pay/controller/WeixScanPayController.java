package com.focustech.focus3d.agent.pay.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.focustech.common.utils.IPTool;
import com.focustech.common.utils.StringUtils;
import com.focustech.common.utils.TCUtil;
import com.focustech.focus3d.agent.common.controller.CommonController;
import com.focustech.focus3d.agent.component.ConfigPropertiesBean;
import com.focustech.focus3d.agent.model.AgentOrder;
import com.focustech.focus3d.agent.model.AgentOrderTrans;
import com.focustech.focus3d.agent.model.AgentUser;
import com.focustech.focus3d.agent.order.service.AgentOrderService;
import com.focustech.focus3d.agent.order.service.AgentOrderTransService;
import com.focustech.focus3d.agent.sms.service.SmsService;
import com.focustech.focus3d.agent.user.service.AgentUserService;
import com.focustech.focus3d.agent.utils.SmsSendType;
import com.focustech.focus3d.agent.utils.WxPayUtils;
import com.focustech.focus3d.pay.wx.common.Configure;
import com.focustech.focus3d.pay.wx.common.HttpRequest;
import com.focustech.focus3d.pay.wx.common.RandomStringGenerator;
import com.focustech.focus3d.pay.wx.common.Signature;
import com.focustech.focus3d.pay.wx.common.Util;
import com.focustech.focus3d.pay.wx.protocol.ScanPayReqData;
import com.focustech.focus3d.pay.wx.protocol.ScanPayRespData;
import com.focustech.focus3d.pay.wx.service.IServiceRequest;
import com.focustech.focus3d.pay.wx.type.WxPayResultCode;
import com.focustech.focus3d.pay.wx.type.WxPayReturnCode;
import com.focustech.focus3d.pay.wx.type.WxPayTradeType;

/**
 * 微信扫码支付
 * *
 * @author lihaijun
 *
 */
@Controller
@RequestMapping(value = "/wxpay/scanpay")
public class WeixScanPayController extends CommonController{
	private static final Logger log = LoggerFactory.getLogger(WeixScanPayController.class);
	@Autowired
	private AgentOrderService<AgentOrder> orderService;
	@Autowired
	private AgentOrderTransService<AgentOrderTrans> oderTransService;

	private Lock lock = new ReentrantLock();

	@Autowired
	private AgentUserService<AgentUser> agentUserService;
	@Autowired
	private SmsService smsService;
	/**
	 * 扫码后微信回调
	 * *
	 * @throws IOException
	 */
	@RequestMapping(value = "/notify", method = RequestMethod.POST)
	public void notify(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String outputXml = "";
		//读取参数
		InputStream inputStream = null;
		try {
			inputStream = request.getInputStream();
			String respStr = Util.inputStreamToString(inputStream);
			if(StringUtils.isNotEmpty(respStr)){
				boolean notifyValid = Signature.checkIsSignValidFromResponseString(respStr);
				ScanPayReqData payReqData = null;
				ScanPayRespData payRespData = null;
				if(notifyValid){
					payRespData = (ScanPayRespData)Util.getObjectFromXML(respStr, ScanPayRespData.class);
					//订单号
					String orderNum = payRespData.getProduct_id();
					String openid = payRespData.getOpenid();
					//商户系统生成订单数据
					AgentOrder memOrder = orderService.getByOrderNum(orderNum);
					if(memOrder != null){
						String prepayId = memOrder.getPrepayId();
						if(StringUtils.isNotEmpty(prepayId)){
							//已经生成，需要在2小时之内完成支付
							payRespData = new ScanPayRespData(WxPayReturnCode.SUCCESS, "OK", RandomStringGenerator.getRandomStringByLength(32), prepayId, WxPayResultCode.SUCCESS, "OK");
							outputXml = Util.getXmlFromObject(payRespData);
							log.debug("从现有的预支付id支付");
						} else {
							//调统一下单API，请求生成预付交易，获取预支付ID
							String wxPayCallBackUrl = ConfigPropertiesBean.getValue("wx.pay.call.back.url");
							log.info("微信支付回调url：" + wxPayCallBackUrl);
							String price = WxPayUtils.valueOfPrice(memOrder.getTotalPrice());
							payReqData = new ScanPayReqData("小小梦想家", "小小梦想家", orderNum, price, IPTool.getRealIp(request), WxPayTradeType.NATIVE, orderNum, openid, "微信支付", wxPayCallBackUrl);
							IServiceRequest serviceRequest = (IServiceRequest)new HttpRequest();
							String prepayReturnStr = "";
							boolean prepayFlag = true;
							String prepayFlagMsg = "";
							try {
								prepayReturnStr = serviceRequest.sendPost(Configure.PREPAY_URL, payReqData);
								//
								if(StringUtils.isNotEmpty(prepayReturnStr)){
									log.info("统一下单返回的数据：" + prepayReturnStr);
									ScanPayRespData prepayResp = (ScanPayRespData)Util.getObjectFromXML(prepayReturnStr, ScanPayRespData.class);
									String returnCode = prepayResp.getReturn_code();
									String resultCode = prepayResp.getResult_code();
									if(WxPayReturnCode.FAIL.eq(returnCode)){
										//支付失败
										String returnMsg = prepayResp.getReturn_msg();
										log.error(returnMsg);
										prepayFlag = false;
										prepayFlagMsg = returnMsg;
									} else {
										if(WxPayResultCode.SUCCESS.eq(resultCode)){
											//支付成功
											String prepay_id = prepayResp.getPrepay_id();
											if(StringUtils.isNotEmpty(prepay_id)){
												//存储预支付id
												memOrder.setPrepayId(prepay_id);
												orderService.updateByKeySelective(memOrder);
											}
											payRespData = new ScanPayRespData(WxPayReturnCode.SUCCESS, "OK", prepayResp.getNonce_str(), prepay_id, WxPayResultCode.SUCCESS, "OK");
											outputXml = Util.getXmlFromObject(payRespData);
										} else {
											//支付失败
											prepayFlag = false;
											String errCode = prepayResp.getErr_code();
											String errCodeDes = prepayResp.getErr_code_des();
											prepayFlagMsg = errCodeDes;
											log.error(errCode);
											log.error(errCodeDes);
										}
									}
								}
							} catch (Exception e) {
								prepayFlag = false;
							}
							if(!prepayFlag){
								outputXml = Util.getXmlFromObject(new ScanPayRespData(WxPayReturnCode.FAIL, "统一下单失败 " + prepayFlagMsg));
							}
						}
					}
				} else {
					//签名错误
					outputXml = Util.getXmlFromObject(new ScanPayRespData(WxPayReturnCode.FAIL, "签名错误 "));
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(inputStream != null){
				inputStream.close();
			}
		}
		log.debug(outputXml);
		ajaxOutput(response, outputXml);
	}

	/**
	 * 微信支付结束后异步通知商户支付结果
	 * *
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/pay", method = RequestMethod.POST)
	public void pay(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String outputXml = "";
		ScanPayRespData payRespData = null;
		//读取参数
		InputStream inputStream = null;
		try {
			inputStream = request.getInputStream();
			String respStr = Util.inputStreamToString(inputStream);
			if(StringUtils.isNotEmpty(respStr)){
				boolean checkSign = Signature.checkIsSignValidFromResponseString(respStr);
				if(checkSign){
					ScanPayRespData resData = (ScanPayRespData)Util.getObjectFromXML(respStr, ScanPayRespData.class);
					//生成预支付ID
					String transaction_id = resData.getTransaction_id();
					String out_trade_no = resData.getOut_trade_no();
					String mch_id = resData.getMch_id();
					if(StringUtils.isEmpty(transaction_id)){
						payRespData = new ScanPayRespData(WxPayReturnCode.FAIL, "支付结果中微信订单号不存在");
					} else if(StringUtils.isEmpty(out_trade_no)){
						payRespData = new ScanPayRespData(WxPayReturnCode.FAIL, "支付结果中商户订单号不存在");
					} else {
						//验证商户订单号
						//商户系统生成订单数据
						try {
							lock.lock();
							AgentOrder memOrder = orderService.getByOrderNum(out_trade_no);
							if(memOrder != null){
								Integer status = memOrder.getStatus();
								if(status != 3){
									memOrder.setStatus(3);
									Date paytime = new Date();
									memOrder.setPayTime(paytime);
									orderService.updateByKeySelective(memOrder);
									//保存交易记录
									AgentOrderTrans memOrderTrans = oderTransService.getByOrderId(out_trade_no);
									if(memOrderTrans == null){
										memOrderTrans = new AgentOrderTrans();
										memOrderTrans.setOrderId(out_trade_no);
										memOrderTrans.setBankCode("");
										memOrderTrans.setBankName("");
										memOrderTrans.setTransDate(paytime);
										memOrderTrans.setTransType("0001");
										memOrderTrans.setTransPlatformType(3);
										memOrderTrans.setTransAccount("");
										memOrderTrans.setTransMoney(memOrder.getTotalPrice());
										memOrderTrans.setMerId(mch_id);
										memOrderTrans.setCurrencyId("156");
										memOrderTrans.setTransStatus(WxPayReturnCode.SUCCESS.name());
										memOrderTrans.setTransId(transaction_id);
										oderTransService.insert(memOrderTrans);
									}
									//发送短信通知
									Long userId = memOrder.getUserId();
									if(userId != null){
										AgentUser agentUser = agentUserService.selectBySn(userId, AgentUser.class);
										Map<String, String> parame = new HashMap<String, String>();
										parame.put("userName", agentUser.getUserName());
										parame.put("userMobile",agentUser.getMobilePhone());
										parame.put("orderNum", memOrder.getOrderNum());
										parame.put("orderPrice", TCUtil.sv(memOrder.getTotalPrice().doubleValue()));
										parame.put("payTypeName", "微信扫码");
										smsService.send(SmsSendType.ORDER_PAY_OK, parame);
									}
								}
								payRespData = new ScanPayRespData(WxPayReturnCode.SUCCESS, "OK");
							}
						} catch (Exception e) {
							e.printStackTrace();
						} finally {
							lock.unlock();
						}
					}
					outputXml = Util.getXmlFromObject(payRespData);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(inputStream != null){
				inputStream.close();
			}
		}
		ajaxOutput(response, outputXml);
	}
}
