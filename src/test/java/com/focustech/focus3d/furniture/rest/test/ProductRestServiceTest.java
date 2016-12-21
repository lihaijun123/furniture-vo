package com.focustech.focus3d.furniture.rest.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;
import org.springframework.http.HttpMethod;

/**
 * *
 * @author lihaijun
 *
 */
public class ProductRestServiceTest extends AbstractTest{
	@Test
	public void testHouse() {
		List<NameValuePair> qparams = new ArrayList<NameValuePair>();
		qparams.add(new BasicNameValuePair("goodsname", "沙发"));
		httpRequest(getProtocal() + "/service/product/bygoodname.htm", qparams, HttpMethod.POST);
	}
	@Override
	protected String getProtocal() {
		return URL_THIRD;
	}

}
