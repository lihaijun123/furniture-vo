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
public interface AgentOrderItemService<T> extends ICommonService<T> {

	public List<T> getByOrderId(long orderId);
}
