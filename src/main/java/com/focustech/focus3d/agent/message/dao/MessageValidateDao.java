package com.focustech.focus3d.agent.message.dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.focustech.common.utils.DateUtils;
import com.focustech.focus3d.agent.dao.CommonDao;
import com.focustech.focus3d.agent.model.MessageValidate;
import com.focustech.focus3d.agent.model.ibator.BizAgentMessageValidateCriteria;

/**
 *
 * *
 * @author lihaijun
 *
 */
@Repository
public class MessageValidateDao extends CommonDao{
	/**
	 *
	 * *
	 * @param mobilePhone
	 */
	public void deleteByMobilePhone(String mobilePhone) {
		BizAgentMessageValidateCriteria criteria = new BizAgentMessageValidateCriteria();
		criteria.createCriteria().andMobilePhoneEqualTo(mobilePhone);
		deleteByCriteria(criteria);
	}
	/**
	 *
	 * *
	 * @param mobilePhone
	 * @param verifyCode
	 * @return
	 */
	public MessageValidate selectByMobilePhone(String mobilePhone, String verifyCode) {
		BizAgentMessageValidateCriteria criteria = new BizAgentMessageValidateCriteria();
		criteria.createCriteria().andMobilePhoneEqualTo(mobilePhone).andVerifyCodeEqualTo(verifyCode).andStatusEqualTo(1);
		return selectFirstByExample(criteria, MessageValidate.class);
	}
	/**
	 *
	 * *
	 * @param mobilePhone
	 * @param verifyCode
	 * @param status
	 * @return
	 */
	public MessageValidate selectByMobilePhone(String mobilePhone, int status) {
		BizAgentMessageValidateCriteria criteria = new BizAgentMessageValidateCriteria();
		criteria.createCriteria().andMobilePhoneEqualTo(mobilePhone).andStatusEqualTo(status);
		return selectFirstByExample(criteria, MessageValidate.class);
	}
	/**
	 *
	 * *
	 * @param mobilePhone
	 * @return
	 */
	public List<MessageValidate> getListByPerDay(String mobilePhone) {
		BizAgentMessageValidateCriteria criteria = new BizAgentMessageValidateCriteria();
		String curDate = DateUtils.getCurDate(DateUtils.DEFAULT_FORMATE);
		DateFormat df = new SimpleDateFormat(DateUtils.DEFAULT_FORMATE);
		Date date = null;
		try {
			date = df.parse(curDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		criteria.createCriteria().andMobilePhoneEqualTo(mobilePhone).andCreateDateEqualTo(date);
		return selectByCriteria(criteria, MessageValidate.class);
	}

}
