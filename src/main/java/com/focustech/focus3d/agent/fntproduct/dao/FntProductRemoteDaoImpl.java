package com.focustech.focus3d.agent.fntproduct.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;

import com.focustech.cief.filemanage.common.utils.FileManageUtil;
import com.focustech.common.utils.ListUtils;
import com.focustech.common.utils.StringUtils;
import com.focustech.common.utils.TCUtil;
import com.focustech.focus3d.agent.dao.CommonDao;
import com.focustech.focus3d.agent.fntproduct.controller.FntProductSearch;
import com.focustech.focus3d.agent.model.FntProductModel;
import com.focustech.focus3d.agent.model.ibator.FntProductCriteria;
import com.focustech.focus3d.furniture.rpc.FntRpc;
/**
 * 
 * *
 * @author lihaijun
 *
 */
@Repository
public class FntProductRemoteDaoImpl extends CommonDao implements IFntProductDao{
	private static final Logger log = LoggerFactory.getLogger(FntProductRemoteDaoImpl.class);
	private FntRpc fntRpc = new FntRpc();
	/**
	 * 
	 * *
	 * @param houseSearch
	 * @return
	 */
	public List<FntProductModel> search(FntProductSearch productSearch) {
		List<FntProductModel> list = createCondition(productSearch, 0);
		if(ListUtils.isNotEmpty(list)){
			int searchTotal = searchTotal(productSearch);
			productSearch.setRecords(list);
			productSearch.setRecordTotal(searchTotal);
		}
		return list;
	}
	
	private List<FntProductModel> createCondition(FntProductSearch productSearch, int searchType){
		List<NameValuePair> qparams = new ArrayList<NameValuePair>();
		qparams.add(new BasicNameValuePair("goodsname", productSearch.getCategoryCode()));
		String result = fntRpc.httpRequest("/service/product/bygoodname.htm", qparams, HttpMethod.POST);
		List<FntProductModel> returnList = new ArrayList<FntProductModel>();
		if(StringUtils.isNotEmpty(result)){
			JSONArray jary = JSONArray.fromObject(result);
			if(!jary.isEmpty()){
				List<FntProductModel> dbList = list();
				for(int i = 0; i < jary.size(); i ++){
					JSONObject item = (JSONObject)jary.get(i);
					String goods_photo = fntRpc.getProtocal() + "/" + item.getString("goods_photo");
					String goods_class_name = item.getString("goods_class_name");
					String goods_price = item.getString("goods_price");
					String goods_id = item.getString("goods_id");
					String goods_inventory = item.getString("goods_inventory");
					String goods_name = item.getString("goods_name");
					String goods_class_id = item.getString("goods_class_id");
					String goods_serial = item.getString("goods_serial");
					String goods_brand_id = TCUtil.svjo(item, "goods_brand_id");
					String goods_brand_name = TCUtil.svjo(item, "goods_brand_name");
					FntProductModel fntProductModel = findById(dbList, goods_id);
					if(fntProductModel != null){
						fntProductModel.setPicFileUrl(goods_photo);
						fntProductModel.setName(goods_name);
						fntProductModel.setCategoryName(goods_class_name);
						fntProductModel.setCategoryCode(goods_class_id);
						fntProductModel.setPrice(new BigDecimal(goods_price));
						fntProductModel.setBrand(goods_brand_name);
						fntProductModel.setBrandId(goods_brand_id);
						fntProductModel.setInventory(goods_inventory);
						fntProductModel.setSerial(goods_serial);
						returnList.add(fntProductModel);
					}
				}
			}
		}
		return returnList;
	}
	/**
	 * *
	 * @return
	 */
	private List<FntProductModel> list(){
		FntProductCriteria criteria = new FntProductCriteria();
		List<FntProductModel> list = selectByCriteria(criteria, FntProductModel.class);
		return list;
	}
	/**
	 * *
	 * @param list
	 * @param productId
	 * @return
	 */
	private FntProductModel findById(List<FntProductModel> list, String productId){
		for (FntProductModel fntProductModel : list) {
			String id = fntProductModel.getProductId();
			//家具有模型数据
			if(StringUtils.isNotEmpty(productId) && productId.equals(id)){
				Long modelFileSn = fntProductModel.getModelFileSn();
				if(modelFileSn != null){
					fntProductModel.setModelFileUrl(TCUtil.sv(FileManageUtil.getFileURL(modelFileSn)));
				} else {
					//无模型数据，需要过滤掉
					return null;
				}
				return fntProductModel;
			}
		}
		//画，地板没有模型数据
		return new FntProductModel();
	}
	/**
	 * 
	 * *
	 * @param houseSearch
	 * @return
	 */
	public int searchTotal(FntProductSearch productSearch) {
		
		return 0;
	}
}
