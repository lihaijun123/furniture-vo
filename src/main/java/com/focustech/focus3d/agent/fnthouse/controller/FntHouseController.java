package com.focustech.focus3d.agent.fnthouse.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.focustech.common.utils.StringUtils;
import com.focustech.common.utils.TCUtil;
import com.focustech.focus3d.agent.common.controller.CommonController;
import com.focustech.focus3d.agent.filter.RequestThreadLocal;
import com.focustech.focus3d.agent.fnthouse.service.FntHouseService;
import com.focustech.focus3d.agent.fnthouseupload.service.FntHouseUploadService;
import com.focustech.focus3d.agent.model.FntHouseModel;
import com.focustech.focus3d.agent.model.FntHouseUploadModel;
import com.focustech.focus3d.furniture.restful.search.FntHouseSearch;
/**
 * 
 * *
 * @author lihaijun
 *
 */
@Controller
@RequestMapping(value = "/fnthouse")
public class FntHouseController extends CommonController {
	@Autowired
	private FntHouseService<FntHouseModel> fntHouseService;
	@Autowired
	private FntHouseUploadService<FntHouseUploadModel> fntHouseUploadService;
	/**
	 * 
	 * *
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(ModelMap modelMap){
		return "/fnthouse/search";
	}
	/**
	 * 
	 * *
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String search(FntHouseSearch houseSearch, ModelMap modelMap){
		List<FntHouseModel> list = fntHouseService.search(houseSearch);
		modelMap.addAttribute("list", list);
		return "/fnthouse/search";
	}
	
	/**
	 * *
	 * @param modelMap
	 * @return
	 */
	/*@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String upload(ModelMap modelMap){
		return "/fnthouse/upload";
	}*/
	
	/**
	 * *
	 * @param houseUploadModel
	 * @param modelMap
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public void upload(FntHouseUploadModel houseUploadModel, ModelMap modelMap, HttpServletResponse response) throws IOException{
		String buildingName = houseUploadModel.getBuildingName();
		Long picFileSn = houseUploadModel.getPicFileSn();
		String status = "";
		if(StringUtils.isNotEmpty(buildingName) && picFileSn != null){
			
			Long sn = RequestThreadLocal.getLoginInfo().getSn();
			houseUploadModel.setUserId(sn);
			houseUploadModel.setHouseName(buildingName);
			houseUploadModel.setHouseFileSn(picFileSn);
			houseUploadModel.setStatus(1);
			//fntHouseUploadService.insert(houseUploadModel);
			FntHouseModel fntHouseModel = new FntHouseModel();
			fntHouseModel.setName(TCUtil.sv(houseUploadModel.getHouseName()));
			fntHouseModel.setPicFileSn(TCUtil.lv(houseUploadModel.getPicFileSn()));
			fntHouseModel.setStatus(4);
			fntHouseModel.setBuildingName(TCUtil.sv(houseUploadModel.getBuildingName()));
			fntHouseModel.setType(TCUtil.iv(houseUploadModel.getType()));
			BigDecimal area = houseUploadModel.getArea();
			if(area != null){
				fntHouseModel.setArea(area);
			}
			fntHouseModel.setRoomNum(TCUtil.iv(houseUploadModel.getRoomNum()));
			fntHouseModel.setLivingRoomNum(TCUtil.iv(houseUploadModel.getLivingRoomNum()));
			fntHouseModel.setProvince(TCUtil.sv(houseUploadModel.getProvince()));
			fntHouseModel.setCity(TCUtil.sv(houseUploadModel.getCity()));
			fntHouseModel.setStreet(TCUtil.sv(houseUploadModel.getStreet()));
			fntHouseModel.setIsRecommend(0);
			try {
				fntHouseService.insert(fntHouseModel);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			status = "ok";
		}
		JSONObject jo = new JSONObject();
		jo.put("Message", status);
		ajaxOutput(response, jo.toString());
	}
}
