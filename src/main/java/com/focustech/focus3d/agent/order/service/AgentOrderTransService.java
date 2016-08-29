package com.focustech.focus3d.agent.order.service;

import com.focustech.focus3d.agent.service.ICommonService;
/**
 *
 * *
 * @author lihaijun
 *
 * @param <T>
 */
public interface AgentOrderTransService<T> extends ICommonService<T> {
	/**
	 *
	 * *
	 * @param orderId
	 * @return
	 */
	public T getByOrderId(String orderId);
}
