package com.focustech.focus3d.furniture.rest.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;
/**
 * *
 * @author lihaijun
 *
 */
public class RestServiceTest extends AbstractTest{
	
	/**
	 * 收藏
	 * *
	 */
	@Test
	public void testFavorite() {
		List<NameValuePair> qparams = new ArrayList<NameValuePair>();
		qparams.add(new BasicNameValuePair("userId", "LnAKoUyeyUpB"));
		httpRequest(getProtocal() + "/rest/favorite/list", qparams);
	}
	/**
	 * 案例保存
	 * *
	 */
	@Test
	public void testCaseSave() {
		List<NameValuePair> qparams = new ArrayList<NameValuePair>();
		qparams.add(new BasicNameValuePair("userId", "LnAKoUyeyUpB"));
		qparams.add(new BasicNameValuePair("houseId", "DqoeAUpKyyey"));
		qparams.add(new BasicNameValuePair("data", "test"));
		httpRequest(getProtocal() + "/rest/case/save", qparams);
		
	}
	/**
	 * 案例
	 * *
	 */
	@Test
	public void testCaseListByUser() {
		List<NameValuePair> qparams = new ArrayList<NameValuePair>();
		qparams.add(new BasicNameValuePair("userId", "LnAKoUyeyUpB"));
		httpRequest(getProtocal() + "/rest/case/list", qparams);
	}
	
	@Test
	public void testCaseListByHouse() {
		List<NameValuePair> qparams = new ArrayList<NameValuePair>();
		qparams.add(new BasicNameValuePair("houseId", "DqoeAUpKyyey"));
		httpRequest(getProtocal() + "/rest/case/list", qparams);
	}
	
	@Test
	public void testCaseListAll() {
		httpRequest(getProtocal() + "/rest/case/list", null);
	}
	
}
