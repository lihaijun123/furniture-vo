package com.focustech.focus3d.agent.fntcase.dao;

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
import com.focustech.focus3d.agent.model.FntCaseModel;
import com.focustech.focus3d.furniture.restful.search.FntCaseSearch;
/**
 * 
 * *
 * @author lihaijun
 *
 */
@Repository
public class FntCaseDao extends CommonDao {
	private static final Logger log = LoggerFactory.getLogger(FntCaseDao.class);
	
	/**
	 * *
	 * @param caseSearch
	 * @return
	 */
	public int searchTotal(FntCaseSearch caseSearch) {
		Map<String, Object> map = createCondition(caseSearch);
		try {
			return TCUtil.iv(getSqlMapClient().queryForObject("c_fnt_case.getFntCaseListCount", map));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	/**
	 * *
	 * @param caseSearch
	 * @return
	 */
	public List<FntCaseModel> search(FntCaseSearch caseSearch) {
		Map<String, Object> map = createCondition(caseSearch);
		List<FntCaseModel> list = new ArrayList<FntCaseModel>();
		try {
			list = getSqlMapClient().queryForList("c_fnt_case.getFntCaseList", map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(ListUtils.isNotEmpty(list)){
			int searchTotal = searchTotal(caseSearch);
			caseSearch.setRecords(list);
			caseSearch.setRecordTotal(searchTotal);
		}
		return list;
	}
	/**
	 * 
	 * *
	 * @param caseSearch
	 * @return
	 */
	private Map<String, Object> createCondition(FntCaseSearch caseSearch) {
		StringBuffer condition = new StringBuffer();
		String userId = caseSearch.getUserId();
		String buildingName = caseSearch.getBuildingName();
		String houseName = caseSearch.getHouseName();
		int pageNow = caseSearch.getPageNow();
		int pageSize = caseSearch.getPageSize();
		if(StringUtils.isNotEmpty(userId)){
			condition.append(" and c_.USER_ID = " + userId);
		}
		if(StringUtils.isNotEmpty(buildingName) || StringUtils.isNotEmpty(houseName)){
			condition.append(" and exists(SELECT h_.SN FROM fnt_house h_ WHERE c_.HOUSE_ID = h_.SN  ");
			if(StringUtils.isNotEmpty(buildingName)){
				condition.append(" and h_.BUILDING_NAME LIKE '%" + buildingName + "%'");
			}
			if(StringUtils.isNotEmpty(houseName)){
				condition.append(" and h_.NAME LIKE '%" + houseName + "%'");
			}
			condition.append(")");
		}
		if(pageNow > 0){
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
	
}
