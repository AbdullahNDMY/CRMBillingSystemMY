package nttdm.bsys.common.bean;


/**
 * TBilD entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class T_BIL_D implements java.io.Serializable {

	// Fields
	private String idRef;
	private String itemLevel;
	private String itemDesc;
	private String itemQty;
	private String itemUprice;
	private String itemAmt;
	private String idCustPlan;
	private String idPlanGrp;
	private String billSvcGrp;
	private String billFrom;
	private String billTo;
	private String isDeleted;
	private String dateCreated;
	private String dateUpdated;
	private String idLogin;

	// Constructors

	/** default constructor */
	public T_BIL_D() {
	}
	// Property accessors

	public String getIdRef() {
		return idRef;
	}

	public void setIdRef(String idRef) {
		this.idRef = idRef;
	}

	public String getItemLevel() {
		return itemLevel;
	}

	public void setItemLevel(String itemLevel) {
		this.itemLevel = itemLevel;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public String getItemQty() {
		return itemQty;
	}

	public void setItemQty(String itemQty) {
		this.itemQty = itemQty;
	}

	public String getItemUprice() {
		return itemUprice;
	}

	public void setItemUprice(String itemUprice) {
		this.itemUprice = itemUprice;
	}

	public String getItemAmt() {
		return itemAmt;
	}

	public void setItemAmt(String itemAmt) {
		this.itemAmt = itemAmt;
	}

	public String getIdCustPlan() {
		return idCustPlan;
	}

	public void setIdCustPlan(String idCustPlan) {
		this.idCustPlan = idCustPlan;
	}

	public String getIdPlanGrp() {
		return idPlanGrp;
	}

	public void setIdPlanGrp(String idPlanGrp) {
		this.idPlanGrp = idPlanGrp;
	}

	public String getBillSvcGrp() {
		return billSvcGrp;
	}

	public void setBillSvcGrp(String billSvcGrp) {
		this.billSvcGrp = billSvcGrp;
	}

	public String getBillFrom() {
		return billFrom;
	}

	public void setBillFrom(String billFrom) {
		this.billFrom = billFrom;
	}

	public String getBillTo() {
		return billTo;
	}

	public void setBillTo(String billTo) {
		this.billTo = billTo;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(String dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public String getIdLogin() {
		return idLogin;
	}

	public void setIdLogin(String idLogin) {
		this.idLogin = idLogin;
	}
	
}