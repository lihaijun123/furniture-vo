package com.focustech.focus3d.agent.account.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.focustech.common.utils.EncryptUtil;
import com.focustech.common.utils.MD5Util;
import com.focustech.common.utils.StringUtils;
import com.focustech.focus3d.agent.common.controller.CommonController;
import com.focustech.focus3d.agent.filter.LoginFilter;
import com.focustech.focus3d.agent.filter.RequestThreadLocal;
import com.focustech.focus3d.agent.login.service.AgentLoginService;
import com.focustech.focus3d.agent.message.service.MessageValidateService;
import com.focustech.focus3d.agent.model.AgentLogin;
import com.focustech.focus3d.agent.model.AgentUser;
import com.focustech.focus3d.agent.model.MessageValidate;
import com.focustech.focus3d.agent.model.ReceiveAddress;
import com.focustech.focus3d.agent.order.service.ReceiveAddressService;
import com.focustech.focus3d.agent.user.service.AgentUserService;

/**
 *
 * *
 * @author lihaijun
 *
 */
@Controller
@RequestMapping(value = "/user")
public class UserController extends CommonController{
	@Autowired
	private AgentUserService<AgentUser> agentUserService;
	@Autowired
	private AgentLoginService<AgentLogin> agentLoginService;
	@Autowired
	private MessageValidateService<MessageValidate> messageValidateService;
	@Autowired
	private ReceiveAddressService<ReceiveAddress> receiveAddressService;
	/**
	 *
	 * *
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String home(ModelMap modelMap){
		return "/user/home";
	}
	/**
	 *
	 * *
	 * @param uid
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/pwdmgr", method = RequestMethod.GET)
	public String getChangePassword(ModelMap modelMap, HttpServletRequest req){
		try {
			AgentLogin currentLogin = RequestThreadLocal.getLoginInfo();
			if(currentLogin != null){
				AgentLogin agentLogin = agentLoginService.selectBySn(currentLogin.getSn(), AgentLogin.class);
				Long userId = agentLogin.getUserId();
				if(userId != null && userId > 0){
					AgentUser agentUser = agentUserService.selectBySn(userId, AgentUser.class);
					if(agentUser != null){
						agentUser.setEncryptSn(EncryptUtil.encode(userId));
					}
					modelMap.put("agentUser", agentUser);
				}
				String message = RequestThreadLocal.getMessageCookie().getMessage();
				modelMap.put("message", message);
				Integer loginTimes = agentLogin.getLoginTimes();
				if(loginTimes <= 2){
					modelMap.put("message", "为了账号安全，请修改密码。");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/user/pwdmgr";
	}
	/**
	 *
	 * *
	 * @param uid
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/pwdmgr", method = RequestMethod.POST)
	public String postChangePassword(AgentLogin login, ModelMap modelMap, HttpServletRequest req){
		AgentLogin currentLogin = RequestThreadLocal.getLoginInfo();
		String loginName = currentLogin.getLoginName();
		String password = login.getPassword();
		String passwordNew = login.getPassword();
		String passwordNewS = login.getPasswordConfirm();
		String verifyCode = login.getSmsCode();
		String msg = "";
		String view = "/account/pwdmgr";
		if(StringUtils.isNotEmpty(password) && StringUtils.isNotEmpty(passwordNew) && StringUtils.isNotEmpty(passwordNewS) && StringUtils.isNotEmpty(verifyCode)){
			if(!passwordNew.equals(passwordNewS)){
				msg = "两次输入的新密码不一致";
			}
			else if(StringUtils.isNotEmpty(loginName)){
				MessageValidate messageValidate = messageValidateService.selectByMobilePhone(loginName, verifyCode);
				if(messageValidate != null){
					AgentLogin dbLogin = agentLoginService.select(loginName);
					if(dbLogin != null){
						boolean isValidate = dbLogin.getPassword().equals(MD5Util.MD5Encode(password, ""));
						if(isValidate){
							dbLogin.setPassword(MD5Util.MD5Encode(passwordNew, ""));
							agentLoginService.updateByKeySelective(dbLogin);
							req.getSession().removeAttribute(LoginFilter.SESSION_KEY);
							view = redirect("/login");
						} else {
							msg = "原始密码输入有误";
						}
					} else {
						msg = "用户不存在";
					}
				} else {
					msg = "短信验证码有误";
				}
			} else {
				msg = "请登录";
			}
		} else {
			msg = "密码不能为空";
		}
		if(StringUtils.isNotEmpty(msg)){
			RequestThreadLocal.getMessageCookie().addMessage(msg);
		}
		return view;
	}
	
	/**
	 *
	 * *
	 * @param uid
	 * @param modelMap
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/baseinfo", method = RequestMethod.GET)
	public String getBaseInfo(ModelMap modelMap, HttpServletRequest req){
		String view = "/user/baseinfo";
		try {
			AgentLogin currentLogin = RequestThreadLocal.getLoginInfo();
			if(currentLogin != null){
				AgentLogin agentLogin = agentLoginService.selectBySn(currentLogin.getSn(), AgentLogin.class);
				Long userId = agentLogin.getUserId();
				if(userId != null && userId > 0){
					AgentUser agentUser = agentUserService.selectBySn(userId, AgentUser.class);
					if(agentUser != null){
						agentUser.setEncryptSn(EncryptUtil.encode(userId));
					}
					modelMap.put("agentUser", agentUser);
					Integer loginTimes = agentLogin.getLoginTimes();
					if(loginTimes <= 1){
						agentLogin.setLoginTimes(++loginTimes);
						agentLoginService.updateByKeySelective(agentLogin);
						view = redirect("/account/pwdmgr");
					}
					modelMap.put("message", RequestThreadLocal.getMessageCookie().getMessage());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return view;
	}
	/**
	 *
	 * *
	 * @param uid
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/baseinfo", method = RequestMethod.POST)
	public String postBaseInfo(AgentUser agentUser, ModelMap modelMap, HttpServletRequest req){
		AgentLogin currentLogin = RequestThreadLocal.getLoginInfo();
		String msg = "";
		String view = "";
		if(currentLogin != null){
			view = redirect("/account");
			String loginName = currentLogin.getLoginName();
			if(loginName.equals(agentUser.getMobilePhone())){
				Long userId = currentLogin.getUserId();
				if(userId != null && userId > 0){
					AgentUser dbUser = agentUserService.selectBySn(userId, AgentUser.class);
					if(agentUser != null){
						dbUser.setUserName(agentUser.getUserName());
						dbUser.setCompanyName(agentUser.getCompanyName());
						dbUser.setProvince(agentUser.getProvince());
						dbUser.setCity(agentUser.getCity());
						dbUser.setStreet(agentUser.getStreet());
						agentUserService.updateByKeySelective(dbUser);
						msg = "修改成功";
					}
					modelMap.put("agentUser", dbUser);
				}
			} else {
				msg = "手机号码需要申请修改";
			}
		}
		//modelMap.put("message", msg);
		RequestThreadLocal.getMessageCookie().addMessage(msg);
		modelMap.put("tabIdx", 0);
		return view;
	}
	/**
	 *
	 * *
	 * @param uid
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/revaddress", method = RequestMethod.GET)
	public String revAddress(ModelMap modelMap, HttpServletRequest req){
		String view = "/user/revaddress";
		try {
			AgentLogin currentLogin = RequestThreadLocal.getLoginInfo();
			if(currentLogin != null){
				AgentLogin agentLogin = agentLoginService.selectBySn(currentLogin.getSn(), AgentLogin.class);
				Long userId = agentLogin.getUserId();
				if(userId != null && userId > 0){
					ReceiveAddress receiveAddress = receiveAddressService.getByUserId(userId);
					if(receiveAddress == null){
						AgentUser agentUser = agentUserService.selectBySn(userId, AgentUser.class);
						receiveAddress = new ReceiveAddress();
						receiveAddress.setUserName(agentUser.getUserName());
						receiveAddress.setMobilePhone(agentUser.getMobilePhone());
						receiveAddress.setProvince(agentUser.getProvince());
						receiveAddress.setCity(agentUser.getCity());
						receiveAddress.setStreet(agentUser.getStreet());
					}
					modelMap.put("receiveAddress", receiveAddress);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return view;
	}
	/**
	 *
	 * *
	 */
	@RequestMapping(value = "/revaddress", method = RequestMethod.POST)
	public String saveRevAddress(ReceiveAddress receiveAddress, ModelMap modelMap, HttpServletRequest req){
		String message = "";
		String view = "/user/revAddress";
		try {
			AgentLogin currentLogin = RequestThreadLocal.getLoginInfo();
			if(currentLogin != null){
				AgentLogin agentLogin = agentLoginService.selectBySn(currentLogin.getSn(), AgentLogin.class);
				Long userId = agentLogin.getUserId();
				if(userId != null && userId > 0){
					String userName = receiveAddress.getUserName();
					String mobilePhone = receiveAddress.getMobilePhone();
					String street = receiveAddress.getStreet();
					if(StringUtils.isNotEmpty(userName) && StringUtils.isNotEmpty(mobilePhone) && StringUtils.isNotEmpty(street)){
						receiveAddress.setUserId(userId);
						Long sn = receiveAddress.getSn();
						if(sn != null){
							receiveAddressService.updateByKeySelective(receiveAddress);

						} else {
							receiveAddressService.insert(receiveAddress);
						}
						message = "修改成功";
					} else {
						message = "请填写姓名、手机号码、街道信息";
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		modelMap.put("message", message);
		return view;
	}

}
