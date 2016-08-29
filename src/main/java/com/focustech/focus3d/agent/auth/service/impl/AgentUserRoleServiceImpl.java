package com.focustech.focus3d.agent.auth.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.focustech.focus3d.agent.auth.dao.AgentUserRoleDao;
import com.focustech.focus3d.agent.auth.service.AgentUserRoleService;
import com.focustech.focus3d.agent.dao.CommonDao;
import com.focustech.focus3d.agent.model.AgentUserRole;
import com.focustech.focus3d.agent.service.impl.CommonServiceTemplate;

/**
 *
 * *
 * @author lihaijun
 *
 */
@Service
@Transactional
public class AgentUserRoleServiceImpl extends CommonServiceTemplate<AgentUserRole> implements AgentUserRoleService<AgentUserRole> {
	@Autowired
	private AgentUserRoleDao agentUserRoleDao;
	@Override
	public CommonDao getDao() {
		return agentUserRoleDao;
	}
	@Override
	public List<AgentUserRole> getListByUserId(Long userId) {
		return agentUserRoleDao.getListByUserId(userId);
	}

}
