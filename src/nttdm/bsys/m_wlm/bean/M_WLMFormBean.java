/*
 * @(#)M_WLMFormBean.java
 *
 *
 */
package nttdm.bsys.m_wlm.bean;

import java.util.List;

import org.apache.struts.util.LabelValueBean;

import jp.terasoluna.fw.web.struts.form.ValidatorActionFormEx;

/**
 * ActionForm class.
 * 
 * @author ss052
 */
public class M_WLMFormBean extends ValidatorActionFormEx {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4141536509931644085L;
	
	
	private String id_screen = null;
	
	private String screen_desc = null;
	
	private String id_section_group = null;
	
	private String listApSection = null;
	
	private String listApAction = null;
	
	private String access_type = null;
	
	private List<SectionGroupBean> listSectionGroup = null;
	
	private List<SectionBean> listSection = null;
	
	private List<SectionBean> listWfSection = null;
	
	private List<LevelBean> listWfLevel = null;
	
	private List<ActionBean> listWfAction = null;
	
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

	public void setId_screen(String id_screen) {
		this.id_screen = id_screen;
	}

	public String getId_screen() {
		return id_screen;
	}
	
	public String getScreen_desc() {
		return screen_desc;
	}
	
	public void setScreen_desc(String screen_desc) {
		this.screen_desc = screen_desc;
	}

	public String getId_section_group() {
		return id_section_group;
	}

	public void setId_section_group(String id_section_group) {
		this.id_section_group = id_section_group;
	}

	public String getListApSection() {
		return listApSection;
	}

	public void setListApSection(String listApSection) {
		this.listApSection = listApSection;
	}

	public String getListApAction() {
		return listApAction;
	}

	public void setListApAction(String listApAction) {
		this.listApAction = listApAction;
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
}