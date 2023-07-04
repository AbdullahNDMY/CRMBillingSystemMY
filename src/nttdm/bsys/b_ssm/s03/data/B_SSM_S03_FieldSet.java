/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : B_SSM
 * SERVICE NAME   : B_SSM_S03
 * FUNCTION       : Constants of field set in B_SSM_S03
 * FILE NAME      : B_SSM_S03_FieldSet.java
 *
 * Copyright (C) 2011 NTTDATA Corporation
 * 
**********************************************************/

package nttdm.bsys.b_ssm.s03.data;

/**
 * @author NTT Data Vietnam
 * Constants of field set in B_SSM_S03
 */
public class B_SSM_S03_FieldSet {
	
	// Process Mode
	public static final int PROCESSMODE_EDIT = 1;	
	public static final int PROCESSMODE_VIEW = 0;
	public static final int PROCESSMODE_SAVE = 2;
	public static final int PROCESSMODE_EXIT = 3;
	public static final int PROCESSMODE_PRINTREPORT = 4;
	public static final int PROCESSMODE_EXPORTITCONTACT = 5;
	public static final int PROCESSMODE_NOTICE = 6;
	public static final int PROCESSMODE_COMPLETIONREPORT = 7;
	public static final int PROCESSMODE_FREEFORMAT = 8;
	public static final int PROCESSMODE_POLLING = 9;
	
	// Field contants
	public static final String FIELD_DOWNLOADSTATUS = "downloadStatus";
	public static final String FIELD_ISPOPUP = "isPopUp";
	public static final String FIELD_CUSTOMERID = "customerID";
	public static final String FIELD_CUSTOMERPLANID = "customerPlanID";
	public static final String FIELD_CUSTOMERNAME = "customerName";	
	public static final String FIELD_SUBSCRIPTIONID = "subscriptionID";
	public static final String FIELD_PROCESSMODE = "processMode";
	public static final String FIELD_INFOIDARRAY = "infoIDArray";
	public static final String FIELD_TAB_POSITION = "tabPosition";
	public static final String FIELD_LOGONUSERID = "logonUserID";
	public static final String FIELD_LOGONUSERNAME = "logonUserName";
	public static final String FIELD_AUDITID = "auditID";	
	public static final String FIELD_EDITEDSTATUS = "editedStatus";
	public static final String FIELD_SERVICENAMELIST = "serviceNameList";
	public static final String FIELD_SELECTEDSERVICES = "selectedServices";
	public static final String FIELD_SELECTEDCUSTOMERPLANLINKIDS = "selectedCustomerPlanLinkIDs";
	public static final String FIELD_SERVICEIDS = "serviceIDs";
	public static final String FIELD_REPORTRESULT = "reportResult";
	public static final String FIELD_SELECTEDSERVICEIDS = "selectedServiceIDs";
	public static final String KEY_FILEPATH_BRPTS04 = "B_SSM_S03_Page.Report.B_RPT_SB04.Path.Template";
	public static final String FIELD_REPORTTEMPLATESTREAM = "reportTemplateStream";
	public static final String FIELD_REPORTTEMPLATEPATH = "reportTemplatePath";
	public static final String FIELD_CUSTOMERPLANLINKIDS = "customerPlanLinkIDs";
	public static final String KEY_FILEPATH_REPORTLOGO = "B_SSM_S03_Page.Report.Path.Logo";
	public static final String KEY_FILEPATH_SUBREPORT = "B_SSM_S03_Page.Report.Path.SubReport";
	public static final String FIELD_REPORTLOGOPATH = "reportLogoPath";
	public static final String FIELD_REPORTPATH = "reportPath";
	public static final String FIELD_SUBREPORTPATH = "subReportPath";
	public static final String FIELD_RACKEQUIPMENTINFO = "rackEquipmentInfo";
	public static final String FIELD_CUSTOMERPLANLINKID = "customerPlanLinkID";
	
