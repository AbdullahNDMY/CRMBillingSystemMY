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

package nttdm.bsys.b_ssm.s01.data;

/**
 * @author NTT Data Vietnam
 * Constants of fieldset in B_SSM_S01
 */
public class B_SSM_S01_FieldSet {
    
    // TabPos Constant
    public static final int TABPOS_GENERAL = 0;
    public static final int TABPOS_ISP = 1;
    public static final int TABPOS_COLOCATION = 2;
    public static final int TABPOS_EMAIL = 3;
    public static final int TABPOS_ADDRESS = 4;
    
    // General Fields
    public static final String FIELD_ACCESSTYPE = "accessType";
    public static final String FIELD_TAB_POSITION = "tabPosition";
    public static final String FIELD_PROCESSMODE = "processMode";
    public static final String FIELD_HASSEARCHSTARTED = "hasSearchStarted";
    public static final String FIELD_LOGONUSERID = "logonUserID";
    
    // ISP Fields
    public static final String FIELD_CATEGORYCODELIST = "categoryCodeList";    
    public static final String FIELD_SERVICECODELIST = "serviceCodeList";    
    public static final String FIELD_PLANCODELIST = "planCodeList";    
    public static final String FIELD_PLANDETAILCODELIST = "planDetailCodeList";    
    public static final String FIELD_CARRIERCODELIST = "carrierCodeList";    
    public static final String FIELD_EQUIPMENTLOCATIONCODELIST = "equipmentLocationCodeList";    
    public static final String FIELD_EQUIPMENTSUITECODELIST = "equipmentSuiteCodeList";    
    public static final String FIELD_CUSTOMERPLANLINKID = "customerPlanLinkID";
    public static final String FIELD_PLANSERVICENAME = "planServiceName";
    public static final String FIELD_ROWNO = "rowNo";
    public static final String FIELD_RESULTSET = "resultSet";    
    public static final String FIELD_CUSTOMERID = "customerID";
    public static final String FIELD_CUSTOMERPLANID = "customerPlanID";
    public static final String FIELD_CUSTOMERNAME = "customerName";
    public static final String FIELD_CUSTOMERTYPE = "customerType";
    public static final String FIELD_CATEGORY = "category";
    public static final String FIELD_SERVICE = "service";
    public static final String FIELD_PLAN = "plan";
    public static final String FIELD_PLAN_NAME = "SVC_LEVEL3";
    public static final String FIELD_SERVICE_START = "SVC_START";
    public static final String FIELD_SERVICE_END = "SVC_END";
    public static final String FIELD_BILLING_INSTRUCTION = "BILL_INSTRUCT";
    public static final String FIELD_CONTRACT_TERMS = "CONTRACT_TERM";
    public static final String FIELD_ACCESS_ACCOUNT = "ACCESS_ACCOUNT";
    public static final String FIELD_TXT_ACCESS_ACCOUNT = "txtAccessAccount";

    public static final String FIELD_PLANDETAIL = "planDetail";
    public static final String FIELD_INSTALLATIONADDRESS1 = "installationAddress1";
    public static final String FIELD_INSTALLATIONADDRESS2 = "installationAddress2";
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
    public static final String FIELD_CANCELLEDSERVICESTATUS = "cancelledServiceStatus";
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
    public static final String FIELD_TELNO1="telno1";
    public static final String FIELD_FAXNO1="faxno1";

    public static final String FIELD_NAME2="name2";
    public static final String FIELD_DESIGNATION2="designation2";
    public static final String FIELD_TELNO2="telno2";
    public static final String FIELD_FAXNO2="faxno2";

    public static final String FIELD_NAME3="name3";
    public static final String FIELD_DESIGNATION3="designation3";
    public static final String FIELD_TELNO3="telno3";
    public static final String FIELD_FAXNO3="faxno3";

    public static final String FIELD_NAMETC="contact_name_tc";
    public static final String FIELD_DESIGNATIONTC="designation_tc";
    public static final String FIELD_EMAIL="EMAIL";
    public static final String FIELD_TELNOTC="telno_tc";
    public static final String FIELD_FAXNOTC="faxno_tc";
    public static final String FIELD_MOBILENO="MOBILE_NO";

    public static final String FIELD_SERVICESTATUSQUERY_STRING = "serviceStatusQueryString";
    public static final String FIELD_SERVICESTATUSARRAY = "serviceStatusArray";
    public static final String FIELD_FULLRESULTSET = "fullResultSet";    

