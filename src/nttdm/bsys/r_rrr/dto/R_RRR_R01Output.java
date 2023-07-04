/**
 * @(#)R_RRR_R01Output.java
 * Billing System
 * Version 1.00
 * Created 2011-8-29
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.r_rrr.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.struts.util.LabelValueBean;

/**
 * R_RRR_R01Output class.
 * 
 * @author xycao
 */
public class R_RRR_R01Output implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 7112562778671150991L;

    /**
     * 
     */
    private String accessType;
    
    /**
     * 
     */
    private List<Map<String, Object>> paymentInfo;
    
    private List<LabelValueBean> listBackInfo;
    
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
     * @return the paymentInfo
     */
	public List<Map<String, Object>> getPaymentInfo() {
		return paymentInfo;
	}

	/**
     * @param paymentInfo the paymentInfo to set
     */
	public void setPaymentInfo(List<Map<String, Object>> paymentInfo) {
		this.paymentInfo = paymentInfo;
	}

    /**
     * @return the listBackInfo
     */
    public List<LabelValueBean> getListBackInfo() {
        return listBackInfo;
    }

    /**
     * @param listBackInfo the listBackInfo to set
     */
    public void setListBackInfo(List<LabelValueBean> listBackInfo) {
        this.listBackInfo = listBackInfo;
    }
}