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
public class B_SSM_E01_ISP {
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
		Map<String, Object>[] name2=queryDAO.executeForMapArray("B_SSM_S01.ISPFileName", name);
		String fileName=name2[0].toString().substring(7,name2[0].toString().length()-17);
		fileName = fileName + date + ".csv";
		// Add header to this file
		exportList.add(this.initHeadingLine(queryDAO));
		int rows = resultObj.size();
		for (int i = 0; i < rows; i++) { 
			HashMap<String, Object> rowObj = resultObj.get(i);
			String[] exportLine = new String[15];
			initData(exportLine);
//			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");	
			
			if (rowObj.get("rowNo") != null) exportLine[0] = CommonUtils.toString(rowObj.get("rowNo"));
			if (rowObj.get("CUSTOMERID") != null) exportLine[1] = (String) rowObj.get("CUSTOMERID");
			if (rowObj.get("CUSTOMERNAME") !=null) exportLine[2] =(String) rowObj.get("CUSTOMERNAME");
			if (rowObj.get("customerType") !=null) exportLine[3] = CommonUtils.getCodeMapListNameByValue("LIST_CUSTOMERTYPE", CommonUtils.toString(rowObj.get("customerType")));
			if (rowObj.get("SUBSCRIPTIONID") !=null) exportLine[4]= rowObj.get("SUBSCRIPTIONID").toString(); 
			if (rowObj.get("PLAN_STATUS") != null) exportLine[5] =  CommonUtils.getCodeMapListNameByValue("LIST_PLANSTATUS", CommonUtils.toString(rowObj.get("PLAN_STATUS")));
			if (rowObj.get("ACCESS_ACCOUNT") != null) exportLine[6] = (String) rowObj.get("ACCESS_ACCOUNT");
			if (rowObj.get("MODEMNO") != null) exportLine[7] = (String) rowObj.get("MODEMNO");
			if (rowObj.get("ADSL_DELNO")!= null) exportLine[8] = (String) rowObj.get("ADSL_DELNO");  
			if (rowObj.get("ROUTERNO") != null) exportLine[9] = (String) rowObj.get("ROUTERNO") ;
			if (rowObj.get("CIRCUITID") != null) exportLine[10] = (String) rowObj.get("CIRCUITID");
			if (rowObj.get("CARRIER") != null) exportLine[11] = (String) rowObj.get("CARRIER") ;
			if (rowObj.get("INSTALLATIONADDRESS1") != null) exportLine[12] = (String) rowObj.get("INSTALLATIONADDRESS1");
			if (rowObj.get("INSTALLATIONADDRESS2") != null) exportLine[13] = (String) rowObj.get("INSTALLATIONADDRESS2");
			
//			// Format SVC_START
//			if (!CommonUtils.isEmpty(CommonUtils.toString(rowObj.get("SVC_START")).trim())) {
//				try {
//					Date dateStart = new SimpleDateFormat("yyyy-MM-dd").parse(rowObj.get("SVC_START").toString());
//					exportLine[12] = dateFormat.format(dateStart);
//				} catch (ParseException e) {
//					e.printStackTrace();
//				}
//			}
//			
//			// Format SVC_END
//			if (!CommonUtils.isEmpty(CommonUtils.toString(rowObj.get("SVC_END")).trim())) {
//				try {
//					Date dateEnd = new SimpleDateFormat("yyyy-MM-dd").parse(rowObj.get("SVC_END").toString());
//					exportLine[13] = dateFormat.format(dateEnd);	
//				} catch (ParseException e) {
//					e.printStackTrace();
//				}
//			}
//			
//			//if (rowObj.get("BILL_INSTRUCT")!= null) exportLine[14] = (String) rowObj.get("BILL_INSTRUCT");
//			String billInstruct = CommonUtils.toString(rowObj.get("BILL_INSTRUCT")).trim();
//			if (!CommonUtils.isEmpty(billInstruct)) {
//			    exportLine[14] = B_SSM_S01_Utils.getCodeMapListNameByValue("BILL_INSTRUCTION_LIST",billInstruct);
//			}
//			exportLine[15] = B_SSM_S01_Utils.contractTermCodeToName(CommonUtils.toString(rowObj.get("CONTRACT_TERM")));
//			if (rowObj.get("name1")!= null) exportLine[16] = (String) rowObj.get("name1");
//			if (rowObj.get("designation1")!= null) exportLine[17] = (String) rowObj.get("designation1");
//			if (rowObj.get("telno1")!= null) exportLine[18] = (String) rowObj.get("telno1");
//			if (rowObj.get("faxno1")!= null) exportLine[19] = (String) rowObj.get("faxno1");
//			if (rowObj.get("name2")!= null) exportLine[20] = (String) rowObj.get("name2");
//			if (rowObj.get("designation2")!= null) exportLine[21] = (String) rowObj.get("designation2");
//			if (rowObj.get("telno2")!= null) exportLine[22] = (String) rowObj.get("telno2");
//			if (rowObj.get("faxno2")!= null) exportLine[23] = (String) rowObj.get("faxno2");
//			if (rowObj.get("name3")!= null) exportLine[24] = (String) rowObj.get("name3");
//			if (rowObj.get("designation3")!= null) exportLine[25] = (String) rowObj.get("designation3");
//			if (rowObj.get("telno3")!= null) exportLine[26] = (String) rowObj.get("telno3");
//			if (rowObj.get("faxno3")!= null) exportLine[27] = (String) rowObj.get("faxno3");
//			if (rowObj.get("contact_name_tc") != null) exportLine [28]=(String) rowObj.get("contact_name_tc");
//			if (rowObj.get("designation_tc")!= null) exportLine[29] = (String) rowObj.get("designation_tc");
//			if (rowObj.get("EMAIL")!= null) exportLine[30] = (String) rowObj.get("EMAIL");
//			if (rowObj.get("telno_tc")!= null) exportLine[31] = (String) rowObj.get("telno_tc");
//			if (rowObj.get("faxno_tc")!= null) exportLine[32] = (String) rowObj.get("faxno_tc");
//			if (rowObj.get("MOBILE_NO")!= null) exportLine[33] = (String) rowObj.get("MOBILE_NO");
			exportList.add(exportLine);
		}
		// Export to csv file
		DownloadFile exportFile = new DownloadFile();
		exportFile.exportFile(exportList, fileName, response);
	}
	private String[] initHeadingLine(QueryDAO queryDAO){		
		HashMap<String, Object> listheader = new HashMap<String,Object>();
		Map<String, Object>[] headingLine=queryDAO.executeForMapArray("B_SSM_S01.ISPHeader", listheader);
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