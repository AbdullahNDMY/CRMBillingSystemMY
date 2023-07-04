/**
 * @(#)R_BAC_R02Output.java
 * Billing System
 * Version 1.00
 * Created 2011-8-29
 * Copyright (c) 2011 NTT DATA . All Rights Reserved.
 */
package nttdm.bsys.r_bac.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * R_BAC_R02Output class.
 * 
 * @author NTTD DATA
 */
public class R_BAC_R02Output {

	/**
	 * page row number
	 */
	private Integer row;

	/**
	 * startIndex
	 */
	private Integer startIndex;

	/**
	 * totalRow
	 */
	private Integer totalRow;

	/**
	 * customer ID
	 */
	private String custId = "";
	/**
	 * access Type
	 */
	private String accessType;

	/**
	 * payment method
	 */
	private String paymentMethod = "";

	/**
	 * customer name
	 */
	private String custName = "";

	/**
	 * billing currency
	 */
	private String billCurrency = "";

	/**
	 * billing account NO.
	 */
	private String billAcc = "";

	/**
	 * is Total Amount Due <> 0
	 */
	private String totalamount = "";

	/**
	 * result list
	 */
	private List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();

	public Integer getRow() {
		return row;
	}

	public void setRow(Integer row) {
		this.row = row;
	}

	public Integer getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

	public Integer getTotalRow() {
		return totalRow;
	}

	public void setTotalRow(Integer totalRow) {
		this.totalRow = totalRow;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getAccessType() {
		return accessType;
	}

	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getBillCurrency() {
		return billCurrency;
	}

	public void setBillCurrency(String billCurrency) {
		this.billCurrency = billCurrency;
	}

	public String getBillAcc() {
		return billAcc;
	}

	public void setBillAcc(String billAcc) {
		this.billAcc = billAcc;
	}

	public String getTotalamount() {
		return totalamount;
	}

	public void setTotalamount(String totalamount) {
		this.totalamount = totalamount;
	}

	public List<Map<String, Object>> getResultList() {
		return resultList;
	}

	public void setResultList(List<Map<String, Object>> resultList) {
		this.resultList = resultList;
	}

}
