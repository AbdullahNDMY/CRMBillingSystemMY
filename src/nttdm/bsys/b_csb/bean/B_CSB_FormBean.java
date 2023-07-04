package nttdm.bsys.b_csb.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.web.struts.form.ValidatorActionFormEx;
import nttdm.bsys.b_csb.dto.Debit_info_bean;
import nttdm.bsys.b_csb.dto.PaymentRefDetail;
import nttdm.bsys.common.util.dto.AutoScaleList;

import org.apache.struts.util.LabelValueBean;

public class B_CSB_FormBean extends ValidatorActionFormEx {

    private static final long serialVersionUID = 1L;

    private String idRef;

    private String pmtMethod;

    private String payer;

    private String payerName;
    /*#263 [T11] Add customer type at inquiry screen and export result CT 06062017*/
    private String payerType;
    /*#263 [T11] Add customer type at inquiry screen and export result CT 06062017*/
    private List<LabelValueBean> cbPayer;

    private String other;

    private String transDate;

    private String bankAcc;

    private List<LabelValueBean> cbBankAcc;

    private String receiptRef;

    private String curCode;

    private String amtReceived;

    private String remark;

    private String paymentStatus;

    private String paymentRef1;

    private String paymentRef2;

    private String bankCharge;

    private String receiptNo;

    private List<Debit_info_bean> debitInfos = new ArrayList<Debit_info_bean>();

    private String actionFlg;

    private String isClosed;

    private String startDate;

    private String endDate;

    private String refNo;

    private String checkpageview;

    private Integer startIndex;

    private Integer row;

    private Integer totalRow;

    private BigDecimal totalAmt;

    private BigDecimal totalBalanceAmt;

    private List<Map<String, Object>> csbInfos;

    private Map<String, Object> refundInfo;

    private List<Map<String, Object>> refundInfoList;

    private String isAlterMsg;

    public String getIsAlterMsg() {
        return isAlterMsg;
    }

    public void setIsAlterMsg(String isAlterMsg) {
        this.isAlterMsg = isAlterMsg;
    }

    public List<Map<String, Object>> getRefundInfoList() {
        return refundInfoList;
    }

    public void setRefundInfoList(List<Map<String, Object>> refundInfoList) {
        this.refundInfoList = refundInfoList;
    }

    public Map<String, Object> getRefundInfo() {
        return refundInfo;
    }

    public void setRefundInfo(Map<String, Object> refundInfo) {
        this.refundInfo = refundInfo;
    }

    /**
     * Filter By Balance
     */
    private String filterBybalance;

    /**
     * Paid Previous Invoice text Yes or No
     */
    private String paidPreInvoiceText;

    /**
     * over paid text Yes or No
     */
    private String overPaidText;

    /**
     * init pattern CST or BAC
     */
    private String pattern;

    /**
     * ID_BILL_ACCOUNT
     */
    private String idBillAccount;

    /**
     * Reject date
     */
    private String rejectionDate;

    /**
     * Bill Account No
     */
    private List<LabelValueBean> cboBillAccountNo;

    /**
     * model Delete or Reject
     */
    private String model;

    /**
     * label PaymentRef1
     */
    private String labPaymentRef1;

    /**
     * label PaymentRef2
     */
    private String labPaymentRef2;

    /**
     * Default CurCode
     */
    private String defCurCode;

    /**
     * Billing Account No.
     */
    private String billaccNo;

    /**
     * REJECT_DATE
     */
    private String rejecteddate;

    /**
     * REJECT_DESC
     */
    private String rejectdesc;

    /**
     * PAYMENT_REF1_DE
     */
    private String paymentRef1De;

    /**
     * PAYMENT_REF2_DE
     */
    private String paymentRef2De;

    private List<PaymentRefDetail> paymentRefDetails1;

    private List<PaymentRefDetail> paymentRefDetails2;

    private String fromPageFlg;

    private String isCheckMulCharFlg;

    public B_CSB_FormBean() {
        this.debitInfos = new AutoScaleList<Debit_info_bean>(new Debit_info_bean());
    }

    public List<PaymentRefDetail> getPaymentRefDetails1() {
        return paymentRefDetails1;
    }

    public void setPaymentRefDetails1(List<PaymentRefDetail> paymentRefDetails1) {
        this.paymentRefDetails1 = paymentRefDetails1;
    }

    public List<PaymentRefDetail> getPaymentRefDetails2() {
        return paymentRefDetails2;
    }

    public void setPaymentRefDetails2(List<PaymentRefDetail> paymentRefDetails2) {
        this.paymentRefDetails2 = paymentRefDetails2;
    }

