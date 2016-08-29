package com.focustech.focus3d.agent.message.service;

import java.util.List;

import com.focustech.focus3d.agent.service.ICommonService;
/**
 *
 * *
 * @author lihaijun
 *
 */
public interface MessageValidateService<T> extends ICommonService<T>{
	/**
	 *
	 * *
	 * @param mobilePhone
	 */
	public void deleteByMobilePhone(String mobilePhone);

	public void setStatus(T t, int status);

	public void save(String mobilePhone, String verifyCode, String ip);

	public T selectByMobilePhone(String mobilePhone, String verifyCode);

	public List<T> getListByPerDay(String mobilePhone);
}
