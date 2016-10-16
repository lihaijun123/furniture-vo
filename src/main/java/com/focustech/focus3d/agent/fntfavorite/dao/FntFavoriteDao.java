package com.focustech.focus3d.agent.fntfavorite.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.focustech.focus3d.agent.dao.CommonDao;
import com.focustech.focus3d.agent.model.FntFavoriteModel;
import com.focustech.focus3d.agent.model.ibator.FntFavoriteCriteria;
/**
 * 
 * *
 * @author lihaijun
 *
 */
@Repository
public class FntFavoriteDao extends CommonDao {
	/**
	 * 
	 * *
	 * @param userId
	 * @param targetId
	 * @param targetType
	 * @return
	 */
	public FntFavoriteModel select(long userId, long targetId, int targetType) {
		FntFavoriteCriteria criteria = new FntFavoriteCriteria();
		criteria.createCriteria().andUserIdEqualTo(userId).andTargetIdEqualTo(targetId).andTargetTypeEqualTo(targetType);
		return selectFirstByExample(criteria, FntFavoriteModel.class);
	}

	public List<FntFavoriteModel> list(long userId, int targetType) {
		FntFavoriteCriteria criteria = new FntFavoriteCriteria();
		criteria.createCriteria().andUserIdEqualTo(userId).andTargetTypeEqualTo(targetType);
		return selectByCriteria(criteria, FntFavoriteModel.class);
	}

}
