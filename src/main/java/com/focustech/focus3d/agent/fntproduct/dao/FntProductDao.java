package com.focustech.focus3d.agent.fntproduct.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.focustech.common.utils.ListUtils;
import com.focustech.common.utils.StringUtils;
import com.focustech.common.utils.TCUtil;
import com.focustech.focus3d.agent.dao.CommonDao;
import com.focustech.focus3d.agent.fntproduct.controller.FntProductSearch;
import com.focustech.focus3d.agent.model.FntProductModel;
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
	public List<FntProductModel> search(FntProductSearch productSearch) {
		Map<String, Object> map = createCondition(productSearch, 1);
		List<FntProductModel> list = new ArrayList<FntProductModel>();
		try {
			list = getSqlMapClient().queryForList("c_fnt_product.getFntProductList", map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(ListUtils.isNotEmpty(list)){
			int searchTotal = searchTotal(productSearch);
			productSearch.setRecords(list);
			productSearch.setRecordTotal(searchTotal);
		}
		return list;
	}
	
	private Map<String, Object> createCondition(FntProductSearch productSearch, int searchType){
		StringBuffer condition = new StringBuffer();
		String categoryCode = productSearch.getCategoryCode();
		String priceRange = productSearch.getPriceRange();
		int pageNow = productSearch.getPageNow();
		int pageSize = productSearch.getPageSize();
		if(StringUtils.isNotEmpty(categoryCode)){
			condition.append(" and category_name='").append(categoryCode).append("'");
		}
		String keyWord = productSearch.getKeyWord();
		if(StringUtils.isNotEmpty(keyWord)){
			condition.append(" and name like '%").append(keyWord).append("%'");
		}
		if(StringUtils.isNotEmpty(priceRange)){
			String[] prices = priceRange.split("-");
			String priceS = "0";
			String priceE = "0";
			if(prices.length == 2){
				priceS = prices[0];
				priceE = prices[1];
			} else if(prices.length == 1){
				priceE = prices[0];
			}
			condition.append(" and price >=").append(priceS).append(" and price <= ").append(priceE);
		}
		if(pageNow > 0 && searchType == 1){
			condition.append(" limit ")
			.append((TCUtil.iv(pageNow) - 1) * TCUtil.iv(pageSize))
			.append(", ")
			.append(TCUtil.iv(pageSize));
		}
		log.debug("condition:" + condition);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("condition", condition.toString());
		return map;
	}
	
	/**
	 * 
	 * *
	 * @param houseSearch
	 * @return
	 */
	public int searchTotal(FntProductSearch productSearch) {
		Map<String, Object> map = createCondition(productSearch, 2);
		try {
			return TCUtil.iv(getSqlMapClient().queryForObject("c_fnt_product.getFntProductListCount", map));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
