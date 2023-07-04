package nttdm.bsys.b_trm.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.web.struts.form.ValidatorActionFormEx;
import nttdm.bsys.common.util.dto.AutoScaleList;

import org.apache.struts.util.LabelValueBean;

public class B_TRM_FormBean extends ValidatorActionFormEx {

    private static final long serialVersionUID = 1L;

    private String action;

    private String customer;

    private List<LabelValueBean> cbCustomer;

    private String billAccount;

    private List<LabelValueBean> cbBillAccount = new AutoScaleList<LabelValueBean>(
            new LabelValueBean());

    private String creditRef;

    private List<LabelValueBean> cbCreditRef = new AutoScaleList<LabelValueBean>(
            new LabelValueBean());

    private String date;

    private String currency;

    private Object origAmt;

    private Object creditBalance;

    private List<DebitInfoBean> debitInfos = new AutoScaleList<DebitInfoBean>(
            new DebitInfoBean());

    // search page
    private Integer startIndex;

    private Integer row;

    private Integer totalRow;

    private String cusName;
    
    //#263 [T11] Add customer type at inquiry screen and export result CT 06062017
    private String customerType;
    //#263 [T11] Add customer type at inquiry screen and export result CT 06062017

    private String creditReference;

    private String debitReference;

    private String startDate;

    private String endDate;

    private String gblSetValue;

    private List<Map<String, Object>> searchResult = new ArrayList<Map<String, Object>>();

    private String transationDate = "";

    private String cboCustomerName = "";

    private String cusID = "";
    private String billAccountNo = "";
    private String billCurrency = "";
    private String lastMsg;

    public String getBillAccountNo() {
        return billAccountNo == null ? "" : billAccountNo;
    }

    public void setBillAccountNo(String billAccountNo) {
        this.billAccountNo = billAccountNo;
    }

    public String getBillCurrency() {
        return billCurrency == null ? "" : billCurrency;
    }

    public void setBillCurrency(String billCurrency) {
        this.billCurrency = billCurrency;
    }

    public String getCusID() {
        return cusID == null ? "" : cusID;
    }

    public void setCusID(String cusID) {
        this.cusID = cusID;
    }

    public String getCboCustomerName() {
        return cboCustomerName;
    }

    public void setCboCustomerName(String cboCustomerName) {
        this.cboCustomerName = cboCustomerName;
    }

    public String getTransationDate() {
        return transationDate;
    }

    public void setTransationDate(String transationDate) {
        this.transationDate = transationDate;
    }

    /**
     * special getter, setter
     */
    public DebitInfoBean getDebitInfo(int index) {
        if (debitInfos.isEmpty())
            return new DebitInfoBean();
        return debitInfos.get(index);
    }

    /**
     * default getter, setter
     */

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public List<LabelValueBean> getCbCustomer() {
        return cbCustomer;
    }

    public void setCbCustomer(List<LabelValueBean> cbCustomer) {
        this.cbCustomer = cbCustomer;
    }

    public String getBillAccount() {
        return billAccount;
    }

    public void setBillAccount(String billAccount) {
        this.billAccount = billAccount;
    }

    public List<LabelValueBean> getCbBillAccount() {
        return cbBillAccount;
    }

    public void setCbBillAccount(List<LabelValueBean> cbBillAccount) {
        this.cbBillAccount = cbBillAccount;
    }

    public String getCreditRef() {
        return creditRef;
    }

    public void setCreditRef(String creditRef) {
        this.creditRef = creditRef;
    }

    public List<LabelValueBean> getCbCreditRef() {
        return cbCreditRef;
    }

    public void setCbCreditRef(List<LabelValueBean> cbCreditRef) {
        this.cbCreditRef = cbCreditRef;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Object getOrigAmt() {
        return origAmt;
    }

    public void setOrigAmt(Object origAmt) {
        this.origAmt = origAmt;
    }

    public Object getCreditBalance() {
        return creditBalance;
    }

    public void setCreditBalance(Object creditBalance) {
        this.creditBalance = creditBalance;
    }

    public List<DebitInfoBean> getDebitInfos() {
        return debitInfos;
    }

    public void setDebitInfos(List<DebitInfoBean> debitInfos) {
        this.debitInfos = debitInfos;
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

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getCreditReference() {
        return creditReference;
    }

    public void setCreditReference(String creditReference) {
        this.creditReference = creditReference;
    }

    public String getDebitReference() {
        return debitReference;
    }

    public void setDebitReference(String debitReference) {
        this.debitReference = debitReference;
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

    public List<Map<String, Object>> getSearchResult() {
        return searchResult;
    }

    public void setSearchResult(List<Map<String, Object>> searchResult) {
        this.searchResult = searchResult;
    }

    public void setGblSetValue(String gblSetValue) {
        this.gblSetValue = gblSetValue;
    }

    public String getGblSetValue() {
        return gblSetValue;
    }

    /**
     * @return the lastMsg
     */
    public String getLastMsg() {
        return lastMsg;
    }

    /**
     * @param lastMsg the lastMsg to set
     */
    public void setLastMsg(String lastMsg) {
        this.lastMsg = lastMsg;
    }
    
    /*#263 [T11] Add customer type at inquiry screen and export result CT 06062017*/
    public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
    /*#263 [T11] Add customer type at inquiry screen and export result CT 06062017*/
}