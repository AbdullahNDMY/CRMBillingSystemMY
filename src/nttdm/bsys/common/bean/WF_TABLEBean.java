package nttdm.bsys.common.bean;

import java.util.Date;

import jp.terasoluna.fw.web.struts.form.ValidatorActionFormEx;

public class WF_TABLEBean extends ValidatorActionFormEx{
	private static final long serialVersionUID = -5538432094237818425L;
	private String section_no ;
	private String section_desc;
	private String level_seq ;
	private String id_action ;
	private String ref_id_action ;
	private String action_type ;
	private String pic ;
	private String response_expire ;
	private String condition_primary ;
	private String condition_operator ;
	private String condition_secondary ;
	private String date_created1 ;
	private String date_updated1 ;
	private String id_login1 ;
	private String date_created2 ;
	private String date_updated2 ;
	private String id_login2 ;
	private String id_screen ;
	private String section_group ; 
	private String sequence_no ; 
	private String id_ref; 
	private Date sys;
	//private String id_screen;
	//private String section_no;
	//t_wf_section table
	private String id_user;
	private String id_tfr_user;
	private Date eff_date_from;
	private Date eff_date_to;
	private String date_created3;
	private String date_updated3;
	private String id_login3;
	private String appr_status;
	private String id_wf_approval;
	private String id_approver;
	private String subject;
	private String msg;
	private String type;
	private String action_user;
	private String id_status;
	private String original_approver;
	
	
	public String getId_status() {
		return id_status;
	}
	public void setId_status(String id_status) {
		this.id_status = id_status;
	}
	public String getAction_user() {
		return action_user;
	}
	public void setAction_user(String action_user) {
		this.action_user = action_user;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getId_approver() {
		return id_approver;
	}
	public void setId_approver(String id_approver) {
		this.id_approver = id_approver;
	}
	public String getAppr_status() {
		return appr_status;
	}
	public void setAppr_status(String appr_status) {
		this.appr_status = appr_status;
	}
	public String getId_wf_approval() {
		return id_wf_approval;
	}
	public void setId_wf_approval(String id_wf_approval) {
		this.id_wf_approval = id_wf_approval;
}
	public Date getSys() {
		return sys;
	}
	public void setSys(Date sys) {
		this.sys = sys;
	}
	public String getId_user() {
		return id_user;
	}
	public void setId_user(String id_user) {
		this.id_user = id_user;
	}
	public String getId_tfr_user() {
		return id_tfr_user;
	}
	public void setId_tfr_user(String id_tfr_user) {
		this.id_tfr_user = id_tfr_user;
	}
	public Date getEff_date_from() {
		return eff_date_from;
	}
	public void setEff_date_from(Date eff_date_from) {
		this.eff_date_from = eff_date_from;
	}
	public Date getEff_date_to() {
		return eff_date_to;
	}
	public void setEff_date_to(Date eff_date_to) {
		this.eff_date_to = eff_date_to;
	}
	public String getDate_created3() {
		return date_created3;
	}
	public void setDate_created3(String date_created3) {
		this.date_created3 = date_created3;
	}
	public String getDate_updated3() {
		return date_updated3;
	}
	public void setDate_updated3(String date_updated3) {
		this.date_updated3 = date_updated3;
	}
	public String getId_login3() {
		return id_login3;
	}
	public void setId_login3(String id_login3) {
		this.id_login3 = id_login3;
	}
	public String getSection_no() {
		return section_no;
	}
	public void setSection_no(String section_no) {
		this.section_no = section_no;
	}
	public String getLevel_seq() {
		return level_seq;
	}
	public void setLevel_seq(String level_seq) {
		this.level_seq = level_seq;
	}
	public String getId_action() {
		return id_action;
	}
	public void setId_action(String id_action) {
		this.id_action = id_action;
	}
	public String getAction_type() {
		return action_type;
	}
	public void setAction_type(String action_type) {
		this.action_type = action_type;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getResponse_expire() {
		return response_expire;
	}
	public void setResponse_expire(String response_expire) {
		this.response_expire = response_expire;
	}
	public String getCondition_primary() {
		return condition_primary;
	}
	public void setCondition_primary(String condition_primary) {
		this.condition_primary = condition_primary;
	}
	public String getCondition_operator() {
		return condition_operator;
	}
	public void setCondition_operator(String condition_operator) {
		this.condition_operator = condition_operator;
	}
	public String getCondition_secondary() {
		return condition_secondary;
	}
	public void setCondition_secondary(String condition_secondary) {
		this.condition_secondary = condition_secondary;
	} 
	public String getId_screen() {
		return id_screen;
	}
	public void setId_screen(String id_screen) {
		this.id_screen = id_screen;
	}
	public String getSection_group() {
		return section_group;
	}
	public void setSection_group(String section_group) {
		this.section_group = section_group;
	}
	public String getSequence_no() {
		return sequence_no;
	}
	public void setSequence_no(String sequence_no) {
		this.sequence_no = sequence_no;
	}
	public String getId_ref() {
		return id_ref;
	}
	public void setId_ref(String id_ref) {
		this.id_ref = id_ref;
	}
	public String getDate_created1() {
		return date_created1;
	}
	public void setDate_created1(String date_created1) {
		this.date_created1 = date_created1;
	}
	public String getDate_updated1() {
		return date_updated1;
	}
	public void setDate_updated1(String date_updated1) {
		this.date_updated1 = date_updated1;
	}
	public String getId_login1() {
		return id_login1;
	}
	public void setId_login1(String id_login1) {
		this.id_login1 = id_login1;
	}
	public String getDate_created2() {
		return date_created2;
	}
	public void setDate_created2(String date_created2) {
		this.date_created2 = date_created2;
	}
	public String getDate_updated2() {
		return date_updated2;
	}
	public void setDate_updated2(String date_updated2) {
		this.date_updated2 = date_updated2;
	}
	public String getId_login2() {
		return id_login2;
	}
	public void setId_login2(String id_login2) {
		this.id_login2 = id_login2;
	}
	/**
	 * @param original_approver the original_approver to set
	 */
	public void setOriginal_approver(String original_approver) {
		this.original_approver = original_approver;
	}
	/**
	 * @return the original_approver
	 */
	public String getOriginal_approver() {
		return original_approver;
	}
	/**
	 * @param ref_id_action the ref_id_action to set
	 */
	public void setRef_id_action(String ref_id_action) {
		this.ref_id_action = ref_id_action;
	}
	/**
	 * @return the ref_id_action
	 */
	public String getRef_id_action() {
		return ref_id_action;
	}
	/**
	 * @param section_desc the section_desc to set
	 */
	public void setSection_desc(String section_desc) {
		this.section_desc = section_desc;
	}
	/**
	 * @return the section_desc
	 */
	public String getSection_desc() {
		return section_desc;
	}
	
}
