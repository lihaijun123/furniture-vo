package com.focustech.focus3d.furniture.restful;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.focustech.common.utils.StringUtils;
import com.focustech.common.utils.TCUtil;
import com.focustech.focus3d.agent.fnthouse.service.FntHouseService;
import com.focustech.focus3d.agent.model.FntHouseModel;
import com.focustech.focus3d.furniture.restful.constant.ContentType;
import com.focustech.focus3d.furniture.restful.search.FntHouseSearch;

/**
 * 
 * *
 * @author lihaijun
 *
 */
@Service
@Path("/rest/house")
@Produces(ContentType.APPLICATION_JSON_UTF_8)
public class HouseRestService {
	@Autowired
	private FntHouseService<FntHouseModel> fntHouseService;
	/**
	 * *
	 * @param province
	 * @param city
	 * @param keyWord
	 * @param type
	 * @param areaRange
	 * @param roomType
	 * @return
	 */
	@GET
	@Path("search")
	public String searchByGet(
			@QueryParam("province") String province,
			@QueryParam("city") String city,
			@QueryParam("keyWord") String keyWord,
			@QueryParam("type") String type,
			@QueryParam("areaRange") String areaRange,
			@QueryParam("roomType") String roomType,
			@QueryParam("pageNow") String pageNow,
			@QueryParam("pageSize") String pageSize
			) {
		return searchData(province, city, keyWord, type, areaRange, roomType, pageNow, pageSize);
	}
	/**
	 * *
	 * @param province
	 * @param city
	 * @param keyWord
	 * @param type
	 * @param areaRange
	 * @param roomType
	 * @return
	 */
	@POST
	@Path("search")
	public String searchByPost(
			@FormParam("province") String province,
			@FormParam("city") String city,
			@FormParam("keyWord") String keyWord,
			@FormParam("type") String type,
			@FormParam("areaRange") String areaRange,
			@FormParam("roomType") String roomType,
			@FormParam("pageNow") String pageNow,
			@FormParam("pageSize") String pageSize
			) {
		return searchData(province, city, keyWord, type, areaRange, roomType, pageNow, pageSize);
	}
	/**
	 * *
	 * @param province
	 * @param city
	 * @param keyWord
	 * @param type
	 * @param areaRange
	 * @param roomType
	 * @return
	 */
	private String searchData(String province, String city, String keyWord,
			String type, String areaRange, String roomType, String pageNow, String pageSize) {
		FntHouseSearch houseSearch = new FntHouseSearch();
		if(StringUtils.isNotEmpty(province)){
			houseSearch.setProvince(province);
		}
		if(StringUtils.isNotEmpty(city)){
			houseSearch.setCity(city);
		}
		if(StringUtils.isNotEmpty(keyWord)){
			houseSearch.setKeyWord(keyWord);
		}
		if(StringUtils.isNotEmpty(type)){
			String[] split = type.split(",");
			for (String string : split) {
				houseSearch.getType().add(string);
			}
		}
		if(StringUtils.isNotEmpty(areaRange)){
			String[] split = areaRange.split(",");
			for (String string : split) {
				houseSearch.getAreaRange().add(string);
			}
		}
		if(StringUtils.isNotEmpty(roomType)){
			String[] split = roomType.split(",");
			for (String string : split) {
				houseSearch.getRoomType().add(string);
			}
		}
		if(StringUtils.isNotEmpty(pageNow) && TCUtil.iv(pageNow) > 0){
			houseSearch.setPageNow(TCUtil.iv(pageNow));
			if(StringUtils.isNotEmpty(pageSize) && TCUtil.iv(pageSize) > 0){
				houseSearch.setPageSize(TCUtil.iv(pageSize));
			}
		}
		JSONArray jary = new JSONArray();
		List<FntHouseModel> list = fntHouseService.search(houseSearch);
		for (FntHouseModel fntHouseModel : list) {
			jary.add(fntHouseService.serialize(fntHouseModel));
		}
		JSONObject rvJo = new JSONObject();
		houseSearch.addPageInfo(rvJo);
		rvJo.put("list", jary);
		return rvJo.toString();
		
	}
}
