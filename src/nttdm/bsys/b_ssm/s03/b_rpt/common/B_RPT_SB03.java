/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : B_RPT_SB01
 * SERVICE NAME   : B_RPT_SB01
 * FUNCTION       : Doc Report
 * FILE NAME      : B_RPT_SB01.java
 * 
 * Copyright © 2011 NTTDATA Corporation
 *
 * History
 * 
 * 
**********************************************************/
package nttdm.bsys.b_ssm.s03.b_rpt.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
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
import nttdm.bsys.b_ssm.s03.b_rpt.data.B_RPT_Output;
import nttdm.bsys.b_ssm.s03.b_rpt.data.B_RPT_SB03_Input;
import nttdm.bsys.b_ssm.s03.b_rpt.data.ContentType;
import nttdm.bsys.b_ssm.s03.data.B_SSM_S03_FieldSet;
import nttdm.bsys.b_ssm.utility.BLogicUtils;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.ZipUtil;
import nttdm.bsys.util.exception.ReportException;

import org.apache.struts.util.MessageResources;

/**
 * B_RPT_SB01<br>
 * <ul>
 * <li>A class to process exporting Subscription information and services (mode "complete")
 * </ul>
 * <br>
 * @author  khaln
 * @version 1.0
 */
public class B_RPT_SB03 extends AB_RPT<B_RPT_SB03_Input> {

	private static final String PDF_EXTENSION = ".pdf";
	private static final String ZIP_EXTENSION = ".zip";
	private static final String SQL_GET_SERVICE_INFO = "B_SSM_S03.getServiceInfo";
	private static final String SQL_GET_REPORT_DISPLAY = "B_SSM_S03.getReportDisplay";
	private static final String SQL_GET_SUBSCRIPTION_INFO = "B_SSM_S03.getSubscriptionInfo";
	private static final String SQL_GET_IT_CONTACT_INFO = "B_SSM_S03.getITContactInfo";
	private static final String SQL_GET_FIREWALL_IP_INFO = "B_SSM_S03.getIPInfo";
	private static final String SQL_GET_FIREWALL_POLICY_INFO = "B_SSM_S03.getPolicyInfo";
	private static final String SQL_GET_SERVER_INFO = "B_SSM_S03.getServerInfo";
	private static final String SQL_GET_POP_ACCOUNT_INFO = "B_SSM_S03.getPOPAccountInfo";
	private static final String SQL_GET_DNS_DOMAIN_INFO = "B_SSM_S03.getDNSDomainInfo";
	private static final String SQL_GET_RACK_EQUIPMENT_INFO = "B_SSM_S03.getRackEquipmentInfo";
	private static final String DATE_FORMAT = "yyMMddHHmmss";
	private static final String NUMBER_FORMAT = "00";
	
	private MessageResources messageResource;
		
	public B_RPT_SB03(QueryDAO queryDAO) {
		super(queryDAO);
		messageResource = MessageResources.getMessageResources("application-messages");
	}

