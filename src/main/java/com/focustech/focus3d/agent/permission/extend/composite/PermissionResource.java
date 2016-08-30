package com.focustech.focus3d.agent.permission.extend.composite;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.focustech.focus3d.agent.permission.constant.PermissionConst;
import com.focustech.focus3d.agent.permission.extend.PermissionResourceVo;


/**
 * 抽象权限资源组件对象
 * *
 * @author lihaijun
 *
 */
public abstract class PermissionResource {
	//资源基本信息
	private String resourceSn;
	private String resourceCode;
	private String resourceName;
	private String resourceDisplayName;
	private String resourceUrl;
	private String resourceType;
	private String resourceLinkId;
	private String permFlag;
	private String active;
	//角色信息
	private String roleSn;
	private String roleID;
	private String roleName;
	//附加信息
	private boolean isAuthorize = true;//是否有权限
	/**父节点*/
	private PermissionResource parentResource;
	/**
	 * 添加一个子节点
	 * *
	 * @param child
	 */
	public abstract void addChild(PermissionResource child);
	/**
	 * 查询子节点
	 * *
	 * @param parentResourceSn
	 * @return
	 */
	public abstract PermissionResource findChild(String parentResourceSn);
	/**
	 * 查询子节点
	 * *
	 * @param resourceUrl
	 * @return
	 */
	public abstract PermissionResource findChildByUrl(String resourceUrl);
	/**
	 * 创建权限树
	 * *
	 * @param resuorceList
	 * @return
	 */
	public abstract PermissionResource createPermissionTree(List<Map<String, String>> resuorceList);
	/**
	 * 移除节点-暂时设置资源未被授予权限标志
	 * *
	 * @param child
	 */
	public abstract void removeChild(PermissionResource child);
	/**
	 * 权限过滤
	 * *
	 * @param assignResourceList
	 */
	public abstract void filter(List<PermissionResource> assignResourceList);
	/**
	 * 获取子节点集合
	 * *
	 * @return
	 */
	public abstract List<PermissionResource> getChildResourceList();
	/**
	 * 是否叶子节点
	 * *
	 * @return
	 */
	public abstract boolean isLeaf();
	/**
	 * 打印权限等级结构-字符串形式
	 * *
	 * @param preStr
	 */
	public abstract void printStringStruct(String preStr);
	/**
	 * 打印json格式等级树形权限结构
	 * *
	 * @param jo
	 * @return
	 */
	public abstract JSONObject createMenuTree(JSONObject jo);

	public abstract void initResource();
	/**
	 * 设置资源数据
	 * *
	 * @param permissionResource
	 * @param resourceMap
	 */
	protected void setPermissionResourceData(PermissionResource permissionResource, Map<String, String> resourceMap) {
		permissionResource.setResourceSn(resourceMap.get(PermissionConst.RESOURCE_SN));
		permissionResource.setResourceCode(resourceMap.get(PermissionConst.NODE_CODE));
		permissionResource.setResourceName(resourceMap.get(PermissionConst.NODE_NAME));
		permissionResource.setResourceDisplayName(resourceMap.get(PermissionConst.NODE_DISPLAY_NAME));
		permissionResource.setResourceType(resourceMap.get(PermissionConst.NODE_TYPE));
		permissionResource.setResourceLinkId(resourceMap.get(PermissionConst.RESOURCE_LINK_ID));
		permissionResource.setResourceUrl(resourceMap.get(PermissionConst.NODE_URL));
		permissionResource.setPermFlag(resourceMap.get(PermissionConst.RESOURCE_PERM_FLAG));
		permissionResource.setActive(resourceMap.get(PermissionConst.NODE_ACTIVE));
	}
	/**
	 * 节点是否被授权
	 * *
	 * @param assignResourceList 授权资源集合
	 * @param nodeCode 被检查的资源节点
	 * @return
	 */
	protected boolean isAuthorize(List<PermissionResource> assignResourceList){
		for(int i = 0, j = assignResourceList.size(); i < j; i ++){
			PermissionResource permissionResource = assignResourceList.get(i);
			String authNodeCode = permissionResource.getResourceCode();
			if(getResourceCode().equals(authNodeCode)){
				return true;
			}
		}
		return false;
	}
	/**
	 * 过滤当前权限节点
	 * *
	 * @param assignResourceList
	 */
	protected void filterCurrentResource(List<PermissionResource> assignResourceList) {
		//可以授权的节点是否被授权
		boolean canAuth = PermissionConst.NODE_TYPE_AUTH.equals(getResourceType()) && !isAuthorize(assignResourceList);
		//不可授权的节点，对子帐户不可见的节点
		boolean noAuth = PermissionConst.NODE_TYPE_NO.equals(getResourceType());
		if(canAuth || noAuth){
			if(null != getParentResource()){
				getParentResource().removeChild(this);
				return;
			}
		}
	}

