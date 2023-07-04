package nttdm.bsys.bif.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;

public class B_BIFS02_03Output implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Map bifInfo = null;
	Map cusInfo = null;
	
	BillingSystemUserValueObject userInfo = null;
	
	Map totalPlan = null;
	
	Map cusAdr = null;

	List plans = null;
	
	List planSs = null;
	
	List planOs = null;
	
	List attachmentsSCBif = null;
	
	List attachmentsQPBif = null;
	
	List attachmentsOTBif = null;

	public Map getBifInfo() {
		return bifInfo;
	}

	public void setBifInfo(Map bifInfo) {
		this.bifInfo = bifInfo;
	}

	public Map getCusInfo() {
		return cusInfo;
	}

	public void setCusInfo(Map cusInfo) {
		this.cusInfo = cusInfo;
	}

	public BillingSystemUserValueObject getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(BillingSystemUserValueObject userInfo) {
		this.userInfo = userInfo;
	}

	public Map getTotalPlan() {
		return totalPlan;
	}

	public void setTotalPlan(Map totalPlan) {
		this.totalPlan = totalPlan;
	}

	public Map getCusAdr() {
		return cusAdr;
	}

	public void setCusAdr(Map cusAdr) {
		this.cusAdr = cusAdr;
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

	public List getAttachmentsSCBif() {
		return attachmentsSCBif;
	}

	public void setAttachmentsSCBif(List attachmentsSCBif) {
		this.attachmentsSCBif = attachmentsSCBif;
	}

	public List getAttachmentsQPBif() {
		return attachmentsQPBif;
	}

	public void setAttachmentsQPBif(List attachmentsQPBif) {
		this.attachmentsQPBif = attachmentsQPBif;
	}

	public List getAttachmentsOTBif() {
		return attachmentsOTBif;
	}

	public void setAttachmentsOTBif(List attachmentsOTBif) {
		this.attachmentsOTBif = attachmentsOTBif;
	}
	


}
