package com.focustech.focus3d.agent.order.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.focustech.focus3d.agent.dao.CommonDao;
import com.focustech.focus3d.agent.model.AgentOrderItem;
import com.focustech.focus3d.agent.model.ibator.BizAgentOrderItemCriteria;
/**
 *
 * *
 * @author lihaijun
 *
 */
@Repository
public class AgentOrderItemDao extends CommonDao{
	/**
	 * *
	 * @param orderId
	 * @return
	 */
	public List<AgentOrderItem> getByOrderId(long orderId) {
		BizAgentOrderItemCriteria criteria = new BizAgentOrderItemCriteria();

		criteria.createCriteria().andOrderIdEqualTo(orderId);

		return selectByCriteria(criteria, AgentOrderItem.class);
	}

}
