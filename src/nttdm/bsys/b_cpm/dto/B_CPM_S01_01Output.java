/*
 * @(#)B_CPM_S01_01Output.java
 *
 *
 */
package nttdm.bsys.b_cpm.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * OutputDTO class.
 * 
 * @author ss054
 */
public class B_CPM_S01_01Output implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2995004396004047588L;
	private List<ServiceGroup> billSrvGrpList = new ArrayList<ServiceGroup>();
	private List<Supplier> carrierList = new ArrayList<Supplier>();
	private String accessType;
	private String activeTab;
	
	public List<Supplier> getCarrierList() {
		return carrierList;
	}

	public void setCarrierList(List<Supplier> carrierList) {
		this.carrierList = carrierList;
	}

	public List<ServiceGroup> getBillSrvGrpList() {
		return billSrvGrpList;
	}

	public void setBillSrvGrpList(List<ServiceGroup> billSrvGrpList) {
		this.billSrvGrpList = billSrvGrpList;
	}


	public String getAccessType() {
		return accessType;
	}

	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}

	public String getActiveTab() {
		return activeTab;
	}

	public void setActiveTab(String activeTab) {
		this.activeTab = activeTab;
	}


	
}