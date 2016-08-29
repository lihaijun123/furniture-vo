package com.focustech.focus3d.agent.user.service;

import java.util.List;

import com.focustech.focus3d.agent.model.AgentUser;
import com.focustech.focus3d.agent.service.ICommonService;
/**
 *
 * *
 * @author lihaijun
 *
 * @param <T>
 */
public interface AgentUserService<T> extends ICommonService<T> {
	/**
	 *
	 * *
	 * @param mobilePhone
	 * @return
	 */
	public T selectByMobilePhone(String mobilePhone);
	/**
	 *
	 * *
	 * @return
	 */
	public List<T> getAvailableList();
	/**
	 *
	 * *
	 * @param agentUser
	 */
	public void saveSubAccount(AgentUser agentUser);
	/**
	 *
	 * *
	 * @param parentSn
	 * @return
	 */
	public List<T> getSubAccountList(long parentSn);
}
