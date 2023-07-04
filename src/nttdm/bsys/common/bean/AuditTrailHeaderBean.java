package nttdm.bsys.common.bean;

public class AuditTrailHeaderBean {
	private Integer idAudit;
	private String idUser;
	private String idModule;
	private String idSubModule;
	private String reference;
	private String status;
	private String action;
	
	public Integer getIdAudit() {
		return idAudit;
	}

	public void setIdAudit(Integer idAudit) {
		this.idAudit = idAudit;
	}

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public String getIdModule() {
		return idModule;
	}

	public void setIdModule(String idModule) {
		this.idModule = idModule;
	}

	public String getIdSubModule() {
		return idSubModule;
	}

	public void setIdSubModule(String idSubModule) {
		this.idSubModule = idSubModule;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
}