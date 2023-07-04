/**
 * Billing System
 * 
 * SUBSYSTEM NAME : G_BTH_P01 
 * SERVICE NAME :  B_BTH_P01
 * FUNCTION : Print Billing Document
 * FILE NAME : G_BTH_P01_2.java
 * 
 * Copyright (C) 2011 NTTDATA Corporation
 */
package nttdm.bsys.common.util.dto;

import java.util.List;

/**
 * used in e_exp_f02 as output parameters
 * 
 * @author NTT Data E_EXP_F02Output.java
 */
public class E_EXP_F02Output {
	// error message
	private String[] msg = {};
	// batch status
	private String batchStatus = "";
	// inserted statement no list
	private List<String> listStatementNo = null;

	public String[] getMsg() {
		return msg;
	}

	public void setMsg(String[] msg) {
		this.msg = msg;
	}

	public String getBatchStatus() {
		return batchStatus;
	}

	public void setBatchStatus(String batchStatus) {
		this.batchStatus = batchStatus;
	}

	public List<String> getListStatementNo() {
		return listStatementNo;
	}

	public void setListStatementNo(List<String> listStatementNo) {
		this.listStatementNo = listStatementNo;
	}

}
