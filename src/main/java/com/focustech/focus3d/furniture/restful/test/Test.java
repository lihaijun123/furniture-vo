package com.focustech.focus3d.furniture.restful.test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
/**
 * *
 * @author lihaijun
 *
 */
public class Test {
	public static String URL_TEST = "http://127.0.0.1:7001";
	public static String URL_RELEASE = "http://139.196.173.139:8888";
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(5000).build();
		HttpPost post = new HttpPost(URL_RELEASE + "/rest/favorite/list");
		post.setConfig(requestConfig);
		List<NameValuePair> qparams = new ArrayList<NameValuePair>();
		qparams.add(new BasicNameValuePair("userId", "LnAKoUyeyUpB"));
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(qparams, "utf-8");
		post.setEntity(entity);
        //示例：提交用户名和密码
		System.out.println(post.getRequestLine());
		HttpResponse resp = httpClient.execute(post);
		int statusCode = resp.getStatusLine().getStatusCode();
		System.out.println(statusCode);
		InputStream is = resp.getEntity().getContent();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		int len = 0;
		byte[] byt = new byte[1024];
		while((len = is.read(byt)) != -1){
			outputStream.write(byt, 0, len);
		}
		System.out.println(new String(outputStream.toByteArray()));
	}

}
