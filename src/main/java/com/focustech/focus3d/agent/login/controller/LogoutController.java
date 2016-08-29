package com.focustech.focus3d.agent.login.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.focustech.focus3d.agent.common.controller.CommonController;
import com.focustech.focus3d.agent.filter.LoginFilter;

/**
 *
 *
 * *
 * @author lihaijun
 *
 */
@Controller
@RequestMapping(value = "/logout")
public class LogoutController extends CommonController{
	/*
	 *
	 * *
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String doLogout(ModelMap modelMap, HttpServletRequest req){
		req.getSession().removeAttribute(LoginFilter.SESSION_KEY);
		return redirect("/login");
	}
}
