package com.focustech.focus3d.agent.fnthouse.dao;

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
import com.focustech.focus3d.agent.model.FntHouseModel;
import com.focustech.focus3d.furniture.restful.search.FntHouseSearch;
/**
 * 
 * *
 * @author lihaijun
 *
 */
@Repository
public class FntHouseDao extends CommonDao {
	private static final Logger log = LoggerFactory.getLogger(FntHouseDao.class);
	/**
	 * 
	 * *
	 * @param houseSearch
	 * @return
	 */
	public List<FntHouseModel> search(FntHouseSearch houseSearch) {
		Map<String, Object> map = createCondition(houseSearch);
		List<FntHouseModel> list = new ArrayList<FntHouseModel>();
		try {
			list = getSqlMapClient().queryForList("c_fnt_house.getFntHouseList", map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(ListUtils.isNotEmpty(list)){
			int searchTotal = searchTotal(houseSearch);
			houseSearch.setRecords(list);
			houseSearch.setRecordTotal(searchTotal);
		}
		return list;
	}
	/**
	 * *
	 * @param houseSearch
	 * @return
	 */
	private Map<String, Object> createCondition(FntHouseSearch houseSearch) {
		StringBuffer condition = new StringBuffer();
		String province = houseSearch.getProvince();
		String city = houseSearch.getCity();
		String keyWord = houseSearch.getKeyWord();
		List<String> areaRange = houseSearch.getAreaRange();
		List<String> roomType = houseSearch.getRoomType();
		List<String> type = houseSearch.getType();
		int pageNow = houseSearch.getPageNow();
		int pageSize = houseSearch.getPageSize();
		if(StringUtils.isNotEmpty(province) && !"请选择".equals(province)){
			if("全国".equals(province)){
				//condition.append(" and province !='").append("请选择").append("'");
			} else {
				condition.append(" and province='").append(province).append("'");
			}
			if(StringUtils.isNotEmpty(city) && !"请选择".equals(city)){
				condition.append(" and city='").append(city).append("'");
			}
		}
		if(StringUtils.isNotEmpty(keyWord)){
			condition.append(" and name like '%").append(keyWord).append("%'");
		}
		if(ListUtils.isNotEmpty(areaRange)){
			condition.append(" and (");
			for(int i = 0; i < areaRange.size(); i ++){
				String[] area = areaRange.get(i).split("-");
				if(area.length == 2){
					String areaS = area[0];
					String areaE = area[1];
					condition.append("( area >").append(areaS).append(" and area <").append(areaE).append(")");
					if(i < areaRange.size() - 1){
						condition.append(" or ");
					}
				}
			}
			condition.append(" )");
		}
		if(ListUtils.isNotEmpty(roomType)){
			condition.append(" and room_num in  (");
			for(int i = 0; i < roomType.size(); i ++){
				condition.append(roomType.get(i));
				if(i < roomType.size() - 1){
					condition.append(",");
				}
			}
			condition.append(" )");
		}
		if(ListUtils.isNotEmpty(type)){
			condition.append(" and type in  (");
			for(int i = 0; i < type.size(); i ++){
				condition.append(type.get(i));
				if(i < type.size() - 1){
					condition.append(",");
				}
			}
			condition.append(" )");
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
	/**
	 * 
	 * *
	 * @param houseSearch
	 * @return
	 */
	public int searchTotal(FntHouseSearch houseSearch) {
		Map<String, Object> map = createCondition(houseSearch);
		try {
			return TCUtil.iv(getSqlMapClient().queryForObject("c_fnt_house.getFntHouseListCount", map));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	

}
