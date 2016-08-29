package com.focustech.focus3d.agent.order.service;

import com.focustech.focus3d.agent.service.ICommonService;
/**
 *
 * *
 * @author lihaijun
 *
 * @param <T>
 */
public interface ReceiveAddressService<T> extends ICommonService<T> {

	T getByUserId(long userId);
}
