package nttdm.bsys.m_wlm.dto;

import java.io.Serializable;
import java.util.List;

import org.apache.struts.util.LabelValueBean;

import nttdm.bsys.m_wlm.bean.ActionBean;
import nttdm.bsys.m_wlm.bean.LevelBean;
import nttdm.bsys.m_wlm.bean.SectionBean;
import nttdm.bsys.m_wlm.bean.SectionGroupBean;

public class M_WLMR01Output implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2404635455320780313L;

	private List<SectionGroupBean> listSectionGroup = null;
	
	private List<SectionBean> listSection = null;
	
	private List<SectionBean> listWfSection = null;
	
	private List<LevelBean> listWfLevel = null;
	
	private List<ActionBean> listWfAction = null;
	
	private String id_screen = null;
	
	private String id_section_group = null;
	
	private String access_type = null;
	
	private List<LabelValueBean> listPic = null;
	
	public List<LabelValueBean> getListPic() {
		return listPic;
	}

	public void setListPic(List<LabelValueBean> listPic) {
		this.listPic = listPic;
	}

	public String getAccess_type() {
		return access_type;
	}

	public void setAccess_type(String access_type) {
		this.access_type = access_type;
	}

	public String getId_screen() {
		return id_screen;
	}

	public void setId_screen(String id_screen) {
		this.id_screen = id_screen;
	}

	public String getId_section_group() {
		return id_section_group;
	}

	public void setId_section_group(String id_section_group) {
		this.id_section_group = id_section_group;
	}

	public List<LevelBean> getListWfLevel() {
		return listWfLevel;
	}

	public void setListWfLevel(List<LevelBean> listWfLevel) {
		this.listWfLevel = listWfLevel;
	}

	public List<ActionBean> getListWfAction() {
		return listWfAction;
	}

	public void setListWfAction(List<ActionBean> listWfAction) {
		this.listWfAction = listWfAction;
	}

	public void setListSectionGroup(List<SectionGroupBean> listSectionGroup) {
		this.listSectionGroup = listSectionGroup;
	}

	public List<SectionGroupBean> getListSectionGroup() {
		return listSectionGroup;
	}

	public void setListSection(List<SectionBean> listSection) {
		this.listSection = listSection;
	}

	public List<SectionBean> getListSection() {
		return listSection;
	}

	public void setListWfSection(List<SectionBean> listWfSection) {
		this.listWfSection = listWfSection;
	}

	public List<SectionBean> getListWfSection() {
		return listWfSection;
	}
	

}
