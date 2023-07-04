/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : B_SSM
 * SERVICE NAME   : B_SSM_S01
 * FUNCTION       : Constants of fieldset in B_SSM_S01
 * FILE NAME      : B_SSM_S01_FieldSet.java
 *
 * Copyright (C) 2011 NTTDATA Corporation
 * 
**********************************************************/

package nttdm.bsys.b_ssm.bean.data;

/**
 * @author NTT Data Vietnam
 * Constants of fieldset in B_SSM_S01
 */
public class B_SSM_S01_FieldSet {
	
	// TabPos Constant
	public static final int TABPOS_ISP = 0;
	public static final int TABPOS_COLOCATION = 1;
	public static final int TABPOS_EMAIL = 2;
	public static final int TABPOS_ADDRESS = 3;
	
	// General Fields
	public static final String FIELD_TAB_POSITION = "tabPosition";
	public static final String FIELD_PROCESSMODE = "processMode";
	public static final String FIELD_HASSEARCHSTARTED = "hasSearchStarted";	
	
	// ISP Fields
	public static final String FIELD_ROWNO = "rowNo";
	public static final String FIELD_RESULTSET = "resultSet";	
	public static final String FIELD_CUSTOMERID = "customerID";
	public static final String FIELD_CUSTOMERNAME = "customerName";
	public static final String FIELD_CUSTOMERTYPE = "customerType";
	public static final String FIELD_CATEGORY = "category";
	public static final String FIELD_SERVICE = "service";
	public static final String FIELD_PLAN = "plan";
	public static final String FIELD_PLANDETAIL = "planDetail";
	public static final String FIELD_INSTALLATIONADDRESS = "installationAddress";
	public static final String FIELD_POSTALCODE = "postalCode";
	public static final String FIELD_ROUTERNO = "routerNo";
	public static final String FIELD_CIRCUITID = "circuitID";
	public static final String FIELD_CARRIER = "carrier";
	public static final String FIELD_MODEMNO = "modemNo";
	public static final String FIELD_ADSL_DELNO = "aDSL_DelNo";
	public static final String FIELD_SUBSCRIPTIONID = "subscriptionID";
	public static final String FIELD_DRAFTSERVICESTATUS = "draftServiceStatus";
	public static final String FIELD_PENDINGAPPROVALSERVICESTATUS = "pendingApprovalServiceStatus";
	public static final String FIELD_ACTIVESERVICESTATUS = "activeServiceStatus";
	public static final String FIELD_ONETIMESERVICESTATUS = "oneTimeServiceStatus";
	public static final String FIELD_TERMINATEDSERVICESTATUS = "terminatedServiceStatus";
	public static final String FIELD_REJECTEDSERVICESTATUS = "rejectedServiceStatus";
	public static final String FIELD_SUSPENDEDSERVICESTATUS = "suspendedServiceStatus";
	public static final String FIELD_BILLINGINSTRUCTION = "billingInstruction";
	public static final String FIELD_FROMSERVICEDATE = "fromServiceDate";
	public static final String FIELD_TOSERVICEDATE = "toServiceDate";
	public static final String FIELD_CONTRACTTERMMODE = "contractTermMode";             
	public static final String FIELD_TIMECONTRACTTERM = "timeContractTerm"; 
	public static final String FIELD_STARTINDEX = "startIndex";
	public static final String FIELD_TOTALROW = "totalRow";
	public static final String FIELD_DISPLAYROWCOUNT = "row";
	public static final String FIELD_CURRENTROW = "now";
	public static final String FIELD_TOTALPAGE = "totalPage";	
	
