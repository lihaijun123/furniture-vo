package com.focustech.focus3d.agent.fntprodcate.service;

import java.util.List;

import com.focustech.focus3d.agent.model.FntProductCategory;
import com.focustech.focus3d.agent.service.ICommonService;

/**
 * 
 * *
 * @author lihaijun
 *
 */
public interface FntProductCateService<T> extends ICommonService<T> {

	public List<FntProductCategory> list(String status);
	
	public T selectByCode(long cateCode);
}
