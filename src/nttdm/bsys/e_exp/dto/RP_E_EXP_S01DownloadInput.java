/**
 * @(#)RP_E_EXP_S01DownloadInput.java
 * 
 * HMSB Online Service Booking System
 * 
 * Version 1.00
 * 
 * Created 2013/08/04
 * 
 * Copyright (c) 2013 Honda Malaysia. All rights reserved.
 */
package nttdm.bsys.e_exp.dto;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;

/**
 * @author gplai
 *
 */
public class RP_E_EXP_S01DownloadInput {

    private BillingSystemUserValueObject uvo;
    
    private String filename;
    
    private String idBatch;

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
     * @return the filename
     */
    public String getFilename() {
        return filename;
    }

    /**
     * @param filename the filename to set
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * @return the idBatch
     */
    public String getIdBatch() {
        return idBatch;
    }

    /**
     * @param idBatch the idBatch to set
     */
    public void setIdBatch(String idBatch) {
        this.idBatch = idBatch;
    }
}
