package com.focustech.focus3d.furniture.restful;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.focustech.common.utils.StringUtils;
import com.focustech.focus3d.agent.fntproduct.controller.FntProductSearch;
import com.focustech.focus3d.agent.fntproduct.service.FntProductService;
import com.focustech.focus3d.agent.model.FntProductModel;
import com.focustech.focus3d.furniture.restful.constant.ContentType;

/**
 * 
 * *
 * 
 * @author lihaijun
 * 
 */
@Service
@Path("/rest/product")
@Produces(ContentType.APPLICATION_JSON_UTF_8)
public class ProductRestService {
	@Autowired
	private FntProductService<FntProductModel> fntProductService;

	@GET
	@Path("{name}")
	public String hello(@PathParam("name") final String name){
		JSONObject jo = new JSONObject();
		jo.put("lihaijun", "李海俊");
		return jo.toString();
	}

	@GET
	@Path("search")
	public String searchByGet(@QueryParam("keyWord") String keyWord, @QueryParam("categoryCode") String categoryCode, @QueryParam("price") String price) {
		return searchData(keyWord, categoryCode, price);
	}

	@POST
	@Path("search")
	public String searchByPost(@FormParam("keyWord") String keyWord, @FormParam("categoryCode") String categoryCode, @QueryParam("price") String price) {
		return searchData(keyWord, categoryCode, price);
	}
	/**
	 * *
	 * @param keyWord
	 * @param categoryCode
	 * @param price 
	 * @return
	 */
	private String searchData(String keyWord, String categoryCode, String price) {
		FntProductSearch fntProductSearch = new FntProductSearch();
		if (StringUtils.isNotEmpty(keyWord)) {
			fntProductSearch.setKeyWord(keyWord);
		}
		if (StringUtils.isNotEmpty(categoryCode)) {
			fntProductSearch.setCategoryCode(categoryCode);
		}
		if (StringUtils.isNotEmpty(price)) {
			fntProductSearch.setPriceRange(price);
		}
		List<FntProductModel> list = fntProductService.search(fntProductSearch);
		JSONArray jary = new JSONArray();
		for (FntProductModel fntProductModel : list) {
			JSONObject jo = fntProductService.serialize(fntProductModel);
			jary.add(jo);
		}
		return jary.toString();
	}
}
