/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_SVG
 * SERVICE NAME   : M_SVG_S01
 * FUNCTION       : AbstractM_SVGS01_01BLogic
 * FILE NAME      : AbstractM_SVGS01_01BLogic.java
 * 
* Copyright © 2011 NTTDATA Corporation
*
 * History
 * 
 * 
**********************************************************/
package nttdm.bsys.m_svg.blogic;

import jp.terasoluna.fw.service.thin.BLogic;
import nttdm.bsys.m_svg.dto.M_SVGS01_01Input;
import jp.terasoluna.fw.dao.QueryDAO;

/**
 * AbstractM_SVGS01_01BLogic<br>
 * <ul>
 * <li>Abstract BLogic class
 * </ul>
 * <br>
* @author  tuyenpv
* @version 1.0
 */
public abstract class AbstractM_SVGS01_01BLogic implements
		BLogic<M_SVGS01_01Input> {

	/**
	 * queryDAO
	 */
	protected QueryDAO queryDAO;

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

}