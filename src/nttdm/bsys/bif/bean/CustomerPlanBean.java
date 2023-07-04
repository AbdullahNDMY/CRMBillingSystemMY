/**
 * @(#)CustomerPlanBean.java
 * 
 * HMSB Online Service Booking System
 * 
 * Version 1.00
 * 
 * Created 2013/03/20
 * 
 * Copyright (c) 2013 Honda Malaysia. All rights reserved.
 */
package nttdm.bsys.bif.bean;

import java.util.List;

import nttdm.bsys.common.util.dto.AutoScaleList;

/**
 * @author gplai
 *
 */
public class CustomerPlanBean {

    private List<CustomerPlanServiceBean> serviceList;

    public CustomerPlanBean() {
        this.serviceList = new AutoScaleList<CustomerPlanServiceBean>(new CustomerPlanServiceBean());
    }
    /**
     * @return the serviceList
     */
    public List<CustomerPlanServiceBean> getServiceList() {
        return serviceList;
    }

    /**
     * @param serviceList the serviceList to set
     */
    public void setServiceList(List<CustomerPlanServiceBean> serviceList) {
        this.serviceList = serviceList;
    }
}
