/*
 * @(#)M_SVT01IO.java
 *
 *
 */
package nttdm.bsys.m_svt.dto;

import java.io.Serializable;
import java.util.*;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;
/**
 * InputDTO class.
 * 
 * @author hungtm
 */
public class M_SVT01IO implements Serializable {
	private static final long serialVersionUID = 1L;
	//input
	String pageEvent;
	BillingSystemUserValueObject uvo;
	//output
	List<ServiceGroup> listServiceGroup;
	//input & output
	String choosed;
	List<PlanService> listPlanService;
	
	String tabValue;
	
	public String getTabValue() {
		return tabValue;
	}
	public void setTabValue(String tabValue) {
		this.tabValue = tabValue;
	}
	public String getChoosed() {
		return choosed;
	}
	public void setChoosed(String choosed) {
		this.choosed = choosed;
	}
	public List<ServiceGroup> getListServiceGroup() {
		return listServiceGroup;
	}
	public void setListServiceGroup(List<ServiceGroup> listServiceGroup) {
		this.listServiceGroup = listServiceGroup;
	}
	public String getPageEvent() {
		return pageEvent;
	}
	public void setPageEvent(String pageEvent) {
		this.pageEvent = pageEvent;
	}
	public List<PlanService> getListPlanService() {
		return listPlanService;
	}
	public void setListPlanService(List<PlanService> listPlanService) {
		this.listPlanService = listPlanService;
	}
	public BillingSystemUserValueObject getUvo() {
		return uvo;
	}
	public void setUvo(BillingSystemUserValueObject uvo) {
		this.uvo = uvo;
	}
	public String getIdLogin() {
		String idLogin ="";
		if(uvo != null){
			idLogin = uvo.getId_user();
		}
		return idLogin;
	}
	public String getAccessType(String module, String subModule){
		String accessType = "";
		if(uvo != null){
			accessType = uvo.getUserAccessInfo(module, subModule).getAccess_type();
		}
		return accessType;
	}
	
}