	public B_RPT_Output export(B_RPT_SB03_Input params) throws ReportException {
		Map<String, Object> output4Report = new HashMap<String, Object>();
		List<Map<String, Object>> companyInfo = queryDAO.executeForObjectList(SQL_GET_SUBSCRIPTION_INFO, params);
		List<Map<String, Object>> serviceInfo = queryDAO.executeForObjectList(SQL_GET_SERVICE_INFO, params);
		
		String loginId = params.getLoginId();
		String loginName = queryDAO.executeForObject("B_RPT_SB03.getUserName", loginId, String.class);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
		DecimalFormat numberFormat = new DecimalFormat(NUMBER_FORMAT);
		B_RPT_Output result = new B_RPT_Output();
		result.setContentType(ContentType.ZIP);
		String zipFileName = "CR_" + params.getCustomerID()
                            + "_" + params.getSubscriptionID()
                            + "_" + dateFormat.format(new Date());
		result.setFileName(zipFileName + ZIP_EXTENSION);
				
		String[] REPORT_TEMP_PATH = new String[serviceInfo.size()];
		
		if (companyInfo.size() > 0)
		{
			Map<String, Object> input1 = new HashMap<String, Object>();
			
			output4Report = companyInfo.get(0);
			
			for(Map<String, Object> mapcompayInfo : companyInfo) {
			    String instantAddressType = CommonUtils.toString(mapcompayInfo.get("instantAddressType")).trim();
			    if(!CommonUtils.isEmpty(instantAddressType)) {
			        Object instantAddressID = mapcompayInfo.get("instantAddressID");
			        Object instantAddressLine1 = mapcompayInfo.get("instantAddressLine1");
			        Object instantAddressLine2 = mapcompayInfo.get("instantAddressLine2");
			        Object instantAddressLine3 = mapcompayInfo.get("instantAddressLine3");
			        Object instantAddressCodeCountry = mapcompayInfo.get("instantAddressCodeCountry");
			        if("A1".equals(instantAddressType)) {
			            output4Report.put("instantAddressID", instantAddressID);
			            output4Report.put("instantAddressLine1", instantAddressLine1);
			            output4Report.put("instantAddressLine2", instantAddressLine2);
			            output4Report.put("instantAddressLine3", instantAddressLine3);
			            output4Report.put("instantAddressCodeCountry", instantAddressCodeCountry);
			        } else if("A2".equals(instantAddressType)) {
			            output4Report.put("instantAddress2ID", instantAddressID);
                        output4Report.put("instantAddress2Line1", instantAddressLine1);
                        output4Report.put("instantAddress2Line2", instantAddressLine2);
                        output4Report.put("instantAddress2Line3", instantAddressLine3);
                        output4Report.put("instantAddress2CodeCountry", instantAddressCodeCountry);
			        }
			    }
			}
			
			String companyZipCode = CommonUtils.toString(output4Report.get("companyZipCode"));
			String companyCountry = CommonUtils.toString(output4Report.get("companyCountry"));
			if(!CommonUtils.isEmpty(companyZipCode)&&!CommonUtils.isEmpty(companyCountry)) {
			    output4Report.put("addressLine4", companyZipCode + " " + companyCountry);
			} else if(CommonUtils.isEmpty(companyZipCode)&&!CommonUtils.isEmpty(companyCountry)) {
			    output4Report.put("addressLine4", companyCountry);
			} else if(!CommonUtils.isEmpty(companyZipCode)&&CommonUtils.isEmpty(companyCountry)) {
			    output4Report.put("addressLine4", companyZipCode);
			} else {
			    output4Report.put("addressLine4", "");
			}
			
			CommonUtils.fixAddress4n(output4Report, "addressLine4");
			CommonUtils.fixAddress4n(output4Report, "customerCodeCountry");
			CommonUtils.fixAddress4n(output4Report, "instantAddressCodeCountry");
			CommonUtils.fixAddress4n(output4Report, "instantAddress2CodeCountry");
            
			output4Report.put("addressLine5", "Tel : " + CommonUtils.toString(output4Report.get("companyTelNo"))
	                + " Fax : " + CommonUtils.toString(output4Report.get("companyFaxNo")));
			output4Report.put("addressLine6", "Reg. No : " + CommonUtils.toString(output4Report.get("companyRegNo")));
			//output4Report.put("addressLine7", "GST Reg. No. : " + CommonUtils.toString(output4Report.get("companyGstRegNo")));
			output4Report.put("addressLine7", "Website : " + CommonUtils.toString(output4Report.get("companyWebsite")));
			//change the display address
	        addressDisplayChange(output4Report, "addressLine");
			String customerType = CommonUtils.toString(output4Report.get("customerType")).trim();
			if ("CORP".equals(customerType)){
			    output4Report.put("customerTelNo", output4Report.get("coTelNo"));
			    output4Report.put("customerFaxNo", output4Report.get("coFaxNo"));
			} else if ("CONS".equals(customerType)){
			    output4Report.put("customerTelNo", output4Report.get("homeTelNo"));
                output4Report.put("customerFaxNo", output4Report.get("homeFaxNo"));
			}
			
			Object contactID = output4Report.get(B_SSM_S03_FieldSet.FIELD_IT_CONTACT_ID);
			Map<String, Object> contactInput = new HashMap<String, Object>();
			contactInput.put(B_SSM_S03_FieldSet.FIELD_IT_CONTACT_ID, contactID);
			List<Map<String, Object>> listContact = queryDAO.executeForObjectList(
					SQL_GET_IT_CONTACT_INFO, contactInput);
			JRMapCollectionDataSource jrITContactDs = new JRMapCollectionDataSource(listContact);
			
            Map<String, Object> cpeConfigInput = new HashMap<String, Object>();
            cpeConfigInput.put("subscriptionID", params.getSubscriptionID());
            List<Map<String, Object>> listCPEConfig = queryDAO.executeForObjectList("B_SSM_S03.getCPEConfInfo", cpeConfigInput);
            //add title
            addTitleNameCPEConfig(listCPEConfig);
            JRMapCollectionDataSource jrCPEConfigDs = new JRMapCollectionDataSource(listCPEConfig);
            JRMapCollectionDataSource jrIPAdressDs = new JRMapCollectionDataSource(listCPEConfig);
			
			Object firewallID = output4Report.get(B_SSM_S03_FieldSet.FIELD_FIREWALL_ID);
			Map<String, Object> firewallInput = new HashMap<String, Object>();
			firewallInput.put(B_SSM_S03_FieldSet.FIELD_FIREWALL_ID, firewallID);
			List<Map<String, Object>> listFirewallIP = queryDAO.executeForObjectList(
					SQL_GET_FIREWALL_IP_INFO, firewallInput);
			JRMapCollectionDataSource jrFirewallIPDs = new JRMapCollectionDataSource(listFirewallIP);
			
			List<Map<String, Object>> listFirewallPolicy = queryDAO.executeForObjectList(
					SQL_GET_FIREWALL_POLICY_INFO, firewallInput);
			JRMapCollectionDataSource jrFirewallPilicyDs = new JRMapCollectionDataSource(listFirewallPolicy);
			
			Object serverHeadID = output4Report.get(B_SSM_S03_FieldSet.FIELD_SERVER_ID);
			Map<String, Object> serverInput = new HashMap<String, Object>();
			serverInput.put(B_SSM_S03_FieldSet.FIELD_SERVER_ID, serverHeadID);
			List<Map<String, Object>> listServer = queryDAO.executeForObjectList(
					SQL_GET_SERVER_INFO, serverInput);
			//add server title
			addTitleNameServerInfo(listServer);
			JRMapCollectionDataSource jrServerDs = new JRMapCollectionDataSource(listServer);
			
			Object popAccID = output4Report.get(B_SSM_S03_FieldSet.FIELD_MAIL_ID);
			Map<String, Object> popAccInput = new HashMap<String, Object>();
			popAccInput.put(B_SSM_S03_FieldSet.FIELD_MAIL_ID, popAccID);
			List<Map<String, Object>> listPopAcc = queryDAO.executeForObjectList(
					SQL_GET_POP_ACCOUNT_INFO, popAccInput);
			JRMapCollectionDataSource jrPopAccDs = new JRMapCollectionDataSource(listPopAcc);
			
			//change the display MigrationCurrentServer
			migrationCurrentServerDisplayChange(output4Report);
			Object dnsZoneID = output4Report.get(B_SSM_S03_FieldSet.FIELD_DNS_ZONE_ID);
			Map<String, Object> dnsInput = new HashMap<String, Object>();
			dnsInput.put(B_SSM_S03_FieldSet.FIELD_DNS_ZONE_ID, dnsZoneID);
			List<Map<String, Object>> listDNSDomain = queryDAO.executeForObjectList(
					SQL_GET_DNS_DOMAIN_INFO, dnsInput);
			JRMapCollectionDataSource jrDnsZoneDs = new JRMapCollectionDataSource(listDNSDomain);
			
			Object rackEquipID = output4Report.get(B_SSM_S03_FieldSet.FIELD_RACK_EQUIP_ID);
			Map<String, Object> rackEquipInput = new HashMap<String, Object>();
			rackEquipInput.put(B_SSM_S03_FieldSet.FIELD_RACK_EQUIP_ID, rackEquipID);
			List<Map<String, Object>> listRackDetail = queryDAO.executeForObjectList(
					SQL_GET_RACK_EQUIPMENT_INFO, rackEquipInput);
			JRMapCollectionDataSource jrRackEquipDs = new JRMapCollectionDataSource(listRackDetail);
			
			
			List<List<String>> allReport = new ArrayList<List<String>>();
			List<String> reportDisplayInfo = new ArrayList<String>();
			for (int i=0; i<serviceInfo.size(); i++) {
			    String idCustPlanGrp = (String)serviceInfo.get(i).get("idCustPlanGrps");
                input1.put("idCustPlanGrp", idCustPlanGrp);
                List<Map<String, Object>> reportDisplay = queryDAO.executeForObjectList(SQL_GET_REPORT_DISPLAY, input1);
                List<String> arrInfo = new ArrayList<String>();
                for (int j=0; j<reportDisplay.size(); j++) {
                    arrInfo.add((String)reportDisplay.get(j).get(B_SSM_S03_FieldSet.FIELD_REPORT_DISPLAY_INFO));
                }
                allReport.add(arrInfo);
			}
			reportDisplayInfo = getArrayInfos(allReport);
			//Completion Report Remark
			String printedRemark = CommonUtils.toString(output4Report.get("printedRemark")).trim();
			
			for (int i=0; i<serviceInfo.size(); i++)
			{
			    Map<String, Object> serviceMap = serviceInfo.get(i);
				int step = 1;
				if (reportDisplayInfo.contains("14")){
				    //output4Report.get(B_SSM_S03_FieldSet.FIELD_IT_CONTACT_ID) != null)
                    output4Report.put(B_SSM_S03_FieldSet.FIELD_IT_CONTACT_LIST, jrITContactDs);
                    output4Report.put("isITContactDisplayFlg", "1");
                    output4Report.put("itContactOrder", Integer.toString(++step));
                    output4Report.put("completionDateOrder", Integer.toString(++step));
				} else {
				    output4Report.remove(B_SSM_S03_FieldSet.FIELD_IT_CONTACT_LIST);
				    output4Report.put("isITContactDisplayFlg", "0");
				    output4Report.put("completionDateOrder", Integer.toString(++step));
				}
				// Service Information
				if (reportDisplayInfo.contains("28")){
				    output4Report.put("serviceInfoOrder", Integer.toString(++step));
				} else {
				    output4Report.remove("serviceInfoOrder");
				}
				
				if (reportDisplayInfo.contains("18")) {
				    //output4Report.get(B_SSM_S03_FieldSet.FIELD_INSTANT_ADDRESS_ID) != null)
                    output4Report.put(B_SSM_S03_FieldSet.FIELD_INSTANT_ADDRESS_ORDER, Integer.toString(++step));
				    output4Report.put("instantAddress2Order", Integer.toString(++step));
				} else {
				    output4Report.remove(B_SSM_S03_FieldSet.FIELD_INSTANT_ADDRESS_ORDER);
				}
				
				if (reportDisplayInfo.contains("21")) //output4Report.get(B_SSM_S03_FieldSet.FIELD_MRTG_ID) != null)
					output4Report.put(B_SSM_S03_FieldSet.FIELD_MRTG_ORDER, Integer.toString(++step));
				else
					output4Report.remove(B_SSM_S03_FieldSet.FIELD_MRTG_ORDER);
				////output4Report.get(B_SSM_S03_FieldSet.FIELD_CPE_CONF_ID) != null)
				if (reportDisplayInfo.contains("22")) {
				    output4Report.put("ipAddressList", jrIPAdressDs);
                    output4Report.put(B_SSM_S03_FieldSet.FIELD_IP_ADDRESS_ORDER, Integer.toString(++step));
				} else {
				    output4Report.remove("ipAddressList");
				    output4Report.remove(B_SSM_S03_FieldSet.FIELD_IP_ADDRESS_ORDER);
				}
				
				//output4Report.get(B_SSM_S03_FieldSet.FIELD_CPE_CONF_ID) != null)
                if (reportDisplayInfo.contains("23")) {
                    output4Report.put("cPEConfigList", jrCPEConfigDs);
                    output4Report.put(B_SSM_S03_FieldSet.FIELD_CPE_CONF_ORDER, Integer.toString(++step));
                } else {
                    output4Report.remove("cPEConfigList");
                    output4Report.remove(B_SSM_S03_FieldSet.FIELD_CPE_CONF_ORDER);
                }
					
				if (reportDisplayInfo.contains("25")) //output4Report.get(B_SSM_S03_FieldSet.FIELD_FIREWALL_ID) != null)
				{
					output4Report.put(B_SSM_S03_FieldSet.FIELD_FIREWALL_ORDER, Integer.toString(++step));
					output4Report.put(B_SSM_S03_FieldSet.FIELD_FIREWALL_IP_LIST, jrFirewallIPDs);
					output4Report.put(B_SSM_S03_FieldSet.FIELD_FIREWALL_POLICY_LIST, jrFirewallPilicyDs);
				}
				else
				{
					output4Report.remove(B_SSM_S03_FieldSet.FIELD_FIREWALL_ORDER);
					output4Report.remove(B_SSM_S03_FieldSet.FIELD_FIREWALL_IP_LIST);
					output4Report.remove(B_SSM_S03_FieldSet.FIELD_FIREWALL_POLICY_LIST);
				}
				
				if (reportDisplayInfo.contains("26")) //output4Report.get(B_SSM_S03_FieldSet.FIELD_SERVER_ID) != null)
				{
					output4Report.put(B_SSM_S03_FieldSet.FIELD_SERVER_ORDER, Integer.toString(++step));	
					output4Report.put(B_SSM_S03_FieldSet.FIELD_SERVER_LIST, jrServerDs);
				}
				else
				{
					output4Report.remove(B_SSM_S03_FieldSet.FIELD_SERVER_ORDER);
					output4Report.remove(B_SSM_S03_FieldSet.FIELD_SERVER_LIST);
				}
				
				if (reportDisplayInfo.contains("17")) //output4Report.get(B_SSM_S03_FieldSet.FIELD_MAIL_ID) != null)
				{
					output4Report.put(B_SSM_S03_FieldSet.FIELD_POP_ACCOUNT_ORDER, Integer.toString(++step));
					output4Report.put(B_SSM_S03_FieldSet.FIELD_POP_ACCOUNT_LIST, jrPopAccDs);
				}
				else
				{
					output4Report.remove(B_SSM_S03_FieldSet.FIELD_POP_ACCOUNT_ORDER);
					output4Report.remove(B_SSM_S03_FieldSet.FIELD_POP_ACCOUNT_LIST);
				}
				
				if (reportDisplayInfo.contains("16")) //output4Report.get(B_SSM_S03_FieldSet.FIELD_MAIL_ID) != null)
					output4Report.put(B_SSM_S03_FieldSet.FIELD_EMAIL_SETUP_ORDER, Integer.toString(++step));
				else
					output4Report.remove(B_SSM_S03_FieldSet.FIELD_EMAIL_SETUP_ORDER);
				
				if (reportDisplayInfo.contains("19")) //output4Report.get(B_SSM_S03_FieldSet.FIELD_FTP_ID) != null)
					output4Report.put(B_SSM_S03_FieldSet.FIELD_FTP_INTERFACE_ORDER, Integer.toString(++step));
				else
					output4Report.remove(B_SSM_S03_FieldSet.FIELD_FTP_INTERFACE_ORDER);
				
				if (reportDisplayInfo.contains("24")) //output4Report.get(B_SSM_S03_FieldSet.FIELD_DNS_ZONE_ID) != null)
				{
					output4Report.put(B_SSM_S03_FieldSet.FIELD_DNS_ZONE_ORDER, Integer.toString(++step));
					output4Report.put(B_SSM_S03_FieldSet.FIELD_DNS_DOMAIN_LIST, jrDnsZoneDs);
				}
				else
				{
					output4Report.remove(B_SSM_S03_FieldSet.FIELD_DNS_ZONE_ORDER);
					output4Report.remove(B_SSM_S03_FieldSet.FIELD_DNS_DOMAIN_LIST);
				}
				
				if (reportDisplayInfo.contains("20")) //output4Report.get(B_SSM_S03_FieldSet.FIELD_RACK_EQUIP_ID) != null)
				{
					output4Report.put(B_SSM_S03_FieldSet.FIELD_RACK_EQUIP_ORDER, Integer.toString(++step));
					output4Report.put(B_SSM_S03_FieldSet.FIELD_RACK_EQUIP_LIST, jrRackEquipDs);
				}
				else
				{
					output4Report.remove(B_SSM_S03_FieldSet.FIELD_RACK_EQUIP_ORDER);
					output4Report.remove(B_SSM_S03_FieldSet.FIELD_RACK_EQUIP_LIST);
				}
				
				output4Report.put(B_SSM_S03_FieldSet.FIELD_HELPDESK_ORDER, Integer.toString(++step));
				output4Report.put("personInChargeOrder", Integer.toString(++step));
				//Remark order
				output4Report.put(B_SSM_S03_FieldSet.FIELD_REMARKS_ORDER, Integer.toString(++step));
			
				output4Report.put(B_SSM_S03_FieldSet.FIELD_SUBSCRIPTIONID, params.getSubscriptionID());
				output4Report.put(B_SSM_S03_FieldSet.FIELD_CUSTOMERID, params.getCustomerID());
		
				// Get report template stream
	            String templatePath = BLogicUtils.emptyValue(params.getReportPath(), "");
	            File templateFile = new File(templatePath);
	            String templateParentDir = templateFile != null ? 
	                                templateFile.getParent() : 
	                                null;
	            // Subreport dir
	            output4Report.put(B_SSM_S03_FieldSet.FIELD_RPT_SUBREPORT_DIR, templateParentDir+"/");
				
				serviceInfo.get(i).put("serviceTypeTitle", "Service Type");
				serviceInfo.get(i).put("serviceLineProviderTitle", "Service Line Provider");
				try 
				{
					output4Report.putAll(serviceMap);
					//to list
					toListInfo(output4Report, loginId,loginName);
					//init header info
					setHeaderInfo(output4Report);
					
					JasperPrint jasperPrint = JasperFillManager.fillReport(
							new FileInputStream(params.getReportPath()),
							output4Report, new JREmptyDataSource());
					
					REPORT_TEMP_PATH[i] = params.getSessionDirectory() + "/" + zipFileName 
						+ "_"+ numberFormat.format(i+1) + PDF_EXTENSION;
					File f = new File(REPORT_TEMP_PATH[i]);
					OutputStream outputStream = new FileOutputStream(f);
					JRPdfExporter exporterPDF = new JRPdfExporter();
					exporterPDF.setParameter(JRPdfExporterParameter.JASPER_PRINT, jasperPrint);
					exporterPDF.setParameter(JRPdfExporterParameter.OUTPUT_STREAM, outputStream);
					exporterPDF.exportReport();
					outputStream.flush();
					outputStream.close();
					exporterPDF.reset();
				} catch (Exception e) {
					e.printStackTrace();
					throw new ReportException(e.getMessage());
				}
			}
			try {
				String zipFilePath = ZipUtil.zip(REPORT_TEMP_PATH, params.getSessionDirectory());
				result.setInputStream(new FileInputStream(zipFilePath));
			} catch (Exception e) {
				e.printStackTrace();
				throw new ReportException(e.getMessage());
			}
			return result;
		}
		else
		{
			String errorMsg = messageResource.getMessage("info.ERR2SC002");
			throw new ReportException(errorMsg);
		}
	}
	
