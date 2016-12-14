package com.focustech.focus3d.agent.fntshoppingcart.service;

import java.util.List;

import com.focustech.focus3d.agent.service.ICommonService;
/**
 * *
 * @author lihaijun
 *
 * @param <T>
 */
public interface FntShoppingCartService<T> extends ICommonService<T>{

	public List<T> listByUser();
	public List<T> listAll();
	public void deleteBySn(long sn);
	
}
