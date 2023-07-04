/**
 * Billing System
 * 
 * SUBSYSTEM NAME : Standard Price and Plan Maintenance
 * SERVICE NAME : M_PPM_S03
 * FUNCTION : Form Bean
 * FILE NAME : M_PPM_S03_02Input.java
 * 
 * Copyright (C) 2011 NTTDATA Corporation
 * History
 */

package nttdm.bsys.m_ppm.dto;

/**
 * M_PPM_S03_02Input<br/>
 * Input information<br/>
 * @author  NTTData Vietnam
 * @version 1.1
 */
public class M_PPM_S03_02Input {

	private String startIndex;

	private String row;

	private String tbxPlanName;

	private String tbxPlanDescript;

	private String tbxServiceName;

	private String cboType;

	private String cboRateMode;

	private String cboCategory;
	
	private String[] idPlanGrp;
	
	private String customerType;
	
	private String doSearch;
	
	private String cboService;
	
	public String getDoSearch() {
		return doSearch;
	}

	public void setDoSearch(String doSearch) {
		this.doSearch = doSearch;
	}

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

	public String getTbxPlanName() {
		return tbxPlanName;
	}

	public void setTbxPlanName(String tbxPlanName) {
		this.tbxPlanName = tbxPlanName;
	}

	public String getTbxPlanDescript() {
		return tbxPlanDescript;
	}

	public void setTbxPlanDescript(String tbxPlanDescript) {
		this.tbxPlanDescript = tbxPlanDescript;
	}

	public String getTbxServiceName() {
		return tbxServiceName;
	}

	public void setTbxServiceName(String tbxServiceName) {
		this.tbxServiceName = tbxServiceName;
	}

	public String getCboType() {
		return cboType;
	}

	public void setCboType(String cboType) {
		this.cboType = cboType;
	}

	public String getCboRateMode() {
		return cboRateMode;
	}

	public void setCboRateMode(String cboRateMode) {
		this.cboRateMode = cboRateMode;
	}

	public String getCboCategory() {
		return cboCategory;
	}

	public void setCboCategory(String cboCategory) {
		this.cboCategory = cboCategory;
	}

	public String[] getIdPlanGrp() {
		return idPlanGrp;
	}

	public void setIdPlanGrp(String[] idPlanGrp) {
		this.idPlanGrp = idPlanGrp;
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
     * @return the cboService
     */
    public String getCboService() {
        return cboService;
    }

    /**
     * @param cboService the cboService to set
     */
    public void setCboService(String cboService) {
        this.cboService = cboService;
    }
}