	public static final String FIELD_RPT_FILEPATH_LOGO = "FILEPATH_LOGO";
	public static final String FIELD_RPT_COMPANYNAME = "COMPANY_NAME";
	public static final String FIELD_RPT_COMPANYADDRESS = "COMPANY_ADDRESS";
	public static final String FIELD_RPT_COMPANYADDRESS1 = "COMPANY_ADDRESS1";
	public static final String FIELD_RPT_COMPANYADDRESS2 = "COMPANY_ADDRESS2";
	public static final String FIELD_RPT_COMPANYADDRESS3 = "COMPANY_ADDRESS3";
	public static final String FIELD_RPT_COMPANYFAXNO = "COMPANY_FAX_NO";
	public static final String FIELD_RPT_COMPANYTELNO = "COMPANY_TEL_NO";
	public static final String FIELD_RPT_COMPANYREGNO = "COMPANY_REG_NO";
	public static final String FIELD_RPT_COMPANYPOSTALCODE = "COMPANY_POSTAL_CODE";
	public static final String FIELD_RPT_COMPANYCOUNTRYNAME = "COMPANY_COUNTRY_NAME";
	public static final String FIELD_RPT_CUSTOMERNAME = "CUSTOMER_NAME";
	public static final String FIELD_CUSTOMERADDRESS1 = "CUSTOMER_ADDRESS1";
	public static final String FIELD_RPT_CUSTOMERADDRESS2 = "CUSTOMER_ADDRESS2";
	public static final String FIELD_RPT_CUSTOMERADDRESS3 = "CUSTOMER_ADDRESS3";
	public static final String FIELD_RPT_CUSTOMERTELNO = "CUSTOMER_TEL_NO";
	public static final String FIELD_RPT_CUSTOMERCOTELNO = "CUSTOMER_CO_TEL_NO";
	public static final String FIELD_RPT_CUSTOMERHOMETELNO = "CUSTOMER_HOME_TEL_NO";
	public static final String FIELD_RPT_CUSTOMERATTENTION = "CUSTOMER_ATTENTION";
	public static final String FIELD_RPT_SERVICENAME = "SERVICE_NAME";
	public static final String FIELD_RPT_CUSTOMERID = "CUSTOMER_ID";
	public static final String FIELD_RPT_SUBSCRIPTIONID = "SUBSCRIPTION_ID";
	public static final String FIELD_RPT_PERSONINCHARGE = "PERSON_IN_CHARGE";
	public static final String FIELD_RPT_SERVICECOMMISSIONDATE = "SERVICE_COMMISSION_DATE";
	public static final String FIELD_RPT_CUSTOMERFAXNO = "CUSTOMER_FAX_NO";
	public static final String FIELD_RPT_CUSTOMERCOFAXNO = "CUSTOMER_CO_FAX_NO";
	public static final String FIELD_RPT_CUSTOMERHOMEFAXNO = "CUSTOMER_HOME_FAX_NO";
	public static final Object FIELD_RPT_CUSTOMERCONTACTNAME = "CUSTOMER_CONTACT_NAME";
	public static final String FIELD_RPT_INFOIDARRAY = "INFO_ID_ARRAY";
	public static final String FIELD_RPT_PRODUCTSERVICEDESCRIPTION = "PRODUCT_SERVICE_DESCRIPTION";
	public static final String FIELD_RPT_SUBREPORT_DIR = "SUBREPORT_DIR";
	
	public static final String FIELD_REPORT_DISPLAY_INFO = "subscriptionInfoID";
	public static final String FIELD_SERVICE_TYPE = "serviceType";
	public static final String FIELD_IT_CONTACT_ID = "contactID";
	public static final String FIELD_IT_CONTACT_LIST = "itContactList";
	public static final String FIELD_INSTANT_ADDRESS_ID = "instantAddressID";
	public static final String FIELD_INSTANT_ADDRESS_ORDER = "instantAddressOrder";
	public static final String FIELD_MRTG_ID = "mrtgID";
	public static final String FIELD_MRTG_ORDER = "mrtgMonitoringOrder";
	public static final String FIELD_CPE_CONF_ID = "cpeConfID";
	public static final String FIELD_CPE_CONF_ORDER = "cpeConfigurationOrder";
	public static final String FIELD_IP_ADDRESS_ORDER = "ipAddressOrder";
	public static final String FIELD_FIREWALL_ID = "firewallID";
	public static final String FIELD_FIREWALL_ORDER = "firewallOrder";
	public static final String FIELD_FIREWALL_IP_LIST = "ipList";
	public static final String FIELD_FIREWALL_POLICY_LIST = "policyList";
	public static final String FIELD_SERVER_ID = "serverInfoID";
	public static final String FIELD_SERVER_ORDER = "serverInfoOrder";
	public static final String FIELD_SERVER_LIST = "serverList";
	public static final String FIELD_MAIL_ID = "mailID";
	public static final String FIELD_POP_ACCOUNT_ORDER = "popAccountOrder";
	public static final String FIELD_POP_ACCOUNT_LIST = "popAccountList";
	public static final String FIELD_EMAIL_SETUP_ORDER = "emailSetupOrder";
	public static final String FIELD_FTP_ID = "ftpID";
	public static final String FIELD_FTP_INTERFACE_ORDER = "ftpInterfaceOrder";
	public static final String FIELD_DNS_ZONE_ID = "dnsZoneID";
	public static final String FIELD_DNS_ZONE_ORDER = "dnsZoneOrder";
	public static final String FIELD_DNS_DOMAIN_LIST = "domainList";
	public static final String FIELD_RACK_EQUIP_ID = "rackEquipID";
	public static final String FIELD_RACK_EQUIP_ORDER = "rackEquipOrder";
	public static final String FIELD_RACK_EQUIP_LIST = "rackEquipment";
	public static final String FIELD_RACK_EQUIP_NO = "rackNo";
	public static final String FIELD_RACK_EQUIP_MAXPOWER = "maxPower";
	public static final String FIELD_RACK_EQUIP_LOCATION = "equipmentLocation";
	public static final String FIELD_RACK_EQUIP_SUITE = "equipmentSuite";
	public static final String FIELD_HELPDESK_ORDER = "helpdeskOrder";
	public static final String FIELD_REMARKS_ORDER = "remarksOrder";
	public static final String FIELD_REPORTERRORSTATUS = "reportErrorStatus";
	
	public static final String REPORTMODE_NOTE = "NTL";
	public static final String REPORTMODE_COMPLETION = "CPR";
	public static final String REPORTMODE_FREEFORMAT = "CFR";
	public static final String REPORTMODE_PRINTDELETIONADDITIONEMAIL = "ENL";
	public static final String FIELD_REPORTCODE = "reportCode";
	
}