	/**
     * init header info data
     * @param output4Report
     */
    private void setHeaderInfo(Map<String, Object> output4Report) {
        List<Map<String, Object>> headerInfoList = new ArrayList<Map<String, Object>>();
        Map<String, Object> headerInfo = new HashMap<String, Object>();
        headerInfo.put("companyName", CommonUtils.toString(output4Report.get("companyName")));
        headerInfo.put("addressLine1", CommonUtils.toString(output4Report.get("addressLine1")));
        headerInfo.put("addressLine2", CommonUtils.toString(output4Report.get("addressLine2")));
        headerInfo.put("addressLine3", CommonUtils.toString(output4Report.get("addressLine3")));
        headerInfo.put("addressLine4", CommonUtils.toString(output4Report.get("addressLine4")));
        headerInfo.put("addressLine5", CommonUtils.toString(output4Report.get("addressLine5")));
        headerInfo.put("addressLine6", CommonUtils.toString(output4Report.get("addressLine6")));
        headerInfo.put("addressLine7", CommonUtils.toString(output4Report.get("addressLine7")));
        headerInfo.put("addressLine8", CommonUtils.toString(output4Report.get("addressLine8")));
        headerInfo.put("COLON", ":");
//        headerInfo.put("companyTelNoTitle", "Tel : " + CommonUtils.toString(output4Report.get("companyTelNo"))
//                                               + " Fax : " + CommonUtils.toString(output4Report.get("companyFaxNo")));
//        headerInfo.put("companyTelNo", CommonUtils.toString(output4Report.get("companyTelNo")));
//        headerInfo.put("companyFaxNoTitle", "Fax");
//        headerInfo.put("companyFaxNo", CommonUtils.toString(output4Report.get("companyFaxNo")));
//        headerInfo.put("companyRegNoTitle", "Reg. No");
//        headerInfo.put("companyRegNo", CommonUtils.toString(output4Report.get("companyRegNo")));
//        headerInfo.put("companyGstRegNoTitle", "GST Reg. No.");
//        headerInfo.put("companyGstRegNo", CommonUtils.toString(output4Report.get("companyGstRegNo")));
//        headerInfo.put("companyWebsiteTitle", "Website");
//        headerInfo.put("companyWebsite", CommonUtils.toString(output4Report.get("companyWebsite")));
        headerInfo.put("SERVICE_NAME_TITLE", "Service Name");
        headerInfo.put("SERVICE_NAME", CommonUtils.toString(output4Report.get("SERVICE_NAME")));
        headerInfo.put("SERVICE_PERFORM_TITLE", "Type of Service Perform");
        headerInfo.put("SERVICE_INSTATION_TITLE", "New Service Installation");
        headerInfo.put("customerIDTitle", "Customer ID");
        headerInfo.put("customerID", CommonUtils.toString(output4Report.get("customerID")));
        headerInfo.put("subscriptionIDTitle", "Subscription ID");
        headerInfo.put("subscriptionID", CommonUtils.toString(output4Report.get("subscriptionID")));
        headerInfo.put("customerInfoTitle", "1.  Customer Information");
        headerInfo.put("customerNameTitle", "Customer Name");
        headerInfo.put("customerName", CommonUtils.toString(output4Report.get("customerName")));
        headerInfo.put("customerAddressTitle", "Address");
        headerInfo.put("customerAddress1", CommonUtils.toString(output4Report.get("customerAddress1")));
        headerInfo.put("customerAddress2", CommonUtils.toString(output4Report.get("customerAddress2")));
        headerInfo.put("customerCodeCountryTitle", "Country/Postal Code");
        headerInfo.put("customerCodeCountry", CommonUtils.toString(output4Report.get("customerCodeCountry")));
        headerInfo.put("customerTelNoTitle", "Tel No");
        headerInfo.put("customerTelNo", CommonUtils.toString(output4Report.get("customerTelNo")));
        headerInfo.put("customerFaxNoTitle", "Fax No");
        headerInfo.put("customerFaxNo", CommonUtils.toString(output4Report.get("customerFaxNo")));
        headerInfoList.add(headerInfo);
        
        JRMapCollectionDataSource headerInfoDS = new JRMapCollectionDataSource(headerInfoList);
        output4Report.put("headerInfo", headerInfoDS);
    }
	
