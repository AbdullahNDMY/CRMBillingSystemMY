/*
 * @(#)RP_B_BIL_S05Output.java
 *
 *
 */
package nttdm.bsys.b_bil.dto;

import java.io.Serializable;

/**
 * OutputDTO class.
 * 
 * @author i-leonzh
 */
public class RP_B_BIL_S05Output implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4232066480229323556L;
	
	private String idRef;
	
	private String itemId;
	
	private String itemDesc;

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public String getIdRef() {
		return idRef;
	}

	public void setIdRef(String idRef) {
		this.idRef = idRef;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

}
