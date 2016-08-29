package com.focustech.focus3d.agent.user.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.focustech.common.utils.EncryptUtil;
import com.focustech.common.utils.ListUtils;
import com.focustech.common.utils.MD5Util;
import com.focustech.common.utils.TCUtil;
import com.focustech.focus3d.agent.auth.dao.AgentRoleDao;
import com.focustech.focus3d.agent.auth.dao.AgentUserRoleDao;
import com.focustech.focus3d.agent.dao.CommonDao;
import com.focustech.focus3d.agent.login.dao.AgentLoginDao;
import com.focustech.focus3d.agent.login.dao.AgentUserDao;
import com.focustech.focus3d.agent.model.AgentLogin;
import com.focustech.focus3d.agent.model.AgentOrder;
import com.focustech.focus3d.agent.model.AgentRole;
import com.focustech.focus3d.agent.model.AgentUser;
import com.focustech.focus3d.agent.model.AgentUserRole;
import com.focustech.focus3d.agent.order.dao.AgentOrderDao;
import com.focustech.focus3d.agent.service.impl.CommonServiceTemplate;
import com.focustech.focus3d.agent.user.service.AgentUserService;
/**
 *
 * *
 * @author lihaijun
 *
 */
@Service
public class AgentUserServiceImpl extends CommonServiceTemplate<AgentUser> implements AgentUserService<AgentUser> {
	private static final Logger log = LoggerFactory.getLogger(AgentUserServiceImpl.class);
	@Autowired
	private AgentUserDao agentUserDao;
	@Autowired
	private AgentLoginDao agentLoginDao;
	@Autowired
	private AgentUserRoleDao agentUserRoleDao;
	@Autowired
	private AgentRoleDao agentRoleDao;
	@Autowired
	private AgentOrderDao agentOrderDao;
	@Override
	public CommonDao getDao() {
		return agentUserDao;
	}

	@Override
	public AgentUser selectByMobilePhone(String mobilePhone) {
		return agentUserDao.selectByMobilePhone(mobilePhone);
	}

	@Override
	public List<AgentUser> getAvailableList() {
		return agentUserDao.getAvailableList();
	}

	@Override
	public void saveSubAccount(AgentUser agentSubUser) {
		Long parentUserSn = agentSubUser.getParentSn();
		if(parentUserSn != null){
			agentSubUser.setStatus(3);
			agentUserDao.insert(agentSubUser);
			//分配登录账户
			Long subUserSn = agentSubUser.getSn();
			AgentLogin agentLogin = agentLoginDao.getByUserId(subUserSn);
			String mobilePhone = agentSubUser.getMobilePhone();
			if(agentLogin == null){
				//创建登录用户
				agentLogin = new AgentLogin();
				agentLogin.setUserId(subUserSn);
				agentLogin.setLoginName(mobilePhone);
				String password = MD5Util.MD5Encode(mobilePhone.substring(5, 11), "");
				agentLogin.setPassword(password);
				agentLogin.setStatus(1);
				agentLoginDao.insert(agentLogin);
			} else {
				//更改登录状态
				agentLogin.setStatus(1);
				//同步登录账号（手机号）
				agentLogin.setLoginName(mobilePhone);
				agentLoginDao.insert(agentLogin);
			}
			//绑定角色信息
			//先删除
			agentUserRoleDao.deleteByUserSn(subUserSn);
			//再保存
			List<AgentUserRole> parentRoles = agentUserRoleDao.getListByUserId(parentUserSn);
			log.info("父亲角色数量：" + parentRoles.size());
			if(ListUtils.isNotEmpty(parentRoles)){
				AgentUserRole parentRole = parentRoles.get(0);
				Long parentFirstRoleSn = parentRole.getRoleSn();
				log.info("父亲角色选择第一个：" + parentFirstRoleSn);
				//选择父亲角色可以分配的子角色
				List<AgentRole> subRoles = agentRoleDao.getListByParentSn(parentFirstRoleSn);
				if(ListUtils.isNotEmpty(subRoles)){
					AgentUserRole agentUserRole = new AgentUserRole();
					agentUserRole.setUserSn(subUserSn);
					agentUserRole.setRoleSn(subRoles.get(0).getSn());
					agentUserRoleDao.insert(agentUserRole);
					//更新子账户级别，冗余
					agentSubUser.setAgentGrade(TCUtil.iv(agentUserRole.getRoleSn()));
					agentUserDao.updateByKeySelective(agentSubUser);
				}
			}
		}
	}

	@Override
	public List<AgentUser> getSubAccountList(long parentSn) {
		List<AgentUser> subAccountList = agentUserDao.getSubAccountList(parentSn);
		for (AgentUser agentUser : subAccountList) {
			agentUser.setEncryptSn(EncryptUtil.encode(agentUser.getSn()));
			List<AgentOrder> orderList = agentOrderDao.getList(agentUser.getSn(), null);
			for (AgentOrder agentOrder : orderList) {
				Integer status = agentOrder.getStatus();
				if(status == 2){
					agentUser.getWaitPayList().add(agentOrder);
				}
				if(status == 3){
					agentUser.getConfirmedList().add(agentOrder);
				}
				if(status == 5){
					agentUser.getWaitConfirmList().add(agentOrder);
				}
			}
			agentUser.setOrderList(orderList);
		}
		return subAccountList;
	}

}
