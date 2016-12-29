package com.focustech.focus3d.furniture.restful;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.focustech.common.utils.TCUtil;
import com.focustech.focus3d.agent.fntprodcate.service.FntProductCateService;
import com.focustech.focus3d.agent.model.FntProductCategory;
import com.focustech.focus3d.furniture.restful.constant.ContentType;

/**
 * 产品目录
 * *
 * @author lihaijun
 *
 */
//@Service
@Path("/rest/productcate")
@Produces(ContentType.APPLICATION_JSON_UTF_8)
public class ProductCategoryRestService {
	@Autowired
	private FntProductCateService<FntProductCategory> cateService;
	/**
	 * 
	 * *
	 * @param modelMap
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@GET
	@Path("list")
	public String list() throws IOException{
		List<FntProductCategory> list = cateService.list("1");
		List<FntProductCategory> rootCateList = getRootCateList(list);
		JSONObject level1Jo = new JSONObject();
		for (FntProductCategory fntProductCategory : rootCateList) {
			//一级目录下面所有类
			Long catCode = fntProductCategory.getCatCode();
			JSONObject levelDataJo = new JSONObject();
			levelDataJo.put("name", fntProductCategory.getCatNameCn());
			levelDataJo.put("child", buildChildrenData(list, catCode));
			level1Jo.put(catCode, levelDataJo);
		}
		return level1Jo.toString();
	}
	/**
	 * *
	 * @param list
	 * @param catCode
	 * @return
	 */
	private JSONArray buildChildrenData(List<FntProductCategory> list, Long catCode) {
		List<FntProductCategory> level2CateList = getChildCateList(list, TCUtil.lv(catCode));
		JSONArray jary = new JSONArray();
		for (FntProductCategory level2Cat : level2CateList) {
			JSONObject jo = new JSONObject();
			jo.put("name", level2Cat.getCatNameCn());
			jo.put("key", TCUtil.sv(level2Cat.getCatCode()));
			Long level2CatCode = level2Cat.getCatCode();
			List<FntProductCategory> level3CateList = getChildCateList(list, TCUtil.lv(level2CatCode));
			JSONArray dJary = new JSONArray();
			for (FntProductCategory level3Cat : level3CateList) {
				JSONObject dJo = new JSONObject();
				dJo.put("name", level3Cat.getCatNameCn());
				dJo.put("key", TCUtil.sv(level3Cat.getCatCode()));
				dJo.put("child", new JSONArray());
				dJary.add(dJo);
			}
			jo.put("child", dJary);
			jary.add(jo);
		}
		
		return jary;
	}
	/**
	 * 
	 * *
	 * @param list
	 * @return
	 */
	private List<FntProductCategory> getRootCateList(List<FntProductCategory> list){
		List<FntProductCategory> rvList = new ArrayList<FntProductCategory>();
		for (FntProductCategory cat : list) {
			Long catCode = cat.getParentCatCode();
			if(catCode.equals(0L)){
				rvList.add(cat);
			}
		}
		return rvList;
	}
	/**
	 * *
	 * @param list
	 * @param code
	 * @return
	 */
	private List<FntProductCategory> getChildCateList(List<FntProductCategory> list, Long code){
		List<FntProductCategory> rvList = new ArrayList<FntProductCategory>();
		for (FntProductCategory cat : list) {
			Long catCode = cat.getParentCatCode();
			if(catCode.equals(code)){
				rvList.add(cat);
			}
		}
		return rvList;
	}
}
