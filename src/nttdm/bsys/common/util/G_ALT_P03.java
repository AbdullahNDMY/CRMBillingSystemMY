/**
 * @(#)G_ALT_P03.java
 *
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nttdm.bsys.common.bean.M_ALT_HBean;
import nttdm.bsys.common.bean.T_QCS_HBean;
import nttdm.bsys.common.bean.WF_TABLEBean;
import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

/**
 * Send Notification(workflow)
 */
public class G_ALT_P03 {
	private static final String A = "A";
	private static final String NOTICE = "NOTICE";
	private static final String INSERT_query_H = "INSERT.BSYS.SQL004";
	private static final String INSERT_query_D = "INSERT.BSYS.SQL004_1";
	private static final String SELECT_QCS = "SELECT.BSYS.SQL009";
	private static final String SELECT_BIF = "SELECT.BSYS.SQL009_2";
	private static final String SELECT_APPROVER = "SELECT.BSYS.SQL009_3";
	private static final String SELECT_ID_MSG = "SELECT.BSYS.SQL009_1";

	/**
	 * <div>updateDAO</div>
	 */
	private UpdateDAO updateDAO = null;
	/**
	 * <div>queryDAO</div>
	 */
	private QueryDAO queryDAO;

	/**
	 * <div>Get UpdateDAO</div>
	 * 
	 * @return UpdateDAO
	 */
	public UpdateDAO getUpdateDAO() {
		return updateDAO;
	}

	/**
	 * <div>Set UpdateDAO</div>
	 * 
	 * @param updateDAO
	 *            UpdateDAO
	 */
	public void setUpdateDAO(UpdateDAO updateDAO) {
		this.updateDAO = updateDAO;
	}

	/**
	 * <div>Get QueryDAO</div>
	 * 
	 * @return QueryDAO
	 */
	public QueryDAO getQueryDAO() {
		return queryDAO;
	}

	/**
	 * <div>Set QueryDAO</div>
	 * 
	 * @param queryDAO
	 *            QueryDAO
	 */
	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}

	/**
	 * <div>G_ALT_P03</div>
	 */
	public G_ALT_P03(QueryDAO queryDAO, UpdateDAO updateDAO) {
		this.updateDAO = updateDAO;
		this.queryDAO = queryDAO;
	}

	/**
	 * <div>execute</div>
	 * 
	 * @throws Exception
	 */
	public void execute(WF_TABLEBean wf) {
		M_ALT_HBean alt_h = new M_ALT_HBean();
		alt_h.setId_login(wf.getId_login3());
		alt_h.setId_screen(wf.getId_screen());
		alt_h.setId_ref(wf.getId_ref());
		alt_h.setMsg_type(NOTICE);
		alt_h.setStatus("1");
		alt_h.setRepeat_mode("0");
		String id_msg = queryDAO.executeForObject(SELECT_ID_MSG, null, String.class);
		alt_h.setId_msg(id_msg);
		if (wf.getType() == null) {
			wf.setType("");
		}
		if (wf.getType().equalsIgnoreCase(A)) {
			// list id_consult, requestor
			List<T_QCS_HBean> arr_alt = new ArrayList<T_QCS_HBean>();
			if (wf.getId_screen().indexOf("BIF") > -1) {
				arr_alt = queryDAO.executeForObjectList(SELECT_BIF, wf.getId_ref());
			} else {
				arr_alt = queryDAO.executeForObjectList(SELECT_QCS, wf.getId_ref());
			}
			// list approver
			Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("id_ref", wf.getId_ref());
			parameter.put("not_send", wf.getAction_user());
			List<T_QCS_HBean> approvers = queryDAO.executeForObjectList(SELECT_APPROVER, parameter);
			// list User to send
			List<String> sendUsers = new ArrayList<String>();
			for (T_QCS_HBean bean : arr_alt) {
				if (!sendUsers.contains(bean.getId_conslt())) {
					sendUsers.add(bean.getId_conslt());
				}
				if (!sendUsers.contains(bean.getReq_user())) {
					sendUsers.add(bean.getReq_user());
				}
			}
			for (T_QCS_HBean bean : approvers) {
				if (!sendUsers.contains(bean.getId_approver())) {
					sendUsers.add(bean.getId_approver());
				}
			}
			// action user
			String actionUser = "";
			for (String user : sendUsers) {
				actionUser += (user + ";");
			}
			/**
			 * send
			 */
			alt_h.setSubject(wf.getSubject());
			alt_h.setMsg(wf.getMsg());
			// send header
			alt_h.setTo_msg(actionUser);
			updateDAO.execute(INSERT_query_H, alt_h);
			// send detail
			for (String user : sendUsers) {
				alt_h.setId_user(user);
				updateDAO.execute(INSERT_query_D, alt_h);
			}
		} else {
			alt_h.setTo_msg(wf.getPic());
			alt_h.setSubject(wf.getSubject());
			alt_h.setMsg(wf.getMsg());
			alt_h.setId_user(wf.getAction_user());
			updateDAO.execute(INSERT_query_H, alt_h);
			updateDAO.execute(INSERT_query_D, alt_h);
		}
	}
}
