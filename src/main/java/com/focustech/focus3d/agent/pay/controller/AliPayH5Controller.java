package com.focustech.focus3d.agent.pay.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.focustech.common.utils.EncryptUtil;
import com.focustech.common.utils.TCUtil;
import com.focustech.focus3d.agent.common.controller.CommonController;
import com.focustech.focus3d.agent.model.AgentOrder;
import com.focustech.focus3d.agent.model.AgentOrderTrans;
import com.focustech.focus3d.agent.model.AgentUser;
import com.focustech.focus3d.agent.order.service.AgentOrderService;
import com.focustech.focus3d.agent.order.service.AgentOrderTransService;
import com.focustech.focus3d.agent.sms.service.SmsService;
import com.focustech.focus3d.agent.user.service.AgentUserService;
import com.focustech.focus3d.agent.utils.SmsSendType;
import com.focustech.focus3d.pay.PayPlatformType;
import com.focustech.focus3d.pay.ali.config.AlipayConfig;
import com.focustech.focus3d.pay.ali.protocol.AlipayRespData;
import com.focustech.focus3d.pay.ali.util.AlipayNotify;
import com.focustech.focus3d.pay.wx.type.WxPayReturnCode;

/**
 *
 *
 * *
 * @author lihaijun
 *
 */
@Controller
@RequestMapping(value = "/alipayh5/")
public class AliPayH5Controller extends CommonController{
	private static final Logger log = LoggerFactory.getLogger(AliPayH5Controller.class);
	@Autowired
	private AgentOrderTransService<AgentOrderTrans> oderTransService;
	@Autowired
	private AgentOrderService<AgentOrder> orderService;
	@Value(value = "${agent.site.domain}")
	private String siteDomain;
	private Lock lock = new ReentrantLock();
	@Autowired
	private SmsService smsService;
	@Autowired
	private AgentUserService<AgentUser> agentUserService;
	@Autowired
	private AlipayConfig alipayConfig;
	/**
    *
    * *后端响应
    * @param service
    * @param id
    * @param mqId
    * @param transactionId
    * @param request
    * @param model
	 * @throws IOException
    */
	@RequestMapping(value = "/notifyCallback")
	public void backCallback(AlipayRespData alipayRespData, Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String result = "";
		//获取支付宝POST过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			params.put(name, valueStr);
		}
		//交易状态
		String trade_status = alipayRespData.getTrade_status();
		if(AlipayNotify.verify(params, alipayConfig)){//验证成功
			if(trade_status.equals("TRADE_FINISHED")){

			} else if (trade_status.equals("TRADE_SUCCESS")){
				processCallback(alipayRespData);
			}
			result = "success";
		}else{//验证失败
			result = "fail";
		}
		ajaxOutput(response, result);
   }


	/**
    *
    * 前端响应
    * *
    * @param modelMap
    * @param request
    * @return
	 * @throws UnsupportedEncodingException
    */
   @RequestMapping(value = "/returnCallback", method = RequestMethod.GET)
   public String pageCallback(AlipayRespData alipayRespData, HttpServletRequest request, Model model, HttpServletRequest req) throws UnsupportedEncodingException {
	 //获取支付宝GET过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}
		String orderId = "";
		String view = "sucess";
		//交易状态
		String trade_status = alipayRespData.getTrade_status();
		if(AlipayNotify.verify(params, alipayConfig)){//验证成功
			if(trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")){
				orderId = processCallback(alipayRespData);
			}
		}else{
			view = "error";
		}
	    log.info("银联前端响应处理");
	    return redirect(siteDomain + "/order/paysult?orderId=" + orderId + "&status=" + view);
   }

   /**
   *
   * *
   * @param payResponseParams
   * @return
   */
	private String processCallback(AlipayRespData alipayRespData) {
		String orderEncryptSn = "";
		try{
			lock.lock();
			String orderno = alipayRespData.getOut_trade_no();
			log.info("银联返回订单号：" + orderno);
			AgentOrder memOrder = orderService.getByOrderNum(orderno);
			if(memOrder != null){
				orderEncryptSn = EncryptUtil.encode(memOrder.getSn());
				Integer orderStatus = memOrder.getStatus();
				if(orderStatus != 3){
					memOrder.setStatus(3);
					Date paytime = new Date();
					memOrder.setPayTime(paytime);
					orderService.updateByKeySelective(memOrder);
					log.info("修改订单状态为“已支付”");
					//保存交易记录
					AgentOrderTrans memOrderTrans = oderTransService.getByOrderId(orderno);
					if(memOrderTrans == null){
						memOrderTrans = new AgentOrderTrans();
						memOrderTrans.setOrderId(orderno);
						memOrderTrans.setBankCode("");
						memOrderTrans.setBankName(memOrderTrans.getBankCode());
						memOrderTrans.setTransDate(paytime);
						memOrderTrans.setTransType("0001");
						memOrderTrans.setTransPlatformType(TCUtil.iv(PayPlatformType.ZFB.getCode()));
						memOrderTrans.setTransAccount("");
						memOrderTrans.setTransMoney(memOrder.getTotalPrice());
						memOrderTrans.setMerId(alipayConfig.getPartner());
						memOrderTrans.setCurrencyId("156");
						memOrderTrans.setTransStatus(WxPayReturnCode.SUCCESS.name());
						memOrderTrans.setTransId(alipayRespData.getTrade_no());
						oderTransService.insert(memOrderTrans);
						log.info("数据库不存在交易记录，开始保存这次交易记录");
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
						parame.put("payTypeName", "支付宝");
						smsService.send(SmsSendType.ORDER_PAY_OK, parame);
					}
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		} finally {
			lock.unlock();
		}
		return orderEncryptSn;
	}

	public String getSiteDomain() {
		return siteDomain;
	}

	public void setSiteDomain(String siteDomain) {
		this.siteDomain = siteDomain;
	}

}
