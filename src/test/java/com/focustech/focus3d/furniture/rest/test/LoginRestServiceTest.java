package com.focustech.focus3d.furniture.rest.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;
import org.springframework.http.HttpMethod;

import com.focustech.common.utils.MD5Util;
import com.focustech.focus3d.furniture.rpc.AbstractRpc;

/**
 * *
 * @author lihaijun
 *
 */
public class LoginRestServiceTest extends AbstractRpc{
	
	@Test
	public void loginTest() {
		List<NameValuePair> qparams = new ArrayList<NameValuePair>();
		qparams.add(new BasicNameValuePair("username", "lihaijun"));
		String md5Encode = MD5Util.MD5Encode("lhj123456", "utf-8").toLowerCase();
		qparams.add(new BasicNameValuePair("password", md5Encode));
		httpRequest("/service/users/login.htm", qparams, HttpMethod.POST);
	}
	@Override
	protected String getProtocal() {
		return URL_THIRD;
	}

}
