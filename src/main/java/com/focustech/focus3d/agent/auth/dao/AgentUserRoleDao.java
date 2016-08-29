package com.focustech.focus3d.agent.auth.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.focustech.focus3d.agent.dao.CommonDao;
import com.focustech.focus3d.agent.model.AgentUserRole;
import com.focustech.focus3d.agent.model.ibator.BizAgentUserRoleCriteria;
/**
 *
 *
 * *
 * @author lihaijun
 *
 */
@Repository
public class AgentUserRoleDao extends CommonDao {
	/**
	 *
	 * *
	 * @param userId
	 * @return
	 */
	public List<AgentUserRole> getListByUserId(Long userId) {
		BizAgentUserRoleCriteria criteria = new BizAgentUserRoleCriteria();
		criteria.createCriteria().andUserSnEqualTo(userId);
		return selectByCriteria(criteria, AgentUserRole.class);
	}
	/**
	 *
	 * *
	 * @param userSn
	 */
	public void deleteByUserSn(long userSn){
		BizAgentUserRoleCriteria criteria = new BizAgentUserRoleCriteria();
		criteria.createCriteria().andUserSnEqualTo(userSn);
		deleteByCriteria(criteria);
	}

}
