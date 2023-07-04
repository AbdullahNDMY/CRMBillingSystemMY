package nttdm.bsys.g_gir.dto;

import java.io.Serializable;

import nttdm.bsys.common.util.G_GIR_P02;

public class G_GIR_P02SqlInput implements Serializable {
	
	private String bankCode;
	private String accountNo;
	private String reference;
	private String valueDate;
	private String isClose;
	private String setSeq;
	private String idSet;
	private String idBatchImpBatch;
	private String fileName;
	private String status;
	private String dateUploaded;
	private String idLogin;
	private String idBatchLog;
	private String idBatchType;
	private String idBatchRefNo;
	private String errMsg;
	private String idRef;
	private String idCust;
	private String pmtMethod;
	private String dateTrans;
	private String receiptNo;
	private String curCode;
	private String amtReceived;
	private String remark;
	private String pmtStatus;
	private String balanceAmt;
	private String reference1;
	private String reference2;
	private String bankCharge;
	private String isDelete;
	private String invoiceNo;
	private String amtPaid;
	private String forexLoss;
	private String forexgain;
	private String paidAmount;
	private String isSettle = null;
	private String receiptRef;
	private Integer amtDue;
	
	
	
	
	
	
	
	

	public Integer getAmtDue() {
		return amtDue;
	}
	public void setAmtDue(Integer amtDue) {
		this.amtDue = amtDue;
	}
	public String getReceiptRef() {
		return receiptRef;
	}
	public void setReceiptRef(String receiptRef) {
		this.receiptRef = receiptRef;
	}
	public String getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(String paidAmount) {
		this.paidAmount = paidAmount;
	}
	public String getIsSettle() {
		return isSettle;
	}
	public void setIsSettle(String isSettle) {
		this.isSettle = isSettle;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public String getAmtPaid() {
		return amtPaid;
	}
	public void setAmtPaid(String amtPaid) {
		this.amtPaid = amtPaid;
	}
	public String getForexLoss() {
		return forexLoss;
	}
	public void setForexLoss(String forexLoss) {
		this.forexLoss = forexLoss;
	}
	public String getForexgain() {
		return forexgain;
	}
	public void setForexgain(String forexgain) {
		this.forexgain = forexgain;
	}
	public String getIdRef() {
		return idRef;
	}
	public void setIdRef(String idRef) {
		this.idRef = idRef;
	}
	public String getIdCust() {
		return idCust;
	}
	public void setIdCust(String idCust) {
		this.idCust = idCust;
	}
	public String getPmtMethod() {
		return pmtMethod;
	}
	public void setPmtMethod(String pmtMethod) {
		this.pmtMethod = pmtMethod;
	}
	public String getDateTrans() {
		return dateTrans;
	}
	public void setDateTrans(String dateTrans) {
		this.dateTrans = dateTrans;
	}
	public String getReceiptNo() {
		return receiptNo;
	}
	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}
	public String getCurCode() {
		return curCode;
	}
	public void setCurCode(String curCode) {
		this.curCode = curCode;
	}
	public String getAmtReceived() {
		return amtReceived;
	}
	public void setAmtReceived(String amtReceived) {
		this.amtReceived = amtReceived;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPmtStatus() {
		return pmtStatus;
	}
	public void setPmtStatus(String pmtStatus) {
		this.pmtStatus = pmtStatus;
	}
	public String getBalanceAmt() {
		return balanceAmt;
	}
	public void setBalanceAmt(String balanceAmt) {
		this.balanceAmt = balanceAmt;
	}
	public String getReference1() {
		return reference1;
	}
	public void setReference1(String reference1) {
		this.reference1 = reference1;
	}
	public String getReference2() {
		return reference2;
	}
	public void setReference2(String reference2) {
		this.reference2 = reference2;
	}
	public String getBankCharge() {
		return bankCharge;
	}
	public void setBankCharge(String bankCharge) {
		this.bankCharge = bankCharge;
	}
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
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
	public String getIdBatchImpBatch() {
		return idBatchImpBatch;
	}
	public void setIdBatchImpBatch(String idBatchImpBatch) {
		this.idBatchImpBatch = idBatchImpBatch;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDateUploaded() {
		return dateUploaded;
	}
	public void setDateUploaded(String dateUploaded) {
		this.dateUploaded = dateUploaded;
	}
	public String getIdLogin() {
		return idLogin;
	}
	public void setIdLogin(String idLogin) {
		this.idLogin = idLogin;
	}
	public String getIdSet() {
		return idSet;
	}
	public void setIdSet(String idSet) {
		this.idSet = idSet;
	}
	public String getSetSeq() {
		return setSeq;
	}
	public void setSetSeq(String setSeq) {
		this.setSeq = setSeq;
	}
	public String getIsClose() {
		return isClose;
	}
	public void setIsClose(String isClose) {
		this.isClose = isClose;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		if (reference.length() < G_GIR_P02.REFERENCE_LENGTH ){
			for (int i = reference.length(); i < G_GIR_P02.REFERENCE_LENGTH; i++) {
				reference = reference + " ";
			}
		}
		System.out.println("reference=" + reference.length());
		this.reference = reference;
	}
	public String getValueDate() {
		return valueDate;
	}
	public void setValueDate(String valueDate) {
		this.valueDate = valueDate;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	
	
}
