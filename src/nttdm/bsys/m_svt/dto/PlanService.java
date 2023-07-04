/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_SVT
 * SERVICE NAME   : M_SVT_S01
 * FUNCTION       : PlanService
 * FILE NAME      : PlanService.java
 * 
* Copyright © 2011 NTTDATA Corporation
*
 * History
 * 
 * 
**********************************************************/
package nttdm.bsys.m_svt.dto;

/**
 * PlanService<br>
 * <ul>
 * <li>An Object to Express a service or a plan
 * </ul>
 * <br>
* @author  NTTData Vietnam
* @version 1.0
 */
public class PlanService {
	
	private String idService;
	private String serviceGroup;
	private String serviceCategory;
	private String serviceDescription;
	private String accCode;
	private String uqty;
	private String gst;
	private String isUsed;
	//null begin
	private String idLogin;
	private Integer idAudit = null;
	//null end
	private String descAbbr;
	private String type;
	private String svcGrpName;
	private String productCode;
	private String reference1;
	private String reference2;
	private String isUserX;
	private String isUserY;
	
	public String getIdService() {
		return idService;
	}
	
	public void setIdService(String idService) {
		this.idService = idService;
	}
	
	public String getServiceGroup() {
		return serviceGroup;
	}
	
	public void setServiceGroup(String serviceGroup) {
		this.serviceGroup = serviceGroup;
	}
	
	public String getServiceCategory() {
		return serviceCategory;
	}
	
	public void setServiceCategory(String serviceCategory) {
		this.serviceCategory = serviceCategory;
	}
	
	public String getServiceDescription() {
		return serviceDescription;
	}
	
	public void setServiceDescription(String serviceDescription) {
		this.serviceDescription = serviceDescription;
	}
	
	public String getAccCode() {
		return accCode;
	}
	
	public void setAccCode(String accCode) {
		this.accCode = accCode;
	}
	
	public String getUqty() {
		return uqty;
	}
	
	public void setUqty(String uqty) {
		this.uqty = uqty;
	}
	
	public String getGst() {
		return gst;
	}
	
	public void setGst(String gst) {
		this.gst = gst;
	}
	
	public String getIsUsed() {
		return isUsed;
	}
	
	public void setIsUsed(String isUsed) {
		this.isUsed = isUsed;
	}
	
	public String getIdLogin() {
		return idLogin;
	}
	
	public void setIdLogin(String idLogin) {
		this.idLogin = idLogin;
	}
	
	public Integer getIdAudit() {
		return idAudit;
	}
	
	public void setIdAudit(Integer idAudit) {
		this.idAudit = idAudit;
	}
	
	public PlanService(){
		idService = "";
		gst = "0";
		isUsed = "0";
	}

    public String getDescAbbr() {
        return descAbbr;
    }

    public void setDescAbbr(String descAbbr) {
        this.descAbbr = descAbbr;
    }

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
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
	 * @return the productCode
	 */
	public String getProductCode() {
		return productCode;
	}

	/**
	 * @param productCode the productCode to set
	 */
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	/**
	 * @return the reference1
	 */
	public String getReference1() {
		return reference1;
	}

	/**
	 * @param reference1 the reference1 to set
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
	 * @param reference2 the reference2 to set
	 */
	public void setReference2(String reference2) {
		this.reference2 = reference2;
	}

	/**
	 * @return the isUserX
	 */
	public String getIsUserX() {
		return isUserX;
	}

	/**
	 * @param isUserX the isUserX to set
	 */
	public void setIsUserX(String isUserX) {
		this.isUserX = isUserX;
	}

	/**
	 * @return the isUserY
	 */
	public String getIsUserY() {
		return isUserY;
	}

	/**
	 * @param isUserY the isUserY to set
	 */
	public void setIsUserY(String isUserY) {
		this.isUserY = isUserY;
	}
}