package nttdm.bsys.g_ips.dto;

import java.io.Serializable;

public class G_IPS_P01SqlInput implements Serializable {
	private String status;
	private String idIpassImpBatch;
	private String idLogin;
	private String accessAc;
	private String codeNo;
	
	private String idBatchType;
	private String idBatchRefNo;
	private String errMsg;
	private String idBatchLog;
	private String fileName;
	private String idBatchImpBatch;
	private String closeMonthYear;
	private String dateUploaded;
	
	private String idIpassImpRaw;
	private String transId;
	private String billAcc;
	private String desc;
	private String timeGmt;
	private String timeLocal;
	private String sessionLen;
	private String billRate;
	private String netBillAtm;
	private String accessType;
	private String servType;
	private String idSet;
	
	
	
	
	public String getIdSet() {
		return idSet;
	}
	public void setIdSet(String idSet) {
		this.idSet = idSet;
	}
	public String getIdIpassImpRaw() {
		return idIpassImpRaw;
	}
	public void setIdIpassImpRaw(String idIpassImpRaw) {
		this.idIpassImpRaw = idIpassImpRaw;
	}
	public String getTransId() {
		return transId;
	}
	public void setTransId(String transId) {
		this.transId = transId;
	}
	public String getBillAcc() {
		return billAcc;
	}
	public void setBillAcc(String billAcc) {
		this.billAcc = billAcc;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getTimeGmt() {
		return timeGmt;
	}
	public void setTimeGmt(String timeGmt) {
		this.timeGmt = timeGmt;
	}
	public String getTimeLocal() {
		return timeLocal;
	}
	public void setTimeLocal(String timeLocal) {
		this.timeLocal = timeLocal;
	}
	public String getSessionLen() {
		return sessionLen;
	}
	public void setSessionLen(String sessionLen) {
		this.sessionLen = sessionLen;
	}
	public String getBillRate() {
		return billRate;
	}
	public void setBillRate(String billRate) {
		this.billRate = billRate;
	}
	public String getNetBillAtm() {
		return netBillAtm;
	}
	public void setNetBillAtm(String netBillAtm) {
		this.netBillAtm = netBillAtm;
	}
	public String getAccessType() {
		return accessType;
	}
	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}
	public String getServType() {
		return servType;
	}
	public void setServType(String servType) {
		this.servType = servType;
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
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getIdBatchImpBatch() {
		return idBatchImpBatch;
	}
	public void setIdBatchImpBatch(String idBatchImpBatch) {
		this.idBatchImpBatch = idBatchImpBatch;
	}
	public String getIdBatchLog() {
		return idBatchLog;
	}
	public void setIdBatchLog(String idBatchLog) {
		this.idBatchLog = idBatchLog;
	}
	public String getIdBatchType() {
		return idBatchType;
	}
	public void setIdBatchType(String idBatchType) {
		this.idBatchType = idBatchType;
	}
	public String getIdBatchRefNo() {
		return idBatchRefNo;
	}
	public void setIdBatchRefNo(String idBatchRefNo) {
		this.idBatchRefNo = idBatchRefNo;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
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
	public String getIdLogin() {
		return idLogin;
	}
	public void setIdLogin(String idLogin) {
		this.idLogin = idLogin;
	}
	public String getAccessAc() {
		return accessAc;
	}
	public void setAccessAc(String accessAc) {
		this.accessAc = accessAc;
	}
	public String getCodeNo() {
		return codeNo;
	}
	public void setCodeNo(String codeNo) {
		this.codeNo = codeNo;
	}
}
