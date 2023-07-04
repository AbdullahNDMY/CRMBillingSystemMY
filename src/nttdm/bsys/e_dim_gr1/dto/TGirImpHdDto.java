package nttdm.bsys.e_dim_gr1.dto;

import java.io.Serializable;

public class TGirImpHdDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String index;
	private String idGirImpBatch;
	private String fileName;
	private String closeMonthYear;
	private String dateUploaded;
	private String status;
	private String dateCreated;
	private String dateUpdated;
	private String idLogin;
	
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getIdGirImpBatch() {
		return idGirImpBatch;
	}
	public void setIdGirImpBatch(String idGirImpBatch) {
		this.idGirImpBatch = idGirImpBatch;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getCloseMonthYear() {
		return closeMonthYear;
	}
	public void setCloseMonthYear(String closeMonthYear) {
		this.closeMonthYear = closeMonthYear;
	}
	public String getDateUploaded() {
		return dateUploaded;
	}
	public void setDateUploaded(String dateUploaded) {
		this.dateUploaded = dateUploaded;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
