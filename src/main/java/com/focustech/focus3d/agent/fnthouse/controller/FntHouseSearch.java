package com.focustech.focus3d.agent.fnthouse.controller;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * *
 * @author lihaijun
 *
 */
public class FntHouseSearch {
	private String province;//省
	private String city;//市
	private String keyWord;//关键字
	private List<String> type = new ArrayList<String>();//房屋性质类型
	private List<String> areaRange = new ArrayList<String>();//面积
	private List<String> roomType = new ArrayList<String>();//户型类型
	private String pageNow;//当前页
	private String pageSize;//每页条数
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	public List<String> getType() {
		return type;
	}
	public void setType(List<String> type) {
		this.type = type;
	}
	public List<String> getAreaRange() {
		return areaRange;
	}
	public void setAreaRange(List<String> areaRange) {
		this.areaRange = areaRange;
	}
	public List<String> getRoomType() {
		return roomType;
	}
	public void setRoomType(List<String> roomType) {
		this.roomType = roomType;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPageNow() {
		return pageNow;
	}
	public void setPageNow(String pageNow) {
		this.pageNow = pageNow;
	}
	public String getPageSize() {
		return pageSize;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
}