	public static final String FIELD_ROW="index";
	public static final String FIELD_ADD1="installationAddress1";
	public static final String FIELD_ADD2="installationAddress2";
	public static final String FIELD_NAME1="name1";
	public static final String FIELD_DESIGNATION1="designation1";
	public static final String FIELD_TELNO1="tel_no1";
	public static final String FIELD_FAXNO1="fax_no1";
	public static final String FIELD_NAME2="name2";
	public static final String FIELD_DESIGNATION2="designation2";
	public static final String FIELD_TELNO2="tel_no2";
	public static final String FIELD_FAXNO2="fax_no2";
	public static final String FIELD_NAME3="name3";
	public static final String FIELD_DESIGNATION3="designation3";
	public static final String FIELD_TELNO3="tel_no3";
	public static final String FIELD_FAXNO3="fax_no3";
	public static final String FIELD_NAMETC="contact_name_tc";
	public static final String FIELD_DESIGNATIONTC="designation_tc";
	public static final String FIELD_EMAIL="EMAIL";
	public static final String FIELD_TELNOTC="tel_no_tc";
	public static final String FIELD_FAXNOTC="fax_no_tc";
	public static final String FIELD_MOBILENO="MOBILE_NO";
	public static final String FIELD_SERVICESTATUSQUERY_STRING = "serviceStatusQueryString";
	public static final String FIELD_SERVICESTATUSARRAY = "serviceStatusArray";
	
