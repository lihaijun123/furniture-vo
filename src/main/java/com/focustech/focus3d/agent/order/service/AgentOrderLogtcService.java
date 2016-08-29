package com.focustech.focus3d.agent.order.service;

import com.focustech.focus3d.agent.service.ICommonService;
/**
 *
 * *
 * @author lihaijun
 *
 * @param <T>
 */
public interface AgentOrderLogtcService<T> extends ICommonService<T> {

	public T getByOrderId(long orderId);
}
