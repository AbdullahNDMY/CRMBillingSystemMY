/**
 * @(#)GlobalProcessResult.java
 *
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicMessages;

/**
 * Global message process.
 * 
 * @author leonzh
 */
public class GlobalProcessResult {
	/**
	 * <div>parameter</div>
	 */
	protected Map<String,Object> parameter=new HashMap<String,Object>();
	/**
	 * <div>messages</div>
	 */
	protected BLogicMessages messages;

	/**
	 * <div>errors</div>
	 */
	protected BLogicMessages errors;

	/**
	 * <div>file</div>
	 */
	protected File file;

	public Map<String, Object> getParameter() {
		return parameter;
	}

	public void setParameter(Map<String, Object> parameter) {
		this.parameter = parameter;
	}

	/**
	 * Get the file
	 * 
	 * @return File this object's attribute
	 */
	public File getFile() {
		return file;
	}

	/**
	 * Set the object's attribute
	 * 
	 * @param file
	 *            File
	 */
	public void setFile(File file) {
		this.file = file;
	}

	/**
	 * Get the messages
	 * 
	 * @return BLogicMessages this object's attribute
	 */
	public BLogicMessages getMessages() {
		return messages;
	}

	/**
	 * Set the object's attribute
	 * 
	 * @param messages
	 *            BLogicMessages
	 */
	public GlobalProcessResult setMessages(BLogicMessages messages) {
		this.messages = messages;
		return this;
	}

	/**
	 * Get the errors
	 * 
	 * @return BLogicMessages this object's attribute
	 */
	public BLogicMessages getErrors() {
		return errors;
	}

	/**
	 * Set the object's attribute
	 * 
	 * @param errors
	 *            BLogicMessages
	 */
	public GlobalProcessResult setErrors(BLogicMessages errors) {
		this.errors = errors;
		return this;
	}

	/**
	 * Append messages
	 * 
	 * @param messages
	 *            BLogicMessages
	 * 
	 * @return GlobalProcessResult itself
	 */
	public GlobalProcessResult appendMessages(BLogicMessages messages) {
		messages.add(this.messages);
		return this;
	}

	/**
	 * Append errors
	 * 
	 * @param errors
	 *            BLogicMessages
	 * 
	 * @return GlobalProcessResult itself
	 */
	public GlobalProcessResult appendErrors(BLogicMessages errors) {
		errors.add(this.errors);
		return this;
	}
}
