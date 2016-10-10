package com.focustech.focus3d.agent.fntfavorite.service;

import java.util.List;

import com.focustech.focus3d.agent.service.ICommonService;
/**
 * 
 * *
 * @author lihaijun
 *
 * @param <T>
 */
public interface FntFavoriteService<T> extends ICommonService<T> {
	/**
	 * 
	 * *
	 * @param userId
	 * @param targetId
	 * @param targetType
	 * @return
	 */
	T select(long userId, long targetId, int targetType);
	/**
	 * 
	 * *
	 * @param userId
	 * @param targetType
	 * @return
	 */
	List<T> list(long userId, int targetType);
}
