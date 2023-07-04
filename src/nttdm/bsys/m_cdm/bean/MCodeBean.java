/*
 * @(#)MCodeBean.java
 * $Id$
 */
package nttdm.bsys.m_cdm.bean;

import java.io.Serializable;

/**
 * resultClass: id=SELECT.M_CDM.SQL001
 * 
 * @author Duyen.Vo
 */
public class MCodeBean implements Serializable {

	/**
	 * <div>serialVersionUID</div>
	 */
	private static final long serialVersionUID = 2231759599223521555L;

	/**
	 * <div>init_val</div>
	 */
	private String init_val = null;

	/**
	 * <div>cur_val</div>
	 */
	private String cur_val = null;
	
	/**
	 * <div>id_login</div>
	 */
	private String id_login = null;
	
	/**
	 * <div>id_code</div>
	 */
	private String id_code = null;
	
	/**
	 * <div>is_deleted</div>
	 */
	private String is_deleted = null;
	
	/**
	 * <div>id_sub_code</div>
	 */
	private String id_sub_code = null;

	/**
	 * <div>type_val</div>
	 */
	private String type_val = null;

	/**
	 * <div>value</div>
	 */
	private String value = null;

	/**
	 * <div>status</div>
	 */
	private String status = null;
	
	private Integer id_audit = null;
	
	private String reset_no;

	public String getId_sub_code() {
		return id_sub_code;
	}

	public void setId_sub_code(String id_sub_code) {
		this.id_sub_code = id_sub_code;
	}

	public String getType_val() {
		return type_val;
	}

	public void setType_val(String type_val) {
		this.type_val = type_val;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setId_code(String id_code) {
		this.id_code = id_code;
	}

	public String getId_code() {
		return id_code;
	}

	public void setInit_val(String init_val) {
		this.init_val = init_val;
	}

	public String getInit_val() {
		return init_val;
	}

	public void setCur_val(String cur_val) {
		this.cur_val = cur_val;
	}

	public String getCur_val() {
		return cur_val;
	}

	public void setId_login(String id_login) {
		this.id_login = id_login;
	}

	public String getId_login() {
		return id_login;
	}

	public void setIs_deleted(String is_deleted) {
		this.is_deleted = is_deleted;
	}

	public String getIs_deleted() {
		return is_deleted;
	}
	
	public MCodeBean(){
	}
	
	public MCodeBean(String codeType, String seq){
		//set default values
		this.id_code=codeType;
		this.id_sub_code=seq;
		this.status="1";
		this.reset_no="0";
	}
	
	public Integer getId_audit() {
		return id_audit;
	}

	public void setId_audit(Integer id_audit) {
		this.id_audit = id_audit;
	}

    /**
     * @return the reset_no
     */
    public String getReset_no() {
        return reset_no;
    }

    /**
     * @param reset_no the reset_no to set
     */
    public void setReset_no(String reset_no) {
        this.reset_no = reset_no;
    }
}