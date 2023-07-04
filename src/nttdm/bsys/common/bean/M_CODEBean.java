package nttdm.bsys.common.bean;

import jp.terasoluna.fw.web.struts.form.ValidatorActionFormEx;

public class M_CODEBean extends ValidatorActionFormEx{
	private static final long serialVersionUID = -5538432094237818425L;
	private String id_login;
	private String cur_val;
	private String id_code;
	private String id_sub_code;
	private String type_val;
	private String init_val;
	private String id_date;
	private String is_deleted;
	private String date_created;
	private String date_updated;
	private String reset_no;
	
	public String getReset_no() {
		return reset_no;
	}
	public void setReset_no(String reset_no) {
		this.reset_no = reset_no;
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
	public String getId_date() {
		return id_date;
	}
	public void setId_date(String id_date) {
		this.id_date = id_date;
	}
	public String getId_login() {
		return id_login;
	}
	public void setId_login(String id_login) {
		this.id_login = id_login;
	}
	public String getCur_val() {
		if(cur_val==null)
		{
			return "";
		}
		return cur_val;
	}
	public void setCur_val(String cur_val) {
		this.cur_val = cur_val;
	}
	public String getId_code() {
		return id_code;
	}
	public void setId_code(String id_code) {
		this.id_code = id_code;
	}
	public String getId_sub_code() {
		return id_sub_code;
	}
	public void setId_sub_code(String id_sub_code) {
		this.id_sub_code = id_sub_code;
	}
	public String getType_val() {
		return type_val;
	}
	public void setType_val(String type_val) {
		this.type_val = type_val;
	}
	public String getInit_val() {
		if(init_val==null)
		{
			return "";
		}
		return init_val;
	}
	public void setInit_val(String init_val) {
		this.init_val = init_val;
	}
	
}
