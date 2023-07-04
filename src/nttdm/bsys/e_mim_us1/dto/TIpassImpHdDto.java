package nttdm.bsys.e_mim_us1.dto;

import java.io.Serializable;

public class TIpassImpHdDto implements Serializable {
	
	private String idBatchLog;
	private String idBatchRefNo;
	private String errorMsg;
	private String dateCreated;
	private String dateUpdated;
	private String idLogin;
	private String index;
	private String dateUploaded;
	private String fileName;
	// for T_IPASS_IMP_HD
	private String idIpassImpBatch;
	private String closeMonthYear;
	private String status;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getIdBatchLog() {
		return idBatchLog;
	}
	public void setIdBatchLog(String idBatchLog) {
		this.idBatchLog = idBatchLog;
	}
	public String getIdBatchRefNo() {
		return idBatchRefNo;
	}
	public void setIdBatchRefNo(String idBatchRefNo) {
		this.idBatchRefNo = idBatchRefNo;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public String getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	public String getDateUpdated() {
		return dateUpdated;
	}
	public void setDateUpdated(String dateUpdated) {
		this.dateUpdated = dateUpdated;
	}
	public String getIdLogin() {
		return idLogin;
	}
	public void setIdLogin(String idLogin) {
		this.idLogin = idLogin;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getDateUploaded() {
		return dateUploaded;
	}
	public void setDateUploaded(String dateUploaded) {
		this.dateUploaded = dateUploaded;
	}
	public String getIdIpassImpBatch() {
		return idIpassImpBatch;
	}
	public void setIdIpassImpBatch(String idIpassImpBatch) {
		this.idIpassImpBatch = idIpassImpBatch;
	}
	public String getCloseMonthYear() {
		return closeMonthYear;
	}
	public void setCloseMonthYear(String closeMonthYear) {
		this.closeMonthYear = closeMonthYear;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	

}
