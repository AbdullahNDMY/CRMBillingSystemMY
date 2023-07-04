package nttdm.bsys.m_atm.bean;
import jp.terasoluna.fw.web.struts.form.ValidatorActionFormEx;
import java.util.List;
import org.apache.struts.util.LabelValueBean;

public class M_ATMFormBean extends ValidatorActionFormEx{
	private static final long serialVersionUID = -5538432094237818425L;
	private List<M_ATMBean> m_atm = null;
	private List<LabelValueBean> arrayName=null;
	private String beanCodeListID = null;
	private String user_name=null; 
	private String detail_inf;
	private String id_tfr_user;
	private String id_screen;
	private String section_no;
	private String section_desc;
	private String role_name;
	private String eff_date_from;
	private String eff_date_to;
	private String id_user;
	private String id_login; 
	private String id_role;
	private String role_list=null;
	private String update_mode;
	private String tfr_name;
	
	public List<LabelValueBean> getArrayName() {
		return arrayName;
	}
	public void setArrayName(List<LabelValueBean> arrayName) {
		this.arrayName = arrayName;
	}
	public String getTfr_name() {
		return tfr_name;
	}
	public void setTfr_name(String tfr_name) {
		this.tfr_name = tfr_name;
	}
	public String getUpdate_mode() {
		return update_mode;
	}
	public void setUpdate_mode(String update_mode) {
		this.update_mode = update_mode;
	}	
	public String getDetail_inf() {
		return detail_inf;
	}
	public void setDetail_inf(String detail_inf) {
		this.detail_inf = detail_inf;
	}
	public String getRole_list() {
		return role_list;
	}
	public void setRole_list(String role_list) {
		this.role_list = role_list;
	}
	public String getId_role() {
		return id_role;
	}
	public void setId_role(String id_role) {
		this.id_role = id_role;
	}
	
	public String getId_tfr_user() {
		return id_tfr_user;
	}
	public void setId_tfr_user(String id_tfr_user) {
		this.id_tfr_user = id_tfr_user;
	}
	public String getId_screen() {
		return id_screen;
	}
	public void setId_screen(String id_screen) {
		this.id_screen = id_screen;
	}
	public String getSection_no() {
		return section_no;
	}
	public void setSection_no(String section_no) {
		this.section_no = section_no;
	}
	public String getSection_desc() {
		return section_desc;
	}
	public void setSection_desc(String section_desc) {
		this.section_desc = section_desc;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	public String getEff_date_from() {
		return eff_date_from;
	}
	public void setEff_date_from(String eff_date_from) {
		this.eff_date_from = eff_date_from;
	}
	public String getEff_date_to() {
		return eff_date_to;
	}
	public void setEff_date_to(String eff_date_to) {
		this.eff_date_to = eff_date_to;
	}
	public String getId_user() {
		return id_user;
	}
	public void setId_user(String id_user) {
		this.id_user = id_user;
	}
	public String getId_login() {
		return id_login;
	}
	public void setId_login(String id_login) {
		this.id_login = id_login;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public List<M_ATMBean> getM_atm() {
		return m_atm;
	}
	public void setM_atm(List<M_ATMBean> m_atm) {
		this.m_atm = m_atm;
	}
	public String getBeanCodeListID() {
		return beanCodeListID;
	}
	public void setBeanCodeListID(String beanCodeListID) {
		this.beanCodeListID = beanCodeListID;
	}

}
