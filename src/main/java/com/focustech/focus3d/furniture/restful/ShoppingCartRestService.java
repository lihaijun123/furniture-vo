package com.focustech.focus3d.furniture.restful;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.focustech.common.utils.EncryptUtil;
import com.focustech.common.utils.StringUtils;
import com.focustech.focus3d.agent.fntshoppingcart.service.FntShoppingCartService;
import com.focustech.focus3d.agent.model.FntShoppingCartModel;
/**
 * 
 * *
 * @author lihaijun
 *
 */
@Service
@Path("/rest/shoppingcart")
public class ShoppingCartRestService {
	@Autowired
	private FntShoppingCartService<FntShoppingCartModel> shoppingCartService;
	
	/**
	 * *
	 * @param userId
	 * @param furnitureId
	 * @return
	 * @throws Exception
	 */
	@POST
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
	}
}
