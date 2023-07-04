/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : Customer Plan Management (new/edit)
 * SERVICE NAME   : B_CPM_S02
 * FUNCTION       : object using for Customer SubPlan Detail
 * FILE NAME      : CustomerSubPlanDetail.java
 * 
 * Copyright c 2011 NTTDATA Corporation
 *
 * History
 * 2011/07/26 [Duong Nguyen] Created
 * 
**********************************************************/
package nttdm.bsys.b_cpm_en.dto;

/**
 * CustomerSubPlanDetail.class<br>
 * <ul>
 * <li>define customer sub plan detail object</li>
 * </ul>
 * <br>
 * @author  NTTData Vietnam
 * @version 1.1
 */
public class CustomerSubPlanDetail {
	private String idCustPlanSvc;
	private String idCustPlanLink;
	private String svcLevel3;
	private String svcLevel4;
	private String vendor;
	private String idLogin;
	private String idAudit;
	
	private String vendorName;
	private String svcLevel3Name;
	private String svcLevel4Name;
	////Use for 'LineDesc' ,when the 'SubPlanDetail' is not empty,then show 'svcLevel2Name+svcLevel3'  in Screen
	private String lineDesc;
	/**
	 * @return the idCustPlanSvc
	 */
	public String getIdCustPlanSvc() {
		return idCustPlanSvc;
	}
	/**
	 * @param idCustPlanSvc the idCustPlanSvc to set
	 */
	public void setIdCustPlanSvc(String idCustPlanSvc) {
		this.idCustPlanSvc = idCustPlanSvc;
	}
	/**
	 * @return the svcLevel3
	 */
	public String getSvcLevel3() {
		return svcLevel3;
	}
	/**
	 * @param svcLevel3 the svcLevel3 to set
	 */
	public void setSvcLevel3(String svcLevel3) {
		this.svcLevel3 = svcLevel3;
	}
	/**
	 * @return the svcLevel4
	 */
	public String getSvcLevel4() {
		return svcLevel4;
	}
	/**
	 * @param svcLevel4 the svcLevel4 to set
	 */
	public void setSvcLevel4(String svcLevel4) {
		this.svcLevel4 = svcLevel4;
	}
	/**
	 * @return the vendor
	 */
	public String getVendor() {
		return vendor;
	}
	/**
	 * @param vendor the vendor to set
	 */
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	/**
	 * @return the idLogin
	 */
	public String getIdLogin() {
		return idLogin;
	}
	/**
	 * @param idLogin the idLogin to set
	 */
	public void setIdLogin(String idLogin) {
		this.idLogin = idLogin;
	}
	/**
	 * @return the idAudit
	 */
	public String getIdAudit() {
		return idAudit;
	}
	/**
	 * @param idAudit the idAudit to set
	 */
	public void setIdAudit(String idAudit) {
		this.idAudit = idAudit;
	}
	/**
	 * @param idCustPlanLink the idCustPlanLink to set
	 */
	public void setIdCustPlanLink(String idCustPlanLink) {
		this.idCustPlanLink = idCustPlanLink;
	}
	/**
	 * @return the idCustPlanLink
	 */
	public String getIdCustPlanLink() {
		return idCustPlanLink;
	}
	/**
	 * @return the vendorName
	 */
	public String getVendorName() {
		return vendorName;
	}
	/**
	 * @param vendorName the vendorName to set
	 */
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	/**
	 * @return the svcLevel3Name
	 */
	public String getSvcLevel3Name() {
		return svcLevel3Name;
	}
	/**
	 * @param svcLevel3Name the svcLevel3Name to set
	 */
	public void setSvcLevel3Name(String svcLevel3Name) {
		this.svcLevel3Name = svcLevel3Name;
	}
	/**
	 * @return the svcLevel4Name
	 */
	public String getSvcLevel4Name() {
		return svcLevel4Name;
	}
	/**
	 * @param svcLevel4Name the svcLevel4Name to set
	 */
	public void setSvcLevel4Name(String svcLevel4Name) {
		this.svcLevel4Name = svcLevel4Name;
	}
	
	/**
     * @return the lineDesc
     */
    public String getLineDesc() {
        return lineDesc;
    }
    
    /**
     * @param lineDesc the lineDesc to set
     */
    public void setLineDesc(String lineDesc) {
        this.lineDesc = lineDesc;
    }
	
}
