/**
 * Billing System
 * 
 * SUBSYSTEM NAME : Subscription Information
 * SERVICE NAME : B_SSM_S02
 * FUNCTION : processing reports from B_SSM_S02
 * FILE NAME : B_RPT_SB02.java
 * 
 * Copyright (C) 2011 NTTDATA Corporation
 * 
 * History
 * 2011/09/31 [Duong Nguyen] Created
 */
package nttdm.bsys.b_ssm.s03.b_rpt.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.dao.QueryDAO;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import nttdm.bsys.b_ssm.s02.data.B_SSM_S02_FieldSet;
import nttdm.bsys.b_ssm.s03.b_rpt.data.B_RPT_Output;
import nttdm.bsys.b_ssm.s03.b_rpt.data.ContentType;
import nttdm.bsys.b_ssm.s03.data.B_SSM_S03_FieldSet;
import nttdm.bsys.b_ssm.utility.BLogicUtils;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.MessageUtil;
import nttdm.bsys.util.exception.ReportException;

/**
 * B_RPT_SB02 class use to export MS Excel<br/>
 * @author  NTTData Vietnam
 * @version 1.1
 */
public class B_RPT_SB02 extends AB_RPT<Map<String, Object>> {

	private static final String PRE_FILE_NAME = "Email_Notice_";
	
	public B_RPT_SB02(QueryDAO queryDAO) {
		super(queryDAO);
	}

