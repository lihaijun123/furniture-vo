package com.focustech.focus3d.agent.permission.extend.composite;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.focustech.common.utils.StringUtils;
import com.focustech.common.utils.TCUtil;

/**
 * 叶子资源对象
 * *
 * @author lihaijun
 *
 */
public class PermissionResourceLeaf extends PermissionResource{

	@Override
	public void addChild(PermissionResource child) {
		throw new UnsupportedOperationException("权限树叶子节点不支持此方法");
	}

	@Override
	public PermissionResource findChild(String parentResourceSn) {
		throw new UnsupportedOperationException("权限树叶子节点不支持此方法");
	}

	@Override
	public PermissionResource createPermissionTree(List<Map<String, String>> resuorceList) {
		throw new UnsupportedOperationException("权限树叶子节点不支持此方法");
	}

	@Override
	public void removeChild(PermissionResource child) {
		throw new UnsupportedOperationException("权限树叶子节点不支持此方法");
	}

	@Override
	public void filter(List<PermissionResource> assignResourceList) {
		filterCurrentResource(assignResourceList);
	}

	@Override
	public List<PermissionResource> getChildResourceList() {
		throw new UnsupportedOperationException("权限树叶子节点不支持此方法");
	}

	@Override
	public boolean isLeaf() {
		return true;
	}

	@Override
	public void printStringStruct(String preStr) {
		System.out.println(preStr + "-" + this.getResourceDisplayName() + getResourceNodeAttr());
	}

	@Override
	public JSONObject createMenuTree(JSONObject jo) {
		if(isDisplayResource(this)){
			JSONArray jsonArray = null;
			//获取父节点jsonAry
			String parentResourceName = this.getParentResource().getResourceNameKey();
			if(jo.containsKey(parentResourceName)){
				jsonArray = jo.getJSONArray(parentResourceName);
			} else {
				//jsonArray = new JSONArray();
			}
			if(null != jsonArray){
				//叶子节点属性
				jsonArray.add(createResourceAttrJson());
			}
		}
		return jo;
	}

	@Override
	public void initResource() {
		this.setAuthorize(true);
	}

	@Override
	public PermissionResource findChildByUrl(String resourceUrl) {
		String tempUrl = getResourceUrl();
		if (!TCUtil.objIsNull(tempUrl) && tempUrl.indexOf("?") != -1) {
			tempUrl = tempUrl.substring(0, tempUrl.indexOf("?"));
        }
		if(!StringUtils.isEmpty(tempUrl) && (resourceUrl.equals(tempUrl) || tempUrl.startsWith(resourceUrl) || resourceUrl.startsWith(tempUrl))){
			if(isDisplayResource(this)){
				return this;
			}
		}
		return null;
	}

}
