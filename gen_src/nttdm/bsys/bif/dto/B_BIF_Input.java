/**
 * 
 */
package nttdm.bsys.bif.dto;

import java.io.Serializable;
import java.util.Map;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;

/**
 * @author duongnld
 *
 */
public class B_BIF_Input implements Serializable {
	
	public static final String ID_SCREEN = "BIF";
	
	public static final String FORWARD_ACTION = "forward";
	public static final String REJECT_ACTION = "reject";
	public static final String OBTAIN_VERIFICATION_ACTION = "obtain verification";
	public static final String OBTAIN_APPROVAL_ACTION = "obtain approval";
	
	public static final String NEW_ACTION = "new";
	public static final String EDIT_ACTION = "edit";
	public static final String EDIT2_ACTION = "edit2";
	public static final String DELETE_ACTION = "delete";
	public static final String VIEW_ACTION = "view";
	public static final String SECTION_ACTION = "section";
	public static final String APPROVAL_ACTION = "approval";
	public static final String CLOSE_ACTION = "close";
	
	public static final String APPROVED_ACTION = "approved";
	public static final String REJECTED_ACTION = "rejected";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5481496301054718304L;
	private String idRef; 
	private String idCust;
	private String idCustPlan;
	private String bifType;
	
	private String billingAddress; //cboBillingAddress
	private String attn; //cboAttn
	private String requestDate; //txtRequestDate
	private String QCSReference;//cboQCSReference
	private String quoReference;//cboQuoReference
	private String customer;//txtCustomerPO
	private String consultantName;//cboConsultantName
	private String preCboConsultantName;//cboConsultantName
	private String jobNo;//cboJobNo
	private String instruction;//rdbInstructionf
	private String creditNoteType;//rdbCreditNoteType
	private String others;//txtOthers
	private String remarks;//txtRemarks
	private String exportAmount;
	private String exportCurrency;
	private String billingCurrency;
	private String userComment;
	private Map<String, Object> screenObject;
	private BillingSystemUserValueObject uvo;
	private String addr1;
	private String addr2;
	private String addr3;
	private String zipCode;
	private String country;
	private String tel;
	private String fax;
	
	private String action;
	
	private String message;
	private String messageParameter;
	
	private String custName;
    private String cboAttn_temp;
    private String countryCd;
    
    private String checkMultipleInOneInvoice;
    private String checkDeliveryEmail;
    
    private String emailToAdd;
    private String emailCcAdd;
    
    
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
	 * @return the billingAddress
	 */
	public String getBillingAddress() {
		return billingAddress;
	}
	/**
	 * @param billingAddress the billingAddress to set
	 */
	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}
	/**
	 * @return the attn
	 */
	public String getAttn() {
		return attn;
	}
	/**
	 * @param attn the attn to set
	 */
	public void setAttn(String attn) {
		this.attn = attn;
	}
	/**
	 * @return the requestDate
	 */
	public String getRequestDate() {
		return requestDate;
	}
	/**
	 * @param requestDate the requestDate to set
	 */
	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}
	/**
	 * @return the qCSReference
	 */
	public String getQCSReference() {
		return QCSReference;
	}
	/**
	 * @param reference the qCSReference to set
	 */
	public void setQCSReference(String reference) {
		QCSReference = reference;
	}
	/**
	 * @return the quoReference
	 */
	public String getQuoReference() {
		return quoReference;
	}
	/**
	 * @param quoReference the quoReference to set
	 */
	public void setQuoReference(String quoReference) {
		this.quoReference = quoReference;
	}
	/**
	 * @return the customer
	 */
	public String getCustomer() {
		return customer;
	}
	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	/**
	 * @return the consultantName
	 */
	public String getConsultantName() {
		return consultantName;
	}
	/**
	 * @param consultantName the consultantName to set
	 */
	public void setConsultantName(String consultantName) {
		this.consultantName = consultantName;
	}
	/**
	 * @return the jobNo
	 */
	public String getJobNo() {
		return jobNo;
	}
	/**
	 * @param jobNo the jobNo to set
	 */
	public void setJobNo(String jobNo) {
		this.jobNo = jobNo;
	}
	/**
	 * @return the instruction
	 */
	public String getInstruction() {
		return instruction;
	}
	/**
	 * @param instruction the instruction to set
	 */
	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}
	/**
	 * @return the creditNoteType
	 */
	public String getCreditNoteType() {
		return creditNoteType;
	}
	/**
	 * @param creditNoteType the creditNoteType to set
	 */
	public void setCreditNoteType(String creditNoteType) {
		this.creditNoteType = creditNoteType;
	}
	/**
	 * @return the others
	 */
	public String getOthers() {
		return others;
	}
	/**
	 * @param others the others to set
	 */
	public void setOthers(String others) {
		this.others = others;
	}
	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}
	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/**
	 * @return the uvo
	 */
	public BillingSystemUserValueObject getUvo() {
		return uvo;
	}
	/**
	 * @param uvo the uvo to set
	 */
	public void setUvo(BillingSystemUserValueObject uvo) {
		this.uvo = uvo;
	}
	/**
	 * @param cboBillingAddress the cboBillingAddress to set
	 */
	/**
	 * @param exportAmount the exportAmount to set
	 */
	public void setExportAmount(String exportAmount) {
		this.exportAmount = exportAmount;
	}
	/**
	 * @return the exportAmount
	 */
	public String getExportAmount() {
		return exportAmount;
	}
	/**
	 * @param exportCurrency the exportCurrency to set
	 */
	public void setExportCurrency(String exportCurrency) {
		this.exportCurrency = exportCurrency;
	}
	/**
	 * @return the exportCurrency
	 */
	public String getExportCurrency() {
		return exportCurrency;
	}
	/**
	 * @param billingCurrency the billingCurrency to set
	 */
	public void setBillingCurrency(String billingCurrency) {
		this.billingCurrency = billingCurrency;
	}
	/**
	 * @return the billingCurrency
	 */
	public String getBillingCurrency() {
		return billingCurrency;
	}
	/**
	 * @param userComment the userComment to set
	 */
	public void setUserComment(String userComment) {
		this.userComment = userComment;
	}
	/**
	 * @return the userComment
	 */
	public String getUserComment() {
		return userComment;
	}
	/**
	 * @param action the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}
	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}
	/**
	 * @param screenObject the screenObject to set
	 */
	public void setScreenObject(Map<String, Object> screenObject) {
		this.screenObject = screenObject;
	}
	/**
	 * @return the screenObject
	 */
	public Map<String, Object> getScreenObject() {
		return screenObject;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param messageParameter the messageParameter to set
	 */
	public void setMessageParameter(String messageParameter) {
		this.messageParameter = messageParameter;
	}
	/**
	 * @return the messageParameter
	 */
	public String getMessageParameter() {
		return messageParameter;
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
