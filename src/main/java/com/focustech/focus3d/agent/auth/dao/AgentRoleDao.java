package com.focustech.focus3d.agent.auth.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.focustech.focus3d.agent.dao.CommonDao;
import com.focustech.focus3d.agent.model.AgentRole;
import com.focustech.focus3d.agent.model.ibator.BizAgentRoleCriteria;
/**
 *
 * *
 * @author lihaijun
 *
 */
@Repository
public class AgentRoleDao extends CommonDao {
	/**
	 *
	 * *
	 * @param name
	 * @return
	 */
	public List<AgentRole> getListByParentSn(Long parentSn){
		BizAgentRoleCriteria agentRoleCriteria = new BizAgentRoleCriteria();
		agentRoleCriteria.createCriteria().andParentSnEqualTo(parentSn);
		return selectByCriteria(agentRoleCriteria, AgentRole.class);
	}
}
