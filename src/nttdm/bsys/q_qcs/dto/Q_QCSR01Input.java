/*
 * @(#)Q_QCSR01Input.java
 *
 *
 */
package nttdm.bsys.q_qcs.dto;

import java.io.Serializable;

/**
 * InputDTO class.
 * 
 * @author ss051
 */
public class Q_QCSR01Input implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3377094672007231472L;

	/**
     * 
     */
	private String id_ref;

	/**
     * 
     */
	private String id_quo;

	/**
     * 
     */
	private String cust_name;

	/**
     * 
     */
	private String user_name;

	/**
     * 
     */
	private String start_date;

	/**
     * 
     */
	private String end_date;
	
	public String getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(String startIndex) {
		this.startIndex = startIndex;
	}

	private String startIndex = null;
	

	/**
	 * id_ref 
	 * 
	 * @return id_ref
	 */
	public String getId_ref() {
		return id_ref;
	}

	/**
	 * id_ref 
	 * 
	 * @param id_ref
	 */
	public void setId_ref(String id_ref) {
		this.id_ref = id_ref;
	}

	/**
	 * id_quo 
	 * 
	 * @return id_quo
	 */
	public String getId_quo() {
		return id_quo;
	}

	/**
	 * id_quo 
	 * 
	 * @param id_quo
	 */
	public void setId_quo(String id_quo) {
		this.id_quo = id_quo;
	}

	/**
	 * cust_name 
	 * 
	 * @return cust_name
	 */
	public String getCust_name() {
		return cust_name;
	}

	/**
	 * cust_name 
	 * 
	 * @param cust_name
	 */
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}

	/**
	 * user_name 
	 * 
	 * @return user_name
	 */
	public String getUser_name() {
		return user_name;
	}

	/**
	 * user_name 
	 * 
	 * @param user_name
	 */
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	/**
	 * start_date 
	 * 
	 * @return start_date
	 */
	public String getStart_date() {
		return start_date;
	}

	/**
	 * start_date 
	 * 
	 * @param start_date
	 */
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	/**
	 * end_date 
	 * 
	 * @return end_date
	 */
	public String getEnd_date() {
		return end_date;
	}

	/**
	 * end_date 
	 * 
	 * @param end_date
	 */
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

}