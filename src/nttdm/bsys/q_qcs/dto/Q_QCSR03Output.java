package nttdm.bsys.q_qcs.dto;

public class Q_QCSR03Output {
	private String clickEvent;
	private String id_ref;
	private String showPopalt;
	private String section_group;
	private String id_wf_approval;
	private String section_no;
	
	public String getSection_group() {
		return section_group;
	}
	public void setSection_group(String section_group) {
		this.section_group = section_group;
	}
	public String getId_wf_approval() {
		return id_wf_approval;
	}
	public void setId_wf_approval(String id_wf_approval) {
		this.id_wf_approval = id_wf_approval;
	}
	public String getSection_no() {
		return section_no;
	}
	public void setSection_no(String section_no) {
		this.section_no = section_no;
	}
	public String getShowPopalt() {
		return showPopalt;
	}
	public void setShowPopalt(String showPopalt) {
		this.showPopalt = showPopalt;
	}
	
	public String getId_ref() {
		return id_ref;
	}

	public void setId_ref(String id_ref) {
		this.id_ref = id_ref;
	}

	public String getClickEvent() {
		return clickEvent;
	}

	public void setClickEvent(String clickEvent) {
		this.clickEvent = clickEvent;
	}
	
}
