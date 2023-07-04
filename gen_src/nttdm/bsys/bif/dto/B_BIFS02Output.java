/**
 * 
 */
package nttdm.bsys.bif.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import nttdm.bsys.bif.bean.CustomerPlanBean;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;

/**
 * @author duongnld
 *
 */
public class B_BIFS02Output extends B_BIF_Output implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5594453239169108882L;

	String exportCurrency = null;
	String rateCurrency = null;
	
	String taxType = null;
	String taxStr = null;
	
	Map<String, Object> cusInfo = null;
	
	BillingSystemUserValueObject userInfo = null;
	
	Map<String, Object> totalPlan = null;
	
	List cusAdr = null;

	List qscRefers = null;

	List quoRefers = null;

	List colsNames = null;
	
	List jobNos = null;
	
	List attns = null;

	List currencies = null;

	List suppliers = null;
	
	List plans = null;
	
	List planSs = null;
	
	List planOs = null;
	
	private Map<String, Object> bifInfo = null;
	
	private Map<String, Object> wfInfo = null;
	
	List attachmentsSCBif = null;
	List attachmentsQPBif = null;
	List attachmentsOTBif = null;
	
	private List<Map<String, Object>> commentBIF;
	private List<Map<String, Object>> commentHCD;
	private List<Map<String, Object>> commentBOP;
	
	private String action = null;
	
	private String chkExportAmount = null;
	
	private Map<String, Object> pagAction;
	
	private CustomerPlanBean customerPlan;
	
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
	
	public String getChkExportAmount() {
		return chkExportAmount;
	}

	public void setChkExportAmount(String chkExportAmount) {
		this.chkExportAmount = chkExportAmount;
	}

	public List getColsNames() {
		return colsNames;
	}

	public void setColsNames(List colsNames) {
		this.colsNames = colsNames;
	}

	public List getCurrencies() {
		return currencies;
	}

	public void setCurrencies(List currencies) {
		this.currencies = currencies;
	}


	public List getQscRefers() {
		return qscRefers;
	}

	public void setQscRefers(List qscRefers) {
		this.qscRefers = qscRefers;
	}

	public List getQuoRefers() {
		return quoRefers;
	}

	public void setQuoRefers(List quoRefers) {
		this.quoRefers = quoRefers;
	}

	public List getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(List suppliers) {
		this.suppliers = suppliers;
	}

	public Map getCusInfo() {
		return cusInfo;
	}

	public void setCusInfo(Map cusInfo) {
		this.cusInfo = cusInfo;
	}

	public List getCusAdr() {
		return cusAdr;
	}

	public void setCusAdr(List cusAdr) {
		this.cusAdr = cusAdr;
	}

	public List getJobNos() {
		return jobNos;
	}

	public void setJobNos(List jobNos) {
		this.jobNos = jobNos;
	}

	public List getAttns() {
		return attns;
	}

	public void setAttns(List attns) {
		this.attns = attns;
	}

	public List getPlans() {
		return plans;
	}

	public void setPlans(List plans) {
		this.plans = plans;
	}

	public List getPlanSs() {
		return planSs;
	}

	public void setPlanSs(List planSs) {
		this.planSs = planSs;
	}

	public List getPlanOs() {
		return planOs;
	}

	public void setPlanOs(List planOs) {
		this.planOs = planOs;
	}

	public Map getTotalPlan() {
		return totalPlan;
	}

	public void setTotalPlan(Map totalPlan) {
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
	 * @param wfInfo the wfInfo to set
	 */
	public void setWfInfo(Map<String, Object> wfInfo) {
		this.wfInfo = wfInfo;
	}
	/**
	 * @return the wfInfo
	 */
	public Map<String, Object> getWfInfo() {
		return wfInfo;
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

	/**
	 * @return the attachmentsSCBif
	 */
	public List getAttachmentsSCBif() {
		return attachmentsSCBif;
	}

	/**
	 * @param attachmentsSCBif the attachmentsSCBif to set
	 */
	public void setAttachmentsSCBif(List attachmentsSCBif) {
		this.attachmentsSCBif = attachmentsSCBif;
	}

	/**
	 * @return the attachmentsQPBif
	 */
	public List getAttachmentsQPBif() {
		return attachmentsQPBif;
	}

	/**
	 * @param attachmentsQPBif the attachmentsQPBif to set
	 */
	public void setAttachmentsQPBif(List attachmentsQPBif) {
		this.attachmentsQPBif = attachmentsQPBif;
	}

	/**
	 * @return the attachmentsOTBif
	 */
	public List getAttachmentsOTBif() {
		return attachmentsOTBif;
	}

	/**
	 * @param attachmentsOTBif the attachmentsOTBif to set
	 */
	public void setAttachmentsOTBif(List attachmentsOTBif) {
		this.attachmentsOTBif = attachmentsOTBif;
	}

	/**
	 * @param bifInfo the bifInfo to set
	 */
	public void setBifInfo(Map<String, Object> bifInfo) {
		this.bifInfo = bifInfo;
	}

	/**
	 * @return the bifInfo
	 */
	public Map<String, Object> getBifInfo() {
		return bifInfo;
	}

	public Map<String, Object> getPagAction() {
		return pagAction;
	}

	public void setPagAction(Map<String, Object> pagAction) {
		this.pagAction = pagAction;
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
