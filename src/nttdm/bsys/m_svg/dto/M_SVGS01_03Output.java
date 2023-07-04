/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_SVG
 * SERVICE NAME   : M_SVG_S01
 * FUNCTION       : M_SVGS01_03Output
 * FILE NAME      : M_SVGS01_03Output.java
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
 * M_SVGS01_03Output<br>
 * <ul>
 * <li>Output DTO
 * </ul>
 * <br>
* @author  tuyenpv
* @version 1.0
 */
public class M_SVGS01_03Output implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5225174591010539862L;

	private List<nttdm.bsys.m_svg.bean.ServiceGroupBean> serviceGrpItem;
	private String action;
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
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}	
}