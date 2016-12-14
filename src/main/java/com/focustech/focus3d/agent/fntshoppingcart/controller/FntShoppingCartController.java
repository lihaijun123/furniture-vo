package com.focustech.focus3d.agent.fntshoppingcart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.focustech.common.utils.EncryptUtil;
import com.focustech.common.utils.StringUtils;
import com.focustech.focus3d.agent.common.controller.CommonController;
import com.focustech.focus3d.agent.fntshoppingcart.service.FntShoppingCartService;
import com.focustech.focus3d.agent.model.FntShoppingCartModel;

/**
 * 
 * *
 * @author lihaijun
 *
 */
@Controller
@RequestMapping(value = "/fntshoppingcart")
public class FntShoppingCartController extends CommonController{
	@Autowired
	private FntShoppingCartService<FntShoppingCartModel> shoppingCartService;
	/**
	 * 
	 * *
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/home/list")
	public String homeList(ModelMap modelMap){
		List<FntShoppingCartModel> list = shoppingCartService.listAll();
		modelMap.put("list", list);
		return "/fntshoppingcart/homelist";
	}
	/**
	 * 
	 * *
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list(ModelMap modelMap){
		List<FntShoppingCartModel> list = shoppingCartService.listByUser();
		modelMap.put("list", list);
		return "/fntshoppingcart/list";
	}
	
	@RequestMapping(value = "/delete/{encryptSn}")
	public String delete(@PathVariable String encryptSn, ModelMap modelMap) throws Exception{
		if(StringUtils.isNotEmpty(encryptSn)){
			Long decodeSn = EncryptUtil.decode(encryptSn);
			shoppingCartService.deleteBySn(decodeSn);
		}
		return redirect("/fntshoppingcart/list");
	}
}
