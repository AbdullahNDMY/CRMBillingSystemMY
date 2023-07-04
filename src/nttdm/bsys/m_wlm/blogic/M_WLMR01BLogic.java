/*
 * @(#)M_WLMR01BLogic.java
 *
 *
 */
package nttdm.bsys.m_wlm.blogic;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.util.LabelValueBean;

import jp.terasoluna.fw.service.thin.BLogicResult;

import nttdm.bsys.m_wlm.bean.ActionBean;
import nttdm.bsys.m_wlm.bean.LevelBean;
import nttdm.bsys.m_wlm.bean.SectionBean;
import nttdm.bsys.m_wlm.bean.SectionGroupBean;
import nttdm.bsys.m_wlm.blogic.AbstractM_WLMR01BLogic;
import nttdm.bsys.m_wlm.dto.M_WLMR01Input;
import nttdm.bsys.m_wlm.dto.M_WLMR01Output;

/**
 * BusinessLogic class.
 * 
 * @author ss052
 */
public class M_WLMR01BLogic extends AbstractM_WLMR01BLogic {

	/**
	 * 
	 * @param param
	 * @return 
	 */
	private static final String SELECT_SQL001 = "SELECT.M_WLM.SQL001";
	private static final String SELECT_SQL002 = "SELECT.M_WLM.SQL002";
	private static final String SELECT_SQL003 = "SELECT.M_WLM.SQL003";
	private static final String SELECT_SQL004 = "SELECT.M_WLM.SQL004";
	private static final String SELECT_SQL005 = "SELECT.M_WLM.SQL005";
	private static final String SELECT_SQL006 = "SELECT.M_WLM.SQL006";
	
	public BLogicResult execute(M_WLMR01Input param) {
		BLogicResult result = new BLogicResult();

		// Define output
		M_WLMR01Output output = new M_WLMR01Output();
		
		output.setId_screen(param.getId_screen());
		output.setId_section_group(param.getId_section_group());
		output.setAccess_type(param.getUvo().getUserAccessInfo("M", "M-WLM").getAccess_type());
		
		output.setListSectionGroup(getSectionGroup(param));
		output.setListSection(getSection(param));
		output.setListWfSection(getWfSection(param));
		output.setListWfLevel(getWfLevel(param));
		output.setListWfAction(getWfAction(param));
		output.setListPic(getListPic());

		result.setResultObject(output);
		result.setResultString("success");
		return result;
	}
	
	private List<SectionGroupBean> getSectionGroup(M_WLMR01Input param){
		
		List<SectionGroupBean> listSecGrp = 
			queryDAO.executeForObjectList(SELECT_SQL001, param);
		
		if (listSecGrp.size() == 0)
			return null;
		return listSecGrp;
	}
	
	private List<SectionBean> getSection(M_WLMR01Input param){
		
		List<SectionBean> listSection =
			queryDAO.executeForObjectList(SELECT_SQL002, param);
		if (listSection != null)
			return listSection;
		return new ArrayList<SectionBean>();
	}
	
	private List<SectionBean> getWfSection(M_WLMR01Input param){
		
		List<SectionBean> listSection =
			queryDAO.executeForObjectList(SELECT_SQL003, param);
		if (listSection != null)
			return listSection;
		return new ArrayList<SectionBean>();
	}
	
	private List<LevelBean> getWfLevel(M_WLMR01Input param){
		List<LevelBean> listLevel =
			queryDAO.executeForObjectList(SELECT_SQL004, param);
		if (listLevel != null)
			return listLevel;
		return new ArrayList<LevelBean>();
	}
	
	private List<ActionBean> getWfAction(M_WLMR01Input param){
		List<ActionBean> listAction =
			queryDAO.executeForObjectList(SELECT_SQL005, param);
		if (listAction != null)
			return listAction;
		return new ArrayList<ActionBean>();
	}
	
	private List<LabelValueBean> getListPic() {
		List<LabelValueBean> listPic = queryDAO.executeForObjectList(SELECT_SQL006, null);
		if(listPic != null)
			return listPic;
		return new ArrayList<LabelValueBean>();
	}
}