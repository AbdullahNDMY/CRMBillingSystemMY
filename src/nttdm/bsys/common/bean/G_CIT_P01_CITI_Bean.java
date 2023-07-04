/**
 * @(#)G_CIT_P01_CITI_Bean.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created Sep 5, 2011
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.bean;

import java.math.BigDecimal;

/**
 * @author Joel Chin Yat Leng
 * 
 */
public class G_CIT_P01_CITI_Bean {

	private String CCARD_NO;
	private String CCARD_EXPIRED_STRING;
	private String ID_REF; // from T_BIL_H
	private BigDecimal TOTAL_AMT_DUE; // From T_BAC_H
	private String RECEIPT_NO;
	private String ID_LOGIN;
	private String ID_BILL_ACCOUNT;
	private String ID_CUST;
	private String CUST_NAME;
	private String BILL_CURRENCY;
	private String DATE_REQ;
	private String CCARD_EXPIRED_DATE;
    private String CCARD_EXPIRED_DATE1;

	public String getCCARD_EXPIRED_DATE() {
		return CCARD_EXPIRED_DATE;
	}

	public void setCCARD_EXPIRED_DATE(String cCARD_EXPIRED_DATE) {
		CCARD_EXPIRED_DATE = cCARD_EXPIRED_DATE;
	}

	private BigDecimal collectionAmount;

	public BigDecimal getCollectionAmount() {
		return collectionAmount;
	}

	public void setCollectionAmount(BigDecimal collectionAmount) {
		this.collectionAmount = collectionAmount;
	}

	public String getDATE_REQ() {
		return DATE_REQ;
	}

	public void setDATE_REQ(String dATE_REQ) {
		DATE_REQ = dATE_REQ;
	}

	public String getCCARD_NO() {
		return CCARD_NO;
	}

	public void setCCARD_NO(String cCARD_NO) {
		CCARD_NO = cCARD_NO;
	}

	public String getCCARD_EXPIRED_STRING() {
		return CCARD_EXPIRED_STRING;
	}

	public void setCCARD_EXPIRED_STRING(String cCARD_EXPIRED_STRING) {
		CCARD_EXPIRED_STRING = cCARD_EXPIRED_STRING;
	}

	public String getID_REF() {
		return ID_REF;
	}

	public void setID_REF(String iD_REF) {
		ID_REF = iD_REF;
	}

	public java.math.BigDecimal getTOTAL_AMT_DUE() {
		return TOTAL_AMT_DUE;
	}

	public void setTOTAL_AMT_DUE(java.math.BigDecimal tOTAL_AMT_DUE) {
		TOTAL_AMT_DUE = tOTAL_AMT_DUE;
	}

	public String getRECEIPT_NO() {
		return RECEIPT_NO;
	}

	public void setRECEIPT_NO(String rECEIPT_NO) {
		RECEIPT_NO = rECEIPT_NO;
	}

	public String getID_LOGIN() {
		return ID_LOGIN;
	}

	public void setID_LOGIN(String iD_LOGIN) {
		ID_LOGIN = iD_LOGIN;
	}

	public String getID_BILL_ACCOUNT() {
		return ID_BILL_ACCOUNT;
	}

	public void setID_BILL_ACCOUNT(String iD_BILL_ACCOUNT) {
		ID_BILL_ACCOUNT = iD_BILL_ACCOUNT;
	}

	public String getID_CUST() {
		return ID_CUST;
	}

	public void setID_CUST(String iD_CUST) {
		ID_CUST = iD_CUST;
	}

	public String getBILL_CURRENCY() {
		return BILL_CURRENCY;
	}

	public void setBILL_CURRENCY(String bILL_CURRENCY) {
		BILL_CURRENCY = bILL_CURRENCY;
	}

    /**
     * @return the cUST_NAME
     */
    public String getCUST_NAME() {
        return CUST_NAME;
    }

    /**
     * @param cUST_NAME the cUST_NAME to set
     */
    public void setCUST_NAME(String cUST_NAME) {
        CUST_NAME = cUST_NAME;
    }

    /**
     * @return the cCARD_EXPIRED_DATE1
     */
    public String getCCARD_EXPIRED_DATE1() {
        return CCARD_EXPIRED_DATE1;
    }

    /**
     * @param cCARD_EXPIRED_DATE1 the cCARD_EXPIRED_DATE1 to set
     */
    public void setCCARD_EXPIRED_DATE1(String cCARD_EXPIRED_DATE1) {
        CCARD_EXPIRED_DATE1 = cCARD_EXPIRED_DATE1;
    }
}