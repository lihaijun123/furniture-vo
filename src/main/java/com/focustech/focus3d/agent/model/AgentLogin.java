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
	private String password;
	private String passwordConfirm;//确认新密码
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
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public String getValidCode() {
		return validCode;
	}

	public void setValidCode(String validCode) {
		this.validCode = validCode;
	}

	@Override
	public String getEncryptSn() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setEncryptSn(String encryptSn) {
		// TODO Auto-generated method stub
		
	}

}
