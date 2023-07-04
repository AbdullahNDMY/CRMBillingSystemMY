/**
 * Billing System
 * 
 * SUBSYSTEM NAME : Standard Price and Plan Maintenance
 * SERVICE NAME : M_PPM_S03
 * FUNCTION : Form Bean
 * FILE NAME : M_PPM_S03_01Output.java
 * 
 * Copyright (C) 2011 NTTDATA Corporation
 * History
 */

package nttdm.bsys.m_ppm.dto;

import java.util.List;
import java.util.Map;

import nttdm.bsys.m_ppm.bean.Service;

/**
 * M_PPM_S03_01Output<br/>
 * Output information<br/>
 * @author  NTTData Vietnam
 * @version 1.1
 */
public class M_PPM_S03_01Output {
	
	private List<Service> cboCategoryList;
	
	private List<Map<String, Object>> cboServiceList;
	
	private String customerType;
	
	public List<Service> getCboCategoryList() {
		return cboCategoryList;
	}

	public void setCboCategoryList(List<Service> cboCategoryList) {
		this.cboCategoryList = cboCategoryList;
	}
	
	/**
     * @return the customerType
     */
    public String getCustomerType() {
        return customerType;
    }

    /**
     * @param customerType the customerType to set
     */
    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    /**
     * @return the cboServiceList
     */
    public List<Map<String, Object>> getCboServiceList() {
        return cboServiceList;
    }

    /**
     * @param cboServiceList the cboServiceList to set
     */
    public void setCboServiceList(List<Map<String, Object>> cboServiceList) {
        this.cboServiceList = cboServiceList;
    }
}