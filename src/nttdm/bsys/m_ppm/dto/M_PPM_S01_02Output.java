/**
 * Billing System
 * 
 * SUBSYSTEM NAME : Standard Price and Plan Maintenance
 * SERVICE NAME : M_PPM_S01
 * FUNCTION : Form Bean
 * FILE NAME : M_PPM_S01_02Output.java
 * 
 * Copyright (C) 2011 NTTDATA Corporation
 * History
 */

package nttdm.bsys.m_ppm.dto;

import java.util.List;
import java.util.Map;

import nttdm.bsys.m_ppm.bean.ServiceGroup;
import nttdm.bsys.m_ppm.bean.StandardPlan;

/**
 * M_PPM_S01_02Output<br/>
 * Output information<br/>
 * @author  NTTData Vietnam
 * @version 1.1
 */
public class M_PPM_S01_02Output {
	private String row;

	private String startIndex;

	private String totalCount;
	
	private List<ServiceGroup> cboCategoryList;
	
	private List<StandardPlan> searchResult;

    private List<Map<String, Object>> cboServiceList;
    private List<Map<String, Object>> cboPlanList;
    private List<Map<String, Object>> cboPlanDetailList;

	public List<Map<String, Object>> getCboServiceList() {
        return cboServiceList;
    }

    public void setCboServiceList(List<Map<String, Object>> cboServiceList) {
        this.cboServiceList = cboServiceList;
    }

    public List<Map<String, Object>> getCboPlanList() {
        return cboPlanList;
    }

    public void setCboPlanList(List<Map<String, Object>> cboPlanList) {
        this.cboPlanList = cboPlanList;
    }

    public List<Map<String, Object>> getCboPlanDetailList() {
        return cboPlanDetailList;
    }

    public void setCboPlanDetailList(List<Map<String, Object>> cboPlanDetailList) {
        this.cboPlanDetailList = cboPlanDetailList;
    }

    public String getRow() {
		return row;
	}

	public void setRow(String row) {
		this.row = row;
	}

	public String getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(String startIndex) {
		this.startIndex = startIndex;
	}

	public String getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}

	public List<ServiceGroup> getCboCategoryList() {
		return cboCategoryList;
	}

	public void setCboCategoryList(List<ServiceGroup> cboCategoryList) {
		this.cboCategoryList = cboCategoryList;
	}

	public List<StandardPlan> getSearchResult() {
		return searchResult;
	}

	public void setSearchResult(List<StandardPlan> searchResult) {
		this.searchResult = searchResult;
	}
}
