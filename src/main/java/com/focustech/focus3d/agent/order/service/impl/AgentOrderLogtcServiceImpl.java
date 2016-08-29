package com.focustech.focus3d.agent.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.focustech.focus3d.agent.dao.CommonDao;
import com.focustech.focus3d.agent.model.AgentOrderLogtc;
import com.focustech.focus3d.agent.order.dao.AgentOrderLogtcDao;
import com.focustech.focus3d.agent.order.service.AgentOrderLogtcService;
import com.focustech.focus3d.agent.service.impl.CommonServiceTemplate;
/**
 *
 * *
 * @author lihaijun
 *
 */
@Service
@Transactional
public class AgentOrderLogtcServiceImpl extends CommonServiceTemplate<AgentOrderLogtc> implements AgentOrderLogtcService<AgentOrderLogtc> {
	@Autowired
	private AgentOrderLogtcDao agentOrderLogtcDao;
	@Override
	public CommonDao getDao() {
		return agentOrderLogtcDao;
	}
	@Override
	public AgentOrderLogtc getByOrderId(long orderId) {
		return agentOrderLogtcDao.getByOrderId(orderId);
	}

}
