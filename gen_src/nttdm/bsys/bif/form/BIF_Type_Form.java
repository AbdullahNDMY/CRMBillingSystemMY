/**
 * 
 */
package nttdm.bsys.bif.form;

import jp.terasoluna.fw.web.struts.form.ValidatorActionFormEx;

/**
 * @author duongnld
 *
 */
public class BIF_Type_Form extends ValidatorActionFormEx  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1767389687566963293L;

	private String idCust;
	private String idRef;
	private String idCustPlan;
	private String bifType;
	private String exportCurrency;
	private String currency;
	private String rateCurrency;
	private String cboBillingAddress;
	private String cboAttn;
	private String txtRequestDate;
	private String cboQCSReference;
	private String cboQuoReference;
	private String txtCustomerPO;
	private String cboConsultantName;
	private String cboJobNo;
	private String chkNewCustomer;
	private String txtRemarks;
	private String rdbInstructionf;
	private String rdbCreditTermAp;
	private String txtOthers;
	private String tbxComments;
	private String chkExportAmount;
	private String hidAction;
	
	/**
	 * @return the idCust
	 */
	public String getIdCust() {
		return idCust;
	}
	/**
	 * @param idCust the idCust to set
	 */
	public void setIdCust(String idCust) {
		this.idCust = idCust;
	}
	/**
	 * @return the idRef
	 */
	public String getIdRef() {
		return idRef;
	}
	/**
	 * @param idRef the idRef to set
	 */
	public void setIdRef(String idRef) {
		this.idRef = idRef;
	}
	/**
	 * @return the idCustPlan
	 */
	public String getIdCustPlan() {
		return idCustPlan;
	}
	/**
	 * @param idCustPlan the idCustPlan to set
	 */
	public void setIdCustPlan(String idCustPlan) {
		this.idCustPlan = idCustPlan;
	}
	/**
	 * @return the bifType
	 */
	public String getBifType() {
		return bifType;
	}
	/**
	 * @param bifType the bifType to set
	 */
	public void setBifType(String bifType) {
		this.bifType = bifType;
	}
	/**
	 * @return the exportCurrency
	 */
	public String getExportCurrency() {
		return exportCurrency;
	}
	/**
	 * @param exportCurrency the exportCurrency to set
	 */
	public void setExportCurrency(String exportCurrency) {
		this.exportCurrency = exportCurrency;
	}
	/**
	 * @return the rateCurrency
	 */
	public String getRateCurrency() {
		return rateCurrency;
	}
	/**
	 * @param rateCurrency the rateCurrency to set
	 */
	public void setRateCurrency(String rateCurrency) {
		this.rateCurrency = rateCurrency;
	}
	/**
	 * @return the cboBillingAddress
	 */
	public String getCboBillingAddress() {
		return cboBillingAddress;
	}
	/**
	 * @param cboBillingAddress the cboBillingAddress to set
	 */
	public void setCboBillingAddress(String cboBillingAddress) {
		this.cboBillingAddress = cboBillingAddress;
	}
	/**
	 * @return the cboAttn
	 */
	public String getCboAttn() {
		return cboAttn;
	}
	/**
	 * @param cboAttn the cboAttn to set
	 */
	public void setCboAttn(String cboAttn) {
		this.cboAttn = cboAttn;
	}
	/**
	 * @return the txtRequestDate
	 */
	public String getTxtRequestDate() {
		return txtRequestDate;
	}
	/**
	 * @param txtRequestDate the txtRequestDate to set
	 */
	public void setTxtRequestDate(String txtRequestDate) {
		this.txtRequestDate = txtRequestDate;
	}
	/**
	 * @return the cboQCSReference
	 */
	public String getCboQCSReference() {
		return cboQCSReference;
	}
	/**
	 * @param cboQCSReference the cboQCSReference to set
	 */
	public void setCboQCSReference(String cboQCSReference) {
		this.cboQCSReference = cboQCSReference;
	}
	/**
	 * @return the cboQuoReference
	 */
	public String getCboQuoReference() {
		return cboQuoReference;
	}
	/**
	 * @param cboQuoReference the cboQuoReference to set
	 */
	public void setCboQuoReference(String cboQuoReference) {
		this.cboQuoReference = cboQuoReference;
	}
	/**
	 * @return the txtCustomerPO
	 */
	public String getTxtCustomerPO() {
		return txtCustomerPO;
	}
	/**
	 * @param txtCustomerPO the txtCustomerPO to set
	 */
	public void setTxtCustomerPO(String txtCustomerPO) {
		this.txtCustomerPO = txtCustomerPO;
	}
	/**
	 * @return the cboConsultantName
	 */
	public String getCboConsultantName() {
		return cboConsultantName;
	}
	/**
	 * @param cboConsultantName the cboConsultantName to set
	 */
	public void setCboConsultantName(String cboConsultantName) {
		this.cboConsultantName = cboConsultantName;
	}
	/**
	 * @return the cboJobNo
	 */
	public String getCboJobNo() {
		return cboJobNo;
	}
	/**
	 * @param cboJobNo the cboJobNo to set
	 */
	public void setCboJobNo(String cboJobNo) {
		this.cboJobNo = cboJobNo;
	}
	/**
	 * @return the chkNewCustomer
	 */
	public String getChkNewCustomer() {
		return chkNewCustomer;
	}
	/**
	 * @param chkNewCustomer the chkNewCustomer to set
	 */
	public void setChkNewCustomer(String chkNewCustomer) {
		this.chkNewCustomer = chkNewCustomer;
	}
	/**
	 * @return the txtRemarks
	 */
	public String getTxtRemarks() {
		return txtRemarks;
	}
	/**
	 * @param txtRemarks the txtRemarks to set
	 */
	public void setTxtRemarks(String txtRemarks) {
		this.txtRemarks = txtRemarks;
	}
	/**
	 * @return the rdbInstructionf
	 */
	public String getRdbInstructionf() {
		return rdbInstructionf;
	}
	/**
	 * @param rdbInstructionf the rdbInstructionf to set
	 */
	public void setRdbInstructionf(String rdbInstructionf) {
		this.rdbInstructionf = rdbInstructionf;
	}
	/**
	 * @return the rdbCreditTermAp
	 */
	public String getRdbCreditTermAp() {
		return rdbCreditTermAp;
	}
	/**
	 * @param rdbCreditTermAp the rdbCreditTermAp to set
	 */
	public void setRdbCreditTermAp(String rdbCreditTermAp) {
		this.rdbCreditTermAp = rdbCreditTermAp;
	}
	/**
	 * @return the txtOthers
	 */
	public String getTxtOthers() {
		return txtOthers;
	}
	/**
	 * @param txtOthers the txtOthers to set
	 */
	public void setTxtOthers(String txtOthers) {
		this.txtOthers = txtOthers;
	}
	/**
	 * @return the tbxComments
	 */
	public String getTbxComments() {
		return tbxComments;
	}
	/**
	 * @param tbxComments the tbxComments to set
	 */
	public void setTbxComments(String tbxComments) {
		this.tbxComments = tbxComments;
	}
	/**
	 * @return the chkExportAmount
	 */
	public String getChkExportAmount() {
		return chkExportAmount;
	}
	/**
	 * @param chkExportAmount the chkExportAmount to set
	 */
	public void setChkExportAmount(String chkExportAmount) {
		this.chkExportAmount = chkExportAmount;
	}
	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * @param hidAction the hidAction to set
	 */
	public void setHidAction(String hidAction) {
		this.hidAction = hidAction;
	}
	/**
	 * @return the hidAction
	 */
	public String getHidAction() {
		return hidAction;
	}
}
