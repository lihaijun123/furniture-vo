package com.focustech.focus3d.agent.fnthouseupload.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.focustech.focus3d.agent.dao.CommonDao;
import com.focustech.focus3d.agent.fnthouseupload.dao.FntHouseUploadDao;
import com.focustech.focus3d.agent.fnthouseupload.service.FntHouseUploadService;
import com.focustech.focus3d.agent.model.FntHouseUploadModel;
import com.focustech.focus3d.agent.service.impl.CommonServiceTemplate;
/**
 * *
 * @author lihaijun
 *
 */
@Service
public class FntHouseUploadServiceImpl extends CommonServiceTemplate<FntHouseUploadModel> implements FntHouseUploadService<FntHouseUploadModel> {
	
	@Autowired
	private FntHouseUploadDao fntHouseUploadDao;
	@Override
	public CommonDao getDao() {
		return fntHouseUploadDao;
	}

}
