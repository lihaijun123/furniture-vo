package com.focustech.focus3d.agent.product.service;

import java.util.List;

import com.focustech.focus3d.agent.service.ICommonService;
/**
 *
 * *
 * @author lihaijun
 *
 * @param <T>
 */
public interface AgentProductUserService<T> extends ICommonService<T>{

	public List<T> getListByUserId(long userId);
}
