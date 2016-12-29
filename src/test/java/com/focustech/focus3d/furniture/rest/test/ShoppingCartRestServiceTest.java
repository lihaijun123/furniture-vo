package com.focustech.focus3d.furniture.rest.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;
import org.springframework.http.HttpMethod;

import com.focustech.focus3d.furniture.rpc.AbstractRpc;

public class ShoppingCartRestServiceTest extends AbstractRpc{
	@Test
	public void testAdd() {
		List<NameValuePair> qparams = new ArrayList<NameValuePair>();
		qparams.add(new BasicNameValuePair("userId", "32794"));
		qparams.add(new BasicNameValuePair("productId", "272"));
		qparams.add(new BasicNameValuePair("count", "1"));
		qparams.add(new BasicNameValuePair("price", "1100"));
		qparams.add(new BasicNameValuePair("gsp", ""));
		httpRequest("/rest/shoppingcart/add", qparams, HttpMethod.POST);
	}

	@Override
	protected String getProtocal() {
		return URL_TEST;
	}
}
