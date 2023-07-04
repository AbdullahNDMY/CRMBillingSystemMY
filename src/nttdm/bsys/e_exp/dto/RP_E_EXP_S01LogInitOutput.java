/**
 * @(#)RP_E_EXP_S01LogInitOutput.java
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

import java.util.List;
import java.util.Map;

/**
 * @author gplai
 *
 */
public class RP_E_EXP_S01LogInitOutput {

    private List<Map<String, Object>> logsList;

    /**
     * @return the logsList
     */
    public List<Map<String, Object>> getLogsList() {
        return logsList;
    }

    /**
     * @param logsList the logsList to set
     */
    public void setLogsList(List<Map<String, Object>> logsList) {
        this.logsList = logsList;
    }
}
