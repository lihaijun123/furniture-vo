package com.focustech.focus3d.agent.fnthouse.service.impl;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.focustech.cief.filemanage.common.utils.FileManageUtil;
import com.focustech.common.utils.EncryptUtil;
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
			setFileInfo(fntHouseModel);
		}
		return list;
	}
	@Override
	public int searchTotal(FntHouseSearch houseSearch) {
		return fntHouseDao.searchTotal(houseSearch);
	}
	
	@Override
	public JSONObject serialize(FntHouseModel fntHouseModel) {
		setFileInfo(fntHouseModel);
		JSONObject jo = new JSONObject();
		jo.put("name", fntHouseModel.getName());
		jo.put("area", fntHouseModel.getArea());
		jo.put("roomNum", fntHouseModel.getRoomNum());
		jo.put("livingRoomNum", fntHouseModel.getLivingRoomNum());
		jo.put("url", fntHouseModel.getModelFileUrl());
		jo.put("version", fntHouseModel.getModelFileVersion());
		jo.put("id", EncryptUtil.encode(fntHouseModel.getSn()));
		jo.put("picUrl", fntHouseModel.getPicFileUrl());
		return jo;
	}
	/**
	 * 
	 * *
	 * @param fntHouseModel
	 */
	private void setFileInfo(FntHouseModel fntHouseModel) {
		Long picFileSn = fntHouseModel.getPicFileSn();
		if(picFileSn != null){
			fntHouseModel.setPicFileUrl(TCUtil.sv(FileManageUtil.getFileURL(picFileSn)));
		}
		Long modelFileSn = fntHouseModel.getModelFileSn();
		if(modelFileSn != null){
			fntHouseModel.setModelFileUrl(TCUtil.sv(FileManageUtil.getFileURL(modelFileSn)));
		}
	}

}
