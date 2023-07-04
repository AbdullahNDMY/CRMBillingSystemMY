/*
 * @(#)B_BIFS02Input.java
 *
 *
 */
package nttdm.bsys.bif.dto;

import java.io.Serializable;
import java.util.Map;

import org.apache.struts.upload.FormFile;

/**
 * 入力DTOクラス。
 * 
 * @author duongnld
 */
public class B_BIFS02Input extends B_BIF_Input implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3331146843477660919L;

	public static final String ID_SCREEN = "BIFIDN";
	
	private String is_ObtainA;
	private String is_ObtainV;
	
	private String deletedAttachmentSC = null;
	private String deletedAttachmentQP = null;
	private String deletedAttachmentOT = null;
	private FormFile[] listFileSCBif;
	private FormFile[] listFileQPBif;
	private FormFile[] listFileOTBif;
	
	//approval comment information
	private String isRevised;
	private String apprComment;
	private String apprStatus;
	
	//customer relation information
	private String idWfApproval;
	private String isSave;
	private String sectionNo;
	private String sectionGroup;
	private String bilOpBy;
	private String dateBifRcv;
	private String bilRefNo;
	private String datePrinted;
	private String dateSigned;
	private String customerAccount;
	
	private String actionR;
	private String idRefR;
	
	private String cboBillingAddress = null;
	private String cboAttn = null;
	private String txtRequestDate = null;
	private String cboQCSReference = null;
	private String cboQuoReference = null;
	private String txtCustomerPO = null;
	private String cboConsultantName = null;
	private String preCboConsultantName = null;
	private String cboJobNo = null;
	private String chkNewCustomer = null;
	private String txtRemarks = null;
	private String rdbInstructionf = null;
	private String rdbCreditTermAp = null;
	private String txtOthers = null;
	private String[] tbxComments = null;
	private String chkExportAmount = null;
	private String rateCurrency = null;
	
	private String customerACNo = null;
	private String chkIDC = null;
	private String chkGIN = null;
	private String chkSI = null;
	private String chkCorporate = null;
	private String chkOthers = null;
	private String txtActivation = null;
	private Map<String, Object> pagAction;
	
	private String checkMultipleInOneInvoice = null;
	private String checkDeliveryEmail=null;
	
	private String custName;
    private String cboAttn_temp;
    private String countryCd;
    private String txtOthersTady;
    
    private String pictureId;
    
    private String emailToAdd = null;
    private String emailCcAdd = null;
    
	/**
	 * @return the pictureId
	 */
	public String getPictureId() {
		return pictureId;
	}
	/**
	 * @param pictureId the pictureId to set
	 */
	public void setPictureId(String pictureId) {
		this.pictureId = pictureId;
	}
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
	/**
	 * @return the is_ObtainA
	 */
	public String getIs_ObtainA() {
		return is_ObtainA;
	}
	/**
	 * @param is_ObtainA the is_ObtainA to set
	 */
	public void setIs_ObtainA(String is_ObtainA) {
		this.is_ObtainA = is_ObtainA;
	}
	/**
	 * @return the is_ObtainV
	 */
	public String getIs_ObtainV() {
		return is_ObtainV;
	}
	/**
	 * @param is_ObtainV the is_ObtainV to set
	 */
	public void setIs_ObtainV(String is_ObtainV) {
		this.is_ObtainV = is_ObtainV;
	}
	/**
	 * @return the deletedAttachmentSC
	 */
	public String getDeletedAttachmentSC() {
		return deletedAttachmentSC;
	}
	/**
	 * @param deletedAttachmentSC the deletedAttachmentSC to set
	 */
	public void setDeletedAttachmentSC(String deletedAttachmentSC) {
		this.deletedAttachmentSC = deletedAttachmentSC;
	}
	/**
	 * @return the deletedAttachmentQP
	 */
	public String getDeletedAttachmentQP() {
		return deletedAttachmentQP;
	}
	/**
	 * @param deletedAttachmentQP the deletedAttachmentQP to set
	 */
	public void setDeletedAttachmentQP(String deletedAttachmentQP) {
		this.deletedAttachmentQP = deletedAttachmentQP;
	}
	/**
	 * @return the deletedAttachmentOT
	 */
	public String getDeletedAttachmentOT() {
		return deletedAttachmentOT;
	}
	/**
	 * @param deletedAttachmentOT the deletedAttachmentOT to set
	 */
	public void setDeletedAttachmentOT(String deletedAttachmentOT) {
		this.deletedAttachmentOT = deletedAttachmentOT;
	}
	/**
	 * @return the listFileSCBif
	 */
	public FormFile[] getListFileSCBif() {
		return listFileSCBif;
	}
	/**
	 * @param listFileSCBif the listFileSCBif to set
	 */
	public void setListFileSCBif(FormFile[] listFileSCBif) {
		this.listFileSCBif = listFileSCBif;
	}
	/**
	 * @return the listFileQPBif
	 */
	public FormFile[] getListFileQPBif() {
		return listFileQPBif;
	}
	/**
	 * @param listFileQPBif the listFileQPBif to set
	 */
	public void setListFileQPBif(FormFile[] listFileQPBif) {
		this.listFileQPBif = listFileQPBif;
	}
	/**
	 * @return the listFileOTBif
	 */
	public FormFile[] getListFileOTBif() {
		return listFileOTBif;
	}
	/**
	 * @param listFileOTBif the listFileOTBif to set
	 */
	public void setListFileOTBif(FormFile[] listFileOTBif) {
		this.listFileOTBif = listFileOTBif;
	}
	/**
	 * @return the isRevised
	 */
	public String getIsRevised() {
		return isRevised;
	}
	/**
	 * @param isRevised the isRevised to set
	 */
	public void setIsRevised(String isRevised) {
		this.isRevised = isRevised;
	}
	/**
	 * @return the apprComment
	 */
	public String getApprComment() {
		return apprComment;
	}
	/**
	 * @param apprComment the apprComment to set
	 */
	public void setApprComment(String apprComment) {
		this.apprComment = apprComment;
	}
	/**
	 * @return the apprStatus
	 */
	public String getApprStatus() {
		return apprStatus;
	}
	/**
	 * @param apprStatus the apprStatus to set
	 */
	public void setApprStatus(String apprStatus) {
		this.apprStatus = apprStatus;
	}
	/**
	 * @return the idWfApproval
	 */
	public String getIdWfApproval() {
		return idWfApproval;
	}
	/**
	 * @param idWfApproval the idWfApproval to set
	 */
	public void setIdWfApproval(String idWfApproval) {
		this.idWfApproval = idWfApproval;
	}
	/**
	 * @return the isSave
	 */
	public String getIsSave() {
		return isSave;
	}
	/**
	 * @param isSave the isSave to set
	 */
	public void setIsSave(String isSave) {
		this.isSave = isSave;
	}
	/**
	 * @return the sectionNo
	 */
	public String getSectionNo() {
		return sectionNo;
	}
	/**
	 * @param sectionNo the sectionNo to set
	 */
	public void setSectionNo(String sectionNo) {
		this.sectionNo = sectionNo;
	}
	/**
	 * @return the sectionGroup
	 */
	public String getSectionGroup() {
		return sectionGroup;
	}
	/**
	 * @param sectionGroup the sectionGroup to set
	 */
	public void setSectionGroup(String sectionGroup) {
		this.sectionGroup = sectionGroup;
	}
	/**
	 * @return the bilOpBy
	 */
	public String getBilOpBy() {
		return bilOpBy;
	}
	/**
	 * @param bilOpBy the bilOpBy to set
	 */
	public void setBilOpBy(String bilOpBy) {
		this.bilOpBy = bilOpBy;
	}
	/**
	 * @return the dateBifRcv
	 */
	public String getDateBifRcv() {
		return dateBifRcv;
	}
	/**
	 * @param dateBifRcv the dateBifRcv to set
	 */
	public void setDateBifRcv(String dateBifRcv) {
		this.dateBifRcv = dateBifRcv;
	}
	/**
	 * @return the bilRefNo
	 */
	public String getBilRefNo() {
		return bilRefNo;
	}
	/**
	 * @param bilRefNo the bilRefNo to set
	 */
	public void setBilRefNo(String bilRefNo) {
		this.bilRefNo = bilRefNo;
	}
	/**
	 * @return the datePrinted
	 */
	public String getDatePrinted() {
		return datePrinted;
	}
	/**
	 * @param datePrinted the datePrinted to set
	 */
	public void setDatePrinted(String datePrinted) {
		this.datePrinted = datePrinted;
	}
	/**
	 * @return the dateSigned
	 */
	public String getDateSigned() {
		return dateSigned;
	}
	/**
	 * @param dateSigned the dateSigned to set
	 */
	public void setDateSigned(String dateSigned) {
		this.dateSigned = dateSigned;
	}
	/**
	 * @return the customerAccount
	 */
	public String getCustomerAccount() {
		return customerAccount;
	}
	/**
	 * @param customerAccount the customerAccount to set
	 */
	public void setCustomerAccount(String customerAccount) {
		this.customerAccount = customerAccount;
	}
	/**
	 * @return the actionR
	 */
	public String getActionR() {
		return actionR;
	}
	/**
	 * @param actionR the actionR to set
	 */
	public void setActionR(String actionR) {
		this.actionR = actionR;
	}
	/**
	 * @return the idRefR
	 */
	public String getIdRefR() {
		return idRefR;
	}
	/**
	 * @param idRefR the idRefR to set
	 */
	public void setIdRefR(String idRefR) {
		this.idRefR = idRefR;
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
	public String[] getTbxComments() {
		return tbxComments;
	}
	/**
	 * @param tbxComments the tbxComments to set
	 */
	public void setTbxComments(String[] tbxComments) {
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
	 * @param rateCurrency the rateCurrency to set
	 */
	public void setRateCurrency(String rateCurrency) {
		this.rateCurrency = rateCurrency;
	}
	/**
	 * @return the rateCurrency
	 */
	public String getRateCurrency() {
		return rateCurrency;
	}
	/**
	 * @return the customerACNo
	 */
	public String getCustomerACNo() {
		return customerACNo;
	}
	/**
	 * @param customerACNo the customerACNo to set
	 */
	public void setCustomerACNo(String customerACNo) {
		this.customerACNo = customerACNo;
	}
	/**
	 * @return the chkIDC
	 */
	public String getChkIDC() {
		return chkIDC;
	}
	/**
	 * @param chkIDC the chkIDC to set
	 */
	public void setChkIDC(String chkIDC) {
		this.chkIDC = chkIDC;
	}
	/**
	 * @return the chkGIN
	 */
	public String getChkGIN() {
		return chkGIN;
	}
	/**
	 * @param chkGIN the chkGIN to set
	 */
	public void setChkGIN(String chkGIN) {
		this.chkGIN = chkGIN;
	}
	/**
	 * @return the chkSI
	 */
	public String getChkSI() {
		return chkSI;
	}
	/**
	 * @param chkSI the chkSI to set
	 */
	public void setChkSI(String chkSI) {
		this.chkSI = chkSI;
	}
	/**
	 * @return the chkCorporate
	 */
	public String getChkCorporate() {
		return chkCorporate;
	}
	/**
	 * @param chkCorporate the chkCorporate to set
	 */
	public void setChkCorporate(String chkCorporate) {
		this.chkCorporate = chkCorporate;
	}
	/**
	 * @return the chkOthers
	 */
	public String getChkOthers() {
		return chkOthers;
	}
	/**
	 * @param chkOthers the chkOthers to set
	 */
	public void setChkOthers(String chkOthers) {
		this.chkOthers = chkOthers;
	}
	/**
	 * @return the txtActivation
	 */
	public String getTxtActivation() {
		return txtActivation;
	}
	/**
	 * @param txtActivation the txtActivation to set
	 */
	public void setTxtActivation(String txtActivation) {
		this.txtActivation = txtActivation;
	}
	public Map<String, Object> getPagAction() {
		return pagAction;
	}
	public void setPagAction(Map<String, Object> pagAction) {
		this.pagAction = pagAction;
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
    /**
     * @return the preCboConsultantName
     */
    public String getPreCboConsultantName() {
        return preCboConsultantName;
    }
    /**
     * @param preCboConsultantName the preCboConsultantName to set
     */
    public void setPreCboConsultantName(String preCboConsultantName) {
        this.preCboConsultantName = preCboConsultantName;
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