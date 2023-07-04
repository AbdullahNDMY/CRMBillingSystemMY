/*
 * @(#)A_PWDS01R02Output.java
 *
 *
 */
package nttdm.bsys.a_pwd.dto;

import java.io.Serializable;

/**
 * 出力DTOクラス。
 * 
 * @author ss042
 */
public class A_PWDS01R02Output implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3560337488244097815L;

	/**
     * 
     */
	private String userID;

	/**
     * 
     */
	private String userName;

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
	 * userName を取得する
	 * 
	 * @return userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * userName を設定する
	 * 
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

}