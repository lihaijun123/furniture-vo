package com.focustech.focus3d.agent.fntprodcate.dao;

import org.springframework.stereotype.Repository;

import com.focustech.focus3d.agent.dao.CommonDao;
import com.focustech.focus3d.agent.model.FntProductCategory;
import com.focustech.focus3d.agent.model.ibator.CoreCategoryCriteria;
/**
 * 
 * *
 * @author lihaijun
 *
 */
@Repository
public class FntProductCategoryDao extends CommonDao {
	/**
	 * *
	 * @param name
	 * @return
	 */
	public FntProductCategory selectByName(String name){
		CoreCategoryCriteria categoryCriteria = new CoreCategoryCriteria();
		categoryCriteria.createCriteria().andCatNameCnEqualTo(name);
		return selectFirstByExample(categoryCriteria, FntProductCategory.class);
	}

}
