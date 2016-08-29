package com.focustech.focus3d.agent.filter;

import com.focustech.focus3d.agent.model.AgentLogin;

/**
 *
 * *
 * @author lihaijun
 *
 */
public class RequestThreadLocal {

	public static final ThreadLocal<RequestMessageCookie> msgThreadLocal = new ThreadLocal<RequestMessageCookie>();
	public static final ThreadLocal<AgentLogin> loginInfoThreadLocal = new ThreadLocal<AgentLogin>();

	public static RequestMessageCookie getMessageCookie(){
		RequestMessageCookie requestMessageCookie = msgThreadLocal.get();
		if(requestMessageCookie == null){
			requestMessageCookie = new RequestMessageCookie();
			msgThreadLocal.set(requestMessageCookie);
		}
		return requestMessageCookie;
	}

	public static AgentLogin getLoginInfo(){
		return loginInfoThreadLocal.get();
	}

	public static void setLoginInfo(AgentLogin loginInfo){
		if(loginInfo != null){
			loginInfoThreadLocal.set(loginInfo);
		}
	}
	public static void setLoginInfo(Object loginInfo){
		if(loginInfo != null && loginInfo instanceof AgentLogin){
			loginInfoThreadLocal.set((AgentLogin)loginInfo);
		}
	}

	public static void cleanAll(){
		msgThreadLocal.remove();
		loginInfoThreadLocal.remove();
	}
}