    // CoLocation Fields
    public static final String FIELD_COLOCATIONCUSTOMERID = "coLocationCustomerID";
    public static final String FIELD_COLOCATIONCUSTOMERNAME = "coLocationCustomerName";
    public static final String FIELD_COLOCATIONCUSTOMERTYPE = "coLocationCustomerType";
    public static final String FIELD_COLOCATIONSEARCHBYDETAILS = "coLocationSearchByDetails";
    public static final String FIELD_COLOCATIONRACKNO = "coLocationRackNo";
    public static final String FIELD_COLOCATIONSTARTRACKNO = "coLocationStartRackNo";
    public static final String FIELD_COLOCATIONENDRACKNO = "coLocationEndRackNo";
    public static final String FIELD_COLOCATIONPOWERCOMMITTED = "coLocationPowerCommitted";
    public static final String FIELD_COLOCATIONTOTALPOWERCOMMITTED = "coLocationTotalPowerCommitted";
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
    public static final String FIELD_COLOCATIONCANCELLEDSERVICESTATUS = "coLocationCancelledServiceStatus";
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
    public static final String FIELD_COLOCATIONFULLRESULTSET = "coLocationFullResultSet";
    public static final String FIELD_COLOCATIONCONTRACTTERM = "coLocationContractTerm";
    public static final String FIELD_COLOCATIONCUSTOMERPLANID="coLocationCustomerPlanID"; 

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
    public static final String FIELD_EMAILCANCELLEDSERVICESTATUS = "emailCancelledServiceStatus";
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
    public static final String FIELD_EMAILTEAMWORKADMINID = "emailTeamworkAdminId";
    public static final String FIELD_EMAILITCONTACTNO = "emailITContactNo";
    public static final String FIELD_EMAILITCONTACTNO1 = "emailITContactNo1";
    public static final String FIELD_EMAILITCONTACT = "emailITContact";
    public static final String FIELD_EMAILITCONTACT1 = "emailITContact1";
    public static final String FIELD_EMAILSERVICESTATUSQUERY_STRING = "emailServiceStatusQueryString";
    public static final String FIELD_EMAILDRAFTSERVICESTATUS = "emailDraftServiceStatus";
    public static final String FIELD_EMAILSERVICESTATUSARRAY = "emailServiceStatusArray";
    public static final String FIELD_EMAILFULLRESULTSET = "emailFullResultSet";    
    public static final String FIELD_EMAILCUSTOMERPLANID="emailCustomerPlanID";
    public static final String FIELD_EMAILSERVICENAME="emailService";
    public static final String FIELD_EMAILSERVICESTATUS="emailServiceStatus";
    public static final String FIELD_EMAILCUSTOMERADDRESS="emailCustomerAddress";

    public static final String FIELD_MAIL1="mail1";    
    public static final String FIELD_MAIL2="email2";
    public static final String FIELD_MAIL3="email3";


    // Address Fields
    public static final String FIELD_REGISTEREDADDRESS = "addressRegistrationAddress";
    public static final String FIELD_BILLINGADDRESS = "addressBillAddress";
    public static final String FIELD_CORRESPONDENCEADDRESS = "addressCorrespondenceAddress";
    public static final String FIELD_TECHNICALADDRESS = "addressTechnicalAddress";
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
    public static final String FIELD_ADDRESSCANCELLEDSERVICESTATUS = "addressCancelledServiceStatus";
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
    public static final String FIELD_ADDRESSFULLRESULTSET = "addressFullResultSet";    
    public static final String FIELD_ADDRESSTEXT = "addressText";
    public static final String FIELD_ADDRESSPLANNAME="addressPlan";
    public static final String FIELD_ADDRESSREGISTRATIONADDRESS="addressRegistrationAddress";
    public static final String FIELD_ADDRESSBILLADDRESS="addressBillAddress";
    public static final String FIELD_ADDRESSCORRESPONDENCEADDRESS="addressCorrespondenceAddress";
    public static final String FIELD_ADRESSTECHNICALADDRESS="addressTechnicalAddress";
    public static final String FIELD_ADDRESSCUSTEMAIL="addressEmail";
    public static final String FIELD_ADDRESSCUSTOMERPLANID = "addressCustomerPlanID";
    // #188 Start
    public static final String FIELD_ADDRESSCONTACTGENERALTYPE = "addressContactGeneralType";
    public static final String FIELD_ADDRESSCONTACTBILLINGTYPE = "addressContactBillingType";
    public static final String FIELD_ADDRESSCONTACTPRIMARYTYPE = "addressContactPrimaryType";
    public static final String FIELD_ADDRESSCONTACTTECHNICALTYPE = "addressContactTechnicalType";
    public static final String FIELD_ADDRESSCONTACTOTHERTYPE = "addressContactOtherType";
    public static final String FIELD_ADDRESSCONTACTITTYPE = "addressContactITType";
    public static final String FIELD_ADDRESSHOMETELNO = "addressHomeTelNo";
    public static final String FIELD_ADDRESSHOMETELFAX = "addressHomeTelFax";
    public static final String FIELD_ADDRESSMOBILENO = "addressMobileNo";
    public static final String FIELD_ADDRESSICNO = "addressICNo";
    public static final String FIELD_ADDRESSCOTELNO = "addressCoTelNo";
    public static final String FIELD_ADDRESSCOFAXNO = "addressCoFaxNo";
    // #188 End
    
