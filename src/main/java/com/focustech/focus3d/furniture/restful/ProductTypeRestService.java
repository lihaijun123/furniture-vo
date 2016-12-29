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
import org.apache.http.message.BasicNameValuePair;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import com.focustech.common.utils.StringUtils;
import com.focustech.common.utils.TCUtil;
import com.focustech.focus3d.furniture.restful.common.RestMethodDesc;
import com.focustech.focus3d.furniture.restful.constant.ContentType;
import com.focustech.focus3d.furniture.rpc.FntRpc;

/***
 * 
 * 
 * @author lihaijun
 *
 */
@RestMethodDesc("家具产品类型服务")
@Service
@Path("/rest/product-type")
@Produces(ContentType.APPLICATION_JSON_UTF_8)
public class ProductTypeRestService {
	
	private FntRpc fntRpc = new FntRpc();
	@RestMethodDesc("家具产品类型列表")
	@POST
	@Path("list")
	public String searchByPost(@FormParam("productId") String productId) {
		List<NameValuePair> qparams = new ArrayList<NameValuePair>();
		qparams.add(new BasicNameValuePair("id", productId));
		String result = fntRpc.httpRequest("/service/product/goodspecs.htm", qparams, HttpMethod.POST);
		JSONObject jo = new JSONObject();
		if(StringUtils.isNotEmpty(result)){
			jo = JSONObject.fromObject(result);
			if(!jo.isEmpty()){
				Object object = jo.get("颜色");
				if(object != null){
					jo.remove("颜色");
					jo.put("color", object);
				}
				Object object2 = jo.get("尺寸");
				if(object2 != null){
					jo.remove("尺寸");
					jo.put("size", object2);
				}
			}
		}
		return jo.toString();
	}
}
