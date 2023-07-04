/**
 * Billing System
 * 
 * SUBSYSTEM NAME : Standard Price and Plan Maintenance
 * SERVICE NAME : M_PPM_S03
 * FUNCTION : Form Bean
 * FILE NAME : M_PPM_S03_01Input.java
 * 
 * Copyright (C) 2011 NTTDATA Corporation
 * History
 */

package nttdm.bsys.m_ppm.dto;

/**
 * M_PPM_S03_01Input<br/>
 * Input information<br/>
 * @author  NTTData Vietnam
 * @version 1.1
 */
public class M_PPM_S03_01Input {
    
    private String customerType;

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
    
}