	private void toListInfo(Map<String, Object> output4Report, String loginId,String loginName) {
	    //Installation Address
	    List<Map<String, Object>> instantAddrList = new ArrayList<Map<String, Object>>();
        Map<String, Object> instantAddrMap = new HashMap<String, Object>();
        instantAddrMap.put("titleName", "Address");
        instantAddrMap.put("value", CommonUtils.toString(output4Report.get("instantAddressLine1")));
        instantAddrList.add(instantAddrMap);
        
        instantAddrMap = new HashMap<String, Object>();
        instantAddrMap.put("titleName", "");
        instantAddrMap.put("value", CommonUtils.toString(output4Report.get("instantAddressLine2")));
        instantAddrList.add(instantAddrMap);
        
        instantAddrMap = new HashMap<String, Object>();
        instantAddrMap.put("titleName", "");
        instantAddrMap.put("value", CommonUtils.toString(output4Report.get("instantAddressLine3")));
        instantAddrList.add(instantAddrMap);
        
        instantAddrMap = new HashMap<String, Object>();
        String instantAddressLine4 = CommonUtils.toString(output4Report.get("instantAddressCodeCountry")).trim();
        instantAddrMap.put("titleName", "Country/Postal Code");
        instantAddrMap.put("value", instantAddressLine4);
        instantAddrList.add(instantAddrMap);
        
        JRMapCollectionDataSource jrInstantAddrDs = new JRMapCollectionDataSource(instantAddrList);
        output4Report.put("instantAddrList", jrInstantAddrDs);
        
        //Installation Address2
        List<Map<String, Object>> instantAddr2List = new ArrayList<Map<String, Object>>();
        Map<String, Object> instantAddr2Map = new HashMap<String, Object>();
        instantAddr2Map.put("titleName", "Address");
        instantAddr2Map.put("value", CommonUtils.toString(output4Report.get("instantAddress2Line1")));
        instantAddr2List.add(instantAddr2Map);
        
        instantAddr2Map = new HashMap<String, Object>();
        instantAddr2Map.put("titleName", "");
        instantAddr2Map.put("value", CommonUtils.toString(output4Report.get("instantAddress2Line2")));
        instantAddr2List.add(instantAddr2Map);
        
        instantAddr2Map = new HashMap<String, Object>();
        instantAddr2Map.put("titleName", "");
        instantAddr2Map.put("value", CommonUtils.toString(output4Report.get("instantAddress2Line3")));
        instantAddr2List.add(instantAddr2Map);
        
        instantAddr2Map = new HashMap<String, Object>();
        String instantAddress2Line4 = CommonUtils.toString(output4Report.get("instantAddress2CodeCountry")).trim();
        instantAddr2Map.put("titleName", "Country/Postal Code");
        instantAddr2Map.put("value", instantAddress2Line4);
        instantAddr2List.add(instantAddr2Map);
        
        JRMapCollectionDataSource jrInstantAddr2Ds = new JRMapCollectionDataSource(instantAddr2List);
        output4Report.put("instantAddr2List", jrInstantAddr2Ds);
        
        // Email Client Setup Information
        List<Map<String, Object>> emailSetupList = new ArrayList<Map<String, Object>>();
        Map<String, Object> emailSetupMap = new HashMap<String, Object>();
        emailSetupMap.put("titleName", "Domain Name");
        emailSetupMap.put("value", CommonUtils.toString(output4Report.get("emailSetupDomainName")));
        emailSetupList.add(emailSetupMap);
        
        emailSetupMap = new HashMap<String, Object>();
        emailSetupMap.put("titleName", "POP 3 (Incoming)");
        emailSetupMap.put("value", CommonUtils.toString(output4Report.get("emailSetupPOP3")));
        emailSetupList.add(emailSetupMap);
        
        emailSetupMap = new HashMap<String, Object>();
        emailSetupMap.put("titleName", "SMTP (Outgoing)");
        emailSetupMap.put("value", CommonUtils.toString(output4Report.get("emailSetupSMTP")));
        emailSetupList.add(emailSetupMap);
        
        emailSetupMap = new HashMap<String, Object>();
        emailSetupMap.put("titleName", "Primary Domain Name Server");
        emailSetupMap.put("value", CommonUtils.toString(output4Report.get("primDomainNo")));
        emailSetupList.add(emailSetupMap);
        
        emailSetupMap = new HashMap<String, Object>();
        emailSetupMap.put("titleName", "Secondary Domain Name Server");
        emailSetupMap.put("value", CommonUtils.toString(output4Report.get("secDomainNo")));
        emailSetupList.add(emailSetupMap);
        
        JRMapCollectionDataSource jrEmailSetupDs = new JRMapCollectionDataSource(emailSetupList);
        output4Report.put("emailSetupList", jrEmailSetupDs);
        
        // FTP Interface Information
        List<Map<String, Object>> ftpInterfaceList = new ArrayList<Map<String, Object>>();
        Map<String, Object> ftpInterfaceMap = new HashMap<String, Object>();
        ftpInterfaceMap.put("titleName", "FTP IP Address");
        ftpInterfaceMap.put("value", CommonUtils.toString(output4Report.get("ftpIPAddress")));
        ftpInterfaceList.add(ftpInterfaceMap);
        
        ftpInterfaceMap = new HashMap<String, Object>();
        ftpInterfaceMap.put("titleName", "FTP Account Name");
        ftpInterfaceMap.put("value", CommonUtils.toString(output4Report.get("ftpAccountName")));
        ftpInterfaceList.add(ftpInterfaceMap);
        
        ftpInterfaceMap = new HashMap<String, Object>();
        ftpInterfaceMap.put("titleName", "FTP Password");
        ftpInterfaceMap.put("value", CommonUtils.toString(output4Report.get("ftpPassword")));
        ftpInterfaceList.add(ftpInterfaceMap);
        
        ftpInterfaceMap = new HashMap<String, Object>();
        ftpInterfaceMap.put("titleName", "Capacity of Web Server");
        ftpInterfaceMap.put("value", CommonUtils.toString(output4Report.get("ftpServerCapacity")));
        ftpInterfaceList.add(ftpInterfaceMap);
        
        ftpInterfaceMap = new HashMap<String, Object>();
        ftpInterfaceMap.put("titleName", "Default Page");
        ftpInterfaceMap.put("value", CommonUtils.toString(output4Report.get("ftpDefaultPage")));
        ftpInterfaceList.add(ftpInterfaceMap);
        
        ftpInterfaceMap = new HashMap<String, Object>();
        ftpInterfaceMap.put("titleName", "URL");
        ftpInterfaceMap.put("value", CommonUtils.toString(output4Report.get("ftpURL")));
        ftpInterfaceList.add(ftpInterfaceMap);
        
        JRMapCollectionDataSource jrFtpInterfaceDs = new JRMapCollectionDataSource(ftpInterfaceList);
        output4Report.put("ftpInterfaceList", jrFtpInterfaceDs);
        
        // FireWall info
        List<Map<String, Object>> fireWallList = new ArrayList<Map<String, Object>>();
        Map<String, Object> fireWallMap = new HashMap<String, Object>();
        fireWallMap.put("titleName1", "Firewall No");
        fireWallMap.put("value1", CommonUtils.toString(output4Report.get("firewallNo")));
        fireWallMap.put("titleName2", "Firewall Type");
        fireWallMap.put("value2", CommonUtils.toString(output4Report.get("firewallType")));
        fireWallList.add(fireWallMap);
        
        fireWallMap = new HashMap<String, Object>();
        fireWallMap.put("titleName1", "Firewall Model");
        fireWallMap.put("value1", CommonUtils.toString(output4Report.get("firewallModel")));
        fireWallMap.put("titleName2", "Interface IP (Trusted)");
        fireWallMap.put("value2", CommonUtils.toString(output4Report.get("firewallTrustedInterfaceIP")));
        fireWallList.add(fireWallMap);
        
        fireWallMap = new HashMap<String, Object>();
        fireWallMap.put("titleName1", "Serial No");
        fireWallMap.put("value1", CommonUtils.toString(output4Report.get("firewallSerialNo")));
        fireWallMap.put("titleName2", "Interface IP (Untrusted)");
        fireWallMap.put("value2", CommonUtils.toString(output4Report.get("firewallUntrustedInterfaceIP")));
        fireWallList.add(fireWallMap);
        
        fireWallMap = new HashMap<String, Object>();
        fireWallMap.put("titleName1", "NAT");
        fireWallMap.put("value1", "1".equals(CommonUtils.toString(output4Report.get("firewallNAT")))?"Yes":"No");
        fireWallMap.put("titleName2", "DHCP");
        fireWallMap.put("value2", "1".equals(CommonUtils.toString(output4Report.get("firewallDHCPOn")))?"Yes":"No");
        fireWallList.add(fireWallMap);
        
        fireWallMap = new HashMap<String, Object>();
        fireWallMap.put("titleName1", "DHCP IP Range");
        fireWallMap.put("value1", CommonUtils.toString(output4Report.get("firewallDHCPRange")));
        fireWallMap.put("titleName2", "DNS");
        fireWallMap.put("value2", CommonUtils.toString(output4Report.get("firewallDNS")));
        fireWallList.add(fireWallMap);
        
        JRMapCollectionDataSource jrFireWallDs = new JRMapCollectionDataSource(fireWallList);
        output4Report.put("fireWallList", jrFireWallDs);
        
        // DNS Zone Information
        List<Map<String, Object>> dnsZoneList = new ArrayList<Map<String, Object>>();
        Map<String, Object> dnsZoneMap = new HashMap<String, Object>();
        dnsZoneMap.put("titleName", "Current Name Server");
        dnsZoneMap.put("value", CommonUtils.toString(output4Report.get("dnsMigrationCurrentServer")));
        dnsZoneList.add(dnsZoneMap);
        
        String dnsMigrationCurrentServer2 = CommonUtils.toString(output4Report.get("dnsMigrationCurrentServer2")).trim();
        String dnsMigrationCurrentServer3 = CommonUtils.toString(output4Report.get("dnsMigrationCurrentServer3")).trim();
        if (!CommonUtils.isEmpty(dnsMigrationCurrentServer2)) {
            dnsZoneMap = new HashMap<String, Object>();
            dnsZoneMap.put("titleName", "");
            dnsZoneMap.put("value", dnsMigrationCurrentServer2);
            dnsZoneList.add(dnsZoneMap);
        }
        if (!CommonUtils.isEmpty(dnsMigrationCurrentServer3)) {
            dnsZoneMap = new HashMap<String, Object>();
            dnsZoneMap.put("titleName", "");
            dnsZoneMap.put("value", dnsMigrationCurrentServer3);
            dnsZoneList.add(dnsZoneMap);
        }
        dnsZoneMap = new HashMap<String, Object>();
        dnsZoneMap.put("titleName", "Current Registrar");
        dnsZoneMap.put("value", CommonUtils.toString(output4Report.get("dnsMigrationCurrentRegistrar")));
        dnsZoneList.add(dnsZoneMap);
        
        JRMapCollectionDataSource jrDnsZoneDs = new JRMapCollectionDataSource(dnsZoneList);
        output4Report.put("dnsZoneList", jrDnsZoneDs);
        
        //MRTG Monitoring
        List<Map<String, Object>> mrtgList = new ArrayList<Map<String, Object>>();
        Map<String, Object> mrtgMap = new HashMap<String, Object>();
        mrtgMap.put("mrtgURLTitle", "URL");
        mrtgMap.put("mrtgURL", CommonUtils.toString(output4Report.get("mrtgURL")));
        mrtgMap.put("mrtgIDTitle", "ID");
        mrtgMap.put("mrtgID", CommonUtils.toString(output4Report.get("mrtgID")));
        mrtgMap.put("mrtgPasswordTitle", "Password");
        mrtgMap.put("mrtgPassword", CommonUtils.toString(output4Report.get("mrtgPassword")));
        mrtgList.add(mrtgMap);
        
        JRMapCollectionDataSource jrMrtgDs = new JRMapCollectionDataSource(mrtgList);
        output4Report.put("mrtgList", jrMrtgDs);
        
        //Person In Charge info
        List<Map<String, Object>> personInChargeList = new ArrayList<Map<String, Object>>();
        Map<String, Object> personInChargeMap = new HashMap<String, Object>();
        personInChargeMap.put("titleName", "Account Manager");
        personInChargeMap.put("value", CommonUtils.toString(output4Report.get("userName")));
        personInChargeList.add(personInChargeMap);
        
        personInChargeMap = new HashMap<String, Object>();
        personInChargeMap.put("titleName", "Project Manager");
        personInChargeMap.put("value", loginName);
        personInChargeList.add(personInChargeMap);
        
        JRMapCollectionDataSource jrPersonInChargeDs = new JRMapCollectionDataSource(personInChargeList);
        output4Report.put("personInChargeList", jrPersonInChargeDs);
	}
	
