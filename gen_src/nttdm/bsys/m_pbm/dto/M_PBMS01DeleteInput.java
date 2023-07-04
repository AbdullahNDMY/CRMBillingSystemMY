/*
 * @(#)M_PBMS01DeleteInput.java
 *
 *
 */
package nttdm.bsys.m_pbm.dto;

import java.io.Serializable;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;

/**
 * InputDTO class.
 * 
 * @author helloworld
 */
public class M_PBMS01DeleteInput implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1522456297935170726L;

	/**
     * 
     */
	private String id_batch;

	/**
     * 
     */
	private String plan_id;
	
	private String id_plan_grp;
	
	public String getId_plan_grp() {
		return id_plan_grp;
	}

	public void setId_plan_grp(String id_plan_grp) {
		this.id_plan_grp = id_plan_grp;
	}

	private BillingSystemUserValueObject uvo;

	public BillingSystemUserValueObject getUvo() {
		return uvo;
	}

	public void setUvo(BillingSystemUserValueObject uvo) {
		this.uvo = uvo;
	}

	/**
	 * id_batch を取得する
	 * 
	 * @return id_batch
	 */
	public String getId_batch() {
		return id_batch;
	}

	/**
	 * id_batch を設定する
	 * 
	 * @param id_batch
	 */
	public void setId_batch(String id_batch) {
		this.id_batch = id_batch;
	}

	/**
	 * plan_id を取得する
	 * 
	 * @return plan_id
	 */
	public String getPlan_id() {
		return plan_id;
	}

	/**
	 * plan_id を設定する
	 * 
	 * @param plan_id
	 */
	public void setPlan_id(String plan_id) {
		this.plan_id = plan_id;
	}

}