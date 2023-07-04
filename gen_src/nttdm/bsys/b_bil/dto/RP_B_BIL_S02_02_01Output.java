/*
 * @(#)RP_B_BIL_S02_02_01Output.java
 *
 *
 */
package nttdm.bsys.b_bil.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nttdm.bsys.b_bil.bean.T_BIL_HeaderInfo;

/**
 * OutputDTO class.
 * 
 * @author khungl0ng
 */
public class RP_B_BIL_S02_02_01Output implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6352213641388502111L;

	/**
     * 
     */
	private String idRef;

	/**
     * 
     */
	private String mode;
	
	private String lastMsg;

	/**
     * 
     */
	private HashMap<String, Object> headerInfo;

	/**
     * 
     */
	private List<HashMap<String, Object>> detailInfo;
	
	private List<Map<String, Object>> footerInfo;
	
	private List<Map<String, Object>> bankFooterInfo;
	
	private T_BIL_HeaderInfo bilHeaderInfo;
	
	private String gstCheck;
	// Add #156 Start
    private String billCnAmtNegative;
	// Add #156 End
    
    private String taxType;
    private String taxStr;
    private String taxRate;

	/**
	 * @return the bankFooterInfo
	 */
	public List<Map<String, Object>> getBankFooterInfo() {
		return bankFooterInfo;
	}

	/**
	 * @param bankFooterInfo the bankFooterInfo to set
	 */
	public void setBankFooterInfo(List<Map<String, Object>> bankFooterInfo) {
		this.bankFooterInfo = bankFooterInfo;
	}

	public List<Map<String, Object>> getFooterInfo() {
		return footerInfo;
	}

	public void setFooterInfo(List<Map<String, Object>> footerInfo) {
		this.footerInfo = footerInfo;
	}

	/**
	 * idRef を�?�得�?�る
	 * 
	 * @return idRef
	 */
	public String getIdRef() {
		return idRef;
	}

	/**
	 * idRef を設定�?�る
	 * 
	 * @param idRef
	 */
	public void setIdRef(String idRef) {
		this.idRef = idRef;
	}

	/**
	 * mode を�?�得�?�る
	 * 
	 * @return mode
	 */
	public String getMode() {
		return mode;
	}

	/**
	 * mode を設定�?�る
	 * 
	 * @param mode
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}

	/**
	 * headerInfo を�?�得�?�る
	 * 
	 * @return headerInfo
	 */
	public HashMap<String, Object> getHeaderInfo() {
		return headerInfo;
	}

	/**
	 * headerInfo を設定�?�る
	 * 
	 * @param headerInfo
	 */
	public void setHeaderInfo(HashMap<String, Object> headerInfo) {
		this.headerInfo = headerInfo;
	}

	/**
	 * detailInfo を�?�得�?�る
	 * 
	 * @return detailInfo
	 */
	public List<HashMap<String, Object>> getDetailInfo() {
		return detailInfo;
	}

	/**
	 * detailInfo を設定�?�る
	 * 
	 * @param detailInfo
	 */
	public void setDetailInfo(List<HashMap<String, Object>> detailInfo) {
		this.detailInfo = detailInfo;
	}

    /**
     * @return the bilHeaderInfo
     */
    public T_BIL_HeaderInfo getBilHeaderInfo() {
        return bilHeaderInfo;
    }

    /**
     * @param bilHeaderInfo the bilHeaderInfo to set
     */
    public void setBilHeaderInfo(T_BIL_HeaderInfo bilHeaderInfo) {
        this.bilHeaderInfo = bilHeaderInfo;
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
	 * @return the gstCheck
	 */
	public String getGstCheck() {
		return gstCheck;
	}

	/**
	 * @param gstCheck the gstCheck to set
	 */
	public void setGstCheck(String gstCheck) {
		this.gstCheck = gstCheck;
	}
    // Add #156 Start
	/**
	 * @return the billCnAmtNegative
	 */
	public String getBillCnAmtNegative() {
		return billCnAmtNegative;
	}

	/**
	 * @param billCnAmtNegative the billCnAmtNegative to set
	 */
	public void setBillCnAmtNegative(String billCnAmtNegative) {
		this.billCnAmtNegative = billCnAmtNegative;
	}
	// Add #156 End

	public String getTaxType() {
		return taxType;
	}

	public void setTaxType(String taxType) {
		this.taxType = taxType;
	}

	public String getTaxStr() {
		return taxStr;
	}

	public void setTaxStr(String taxStr) {
		this.taxStr = taxStr;
	}

	public String getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(String taxRate) {
		this.taxRate = taxRate;
	}
	
}