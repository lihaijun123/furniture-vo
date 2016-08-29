package com.focustech.focus3d.agent.order.dao;

import org.springframework.stereotype.Repository;

import com.focustech.focus3d.agent.dao.CommonDao;
import com.focustech.focus3d.agent.model.AgentOrderTrans;
import com.focustech.focus3d.agent.model.ibator.BizAgentOrderTransCriteria;
/**
 *
 * *
 * @author lihaijun
 *
 */
@Repository
public class AgentOrderTransDao extends CommonDao {

	public AgentOrderTrans getByOrderId(String orderId) {
		BizAgentOrderTransCriteria criteria = new BizAgentOrderTransCriteria();
		criteria.createCriteria().andOrderIdEqualTo(orderId);
		return selectFirstByExample(criteria, AgentOrderTrans.class);
	}

}
