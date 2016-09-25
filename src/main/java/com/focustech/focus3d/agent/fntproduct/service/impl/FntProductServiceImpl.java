package com.focustech.focus3d.agent.fntproduct.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.focustech.cief.filemanage.common.utils.FileManageUtil;
import com.focustech.common.utils.TCUtil;
import com.focustech.focus3d.agent.dao.CommonDao;
import com.focustech.focus3d.agent.fntproduct.controller.FntProductSearch;
import com.focustech.focus3d.agent.fntproduct.dao.FntProductDao;
import com.focustech.focus3d.agent.fntproduct.service.FntProductService;
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
	public List<FntProductModel> search(FntProductSearch productSearch) {
		List<FntProductModel> list = productDao.search(productSearch);
		for (FntProductModel fntHouseModel : list) {
			Long picFileSn = fntHouseModel.getPicFileSn();
			if(picFileSn != null){
				fntHouseModel.setPicFileUrl(TCUtil.sv(FileManageUtil.getFileURL(picFileSn)));
			}
			Long modelFileSn = fntHouseModel.getModelFileSn();
			if(modelFileSn != null){
				fntHouseModel.setModelFileUrl(TCUtil.sv(FileManageUtil.getFileURL(modelFileSn)));
			}
		}
		return list;
	}
}
