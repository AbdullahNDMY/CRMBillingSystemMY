/*
 * @(#)M_COMS01_1updateDataOutput.java
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
public class M_COMS01_1updateDataOutput implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8530776368578381278L;

	/**
     * 
     */
	private String id_com;

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

}