	public B_RPT_Output export(Map<String, Object> params) throws ReportException {
		// TODO Auto-generated method stub
		B_RPT_Output out = new B_RPT_Output();
		params.put("addType", "RA");
		
		Map<String, Object> companyInfo = queryDAO.executeForMap("B_RPT.getCompanyInformation", params);
		Map<String, Object> custInfo = queryDAO.executeForMap("B_RPT.getCustomerInformation", params);
		List<Map<String, Object>> newMail = queryDAO.executeForMapList("B_SSM_S02.GET_NEW_MAIL", params);
		JRMapCollectionDataSource newMailDS = new JRMapCollectionDataSource(newMail);
		
		List<Map<String, Object>> deletionMail = queryDAO.executeForMapList("B_SSM_S02.GET_DELETION_MAIL", params);
		JRMapCollectionDataSource deletionMailDS = new JRMapCollectionDataSource(deletionMail);
		
		//initialize report parameter
		Map<String, Object> rptParam = new HashMap<String, Object>();
		if (newMail.size() == 0 && deletionMail.size() == 0) {
			//get error message
			String errorMsg = MessageUtil.get("info.ERR1SC033", "Email Address", "Email Address");
			throw new ReportException(errorMsg);
		} else {
			//setting for new email display
			if (newMail.size() == 0) {
				rptParam.put("IS_DISPLAY_NEW", "N");
			} else {
				rptParam.put("IS_DISPLAY_NEW", "Y");
			}
			
			//setting for deletion email display
			if (deletionMail.size() == 0) {
				rptParam.put("IS_DISPLAY_DELETION", "N");
			} else {
				rptParam.put("IS_DISPLAY_DELETION", "Y");
			}
		}
		
		String companyAddress1 = BLogicUtils.emptyValue(companyInfo.get(B_SSM_S03_FieldSet.FIELD_RPT_COMPANYADDRESS1), "").trim();
        String companyAddress2 = BLogicUtils.emptyValue(companyInfo.get(B_SSM_S03_FieldSet.FIELD_RPT_COMPANYADDRESS2), "").trim();
        String companyAddress3 = BLogicUtils.emptyValue(companyInfo.get(B_SSM_S03_FieldSet.FIELD_RPT_COMPANYADDRESS3), "").trim();
        String companyZipCode = BLogicUtils.emptyValue(companyInfo.get("COMPANY_POSTAL_CODE"), "").trim();
        String companyCountry = BLogicUtils.emptyValue(companyInfo.get("COMPANY_COUNTRY_NAME"), "").trim();
        String companyAddress4 = "";
        if(!CommonUtils.isEmpty(companyZipCode)&&!CommonUtils.isEmpty(companyCountry)) {
            companyAddress4 = companyCountry + " " + companyZipCode;
        } else if(CommonUtils.isEmpty(companyZipCode)&&!CommonUtils.isEmpty(companyCountry)) {
            companyAddress4 = companyCountry;
        } else if(!CommonUtils.isEmpty(companyZipCode)&&CommonUtils.isEmpty(companyCountry)) {
            companyAddress4 = companyZipCode;
        }
        companyInfo.put(B_SSM_S03_FieldSet.FIELD_RPT_COMPANYADDRESS1, companyAddress1);
        companyInfo.put(B_SSM_S03_FieldSet.FIELD_RPT_COMPANYADDRESS2, companyAddress2);
        companyInfo.put(B_SSM_S03_FieldSet.FIELD_RPT_COMPANYADDRESS3, companyAddress3);
        companyInfo.put("COMPANY_ADDRESS4", companyAddress4);
        
        companyInfo.put("COMPANY_ADDRESS5", "Tel : " + CommonUtils.toString(companyInfo.get("COMPANY_TEL_NO"))
                + " Fax : " + CommonUtils.toString(companyInfo.get("COMPANY_FAX_NO")));
        companyInfo.put("COMPANY_ADDRESS6", "Reg. No : " + CommonUtils.toString(companyInfo.get("COMPANY_REG_NO")));
        //companyInfo.put("COMPANY_ADDRESS7", "GST Reg. No. : " + CommonUtils.toString(companyInfo.get("COMPANY_GST_REG_NO")));
        companyInfo.put("COMPANY_ADDRESS7", "Website : " + CommonUtils.toString(companyInfo.get("COM_WEBSITE")));
        //change the display address
        addressDisplayChange(companyInfo, "COMPANY_ADDRESS");
        
        String customerZipCode = CommonUtils.toString(custInfo.get("CUSTOMER_ZIPCODE")).trim();
        String customerCountryName = CommonUtils.toString(custInfo.get("CUSTOMER_COUNTRY_NAME")).trim();
        if(!CommonUtils.isEmpty(customerZipCode)&&!CommonUtils.isEmpty(customerCountryName)) {
            custInfo.put("CUSTOMER_ADDRESS4", customerCountryName + " " + customerZipCode);
        } else if(CommonUtils.isEmpty(customerZipCode)&&!CommonUtils.isEmpty(customerCountryName)) {
            custInfo.put("CUSTOMER_ADDRESS4", customerCountryName);
        } else if(!CommonUtils.isEmpty(customerZipCode)&&CommonUtils.isEmpty(customerCountryName)) {
            custInfo.put("CUSTOMER_ADDRESS4", customerZipCode);
        } else {
            custInfo.put("CUSTOMER_ADDRESS4", "");
        }
        //change the display address
        addressDisplayChange(custInfo, "CUSTOMER_ADDRESS");
        
        String customerType = CommonUtils.toString(custInfo.get("CUSTOMER_TYPE")).trim();
        String custTelNo = "";
        String custFaxNo = "";
        if ("CORP".equals(customerType)){
            custTelNo = CommonUtils.toString(custInfo.get("CUSTOMER_CO_TEL_NO"));
            custFaxNo = CommonUtils.toString(custInfo.get("CUSTOMER_CO_FAX_NO"));
        } else if ("CONS".equals(customerType)){
            custTelNo = CommonUtils.toString(custInfo.get("CUSTOMER_HOME_TEL_NO"));
            custFaxNo = CommonUtils.toString(custInfo.get("CUSTOMER_FAX_TEL_NO"));
        }
        custInfo.put("CUSTOMER_TEL_NO", custTelNo);
        custInfo.put("CUSTOMER_FAX_NO", custFaxNo);
        
		rptParam.putAll(companyInfo);
		rptParam.putAll(custInfo);
		
		rptParam.put("NEW_MAIL", newMailDS);
		rptParam.put("DELETION_MAIL", deletionMailDS);
		rptParam.put("IMAGE_PATH", params.get(B_SSM_S02_FieldSet.FIELD_REPORTLOGOPATH));
		rptParam.put("ID_CUST", params.get(B_SSM_S02_FieldSet.FIELD_CUSTOMERID));
		rptParam.put("SUBSCIPTION_ID", params.get(B_SSM_S02_FieldSet.FIELD_SUBSCRIPTIONID));
		String sysdate = (new SimpleDateFormat("dd/MM/yyyy")).format(new Date());
		rptParam.put("SYSDATE", sysdate);
		//init header info
		setHeaderInfo(rptParam);
		
		try {
			// Report fill data
			String rpt = params.get(B_SSM_S02_FieldSet.FIELD_REPORTTEMPLATEPATH).toString();
			JasperPrint rptPrint = JasperFillManager.fillReport(rpt, rptParam, new JREmptyDataSource());
			
			//report output
			ByteArrayOutputStream rptOutput = new ByteArrayOutputStream();
			
			//init export object
			JRPdfExporter exportManager = new JRPdfExporter();
			exportManager.setParameter(JRPdfExporterParameter.JASPER_PRINT, rptPrint);
			exportManager.setParameter(JRPdfExporterParameter.OUTPUT_STREAM, rptOutput);
			exportManager.exportReport();
			
			InputStream inputStream = new ByteArrayInputStream(rptOutput.toByteArray());
			
			out.setContentType(ContentType.MS_WORD);
			out.setFileName(PRE_FILE_NAME + params.get(B_SSM_S02_FieldSet.FIELD_CUSTOMERID) + "_" + params.get(B_SSM_S02_FieldSet.FIELD_CUSTOMERPLANID) + ".pdf");
			out.setInputStream(inputStream);
			return out;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ReportException(e.getMessage());
		}
	}
	
