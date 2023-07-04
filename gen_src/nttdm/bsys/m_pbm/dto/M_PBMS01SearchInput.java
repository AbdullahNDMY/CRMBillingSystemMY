/*
 * @(#)M_PBMS01SearchInput.java
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
 * @author khaln
 */
public class M_PBMS01SearchInput implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7426480322456995576L;

	/**
     * 
     */
	private String id_batch;
	
	/**
     * 
     */
	private String bill_currency;
	
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
	private List<java.util.Map<String, Object>> search_info;
	
    /**
     * 
     */
    private List<java.util.Map<String, Object>> search_OptSvcinfo;
    
	/**
     * 
     */
	private String plan_id_new;

	/**
     * 
     */
	private List<java.util.Map<String, Object>> plan_list;

	/**
     * 
     */
	private String id_batch_new;

	/**
     * 
     */
	private List<java.util.Map<String, Object>> new_info;
	
    /**
     * 
     */
    private List<java.util.Map<String, Object>> new_OptSvcinfo;

	/**
     * 
     */
	private ArrayList cbo_uom;

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
	private String message;

    private String new_data;
    private String edit_data;
    private String chkCheckAMA;
    private String cboOptSvcAMA;
    private String chkCheckAMQ;
    private String cboOptSvcAMQ;
    private String chkCheckVRS;
    private String cboOptSvcVRS;
    private String chkCheckASP;
    private String cboOptSvcASP;
    private String chkCheckJMG;
    private String cboOptSvcJMG;
    private String errorMode;
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
	 * bill_currency を取得する
	 * 
	 * @return bill_currency
	 */
	public String getBill_currency() {
		return bill_currency;
	}

	 /** currency を設定する
	 * 
	 * @param currency
	 */
	public void setBill_currency(String bill_currency) {
		this.bill_currency = bill_currency;
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

    public List<java.util.Map<String, Object>> getSearch_OptSvcinfo() {
        return search_OptSvcinfo;
    }

    public void setSearch_OptSvcinfo(List<java.util.Map<String, Object>> search_OptSvcinfo) {
        this.search_OptSvcinfo = search_OptSvcinfo;
    }

    public List<java.util.Map<String, Object>> getNew_OptSvcinfo() {
        return new_OptSvcinfo;
    }

    public void setNew_OptSvcinfo(List<java.util.Map<String, Object>> new_OptSvcinfo) {
        this.new_OptSvcinfo = new_OptSvcinfo;
    }

    public String getChkCheckAMA() {
        return chkCheckAMA;
    }

    public void setChkCheckAMA(String chkCheckAMA) {
        this.chkCheckAMA = chkCheckAMA;
    }

    public String getCboOptSvcAMA() {
        return cboOptSvcAMA;
    }

    public void setCboOptSvcAMA(String cboOptSvcAMA) {
        this.cboOptSvcAMA = cboOptSvcAMA;
    }

    public String getChkCheckAMQ() {
        return chkCheckAMQ;
    }

    public void setChkCheckAMQ(String chkCheckAMQ) {
        this.chkCheckAMQ = chkCheckAMQ;
    }

    public String getCboOptSvcAMQ() {
        return cboOptSvcAMQ;
    }

    public void setCboOptSvcAMQ(String cboOptSvcAMQ) {
        this.cboOptSvcAMQ = cboOptSvcAMQ;
    }

    public String getChkCheckVRS() {
        return chkCheckVRS;
    }

    public void setChkCheckVRS(String chkCheckVRS) {
        this.chkCheckVRS = chkCheckVRS;
    }

    public String getCboOptSvcVRS() {
        return cboOptSvcVRS;
    }

    public void setCboOptSvcVRS(String cboOptSvcVRS) {
        this.cboOptSvcVRS = cboOptSvcVRS;
    }

    public String getChkCheckASP() {
        return chkCheckASP;
    }

    public void setChkCheckASP(String chkCheckASP) {
        this.chkCheckASP = chkCheckASP;
    }

    public String getCboOptSvcASP() {
        return cboOptSvcASP;
    }

    public void setCboOptSvcASP(String cboOptSvcASP) {
        this.cboOptSvcASP = cboOptSvcASP;
    }

    public String getChkCheckJMG() {
        return chkCheckJMG;
    }

    public void setChkCheckJMG(String chkCheckJMG) {
        this.chkCheckJMG = chkCheckJMG;
    }

    public String getCboOptSvcJMG() {
        return cboOptSvcJMG;
    }

    public void setCboOptSvcJMG(String cboOptSvcJMG) {
        this.cboOptSvcJMG = cboOptSvcJMG;
    }

    public String getNew_data() {
        return new_data;
    }

    public void setNew_data(String new_data) {
        this.new_data = new_data;
    }

    public String getEdit_data() {
        return edit_data;
    }

    public void setEdit_data(String edit_data) {
        this.edit_data = edit_data;
    }

    public String getErrorMode() {
        return errorMode;
    }

    public void setErrorMode(String errorMode) {
        this.errorMode = errorMode;
    }

}