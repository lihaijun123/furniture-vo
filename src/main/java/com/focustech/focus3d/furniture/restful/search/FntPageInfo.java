package com.focustech.focus3d.furniture.restful.search;

import java.util.List;

import net.sf.json.JSONObject;

/**
 * 
 * *
 * @author lihaijun
 *
 */
public class FntPageInfo {
	private int pageNow;//当前页
	private int pageSize = 5;//每页条数
	private List records;//分页记录数
	private int recordTotal;//总记录数
	private int pageTotal;//一共几页

	public int getPageNow() {
		return pageNow;
	}

	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List getRecords() {
		return records;
	}

	public void setRecords(List records) {
		this.records = records;
	}

	public int getRecordTotal() {
		return recordTotal;
	}

	public void setRecordTotal(int recordTotal) {
		this.recordTotal = recordTotal;
	}

	public int getPageTotal() {
		return pageTotal;
	}

	public void setPageTotal(int pageTotal) {
		this.pageTotal = pageTotal;
	}

	public JSONObject addPageInfo(JSONObject rvJo){
		rvJo.put("pageNow", pageNow <= 0 ? 1 : pageNow);
		rvJo.put("pageSize", getRecords() != null ? getRecords().size() : 0);
		rvJo.put("pageTotal", pageNow <= 0 ? 1 : (recordTotal + pageSize - 1) / pageSize);
		return rvJo;
	}
}
