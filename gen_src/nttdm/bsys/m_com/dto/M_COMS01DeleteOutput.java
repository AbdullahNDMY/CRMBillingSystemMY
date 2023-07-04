/*
 * @(#)M_COMS01DeleteOutput.java
 *
 *
 */
package nttdm.bsys.m_com.dto;

import java.io.Serializable;

/**
 * OutputDTO class.
 * 
 * @author helloworld
 */
public class M_COMS01DeleteOutput implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4174909071402793726L;

	/**
     * 
     */
	private String ID_COM_BANK;

	/**
	 * ID_COM_BANK を取得する
	 * 
	 * @return ID_COM_BANK
	 */
	public String getID_COM_BANK() {
		return ID_COM_BANK;
	}

	/**
	 * ID_COM_BANK を設定する
	 * 
	 * @param ID_COM_BANK
	 */
	public void setID_COM_BANK(String ID_COM_BANK) {
		this.ID_COM_BANK = ID_COM_BANK;
	}

}