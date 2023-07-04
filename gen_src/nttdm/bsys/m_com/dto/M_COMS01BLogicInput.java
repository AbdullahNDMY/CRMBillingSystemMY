/*
 * @(#)M_COMS01BLogicInput.java
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
public class M_COMS01BLogicInput implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -803143361569995501L;

	/**
     * 
     */
	private BillingSystemUserValueObject uvo;

	/**
     * 
     */
	private String id_com;

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
	 * id_com を取得する
	 * 
	 * @return id_com
	 */
	private String checkpagetype;
	
	public String getCheckpagetype() {
		return checkpagetype;
	}

	public void setCheckpagetype(String checkpagetype) {
		this.checkpagetype = checkpagetype;
	}

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

}