package com.focustech.focus3d.agent.model;

import com.focustech.focus3d.agent.model.ibator.BizAgentProduct;
import com.focustech.focus3d.agent.model.ibator.BizAgentProductCriteria;
/**
 *
 * *
 * @author lihaijun
 *
 */
public class AgentProduct extends BizAgentProduct<AgentProduct, BizAgentProductCriteria> implements CommonModel {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String encryptSn;

	public String getEncryptSn() {
		return encryptSn;
	}

	public void setEncryptSn(String encryptSn) {
		this.encryptSn = encryptSn;
	}



}
