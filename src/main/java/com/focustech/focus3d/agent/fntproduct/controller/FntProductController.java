package com.focustech.focus3d.agent.fntproduct.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.focustech.focus3d.agent.common.controller.CommonController;
import com.focustech.focus3d.agent.fntproduct.service.FntProductService;
import com.focustech.focus3d.agent.model.FntHouseModel;
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
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(ModelMap modelMap){
		return "/fntproduct/search";
	}
	/**
	 * 
	 * *
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String search(FntProductSearch productSearch, ModelMap modelMap){
		List<FntHouseModel> list = fntProductService.search(productSearch);
		modelMap.addAttribute("list", list);
		return "/fntproduct/search";
	}
	
}
