/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_SVG
 * SERVICE NAME   : M_SVG_S01
 * FUNCTION       : AbstractM_SVGS01_03BLogic
 * FILE NAME      : AbstractM_SVGS01_03BLogic.java
 * 
* Copyright © 2011 NTTDATA Corporation
*
 * History
 * 
 * 
**********************************************************/
package nttdm.bsys.m_svg.blogic;

import jp.terasoluna.fw.service.thin.BLogic;
import nttdm.bsys.common.dao.UpdateDAOiBatisNuked;
import nttdm.bsys.m_svg.dto.M_SVGS01_03Input;
import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

/**
 * AbstractM_SVGS01_03BLogic<br>
 * <ul>
 * <li>Abstract BLogic class
 * </ul>
 * <br>
* @author  tuyenpv
* @version 1.0
 */
public abstract class AbstractM_SVGS01_03BLogic implements
		BLogic<M_SVGS01_03Input> {

	/**
	 * queryDAO
	 */
	protected QueryDAO queryDAO;

	/**
	 * updateDAO
	 */
	protected UpdateDAO updateDAO;
	
	/**
	 * updateDAONuked
	 */
	protected UpdateDAOiBatisNuked updateDAONuked;
	
	/**
	 * queryDAO を取得する
	 * 
	 * @return queryDAO
	 */
	public QueryDAO getQueryDAO() {
		return queryDAO;
	}

	/**
	 * queryDAO を設定する
	 * 
	 * @param queryDAO
	 *            queryDAO
	 */
	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}

	/**
	 * updateDAO を取得する
	 * 
	 * @return updateDAO
	 */
	public UpdateDAO getUpdateDAO() {
		return updateDAO;
	}

	/**
	 * updateDAO を設定する
	 * 
	 * @param updateDAO
	 *            updateDAO
	 */
	public void setUpdateDAO(UpdateDAO updateDAO) {
		this.updateDAO = updateDAO;
	}

	public UpdateDAOiBatisNuked getUpdateDAONuked() {
		return updateDAONuked;
	}

	public void setUpdateDAONuked(UpdateDAOiBatisNuked updateDAONuked) {
		this.updateDAONuked = updateDAONuked;
	}
}