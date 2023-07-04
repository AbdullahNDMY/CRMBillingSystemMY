/*
 * @(#)RP_B_BIL_S02_01Output.java
 *
 *
 */
package nttdm.bsys.b_bil.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * OutputDTO class.
 * 
 * @author khungl0ng
 */
public class RP_B_BIL_S02_01Output implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 3360200893028556997L;

	/**
     * 
     */
	private String idRef;

	/**
     * 
     */
	private String mode;

	/**
     * 
     */
	private HashMap<String, Object> headerInfo;

	/**
     * 
     */
	private HashMap<String, Object> custInfo;

	/**
     * 
     */
	private List<HashMap<String, Object>> detailInfo;

	/**
     * 
     */
	private List<Map<String, Object>> footerInfo;
	
	private List<Map<String, Object>> bankFooterInfo;

	private String lastMsg;
	
	private Map<String, Object> loadObject=new HashMap<String, Object>();
	
	private String gstCheck;
	
	// Add #156 Start
    private String billCnAmtNegative;
	// Add #156 End
    
    private String taxType;
    private String taxStr;
    private String taxRate;
    private BigDecimal taxableAmount;
    private BigDecimal nonTaxableAmount;
    
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
     * 
     */
	private HashMap<String, Object> subInfo;

	/**
     * 
     */
	private String accessType;
	
	/**
     *  get systemBase value
     */
	private String sysBaseVal;

	private String autSign;
	
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
	 * custInfo を�?�得�?�る
	 * 
	 * @return custInfo
	 */
	public HashMap<String, Object> getCustInfo() {
		return custInfo;
	}

	/**
	 * custInfo を設定�?�る
	 * 
	 * @param custInfo
	 */
	public void setCustInfo(HashMap<String, Object> custInfo) {
		this.custInfo = custInfo;
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
	 * subInfo を�?�得�?�る
	 * 
	 * @return subInfo
	 */
	public HashMap<String, Object> getSubInfo() {
		return subInfo;
	}

	/**
	 * subInfo を設定�?�る
	 * 
	 * @param subInfo
	 */
	public void setSubInfo(HashMap<String, Object> subInfo) {
		this.subInfo = subInfo;
	}

	/**
	 * accessType を�?�得�?�る
	 * 
	 * @return accessType
	 */
	public String getAccessType() {
		return accessType;
	}

	/**
	 * accessType を設定�?�る
	 * 
	 * @param accessType
	 */
	public void setAccessType(String accessType) {
		this.accessType = accessType;
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
     * @return the sysBaseVal
     */
    public String getSysBaseVal() {
        return sysBaseVal;
    }

    /**
     * @param sysBaseVal the sysBaseVal to set
     */
    public void setSysBaseVal(String sysBaseVal) {
        this.sysBaseVal = sysBaseVal;
    }

    /**
     * @return the autSign
     */
    public String getAutSign() {
        return autSign;
    }

    /**
     * @param autSign the autSign to set
     */
    public void setAutSign(String autSign) {
        this.autSign = autSign;
    }

    public Map<String, Object> getLoadObject() {
        return loadObject;
    }

    public void setLoadObject(Map<String, Object> loadObject) {
        this.loadObject = loadObject;
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

	public BigDecimal getTaxableAmount() {
		return taxableAmount;
	}

	public void setTaxableAmount(BigDecimal taxableAmount) {
		this.taxableAmount = taxableAmount;
	}

	public BigDecimal getNonTaxableAmount() {
		return nonTaxableAmount;
	}

	public void setNonTaxableAmount(BigDecimal nonTaxableAmount) {
		this.nonTaxableAmount = nonTaxableAmount;
	}
	
}