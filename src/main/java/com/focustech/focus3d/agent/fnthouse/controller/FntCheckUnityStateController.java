package com.focustech.focus3d.agent.fnthouse.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.focustech.common.utils.StringUtils;
import com.focustech.common.utils.TCUtil;
import com.focustech.focus3d.agent.common.controller.CommonController;
import com.focustech.focus3d.agent.filter.LoginFilter;
import com.focustech.focus3d.agent.filter.RequestThreadLocal;
import com.focustech.focus3d.agent.login.service.AgentLoginService;
import com.focustech.focus3d.agent.model.AgentLogin;
import com.focustech.focus3d.furniture.rpc.FntRpc;

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
	private FntRpc fntRpc = new FntRpc();
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
		String userName = "";
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
			userName = loginInfo.getLoginName();
		}
		JSONObject jo = new JSONObject();
		jo.put("Message", msg);
		jo.put("Data", data);
		jo.put("userId", userId);
		jo.put("userName", userName);
		ajaxOutput(response, jo.toString());
	}
	/**
	 * *
	 * @param response
	 * @throws IOException
	 */
	
	@RequestMapping(value = "/logout")
	public void logout(HttpServletRequest req, HttpServletResponse response) throws IOException{
		List<NameValuePair> qparams = new ArrayList<NameValuePair>();
		String result = fntRpc.httpRequest("/shopping_logout.htm", qparams, HttpMethod.GET);
		req.getSession().removeAttribute(LoginFilter.SESSION_KEY);
		JSONObject jo = new JSONObject();
		jo.put("Message", "ok");
		ajaxOutput(response, jo.toString());
	}
	/**
	 * *
	 * @param req
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/register")
	public void register(String myname, String password, String confirmPwd, String verifyCodeReg, HttpServletRequest req, HttpServletResponse response) throws IOException{
		String msg = "error";
		String data = "";
		String userId = "";
		String userName = "";
		AgentLogin loginInfo = null;
		if(StringUtils.isNotEmpty(myname) && StringUtils.isNotEmpty(password) && StringUtils.isNotEmpty(confirmPwd) && StringUtils.isNotEmpty(verifyCodeReg)){
			if(password.equals(confirmPwd)){
				String sValidCode = TCUtil.sv(req.getSession().getAttribute("captcha"));
				if(sValidCode.equalsIgnoreCase(verifyCodeReg)){
					List<NameValuePair> qparams = new ArrayList<NameValuePair>();
					qparams.add(new BasicNameValuePair("username", myname));
					qparams.add(new BasicNameValuePair("password", password));
					qparams.add(new BasicNameValuePair("email", ""));
					String result = fntRpc.httpRequest("/service/users/register.htm", qparams, HttpMethod.POST);
					if(StringUtils.isNotEmpty(result)){
						JSONObject resultJo = JSONObject.fromObject(result);
						String code = TCUtil.svjo(resultJo, "code");
						if("注册成功".equals(code)){
							/*loginInfo = new AgentLogin();
							loginInfo.setUserId(1L);
							loginInfo.setLoginName(myname);
							req.getSession().setAttribute(LoginFilter.SESSION_KEY, loginInfo);
							RequestThreadLocal.setLoginInfo(loginInfo);*/
						}
						msg = code;
					}
				} else {
					data = "验证码不正确";
				}
			} else {
				data = "两次密码输入不一致";
			}
		} else {
			data = "用户名、密码、验证码不能为空";
		}
		/*if(loginInfo != null){
			userId = TCUtil.sv(loginInfo.getUserId());
			userName = loginInfo.getLoginName();
		}*/
		JSONObject jo = new JSONObject();
		jo.put("Message", msg);
		jo.put("Data", data);
		jo.put("userId", "");
		jo.put("userName", "");
		ajaxOutput(response, jo.toString());
	}
	
	
}
