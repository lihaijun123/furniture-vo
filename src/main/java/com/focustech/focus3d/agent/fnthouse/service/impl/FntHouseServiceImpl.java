package com.focustech.focus3d.agent.fnthouse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.focustech.focus3d.agent.dao.CommonDao;
import com.focustech.focus3d.agent.fnthouse.dao.FntHouseDao;
import com.focustech.focus3d.agent.fnthouse.service.FntHouseService;
import com.focustech.focus3d.agent.model.FntHouseModel;
import com.focustech.focus3d.agent.service.impl.CommonServiceTemplate;
/**
 * 
 * *
 * @author lihaijun
 *
 */
@Service
public class FntHouseServiceImpl extends CommonServiceTemplate<FntHouseModel> implements FntHouseService<FntHouseModel> {
	@Autowired
	private FntHouseDao fntHouseDao;
	@Override
	public CommonDao getDao() {
		return fntHouseDao;
	}

}
