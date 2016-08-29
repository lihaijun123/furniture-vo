package com.focustech.focus3d.agent.order.dao;

import org.springframework.stereotype.Repository;

import com.focustech.focus3d.agent.dao.CommonDao;
import com.focustech.focus3d.agent.model.AgentOrderLogtc;
import com.focustech.focus3d.agent.model.ibator.BizAgentOrderLogtcCriteria;
/**
 *
 * *
 * @author lihaijun
 *
 */
@Repository
public class AgentOrderLogtcDao extends CommonDao {
	/**
	 *
	 * *
	 * @param orderId
	 * @return
	 */
	public AgentOrderLogtc getByOrderId(long orderId) {
		BizAgentOrderLogtcCriteria criteria = new BizAgentOrderLogtcCriteria();
		criteria.createCriteria().andOrderIdEqualTo(orderId);
		return selectFirstByExample(criteria, AgentOrderLogtc.class);
	}

}
