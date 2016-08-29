package com.focustech.focus3d.agent.product.service;

import java.util.List;

import com.focustech.focus3d.agent.service.ICommonService;
/***
 *
 * @author lihaijun
 *
 * @param <T>
 */
public interface AgentProductService<T> extends ICommonService<T>{
	/**
	 *
	 * *
	 * @param status
	 * @return
	 */
	public List<T> getList(int status);

	public List<T> getListByUserId(long userId);

	public List<String> getUniqProdNameList(int status);

	public List<T> getList(int status, String name);
}
