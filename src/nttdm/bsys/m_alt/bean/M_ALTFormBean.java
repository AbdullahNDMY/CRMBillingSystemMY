/*
 * @(#)M_ALTFormBean.java
 *
 *
 */
package nttdm.bsys.m_alt.bean;

import java.io.Serializable;
import java.util.List;

import org.apache.struts.upload.FormFile;
import jp.terasoluna.fw.web.struts.form.ValidatorActionFormEx;

/**
 * ActionForm class.
 * 
 * @author ss051
 */
public class M_ALTFormBean extends ValidatorActionFormEx implements Serializable{

	private static final long serialVersionUID = -6845144270623150915L;

	private String msg_type;
	private String msg_type_label;
	private String to_msg;
	private String to_msg_name;
	private String cc_msg;
	private String cc_msg_name;
	private String subject;
	private String reminder_date;
	private String start_date;
	private String end_date;	
	private String attachment;
	private String priority;
	private String msg;
	private String id_creator;
	private String creator_name;
	private String id_msg;
	private String reminder_chk;
	private String importance_chk="0";
	private String screen_mod;
	private String click_event;
	
	private String msg_type_hidden;
	private String to_msg_hidden;
	private String cc_msg_hidden;
	private String subject_hidden;
	private String reminder_date_hidden;
	private String start_date_hidden;
	private String end_date_hidden;	
	private String priority_hidden;
	private String msg_hidden;
	private String reminder_chk_hidden;
	private String importance_chk_hidden;
	private FormFile[] listFile = new FormFile[10];
	private List<FileInfo> fileInfos = null;
	private long file_id;
	private int user_access=2;
	private String[] listFileIdOld;
	private String screen_mode_forward="0";	

	public String[] getListFileIdOld() {
		return listFileIdOld;
	}

	public void setListFileIdOld(String[] listFileIdOld) {
		this.listFileIdOld = listFileIdOld;
	}

	public String getScreen_mode_forward() {
		return screen_mode_forward;
	}

	public void setScreen_mode_forward(String screen_mode_forward) {
		this.screen_mode_forward = screen_mode_forward;
	}
	public String getMsg_type_label() {
		return msg_type_label;
	}

	public void setMsg_type_label(String msg_type_label) {
		this.msg_type_label = msg_type_label;
	}

	public int getUser_access() {
		return user_access;
	}

	public void setUser_access(int user_access) {
		this.user_access = user_access;
	}

	public long getFile_id() {
		return file_id;
	}

	public void setFile_id(long file_id) {
		this.file_id = file_id;
	}

	public List<FileInfo> getFileInfos() {
		return fileInfos;
	}

	public void setFileInfos(List<FileInfo> fileInfos) {
		this.fileInfos = fileInfos;
	}

	public FormFile[] getListFile() {
		return listFile;
	}

	public void setListFile(FormFile[] listFile) {
		this.listFile = listFile;
	}

	private String lst_user;
	
	public String getLst_user() {
		return lst_user;
	}

	public void setLst_user(String lst_user) {
		this.lst_user = lst_user;
	}

	public String getMsg_type_hidden() {
		return msg_type_hidden;
	}

	public void setMsg_type_hidden(String msg_type_hidden) {
		this.msg_type_hidden = msg_type_hidden;
	}

	public String getTo_msg_hidden() {
		return to_msg_hidden;
	}

	public void setTo_msg_hidden(String to_msg_hidden) {
		this.to_msg_hidden = to_msg_hidden;
	}

	public String getCc_msg_hidden() {
		return cc_msg_hidden;
	}

	public void setCc_msg_hidden(String cc_msg_hidden) {
		this.cc_msg_hidden = cc_msg_hidden;
	}

	public String getSubject_hidden() {
		return subject_hidden;
	}

	public void setSubject_hidden(String subject_hidden) {
		this.subject_hidden = subject_hidden;
	}

	public String getReminder_date_hidden() {
		return reminder_date_hidden;
	}

	public void setReminder_date_hidden(String reminder_date_hidden) {
		this.reminder_date_hidden = reminder_date_hidden;
	}

	public String getStart_date_hidden() {
		return start_date_hidden;
	}

	public void setStart_date_hidden(String start_date_hidden) {
		this.start_date_hidden = start_date_hidden;
	}

	public String getEnd_date_hidden() {
		return end_date_hidden;
	}

	public void setEnd_date_hidden(String end_date_hidden) {
		this.end_date_hidden = end_date_hidden;
	}

	public String getPriority_hidden() {
		return priority_hidden;
	}

	public void setPriority_hidden(String priority_hidden) {
		this.priority_hidden = priority_hidden;
	}

	public String getMsg_hidden() {
		return msg_hidden;
	}

	public void setMsg_hidden(String msg_hidden) {
		this.msg_hidden = msg_hidden;
	}

	public String getReminder_chk_hidden() {
		return reminder_chk_hidden;
	}

	public void setReminder_chk_hidden(String reminder_chk_hidden) {
		this.reminder_chk_hidden = reminder_chk_hidden;
	}

	public String getImportance_chk_hidden() {
		return importance_chk_hidden;
	}

	public void setImportance_chk_hidden(String importance_chk_hidden) {
		this.importance_chk_hidden = importance_chk_hidden;
	}

	public String getId_msg() {
		return id_msg;
	}

	public void setId_msg(String id_msg) {
		this.id_msg = id_msg;
	}

	public String getClick_event() {
		return click_event;
	}

	public void setClick_event(String click_event) {
		this.click_event = click_event;
	}

	public String getScreen_mod() {
		return screen_mod;
	}

	public void setScreen_mod(String screen_mod) {
		this.screen_mod = screen_mod;
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

	public String getImportance_chk() {
		return importance_chk;
	}

	public void setImportance_chk(String importance_chk) {
		this.importance_chk = importance_chk;
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

	public String getReminder_chk() {
		return reminder_chk;
	}

	public void setReminder_chk(String reminder_chk) {
		this.reminder_chk = reminder_chk;
	}

	public String getMsg_type() {
		return msg_type;
	}

	public void setMsg_type(String msg_type) {
		this.msg_type = msg_type;
	}

	public String getTo_msg_name() {
		return to_msg_name;
	}

	public void setTo_msg_name(String to_msg_name) {
		this.to_msg_name = to_msg_name;
	}

	public String getCc_msg_name() {
		return cc_msg_name;
	}

	public void setCc_msg_name(String cc_msg_name) {
		this.cc_msg_name = cc_msg_name;
	}

	public String getCreator_name() {
		return creator_name;
	}

	public void setCreator_name(String creator_name) {
		this.creator_name = creator_name;
	}
}