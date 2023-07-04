package nttdm.bsys.b_cpm.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import nttdm.bsys.b_cpm.common.B_CPM_S02UtilP1;
import nttdm.bsys.common.util.dto.AutoScaleList;

public class Service implements Serializable {

	public static String SUBPLAN = "S";
	public static String OPTIONSERVICE = "O";
	
	private static final long serialVersionUID = 1L;
	B_CPM_S02UtilP1 util = new B_CPM_S02UtilP1();
	private String subPlanName;
	private String quantity;
	private String unitPrice;
	private String totalAmount;
	private String rate;
	private String mode;
	private String modeName;
	private String gst;
	
	private String serviceGroup;
	private String serviceGroupName;
	private String serviceType;
	private String serviceTypeName;
	private String serviceItem;
	private String serviceItemName;
	private String vendor;
	private String vendorName;
	private String rateType;
	private String idPlanGroup;
	private String itemType;
	private String itemGroupName;
	private String billCurrency;
	private String rateMode;
	private String itemDescription;
	private List<Service> subPlanDetails;
	private String idCustPlan;
	private String isApply;
	private String idLogin;
	private String tbxUnitPrice_S;
	private String tbxQuantity_S ;
	private String tbxTotalAmount_S;
	private String idPlan;
	private String idPlanSvc;
	private Integer subPlanDetailCount; // number of subPlanDetails
	private Integer idAudit = null;

	//Hung : property details, interchange for subPlanDetails
	private List<PlanService> details;
	private List<PlanService> listServiceType;
	private List<PlanService> listServiceItem;
	public Service(){
		subPlanDetails = new ArrayList<Service>();
		details = new AutoScaleList<PlanService>(new PlanService());
		listServiceType = new ArrayList<PlanService>();
		listServiceItem = new ArrayList<PlanService>();
		gst = "0";
		idPlanGroup = "";
	}
	
	public String getModeName() {
		return util.getModeName(this.mode);
	}
	public void setModeName(String modeName) {
		this.modeName = modeName;
	}
	public String getServiceTypeName() {
		return serviceTypeName;
	}
	public void setServiceTypeName(String serviceTypeName) {
		this.serviceTypeName = serviceTypeName;
	}
	public String getServiceItemName() {
		return serviceItemName;
	}
	public void setServiceItemName(String serviceItemName) {
		this.serviceItemName = serviceItemName;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public Integer getSubPlanDetailCount() {
		return subPlanDetailCount;
	}
	public void setSubPlanDetailCount(Integer subPlanDetailCount) {
		this.subPlanDetailCount = subPlanDetailCount;
	}
	public String getServiceGroupName() {
		return serviceGroupName;
	}
	public void setServiceGroupName(String serviceGroupName) {
		this.serviceGroupName = serviceGroupName;
	}
	public String getIdPlanSvc() {
		return idPlanSvc;
	}
	public void setIdPlanSvc(String idPlanSvc) {
		this.idPlanSvc = idPlanSvc;
	}
	public String getIdPlan() {
		return idPlan;
	}
	public void setIdPlan(String idPlan) {
		this.idPlan = idPlan;
	}
	public String getTbxUnitPrice_S() {
		return this.unitPrice;
	}
	public void setTbxUnitPrice_S(String tbxUnitPrice_S) {
		this.tbxUnitPrice_S = tbxUnitPrice_S;
		this.unitPrice = tbxUnitPrice_S;
	}
	public String getTbxQuantity_S() {
		return this.quantity;
	}
	public void setTbxQuantity_S(String tbxQuantity_S) {
		this.tbxQuantity_S = tbxQuantity_S;
		this.quantity = tbxQuantity_S;
	}
	public String getTbxTotalAmount_S() {
		return this.totalAmount;
	}
	public void setTbxTotalAmount_S(String tbxTotalAmount_S) {
		this.tbxTotalAmount_S = tbxTotalAmount_S;
		this.totalAmount = tbxTotalAmount_S;
	}
	public String getIdLogin() {
		return idLogin;
	}
	public void setIdLogin(String idLogin) {
		this.idLogin = idLogin;
	}
	public String getIsApply() {
		return isApply;
	}
	public void setIsApply(String isApply) {
		this.isApply = isApply;
	}
	public String getIdCustPlan() {
		return idCustPlan;
	}
	public void setIdCustPlan(String idCustPlan) {
		this.idCustPlan = idCustPlan;
	}
	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	public List<Service> getSubPlanDetails() {
		return subPlanDetails;
	}
	public void setSubPlanDetails(List<Service> subPlanDetails) {
		this.subPlanDetails = subPlanDetails;
	}
	public String getRateMode() {
		return rateMode;
	}
	public void setRateMode(String rateMode) {
		this.rateMode = rateMode;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public String getItemGroupName() {
		return itemGroupName;
	}
	public void setItemGroupName(String itemGroupName) {
		this.itemGroupName = itemGroupName;
	}
	public String getBillCurrency() {
		return billCurrency;
	}
	public void setBillCurrency(String billCurrency) {
		this.billCurrency = billCurrency;
	}
	public String getIdPlanGroup() {
		return idPlanGroup;
	}
	public void setIdPlanGroup(String idPlanGroup) {
		this.idPlanGroup = idPlanGroup;
	}
	public String getGst() {
		return gst;
	}
	public void setGst(String gst) {
		this.gst = gst;
	}
	public String getRateType() {
		return rateType;
	}
	public void setRateType(String rateType) {
		this.rateType = rateType;
	}
	public String getServiceGroup() {
		return serviceGroup;
	}
	public void setServiceGroup(String serviceGroup) {
		this.serviceGroup = serviceGroup;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public String getServiceItem() {
		return serviceItem;
	}
	public void setServiceItem(String serviceItem) {
		this.serviceItem = serviceItem;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getSubPlanName() {
		return subPlanName;
	}
	public void setSubPlanName(String subPlanName) {
		this.subPlanName = subPlanName;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	public void setPriceQuantity(Service subPlan){
		if (subPlan != null){
			if (subPlan.getTbxQuantity_S() != null) this.setTbxQuantity_S(subPlan.getTbxQuantity_S());
			if (subPlan.getTbxTotalAmount_S() != null) this.setTbxTotalAmount_S(subPlan.getTbxTotalAmount_S());
			if (subPlan.getTbxUnitPrice_S() != null) this.setTbxUnitPrice_S(subPlan.getTbxUnitPrice_S());
			if (subPlan.getItemDescription() != null) this.setItemDescription(subPlan.getItemDescription());
		}
	}

	public List<PlanService> getDetails() {
		return details;
	}

	public void setDetails(List<PlanService> details) {
		this.details = details;
	}

	public List<PlanService> getListServiceType() {
		return listServiceType;
	}

	public void setListServiceType(List<PlanService> listServiceType) {
		this.listServiceType = listServiceType;
	}

	public List<PlanService> getListServiceItem() {
		return listServiceItem;
	}

	public void setListServiceItem(List<PlanService> listServiceItem) {
		this.listServiceItem = listServiceItem;
	}

	public Integer getIdAudit() {
		return idAudit;
	}

	public void setIdAudit(Integer idAudit) {
		this.idAudit = idAudit;
	}
}
