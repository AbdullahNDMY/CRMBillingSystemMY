/*
 * @(#)A_PWDS01R01Input.java
 *
 *
 */
package nttdm.bsys.a_pwd.dto;

import java.io.Serializable;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;

/**
 * 入力DTOクラス。
 * 
 * @author ss042
 */
public class A_PWDS01R01Input implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1920548806511610480L;

	/**
     * 
     */
	private String userID;

	/**
     * 
     */
	private String oldPassword;

	/**
     * 
     */
	private String newPassword;
	
	/**
	 * 
	 */
	private String reEnterPassword;
	
	private Integer idAudit = null;


	public Integer getIdAudit() {
		return idAudit;
	}

	public void setIdAudit(Integer idAudit) {
		this.idAudit = idAudit;
	}

	/**
	 * 
	 */
	private BillingSystemUserValueObject uvo = null;
	
	/**
	 * userID を取得する
	 * 
	 * @return userID
	 */
	public String getUserID() {
		return userID;
	}

	/**
	 * userID を設定する
	 * 
	 * @param userID
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}

	/**
	 * oldPassword を取得する
	 * 
	 * @return oldPassword
	 */
	public String getOldPassword() {
		return oldPassword;
	}

	/**
	 * oldPassword を設定する
	 * 
	 * @param oldPassword
	 */
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	/**
	 * newPassword を取得する
	 * 
	 * @return newPassword
	 */
	public String getNewPassword() {
		return newPassword;
	}

	/**
	 * newPassword を設定する
	 * 
	 * @param newPassword
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getReEnterPassword() {
		return reEnterPassword;
	}

	/**
	 * 
	 * @param reEnterPassword
	 */
	public void setReEnterPassword(String reEnterPassword) {
		this.reEnterPassword = reEnterPassword;
	}	

	/**
	 * 
	 * @return
	 */
	public BillingSystemUserValueObject getUvo() {
		return uvo;
	}

	/**
	 * 
	 * @param uvo
	 */
	public void setUvo(BillingSystemUserValueObject uvo) {
		this.uvo = uvo;
	}
}