/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_SSM
 * SERVICE NAME   : M_SSM_S01
 * FUNCTION       : OutputObject
 * FILE NAME      : OutputObject.java
 * 
* Copyright © 2011 NTTDATA Corporation
*
 * History
 * 
 * 
**********************************************************/
package nttdm.bsys.m_ssm.bean;

/**
 * OutputObject<br>
 * <ul>
 * <li>An object to retrieve output data from SQL execution
 * </ul>
 * <br>
* @author  NTTData Vietnam
* @version 1.0
 */
public class OutputObject {
	private int totalRecord;
	private boolean isDeleted = false;

	public boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
}
