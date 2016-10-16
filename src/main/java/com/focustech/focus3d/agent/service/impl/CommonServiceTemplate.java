package com.focustech.focus3d.agent.service.impl;

import java.util.List;

import com.focustech.focus3d.agent.dao.CommonDao;
/**
 *
 * *
 * @author lihaijun
 *
 */
public abstract class CommonServiceTemplate<T> {

	public abstract CommonDao getDao();

	public void insert(T t) {
		getDao().insert(t);
	}

	public T selectByKey(T t) {
		return getDao().selectByKey(t);
	}

	public int updateByKeySelective(T t) {
		return getDao().updateByKeySelective(t);
	}

	public int countByCriteria(Object u) {
		return getDao().countByCriteria(u);
	}

	public int deleteByCriteria(Object u) {
		return getDao().deleteByCriteria(u);
	}

	public List<T> selectByCriteria(Object u, Class<?> targetClass) {
		return getDao().selectByCriteria(u, targetClass);
	}

	public T selectFirstByExample(Object u, Class<?> targetClass) {
		return getDao().selectFirstByExample(u, targetClass);
	}

	public int updateByCriteriaSelective(Object u) {
		return getDao().updateByCriteriaSelective(u);
	}

	public T selectBySn(Long sn, Class<?> cls){

		return getDao().selectByKey(sn, cls);
	}
	
	public List<T> selectByCriteriaWithBlob(Object u, Class<?> targetClass) {
		return getDao().selectByCriteriaWithBlob(u, targetClass);
	}

}
