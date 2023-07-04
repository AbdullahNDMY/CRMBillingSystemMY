/*
 * @(#)RP_B_BIL_S03_01Output.java
 *
 *
 */
package nttdm.bsys.b_bil.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * OutputDTO class.
 * 
 * @author khungl0ng
 */
public class RP_B_BIL_S03_01Output implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 9011195108266751573L;

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
	private String accessType;

	/**
     * 
     */
	private String idRef;
	
	private List<Map<String, Object>> footerInfo;
	
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

	public List<Map<String, Object>> getFooterInfo() {
		return footerInfo;
	}

	public void setFooterInfo(List<Map<String, Object>> footerInfo) {
		this.footerInfo = footerInfo;
	}
}