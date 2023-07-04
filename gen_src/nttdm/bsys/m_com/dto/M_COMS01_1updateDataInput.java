/*
 * @(#)M_COMS01_1updateDataInput.java
 *
 *
 */
package nttdm.bsys.m_com.dto;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * InputDTO class.
 * 
 * @author helloworld
 */
public class M_COMS01_1updateDataInput implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 9166313705414964802L;

	/**
     * 
     */
	private String id_com;

	/**
     * 
     */
	private ArrayList<java.util.HashMap<java.lang.String, java.lang.Object>> paramMap;

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
	 * paramMap を取得する
	 * 
	 * @return paramMap
	 */
	public ArrayList<java.util.HashMap<java.lang.String, java.lang.Object>> getParamMap() {
		return paramMap;
	}

	/**
	 * paramMap を設定する
	 * 
	 * @param paramMap
	 */
	public void setParamMap(
			ArrayList<java.util.HashMap<java.lang.String, java.lang.Object>> paramMap) {
		this.paramMap = paramMap;
	}

}