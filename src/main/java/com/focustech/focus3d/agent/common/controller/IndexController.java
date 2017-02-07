package com.focustech.focus3d.agent.common.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.focustech.focus3d.agent.fnthouse.service.FntHouseService;
import com.focustech.focus3d.agent.model.FntHouseModel;

/**
 *
 * *
 * @author lihaijun
 *
 */
@Controller
@RequestMapping(value = "/index")
public class IndexController extends CommonController{
	@Autowired
	private FntHouseService<FntHouseModel> houseService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String index(ModelMap modelMap){
		List<FntHouseModel> recommendHouseList = houseService.list(true);
		modelMap.put("recommendHouseList", recommendHouseList);
		return "/home/index";
	}
	
	@RequestMapping(value="/old", method = RequestMethod.GET)
	public String oldIndex(ModelMap modelMap){
		List<FntHouseModel> recommendHouseList = houseService.list(true);
		modelMap.put("recommendHouseList", recommendHouseList);
		return "/home/index-old";
	}
}
