/*
 * @(#)RP_B_BIL_S03_02_02Input.java
 *
 *
 */
package nttdm.bsys.b_bil.dto;

import java.io.Serializable;
import nttdm.bsys.common.bean.T_BIL_H;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;

/**
 * InputDTO class.
 * 
 * @author khungl0ng
 */
public class RP_B_BIL_S03_02_02Input implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6394704732771371497L;

	/**
     * 
     */
	private T_BIL_H inputHeaderInfo;
	
	/**
	 * 
	 */
	private Integer[] itemId;

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
	private String idRef;

	/**
     * 
     */
	private String mode;

	/**
     * 
     */
	private BillingSystemUserValueObject uvo;

	/**
	 * inputHeaderInfo を�?�得�?�る
	 * 
	 * @return inputHeaderInfo
	 */
	public T_BIL_H getInputHeaderInfo() {
		return inputHeaderInfo;
	}

	/**
	 * inputHeaderInfo を設定�?�る
	 * 
	 * @param inputHeaderInfo
	 */
	public void setInputHeaderInfo(T_BIL_H inputHeaderInfo) {
		this.inputHeaderInfo = inputHeaderInfo;
	}

	public Integer[] getItemId() {
		return itemId;
	}

	public void setItemId(Integer[] itemId) {
		this.itemId = itemId;
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
	 * itemDesc を�?�得�?�る
	 * 
	 * @return itemDesc
	 */
	public String[] getItemDesc() {
		return itemDesc;
	}

	/**
	 * itemDesc を設定�?�る
	 * 
	 * @param itemDesc
	 */
	public void setItemDesc(String[] itemDesc) {
		this.itemDesc = itemDesc;
	}

	/**
	 * itemQty を�?�得�?�る
	 * 
	 * @return itemQty
	 */
	public String[] getItemQty() {
		return itemQty;
	}

	/**
	 * itemQty を設定�?�る
	 * 
	 * @param itemQty
	 */
	public void setItemQty(String[] itemQty) {
		this.itemQty = itemQty;
	}

	/**
	 * itemUprice を�?�得�?�る
	 * 
	 * @return itemUprice
	 */
	public String[] getItemUprice() {
		return itemUprice;
	}

	/**
	 * itemUprice を設定�?�る
	 * 
	 * @param itemUprice
	 */
	public void setItemUprice(String[] itemUprice) {
		this.itemUprice = itemUprice;
	}

	/**
	 * itemAtm を�?�得�?�る
	 * 
	 * @return itemAtm
	 */
	public String[] getItemAtm() {
		return itemAtm;
	}

	/**
	 * itemAtm を設定�?�る
	 * 
	 * @param itemAtm
	 */
	public void setItemAtm(String[] itemAtm) {
		this.itemAtm = itemAtm;
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
	 * uvo を�?�得�?�る
	 * 
	 * @return uvo
	 */
	public BillingSystemUserValueObject getUvo() {
		return uvo;
	}

	/**
	 * uvo を設定�?�る
	 * 
	 * @param uvo
	 */
	public void setUvo(BillingSystemUserValueObject uvo) {
		this.uvo = uvo;
	}

}