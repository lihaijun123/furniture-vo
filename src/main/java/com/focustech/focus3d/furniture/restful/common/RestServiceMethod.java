package com.focustech.focus3d.furniture.restful.common;

import java.util.ArrayList;
import java.util.List;

public class RestServiceMethod {

	private String path;
	private String httpMethod;
	private List<String> requestParameters = new ArrayList<String>();
	private String desc;
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getHttpMethod() {
		return httpMethod;
	}
	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}
	
	public List<String> getRequestParameters() {
		return requestParameters;
	}
	public void setRequestParameters(List<String> requestParameters) {
		this.requestParameters = requestParameters;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}

}
