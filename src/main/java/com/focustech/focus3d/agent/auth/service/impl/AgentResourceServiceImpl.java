package com.focustech.focus3d.agent.auth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.focustech.focus3d.agent.auth.dao.AgentResourceDao;
import com.focustech.focus3d.agent.auth.service.AgentResourceService;
import com.focustech.focus3d.agent.dao.CommonDao;
import com.focustech.focus3d.agent.model.AgentResource;
import com.focustech.focus3d.agent.service.impl.CommonServiceTemplate;
/**
 *
 * *
 * @author lihaijun
 *
 */
@Service
@Transactional
public class AgentResourceServiceImpl extends CommonServiceTemplate<AgentResource> implements AgentResourceService<AgentResource> {
	@Autowired
	private AgentResourceDao agentResourceDao;
	@Override
	public CommonDao getDao() {
		return agentResourceDao;
	}

}
