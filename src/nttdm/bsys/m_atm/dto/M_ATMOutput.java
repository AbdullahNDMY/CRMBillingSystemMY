package nttdm.bsys.m_atm.dto;

import java.io.Serializable;
import java.util.List;

import org.apache.struts.util.LabelValueBean;

import nttdm.bsys.m_atm.bean.M_ATMBean;

public class M_ATMOutput  implements Serializable{
	private static final long serialVersionUID = -3825291939065997528L;
	private List<M_ATMBean> m_atm=null;
	private List<LabelValueBean> arrayName=null;
	private String role_list=null;
	private String detail_inf;
	private String update_mode;
	private String id_user;
	
	public List<LabelValueBean> getArrayName() {
		return arrayName;
	}
	public void setArrayName(List<LabelValueBean> arrayName) {
		this.arrayName = arrayName;
	}
	public String getId_user() {
		return id_user;
	}
	public void setId_user(String id_user) {
		this.id_user = id_user;
	}
	public String getDetail_inf() {
		return detail_inf;
	}
	public void setDetail_inf(String detail_inf) {
		this.detail_inf = detail_inf;
	}
	public String getUpdate_mode() {
		return update_mode;
	}
	public void setUpdate_mode(String update_mode) {
		this.update_mode = update_mode;
	}
	public String getRole_list() {
		return role_list;
	}
	public void setRole_list(String role_list) {
		this.role_list = role_list;
	}
	
	public List<M_ATMBean> getM_atm() {
		return m_atm;
	}
	public void setM_atm(List<M_ATMBean> m_atm) {
		this.m_atm = m_atm;
	}
	
}
