package com.focustech.focus3d.agent.service;

import java.util.List;

/**
 *
 * *
 * @author lihaijun
 *
 */
public interface ICommonService<T> {


	public void insert(T t);

	public T selectByKey(T t);

	public int updateByKeySelective(T t);

	public int countByCriteria(Object u);

	public int deleteByCriteria(Object u);

	public List<T> selectByCriteria(Object u, Class<?> targetClass);

	public T selectFirstByExample(Object u, Class<?> targetClass);

	public int updateByCriteriaSelective(Object u);

	public T selectBySn(Long sn, Class<?> targetClass);

}
