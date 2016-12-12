package com.focustech.focus3d.agent.login.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.focustech.common.utils.MD5Util;
import com.focustech.common.utils.StringUtils;
import com.focustech.common.utils.TCUtil;
import com.focustech.focus3d.agent.common.controller.CommonController;
import com.focustech.focus3d.agent.filter.LoginFilter;
import com.focustech.focus3d.agent.filter.RequestThreadLocal;
import com.focustech.focus3d.agent.login.service.AgentLoginService;
import com.focustech.focus3d.agent.model.AgentLogin;
import com.focustech.focus3d.agent.model.MessageValidate;

/**
 *
 * *
 * @author lihaijun
 *
 */
@Controller
@RequestMapping("/login")
public class LoginController extends CommonController{
	@Autowired
	private AgentLoginService<AgentLogin> agentLoginService;
/*	@Autowired
	private MessageValidateService<MessageValidate> messageValidateService;*/
	@Value(value = "${test.account}")
	private String testAccount;
	private int testAccountLoginCount = 0;
	/**
	 *
	 * *
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String index(ModelMap modelMap){
		String message = RequestThreadLocal.getMessageCookie().getMessage();
		modelMap.put("message", message);
		return "/login";
	}
	/**
	 *
	 * *
	 * @param reqLogin
	 * @param modelMap
	 * @param req
	 * @param response
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String doLogin(AgentLogin reqLogin, ModelMap modelMap, HttpServletRequest req, HttpServletResponse response){
		String view = "/login";
		String loginName = reqLogin.getLoginName();
		String password = reqLogin.getPassword();
		//String smsCode = reqLogin.getSmsCode();
		String validCode = reqLogin.getValidCode();
		int status = 0;
		if(StringUtils.isNotEmpty(loginName) && StringUtils.isNotEmpty(password) && StringUtils.isNotEmpty(validCode)){
			boolean isTestAccount = true;//isTestAccount(loginName, password, smsCode);
			MessageValidate messageValidate = null;
			if(!isTestAccount){
				//messageValidate = messageValidateService.selectByMobilePhone(loginName, smsCode);
			}
			if(isTestAccount){
				//验证验证码是否正确
				String sValidCode = TCUtil.sv(req.getSession().getAttribute("captcha"));
				if(sValidCode.equalsIgnoreCase(validCode)){
					AgentLogin agentLogin = agentLoginService.select(loginName);
					if(agentLogin != null){
						boolean isValidate = agentLogin.getPassword().equals(MD5Util.MD5Encode(password, ""));
						if(isValidate){
							status = agentLogin.getStatus();
							if(status == 1){
								req.getSession().setAttribute(LoginFilter.SESSION_KEY, agentLogin);
								RequestThreadLocal.setLoginInfo(agentLogin);
								/*List<AgentResource> agentResources = getUserResourceList();
								if(ListUtils.isNotEmpty(agentResources)){
								}*/
								view = redirect("/index");
								//删除短信验证码
								/*if(messageValidate != null){
									messageValidateService.setStatus(messageValidate, 0);
								}*/
								Integer loginTimes = TCUtil.iv(agentLogin.getLoginTimes());
								agentLogin.setLoginTimes(++ loginTimes);
								agentLogin.setLastLoginTime(new Date());
								agentLoginService.updateByKeySelective(agentLogin);
								if(isTestAccount){
									testAccountLoginCount ++;
								}
							}
						}
					}
				} else {
					status = 7;
				}
			} else {
				status = 6;
			}
		}
		String msg = "";
		if(status != 1){
			if(status == 6){
				msg = "短信验证码有误";
			} else if(status == 7){
				msg = "验证码有误";
			} else {
				msg = "用户名或者密码错误";
			}
			modelMap.addAttribute("message", msg);
		}
		return view;
	}
	/**
	 * 是否测试账号
	 * *
	 * @param loginName
	 * @param password
	 * @param verifyCode
	 * @return
	 */
	private boolean isTestAccount(String loginName, String password, String verifyCode) {
		//是不是测试账户
		boolean isTestAccount = false;
		if(StringUtils.isNotEmpty(testAccount)){
			String[] testAccounts = testAccount.split(",");
			for (String testAccount : testAccounts) {
				String[] accountInfo = testAccount.split("#");
				if(accountInfo != null && accountInfo.length == 4){
					String tLoginName = accountInfo[0];
					String tPassword = accountInfo[1];
					String tVerifyCode = accountInfo[2];
					int tLimit = TCUtil.iv(accountInfo[3]);
					if(StringUtils.isNotEmpty(tLoginName) && StringUtils.isNotEmpty(tPassword) && StringUtils.isNotEmpty(tVerifyCode) && tLimit > 0){
						if(tLoginName.equals(loginName) && tPassword.equals(password) && tVerifyCode.equals(verifyCode) && testAccountLoginCount <= tLimit){
							isTestAccount = true;
							break;
						}
					}
				}
			}
		}
		return isTestAccount;
	}

	public String getTestAccount() {
		return testAccount;
	}
	public void setTestAccount(String testAccount) {
		this.testAccount = testAccount;
	}
}