    // Process Mode Fields
    public static final int PROCESSMODE_SEARCH = 0;
    public static final int PROCESSMODE_EXPORT = 1;
    public static final int PROCESSMODE_VIEWPREVIOUSSTATE = 2;
    public static final int PROCESSMODE_PAGELIST = 3;
    public static final int PROCESSMODE_INITIAL = 4;
    
    // Values
    public static final String VALUE_REGISTERED_ADDRESS = "RA";
    public static final String VALUE_CORRESPONDENCE_ADDRESS = "CA";
    public static final String VALUE_BILLING_ADDRESS = "BA";
    public static final String VALUE_TECHNICAL_ADDRESS = "TA";
    
    //General
    public static final String FIELD_GENERALSTARTINDEX = "generalStartIndex";
    public static final String FIELD_GENERALTOTALROW = "generalTotalRow";
    public static final String FIELD_GENERALDISPLAYROWCOUNT = "generalRow";
    public static final String FIELD_GENERALCURRENTROW = "generalNow";
    public static final String FIELD_GENERALTOTALPAGE = "generalTotalPage";
    public static final String FIELD_GENERALRESULTSET = "generalResultSet";
    public static final String FIELD_GENERALFULLRESULTSET = "generalFullResultSet";
    public static final String FIELD_GENERALCUSTOMERID = "generalCustomerID";
    public static final String FIELD_GENERALCUSTOMERNAME = "generalCustomerName";
    public static final String FIELD_GENERALCUSTOMERTYPE = "generalCustomerType";
    public static final String FIELD_GENERALCATEGORY = "generalCategory";
    public static final String FIELD_GENERALERVICE = "generalService";
    public static final String FIELD_GENERALPLAN = "generalPlan";
    public static final String FIELD_GENERALPLANDETAIL = "generalPlanDetail";
    public static final String FIELD_GENERALSUBSCRIPTIONID = "generalSubscriptionID";
    public static final String FIELD_GENERALDRAFTSERVICESTATUS = "generalDraftServiceStatus";
    public static final String FIELD_GENERALPENDINGSERVICESTATUS = "generalPendingApprovalServiceStatus";
    public static final String FIELD_GENERALACTIVESERVICESTATUS = "generalActiveServiceStatus";
    public static final String FIELD_GENERALONETIMESERVICESTATUS = "generalOneTimeServiceStatus";
    public static final String FIELD_GENERALCANCELLEDSERVICESTATUS = "generalCancelledServiceStatus";
    public static final String FIELD_GENERALTERMINATEDSERVICESTATUS = "generalTerminatedServiceStatus";
    public static final String FIELD_GENERALREJECTEDSERVICESTATUS = "generalRejectedServiceStatus";
    public static final String FIELD_GENERALSUSPENDEDSERVICESTATUS = "generalSuspendedServiceStatus";
    public static final String FIELD_GENERALBILLINGINSTRUCTION = "generalBillingInstruction";
    public static final String FIELD_GENERALBILLINGACCOUNT = "generalBillingAccount";
    public static final String FIELD_GENERALFROMSERVICEDATE = "generalFromServiceDate";
    public static final String FIELD_GENERALTOSERVICEDATE = "generalToServiceDate";
    public static final String FIELD_GENERALENDFROMSERVICEDATE = "generalEndFromServiceDate";
    public static final String FIELD_GENERALENDTOSERVICEDATE = "generalEndToServiceDate";
    public static final String FIELD_GENERALCONTRACTTERMMODE = "generalContractTermMode";
    public static final String FIELD_GENERALTIMECONTRACTTERM = "generalTimeContractTerm";
}
