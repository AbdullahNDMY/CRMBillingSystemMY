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
	 * headerData を�?�得�?�る
	 * 
	 * @return headerData
	 */
	public T_BIL_H getHeaderData() {
		return headerData;
	}

	/**
	 * headerData を設定�?�る
	 * 
	 * @param headerData
	 */
	public void setHeaderData(T_BIL_H headerData) {
		this.headerData = headerData;
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