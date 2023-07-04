/**
 * @(#)E_MWF_AR1_Task.java
 *
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.e_mwf_ar1.batch;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

/**
 * Batch Escalation Workflow Process
 */
public class E_MWF_AR1_Task {

	/**
	 * <div>updateDAO</div>
	 */
	private QueryDAO queryDAO;

	/**
	 * <div>getQueryDAO</div>
	 * 
	 * @return QueryDAO
	 */
	public QueryDAO getQueryDAO() {
		return queryDAO;
	}

	/**
	 * <div>setQueryDAO</div>
	 * 
	 * @param queryDAO
	 *            QueryDAO
	 */
	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}

	/**
	 * <div>getUpdateDAO</div>
	 * 
	 * @return UpdateDAO
	 */
	public UpdateDAO getUpdateDAO() {
		return updateDAO;
	}

	/**
	 * <div>setUpdateDAO</div>
	 * 
	 * @param updateDAO
	 *            UpdateDAO
	 */
	public void setUpdateDAO(UpdateDAO updateDAO) {
		this.updateDAO = updateDAO;
	}

	/**
	 * <div>updateDAO</div>
	 */
	private UpdateDAO updateDAO;

	/**
	 * <div>printMessage</div>
	 */
	public void printMessage() {
		E_MWF_RA1_Process e_mwf_ra1 = new E_MWF_RA1_Process(this.queryDAO,
				this.updateDAO);
		// not yet test, when test will remove comment
		e_mwf_ra1.execute();
	}
}
