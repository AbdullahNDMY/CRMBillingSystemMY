/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_SVG
 * SERVICE NAME   : M_SVG_S01
 * FUNCTION       : M_SVGS01_03Input
 * FILE NAME      : M_SVGS01_03Input.java
 * 
* Copyright © 2011 NTTDATA Corporation
*
 * History
 * 
 * 
**********************************************************/
package nttdm.bsys.m_svg.dto;

import java.io.Serializable;
import java.util.List;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;

/**
 * M_SVGS01_03Input<br>
 * <ul>
 * <li>Input DTO
 * </ul>
 * <br>
* @author  tuyenpv
* @version 1.0
 */
public class M_SVGS01_03Input implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7298823924934957035L;

	private List<nttdm.bsys.m_svg.bean.ServiceGroupBean> serviceGrpItem;
	private String action;
	private BillingSystemUserValueObject uvo;

	public BillingSystemUserValueObject getUvo() {
		return uvo;
	}

	public void setUvo(BillingSystemUserValueObject uvo) {
		this.uvo = uvo;
	}

	public List<nttdm.bsys.m_svg.bean.ServiceGroupBean> getServiceGrpItem() {
		return serviceGrpItem;
	}

	public void setServiceGrpItem(
			List<nttdm.bsys.m_svg.bean.ServiceGroupBean> serviceGrpItem) {
		this.serviceGrpItem = serviceGrpItem;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}	
	
}