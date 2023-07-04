/**
 * @(#)G_CMN_P01.java
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.util;

import java.util.ArrayList;
import java.util.List;

import nttdm.bsys.c_cmn002.bean.MessageBean;
import nttdm.bsys.c_cmn002.dto.C_CMN002Input;
import nttdm.bsys.c_cmn002.dto.C_CMN002Output;
import jp.terasoluna.fw.dao.QueryDAO;

/**
 * Process to Dashboard and Menu Display
 * 
 * @author lixinj
 */
public class G_CMN_P01 {
	/**
	 * <div>QueryDAO</div>
	 */
	private QueryDAO queryDAO = null;
	/**
	 * <div>SELECT_SQL_Notification</div>
	 */
	private static final String SELECT_SQL_Notification = "SELECT.C_CMN002.SQL001";
	/**
	 * <div>SELECT_SQL_Task</div>
	 */
	private static final String SELECT_SQL_Task = "SELECT.C_CMN002.SQL002";
	/**
	 * <div>SELECT_SQL_Sent</div>
	 */
	private static final String SELECT_SQL_Sent = "SELECT.C_CMN002.SQL003";
	/**
	 * <div>SELECT_SQL_Sent</div>
	 */
	private static final String SELECT_NewMsg = "SELECT.C_CMN002.SQL007";
	/**
	 * <div>SAVE_ERROR_MSG</div>
	 */
	static final String SAVE_ERROR_MSG = "info.ERR2SC004";
	/**
	 * <div>row_count</div>
	 */
	private int row_count = 0;
	/**
	 * <div>notif_scroll</div>
	 */
	private int notif_scroll = 0;
	/**
	 * <div>task_scroll</div>
	 */
	private int task_scroll = 0;
	/**
	 * <div>sent_scroll</div>
	 */
	private int sent_scroll = 0;

	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}

	public QueryDAO getQueryDAO() {
		return queryDAO;
	}

	/**
	 * <div>Constructor</div>
	 * 
	 * @param queryDAO
	 *            QueryDAO
	 */
	public G_CMN_P01(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}

	/**
	 * <div>get data to display</div>
	 * 
	 * @param param
	 *            input UserID
	 * @throws Exception
	 */
	public C_CMN002Output execute(C_CMN002Input param) throws Exception {
		try {
			// Declare output array object
			C_CMN002Output outputDTO = new C_CMN002Output();
			// Set user id for object
			param.setId_user(param.getUvo().getId_user());
			// get number of row for scroll
			BillingSystemSettings bss = new BillingSystemSettings(queryDAO);
			row_count = bss.getNumberOfRowsInDB();
			// Load data array by user id
			loadData(param, outputDTO);
			outputDTO.setNewmsg(getTotalCount(param));
			outputDTO.setMax_row(row_count * 20);
			return outputDTO;
		} catch (Exception ex) {
			throw ex;
		}
	}

	/**
	 * <div>get notification data</div>
	 * 
	 * @param param
	 *            input UserID
	 */
	private ArrayList<MessageBean> getNotificationList(C_CMN002Input input) {
		// declare new MessageBean Arraylist
		List<MessageBean> array = new ArrayList<MessageBean>();
		// get data from query
		array = queryDAO.executeForObjectList(SELECT_SQL_Notification, input);
		// generate empty rows
		for (int i = row_count - array.size(); i > 0; i--) {
			array.add(new MessageBean());
		}
		// set scrollbar
		if (this.row_count < array.size()) {
			notif_scroll = 1;
		}
		return (ArrayList<MessageBean>) array;
	}

	/**
	 * <div>Indicates if message is new or have been read.</div>
	 * 
	 * @param param
	 *            input UserID
	 */
	private String getTotalCount(C_CMN002Input arg0) {
		// count total items from searching result
		String count = queryDAO.executeForObject(SELECT_NewMsg,
				arg0.getId_user(), String.class);
		// when count is null
		if (count == null) {
			// set count=0
			count = "";
		}
		// return
		return count;
	}

	/**
	 * <div>get task data</div>
	 * 
	 * @param param
	 *            input UserID
	 */
	private ArrayList<MessageBean> getMyTasksList(C_CMN002Input input) {
		// declare new MessageBean Arraylist
		List<MessageBean> array = new ArrayList<MessageBean>();
		// get data from query
		array = queryDAO.executeForObjectList(SELECT_SQL_Task, input);
		// generate empty rows
		for (int i = row_count - array.size(); i > 0; i--) {
			array.add(new MessageBean());
		}
		// set scrollbar
		if (this.row_count < array.size()) {
			task_scroll = 1;
		}
		return (ArrayList<MessageBean>) array;
	}

	/**
	 * <div>get sent data</div>
	 * 
	 * @param param
	 *            input UserID
	 */
	private ArrayList<MessageBean> getSentList(C_CMN002Input input) {
		// declare new MessageBean Arraylist
		List<MessageBean> array = new ArrayList<MessageBean>();
		// get data from query
		array = queryDAO.executeForObjectList(SELECT_SQL_Sent, input);
		// generate empty rows
		for (int i = row_count - array.size(); i > 0; i--) {
			array.add(new MessageBean());
		}
		// set scrollbar
		if (this.row_count < array.size()) {
			sent_scroll = 1;
		}
		return (ArrayList<MessageBean>) array;
	}

	/**
	 * 
	 * <div>get data to display</div>
	 * 
	 * @param param
	 *            input UserID
	 * @param param
	 *            output
	 */
	private void loadData(C_CMN002Input input, C_CMN002Output output) {
		output.setArray_notification(getNotificationList(input));
		output.setNotif_count(notif_scroll);
		output.setArray_mytasks(getMyTasksList(input));
		output.setTask_count(task_scroll);
		output.setArray_sent(getSentList(input));
		output.setSent_count(sent_scroll);
	}
}
