package com.focustech.focus3d.agent.fntfavorite.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.focustech.focus3d.agent.dao.CommonDao;
import com.focustech.focus3d.agent.fntfavorite.dao.FntFavoriteDao;
import com.focustech.focus3d.agent.fntfavorite.service.FntFavoriteService;
import com.focustech.focus3d.agent.model.FntFavoriteModel;
import com.focustech.focus3d.agent.service.impl.CommonServiceTemplate;
/**
 * 
 * *
 * @author lihaijun
 *
 */
@Service
@Transactional
public class FntFavoriteServiceImpl extends CommonServiceTemplate<FntFavoriteModel> implements FntFavoriteService<FntFavoriteModel> {
	@Autowired
	private FntFavoriteDao favoriteDao;
	
	@Override
	public CommonDao getDao() {
		return favoriteDao;
	}

	@Override
	public FntFavoriteModel select(long userId, long targetId, int targetType) {
		return favoriteDao.select(userId, targetId, targetType);
	}

	@Override
	public List<FntFavoriteModel> list(long userId, int targetType) {
		return favoriteDao.list(userId, targetType);
	}

}
