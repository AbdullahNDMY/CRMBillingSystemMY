/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : Customer Plan Management
 * SERVICE NAME   : B_CPM_S02
 * FUNCTION       : Abstract class blogic
 * FILE NAME      : AbstractB_CPM_S02ViewBLogic.java
 * 
 * Copyright c 2011 NTTDATA Corporation
 *
 * History
 * 2011/08/05 [Duong Nguyen] Created
 * 
**********************************************************/
package nttdm.bsys.b_cpm.blogic;

import jp.terasoluna.fw.service.thin.BLogic;
import java.util.HashMap;
import jp.terasoluna.fw.dao.QueryDAO;

/**
 * AbstractB_CPM_S02ViewBLogic.class<br>
 * <ul>
 * <li>View BLogic</li>
 * </ul>
 * <br>
 * @author  NTTData Vietnam
 * @version 1.1
 */
public abstract class AbstractB_CPM_S02ViewBLogic implements BLogic<HashMap<String, Object>> {

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