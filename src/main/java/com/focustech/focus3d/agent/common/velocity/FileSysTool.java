package com.focustech.focus3d.agent.common.velocity;

import com.focustech.cief.filemanage.common.utils.FileManageUtil;

/**
 * 文件服务器velocity工具
 * *
 * @author lihaijun
 *
 */
public class FileSysTool {
	/**
	 * *
	 * @param id
	 * @return
	 */
	public String url(Long id){
		if(id != null && id > 0){
			return FileManageUtil.getFileURL(id);
		}
		return "";
	}
	public String name(Long id){
		if(id != null && id > 0){
			return FileManageUtil.getFileName(id);
		}
		return "";
	}
}
