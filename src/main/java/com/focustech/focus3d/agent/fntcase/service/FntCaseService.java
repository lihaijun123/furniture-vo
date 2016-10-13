package com.focustech.focus3d.agent.fntcase.service;

import java.util.List;

import com.focustech.focus3d.agent.service.ICommonService;
/**
 * *
 * @author lihaijun
 *
 * @param <T>
 */
public interface FntCaseService<T> extends ICommonService<T> {

	public int delete(long userId, long houseId);
	
	public T select(long userId, long houseId);
	
	public List<T> listByUser(long userId);
	
	public List<T> listByHouse(long houseId);
	
	
}
