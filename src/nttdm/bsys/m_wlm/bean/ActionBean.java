package nttdm.bsys.m_wlm.bean;

import java.io.Serializable;

public class ActionBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6818878593530928509L;
	private String id_secgrp = null;
	private String section_no = null;
	private String level_seq = null;
	private String id_action = null;
	private String action_type = null;
	private String pic = null;
	private String response_expire = null;
	private String condition_primary = null;
	private String condition_operator = null;
	private String condition_secondary = null;
	private String id_user = null;
	
	/**
	 * Audit trail ID
	 */
	private Integer auditID;
	
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
	public String getLevel_seq() {
		return level_seq;
	}
	public void setLevel_seq(String level_seq) {
		this.level_seq = level_seq;
	}
	public String getId_action() {
		return id_action;
	}
	public void setId_action(String id_action) {
		this.id_action = id_action;
	}
	public String getAction_type() {
		return action_type;
	}
	public void setAction_type(String action_type) {
		this.action_type = action_type;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getResponse_expire() {
		return response_expire;
	}
	public void setResponse_expire(String response_expire) {
		this.response_expire = response_expire;
	}
	public String getCondition_primary() {
		return condition_primary;
	}
	public void setCondition_primary(String condition_primary) {
		this.condition_primary = condition_primary;
	}
	public String getCondition_operator() {
		return condition_operator;
	}
	public void setCondition_operator(String condition_operator) {
		this.condition_operator = condition_operator;
	}
	public String getCondition_secondary() {
		return condition_secondary;
	}
	public void setCondition_secondary(String condition_secondary) {
		this.condition_secondary = condition_secondary;
	}
	public String getId_user() {
		return id_user;
	}
	public void setId_user(String id_user) {
		this.id_user = id_user;
	}
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
