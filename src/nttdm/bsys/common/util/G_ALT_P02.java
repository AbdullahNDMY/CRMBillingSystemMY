/**
 * @(#)G_ALT_P02.java
 *
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import nttdm.bsys.common.bean.WF_TABLEBean;
import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import jp.terasoluna.fw.web.struts.action.GlobalMessageResources;

/**
 * Selecting Active User for WorkFLow
 */
public class G_ALT_P02 {
	/**
	 * <div>updateDAO</div>
	 */
	private UpdateDAO updateDAO = null;
	/**
	 * <div>queryDAO</div>
	 */
	private QueryDAO queryDAO;
	/**
	 * <div>SELECT_G_ALT_P02</div>
	 */
	private static final String UPDATE_G_ALT_P02_AA = "INSERT.BSYS.SQL003";
	/**
	 * <div>SELECT_G_ALT_P02</div>
	 */
	private static final String SELECT_G_ALT_P02 = "SELECT.BSYS.SQL008_01";
	/**
	 * <div>SELECT_Level_Seq</div>
	 */
	private static final String SELECT_Level_Seq = "SELECT.BSYS.SQL006";
	/**
	 * <div>ConditionStr1</div>
	 */
	private static final String ConditionStr1 = "contractvalue";
	/**
	 * <div>ConditionStr2</div>
	 */
	private static final String Q081 = "Q081";
	/**
	 * <div>ConditionStr3</div>
	 */
	private static final String Q082 = "Q082";
	/**
	 * <div>ConditionStr4</div>
	 */
	private static final String Q083 = "Q083";
	/**
	 * <div>AA</div>
	 */
	private static final String AA = "AA";
	/**
	 * <div>AO</div>
	 */
	private static final String AO = "AO";
	/**
	 * <div>AS1</div>
	 */
	private static final String AS1 = "AS1";
	private static final String EQL = "EQL";
	private static final String LRG = "LRG";
	private static final String SML = "SML";
	private static final String ELR = "ELR";
	private static final String ESM = "ESM";
	private static final String NTE = "NTE";
	/**
	 * <div>ERR2SC012</div>
	 */
	private static final String ERR2SC012 = "info.ERR2SC012";
	/**
	 * <div>ERR2SC013</div>
	 */
	private static final String ERR2SC013 = "info.ERR2SC013";
	/**
	 * <div>ERR2SC014</div>
	 */
	private static final String ERR2SC014 = "info.ERR2SC014";
	/**
	 * <div>ERR2SC015</div>
	 */
	private static final String ERR2SC015 = "info.ERR2SC015";
	/**
	 * <div>G_WFM_P01</div>
	 */
	private G_WFM_P01 g_wfm;
	/**
	 * <div>GlobalMessageResources</div>
	 */
	private GlobalMessageResources msgResource;

	/**
	 * Get G_WFM_P01
	 * @return G_WFM_P01
	 */
	public G_WFM_P01 getG_wfm() {
		return g_wfm;
	}

	/**
	 * Set G_WFM_P01
	 * @param g_wfm G_WFM_P01
	 */
	public void setG_wfm(G_WFM_P01 g_wfm) {
		this.g_wfm = g_wfm;
	}

	/**
	 * Get QueryDAO
	 * @return QueryDAO
	 */
	public QueryDAO getQueryDAO() {
		return queryDAO;
	}

	/**
	 * Get UpdateDAO
	 * @return UpdateDAO
	 */
	public UpdateDAO getUpdateDAO() {
		return updateDAO;
	}

	/**
	 * Set UpdateDAO
	 * @param updateDAO UpdateDAO
	 */
	public void setUpdateDAO(UpdateDAO updateDAO) {
		this.updateDAO = updateDAO;
	}

