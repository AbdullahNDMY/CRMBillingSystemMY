/*
 * @(#)RP_B_BIL_S03_03_01Input.java
 *
 *
 */
package nttdm.bsys.b_bil.dto;

import java.io.Serializable;

import nttdm.bsys.b_bil.bean.T_BIL_HeaderInfo;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;

/**
 * InputDTO class.
 * 
 * @author dunglq
 */
public class RP_B_BIL_S03_03_01Input implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1712547404887636971L;
	
	private BillingSystemUserValueObject uvo;

    private String forward_iv;
    private String forward_dn;
    private String forward_cn;
    
    private T_BIL_HeaderInfo bilHeaderInfo;
	
    /**
     * @return the uvo
     */
    public BillingSystemUserValueObject getUvo() {
        return uvo;
    }

    /**
     * @param uvo the uvo to set
     */
    public void setUvo(BillingSystemUserValueObject uvo) {
        this.uvo = uvo;
    }

    /**
     * @return the forward_iv
     */
    public String getForward_iv() {
        return forward_iv;
    }

    /**
     * @param forward_iv the forward_iv to set
     */
    public void setForward_iv(String forward_iv) {
        this.forward_iv = forward_iv;
    }

    /**
     * @return the forward_dn
     */
    public String getForward_dn() {
        return forward_dn;
    }

    /**
     * @param forward_dn the forward_dn to set
     */
    public void setForward_dn(String forward_dn) {
        this.forward_dn = forward_dn;
    }

    /**
     * @return the forward_cn
     */
    public String getForward_cn() {
        return forward_cn;
    }

    /**
     * @param forward_cn the forward_cn to set
     */
    public void setForward_cn(String forward_cn) {
        this.forward_cn = forward_cn;
    }

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