package nttdm.bsys.q_qcs.bean;

public class WF_ACTION_SECTION {
	private String section_group;
	private String id_screen;
	private String section_no;
	private String action_type;
	private String id_ref;
	
	public String getId_ref() {
		return id_ref;
	}
	public void setId_ref(String id_ref) {
		this.id_ref = id_ref;
	}
	public String getSection_group() {
		return section_group;
	}
	public void setSection_group(String section_group) {
		this.section_group = section_group;
	}
	public String getId_screen() {
		return id_screen;
	}
	public void setId_screen(String id_screen) {
		this.id_screen = id_screen;
	}
	public String getSection_no() {
		return section_no;
	}
	public void setSection_no(String section_no) {
		this.section_no = section_no;
	}
	public String getAction_type() {
		return action_type;
	}
	public void setAction_type(String action_type) {
		this.action_type = action_type;
	}

}
