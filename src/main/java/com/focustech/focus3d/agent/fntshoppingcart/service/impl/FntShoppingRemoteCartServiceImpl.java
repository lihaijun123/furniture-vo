package com.focustech.focus3d.agent.fntshoppingcart.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.focustech.focus3d.agent.dao.CommonDao;
import com.focustech.focus3d.agent.fntshoppingcart.service.FntShoppingCartService;
import com.focustech.focus3d.agent.model.FntShoppingCartModel;
import com.focustech.focus3d.agent.service.impl.CommonServiceTemplate;
@Service
public class FntShoppingRemoteCartServiceImpl extends CommonServiceTemplate<FntShoppingCartModel> implements FntShoppingCartService<FntShoppingCartModel> {

	@Override
	public List<FntShoppingCartModel> listByUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FntShoppingCartModel> listAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteBySn(long sn) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CommonDao getDao() {
		// TODO Auto-generated method stub
		return null;
	}

}
