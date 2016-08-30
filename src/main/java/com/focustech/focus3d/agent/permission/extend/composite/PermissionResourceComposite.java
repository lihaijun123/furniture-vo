package com.focustech.focus3d.agent.permission.extend.composite;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.focustech.common.utils.StringUtils;
import com.focustech.common.utils.TCUtil;
import com.focustech.focus3d.agent.permission.constant.PermissionConst;

/**
 * 权限-角色资源
 * *
 * @author lihaijun
 *
 */
public class PermissionResourceComposite extends PermissionResource{
	/**组件对象集合*/
	private List<PermissionResource> childResourceList = null;
	/**
	 * 添加子组件
	 * *
	 * @param child
	 */
	public void addChild(PermissionResource child){
		if(null == childResourceList){
			childResourceList = new ArrayList<PermissionResource>();
		}
		childResourceList.add(child);
		child.setParentResource(this);
	}

	@Override
	public void removeChild(PermissionResource child) {
		if(null != childResourceList){
			int idx = childResourceList.indexOf(child);
			if(idx != -1){
				//childResourceList.remove(idx);
				child.setAuthorize(false);
				if(!child.isLeaf()){
					for(PermissionResource childResource : child.getChildResourceList()){
						childResource.removeChild(this);
					}
				}
			}
		}
	}
	@Override
	public void filter(List<PermissionResource> assignResourceList) {
		filterCurrentResource(assignResourceList);
		if(null != this.childResourceList){
			for(int i = 0, j = childResourceList.size(); i < j; i ++){
				PermissionResource component = childResourceList.get(i);
				component.filter(assignResourceList);
			}
		}
	}


	@Override
	public void initResource() {
		this.setAuthorize(true);
		if(null != this.childResourceList){
			for(int i = 0, j = childResourceList.size(); i < j; i ++){
				PermissionResource component = childResourceList.get(i);
				component.initResource();
			}
		}
	}
	/**
	 * 创建权限树
	 * *
	 * @param parentResourceList 父资源列表
	 * @param resuorceList 资源列表
	 */
	@Override
	public PermissionResource createPermissionTree(List<Map<String, String>> resuorceList){
		//创建角色组合对象
		PermissionResource permissionResource = null;
		for(int i = 0, j = resuorceList.size(); i < j; i ++){
			Map<String, String> resourceMap = resuorceList.get(i);
			String resourceSn = resourceMap.get(PermissionConst.RESOURCE_SN);
			String parentSn = resourceMap.get(PermissionConst.PARENT_NODE_SN);
			if(isLeaf(resuorceList, resourceSn, parentSn)){
				permissionResource = new  PermissionResourceLeaf();
			} else {
				permissionResource = new PermissionResourceComposite();
			}
			setPermissionResourceData(permissionResource, resourceMap);
			PermissionResource parentResource = this.findChild(parentSn);
			if(null != parentResource){
				//把子节点添加到组合对象中
				parentResource.addChild(permissionResource);
			}
		}
		return this;
	}

	/**
	 *
	 * *
	 * @param resuorceList
	 * @param resourceSn
	 * @return
	 */
	private boolean isLeaf(List<Map<String, String>> resuorceList, String resourceSn, String parentSn){
		if(StringUtils.isEmpty(resourceSn)){
			return false;
		}
		if(PermissionConst.PARENT_NODE.equals(parentSn)){
			return false;
		}
		boolean flag = true;
		for(int i = 0, j = resuorceList.size(); i < j; i ++){
			String parentId = resuorceList.get(i).get(PermissionConst.PARENT_NODE_SN);
			if(resourceSn.equals(parentId)){
				flag = false;
				break;
			}
		}
		return flag;
	}

	@Override
	public PermissionResource findChild(String parentResourceSn) {
		if(getResourceSn().equals(parentResourceSn)){
			return this;
		}
		if(null != this.childResourceList){
			for(PermissionResource permissionResource : childResourceList){
				if(!permissionResource.isLeaf()){
					PermissionResource childResource = permissionResource.findChild(parentResourceSn);
					if(null != childResource){
						return childResource;
					}
				} else {
					if(parentResourceSn.equals(permissionResource.getResourceSn())){
						return permissionResource;
					}
				}
			}
		}
		return null;
	}
	@Override
	public List<PermissionResource> getChildResourceList() {
		return childResourceList;
	}

	public void setChildResourceList(List<PermissionResource> childResourceList) {
		this.childResourceList = childResourceList;
	}

	@Override
	public boolean isLeaf() {
		return false;
	}

	@Override
	public void printStringStruct(String preStr) {
		System.out.println(preStr + "+" + this.getResourceDisplayName() + getResourceNodeAttr());
		if(null != this.getChildResourceList()){
			preStr += " ";
			for(PermissionResource c : this.getChildResourceList()){
				//递归输出每个子对象
				c.printStringStruct(preStr);
			}
		}
	}

	@Override
	public JSONObject createMenuTree(JSONObject jo) {
		JSONArray jsonArray = null;
		//不是root节点
		if(!PermissionConst.PARENT_NODE.equals(this.getResourceSn())){
			//root的子节点
			if(PermissionConst.PARENT_NODE.equals(this.getParentResource().getResourceSn())){
				//root节点的子节点，用来加入下面的叶子节点
				jsonArray = new JSONArray();
				jo.put(this.getResourceNameKey(), jsonArray);
			} else {
				if(isDisplayResource(this)){
					//获取父节点下面的jsonAry
					String parentResourceName = this.getParentResource().getResourceNameKey();
					if(jo.containsKey(parentResourceName)){
						jsonArray = jo.getJSONArray(parentResourceName);
					}
					//组合节点属性
					jsonArray.add(createResourceAttrJson());
				}
			}
		}
		if(null != getChildResourceList()){
			for (PermissionResource permissionResource : getChildResourceList()) {
				permissionResource.createMenuTree(jo);
			}
		}
		return jo;
	}

	@Override
	public PermissionResource findChildByUrl(String resourceUrl) {
		String tempUrl = getResourceUrl();
		if (!TCUtil.objIsNull(tempUrl) && tempUrl.indexOf("?") != -1) {
			tempUrl = tempUrl.substring(0, tempUrl.indexOf("?"));
        }
		if(!StringUtils.isEmpty(tempUrl) && (resourceUrl.equals(tempUrl) || tempUrl.startsWith(resourceUrl) || resourceUrl.startsWith(tempUrl))){
			return this;
		}
		if(null != this.childResourceList){
			for(PermissionResource permissionResource : childResourceList){
				PermissionResource childResource = permissionResource.findChildByUrl(resourceUrl);
				if(null != childResource && isDisplayResource(this)){
					return childResource;
				}
			}
		}
		return null;
	}

}
