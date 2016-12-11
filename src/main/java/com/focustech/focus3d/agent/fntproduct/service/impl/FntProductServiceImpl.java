package com.focustech.focus3d.agent.fntproduct.service.impl;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.focustech.cief.filemanage.common.utils.FileManageUtil;
import com.focustech.common.utils.EncryptUtil;
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
		for (FntProductModel productModel : list) {
			setFileInfo(productModel);
		}
		return list;
	}
	
	
	@Override
	public JSONObject serialize(FntProductModel fntProductModel) {
		setFileInfo(fntProductModel);
		JSONObject jo = new JSONObject();
		jo.put("name", fntProductModel.getName());
		jo.put("price", fntProductModel.getPrice());
		jo.put("url", fntProductModel.getModelFileUrl());
		jo.put("version", fntProductModel.getModelFileVersion());
		jo.put("picUrl", fntProductModel.getPicFileUrl());
		String categoryName = fntProductModel.getCategoryName();
		String category = "";
		if(categoryName.contains("地")){
			category = "floor";
		} else if(categoryName.contains("墙")){
			category = "wall";
		} else {
			category = "furniture";
		}
		jo.put("type", category);
		jo.put("id", EncryptUtil.encode(fntProductModel.getSn()));
		return jo;
	}
	/**
	 * 
	 * *
	 * @param fntProductModel
	 */
	private void setFileInfo(FntProductModel fntProductModel) {
		Long picFileSn = fntProductModel.getPicFileSn();
		if(picFileSn != null){
			fntProductModel.setPicFileUrl(TCUtil.sv(FileManageUtil.getFileURL(picFileSn)));
		}
		Long modelFileSn = fntProductModel.getModelFileSn();
		if(modelFileSn != null){
			fntProductModel.setModelFileUrl(TCUtil.sv(FileManageUtil.getFileURL(modelFileSn)));
		}
	}
	@Override
	public JSONObject serialize(long productId) {
		FntProductModel productModel = selectBySn(productId, FntProductModel.class);
		return serialize(productModel);
	}
}
