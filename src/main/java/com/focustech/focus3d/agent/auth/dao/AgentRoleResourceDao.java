package com.focustech.focus3d.agent.auth.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.focustech.focus3d.agent.dao.CommonDao;
import com.focustech.focus3d.agent.model.AgentRoleResource;
import com.focustech.focus3d.agent.model.ibator.BizAgentRoleResourceCriteria;
/**
 *
 * *
 * @author lihaijun
 *
 */
@Repository
public class AgentRoleResourceDao extends CommonDao {
	/**
	 *
	 * *
	 * @param roleId
	 * @return
	 */
	public List<AgentRoleResource> getListByRoleId(long roleId) {
		BizAgentRoleResourceCriteria criteria = new BizAgentRoleResourceCriteria();
		criteria.createCriteria().andRoleSnEqualTo(roleId);
		return selectByCriteria(criteria, AgentRoleResource.class);
	}

}
