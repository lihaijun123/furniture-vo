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
public class ProductRestServiceTest extends AbstractRpc{
	@Test
	public void testHouse() {
		List<NameValuePair> qparams = new ArrayList<NameValuePair>();
		qparams.add(new BasicNameValuePair("categoryCode", "沙发"));
		httpRequest("/rest/product/search", qparams, HttpMethod.POST);
	}
	//@Test
	public void testType() {
		List<NameValuePair> qparams = new ArrayList<NameValuePair>();
		qparams.add(new BasicNameValuePair("id", "272"));
		httpRequest("/service/product/goodspecs.htm", qparams, HttpMethod.POST);
	}
	@Override
	protected String getProtocal() {
		return URL_RELEASE;
	}

}
