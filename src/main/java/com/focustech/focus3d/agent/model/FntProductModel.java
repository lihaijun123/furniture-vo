package com.focustech.focus3d.agent.model;

import com.focustech.focus3d.agent.model.ibator.FntProduct;
import com.focustech.focus3d.agent.model.ibator.FntProductCriteria;
/**
 * 
 * *
 * @author lihaijun
 *
 */
public class FntProductModel extends FntProduct<FntProductModel, FntProductCriteria> implements CommonModel{

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
