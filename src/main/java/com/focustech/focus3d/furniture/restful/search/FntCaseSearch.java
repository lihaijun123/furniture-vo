package com.focustech.focus3d.furniture.restful.search;


/**
 * 
 * *
 * @author lihaijun
 *
 */
public class FntCaseSearch extends FntPageInfo{
	private String userId;//用户编号
	private String houseName;//户型名称
	private String buildingName;//楼盘名称
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getHouseName() {
		return houseName;
	}
	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}
	public String getBuildingName() {
		return buildingName;
	}
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	
}
