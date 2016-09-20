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
	private String province;
	private String city;
	private String keyWord;
	private List<String> type = new ArrayList<String>();
	private List<String> areaRange = new ArrayList<String>();
	private List<String> roomType = new ArrayList<String>();
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
}
