package nttdm.bsys.common.bean;

public class T_WF_APPROVAL {
	//private String id_wf_approver;
	private String id_ref;
	private String section_group;
	private String section_no;
	private String id_approver;
	private String date_appr;
	private String appr_status;
	private String id_forward_to;
	private String is_revised;
	private String original_approver;
	private String is_forwarded;
	private String is_escalated;
	private String id_action;
	private String ref_id_action;
	private String is_deleted;
	private String date_created;
	private String date_updated;
	private String id_login;
	private String new_appr_status;
	private String id_wf_approval;
	
	public String getId_wf_approval() {
		return id_wf_approval;
	}
	public void setId_wf_approval(String id_wf_approval) {
		this.id_wf_approval = id_wf_approval;
	}
	public String getNew_appr_status() {
		return new_appr_status;
	}
	public void setNew_appr_status(String new_appr_status) {
		this.new_appr_status = new_appr_status;
	}
	public String getOriginal_approver() {
		return original_approver;
	}
	public void setOriginal_approver(String original_approver) {
		this.original_approver = original_approver;
	}
	/*public String getId_wf_approver() {
		return id_wf_approver;
	}
	public void setId_wf_approver(String id_wf_approver) {
		this.id_wf_approver = id_wf_approver;
	}*/
	public String getId_ref() {
		return id_ref;
	}
	public void setId_ref(String id_ref) {
		this.id_ref = id_ref;
	}
	public String getDate_appr() {
		return date_appr;
	}
	public void setDate_appr(String date_appr) {
		this.date_appr = date_appr;
	}
	public String getId_forward_to() {
		return id_forward_to;
	}
	public void setId_forward_to(String id_forward_to) {
		this.id_forward_to = id_forward_to;
	}
	public String getIs_revised() {
		return is_revised;
	}
	public void setIs_revised(String is_revised) {
		this.is_revised = is_revised;
	}
	public String getIs_forwarded() {
		return is_forwarded;
	}
	public void setIs_forwarded(String is_forwarded) {
		this.is_forwarded = is_forwarded;
	}
	public String getIs_escalated() {
		return is_escalated;
	}
	public void setIs_escalated(String is_escalated) {
		this.is_escalated = is_escalated;
	}
	public String getId_action() {
		return id_action;
	}
	public void setId_action(String id_action) {
		this.id_action = id_action;
	}
	public String getIs_deleted() {
		return is_deleted;
	}
	public void setIs_deleted(String is_deleted) {
		this.is_deleted = is_deleted;
	}
	public String getDate_created() {
		return date_created;
	}
	public void setDate_created(String date_created) {
		this.date_created = date_created;
	}
	public String getDate_updated() {
		return date_updated;
	}
	public void setDate_updated(String date_updated) {
		this.date_updated = date_updated;
	}
	public String getId_login() {
		return id_login;
	}
	public void setId_login(String id_login) {
		this.id_login = id_login;
	}
	public String getId_approver() {
		return id_approver;
	}
	public void setId_approver(String id_approver) {
		this.id_approver = id_approver;
	}
	public String getSection_group() {
		return section_group;
	}
	public void setSection_group(String section_group) {
		this.section_group = section_group;
	}
	public String getSection_no() {
		return section_no;
	}
	public void setSection_no(String section_no) {
		this.section_no = section_no;
	}
	public String getAppr_status() {
		return appr_status;
	}
	public void setAppr_status(String appr_status) {
		this.appr_status = appr_status;
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
	
}
