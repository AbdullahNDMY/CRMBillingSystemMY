/*
 * @(#)M_ALTR01BLogicInput.java
 *
 *
 */
package nttdm.bsys.m_alt.dto;

import java.io.Serializable;
import java.util.List;

import nttdm.bsys.m_alt.bean.FileInfo;

/**
 * <div>
 * Output class
 * </div>
 * @author Phu.Nguyen
 *
 */
public class M_ALTR01Output implements Serializable {

	private static final long serialVersionUID = -5145082729983505266L;
	/**
	 * <div>lst_user</div>
	 */
	private String lst_user;
	private int user_access;
	
	public int getUser_access() {
		return user_access;
	}

	public void setUser_access(int user_access) {
		this.user_access = user_access;
	}

	public String getLst_user() {
		return lst_user;
	}

	public void setLst_user(String lst_user) {
		this.lst_user = lst_user;
	}
	
	
	/**
	 * <div>to_msg</div>
	 */
	private String to_msg;
	/**
	 * <div>to_msg_name</div>
	 */
	private String to_msg_name;
	/**
	 * <div>cc_msg</div>
	 */
	private String cc_msg;
	/**
	 * <div>cc_msg_name</div>
	 */
	private String cc_msg_name;
	/**
	 * <div>subject</div>
	 */
	private String subject;
	/**
	 * <div>reminder_date</div>
	 */
	private String reminder_date;
	/**
	 * <div>start_date</div>
	 */
	private String start_date;
	/**
	 * <div>end_date</div>
	 */
	private String end_date;	
	/**
	 * <div>attachment</div>
	 */
	private String attachment;
	/**
	 * <div>priority</div>
	 */
	private String priority;
	/**
	 * <div>msg</div>
	 */
	private String msg;
	/**
	 * <div>id_creator</div>
	 */
	private String id_creator;
	/**
	 * <div>creator_name</div>
	 */
	private String creator_name;
	/**
	 * <div>id_msg</div>
	 */
	private String id_msg;
	/**
	 * <div>reminder_chk</div>
	 */
	private String reminder_chk;
	/**
	 * <div>importance_chk</div>
	 */
	private String importance_chk;
	/**
	 * <div>screen_mod</div>
	 */
	private String screen_mod;
	/**
	 * <div>msg_type</div>
	 */
	private String msg_type;
	
	private String screen_mode_forward;
	public String getScreen_mode_forward() {
		return screen_mode_forward;
	}

	public void setScreen_mode_forward(String screen_mode_forward) {
		this.screen_mode_forward = screen_mode_forward;
	}


	/**
	 * <div>msg_type_hidden</div>
	 */
	private String msg_type_hidden;
	/**
	 * <div>to_msg_hidden</div>
	 */
	private String to_msg_hidden;
	/**
	 * <div>cc_msg_hidden</div>
	 */
	private String cc_msg_hidden;
	/**
	 * <div>subject_hidden</div>
	 */
	private String subject_hidden;
	/**
	 * <div>reminder_date_hidden</div>
	 */
	private String reminder_date_hidden;
	/**
	 * <div>start_date_hidden</div>
	 */
	private String start_date_hidden;
	/**
	 * <div>end_date_hidden</div>
	 */
	private String end_date_hidden;	
	/**
	 * <div>priority_hidden</div>
	 */
	private String priority_hidden;
	/**
	 * <div>msg_hidden</div>
	 */
	private String msg_hidden;
	/**
	 * <div>reminder_chk_hidden</div>
	 */
	private String reminder_chk_hidden;
	/**
	 * <div>importance_chk_hidden</div>
	 */
	private String importance_chk_hidden;
	
	private List<FileInfo> fileInfos = null;
	
	public List<FileInfo> getFileInfos() {
		return fileInfos;
	}

	public void setFileInfos(List<FileInfo> fileInfos) {
		this.fileInfos = fileInfos;
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
	public String getMsg_type() {
		return msg_type;
	}
	public void setMsg_type(String msg_type) {
		this.msg_type = msg_type;
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
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getId_creator() {
		return id_creator;
	}
	public void setId_creator(String id_creator) {
		this.id_creator = id_creator;
	}
	public String getReminder_chk() {
		return reminder_chk;
	}
	public void setReminder_chk(String reminder_chk) {
		this.reminder_chk = reminder_chk;
	}
	public String getImportance_chk() {
		return importance_chk;
	}
	public void setImportance_chk(String importance_chk) {
		this.importance_chk = importance_chk;
	}
	public String getScreen_mod() {
		return screen_mod;
	}
	public void setScreen_mod(String screen_mod) {
		this.screen_mod = screen_mod;
	}
	private String msg_type_label;

	public String getMsg_type_label() {
		return msg_type_label;
	}

	public void setMsg_type_label(String msg_type_label) {
		this.msg_type_label = msg_type_label;
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