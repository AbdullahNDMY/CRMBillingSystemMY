package nttdm.bsys.b_cpm.dto;

import java.util.*;
public class ResultSearchPlan {
	
	private String planId;
	private String no;
	private String subId;
	private String customerName;
	private String planName;
	private String description;
	private String transType;
	private String billServiceGrp;
	private String billCur;
	private String serviceDateFrom;
	private String serviceDateTo;
	private String status;
	private List<Service> subPlans;
	
	public ResultSearchPlan(){
		subPlans = new ArrayList<Service>();
	}
	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}
	public String getSubId() {
		return subId;
	}
	public void setSubId(String subId) {
		this.subId = subId;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public String getBillServiceGrp() {
		return billServiceGrp;
	}

	public void setBillServiceGrp(String billServiceGrp) {
		this.billServiceGrp = billServiceGrp;
	}

	public String getBillCur() {
		return billCur;
	}

	public void setBillCur(String billCur) {
		this.billCur = billCur;
	}

	public String getServiceDateFrom() {
		return serviceDateFrom;
	}

	public void setServiceDateFrom(String serviceDateFrom) {
		this.serviceDateFrom = serviceDateFrom;
	}

	public String getServiceDateTo() {
		return serviceDateTo;
	}

	public void setServiceDateTo(String serviceDateTo) {
		this.serviceDateTo = serviceDateTo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Service> getSubPlans() {
		return subPlans;
	}

	public void setSubPlans(List<Service> subPlans) {
		this.subPlans = subPlans;
	}

}
