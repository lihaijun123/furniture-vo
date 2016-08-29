package com.focustech.focus3d.agent.login.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.focustech.focus3d.agent.dao.CommonDao;
import com.focustech.focus3d.agent.login.dao.AgentLoginDao;
import com.focustech.focus3d.agent.login.service.AgentLoginService;
import com.focustech.focus3d.agent.model.AgentLogin;
import com.focustech.focus3d.agent.service.impl.CommonServiceTemplate;
/**
 *
 * *
 * @author lihaijun
 *
 */
@Service
@Transactional
public class AgentLoginServiceImpl extends CommonServiceTemplate<AgentLogin> implements AgentLoginService<AgentLogin> {
	@Autowired
	private AgentLoginDao agentLoginDao;

	@Override
	public CommonDao getDao() {
		return agentLoginDao;
	}

	@Override
	public AgentLogin select(String loginName, String password) {
		return agentLoginDao.select(loginName, password);
	}

	@Override
	public AgentLogin select(String loginName) {

		return agentLoginDao.select(loginName);
	}


}
