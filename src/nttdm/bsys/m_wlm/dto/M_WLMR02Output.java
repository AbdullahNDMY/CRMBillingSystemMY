/*
 * @(#)M_WLMR02BLogicInput.java
 *
 *
 */
package nttdm.bsys.m_wlm.dto;

import java.io.Serializable;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;

/**
 * OutputDTO class.
 * 
 * @author ss052
 */
public class M_WLMR02Output implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -457544517999413588L;

	private String id_screen = null;
	
	private String id_section_group = null;
	
	private BillingSystemUserValueObject uvo;
	
	public String getId_screen() {
		return id_screen;
	}

	public void setId_screen(String id_screen) {
		this.id_screen = id_screen;
	}

	public String getId_section_group() {
		return id_section_group;
	}

	public void setId_section_group(String id_section_group) {
		this.id_section_group = id_section_group;
	}

	public BillingSystemUserValueObject getUvo() {
		return uvo;
	}

	public void setUvo(BillingSystemUserValueObject uvo) {
		this.uvo = uvo;
	}
	
}