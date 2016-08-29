package com.focustech.focus3d.agent.product.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.focustech.common.utils.EncryptUtil;
import com.focustech.focus3d.agent.dao.CommonDao;
import com.focustech.focus3d.agent.model.AgentProduct;
import com.focustech.focus3d.agent.model.AgentUserProduct;
import com.focustech.focus3d.agent.product.dao.AgentProductDao;
import com.focustech.focus3d.agent.product.dao.AgentProductUserDao;
import com.focustech.focus3d.agent.product.service.AgentProductService;
import com.focustech.focus3d.agent.service.impl.CommonServiceTemplate;
/**
 *
 * *
 * @author lihaijun
 *
 */
@Service
@Transactional
public class AgentProductServiceImpl extends CommonServiceTemplate<AgentProduct> implements AgentProductService<AgentProduct> {

	@Autowired
	private AgentProductDao agentProductDao;
	@Autowired
	private AgentProductUserDao productUserDao;


	@Override
	public CommonDao getDao() {
		return agentProductDao;
	}


	@Override
	public List<AgentProduct> getList(int status) {
		List<AgentProduct> list = agentProductDao.getList(status);
		for (AgentProduct agentProduct : list) {
			agentProduct.setEncryptSn(EncryptUtil.encode(agentProduct.getSn()));
		}
		return list;
	}


	@Override
	public List<AgentProduct> getListByUserId(long userId) {
		List<AgentProduct> products = new ArrayList<AgentProduct>();
		List<AgentUserProduct> userProducts = productUserDao.getListByUserId(userId);
		for (AgentUserProduct agentUserProduct : userProducts) {
			Long productId = agentUserProduct.getProductId();
			AgentProduct product = agentProductDao.selectByKey(productId, AgentProduct.class);
			product.setEncryptSn(EncryptUtil.encode(product.getSn()));
			products.add(product);
		}
		return products;
	}


	@Override
	public List<String> getUniqProdNameList(int status) {
		return agentProductDao.getUniqProdNameList(status);
	}

	@Override
	public List<AgentProduct> getList(int status, String name) {

		return agentProductDao.getList(status, name);
	}

}
