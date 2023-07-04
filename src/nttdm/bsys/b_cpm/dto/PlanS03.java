package nttdm.bsys.b_cpm.dto;

import java.io.Serializable;

public class PlanS03 implements Serializable {
	private String idPlanGroup;
	private String serviceGroup;
	private String serviceType;
	private String entry;
	private String infoId;
	
	
	public String getEntry() {
		return entry;
	}
	public void setEntry(String entry) {
		this.entry = entry;
	}
	public String getInfoId() {
		return infoId;
	}
	public void setInfoId(String infoId) {
		this.infoId = infoId;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public String getIdPlanGroup() {
		
		return idPlanGroup;
	}
	public void setIdPlanGroup(String idPlanGroup) {
		this.idPlanGroup = idPlanGroup;
	}
	public String getServiceGroup() {
		return serviceGroup;
	}
	public void setServiceGroup(String serviceGroup) {
		this.serviceGroup = serviceGroup;
	}
	
	
}