	// CoLocation Fields
	public static final String FIELD_COLOCATIONCUSTOMERID = "coLocationCustomerID";
	public static final String FIELD_COLOCATIONCUSTOMERNAME = "coLocationCustomerName";
    public static final String FIELD_COLOCATIONCUSTOMERTYPE = "coLocationCustomerType";
    public static final String FIELD_COLOCATIONSEARCHBYDETAILS = "coLocationSearchByDetails";
    public static final String FIELD_COLOCATIONRACKNO = "coLocationRackNo";
    public static final String FIELD_COLOCATIONPOWERCOMMITTED = "coLocationPowerCommitted";
    public static final String FIELD_COLOCATIONEQUIPMENTLOCATION = "coLocationEquipmentLocation";
    public static final String FIELD_COLOCATIONEQUIPMENTSUITE = "coLocationEquipmentSuite";
    public static final String FIELD_COLOCATIONCATEGORY = "coLocationCategory";
    public static final String FIELD_COLOCATIONSERVICE = "coLocationService";
    public static final String FIELD_COLOCATIONPLAN = "coLocationPlan";
    public static final String FIELD_COLOCATIONPLANDETAIL = "coLocationPlanDetail";
    public static final String FIELD_COLOCATIONSUBSCRIPTIONID = "coLocationSubscriptionID";
    public static final String FIELD_COLOCATIONDRAFTSERVICESTATUS = "coLocationDraftServiceStatus";
    public static final String FIELD_COLOCATIONPENDINGSERVICESTATUS = "coLocationPendingApprovalServiceStatus";
    public static final String FIELD_COLOCATIONACTIVESERVICESTATUS = "coLocationActiveServiceStatus";
    public static final String FIELD_COLOCATIONONETIMESERVICESTATUS = "coLocationOneTimeServiceStatus";
    public static final String FIELD_COLOCATIONTERMINATEDSERVICESTATUS = "coLocationTerminatedServiceStatus";
    public static final String FIELD_COLOCATIONREJECTEDSERVICESTATUS = "coLocationRejectedServiceStatus";
    public static final String FIELD_COLOCATIONSUSPENDEDSERVICESTATUS = "coLocationSuspendedServiceStatus";
    public static final String FIELD_COLOCATIONBILLINGINSTRUCTION = "coLocationBillingInstruction";
    public static final String FIELD_COLOCATIONFROMSERVICEDATE = "coLocationFromServiceDate";
    public static final String FIELD_COLOCATIONTOSERVICEDATE = "coLocationToServiceDate";
    public static final String FIELD_COLOCATIONCONTRACTTERMMODE = "coLocationContractTermMode";
    public static final String FIELD_COLOCATIONTIMECONTRACTTERM = "coLocationTimeContractTerm";
    public static final String FIELD_COLOCATIONSTARTINDEX = "coLocationStartIndex";
    public static final String FIELD_COLOCATIONTOTALROW = "coLocationTotalRow";
	public static final String FIELD_COLOCATIONDISPLAYROWCOUNT = "coLocationRow";
	public static final String FIELD_COLOCATIONCURRENTROW = "coLocationNow";
	public static final String FIELD_COLOCATIONTOTALPAGE = "coLocationTotalPage";	
	public static final String FIELD_COLOCATIONRESULTSET = "coLocationResultSet";
	public static final String FIELD_COLOCATIONTOTALRACKNO = "coLocationTotalRackNo";
	public static final String FIELD_COLOCATIONSERVICESTATUSQUERY_STRING = "coLocationServiceStatusQueryString";
	public static final String FIELD_COLOCATIONSERVICESTATUSARRAY = "coLocationServiceStatusArray";
	public static final String FIELD_COLOCATIONCUSTOMERPLANID="coLocationCustomerPlanID"; 
	public static final String FIELD_COLOCATIONCONTRACTTERM = "coLocationContractTerm";
    // Email Fields
	public static final String EMAIL_SUBSCRIBEDINFOMODE_VALUE = "emailSubscribedMode";
	public static final String EMAIL_DOMAINNAMEINFOMODE_VALUE = "emailDomainNameMode";
	public static final String EMAIL_ITCONTACTINFOMODE_VALUE = "emailITContactMode";	
    public static final String FIELD_EMAILCUSTOMERID = "emailCustomerID";
    public static final String FIELD_EMAILCUSTOMERNAME = "emailCustomerName";
    public static final String FIELD_EMAILCUSTOMERTYPE = "emailCustomerType";
    public static final String FIELD_EMAILCUSTOMERTELEPHONE = "emailCustomerTelephone";
    public static final String FIELD_EMAILSUBSCRIBEDMODE = "emailSubscribedMode";
    public static final String FIELD_EMAILDOMAINNAMEMODE = "emailDomainNameMode";
    public static final String FIELD_EMAILITCONTACTMODE = "emailITContactMode";
    public static final String FIELD_EMAILINFOMODE = "emailInfoMode";
    public static final String FIELD_EMAILSUBSCRIBEDADDRESSTEXT = "emailSubscribedAddressText";
    public static final String FIELD_EMAILDOMAINNAMETEXT = "emailDomainNameText";
    public static final String FIELD_EMAILDOMAINNAME = "emailDomainName";
    public static final String FIELD_EMAILCATEGORY = "emailCategory";
    public static final String FIELD_EMAILSERVICE = "emailService";
    public static final String FIELD_EMAILPLAN = "emailPlan";
    public static final String FIELD_EMAILPLANDETAIL = "emailPlanDetail";
    public static final String FIELD_EMAILSUBSCRIPTIONID = "emailSubscriptionID";   
    public static final String FIELD_EMAILPENDINGSERVICESTATUS = "emailPendingApprovalServiceStatus";
    public static final String FIELD_EMAILACTIVESERVICESTATUS = "emailActiveServiceStatus";
    public static final String FIELD_EMAILONETIMESERVICESTATUS = "emailOneTimeServiceStatus";
    public static final String FIELD_EMAILTERMINATEDSERVICESTATUS = "emailTerminatedServiceStatus";
    public static final String FIELD_EMAILREJECTEDSERVICESTATUS = "emailRejectedServiceStatus";
    public static final String FIELD_EMAILSUSPENDEDSERVICESTATUS = "emailSuspendedServiceStatus";
    public static final String FIELD_EMAILSTARTINDEX = "emailStartIndex";
    public static final String FIELD_EMAILTOTALROW = "emailTotalRow";
	public static final String FIELD_EMAILDISPLAYROWCOUNT = "emailRow";
	public static final String FIELD_EMAILCURRENTROW = "emailNow";
	public static final String FIELD_EMAILTOTALPAGE = "emailTotalPage";
	public static final String FIELD_EMAILRESULTSET = "emailResultSet";
	public static final String FIELD_EMAILFTP_URL = "emailFTP_URL";	
	public static final String FIELD_EMAILDNSDOMAINNAME = "emailDNSDomainName";	
	public static final String FIELD_EMAILTEAMWORKDOMAINNAMEADDRESS = "emailTeamworkDomainAddress";
	public static final String FIELD_EMAILTEAMWORKURL = "emailTeamworkURL";	
	public static final String FIELD_EMAILITCONTACTNO = "emailITContactNo";
	public static final String FIELD_EMAILITCONTACT = "emailITContact";
	public static final String FIELD_EMAILSERVICESTATUSQUERY_STRING = "emailServiceStatusQueryString";
	public static final String FIELD_EMAILDRAFTSERVICESTATUS = "emailDraftServiceStatus";
	public static final String FIELD_EMAILSERVICESTATUSARRAY = "emailServiceStatusArray";
	public static final String FIELD_EMAILSERVICESTATUS="emailServiceStatus";
	public static final String FIELD_EMAILCUSTOMERADDRESS="emailCustomerAddress";
	public static final String FIELD_EMAILCUSTOMERPLANID="emailCustomerPlanID";
	public static final String FIELD_EMAILSERVICENAME="emailServiceName";
	public static final String FIELD_EMAIL_MAIL1="email_mail1";
	public static final String FIELD_EMAIL_TELNO1="email_telno1";
	public static final String FIELD_EMAIL_MAIL2="email_email2";
	public static final String FIELD_EMAIL_TELNO2="email_telno2";
	public static final String FIELD_EMAIL_MAIL3="email_email3";
	public static final String FIELD_EMAIL_TELNO3="email_telno3";
   
