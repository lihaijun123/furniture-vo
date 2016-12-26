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
public class RestServiceTest extends AbstractRpc{
	
	/**
	 * 收藏
	 * *
	 */
	//@Test
	public void testFavorite() {
		List<NameValuePair> qparams = new ArrayList<NameValuePair>();
		qparams.add(new BasicNameValuePair("userId", "LnAKoUyeyUpB"));
		httpRequest(getProtocal() + "/rest/favorite/list", qparams, HttpMethod.POST);
	}
	//@Test
	public void testHouse() {
		List<NameValuePair> qparams = new ArrayList<NameValuePair>();
		qparams.add(new BasicNameValuePair("province", ""));
		qparams.add(new BasicNameValuePair("city", ""));
		qparams.add(new BasicNameValuePair("keyWord", ""));
		qparams.add(new BasicNameValuePair("type", ""));
		qparams.add(new BasicNameValuePair("areaRange", ""));
		qparams.add(new BasicNameValuePair("roomType", ""));
		qparams.add(new BasicNameValuePair("pageNow", "1"));
		qparams.add(new BasicNameValuePair("pageSize", ""));
		httpRequest(getProtocal() + "/rest/house/search", qparams, HttpMethod.POST);
	}
	//@Test
	public void testProductCate() {
		List<NameValuePair> qparams = new ArrayList<NameValuePair>();
		httpRequest(getProtocal() + "/rest/productcate/list", qparams, HttpMethod.GET);
	}
	//@Test
	public void testProduct() {
		List<NameValuePair> qparams = new ArrayList<NameValuePair>();
		qparams.add(new BasicNameValuePair("keyWord", ""));
		qparams.add(new BasicNameValuePair("categoryCode", ""));
		httpRequest(getProtocal() + "/rest/product/search", qparams, HttpMethod.POST);
	}
	/**
	 * 案例保存
	 * *
	 */
	//@Test
	public void testCaseSave() {
		List<NameValuePair> qparams = new ArrayList<NameValuePair>();
		qparams.add(new BasicNameValuePair("userId", "LnAKoUyeyUpB"));
		qparams.add(new BasicNameValuePair("houseId", "DqoeAUpKyyey"));
		qparams.add(new BasicNameValuePair("data", "test"));
		httpRequest(getProtocal() + "/rest/case/save", qparams, HttpMethod.POST);
		
	}
	/**
	 * 案例
	 * *
	 */
	//@Test
	public void testCaseListByUser() {
		List<NameValuePair> qparams = new ArrayList<NameValuePair>();
		qparams.add(new BasicNameValuePair("userId", "LnAKoUyeyUpB"));
		httpRequest(getProtocal() + "/rest/case/list", qparams, HttpMethod.POST);
	}
	
	//@Test
	public void testCaseListByHouse() {
		List<NameValuePair> qparams = new ArrayList<NameValuePair>();
		qparams.add(new BasicNameValuePair("houseId", "DqoeAUpKyyey"));
		httpRequest(getProtocal() + "/rest/case/list", qparams, HttpMethod.POST);
	}
	
	@Test
	public void testCaseSearch() {
		List<NameValuePair> qparams = new ArrayList<NameValuePair>();
		qparams.add(new BasicNameValuePair("userId", ""));
		qparams.add(new BasicNameValuePair("buildingName", "雅居"));
		qparams.add(new BasicNameValuePair("pageNow", "1"));
		qparams.add(new BasicNameValuePair("pageSize", "2"));
		httpRequest(getProtocal() + "/rest/case/search", qparams, HttpMethod.POST);
	}
	@Override
	protected String getProtocal() {
		return URL_TEST;
	}
	
}
