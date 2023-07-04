/*
 * @(#)RP_B_BIL_S03_02_03Input.java
 *
 *
 */
package nttdm.bsys.b_bil.dto;

import java.io.Serializable;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.bean.T_BIL_H;

/**
 * InputDTO class.
 * 
 * @author dunglq
 */
public class RP_B_BIL_S03_02_03Input implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -8977888306060045L;

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
     * 
     */
    private T_BIL_H inputHeaderInfo;

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
    private String grandTotalWording;

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
     * @param idRef String
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
     * @param mode String
     */
    public void setMode(String mode) {
        this.mode = mode;
    }

    /**
     * uvo を取得する
     * 
     * @return uvo
     */
    public BillingSystemUserValueObject getUvo() {
        return uvo;
    }

    /**
     * uvo を設定する
     * 
     * @param uvo BillingSystemUserValueObject
     */
    public void setUvo(BillingSystemUserValueObject uvo) {
        this.uvo = uvo;
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
     * @param inputHeaderInfo T_BIL_H
     */
    public void setInputHeaderInfo(T_BIL_H inputHeaderInfo) {
        this.inputHeaderInfo = inputHeaderInfo;
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
     * @param itemDesc String[]
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
     * @param itemQty String[]
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
     * @param itemUprice String[]
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
     * @param itemAtm String[]
     */
    public void setItemAtm(String[] itemAtm) {
        this.itemAtm = itemAtm;
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
     * @param grandTotalWording String
     */
    public void setGrandTotalWording(String grandTotalWording) {
        this.grandTotalWording = grandTotalWording;
    }

}