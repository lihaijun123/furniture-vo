package com.focustech.focus3d.furniture.restful;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.focustech.common.utils.EncryptUtil;
import com.focustech.common.utils.StringUtils;
import com.focustech.focus3d.agent.fntfavorite.service.FntFavoriteService;
import com.focustech.focus3d.agent.fntproduct.service.FntProductService;
import com.focustech.focus3d.agent.model.FntFavoriteModel;
import com.focustech.focus3d.agent.model.FntProductModel;
import com.focustech.focus3d.furniture.restful.constant.ContentType;

/**
 * 
 * *
 * @author lihaijun
 *
 */
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
	@POST
	@Path("/save")
	public String save(
			@QueryParam("userId") String userId,
			@QueryParam("targetId") String targetId,
			@QueryParam("targetType") Integer targetType
			){
		int status = 0;
		String message = "收藏成功";
		if(StringUtils.isNotEmpty(userId) && StringUtils.isNotEmpty(targetId)){
			if(targetType == null){
				targetType = 1;//默认家具
			}
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
	@GET
	@Path("/list/{userId}/{targetType}")
	public String list(@PathParam("userId") String userId, @PathParam("targetType") Integer targetType){
		//LnAKoUyeyUpB DqoeAUpKyyey
		EncryptUtil.encode(100000L);
		JSONArray jary = new JSONArray();
		if(StringUtils.isNotEmpty(userId)){
			if(targetType == null){
				targetType = 1;
			}
			try {
				List<FntFavoriteModel> list = favoriteService.list(EncryptUtil.decode(userId), targetType);
				for (FntFavoriteModel fntFavoriteModel : list) {
					FntProductModel productModel = fntProductService.selectBySn(fntFavoriteModel.getTargetId(), FntProductModel.class);
					jary.add(fntProductService.serialize(productModel));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return jary.toString();
	}
}
