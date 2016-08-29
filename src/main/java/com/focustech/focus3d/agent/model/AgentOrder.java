package com.focustech.focus3d.agent.model;

import java.util.List;

import com.focustech.focus3d.agent.model.ibator.BizAgentOrder;
import com.focustech.focus3d.agent.model.ibator.BizAgentOrderCriteria;
import com.focustech.focus3d.agent.utils.AutoArrayList;
/**
 *
 * *
 * @author lihaijun
 *
 */
public class AgentOrder extends BizAgentOrder<AgentOrder, BizAgentOrderCriteria> implements CommonModel {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	//订单项
	private List<AgentOrderItem> items = new AutoArrayList<AgentOrderItem>(AgentOrder.class);
	//收货地址
	private ReceiveAddress receiveAddress;
	//物流信息
	private AgentOrderLogtc orderLogtc;

	private String encryptSn;

	private String qrFileUrl;

	private String subUserId;

	public List<AgentOrderItem> getItems() {
		return items;
	}

	public void setItems(List<AgentOrderItem> items) {
		this.items = items;
	}

	public ReceiveAddress getReceiveAddress() {
		return receiveAddress;
	}

	public void setReceiveAddress(ReceiveAddress receiveAddress) {
		this.receiveAddress = receiveAddress;
	}

	public String getEncryptSn() {
		return encryptSn;
	}

	public void setEncryptSn(String encryptSn) {
		this.encryptSn = encryptSn;
	}

	public String getQrFileUrl() {
		return qrFileUrl;
	}

	public void setQrFileUrl(String qrFileUrl) {
		this.qrFileUrl = qrFileUrl;
	}

	public AgentOrderLogtc getOrderLogtc() {
		return orderLogtc;
	}

	public void setOrderLogtc(AgentOrderLogtc orderLogtc) {
		this.orderLogtc = orderLogtc;
	}

	public String getSubUserId() {
		return subUserId;
	}

	public void setSubUserId(String subUserId) {
		this.subUserId = subUserId;
	}

}
