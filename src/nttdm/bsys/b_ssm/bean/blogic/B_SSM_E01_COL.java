/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : B_SSM
 * SERVICE NAME   : B_SSM_E01
 * FUNCTION       : Write data to export file Colocation_" + "yymmddhhmmss.csv
 * FILE NAME      : B_SSM_E01_COL.java
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
public class B_SSM_E01_COL {
	
	public QueryDAO queryDAO;
	public QueryDAO getQueryDAO() {
		return queryDAO;
	}
	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}
	public class FileImportExportException extends RuntimeException{
		private static final long serialVersionUID = 1L;
		public FileImportExportException(String errorMessage)
		{
			super(errorMessage);
		}
	}
	/**
	 * 
	 * @param param
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void exportCSVFile(List<HashMap<String,Object>> resultObj,int detail,QueryDAO qureyDAO,HttpServletResponse response){
		List<String[]> exportList = new ArrayList<String[]>();
		Date today = Calendar.getInstance().getTime();
		SimpleDateFormat sdf = new SimpleDateFormat(BillingSystemConstants.DATETIME_FORMAT_B_SSM_EXPORT);
		String date = sdf.format(today.getTime());
		HashMap<String, Object> name = new HashMap<String,Object>();
		Map<String, Object>[] name2=queryDAO.executeForMapArray("B_SSM_S01.ColocatonFileName", name);
		String fileName=name2[0].toString().substring(7,name2[0].toString().length()-17);
		fileName = fileName + date + ".csv";
		// Add header to this file
		String[] headerInfo = this.initHeadingLine(queryDAO, detail);
		exportList.add(headerInfo);
		int rows = resultObj.size();
		
		//Detail
		if(detail==1) {
		    int rowNo = 0;
		    for (int i = 0; i < rows; i++) { 
		        HashMap<String, Object> rowObj = resultObj.get(i);
		        int subLineCount = (((List<String>) rowObj.get("coLocationRackNo"))).size();
		        if (subLineCount!=0) {
		            for (int j = 0; j < subLineCount; j++) {
	                    rowNo = rowNo + 1;
	                    String[] exportLine = new String[headerInfo.length];
	                    initData(exportLine);
	                    exportLine[0] = CommonUtils.toString(rowNo);
	                    if (rowObj.get("coLocationCustomerID") != null) exportLine[1] = (String) rowObj.get("coLocationCustomerID");
	                    if (rowObj.get("coLocationCustomerName") !=null) exportLine[2] =(String) rowObj.get("coLocationCustomerName");
	                    if (rowObj.get("customerType") !=null) exportLine[3] = CommonUtils.getCodeMapListNameByValue("LIST_CUSTOMERTYPE", CommonUtils.toString(rowObj.get("customerType")));
	                    if (rowObj.get("coLocationSubscriptionID") !=null) exportLine[4]= rowObj.get("coLocationSubscriptionID").toString();
	                    if (rowObj.get("PLAN_STATUS") != null) exportLine[5] =  CommonUtils.getCodeMapListNameByValue("LIST_PLANSTATUS", CommonUtils.toString(rowObj.get("PLAN_STATUS")));
	                    exportLine[6]=((List<String>)rowObj.get("coLocationEquipmentLocation")).size() > j ?
	                                  ((List<String>)rowObj.get("coLocationEquipmentLocation")).get(j) : "";    
	                                  
	                    exportLine[7]=((List<String>)rowObj.get("coLocationEquipmentSuite")).size() > j ?
	                                  ((List<String>)rowObj.get("coLocationEquipmentSuite")).get(j) : "";
	                            
	                    exportLine[8]=((List<String>)rowObj.get("coLocationRackNo")).size() > j ?
	                                  ((List<String>)rowObj.get("coLocationRackNo")).get(j) : "";
	                                          
	                    exportLine[9]=((List<String>)rowObj.get("coLocationPowerCommitted")).size() > j ? 
	                                   ((List<String>)rowObj.get("coLocationPowerCommitted")).get(j) :  "";
	                    exportList.add(exportLine);
	                }
		        } else {
		            rowNo = rowNo + 1;
		            String[] exportLine = new String[headerInfo.length];
                    initData(exportLine);
                    
                    exportLine[0] = CommonUtils.toString(rowNo);
                    if (rowObj.get("coLocationCustomerID") != null) exportLine[1] = (String) rowObj.get("coLocationCustomerID");
                    if (rowObj.get("coLocationCustomerName") !=null) exportLine[2] =(String) rowObj.get("coLocationCustomerName");
                    if (rowObj.get("customerType") !=null) exportLine[3] = CommonUtils.getCodeMapListNameByValue("LIST_CUSTOMERTYPE", CommonUtils.toString(rowObj.get("customerType")));
                    if (rowObj.get("coLocationSubscriptionID") !=null) exportLine[4]= rowObj.get("coLocationSubscriptionID").toString();
                    if (rowObj.get("PLAN_STATUS") != null) exportLine[5] =  CommonUtils.getCodeMapListNameByValue("LIST_PLANSTATUS", CommonUtils.toString(rowObj.get("PLAN_STATUS")));
                    exportLine[6]="";    
                    exportLine[7]="";
                    exportLine[8]="";
                    exportLine[9]="";
                    
                    exportList.add(exportLine);
		        }
		    }
		} else {
		    for (int i = 0; i < rows; i++) { 
                HashMap<String, Object> rowObj = resultObj.get(i);
                String[] exportLine = new String[headerInfo.length];
                initData(exportLine);
                exportLine[0] = CommonUtils.toString(i+1);
                if (rowObj.get("coLocationCustomerID") != null) exportLine[1] = (String) rowObj.get("coLocationCustomerID");
                if (rowObj.get("coLocationCustomerName") !=null) exportLine[2] =(String) rowObj.get("coLocationCustomerName");
                if (rowObj.get("customerType") !=null) exportLine[3] = CommonUtils.getCodeMapListNameByValue("LIST_CUSTOMERTYPE", CommonUtils.toString(rowObj.get("customerType")));
                if (rowObj.get("coLocationSubscriptionID") !=null) exportLine[4]= rowObj.get("coLocationSubscriptionID").toString();
                if (rowObj.get("PLAN_STATUS") != null) exportLine[5] =  CommonUtils.getCodeMapListNameByValue("LIST_PLANSTATUS", CommonUtils.toString(rowObj.get("PLAN_STATUS")));
                exportLine[6] = CommonUtils.toString(rowObj.get("coLocationEquipmentLocation"));
                exportLine[7] = CommonUtils.toString(rowObj.get("coLocationEquipmentSuite"));
                exportLine[8] = CommonUtils.toString(rowObj.get("coLocationRackNo"));
                exportLine[9] = CommonUtils.toString(rowObj.get("coLocationTotalRackNo"));
                String coLocationPowerCommitted = CommonUtils.toString(rowObj.get("coLocationPowerCommitted")).trim();
                if (CommonUtils.isEmpty(coLocationPowerCommitted)) {
                    exportLine[10] = "";
                } else {
                    exportLine[10] = CommonUtils.toString(rowObj.get("coLocationPowerCommitted"));
                }
                
                exportList.add(exportLine);
            }
		}
		// export file
		DownloadFile exportFile = new DownloadFile();
		exportFile.exportFile(exportList, fileName, response);
	}
	private String[] initHeadingLine(QueryDAO queryDAO, int detail){		
		HashMap<String, Object> listheader = new HashMap<String,Object>();
		Map<String, Object>[] headingLine=queryDAO.executeForMapArray("B_SSM_S01.ColocatonHeader", listheader);
		List<String> header = new ArrayList<String>();
		if(detail == 1) {
		    for(int i=0;i<headingLine.length;i++){
		        if(i!=headingLine.length-2) {
		            header.add(headingLine[i].toString().substring(7,headingLine[i].toString().length()-1 ));
		        }
	        }
		} else {
		    for(int i=0;i<headingLine.length;i++){
		        header.add(headingLine[i].toString().substring(7,headingLine[i].toString().length()-1 ));
	        }
		}
		String[] header2 =  new String[header.size()]; 
		return header.toArray(header2);
	}
	private String[] initData(String[] exportLine){
		for (int i = 0; i < exportLine.length; i++) {
			exportLine[i] = "";
		}
		return exportLine;
	}
}