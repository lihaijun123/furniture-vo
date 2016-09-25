package com.focustech.focus3d.agent.fntproduct.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.focustech.focus3d.agent.common.controller.CommonController;

/**
 * 
 * *
 * @author lihaijun
 *
 */
@Controller
@RequestMapping(value = "/fntproduct")
public class FntProductController  extends CommonController {
	
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
		
		return "/fntproduct/search";
	}
	
}
