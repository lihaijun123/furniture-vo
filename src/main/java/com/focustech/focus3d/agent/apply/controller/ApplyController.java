package com.focustech.focus3d.agent.apply.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.focustech.common.utils.MD5Util;
import com.focustech.common.utils.StringUtils;
import com.focustech.common.utils.TCUtil;
import com.focustech.focus3d.agent.common.controller.CommonController;
import com.focustech.focus3d.agent.login.service.AgentLoginService;
import com.focustech.focus3d.agent.model.AgentLogin;
import com.focustech.focus3d.agent.model.AgentUser;
import com.focustech.focus3d.agent.user.service.AgentUserService;

/**
 * 申请代理
 * *
 * @author lihaijun
 *
 */
@Controller
@RequestMapping(value = "/register")
public class ApplyController extends CommonController{
	@Autowired
	private AgentUserService<AgentUser> agentUserService;
	@Autowired
	private AgentLoginService<AgentLogin> agentLoginService;
	/**
	 *
	 * *
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String index(ModelMap modelMap){
		return "/user/apply";
	}
	/**
	 *
	 *
	 * *
	 * @param agentUser
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String doPost(AgentUser agentUser, ModelMap modelMap, HttpServletRequest req){
		String mobilePhone = agentUser.getMobilePhone();
		String validCode = agentUser.getValidCode();
		String password = agentUser.getLoginInfo().getPassword();
		String passwordConfirm = agentUser.getLoginInfo().getPasswordConfirm();
		String msg = "";
		if(StringUtils.isEmpty(mobilePhone)){
			msg = "手机号不能为空";
		}
		else if(StringUtils.isEmpty(password)){
			msg = "密码不能为空";
		}
		else if(StringUtils.isEmpty(passwordConfirm)){
			msg = "确认密码不能为空";
		} else if(!password.equals(passwordConfirm)){
			msg = "两次密码输入不一致";
		} else if(StringUtils.isEmpty(validCode)){
			msg = "验证码不能为空";
		} else {
			String sValidCode = TCUtil.sv(req.getSession().getAttribute("captcha"));
			if(sValidCode.equalsIgnoreCase(validCode)){
				AgentUser dbExist = agentUserService.selectByMobilePhone(mobilePhone);
				if(dbExist != null){
					msg = "手机号码已经被申请过，请换个手机号。";
				} else {
					agentUser.setPartnerId("");
					agentUser.setStatus(1);
					//注册通道类型
					int mobileSystemType = getMobileSystemType(req);
					int regChannelType = 1;
					if(mobileSystemType > 0){
						if(isWeixinBrowser(req)){
							regChannelType = 2;
						}
					}
					agentUser.setRegChannelType(regChannelType);
					agentUserService.insert(agentUser);
					//创建账户
				    AgentLogin agentLogin = new AgentLogin();
					agentLogin.setUserId(agentUser.getSn());
					agentLogin.setLoginName(agentUser.getMobilePhone());
					password = MD5Util.MD5Encode(agentUser.getPartnerId(), "");
					agentLogin.setPassword(password);
					agentLogin.setStatus(1);
					agentLoginService.insert(agentLogin);
					return redirect("/register/complate");
				}
			} else {
				msg = "验证码有误";
			}
		} 
		modelMap.put("agentUser", agentUser);
		modelMap.put("message", msg);
		return "/user/apply";
	}
	
	/**
	 *
	 * *
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/complate", method = RequestMethod.GET)
	public String complate(ModelMap modelMap){
		return "/user/complate";
	}
}
