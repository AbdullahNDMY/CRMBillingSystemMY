/*
 * @(#)RP_B_BIL_S03_01Input.java
 *
 *
 */
package nttdm.bsys.b_bil.dto;

import java.io.Serializable;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;

/**
 * InputDTO class.
 * 
 * @author khungl0ng
 */
public class RP_B_BIL_S03_01Input implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6008614465008217641L;

	/**
     * 
     */
	private String idRef;

	/**
     * 
     */
	private BillingSystemUserValueObject uvo;
	
	/**
     * 
     */
    private String fromPageFlag;
    
    /**
     * 
     */
    private String mode;

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
}