package com.focustech.focus3d.furniture.restful.common;

import java.io.ByteArrayOutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.focustech.common.utils.DateUtils;
import com.focustech.common.utils.ListUtils;
import com.focustech.focus3d.agent.common.spring.RestServiceBeanPostProcessor;

/**
 * 
 * *
 * @author lihaijun
 *
 */
@RestMethodDesc("导出服务")
@Service
@Path("/rest/export")
@Produces(MediaType.APPLICATION_OCTET_STREAM)
public class ExportServiceList {
	@Value("${rest.service.protocal}")
	private String restServiceProtocal;
	private static final String[] xlsColumnAry = new String[]{"序号", "名称", "服务名", "请求参数", "URL", "返回结果", "备注"};
	@RestMethodDesc("导出服务列表")
	@GET
	@Path("xls")
	public Response export() throws Exception{
		Map<String, RestServiceBean> cache = RestServiceBeanPostProcessor.getCache();
		List<RestServiceBean> list = new ArrayList<RestServiceBean>();
		for(Map.Entry<String, RestServiceBean> serviceBean : cache.entrySet()){
			RestServiceBean value = serviceBean.getValue();
			list.add(value);
		}
		byte[] resultSetToExcel = new byte[0];
		String xlsFileName = "服务列表_" + DateUtils.getCurDate("yyyyMMddHHmmss") + ".xls";
		if(ListUtils.isNotEmpty(list)){
			resultSetToExcel = resultSetToExcel(xlsFileName, list);
		}
		return Response.ok(resultSetToExcel, "application/octet-stream")
			   .header("Content-Disposition", "attachment; filename=" + URLEncoder.encode(xlsFileName, "UTF-8"))
			   .build();
	}
	
	/**
	 *
	 * *
	 * @param filePath
	 * @param sheetName
	 * @param list
	 * @throws Exception
	 */
	private byte[] resultSetToExcel(String sheetName, List<RestServiceBean> restServiceBeans) throws Exception {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		sheet.setColumnWidth((short)1, (short) (10 * 256));
		sheet.setColumnWidth((short)2, (short) (30 * 256));
		sheet.setColumnWidth((short)3, (short) (30 * 256));
		sheet.setColumnWidth((short)4, (short) (30 * 256));
		sheet.setColumnWidth((short)5, (short) (30 * 256));
		workbook.setSheetName(0, sheetName, HSSFWorkbook.ENCODING_UTF_16);
		HSSFRow row = sheet.createRow((short) 0);
		row.setHeight((short) (2 * 256));
		HSSFCell cell = null;
		HSSFCellStyle cellStyle = null;
		/*HSSFFont font = workbook.createFont();
		font.setColor(HSSFFont.COLOR_NORMAL);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); */

		int nColumn = xlsColumnAry.length;
		// 写入各个字段的名称
		for (int i = 0; i < nColumn; i++) {
			cell = row.createCell((short) i);
			cellStyle = cell.getCellStyle();
			//cellStyle.setFont(font);
			cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(xlsColumnAry[i]);
		}
		int index = 0;
		for(RestServiceBean restServiceBean : restServiceBeans){
			// 写入各条记录，每条记录对应Excel中的一行
			List<RestServiceMethod> list = restServiceBean.getServices();
			for (int j = 1; j <= list.size(); j++) {
				++ index;
				RestServiceMethod serviceMethod = list.get(j - 1);
				//1
				row = sheet.createRow((short) index);
				row.setHeight((short) (1 * 256));
				cell = row.createCell((short) 0);
				cellStyle = cell.getCellStyle();
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellValue(index);
				//2
				cell = row.createCell((short) 1);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellValue("[" + restServiceBean.getDesc() + "]" + serviceMethod.getDesc());
				//3
				cell = row.createCell((short) 2);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				String serviceUrl = restServiceBean.getPath() + "/" + serviceMethod.getPath();
				cell.setCellValue(serviceUrl + "（" + serviceMethod.getHttpMethod() + "）");
				//4
				cell = row.createCell((short) 3);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				List<String> requestParameters = serviceMethod.getRequestParameters();
				String parames = "";
				if(ListUtils.isNotEmpty(requestParameters)){
					for (String p : requestParameters) {
						parames += (p + ",");
					}
				}
				cell.setCellValue(parames);
				//5
				cell = row.createCell((short) 4);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				StringBuffer reqUrl = new StringBuffer();
				reqUrl.append(restServiceProtocal + serviceUrl);
				cell.setCellValue(reqUrl.toString());
				//6
				cell = row.createCell((short) 5);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellValue("");
				//7
				cell = row.createCell((short) 6);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellValue("");
			}
		}
		ByteArrayOutputStream fOut = new ByteArrayOutputStream();
		workbook.write(fOut);
		fOut.flush();
		fOut.close();
		return fOut.toByteArray();
	}

}
