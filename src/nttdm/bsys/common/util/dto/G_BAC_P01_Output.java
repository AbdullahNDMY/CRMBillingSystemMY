/**
 * @(#)G_BAC_P01_Output.java
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */

package nttdm.bsys.common.util.dto;

/**
 * Output Object for Update Total Amount Due
 * 
 * @author bench
 */
public class G_BAC_P01_Output {
	
	private String MSG = "";
    private String status="";
    
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMSG() {
		return MSG;
	}

	public void setMSG(String mSG) {
		MSG = mSG;
	}
}
