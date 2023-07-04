/**
 * @(#)M_GBS_S02FormBean.java
 * 
 * HMSB Online Service Booking System
 * 
 * Version 1.00
 * 
 * Created Sep 4, 2014
 * 
 * Copyright (c) 2014 Honda Malaysia. All rights reserved.
 */
package nttdm.bsys.m_gbs.bean;

import java.util.List;

import jp.terasoluna.fw.web.struts.form.ValidatorActionFormEx;
import nttdm.bsys.common.util.dto.AutoScaleList;

/**
 * @author loamanma
 * 
 */
public class M_GBS_S02FormBean extends ValidatorActionFormEx {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    // combox value
    private String columnType;
    // previous select value
    private String selectType;

    //old select value
    private String preSelItem;
    
    private List<ResultItem> listResult = new AutoScaleList<ResultItem>(new ResultItem());

    private String localcCtegory;
    
    public String getSelectType() {
        return selectType;
    }

    public void setSelectType(String selectType) {
        this.selectType = selectType;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public List<ResultItem> getListResult() {
        return listResult;
    }

    public void setListResult(List<ResultItem> listResult) {
        this.listResult = listResult;
    }
    
    public String getPreSelItem() {
        return preSelItem;
    }

    public void setPreSelItem(String preSelItem) {
        this.preSelItem = preSelItem;
    }

	/**
	 * @return the localcCtegory
	 */
	public String getLocalcCtegory() {
		return localcCtegory;
	}

	/**
	 * @param localcCtegory the localcCtegory to set
	 */
	public void setLocalcCtegory(String localcCtegory) {
		this.localcCtegory = localcCtegory;
	}

}
