/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : B_SSM
 * SERVICE NAME   : B_SSM_E01
 * FUNCTION       : Write data to export File Email_" + "yymmddhhmmss.csv
 * FILE NAME      : B_SSM_E01_EMAIL.java
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
import nttdm.bsys.b_ssm.s01.data.B_SSM_S01_FieldSet;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;

/**
 * BusinessLogic class.
 * @author NTT Data Vietnam	
 * Action export file B_SSM_E01
 * 
 */
public class B_SSM_E01_EMAIL {
	public QueryDAO queryDAO;
	public QueryDAO getQueryDAO() {
		return queryDAO;
	}
	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}
	public class FileImportExportException extends RuntimeException{
		private static final long serialVersionUID = 1L;
		public FileImportExportException(String errorMessage){
			super(errorMessage);
		}
	}
	/**
	 * 
	 * @param param
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void exportCSVFile(List<HashMap<String,Object>> resultObj,int typesearch, QueryDAO queryDAO, HttpServletResponse response){
		List<String[]> exportList = new ArrayList<String[]>();		
		Date today = Calendar.getInstance().getTime();
		SimpleDateFormat sdf = new SimpleDateFormat(BillingSystemConstants.DATETIME_FORMAT_B_SSM_EXPORT);
		String date = sdf.format(today.getTime());
		HashMap<String, Object> name = new HashMap<String,Object>();
		Map<String, Object>[] name2=queryDAO.executeForMapArray("B_SSM_S01.EmailFileName", name);
		String fileName=name2[0].toString().substring(7,name2[0].toString().length()-17);
		fileName = fileName + date + ".csv";
		// Add header to this file
		String[] header = this.initHeadingLine(queryDAO, typesearch);
		exportList.add(header);
		int rows = resultObj.size();
		int index = 0;
		if (typesearch==1) {
		    for (int i = 0; i < rows; i++) { 
		        HashMap<String, Object> rowObj = resultObj.get(i);
		        List<Map<String, Object>> mailAccountList = (List<Map<String, Object>>)rowObj.get("mailAccountInfoList");
		        if(mailAccountList!=null && 0<mailAccountList.size()) {
		            for(int j=0;j<mailAccountList.size();j++) {
		                String[] exportLine = new String[header.length];
		                initData(exportLine);
		                
		                index = index + 1;
		                exportLine[0] = CommonUtils.toString(index);
		                exportLine[1] = CommonUtils.toString(rowObj.get("emailCustomerID"));
		                exportLine[2] = CommonUtils.toString(rowObj.get("emailCustomerName"));
		                exportLine[3] = CommonUtils.getCodeMapListNameByValue("LIST_CUSTOMERTYPE", CommonUtils.toString(rowObj.get("customerType")));
		                exportLine[4] = CommonUtils.toString(rowObj.get("emailSubscriptionID"));
		                exportLine[5] = CommonUtils.getCodeMapListNameByValue("LIST_PLANSTATUS", CommonUtils.toString(rowObj.get("PLAN_STATUS")));
		                exportLine[6] = CommonUtils.toString(rowObj.get("emailDomainName"));
		                Map<String, Object> mapMailAccount = mailAccountList.get(j);
		                exportLine[7] = CommonUtils.toString(mapMailAccount.get("MAIL_ACCOUNT"));
		                String mailIsDeleted = CommonUtils.toString(mapMailAccount.get("IS_DELETED")).trim();
		                if (CommonUtils.isEmpty(mailIsDeleted)) {
		                    exportLine[8] = "";
		                } else {
		                    exportLine[8] = "0".equals(CommonUtils.toString(mapMailAccount.get("IS_DELETED"))) ? "No":"Yes";
		                }
		                
		                exportList.add(exportLine);
		            }
		        } else {
		            index = index + 1;
		            String[] exportLine = new String[header.length];
	                initData(exportLine);
	                
		            exportLine[0] = CommonUtils.toString(index);
                    exportLine[1] = CommonUtils.toString(rowObj.get("emailCustomerID"));
                    exportLine[2] = CommonUtils.toString(rowObj.get("emailCustomerName"));
                    exportLine[3] = CommonUtils.getCodeMapListNameByValue("LIST_CUSTOMERTYPE", CommonUtils.toString(rowObj.get("customerType")));
                    exportLine[4] = CommonUtils.toString(rowObj.get("emailSubscriptionID"));
                    exportLine[5] = CommonUtils.getCodeMapListNameByValue("LIST_PLANSTATUS", CommonUtils.toString(rowObj.get("PLAN_STATUS")));
                    exportLine[6] = CommonUtils.toString(rowObj.get("emailDomainName"));
                    exportLine[7] = "";
                    exportLine[8] = "";
                    
                    exportList.add(exportLine);
		        }
		    }
		} else if (typesearch == 2) {
		    for (int i = 0; i < rows; i++) { 
                HashMap<String, Object> rowObj = resultObj.get(i);
                String[] exportLine = new String[header.length];
                initData(exportLine);
                
                exportLine[0] = CommonUtils.toString(i+1);
                exportLine[1] = CommonUtils.toString(rowObj.get("emailCustomerID"));
                exportLine[2] = CommonUtils.toString(rowObj.get("emailCustomerName"));
                exportLine[3] = CommonUtils.getCodeMapListNameByValue("LIST_CUSTOMERTYPE", CommonUtils.toString(rowObj.get("customerType")));
                exportLine[4] = CommonUtils.toString(rowObj.get("emailSubscriptionID"));
                exportLine[5] = CommonUtils.getCodeMapListNameByValue("LIST_PLANSTATUS", CommonUtils.toString(rowObj.get("PLAN_STATUS")));
                exportLine[6] = CommonUtils.toString(rowObj.get(B_SSM_S01_FieldSet.FIELD_EMAILTEAMWORKURL));
                exportLine[7] = CommonUtils.toString(rowObj.get(B_SSM_S01_FieldSet.FIELD_EMAILTEAMWORKADMINID));
                exportLine[8] = CommonUtils.toString(rowObj.get(B_SSM_S01_FieldSet.FIELD_EMAILTEAMWORKDOMAINNAMEADDRESS));
                
                exportList.add(exportLine);
            }
		}
		// Export to csv file
		DownloadFile exportFile = new DownloadFile();
		exportFile.exportFile(exportList, fileName, response);
	}
	private String[] initHeadingLine(QueryDAO queryDAO, int typesearch){		
		HashMap<String, Object> listheader = new HashMap<String,Object>();
		Map<String, Object>[] headingLine=queryDAO.executeForMapArray("B_SSM_S01.EmailHeader", listheader);
		List<String> header = new ArrayList<String>();
		
		if (typesearch==1) {
		    for(int i=0;i<headingLine.length-3;i++){
	            header.add(headingLine[i].toString().substring(7,headingLine[i].toString().length()-1 ));
	        }
		} else if (typesearch==2) {
		    for(int i=0;i<headingLine.length;i++){
		        //Domain Name and Subscribed Email Address And Deleted Mail
		        if(i!=6 && i!=7 && i!=8) {
		            header.add(headingLine[i].toString().substring(7,headingLine[i].toString().length()-1 ));
		        }
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