	/**
	 * init header info data
	 * @param rptParam
	 */
	private void setHeaderInfo(Map<String, Object> rptParam) {
	    List<Map<String, Object>> headerInfoList = new ArrayList<Map<String, Object>>();
	    Map<String, Object> headerInfo = new HashMap<String, Object>();
	    headerInfo.put("COMPANY_NAME", CommonUtils.toString(rptParam.get("COMPANY_NAME")));
	    headerInfo.put("COMPANY_ADDRESS1", CommonUtils.toString(rptParam.get("COMPANY_ADDRESS1")));
	    headerInfo.put("COMPANY_ADDRESS2", CommonUtils.toString(rptParam.get("COMPANY_ADDRESS2")));
	    headerInfo.put("COMPANY_ADDRESS3", CommonUtils.toString(rptParam.get("COMPANY_ADDRESS3")));
	    headerInfo.put("COMPANY_ADDRESS4", CommonUtils.toString(rptParam.get("COMPANY_ADDRESS4")));
	    headerInfo.put("COMPANY_ADDRESS5", CommonUtils.toString(rptParam.get("COMPANY_ADDRESS5")));
	    headerInfo.put("COMPANY_ADDRESS6", CommonUtils.toString(rptParam.get("COMPANY_ADDRESS6")));
	    headerInfo.put("COMPANY_ADDRESS7", CommonUtils.toString(rptParam.get("COMPANY_ADDRESS7")));
	    headerInfo.put("COMPANY_ADDRESS8", CommonUtils.toString(rptParam.get("COMPANY_ADDRESS8")));
//	    headerInfo.put("COMPANY_TEL_NO_TITLE", "Tel : " + CommonUtils.toString(rptParam.get("COMPANY_TEL_NO"))
//	                                            + " Fax : " + CommonUtils.toString(rptParam.get("COMPANY_FAX_NO")));
//	    headerInfo.put("COMPANY_TEL_NO", CommonUtils.toString(rptParam.get("COMPANY_TEL_NO")));
//	    headerInfo.put("COMPANY_FAX_NO_TITLE", "Fax");
//	    headerInfo.put("COMPANY_FAX_NO", CommonUtils.toString(rptParam.get("COMPANY_FAX_NO")));
//	    headerInfo.put("COMPANY_REG_NO_TITLE", "Reg. No");
//        headerInfo.put("COMPANY_REG_NO", CommonUtils.toString(rptParam.get("COMPANY_REG_NO")));
//        headerInfo.put("COMPANY_GST_REG_NO_TITLE", "GST Reg. No.");
//        headerInfo.put("COMPANY_GST_REG_NO", CommonUtils.toString(rptParam.get("COMPANY_GST_REG_NO")));
//        headerInfo.put("WEBSITE_TITLE", "Website");
//        headerInfo.put("WEBSITE", CommonUtils.toString(rptParam.get("COM_WEBSITE")));
        headerInfo.put("CUSTOMER_NAME_TITLE", "To");
        headerInfo.put("COLON", ":");
        headerInfo.put("CUSTOMER_NAME", CommonUtils.toString(rptParam.get("CUSTOMER_NAME")));
        headerInfo.put("CUSTOMER_ADDRESS1", CommonUtils.toString(rptParam.get("CUSTOMER_ADDRESS1")));
        headerInfo.put("CUSTOMER_ADDRESS2", CommonUtils.toString(rptParam.get("CUSTOMER_ADDRESS2")));
        headerInfo.put("CUSTOMER_ADDRESS3", CommonUtils.toString(rptParam.get("CUSTOMER_ADDRESS3")));
        headerInfo.put("CUSTOMER_ADDRESS4", CommonUtils.toString(rptParam.get("CUSTOMER_ADDRESS4")));
        headerInfo.put("CUSTOMER_TEL_NO_TITLE", "Tel");
        headerInfo.put("CUSTOMER_TEL_NO", CommonUtils.toString(rptParam.get("CUSTOMER_TEL_NO")));
        headerInfo.put("CUSTOMER_FAX_NO_TITLE", "Fax");
        headerInfo.put("CUSTOMER_FAX_NO", CommonUtils.toString(rptParam.get("CUSTOMER_FAX_NO")));
        headerInfo.put("SYSDATE_TITLE", "Date");
        headerInfo.put("SYSDATE", CommonUtils.toString(rptParam.get("SYSDATE")));
        headerInfo.put("ID_CUST_TITLE", "Customer ID");
        headerInfo.put("ID_CUST", CommonUtils.toString(rptParam.get("ID_CUST")));
        headerInfo.put("SUBSCIPTION_ID_TITLE", "Subscription ID");
        headerInfo.put("SUBSCIPTION_ID", CommonUtils.toString(rptParam.get("SUBSCIPTION_ID")));
        headerInfo.put("CUSTOMER_CONTACT_NAME_TITLE", "Attention");
        headerInfo.put("CUSTOMER_CONTACT_NAME", CommonUtils.toString(rptParam.get("CUSTOMER_CONTACT_NAME")));
	    headerInfoList.add(headerInfo);
	    
        JRMapCollectionDataSource headerInfoDS = new JRMapCollectionDataSource(headerInfoList);
        rptParam.put("HEADER_INFO", headerInfoDS);
	}
	
