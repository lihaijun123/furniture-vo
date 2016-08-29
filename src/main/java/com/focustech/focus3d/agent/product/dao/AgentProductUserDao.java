package com.focustech.focus3d.agent.product.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.focustech.focus3d.agent.dao.CommonDao;
import com.focustech.focus3d.agent.model.AgentUserProduct;
import com.focustech.focus3d.agent.model.ibator.BizAgentUserProductCriteria;
/**
 *
 * *
 * @author lihaijun
 *
 */
@Repository
public class AgentProductUserDao extends CommonDao{
	/**
	 *
	 * *
	 * @param userId
	 * @return
	 */
	public List<AgentUserProduct> getListByUserId(long userId){
		BizAgentUserProductCriteria criteria = new BizAgentUserProductCriteria();
		criteria.createCriteria().andUserIdEqualTo(userId);
		return selectByCriteria(criteria, AgentUserProduct.class);
	}
}
