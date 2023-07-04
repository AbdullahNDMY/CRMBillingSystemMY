/**
 * @(#)G_ADT_P01.java
 *
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.util;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import nttdm.bsys.common.bean.AuditTrailHeaderBean;
import nttdm.bsys.common.dao.UpdateDAOiBatisNuked;

/**
 * Audit Trail Capturing Process.
 * 
 */
public class G_ADT_P01 {
	private QueryDAO queryDAO;
	private UpdateDAO updateDAO;
	private UpdateDAOiBatisNuked updateDAONuked;

	/**
	 * <div>getQueryDAO</div>
	 */
	public QueryDAO getQueryDAO() {
		return queryDAO;
	}

	/**
	 * <div>setQueryDAO</div>
	 */
	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}

	/**
	 * <div>getUpdateDAONuked</div>
	 */
	public UpdateDAOiBatisNuked getUpdateDAONuked() {
		return updateDAONuked;
	}

	/**
	 * <div>setUpdateDAONuked</div>
	 */
	public void setUpdateDAONuked(UpdateDAOiBatisNuked updateDAONuked) {
		this.updateDAONuked = updateDAONuked;
	}

	/**
	 * <div>getUpdateDAO</div>
	 */
	public UpdateDAO getUpdateDAO() {
		return updateDAO;
	}

	/**
	 * <div>setUpdateDAO</div>
	 */
	public void setUpdateDAO(UpdateDAO updateDAO) {
		this.updateDAO = updateDAO;
	}

	/**
	 * begin write log with each action from screen
	 * 
	 * @param idUser
	 *            : M_USER.ID_USER idModule : M_MODULES.ID_MODULE idSubModule :
	 *            M_SUB_MODULES.ID_SUB_MODULE reference : depend of module
	 *            status : depend of module action : Created, Edited, Deleted,
	 *            Login, Logout
	 * @return M_AUDIT_TRAIL_H.ID_AUDIT
	 * @return null if have any exception
	 */
	public Integer writeLog(AuditTrailHeaderBean auditHeader) {
		try {
			return this.updateDAONuked.insert("INSERT.BSYS.UTILS.SQL001",
					auditHeader);
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * update log data
	 * 
	 * @param auditHeader
	 *            update data bean
	 * @return record number that is updated
	 */
	public int updateLog(AuditTrailHeaderBean auditHeader) {
		return this.updateDAO.execute("UPDATE.BSYS.UTILS.SQL001", auditHeader);
	}

	/**
	 * delete log in M_AUDIT_TRAIL_H when no associate data in M_AUDIT_TRAIL_D
	 * 
	 * @param idAudit
	 *            audit_id in table
	 * @param emptyRollback
	 *            roallback flag
	 */
	public void endLog(Integer idAudit, boolean emptyRollback) {
		if (emptyRollback) {
			Integer count = this.queryDAO.executeForObject(
					"SELECT.BSYS.UTILS.SQL004", idAudit, Integer.class);
			if (count == 0) {
				this.updateDAO.execute("DELETE.BSYS.UTILS.SQL001", idAudit);
			}
		}
	}
}