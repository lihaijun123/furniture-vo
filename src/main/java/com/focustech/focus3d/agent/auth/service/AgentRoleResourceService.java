package com.focustech.focus3d.agent.auth.service;

import java.util.List;

import com.focustech.focus3d.agent.service.ICommonService;
/**
 *
 * *
 * @author lihaijun
 *
 * @param <T>
 */
public interface AgentRoleResourceService<T> extends ICommonService<T> {

	List<T> getListByRoleId(long roleId);
}
