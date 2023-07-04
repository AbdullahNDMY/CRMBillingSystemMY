package nttdm.bsys.bif.dto;

import java.io.Serializable;

import org.apache.struts.upload.FormFile;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;

public class B_BIFS02_02Input implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String idCust = null;
	private String exportCurrency = null;
	private String billingCurrency = null;
	private String rateCurrency = null;
	private String idRef = null;
	private String idCustPlan = null;
	private String bifType = null;
	private BillingSystemUserValueObject uvo;
	private String cboBillingAddress = null;
	private String addr1 = null;
	private String addr2 = null;
	private String addr3 = null;
	private String zipCode = null;
	private String country = null;
	private String tel = null;
	private String fax = null;
	private String cboAttn = null;
	private String txtRequestDate = null;
	private String cboQCSReference = null;
	private String cboQuoReference = null;
	private String txtCustomerPO = null;
	private String cboConsultantName = null;
	private String cboJobNo = null;
	private String chkNewCustomer = null;
	private String txtRemarks = null;
	private String rdbInstructionf = null;
	private String rdbCreditTermAp = null;
	private String txtOthers = null;
	private String[] tbxComments = null;
	private String chkExportAmount = null;
	private FormFile[] listFileSCBif;
	private FormFile[] listFileQPBif;
	private FormFile[] listFileOTBif;
	private String custName;
	private String cboAttn_temp;
	private String countryCd;
	private String checkMultipleInOneInvoice = null;
	private String checkDeliveryEmail=null;
	private String txtOthersTady = null;
	private String emailToAdd = null;
	private String emailCcAdd = null;

	/**
	 * @return the txtOthersTady
	 */
	public String getTxtOthersTady() {
		return txtOthersTady;
	}

	/**
	 * @param txtOthersTady the txtOthersTady to set
	 */
	public void setTxtOthersTady(String txtOthersTady) {
		this.txtOthersTady = txtOthersTady;
	}

	public String getCboBillingAddress() {
		return cboBillingAddress;
	}

	public void setCboBillingAddress(String cboBillingAddress) {
		this.cboBillingAddress = cboBillingAddress;
	}

	public String getCboAttn() {
		return cboAttn;
	}

	public void setCboAttn(String cboAttn) {
		this.cboAttn = cboAttn;
	}

	public String getTxtRequestDate() {
		return txtRequestDate;
	}

	public void setTxtRequestDate(String txtRequestDate) {
		this.txtRequestDate = txtRequestDate;
	}

	public String getCboQCSReference() {
		return cboQCSReference;
	}

	public void setCboQCSReference(String cboQCSReference) {
		this.cboQCSReference = cboQCSReference;
	}

	public String getCboQuoReference() {
		return cboQuoReference;
	}

	public void setCboQuoReference(String cboQuoReference) {
		this.cboQuoReference = cboQuoReference;
	}

	public String getTxtCustomerPO() {
		return txtCustomerPO;
	}

	public void setTxtCustomerPO(String txtCustomerPO) {
		this.txtCustomerPO = txtCustomerPO;
	}

	public String getCboConsultantName() {
		return cboConsultantName;
	}

	public void setCboConsultantName(String cboConsultantName) {
		this.cboConsultantName = cboConsultantName;
	}

	public String getCboJobNo() {
		return cboJobNo;
	}

	public void setCboJobNo(String cboJobNo) {
		this.cboJobNo = cboJobNo;
	}

	public String getChkNewCustomer() {
		return chkNewCustomer;
	}

	public void setChkNewCustomer(String chkNewCustomer) {
		this.chkNewCustomer = chkNewCustomer;
	}

	public String getTxtRemarks() {
		return txtRemarks;
	}

	public void setTxtRemarks(String txtRemarks) {
		this.txtRemarks = txtRemarks;
	}

	public String getRdbInstructionf() {
		return rdbInstructionf;
	}

	public void setRdbInstructionf(String rdbInstructionf) {
		this.rdbInstructionf = rdbInstructionf;
	}

	public String getRdbCreditTermAp() {
		return rdbCreditTermAp;
	}

	public void setRdbCreditTermAp(String rdbCreditTermAp) {
		this.rdbCreditTermAp = rdbCreditTermAp;
	}

	public String getTxtOthers() {
		return txtOthers;
	}

	public void setTxtOthers(String txtOthers) {
		this.txtOthers = txtOthers;
	}

	public String getChkExportAmount() {
		return chkExportAmount;
	}

	public void setChkExportAmount(String chkExportAmount) {
		this.chkExportAmount = chkExportAmount;
	}

	public BillingSystemUserValueObject getUvo() {
		return uvo;
	}

	public void setUvo(BillingSystemUserValueObject uvo) {
		this.uvo = uvo;
	}

	public String[] getTbxComments() {
		return tbxComments;
	}

	public void setTbxComments(String[] tbxComments) {
		this.tbxComments = tbxComments;
	}

	public String getIdCust() {
		return idCust;
	}

	public void setIdCust(String idCust) {
		this.idCust = idCust;
	}

	public String getIdCustPlan() {
		return idCustPlan;
	}

	public void setIdCustPlan(String idCustPlan) {
		this.idCustPlan = idCustPlan;
	}

	public String getBifType() {
		return bifType;
	}

	public void setBifType(String bifType) {
		this.bifType = bifType;
	}

	public String getIdRef() {
		return idRef;
	}

	public void setIdRef(String idRef) {
		this.idRef = idRef;
	}

	public String getExportCurrency() {
		return exportCurrency;
	}

	public void setExportCurrency(String exportCurrency) {
		this.exportCurrency = exportCurrency;
	}

	public String getBillingCurrency() {
		return billingCurrency;
	}

	public void setBillingCurrency(String billingCurrency) {
		this.billingCurrency = billingCurrency;
	}

	public String getRateCurrency() {
		return rateCurrency;
	}

	public void setRateCurrency(String rateCurrency) {
		this.rateCurrency = rateCurrency;
	}

	public FormFile[] getListFileSCBif() {
		return listFileSCBif;
	}

	public void setListFileSCBif(FormFile[] listFileSCBif) {
		this.listFileSCBif = listFileSCBif;
	}

	public FormFile[] getListFileQPBif() {
		return listFileQPBif;
	}

	public void setListFileQPBif(FormFile[] listFileQPBif) {
		this.listFileQPBif = listFileQPBif;
	}

	public FormFile[] getListFileOTBif() {
		return listFileOTBif;
	}

	public void setListFileOTBif(FormFile[] listFileOTBif) {
		this.listFileOTBif = listFileOTBif;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getAddr2() {
		return addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}

	public String getAddr3() {
		return addr3;
	}

	public void setAddr3(String addr3) {
		this.addr3 = addr3;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

    /**
     * @return the custName
     */
    public String getCustName() {
        return custName;
    }

    /**
     * @param custName the custName to set
     */
    public void setCustName(String custName) {
        this.custName = custName;
    }

    /**
     * @return the cboAttn_temp
     */
    public String getCboAttn_temp() {
        return cboAttn_temp;
    }

    /**
     * @param cboAttn_temp the cboAttn_temp to set
     */
    public void setCboAttn_temp(String cboAttn_temp) {
        this.cboAttn_temp = cboAttn_temp;
    }

    /**
     * @return the countryCd
     */
    public String getCountryCd() {
        return countryCd;
    }

    /**
     * @param countryCd the countryCd to set
     */
    public void setCountryCd(String countryCd) {
        this.countryCd = countryCd;
    }

	public void setCheckMultipleInOneInvoice(String checkMultipleInOneInvoice) {
		this.checkMultipleInOneInvoice = checkMultipleInOneInvoice;
	}

	public String getCheckMultipleInOneInvoice() {
		return checkMultipleInOneInvoice;
	}
	
	/**
     * @return the checkDeliveryEmail
     */
    public String getCheckDeliveryEmail() {
        return checkDeliveryEmail;
    }
    
    /**
     * @param checkDeliveryEmail the checkDeliveryEmail to set
     */
    public void setCheckDeliveryEmail(String checkDeliveryEmail) {
        this.checkDeliveryEmail = checkDeliveryEmail;
    }

	/**
	 * @return the emailToAdd
	 */
	public String getEmailToAdd() {
		return emailToAdd;
	}

	/**
	 * @param emailToAdd the emailToAdd to set
	 */
	public void setEmailToAdd(String emailToAdd) {
		this.emailToAdd = emailToAdd;
	}

	/**
	 * @return the emailCcAdd
	 */
	public String getEmailCcAdd() {
		return emailCcAdd;
	}

	/**
	 * @param emailCcAdd the emailCcAdd to set
	 */
	public void setEmailCcAdd(String emailCcAdd) {
		this.emailCcAdd = emailCcAdd;
	}
}
