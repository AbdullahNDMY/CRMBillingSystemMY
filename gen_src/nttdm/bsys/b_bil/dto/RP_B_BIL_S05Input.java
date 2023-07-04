/**
 * @(#)RP_B_BIL_S05Input.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2012/05/25
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.b_bil.dto;

import java.io.Serializable;

/**
 * InputDTO class.
 * 
 * @author i-leonzh
 * 
 */
public class RP_B_BIL_S05Input implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 3192363793424366552L;
	
	private String idRef;
	
	private String itemId;
	
	private String itemDesc;

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

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

}
