package nttdm.bsys.common.bean;

import jp.terasoluna.fw.web.struts.form.ValidatorActionFormEx;

public class T_WF_DOCBean extends ValidatorActionFormEx{
	private static final long serialVersionUID = -5538432094237818425L;
	private String id_ref;
	private String section_group;
	private String id_doc;
	private String is_deleted;
	private String date_created;
	private String date_updated;
	private String id_login;
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
	public String getId_doc() {
		return id_doc;
	}
	public void setId_doc(String id_doc) {
		this.id_doc = id_doc;
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

}
