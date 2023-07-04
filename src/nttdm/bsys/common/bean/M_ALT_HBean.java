package nttdm.bsys.common.bean;

import jp.terasoluna.fw.web.struts.form.ValidatorActionFormEx;

public class M_ALT_HBean extends ValidatorActionFormEx{
	private static final long serialVersionUID = -5538432094237818425L;
	private String id_msg;
	private String id_user;
	private String msg_type;
	private String id_ref;
	private String id_screen;
	private String id_section;
	private String status;
	private String priority;
	private String repeat_mode;
	private String start_date;
	private String end_date;
	private String reminder_date;
	private String reminder;
	private String to_msg;
	private String cc_msg;
	private String subject;
	private String msg;
	private String is_deleted;
	private String id_creator;
	private String date_created;
	private String date_updated;
	private String id_login;
	
	public String getId_user() {
		return id_user;
	}
	public void setId_user(String id_user) {
		this.id_user = id_user;
	}
	public String getId_msg() {
		return id_msg;
	}
	public void setId_msg(String id_msg) {
		this.id_msg = id_msg;
	}
	public String getMsg_type() {
		return msg_type;
	}
	public void setMsg_type(String msg_type) {
		this.msg_type = msg_type;
	}
	public String getId_ref() {
		return id_ref;
	}
	public void setId_ref(String id_ref) {
		this.id_ref = id_ref;
	}
	public String getId_screen() {
		return id_screen;
	}
	public void setId_screen(String id_screen) {
		this.id_screen = id_screen;
	}
	public String getId_section() {
		return id_section;
	}
	public void setId_section(String id_section) {
		this.id_section = id_section;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getRepeat_mode() {
		return repeat_mode;
	}
	public void setRepeat_mode(String repeat_mode) {
		this.repeat_mode = repeat_mode;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public String getReminder_date() {
		return reminder_date;
	}
	public void setReminder_date(String reminder_date) {
		this.reminder_date = reminder_date;
	}
	public String getReminder() {
		return reminder;
	}
	public void setReminder(String reminder) {
		this.reminder = reminder;
	}
	public String getTo_msg() {
		return to_msg;
	}
	public void setTo_msg(String to_msg) {
		this.to_msg = to_msg;
	}
	public String getCc_msg() {
		return cc_msg;
	}
	public void setCc_msg(String cc_msg) {
		this.cc_msg = cc_msg;
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
	public String getIs_deleted() {
		return is_deleted;
	}
	public void setIs_deleted(String is_deleted) {
		this.is_deleted = is_deleted;
	}
	public String getId_creator() {
		return id_creator;
	}
	public void setId_creator(String id_creator) {
		this.id_creator = id_creator;
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
