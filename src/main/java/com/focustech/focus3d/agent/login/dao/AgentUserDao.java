package com.focustech.focus3d.agent.login.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.focustech.focus3d.agent.dao.CommonDao;
import com.focustech.focus3d.agent.model.AgentUser;
import com.focustech.focus3d.agent.model.ibator.BizAgentUserCriteria;
/**
 *
 * *
 * @author lihaijun
 *
 */
@Repository
public class AgentUserDao extends CommonDao {
	/**
	 *
	 * *
	 * @param mobilePhone
	 * @return
	 */
	public AgentUser selectByMobilePhone(String mobilePhone) {
		BizAgentUserCriteria criteria = new BizAgentUserCriteria();
		criteria.createCriteria().andMobilePhoneEqualTo(mobilePhone);
		return selectFirstByExample(criteria, AgentUser.class);
	}
	/**
	 *
	 * *
	 * @return
	 */
	public List<AgentUser> getAvailableList() {
		BizAgentUserCriteria criteria = new BizAgentUserCriteria();
		criteria.createCriteria().andStatusEqualTo(3);
		return selectByCriteria(criteria, AgentUser.class);
	}
	/**
	 *
	 * *
	 * @param parentSn
	 * @return
	 */
	public List<AgentUser> getSubAccountList(long parentSn) {
		BizAgentUserCriteria criteria = new BizAgentUserCriteria();
		criteria.createCriteria().andStatusEqualTo(3).andParentSnEqualTo(parentSn);
		criteria.setOrderByClause(" add_time desc");
		return selectByCriteria(criteria, AgentUser.class);
	}

}
