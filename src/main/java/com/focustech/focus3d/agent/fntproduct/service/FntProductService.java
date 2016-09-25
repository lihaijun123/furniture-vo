package com.focustech.focus3d.agent.fntproduct.service;

import java.util.List;

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
	public List<T> search(FntProductSearch productSearch);
}
