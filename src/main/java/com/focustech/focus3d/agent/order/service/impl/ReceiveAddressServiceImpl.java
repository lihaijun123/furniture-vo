package com.focustech.focus3d.agent.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.focustech.focus3d.agent.dao.CommonDao;
import com.focustech.focus3d.agent.model.ReceiveAddress;
import com.focustech.focus3d.agent.order.dao.ReceiveAddressDao;
import com.focustech.focus3d.agent.order.service.ReceiveAddressService;
import com.focustech.focus3d.agent.service.impl.CommonServiceTemplate;
/**
 *
 * *
 * @author lihaijun
 *
 */
@Service
@Transactional
public class ReceiveAddressServiceImpl extends CommonServiceTemplate<ReceiveAddress> implements ReceiveAddressService<ReceiveAddress> {
	@Autowired
	private ReceiveAddressDao receiveAddressDao;

	@Override
	public CommonDao getDao() {
		return receiveAddressDao;
	}

	@Override
	public ReceiveAddress getByUserId(long userId) {
		return receiveAddressDao.getByUserId(userId);
	}

}
