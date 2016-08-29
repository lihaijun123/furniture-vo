package com.focustech.focus3d.agent.order.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.focustech.focus3d.agent.dao.CommonDao;
import com.focustech.focus3d.agent.model.AgentOrderItem;
import com.focustech.focus3d.agent.model.AgentProduct;
import com.focustech.focus3d.agent.order.dao.AgentOrderItemDao;
import com.focustech.focus3d.agent.order.service.AgentOrderItemService;
import com.focustech.focus3d.agent.product.dao.AgentProductDao;
import com.focustech.focus3d.agent.service.impl.CommonServiceTemplate;
/**
 *
 * *
 * @author lihaijun
 *
 */
@Service
@Transactional
public class AgentOrderItemServiceImpl extends CommonServiceTemplate<AgentOrderItem> implements AgentOrderItemService<AgentOrderItem> {
	@Autowired
	private AgentOrderItemDao agentOrderItemDao;
	@Autowired
	private AgentProductDao productDao;
	@Override
	public CommonDao getDao() {
		return agentOrderItemDao;
	}
	@Override
	public List<AgentOrderItem> getByOrderId(long orderId) {
		List<AgentOrderItem> orderItems = agentOrderItemDao.getByOrderId(orderId);
		for (AgentOrderItem agentOrderItem : orderItems) {
			Long productId = agentOrderItem.getProductId();
			if(productId != null && productId > 0){
				AgentProduct product = productDao.selectByKey(productId, AgentProduct.class);
				agentOrderItem.setProduct(product);
			}
		}
		return orderItems;
	}

}
