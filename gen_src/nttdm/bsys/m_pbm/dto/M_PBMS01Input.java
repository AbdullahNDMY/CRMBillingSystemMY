/*
 * @(#)M_PBMS01Input.java
 *
 *
 */
package nttdm.bsys.m_pbm.dto;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

/**
 * InputDTO class.
 * 
 * @author helloworld
 */
public class M_PBMS01Input implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2715551427172566969L;

	/**
     * 
     */
	private String id_batch;

	/**
     * 
     */
	private List<java.util.Map<String, Object>> search_info;

	/**
     * 
     */
	private String total_plan;

	/**
     * 
     */
	private String rp;

	/**
     * 
     */
	private String ip;

	/**
     * 
     */
	private String mode;

	/**
     * 
     */
	private String plan_id;

	/**
     * 
     */
	private String plan_name;

	/**
     * 
     */
	private String plan_desc;

	/**
     * 
     */
	private String batch_name;

	/**
     * 
     */
	private String enable_delete;

	/**
     * 
     */
	private String id_batch_new;

	/**
     * 
     */
	private List<java.util.Map<String, Object>> plan_list;

	/**
     * 
     */
	private String plan_id_new;

	/**
     * 
     */
	private List<java.util.Map<String, Object>> new_info;

	/**
     * 
     */
	private ArrayList cbo_uom;

	/**
     * 
     */
	private ArrayList cbo_code_value;

	/**
     * 
     */
	private String uom;

	/**
     * 
     */
	private String code_value;

	/**
     * 
     */
	private String id_plan_grp;

	/**
     * 
     */
	private String deleted;

	/**
     * 
     */
	private String message;

	/**
     * 
     */
	private List<java.util.Map<String, Object>> plan_batch;

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
	 * search_info を取得する
	 * 
	 * @return search_info
	 */
	public List<java.util.Map<String, Object>> getSearch_info() {
		return search_info;
	}

	/**
	 * search_info を設定する
	 * 
	 * @param search_info
	 */
	public void setSearch_info(List<java.util.Map<String, Object>> search_info) {
		this.search_info = search_info;
	}

	/**
	 * total_plan を取得する
	 * 
	 * @return total_plan
	 */
	public String getTotal_plan() {
		return total_plan;
	}

	/**
	 * total_plan を設定する
	 * 
	 * @param total_plan
	 */
	public void setTotal_plan(String total_plan) {
		this.total_plan = total_plan;
	}

	/**
	 * rp を取得する
	 * 
	 * @return rp
	 */
	public String getRp() {
		return rp;
	}

	/**
	 * rp を設定する
	 * 
	 * @param rp
	 */
	public void setRp(String rp) {
		this.rp = rp;
	}

	/**
	 * ip を取得する
	 * 
	 * @return ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * ip を設定する
	 * 
	 * @param ip
	 */
	public void setIp(String ip) {
		this.ip = ip;
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
	 * plan_name を取得する
	 * 
	 * @return plan_name
	 */
	public String getPlan_name() {
		return plan_name;
	}

	/**
	 * plan_name を設定する
	 * 
	 * @param plan_name
	 */
	public void setPlan_name(String plan_name) {
		this.plan_name = plan_name;
	}

	/**
	 * plan_desc を取得する
	 * 
	 * @return plan_desc
	 */
	public String getPlan_desc() {
		return plan_desc;
	}

	/**
	 * plan_desc を設定する
	 * 
	 * @param plan_desc
	 */
	public void setPlan_desc(String plan_desc) {
		this.plan_desc = plan_desc;
	}

	/**
	 * batch_name を取得する
	 * 
	 * @return batch_name
	 */
	public String getBatch_name() {
		return batch_name;
	}

	/**
	 * batch_name を設定する
	 * 
	 * @param batch_name
	 */
	public void setBatch_name(String batch_name) {
		this.batch_name = batch_name;
	}

	/**
	 * enable_delete を取得する
	 * 
	 * @return enable_delete
	 */
	public String getEnable_delete() {
		return enable_delete;
	}

	/**
	 * enable_delete を設定する
	 * 
	 * @param enable_delete
	 */
	public void setEnable_delete(String enable_delete) {
		this.enable_delete = enable_delete;
	}

	/**
	 * id_batch_new を取得する
	 * 
	 * @return id_batch_new
	 */
	public String getId_batch_new() {
		return id_batch_new;
	}

	/**
	 * id_batch_new を設定する
	 * 
	 * @param id_batch_new
	 */
	public void setId_batch_new(String id_batch_new) {
		this.id_batch_new = id_batch_new;
	}

	/**
	 * plan_list を取得する
	 * 
	 * @return plan_list
	 */
	public List<java.util.Map<String, Object>> getPlan_list() {
		return plan_list;
	}

	/**
	 * plan_list を設定する
	 * 
	 * @param plan_list
	 */
	public void setPlan_list(List<java.util.Map<String, Object>> plan_list) {
		this.plan_list = plan_list;
	}

	/**
	 * plan_id_new を取得する
	 * 
	 * @return plan_id_new
	 */
	public String getPlan_id_new() {
		return plan_id_new;
	}

	/**
	 * plan_id_new を設定する
	 * 
	 * @param plan_id_new
	 */
	public void setPlan_id_new(String plan_id_new) {
		this.plan_id_new = plan_id_new;
	}

	/**
	 * new_info を取得する
	 * 
	 * @return new_info
	 */
	public List<java.util.Map<String, Object>> getNew_info() {
		return new_info;
	}

	/**
	 * new_info を設定する
	 * 
	 * @param new_info
	 */
	public void setNew_info(List<java.util.Map<String, Object>> new_info) {
		this.new_info = new_info;
	}

	/**
	 * cbo_uom を取得する
	 * 
	 * @return cbo_uom
	 */
	public ArrayList getCbo_uom() {
		return cbo_uom;
	}

	/**
	 * cbo_uom を設定する
	 * 
	 * @param cbo_uom
	 */
	public void setCbo_uom(ArrayList cbo_uom) {
		this.cbo_uom = cbo_uom;
	}

	/**
	 * cbo_code_value を取得する
	 * 
	 * @return cbo_code_value
	 */
	public ArrayList getCbo_code_value() {
		return cbo_code_value;
	}

	/**
	 * cbo_code_value を設定する
	 * 
	 * @param cbo_code_value
	 */
	public void setCbo_code_value(ArrayList cbo_code_value) {
		this.cbo_code_value = cbo_code_value;
	}

	/**
	 * uom を取得する
	 * 
	 * @return uom
	 */
	public String getUom() {
		return uom;
	}

	/**
	 * uom を設定する
	 * 
	 * @param uom
	 */
	public void setUom(String uom) {
		this.uom = uom;
	}

	/**
	 * code_value を取得する
	 * 
	 * @return code_value
	 */
	public String getCode_value() {
		return code_value;
	}

	/**
	 * code_value を設定する
	 * 
	 * @param code_value
	 */
	public void setCode_value(String code_value) {
		this.code_value = code_value;
	}

	/**
	 * id_plan_grp を取得する
	 * 
	 * @return id_plan_grp
	 */
	public String getId_plan_grp() {
		return id_plan_grp;
	}

	/**
	 * id_plan_grp を設定する
	 * 
	 * @param id_plan_grp
	 */
	public void setId_plan_grp(String id_plan_grp) {
		this.id_plan_grp = id_plan_grp;
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

	/**
	 * plan_batch を取得する
	 * 
	 * @return plan_batch
	 */
	public List<java.util.Map<String, Object>> getPlan_batch() {
		return plan_batch;
	}

	/**
	 * plan_batch を設定する
	 * 
	 * @param plan_batch
	 */
	public void setPlan_batch(List<java.util.Map<String, Object>> plan_batch) {
		this.plan_batch = plan_batch;
	}

}