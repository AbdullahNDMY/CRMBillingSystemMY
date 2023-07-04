/*
 * @(#)AbstractRP_B_BIL_S01ExportBLogic.java
 *
 *
 */
package nttdm.bsys.b_bil.blogic;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import nttdm.bsys.b_bil.dto.RP_B_BIL_S01ExportInput;

/**
 * Abstract BusinessLogic class.
 * 
 * @author khungl0ng
 */
public abstract class AbstractRP_B_BIL_S01ExportBLogic implements
		BLogic<RP_B_BIL_S01ExportInput> {

	/**
	 * queryDAO
	 */
	protected QueryDAO queryDAO;

	/**
	 * updateDAO
	 */
	protected UpdateDAO updateDAO;

	/**
	 * queryDAO を�?�得�?�る
	 * 
	 * @return queryDAO
	 */
	public QueryDAO getQueryDAO() {
		return queryDAO;
	}

	/**
	 * queryDAO を設定�?�る
	 * 
	 * @param queryDAO
	 *            queryDAO
	 */
	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}

	/**
	 * updateDAO を�?�得�?�る
	 * 
	 * @return updateDAO
	 */
	public UpdateDAO getUpdateDAO() {
		return updateDAO;
	}

	/**
	 * updateDAO を設定�?�る
	 * 
	 * @param updateDAO
	 *            updateDAO
	 */
	public void setUpdateDAO(UpdateDAO updateDAO) {
		this.updateDAO = updateDAO;
	}

}