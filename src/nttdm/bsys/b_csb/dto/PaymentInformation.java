/**
 * @(#)PaymentInformation.java
 * 
 * Version 1.11
 * 
 * Created 2014-3-6
 */
package nttdm.bsys.b_csb.dto;

import java.math.BigDecimal;

/**
 * to store the data of payment information
 * 
 * @author loamanma
 * 
 */
public class PaymentInformation {

    // Applied To
    private String appliedTo;

    // csb_h Receipt Date
    private String receiptDate;

    // csb_h Receipt No
    private String receiptNo = "";

    // csb_h currency
    private String currency;

    // Amount Received
    private BigDecimal amountReceived = BigDecimal.ZERO;

    // Balance Amount
    private BigDecimal balanceAmount = BigDecimal.ZERO;

    private String refundAmount = "0";

    public String getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(String refundAmount) {
        this.refundAmount = refundAmount;
    }

    public String getAppliedTo() {
        return appliedTo;
    }

    public void setAppliedTo(String appliedTo) {
        this.appliedTo = appliedTo;
    }

    public String getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(String receiptDate) {
        this.receiptDate = receiptDate;
    }

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getAmountReceived() {
        return amountReceived;
    }

    public void setAmountReceived(BigDecimal amountReceived) {
        this.amountReceived = amountReceived;
    }

    public BigDecimal getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(BigDecimal balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    @Override
    public int hashCode() {
        return getReceiptNo().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof PaymentInformation) {
            PaymentInformation pi = (PaymentInformation) obj;

            if (this.receiptNo.trim().equals(pi.getReceiptNo().trim())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
