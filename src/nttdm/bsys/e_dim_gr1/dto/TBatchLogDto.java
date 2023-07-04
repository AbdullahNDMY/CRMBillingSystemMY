package nttdm.bsys.e_dim_gr1.dto;

import java.io.Serializable;

public class TBatchLogDto implements Serializable {
	private String idBatchLog;
	private String idBatchRefNo;
	private String errorMsg;
	private String dateCreated;
	private String dateUpdated;
	private String idLogin;
	private String index;
	private String dateUploaded = "";
	
	
	public String getDateUploaded() {
		return dateUploaded;
	}
	public void setDateUploaded(String dateUploaded) {
		this.dateUploaded = dateUploaded;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
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
	
	
	

}
