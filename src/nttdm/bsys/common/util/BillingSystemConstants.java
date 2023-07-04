/**
 * @(#)BillingSystemConstants.java
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.util;

/**
 * BillingSystem Constants
 */
public class BillingSystemConstants {

	// logic status value
	public static final String BLOGIC_RESULT_STR_SUCCESS = "success";

	public static final String BLOGIC_RESULT_STR_FAILURE = "failure";
	public static final String BLOGIC_RESULT_STR_DUPLICATE = "duplicate";
	public static final String BLOGIC_RESULT_STR_VIEW = "view";

	// date format
	public static final String DATE_FORMAT = "dd/MM/yyyy";

	public static final String DATETIME_FORMAT = "dd/MM/yyyy HH:mm:ss";
	public static final String DATETIME_FORMAT_EXPORT = "ddMMyyyy_HHmmss";
	public static final String DATETIME_FORMAT_B_SSM_EXPORT = "yyMMddHHmmss";
	// Document status value
	public static final String DOCUMENT_STATUS_DRAFTED = "Drafted";
	public static final String DOCUMENT_STATUS_OPEN = "Open";
	public static final String DOCUMENT_STATUS_APPROVAL_IN_PROGRESS = "Approval In-Progress";
	public static final String DOCUMENT_STATUS_COMPLETED = "Completed";
	public static final String DOCUMENT_STATUS_REJECTED = "Rejected";
	public static final String DOCUMENT_STATUS_CANCELLED = "Cancelled";
	// Document status value
	public static final String DOCUMENT_STATUS_DRAFTED_VALUE = "DS0";
	public static final String DOCUMENT_STATUS_OPEN_VALUE = "DS1";
	public static final String DOCUMENT_STATUS_APPROVAL_IN_PROGRESS_VALUE = "DS2";
	public static final String DOCUMENT_STATUS_COMPLETED_VALUE = "DS3";
	public static final String DOCUMENT_STATUS_REJECTED_VALUE = "DS4";
	public static final String DOCUMENT_STATUS_CANCELLED_VALUE = "DS5";
	// Approval status
	public static final String APPROVAL_STATUS_PENDING = "Pending for Approval";
	public static final String APPROVAL_STATUS_APPROVED = "Approved";
	public static final String APPROVAL_STATUS_REJECTED = "Rejected";
	public static final String APPROVAL_STATUS_CANCELLED = "Cancelled";
	public static final String APPROVAL_STATUS_NOT_REQUIRE = "Not Require Approval";
	public static final String APPROVAL_STATUS_FORWARDED = "Forwarded";
	public static final String APPROVAL_STATUS_ESCALATION = "Escalation/Forward";
	// Approval status value
	public static final String APPROVAL_STATUS_PENDING_VALUE = "AS1";
	public static final String APPROVAL_STATUS_APPROVED_VALUE = "AS2";
	public static final String APPROVAL_STATUS_REJECTED_VALUE = "AS3";
	public static final String APPROVAL_STATUS_CANCELLED_VALUE = "AS4";
	public static final String APPROVAL_STATUS_NOT_REQUIRE_VALUE = "AS5";
	public static final String APPROVAL_STATUS_FORWARDED_VALUE = "AS6";
	public static final String APPROVAL_STATUS_ESCALATION_VALUE = "AS7";

	public static final String CREDIT_TERM_COD_VALUE = "1";
	public static final String CREDIT_TERM_30DAYS_VALUE = "2";
	public static final String CREDIT_TERM_OTHERS_VALUE = "3";

	public static final String DEPOSIT_WAIVED_VALUE = "1";
	public static final String DEPOSIT_1MONTH_VALUE = "2";
	public static final String DEPOSIT_2MONTHS_VALUE = "3";

	// Section no
	public static final String SECTION_NO_QCS_VALUE = "Q000";
	public static final String SECTION_NO_PMR_STANDARD_VALUE = "Q011";
	public static final String SECTION_NO_PMR_NON_STANDARD_VALUE = "Q012";
	public static final String SECTION_NO_BZR_STANDARD_VALUE = "Q021";
	public static final String SECTION_NO_BZR_NON_STANDARD_VALUE = "Q022";
	public static final String SECTION_NO_CTC_STANDARD_VALUE = "Q031";
	public static final String SECTION_NO_CTC_NON_STANDARD_VALUE = "Q032";
	public static final String SECTION_NO_PRI_STANDARD_VALUE = "Q041";
	public static final String SECTION_NO_PRI_NON_STANDARD_VALUE = "Q042";
	public static final String SECTION_NO_MRG_STANDARD_VALUE = "Q051";
	public static final String SECTION_NO_MRG_NON_STANDARD_Q052_VALUE = "Q052";
	public static final String SECTION_NO_MRG_NON_STANDARD_Q053_VALUE = "Q053";
	public static final String SECTION_NO_CRC_STANDARD_VALUE = "Q061";
	public static final String SECTION_NO_CRC_NON_STANDARD_VALUE = "Q062";
	public static final String SECTION_NO_FRX_STANDARD_VALUE = "Q071";
	public static final String SECTION_NO_FRX_NON_STANDARD_Q072_VALUE = "Q072";
	public static final String SECTION_NO_FRX_NON_STANDARD_Q073_VALUE = "Q073";
	public static final String SECTION_NO_COV_STANDARD_VALUE = "Q081";
	public static final String SECTION_NO_COV_NON_STANDARD_Q082_VALUE = "Q082";
	public static final String SECTION_NO_COV_NON_STANDARD_Q083_VALUE = "Q083";

