/*
 * @(#)RP_B_BIL_S02_02_02Input.java
 *
 *
 */
package nttdm.bsys.b_bil.dto;

import java.io.Serializable;
import java.util.HashMap;
import nttdm.bsys.common.bean.T_BIL_H;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;

/**
 * InputDTO class.
 * 
 * @author khungl0ng
 */
public class RP_B_BIL_S02_02_02Input implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -674272346897185737L;

	/**
     * 
     */
	private String idRef;

	/**
     * 
     */
	private HashMap<String, Object> headerInfo;

	/**
     * 
     */
	private T_BIL_H headerData;

	/**
     * 
     */
	private BillingSystemUserValueObject uvo;

	/**
	 * idRef ã‚’å?–å¾—ã?™ã‚‹
	 * 
	 * @return idRef
	 */
	public String getIdRef() {
		return idRef;
	}

	/**
	 * idRef ã‚’è¨­å®šã?™ã‚‹
	 * 
	 * @param idRef
	 */
	public void setIdRef(String idRef) {
		this.idRef = idRef;
	}

	/**
	 * headerInfo ã‚’å?–å¾—ã?™ã‚‹
	 * 
	 * @return headerInfo
	 */
	public HashMap<String, Object> getHeaderInfo() {
		return headerInfo;
	}

	/**
	 * headerInfo ã‚’è¨­å®šã?™ã‚‹
	 * 
	 * @param headerInfo
	 */
	public void setHeaderInfo(HashMap<String, Object> headerInfo) {
		this.headerInfo = headerInfo;
	}

	/**
	 * headerData ã‚’å?–å¾—ã?™ã‚‹
	 * 
	 * @return headerData
	 */
	public T_BIL_H getHeaderData() {
		return headerData;
	}

	/**
	 * headerData ã‚’è¨­å®šã?™ã‚‹
	 * 
	 * @param headerData
	 */
	public void setHeaderData(T_BIL_H headerData) {
		this.headerData = headerData;
	}

	/**
	 * uvo ã‚’å?–å¾—ã?™ã‚‹
	 * 
	 * @return uvo
	 */
	public BillingSystemUserValueObject getUvo() {
		return uvo;
	}

	/**
	 * uvo ã‚’è¨­å®šã?™ã‚‹
	 * 
	 * @param uvo
	 */
	public void setUvo(BillingSystemUserValueObject uvo) {
		this.uvo = uvo;
	}

}