package nttdm.bsys.m_cst.dto;

import java.io.Serializable;

public class M_CSTR03Output implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4800001648457681913L;
	
	/**
	 * <div>temp_cust_acc_no</div>
	 */
	private String temp_cust_acc_no = null;

	/**
	 * <div>temp_cust_name</div>
	 */
	private String temp_cust_name = null;

	/**
	 * <div>temp_co_regno</div>
	 */
	private String temp_co_regno = null;
	
	/**
	 * <div>temp_country</div>
	 */
	private String temp_country = null;
	
	/**
	 * <div>event</div>
	 */
	private String event = null;
	
	private String mode = null;
	
	private String id_cust = null;
	
	

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getId_cust() {
		return id_cust;
	}

	public void setId_cust(String id_cust) {
		this.id_cust = id_cust;
	}

	public String getTemp_cust_acc_no() {
		return temp_cust_acc_no;
	}

	public void setTemp_cust_acc_no(String temp_cust_acc_no) {
		this.temp_cust_acc_no = temp_cust_acc_no;
	}

	public String getTemp_cust_name() {
		return temp_cust_name;
	}

	public void setTemp_cust_name(String temp_cust_name) {
		this.temp_cust_name = temp_cust_name;
	}

	public String getTemp_co_regno() {
		return temp_co_regno;
	}

	public void setTemp_co_regno(String temp_co_regno) {
		this.temp_co_regno = temp_co_regno;
	}

	public String getTemp_country() {
		return temp_country;
	}

	public void setTemp_country(String temp_country) {
		this.temp_country = temp_country;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}
	
}