	/**
	 * add server title
	 * @param listServer
	 */
	private void addTitleNameServerInfo(List<Map<String, Object>> listServer) {
	    if(listServer!=null && 0<listServer.size()) {
	        for(Map<String, Object> serverInfo: listServer) {
	            serverInfo.put("serverNameTitle", "Server Name");
	            serverInfo.put("modelNoTitle", "Model No");
	            serverInfo.put("hardwareTitle", "Hardware");
	            serverInfo.put("webHostSpaceTitle", "Web Host Space");
	            serverInfo.put("osTitle", "OS");
	            serverInfo.put("sqlTitle", "SQL");
	            serverInfo.put("ipTitle", "IP");
	            serverInfo.put("sqlDBNameTitle", "SQL DB Name");
	            serverInfo.put("gatewayTitle", "Gateway");
	            serverInfo.put("sqlSizeTitle", "SQL Size");
	            serverInfo.put("subnetMaskTitle", "Subnet Mask");
                serverInfo.put("sqlIDTitle", "SQL ID");
                serverInfo.put("mosVersionTitle", "MOS Version");
                serverInfo.put("sqlDBPasswordTitle", "SQL DB Password");
                serverInfo.put("hostIDTitle", "Host ID");
                serverInfo.put("backupTitle", "Backup");
                serverInfo.put("serialNoTitle", "Serial No");
                serverInfo.put("backupSizeTitle", "Backup Size");
                serverInfo.put("userLicenseTitle", "User License");
                serverInfo.put("monitoringTitle", "Monitoring");
                serverInfo.put("primaryDNSTitle", "Primary DNS");
                serverInfo.put("secondaryDNSTitle", "Secondary DNS");
                serverInfo.put("installedApplicationTitle", "Installed\r\nApplication");
	        }
	    }
	}
	
