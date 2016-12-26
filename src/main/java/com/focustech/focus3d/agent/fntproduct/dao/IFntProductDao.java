package com.focustech.focus3d.agent.fntproduct.dao;

import java.util.List;

import com.focustech.focus3d.agent.fntproduct.controller.FntProductSearch;
import com.focustech.focus3d.agent.model.FntProductModel;

public interface IFntProductDao {

	public List<FntProductModel> search(FntProductSearch productSearch);
	
	public int searchTotal(FntProductSearch productSearch);
	
}
