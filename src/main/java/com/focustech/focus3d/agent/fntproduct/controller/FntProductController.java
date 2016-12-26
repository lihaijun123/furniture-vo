package com.focustech.focus3d.agent.fntproduct.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.focustech.focus3d.agent.common.controller.CommonController;
import com.focustech.focus3d.agent.fntproduct.service.FntProductService;
import com.focustech.focus3d.agent.model.FntProductModel;

/**
 * 
 * *
 * @author lihaijun
 *
 */
@Controller
@RequestMapping(value = "/fntproduct")
public class FntProductController  extends CommonController {
	@Autowired
	private FntProductService<FntProductModel> fntProductService;
	/**
	 * 
	 * *
	 * @param modelMap
	 * @return
	 */
	/*@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(ModelMap modelMap){
		return "/fntproduct/search";
	}*/
	/**
	 * 
	 * *
	 * @param modelMap
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/search")
	public void search(FntProductSearch productSearch, ModelMap modelMap, HttpServletResponse response) throws IOException{
		List<FntProductModel> list = fntProductService.search(productSearch);
		JSONArray jary = new JSONArray();
		for (FntProductModel fntProductModel : list) {
			JSONObject jo = new JSONObject();
			jo.put("url", fntProductModel.getModelFileUrl());
			jo.put("version", fntProductModel.getModelFileVersion());
			jo.put("picUrl", fntProductModel.getPicFileUrl());
			jary.add(jo);
		}
		ajaxOutput(response, jary.toString());
		//modelMap.addAttribute("list", list);
		//return "/fntproduct/search";
		
	}
/*	*//**
	 * 
	 * *
	 * @param modelMap
	 * @return
	 *//*
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String search(FntProductSearch productSearch, ModelMap modelMap){
		List<FntProductModel> list = fntProductService.search(productSearch);
		JSONArray jary = new JSONArray();
		for (FntProductModel fntProductModel : list) {
			JSONObject jo = new JSONObject();
			jo.put("url", fntProductModel.getModelFileUrl());
			jo.put("version", fntProductModel.getModelFileVersion());
			jo.put("picUrl", fntProductModel.getPicFileUrl());
			jary.add(jo);
		}
		//modelMap.addAttribute("list", list);
		//return "/fntproduct/search";
		
	}
*/	
}
