/*
 * @(#)AbstractC_CMN001R02BLogic.java
 *
 *
 */
package nttdm.bsys.c_cmn001.blogic;

import jp.terasoluna.fw.service.thin.BLogic;
import nttdm.bsys.c_cmn001.dto.C_CMN001R02Input;
import jp.terasoluna.fw.dao.QueryDAO;

/**
 * Abstract BusinessLogic class.
 * 
 * @author ss052
 */
public abstract class AbstractC_CMN001R02BLogic implements
		BLogic<C_CMN001R02Input> {

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