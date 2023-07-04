/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : Customer Plan Management
 * SERVICE NAME   : B_CPM Constant
 * FUNCTION       : define constant that is use in B-CPM module
 * FILE NAME      : B_CPM_CONSTANT.java
 * 
 * Copyright c 2011 NTTDATA Corporation
 *
 * History
 * 2011/07/26 [Duong Nguyen] Created
 * 2011/10/24 [Duong Nguyen] Fix Bug #2823
 * 2011/10/25 [Duong Nguyen] Fix Bug #2834
 * 
**********************************************************/
package nttdm.bsys.b_cpm_en.dto;

/**
 * B_CPM_Common.class<br>
 * <ul>
 * <li>define constant for B-CPM</li>
 * </ul>
 * <br>
 * @author  NTTData Vietnam
 * @version 1.1
 */
public interface B_CPM_CONSTANT {
	public static final String NAMESPACE = "B_CPM."; 
	public static final String STANDARD_PLAN = "SP";
	public static final String NON_STANDARD_PLAN = "NP";
	
	public static final String SCR_B_CPM_02 = "2";
	public static final String SCR_B_CPM_05 = "5";
	
	public static final String INVOICE = "IN";
	public static final String CREDIT_NOTE = "CN";
	public static final String DEBIT_NOTE = "DN";
	
	public static final String SUBSCRIPTION_CODE = "SCPID";
	public static final String INVOICE_CODE = "INVNO";
	public static final String CREDIT_NOTE_CODE = "CDTNO";
	public static final String DEBIT_NOTE_CODE = "DBTNO";
	
	public static final String ACTION_SUSPEND = "suspend";
	public static final String ACTION_UNSUSPEND = "unsuspend";
	public static final String ACTION_TERMANITE = "terminate";
	
	static final String SAVE_ERROR_MSG = "info.ERR2SC004";
	static final String SAVE_SUCCESS_MSG = "info.ERR2SC003";
	static final String DELETE_SUCCESS_MSG = "info.ERR2SC005";
}
