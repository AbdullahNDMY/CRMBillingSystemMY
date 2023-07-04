package nttdm.bsys.b_cpm.dto;

public class MailAddress {
	private String idCustPlan;
	private String mailId;
	private String mailAccount;
	private String mailPassword;
	private String popAccount;
	private String mailBoxQuantity;
	private String mailBoxSize;
	private String virusScan;
	private String antiSpam;
	private String junkManagment;
	private String idLogin;
	private Integer idAudit = null;
	public Integer getIdAudit() {
		return idAudit;
	}
	public void setIdAudit(Integer idAudit) {
		this.idAudit = idAudit;
	}
	public MailAddress(){
		virusScan = "0";
		antiSpam = "0";
		junkManagment = "0";
	}
	public String getIdCustPlan() {
		return idCustPlan;
	}
	public void setIdCustPlan(String idCustPlan) {
		this.idCustPlan = idCustPlan;
	}
	public String getMailId() {
		return mailId;
	}
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
	public String getMailAccount() {
		return mailAccount;
	}
	public void setMailAccount(String mailAccount) {
		this.mailAccount = mailAccount;
	}
	public String getMailPassword() {
		return mailPassword;
	}
	public void setMailPassword(String mailPassword) {
		this.mailPassword = mailPassword;
	}
	public String getPopAccount() {
		return popAccount;
	}
	public void setPopAccount(String popAccount) {
		this.popAccount = popAccount;
	}
	public String getMailBoxQuantity() {
		return mailBoxQuantity;
	}
	public void setMailBoxQuantity(String mailBoxQuantity) {
		this.mailBoxQuantity = mailBoxQuantity;
	}
	public String getMailBoxSize() {
		return mailBoxSize;
	}
	public void setMailBoxSize(String mailBoxSize) {
		this.mailBoxSize = mailBoxSize;
	}
	public String getVirusScan() {
		return virusScan;
	}
	public void setVirusScan(String virusScan) {
		this.virusScan = virusScan;
	}
	public String getAntiSpam() {
		return antiSpam;
	}
	public void setAntiSpam(String antiSpam) {
		this.antiSpam = antiSpam;
	}
	public String getJunkManagment() {
		return junkManagment;
	}
	public void setJunkManagment(String junkManagment) {
		this.junkManagment = junkManagment;
	}
	public String getIdLogin() {
		return idLogin;
	}
	public void setIdLogin(String idLogin) {
		this.idLogin = idLogin;
	}

}
