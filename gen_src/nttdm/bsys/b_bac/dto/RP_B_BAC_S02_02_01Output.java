/*
 * @(#)RP_B_BAC_S02_02_01Output.java
 *
 *
 */
package nttdm.bsys.b_bac.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import nttdm.bsys.common.util.BillingSystemSettings;

import org.apache.struts.util.LabelValueBean;

/**
 * OutputDTO class.
 * 
 * @author dunglq
 */
public class RP_B_BAC_S02_02_01Output implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -548222894473512237L;

	/**
     * 
     */
	private List<HashMap<String, Object>> billRefInfo;

	/**
     * 
     */
	private HashMap<String, Object> custInfo;

	/**
     * 
     */
	private List<HashMap<String, Object>> listPlanInfo;

	/**
     * 
     */
	private HashMap<String, Object> addressInfo;

	/**
     * 
     */
	private HashMap<String, Object> headerInfo;

	/**
     * 
     */
	private HashMap<String, Object> contactInfo;

	/**
     * 
     */
	private List<LabelValueBean> listContact;

	/**
     * 
     */
	private List<HashMap<String, Object>> billRefInfoSess;

	/**
     * 
     */
	private List<HashMap<String, Object>> listPlanInfoSess;

	/**
     * 
     */
	private String billRefDispCond;

	/**
     * 
     */
	private String billRefDispCondSess;

	/**
     * 
     */
	private String accessType;

	/**
     * 
     */
	private String isDisplayTerCnBut;
	
	/**
     * 
     */
    private BillingSystemSettings bss;
    
    /**
     * 
     */
    private String editFlg;
    
    private String bacType;
    
    private String fixedForex;
    
    private String fromPage;
    
    private String idRef;
    
    private String referenceDisplay;
    
    private String checkBoxDisPlayFlg;
    
    private String taxTypeDisplay;

	/**
	 * @return the referenceDisplay
	 */
	public String getReferenceDisplay() {
		return referenceDisplay;
	}

	/**
	 * @param referenceDisplay the referenceDisplay to set
	 */
	public void setReferenceDisplay(String referenceDisplay) {
		this.referenceDisplay = referenceDisplay;
	}

	/**
	 * billRefInfo を取得する
	 * 
	 * @return billRefInfo
	 */
	public List<HashMap<String, Object>> getBillRefInfo() {
		return billRefInfo;
	}

	/**
	 * billRefInfo を設定する
	 * 
	 * @param billRefInfo
	 */
	public void setBillRefInfo(List<HashMap<String, Object>> billRefInfo) {
		this.billRefInfo = billRefInfo;
	}

	/**
	 * custInfo を取得する
	 * 
	 * @return custInfo
	 */
	public HashMap<String, Object> getCustInfo() {
		return custInfo;
	}

	/**
	 * custInfo を設定する
	 * 
	 * @param custInfo
	 */
	public void setCustInfo(HashMap<String, Object> custInfo) {
		this.custInfo = custInfo;
	}

	/**
	 * listPlanInfo を取得する
	 * 
	 * @return listPlanInfo
	 */
	public List<HashMap<String, Object>> getListPlanInfo() {
		return listPlanInfo;
	}

	/**
	 * listPlanInfo を設定する
	 * 
	 * @param listPlanInfo
	 */
	public void setListPlanInfo(List<HashMap<String, Object>> listPlanInfo) {
		this.listPlanInfo = listPlanInfo;
	}

	/**
	 * addressInfo を取得する
	 * 
	 * @return addressInfo
	 */
	public HashMap<String, Object> getAddressInfo() {
		return addressInfo;
	}

	/**
	 * addressInfo を設定する
	 * 
	 * @param addressInfo
	 */
	public void setAddressInfo(HashMap<String, Object> addressInfo) {
		this.addressInfo = addressInfo;
	}

	/**
	 * headerInfo を取得する
	 * 
	 * @return headerInfo
	 */
	public HashMap<String, Object> getHeaderInfo() {
		return headerInfo;
	}

	/**
	 * headerInfo を設定する
	 * 
	 * @param headerInfo
	 */
	public void setHeaderInfo(HashMap<String, Object> headerInfo) {
		this.headerInfo = headerInfo;
	}

	/**
	 * contactInfo を取得する
	 * 
	 * @return contactInfo
	 */
	public HashMap<String, Object> getContactInfo() {
		return contactInfo;
	}

	/**
	 * contactInfo を設定する
	 * 
	 * @param contactInfo
	 */
	public void setContactInfo(HashMap<String, Object> contactInfo) {
		this.contactInfo = contactInfo;
	}

	/**
	 * listContact を取得する
	 * 
	 * @return listContact
	 */
	public List<LabelValueBean> getListContact() {
		return listContact;
	}

	/**
	 * listContact を設定する
	 * 
	 * @param listContact
	 */
	public void setListContact(List<LabelValueBean> listContact) {
		this.listContact = listContact;
	}

	/**
	 * billRefInfoSess を取得する
	 * 
	 * @return billRefInfoSess
	 */
	public List<HashMap<String, Object>> getBillRefInfoSess() {
		return billRefInfoSess;
	}

	/**
	 * billRefInfoSess を設定する
	 * 
	 * @param billRefInfoSess
	 */
	public void setBillRefInfoSess(List<HashMap<String, Object>> billRefInfoSess) {
		this.billRefInfoSess = billRefInfoSess;
	}

	/**
	 * listPlanInfoSess を取得する
	 * 
	 * @return listPlanInfoSess
	 */
	public List<HashMap<String, Object>> getListPlanInfoSess() {
		return listPlanInfoSess;
	}

	/**
	 * listPlanInfoSess を設定する
	 * 
	 * @param listPlanInfoSess
	 */
	public void setListPlanInfoSess(
			List<HashMap<String, Object>> listPlanInfoSess) {
		this.listPlanInfoSess = listPlanInfoSess;
	}

	/**
	 * billRefDispCond を取得する
	 * 
	 * @return billRefDispCond
	 */
	public String getBillRefDispCond() {
		return billRefDispCond;
	}

	/**
	 * billRefDispCond を設定する
	 * 
	 * @param billRefDispCond
	 */
	public void setBillRefDispCond(String billRefDispCond) {
		this.billRefDispCond = billRefDispCond;
	}

	/**
	 * billRefDispCondSess を取得する
	 * 
	 * @return billRefDispCondSess
	 */
	public String getBillRefDispCondSess() {
		return billRefDispCondSess;
	}

	/**
	 * billRefDispCondSess を設定する
	 * 
	 * @param billRefDispCondSess
	 */
	public void setBillRefDispCondSess(String billRefDispCondSess) {
		this.billRefDispCondSess = billRefDispCondSess;
	}

	/**
	 * accessType を取得する
	 * 
	 * @return accessType
	 */
	public String getAccessType() {
		return accessType;
	}

	/**
	 * accessType を設定する
	 * 
	 * @param accessType
	 */
	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}

	/**
	 * isDisplayTerCnBut を取得する
	 * 
	 * @return isDisplayTerCnBut
	 */
	public String getIsDisplayTerCnBut() {
		return isDisplayTerCnBut;
	}

	/**
	 * isDisplayTerCnBut を設定する
	 * 
	 * @param isDisplayTerCnBut
	 */
	public void setIsDisplayTerCnBut(String isDisplayTerCnBut) {
		this.isDisplayTerCnBut = isDisplayTerCnBut;
	}
	
    /**
     * bss を取得する
     * 
     * @return bss
     */
    public BillingSystemSettings getBss() {
        return bss;
    }

    /**
     * bss を設定する
     * 
     * @param bss
     */
    public void setBss(BillingSystemSettings bss) {
        this.bss = bss;
    }

    /**
     * @return the editFlg
     */
    public String getEditFlg() {
        return editFlg;
    }

    /**
     * @param editFlg the editFlg to set
     */
    public void setEditFlg(String editFlg) {
        this.editFlg = editFlg;
    }

    /**
     * @return the bacType
     */
    public String getBacType() {
        return bacType;
    }

    /**
     * @param bacType the bacType to set
     */
    public void setBacType(String bacType) {
        this.bacType = bacType;
    }

    /**
     * @return the fromPage
     */
    public String getFromPage() {
        return fromPage;
    }

    /**
     * @param fromPage the fromPage to set
     */
    public void setFromPage(String fromPage) {
        this.fromPage = fromPage;
    }

    public String getFixedForex() {
        return fixedForex;
    }

    public void setFixedForex(String fixedForex) {
        this.fixedForex = fixedForex;
    }

	public String getIdRef() {
		return idRef;
	}

	public void setIdRef(String idRef) {
		this.idRef = idRef;
	}

	/**
	 * @return the checkBoxDisPlayFlg
	 */
	public String getCheckBoxDisPlayFlg() {
		return checkBoxDisPlayFlg;
	}

	/**
	 * @param checkBoxDisPlayFlg the checkBoxDisPlayFlg to set
	 */
	public void setCheckBoxDisPlayFlg(String checkBoxDisPlayFlg) {
		this.checkBoxDisPlayFlg = checkBoxDisPlayFlg;
	}

	/**
	 * @return the taxTypeDisplay
	 */
	public String getTaxTypeDisplay() {
		return taxTypeDisplay;
	}

	/**
	 * @param taxTypeDisplay the taxTypeDisplay to set
	 */
	public void setTaxTypeDisplay(String taxTypeDisplay) {
		this.taxTypeDisplay = taxTypeDisplay;
	}

}