	/**
     * change the display address
     * @param map
     * @param addrName
     */
    private void addressDisplayChange(Map<String, Object> map,String addrName){
        ArrayList<String> addrList = new ArrayList<String>();
        
        String addressName1 = addrName+"1";
        String addressName2 = addrName+"2";
        String addressName3 = addrName+"3";
        String addressName4 = addrName+"4";
//        String addressName5 = addrName+"5";
//        String addressName6 = addrName+"6";
//        String addressName7 = addrName+"7";
//        String addressName8 = addrName+"8";
        if(!"".equals(CommonUtils.toString(map.get(addressName1)).trim())){
            addrList.add(CommonUtils.toString(map.get(addressName1)).trim());
        }
        if(!"".equals(CommonUtils.toString(map.get(addressName2)).trim())){
            addrList.add(CommonUtils.toString(map.get(addressName2)).trim());
        }
        if(!"".equals(CommonUtils.toString(map.get(addressName3)).trim())){
            addrList.add(CommonUtils.toString(map.get(addressName3)).trim());
        }
        if(!"".equals(CommonUtils.toString(map.get(addressName4)).trim())){
            addrList.add(CommonUtils.toString(map.get(addressName4)).trim());
        }
//        if(!"".equals(CommonUtils.toString(map.get(addressName5)).trim())){
//            addrList.add(CommonUtils.toString(map.get(addressName5)).trim());
//        }
//        if(!"".equals(CommonUtils.toString(map.get(addressName6)).trim())){
//            addrList.add(CommonUtils.toString(map.get(addressName6)).trim());
//        }
//        if(!"".equals(CommonUtils.toString(map.get(addressName7)).trim())){
//            addrList.add(CommonUtils.toString(map.get(addressName7)).trim());
//        }
//        if(!"".equals(CommonUtils.toString(map.get(addressName8)).trim())){
//            addrList.add(CommonUtils.toString(map.get(addressName8)).trim());
//        }
        if(addrList.size()==0) {
            map.put(addressName1,"");
            map.put(addressName2,"");
            map.put(addressName3,"");
            map.put(addressName4,"");
//            map.put(addressName5,"");
//            map.put(addressName6,"");
//            map.put(addressName7,"");
//            map.put(addressName8,"");
        } else if(addrList.size()==1) {
            map.put(addressName1,addrList.get(0));
            map.put(addressName2,"");
            map.put(addressName3,"");
            map.put(addressName4,"");
//            map.put(addressName5,"");
//            map.put(addressName6,"");
//            map.put(addressName7,"");
//            map.put(addressName8,"");
        } else if(addrList.size()==2) {
            map.put(addressName1,addrList.get(0));
            map.put(addressName2,addrList.get(1));
            map.put(addressName3,"");
            map.put(addressName4,"");
//            map.put(addressName5,"");
//            map.put(addressName6,"");
//            map.put(addressName7,"");
//            map.put(addressName8,"");
        } else if(addrList.size()==3) {
            map.put(addressName1,addrList.get(0));
            map.put(addressName2,addrList.get(1));
            map.put(addressName3,addrList.get(2));
            map.put(addressName4,"");
//            map.put(addressName5,"");
//            map.put(addressName6,"");
//            map.put(addressName7,"");
//            map.put(addressName8,"");
        } else if(addrList.size()==4) {
            map.put(addressName1,addrList.get(0));
            map.put(addressName2,addrList.get(1));
            map.put(addressName3,addrList.get(2));
            map.put(addressName4,addrList.get(3));
//            map.put(addressName5,"");
//            map.put(addressName6,"");
//            map.put(addressName7,"");
//            map.put(addressName8,"");
        } 
//        else if(addrList.size()==5) {
//            map.put(addressName1,addrList.get(0));
//            map.put(addressName2,addrList.get(1));
//            map.put(addressName3,addrList.get(2));
//            map.put(addressName4,addrList.get(3));
//            map.put(addressName5,addrList.get(4));
//            map.put(addressName6,"");
//            map.put(addressName7,"");
//            map.put(addressName8,"");
//        } else if(addrList.size()==6) {
//            map.put(addressName1,addrList.get(0));
//            map.put(addressName2,addrList.get(1));
//            map.put(addressName3,addrList.get(2));
//            map.put(addressName4,addrList.get(3));
//            map.put(addressName5,addrList.get(4));
//            map.put(addressName6,addrList.get(5));
//            map.put(addressName7,"");
//            map.put(addressName8,"");
//        } else if(addrList.size()==7) {
//            map.put(addressName1,addrList.get(0));
//            map.put(addressName2,addrList.get(1));
//            map.put(addressName3,addrList.get(2));
//            map.put(addressName4,addrList.get(3));
//            map.put(addressName5,addrList.get(4));
//            map.put(addressName6,addrList.get(5));
//            map.put(addressName7,addrList.get(6));
//            map.put(addressName8,"");
//        } else if(addrList.size()==8) {
//            map.put(addressName1,addrList.get(0));
//            map.put(addressName2,addrList.get(1));
//            map.put(addressName3,addrList.get(2));
//            map.put(addressName4,addrList.get(3));
//            map.put(addressName5,addrList.get(4));
//            map.put(addressName6,addrList.get(5));
//            map.put(addressName7,addrList.get(6));
//            map.put(addressName8,addrList.get(7));
//        }
    }
}
