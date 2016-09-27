package com.focustech.focus3d.agent.fntprodcate.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.focustech.common.utils.TCUtil;
import com.focustech.focus3d.agent.common.controller.CommonController;
import com.focustech.focus3d.agent.fntprodcate.service.FntProductCateService;
import com.focustech.focus3d.agent.model.FntProductCategory;
/**
 * 
 * *
 * @author lihaijun
 *
 */
@Controller
@RequestMapping(value = "/fntprodcate")
public class FntProductCateController extends CommonController{
	@Autowired
	private FntProductCateService<FntProductCategory> cateService;
	/**
	 * 
	 * *
	 * @param modelMap
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/search/{code}", method = RequestMethod.GET)
	public void search(@PathVariable("code") String code, ModelMap modelMap, HttpServletResponse response) throws IOException{
		List<FntProductCategory> list = cateService.list("1");
		JSONArray jary = new JSONArray();
		if("0".equals(code)){
			List<FntProductCategory> rootCateList = getRootCateList(list);
			for (FntProductCategory fntProductCategory : rootCateList) {
				JSONObject jo = new JSONObject();
				jo.put("code", fntProductCategory.getCatCode());
				jo.put("name", fntProductCategory.getCatNameCn());
				jary.add(jo);
			}
		} else {
			List<FntProductCategory> level2CateList = getChildCateList(list, TCUtil.lv(code));
			for (FntProductCategory level2Cat : level2CateList) {
				JSONObject jo = new JSONObject();
				jo.put("name", level2Cat.getCatNameCn());
				jo.put("code", level2Cat.getCatCode());
				Long level2CatCode = level2Cat.getCatCode();
				List<FntProductCategory> level3CateList = getChildCateList(list, TCUtil.lv(level2CatCode));
				JSONArray dJary = new JSONArray();
				for (FntProductCategory level3Cat : level3CateList) {
					JSONObject dJo = new JSONObject();
					dJo.put("name", level3Cat.getCatNameCn());
					dJo.put("code", level3Cat.getCatCode());
					dJary.add(dJo);
				}
				jo.put("list", dJary);
				jary.add(jo);
			}
		}
		ajaxOutput(response, jary.toString());
	}
	/**
	 * 
	 * *
	 * @param modelMap
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public void searchAll(ModelMap modelMap, HttpServletResponse response) throws IOException{
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
		ajaxOutput(response, level1Jo.toString());
	}
	
	
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
}
