package com.focustech.focus3d.agent.order.dao;

import org.springframework.stereotype.Repository;

import com.focustech.focus3d.agent.dao.CommonDao;
import com.focustech.focus3d.agent.model.ReceiveAddress;
import com.focustech.focus3d.agent.model.ibator.BizAgentReceiveAddressCriteria;
/**
 *
 * *
 * @author lihaijun
 *
 */
@Repository
public class ReceiveAddressDao extends CommonDao {
	/**
	 *
	 * *
	 * @param userId
	 */
	public void deleteByUserId(long userId){
		BizAgentReceiveAddressCriteria criteria = new BizAgentReceiveAddressCriteria();
		criteria.createCriteria().andUserIdEqualTo(userId);
		deleteByCriteria(criteria);
	}
	/**
	 *
	 * *
	 * @param userId
	 * @return
	 */
	public ReceiveAddress getByUserId(long userId) {
		BizAgentReceiveAddressCriteria criteria = new BizAgentReceiveAddressCriteria();
		criteria.createCriteria().andUserIdEqualTo(userId);
		return selectFirstByExample(criteria, ReceiveAddress.class);
	}

}
