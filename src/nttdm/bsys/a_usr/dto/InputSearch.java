package nttdm.bsys.a_usr.dto;

public class InputSearch {
	private String idUser;
	private String userName;
	private String idDivision;
	private String idDepartment;
	private String[] status;
	private String[] accessRight;
	private String idRole;
	
	public String[] getStatus() {
        return status;
    }
    public void setStatus(String[] status) {
        this.status = status;
    }
    public String[] getAccessRight() {
        return accessRight;
    }
    public void setAccessRight(String[] accessRight) {
        this.accessRight = accessRight;
    }
    public String getIdRole() {
        return idRole;
    }
    public void setIdRole(String idRole) {
        this.idRole = idRole;
    }
    public String getIdUser() {
		return idUser;
	}
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getIdDivision() {
		return idDivision;
	}
	public void setIdDivision(String idDivision) {
		this.idDivision = idDivision;
	}
	public String getIdDepartment() {
		return idDepartment;
	}
	public void setIdDepartment(String idDepartment) {
		this.idDepartment = idDepartment;
	}
	
}
