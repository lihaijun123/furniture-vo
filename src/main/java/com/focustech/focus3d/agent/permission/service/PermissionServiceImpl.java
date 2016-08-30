package com.focustech.focus3d.agent.permission.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.focustech.common.utils.TCUtil;
import com.focustech.focus3d.agent.permission.constant.PermissionConst;
import com.focustech.focus3d.agent.permission.dao.MenuTreeDao;
/**
 * 菜单树业务类
 * *
 * @author lihaijun
 *
 */
@Service
@Transactional
public class PermissionServiceImpl implements PermissionService{
	@Autowired
	private MenuTreeDao menuTreeDao;
	private static final long serialVersionUID = 1L;

	public List<Map<String, String>> getMenuTreeList(){
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("permFlag", PermissionConst.NODE_PERM_FLAG_MENU + "," + PermissionConst.NODE_PERM_FLAG_FUNC);
		return menuTreeDao.selectMenuTreeList(paraMap);
	}

	@Override
	public List<Map<String, String>> getParentNodeList() {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("parentSN", TCUtil.lv(PermissionConst.PARENT_NODE));
		return menuTreeDao.selectMenuTreeList(paraMap);
	}

	@Override
	public List<Map<String, String>> getRoleList() {
		return menuTreeDao.selectRoleList(null);
	}

	@Override
	public List<Map<String, String>> getMenuTreeListByRoleSN(String roleSN) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleSN", TCUtil.lv(roleSN));
		map.put("permFlag", TCUtil.lv(PermissionConst.NODE_PERM_FLAG_MENU));
		return menuTreeDao.selectMenuTreeByRoleIdList(map);
	}

	@Override
	public List<Map<String, String>> getAuthMenuTreeList(String userSN, String exbSN) {
		return menuTreeDao.selectAuthMenuTreeList(userSN, exbSN);
	}

	@Override
	public List<Map<String, String>> getFuncNodeListByParentSn(String parentSn) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("parentSN", TCUtil.lv(parentSn));
		paraMap.put("permFlag", TCUtil.lv(PermissionConst.NODE_PERM_FLAG_FUNC));
		return menuTreeDao.selectMenuTreeList(paraMap);
	}

	@Override
	public List<Map<String, String>> getNodeList(String parentSn, String permFlag) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("parentSN", TCUtil.lv(parentSn));
		paraMap.put("permFlag", TCUtil.lv(permFlag));
		return menuTreeDao.selectMenuTreeList(paraMap);
	}

	@Override
	public List<Map<String, String>> selectAllNodeList(String parentSn, String permFlag) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("parentSN", parentSn);
		paraMap.put("permFlag", permFlag);
		return menuTreeDao.selectAllNodeList(paraMap);
	}

	@Override
	public List<Map<String, String>> getMenuTreeParentNodeList(String roleSN) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("permFlag", TCUtil.lv(PermissionConst.NODE_PERM_FLAG_MENU));
		paraMap.put("parentSN", TCUtil.lv(PermissionConst.PARENT_NODE));
		paraMap.put("roleSN", TCUtil.lv(roleSN));
		return menuTreeDao.selectMenuTreeByRoleIdList(paraMap);
	}

	@Override
	public List<Map<String, String>> selectParentNodeWithFirstChildUrlList(String roleID) {
		return menuTreeDao.selectParentNodeWithFirstChildUrlList(roleID);
	}

	@Override
	public List<Map<String, String>> getMenuParentNodeList() {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("parentSN", TCUtil.lv(PermissionConst.PARENT_NODE));
		paraMap.put("permFlag", TCUtil.lv(PermissionConst.NODE_PERM_FLAG_MENU));
		return menuTreeDao.selectMenuTreeList(paraMap);
	}

	@Override
	public List<Map<String, String>> getPermissionListByRoleSN(String roleSN) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleSN", TCUtil.lv(roleSN));
		map.put("permFlag", PermissionConst.NODE_PERM_FLAG_MENU + "," + PermissionConst.NODE_PERM_FLAG_FUNC);
		return menuTreeDao.selectMenuTreeByRoleIdList(map);
	}
}
