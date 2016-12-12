package com.focustech.focus3d.agent.model;

import com.focustech.cief.ibatis.domain.BaseEntity;
import com.focustech.focus3d.agent.model.ibator.FntShoppingCart;
import com.focustech.focus3d.agent.model.ibator.FntShoppingCartCriteria;
/**
 * *
 * @author lihaijun
 *
 */
public class FntShoppingCartModel extends FntShoppingCart<FntShoppingCartModel, FntShoppingCartCriteria> implements BaseEntity, CommonModel{
	private FntProductModel product;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public FntProductModel getProduct() {
		return product;
	}
	public void setProduct(FntProductModel product) {
		this.product = product;
	}
	
}
