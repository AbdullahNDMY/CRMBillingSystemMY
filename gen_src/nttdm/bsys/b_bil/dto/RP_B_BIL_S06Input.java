/**
 * @(#)RP_B_BIL_S05Input.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2012/05/25
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.b_bil.dto;

import java.io.Serializable;

import nttdm.bsys.b_bil.bean.B_BIL_S06Bean;

/**
 * InputDTO class.
 * 
 * @author gplai
 * 
 */
public class RP_B_BIL_S06Input implements Serializable {

    private static final long serialVersionUID = -9188067125172794770L;
    
    private B_BIL_S06Bean bilS06Bean;

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
}
