/**
 * @(#)R_SOA_S01SearchOutput.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2012/07/02
 * 
 * Copyright (c) 2012 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.r_soa.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * @author gplai
 *
 */
public class R_SOA_S01SearchOutput implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = -747861922073612788L;

    private Integer row;

    private Integer startIndex;

    private Integer totalRow;

    private List<HashMap<String, Object>> listStatement;
    
    private String[] selectedStatementNo;
    
    private String fullStmtNo;
    
    private String lastMsg;
    
    private String clickBtnTypeFlg;
    
	private String[] cboPaymentCurrencyArray;
   
	/**
	 * @return the cboPaymentCurrencyArray
	 */
	public String[] getCboPaymentCurrencyArray() {
		return cboPaymentCurrencyArray;
	}

	/**
	 * @param cboPaymentCurrencyArray the cboPaymentCurrencyArray to set
	 */
	public void setCboPaymentCurrencyArray(String[] cboPaymentCurrencyArray) {
		this.cboPaymentCurrencyArray = cboPaymentCurrencyArray;
	}

	/**
     * @return the row
     */
    public Integer getRow() {
        return row;
    }

    /**
     * @param row the row to set
     */
    public void setRow(Integer row) {
        this.row = row;
    }

    /**
     * @return the startIndex
     */
    public Integer getStartIndex() {
        return startIndex;
    }

    /**
     * @param startIndex the startIndex to set
     */
    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    /**
     * @return the totalRow
     */
    public Integer getTotalRow() {
        return totalRow;
    }

    /**
     * @param totalRow the totalRow to set
     */
    public void setTotalRow(Integer totalRow) {
        this.totalRow = totalRow;
    }

    /**
     * @return the listStatement
     */
    public List<HashMap<String, Object>> getListStatement() {
        return listStatement;
    }

    /**
     * @param listStatement the listStatement to set
     */
    public void setListStatement(List<HashMap<String, Object>> listStatement) {
        this.listStatement = listStatement;
    }

    /**
     * @return the selectedStatementNo
     */
    public String[] getSelectedStatementNo() {
        return selectedStatementNo;
    }

    /**
     * @param selectedStatementNo the selectedStatementNo to set
     */
    public void setSelectedStatementNo(String[] selectedStatementNo) {
        this.selectedStatementNo = selectedStatementNo;
    }

    /**
     * @return the fullStmtNo
     */
    public String getFullStmtNo() {
        return fullStmtNo;
    }

    /**
     * @param fullStmtNo the fullStmtNo to set
     */
    public void setFullStmtNo(String fullStmtNo) {
        this.fullStmtNo = fullStmtNo;
    }

    /**
     * @return the lastMsg
     */
    public String getLastMsg() {
        return lastMsg;
    }

    /**
     * @param lastMsg the lastMsg to set
     */
    public void setLastMsg(String lastMsg) {
        this.lastMsg = lastMsg;
    }

    /**
     * @return the clickBtnTypeFlg
     */
    public String getClickBtnTypeFlg() {
        return clickBtnTypeFlg;
    }

    /**
     * @param clickBtnTypeFlg the clickBtnTypeFlg to set
     */
    public void setClickBtnTypeFlg(String clickBtnTypeFlg) {
        this.clickBtnTypeFlg = clickBtnTypeFlg;
    }
}
