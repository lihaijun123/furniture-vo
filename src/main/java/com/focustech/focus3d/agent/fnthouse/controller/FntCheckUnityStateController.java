package com.focustech.focus3d.agent.fnthouse.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.focustech.focus3d.agent.common.controller.CommonController;

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
		JSONObject jo = new JSONObject();
		jo.put("Message", "ok");
		ajaxOutput(response, jo.toString());
	}
}
