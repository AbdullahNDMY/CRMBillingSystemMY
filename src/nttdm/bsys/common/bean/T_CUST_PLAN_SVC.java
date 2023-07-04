package nttdm.bsys.common.bean;

import jp.terasoluna.fw.web.struts.form.ValidatorActionFormEx;

public class T_CUST_PLAN_SVC extends ValidatorActionFormEx {
    private String idCustPlanSvc;
    private String id_cust_plan_svc;
    private String id_cust_plan_link;
    private String svc_level3;
    private String svc_level4;
    private String id_supplier;
    private String is_deleted;
    private String date_created;
    private String date_updated;
    private String id_login;
    private String id_audit;

    /**
     * 
     */
    private static final long serialVersionUID = 7915411401388312829L;

    /**
     * @param id_cust_plan_svc
     *            the id_cust_plan_svc to set
     */
    public void setId_cust_plan_svc(String id_cust_plan_svc) {
        this.id_cust_plan_svc = id_cust_plan_svc;
    }

    /**
     * @return the id_cust_plan_svc
     */
    public String getId_cust_plan_svc() {
        return id_cust_plan_svc;
    }

    /**
     * @param id_cust_plan_link
     *            the id_cust_plan_link to set
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
     * @param svc_level3
     *            the svc_level3 to set
     */
    public void setSvc_level3(String svc_level3) {
        this.svc_level3 = svc_level3;
    }

    /**
     * @return the svc_level3
     */
    public String getSvc_level3() {
        return svc_level3;
    }

    /**
     * @param svc_level4
     *            the svc_level4 to set
     */
    public void setSvc_level4(String svc_level4) {
        this.svc_level4 = svc_level4;
    }

    /**
     * @return the svc_level4
     */
    public String getSvc_level4() {
        return svc_level4;
    }

    /**
     * @param id_supplier
     *            the id_supplier to set
     */
    public void setId_supplier(String id_supplier) {
        this.id_supplier = id_supplier;
    }

    /**
     * @return the id_supplier
     */
    public String getId_supplier() {
        return id_supplier;
    }

    /**
     * @param is_deleted
     *            the is_deleted to set
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
     *            the date_created to set
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
     *            the date_updated to set
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
     *            the id_login to set
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
     *            the id_audit to set
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
     * @param idCustPlanSvc the idCustPlanSvc to set
     */
    public void setIdCustPlanSvc(String idCustPlanSvc) {
        this.idCustPlanSvc = idCustPlanSvc;
    }

    /**
     * @return the idCustPlanSvc
     */
    public String getIdCustPlanSvc() {
        return idCustPlanSvc;
    }

}
