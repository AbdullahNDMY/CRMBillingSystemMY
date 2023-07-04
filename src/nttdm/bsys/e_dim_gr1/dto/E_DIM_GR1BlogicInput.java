package nttdm.bsys.e_dim_gr1.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;

import org.apache.struts.upload.FormFile;

public class E_DIM_GR1BlogicInput implements Serializable{
	
	private FormFile fileName;
	private Integer totalCount=0;
	private Integer row=0;
	private Integer startIndex=0;
	private String errorMessage;
	private String logDate;
	private BillingSystemUserValueObject uvo;
	private String alertMsg;
	
	
	public String getAlertMsg() {
		return alertMsg;
	}
	public void setAlertMsg(String alertMsg) {
		this.alertMsg = alertMsg;
	}
	public BillingSystemUserValueObject getUvo() {
		return uvo;
	}
	public void setUvo(BillingSystemUserValueObject uvo) {
		this.uvo = uvo;
	}
	public FormFile getFileName() {
		return fileName;
	}
	public void setFileName(FormFile fileName) {
		this.fileName = fileName;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getRow() {
		return row;
	}
	public void setRow(Integer row) {
		this.row = row;
	}
	public Integer getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getLogDate() {
		return logDate;
	}
	public void setLogDate(String logDate) {
		this.logDate = logDate;
	}
	
	
}
