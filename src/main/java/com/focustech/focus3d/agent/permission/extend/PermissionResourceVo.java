package com.focustech.focus3d.agent.permission.extend;


/**
 * 资源类值对象，用于客户端传输数据
 * *
 * @author lihaijun
 *
 */
public class PermissionResourceVo {
	private String resourceName;
	private String resourceCode;
	private String resourceDisplayName;
	private String resourceUrl;
	private String active;

	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public String getResourceCode() {
		return resourceCode;
	}
	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
	}
	public String getResourceDisplayName() {
		return resourceDisplayName;
	}
	public void setResourceDisplayName(String resourceDisplayName) {
		this.resourceDisplayName = resourceDisplayName;
	}
	public String getResourceUrl() {
		return resourceUrl;
	}
	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}

	@Override
	public String toString() {
		return "resourceCode:" + getResourceCode()
				+ ",resourceDisplayName:" + getResourceDisplayName()
				+ ",resourceUrl:" + getResourceUrl();
	}

}
