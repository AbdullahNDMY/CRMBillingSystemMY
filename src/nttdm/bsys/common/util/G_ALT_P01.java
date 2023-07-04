/**
 * @(#)G_ALT_P01.java
 *
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nttdm.bsys.common.bean.WF_TABLEBean;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

/**
 * Duplicate Runing Workflow List
 */
public class G_ALT_P01 {
	/**
	 * <div>queryDAO</div>
	 */
	private QueryDAO queryDAO;
	/**
	 * <div>updateDAO</div>
	 */
	private UpdateDAO updateDAO = null;
	/**
	 * <div>SELECT_Duplicate</div>
	 */
	private static final String SELECT_Duplicate = "SELECT.BSYS.SQL003";
	/**
	 * <div>SELECT_M_WF_SECTION</div>
	 */
	private static final String SELECT_M_WF_SECTION = "SELECT.BSYS.SQL003_1";
	/**
	 * <div>INSERT_T_Section</div>
	 */
	private static final String INSERT_T_Section = "INSERT.BSYS.SQL001";
	/**
	 * <div>INSERT_T_Action</div>
	 */
	private static final String INSERT_T_Action = "INSERT.BSYS.SQL002";
	/**
	 * <div>SQL_GET_NEXT_LEVEL_SEQ</div>
	 */
	static final String SQL_GET_NEXT_LEVEL_SEQ = "SELECT.BSYS.SQL039_01";

	/**
	 * <div>Get queryDAO</div>
	 * @return QueryDAO
	 */
	public QueryDAO getQueryDAO() {
		return queryDAO;
	}

	/**
	 * <div>Set queryDAO</div>
	 * @param queryDAO QueryDAO
	 */
	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}

	/**
	 * <div>Get updateDAO</div>
	 * @return UpdateDAO
	 */
	public UpdateDAO getUpdateDAO() {
		return updateDAO;
	}

	/**
	 * <div>Set updateDAO</div>
	 * @param updateDAO UpdateDAO
	 */
	public void setUpdateDAO(UpdateDAO updateDAO) {
		this.updateDAO = updateDAO;
	}

	/**
	 * <div>G_ALT_P01</div>
	 */
	public G_ALT_P01(QueryDAO queryDAO, UpdateDAO updateDAO) {
		// Initial DAO object
		this.queryDAO = queryDAO;
		this.updateDAO = updateDAO;
	}

	/**
	 * <div>Main function to process db</div>
	 * 
	 * @param wf_obj WF_TABLEBean
	 * @throws Exception
	 */
	public void execute(WF_TABLEBean wf_obj) throws Exception {
		try {
			// fill data into array list
			List<WF_TABLEBean> wf = new ArrayList<WF_TABLEBean>();
			wf = queryDAO.executeForObjectList(SELECT_M_WF_SECTION, wf_obj);
			for (WF_TABLEBean bean : wf) {
				// bean.sets
				bean.setId_ref(wf_obj.getId_ref());
				updateDAO.execute(INSERT_T_Section, bean);
			}
			wf = queryDAO.executeForObjectList(SELECT_Duplicate, wf_obj);
			// insert data into temporary WF table group
			G_ALT_P02 p02 = new G_ALT_P02(queryDAO, updateDAO);
			for (WF_TABLEBean bean : wf) {
				// bean.sets
				bean.setId_ref(wf_obj.getId_ref());
				updateDAO.execute(INSERT_T_Action, bean);
			}

			// get first level_seq of section_no
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("id_ref", wf_obj.getId_ref());
			params.put("section_no", wf_obj.getSection_no());
			// set to get first level
			params.put("level_seq", -9999);
			String first_level = this.queryDAO.executeForObject(
					SQL_GET_NEXT_LEVEL_SEQ, params, String.class);
			if (first_level != null) {
				wf_obj.setLevel_seq(first_level.toString());
				p02.execute(wf_obj);
			}
		} catch (Exception ex) {
			// error occur
			throw ex;
		}
	}
}
