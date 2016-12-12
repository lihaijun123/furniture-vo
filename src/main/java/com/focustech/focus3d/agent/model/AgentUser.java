package com.focustech.focus3d.agent.model;

import com.focustech.focus3d.agent.model.ibator.BizAgentUser;
import com.focustech.focus3d.agent.model.ibator.BizAgentUserCriteria;
/**
 *
 * *
 * @author lihaijun
 *
 */
public class AgentUser extends BizAgentUser<AgentUser, BizAgentUserCriteria> implements CommonModel{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String smsCode;//短信验证码
	private String validCode;//验证码
	private String idCardFileUrl;
	private String kbisFileUrl;
	private String encryptSn;
	private AgentLogin loginInfo;
	public String getSmsCode() {
		return smsCode;
	}

	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}

	public String getIdCardFileUrl() {
		return idCardFileUrl;
	}

	public void setIdCardFileUrl(String idCardFileUrl) {
		this.idCardFileUrl = idCardFileUrl;
	}

	public String getKbisFileUrl() {
		return kbisFileUrl;
	}

	public void setKbisFileUrl(String kbisFileUrl) {
		this.kbisFileUrl = kbisFileUrl;
	}

	public String getEncryptSn() {
		return encryptSn;
	}

	public void setEncryptSn(String encryptSn) {
		this.encryptSn = encryptSn;
	}

	public String getValidCode() {
		return validCode;
	}

	public void setValidCode(String validCode) {
		this.validCode = validCode;
	}

	public AgentLogin getLoginInfo() {
		return loginInfo;
	}

	public void setLoginInfo(AgentLogin loginInfo) {
		this.loginInfo = loginInfo;
	}
	
}
