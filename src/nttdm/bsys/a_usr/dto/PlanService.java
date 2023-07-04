package nttdm.bsys.a_usr.dto;

public class PlanService {
	private String id;
	private String name;
	private String isUsed;
	private String idLogin;
	public PlanService(){
		this.isUsed = "0";
	}
	
	/**
	 * @return the idLogin
	 */
	public String getIdLogin() {
		return idLogin;
	}
	/**
	 * @param idLogin the idLogin to set
	 */
	public void setIdLogin(String idLogin) {
		this.idLogin = idLogin;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id =id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the isUsed
	 */
	public String getIsUsed() {
		return isUsed;
	}
	/**
	 * @param isUsed the isUsed to set
	 */
	public void setIsUsed(String isUsed) {
		this.isUsed = isUsed;
	}
	
}
