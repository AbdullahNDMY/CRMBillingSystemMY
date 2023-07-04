/*
 * @(#)M_COMS01_1Output.java
 *
 *
 */
package nttdm.bsys.m_com.dto;

import java.io.Serializable;
import java.util.List;

/**
 * OutputDTO class.
 * 
 * @author helloworld
 */
public class M_COMS01_1Output implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6461781367426143798L;

	/**
     * 
     */
	private List<java.util.Map<java.lang.String, java.lang.Object>> giro;

	/**
     * 
     */
	private String id_bank;

	/**
     * 
     */
	private List<java.util.Map<java.lang.String, java.lang.Object>> bank_info;

	/**
     * 
     */
	private String giro_size;

	/**
     * 
     */
	private String id_com;

	/**
     * 
     */
	private String acc_type;

	/**
     * 
     */
	private String bank_info_string;

	/**
	 * giro を取得する
	 * 
	 * @return giro
	 */
	public List<java.util.Map<java.lang.String, java.lang.Object>> getGiro() {
		return giro;
	}

	/**
	 * giro を設定する
	 * 
	 * @param giro
	 */
	public void setGiro(
			List<java.util.Map<java.lang.String, java.lang.Object>> giro) {
		this.giro = giro;
	}

	/**
	 * id_bank を取得する
	 * 
	 * @return id_bank
	 */
	public String getId_bank() {
		return id_bank;
	}

	/**
	 * id_bank を設定する
	 * 
	 * @param id_bank
	 */
	public void setId_bank(String id_bank) {
		this.id_bank = id_bank;
	}

	/**
	 * bank_info を取得する
	 * 
	 * @return bank_info
	 */
	public List<java.util.Map<java.lang.String, java.lang.Object>> getBank_info() {
		return bank_info;
	}

	/**
	 * bank_info を設定する
	 * 
	 * @param bank_info
	 */
	public void setBank_info(
			List<java.util.Map<java.lang.String, java.lang.Object>> bank_info) {
		this.bank_info = bank_info;
	}

	/**
	 * giro_size を取得する
	 * 
	 * @return giro_size
	 */
	public String getGiro_size() {
		return giro_size;
	}

	/**
	 * giro_size を設定する
	 * 
	 * @param giro_size
	 */
	public void setGiro_size(String giro_size) {
		this.giro_size = giro_size;
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

	/**
	 * bank_info_string を取得する
	 * 
	 * @return bank_info_string
	 */
	public String getBank_info_string() {
		return bank_info_string;
	}

	/**
	 * bank_info_string を設定する
	 * 
	 * @param bank_info_string
	 */
	public void setBank_info_string(String bank_info_string) {
		this.bank_info_string = bank_info_string;
	}

}