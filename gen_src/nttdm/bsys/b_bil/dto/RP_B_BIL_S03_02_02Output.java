/*
 * @(#)RP_B_BIL_S03_02_02Output.java
 *
 *
 */
package nttdm.bsys.b_bil.dto;

import java.io.Serializable;

/**
 * OutputDTO class.
 * 
 * @author gplai
 */
public class RP_B_BIL_S03_02_02Output implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6352213641388502111L;

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
    private String fromPageFlag;

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
	 * mode を�?�得�?�る
	 * 
	 * @return mode
	 */
	public String getMode() {
		return mode;
	}

	/**
	 * mode を設定�?�る
	 * 
	 * @param mode
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}
	
	/**
     * @return the fromPageFlag
     */
    public String getFromPageFlag() {
        return fromPageFlag;
    }

    /**
     * @param fromPageFlag the fromPageFlag to set
     */
    public void setFromPageFlag(String fromPageFlag) {
        this.fromPageFlag = fromPageFlag;
    }
}