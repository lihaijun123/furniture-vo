package com.focustech.focus3d.agent.sms.service;

import java.rmi.RemoteException;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.focustech.common.utils.DateUtils;
import com.focustech.common.utils.StringUtils;
import com.focustech.common.utils.TCUtil;
import com.focustech.focus3d.agent.utils.SmsSendType;
import com.focustech.focus3d.bundle.sms.client.SmsClient;
/**
 * 短信服务
 * *
 * @author lihaijun
 *
 */
@Service
public class SmsServiceImpl implements SmsService {
	@Autowired
	private SmsClient smsClient;
	@Value(value = "${apply.sms.mobile.list}")
	private String focus3dMobileList;
	private static final String PREFIX = "[小小梦想家]";
	private ExecutorService pool = Executors.newFixedThreadPool(4);

	@Override
	public int send(SmsSendType sendType, String mobileStr, Map<String, String> parame) {
		boolean isSend = true;
		int status = 0;
		if(parame != null){
			String msg = "";
			String curDate = DateUtils.getCurDate(DateUtils.DEFAULT_FORMATE_ALL);
			if(SmsSendType.ORDER_SUBMIT.equals(sendType)){
				String userName = TCUtil.sv(parame, "userName");
				String orderNum = TCUtil.sv(parame, "orderNum");
				String userMobile = TCUtil.sv(parame, "userMobile");
				String orderPrice = TCUtil.sv(parame, "orderPrice");
				msg = PREFIX + "新订单提交：姓名：" + userName + ", 手机："
				+ userMobile + ", 提交时间：" + curDate + ", 订单号：" + orderNum + "，金额：" + orderPrice + " 元";
				mobileStr = focus3dMobileList;
				if(StringUtils.isEmpty(userMobile) || StringUtils.isEmpty(userName)){
					isSend = false;
				}
			}
			else if(SmsSendType.ORDER_PAY_OK.equals(sendType)){
				String userName = TCUtil.sv(parame, "userName");
				String orderNum = TCUtil.sv(parame, "orderNum");
				String userMobile = TCUtil.sv(parame, "userMobile");
				String payTypeName = TCUtil.sv(parame, "payTypeName");
				String orderPrice = TCUtil.sv(parame, "orderPrice");
				msg = PREFIX + "新订单成功支付：姓名：" + userName + ", 手机：" + userMobile + ", 提交时间：" + curDate
				+ ", 订单号：" + orderNum + ", 支付方式：" + payTypeName + "，金额：" + orderPrice + " 元";
				mobileStr = focus3dMobileList;
			}
			else if(SmsSendType.AGENT_APPLY_INSIDE_NOTIFY.equals(sendType)){
				String userName = TCUtil.sv(parame, "userName");
				String userMobile = TCUtil.sv(parame, "userMobile");
				msg = PREFIX + "新代理商申请：姓名：" + userName + ", 手机：" + userMobile + ", 申请时间：" + curDate;
				mobileStr = focus3dMobileList;
			}
			else if(SmsSendType.AGENT_LOGIN.equals(sendType)){
				String verifyCode = TCUtil.sv(parame, "verifyCode");
				msg = PREFIX + "您正在登陆，校验码 " + verifyCode + "。";
			}
			else if(SmsSendType.AGENT_APPLY.equals(sendType)){
				String verifyCode = TCUtil.sv(parame, "verifyCode");
				msg = PREFIX + "您正在申请，校验码 " + verifyCode + "。";
			}
			else if(SmsSendType.AGENT_MODIFY_PASSWORD.equals(sendType)){
				String verifyCode = TCUtil.sv(parame, "verifyCode");
				msg = PREFIX + "您正在修改密码，校验码 " + verifyCode + "。";
			}
			else {
				String verifyCode = TCUtil.sv(parame, "verifyCode");
				msg = PREFIX + "校验码 " + verifyCode + "。";
			}
			if(isSend){
				//发短信通知
				pool.execute(new smsTask(mobileStr, msg));
			}
		}
		return status;
	}
	/**
	 *
	 * *
	 * @author lihaijun
	 *
	 */
	class smsTask implements Runnable {

		private String mobileStr;
		private String msg;

		public smsTask(String mobileStr, String msg){
			this.mobileStr = mobileStr;
			this.msg = msg;
		}
		@Override
		public void run() {
			String[] split = mobileStr.split(",");
			for (String mobile : split) {
				try {
					if(StringUtils.isNotEmpty(mobile) && StringUtils.isNotEmpty(msg)){
						smsClient.send(mobile, msg);
					}
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public String getFocus3dMobileList() {
		return focus3dMobileList;
	}
	public void setFocus3dMobileList(String focus3dMobileList) {
		this.focus3dMobileList = focus3dMobileList;
	}
	@Override
	public int send(SmsSendType sendType, Map<String, String> parame) {
		return send(sendType, "", parame);
	}
}
