package com.focustech.focus3d.agent.model;

import com.focustech.focus3d.agent.model.ibator.BizAgentLogin;
import com.focustech.focus3d.agent.model.ibator.BizAgentLoginCriteria;
/**
 *
 * *
 * @author lihaijun
 *
 */
public class AgentLogin extends BizAgentLogin<AgentLogin, BizAgentLoginCriteria> implements CommonModel{

	private String smsCode;//短信验证码
	private String validCode;//验证码
	private String passwordNew;//新密码
	private String passwordNewS;//确认新密码

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public String getSmsCode() {
		return smsCode;
	}

	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}

	public String getPasswordNew() {
		return passwordNew;
	}

	public void setPasswordNew(String passwordNew) {
		this.passwordNew = passwordNew;
	}

	public String getPasswordNewS() {
		return passwordNewS;
	}

	public void setPasswordNewS(String passwordNewS) {
		this.passwordNewS = passwordNewS;
	}

	public String getValidCode() {
		return validCode;
	}

	public void setValidCode(String validCode) {
		this.validCode = validCode;
	}

}
