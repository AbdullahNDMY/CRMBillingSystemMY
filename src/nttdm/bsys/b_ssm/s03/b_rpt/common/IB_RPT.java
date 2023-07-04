/**
 * Billing System
 * 
 * SUBSYSTEM NAME : Subscription Information
 * SERVICE NAME : B_SSM_S03
 * FUNCTION : processing reports from B_SSM_S03
 * FILE NAME : IB_RPT.java
 * 
 * Copyright (C) 2011 NTTDATA Corporation
 * 
 * History
 */
package nttdm.bsys.b_ssm.s03.b_rpt.common;

import nttdm.bsys.b_ssm.s03.b_rpt.data.B_RPT_Output;
import nttdm.bsys.util.exception.ReportException;

/**
 * Iterface use to export<br/>
 * @author  NTTData Vietnam
 * @version 1.1
 */
public interface IB_RPT<T> {
	
	/**
	 * Use to export file
	 * @param queryDAO
	 * @param params
	 * @return
	 */
	public B_RPT_Output export(T params) throws ReportException;

}
