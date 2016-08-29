package com.focustech.focus3d.agent.message.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.focustech.focus3d.agent.dao.CommonDao;
import com.focustech.focus3d.agent.message.dao.MessageValidateDao;
import com.focustech.focus3d.agent.message.service.MessageValidateService;
import com.focustech.focus3d.agent.model.MessageValidate;
import com.focustech.focus3d.agent.service.impl.CommonServiceTemplate;
/**
 *
 * *
 * @author lihaijun
 *
 */
@Service
@Transactional
public class MessageValidateServiceImpl extends CommonServiceTemplate<MessageValidate> implements MessageValidateService<MessageValidate> {
	@Autowired
	private MessageValidateDao messageValidateDao;

	@Override
	public CommonDao getDao() {
		return messageValidateDao;
	}

	@Override
	public void deleteByMobilePhone(String mobilePhone) {
		messageValidateDao.deleteByMobilePhone(mobilePhone);
	}

	@Override
	public void save(String mobilePhone, String verifyCode, String ip) {
		//先删除之前记录
		MessageValidate existObj = messageValidateDao.selectByMobilePhone(mobilePhone, 1);
		if(existObj != null){
			setStatus(existObj, 0);
		}
		//保存一条新记录
		MessageValidate mobileValidate = new MessageValidate();
		mobileValidate.setMobilePhone(mobilePhone);
		mobileValidate.setVerifyCode(verifyCode);
		mobileValidate.setStatus(1);
		mobileValidate.setIpAddr(ip);
		mobileValidate.setIpPosition("");
		mobileValidate.setCreateDate(new Date());
		messageValidateDao.insert(mobileValidate);
	}

	@Override
	public MessageValidate selectByMobilePhone(String mobilePhone, String verifyCode) {
		return messageValidateDao.selectByMobilePhone(mobilePhone, verifyCode);
	}

	@Override
	public List<MessageValidate> getListByPerDay(String mobilePhone) {
		return messageValidateDao.getListByPerDay(mobilePhone);
	}

	@Override
	public void setStatus(MessageValidate t, int status) {
		t.setStatus(status);
		messageValidateDao.updateByKeySelective(t);
	}



}
