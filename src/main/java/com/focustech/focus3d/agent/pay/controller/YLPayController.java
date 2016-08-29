package com.focustech.focus3d.agent.pay.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
import com.focustech.focus3d.pay.protocol.PayStatusCode;
import com.focustech.focus3d.pay.service.PayService;
import com.focustech.focus3d.pay.utils.PayUtils;
import com.focustech.focus3d.pay.wx.type.WxPayReturnCode;
import com.focustech.focus3d.pay.yl.protocol.NetPayResponse;

/**
 *
 *
 * *
 * @author lihaijun
 *
 */
@Controller
@RequestMapping(value = "/ylpay/")
public class YLPayController extends CommonController{
	private static final Logger log = LoggerFactory.getLogger(YLPayController.class);
	@Autowired
	private PayService payService;
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
	/**
    *
    * *后端响应
    * @param service
    * @param id
    * @param mqId
    * @param transactionId
    * @param request
    * @param model
	 * @throws UnsupportedEncodingException
    */
   @SuppressWarnings("rawtypes")
   @RequestMapping(value = "/backCallback")
	public String backCallback(NetPayResponse payResponse, Model model, HttpServletRequest req) throws UnsupportedEncodingException {
	   log.info("银联后端响应处理");
	   Enumeration parameterNames = req.getParameterNames();
	   while(parameterNames.hasMoreElements()){
		   String key = TCUtil.sv(parameterNames.nextElement());
		   if(key.contains("OrderStatus")){
			   String value = req.getParameter(key);
			   payResponse.setOrderStatus(value);
			   break;
		   }
	   }
	   boolean verifyPayResponse = payService.verifyPayResponse(payResponse, true);
	   	String view = "sucess";
	   	String orderId = "";
		if (!verifyPayResponse)
		{
			view = "error";
		}
		else
		{
			String status = payResponse.getOrderStatus();
			if(PayStatusCode.NET_PAY_RESPONSE_SUCCESS_CODE.equals(status)){
				orderId = processCallback(payResponse);
			} else {
				view = "error";
			}
		}
		return redirect(siteDomain + "/order/paysult?orderId=" + orderId + "&status=" + view);
   }


	/**
    *
    * 前端响应
    * *
    * @param modelMap
    * @param request
    * @return
    */
   @RequestMapping(value = "/frontCallback")
   public String pageCallback(NetPayResponse payResponse, HttpServletRequest request, Model model, HttpServletRequest req) {
	   	log.info("银联前端响应处理");
		boolean verifyPayResponse = payService.verifyPayResponse(payResponse);
	   	String view = "sucess";
	   	String orderId = "";
		if (!verifyPayResponse)
		{
			view = "error";
		}
		else
		{
			String status = payResponse.getOrderStatus();
			if(PayStatusCode.NET_PAY_RESPONSE_SUCCESS_CODE.equals(status)){
				orderId = processCallback(payResponse);
			} else {
				view = "error";
			}
		}
		return redirect(siteDomain + "/order/paysult?orderId=" + orderId + "&status=" + view);
   }

   /**
   *
   * *
   * @param payResponse
   * @return
   */
	private String processCallback(NetPayResponse payResponse) {
		String orderEncryptSn = "";
		try{
			lock.lock();
			String orderno = payResponse.getMerOrderNo();
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
						memOrderTrans.setBankCode(PayUtils.getPriv1(payResponse.getMerResv(), "bankCode"));
						memOrderTrans.setBankName(memOrderTrans.getBankCode());
						memOrderTrans.setTransDate(paytime);
						memOrderTrans.setTransType(payResponse.getTranType());
						memOrderTrans.setTransPlatformType(TCUtil.iv(PayPlatformType.ZGYL.getCode()));
						memOrderTrans.setTransAccount("");
						memOrderTrans.setTransMoney(new BigDecimal(PayUtils.convertFen2Yuan(payResponse.getOrderAmt())));
						memOrderTrans.setMerId(payResponse.getMerId());
						memOrderTrans.setCurrencyId(payResponse.getCurryNo());
						memOrderTrans.setTransStatus(WxPayReturnCode.SUCCESS.name());
						memOrderTrans.setTransId("");
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
						parame.put("payTypeName", "中国银联");
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
