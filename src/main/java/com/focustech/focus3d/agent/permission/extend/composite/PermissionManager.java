package com.focustech.focus3d.agent.permission.extend.composite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.focustech.common.utils.HttpUtil;
import com.focustech.common.utils.StringUtils;
import com.focustech.common.utils.TCUtil;
import com.focustech.focus3d.agent.permission.constant.PermissionConst;
import com.focustech.focus3d.agent.permission.extend.IPermissionHandle;
import com.focustech.focus3d.agent.permission.extend.IPermissionQuery;
import com.focustech.focus3d.agent.permission.extend.PermissionParameter;
import com.focustech.focus3d.agent.permission.extend.PermissionResourceVo;
import com.focustech.focus3d.agent.permission.service.PermissionService;

/**
 * 权限管理
 * 控制菜单权限 和 功能按钮权限
 * *
 * @author lihaijun
 *
 */
public class PermissionManager implements IPermissionQuery, IPermissionHandle{

	public static Map<String, PermissionResource> permissionCache = new HashMap<String, PermissionResource>();

	@Autowired
	private PermissionService permissionService;

	public void init(){
		initResourceComposite();
	}
	
	public void initResourceComposite(){
		List<Map<String, String>> roleMapList = permissionService.getRoleList();
		for(int i = 0, j = roleMapList.size(); i < j; i ++){
			PermissionResource resourceComposite = new PermissionResourceComposite();
			Map<String, String> rMap = roleMapList.get(i);
			String roleSn = rMap.get(PermissionConst.ROLE_SN);
			String roleID = rMap.get(PermissionConst.ROLE_ID);
			String roleName = rMap.get(PermissionConst.ROLE_NAME);
			resourceComposite.setResourceSn(PermissionConst.PARENT_NODE);
			resourceComposite.setResourceName(roleName);
			resourceComposite.setRoleSn(roleSn);
			resourceComposite.setRoleID(roleID);
			resourceComposite.setRoleName(roleName);
			resourceComposite.setResourceDisplayName("会员等级：" + roleID + "-" + roleName);
			List<Map<String, String>> resourceList = permissionService.getPermissionListByRoleSN(roleSn);
			resourceComposite = resourceComposite.createPermissionTree(resourceList);
			permissionCache.put(roleID, resourceComposite);
		}
	}
	/**
	 * 获取权限菜单数据
	 * *
	 * @param parameter
	 * @return
	 */
	@Override
	public String getMenuTreeData(PermissionParameter parameter) {
		PermissionResource permissionResource = getPermissionResource(parameter);
		String treeJsonStr = permissionResource.createMenuTree(new JSONObject()).toString();
		//初始化resource缓存
		permissionResource.initResource();
		return treeJsonStr;
	}
	/**
	 * 获取菜单树
	 * *
	 * @param parameter
	 * @return
	 */
	private PermissionResource getPermissionResource(PermissionParameter parameter){
		String userSn = parameter.getUserSn();
		String grade = parameter.getGrade();
		String currentExbSN = parameter.getCurrentExbSn();
		if(StringUtils.isEmpty(grade)){
			throw new IllegalArgumentException("PermissionParameter-grade 不能为空");
		}
		boolean isAdmin = parameter.isAdmin();
		PermissionResource permissionResource = null;
		if(isAdmin){
			permissionResource = permissionCache.get(grade);
		} else {
			//如果是子帐户需要根据当前用户来过滤不可见的权限资源
			PermissionUser permissionUser = new PermissionUser();
			permissionUser.setUserSn(userSn);
			permissionUser.addPermissionRoleList(permissionCache.get(grade));
			List<Map<String, String>> assignResourceList = permissionService.getAuthMenuTreeList(userSn, currentExbSN);
			for(int i = 0, j = assignResourceList.size(); i < j; i ++){
				PermissionResource leafResource = new PermissionResourceLeaf();
				leafResource.setPermissionResourceData(leafResource, assignResourceList.get(i));
				permissionUser.addAssignList(leafResource);
			}
			permissionResource = permissionUser.filterUnAssignPermission();
		}
		return permissionResource;
	}

	@Override
	public boolean isAuthorizedResource(PermissionParameter permissionParameter) {
		boolean flag = false;
		PermissionResource permissionTree = getPermissionResource(permissionParameter);
		String resourceNodeCode = permissionParameter.getResourceNodeCode();
		if(StringUtils.isEmpty(resourceNodeCode)){
			throw new IllegalArgumentException("输入参数不合法，必须包含domain信息");
		}
		//菜单资源
		PermissionResource resource = permissionTree.findChild(resourceNodeCode);
		if(null != resource){
			flag = resource.isDisplayResource(resource);
		}
		//初始化resource缓存
		permissionTree.initResource();
		return flag;
	}

