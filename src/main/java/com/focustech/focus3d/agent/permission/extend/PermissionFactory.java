package com.focustech.focus3d.agent.permission.extend;

import com.focustech.common.utils.SpringUtil;

/**
 * 权限实现类工厂
 * *
 * @author lihaijun
 *
 */
public class PermissionFactory {
	public static final IPermissionQuery permissionQuery = (IPermissionQuery) SpringUtil.getBean("permissionManager");
	/**
	 * 获取权限查询实现对象
	 * *
	 * @return
	 */
	public static IPermissionQuery getQueryInstance(){
		return permissionQuery;
	}
}
