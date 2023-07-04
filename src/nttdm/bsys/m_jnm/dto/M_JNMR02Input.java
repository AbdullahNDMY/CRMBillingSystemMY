/*
 * @(#)M_JNMR01Input.java
 *
 *
 */
package nttdm.bsys.m_jnm.dto;

import java.io.Serializable;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;


public class M_JNMR02Input implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6016366325152242755L;

	private BillingSystemUserValueObject uvo;
	private String id_cust;
	private String job_no;
	private String idLogin = "";
	private String clickEvent;
	private String saveMode;
	private String msgBoxMode;
	private String isSaveFlg;
	private String actionFrom;
	
	public String getMsgBoxMode() {
		return msgBoxMode;
	}

	public void setMsgBoxMode(String msgBoxMode) {
		this.msgBoxMode = msgBoxMode;
	}

	public String getSaveMode() {
		return saveMode;
	}

	public void setSaveMode(String saveMode) {
		this.saveMode = saveMode;
	}

	/**
	 * get uvo
	 * 
	 * @return uvo
	 */
	public BillingSystemUserValueObject getUvo() {
		return uvo;
	}
	
	/**
	 * setting uvo
	 * 
	 * @param uvo
	 */
	public void setUvo(BillingSystemUserValueObject uvo) {
		this.uvo = uvo;
	}
	
	
	/**
	 * get id_cust
	 * 
	 * @return id_cust
	 */
	public String getId_cust() {		
		return id_cust;
	}
	
	/**
	 * setting id_cust
	 * 
	 * @param id_cust
	 */
	public void setId_cust(String id_cust) {
		this.id_cust = id_cust != null ? id_cust.trim() :id_cust;
	}

	/**
	 * get Job_no
	 * 
	 * @return Job_no
	 */
	public String getJob_no() {
		return job_no;
	}

	/**
	 * setting Job_no
	 * 
	 * @param Job_no
	 */
	public void setJob_no(String job_no) {
		this.job_no = job_no != null ? job_no.trim() :job_no;
	}

	/**
	 * get idLogin
	 * 
	 * @return idLogin
	 */
	public String getIdLogin() {
		return idLogin;
	}

	/**
	 * setting idLogin
	 * 
	 * @param idLogin
	 */
	public void setIdLogin(String idLogin) {
		this.idLogin = idLogin;
	}
	
	/**
	 * get clickEvent
	 * 
	 * @return clickEvent
	 */
	public String getClickEvent() {
		return clickEvent;
	}
	
	/**
	 * setting clickEvent
	 * 
	 * @param clickEvent
	 */
	public void setClickEvent(String clickEvent) {
		this.clickEvent = clickEvent;
	}

	/**
	 * get isSaveFlg
	 * 
	 * @return isSaveFlg
	 */
	public String getIsSaveFlg() {
		return isSaveFlg;
	}

	/**
	 * setting isSaveFlg
	 * 
	 * @param isSaveFlg
	 */
	public void setIsSaveFlg(String isSaveFlg) {
		this.isSaveFlg = isSaveFlg;
	}

	/**
     * get actionFrom
     * 
     * @return actionFrom
     */
    public String getActionFrom() {
        return actionFrom;
    }

    /**
     * setting actionFrom
     * 
     * @param actionFrom
     */
    public void setActionFrom(String actionFrom) {
        this.actionFrom = actionFrom;
    }
	
}