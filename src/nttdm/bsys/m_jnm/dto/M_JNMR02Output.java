/*
 * @(#)M_JNMR01Output.java
 *
 *
 */
package nttdm.bsys.m_jnm.dto;

import java.io.Serializable;

/**
 * OutputDTO class.
 * 
 * @author ss051
 */
public class M_JNMR02Output implements Serializable {

	private static final long serialVersionUID = -77363358361876312L;
		
	private String id_cust;	
	private String job_no;
	private String msgBoxMode;
	private String saveMode;
	private String cust_name;	
	private String isSaveFlg;
	private String same_cust_name;
	private String same_id_cust;
	private String actionFrom;
	
	/**
	 * get msgBoxMode
	 * 
	 * @return msgBoxMode
	 */
	public String getMsgBoxMode() {
		return msgBoxMode;
	}

	/**
	 * setting msgBoxMode
	 * 
	 * @param msgBoxMode
	 */
	public void setMsgBoxMode(String msgBoxMode) {
		this.msgBoxMode = msgBoxMode;
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
		this.id_cust = id_cust;
	}
	
	/**
	 * get job_no
	 * 
	 * @return job_no
	 */
	public String getJob_no() {
		return job_no;
	}
	
	/**
	 * setting job_no
	 * 
	 * @param job_no
	 */
	public void setJob_no(String job_no) {
		this.job_no = job_no;
	}

	/**
	 * get saveMode
	 * 
	 * @return saveMode
	 */
	public String getSaveMode() {
		return saveMode;
	}

	/**
	 * setting saveMode
	 * 
	 * @param saveMode
	 */
	public void setSaveMode(String saveMode) {
		this.saveMode = saveMode;
	}

	/**
	 * get cust_name
	 * 
	 * @return cust_name
	 */
	public String getCust_name() {
		return cust_name;
	}

	/**
	 * setting cust_name
	 * 
	 * @param cust_name
	 */
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
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

	public String getSame_cust_name() {
		return same_cust_name;
	}

	public void setSame_cust_name(String same_cust_name) {
		this.same_cust_name = same_cust_name;
	}

	public String getSame_id_cust() {
		return same_id_cust;
	}

	public void setSame_id_cust(String same_id_cust) {
		this.same_id_cust = same_id_cust;
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