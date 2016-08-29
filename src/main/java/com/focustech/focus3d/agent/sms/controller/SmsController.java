package com.focustech.focus3d.agent.sms.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.focustech.common.utils.IPTool;
import com.focustech.common.utils.StringUtils;
import com.focustech.common.utils.TCUtil;
import com.focustech.focus3d.agent.common.controller.CommonController;
import com.focustech.focus3d.agent.message.service.MessageValidateService;
import com.focustech.focus3d.agent.model.MessageValidate;
import com.focustech.focus3d.agent.sms.service.SmsService;
import com.focustech.focus3d.agent.utils.SmsSendType;
/**
 *
 * *
 * @author lihaijun
 *
 */
@Controller
@RequestMapping(value = "/sms")
public class SmsController extends CommonController {
	@Autowired
	private SmsService smsService;
	@Autowired
	private MessageValidateService<MessageValidate> messageValidateService;

	private Lock lock = new ReentrantLock();

	private static final int SMS_SEND_LIMIT_PER_DAY = 15;
	/**
	 * 发送短信
	 * *
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/send", method = RequestMethod.POST)
	public void sendSms(String mobilePhone, String type, HttpServletRequest req, HttpServletResponse resp){
		int status = 0;
		try {

			if(StringUtils.isNotEmpty(mobilePhone)){
				String millis = TCUtil.sv(System.currentTimeMillis());
				String verifyCode = millis.substring(millis.length() - 6);
				try {
					String ipAddr = TCUtil.sv(IPTool.getRealIp(req));
					try {
						lock.lock();
						List<MessageValidate> list = messageValidateService.getListByPerDay(mobilePhone);
						if(list.size() <= SMS_SEND_LIMIT_PER_DAY){
							messageValidateService.save(mobilePhone, verifyCode, ipAddr);
							Map<String, String> parame = new HashMap<String, String>();
							parame.put("verifyCode", verifyCode);
							status = smsService.send(SmsSendType.valueOf(TCUtil.iv(type)), mobilePhone, parame);
						} else {
							throw new RuntimeException("发送短信超出每天限制：" + SMS_SEND_LIMIT_PER_DAY);
						}
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						lock.unlock();
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			JSONObject jo = new JSONObject();
			jo.put("status", TCUtil.sv(status));
			ajaxOutput(resp, jo.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
