package com.focustech.focus3d.furniture.restful;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.focustech.common.utils.EncryptUtil;
import com.focustech.common.utils.StringUtils;
import com.focustech.focus3d.agent.fntcase.service.FntCaseService;
import com.focustech.focus3d.agent.fnthouse.service.FntHouseService;
import com.focustech.focus3d.agent.model.FntCaseModel;
import com.focustech.focus3d.agent.model.FntHouseModel;
import com.focustech.focus3d.furniture.restful.constant.ContentType;

/**
 * 
 * *
 * @author lihaijun
 *
 */
@Service
@Path("/rest/case")
@Produces(ContentType.APPLICATION_JSON_UTF_8)
public class CaseRestService {
	@Autowired
	private FntCaseService<FntCaseModel> caseService;
	@Autowired
	private FntHouseService<FntHouseModel> houseService;
	/**
	 * *
	 * @param userId
	 * @param houseId
	 * @param caseData
	 * @return
	 */
	@POST
	@Path("/save")
	public String save(@FormParam("userId") String userId, @FormParam("houseId") String houseId, @FormParam("data") String caseData){
		int status = 0;
		String message = "";
		if(StringUtils.isNotEmpty(userId) && StringUtils.isNotEmpty(houseId) && StringUtils.isNotEmpty(caseData)){
			try {
				long userIdDec = EncryptUtil.decode(userId);
				long houseIdDec = EncryptUtil.decode(houseId);
				FntCaseModel caseModel = caseService.select(userIdDec, houseIdDec);
				if(caseModel == null){
					caseModel = new FntCaseModel();
					caseModel.setUserId(userIdDec);
					caseModel.setHouseId(houseIdDec);
					caseModel.setCaseData(caseData);
					caseService.insert(caseModel);
					message = "添加成功";
				} else {
					caseModel.setCaseData(caseData);
					caseService.updateByKeySelective(caseModel);
					message = "修改成功";
				}
			} catch (Exception e) {
				message = "添加失败";
				e.printStackTrace();
			}
		} else {
			message = "参数不正确";
		}
		JSONObject jo = new JSONObject();
		jo.put("status", status);
		jo.put("message", message);
		return jo.toString();
	}
	/**
	 * 
	 * *
	 * @param userId
	 * @return
	 */
	@POST
	@Path("/list")
	public String list(@FormParam("userId") String userId, @FormParam("houseId") String houseId){
		JSONArray jary = new JSONArray();
		List<FntCaseModel> list = new ArrayList<FntCaseModel>();
		try {
			if(StringUtils.isNotEmpty(userId)){
				long userIdDec = EncryptUtil.decode(userId);
				list = caseService.listByUser(userIdDec);
			} else if(StringUtils.isNotEmpty(houseId)){
				long houseIdDec = EncryptUtil.decode(houseId);
				list = caseService.listByHouse(houseIdDec);
			} else {
				list = caseService.listAll();
			}
			for (FntCaseModel fntCaseModel : list) {
				JSONObject jo = new JSONObject();
				jo.put("userId", EncryptUtil.encode(fntCaseModel.getUserId()));
				jo.put("house", houseService.serialize(fntCaseModel.getHouseId()));
				jo.put("data", fntCaseModel.getCaseData());
				jary.add(jo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jary.toString();
	}
	
	@GET
	@Path("/test")
	public String test(){
		JSONObject jo = new JSONObject();
		jo.put("userId", EncryptUtil.encode(11L));
		jo.put("houseId", EncryptUtil.encode(100000L));
		return jo.toString();
	}
}
