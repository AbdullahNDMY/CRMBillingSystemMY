/*
 * @(#)A_PWD_S01FormBean.java
 *
 *
 */
package nttdm.bsys.a_pwd.bean;

import jp.terasoluna.fw.web.struts.form.ValidatorActionFormEx;

/**
 * アクションフォームクラス。
 * 
 * @author ss042
 */
public class A_PWD_S01FormBean extends ValidatorActionFormEx {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1559892657907064428L;

	/**
	 * userID
	 */
	private String userID;
	
	/**
	 * 
	 */
	private String userName;

	/**
	 * oldPassword
	 */
	private String oldPassword;

	/**
	 * newPassword
	 */
	private String newPassword;

	/**
	 * reEnterPassword
	 */
	private String reEnterPassword;

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
	 *            userID
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
	 *            oldPassword
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
	 *            newPassword
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	/**
	 * 
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
	public String getUserName() {
		return userName;
	}

	/**
	 * 
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}	
}