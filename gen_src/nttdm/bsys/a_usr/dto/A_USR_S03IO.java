/*
 * @(#)A_USR_S03IO.java
 *
 */
package nttdm.bsys.a_usr.dto;

import java.io.Serializable;
import java.util.*;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;
/**
 * DTO class.
 * @author i-yang
 */
public class A_USR_S03IO implements Serializable {
	private static final long serialVersionUID = 1L;
	//input
	String pageEvent;
	BillingSystemUserValueObject uvo;
	
	//input & output
	String choosed;
	List<PlanService> listPlanService;	
	
	public String getChoosed() {
		return choosed;
	}
	public void setChoosed(String choosed) {
		this.choosed = choosed;
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