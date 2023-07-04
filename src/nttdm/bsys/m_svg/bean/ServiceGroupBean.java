/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_SVG
 * SERVICE NAME   : M_SVG_S01
 * FUNCTION       : ServiceGroupBean
 * FILE NAME      : ServiceGroupBean.java
 * 
 * Copyright © 2011 NTTDATA Corporation
 *
 * History
 * 
 * 
 **********************************************************/
package nttdm.bsys.m_svg.bean;

/**
 * ServiceGroupBean<br>
 * <ul>
 * <li>A Bean to Express a group of services
 * </ul>
 * <br>
 * 
 * @author NTTData Vietnam
 * @version 1.0
 */
public class ServiceGroupBean {
    private String svc_grp = "";
    private String svc_grp_name = "";
    private String acc_code = "";
    private String origin_code = "";
    private String product_code = "";
    private String remark = "";
    private String id_login = "";
    private Integer id_audit = null;
    private String journal_no = "";
    private String description = "";
    private String is_used = "";

    public String getIs_used() {
		return is_used;
	}

	public void setIs_used(String is_used) {
		this.is_used = is_used;
	}

	public String getJournal_no() {
		return journal_no;
	}

	public void setJournal_no(String journal_no) {
		this.journal_no = journal_no;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getId_audit() {
        return id_audit;
    }

    public void setId_audit(Integer id_audit) {
        this.id_audit = id_audit;
    }

    public String getSvc_grp() {
        return svc_grp;
    }

    public void setSvc_grp(String svc_grp) {
        this.svc_grp = svc_grp;
    }

    public String getSvc_grp_name() {
        return svc_grp_name;
    }

    public void setSvc_grp_name(String svc_grp_name) {
        this.svc_grp_name = svc_grp_name;
    }

    public String getAcc_code() {
        return acc_code;
    }

    public void setAcc_code(String acc_code) {
        this.acc_code = acc_code;
    }

    public String getOrigin_code() {
        return origin_code;
    }

    public void setOrigin_code(String origin_code) {
        this.origin_code = origin_code;
    }

    public String getProduct_code() {

        if (product_code != null) {
            return product_code.trim();
        } else {
            return product_code;
        }
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getId_login() {
        return id_login;
    }

    public void setId_login(String id_login) {
        this.id_login = id_login;
    }
}
