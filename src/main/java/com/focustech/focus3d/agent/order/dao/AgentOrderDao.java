package com.focustech.focus3d.agent.order.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.focustech.focus3d.agent.dao.CommonDao;
import com.focustech.focus3d.agent.model.AgentOrder;
import com.focustech.focus3d.agent.model.ibator.BizAgentOrderCriteria;
import com.focustech.focus3d.agent.model.ibator.BizAgentOrderCriteria.Criteria;
/**
 *
 * *
 * @author lihaijun
 *
 */
@Repository
public class AgentOrderDao extends CommonDao {
	/**
	 *
	 * *
	 * @param userId
	 * @param status
	 * @return
	 */
	public List<AgentOrder> getList(long userId, Integer status) {
		BizAgentOrderCriteria criteria = new BizAgentOrderCriteria();
		Criteria andUserIdEqualTo = criteria.createCriteria().andUserIdEqualTo(userId);
		if(status != null && status > 0){
			andUserIdEqualTo.andStatusEqualTo(status);
		}
		criteria.setOrderByClause(" ORDER_TIME desc");
		return selectByCriteria(criteria, AgentOrder.class);
	}
	/**
	 *
	 * *
	 * @param orderNum
	 * @return
	 */
	public AgentOrder getByOrderNum(String orderNum) {
		BizAgentOrderCriteria criteria = new BizAgentOrderCriteria();
		criteria.createCriteria().andOrderNumEqualTo(orderNum);
		return selectFirstByExample(criteria, AgentOrder.class);
	}


}
