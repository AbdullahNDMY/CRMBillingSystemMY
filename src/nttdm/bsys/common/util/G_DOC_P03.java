/**
 * @(#)G_DOC_P03.java
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.util;

import nttdm.bsys.common.bean.T_WF_DOCBean;
import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

/**
 * Delete attachment
 * 
 * @author lixinj
 */
public class G_DOC_P03 {
	/**
	 * <div>UpdateDAO</div>
	 */
	protected UpdateDAO updateDAO;
	/**
	 * <div>queryDAO</div>
	 */
	protected QueryDAO queryDAO = null;
	/**
	 * <div>SQL_UPDATE_T_WF_DOC</div>
	 */
	static final String SQL_UPDATE_T_WF_DOC = "UPDATE.BSYS.SQL002";
	/**
	 * <div>SQL_UPDATE_T_DOC</div>
	 */
	static final String SQL_UPDATE_T_DOC = "UPDATE.BSYS.SQL003";
	/**
	 * <div>SQL_SELECT_FILE_PATH</div>
	 */
	static final String SQL_SELECT_FILE_PATH = "SELECT.BSYS.SQL017";

	/**
	 * @param updateDAO
	 *            UpdateDAO
	 * 
	 * @return updateDAO
	 */
	public UpdateDAO getUpdateDAO() {
		return updateDAO;
	}

	/**
	 * @param updateDAO
	 *            UpdateDAO
	 */
	public void setUpdateDAO(UpdateDAO updateDAO) {
		this.updateDAO = updateDAO;
	}

	/**
	 * Constructor
	 * 
	 * @param updateDAO
	 *            UpdateDAO
	 * 
	 * @param queryDAO
	 *            QueryDAO
	 */
	public G_DOC_P03(UpdateDAO updateDAO, QueryDAO queryDAO) {
		this.updateDAO = updateDAO;
		this.queryDAO = queryDAO;
	}

	/**
	 * Delete uploaded file
	 * 
	 * @param param
	 *            T_WF_DOCBean
	 * @throws Exception
	 */
	public void deleteFileUploaded(T_WF_DOCBean param) throws Exception {
		try {
			// Delete file info in T_WF_DOC table
			updateDAO.execute(SQL_UPDATE_T_WF_DOC, param);
			// Delete file info in T_DOC table
			updateDAO.execute(SQL_UPDATE_T_DOC, param);
		} catch (Exception ex) {
			throw ex;
		}
	}
}