	@Override
	public List<PermissionResourceVo> getMenuNavigationList(PermissionParameter permissionParameter) {
		Map<String, Object> parameterMap = permissionParameter.getCustomParameter();
		String urlDomain = "";
		if(parameterMap.containsKey(PermissionConst.VO_DOMAIN)){
			urlDomain = TCUtil.sv(parameterMap.get(PermissionConst.VO_DOMAIN));
		} else {
			throw new IllegalArgumentException("输入参数不合法，必须包含domain信息");
		}
		String[] exceptResourceAry = null;
		if(parameterMap.containsKey(PermissionConst.EXCEPT_RESOURCE_CODE_ARY)){
			exceptResourceAry = (String[])parameterMap.get(PermissionConst.EXCEPT_RESOURCE_CODE_ARY);
		}
		List<PermissionResourceVo> list = new ArrayList<PermissionResourceVo>();
		PermissionResource permissionTree = getPermissionResource(permissionParameter);
		//根节点下菜单目录
		List<PermissionResource> rootMenuResourceList = permissionTree.getChildResourceList();
		if(null != rootMenuResourceList){
			for(int i = 0, j = rootMenuResourceList.size(); i < j; i ++){
				//菜单资源
				PermissionResource menuResource = rootMenuResourceList.get(i);
				if(null != exceptResourceAry && Arrays.asList(exceptResourceAry).contains(menuResource.getResourceCode())){
					continue;
				}
				List<PermissionResource> menuResourceList = menuResource.getChildResourceList();
				//选择菜单资源下第一个子菜单资源
                if (null != menuResourceList) {
                    list.add(chooseUrlFromResourceList(menuResourceList, urlDomain));
                }
			}
		}
		permissionTree.initResource();
		return list;
	}
	/**
	 * 从授权的菜单中选择第一个url
	 * *
	 * @param menuResourceList
	 */
	private PermissionResourceVo chooseUrlFromResourceList(List<PermissionResource> menuResourceList, String urlDomain){
		String url = "";
		String resourceDisplayName = "";
		if(null != menuResourceList && menuResourceList.size() > 0){
			for (PermissionResource permissionResource : menuResourceList) {
				if(permissionResource.isAuthorize() && PermissionResource.isDisplayResource(permissionResource)){
					url = permissionResource.getResourceUrl();
					resourceDisplayName = permissionResource.getParentResource().getResourceDisplayName();
					break;
				}
			}
		}
		PermissionResourceVo permissionResourceVo = new PermissionResourceVo();
		permissionResourceVo.setResourceDisplayName(resourceDisplayName);
		permissionResourceVo.setResourceUrl(HttpUtil.addUrlParameter(urlDomain + url, PermissionConst.WHERE_USE_MAIN_SITE));
		return permissionResourceVo;
	}

	@Override
	public List<PermissionResourceVo> getMenuFunctionList(PermissionParameter permissionParameter) {
		List<PermissionResourceVo> resourceVoList = new ArrayList<PermissionResourceVo>();
		PermissionResource permissionTree = getPermissionResource(permissionParameter);
		String resourceNodeCode = permissionParameter.getResourceNodeCode();
		if(StringUtils.isEmpty(resourceNodeCode)){
			throw new IllegalArgumentException("输入参数不合法，必须包含domain信息");
		}
		//菜单资源
		PermissionResource menuResource = permissionTree.findChild(resourceNodeCode);
		//菜单下功能节点
		if(null != menuResource){
			List<PermissionResource> childResourceList = menuResource.getChildResourceList();
			if(null != childResourceList){
				for(int i = 0, j = childResourceList.size(); i < j; i ++){
					PermissionResource childResource = childResourceList.get(i);
					if(PermissionConst.NODE_PERM_FLAG_FUNC.equals(childResource.getPermFlag()) && childResource.isDisplayResource(childResource)){
						resourceVoList.add(childResource.createResourceBaseInfo());
					}
				}
			}
		}
		//初始化resource缓存
		permissionTree.initResource();
		return resourceVoList;
	}

	@Override
	public PermissionResourceVo findResource(PermissionParameter permissionParameter) {
		PermissionResource permissionTree = getPermissionResource(permissionParameter);
		String resourceNodeUrl = permissionParameter.getResourceUrl();
		if(StringUtils.isEmpty(resourceNodeUrl)){
			throw new IllegalArgumentException("输入参数不合法，必须包含domain信息");
		}
		//菜单资源
		PermissionResource menuResource = permissionTree.findChildByUrl(resourceNodeUrl);
		PermissionResourceVo resourceBaseInfo = null;
		if(null != menuResource){
			resourceBaseInfo = menuResource.createResourceBaseInfo();
		}
		//初始化resource缓存
		permissionTree.initResource();
		return resourceBaseInfo;
	}

	@Override
	public boolean assign(PermissionParameter permissionParameter) {
		// TODO Auto-generated method stub
		return false;
	}
}
