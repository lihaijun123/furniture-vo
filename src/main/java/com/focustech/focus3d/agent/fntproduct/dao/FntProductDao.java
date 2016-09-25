package com.focustech.focus3d.agent.fntproduct.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.focustech.common.utils.StringUtils;
import com.focustech.focus3d.agent.dao.CommonDao;
import com.focustech.focus3d.agent.fntproduct.controller.FntProductSearch;
import com.focustech.focus3d.agent.model.FntHouseModel;
/**
 * 
 * *
 * @author lihaijun
 *
 */
@Repository
public class FntProductDao extends CommonDao {
	private static final Logger log = LoggerFactory.getLogger(FntProductDao.class);
	/**
	 * 
	 * *
	 * @param houseSearch
	 * @return
	 */
	public List<FntHouseModel> search(FntProductSearch productSearch) {
		StringBuffer condition = new StringBuffer();
		String categoryCode = productSearch.getCategoryCode();
		if(StringUtils.isNotEmpty(categoryCode)){
			condition.append("category_code='").append(categoryCode).append("'");
		}
		log.debug("condition:" + condition);
		Map<String, Object> map = new HashMap<String, Object>();
		List<FntHouseModel> list = new ArrayList<FntHouseModel>();
		try {
			map.put("condition", condition.toString());
			list = getSqlMapClient().queryForList("c_fnt_product.getFntProductList", map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
