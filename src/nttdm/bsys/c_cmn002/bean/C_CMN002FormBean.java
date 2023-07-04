package nttdm.bsys.c_cmn002.bean;

import java.util.List;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import jp.terasoluna.fw.web.struts.form.ValidatorActionFormEx;

public class C_CMN002FormBean extends ValidatorActionFormEx{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2210420460886983362L;
	
	private String id_user = null;	
	private String user_name = null;	
	private String id_div = null;	
	private String id_dept = null;
	private String display_date;
	private String subject;
	private String date_created;
	private String id_ref;
	private String msg_type;
	private String priority;
	private String end_date;
	private String id_msg;
	
	private BillingSystemUserValueObject uvo;	
	private List<MessageBean> array_notification = null;    
    private List<MessageBean> array_mytasks= null;    
    private List<MessageBean> array_sent = null;     
	private String no;
	private int max_row;
	
	private int notif_count;
	private int task_count;
	private int sent_count;	
	private String newmsg;
	private String access_type;
	public String getNewmsg() {
		return newmsg;
	}
	public void setNewmsg(String newmsg) {
		this.newmsg = newmsg;
	}
	
	public int getNotif_count() {
		return notif_count;
	}
	public void setNotif_count(int notif_count) {
		this.notif_count = notif_count;
	}
	public int getTask_count() {
		return task_count;
	}
	public void setTask_count(int task_count) {
		this.task_count = task_count;
	}
	public int getSent_count() {
		return sent_count;
	}
	public void setSent_count(int sent_count) {
		this.sent_count = sent_count;
	}
	public int getMax_row() {
		return max_row;
	}
	public void setMax_row(int max_row) {
		this.max_row = max_row;
	}

	public String getPriority() {
		return priority;
	}
	public String getNo() { 
		return no; 
	}
	public void setNo(String no) {		
		this.no = no;
	}
	public String getId_msg() {
		return id_msg;
	}


	public void setId_msg(String id_msg) {
		this.id_msg = id_msg;
	}


	public String getDisplay_date() {
		return display_date;
	}


	public void setDisplay_date(String display_date) {
		this.display_date = display_date;
	}


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}


	public String getDate_created() {
		return date_created;
	}


	public void setDate_created(String date_created) {
		this.date_created = date_created;
	}


	public String getAccess_type() {
		return access_type;
	}
	public void setAccess_type(String access_type) {
		this.access_type = access_type;
	}
	public String getId_ref() {
		return id_ref;
	}


	public void setId_ref(String id_ref) {
		this.id_ref = id_ref;
	}


	public String getMsg_type() {
		return msg_type;
	}


	public void setMsg_type(String msg_type) {
		this.msg_type = msg_type;
	}





	public void setPriority(String priority) {
		this.priority = priority;
	}


	public String getEnd_date() {
		return end_date;
	}


	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}


	public BillingSystemUserValueObject getUvo() {
		return uvo;
	}


	public void setUvo(BillingSystemUserValueObject uvo) {
		this.uvo = uvo;
	}


	public List<MessageBean> getArray_notification() {
		return array_notification;
	}


	public void setArray_notification(List<MessageBean> array_notification) {
		this.array_notification = array_notification;
	}


	public List<MessageBean> getArray_mytasks() {
		return array_mytasks;
	}


	public void setArray_mytasks(List<MessageBean> array_mytasks) {
		this.array_mytasks = array_mytasks;
	}


	public List<MessageBean> getArray_sent() {
		return array_sent;
	}


	public void setArray_sent(List<MessageBean> array_sent) {
		this.array_sent = array_sent;
	}


	public String getUser_name() {
		return user_name;
	}


	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}


	public String getId_dept() {
		return id_dept;
	}


	public void setId_dept(String id_dept) {
		this.id_dept = id_dept;
	}


	public void setId_user(String id_user) {
		this.id_user = id_user;
	}


	public String getId_user() {
		return id_user;
	}


	public void setId_div(String id_div) {
		this.id_div = id_div;
	}


	public String getId_div() {
		return id_div;
	}

}
