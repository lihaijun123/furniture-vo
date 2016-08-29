package com.focustech.focus3d.agent.sms.service;

import java.util.Map;

import com.focustech.focus3d.agent.utils.SmsSendType;
/**
 *
 * *
 * @author lihaijun
 *
 */
public interface SmsService {

	public int send(SmsSendType sendType, String mobile, Map<String, String> parame);
	public int send(SmsSendType sendType, Map<String, String> parame);
}
