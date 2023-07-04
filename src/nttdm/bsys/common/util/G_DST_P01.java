/**
 * @(#)G_DST_P01.java
 *
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nttdm.bsys.bif.dto.B_BIF_Input;
import nttdm.bsys.common.bean.T_QCS_HBean;
import nttdm.bsys.common.bean.WF_TABLEBean;
import nttdm.bsys.q_qcs.blogic.Q_QCSR03BLogic;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

/**
 * Process to Update Document Status (QCS, BIF)
 */
public class G_DST_P01 {
	/**
	 * <div>queryDAO</div>
	 */
	private QueryDAO queryDAO;
	/**
	 * <div>updateDAO</div>
	 */
	private UpdateDAO updateDAO = null;
	/**
	 * <div>id_ref</div>
	 */
	private String id_ref;
	/**
	 * <div>id_screen</div>
	 */
	private String id_screen;
	/**
	 * <div>id_user</div>
	 */
	private String id_user;

	private static final String SELECT_APPR_STATUS = "SELECT.BSYS.SQL021";
	private static final String SELECT_COUNT_APPROVAL = "SELECT.BSYS.SQL022";
	private static final String SELECT_COUNT_SECTION = "SELECT.BSYS.SQL023";
	private static final String UPDATE_T_QCS_H = "UPDATE.BSYS.SQL004";
	private static final String UPDATE_T_BIF_H = "UPDATE.BSYS.SQL005";
	private static final String UPDATE_T_QCS_H_QUO = "UPDATE.BSYS.SQL006";
	private static final String UPDATE_T_BIF_H_QUO = "UPDATE.BSYS.SQL007";
	private static final String BIFCN_TABLE_A = "T_BIF_H";
	private static final String QCS_TABLE_A = "T_QCS_H";

	private static final String SELECT_NOT_COMPLETE_STATUS = "SELECT.BSYS.SQL021_01";

	private static final String AS2 = "AS2";
	private static final String AS3 = "AS3";
	private static final String AS5 = "AS5";
	private static final String AS6 = "AS6";
	private static final String AS7 = "AS7";

	private static final String DS2 = "DS2";
	private static final String DS3 = "DS3";
	private static final String DS4 = "DS4";

	private static final String QCS = "QCS";

	/**
	 * <div>Constructor</div>
	 * 
	 * @param queryDAO
	 *            QueryDAO
	 * @param updateDAO
	 *            UpdateDAO
	 * @param id_ref
	 *            String
	 * @param id_screen
	 *            String
	 * @param id_user
	 *            String
	 * 
	 */
	public G_DST_P01(QueryDAO queryDAO, UpdateDAO updateDAO, String id_ref,
			String id_screen, String id_user) {
		this.queryDAO = queryDAO;
		this.updateDAO = updateDAO;
		this.id_ref = id_ref;
		this.id_screen = id_screen;
		this.id_user = id_user;
	}

	/**
	 * <div>Constructor</div>
	 * 
	 * @param queryDAO
	 *            QueryDAO
	 * @param updateDAO
	 *            UpdateDAO
	 */
	public G_DST_P01(QueryDAO queryDAO, UpdateDAO updateDAO) {
		this.queryDAO = queryDAO;
		this.updateDAO = updateDAO;
	}

	/**
	 * <div>execute</div>
	 * 
	 * @param wf
	 *            WF_TABLEBean
	 * @return instance of WF_TABLEBean
	 * @throws Exception
	 */
	public WF_TABLEBean execute(WF_TABLEBean wf) throws Exception {
		ArrayList<String> arr = G_TBL_P01.execute(wf.getId_screen());
		String table = "";
		if (arr.size() > 0) {
			table = arr.get(0);
		}

		// create status object
		T_QCS_HBean t_qcs = new T_QCS_HBean();
		t_qcs.setId_ref(wf.getId_ref());

		// select to check any status of workflow is DS1 or DS3 <not completed>
		List<Map<String, Object>> wfStatusList = this.queryDAO
				.executeForMapList(SELECT_NOT_COMPLETE_STATUS, wf.getId_ref());
		if (wfStatusList.size() > 0) {
			// have any records are not confirmed or reject
			for (Map<String, Object> record : wfStatusList) {
				Object status = record.get("APPR_STATUS");
				Object is_revised = record.get("IS_REVISED");
				if (status != null && is_revised != null) {
					if (status.equals(AS3) && is_revised.equals("1")) {
						// update status to DS4
						t_qcs.setId_status(DS4);
						updateStatus(table, t_qcs);
					}
				}
			}
			return wf;
		} else {
			// all records are confirmed
			// check any section is not confirmed
			// Generate QCO
			String count_section = queryDAO.executeForObject(
					SELECT_COUNT_SECTION, wf.getId_ref(), String.class);
			String count_approval = queryDAO.executeForObject(
					SELECT_COUNT_APPROVAL, wf.getId_ref(), String.class);
			if (count_section.equals(count_approval)) {
				if (wf.getId_screen().equals(QCS)) {
					// call G_CDM
					G_CDM_P01 cdm = new G_CDM_P01(queryDAO, updateDAO,
							wf.getId_user());
					// update QUO with id_code
					// t_qcs.setId_quo(cdm.getGenerateCode(cdm.getGenerateCode(Q_QCSR03BLogic.QCSNO_ID_CODE,
					// wf.getId_user()), count_approval));
					String id_quo = "";
					if (wf.getId_screen().equals(B_BIF_Input.ID_SCREEN)) {
						// generate following BIF
						id_quo = cdm.getGenerateCode("BIFNO", wf.getId_user());
					} else {
						// generate following DCS
						id_quo = cdm.getGenerateCode(
								Q_QCSR03BLogic.QCSNO_ID_CODE, wf.getId_user());
					}
					t_qcs.setId_quo(id_quo);
					this.updateQuo(table, t_qcs);
				}
				// update status
				t_qcs.setId_status(DS3);
				this.updateStatus(table, t_qcs);
			} else {
                // update status
                t_qcs.setId_status(DS2);
                this.updateStatus(table, t_qcs);
			}
		}

		// set id_status after updated for wf
		wf.setId_status(t_qcs.getId_status());

		return wf;
	}

	/**
	 * <div>updateStatus</div>
	 * 
	 * @param table
	 *            String
	 * @param t_qcs
	 *            T_QCS_HBean
	 */
	private void updateStatus(String table, T_QCS_HBean t_qcs) {
		// update table
		if (table.equals(BIFCN_TABLE_A)) {
			// for T_BIF_H
			this.updateDAO.execute(UPDATE_T_BIF_H, t_qcs);
		} else if (table.equals(QCS_TABLE_A)) {
			// for T_QCS_H
			this.updateDAO.execute(UPDATE_T_QCS_H, t_qcs);
		}
	}

	/**
	 * <div>updateQuo</div>
	 * 
	 * @param table
	 *            String
	 * @param t_qcs
	 *            T_QCS_HBean
	 */
	private void updateQuo(String table, T_QCS_HBean t_qcs) {
		// update table
		if (table.equals(BIFCN_TABLE_A)) {
			// for T_BIF_H
			this.updateDAO.execute(UPDATE_T_BIF_H_QUO, t_qcs);
		} else if (table.equals(QCS_TABLE_A)) {
			// for T_QCS_H
			this.updateDAO.execute(UPDATE_T_QCS_H_QUO, t_qcs);
		}
	}
}
