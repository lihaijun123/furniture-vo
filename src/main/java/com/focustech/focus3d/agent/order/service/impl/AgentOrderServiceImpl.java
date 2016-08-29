package com.focustech.focus3d.agent.order.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.focustech.common.utils.EncryptUtil;
import com.focustech.common.utils.StringUtils;
import com.focustech.common.utils.TCUtil;
import com.focustech.focus3d.agent.dao.CommonDao;
import com.focustech.focus3d.agent.login.dao.AgentUserDao;
import com.focustech.focus3d.agent.model.AgentOrder;
import com.focustech.focus3d.agent.model.AgentOrderItem;
import com.focustech.focus3d.agent.model.AgentOrderLogtc;
import com.focustech.focus3d.agent.model.AgentUser;
import com.focustech.focus3d.agent.model.ReceiveAddress;
import com.focustech.focus3d.agent.order.dao.AgentOrderDao;
import com.focustech.focus3d.agent.order.dao.AgentOrderItemDao;
import com.focustech.focus3d.agent.order.dao.AgentOrderLogtcDao;
import com.focustech.focus3d.agent.order.dao.ReceiveAddressDao;
import com.focustech.focus3d.agent.order.service.AgentOrderService;
import com.focustech.focus3d.agent.service.impl.CommonServiceTemplate;
import com.focustech.focus3d.agent.sms.service.SmsService;
import com.focustech.focus3d.agent.utils.SmsSendType;
import com.focustech.focus3d.pay.orderno.OrderNumber;
/**
 *
 * *
 * @author lihaijun
 *
 */
@Service
@Transactional
public class AgentOrderServiceImpl extends CommonServiceTemplate<AgentOrder> implements AgentOrderService<AgentOrder> {
	@Autowired
	private AgentOrderDao agentOrderDao;
	@Autowired
	private AgentOrderItemDao itemDao;
	@Autowired
	private OrderNumber orderNumber;
	@Autowired
	private ReceiveAddressDao receiveAddressDao;
	@Autowired
	private AgentOrderLogtcDao agentOrderLogtcDao;
	@Autowired
	private AgentUserDao agentUserDao;
	@Autowired
	private SmsService smsService;
	@Override
	public CommonDao getDao() {
		return agentOrderDao;
	}
	@Override
	public AgentOrder save(AgentOrder t) {
		//保存收货信息
		receiveAddressDao.deleteByUserId(t.getUserId());
		ReceiveAddress receiveAddress = t.getReceiveAddress();
		receiveAddressDao.insert(receiveAddress);
		//保存订单
		String orderNum = orderNumber.getNumber();
		while(agentOrderDao.getByOrderNum(orderNum) != null){
			orderNum = orderNumber.getNumber();
		}
		if(StringUtils.isNotEmpty(orderNum)){
			List<AgentOrderItem> items = t.getItems();
			double totalPrice = 0;
			for (AgentOrderItem itm : items) {
				totalPrice += itm.getItemPrice().multiply(new BigDecimal(itm.getItemAmount())).doubleValue();
			}
			if(totalPrice > 0){
				t.setOrderNum(orderNum);
				t.setOrderTime(new Date());
				t.setStatus(2);
				t.setTotalPrice(BigDecimal.valueOf(totalPrice));
				Integer isInvoice = t.getIsInvoice();
				t.setIsInvoice(isInvoice != null && isInvoice > 0 ? 1 : 0);
				if(t.getIsInvoice() > 0){
					String invoiceHead = t.getInvoiceHead();
					if(StringUtils.isNotEmpty(invoiceHead)){
						t.setInvoiceType(1);
					} else {
						String invoiceCompanyName = t.getInvoiceCompanyName();
						String invoiceTaxpayerId = t.getInvoiceTaxpayerId();
						if(StringUtils.isNotEmpty(invoiceCompanyName) && StringUtils.isNotEmpty(invoiceTaxpayerId)){
							t.setInvoiceType(2);
						}
					}
				}
				Long revAddressSn = receiveAddress.getSn();
				if(revAddressSn != null){
					t.setReceiveAddressId(revAddressSn);
					agentOrderDao.insert(t);
					Long orderSn = t.getSn();
					if(orderSn != null){
						//保存订单项
						for (AgentOrderItem itm : items) {
							itm.setOrderId(orderSn);
							String encryptProductId = itm.getEncryptProductId();
							long prodId = 0L;
							try {
								prodId = EncryptUtil.decode(encryptProductId);
							} catch (Exception e) {
								e.printStackTrace();
							}
							if(prodId > 0){
								itm.setProductId(prodId);
								itemDao.insert(itm);
							}
						}
						//保存物流信息
						AgentOrderLogtc orderLogtc = t.getOrderLogtc();
						orderLogtc.setOrderId(orderSn);
						orderLogtc.setIsSend(0);
						orderLogtc.setIsReceive(0);
						Integer logicType = orderLogtc.getLogicType();
						if(logicType == null){
							orderLogtc.setLogicType(1);//默认快递
						}
						agentOrderLogtcDao.insert(orderLogtc);
						//发短信通知
						AgentUser agentUser = agentUserDao.selectByKey(t.getUserId(), AgentUser.class);
						Map<String, String> parame = new HashMap<String, String>();
						parame.put("userName", agentUser.getUserName());
						parame.put("userMobile",agentUser.getMobilePhone());
						parame.put("orderNum", t.getOrderNum());
						parame.put("orderPrice", TCUtil.sv(t.getTotalPrice().doubleValue()));
						smsService.send(SmsSendType.ORDER_SUBMIT, parame);
					}

				}
			}
		}
		return t;
	}
	@Override
	public List<AgentOrder> getList(long userId, Integer status) {
		List<AgentOrder> orderList = agentOrderDao.getList(userId, status);
		for (AgentOrder agentOrder : orderList) {
			agentOrder.setEncryptSn(EncryptUtil.encode(agentOrder.getSn()));
			AgentOrderLogtc orderLogtc = agentOrderLogtcDao.getByOrderId(agentOrder.getSn());
			if(orderLogtc != null){
				agentOrder.setOrderLogtc(orderLogtc);
			}
		}
		return orderList;
	}
	@Override
	public AgentOrder getByOrderNum(String orderNum) {
		return agentOrderDao.getByOrderNum(orderNum);
	}

