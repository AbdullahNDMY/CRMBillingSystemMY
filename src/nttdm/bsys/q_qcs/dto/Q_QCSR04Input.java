/*
 * @(#)Q_QCSR04Input.java
 *
 *
 */
package nttdm.bsys.q_qcs.dto;

import java.io.Serializable;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;

/**
 * InputDTO class.
 * 
 * @author ss051
 */
public class Q_QCSR04Input implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8681107381279520747L;


	private String id_ref;
	private String clickEvent;
	private String showPopalt;
	private BillingSystemUserValueObject uvo;
	private String section_group;
	private String id_wf_approval;
	private String section_no;
	
	public String getSection_group() {
		return section_group;
	}
	public void setSection_group(String section_group) {
		this.section_group = section_group;
	}
	public String getId_wf_approval() {
		return id_wf_approval;
	}
	public void setId_wf_approval(String id_wf_approval) {
		this.id_wf_approval = id_wf_approval;
	}
	public String getSection_no() {
		return section_no;
	}
	public void setSection_no(String section_no) {
		this.section_no = section_no;
	}
	public BillingSystemUserValueObject getUvo() {
		return uvo;
	}
	public void setUvo(BillingSystemUserValueObject uvo) {
		this.uvo = uvo;
	}
	public String getShowPopalt() {
		return showPopalt;
	}
	public void setShowPopalt(String showPopalt) {
		this.showPopalt = showPopalt;
	}
	
	public String getClickEvent() {
		return clickEvent;
	}

	public void setClickEvent(String clickEvent) {
		this.clickEvent = clickEvent;
	}

	public String getId_ref() {
		return id_ref;
	}

	public void setId_ref(String id_ref) {
		this.id_ref = id_ref;
	}

}