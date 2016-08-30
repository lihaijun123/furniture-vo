package com.focustech.focus3d.agent.permission.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.focustech.focus3d.agent.common.controller.CommonController;
import com.focustech.focus3d.agent.permission.extend.IPermissionQuery;
import com.focustech.focus3d.agent.permission.extend.PermissionParameter;

/**
 * 菜单树
 * *
 * @author lihaijun
 *
 */
@Controller
@RequestMapping("/menutree")
public class MenuTreeController extends CommonController{
	
	@Autowired
	private IPermissionQuery permissionQuery ;

	@RequestMapping(value="/data/{id}", method = RequestMethod.GET)
    public void getData(@PathVariable String id, HttpServletRequest req, HttpServletResponse resp, Model model) {
		PermissionParameter permissionParameter = createPermissionParam(req);
		String configData = permissionQuery.getMenuTreeData(permissionParameter);
		try {
			ajaxOutput(resp, configData);
		} catch (IOException e) {
			e.printStackTrace();
		}
	
    }
	public PermissionParameter createPermissionParam(HttpServletRequest req){
		PermissionParameter permissionParameter = new PermissionParameter();
		permissionParameter.setUserSn("-1");
		permissionParameter.setGrade("5");
		permissionParameter.setAdmin(true);
		return permissionParameter;
	}

}
