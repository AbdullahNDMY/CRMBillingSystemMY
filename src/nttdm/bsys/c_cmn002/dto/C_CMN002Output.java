package nttdm.bsys.c_cmn002.dto;

import java.io.Serializable;
import java.util.List; 

import nttdm.bsys.c_cmn002.bean.MessageBean;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;

public class C_CMN002Output implements Serializable{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2940134444628081974L;
	
	private List<MessageBean> array_notification = null;
    
    private List<MessageBean> array_mytasks= null;
    
    private List<MessageBean> array_sent = null;
    
    private int max_row=0;     
	
	private int notif_count;
	private int task_count;
	private int sent_count;	
	private String access_type;
	private String newmsg;
	
	public String getNewmsg() {
		return newmsg;
	}
	public void setNewmsg(String newmsg) {
		this.newmsg = newmsg;
	}
	public int getMax_row() {
		return max_row;
	}
	public void setMax_row(int max_row) {
		this.max_row = max_row;
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
	
	public String getAccess_type() {
		return access_type;
	}
	public void setAccess_type(String access_type) {
		this.access_type = access_type;
	}
	public int getSent_count() {
		return sent_count;
	}
	public void setSent_count(int sent_count) {
		this.sent_count = sent_count;
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
    
}
