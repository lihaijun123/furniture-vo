package com.focustech.focus3d.agent.fnthouse.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.focustech.common.utils.StringUtils;
import com.focustech.common.utils.TCUtil;
import com.focustech.focus3d.agent.common.controller.CommonController;
import com.focustech.focus3d.agent.filter.LoginFilter;
import com.focustech.focus3d.agent.filter.RequestThreadLocal;
import com.focustech.focus3d.agent.login.service.AgentLoginService;
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
	@Autowired
	private AgentLoginService<AgentLogin> agentLoginService;
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
	
	@RequestMapping(value = "/loginAuth/login")
	public void loginAuth(String mynamelogin, String passwordlogin, String verifyCodelogin, HttpServletRequest req, HttpServletResponse response) throws IOException{
		AgentLogin loginInfo = RequestThreadLocal.getLoginInfo();
		String msg = "error";
		String data = "";
		String userId = "";
		if(loginInfo != null){
			msg = "alreadylogin";
		} else {
			//登录
			String loginName = mynamelogin;
			String password = passwordlogin;
			//String smsCode = reqLogin.getSmsCode();
			String validCode = verifyCodelogin;
			if(StringUtils.isNotEmpty(loginName) && StringUtils.isNotEmpty(password) && StringUtils.isNotEmpty(validCode)){
				boolean isTestAccount = true;//isTestAccount(loginName, password, smsCode);
				if(!isTestAccount){
					//messageValidate = messageValidateService.selectByMobilePhone(loginName, smsCode);
				}
				if(isTestAccount){
					//验证验证码是否正确
					String sValidCode = TCUtil.sv(req.getSession().getAttribute("captcha"));
					if(sValidCode.equalsIgnoreCase(validCode)){
						loginInfo = agentLoginService.select(loginName, password);
						if(loginInfo != null){
							req.getSession().setAttribute(LoginFilter.SESSION_KEY, loginInfo);
							RequestThreadLocal.setLoginInfo(loginInfo);
							msg = "success";
						} else {
							data = "用户名或者密码有误";
						}
					} else {
						data = "验证码不正确";
					}
				} else {
				}
			} else {
				data = "请填写用户名和密码";
			}
		}
		if(loginInfo != null){
			userId = TCUtil.sv(loginInfo.getUserId());
		}
		JSONObject jo = new JSONObject();
		jo.put("Message", msg);
		jo.put("Data", data);
		jo.put("userId", userId);
		ajaxOutput(response, jo.toString());
	}
}
