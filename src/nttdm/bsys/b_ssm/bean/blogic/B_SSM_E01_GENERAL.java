/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : B_SSM
 * SERVICE NAME   : B_SSM_E01
 * FUNCTION       : Wtire data to file ISP_" + "yymmddhhmmss.csv
 * FILE NAME      : B_SSM_E01_ISP.java
 *
 * Copyright (C) 2011 NTTDATA Corporation
 * 
**********************************************************/
package nttdm.bsys.b_ssm.bean.blogic;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import jp.terasoluna.fw.dao.QueryDAO;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;

/**
 * BusinessLogic class.
 * @author NTT Data Vietnam	
 * Action export file B_SSM_E01
 * 
 */
public class B_SSM_E01_GENERAL {
	public QueryDAO queryDAO;
	public class FileImportExportException extends RuntimeException{
		private static final long serialVersionUID = 1L;
		public FileImportExportException(String errorMessage)
		{
			super(errorMessage);
		}
	}
	public QueryDAO getQueryDAO() {
		return queryDAO;
	}
	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}
	/**
	 * 
	 * @param param
	 * 
	 */	
	public void exportCSVFile(List<HashMap<String,Object>> resultObj,QueryDAO queryDAO,HttpServletResponse response){
		List<String[]> exportList = new ArrayList<String[]>();
		Date today = Calendar.getInstance().getTime();
		SimpleDateFormat sdf = new SimpleDateFormat(BillingSystemConstants.DATETIME_FORMAT_B_SSM_EXPORT);
		String date = sdf.format(today.getTime());
		HashMap<String, Object> name = new HashMap<String,Object>();
		Map<String, Object>[] name2=queryDAO.executeForMapArray("B_SSM_S01.GeneralFileName", name);
		String fileName=name2[0].toString().substring(7,name2[0].toString().length()-17);
		fileName = fileName + date + ".csv";
		// Add header to this file
		exportList.add(this.initHeadingLine(queryDAO));
		int rows = resultObj.size();
		for (int i = 0; i < rows; i++) { 
			HashMap<String, Object> rowObj = resultObj.get(i);
			String[] exportLine = new String[6];
			initData(exportLine);
			
			if (rowObj.get("rowNo") != null) exportLine[0] = CommonUtils.toString(rowObj.get("rowNo"));
			if (rowObj.get("CUSTOMERID") != null) exportLine[1] = CommonUtils.toString(rowObj.get("CUSTOMERID"));
			if (rowObj.get("CUSTOMERNAME") !=null) exportLine[2] = CommonUtils.toString(rowObj.get("CUSTOMERNAME"));
			if (rowObj.get("customerType") !=null) exportLine[3] = CommonUtils.getCodeMapListNameByValue("LIST_CUSTOMERTYPE", CommonUtils.toString(rowObj.get("customerType")));
			if (rowObj.get("SUBSCRIPTIONID") !=null) exportLine[4]= CommonUtils.toString(rowObj.get("SUBSCRIPTIONID")); 
			if (rowObj.get("PLAN_STATUS") != null) exportLine[5] =  CommonUtils.getCodeMapListNameByValue("LIST_PLANSTATUS", CommonUtils.toString(rowObj.get("PLAN_STATUS")));	
			exportList.add(exportLine);
		}
		// Export to csv file
		DownloadFile exportFile = new DownloadFile();
		exportFile.exportFile(exportList, fileName, response);
	}
	private String[] initHeadingLine(QueryDAO queryDAO){		
		HashMap<String, Object> listheader = new HashMap<String,Object>();
		Map<String, Object>[] headingLine=queryDAO.executeForMapArray("B_SSM_S01.GeneralHeader", listheader);
		String[] header2 = new String[headingLine.length];
		for(int i=0;i<headingLine.length;i++){
			header2[i]=headingLine[i].toString().substring(7,headingLine[i].toString().length()-1 );
		}
		return header2;
	}
	private String[] initData(String[] exportLine){
		for (int i = 0; i < exportLine.length; i++) {
			exportLine[i] = "";
		}
		return exportLine;
	}
}