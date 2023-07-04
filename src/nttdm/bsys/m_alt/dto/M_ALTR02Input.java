/*
 * @(#)M_ALTR02BLogicInput.java
 *
 *
 */
package nttdm.bsys.m_alt.dto;

import java.io.Serializable;
import java.util.ArrayList;

import org.apache.struts.upload.FormFile;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;

/**
 * <div>
 * Input class
 * </div>
 * @author Phu.Nguyen
 *
 */
public class M_ALTR02Input implements Serializable {

	/**
	 * <div>serialVersionUID</div>
	 */
	private static final long serialVersionUID = -4970851306887399130L;
	/**
	 * <div>uvo</div>
	 */
	private BillingSystemUserValueObject uvo;
	/**
	 * <div>id_login</div>
	 */
	private String id_login;
	/**
	 * <div>id_user</div>
	 */
	private String id_user;
	
	/**
	 * <div>msg_type</div>
	 */
	private String msg_type;

	/**
	 * <div>to_msg</div>
	 */
	private String to_msg;
	/**
	 * <div>cc_msg</div>
	 */
	private String cc_msg;

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
	 * <div>listFile</div>
	 */
	private FormFile[] listFile;
	/**
	 * <div>listFileName</div>
	 */
	private ArrayList<String> listFileName;
	/**
	 * <div>listFilePath</div>
	 */
	private ArrayList<String> listFilePath;
	
	private String[] listFileIdOld;
	
	private String click_event;
	
	public String getClick_event() {
		return click_event;
	}

	public void setClick_event(String click_event) {
		this.click_event = click_event;
	}

	public String[] getListFileIdOld() {
		return listFileIdOld;
	}

	public void setListFileIdOld(String[] listFileIdOld) {
		this.listFileIdOld = listFileIdOld;
	}

	public ArrayList<String> getListFileName() {
		return listFileName;
	}

	public void setListFileName(ArrayList<String> listFileName) {
		this.listFileName = listFileName;
	}

	public ArrayList<String> getListFilePath() {
		return listFilePath;
	}

	public void setListFilePath(ArrayList<String> listFilePath) {
		this.listFilePath = listFilePath;
	}

	public FormFile[] getListFile() {
		return listFile;
	}

	public void setListFile(FormFile[] listFile) {
		this.listFile = listFile;
	}

	public String getId_user() {
		return id_user;
	}

	public void setId_user(String id_user) {
		this.id_user = id_user;
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

	public String getId_msg() {
		return id_msg;
	}

	public void setId_msg(String id_msg) {
		this.id_msg = id_msg;
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

}