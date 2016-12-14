package com.focustech.focus3d.agent.fntshoppingcart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.focustech.focus3d.agent.dao.CommonDao;
import com.focustech.focus3d.agent.filter.RequestThreadLocal;
import com.focustech.focus3d.agent.fntproduct.dao.FntProductDao;
import com.focustech.focus3d.agent.fntshoppingcart.dao.FntShoppingCartDao;
import com.focustech.focus3d.agent.fntshoppingcart.service.FntShoppingCartService;
import com.focustech.focus3d.agent.model.AgentLogin;
import com.focustech.focus3d.agent.model.FntProductModel;
import com.focustech.focus3d.agent.model.FntShoppingCartModel;
import com.focustech.focus3d.agent.model.ibator.FntShoppingCartCriteria;
import com.focustech.focus3d.agent.service.impl.CommonServiceTemplate;
/**
 * 
 * *
 * @author lihaijun
 *
 */
@Service
public class FntShoppingCartServiceImpl extends CommonServiceTemplate<FntShoppingCartModel> implements FntShoppingCartService<FntShoppingCartModel>{
	
	@Autowired
	private FntShoppingCartDao shoppingCartDao;
	@Autowired
	private FntProductDao productDao;

	@Override
	public CommonDao getDao() {
		return shoppingCartDao;
	}

	@Override
	public List<FntShoppingCartModel> listByUser() {
		AgentLogin loginInfo = RequestThreadLocal.getLoginInfo();
		Long userId = loginInfo.getUserId();
		List<FntShoppingCartModel> list = shoppingCartDao.listByUser(userId);
		for (FntShoppingCartModel fntShoppingCartModel : list) {
			Long furnitureId = fntShoppingCartModel.getFurnitureId();
			FntProductModel product = productDao.selectByKey(furnitureId, FntProductModel.class);
			fntShoppingCartModel.setProduct(product);
		}
		return list;
	}

	@Override
	public void deleteBySn(long sn) {
		shoppingCartDao.deleteBySn(sn);
	}

	@Override
	public List<FntShoppingCartModel> listAll() {
		FntShoppingCartCriteria cartCriteria = new FntShoppingCartCriteria();
		List<FntShoppingCartModel> list = shoppingCartDao.selectByCriteria(cartCriteria, FntShoppingCartModel.class);
		for (FntShoppingCartModel fntShoppingCartModel : list) {
			Long furnitureId = fntShoppingCartModel.getFurnitureId();
			FntProductModel product = productDao.selectByKey(furnitureId, FntProductModel.class);
			if(product == null){
				list.remove(product);
				continue;
			}
			fntShoppingCartModel.setProduct(product);
		}
		return list;
	}
	
}
