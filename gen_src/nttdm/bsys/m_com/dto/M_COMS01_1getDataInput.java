/*
 * @(#)M_COMS01_1getDataInput.java
 *
 *
 */
package nttdm.bsys.m_com.dto;

import java.io.Serializable;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;

/**
 * InputDTO class.
 * 
 * @author helloworld
 */
public class M_COMS01_1getDataInput implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5341160545461139676L;

	/**
     * 
     */
	private String update_data;

	/**
     * 
     */
	private String id_com;

	/**
     * 
     */
	private BillingSystemUserValueObject uvo;

	/**
	 * update_data を取得する
	 * 
	 * @return update_data
	 */
	public String getUpdate_data() {
		return update_data;
	}

	/**
	 * update_data を設定する
	 * 
	 * @param update_data
	 */
	public void setUpdate_data(String update_data) {
		this.update_data = update_data;
	}

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

}