	/**
	 *  add title
	 * @param listCPEConfig
	 */
	private void addTitleNameCPEConfig(List<Map<String, Object>> listCPEConfig) {
	    if (listCPEConfig!=null && 0<listCPEConfig.size()) {
	        for(Map<String, Object> CPEConfig : listCPEConfig) {
	            CPEConfig.put("cpeConfigurationNameTitleName", "CPE Name");
	            CPEConfig.put("cpeConfigurationUsedTitleName", "Managed CPE is used");
	            CPEConfig.put("cpeConfigurationModelInstalledTitleName", "CPE Model Installed");
	            CPEConfig.put("cpeConfigurationSerialNoTitleName", "Serial No");
	            CPEConfig.put("cpeConfigurationLANTitleName", "LAN");
	            CPEConfig.put("cpeConfigurationWANTitleName", "WAN");
	            CPEConfig.put("cpeConfigurationEquipmentItemTileName", "Equipment Items Included in Managed CPE Package(s)");
	            CPEConfig.put("cpeConfigurationCustomerConfRouterTitleName", "Customer Specific Configuration on Router");
	        }
	    }
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
     * change the display MigrationCurrentServer
     * @param map
     * @param addrName
     */
    private void migrationCurrentServerDisplayChange(Map<String, Object> map){
        ArrayList<String> addrList = new ArrayList<String>();
        
        String migrationCurrentServer = "dnsMigrationCurrentServer";
        String migrationCurrentServer2 = "dnsMigrationCurrentServer2";
        String migrationCurrentServer3 = "dnsMigrationCurrentServer3";
        if(!"".equals(CommonUtils.toString(map.get(migrationCurrentServer)).trim())){
            addrList.add(CommonUtils.toString(map.get(migrationCurrentServer)).trim());
        }
        if(!"".equals(CommonUtils.toString(map.get(migrationCurrentServer2)).trim())){
            addrList.add(CommonUtils.toString(map.get(migrationCurrentServer2)).trim());
        }
        if(!"".equals(CommonUtils.toString(map.get(migrationCurrentServer3)).trim())){
            addrList.add(CommonUtils.toString(map.get(migrationCurrentServer3)).trim());
        }
        if(addrList.size()==0) {
            map.put(migrationCurrentServer,"");
            map.put(migrationCurrentServer2,"");
            map.put(migrationCurrentServer3,"");
        } else if(addrList.size()==1) {
            map.put(migrationCurrentServer,addrList.get(0));
            map.put(migrationCurrentServer2,"");
            map.put(migrationCurrentServer3,"");
        } else if(addrList.size()==2) {
            map.put(migrationCurrentServer,addrList.get(0));
            map.put(migrationCurrentServer2,addrList.get(1));
            map.put(migrationCurrentServer3,"");
        } else if(addrList.size()==3) {
            map.put(migrationCurrentServer,addrList.get(0));
            map.put(migrationCurrentServer2,addrList.get(1));
            map.put(migrationCurrentServer3,addrList.get(2));
        }
    }
    /**
     * get report id list
     * @param servicesReport
     * @return
     */
    private List<String> getArrayInfos(List<List<String>> servicesReport){
        List<String> arrayInfo = new ArrayList<String>();
        List<String> allSubArrayInfo = queryDAO.executeForObjectList("B_SSM_S03.selectServiceBasicInfo", null);
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
        return arrayInfo;
    }
}
