package nttdm.bsys.b_cpm.dto;

import java.io.Serializable;

public class Customer implements Serializable {
	private String custName;
	private String idCust;
	private String custType;
	
	
	public String getCustType() {
		return custType;
	}
	public void setCustType(String custType) {
		this.custType = custType;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getIdCust() {
		return idCust;
		
	}
	public void setIdCust(String idCust) {
		this.idCust = idCust;
	}
	
	
}
