package com.focustech.focus3d.agent.fnthouse.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.focustech.cief.filemanage.common.utils.FileManageUtil;
import com.focustech.common.utils.TCUtil;
import com.focustech.focus3d.agent.dao.CommonDao;
import com.focustech.focus3d.agent.fnthouse.controller.FntHouseSearch;
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
	@Override
	public List<FntHouseModel> search(FntHouseSearch houseSearch) {
		List<FntHouseModel> list = fntHouseDao.search(houseSearch);
		for (FntHouseModel fntHouseModel : list) {
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
