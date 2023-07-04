/*
 * @(#)RP_B_BIL_S03_03_03Input.java
 *
 *
 */
package nttdm.bsys.b_bil.dto;

import java.io.Serializable;

import nttdm.bsys.b_bil.bean.T_BIL_HeaderInfo;

/**
 * InputDTO class.
 * 
 * @author dunglq
 */
public class RP_B_BIL_S03_03_03Input implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 3077078552278524161L;

	private T_BIL_HeaderInfo bilHeaderInfo;

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

}