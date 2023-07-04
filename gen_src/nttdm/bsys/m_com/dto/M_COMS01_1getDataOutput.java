/*
 * @(#)M_COMS01_1getDataOutput.java
 *
 *
 */
package nttdm.bsys.m_com.dto;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * OutputDTO class.
 * 
 * @author helloworld
 */
public class M_COMS01_1getDataOutput implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4399922778065205879L;

	/**
     * 
     */
	private String id_com;

	/**
     * 
     */
	private String update_data;

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