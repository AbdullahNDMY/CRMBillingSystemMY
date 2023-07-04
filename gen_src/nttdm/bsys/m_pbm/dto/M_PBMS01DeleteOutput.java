/*
 * @(#)M_PBMS01DeleteOutput.java
 *
 *
 */
package nttdm.bsys.m_pbm.dto;

import java.io.Serializable;

/**
 * OutputDTO class.
 * 
 * @author helloworld
 */
public class M_PBMS01DeleteOutput implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6626591071156783553L;

	/**
     * 
     */
	private String id_batch;

	/**
     * 
     */
	private String plan_id;

	/**
     * 
     */
	private String deleted;

	/**
     * 
     */
	private String mode;

	/**
     * 
     */
	private String message;

	/**
	 * id_batch を取得する
	 * 
	 * @return id_batch
	 */
	public String getId_batch() {
		return id_batch;
	}

	/**
	 * id_batch を設定する
	 * 
	 * @param id_batch
	 */
	public void setId_batch(String id_batch) {
		this.id_batch = id_batch;
	}

	/**
	 * plan_id を取得する
	 * 
	 * @return plan_id
	 */
	public String getPlan_id() {
		return plan_id;
	}

	/**
	 * plan_id を設定する
	 * 
	 * @param plan_id
	 */
	public void setPlan_id(String plan_id) {
		this.plan_id = plan_id;
	}

	/**
	 * deleted を取得する
	 * 
	 * @return deleted
	 */
	public String getDeleted() {
		return deleted;
	}

	/**
	 * deleted を設定する
	 * 
	 * @param deleted
	 */
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	/**
	 * mode を取得する
	 * 
	 * @return mode
	 */
	public String getMode() {
		return mode;
	}

	/**
	 * mode を設定する
	 * 
	 * @param mode
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}

	/**
	 * message を取得する
	 * 
	 * @return message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * message を設定する
	 * 
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}