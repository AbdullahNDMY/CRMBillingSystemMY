/**
 * @(#)T_CUST_PLAN_LINK.java
 * Billing System
 * Version 1.00
 * Created Aug 24, 2011
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All
 * Rights Reserved.
 */
package nttdm.bsys.common.bean;

import jp.terasoluna.fw.web.struts.form.ValidatorActionFormEx;

/**
 * @author qinjinh
 */
public class T_CUST_PLAN_LINK extends ValidatorActionFormEx {
    private String idCustPlanLink;
    private String id_cust_plan_link;
    private String id_cust_plan_grp;
    private String item_type;
    private String item_desc;
    private String quantity;
    private String unit_price;
    private String total_amount;
    private String job_no;
    private String id_plan;
    private String plan_name;
    private String plan_desc;
    private String id_plan_grp;
    private String item_grp_name;
    private String svc_level1;
    private String svc_level2;
    private String rate_type;
    private String rate_mode;
    private String rate;
    private String apply_gst;
    private String is_deleted;
    private String date_created;
    private String date_updated;
    private String id_login;
    private String id_audit;

    /**
     * 
     */
    private static final long serialVersionUID = -7627448332014652331L;

    /**
     * @param id_cust_plan_link
     * the id_cust_plan_link to set
     */
    public void setId_cust_plan_link(String id_cust_plan_link) {
        this.id_cust_plan_link = id_cust_plan_link;
    }

    /**
     * @return the id_cust_plan_link
     */
    public String getId_cust_plan_link() {
        return id_cust_plan_link;
    }

    /**
     * @param id_cust_plan_grp
     * the id_cust_plan_grp to set
     */
    public void setId_cust_plan_grp(String id_cust_plan_grp) {
        this.id_cust_plan_grp = id_cust_plan_grp;
    }

    /**
     * @return the id_cust_plan_grp
     */
    public String getId_cust_plan_grp() {
        return id_cust_plan_grp;
    }

    /**
     * @param item_type
     * the item_type to set
     */
    public void setItem_type(String item_type) {
        this.item_type = item_type;
    }

    /**
     * @return the item_type
     */
    public String getItem_type() {
        return item_type;
    }

    /**
     * @param item_desc
     * the item_desc to set
     */
    public void setItem_desc(String item_desc) {
        this.item_desc = item_desc;
    }

    /**
     * @return the item_desc
     */
    public String getItem_desc() {
        return item_desc;
    }

    /**
     * @param quantity
     * the quantity to set
     */
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the quantity
     */
    public String getQuantity() {
        return quantity;
    }

    /**
     * @param unit_price
     * the unit_price to set
     */
    public void setUnit_price(String unit_price) {
        this.unit_price = unit_price;
    }

    /**
     * @return the unit_price
     */
    public String getUnit_price() {
        return unit_price;
    }

    /**
     * @param total_amount
     * the total_amount to set
     */
    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    /**
     * @return the total_amount
     */
    public String getTotal_amount() {
        return total_amount;
    }

    /**
     * @param job_no
     * the job_no to set
     */
    public void setJob_no(String job_no) {
        this.job_no = job_no;
    }

    /**
     * @return the job_no
     */
    public String getJob_no() {
        return job_no;
    }

    /**
     * @param id_plan
     * the id_plan to set
     */
    public void setId_plan(String id_plan) {
        this.id_plan = id_plan;
    }

    /**
     * @return the id_plan
     */
    public String getId_plan() {
        return id_plan;
    }

    /**
     * @param plan_name
     * the plan_name to set
     */
    public void setPlan_name(String plan_name) {
        this.plan_name = plan_name;
    }

    /**
     * @return the plan_name
     */
    public String getPlan_name() {
        return plan_name;
    }

    /**
     * @param plan_desc
     * the plan_desc to set
     */
    public void setPlan_desc(String plan_desc) {
        this.plan_desc = plan_desc;
    }

    /**
     * @return the plan_desc
     */
    public String getPlan_desc() {
        return plan_desc;
    }

    /**
     * @param id_plan_grp
     * the id_plan_grp to set
     */
    public void setId_plan_grp(String id_plan_grp) {
        this.id_plan_grp = id_plan_grp;
    }

    /**
     * @return the id_plan_grp
     */
    public String getId_plan_grp() {
        return id_plan_grp;
    }

    /**
     * @param item_grp_name
     * the item_grp_name to set
     */
    public void setItem_grp_name(String item_grp_name) {
        this.item_grp_name = item_grp_name;
    }

    /**
     * @return the item_grp_name
     */
    public String getItem_grp_name() {
        return item_grp_name;
    }

    /**
     * @param svc_level1
     * the svc_level1 to set
     */
    public void setSvc_level1(String svc_level1) {
        this.svc_level1 = svc_level1;
    }

    /**
     * @return the svc_level1
     */
    public String getSvc_level1() {
        return svc_level1;
    }

    /**
     * @param svc_level2
     * the svc_level2 to set
     */
    public void setSvc_level2(String svc_level2) {
        this.svc_level2 = svc_level2;
    }

    /**
     * @return the svc_level2
     */
    public String getSvc_level2() {
        return svc_level2;
    }

    /**
     * @param rate_type
     * the rate_type to set
     */
    public void setRate_type(String rate_type) {
        this.rate_type = rate_type;
    }

    /**
     * @return the rate_type
     */
    public String getRate_type() {
        return rate_type;
    }

    /**
     * @param rate_mode
     * the rate_mode to set
     */
    public void setRate_mode(String rate_mode) {
        this.rate_mode = rate_mode;
    }

    /**
     * @return the rate_mode
     */
    public String getRate_mode() {
        return rate_mode;
    }

    /**
     * @param rate
     * the rate to set
     */
    public void setRate(String rate) {
        this.rate = rate;
    }

    /**
     * @return the rate
     */
    public String getRate() {
        return rate;
    }

    /**
     * @param apply_gst
     * the apply_gst to set
     */
    public void setApply_gst(String apply_gst) {
        this.apply_gst = apply_gst;
    }

    /**
     * @return the apply_gst
     */
    public String getApply_gst() {
        return apply_gst;
    }

    /**
     * @param is_deleted
     * the is_deleted to set
     */
    public void setIs_deleted(String is_deleted) {
        this.is_deleted = is_deleted;
    }

    /**
     * @return the is_deleted
     */
    public String getIs_deleted() {
        return is_deleted;
    }

    /**
     * @param date_created
     * the date_created to set
     */
    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    /**
     * @return the date_created
     */
    public String getDate_created() {
        return date_created;
    }

    /**
     * @param date_updated
     * the date_updated to set
     */
    public void setDate_updated(String date_updated) {
        this.date_updated = date_updated;
    }

    /**
     * @return the date_updated
     */
    public String getDate_updated() {
        return date_updated;
    }

    /**
     * @param id_login
     * the id_login to set
     */
    public void setId_login(String id_login) {
        this.id_login = id_login;
    }

    /**
     * @return the id_login
     */
    public String getId_login() {
        return id_login;
    }

    /**
     * @param id_audit
     * the id_audit to set
     */
    public void setId_audit(String id_audit) {
        this.id_audit = id_audit;
    }

    /**
     * @return the id_audit
     */
    public String getId_audit() {
        return id_audit;
    }

    /**
     * @param idCustPlanLink
     * the idCustPlanLink to set
     */
    public void setIdCustPlanLink(String idCustPlanLink) {
        this.idCustPlanLink = idCustPlanLink;
    }

    /**
     * @return the idCustPlanLink
     */
    public String getIdCustPlanLink() {
        return idCustPlanLink;
    }

}
