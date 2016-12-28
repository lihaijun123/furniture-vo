package com.focustech.focus3d.furniture.restful;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import net.sf.json.JSONObject;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import com.focustech.common.utils.StringUtils;
import com.focustech.focus3d.furniture.rpc.FntRpc;
/**
 * 
 * *
 * @author lihaijun
 *
 */
@Service
@Path("/rest/shoppingcart")
public class ShoppingCartRestService {
	private FntRpc fntRpc = new FntRpc();
	/*@Autowired
	private FntShoppingCartService<FntShoppingCartModel> shoppingCartService;*/
	
	/**
	 * *
	 * @param userId
	 * @param furnitureId
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("add")
	public String add(
			@FormParam("productId") String productId,
			@FormParam("count") String count,
			@FormParam("price") String price,
			@FormParam("gsp") String gsp
			) throws Exception{
		int status = 0;
		String message = "";
		count = "1";
		if(StringUtils.isNotEmpty(productId)){
			List<NameValuePair> qparams = new ArrayList<NameValuePair>();
			qparams.add(new BasicNameValuePair("id", productId));
			qparams.add(new BasicNameValuePair("count ", count));
			qparams.add(new BasicNameValuePair("price ", price));
			qparams.add(new BasicNameValuePair("gsp ", gsp));
			//String result = fntRpc.httpRequest("/service/add_goods_cart.htm?id=272&count=1&price=1100&gsp=", qparams, HttpMethod.POST);
			String result = fntRpc.httpRequest("/add_goods_cart.htm", qparams, HttpMethod.POST);
			JSONObject jo = JSONObject.fromObject(result);
			if(jo.isEmpty()){
				status = 2;
				message = "添加失败";
			} else {
				message = "添加成功";
			}
		} else {
			status = 1;
			message = "参数不能为空";
		}
		JSONObject jo = new JSONObject();
		jo.put("status", status);
		jo.put("message", message);
		return jo.toString();
	}
	/*@POST
	@Path("add")
	public String add(@FormParam("userId") String userId, @FormParam("furnitureId") String furnitureId) throws Exception{
		int status = 0;
		String message = "";
		if(StringUtils.isNotEmpty(userId) && StringUtils.isNotEmpty(furnitureId)){
			Long decodeUserId = EncryptUtil.decode(userId);
			Long decodeFurnitureId = EncryptUtil.decode(furnitureId);
			if(decodeUserId != null && decodeUserId > 0 && decodeFurnitureId != null && decodeFurnitureId > 0){
				FntShoppingCartModel shoppingCartModel = new FntShoppingCartModel();
				shoppingCartModel.setUserId(decodeUserId);
				shoppingCartModel.setFurnitureId(decodeFurnitureId);
				shoppingCartModel.setNum(1);
				shoppingCartService.insert(shoppingCartModel);
				message = "成功加入购物车";
			} else {
				status = 1;
				message = "userId、furnitureId参数值非法";
			}
		} else {
			status = 1;
			message = "userId、furnitureId参数不能为空";
		}
		JSONObject jo = new JSONObject();
		jo.put("status", status);
		jo.put("message", message);
		return jo.toString();
	}*/
}
