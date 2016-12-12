package com.focustech.focus3d.agent.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.focustech.focus3d.agent.filter.RequestThreadLocal;
import com.focustech.focus3d.agent.model.AgentLogin;
import com.focustech.focus3d.agent.model.AgentUser;
import com.focustech.focus3d.agent.user.service.AgentUserService;

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
	private AgentUserService<AgentUser> userService;
	@RequestMapping(method = RequestMethod.GET)
	public String index(ModelMap modelMap){
		//String userResourceOfFirst = getUserResourceOfFirst();
		//return redirect(userResourceOfFirst);
		AgentLogin loginInfo = RequestThreadLocal.getLoginInfo();
		if(loginInfo != null){
			Long userId = loginInfo.getUserId();
			AgentUser agentUser = userService.selectBySn(userId, AgentUser.class);
			modelMap.put("user", agentUser);
		}
		return "/home/index";
	}
}
