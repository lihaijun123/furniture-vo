package com.focustech.focus3d.agent.account.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.focustech.common.utils.EncryptUtil;
import com.focustech.common.utils.StringUtils;
import com.focustech.focus3d.agent.common.controller.CommonController;
import com.focustech.focus3d.agent.filter.RequestThreadLocal;
import com.focustech.focus3d.agent.model.AgentLogin;
import com.focustech.focus3d.agent.model.AgentUser;
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
@RequestMapping(value = "/accountsub")
public class AgentSubAccountController extends CommonController{
	@Autowired
	private AgentUserService<AgentUser> agentUserService;
	@Autowired
	private ReceiveAddressService<ReceiveAddress> receiveAddressService;
	/**
	 *
	 * *
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String index(ModelMap modelMap){
		AgentLogin currentLogin = RequestThreadLocal.getLoginInfo();
		if(currentLogin != null){
			List<AgentUser> subAccountList = agentUserService.getSubAccountList(currentLogin.getUserId());
			modelMap.put("subAccountList", subAccountList);
		}

		String message = RequestThreadLocal.getMessageCookie().getMessage();
		modelMap.put("message", message);
		return "/user/sub/list";
	}
	/**
	 *
	 * *
	 * @param encryptSn
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/view/{encryptSn}", method = RequestMethod.GET)
	public String view(@PathVariable String encryptSn, ModelMap modelMap){
		String view = "/user/sub/baseinfo";
		try {
			AgentLogin currentLogin = RequestThreadLocal.getLoginInfo();
			if(currentLogin != null && StringUtils.isNotEmpty(encryptSn)){
				Long userId = EncryptUtil.decode(encryptSn);
				AgentUser agentUser = agentUserService.selectBySn(userId, AgentUser.class);
				if(agentUser != null){
					agentUser.setEncryptSn(EncryptUtil.encode(userId));
					modelMap.put("agentUser", agentUser);
				}
				ReceiveAddress receiveAddress = receiveAddressService.getByUserId(userId);
				if(receiveAddress != null){
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
	 * @param agentUser
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(AgentUser agentUser, ModelMap modelMap, HttpServletRequest req){
		String message = "";
		String mobilePhone = agentUser.getMobilePhone();
		if(StringUtils.isNotEmpty(mobilePhone) && mobilePhone.length() == 11){
			AgentLogin currentLogin = RequestThreadLocal.getLoginInfo();
			if(currentLogin != null){
				AgentUser agentSubUser = agentUserService.selectByMobilePhone(mobilePhone);
				if(agentSubUser != null){
					message = "手机号已存在，请更换";
				} else {
					agentUser.setPartnerId("");
					agentUser.setParentSn(currentLogin.getUserId());
					agentUser.setRegChannelType(4);
					agentUserService.saveSubAccount(agentUser);
					message = "创建成功！账号：" + mobilePhone + " 密码：默认手机后6位";
				}
			}
		} else {
			message = "手机号码不能为空";
		}
		if(StringUtils.isNotEmpty(message)){
			RequestThreadLocal.getMessageCookie().addMessage(message);
		}
		return redirect("/accountsub");
	}

	/**
	 * 子账户的付款账户
	 * *
	 * @param encryptSn
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/payaccount", method = RequestMethod.GET)
	public String payAccount(ModelMap modelMap){
		AgentLogin currentLogin = RequestThreadLocal.getLoginInfo();
		if(currentLogin != null){
			AgentUser agentUser = agentUserService.selectBySn(currentLogin.getUserId(), AgentUser.class);
			modelMap.put("agentUser", agentUser);
		}
		return "/user/sub/payaccount";
	}
	/**
	 *
	 * *
	 * @param agentUser
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/payaccount", method = RequestMethod.POST)
	public String postPayAccount(AgentUser agentUser, ModelMap modelMap){
		AgentLogin currentLogin = RequestThreadLocal.getLoginInfo();
		if(currentLogin != null){
			String payWxId = agentUser.getPayWxId();
			String payZfbId = agentUser.getPayZfbId();
			String payYlId = agentUser.getPayYlId();
			Long sn = agentUser.getSn();
			if(sn != null){
				agentUser = agentUserService.selectBySn(sn, AgentUser.class);
				agentUser.setPayWxId(payWxId);
				agentUser.setPayZfbId(payZfbId);
				agentUser.setPayYlId(payYlId);
				agentUserService.updateByKeySelective(agentUser);
				modelMap.put("agentUser", agentUser);
				modelMap.put("message", "修改成功");
			}
		}
		return "/user/sub/payaccount";
	}

}
