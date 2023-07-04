/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : Customer Plan Management
 * SERVICE NAME   : B_CPM_S01
 * FUNCTION       : Abstract class blogic
 * FILE NAME      : AbstractB_CPM_S01SearchBLogic.java
 * 
 * Copyright c 2011 NTTDATA Corporation
 *
 * History
 * 2011/08/05 [Duong Nguyen] Created
 * 
**********************************************************/
package nttdm.bsys.b_cpm.blogic;

import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogic;
import jp.terasoluna.fw.dao.QueryDAO;

/**
 * AbstractB_CPM_S04SearchBLogic.class<br>
 * <ul>
 * <li>Search BLogic</li>
 * </ul>
 * <br>
 * @author  NTTData Vietnam
 * @version 1.1
 */
public abstract class AbstractB_CPM_S04SearchBLogic implements
		BLogic<Map<String, Object>> {

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