	/**
	 * Set QueryDAO
	 * @param queryDAO QueryDAO
	 */
	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}

	/**
	 * Constructor
	 * 
	 * @param queryDAO
	 *            QueryDAO
	 * @param updateDAO
	 *            UpdateDAO
	 */
	public G_ALT_P02(QueryDAO queryDAO, UpdateDAO updateDAO) {
		this.updateDAO = updateDAO;
		this.queryDAO = queryDAO;

		// init resource
		this.msgResource = GlobalMessageResources.getInstance();
	}

	/**
	 * <div>execute_P02_2</div>
	 * @param wf WF_TABLEBean
	 */
	private void execute_P02_2(WF_TABLEBean wf) throws Exception {
		try {
			List<WF_TABLEBean> lst_wf = new ArrayList<WF_TABLEBean>();
			lst_wf = queryDAO.executeForObjectList(SELECT_G_ALT_P02, wf);
			String action_user = "";
			// size always is one when select by pic
			if (lst_wf.size() > 0) {
				for (WF_TABLEBean bean : lst_wf) {
					bean.setId_action(wf.getId_action());
					bean.setRef_id_action(wf.getRef_id_action());
					bean.setId_ref(wf.getId_ref());
					bean.setSection_group(wf.getSection_group());
					bean.setSection_no(wf.getSection_no());
					bean.setSequence_no(wf.getSequence_no());
					bean.setId_login3(wf.getId_login3());
					bean.setId_screen(wf.getId_screen());
					bean.setSys(wf.getSys());
					if (bean.getId_tfr_user() != null) {
						if (bean.getEff_date_from() != null
								&& bean.getEff_date_to() != null) {
							// Get Date From
							Date df = bean.getEff_date_from();
							Date dt = bean.getEff_date_to();
							Date now = bean.getSys();
							if (BillingCalendar.compare(df, now) < 1
									&& BillingCalendar.compare(dt, now) > -1) {
								action_user = bean.getId_tfr_user();
							} else {
								// action user = D.PIC
								action_user = bean.getPic();
							}
						} else {
							// action user = D.PIC
							action_user = bean.getPic();
						}
					} else {
						// action user = D.PIC
						action_user = bean.getPic();
					}

					updateW_TF_APPROVAL(bean, action_user);
				} // end for
			} else {
				WF_TABLEBean acc = queryDAO.executeForObject(
						"SELECT.BSYS.SQL008_02", wf, WF_TABLEBean.class);
				// if no record found do nothing
				action_user = wf.getPic();
				wf.setAction_user(action_user);
				wf.setSection_desc(acc.getSection_desc());
				updateW_TF_APPROVAL(wf, action_user);
			}
		} catch (Exception ex) {
			throw ex;
		}
	}

	/**
	 * update W_TF_APPROVAL
	 * 
	 * @param bean WF_TABLEBean
	 * @param action_user Action Use
	 * @throws Exception
	 */
	private void updateW_TF_APPROVAL(WF_TABLEBean bean, String action_user)
			throws Exception {
		String req_user_name = this.queryDAO.executeForObject(
				"SELECT.BSYS.UTILS.SQL001", bean.getId_ref(), String.class);
		G_ALT_P03 alt_p03 = new G_ALT_P03(queryDAO, updateDAO);
		bean.setAction_user(action_user);

		String message = "";
		List<String> messageParams = new ArrayList<String>();
		messageParams.add(bean.getId_ref().trim());
		// process D.ACTION_TYPE="AA"
		if (bean.getAction_type().equals(AA)) {
			if (bean.getOriginal_approver() == null
					|| bean.getOriginal_approver().equals("")) {
				bean.setOriginal_approver(bean.getPic());
			}
			bean.setId_approver(action_user);
			bean.setAppr_status(AS1);
			updateDAO.execute(UPDATE_G_ALT_P02_AA, bean);
			// goto G_ALT_P03
			message = this.msgResource.getMessage(ERR2SC012, messageParams.toArray());
			bean.setSubject(message);
			messageParams.add(req_user_name);

			Date expiredDate = null;
			if (bean.getResponse_expire() != null) {
				expiredDate = BillingCalendar.addValue4Date(bean.getSys(),
						BillingCalendar.DAY_OF_YEAR,
						Integer.parseInt(bean.getResponse_expire()));
			} else {
				expiredDate = bean.getSys();
			}
			messageParams.add(BillingCalendar.convertDateToString(expiredDate, "dd/MM/yyyy"));
			message = this.msgResource.getMessage(ERR2SC013, messageParams.toArray());
			bean.setMsg(message);
			// pass param to P03 with TYPE!=A
			alt_p03.execute(bean);
		} else if (bean.getAction_type().equals(AO)) {
			// check D.ACTION_TYPE="A0"
			// goto G_ALT_P03
			message = this.msgResource.getMessage(ERR2SC014, messageParams.toArray());
			bean.setSubject(message);
			messageParams.add(bean.getSection_desc());
			messageParams.add(req_user_name);
			message = this.msgResource.getMessage(ERR2SC015, messageParams.toArray());
			bean.setMsg(message);
			// pass param to P03 with TYPE!=A
			alt_p03.execute(bean);
		}
	}

	/**
	 * <div>Main function to process db</div>
	 * 
	 * @param wf WF_TABLEBean
	 * @throws Exception
	 */
	public void execute(WF_TABLEBean wf) throws Exception {
		try {
			List<WF_TABLEBean> lst_bean = new ArrayList<WF_TABLEBean>();
			lst_bean = queryDAO.executeForObjectList(SELECT_Level_Seq, wf);
			if (lst_bean.size() <= 0) {
				// pass result $Action_user to G_WFM_P01
				if (this.g_wfm != null) {
					this.g_wfm.setAnyUser(false);
				}
			} else {
				// pass result $Action_user to G_WFM_P01
				if (this.g_wfm != null) {
					this.g_wfm.setAnyUser(true);
				}
				for (WF_TABLEBean bean : lst_bean) {
					bean.setId_ref(wf.getId_ref());
					bean.setSection_group(wf.getSection_group());
					bean.setSection_no(wf.getSection_no());
					bean.setSequence_no(wf.getSequence_no());
					bean.setSys(new Date());
					if (wf.getRef_id_action() != null
							&& !wf.getRef_id_action().equals("")) {
						bean.setRef_id_action(wf.getRef_id_action());
					} else {
						bean.setRef_id_action(bean.getId_action());
					}
					bean.setId_login3(wf.getId_login3());
					bean.setId_screen(wf.getId_screen());
					// check contract value
					if (bean.getCondition_primary() == null) {
						bean.setCondition_primary("");
					}
					if (bean.getCondition_primary().equalsIgnoreCase(ConditionStr1)) {
						if (bean.getCondition_secondary() != null) {
							int condition_secondary = Integer.parseInt(bean.getCondition_secondary());
							/*
							 * EQL= LRG> SML< ELR=> ESM=< NTE!=
							 */
							if (bean.getSection_no().equals(Q081)) {
								// Q081
								if (compare(bean.getCondition_operator(),
										300000, condition_secondary)) {
									// return bean.PIC to P02-2
									execute_P02_2(bean);
								}
							} else if (bean.getSection_no().equals(Q082)) {
								// Q082
								if (condition_secondary > 300000
										&& condition_secondary <= 1000000) {
									// return bean.PIC to P02-2
									execute_P02_2(bean);
								}
							} else if (bean.getSection_no().equals(Q083)) {
								// Q083
								// modify 2011/08/05 for #8 bug
								if (compare(bean.getCondition_operator(),
										1000000, condition_secondary)) {
									// return bean.PIC to P02-2
									execute_P02_2(bean);
								}
							} else {
								// return bean.PIC to P02-2
								execute_P02_2(bean);
							}
						} else { // end of check section_group
							// return bean.PIC to P02-2
							execute_P02_2(bean);
						}
					} else {
						// return bean.PIC to P02-2
						execute_P02_2(bean);
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

	/**
	 * Compare value and condition_secondary by operator.
	 * 
	 * @param operator
	 *            EQL= LRG> SML< ELR=> ESM=< NTE!=
	 * @param value
	 *            value to be compared
	 * @param condition_secondary
	 *            value for compare
	 * @return true if value is operator to condition_secondary
	 */
	private boolean compare(String operator, long value,
			long condition_secondary) {
		/* EQL= LRG> SML< ELR=> ESM=< NTE!= */
		if (operator.equals(EQL)) {
			// =
			if (value == condition_secondary) {
				return true;
			} else {
				return false;
			}
		}
		if (operator.equals(NTE)) {
			// >
			if (condition_secondary != value) {
				return true;
			} else {
				return false;
			}
		}
		if (operator.equals(ESM)) {
			// >
			if (condition_secondary <= value) {
				return true;
			} else {
				return false;
			}
		}
		if (operator.equals(ELR)) {
			// >
			if (condition_secondary >= value) {
				return true;
			} else {
				return false;
			}
		}
		if (operator.equals(SML)) {
			// >
			if (condition_secondary < value) {
				return true;
			} else {
				return false;
			}
		}
		if (operator.equals(LRG)) {
			// >
			if (condition_secondary > value) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
}