    // Address Fields
    public static final String FIELD_ADDRESSCUSTOMERID = "addressCustomerID";
    public static final String FIELD_ADDRESSCUSTOMERNAME = "addressCustomerName";
    public static final String FIELD_ADDRESSCUSTOMERTYPE = "addressCustomerType";
    public static final String FIELD_ADDRESSCUSTOMERTELEPHONE = "addressCustomerTelephone";
    public static final String FIELD_ADDRESSCUSTOMERFAXNO = "addressCustomerFaxNo";
    public static final String FIELD_ADDRESSREGISTEREDTYPE = "addressRegisteredType";
    public static final String FIELD_ADDRESSBILLINGTYPE = "addressBillingType";
    public static final String FIELD_ADDRESSCORRESPONDENCETYPE = "addressCorrespondenceType";
    public static final String FIELD_ADDRESSTECHNICALTYPE = "addressTechnicalType";             
    public static final String FIELD_ADDRESSCATEGORY = "addressCategory";
    public static final String FIELD_ADDRESSSERVICE = "addressService";
    public static final String FIELD_ADDRESSPLAN = "addressPlan";
    public static final String FIELD_ADDRESSPLANDETAIL = "addressPlanDetail";
    public static final String FIELD_ADDRESSSUBSCRIPTIONID = "addressSubscriptionID";
    public static final String FIELD_ADDRESSDRAFTSERVICESTATUS = "addressDraftServiceStatus";
    public static final String FIELD_ADDRESSPENDINGSERVICESTATUS = "addressPendingApprovalServiceStatus";
    public static final String FIELD_ADDRESSACTIVESERVICESTATUS = "addressActiveServiceStatus";
    public static final String FIELD_ADDRESSONETIMESERVICESTATUS = "addressOneTimeServiceStatus";
    public static final String FIELD_ADDRESSTERMINATEDSERVICESTATUS = "addressTerminatedServiceStatus";
    public static final String FIELD_ADDRESSREJECTEDSERVICESTATUS = "addressRejectedServiceStatus";
    public static final String FIELD_ADDRESSSUSPENDEDSERVICESTATUS = "addressSuspendedServiceStatus";
	public static final String FIELD_ADDRESSSTARTINDEX = "addressStartIndex";
	public static final String FIELD_ADDRESSTOTALROW = "addressTotalRow";
	public static final String FIELD_ADDRESSDISPLAYROWCOUNT = "addressRow";
	public static final String FIELD_ADDRESSCURRENTROW = "addressNow";
	public static final String FIELD_ADDRESSTOTALPAGE = "addressTotalPage";
	public static final String FIELD_ADDRESSRESULTSET = "addressResultSet";	
	public static final String FIELD_ADDRESSTYPE = "addressType";
	public static final String FIELD_ADDRESSSERVICESTATUSQUERY_STRING = "addressServiceStatusQueryString";
	public static final String FIELD_ADDRESSTYPEQUERY_STRING = "addressTypeQueryString";	
	public static final String FIELD_ADDRESSSERVICESTATUSARRAY = "addressServiceStatusArray";
	public static final String FIELD_ADDRESSTYPEARRAY = "addressTypeArray";
	
	// Process Mode Fields
	public static final int SEARCH_PROCESS_MODE_VALUE = 0;
	public static final int EXPORT_PROCESS_MODE_VALUE = 1;	
}
