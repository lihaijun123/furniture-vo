package com.focustech.focus3d.furniture.restful;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.focustech.common.utils.EncryptUtil;
import com.focustech.common.utils.StringUtils;
import com.focustech.common.utils.TCUtil;
import com.focustech.focus3d.agent.fntfavorite.service.FntFavoriteService;
import com.focustech.focus3d.agent.fntproduct.service.FntProductService;
import com.focustech.focus3d.agent.model.FntFavoriteModel;
import com.focustech.focus3d.agent.model.FntProductModel;
import com.focustech.focus3d.furniture.restful.common.RestMethodDesc;
import com.focustech.focus3d.furniture.restful.constant.ContentType;

/**
 * 
 * *
 * @author lihaijun
 *
 */
@RestMethodDesc("收藏服务")
@Service
@Path("/rest/favorite")
@Produces(ContentType.APPLICATION_JSON_UTF_8)
public class FavoriteRestService {
	@Autowired
	private FntFavoriteService<FntFavoriteModel> favoriteService;
	@Autowired
	private FntProductService<FntProductModel> fntProductService;
	/**
	 * 
	 * *
	 * @param userId
	 * @param targetId
	 * @param targetType
	 * @return
	 */
	@RestMethodDesc("保存收藏")
	@POST
	@Path("save")
	public String save(
			@FormParam("userId") String userId, 
			@FormParam("targetId") String targetId,
			@FormParam("type") String type
			){
		int status = 0;
		String message = "";
		if(StringUtils.isNotEmpty(userId) && StringUtils.isNotEmpty(targetId)){
			int targetType = TCUtil.iv(type);//默认家具
			try {
				Long userIdDecode = EncryptUtil.decode(userId);
				Long targetIdDecode = EncryptUtil.decode(targetId);
				FntFavoriteModel favoriteModel = favoriteService.select(userIdDecode, targetIdDecode, targetType);
				if(favoriteModel == null){
					favoriteModel = new FntFavoriteModel();
					favoriteModel.setUserId(userIdDecode);
					favoriteModel.setTargetId(targetIdDecode);
					favoriteModel.setTargetType(targetType);
					favoriteService.insert(favoriteModel);
					message = "收藏成功";
				} else {
					status = 1;
					message = "收藏失败，已经被收藏";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		JSONObject jo = new JSONObject();
		jo.put("status", status);
		jo.put("message", message);
		return jo.toString();
	}
	/**
	 * *
	 * @param userId
	 * @param targetType
	 * @return
	 */
	@RestMethodDesc("获取用户收藏列表")
	@GET
	@Path("list/{userId}")
	public String list(@PathParam("userId") String userId){
		JSONArray jary = getList(userId);
		return jary.toString();
	}
	/**
	 * *
	 * @param userId
	 * @param targetType
	 * @return
	 */
	@RestMethodDesc("获取用户收藏列表")
	@POST
	@Path("list")
	public String listByPost(@FormParam("userId") String userId){
		JSONArray jary = getList(userId);
		return jary.toString();
	}
	/**
	 * 
	 * *
	 * @param userId
	 * @return
	 */
	private JSONArray getList(String userId) {
		//LnAKoUyeyUpB DqoeAUpKyyey
		//EncryptUtil.encode(100000L);
		JSONArray jary = new JSONArray();
		if(StringUtils.isNotEmpty(userId)){
			int targetType = 1;
			try {
				List<FntFavoriteModel> list = favoriteService.list(EncryptUtil.decode(userId), targetType);
				for (FntFavoriteModel fntFavoriteModel : list) {
					jary.add(fntProductService.serialize(fntFavoriteModel.getTargetId()));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return jary;
	}
}
