package com.focustech.focus3d.agent.model;

import com.focustech.focus3d.agent.model.ibator.BizAgentOrderItem;
import com.focustech.focus3d.agent.model.ibator.BizAgentOrderItemCriteria;
/**
 *
 * *
 * @author lihaijun
 *
 */

public class AgentOrderItem extends BizAgentOrderItem<AgentOrderItem, BizAgentOrderItemCriteria> implements CommonModel {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String encryptProductId;

	private AgentProduct product;

	public String getEncryptProductId() {
		return encryptProductId;
	}

	public void setEncryptProductId(String encryptProductId) {
		this.encryptProductId = encryptProductId;
	}

	public AgentProduct getProduct() {
		return product;
	}

	public void setProduct(AgentProduct product) {
		this.product = product;
	}

}
