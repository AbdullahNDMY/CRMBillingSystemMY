/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_SSM
 * SERVICE NAME   : M_SSM_S01
 * FUNCTION       : M_SSMS01_02Input
 * FILE NAME      : M_SSMS01_02Input.java
 * 
* Copyright © 2011 NTTDATA Corporation
*
 * History
 * 
 * 
**********************************************************/
package nttdm.bsys.m_ssm.dto;

import java.io.Serializable;

/**
 * M_SSMS01_02Input<br>
 * <ul>
 * <li>Input DTO
 * </ul>
 * <br>
* @author  NTTData Vietnam
* @version 1.0
 */
public class M_SSMS01_02Input implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -542230391947192893L;

	/**
     * 
     */
	private String svc_grp;

	/**
	 * svc_grp を取得する
	 * 
	 * @return svc_grp
	 */
	public String getSvc_grp() {
		return svc_grp;
	}

	/**
	 * svc_grp を設定する
	 * 
	 * @param svc_grp
	 */
	public void setSvc_grp(String svc_grp) {
		this.svc_grp = svc_grp;
	}

}