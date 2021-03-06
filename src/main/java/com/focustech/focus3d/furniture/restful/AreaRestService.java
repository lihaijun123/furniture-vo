package com.focustech.focus3d.furniture.restful;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.focustech.focus3d.furniture.restful.constant.ContentType;
import com.focustech.focus3d.furniture.restful.constant.ProvinceCityConst;

/**
 * 
 * *
 * @author lihaijun
 *
 */
//@Service
@Path("/rest/area")
@Produces(ContentType.APPLICATION_JSON_UTF_8)
public class AreaRestService {
	/**
	 * 
	 * *
	 * @return
	 */
	@GET
	@Path("list")
	public String list() {
		JSONObject data = new JSONObject();
		String[] province = ProvinceCityConst.province;
		for(int i = 0; i < province.length; i ++){
			String[] cityAry = ProvinceCityConst.province_city.get("city_" + i);
			JSONArray cityJary = new JSONArray();
			for (String city : cityAry) {
				JSONObject cityJo = new JSONObject();
				cityJo.put("city", city);
				cityJary.add(cityJo);
			}
			data.put(province[i], cityJary);
		}
		return data.toString();
	}

}
