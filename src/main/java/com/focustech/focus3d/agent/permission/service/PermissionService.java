package com.focustech.focus3d.agent.permission.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 *
 * *
 * @author lihaijun
 *
 */
public interface PermissionService extends Serializable{
	/**
	 * 获取所有数据
	 * *
	 * @param map
	 * @return
	 */
	public List<Map<String, String>> getMenuTreeList();

	/**
	 * 获取所有资源父亲节点数据
	 * *
	 * @param map
	 * @return
	 */
	public List<Map<String, String>> getParentNodeList();

	/**
	 * 获取菜单资源父亲节点数据
	 * *
	 * @param map
	 * @return
	 */
	public List<Map<String, String>> getMenuParentNodeList();


	/**
	 * 获取菜单树父亲节点数据
	 * *
	 * @param map
	 * @return
	 */
	public List<Map<String, String>> getMenuTreeParentNodeList(String roleSN);


	/**
	 * 获取角色列表
	 * *
	 * @return
	 */
	public List<Map<String, String>> getRoleList();

	/**
	 *通过角色sn获取对应的菜单树信息
	 * *
	 * @return
	 */
	public List<Map<String, String>> getMenuTreeListByRoleSN(String roleSN);

	/**
	 *
	 * *
	 * @param userSN
	 * @param exbSN
	 * @return
	 */
	public List<Map<String, String>> getAuthMenuTreeList(String userSN, String exbSN);

	/**
	 * 获取父亲节点下的功能菜单资源
	 * *
	 * @param map
	 * @return
	 */
	public List<Map<String, String>> getFuncNodeListByParentSn(String parentSn);
	/**
	 * 获取节点集合
	 * *
	 * @param parentSn
	 * @param permFlag
	 * @return
	 */
	public List<Map<String, String>> getNodeList(String parentSn, String permFlag);
	/**
	 * 获取所有状态的节点
	 * *
	 * @param map
	 * @return
	 */
	public List<Map<String, String>> selectAllNodeList(String parentSn, String permFlag);

	/**
	 * 获取父节点名称和子节点的名称和url
	 * *
	 * @param roleID
	 * @return
	 */
	public List<Map<String, String>> selectParentNodeWithFirstChildUrlList(String roleID);
	/**
	 * 获取权限列表，包括菜单权限和功能权限
	 * *
	 * @param roleSN
	 * @return
	 */
	public List<Map<String, String>> getPermissionListByRoleSN(String roleSN);
}
