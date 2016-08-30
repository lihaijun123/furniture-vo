package com.focustech.focus3d.agent.permission.extend;

import java.util.List;

/**
 * 权限查询接口
 * *
 * @author lihaijun
 *
 */
public interface IPermissionQuery{
	/**
	 * 通过登录用户获取菜单树
	 * *
	 * @param loginName
	 * @return
	 */
	public String getMenuTreeData(PermissionParameter permissionParameter);
	/**
	 * 获取权限菜单导航数据
	 * *
	 * @param permissionParameter
	 * @return
	 */
	public List<PermissionResourceVo> getMenuNavigationList(PermissionParameter permissionParameter);
	/**
	 * 获取功能权限
	 * *
	 * @param permissionParameter
	 * @return
	 */
	public List<PermissionResourceVo> getMenuFunctionList(PermissionParameter permissionParameter);
	/**
	 * 通过url查询节点资源
	 * *
	 * @param permissionParameter
	 * @return
	 */
	public PermissionResourceVo findResource(PermissionParameter permissionParameter);
	/**
	 * 资源是否被授权
	 * *
	 * @param userSn 当前用户
	 * @param grade 会员等级
	 * @param isAdmin 是否是管理员
	 * @param exbSn 展会sn
	 * @param resourceCode 资源code
	 * @return
	 */
	public boolean isAuthorizedResource(PermissionParameter permissionParameter);

}
