package com.focustech.focus3d.agent.order.service;

import org.springframework.core.io.Resource;

import com.focustech.focus3d.pay.wx.resource.WxPayResource;
/**
 *
 * *
 * @author lihaijun
 *
 */
public class WxPayResourceImpl implements WxPayResource {
	private Resource orderNoResource;
	@Override
	public Resource getOrderNoResource() {
		return orderNoResource;
	}

	@Override
	public void setOrderNoResource(Resource orderNoResource) {
		this.orderNoResource = orderNoResource;
	}

}
