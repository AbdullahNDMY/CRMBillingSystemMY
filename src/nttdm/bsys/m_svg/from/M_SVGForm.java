/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_SVG
 * SERVICE NAME   : M_SVG_S01
 * FUNCTION       : M_SVGForm
 * FILE NAME      : M_SVGForm.java
 * 
* Copyright © 2011 NTTDATA Corporation
*
 * History
 * 
 * 
**********************************************************/
package nttdm.bsys.m_svg.from;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.web.struts.form.ValidatorActionFormEx;
import nttdm.bsys.m_svg.bean.*;

/**
 * M_SVGForm<br>
 * <ul>
 * <li>A static form for validating Groups of services
 * </ul>
 * <br>
* @author  NTTData Vietnam
* @version 1.0
 */
public class M_SVGForm extends ValidatorActionFormEx {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ServiceGroupBean> listServiceGroupBean=new ArrayList<ServiceGroupBean>();
	private String action="";
	private List<Map<String, String>> journalList = new ArrayList<Map<String, String>>();

	public List<Map<String, String>> getJournalList() {
		return journalList;
	}

	public void setJournalList(List<Map<String, String>> journalList) {
		this.journalList = journalList;
	}

	public List<ServiceGroupBean> getListServiceGroupBean() {
		return listServiceGroupBean;
	}

	public void setListServiceGroupBean(List<ServiceGroupBean> listServiceGroupBean) {
		this.listServiceGroupBean = listServiceGroupBean;
	} 
	
	public ServiceGroupBean getServiceGroupBean(int index)
	{
        while(index >= listServiceGroupBean.size()){
        	ServiceGroupBean test = new ServiceGroupBean();
        	test.setRemark("test");
        	listServiceGroupBean.add(test);
        }
        
        System.out.println(index);
        return (ServiceGroupBean) listServiceGroupBean.get(index);
	}
	
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}	
}