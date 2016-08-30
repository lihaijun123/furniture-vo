package com.focustech.focus3d.agent.permission.extend;


/**
 * 权限处理接口
 * *
 * @author lihaijun
 *
 */
public interface IPermissionHandle {
	/**
	 * 分配权限
	 * *
	 * @param permissionParameter
	 * @return
	 */
	public boolean assign(PermissionParameter permissionParameter);
}
