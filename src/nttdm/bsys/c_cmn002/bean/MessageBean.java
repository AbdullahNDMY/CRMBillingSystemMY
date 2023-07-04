package nttdm.bsys.c_cmn002.bean;

import java.io.Serializable;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;

public class MessageBean  implements Serializable{
	private static final long serialVersionUID = -5538432094237818425L;
	private String display_date;
	private String subject;
	private String date_created;
	private String id_ref;
	private String id_screen;
	private String msg_type;
	private String priority;
	private String end_date;
	private String no;
	private int max_row;
	
	private int notif_count;
	private int task_count;
	private int sent_count;	
	private String newmsg;
	private BillingSystemUserValueObject uvo;
	private String id_login;
	private String[] idMsgList=new String[0];
	
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
	public String getNo() { 
		return no; 
	}
	public void setNo(String no) {		
		this.no = no;
	}
	private String id_msg;
	
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
	public String getPriority() {
		return priority;
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
	/**
	 * @param id_screen the id_screen to set
	 */
	public void setId_screen(String id_screen) {
		this.id_screen = id_screen;
	}
	/**
	 * @return the id_screen
	 */
	public String getId_screen() {
		return id_screen;
	}
	public BillingSystemUserValueObject getUvo() {
		return uvo;
	}
	public void setUvo(BillingSystemUserValueObject uvo) {
		this.uvo = uvo;
	}
	public String getId_login() {
		return id_login;
	}
	public void setId_login(String id_login) {
		this.id_login = id_login;
	}
    public String[] getIdMsgList() {
        return idMsgList;
    }
    public void setIdMsgList(String[] idMsgList) {
        this.idMsgList = idMsgList;
    }
	
	
}
