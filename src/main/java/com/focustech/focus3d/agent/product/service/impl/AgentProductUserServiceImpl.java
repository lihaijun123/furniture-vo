package com.focustech.focus3d.agent.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.focustech.focus3d.agent.dao.CommonDao;
import com.focustech.focus3d.agent.model.AgentUserProduct;
import com.focustech.focus3d.agent.product.dao.AgentProductUserDao;
import com.focustech.focus3d.agent.product.service.AgentProductUserService;
import com.focustech.focus3d.agent.service.impl.CommonServiceTemplate;
/**
 *
 * *
 * @author lihaijun
 *
 */
@Service
@Transactional
public class AgentProductUserServiceImpl extends CommonServiceTemplate<AgentUserProduct> implements AgentProductUserService<AgentUserProduct> {
	@Autowired
	private AgentProductUserDao agentProductUserDao;
	@Override
	public CommonDao getDao() {
		return agentProductUserDao;
	}
	/**
	 *
	 * *
	 * @param userId
	 * @return
	 */
	public List<AgentUserProduct> getListByUserId(long userId){
		return agentProductUserDao.getListByUserId(userId);
	}
}
