package com.focustech.focus3d.agent.fnthouse.service;

import java.util.List;

import com.focustech.focus3d.agent.fnthouse.controller.FntHouseSearch;
import com.focustech.focus3d.agent.service.ICommonService;
/**
 * *
 * @author lihaijun
 *
 * @param <T>
 */
public interface FntHouseService<T> extends ICommonService<T> {
	/**
	 * *
	 * @param houseSearch
	 * @return
	 */
	public List<T> search(FntHouseSearch houseSearch);
	/**
	 * *
	 * @param houseSearch
	 * @return
	 */
	public int searchTotal(FntHouseSearch houseSearch);
}