	/**
	 * 是否显示权限节点
	 * *
	 * @return
	 */
	public static boolean isDisplayResource(PermissionResource permissionResource){
		if(!permissionResource.isAuthorize()){
			return false;
		} else {
			if(!permissionResource.isLeaf()){
				for (PermissionResource childResource : permissionResource.getChildResourceList()) {
					if(childResource.isAuthorize()){
						return true;
					}
				}
			} else {
				return permissionResource.isAuthorize();
			}
		}
		return false;
	}
	/**
	 * 创建json格式的资源节点属性
	 * *
	 * @return
	 */
	protected JSONObject createResourceAttrJson() {
		JSONObject leafJo = new JSONObject();
		leafJo.put(PermissionConst.NODE_DISPLAY_NAME, this.getResourceDisplayName());
		leafJo.put(PermissionConst.RESOURCE_LINK_ID, this.getResourceLinkId());
		leafJo.put(PermissionConst.NODE_URL, this.getResourceUrl());
		return leafJo;
	}
	/**
	 * 创建资源基本信息
	 * *
	 * @param map
	 * @return
	 */
	public PermissionResourceVo createResourceBaseInfo(){
		PermissionResourceVo resource = new PermissionResourceVo();
		resource.setResourceCode(this.getResourceCode());
		resource.setResourceDisplayName(this.getResourceDisplayName());
		resource.setResourceUrl(this.getResourceUrl());
		resource.setActive(this.getActive());
		return resource;
	}
	/**
	 * 获取资源节点属性
	 * *
	 * @return
	 */
	protected String getResourceNodeAttr() {
		String resourceNodeAttr = "(" + (PermissionConst.NODE_PERM_FLAG_MENU.equals(getPermFlag()) ? "菜单权限" : "功能权限") + "-" + (isAuthorize() ? "授权" : "未授权") + ")";
		return resourceNodeAttr;
	}
	public String getRoleSn() {
		return roleSn;
	}
	public void setRoleSn(String roleSn) {
		this.roleSn = roleSn;
	}
	public String getRoleID() {
		return roleID;
	}
	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getResourceSn() {
		return resourceSn;
	}
	public void setResourceSn(String resourceSn) {
		this.resourceSn = resourceSn;
	}
	public PermissionResource getParentResource() {
		return parentResource;
	}
	public void setParentResource(PermissionResource parentResource) {
		this.parentResource = parentResource;
	}
	public String getResourceType() {
		return resourceType;
	}
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}
	public String getResourceLinkId() {
		return resourceLinkId;
	}
	public void setResourceLinkId(String resourceLinkId) {
		this.resourceLinkId = resourceLinkId;
	}
	public String getPermFlag() {
		return permFlag;
	}
	public void setPermFlag(String permFlag) {
		this.permFlag = permFlag;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public String getResourceCode() {
		return resourceCode;
	}
	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
	}
	public String getResourceDisplayName() {
		return resourceDisplayName;
	}
	public void setResourceDisplayName(String resourceDisplayName) {
		this.resourceDisplayName = resourceDisplayName;
	}
	public String getResourceUrl() {
		return resourceUrl;
	}
	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}
	public boolean isAuthorize() {
		return isAuthorize;
	}
	public void setAuthorize(boolean isAuthorize) {
		this.isAuthorize = isAuthorize;
	}
	/**
	 * *
	 * @return
	 */
	public String getResourceNameKey() {
		String name = this.getResourceDisplayName();
		if(!isLeaf()){
			name += "_";
			if(null != getChildResourceList()){
				name += PermissionConst.PARENT_NODE;
			} else {
				name += getResourceUrl();
			}
		}
		return name;
	}
}
