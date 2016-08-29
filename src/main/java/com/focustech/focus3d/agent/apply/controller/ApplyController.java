package com.focustech.focus3d.agent.apply.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.focustech.cief.filemanage.common.utils.FileManageUtil;
import com.focustech.common.utils.StringUtils;
import com.focustech.common.utils.TCUtil;
import com.focustech.focus3d.agent.common.controller.CommonController;
import com.focustech.focus3d.agent.message.service.MessageValidateService;
import com.focustech.focus3d.agent.model.AgentUser;
import com.focustech.focus3d.agent.model.MessageValidate;
import com.focustech.focus3d.agent.sms.service.SmsService;
import com.focustech.focus3d.agent.user.service.AgentUserService;
import com.focustech.focus3d.agent.utils.SmsSendType;

/**
 * 申请代理
 * *
 * @author lihaijun
 *
 */
@Controller
@RequestMapping(value = "/apply")
public class ApplyController extends CommonController{
	@Autowired
	private MessageValidateService<MessageValidate> messageValidateService;
	@Autowired
	private AgentUserService<AgentUser> agentUserService;
	@Autowired
	private SmsService smsService;
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
		String view = redirect("/apply/complate");
		String userName = agentUser.getUserName();
		String mobilePhone = agentUser.getMobilePhone();
		String street = agentUser.getStreet();
		String smsCode = agentUser.getSmsCode();
		String validCode = agentUser.getValidCode();
		String msg = "";
		if(
				StringUtils.isNotEmpty(userName)
				&& StringUtils.isNotEmpty(mobilePhone)
				&& StringUtils.isNotEmpty(street)
				&& StringUtils.isNotEmpty(smsCode)
				&& StringUtils.isNotEmpty(validCode)
		){
			MessageValidate messageValidate = messageValidateService.selectByMobilePhone(mobilePhone, smsCode);
			if(messageValidate != null){
				//验证码是否正确
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
						messageValidateService.setStatus(messageValidate, 0);
						//发送短信通知
						Map<String, String> parame = new HashMap<String, String>();
						parame.put("userName", agentUser.getUserName());
						parame.put("userMobile",agentUser.getMobilePhone());
						smsService.send(SmsSendType.AGENT_APPLY_INSIDE_NOTIFY, parame);
					}
				} else {
					msg = "验证码有误";
				}
			} else {
				msg = "短信验证码有误";
			}
		} else {
			msg = "请填写完整信息";
		}
		if(StringUtils.isNotEmpty(msg)){
			Long idCardFileSn = agentUser.getIdCardFileSn();
			if(idCardFileSn != null){
				String idCardFileUrl = FileManageUtil.getFileURL(idCardFileSn);
				if(StringUtils.isNotEmpty(idCardFileUrl)){
					agentUser.setIdCardFileUrl(idCardFileUrl);
				}
			}
			Long kbisFileSn = agentUser.getKbisFileSn();
			if(kbisFileSn != null){
				String kbisFileUrl = FileManageUtil.getFileURL(kbisFileSn);
				if(StringUtils.isNotEmpty(kbisFileUrl)){
					agentUser.setKbisFileUrl(kbisFileUrl);
				}
			}
			modelMap.put("message", msg);
			modelMap.put("agentUser", agentUser);
			view = "/user/apply";
		}
		return view;
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
