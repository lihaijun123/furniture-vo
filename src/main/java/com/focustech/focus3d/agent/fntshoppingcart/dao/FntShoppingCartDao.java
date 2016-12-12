package com.focustech.focus3d.agent.fntshoppingcart.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.focustech.focus3d.agent.dao.CommonDao;
import com.focustech.focus3d.agent.model.FntShoppingCartModel;
import com.focustech.focus3d.agent.model.ibator.FntShoppingCartCriteria;
/**
 * 
 * *
 * @author lihaijun
 *
 */
@Repository
public class FntShoppingCartDao extends CommonDao {
	/***
	 * 
	 * @param userId
	 * @return
	 */
	public List<FntShoppingCartModel> listByUser(Long userId) {
		FntShoppingCartCriteria cartCriteria = new FntShoppingCartCriteria();
		cartCriteria.createCriteria().andUserIdEqualTo(userId);
		return selectByCriteria(cartCriteria, FntShoppingCartModel.class);
	}

	public void deleteBySn(long sn) {
		FntShoppingCartCriteria cartCriteria = new FntShoppingCartCriteria();
		cartCriteria.createCriteria().andSnEqualTo(sn);
		deleteByCriteria(cartCriteria);
	}

}
