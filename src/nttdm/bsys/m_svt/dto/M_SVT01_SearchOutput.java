package nttdm.bsys.m_svt.dto;

import java.io.Serializable;
import java.util.List;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.dto.AutoScaleList;

public class M_SVT01_SearchOutput implements Serializable {
	private static final long serialVersionUID = -3825291939065997528L;
	private BillingSystemUserValueObject uvo;
	private String category;
	private List<PlanService> listPlanService;
	private String mode;
	private String abbreviation;
	private String type;
	private String productCode;
	private String description;
	private Integer totalCount = 0;
	private Integer startIndex = 0;
	private Integer row;
	private String accountCode;
	private String reference1;
	private String reference2;
	private String message;

	private String title;
	private String editStatus;
	private String returnFlg;
	private String parameters;
	private String svcGrpName;
	private List<PlanService> planServiceList = new AutoScaleList<PlanService>(new PlanService());
	//#157 start
	private String idServiceListStr;
	//#157 end
	
	//Ver3.06 #278 [SIT] Issues found and fix: 1.M-CST, 2.M-SGV, 3.M-SVT CT 13072017
	private String productCodeInput;
	//Ver3.06 #278 [SIT] Issues found and fix: 1.M-CST, 2.M-SGV, 3.M-SVT CT 13072017
	
	/**
	 * @return the uvo
	 */
	public BillingSystemUserValueObject getUvo() {
		return uvo;
	}

	/**
	 * @param uvo
	 *            the uvo to set
	 */
	public void setUvo(BillingSystemUserValueObject uvo) {
		this.uvo = uvo;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return the listPlanService
	 */
	public List<PlanService> getListPlanService() {
		return listPlanService;
	}

	/**
	 * @param listPlanService
	 *            the listPlanService to set
	 */
	public void setListPlanService(List<PlanService> listPlanService) {
		this.listPlanService = listPlanService;
	}

	/**
	 * @return the mode
	 */
	public String getMode() {
		return mode;
	}

	/**
	 * @param mode
	 *            the mode to set
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}

	/**
	 * @return the abbreviation
	 */
	public String getAbbreviation() {
		return abbreviation;
	}

	/**
	 * @param abbreviation
	 *            the abbreviation to set
	 */
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the productCode
	 */
	public String getProductCode() {
		return productCode;
	}

	/**
	 * @param productCode
	 *            the productCode to set
	 */
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the totalCount
	 */
	public Integer getTotalCount() {
		return totalCount;
	}

	/**
	 * @param totalCount
	 *            the totalCount to set
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
	 * @param startIndex
	 *            the startIndex to set
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
	 * @param row
	 *            the row to set
	 */
	public void setRow(Integer row) {
		this.row = row;
	}

	/**
	 * @return the accountCode
	 */
	public String getAccountCode() {
		return accountCode;
	}

	/**
	 * @param accountCode
	 *            the accountCode to set
	 */
	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	/**
	 * @return the reference1
	 */
	public String getReference1() {
		return reference1;
	}

	/**
	 * @param reference1
	 *            the reference1 to set
	 */
	public void setReference1(String reference1) {
		this.reference1 = reference1;
	}

	/**
	 * @return the reference2
	 */
	public String getReference2() {
		return reference2;
	}

	/**
	 * @param reference2
	 *            the reference2 to set
	 */
	public void setReference2(String reference2) {
		this.reference2 = reference2;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the editStatus
	 */
	public String getEditStatus() {
		return editStatus;
	}

	/**
	 * @param editStatus
	 *            the editStatus to set
	 */
	public void setEditStatus(String editStatus) {
		this.editStatus = editStatus;
	}

	/**
	 * @return the returnFlg
	 */
	public String getReturnFlg() {
		return returnFlg;
	}

	/**
	 * @param returnFlg
	 *            the returnFlg to set
	 */
	public void setReturnFlg(String returnFlg) {
		this.returnFlg = returnFlg;
	}

	/**
	 * @return the parameters
	 */
	public String getParameters() {
		return parameters;
	}

	/**
	 * @param parameters
	 *            the parameters to set
	 */
	public void setParameters(String parameters) {
		this.parameters = parameters;
	}

	/**
	 * @return the planServiceList
	 */
	public List<PlanService> getPlanServiceList() {
		return planServiceList;
	}

	/**
	 * @param planServiceList the planServiceList to set
	 */
	public void setPlanServiceList(List<PlanService> planServiceList) {
		this.planServiceList = planServiceList;
	}

	/**
	 * @return the svcGrpName
	 */
	public String getSvcGrpName() {
		return svcGrpName;
	}

	/**
	 * @param svcGrpName the svcGrpName to set
	 */
	public void setSvcGrpName(String svcGrpName) {
		this.svcGrpName = svcGrpName;
	}

	/**
	 * @return the idServiceListStr
	 */
	public String getIdServiceListStr() {
		return idServiceListStr;
	}

	/**
	 * @param idServiceListStr the idServiceListStr to set
	 */
	public void setIdServiceListStr(String idServiceListStr) {
		this.idServiceListStr = idServiceListStr;
	}

	public String getProductCodeInput() {
		return productCodeInput;
	}

	public void setProductCodeInput(String productCodeInput) {
		this.productCodeInput = productCodeInput;
	}
	
}
