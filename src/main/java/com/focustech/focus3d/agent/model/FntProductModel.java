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
	
	private String brandId;
	
	private String inventory;
	
	private String serial;

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

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getInventory() {
		return inventory;
	}

	public void setInventory(String inventory) {
		this.inventory = inventory;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}
	
}
