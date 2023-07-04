/**
 * @(#)R_REV_R03BLogic.java
 * Billing System
 * Version 3.03
 * Created 2016/09/30
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.r_rev.dto;

import java.io.Serializable;
import java.util.List;

/**
 * R_ACR_R01Output class.
 * 
 * @author binz
 */
public class R_REV_R03Output implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 7112562778671150991L;

    private List<EXCEL_LIST> resultList;
    private List<EXCEL_LIST> resultList2;
    private String reGenerateFlag;

	public List<EXCEL_LIST> getResultList2() {
		return resultList2;
	}

	public void setResultList2(List<EXCEL_LIST> resultList2) {
		this.resultList2 = resultList2;
	}

	public String getReGenerateFlag() {
		return reGenerateFlag;
	}

	public void setReGenerateFlag(String reGenerateFlag) {
		this.reGenerateFlag = reGenerateFlag;
	}

	public List<EXCEL_LIST> getResultList() {
		return resultList;
	}

	public void setResultList(List<EXCEL_LIST> resultList) {
		this.resultList = resultList;
	}

}