	@Override
	public AgentOrder saveSub(AgentOrder t) {
		//设置扩展信息
		//String extend = t.getExtend();
		//String subItemPrice = t.getSubItemPrice();
		//String subItemAmount = t.getSubItemAmount();
		//t.setExtend(extend + " 数量：" + subItemAmount + " 单价：" + subItemPrice);
		//保存收货信息
		//receiveAddressDao.deleteByUserId(t.getUserId());
		ReceiveAddress receiveAddress = t.getReceiveAddress();
		Long rvAddressSn = receiveAddress.getSn();
		if(rvAddressSn != null){
			receiveAddressDao.updateByKeySelective(receiveAddress);
		} else {
			receiveAddressDao.insert(receiveAddress);
		}
		//保存订单
		String orderNum = orderNumber.getNumber();
		while(agentOrderDao.getByOrderNum(orderNum) != null){
			orderNum = orderNumber.getNumber();
		}
		if(StringUtils.isNotEmpty(orderNum)){
			List<AgentOrderItem> items = t.getItems();
			double totalPrice = 0;
			for (AgentOrderItem itm : items) {
				totalPrice += itm.getItemPrice().multiply(new BigDecimal(itm.getItemAmount())).doubleValue();
			}
			if(totalPrice > 0){
				//是否有运费
				BigDecimal logtcPrice = t.getOrderLogtc().getLogtcPrice();
				if(logtcPrice != null){
					totalPrice += logtcPrice.doubleValue();
				}
				t.setOrderNum(orderNum);
				t.setOrderTime(new Date());
				t.setStatus(2);
				t.setTotalPrice(BigDecimal.valueOf(totalPrice));
				Integer isInvoice = t.getIsInvoice();
				t.setIsInvoice(isInvoice != null && isInvoice > 0 ? 1 : 0);
				if(t.getIsInvoice() > 0){
					String invoiceHead = t.getInvoiceHead();
					if(StringUtils.isNotEmpty(invoiceHead)){
						t.setInvoiceType(1);
					} else {
						String invoiceCompanyName = t.getInvoiceCompanyName();
						String invoiceTaxpayerId = t.getInvoiceTaxpayerId();
						if(StringUtils.isNotEmpty(invoiceCompanyName) && StringUtils.isNotEmpty(invoiceTaxpayerId)){
							t.setInvoiceType(2);
						}
					}
				}
				Long revAddressSn = receiveAddress.getSn();
				if(revAddressSn != null){
					t.setReceiveAddressId(revAddressSn);
					agentOrderDao.insert(t);
					Long orderSn = t.getSn();
					if(orderSn != null){
						//保存订单项
						for (AgentOrderItem itm : items) {
							itm.setOrderId(orderSn);
							itm.setProductId(-1L);
							itemDao.insert(itm);
						}
						//保存物流信息
						AgentOrderLogtc orderLogtc = t.getOrderLogtc();
						orderLogtc.setOrderId(orderSn);
						orderLogtc.setIsSend(0);
						orderLogtc.setIsReceive(0);
						Integer logicType = orderLogtc.getLogicType();
						if(logicType == null){
							orderLogtc.setLogicType(1);//默认快递
						}
						agentOrderLogtcDao.insert(orderLogtc);
						//发短信通知
						AgentUser agentUser = agentUserDao.selectByKey(t.getUserId(), AgentUser.class);
						Map<String, String> parame = new HashMap<String, String>();
						parame.put("userName", agentUser.getUserName());
						parame.put("userMobile",agentUser.getMobilePhone());
						parame.put("orderNum", t.getOrderNum());
						parame.put("orderPrice", TCUtil.sv(t.getTotalPrice().doubleValue()));
						smsService.send(SmsSendType.ORDER_SUBMIT, parame);
					}
				}
			}
		}
		return t;
	}

}
