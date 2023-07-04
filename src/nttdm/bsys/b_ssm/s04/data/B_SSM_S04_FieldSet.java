/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : B_SSM
 * SERVICE NAME   : B_SSM_S04
 * FUNCTION       : Constants of field set in B_SSM_S04
 * FILE NAME      : B_SSM_S04_FieldSet.java
 *
 * Copyright (C) 2011 NTTDATA Corporation
 * 
**********************************************************/

package nttdm.bsys.b_ssm.s04.data;

/**
 * @author NTT Data Vietnam
 * Constants of field set in B_SSM_S04
 */
public class B_SSM_S04_FieldSet {
	
	// Field name constants
	public static final String FIELD_ISPOPUP = "isPopUp";
	public static final String FIELD_CUSTOMERID = "customerID";
	public static final String FIELD_CUSTOMERPLANID = "customerPlanID";
	public static final String FIELD_CUSTOMERNAME = "customerName";	
	public static final String FIELD_SUBSCRIPTIONID = "subscriptionID";
	public static final String FIELD_PROCESSMODE = "processMode";
	public static final String FIELD_BILLINGINSTRUCTION = "billingInstruction";
	public static final String FIELD_SERVICENAMELIST = "serviceNameList";
	public static final String FIELD_SERVICENAME = "serviceName";
	public static final String FIELD_SERVICEGROUPID = "serviceGroupID";
	public static final String FIELD_PLANDESCRIPTION = "planDescription";
	public static final String FIELD_CONTRACTPERIOD = "contractPeriod";
	public static final Object FIELD_STARTCONTRACTPERIOD = "startContractPeriod";
	public static final Object FIELD_ENDCONTRACTPERIOD = "endContractPeriod";
	public static final String FIELD_CONTRACTTERMS = "contractTerms";
	public static final String FIELD_SERVICESTATUS = "serviceStatus";
	
	// Process Mode
	public static final int PROCESSMODE_EDIT = 1;	
	public static final int PROCESSMODE_VIEW = 0;
	public static final int PROCESSMODE_SAVE = 2;
	public static final int PROCESSMODE_EXIT = 3;
	public static final int PROCESSMODE_PRINTREPORT = 4;
	public static final int PROCESSMODE_EXPORTITCONTACT = 5;
	
	// Edited status
	public static final String STATUS_EDITED_FAIL = "fail";	
	public static final String STATUS_EDITED_SUCCESS = "success";
}
