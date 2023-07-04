package nttdm.bsys.g_gir.dto;

import java.io.Serializable;

public class T_BIL_HDto implements Serializable {
	private Integer billAmount;
	private Integer paidAmount;
	private String idRef;
	private String idCust;
	
	
	
	public String getIdCust() {
		return idCust;
	}
	public void setIdCust(String idCust) {
		this.idCust = idCust;
	}
	public String getIdRef() {
		return idRef;
	}
	public void setIdRef(String idRef) {
		this.idRef = idRef;
	}
	public Integer getBillAmount() {
		return billAmount;
	}
	public void setBillAmount(Integer billAmount) {
		this.billAmount = billAmount;
	}
	public Integer getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(Integer paidAmount) {
		this.paidAmount = paidAmount;
	}
	
	
}
