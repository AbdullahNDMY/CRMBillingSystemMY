/**
 * Billing System
 * 
 * SUBSYSTEM NAME : Standard Price and Plan Maintenance
 * SERVICE NAME : M_PPM_S03
 * FUNCTION : Form Bean
 * FILE NAME : M_PPM_S03_02Output.java
 * 
 * Copyright (C) 2011 NTTDATA Corporation
 * History
 */

package nttdm.bsys.m_ppm.dto;

import java.util.List;
import java.util.Map;

import nttdm.bsys.m_ppm.bean.Service;
import nttdm.bsys.m_ppm.bean.StandardPlan;

/**
 * M_PPM_S03_02Output<br/>
 * Output information<br/>
 * @author  NTTData Vietnam
 * @version 1.1
 */
public class M_PPM_S03_02Output {
	
	private String startIndex;
	
	private String row;
	
	private String totalCount;
	
	private List<StandardPlan> searchResult;
	
	private List<Service> cboCategoryList;
	
	private String[] idPlanGrp;
	
	private List<Map<String, Object>> cboServiceList;

	public String getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(String startIndex) {
		this.startIndex = startIndex;
	}

	public String getRow() {
		return row;
	}

	public void setRow(String row) {
		this.row = row;
	}

	public String getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}

	public List<StandardPlan> getSearchResult() {
		return searchResult;
	}

	public void setSearchResult(List<StandardPlan> searchResult) {
		this.searchResult = searchResult;
	}

	public List<Service> getCboCategoryList() {
		return cboCategoryList;
	}

	public void setCboCategoryList(List<Service> cboCategoryList) {
		this.cboCategoryList = cboCategoryList;
	}

	public String[] getIdPlanGrp() {
		return idPlanGrp;
	}

	public void setIdPlanGrp(String[] idPlanGrp) {
		this.idPlanGrp = idPlanGrp;
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