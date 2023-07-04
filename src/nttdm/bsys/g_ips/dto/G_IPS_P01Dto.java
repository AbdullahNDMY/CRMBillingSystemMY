package nttdm.bsys.g_ips.dto;

import java.io.Serializable;
import java.util.Date;

public class G_IPS_P01Dto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String status;
	private String idIpassImpBatch;
	private Date updatedDate;	
	private Integer sessionTotal; 
	private String codeNo;
	private String accessAcc; 
	
	public String getCodeNo() {
		return codeNo;
	}
	public void setCodeNo(String codeNo) {
		this.codeNo = codeNo;
	}
	public Integer getSessionTotal() {
		return sessionTotal;
	}
	public void setSessionTotal(Integer sessionTotal) {
		this.sessionTotal = sessionTotal;
	}

	public String getAccessAcc() {
		return accessAcc;
	}
	public void setAccessAcc(String accessAcc) {
		this.accessAcc = accessAcc;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getIdIpassImpBatch() {
		return idIpassImpBatch;
	}
	public void setIdIpassImpBatch(String idIpassImpBatch) {
		this.idIpassImpBatch = idIpassImpBatch;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
}
