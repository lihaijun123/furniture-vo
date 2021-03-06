package com.focustech.focus3d.furniture.restful;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import com.focustech.common.utils.StringUtils;
import com.focustech.common.utils.TCUtil;
import com.focustech.focus3d.agent.fntproduct.controller.FntProductSearch;
import com.focustech.focus3d.agent.fntproduct.service.FntProductService;
import com.focustech.focus3d.agent.model.FntProductModel;
import com.focustech.focus3d.furniture.restful.common.RestMethodDesc;
import com.focustech.focus3d.furniture.restful.constant.ContentType;
import com.focustech.focus3d.furniture.rpc.FntRpc;

/**
 * 
 * *
 * 
 * @author lihaijun
 * 
 */
@RestMethodDesc("家具产品服务")
@Service
@Path("/rest/product")
@Produces(ContentType.APPLICATION_JSON_UTF_8)
public class ProductRestService {
	@Autowired
	private FntProductService<FntProductModel> fntProductService;
	private FntRpc fntRpc = new FntRpc();
	
	@RestMethodDesc("查询家具产品")
	@GET
	@Path("search")
	public String searchByGet(
			@QueryParam("keyWord") String keyWord, 
			@QueryParam("categoryCode") String categoryCode, 
			@QueryParam("price") String price,
			@QueryParam("pageNow") String pageNow,
			@QueryParam("pageSize") String pageSize,
			@QueryParam("type") String type
			) {
		return searchData(keyWord, categoryCode, price, type, pageNow, pageSize);
	}
	@RestMethodDesc("查询家具产品")
	@POST
	@Path("search")
	public String searchByPost(
			@FormParam("keyWord") String keyWord, 
			@FormParam("categoryCode") String categoryCode, 
			@FormParam("price") String price,
			@FormParam("pageNow") String pageNow,
			@FormParam("pageSize") String pageSize,
			@FormParam("type") String type
			) {
		return searchData(keyWord, categoryCode, price, type, pageNow, pageSize);
	}
	@RestMethodDesc("获取家具产品价格和库存")
	@POST
	@Path("gsp-get")
	public String getGsp(
			@FormParam("productId") String productId, 
			@FormParam("gsp") String gsp
			) {
		if(StringUtils.isNotEmpty(productId) && StringUtils.isNotEmpty(gsp)){
			List<NameValuePair> qparams = new ArrayList<NameValuePair>();
			qparams.add(new BasicNameValuePair("id", productId));
			qparams.add(new BasicNameValuePair("gsp", gsp));
			String result = fntRpc.httpRequest("/load_goods_gsp.htm", qparams, HttpMethod.POST);
			return result;
		}
		return (new JSONObject()).toString();
	}
	/**
	 * *
	 * @param keyWord
	 * @param categoryCode
	 * @param price 
	 * @param pageSize 
	 * @param pageNow 
	 * @param pageSize2 
	 * @return
	 */
	private String searchData(String keyWord, String categoryCode, String price, String type, String pageNow, String pageSize) {
		FntProductSearch productSearch = new FntProductSearch();
		if (StringUtils.isNotEmpty(keyWord)) {
			productSearch.setKeyWord(keyWord);
		}
		if (StringUtils.isNotEmpty(categoryCode)) {
			productSearch.setCategoryCode(categoryCode);
		}
		if (StringUtils.isNotEmpty(price)) {
			productSearch.setPriceRange(price);
		}
		if (StringUtils.isNotEmpty(type)) {
			productSearch.setType(type);
		}
		if(StringUtils.isNotEmpty(pageNow) && TCUtil.iv(pageNow) > 0){
			productSearch.setPageNow(TCUtil.iv(pageNow));
			if(StringUtils.isNotEmpty(pageSize) && TCUtil.iv(pageSize) > 0){
				productSearch.setPageSize(TCUtil.iv(pageSize));
			}
		}
		List<FntProductModel> list = fntProductService.search(productSearch);
		JSONArray jary = new JSONArray();
		for (FntProductModel fntProductModel : list) {
			JSONObject jo = fntProductService.serialize(fntProductModel);
			jary.add(jo);
		}
		JSONObject rvJo = new JSONObject();
		productSearch.addPageInfo(rvJo);
		rvJo.put("list", jary);
		return rvJo.toString();
	}
}
