package com.focustech.focus3d.agent.pgshare.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.focustech.cief.filemanage.common.utils.FileManageUtil;
import com.focustech.common.utils.StringUtils;
import com.focustech.common.utils.TCUtil;

/**
 * *
 * @author lihaijun
 *
 */
@Controller
@RequestMapping(value = "/pgshare")
public class PageShareController {
	/**
	 * *
	 * @param id
	 * @param req
	 * @param resp
	 * @param model
	 */
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
    public String index(@PathVariable String id, HttpServletRequest req, HttpServletResponse resp, Model model) {
		if(StringUtils.isNotEmpty(id)){
			String fileURL = FileManageUtil.getFileURL(TCUtil.lv(id));
			model.addAttribute("picUrl", fileURL);
		}
		return "/pgshare/share";
	}
}
