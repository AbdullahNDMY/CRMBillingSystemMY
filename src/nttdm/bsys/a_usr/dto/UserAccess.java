package nttdm.bsys.a_usr.dto;

import nttdm.bsys.common.util.dto.AccessType;

public class UserAccess {
	private String idUser;
	private String idModule;
	private String idSubModule;
	private String accessType;
	private String idLogin;
	private Integer idAudit = null;
	public Integer getIdAudit() {
		return idAudit;
	}

	public void setIdAudit(Integer idAudit) {
		this.idAudit = idAudit;
	}

	public UserAccess(){
		accessType = AccessType.READ_ONLY;
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
	public String getAccessType() {
		return accessType;
	}
	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}

	public String getIdLogin() {
		return idLogin;
	}

	public void setIdLogin(String idLogin) {
		this.idLogin = idLogin;
	}
	
	
}
