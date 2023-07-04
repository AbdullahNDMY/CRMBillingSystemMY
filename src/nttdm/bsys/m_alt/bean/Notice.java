package nttdm.bsys.m_alt.bean;

public class Notice {
	private String msg_type;
	private String to_msg;
	private String cc_msg;
	private String subject;
	private String reminder_date;
	private String start_date;
	private String end_date;	
	private String attachment;
	private String priority;
	private String msg;
	private String id_creator;
	private String id_msg;
	
	public String getId_msg() {
		return id_msg;
	}

	public void setId_msg(String id_msg) {
		this.id_msg = id_msg;
	}

	public String getId_creator() {
		return id_creator;
	}

	public void setId_creator(String id_creator) {
		this.id_creator = id_creator;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
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

	public String getReminder_date() {
		return reminder_date;
	}

	public void setReminder_date(String reminder_date) {
		this.reminder_date = reminder_date;
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

	public String getMsg_type() {
		return msg_type;
	}

	public void setMsg_type(String msg_type) {
		this.msg_type = msg_type;
	}
}
