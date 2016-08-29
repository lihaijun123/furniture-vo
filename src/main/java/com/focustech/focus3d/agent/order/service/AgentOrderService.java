package com.focustech.focus3d.agent.order.service;

import java.util.List;

import com.focustech.focus3d.agent.service.ICommonService;
/**
 *
 * *
 * @author lihaijun
 *
 * @param <T>
 */
public interface AgentOrderService<T> extends ICommonService<T>{

	public T save(T t);

	public T saveSub(T t);

	public List<T> getList(long userId, Integer status);

	public T getByOrderNum(String orderNum);
}
