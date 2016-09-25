package com.focustech.focus3d.agent.fntprodcate.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.focustech.focus3d.agent.dao.CommonDao;
import com.focustech.focus3d.agent.fntprodcate.dao.FntProductCategoryDao;
import com.focustech.focus3d.agent.fntprodcate.service.FntProductCateService;
import com.focustech.focus3d.agent.model.FntProductCategory;
import com.focustech.focus3d.agent.model.ibator.CoreCategoryCriteria;
import com.focustech.focus3d.agent.service.impl.CommonServiceTemplate;

/**
 * 
 * *
 * @author lihaijun
 *
 */
@Service
public class FntProductCateServiceImpl extends CommonServiceTemplate<FntProductCategory> implements FntProductCateService<FntProductCategory>{
	@Autowired
	private FntProductCategoryDao categoryDao;
	@Override
	public CommonDao getDao() {
		return categoryDao;
	}
	@Override
	public List<FntProductCategory> list(String status) {
		CoreCategoryCriteria categoryCriteria = new CoreCategoryCriteria();
		categoryCriteria.createCriteria().andCatStatusEqualTo(status);
		return selectByCriteria(categoryCriteria, FntProductCategory.class);
	}

}
