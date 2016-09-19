package com.focustech.focus3d.agent.fnthouse.controller;

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
@RequestMapping(value = "/fnthouse")
public class FntHouseController extends CommonController {
	/**
	 * 
	 * *
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(ModelMap modelMap){
		return "/fnthouse/search";
	}
}
