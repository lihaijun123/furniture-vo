package com.focustech.focus3d.agent.test.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.focustech.focus3d.agent.dao.CommonDao;
import com.focustech.focus3d.agent.message.dao.BizProdCaseDAO;
import com.focustech.focus3d.agent.service.impl.CommonServiceTemplate;
import com.focustech.focus3d.agent.test.service.TestService;

/**
 *
 * *
 * @author lihaijun
 *
 */
@Service
@Transactional
public class TestServiceImpl extends CommonServiceTemplate implements TestService {
	@Autowired
	private BizProdCaseDAO bizProdCaseDAO;

	public BizProdCaseDAO getBizProdCaseDAO() {
		return bizProdCaseDAO;
	}

	public void setBizProdCaseDAO(BizProdCaseDAO bizProdCaseDAO) {
		this.bizProdCaseDAO = bizProdCaseDAO;
	}

	@Override
	public CommonDao getDao() {
		return bizProdCaseDAO;
	}

}
