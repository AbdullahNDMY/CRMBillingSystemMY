/**
 * @(#)G_PAG_P01.java
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nttdm.bsys.a_usr.bean.UserAccessBean;
import nttdm.bsys.common.bean.WF_TABLEBean;
import jp.terasoluna.fw.dao.QueryDAO;

/**
 * Process to determine Page Mode
 * 
 * @author lixinj
 */
public class G_PAG_P01 {

	/**
	 * return result
	 */
	private String page2 = "";

	/**
	 * id_user
	 */
	private String id_user;

	/**
	 * id_ref
	 */
	private String id_ref;

	/**
	 * id_screen
	 */
	private String id_screen;

	/**
	 * id_sub_module
	 */
	private String id_sub_module;

	/**
	 * <div>SQL_SELECT_ACCESS_TYPE</div>
	 */
	private static final String G_PAG_016 = "SELECT.BSYS.SQL016";

	/**
	 * ERROR_MESSAGE_ID
	 */
	private static final String ERR1SC019 = "ERR1SC019";

	/**
	 * SELECT RESULT FROM t_wf_approval
	 */
	private WF_TABLEBean t_wf_app;

	/**
	 * save button attribute
	 */
	private Map<String, Object> moreAction = new HashMap<String, Object>();
	
	/**
	 * BUTTON_APPROVAL_DISABLED
	 */
	private static final String BUTTON_APPROVAL_DISABLED = "BUTTON_APPROVAL_DISABLED";
	
	/**
	 * BUTTON_REJECT_DISABLED
	 */
	private static final String BUTTON_REJECT_DISABLED = "BUTTON_REJECT_DISABLED";

	/**
	 * <div>Get t_wf_app</div>
	 * 
	 * @return t_wf_app
	 */
	public WF_TABLEBean getT_wf_app() {
		return t_wf_app;
	}

	/**
	 * <div>queryDAO</div>
	 */
	private QueryDAO queryDAO;

	/**
	 * <div>Get queryDAO</div>
	 * 
	 * @return queryDAO
	 */
	public QueryDAO getQueryDAO() {
		return queryDAO;
	}

	/**
	 * <div>Set updateDAO</div>
	 * 
	 * @param updateDAO
	 */
	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}

	/**
	 * <div>Constructor</div>
	 * 
	 * @param page
	 * 
	 * @param idUser
	 *            ID_USER
	 * 
	 * @param idRef
	 *            ID_REF
	 * 
	 * @param idScreen
	 *            ID_SCREEN
	 * 
	 * @param queryDAO
	 *            QueryDAO
	 * 
	 * @param id_sub_module
	 *            ID_SUB_MODULE
	 */
	public G_PAG_P01(String page, String idUser, String idRef, String idScreen,
			QueryDAO queryDAO, String id_sub_module) {
		this.page2 = page;
		this.id_user = idUser;
		this.id_ref = idRef;
		this.id_screen = idScreen;
		this.queryDAO = queryDAO;
		this.id_sub_module = id_sub_module;
	}

	/**
	 * get return page
	 * 
	 * @throws Exception
	 */
	public String getPage() throws Exception {
		try {
			WF_TABLEBean wf = new WF_TABLEBean();
			wf.setId_approver(this.id_user);
			wf.setId_ref(this.id_ref);

			// select data from table T_WF_APPROVAL
			this.t_wf_app = queryDAO.executeForObject("SELECT.G_PAG.SQL001",
					wf, WF_TABLEBean.class);
			if (this.t_wf_app != null) {
				return this.page2 + "a";
			} else {
				List<WF_TABLEBean> arrwf = new ArrayList<WF_TABLEBean>();
				wf.setId_user(this.id_user);
				wf.setId_screen(this.id_screen);

				// select data from table T_WF_APPROVAL and M_ATR_TFR
				arrwf = queryDAO.executeForObjectList("SELECT.G_PAG.SQL002", wf);
				if (arrwf != null && !arrwf.isEmpty()) {
					for (WF_TABLEBean bean : arrwf) {
						Date df = bean.getEff_date_from();
						Date dt = bean.getEff_date_to();
						Date now = bean.getSys();
						if (BillingCalendar.compare(df, now) < 1
								&& BillingCalendar.compare(dt, now) > -1) {
							return this.page2 + "a";
						}
					}
					return getUserAccessRight(this.id_user);
				} else {
					wf.setId_user(this.id_user);
					wf.setId_ref(this.id_ref);

					// select data from table T_WF_APPROVAL and T_WF_ACTION
					arrwf = queryDAO.executeForObjectList("SELECT.G_PAG.SQL003", wf);
					if (arrwf != null && !arrwf.isEmpty()) {

						// set button attribute
						moreAction.put(BUTTON_APPROVAL_DISABLED, true);
						moreAction.put(BUTTON_REJECT_DISABLED, true);
						return this.page2 + "a";
					} else {

						// select data from table T_WF_APPROVAL
						arrwf = queryDAO.executeForObjectList(
								"SELECT.G_PAG.SQL004", wf);
						if (arrwf != null && !arrwf.isEmpty()) {
							return this.page2 + "s";
						} else {
							wf.setId_screen(this.id_screen);

							// select data from table T_WF_APPROVAL and
							// M_ATR_TFR
							arrwf = queryDAO.executeForObjectList(
									"SELECT.G_PAG.SQL005", wf);
							if (arrwf != null && !arrwf.isEmpty()) {
								for (WF_TABLEBean bean : arrwf) {
									Date df = bean.getEff_date_from();
									Date dt = bean.getEff_date_to();
									Date now = bean.getSys();
									if (BillingCalendar.compare(df, now) < 1
											&& BillingCalendar.compare(dt, now) > -1) {
										return this.page2 + "s";
									}
								}
								return getUserAccessRight(this.id_user);
							} else {

								// select data from table T_WF_APPROVAL and T_WF_ACTION
								arrwf = queryDAO.executeForObjectList("SELECT.G_PAG.SQL006", wf);
								if (arrwf != null && !arrwf.isEmpty()) {

									// set button attribute
									moreAction.put(BUTTON_APPROVAL_DISABLED, true);
									moreAction.put(BUTTON_REJECT_DISABLED, true);
									return this.page2 + "s";
								} else {
									return getUserAccessRight(this.id_user);
								}
							}
						}
					}
				}
			}
		} catch (Exception ex) {
			throw ex;
		}
	}

	/**
	 * get access right from user
	 * 
	 * @param idUser
	 *            ID_USER
	 */
	private String getUserAccessRight(String idUser) {
		// get user access right
		UserAccessBean us = new UserAccessBean();
		us.setId_user(this.id_user);
		us.setId_sub_module(this.id_sub_module);
		String usrAcc = queryDAO.executeForObject(G_PAG_016, us, String.class);
		if (usrAcc == null) {
			usrAcc = "";
		}
		if (usrAcc.equals("1")) {
			return this.page2 + "x";
		} else if (usrAcc.equals("2")) {
			return this.page2 + "v";
		} else {
			return ERR1SC019;
		}
	}

	/**
	 * <div>Get moreAction</div>
	 * 
	 * @return moreAction
	 */
	public Map<String, Object> getMoreAction() {
		return moreAction;
	}

	/**
	 * <div>Set moreAction</div>
	 * 
	 * @param moreAction
	 */
	public void setMoreAction(Map<String, Object> moreAction) {
		this.moreAction = moreAction;
	}
}
