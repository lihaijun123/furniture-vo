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
import com.focustech.focus3d.agent.fntprodcate.service.FntProductCateService;
import com.focustech.focus3d.agent.fntproduct.controller.FntProductSearch;
import com.focustech.focus3d.agent.fntproduct.service.FntProductService;
import com.focustech.focus3d.agent.model.FntProductCategory;
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
	public String searchByGet(@QueryParam("keyWord") String keyWord, @QueryParam("categoryCode") String categoryCode) {
		return searchData(keyWord, categoryCode);
	}

	@POST
	@Path("search")
	public String searchByPost(@FormParam("keyWord") String keyWord, @FormParam("categoryCode") String categoryCode) {
		return searchData(keyWord, categoryCode);
	}
	
	private String searchData(String keyWord, String categoryCode) {
		FntProductSearch fntProductSearch = new FntProductSearch();
		if (StringUtils.isNotEmpty(keyWord)) {
			fntProductSearch.setKeyWord(keyWord);
		}
		if (StringUtils.isNotEmpty(categoryCode)) {
			fntProductSearch.setCategoryCode(categoryCode);
		}
		List<FntProductModel> list = fntProductService.search(fntProductSearch);
		JSONArray jary = new JSONArray();
		for (FntProductModel fntProductModel : list) {
			JSONObject jo = new JSONObject();
			jo.put("url", fntProductModel.getModelFileUrl());
			jo.put("version", fntProductModel.getModelFileVersion());
			jo.put("picUrl", fntProductModel.getPicFileUrl());
			String categoryName = fntProductModel.getCategoryName();
			String category = "";
			if(categoryName.contains("地")){
				category = "floor";
			} else if(categoryName.contains("墙")){
				category = "wall";
			} else {
				category = "furniture";
			}
			jo.put("category", category);
			jary.add(jo);
		}
		return jary.toString();
	}
}
