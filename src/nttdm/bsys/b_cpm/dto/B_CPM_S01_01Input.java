/*
 * @(#)B_CPM_S01_01Input.java
 *
 *
 */
package nttdm.bsys.b_cpm.dto;

import java.io.Serializable;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;

/**
 * InputDTO class.
 * 
 * @author ss054
 */
public class B_CPM_S01_01Input implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7678616146657568670L;
	private String activeTab;
	private BillingSystemUserValueObject uvo;
	public BillingSystemUserValueObject getUvo() {
		return uvo;
	}

	public void setUvo(BillingSystemUserValueObject uvo) {
		this.uvo = uvo;
	}

	public String getActiveTab() {
		return activeTab;
	}

	public void setActiveTab(String activeTab) {
		this.activeTab = activeTab;
	}
	
}