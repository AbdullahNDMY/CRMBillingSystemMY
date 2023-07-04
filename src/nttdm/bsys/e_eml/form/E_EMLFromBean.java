package nttdm.bsys.e_eml.form;

import java.util.List;

import jp.terasoluna.fw.web.struts.form.ValidatorActionFormEx;
import nttdm.bsys.e_eml.bean.E_EML01Bean;

public class E_EMLFromBean extends ValidatorActionFormEx{
	
	private static final long serialVersionUID = 5471385394462378902L;
	private String billDocuNo;
	private String customerName;
	private String billAcountNo;
	private String transaction;
	private String batchId;
	private Integer totalCount=-1;
	private Integer startIndex=0;
	private Integer row=0;
	private String start_docuDate;
	private String end_docuDate;
	private String start_emailDate;
	private String end_emailDate;
	private String[] pdfGen;
	private String[] emailSend;
	private String[] sameEmail;
	private String nonTaxInvoiceShowFlg;
	private List<E_EML01Bean>billInfo;
	private String[] idRefs;
	private String allIdRefs;
	private String[] deliveryEmail;
	private String[] peopleAccNo;
	private String pdfFilename;
	private String pdfZipFile;
	private String idCust;
	/**
	 * @return the billDocuNo
	 */
	public String getBillDocuNo() {
		return billDocuNo;
	}
	/**
	 * @param billDocuNo the billDocuNo to set
	 */
	public void setBillDocuNo(String billDocuNo) {
		this.billDocuNo = billDocuNo;
	}
	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}
	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	/**
	 * @return the billAcountNo
	 */
	public String getBillAcountNo() {
		return billAcountNo;
	}
	/**
	 * @param billAcountNo the billAcountNo to set
	 */
	public void setBillAcountNo(String billAcountNo) {
		this.billAcountNo = billAcountNo;
	}
	/**
	 * @return the transaction
	 */
	public String getTransaction() {
		return transaction;
	}
	/**
	 * @param transaction the transaction to set
	 */
	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}
	/**
	 * @return the batchId
	 */
	public String getBatchId() {
		return batchId;
	}
	/**
	 * @param batchId the batchId to set
	 */
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	/**
	 * @return the totalCount
	 */
	public Integer getTotalCount() {
		return totalCount;
	}
	/**
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	/**
	 * @return the startIndex
	 */
	public Integer getStartIndex() {
		return startIndex;
	}
	/**
	 * @param startIndex the startIndex to set
	 */
	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}
	/**
	 * @return the row
	 */
	public Integer getRow() {
		return row;
	}
	/**
	 * @param row the row to set
	 */
	public void setRow(Integer row) {
		this.row = row;
	}
	/**
	 * @return the start_docuDate
	 */
	public String getStart_docuDate() {
		return start_docuDate;
	}
	/**
	 * @param start_docuDate the start_docuDate to set
	 */
	public void setStart_docuDate(String start_docuDate) {
		this.start_docuDate = start_docuDate;
	}
	/**
	 * @return the end_docuDate
	 */
	public String getEnd_docuDate() {
		return end_docuDate;
	}
	/**
	 * @param end_docuDate the end_docuDate to set
	 */
	public void setEnd_docuDate(String end_docuDate) {
		this.end_docuDate = end_docuDate;
	}
	/**
	 * @return the start_emailDate
	 */
	public String getStart_emailDate() {
		return start_emailDate;
	}
	/**
	 * @param start_emailDate the start_emailDate to set
	 */
	public void setStart_emailDate(String start_emailDate) {
		this.start_emailDate = start_emailDate;
	}
	/**
	 * @return the end_emailDate
	 */
	public String getEnd_emailDate() {
		return end_emailDate;
	}
	/**
	 * @param end_emailDate the end_emailDate to set
	 */
	public void setEnd_emailDate(String end_emailDate) {
		this.end_emailDate = end_emailDate;
	}
	/**
	 * @return the nonTaxInvoiceShowFlg
	 */
	public String getNonTaxInvoiceShowFlg() {
		return nonTaxInvoiceShowFlg;
	}
	/**
	 * @param nonTaxInvoiceShowFlg the nonTaxInvoiceShowFlg to set
	 */
	public void setNonTaxInvoiceShowFlg(String nonTaxInvoiceShowFlg) {
		this.nonTaxInvoiceShowFlg = nonTaxInvoiceShowFlg;
	}
	/**
	 * @return the pdfGen
	 */
	public String[] getPdfGen() {
		return pdfGen;
	}
	/**
	 * @param pdfGen the pdfGen to set
	 */
	public void setPdfGen(String[] pdfGen) {
		this.pdfGen = pdfGen;
	}
	/**
	 * @return the emailSend
	 */
	public String[] getEmailSend() {
		return emailSend;
	}
	/**
	 * @param emailSend the emailSend to set
	 */
	public void setEmailSend(String[] emailSend) {
		this.emailSend = emailSend;
	}
	/**
	 * @return the sameEmail
	 */
	public String[] getSameEmail() {
		return sameEmail;
	}
	/**
	 * @param sameEmail the sameEmail to set
	 */
	public void setSameEmail(String[] sameEmail) {
		this.sameEmail = sameEmail;
	}
	/**
	 * @return the billInfo
	 */
	public List<E_EML01Bean> getBillInfo() {
		return billInfo;
	}
	/**
	 * @param billInfo the billInfo to set
	 */
	public void setBillInfo(List<E_EML01Bean> billInfo) {
		this.billInfo = billInfo;
	}
	/**
	 * @return the idRefs
	 */
	public String[] getIdRefs() {
		return idRefs;
	}
	/**
	 * @param idRefs the idRefs to set
	 */
	public void setIdRefs(String[] idRefs) {
		this.idRefs = idRefs;
	}
	/**
	 * @return the allIdRefs
	 */
	public String getAllIdRefs() {
		return allIdRefs;
	}
	/**
	 * @param allIdRefs the allIdRefs to set
	 */
	public void setAllIdRefs(String allIdRefs) {
		this.allIdRefs = allIdRefs;
	}
	/**
	 * @return the deliveryEmail
	 */
	public String[] getDeliveryEmail() {
		return deliveryEmail;
	}
	/**
	 * @param deliveryEmail the deliveryEmail to set
	 */
	public void setDeliveryEmail(String[] deliveryEmail) {
		this.deliveryEmail = deliveryEmail;
	}
	/**
	 * @return the peopleAccNo
	 */
	public String[] getPeopleAccNo() {
		return peopleAccNo;
	}
	/**
	 * @param peopleAccNo the peopleAccNo to set
	 */
	public void setPeopleAccNo(String[] peopleAccNo) {
		this.peopleAccNo = peopleAccNo;
	}
	/**
	 * @return the pdfFilename
	 */
	public String getPdfFilename() {
		return pdfFilename;
	}
	/**
	 * @param pdfFilename the pdfFilename to set
	 */
	public void setPdfFilename(String pdfFilename) {
		this.pdfFilename = pdfFilename;
	}
	/**
	 * @return the pdfZipFile
	 */
	public String getPdfZipFile() {
		return pdfZipFile;
	}
	/**
	 * @param pdfZipFile the pdfZipFile to set
	 */
	public void setPdfZipFile(String pdfZipFile) {
		this.pdfZipFile = pdfZipFile;
	}
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
}
