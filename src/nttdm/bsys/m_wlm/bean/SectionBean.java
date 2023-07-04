package nttdm.bsys.m_wlm.bean;

import java.io.Serializable;

public class SectionBean implements Serializable{
	public String getId_secgrp() {
		return id_secgrp;
	}
	public void setId_secgrp(String id_secgrp) {
		this.id_secgrp = id_secgrp;
	}
	public String getSection_no() {
		return section_no;
	}
	public void setSection_no(String section_no) {
		this.section_no = section_no;
	}
	public String getSection_desc() {
		return section_desc;
	}
	public void setSection_desc(String section_desc) {
		this.section_desc = section_desc;
	}
	public void setSequence_no(String sequence_no) {
		this.sequence_no = sequence_no;
	}
	public String getSequence_no() {
		return sequence_no;
	}
	
	public String getId_screen() {
		return id_screen;
	}
	public void setId_screen(String id_screen) {
		this.id_screen = id_screen;
	}
	public String getId_user() {
		return id_user;
	}
	public void setId_user(String id_user) {
		this.id_user = id_user;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -1695685258200271029L;
	private String id_screen = null;
	private String id_secgrp = null;
	private String section_no = null;
	private String section_desc = null;
	private String sequence_no = null;
	private String id_user = null;
	
	/**
	 * Audit trail ID
	 */
	private Integer auditID;

	/**
	 * Gets the auditID
	 * @return auditID
	 */
	public Integer getAuditID() {
		return auditID;
	}
	/**
	 * Sets the auditID
	 * @param auditID
	 */
	public void setAuditID(Integer auditID) {
		this.auditID = auditID;
	}
	
}
