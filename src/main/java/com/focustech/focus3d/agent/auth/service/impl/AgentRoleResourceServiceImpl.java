package com.focustech.focus3d.agent.auth.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.focustech.focus3d.agent.auth.dao.AgentRoleResourceDao;
import com.focustech.focus3d.agent.auth.service.AgentRoleResourceService;
import com.focustech.focus3d.agent.dao.CommonDao;
import com.focustech.focus3d.agent.model.AgentRoleResource;
import com.focustech.focus3d.agent.service.impl.CommonServiceTemplate;
/**
 *
 * *
 * @author lihaijun
 *
 */
@Service
@Transactional
public class AgentRoleResourceServiceImpl extends CommonServiceTemplate<AgentRoleResource> implements AgentRoleResourceService<AgentRoleResource> {
	@Autowired
	private AgentRoleResourceDao agentRoleResourceDao;
	@Override
	public CommonDao getDao() {
		return agentRoleResourceDao;
	}
	@Override
	public List<AgentRoleResource> getListByRoleId(long roleId) {
		return agentRoleResourceDao.getListByRoleId(roleId);
	}

}
