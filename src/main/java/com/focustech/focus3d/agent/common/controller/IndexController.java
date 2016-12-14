package com.focustech.focus3d.agent.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * *
 * @author lihaijun
 *
 */
@Controller
@RequestMapping(value = "/index")
public class IndexController extends CommonController{
	@RequestMapping(method = RequestMethod.GET)
	public String index(ModelMap modelMap){
		//String userResourceOfFirst = getUserResourceOfFirst();
		//return redirect(userResourceOfFirst);
		return "/home/index";
	}
}
