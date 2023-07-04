/*
 * @(#)M_PBMS01SaveOutput.java
 *
 *
 */
package nttdm.bsys.m_pbm.dto;

import java.io.Serializable;
import java.util.List;

/**
 * OutputDTO class.
 * 
 * @author helloworld
 */
public class M_PBMS01SaveOutput implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5261660471887498081L;

	/**
     * 
     */
	private String id_batch;

	/**
     * 
     */
	private String id_batch_new;

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
	private String message;

	/**
     * 
     */
	private List<java.util.Map<String, Object>> plan_batch;

	/**
     * 
     */
	private String plan_id_new;

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