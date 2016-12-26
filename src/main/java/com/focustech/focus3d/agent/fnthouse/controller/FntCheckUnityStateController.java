package com.focustech.focus3d.agent.fnthouse.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.focustech.focus3d.agent.common.controller.CommonController;
import com.focustech.focus3d.agent.filter.RequestThreadLocal;
import com.focustech.focus3d.agent.model.AgentLogin;

/**
 * 
 * *
 * @author lihaijun
 *
 */
@Controller
@RequestMapping(value = "/common")
public class FntCheckUnityStateController extends CommonController{
	/**
	 * 
	 * *
	 * @throws IOException 
	 */
	@RequestMapping(value = "/checkunitystate")
	public void checkUserStateAndInfo(HttpServletResponse response) throws IOException{
		AgentLogin loginInfo = RequestThreadLocal.getLoginInfo();
		String msg = "ok";
		if(loginInfo == null){
			msg = "nologin";
		}
		JSONObject jo = new JSONObject();
		jo.put("Message", msg);
		ajaxOutput(response, jo.toString());
	}
}
