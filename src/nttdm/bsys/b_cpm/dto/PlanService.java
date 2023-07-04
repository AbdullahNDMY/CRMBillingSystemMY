package nttdm.bsys.b_cpm.dto;

public class PlanService {
	public static String ITEM = "ITM";
	public static String TYPE = "TYP";
	private String id_service;
	private String svc_grp;
	private String id_service_name;
	private String svc_desc;
	private String gst;
	private String serviceType;
	private String serviceItem;
	private String vendor;
	private String rateType;
	private String idPlanService;
	private String idPlanGroup;
	private String idLogin;
	private Integer idAudit = null;
	public PlanService(){
		idPlanService = "";
	}
	public String getId_service() {
		return id_service;
	}
	public void setId_service(String id_service) {
		this.id_service = id_service;
	}
	public String getSvc_grp() {
		return svc_grp;
	}
	public void setSvc_grp(String svc_grp) {
		this.svc_grp = svc_grp;
	}
	public String getId_service_name() {
		return id_service_name;
	}
	public void setId_service_name(String id_service_name) {
		this.id_service_name = id_service_name;
	}
	public String getSvc_desc() {
		return svc_desc;
	}
	public void setSvc_desc(String svc_desc) {
		this.svc_desc = svc_desc;
	}
	public String getGst() {
		return gst;
	}
	public void setGst(String gst) {
		this.gst = gst;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public String getServiceItem() {
		return serviceItem;
	}
	public void setServiceItem(String serviceItem) {
		this.serviceItem = serviceItem;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public String getRateType() {
		return rateType;
	}
	public void setRateType(String rateType) {
		this.rateType = rateType;
	}
	public String getIdPlanService() {
		return idPlanService;
	}
	public void setIdPlanService(String idPlanService) {
		this.idPlanService = idPlanService;
	}
	public String getIdPlanGroup() {
		return idPlanGroup;
	}
	public void setIdPlanGroup(String idPlanGroup) {
		this.idPlanGroup = idPlanGroup;
	}
	public String getIdLogin() {
		return idLogin;
	}
	public void setIdLogin(String idLogin) {
		this.idLogin = idLogin;
	}
	public Integer getIdAudit() {
		return idAudit;
	}
	public void setIdAudit(Integer idAudit) {
		this.idAudit = idAudit;
	}
}