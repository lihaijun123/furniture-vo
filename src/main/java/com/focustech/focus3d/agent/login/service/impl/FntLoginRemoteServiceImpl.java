package com.focustech.focus3d.agent.login.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import com.focustech.common.utils.MD5Util;
import com.focustech.common.utils.StringUtils;
import com.focustech.common.utils.TCUtil;
import com.focustech.focus3d.agent.dao.CommonDao;
import com.focustech.focus3d.agent.login.service.AgentLoginService;
import com.focustech.focus3d.agent.model.AgentLogin;
import com.focustech.focus3d.agent.service.impl.CommonServiceTemplate;
import com.focustech.focus3d.furniture.rpc.FntRpc;
/**
 * *
 * @author lihaijun
 *
 */
@Service
public class FntLoginRemoteServiceImpl extends CommonServiceTemplate<AgentLogin> implements AgentLoginService<AgentLogin> {
	private FntRpc fntRpc = new FntRpc();
	@Override
	public AgentLogin select(String loginName, String password) {
		List<NameValuePair> qparams = new ArrayList<NameValuePair>();
		qparams.add(new BasicNameValuePair("username", loginName));
		String pwd = MD5Util.MD5Encode(password, "utf-8").toLowerCase();
		qparams.add(new BasicNameValuePair("password", pwd));
		String result = fntRpc.httpRequest("/service/users/login.htm", qparams, HttpMethod.POST);
		if(StringUtils.isNotEmpty(result)){
			JSONArray jary = JSONArray.fromObject(result);
			if(!jary.isEmpty()){
				JSONObject jo = (JSONObject)jary.get(0);
				String code = TCUtil.svjo(jo, "code");
				if("success".equals(code)){
					String id = TCUtil.svjo(jo, "id");
					AgentLogin agentLogin = new AgentLogin();
					agentLogin.setLoginName(loginName);
					agentLogin.setId(id);
					return agentLogin;
				}
			}
		}
		return null;
	}
	
	@Override
	public AgentLogin select(String loginName) {
		
		return null;
	}

	@Override
	public CommonDao getDao() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
