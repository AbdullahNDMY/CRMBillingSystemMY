/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : B_SSM
 * SERVICE NAME   : B_SSM_S03
 * FUNCTION       : processing exporting report sb04
 * FILE NAME      : B_RPT_SB04.java
 *
 * Copyright (C) 2011 NTTDATA Corporation
 * 
**********************************************************/

package nttdm.bsys.b_ssm.s03.b_rpt.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.dao.QueryDAO;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import nttdm.bsys.b_ssm.s03.b_rpt.data.B_RPT_Output;
import nttdm.bsys.b_ssm.s03.b_rpt.data.ContentType;
import nttdm.bsys.b_ssm.s03.data.B_SSM_S03_FieldSet;
import nttdm.bsys.b_ssm.utility.BLogicUtils;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.util.exception.ReportException;

/**
 * @author NTT Data Vietnam	
 * Class Blogic processing exporting report sb04
 * @param <P>
 */
public class B_RPT_SB04<T> implements IB_RPT<T>  {
	// Constants
	public static final String PATTERN_FILENAME_DATETIME = "yyMMddHHmmss";
	public static final String FILENAME_EXTENTION_XLS = ".xls";
	private static final String FILENAME_EXTENTION_ZIP = ".zip";
	private static final String NUMBER_FORMAT = "00";
	
	private List<Integer> infoIDArray = new ArrayList<Integer>();
	
	// Private properties
	private QueryDAO queryDAO = null;
	private String templateParentDir = null;

	/**
	 * Initialize 
	 * @param queryDAO
	 */
	public B_RPT_SB04(QueryDAO queryDAO) {
		this.setQueryDAO(queryDAO);
	}

	/* (non-Javadoc)
	 * @see nttdm.bsys.b_ssm.s03.b_rpt.common.IB_RPT#export(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public B_RPT_Output export(T inputParams) throws ReportException {		
		try {
			B_RPT_Output output = new B_RPT_Output();
			HashMap<String,	Object> inputParamMap = (HashMap<String, Object>) inputParams;
			String customerID = BLogicUtils.emptyValue(inputParamMap.get(B_SSM_S03_FieldSet.FIELD_CUSTOMERID), "");
			String subscriptionID = BLogicUtils.emptyValue(inputParamMap.get(B_SSM_S03_FieldSet.FIELD_SUBSCRIPTIONID), "");
			
			// Get report template stream
			String templatePath = BLogicUtils.emptyValue(inputParamMap.get(B_SSM_S03_FieldSet.FIELD_REPORTTEMPLATEPATH), "");
			File templateFile = new File(templatePath);
			templateParentDir = templateFile != null ? 
							    templateFile.getParent() : 
							    null;
			InputStream templateStream = new FileInputStream(templateFile);
			// Throw exception with null template stream
			if (templateStream == null) {
				throw new ReportException("Null report template stream");
			}
			
			// Get plan link ids
			String[] custPlanGrps = inputParamMap.get("selectedServiceIdArr") != null?
									   (String[])inputParamMap.get("selectedServiceIdArr"):null;
			// Throw exception with null selected plan link ids
			if (custPlanGrps == null || custPlanGrps.length == 0) {
				throw new ReportException("No selected customer plan link ids");
			}						   
			
			List<List<String>> allReport = new ArrayList<List<String>>();
			HashMap<String,  Object> map = new HashMap<String, Object>();
			String idCustPlan = BLogicUtils.emptyValue(inputParamMap.get(B_SSM_S03_FieldSet.FIELD_CUSTOMERPLANID), "");
			map.put("idCustPlan", idCustPlan);
            for (int i = 0; i < custPlanGrps.length; i++ ) {
                String idCustPlanGrp = custPlanGrps[i];
                map.put("idCustPlanGrp", idCustPlanGrp);
                // Get array id info     
                List<String> arrInfo = queryDAO.executeForObjectList("B_RPT_SB04.getArrayInfo", map);
                allReport.add(arrInfo);
            }
            infoIDArray = getArrayInfos(allReport);
            
			// Create report item 
			for (int i = 0; i < custPlanGrps.length; i++ ) {
				String custPlanGrp = custPlanGrps[i];
				templateStream = new FileInputStream(templateFile);
				createReport(templateStream, custPlanGrp, i + 1, inputParamMap, output);
				templateStream.close();
			}
			
			// Set package name
			String packageName = getReportFileName(customerID, subscriptionID);
			output.setPackageName(packageName);
			
			return output;
		} catch (Exception e) {			
			e.printStackTrace();
			throw new ReportException(e.getMessage());
		}
		
	}
	
	/**
	 * Create report with specific cust plan link id
	 * @throws JRException 
	 * @throws IOException 
	 */
	private void createReport(InputStream templateStream, 
								String idCustPlanGrp,
								int rptIndex,
								HashMap<String, Object> inputParams,
								B_RPT_Output rptOutput) throws JRException, IOException {		
		String customerID = BLogicUtils.emptyValue(inputParams.get(B_SSM_S03_FieldSet.FIELD_CUSTOMERID), "");
		String subscriptionID = BLogicUtils.emptyValue(inputParams.get(B_SSM_S03_FieldSet.FIELD_SUBSCRIPTIONID), "");
		String idCustPlan = BLogicUtils.emptyValue(inputParams.get(B_SSM_S03_FieldSet.FIELD_CUSTOMERPLANID), "");
		
		inputParams.put("idCustPlanGrp", idCustPlanGrp);
		inputParams.put("idCustPlan", idCustPlan);
		
		// Create datasource
        List<HashMap<String, Object>> equipmentInfo = new ArrayList<HashMap<String, Object>>();
        equipmentInfo = queryDAO.executeForObjectList("B_RPT_SB04.getRackEquipmentLocationInfo", inputParams);
        List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> dataItem = new HashMap<String, Object>();
        dataItem.put(B_SSM_S03_FieldSet.FIELD_RACKEQUIPMENTINFO, equipmentInfo);
        data.add(dataItem);
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data);
		
