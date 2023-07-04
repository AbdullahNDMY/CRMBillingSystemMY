package nttdm.bsys.b_csb.bean;

import java.math.BigDecimal;
import java.util.List;

import jp.terasoluna.fw.web.struts.form.ValidatorActionFormEx;
import nttdm.bsys.b_csb.dto.BillAccountInfo;
import nttdm.bsys.b_csb.dto.PaymentInformation;
import nttdm.bsys.common.util.dto.AutoScaleList;

import org.apache.struts.util.LabelValueBean;

/**
 * Refund form for B_CSB_S03
 * 
 * 
 * @author loamanma
 * 
 */
public class B_CSB_S03FormBean extends ValidatorActionFormEx {

    private static final long serialVersionUID = 1L;

    // id bill account no from b_bac_s02v
    private String idBillAccount;

    // receipt No
    private String receiptNo;

    // Transaction Date
    private String transDate;

    // Amount Refunded
    private String amtRefunded;

    // bank account
    private String bankAcc;

    // Remark
    private String remark;

    // Refund Reference
    private String refundRefernece;

    // Action Flg new or edit
    private String actionFlg;

    // bill accout information
    private BillAccountInfo billAccountInfo = new BillAccountInfo();

    // back information
    private List<LabelValueBean> cbBankAccList;

    // Payment Information
    private List<PaymentInformation> paymentInformationList = new AutoScaleList<PaymentInformation>(new PaymentInformation());

    // total amount receive
    private BigDecimal totalAmoutReceive = BigDecimal.ZERO;

    // total balance amount
    private BigDecimal totalBalanceAmount = BigDecimal.ZERO;

    // total refund amount
    private BigDecimal totalRefundAmount = BigDecimal.ZERO;

    // for view page edit and delete access
    private String isDisplay;
    
    private String fromPage;

    public String getFromPage() {
        return fromPage;
    }

    public void setFromPage(String fromPage) {
        this.fromPage = fromPage;
    }

    public String getIsDisplay() {
        return isDisplay;
    }

    public void setIsDisplay(String isDisplay) {
        this.isDisplay = isDisplay;
    }

    public String getActionFlg() {
        return actionFlg;
    }

    public void setActionFlg(String actionFlg) {
        this.actionFlg = actionFlg;
    }

    public String getIdBillAccount() {
        return idBillAccount;
    }

    public void setIdBillAccount(String idBillAccount) {
        this.idBillAccount = idBillAccount;
    }

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    public String getTransDate() {
        return transDate;
    }

    public void setTransDate(String transDate) {
        this.transDate = transDate;
    }

    public String getAmtRefunded() {
        return amtRefunded;
    }

    public void setAmtRefunded(String amtRefunded) {
        this.amtRefunded = amtRefunded;
    }

    public String getBankAcc() {
        return bankAcc;
    }

    public void setBankAcc(String bankAcc) {
        this.bankAcc = bankAcc;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRefundRefernece() {
        return refundRefernece;
    }

    public void setRefundRefernece(String refundRefernece) {
        this.refundRefernece = refundRefernece;
    }

    public BillAccountInfo getBillAccountInfo() {
        return billAccountInfo;
    }

    public void setBillAccountInfo(BillAccountInfo billAccountInfo) {
        this.billAccountInfo = billAccountInfo;
    }

    public List<LabelValueBean> getCbBankAccList() {
        return cbBankAccList;
    }

    public void setCbBankAccList(List<LabelValueBean> cbBankAccList) {
        this.cbBankAccList = cbBankAccList;
    }

    public List<PaymentInformation> getPaymentInformationList() {
        return paymentInformationList;
    }

    public void setPaymentInformationList(List<PaymentInformation> paymentInformationList) {
        this.paymentInformationList = paymentInformationList;
    }

    public BigDecimal getTotalAmoutReceive() {
        return totalAmoutReceive;
    }

    public void setTotalAmoutReceive(BigDecimal totalAmoutReceive) {
        this.totalAmoutReceive = totalAmoutReceive;
    }

    public BigDecimal getTotalBalanceAmount() {
        return totalBalanceAmount;
    }

    public void setTotalBalanceAmount(BigDecimal totalBalanceAmount) {
        this.totalBalanceAmount = totalBalanceAmount;
    }

    public BigDecimal getTotalRefundAmount() {
        return totalRefundAmount;
    }

    public void setTotalRefundAmount(BigDecimal totalRefundAmount) {
        this.totalRefundAmount = totalRefundAmount;
    }

}