	// Section group
	public static final String SECTION_GROUP_QCS_VALUE = "QCS";
	public static final String SECTION_GROUP_PMR_VALUE = "PMR";
	public static final String SECTION_GROUP_BZR_VALUE = "BZR";
	public static final String SECTION_GROUP_CTC_VALUE = "CTC";
	public static final String SECTION_GROUP_PRI_VALUE = "PRI";
	public static final String SECTION_GROUP_MRG_VALUE = "MRG";
	public static final String SECTION_GROUP_CRC_VALUE = "CRC";
	public static final String SECTION_GROUP_FRX_VALUE = "FRX";
	public static final String SECTION_GROUP_COV_VALUE = "COV";
	public static final String SECTION_GROUP_QUO_VALUE = "QUO";

	// Foreign exchange
	public static final String FRX_MYR_VALUE = "MYR";
	public static final String FRX_USD_VALUE = "USD";

	public static final String ID_SCREEN_QCS = "QCS";
	public static final String ID_SUB_MODULE = "Q-QCS";

	// Doc type
	public static final String DOCTYPE_QUOTATION = "DQUO";

	// Action type
	public static final String ACTION_TYPE_AA = "AA";
	public static final String ACTION_TYPE_AO = "AO";

	// Revise value
	public static final String IS_REVISED_0 = "0";
	public static final String IS_REVISED_1 = "1";

	// Audit Trail action
	public static final String AUDIT_TRAIL_CREATED = "Created";
	public static final String AUDIT_TRAIL_EDITED = "Edited";
	public static final String AUDIT_TRAIL_DELETED = "Deleted";
	public static final String AUDIT_TRAIL_LOGIN = "Login";
	public static final String AUDIT_TRAIL_LOGOUT = "Logout";
	public static final String AUDIT_TRAIL_VIEWED = "Viewed";

	public static final String USER_SYSTEM_DEFAULT = "system";

	// modules
	public static final String MODULE_A = "A";
	public static final String MODULE_B = "B";
	public static final String MODULE_E = "E";
	public static final String MODULE_M = "M";
	public static final String MODULE_Q = "Q";
	public static final String MODULE_R = "R";

	// sub modules
	public static final String SUB_MODULE_A_USR = "A-USR";
	public static final String SUB_MODULE_A_PWD = "A-PWD";
	public static final String SUB_MODULE_A_ADT = "A-ADT";
	public static final String SUB_MODULE_B_CPM = "B-CPM";
	public static final String SUB_MODULE_B_CPM_SB = "B-CPM-SB";
	public static final String SUB_MODULE_B_BIF = "B-BIF";
	public static final String SUB_MODULE_B_BAC = "B-BAC";
	public static final String SUB_MODULE_B_BIL = "B-BIL";	
	public static final String SUB_MODULE_B_SSM = "B-SSM";
	public static final String SUB_MODULE_B_BTH = "B-BTH";
	public static final String SUB_MODULE_B_CSB = "B-CSB";
	public static final String SUB_MODULE_B_TRM = "B-TRM";
	public static final String SUB_MODULE_B_CLS = "B-CLS";
	public static final String SUB_MODULE_E_SET = "E-SET";
	public static final String SUB_MODULE_E_MIM_US1 = "E-MIM-US1";
	public static final String SUB_MODULE_E_MIM_US2 = "E-MIM-US2";
	public static final String SUB_MODULE_E_MIM_US3 = "E-MIM-US3";
	public static final String SUB_MODULE_E_MEX_SP1 = "E-MEX-SP1";
	public static final String SUB_MODULE_E_MEX_GR1 = "E-MEX-GR1";
	public static final String SUB_MODULE_E_MEX_CT1 = "E-MEX-CT1";
	public static final String SUB_MODULE_E_DIM_SP1 = "E-DIM-SP1";
	public static final String SUB_MODULE_E_DIM_GR1 = "E-DIM-GR1";
	public static final String SUB_MODULE_E_DIM_CT1 = "E-DIM-CT1";
	public static final String SUB_MODULE_M_CST = "M-CST";
	public static final String SUB_MODULE_M_CST_BI = "M-CST-BI";
	public static final String SUB_MODULE_M_CST_AC = "M-CST-AC";
	public static final String SUB_MODULE_M_SVG = "M-SVG";
	public static final String SUB_MODULE_M_SVT = "M-SVT";
	public static final String SUB_MODULE_M_WLM = "M-WLM";
	public static final String SUB_MODULE_M_ATM = "M-ATM";
	public static final String SUB_MODULE_M_CDM = "M-CDM";
	public static final String SUB_MODULE_M_CUR = "M-CUR";
	public static final String SUB_MODULE_M_ALT = "M-ALT";
	public static final String SUB_MODULE_M_JNM = "M-JNM";
	public static final String SUB_MODULE_M_PPM = "M-PPM";
	public static final String SUB_MODULE_M_PBM = "M-PBM";
	public static final String SUB_MODULE_M_SSM = "M-SSM";
	public static final String SUB_MODULE_M_GBS = "M-GBS";
	public static final String SUB_MODULE_M_COM = "M-COM";
	public static final String SUB_MODULE_M_COM_BI = "M-COM-BI";
	public static final String SUB_MODULE_M_BNK = "M-BNK";
	public static final String SUB_MODULE_M_EML = "M-EML";
	public static final String SUB_MODULE_Q_QCS = "Q-QCS";
	public static final String SUB_MODULE_R_SAL = "R-SAL";
	public static final String SUB_MODULE_R_RRR = "R-RRR";
	public static final String SUB_MODULE_R_AGR = "R-AGR";
	public static final String SUB_MODULE_R_ACR = "R-ACR";
}
