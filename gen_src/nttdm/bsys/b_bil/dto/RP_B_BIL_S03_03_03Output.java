/*
 * @(#)RP_B_BIL_S03_03_03Output.java
 *
 *
 */
package nttdm.bsys.b_bil.dto;

import java.io.Serializable;

import nttdm.bsys.b_bil.bean.T_BIL_HeaderInfo;

/**
 * OutputDTO class.
 * 
 * @author dunglq
 */
public class RP_B_BIL_S03_03_03Output implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 428256953509105009L;

	private T_BIL_HeaderInfo bilHeaderInfo;
	
	private String gstCheck;

    /**
     * @return the bilHeaderInfo
     */
    public T_BIL_HeaderInfo getBilHeaderInfo() {
        return bilHeaderInfo;
    }

    /**
     * @param bilHeaderInfo the bilHeaderInfo to set
     */
    public void setBilHeaderInfo(T_BIL_HeaderInfo bilHeaderInfo) {
        this.bilHeaderInfo = bilHeaderInfo;
    }

	/**
	 * @return the gstCheck
	 */
	public String getGstCheck() {
		return gstCheck;
	}

	/**
	 * @param gstCheck the gstCheck to set
	 */
	public void setGstCheck(String gstCheck) {
		this.gstCheck = gstCheck;
	}
}