    public String getRejecteddate() {
        return rejecteddate;
    }

    public void setRejecteddate(String rejecteddate) {
        this.rejecteddate = rejecteddate;
    }

    public String getRejectdesc() {
        return rejectdesc;
    }

    public void setRejectdesc(String rejectdesc) {
        this.rejectdesc = rejectdesc;
    }

    public String getBillaccNo() {
        return billaccNo;
    }

    public void setBillaccNo(String billaccNo) {
        this.billaccNo = billaccNo;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(Integer totalRow) {
        this.totalRow = totalRow;
    }

    public BigDecimal getTotalAmt() {
        return totalAmt;
    }

    public void setTotalAmt(BigDecimal totalAmt) {
        this.totalAmt = totalAmt;
    }

    /**
     * special getter and setter
     */
    // public Debit_info_bean getDbInfo(int index) {
    // if(this.debitInfos.isEmpty())
    // return new Debit_info_bean();
    // return this.debitInfos.get(index);
    // }

    /**
     * getter and setter
     */

    public String getIdRef() {
        return idRef;
    }

    public void setIdRef(String idRef) {
        this.idRef = idRef;
    }

    public String getPmtMethod() {
        return pmtMethod;
    }

    public void setPmtMethod(String pmtMethod) {
        this.pmtMethod = pmtMethod;
    }

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public List<LabelValueBean> getCbPayer() {
        return cbPayer;
    }

    public void setCbPayer(List<LabelValueBean> cbPayer) {
        this.cbPayer = cbPayer;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getTransDate() {
        return transDate;
    }

    public void setTransDate(String transDate) {
        this.transDate = transDate;
    }

    public String getBankAcc() {
        return bankAcc;
    }

    public void setBankAcc(String bankAcc) {
        this.bankAcc = bankAcc;
    }

    public List<LabelValueBean> getCbBankAcc() {
        return cbBankAcc;
    }

    public void setCbBankAcc(List<LabelValueBean> cbBankAcc) {
        this.cbBankAcc = cbBankAcc;
    }

    public String getReceiptRef() {
        return receiptRef;
    }

    public void setReceiptRef(String receiptRef) {
        this.receiptRef = receiptRef;
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

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentRef1() {
        return paymentRef1;
    }

    public void setPaymentRef1(String paymentRef1) {
        this.paymentRef1 = paymentRef1;
    }

    public String getPaymentRef2() {
        return paymentRef2;
    }

    public void setPaymentRef2(String paymentRef2) {
        this.paymentRef2 = paymentRef2;
    }

    public String getBankCharge() {
        return bankCharge;
    }

    public void setBankCharge(String bankCharge) {
        this.bankCharge = bankCharge;
    }

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    public List<Debit_info_bean> getDebitInfos() {
        return debitInfos;
    }

    public void setDebitInfos(List<Debit_info_bean> debitInfos) {
        this.debitInfos = debitInfos;
    }

    /**
     * @return the actionFlg
     */
    public String getActionFlg() {
        return actionFlg;
    }

    /**
     * @param actionFlg
     *            the actionFlg to set
     */
    public void setActionFlg(String actionFlg) {
        this.actionFlg = actionFlg;
    }

    public String getIsClosed() {
        return isClosed;
    }

    public void setIsClosed(String isClosed) {
        this.isClosed = isClosed;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getRefNo() {
        return refNo;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    public String getCheckpageview() {
        return checkpageview;
    }

    public void setCheckpageview(String checkpageview) {
        this.checkpageview = checkpageview;
    }

    public List<Map<String, Object>> getCsbInfos() {
        return csbInfos;
    }

    public void setCsbInfos(List<Map<String, Object>> csbInfos) {
        this.csbInfos = csbInfos;
    }

    /**
     * @return the filterBybalance
     */
    public String getFilterBybalance() {
        return filterBybalance;
    }

    /**
     * @param filterBybalance
     *            the filterBybalance to set
     */
    public void setFilterBybalance(String filterBybalance) {
        this.filterBybalance = filterBybalance;
    }

    /**
     * @return the paidPreInvoiceText
     */
    public String getPaidPreInvoiceText() {
        return paidPreInvoiceText;
    }

    /**
     * @param paidPreInvoiceText
     *            the paidPreInvoiceText to set
     */
    public void setPaidPreInvoiceText(String paidPreInvoiceText) {
        this.paidPreInvoiceText = paidPreInvoiceText;
    }

    /**
     * @return the overPaidText
     */
    public String getOverPaidText() {
        return overPaidText;
    }

    /**
     * @param overPaidText
     *            the overPaidText to set
     */
    public void setOverPaidText(String overPaidText) {
        this.overPaidText = overPaidText;
    }

    /**
     * @return the pattern
     */
    public String getPattern() {
        return pattern;
    }

    /**
     * @param pattern
     *            the pattern to set
     */
    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    /**
     * @return the idBillAccount
     */
    public String getIdBillAccount() {
        return idBillAccount;
    }

    /**
     * @param idBillAccount
     *            the idBillAccount to set
     */
    public void setIdBillAccount(String idBillAccount) {
        this.idBillAccount = idBillAccount;
    }

    /**
     * @return the rejectionDate
     */
    public String getRejectionDate() {
        return rejectionDate;
    }

    /**
     * @param rejectionDate
     *            the rejectionDate to set
     */
    public void setRejectionDate(String rejectionDate) {
        this.rejectionDate = rejectionDate;
    }

    /**
     * @return the model
     */
    public String getModel() {
        return model;
    }

    /**
     * @param model
     *            the model to set
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * @return the cboBillAccountNo
     */
    public List<LabelValueBean> getCboBillAccountNo() {
        return cboBillAccountNo;
    }

    /**
     * @param cboBillAccountNo
     *            the cboBillAccountNo to set
     */
    public void setCboBillAccountNo(List<LabelValueBean> cboBillAccountNo) {
        this.cboBillAccountNo = cboBillAccountNo;
    }

    /**
     * @return the labPaymentRef1
     */
    public String getLabPaymentRef1() {
        return labPaymentRef1;
    }

    /**
     * @param labPaymentRef1
     *            the labPaymentRef1 to set
     */
    public void setLabPaymentRef1(String labPaymentRef1) {
        this.labPaymentRef1 = labPaymentRef1;
    }

    /**
     * @return the labPaymentRef2
     */
    public String getLabPaymentRef2() {
        return labPaymentRef2;
    }

    /**
     * @param labPaymentRef2
     *            the labPaymentRef2 to set
     */
    public void setLabPaymentRef2(String labPaymentRef2) {
        this.labPaymentRef2 = labPaymentRef2;
    }

    /**
     * @return the defCurCode
     */
    public String getDefCurCode() {
        return defCurCode;
    }

    /**
     * @param defCurCode
     *            the defCurCode to set
     */
    public void setDefCurCode(String defCurCode) {
        this.defCurCode = defCurCode;
    }

    /**
     * @return the paymentRef1De
     */
    public String getPaymentRef1De() {
        return paymentRef1De;
    }

    /**
     * @param paymentRef1De
     *            the paymentRef1De to set
     */
    public void setPaymentRef1De(String paymentRef1De) {
        this.paymentRef1De = paymentRef1De;
    }

    /**
     * @return the paymentRef2De
     */
    public String getPaymentRef2De() {
        return paymentRef2De;
    }

    /**
     * @param paymentRef2De
     *            the paymentRef2De to set
     */
    public void setPaymentRef2De(String paymentRef2De) {
        this.paymentRef2De = paymentRef2De;
    }

    /**
     * @return the payerName
     */
    public String getPayerName() {
        return payerName;
    }

    /**
     * @param payerName
     *            the payerName to set
     */
    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    /**
     * @return the fromPageFlg
     */
    public String getFromPageFlg() {
        return fromPageFlg;
    }

    /**
     * @param fromPageFlg
     *            the fromPageFlg to set
     */
    public void setFromPageFlg(String fromPageFlg) {
        this.fromPageFlg = fromPageFlg;
    }

    public BigDecimal getTotalBalanceAmt() {
        return totalBalanceAmt;
    }

    public void setTotalBalanceAmt(BigDecimal totalBalanceAmt) {
        this.totalBalanceAmt = totalBalanceAmt;
    }

    /**
     * @return the isCheckMulCharFlg
     */
    public String getIsCheckMulCharFlg() {
        return isCheckMulCharFlg;
    }

    /**
     * @param isCheckMulCharFlg
     *            the isCheckMulCharFlg to set
     */
    public void setIsCheckMulCharFlg(String isCheckMulCharFlg) {
        this.isCheckMulCharFlg = isCheckMulCharFlg;
    }

	public String getPayerType() {
		return payerType;
	}

	public void setPayerType(String payerType) {
		this.payerType = payerType;
	}
    
    /*#263 [T11] Add customer type at inquiry screen and export result CT 06062017*/
    
    /*#263 [T11] Add customer type at inquiry screen and export result CT 06062017*/
}
