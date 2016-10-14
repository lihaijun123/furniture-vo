package com.focustech.focus3d.agent.fntcase.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.focustech.focus3d.agent.dao.CommonDao;
import com.focustech.focus3d.agent.fntcase.dao.FntCaseDao;
import com.focustech.focus3d.agent.fntcase.service.FntCaseService;
import com.focustech.focus3d.agent.model.FntCaseModel;
import com.focustech.focus3d.agent.model.ibator.FntCaseCriteria;
import com.focustech.focus3d.agent.service.impl.CommonServiceTemplate;
import com.focustech.focus3d.furniture.restful.search.FntCaseSearch;
/**
 * 
 * *
 * @author lihaijun
 *
 */
@Service
@Transactional
public class FntCaseServiceImpl extends CommonServiceTemplate<FntCaseModel> implements FntCaseService<FntCaseModel> {
	@Autowired
	private FntCaseDao fntCaseDao;
	@Override
	public CommonDao getDao() {
		return fntCaseDao;
	}
	@Override
	public int delete(long userId, long houseId) {
		FntCaseCriteria caseCriteria = new FntCaseCriteria();
		caseCriteria.createCriteria().andUserIdEqualTo(userId).andHouseIdEqualTo(houseId);
		return deleteByCriteria(caseCriteria);
	}
	
	@Override
	public FntCaseModel select(long userId, long houseId) {
		FntCaseCriteria caseCriteria = new FntCaseCriteria();
		caseCriteria.createCriteria().andUserIdEqualTo(userId).andHouseIdEqualTo(houseId);
		return selectFirstByExample(caseCriteria, FntCaseModel.class);
	}
	
	@Override
	public List<FntCaseModel> listByUser(long userId) {
		FntCaseCriteria caseCriteria = new FntCaseCriteria();
		caseCriteria.createCriteria().andUserIdEqualTo(userId);
		return selectByCriteriaWithBlob(caseCriteria, FntCaseModel.class);
	}
	
	@Override
	public List<FntCaseModel> listByHouse(long houseId) {
		FntCaseCriteria caseCriteria = new FntCaseCriteria();
		caseCriteria.createCriteria().andHouseIdEqualTo(houseId);
		return selectByCriteriaWithBlob(caseCriteria, FntCaseModel.class);
	}
	@Override
	public List<FntCaseModel> listAll() {
		FntCaseCriteria caseCriteria = new FntCaseCriteria();
		return selectByCriteriaWithBlob(caseCriteria, FntCaseModel.class);
	}
	@Override
	public List<FntCaseModel> search(FntCaseSearch caseSearch) {
		return fntCaseDao.search(caseSearch);
	}
	@Override
	public int searchTotal(FntCaseSearch caseSearch) {
		return fntCaseDao.searchTotal(caseSearch);
	}
}
