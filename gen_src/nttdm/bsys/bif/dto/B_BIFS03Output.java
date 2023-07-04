/**
 * 
 */
package nttdm.bsys.bif.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import nttdm.bsys.bif.bean.CustomerPlanBean;
import nttdm.bsys.bif.bean.FileInfo;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;

/**
 * @author duongnld
 *
 */
public class B_BIFS03Output extends B_BIF_Output implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5594453239169108882L;
	
	
	private List<FileInfo> attachmentsTFBif;
	private List<FileInfo> attachmentsTLBif;
	private List<FileInfo> attachmentsIVBif;
	private List<FileInfo> attachmentsMRBif;
	private List<FileInfo> attachmentsPOBif;
	private List<FileInfo> attachmentsAGBif;
	private List<FileInfo> attachmentsCRBif;
	private List<FileInfo> attachmentsOTBif;
	private String pictureId;
	private String accessType;
	private String uploadDisplay;
	private String refurbish;
    
	/**
	 * @return the refurbish
	 */
	public String getRefurbish() {
		return refurbish;
	}
	/**
	 * @param refurbish the refurbish to set
	 */
	public void setRefurbish(String refurbish) {
		this.refurbish = refurbish;
	}
	/**
	 * @return the uploadDisplay
	 */
	public String getUploadDisplay() {
		return uploadDisplay;
	}
	/**
	 * @param uploadDisplay the uploadDisplay to set
	 */
	public void setUploadDisplay(String uploadDisplay) {
		this.uploadDisplay = uploadDisplay;
	}
	/**
	 * @return the accessType
	 */
	public String getAccessType() {
		return accessType;
	}
	/**
	 * @param accessType the accessType to set
	 */
	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}
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
	 * @return the attachmentsTFBif
	 */
	public List<FileInfo> getAttachmentsTFBif() {
		return attachmentsTFBif;
	}
	/**
	 * @param attachmentsTFBif the attachmentsTFBif to set
	 */
	public void setAttachmentsTFBif(List<FileInfo> attachmentsTFBif) {
		this.attachmentsTFBif = attachmentsTFBif;
	}
	/**
	 * @return the attachmentsTLBif
	 */
	public List<FileInfo> getAttachmentsTLBif() {
		return attachmentsTLBif;
	}
	/**
	 * @param attachmentsTLBif the attachmentsTLBif to set
	 */
	public void setAttachmentsTLBif(List<FileInfo> attachmentsTLBif) {
		this.attachmentsTLBif = attachmentsTLBif;
	}
	/**
	 * @return the attachmentsIVBif
	 */
	public List<FileInfo> getAttachmentsIVBif() {
		return attachmentsIVBif;
	}
	/**
	 * @param attachmentsIVBif the attachmentsIVBif to set
	 */
	public void setAttachmentsIVBif(List<FileInfo> attachmentsIVBif) {
		this.attachmentsIVBif = attachmentsIVBif;
	}
	/**
	 * @return the attachmentsMRBif
	 */
	public List<FileInfo> getAttachmentsMRBif() {
		return attachmentsMRBif;
	}
	/**
	 * @param attachmentsMRBif the attachmentsMRBif to set
	 */
	public void setAttachmentsMRBif(List<FileInfo> attachmentsMRBif) {
		this.attachmentsMRBif = attachmentsMRBif;
	}
	/**
	 * @return the attachmentsPOBif
	 */
	public List<FileInfo> getAttachmentsPOBif() {
		return attachmentsPOBif;
	}
	/**
	 * @param attachmentsPOBif the attachmentsPOBif to set
	 */
	public void setAttachmentsPOBif(List<FileInfo> attachmentsPOBif) {
		this.attachmentsPOBif = attachmentsPOBif;
	}
	/**
	 * @return the attachmentsAGBif
	 */
	public List<FileInfo> getAttachmentsAGBif() {
		return attachmentsAGBif;
	}
	/**
	 * @param attachmentsAGBif the attachmentsAGBif to set
	 */
	public void setAttachmentsAGBif(List<FileInfo> attachmentsAGBif) {
		this.attachmentsAGBif = attachmentsAGBif;
	}
	/**
	 * @return the attachmentsCRBif
	 */
	public List<FileInfo> getAttachmentsCRBif() {
		return attachmentsCRBif;
	}
	/**
	 * @param attachmentsCRBif the attachmentsCRBif to set
	 */
	public void setAttachmentsCRBif(List<FileInfo> attachmentsCRBif) {
		this.attachmentsCRBif = attachmentsCRBif;
	}
	/**
	 * @return the attachmentsOTBif
	 */
	public List<FileInfo> getAttachmentsOTBif() {
		return attachmentsOTBif;
	}
	/**
	 * @param attachmentsOTBif the attachmentsOTBif to set
	 */
	public void setAttachmentsOTBif(List<FileInfo> attachmentsOTBif) {
		this.attachmentsOTBif = attachmentsOTBif;
	}
	String exportCurrency = null;
	String rateCurrency = null;
	
	Map<String, Object> cusInfo = null;
	
	BillingSystemUserValueObject userInfo = null;
	
	Map<String, Object> totalPlan = null;
	
	List<Map<String, Object>> cusAdr = null;

	List<Map<String, Object>> qscRefers = null;

	List<Map<String, Object>> quoRefers = null;

	List<Map<String, Object>> colsNames = null;
	
	List<Map<String, Object>> jobNos = null;
	
	List<Map<String, Object>> attns = null;

	List<Map<String, Object>> currencies = null;

	List<Map<String, Object>> suppliers = null;
	
	List<Map<String, Object>> plans = null;
	
	List<Map<String, Object>> planSs = null;
	
	List<Map<String, Object>> planOs = null;
	
	private Map<String, Object> bifObject = null;
	
	private List<Map<String, Object>> commentBIF;
	private List<Map<String, Object>> commentHCD;
	private List<Map<String, Object>> commentBOP;
	
	private String action = null;
	
	private CustomerPlanBean customerPlan;
	
	private String taxType = null;
	private String taxStr = null;
	
	public List<Map<String, Object>> getColsNames() {
		return colsNames;
	}

	public void setColsNames(List<Map<String, Object>> colsNames) {
		this.colsNames = colsNames;
	}

	public List<Map<String, Object>> getCurrencies() {
		return currencies;
	}

	public void setCurrencies(List<Map<String, Object>> currencies) {
		this.currencies = currencies;
	}


	public List<Map<String, Object>> getQscRefers() {
		return qscRefers;
	}

	public void setQscRefers(List<Map<String, Object>> qscRefers) {
		this.qscRefers = qscRefers;
	}

	public List<Map<String, Object>> getQuoRefers() {
		return quoRefers;
	}

	public void setQuoRefers(List<Map<String, Object>> quoRefers) {
		this.quoRefers = quoRefers;
	}

	public List<Map<String, Object>> getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(List<Map<String, Object>> suppliers) {
		this.suppliers = suppliers;
	}

	public Map<String, Object> getCusInfo() {
		return cusInfo;
	}

	public void setCusInfo(Map<String, Object> cusInfo) {
		this.cusInfo = cusInfo;
	}

	public List<Map<String, Object>> getCusAdr() {
		return cusAdr;
	}

	public void setCusAdr(List<Map<String, Object>> cusAdr) {
		this.cusAdr = cusAdr;
	}

	public List<Map<String, Object>> getJobNos() {
		return jobNos;
	}

	public void setJobNos(List<Map<String, Object>> jobNos) {
		this.jobNos = jobNos;
	}

	public List<Map<String, Object>> getAttns() {
		return attns;
	}

	public void setAttns(List<Map<String, Object>> attns) {
		this.attns = attns;
	}

	public List<Map<String, Object>> getPlans() {
		return plans;
	}

	public void setPlans(List<Map<String, Object>> plans) {
		this.plans = plans;
	}

	public List<Map<String, Object>> getPlanSs() {
		return planSs;
	}

	public void setPlanSs(List<Map<String, Object>> planSs) {
		this.planSs = planSs;
	}

	public List<Map<String, Object>> getPlanOs() {
		return planOs;
	}

	public void setPlanOs(List<Map<String, Object>> planOs) {
		this.planOs = planOs;
	}

	public Map<String, Object> getTotalPlan() {
		return totalPlan;
	}

	public void setTotalPlan(Map<String, Object> totalPlan) {
		this.totalPlan = totalPlan;
	}

	public BillingSystemUserValueObject getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(BillingSystemUserValueObject userInfo) {
		this.userInfo = userInfo;
	}

	public String getExportCurrency() {
		return exportCurrency;
	}

	public void setExportCurrency(String exportCurrency) {
		this.exportCurrency = exportCurrency;
	}

	public String getRateCurrency() {
		return rateCurrency;
	}

	public void setRateCurrency(String rateCurrency) {
		this.rateCurrency = rateCurrency;
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
	 * @param bifObject the bifObject to set
	 */
	public void setBifObject(Map<String, Object> bifObject) {
		this.bifObject = bifObject;
	}

	/**
	 * @return the bifObject
	 */
	public Map<String, Object> getBifObject() {
		return bifObject;
	}

	/**
	 * @return the commentBIF
	 */
	public List<Map<String, Object>> getCommentBIF() {
		return commentBIF;
	}
	/**
	 * @param commentBIF the commentBIF to set
	 */
	public void setCommentBIF(List<Map<String, Object>> commentBIF) {
		this.commentBIF = commentBIF;
	}
	/**
	 * @return the commentHCD
	 */
	public List<Map<String, Object>> getCommentHCD() {
		return commentHCD;
	}
	/**
	 * @param commentHCD the commentHCD to set
	 */
	public void setCommentHCD(List<Map<String, Object>> commentHCD) {
		this.commentHCD = commentHCD;
	}
	/**
	 * @return the commentBOP
	 */
	public List<Map<String, Object>> getCommentBOP() {
		return commentBOP;
	}
	/**
	 * @param commentBOP the commentBOP to set
	 */
	public void setCommentBOP(List<Map<String, Object>> commentBOP) {
		this.commentBOP = commentBOP;
	}
	
	private String chkExportAmount = null;

	public String getChkExportAmount() {
		return chkExportAmount;
	}
	
	public void setChkExportAmount(String chkExportAmount) {
		this.chkExportAmount = chkExportAmount;
	}
    /**
     * @return the customerPlan
     */
    public CustomerPlanBean getCustomerPlan() {
        return customerPlan;
    }
    /**
     * @param customerPlan the customerPlan to set
     */
    public void setCustomerPlan(CustomerPlanBean customerPlan) {
        this.customerPlan = customerPlan;
    }
	public String getTaxType() {
		return taxType;
	}
	public void setTaxType(String taxType) {
		this.taxType = taxType;
	}
	public String getTaxStr() {
		return taxStr;
	}
	public void setTaxStr(String taxStr) {
		this.taxStr = taxStr;
	}
}
