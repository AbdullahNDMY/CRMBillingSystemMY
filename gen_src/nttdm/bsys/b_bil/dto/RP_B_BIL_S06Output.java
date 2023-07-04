/*
 * @(#)RP_B_BIL_S05Output.java
 *
 *
 */
package nttdm.bsys.b_bil.dto;

import java.io.Serializable;

import nttdm.bsys.b_bil.bean.B_BIL_S06Bean;

/**
 * OutputDTO class.
 * 
 * @author gplai
 */
public class RP_B_BIL_S06Output implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -2588946444821017372L;

    private B_BIL_S06Bean bilS06Bean;
    
	private String gstCheck;

    /**
     * @return the bilS06Bean
     */
    public B_BIL_S06Bean getBilS06Bean() {
        return bilS06Bean;
    }

    /**
     * @param bilS06Bean the bilS06Bean to set
     */
    public void setBilS06Bean(B_BIL_S06Bean bilS06Bean) {
        this.bilS06Bean = bilS06Bean;
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
