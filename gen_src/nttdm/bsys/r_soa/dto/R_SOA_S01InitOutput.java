/**
 * @(#)R_SOA_S01InitOutput.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2012/07/02
 * 
 * Copyright (c) 2012 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.r_soa.dto;

import java.io.Serializable;

/**
 * @author gplai
 *
 */
public class R_SOA_S01InitOutput implements Serializable {

    private static final long serialVersionUID = -2968499897580623080L;
    
    private String accessType;
    
    private String cboPrintStatement;
    
    private String chkStatementTotal;
    
    private String sysdateYYYY;
    
    private String sysdateMM;

    private String[] cboPaymentCurrencyArray;
   
    /**
	 * @return the cboPaymentCurrencyArray
	 */
	public String[] getCboPaymentCurrencyArray() {
		return cboPaymentCurrencyArray;
	}

	/**
	 * @param cboPaymentCurrencyArray the cboPaymentCurrencyArray to set
	 */
	public void setCboPaymentCurrencyArray(String[] cboPaymentCurrencyArray) {
		this.cboPaymentCurrencyArray = cboPaymentCurrencyArray;
	}

	/**
     * @return the accessType
     */
    public String getAccessType() {
        return accessType;
    }

    /**
     * @param accessType the accessType to set
     */
    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }

    /**
     * @return the cboPrintStatement
     */
    public String getCboPrintStatement() {
        return cboPrintStatement;
    }

    /**
     * @param cboPrintStatement the cboPrintStatement to set
     */
    public void setCboPrintStatement(String cboPrintStatement) {
        this.cboPrintStatement = cboPrintStatement;
    }

    /**
     * @return the chkStatementTotal
     */
    public String getChkStatementTotal() {
        return chkStatementTotal;
    }

    /**
     * @param chkStatementTotal the chkStatementTotal to set
     */
    public void setChkStatementTotal(String chkStatementTotal) {
        this.chkStatementTotal = chkStatementTotal;
    }

    /**
     * @return the sysdateYYYY
     */
    public String getSysdateYYYY() {
        return sysdateYYYY;
    }

    /**
     * @param sysdateYYYY the sysdateYYYY to set
     */
    public void setSysdateYYYY(String sysdateYYYY) {
        this.sysdateYYYY = sysdateYYYY;
    }

    /**
     * @return the sysdateMM
     */
    public String getSysdateMM() {
        return sysdateMM;
    }

    /**
     * @param sysdateMM the sysdateMM to set
     */
    public void setSysdateMM(String sysdateMM) {
        this.sysdateMM = sysdateMM;
    }
}
