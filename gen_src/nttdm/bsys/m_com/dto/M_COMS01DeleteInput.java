/*
 * @(#)M_COMS01DeleteInput.java
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
public class M_COMS01DeleteInput implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6255086275269702977L;

	/**
     * 
     */
	private String id_com_bank;

	/**
	 * id_com_bank を取得する
	 * 
	 * @return id_com_bank
	 */
	public String getId_com_bank() {
		return id_com_bank;
	}

	/**
	 * id_com_bank を設定する
	 * 
	 * @param id_com_bank
	 */
	public void setId_com_bank(String id_com_bank) {
		this.id_com_bank = id_com_bank;
	}

}