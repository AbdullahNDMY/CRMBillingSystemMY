package nttdm.bsys.g_alt;

import java.io.Serializable;

public class M_ALT_DDto implements Serializable {
	private String idUser;
	private String idMsg;
	private String isNew;
	private String idLogin;
	private String isDelete;
	
	
	
	
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	public String getIdLogin() {
		return idLogin;
	}
	public void setIdLogin(String idLogin) {
		this.idLogin = idLogin;
	}
	public M_ALT_DDto(){
		
	}
	public M_ALT_DDto(String idUser, String idMsg, String isNew){
		this.idUser = idUser;
		this.idMsg = idMsg;
		this.isNew = isNew;
	}
	public String getIdUser() {
		return idUser;
	}
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
	public String getIdMsg() {
		return idMsg;
	}
	public void setIdMsg(String idMsg) {
		this.idMsg = idMsg;
	}
	public String getIsNew() {
		return isNew;
	}
	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}

	
}
