package com.focustech.focus3d.agent.rpc.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.focustech.common.utils.StringUtils;
import com.focustech.focus3d.agent.common.controller.CommonController;
import com.focustech.focus3d.agent.rpc.service.AgentRpcService;
import com.focustech.focus3d.pay.wx.common.Util;

/**
 *
 * *
 * @author lihaijun
 *
 */
@Controller
@RequestMapping(value = "/agent/rpc")
public class AgentRpcController extends CommonController{
	@Autowired
	private AgentRpcService agentRpcService;
	/**
	 *
	 * *
	 * @param key
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public void search(HttpServletRequest request, HttpServletResponse response) throws IOException{
		InputStream inputStream = null;
		JSONObject jo = new JSONObject();
		try {
			boolean searchAgent = false;
			inputStream = request.getInputStream();
			String key = Util.inputStreamToString(inputStream);
			if(StringUtils.isNotEmpty(key) && key.length() > 1){
				searchAgent = agentRpcService.searchAgent(key);
			}
			jo.put("status", searchAgent ? "1" : "0");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(inputStream != null){
				inputStream.close();
			}
		}
		ajaxOutput(response, jo.toString());
	}


}
