package com.focustech.focus3d.agent.fntproduct.controller;

import com.focustech.focus3d.furniture.restful.search.FntPageInfo;

/**
 * 
 * *
 * @author lihaijun
 *
 */
public class FntProductSearch extends FntPageInfo{
	//改为对应目录名称
	private String categoryCode;
	private String priceRange;
	private String keyWord;
	private String type;
	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getPriceRange() {
		return priceRange;
	}

	public void setPriceRange(String priceRange) {
		this.priceRange = priceRange;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
