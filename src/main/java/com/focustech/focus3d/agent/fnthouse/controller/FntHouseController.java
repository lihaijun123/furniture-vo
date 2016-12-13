package com.focustech.focus3d.agent.fnthouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.focustech.common.utils.StringUtils;
import com.focustech.focus3d.agent.common.controller.CommonController;
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
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String upload(ModelMap modelMap){
		return "/fnthouse/upload";
	}
	
	/**
	 * *
	 * @param houseUploadModel
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(FntHouseUploadModel houseUploadModel, ModelMap modelMap){
		String buildingName = houseUploadModel.getBuildingName();
		Long picFileSn = houseUploadModel.getPicFileSn();
		int status = 1;
		if(StringUtils.isNotEmpty(buildingName) && picFileSn != null){
			houseUploadModel.setUserId(7L);
			houseUploadModel.setHouseName(buildingName);
			houseUploadModel.setHouseFileSn(picFileSn);
			houseUploadModel.setStatus(1);
			fntHouseUploadService.insert(houseUploadModel);
		}
		modelMap.put("status", status);
		return "/fnthouse/upload";
	}
}
