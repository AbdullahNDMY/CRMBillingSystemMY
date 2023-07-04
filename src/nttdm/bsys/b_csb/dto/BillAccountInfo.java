/**
 * @(#)BillAccountInfo.java
 * Version 1.00
 * 
 * Created 2014-3-10
 * 
 */
package nttdm.bsys.b_csb.dto;

/**
 * @author loamanma
 * 
 */
public class BillAccountInfo {

    private String custName;
    private String idCust;
    private String idBillAccount;
    private String paymentMethod;
    private String billCurrency;
    private String totalAmtDue;

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getIdCust() {
        return idCust;
    }

    public void setIdCust(String idCust) {
        this.idCust = idCust;
    }

    public String getIdBillAccount() {
        return idBillAccount;
    }

    public void setIdBillAccount(String idBillAccount) {
        this.idBillAccount = idBillAccount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getBillCurrency() {
        return billCurrency;
    }

    public void setBillCurrency(String billCurrency) {
        this.billCurrency = billCurrency;
    }

    public String getTotalAmtDue() {
        return totalAmtDue;
    }

    public void setTotalAmtDue(String totalAmtDue) {
        this.totalAmtDue = totalAmtDue;
    }

}
