package nttdm.bsys.e_dim_gr1.dto;

import java.io.Serializable;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;

import org.apache.struts.upload.FormFile;

public class E_DIM_GR1_2BlogicInput implements Serializable{
	private String idGirImpBatch;
	private Integer startIndext = 0;
	private Integer row = 0;
	private FormFile fileName;
	private BillingSystemUserValueObject uvo;
	private String alertMsg;
	private Integer startIndex=0;

	
	


	public FormFile getFileName() {
		return fileName;
	}

	public void setFileName(FormFile fileName) {
		this.fileName = fileName;
	}

	public BillingSystemUserValueObject getUvo() {
		return uvo;
	}

	public void setUvo(BillingSystemUserValueObject uvo) {
		this.uvo = uvo;
	}

	public String getAlertMsg() {
		return alertMsg;
	}

	public void setAlertMsg(String alertMsg) {
		this.alertMsg = alertMsg;
	}

	public Integer getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

	public Integer getStartIndext() {
		return startIndext;
	}

	public void setStartIndext(Integer startIndext) {
		this.startIndext = startIndext;
	}

	public Integer getRow() {
		return row;
	}

	public void setRow(Integer row) {
		this.row = row;
	}

	public String getIdGirImpBatch() {
		return idGirImpBatch;
	}

	public void setIdGirImpBatch(String idGirImpBatch) {
		this.idGirImpBatch = idGirImpBatch;
	}
	
	
}
