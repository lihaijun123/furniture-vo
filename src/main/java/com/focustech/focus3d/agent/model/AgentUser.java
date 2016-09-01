package com.focustech.focus3d.agent.model;

import java.util.ArrayList;
import java.util.List;

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
	private List<AgentOrder> waitConfirmList = new ArrayList<AgentOrder>();//买家已付款等待卖家确认5
	private List<AgentOrder> confirmedList = new ArrayList<AgentOrder>();//卖家已经确认买家付款3
	private List<AgentOrder> waitPayList = new ArrayList<AgentOrder>();//等待买家付款2
	private List<AgentOrder> orderList = new ArrayList<AgentOrder>();//全部
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

	public List<AgentOrder> getWaitConfirmList() {
		return waitConfirmList;
	}

	public void setWaitConfirmList(List<AgentOrder> waitConfirmList) {
		this.waitConfirmList = waitConfirmList;
	}

	public List<AgentOrder> getConfirmedList() {
		return confirmedList;
	}

	public void setConfirmedList(List<AgentOrder> confirmedList) {
		this.confirmedList = confirmedList;
	}

	public List<AgentOrder> getWaitPayList() {
		return waitPayList;
	}

	public void setWaitPayList(List<AgentOrder> waitPayList) {
		this.waitPayList = waitPayList;
	}

	public List<AgentOrder> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<AgentOrder> orderList) {
		this.orderList = orderList;
	}

	public AgentLogin getLoginInfo() {
		return loginInfo;
	}

	public void setLoginInfo(AgentLogin loginInfo) {
		this.loginInfo = loginInfo;
	}
	
}
