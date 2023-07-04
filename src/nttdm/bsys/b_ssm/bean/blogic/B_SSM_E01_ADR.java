/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : B_SSM
 * SERVICE NAME   : B_SSM_E01
 * FUNCTION       : Write data to file Address_" + "yymmddhhmmss.csv
 * FILE NAME      : B_SSM_E01_ADR.java
 *
 * Copyright (C) 2011 NTTDATA Corporation
 * History
 * 2011/10/03 [Hoang Duong] Updated
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
import nttdm.bsys.b_ssm.utility.BLogicUtils;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;

/**
 * BusinessLogic class.
 * @author NTT Data Vietnam	
 * Action export file B_SSM_E01
 * 
 */
public class B_SSM_E01_ADR {
	public QueryDAO queryDAO;
	public QueryDAO getQueryDAO() {
		return queryDAO;
	}
	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}
	public class FileImportExportException extends RuntimeException{
		private static final long serialVersionUID = 1L;
		public FileImportExportException(String errorMessage) {
			super(errorMessage);
		}
	}
	/**
	 * 
	 * @param param
	 * 
	 */
	public void exportCSVFile(List<HashMap<String,Object>> resultObj,QueryDAO queryDAO,HttpServletResponse response, List<String> addressTypeArray){
	    String addressRAFlag = "";
        String addressBAFlag = "";
        String addressCAFlag = "";
        String addressTAFlag = "";
	    if (addressTypeArray != null && 0<addressTypeArray.size()) {
            for(String str : addressTypeArray) {
                if ("RA".equals(str)) {
                    addressRAFlag = str;
                } else if ("BA".equals(str)) {
                    addressBAFlag = str;
                } else if ("CA".equals(str)) {
                    addressCAFlag = str;
                } else {
                    addressTAFlag = str;
                }
            }
        }
        // not select Address Type then address select RA,BA,CA,TA
        if ("".equals(addressRAFlag) && "".equals(addressBAFlag)
                && "".equals(addressCAFlag) && "".equals(addressTAFlag)) {
            addressRAFlag = "RA";
            addressBAFlag = "BA";
            addressCAFlag = "CA";
            addressTAFlag = "TA";
        }
	    List<String[]> exportList = new ArrayList<String[]>();
		Date today = Calendar.getInstance().getTime();
		SimpleDateFormat sdf = new SimpleDateFormat(BillingSystemConstants.DATETIME_FORMAT_B_SSM_EXPORT);
		String date = sdf.format(today.getTime());
		HashMap<String, Object> name = new HashMap<String,Object>();
		Map<String, Object>[] name2=queryDAO.executeForMapArray("B_SSM_S01.AdderssFileName", name);
		String fileName=name2[0].toString().substring(7,name2[0].toString().length()-17);
		fileName = fileName + date + ".csv";
		// Add header to this file
		String[] headerTitle = this.initHeadingLine(queryDAO, addressRAFlag, addressBAFlag, addressCAFlag, addressTAFlag);
		exportList.add(headerTitle);
		int rows = resultObj.size();
		for (int i = 0; i < rows; i++) { 
			HashMap<String, Object> rowObj = resultObj.get(i);
			String[] exportLine = new String[headerTitle.length];//rowObj.size()
			initData(exportLine);
			exportLine[0] = CommonUtils.toString(i+1);
			if (rowObj.get("addressCustomerID") != null) exportLine[1] = rowObj.get("addressCustomerID").toString().trim();	
			if (rowObj.get("addressCustomerName") != null) exportLine[2] =  rowObj.get("addressCustomerName").toString().trim() ;
			if (rowObj.get("customerType") !=null) exportLine[3] = CommonUtils.getCodeMapListNameByValue("LIST_CUSTOMERTYPE", CommonUtils.toString(rowObj.get("customerType")));
			if (rowObj.get("addressSubscriptionID") !=null) exportLine[4] = rowObj.get("addressSubscriptionID").toString().trim()  ;				
			if (rowObj.get("PLAN_STATUS") != null) exportLine[5] =  CommonUtils.getCodeMapListNameByValue("LIST_PLANSTATUS", CommonUtils.toString(rowObj.get("PLAN_STATUS")));		
			if (rowObj.get("addressCustomerTelephone") != null) exportLine[6] = rowObj.get("addressCustomerTelephone").toString().trim();				
			if (rowObj.get("addressCustomerFaxNo") != null) exportLine[7] = rowObj.get("addressCustomerFaxNo").toString().trim();
			int index = 8;
			if (!CommonUtils.isEmpty(addressRAFlag)) {
			    if (rowObj.get("addressRA")!= null) exportLine[8] = rowObj.get("addressRA").toString().trim();
			    index = index + 1;
			}
			if (!CommonUtils.isEmpty(addressBAFlag)) {
			    index = 8;
			    if (!CommonUtils.isEmpty(addressRAFlag)) {
			        if (rowObj.get("addressBA") != null) exportLine[9] = rowObj.get("addressBA").toString().trim();
			        index = index + 2;
			    } else {
			        if (rowObj.get("addressBA") != null) exportLine[8] = rowObj.get("addressBA").toString().trim();
			        index = index + 1;
			    }
			}
			if (!CommonUtils.isEmpty(addressCAFlag)) {
			    index = 8;
			    if (!CommonUtils.isEmpty(addressRAFlag) && !CommonUtils.isEmpty(addressBAFlag)) {
			        if (rowObj.get("addressCA") != null) exportLine[10] = rowObj.get("addressCA").toString().trim();
			        index = index + 3;
			    } else if ((CommonUtils.isEmpty(addressRAFlag) && !CommonUtils.isEmpty(addressBAFlag)) || (!CommonUtils.isEmpty(addressRAFlag) && CommonUtils.isEmpty(addressBAFlag))) {
			        if (rowObj.get("addressCA") != null) exportLine[9] = rowObj.get("addressCA").toString().trim();
			        index = index + 2;
			    } else if (CommonUtils.isEmpty(addressRAFlag) && CommonUtils.isEmpty(addressBAFlag)) {
			        if (rowObj.get("addressCA") != null) exportLine[8] = rowObj.get("addressCA").toString().trim();
			        index = index + 1;
			    }
			}
			if (!CommonUtils.isEmpty(addressTAFlag)) {
			    index = 8;
			    if (!CommonUtils.isEmpty(addressRAFlag) && !CommonUtils.isEmpty(addressBAFlag) && !CommonUtils.isEmpty(addressCAFlag)) {
			        if (rowObj.get("addressTA") != null) exportLine[11] = rowObj.get("addressTA").toString().trim();
			        index = index + 4;
			    } else if ((CommonUtils.isEmpty(addressRAFlag) && !CommonUtils.isEmpty(addressBAFlag) && !CommonUtils.isEmpty(addressCAFlag))
			               || (!CommonUtils.isEmpty(addressRAFlag) && CommonUtils.isEmpty(addressBAFlag) && !CommonUtils.isEmpty(addressCAFlag))
			               || (!CommonUtils.isEmpty(addressRAFlag) && !CommonUtils.isEmpty(addressBAFlag) && CommonUtils.isEmpty(addressCAFlag))) {
			        if (rowObj.get("addressTA") != null) exportLine[10] = rowObj.get("addressTA").toString().trim();
			        index = index + 3;
			    } else if ((CommonUtils.isEmpty(addressRAFlag) && CommonUtils.isEmpty(addressBAFlag) && !CommonUtils.isEmpty(addressCAFlag))
                        || (CommonUtils.isEmpty(addressRAFlag) && !CommonUtils.isEmpty(addressBAFlag) && CommonUtils.isEmpty(addressCAFlag))
                        || (!CommonUtils.isEmpty(addressRAFlag) && CommonUtils.isEmpty(addressBAFlag) && CommonUtils.isEmpty(addressCAFlag))) {
			        if (rowObj.get("addressTA") != null) exportLine[9] = rowObj.get("addressTA").toString().trim();
			        index = index + 2;
			    } else if (CommonUtils.isEmpty(addressRAFlag) && CommonUtils.isEmpty(addressBAFlag) && CommonUtils.isEmpty(addressCAFlag)) {
			        if (rowObj.get("addressTA") != null) exportLine[8] = rowObj.get("addressTA").toString().trim();
			        index = index + 1;
			    }
			}
			
			if (rowObj.get("name1")!= null) exportLine[index] = rowObj.get("name1").toString().trim();
			if (rowObj.get("mail1")!= null) exportLine[index + 1] = rowObj.get("mail1").toString().trim();	
			if (rowObj.get("telno1") != null) exportLine[index + 2] = rowObj.get("telno1").toString().trim();
			if (rowObj.get("name2")!= null) exportLine[index + 3] = rowObj.get("name2").toString().trim();
			if (rowObj.get("email2")!= null) exportLine[index + 4] = rowObj.get("email2").toString().trim();
			if (rowObj.get("telno2")!= null) exportLine[index + 5] = rowObj.get("telno2").toString().trim();
			if (rowObj.get("name3")!= null) exportLine[index + 6] = rowObj.get("name3").toString().trim();
			if (rowObj.get("email3")!= null) exportLine[index + 7] = rowObj.get("email3").toString().trim();
			if (rowObj.get("telno3")!= null) exportLine[index + 8] = rowObj.get("telno3").toString().trim();
			exportList.add(exportLine);
		}
		// Export to csv file
		DownloadFile exportFile = new DownloadFile();
		exportFile.exportFile(exportList, fileName, response);
	}
	private String[] initHeadingLine(QueryDAO queryDAO, String addressRAFlag, String addressBAFlag, String addressCAFlag, String addressTAFlag){		
		HashMap<String, Object> listheader = new HashMap<String,Object>();
		Map<String, Object>[] headingLine=queryDAO.executeForMapArray("B_SSM_S01.AdderssHeader", listheader);
		int header2Len = headingLine.length;
		if (CommonUtils.isEmpty(addressRAFlag)) {
		    header2Len = header2Len - 1;
		}
		if (CommonUtils.isEmpty(addressBAFlag)) {
            header2Len = header2Len - 1;
        }
		if (CommonUtils.isEmpty(addressCAFlag)) {
            header2Len = header2Len - 1;
        }
		if (CommonUtils.isEmpty(addressTAFlag)) {
            header2Len = header2Len - 1;
        }
		String[] header2 = new String[header2Len];
		int index = 0 ;
		for(int i=0;i<headingLine.length;i++){
		    if(i==8) {
		        if (!CommonUtils.isEmpty(addressRAFlag)) {
		            header2[index]=headingLine[i].toString().substring(7,headingLine[i].toString().length()-1 );
		            index = index + 1;
		        }
		    } else if(i==9) {
                if (!CommonUtils.isEmpty(addressBAFlag)) {
                    header2[index]=headingLine[i].toString().substring(7,headingLine[i].toString().length()-1 );
                    index = index + 1;
                }
            } else if(i==10) {
                if (!CommonUtils.isEmpty(addressCAFlag)) {
                    header2[index]=headingLine[i].toString().substring(7,headingLine[i].toString().length()-1 );
                    index = index + 1;
                }
            } else if(i==11) {
                if (!CommonUtils.isEmpty(addressTAFlag)) {
                    header2[index]=headingLine[i].toString().substring(7,headingLine[i].toString().length()-1 );
                    index = index + 1;
                }
            } else {
                header2[index]=headingLine[i].toString().substring(7,headingLine[i].toString().length()-1 );
                index = index + 1;
            }
		}
		return header2;
	}
	private String[] initData(String[] exportLine){
		for (int i = 0; i < exportLine.length; i++) {
			exportLine[i] = "";
		}
		return exportLine;
	}
	// #188 Start
	public void exportCSVFile(List<HashMap<String, Object>> resultObj,
			QueryDAO queryDAO, HttpServletResponse response,
			HashMap<String, Object> logicInput) {
		List<String[]> exportList = new ArrayList<String[]>();
		Date today = Calendar.getInstance().getTime();
		SimpleDateFormat sdf = new SimpleDateFormat(BillingSystemConstants.DATETIME_FORMAT_B_SSM_EXPORT);
		String date = sdf.format(today.getTime());
		HashMap<String, Object> name = new HashMap<String,Object>();
		Map<String, Object>[] name2=queryDAO.executeForMapArray("B_SSM_S01.AdderssFileName", name);
		String fileName=name2[0].toString().substring(7,name2[0].toString().length()-17);
		fileName = fileName + date + ".csv";
		// Add header to this file
		Map<String, Object>[] headingLine = queryDAO.executeForMapArray("B_SSM_S01.AdderssHeader", logicInput);
		String[] headerTitle = new String[headingLine.length];
		for (int i = 0; i < headingLine.length; i++) {
			headerTitle[i] = headingLine[i].get("VALUE").toString();
		}
		exportList.add(headerTitle);
		
		for (int i = 0; i < resultObj.size(); i++) {
			HashMap<String, Object> result = resultObj.get(i);
			String[] exportLine = new String[headerTitle.length];
			int index = 0;
			exportLine[index++] = CommonUtils.toString(i+1);
			exportLine[index++] = BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_ADDRESSCUSTOMERID.toUpperCase()), "");
			exportLine[index++] = BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_ADDRESSCUSTOMERNAME.toUpperCase()), "");
			exportLine[index++] = CommonUtils.getCodeMapListNameByValue("LIST_CUSTOMERTYPE",BLogicUtils.emptyValue(result.get("CUSTOMER_TYPE"), ""));
			exportLine[index++] = BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_ADDRESSSUBSCRIPTIONID.toUpperCase()), "");
			exportLine[index++] = CommonUtils.getCodeMapListNameByValue("LIST_PLANSTATUS",BLogicUtils.emptyValue(result.get("PLAN_STATUS"), ""));
			exportLine[index++] = BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_ADDRESSCOTELNO.toUpperCase()), "");
			exportLine[index++] = BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_ADDRESSCOFAXNO.toUpperCase()), "");
			exportLine[index++] = BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_ADDRESSHOMETELNO.toUpperCase()), "");
			exportLine[index++] = BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_ADDRESSHOMETELFAX.toUpperCase()), "");
			exportLine[index++] = BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_ADDRESSMOBILENO.toUpperCase()), "");
			exportLine[index++] = BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_ADDRESSCUSTEMAIL.toUpperCase()), "");
			exportLine[index++] = BLogicUtils.emptyValue(result.get(B_SSM_S01_FieldSet.FIELD_ADDRESSICNO.toUpperCase()), "");
			//if (!"".equals(logicInput.get("addressRAFlag"))) {
				exportLine[index++] = BLogicUtils.emptyValue(result.get("ADDRESSRA"), "");
			//}
			//if (!"".equals(logicInput.get("addressBAFlag"))) {
				exportLine[index++] = BLogicUtils.emptyValue(result.get("ADDRESSBA1"), "");
				exportLine[index++] = BLogicUtils.emptyValue(result.get("ADDRESSBA2"), "");
				exportLine[index++] = BLogicUtils.emptyValue(result.get("ADDRESSBA3"), "");
				exportLine[index++] = BLogicUtils.emptyValue(result.get("ADDRESSBA4"), "");
			//}
			//if (!"".equals(logicInput.get("addressCAFlag"))) {
				exportLine[index++] = BLogicUtils.emptyValue(result.get("ADDRESSCA"), "");
			//}
			//if (!"".equals(logicInput.get("addressTAFlag"))) {
				exportLine[index++] = BLogicUtils.emptyValue(result.get("ADDRESSTA"), "");
			//}
			//if (!"".equals(logicInput.get("contactBCFlag"))) {
				exportLine[index++] = BLogicUtils.emptyValue(result.get("BC1NAME"), "");
				exportLine[index++] = BLogicUtils.emptyValue(result.get("BC1TELNO"), "");
				exportLine[index++] = BLogicUtils.emptyValue(result.get("BC1FAXNO"), "");
				exportLine[index++] = BLogicUtils.emptyValue(result.get("BC1MOBILE"), "");
				exportLine[index++] = BLogicUtils.emptyValue(result.get("BC1EMAIL"), "");
				exportLine[index++] = BLogicUtils.emptyValue(result.get("BC1EMAILCC"), "");
				exportLine[index++] = BLogicUtils.emptyValue(result.get("BC2NAME"), "");
				exportLine[index++] = BLogicUtils.emptyValue(result.get("BC2TELNO"), "");
				exportLine[index++] = BLogicUtils.emptyValue(result.get("BC2FAXNO"), "");
				exportLine[index++] = BLogicUtils.emptyValue(result.get("BC2MOBILE"), "");
				exportLine[index++] = BLogicUtils.emptyValue(result.get("BC2EMAIL"), "");
				exportLine[index++] = BLogicUtils.emptyValue(result.get("BC2EMAILCC"), "");
				exportLine[index++] = BLogicUtils.emptyValue(result.get("BC3NAME"), "");
				exportLine[index++] = BLogicUtils.emptyValue(result.get("BC3TELNO"), "");
				exportLine[index++] = BLogicUtils.emptyValue(result.get("BC3FAXNO"), "");
				exportLine[index++] = BLogicUtils.emptyValue(result.get("BC3MOBILE"), "");
				exportLine[index++] = BLogicUtils.emptyValue(result.get("BC3EMAIL"), "");
				exportLine[index++] = BLogicUtils.emptyValue(result.get("BC3EMAILCC"), "");
				exportLine[index++] = BLogicUtils.emptyValue(result.get("BC4NAME"), "");
				exportLine[index++] = BLogicUtils.emptyValue(result.get("BC4TELNO"), "");
				exportLine[index++] = BLogicUtils.emptyValue(result.get("BC4FAXNO"), "");
				exportLine[index++] = BLogicUtils.emptyValue(result.get("BC4MOBILE"), "");
				exportLine[index++] = BLogicUtils.emptyValue(result.get("BC4EMAIL"), "");
				exportLine[index++] = BLogicUtils.emptyValue(result.get("BC4EMAILCC"), "");
			//}
			//if (!"".equals(logicInput.get("contactPCFlag"))) {
				exportLine[index++] = BLogicUtils.emptyValue(result.get("BC5NAME"), "");
				exportLine[index++] = BLogicUtils.emptyValue(result.get("BC5TELNO"), "");
				exportLine[index++] = BLogicUtils.emptyValue(result.get("BC5FAXNO"), "");
				exportLine[index++] = BLogicUtils.emptyValue(result.get("BC5MOBILE"), "");
				exportLine[index++] = BLogicUtils.emptyValue(result.get("BC5EMAIL"), "");
				exportLine[index++] = BLogicUtils.emptyValue(result.get("BC5EMAILCC"), "");
			//}
			//if (!"".equals(logicInput.get("contactTCFlag"))) {
				exportLine[index++] = BLogicUtils.emptyValue(result.get("BC6NAME"), "");
				exportLine[index++] = BLogicUtils.emptyValue(result.get("BC6TELNO"), "");
				exportLine[index++] = BLogicUtils.emptyValue(result.get("BC6FAXNO"), "");
				exportLine[index++] = BLogicUtils.emptyValue(result.get("BC6MOBILE"), "");
				exportLine[index++] = BLogicUtils.emptyValue(result.get("BC6EMAIL"), "");
				exportLine[index++] = BLogicUtils.emptyValue(result.get("BC6EMAILCC"), "");
			//}
			//if (!"".equals(logicInput.get("contactOCFlag"))) {
				exportLine[index++] = BLogicUtils.emptyValue(result.get("BC7NAME"), "");
				exportLine[index++] = BLogicUtils.emptyValue(result.get("BC7TELNO"), "");
				exportLine[index++] = BLogicUtils.emptyValue(result.get("BC7FAXNO"), "");
				exportLine[index++] = BLogicUtils.emptyValue(result.get("BC7MOBILE"), "");
				exportLine[index++] = BLogicUtils.emptyValue(result.get("BC7EMAIL"), "");
				exportLine[index++] = BLogicUtils.emptyValue(result.get("BC7EMAILCC"), "");
			//}
			//if (!"".equals(logicInput.get("contactITCFlag"))) {
				exportLine[index++] = BLogicUtils.emptyValue(result.get("ITC1NAME"), "");
				exportLine[index++] = BLogicUtils.emptyValue(result.get("ITC1TELNO"), "");
				exportLine[index++] = BLogicUtils.emptyValue(result.get("ITC1FAXNO"), "");
				exportLine[index++] = BLogicUtils.emptyValue(result.get("ITC1EMAIL"), "");
				exportLine[index++] = BLogicUtils.emptyValue(result.get("ITC2NAME"), "");
				exportLine[index++] = BLogicUtils.emptyValue(result.get("ITC2TELNO"), "");
				exportLine[index++] = BLogicUtils.emptyValue(result.get("ITC2FAXNO"), "");
				exportLine[index++] = BLogicUtils.emptyValue(result.get("ITC2EMAIL"), "");
				exportLine[index++] = BLogicUtils.emptyValue(result.get("ITC3NAME"), "");
				exportLine[index++] = BLogicUtils.emptyValue(result.get("ITC3TELNO"), "");
				exportLine[index++] = BLogicUtils.emptyValue(result.get("ITC3FAXNO"), "");
				exportLine[index++] = BLogicUtils.emptyValue(result.get("ITC3EMAIL"), "");
			//}
			exportList.add(exportLine);
		}
		// Export to csv file
		DownloadFile exportFile = new DownloadFile();
		exportFile.exportFile(exportList, fileName, response);
	}
	// #188 End
}