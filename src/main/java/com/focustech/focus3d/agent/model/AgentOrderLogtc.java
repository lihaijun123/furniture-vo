package com.focustech.focus3d.agent.model;

import com.focustech.focus3d.agent.model.ibator.BizAgentOrderLogtc;
import com.focustech.focus3d.agent.model.ibator.BizAgentOrderLogtcCriteria;
/**
 *
 * *
 * @author lihaijun
 *
 */
public class AgentOrderLogtc extends BizAgentOrderLogtc<AgentOrderLogtc, BizAgentOrderLogtcCriteria> implements CommonModel {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String subUserId;

	public String getSubUserId() {
		return subUserId;
	}

	public void setSubUserId(String subUserId) {
		this.subUserId = subUserId;
	}



}
