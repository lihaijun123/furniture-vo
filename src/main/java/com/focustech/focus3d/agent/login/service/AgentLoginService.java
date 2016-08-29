package com.focustech.focus3d.agent.login.service;

import com.focustech.focus3d.agent.service.ICommonService;
/**
 *
 * *
 * @author lihaijun
 *
 */
public interface AgentLoginService<T> extends ICommonService<T> {
	/**
	 *
	 * *
	 * @param loginName
	 * @param password
	 * @return
	 */
	public T select(String loginName, String password);

	public T select(String loginName);



}

