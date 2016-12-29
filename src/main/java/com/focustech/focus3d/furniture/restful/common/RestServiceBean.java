package com.focustech.focus3d.furniture.restful.common;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * *
 * @author lihaijun
 *
 */
public class RestServiceBean {
	private String path;
	private String[] produces;
	private String desc;
	private List<RestServiceMethod> services = new ArrayList<RestServiceMethod>();
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	public String[] getProduces() {
		return produces;
	}
	public void setProduces(String[] produces) {
		this.produces = produces;
	}
	public List<RestServiceMethod> getServices() {
		return services;
	}
	public void setServices(List<RestServiceMethod> services) {
		this.services = services;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
