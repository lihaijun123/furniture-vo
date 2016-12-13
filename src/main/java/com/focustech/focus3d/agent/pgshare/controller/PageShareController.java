package com.focustech.focus3d.agent.pgshare.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.aspectj.weaver.AjAttribute;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.focustech.cief.filemanage.common.utils.FileManageUtil;
import com.focustech.common.utils.StringUtils;
import com.focustech.common.utils.TCUtil;
import com.focustech.focus3d.agent.common.controller.CommonController;

/**
 * *
 * @author lihaijun
 *
 */
@Controller
@RequestMapping(value = "/pgshare")
public class PageShareController extends CommonController{
	/**
	 * *
	 * @param id
	 * @param req
	 * @param resp
	 * @param model
	 * @throws IOException 
	 */
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
    public void index(@PathVariable String id, HttpServletRequest req, HttpServletResponse response, Model model) throws IOException {
		JSONObject jo = new JSONObject();
		String fileURL = "";
		if(StringUtils.isNotEmpty(id)){
			fileURL = FileManageUtil.getFileURL(TCUtil.lv(id));
		}
		jo.put("picUrl", fileURL);
		ajaxOutput(response, jo.toString());
	}
}
