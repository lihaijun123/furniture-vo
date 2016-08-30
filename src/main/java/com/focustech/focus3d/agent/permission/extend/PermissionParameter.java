package com.focustech.focus3d.agent.permission.extend;

import java.util.HashMap;
import java.util.Map;

import com.focustech.common.constant.OverallConst;

/**
 * 权限参数，封装调用权限方法输入参数
 * *
 * @author lihaijun
 *
 */
public class PermissionParameter {
	/**用户sn*/
	private String userSn;
	/**用户会员等级*/
	private String grade;
	/**当前展会sn*/
	private String currentExbSn = OverallConst.TEMP_EXB_SN;;
	/**资源几点code*/
	private String resourceNodeCode;

	private String resourceUrl;
	/**是否管理员*/
	private boolean isAdmin;
	/**
	 * 自定义参数
	 * *
	 */
	private Map<String, Object> customParameter = new HashMap<String, Object>();

	public String getResourceUrl() {
		return resourceUrl;
	}
	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}
	public Map<String, Object> getCustomParameter() {
		return customParameter;
	}
	public void setCustomParameter(Map<String, Object> customParameter) {
		this.customParameter = customParameter;
	}
	public String getUserSn() {
		return userSn;
	}
	public void setUserSn(String userSn) {
		this.userSn = userSn;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getCurrentExbSn() {
		return currentExbSn;
	}
	public void setCurrentExbSn(String currentExbSn) {
		this.currentExbSn = currentExbSn;
	}
	public String getResourceNodeCode() {
		return resourceNodeCode;
	}
	public void setResourceNodeCode(String resourceNodeCode) {
		this.resourceNodeCode = resourceNodeCode;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
}