		// Report params
		Map<String, Object> rptParams = new HashMap<String, Object>();			
		createReportParams(rptParams, inputParams);
		//init header info data
		setHeaderInfo(rptParams);
		
		// Report design
		JasperDesign rptDesign = JRXmlLoader.load(templateStream);
		JasperReport rpt = JasperCompileManager.compileReport(rptDesign);
		JasperPrint rptPrint = JasperFillManager.fillReport(rpt, rptParams, dataSource);
		
		// Get report stream 
		ByteArrayOutputStream rptOutputStream = new ByteArrayOutputStream();
		JRXlsExporter exportManager = new JRXlsExporter();
		exportManager.setParameter(JRXlsExporterParameter.JASPER_PRINT, rptPrint);
		exportManager.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, rptOutputStream);
		exportManager.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
		
		// Export
		exportManager.exportReport(); 
		rptOutputStream.close();
		
		// Add report
		byte[] rptByteArray = rptOutputStream == null ? null : rptOutputStream.toByteArray();
		if (rptByteArray != null) {			
			ByteArrayInputStream rptInputStream = new ByteArrayInputStream(rptByteArray);
			rptOutput.getInputStreams().add(rptInputStream);
			rptOutput.getFileNames().add(getReportFileName(customerID, subscriptionID, rptIndex));
			rptOutput.setContentType(ContentType.MS_EXCEL);
		}
	}
	
	/**
     * init header info data
     * @param rptParams
     */
    private void setHeaderInfo(Map<String, Object> rptParams) {
        List<Map<String, Object>> headerInfoList = new ArrayList<Map<String, Object>>();
        Map<String, Object> headerInfo = new HashMap<String, Object>();
        headerInfo.put("COMPANY_NAME", CommonUtils.toString(rptParams.get("COMPANY_NAME")));
        //headerInfo.put("COMPANY_ADDRESS123", CommonUtils.toString(rptParams.get("COMPANY_ADDRESS123")));
        headerInfo.put("COMPANY_ADDRESS1", CommonUtils.toString(rptParams.get("COMPANY_ADDRESS1")));
        headerInfo.put("COMPANY_ADDRESS2", CommonUtils.toString(rptParams.get("COMPANY_ADDRESS2")));
        headerInfo.put("COMPANY_ADDRESS3", CommonUtils.toString(rptParams.get("COMPANY_ADDRESS3")));
        headerInfo.put("COMPANY_ADDRESS4", CommonUtils.toString(rptParams.get("COMPANY_ADDRESS4")));
        headerInfo.put("COLON", ":");
        headerInfo.put("COMPANY_TEL_NO_TITLE", "Tel : " + CommonUtils.toString(rptParams.get("COMPANY_TEL_NO"))
                                                + " Fax : " + CommonUtils.toString(rptParams.get("COMPANY_FAX_NO")));
//        headerInfo.put("COMPANY_TEL_NO", CommonUtils.toString(rptParams.get("COMPANY_TEL_NO")));
//        headerInfo.put("COMPANY_FAX_NO_TITLE", "Fax");
//        headerInfo.put("COMPANY_FAX_NO", CommonUtils.toString(rptParams.get("COMPANY_FAX_NO")));
        headerInfo.put("COMPANY_REG_NO_TITLE", "Reg. No");
        headerInfo.put("COMPANY_REG_NO", CommonUtils.toString(rptParams.get("COMPANY_REG_NO")));
        headerInfo.put("COMPANY_GST_REG_NO_TITLE", "GST Reg. No.");
        headerInfo.put("COMPANY_GST_REG_NO", CommonUtils.toString(rptParams.get("COMPANY_GST_REG_NO")));
        headerInfo.put("COMPANY_WEBSITE_TITLE", "Website");
        headerInfo.put("COMPANY_WEBSITE", CommonUtils.toString(rptParams.get("COMPANY_WEBSITE")));
        headerInfo.put("DATE_TITLE", "Date");
        headerInfo.put("REF_TITLE", "Ref");
        headerInfo.put("CUSTOMER_NAME2_TITLE", "To");
        headerInfo.put("CUSTOMER_NAME2", CommonUtils.toString(rptParams.get("CUSTOMER_NAME2")));
        headerInfo.put("CUSTOMER_ADDRESS1", CommonUtils.toString(rptParams.get("CUSTOMER_ADDRESS1")));
        headerInfo.put("CUSTOMER_ADDRESS2", CommonUtils.toString(rptParams.get("CUSTOMER_ADDRESS2")));
        headerInfo.put("CUSTOMER_ADDRESS3", CommonUtils.toString(rptParams.get("CUSTOMER_ADDRESS3")));
        headerInfo.put("CUSTOMER_ADDRESS4", CommonUtils.toString(rptParams.get("CUSTOMER_ADDRESS4")));
        headerInfo.put("CUSTOMER_TEL_NO_TITLE", "Tel");
        headerInfo.put("CUSTOMER_TEL_NO", CommonUtils.toString(rptParams.get("CUSTOMER_TEL_NO")));
        headerInfo.put("CUSTOMER_FAX_NO_TITLE", "Fax");
        headerInfo.put("CUSTOMER_FAX_NO", CommonUtils.toString(rptParams.get("CUSTOMER_FAX_NO")));
        headerInfo.put("CUSTOMER_ATTENTION_TITLE", "Attn");
        headerInfo.put("CUSTOMER_ATTENTION", CommonUtils.toString(rptParams.get("CUSTOMER_ATTENTION")));
        headerInfo.put("CUSTOMER_ID_TITLE", "  Customer ID");
        headerInfo.put("CUSTOMER_ID", CommonUtils.toString(rptParams.get("CUSTOMER_ID")));
        headerInfo.put("SUBSCRIPTION_ID_TITLE", "  Subscription ID");
        headerInfo.put("SUBSCRIPTION_ID", CommonUtils.toString(rptParams.get("SUBSCRIPTION_ID")));
        headerInfo.put("SERVICE_NAME_TITLE", "  Description");
        headerInfo.put("SERVICE_NAME", CommonUtils.toString(rptParams.get("SERVICE_NAME")));
        headerInfo.put("PERSON_IN_CHARGE_TITLE", "  Person in Charge");
        headerInfo.put("PERSON_IN_CHARGE", CommonUtils.toString(rptParams.get("PERSON_IN_CHARGE")));
        headerInfo.put("SERVICE_COMMISSION_DATE_TITLE", "  Service Completion Date");
        headerInfo.put("SERVICE_COMMISSION_DATE", CommonUtils.toString(rptParams.get("SERVICE_COMMISSION_DATE")));
        headerInfo.put("USER_NAME_TITLE", "Account Manager");
        headerInfo.put("USER_NAME", CommonUtils.toString(rptParams.get("USER_NAME")));
        headerInfoList.add(headerInfo);
        
        JRMapCollectionDataSource headerInfoDS = new JRMapCollectionDataSource(headerInfoList);
        rptParams.put("headerInfo", headerInfoDS);
    }

	/**
	 * Get report file name with specific index
	 * @param rptIndex
	 * @return String
	 */
	private String getReportFileName(String customerID, String subscriptionID, int rptIndex) {		
		SimpleDateFormat dateFormat = new SimpleDateFormat(PATTERN_FILENAME_DATETIME);
		String fileDateTimeStr = dateFormat.format(new Date());
		DecimalFormat numberFormat = new DecimalFormat(NUMBER_FORMAT);
		return "CR_" + customerID + "_" + subscriptionID + "_" + fileDateTimeStr + "_" + numberFormat.format(rptIndex) + FILENAME_EXTENTION_XLS;
	}

	/**
	 * Get report file name
	 * @param customerID
	 * @param subscriptionID 
	 */
	private String getReportFileName(String customerID, String subscriptionID) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(PATTERN_FILENAME_DATETIME);
		String fileDateTimeStr = dateFormat.format(new Date());
		return "CR_" + customerID + "_" + subscriptionID + "_" + fileDateTimeStr + FILENAME_EXTENTION_ZIP;
	}

	/**
	 * Create report params
	 * @param params
	 * @param inputParams 
	 */
	private void createReportParams(Map<String, Object> params, HashMap<String, Object> inputParams) {
		if (params == null || inputParams == null) {
			return;
		}		
		
		// Get customer info
		List<HashMap<String, Object>> custInfoArray = new ArrayList<HashMap<String, Object>>();
		custInfoArray = queryDAO.executeForObjectList("B_RPT.getCustomerInformation", inputParams);
		HashMap<String, Object> customerInfo = custInfoArray.size() > 0 ? 
											   custInfoArray.get(0) : 
											   new HashMap<String, Object>();
		CommonUtils.fixAddress4n(customerInfo, "CUSTOMER_ADDRESS4");
		
		// Get company info
		List<HashMap<String, Object>> companyInfoArray = new ArrayList<HashMap<String, Object>>();
		companyInfoArray = queryDAO.executeForObjectList("B_RPT.getCompanyInformation", inputParams);
		HashMap<String, Object> companyInfo = companyInfoArray.size() > 0 ? 
											  companyInfoArray.get(0) : 
											  new HashMap<String, Object>();
			
		// Get service info
		List<HashMap<String, Object>> serviceInfoArray = new ArrayList<HashMap<String, Object>>();
		serviceInfoArray = queryDAO.executeForObjectList("B_RPT_SB04.getServiceInfo" , inputParams);
		HashMap<String, Object> serviceInfo = serviceInfoArray.size() > 0 ? 
											  serviceInfoArray.get(0) :  
											  new HashMap<String, Object>();
		
		params.put(B_SSM_S03_FieldSet.FIELD_RPT_INFOIDARRAY, infoIDArray);	
											  
		// Logo path
		params.put(B_SSM_S03_FieldSet.FIELD_RPT_FILEPATH_LOGO, 
				   BLogicUtils.emptyValue(inputParams.get(B_SSM_S03_FieldSet.FIELD_REPORTLOGOPATH), ""));		
		
		// Subreport dir
		params.put(B_SSM_S03_FieldSet.FIELD_RPT_SUBREPORT_DIR, 
				   templateParentDir != null?
				   templateParentDir+"/" :		   
				   BLogicUtils.emptyValue(inputParams.get(B_SSM_S03_FieldSet.FIELD_SUBREPORTPATH), ""));
		
		// Company name
		params.put(B_SSM_S03_FieldSet.FIELD_RPT_COMPANYNAME, 
				   BLogicUtils.emptyValue(companyInfo.get(B_SSM_S03_FieldSet.FIELD_RPT_COMPANYNAME), ""));	
		
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
		
		params.put("COMPANY_ADDRESS1", companyAddress1);
		params.put("COMPANY_ADDRESS2", companyAddress2);
		params.put("COMPANY_ADDRESS3", companyAddress3);
		params.put("COMPANY_ADDRESS4", companyAddress4);
		addressDisplayChange(params, "COMPANY_ADDRESS");
//		String companyAddress123 = "";
//		if(!"".equals(companyAddress1)&&!"".equals(companyAddress2)&&!"".equals(companyAddress3)) {
//		    companyAddress123 = companyAddress1 + " " + companyAddress2 + " " + companyAddress3;
//		} else if("".equals(companyAddress1)&&!"".equals(companyAddress2)&&!"".equals(companyAddress3)) {
//            companyAddress123 = companyAddress2 + " " + companyAddress3;
//        } else if(!"".equals(companyAddress1)&&"".equals(companyAddress2)&&!"".equals(companyAddress3)) {
//            companyAddress123 = companyAddress1 + " " + companyAddress3;
//        } else if(!"".equals(companyAddress1)&&!"".equals(companyAddress2)&&"".equals(companyAddress3)) {
//            companyAddress123 = companyAddress1 + " " + companyAddress2;
//        } else if("".equals(companyAddress1)&&"".equals(companyAddress2)&&!"".equals(companyAddress3)) {
//            companyAddress123 = companyAddress3;
//        } else if("".equals(companyAddress1)&&!"".equals(companyAddress2)&&"".equals(companyAddress3)) {
//            companyAddress123 = companyAddress2;
//        } else if(!"".equals(companyAddress1)&&"".equals(companyAddress2)&&"".equals(companyAddress3)) {
//            companyAddress123 = companyAddress1;
//        }
//		if(!"".equals(companyAddress123)&&!"".equals(companyAddress4)) {
//		    params.put("COMPANY_ADDRESS123", companyAddress123);
//		    params.put("COMPANY_ADDRESS4", companyAddress4);
//		} else if("".equals(companyAddress123)&&!"".equals(companyAddress4)) {
//            params.put("COMPANY_ADDRESS123", companyAddress4);
//            params.put("COMPANY_ADDRESS4", "");
//        } else if(!"".equals(companyAddress123)&&"".equals(companyAddress4)) {
//            params.put("COMPANY_ADDRESS123", companyAddress123);
//            params.put("COMPANY_ADDRESS4", "");
//        } else {
//            params.put("COMPANY_ADDRESS123", "");
//            params.put("COMPANY_ADDRESS4", "");
//        }
		
		// Company fax no
		params.put(B_SSM_S03_FieldSet.FIELD_RPT_COMPANYFAXNO, 
				   BLogicUtils.emptyValue(companyInfo.get(B_SSM_S03_FieldSet.FIELD_RPT_COMPANYFAXNO), ""));
		
		// Company tel no
		params.put(B_SSM_S03_FieldSet.FIELD_RPT_COMPANYTELNO, 
				   BLogicUtils.emptyValue(companyInfo.get(B_SSM_S03_FieldSet.FIELD_RPT_COMPANYTELNO), ""));
		
		// Company reg no
		params.put(B_SSM_S03_FieldSet.FIELD_RPT_COMPANYREGNO, 
				   BLogicUtils.emptyValue(companyInfo.get(B_SSM_S03_FieldSet.FIELD_RPT_COMPANYREGNO), ""));
		// Company Gst reg no
        params.put("COMPANY_GST_REG_NO", BLogicUtils.emptyValue(companyInfo.get("COMPANY_GST_REG_NO"), ""));
		// Website
        params.put("COMPANY_WEBSITE", 
                   BLogicUtils.emptyValue(companyInfo.get("COM_WEBSITE"), ""));
		
		// Customer name
		params.put("CUSTOMER_NAME2", 
				   BLogicUtils.emptyValue(customerInfo.get(B_SSM_S03_FieldSet.FIELD_RPT_CUSTOMERNAME), ""));
		
		// Customer address1
		params.put(B_SSM_S03_FieldSet.FIELD_CUSTOMERADDRESS1, 
				   BLogicUtils.emptyValue(customerInfo.get(B_SSM_S03_FieldSet.FIELD_CUSTOMERADDRESS1), ""));
		
		// Customer address2
		params.put(B_SSM_S03_FieldSet.FIELD_RPT_CUSTOMERADDRESS2, 
				   BLogicUtils.emptyValue(customerInfo.get(B_SSM_S03_FieldSet.FIELD_RPT_CUSTOMERADDRESS2), ""));
		
		// Customer address3
		params.put(B_SSM_S03_FieldSet.FIELD_RPT_CUSTOMERADDRESS3, 
				   BLogicUtils.emptyValue(customerInfo.get(B_SSM_S03_FieldSet.FIELD_RPT_CUSTOMERADDRESS3), ""));
		
		// Customer address4
        params.put("CUSTOMER_ADDRESS4", BLogicUtils.emptyValue(customerInfo.get("CUSTOMER_ADDRESS4"), ""));
        //change the display address
        addressDisplayChange(params, "CUSTOMER_ADDRESS");
        
        String customerType = CommonUtils.toString(customerInfo.get("CUSTOMER_TYPE")).trim();
        String custTelNo = "";
        String custFaxNo = "";
        if ("CORP".equals(customerType)){
            custTelNo = CommonUtils.toString(customerInfo.get("CUSTOMER_CO_TEL_NO"));
            custFaxNo = CommonUtils.toString(customerInfo.get("CUSTOMER_CO_FAX_NO"));
        } else if ("CONS".equals(customerType)){
            custTelNo = CommonUtils.toString(customerInfo.get("CUSTOMER_HOME_TEL_NO"));
            custFaxNo = CommonUtils.toString(customerInfo.get("CUSTOMER_FAX_TEL_NO"));
        }
        params.put("CUSTOMER_TEL_NO", custTelNo);
        params.put("CUSTOMER_FAX_NO", custFaxNo);
		
		// Customer attention
		params.put(B_SSM_S03_FieldSet.FIELD_RPT_CUSTOMERATTENTION, 
				   BLogicUtils.emptyValue(customerInfo.get(B_SSM_S03_FieldSet.FIELD_RPT_CUSTOMERCONTACTNAME), ""));
		//USER_NAME
		params.put("USER_NAME", BLogicUtils.emptyValue(customerInfo.get("USER_NAME"), ""));
		
		// Customer ID
		params.put(B_SSM_S03_FieldSet.FIELD_RPT_CUSTOMERID, 
				   BLogicUtils.emptyValue(inputParams.get(B_SSM_S03_FieldSet.FIELD_CUSTOMERID), ""));
		
		// Subscription ID
		params.put(B_SSM_S03_FieldSet.FIELD_RPT_SUBSCRIPTIONID, 
				   BLogicUtils.emptyValue(inputParams.get(B_SSM_S03_FieldSet.FIELD_SUBSCRIPTIONID), ""));
		
		// Description
		params.put(B_SSM_S03_FieldSet.FIELD_RPT_SERVICENAME, 
				   BLogicUtils.emptyValue(serviceInfo.get(B_SSM_S03_FieldSet.FIELD_RPT_SERVICENAME), ""));
		String loginUserID = CommonUtils.toString(inputParams.get(B_SSM_S03_FieldSet.FIELD_LOGONUSERID));
		Map<String, Object> loginUserInfo = queryDAO.executeForMap("B_RPT.getUserInfo", loginUserID);
		// Person in charge
		params.put(B_SSM_S03_FieldSet.FIELD_RPT_PERSONINCHARGE, CommonUtils.toString(loginUserInfo.get("USER_NAME")));
		
		// Service commission date
		params.put(B_SSM_S03_FieldSet.FIELD_RPT_SERVICECOMMISSIONDATE, 
				   BLogicUtils.emptyValue(serviceInfo.get(B_SSM_S03_FieldSet.FIELD_RPT_SERVICECOMMISSIONDATE), ""));
		
		// Service product description
		params.put(B_SSM_S03_FieldSet.FIELD_RPT_PRODUCTSERVICEDESCRIPTION, 
				   BLogicUtils.emptyValue(serviceInfo.get(B_SSM_S03_FieldSet.FIELD_RPT_PRODUCTSERVICEDESCRIPTION), ""));
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

	/**
	 * Set queryDAO
	 * @param queryDAO
	 */
	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}

	/**
	 * Get queryDAO
	 * @return queryDAO
	 */
	public QueryDAO getQueryDAO() {
		return queryDAO;
	}

	/**
     * get report id list
     * @param servicesReport
     * @return
     */
    private List<Integer> getArrayInfos(List<List<String>> servicesReport){
        List<Integer> retArrayInfo = new ArrayList<Integer>();
        List<String> arrayInfo = new ArrayList<String>();
        List<String> allSubArrayInfo = queryDAO.executeForObjectList("B_RPT_SB01.selectServiceBasicInfo", null);
        for(String type : allSubArrayInfo) {
            boolean allAllowReportFlg = true;
            if (servicesReport!=null && 0<servicesReport.size()) {
                for(List<String> allowReportArrayInfo : servicesReport) {
                    if(!allowReportArrayInfo.contains(type)) {
                        allAllowReportFlg = false;
                        break;
                    }
                }
            } else {
                continue;
            }
            if (allAllowReportFlg) {
                arrayInfo.add(type);
            }
        }
        for(String info : arrayInfo) {
            retArrayInfo.add(Integer.parseInt(info));
        }
        return retArrayInfo;
    }
}
