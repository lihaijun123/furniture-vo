package com.focustech.focus3d.agent.model;

import com.focustech.focus3d.agent.model.ibator.FntHouse;
import com.focustech.focus3d.agent.model.ibator.FntHouseCriteria;
/**
 * *
 * @author lihaijun
 *
 */
public class FntHouseModel extends FntHouse<FntHouseModel, FntHouseCriteria> implements CommonModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String picFileUrl;
	
	private String modelFileUrl;

	public String getPicFileUrl() {
		return picFileUrl;
	}

	public void setPicFileUrl(String picFileUrl) {
		this.picFileUrl = picFileUrl;
	}

	public String getModelFileUrl() {
		return modelFileUrl;
	}

	public void setModelFileUrl(String modelFileUrl) {
		this.modelFileUrl = modelFileUrl;
	}
}
