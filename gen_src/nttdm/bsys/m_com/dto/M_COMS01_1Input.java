/*
 * @(#)M_COMS01_1Input.java
 *
 *
 */
package nttdm.bsys.m_com.dto;

import java.io.Serializable;

/**
 * InputDTO class.
 * 
 * @author helloworld
 */
public class M_COMS01_1Input implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8155094008306921655L;

	/**
     * 
     */
	private String id_com;

	/**
     * 
     */
	private String acc_type;

	/**
	 * id_com を取得する
	 * 
	 * @return id_com
	 */
	public String getId_com() {
		return id_com;
	}

	/**
	 * id_com を設定する
	 * 
	 * @param id_com
	 */
	public void setId_com(String id_com) {
		this.id_com = id_com;
	}

	/**
	 * acc_type を取得する
	 * 
	 * @return acc_type
	 */
	public String getAcc_type() {
		return acc_type;
	}

	/**
	 * acc_type を設定する
	 * 
	 * @param acc_type
	 */
	public void setAcc_type(String acc_type) {
		this.acc_type = acc_type;
	}

}