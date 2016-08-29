package com.focustech.focus3d.agent.login.dao;

import org.springframework.stereotype.Repository;

import com.focustech.focus3d.agent.dao.CommonDao;
import com.focustech.focus3d.agent.model.AgentLogin;
import com.focustech.focus3d.agent.model.ibator.BizAgentLoginCriteria;
/**
 *
 * *
 * @author lihaijun
 *
 */
@Repository
public class AgentLoginDao extends CommonDao {
	/**
	 *
	 * *
	 * @param loginName
	 * @param password
	 * @return
	 */
	public AgentLogin select(String loginName, String password) {
		BizAgentLoginCriteria criteria = new BizAgentLoginCriteria();
		criteria.createCriteria().andLoginNameEqualTo(loginName).andPasswordEqualTo(password);
		return selectFirstByExample(criteria, AgentLogin.class);
	}
	/**
	 *
	 * *
	 * @param loginName
	 * @return
	 */
	public AgentLogin select(String loginName) {
		BizAgentLoginCriteria criteria = new BizAgentLoginCriteria();
		criteria.createCriteria().andLoginNameEqualTo(loginName);
		return selectFirstByExample(criteria, AgentLogin.class);
	}
	/**
	 *
	 * *
	 * @param userId
	 * @return
	 */
	public AgentLogin getByUserId(long userId){
		BizAgentLoginCriteria criteria = new BizAgentLoginCriteria();
		criteria.createCriteria().andUserIdEqualTo(userId);
		return selectFirstByExample(criteria, AgentLogin.class);
	}

}
