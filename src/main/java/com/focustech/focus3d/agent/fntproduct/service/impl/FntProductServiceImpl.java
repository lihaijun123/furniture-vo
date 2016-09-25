package com.focustech.focus3d.agent.fntproduct.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.focustech.focus3d.agent.dao.CommonDao;
import com.focustech.focus3d.agent.fntproduct.controller.FntProductSearch;
import com.focustech.focus3d.agent.fntproduct.dao.FntProductDao;
import com.focustech.focus3d.agent.fntproduct.service.FntProductService;
import com.focustech.focus3d.agent.model.FntHouseModel;
import com.focustech.focus3d.agent.model.FntProductModel;
import com.focustech.focus3d.agent.service.impl.CommonServiceTemplate;
/**
 * 
 * *
 * @author lihaijun
 *
 */
@Service
public class FntProductServiceImpl extends CommonServiceTemplate<FntProductModel> implements FntProductService<FntProductModel> {
	@Autowired
	private FntProductDao productDao;
	@Override
	public CommonDao getDao() {
		return productDao;
	}
	@Override
	public List<FntHouseModel> search(FntProductSearch productSearch) {
		return productDao.search(productSearch);
	}
}
