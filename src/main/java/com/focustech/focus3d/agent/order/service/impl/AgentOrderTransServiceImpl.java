package com.focustech.focus3d.agent.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.focustech.focus3d.agent.dao.CommonDao;
import com.focustech.focus3d.agent.model.AgentOrderTrans;
import com.focustech.focus3d.agent.order.dao.AgentOrderTransDao;
import com.focustech.focus3d.agent.order.service.AgentOrderTransService;
import com.focustech.focus3d.agent.service.impl.CommonServiceTemplate;
/**
 *
 * *
 * @author lihaijun
 *
 */
@Service
@Transactional
public class AgentOrderTransServiceImpl  extends CommonServiceTemplate<AgentOrderTrans> implements AgentOrderTransService<AgentOrderTrans>{
	@Autowired
	private AgentOrderTransDao agentOrderTransDao;
	@Override
	public CommonDao getDao() {
		return agentOrderTransDao;
	}
	@Override
	public AgentOrderTrans getByOrderId(String orderId) {
		return agentOrderTransDao.getByOrderId(orderId);
	}

}
