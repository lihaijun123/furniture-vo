package com.focustech.focus3d.agent.product.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.focustech.common.utils.TCUtil;
import com.focustech.focus3d.agent.dao.CommonDao;
import com.focustech.focus3d.agent.model.AgentProduct;
import com.focustech.focus3d.agent.model.ibator.BizAgentProductCriteria;
/**
 *
 * *
 * @author lihaijun
 *
 */
@Repository
public class AgentProductDao extends CommonDao {
	/**
	 *
	 * *
	 * @param status
	 * @return
	 */
	public List<AgentProduct> getList(int status) {
		BizAgentProductCriteria criteria = new BizAgentProductCriteria();
		criteria.createCriteria().andStatusEqualTo(status);
		return selectByCriteria(criteria, AgentProduct.class);
	}

	public List<String> getUniqProdNameList(int status) {
		List<String> list = new ArrayList<String>();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("status", status);
			List queryForList = getSqlMapClient().queryForList("agent_product.getUniqProdNameList", map);
			if(queryForList != null && !queryForList.isEmpty()){
				for (Object object : queryForList) {
					if(object instanceof Map){
						Map<String, Object> mp = (Map<String, Object>)object;
						list.add(TCUtil.sv(mp.get("NAME")));
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<AgentProduct> getList(int status, String name) {
		BizAgentProductCriteria criteria = new BizAgentProductCriteria();
		criteria.createCriteria().andStatusEqualTo(status).andNameEqualTo(name);
		return selectByCriteria(criteria, AgentProduct.class);
	}

}
