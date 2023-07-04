package nttdm.bsys.e_mex.dto;

import java.io.Serializable;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;

public class RP_E_MEX_GR1_3Input implements Serializable {
	
	private BillingSystemUserValueObject uvo;
	
	private String giroDay;
	
	private String giroMonth;
	
	private String giroYear;
	
	private Integer idBatch;
	
	private String forward_execute;

	public String getForward_execute() {
		return forward_execute;
	}

	public void setForward_execute(String forward_execute) {
		this.forward_execute = forward_execute;
	}

	public Integer getIdBatch() {
		return idBatch;
	}

	public void setIdBatch(Integer idBatch) {
		this.idBatch = idBatch;
	}

	public BillingSystemUserValueObject getUvo() {
		return uvo;
	}

	public void setUvo(BillingSystemUserValueObject uvo) {
		this.uvo = uvo;
	}

	public String getGiroDay() {
		return giroDay;
	}

	public void setGiroDay(String giroDay) {
		this.giroDay = giroDay;
	}

	public String getGiroMonth() {
		return giroMonth;
	}

	public void setGiroMonth(String giroMonth) {
		this.giroMonth = giroMonth;
	}

	public String getGiroYear() {
		return giroYear;
	}

	public void setGiroYear(String giroYear) {
		this.giroYear = giroYear;
	}
}
