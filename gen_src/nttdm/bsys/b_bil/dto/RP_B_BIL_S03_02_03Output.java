/*
 * @(#)RP_B_BIL_S03_02_03Output.java
 *
 *
 */
package nttdm.bsys.b_bil.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nttdm.bsys.common.bean.T_BIL_H;

import org.apache.struts.util.LabelValueBean;

/**
 * OutputDTO class.
 * 
 * @author dunglq
 */
public class RP_B_BIL_S03_02_03Output implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4902658475887395474L;

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
	private T_BIL_H inputHeaderInfo;

	/**
     * 
     */
	private List<LabelValueBean> listCust;

	/**
     * 
     */
	private List<LabelValueBean> listContact;

	/**
     * 
     */
	private List<LabelValueBean> listQcsRef;

	/**
     * 
     */
	private List<LabelValueBean> listQuoRef;

	/**
     * 
     */
	private List<LabelValueBean> listAcMan;

	/**
     * 
     */
	private List<LabelValueBean> listJobNo;
    
    /**
     * listBillAccountNo
     */
    private List<LabelValueBean> listBillingAccountNo;

	/**
     * 
     */
	private String[] itemNo;
	
	/**
     * 
     */
	private String[] itemDesc;

	/**
     * 
     */
	private String[] itemQty;

	/**
     * 
     */
	private String[] itemUprice;

	/**
     * 
     */
	private String[] itemAtm;

	/**
     * 
     */
	private HashMap<String, Object> headerInfo;

	/**
     * 
     */
	private List<HashMap<String, Object>> detailInfo;

	/**
     * 
     */
	private String grandTotalWording;

	/**
     * 
     */
	private String gstValue;
	
	/**
     * footerInfo
     */
    private List<Map<String,Object>> footerInfo ;
    
    private List<Map<String, Object>> bankFooterInfo;

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

	/**
	 * idRef を取得する
	 * 
	 * @return idRef
	 */
	public String getIdRef() {
		return idRef;
	}

	/**
	 * idRef を設定する
	 * 
	 * @param idRef
	 */
	public void setIdRef(String idRef) {
		this.idRef = idRef;
	}

	/**
	 * mode を取得する
	 * 
	 * @return mode
	 */
	public String getMode() {
		return mode;
	}

	/**
	 * mode を設定する
	 * 
	 * @param mode
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}

	/**
	 * inputHeaderInfo を取得する
	 * 
	 * @return inputHeaderInfo
	 */
	public T_BIL_H getInputHeaderInfo() {
		return inputHeaderInfo;
	}

	/**
	 * inputHeaderInfo を設定する
	 * 
	 * @param inputHeaderInfo
	 */
	public void setInputHeaderInfo(T_BIL_H inputHeaderInfo) {
		this.inputHeaderInfo = inputHeaderInfo;
	}

	/**
	 * listCust を取得する
	 * 
	 * @return listCust
	 */
	public List<LabelValueBean> getListCust() {
		return listCust;
	}

	/**
	 * listCust を設定する
	 * 
	 * @param listCust
	 */
	public void setListCust(List<LabelValueBean> listCust) {
		this.listCust = listCust;
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
	 * listQcsRef を取得する
	 * 
	 * @return listQcsRef
	 */
	public List<LabelValueBean> getListQcsRef() {
		return listQcsRef;
	}

	/**
	 * listQcsRef を設定する
	 * 
	 * @param listQcsRef
	 */
	public void setListQcsRef(List<LabelValueBean> listQcsRef) {
		this.listQcsRef = listQcsRef;
	}

	/**
	 * listQuoRef を取得する
	 * 
	 * @return listQuoRef
	 */
	public List<LabelValueBean> getListQuoRef() {
		return listQuoRef;
	}

	/**
	 * listQuoRef を設定する
	 * 
	 * @param listQuoRef
	 */
	public void setListQuoRef(List<LabelValueBean> listQuoRef) {
		this.listQuoRef = listQuoRef;
	}

	/**
	 * listAcMan を取得する
	 * 
	 * @return listAcMan
	 */
	public List<LabelValueBean> getListAcMan() {
		return listAcMan;
	}

	/**
	 * listAcMan を設定する
	 * 
	 * @param listAcMan
	 */
	public void setListAcMan(List<LabelValueBean> listAcMan) {
		this.listAcMan = listAcMan;
	}

	/**
	 * listJobNo を取得する
	 * 
	 * @return listJobNo
	 */
	public List<LabelValueBean> getListJobNo() {
		return listJobNo;
	}

	/**
	 * listJobNo を設定する
	 * 
	 * @param listJobNo
	 */
	public void setListJobNo(List<LabelValueBean> listJobNo) {
		this.listJobNo = listJobNo;
	}

    /**
     * @return the listBillingAccountNo
     */
    public List<LabelValueBean> getListBillingAccountNo() {
        return listBillingAccountNo;
    }

    /**
     * @param listBillingAccountNo the listBillingAccountNo to set
     */
    public void setListBillingAccountNo(
            List<LabelValueBean> listBillingAccountNo) {
        this.listBillingAccountNo = listBillingAccountNo;
    }

	/**
	 * @return the itemNo
	 */
	public String[] getItemNo() {
		return itemNo;
	}

	/**
	 * @param itemNo the itemNo to set
	 */
	public void setItemNo(String[] itemNo) {
		this.itemNo = itemNo;
	}

	/**
	 * itemDesc を取得する
	 * 
	 * @return itemDesc
	 */
	public String[] getItemDesc() {
		return itemDesc;
	}

	/**
	 * itemDesc を設定する
	 * 
	 * @param itemDesc
	 */
	public void setItemDesc(String[] itemDesc) {
		this.itemDesc = itemDesc;
	}

	/**
	 * itemQty を取得する
	 * 
	 * @return itemQty
	 */
	public String[] getItemQty() {
		return itemQty;
	}

	/**
	 * itemQty を設定する
	 * 
	 * @param itemQty
	 */
	public void setItemQty(String[] itemQty) {
		this.itemQty = itemQty;
	}

	/**
	 * itemUprice を取得する
	 * 
	 * @return itemUprice
	 */
	public String[] getItemUprice() {
		return itemUprice;
	}

	/**
	 * itemUprice を設定する
	 * 
	 * @param itemUprice
	 */
	public void setItemUprice(String[] itemUprice) {
		this.itemUprice = itemUprice;
	}

	/**
	 * itemAtm を取得する
	 * 
	 * @return itemAtm
	 */
	public String[] getItemAtm() {
		return itemAtm;
	}

	/**
	 * itemAtm を設定する
	 * 
	 * @param itemAtm
	 */
	public void setItemAtm(String[] itemAtm) {
		this.itemAtm = itemAtm;
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
	 * detailInfo を取得する
	 * 
	 * @return detailInfo
	 */
	public List<HashMap<String, Object>> getDetailInfo() {
		return detailInfo;
	}

	/**
	 * detailInfo を設定する
	 * 
	 * @param detailInfo
	 */
	public void setDetailInfo(List<HashMap<String, Object>> detailInfo) {
		this.detailInfo = detailInfo;
	}

	/**
	 * grandTotalWording を取得する
	 * 
	 * @return grandTotalWording
	 */
	public String getGrandTotalWording() {
		return grandTotalWording;
	}

	/**
	 * grandTotalWording を設定する
	 * 
	 * @param grandTotalWording
	 */
	public void setGrandTotalWording(String grandTotalWording) {
		this.grandTotalWording = grandTotalWording;
	}

	/**
	 * gstValue を取得する
	 * 
	 * @return gstValue
	 */
	public String getGstValue() {
		return gstValue;
	}

	/**
	 * gstValue を設定する
	 * 
	 * @param gstValue
	 */
	public void setGstValue(String gstValue) {
		this.gstValue = gstValue;
	}
	
	/**
     * @return the footerInfo
     */
    public List<Map<String, Object>> getFooterInfo() {
        return footerInfo;
    }

    /**
     * @param footerInfo the footerInfo to set
     */
    public void setFooterInfo(List<Map<String, Object>> footerInfo) {
        this.footerInfo = footerInfo;
    }
}