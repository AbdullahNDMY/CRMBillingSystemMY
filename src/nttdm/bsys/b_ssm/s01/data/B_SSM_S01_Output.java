/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : B_SSM
 * SERVICE NAME   : B_SSM_S01
 * FUNCTION       : Processed output of B_SSM_S01
 * FILE NAME      : B_SSM_S01_Output.java
 *
 * Copyright (C) 2011 NTTDATA Corporation
 * 
**********************************************************/

package nttdm.bsys.b_ssm.s01.data;

/**
 * @author NTT Data Vietnam
 * Processed output of B_SSM_S01
 */
public class B_SSM_S01_Output {
	private int tabPosition = 0;

	public void setTabPosition(int tabPosition) {
		this.tabPosition = tabPosition;
	}

	public int getTabPosition() {
		return tabPosition;
	}
}
