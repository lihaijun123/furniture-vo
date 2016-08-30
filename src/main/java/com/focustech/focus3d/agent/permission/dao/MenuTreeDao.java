package com.focustech.focus3d.agent.permission.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.focustech.cief.ibatis.IbatisWrapper;
import com.focustech.common.utils.TCUtil;
/**
 * 
 * *
 * @author lihaijun
 *
 */
@Repository
public class MenuTreeDao extends IbatisWrapper{
	public final static String KEY = "MenuTreeModel";
	/**
	 * 获取菜单树全部数据
	 * *
	 * @param sceneSelfSn
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, String>> selectMenuTreeList(Map<String, Object> map){
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		try {
			list = getSqlMapClient().queryForList(KEY + ".selectMenuTreeList", map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 *
	 * *
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, String>> selectAllNodeList(Map<String, Object> map){
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		try {
			list = getSqlMapClient().queryForList(KEY + ".selectAllNodeList", map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	
	}

	/**
	 * 获取菜单树全部数据
	 * *
	 * @param sceneSelfSn
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, String>> selectRoleList(Map<String, Object> map){
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		try {
			list = getSqlMapClient().queryForList(KEY + ".selectRoleList", map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	
	}

	/**
	 * 获取菜单树全部数据
	 * *
	 * @param sceneSelfSn
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, String>> selectMenuTreeByRoleIdList(Map<String, Object> map){
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		try {
			list = getSqlMapClient().queryForList(KEY + ".selectMenuTreeByRoleIdList", map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	
	}
	/**
	 * 
	 * *
	 * @param userSN
	 * @param exbSN
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, String>> selectAuthMenuTreeList(String userSN, String exbSN) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userSN", TCUtil.lv(userSN));
		map.put("exbSN", TCUtil.lv(exbSN));
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		try {
			list = getSqlMapClient().queryForList(KEY + ".selectAuthMenuTreeList", map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 获取父节点名称和子节点的名称和url
	 * *
	 * @param roleID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, String>> selectParentNodeWithFirstChildUrlList(String roleID){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleID", roleID);
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		try {
			list = getSqlMapClient().queryForList(KEY + ".selectParentNodeWithFirstChildUrlList", map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}


}
