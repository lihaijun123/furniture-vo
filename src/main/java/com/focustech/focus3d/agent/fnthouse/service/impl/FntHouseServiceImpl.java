package com.focustech.focus3d.agent.fnthouse.service.impl;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.focustech.cief.filemanage.common.utils.FileManageUtil;
import com.focustech.common.utils.EncryptUtil;
import com.focustech.common.utils.TCUtil;
import com.focustech.focus3d.agent.dao.CommonDao;
import com.focustech.focus3d.agent.fnthouse.dao.FntHouseDao;
import com.focustech.focus3d.agent.fnthouse.service.FntHouseService;
import com.focustech.focus3d.agent.model.FntHouseModel;
import com.focustech.focus3d.agent.service.impl.CommonServiceTemplate;
import com.focustech.focus3d.furniture.restful.search.FntHouseSearch;
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
		jo.put("ApartmentId", fntHouseModel.getName());
		jo.put("ApartmentModelUrl", fntHouseModel.getModelFileUrl());
		jo.put("Area", fntHouseModel.getArea());
		jo.put("City", fntHouseModel.getCity());
		jo.put("Country", fntHouseModel.getStreet());
		jo.put("Floor", fntHouseModel.getName());
		jo.put("Callery", "9");
		jo.put("Id", EncryptUtil.encode(fntHouseModel.getSn()));
		jo.put("IsRenovation", false);
		//jo.put("livingRoomNum", fntHouseModel.getLivingRoomNum());
		//jo.put("url", fntHouseModel.getModelFileUrl());
		jo.put("version", fntHouseModel.getModelFileVersion());
		//jo.put("id", EncryptUtil.encode(fntHouseModel.getSn()));
		jo.put("picId", fntHouseModel.getPicFileUrl());
		jo.put("picUrl", fntHouseModel.getPicFileUrl());
		jo.put("Province", fntHouseModel.getProvince());
		jo.put("Rooms", fntHouseModel.getRoomNum());
		jo.put("Saloons", 2);
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
	@Override
	public JSONObject serialize(long houseId) {
		FntHouseModel houseModel = selectBySn(houseId, FntHouseModel.class);
		return serialize(houseModel);
	}

}
