package com.focustech.focus3d.agent.permission.extend.composite;

import java.util.ArrayList;
import java.util.List;


/**
 * 权限-用户
 * *
 * @author lihaijun
 *
 */
public class PermissionUser {
	private String userSn;
	private String userName;
	//一个用户可以拥有多个权限角色，权限角色拥有自己的权限数据
	private List<PermissionResource> permissionRoleList = new ArrayList<PermissionResource>();
	//用户被分配的权限列表
	private List<PermissionResource> assignList = new ArrayList<PermissionResource>();
	/**
	 *
	 * *
	 * @param composite
	 */
	public void addPermissionRoleList(PermissionResource composite){
		permissionRoleList.add(composite);
	}
	/**
	 *
	 * *
	 * @param resource
	 */
	public void addAssignList(PermissionResource resource){
		assignList.add(resource);
	}
	/**
	 * 过滤掉未分配的权限
	 * *
	 */
	public PermissionResource filterUnAssignPermission(){
		PermissionResource permissionResource = null;
		for(int i = 0, j = permissionRoleList.size(); i < j; i ++){
			permissionResource = permissionRoleList.get(i);
			permissionResource.filter(getAssignList());
		}
		permissionResource.printStringStruct("子帐户" + getUserSn() + "-" + permissionResource.getRoleName() + " 权限等级结构-----");
		return permissionResource;
	}

	public List<PermissionResource> getAssignList() {
		return assignList;
	}
	public void setAssignList(List<PermissionResource> assignList) {
		this.assignList = assignList;
	}
	public String getUserSn() {
		return userSn;
	}
	public void setUserSn(String userSn) {
		this.userSn = userSn;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public List<PermissionResource> getPermissionRoleList() {
		return permissionRoleList;
	}
	public void setPermissionRoleList(List<PermissionResource> permissionRoleList) {
		this.permissionRoleList = permissionRoleList;
	}

}
