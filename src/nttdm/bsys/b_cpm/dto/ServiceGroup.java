package nttdm.bsys.b_cpm.dto;

import java.io.Serializable;

public class ServiceGroup implements Serializable {
	private String svcGrp;
	private String svcGrpName;
	private String accCode;
	private String originCode;
	private String productCode;
	private String remark;
	private String dateCreated;
	private String dateUpdated;
	private String idLogin;
	public String getSvcGrp() {
		return svcGrp;
	}
	public void setSvcGrp(String svcGrp) {
		this.svcGrp = svcGrp;
	}
	public String getSvcGrpName() {
		return svcGrpName;
	}
	public void setSvcGrpName(String svcGrpName) {
		this.svcGrpName = svcGrpName;
	}
	public String getAccCode() {
		return accCode;
	}
	public void setAccCode(String accCode) {
		this.accCode = accCode;
	}
	public String getOriginCode() {
		return originCode;
	}
	public void setOriginCode(String originCode) {
		this.originCode = originCode;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	public String getDateUpdated() {
		return dateUpdated;
	}
	public void setDateUpdated(String dateUpdated) {
		this.dateUpdated = dateUpdated;
	}
	public String getIdLogin() {
		return idLogin;
	}
	public void setIdLogin(String idLogin) {
		this.idLogin = idLogin;
	}
	
	

}
