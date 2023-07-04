/*
 * @(#)M_SSMS01_01Input.java
 *
 *
 */
package nttdm.bsys.m_ssm.dto;

import java.io.Serializable;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;

/**
 * InputDTO class.
 * 
 * @author khaln
 */
public class M_SSMS01_01Input implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2863243011706835858L;

	/**
     * 
     */
	private String svc_grp;

	/**
     * 
     */
	private String[] entry;

	/**
     * 
     */
	private String[] report;

	/**
     * 
     */
	private String actionType;

	/**
     * 
     */
	private String[] idService;

	/**
     * 
     */
	private BillingSystemUserValueObject uvo;

	/**
     * 
     */
	private String[] deletedIdService;

	/**
     * 
     */
	private String[] infoId;
	
	
	private String serviceInfoQuantity;
	

	public String getServiceInfoQuantity() {
		return serviceInfoQuantity;
	}

	public void setServiceInfoQuantity(String serviceInfoQuantity) {
		this.serviceInfoQuantity = serviceInfoQuantity;
	}

	/**
	 * svc_grp を取得する
	 * 
	 * @return svc_grp
	 */
	public String getSvc_grp() {
		return svc_grp;
	}

	/**
	 * svc_grp を設定する
	 * 
	 * @param svc_grp
	 */
	public void setSvc_grp(String svc_grp) {
		this.svc_grp = svc_grp;
	}

	/**
	 * entry を取得する
	 * 
	 * @return entry
	 */
	public String[] getEntry() {
		return entry;
	}

	/**
	 * entry を設定する
	 * 
	 * @param entry
	 */
	public void setEntry(String[] entry) {
		this.entry = entry;
	}

	/**
	 * report を取得する
	 * 
	 * @return report
	 */
	public String[] getReport() {
		return report;
	}

	/**
	 * report を設定する
	 * 
	 * @param report
	 */
	public void setReport(String[] report) {
		this.report = report;
	}

	/**
	 * actionType を取得する
	 * 
	 * @return actionType
	 */
	public String getActionType() {
		return actionType;
	}

	/**
	 * actionType を設定する
	 * 
	 * @param actionType
	 */
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	/**
	 * idService を取得する
	 * 
	 * @return idService
	 */
	public String[] getIdService() {
		return idService;
	}

	/**
	 * idService を設定する
	 * 
	 * @param idService
	 */
	public void setIdService(String[] idService) {
		this.idService = idService;
	}

	/**
	 * uvo を取得する
	 * 
	 * @return uvo
	 */
	public BillingSystemUserValueObject getUvo() {
		return uvo;
	}

	/**
	 * uvo を設定する
	 * 
	 * @param uvo
	 */
	public void setUvo(BillingSystemUserValueObject uvo) {
		this.uvo = uvo;
	}

	/**
	 * deletedIdService を取得する
	 * 
	 * @return deletedIdService
	 */
	public String[] getDeletedIdService() {
		return deletedIdService;
	}

	/**
	 * deletedIdService を設定する
	 * 
	 * @param deletedIdService
	 */
	public void setDeletedIdService(String[] deletedIdService) {
		this.deletedIdService = deletedIdService;
	}

	/**
	 * infoId を取得する
	 * 
	 * @return infoId
	 */
	public String[] getInfoId() {
		return infoId;
	}

	/**
	 * infoId を設定する
	 * 
	 * @param infoId
	 */
	public void setInfoId(String[] infoId) {
		this.infoId = infoId;
	}

}