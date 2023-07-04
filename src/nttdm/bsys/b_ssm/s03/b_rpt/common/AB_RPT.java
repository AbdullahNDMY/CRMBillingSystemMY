/**
 * Billing System
 * 
 * SUBSYSTEM NAME : Subscription Information
 * SERVICE NAME : B_SSM_S03
 * FUNCTION : processing reports from B_SSM_S03
 * FILE NAME : AB_RPT.java
 * 
 * Copyright (C) 2011 NTTDATA Corporation
 * 
 * History
 */
package nttdm.bsys.b_ssm.s03.b_rpt.common;

import jp.terasoluna.fw.dao.QueryDAO;

/**
 * Abstract class use to export<br/>
 * @author  NTTData Vietnam
 * @version 1.1
 */
public abstract class AB_RPT<T> implements IB_RPT<T> {
	
	public static String REPORT_FILE_PATH = "REPORT_FILE_PATH";
	
	protected QueryDAO queryDAO;
	
	public AB_RPT(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}

	public QueryDAO getQueryDAO() {
		return queryDAO;
	}

	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}
}
