package com.focustech.focus3d.agent.fntproduct.service;

import java.util.List;

import net.sf.json.JSONObject;

import com.focustech.focus3d.agent.fntproduct.controller.FntProductSearch;
import com.focustech.focus3d.agent.service.ICommonService;
/**
 * 
 * *
 * @author lihaijun
 *
 * @param <T>
 */
public interface FntProductService<T> extends ICommonService<T> {
	/**
	 * 
	 * *
	 * @param productSearch
	 * @return
	 */
	public List<T> search(FntProductSearch productSearch);
	/**
	 * *
	 * @param fntProductModel
	 * @return
	 */
	public JSONObject serialize(T fntProductModel);
}
