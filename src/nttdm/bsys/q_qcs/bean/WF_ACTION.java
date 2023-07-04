package nttdm.bsys.q_qcs.bean;

public class WF_ACTION {
	private String pic;
	private String appr_status;
	private String date_appr;
	private String appr_status_css;
	private String section_no;
	private String section_group;
	
	public String getSection_no() {
		return section_no;
	}

	public void setSection_no(String section_no) {
		this.section_no = section_no;
	}

	public String getSection_group() {
		return section_group;
	}

	public void setSection_group(String section_group) {
		this.section_group = section_group;
	}

	public String getAppr_status_css() {
		return appr_status_css;
	}

	public void setAppr_status_css(String appr_status_css) {
		this.appr_status_css = appr_status_css;
	}

	public String getAppr_status() {
		return appr_status;
	}

	public void setAppr_status(String appr_status) {
		this.appr_status = appr_status;
	}

	public String getDate_appr() {
		return date_appr;
	}

	public void setDate_appr(String date_appr) {
		this.date_appr = date_appr;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}
	
}
