package com.focustech.focus3d.furniture.rest.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;
import org.springframework.http.HttpMethod;

import com.focustech.focus3d.furniture.rpc.AbstractRpc;

/**
 * *
 * @author lihaijun
 *
 */
public class ProductTypeRestServiceTest extends AbstractRpc{
	@Test
	public void testHouse() {
		List<NameValuePair> qparams = new ArrayList<NameValuePair>();
		qparams.add(new BasicNameValuePair("productId", "272"));
		httpRequest("/rest/product-type/list", qparams, HttpMethod.POST);
	}
	
	@Override
	protected String getProtocal() {
		return URL_TEST;
	}

}
