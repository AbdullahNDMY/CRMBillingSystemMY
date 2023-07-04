/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_SVG
 * SERVICE NAME   : M_SVG_S01
 * FUNCTION       : M_SVGS01_01Input
 * FILE NAME      : M_SVGS01_01Input.java
 * 
* Copyright © 2011 NTTDATA Corporation
*
 * History
 * 
 * 
**********************************************************/
package nttdm.bsys.m_svg.dto;

import java.io.Serializable;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;

/**
 * M_SVGS01_01Input<br>
 * <ul>
 * <li>Input DTO
 * </ul>
 * <br>
* @author  tuyenpv
* @version 1.0
 */
public class M_SVGS01_01Input implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4173255142911045944L;

	/**
     * 
     */
	private BillingSystemUserValueObject uvo;

	public BillingSystemUserValueObject getUvo() {
		return uvo;
	}

	public void setUvo(BillingSystemUserValueObject uvo) {
		this.uvo = uvo;
	}
}