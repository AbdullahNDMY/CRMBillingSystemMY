/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_SVG
 * SERVICE NAME   : M_SVG_S01
 * FUNCTION       : M_SVGS01_01Output
 * FILE NAME      : M_SVGS01_01Output.java
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
import java.util.Map;

/**
 * M_SVGS01_01Output<br>
 * <ul>
 * <li>Output DTO
 * </ul>
 * <br>
* @author  duongnld
* @version 1.0
 */
public class M_SVGS01_01Output implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1419313003449162038L;

	/**
     * 
     */
	private List<nttdm.bsys.m_svg.bean.ServiceGroupBean> serviceGrpItem;
	private List<Map<String, String>> journal_list;
	
	public List<Map<String, String>> getJournal_list() {
		return journal_list;
	}

	public void setJournal_list(List<Map<String, String>> journal_list) {
		this.journal_list = journal_list;
	}

	public List<nttdm.bsys.m_svg.bean.ServiceGroupBean> getServiceGrpItem() {
		return serviceGrpItem;
	}

	public void setServiceGrpItem(
			List<nttdm.bsys.m_svg.bean.ServiceGroupBean> serviceGrpItem) {
		this.serviceGrpItem = serviceGrpItem;
	}
}