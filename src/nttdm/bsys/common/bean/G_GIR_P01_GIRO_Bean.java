/**
 * @(#)G_GIR_P01_GIRO_Bean.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created Aug 23, 2011
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.bean;

import java.math.BigDecimal;

/**
 * @author Joel Chin Yat Leng
 * object created for Retrieving Giro Details (joining 2 tables; M_BANK and M_BANKINFO)
 */
public class G_GIR_P01_GIRO_Bean {
	//a.GIRO_ACCT_NO, a.NO_REFERENCE, b.BANK_CODE
	
	private String GIRO_ACCT_NO;
	private String NO_REFERENCE;
	private String BANK_CODE;
	private BigDecimal TOTAL_AMT_DUE;
	private String GIRO_ACCT_NAME;
	private String ID_BILL_ACCOUNT;
	private String RECEIPT_NO;
	private String ID_REF; 	
	private String BILL_CURRENCY;
	private String ID_CUST;
	private String ID_LOGIN;
	private String BRANCH_CODE;
	
	private String BIC_CODE;
		
	public String getBIC_CODE() {
		return BIC_CODE;
	}

	public void setBIC_CODE(String bIC_CODE) {
		BIC_CODE = bIC_CODE;
	}

	
	private BigDecimal collectionAmount;

	public String getBRANCH_CODE() {
		return BRANCH_CODE;
	}

	public void setBRANCH_CODE(String bRANCH_CODE) {
		BRANCH_CODE = bRANCH_CODE;
	}

	public BigDecimal getCollectionAmount() {
		return collectionAmount;
	}

	public void setCollectionAmount(BigDecimal collectionAmount) {
		this.collectionAmount = collectionAmount;
	}

	/**
	 * @return the iD_LOGIN
	 */
	public String getID_LOGIN() {
		return ID_LOGIN;
	}

	/**
	 * @param iD_LOGIN the iD_LOGIN to set
	 */
	public void setID_LOGIN(String iD_LOGIN) {
		ID_LOGIN = iD_LOGIN;
	}

	public String getID_CUST() {
		return ID_CUST;
	}
	
	public String getBILL_CURRENCY() {
		return BILL_CURRENCY;
	}

	public void setBILL_CURRENCY(String bILL_CURRENCY) {
		BILL_CURRENCY = bILL_CURRENCY;
	}
	
	public void setID_CUST(String iD_CUST) {
		ID_CUST = iD_CUST;
	}
	
	public String getID_REF() {
		return ID_REF;
	}

	public void setID_REF(String iD_REF) {
		ID_REF = iD_REF;
	}
	//TODO: generate the constructor.
	
	/**
	 * @return the rECEIPT_NO
	 */
	public String getRECEIPT_NO() {
		return RECEIPT_NO;
	}
	/**
	 * @param rECEIPT_NO the rECEIPT_NO to set
	 */
	public void setRECEIPT_NO(String rECEIPT_NO) {
		RECEIPT_NO = rECEIPT_NO;
	}
	/**
	 * @return the iD_BILL_ACCOUNT
	 */
	public String getID_BILL_ACCOUNT() {
		return ID_BILL_ACCOUNT;
	}
	/**
	 * @param iD_BILL_ACCOUNT the iD_BILL_ACCOUNT to set
	 */
	public void setID_BILL_ACCOUNT(String iD_BILL_ACCOUNT) {
		ID_BILL_ACCOUNT = iD_BILL_ACCOUNT;
	}
	/**
	 * @return the gIRO_ACCT_NAME
	 */
	public String getGIRO_ACCT_NAME() {
		return GIRO_ACCT_NAME;
	}

	/**
	 * @param gIRO_ACCT_NAME the gIRO_ACCT_NAME to set
	 */
	public void setGIRO_ACCT_NAME(String gIRO_ACCT_NAME) {
		GIRO_ACCT_NAME = gIRO_ACCT_NAME;
	}

	/**
	 * @return the gIRO_ACCT_NO
	 */
	public String getGIRO_ACCT_NO() {
		return GIRO_ACCT_NO;
	}
	/**
	 * @return the tOTAL_AMT_DUE
	 */
	public java.math.BigDecimal getTOTAL_AMT_DUE() {
		return TOTAL_AMT_DUE;
	}
	/**
	 * @param tOTAL_AMT_DUE the tOTAL_AMT_DUE to set
	 */
	public void setTOTAL_AMT_DUE(java.math.BigDecimal tOTAL_AMT_DUE) {
		TOTAL_AMT_DUE = tOTAL_AMT_DUE;
	}
	/**
	 * @param gIRO_ACCT_NO the gIRO_ACCT_NO to set
	 */
	public void setGIRO_ACCT_NO(String gIRO_ACCT_NO) {
		GIRO_ACCT_NO = gIRO_ACCT_NO;
	}
	/**
	 * @return the nO_REFERENCE
	 */
	public String getNO_REFERENCE() {
		return NO_REFERENCE;
	}
	/**
	 * @param nO_REFERENCE the nO_REFERENCE to set
	 */
	public void setNO_REFERENCE(String nO_REFERENCE) {
		NO_REFERENCE = nO_REFERENCE;
	}
	/**
	 * @return the bANK_CODE
	 */
	public String getBANK_CODE() {
		return BANK_CODE;
	}
	/**
	 * @param bANK_CODE the bANK_CODE to set
	 */
	public void setBANK_CODE(String bANK_CODE) {
		BANK_CODE = bANK_CODE;
	}
	
	

}
