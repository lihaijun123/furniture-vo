package com.focustech.focus3d.furniture.restful;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.NameValuePair;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import com.focustech.common.utils.StringUtils;
import com.focustech.focus3d.furniture.restful.constant.ContentType;
import com.focustech.focus3d.furniture.rpc.FntRpc;

/***
 * 
 * 
 * @author lihaijun
 *
 */
@Service
@Path("/rest/product-type")
@Produces(ContentType.APPLICATION_JSON_UTF_8)
public class ProductTypeRestService {
	
	private FntRpc fntRpc = new FntRpc();
	
	@POST
	@Path("list")
	public String searchByPost(@FormParam("productId") String keyWord) {
		List<NameValuePair> qparams = new ArrayList<NameValuePair>();
		String result = fntRpc.httpRequest("/service/product/goodspecs.htm", qparams, HttpMethod.POST);
		if(StringUtils.isNotEmpty(result)){
			if(JSONObject.fromObject(result).isEmpty()){
				JSONObject jo = new JSONObject();
				JSONArray colorJary = new JSONArray();
				JSONObject color1Jo = new JSONObject();
				color1Jo.put("gsp_id", "1");
				color1Jo.put("gsp_value", "浅蓝");
				colorJary.add(color1Jo);
				JSONObject color2Jo = new JSONObject();
				color2Jo.put("gsp_id", "2");
				color2Jo.put("gsp_value", "米黄");
				colorJary.add(color2Jo);
				jo.put("color", colorJary);
				
				JSONArray sizeJary = new JSONArray();
				JSONObject size1Jo = new JSONObject();
				size1Jo.put("gsp_id", "3");
				size1Jo.put("gsp_value", "单人位：760*1010*720mm");
				sizeJary.add(size1Jo);
				JSONObject size2Jo = new JSONObject();
				size2Jo.put("gsp_id", "4");
				size2Jo.put("gsp_value", "三人位：1900*1010*720mm");
				sizeJary.add(size2Jo);
				jo.put("size", sizeJary);
				result = jo.toString();
			}
		} else {
			result = (new JSONObject()).toString();
		}
		return result;
	}
}
