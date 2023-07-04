/*
 * @(#)M_WLMR02BLogic.java
 *
 *
 */
package nttdm.bsys.m_wlm.blogic;

import org.apache.struts.Globals;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.m_wlm.dto.M_WLMR02Input;
import nttdm.bsys.m_wlm.dto.M_WLMR02Output;

import nttdm.bsys.m_wlm.bean.ActionBean;
import nttdm.bsys.m_wlm.bean.SectionBean;
import nttdm.bsys.m_wlm.blogic.AbstractM_WLMR02BLogic;

/**
 * BusinessLogic class.
 * 
 * @author ss052
 */
public class M_WLMR02BLogic extends AbstractM_WLMR02BLogic {
	
	private static final String DELETE_SQL001 = "DELETE.M_WLM.SQL001";
	private static final String DELETE_SQL002 = "DELETE.M_WLM.SQL002";
	private static final String DELETE_SQL003 = "DELETE.M_WLM.SQL003";
	private static final String INSERT_SQL001 = "INSERT.M_WLM.SQL001";
	private static final String INSERT_SQL002 = "INSERT.M_WLM.SQL002";
	private static final String INSERT_SQL003 = "INSERT.M_WLM.SQL003";	
	
	private static final String COLON_KEY = ":";
	
	private static final String SEMI_COLON_KEY = ";";
	
	private static final String SUCCESS_MESSAGE = "info.ERR2SC003";
	
	private static final String FAILURE_MESSAGE = "info.ERR2SC004";
	
	private static final String PATTERN_ID_NAME = "%s:%s";	
	
	/**
	 * 
	 * @param param
	 * @return 
	 */
	public BLogicResult execute(M_WLMR02Input param) {
		BLogicResult result = new BLogicResult();
		
		BLogicMessages messages = new BLogicMessages();
		
		// Define output
		M_WLMR02Output output = new M_WLMR02Output();
		output.setId_screen(param.getId_screen());
		output.setId_section_group(param.getId_section_group());
		
		try {
			// Audit Trail
			Integer auditID = -1;
			
			// Delete existed section and action
			deleteSection(param);
			deleteAction(param);
			
			// Insert Workflow Section
			auditID = insertWfSectionList(param, auditID);
			
			// Insert Workflow Action
			insertWfActionList(param, auditID);			
			CommonUtils.auditTrailEnd(auditID);//End Audit Trail
			
			messages.add(Globals.MESSAGE_KEY, new BLogicMessage(SUCCESS_MESSAGE));
			
			result.setMessages(messages);
			result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS);
		}catch(Exception ex){
			
			messages.add(Globals.MESSAGE_KEY, new BLogicMessage(FAILURE_MESSAGE));
			
			result.setMessages(messages);
			result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
		}
		
		result.setResultObject(output);		
		
		return result;
	}
	
	/**
	 * Insert into WF_SECTION
	 * @param param
	 */
	private Integer insertWfSectionList(M_WLMR02Input param, Integer auditID){
		SectionBean secBean = new SectionBean();
		secBean.setId_screen(param.getId_screen());
		secBean.setId_secgrp(param.getId_section_group());
		secBean.setId_user(param.getUvo().getId_user());
		
		// The structure Section1:Sequence1;Section2:Sequence2;
		String listSection = param.getListApSection();
		
		String[] sectionArray = listSection.split(SEMI_COLON_KEY);
		
		 for (String sectionStr : sectionArray) {
			 String[] sectionMember = sectionStr.split(COLON_KEY);
			 if (sectionMember.length > 0){
				 
				 secBean.setSection_no(sectionMember[0].trim());
				 secBean.setSequence_no(sectionMember[1].trim());
				 
				 // Audit Trail
				 if( auditID ==  -1 ){
					 auditID = this.writeAuditHeader(param);
				 }
				 secBean.setAuditID(auditID);
				 
				 updateDAO.execute(INSERT_SQL001, secBean);
			 }
		 }
		 return auditID;
	}
	
	/**
	 * Insert into WF_ACTION
	 * @param param
	 */
	private Integer insertWfActionList(M_WLMR02Input param, Integer auditID){
		ActionBean actBean = new ActionBean();
		actBean.setId_user(param.getUvo().getId_user());
		
		String listAction = param.getListApAction();
		// The structure Sec:Level:Act:ActType:pic:Res:Con1:Con2:Con3;
		String[] actionArray = listAction.split(SEMI_COLON_KEY);
		
		for (String actionStr : actionArray){
			String[] actMember = actionStr.split(COLON_KEY);
			if (actMember.length > 0){
				actBean.setSection_no(actMember[0].trim());
				actBean.setLevel_seq(actMember[1].trim());
				actBean.setId_action(actMember[2].trim());
				actBean.setAction_type(actMember[3].trim());
				actBean.setPic(actMember[4].trim());
				actBean.setResponse_expire(actMember[5].trim());
				actBean.setCondition_primary(actMember[6].trim());
				actBean.setCondition_operator(actMember[7].trim());
				actBean.setCondition_secondary(actMember[8].trim());
				
				 // Audit Trail
				 if( auditID ==  -1 ){
					 auditID = this.writeAuditHeader(param);
				 }
				 actBean.setAuditID(auditID);
				 
				 updateDAO.execute(INSERT_SQL002, actBean);
			}
			//SON-NB ADD 20100903
			if ("AA".equals(actMember[3].trim())){
				SectionBean secBean = new SectionBean();
				secBean.setId_screen(param.getId_screen());
				secBean.setSection_no(actMember[0].trim());
				secBean.setId_user(actMember[4].trim());
				
				updateDAO.execute(DELETE_SQL003, secBean);
				
				 // Audit Trail
				 if( auditID ==  -1 ){
					 auditID = this.writeAuditHeader(param);
				 }
				 secBean.setAuditID(auditID);
				 
				 updateDAO.execute(INSERT_SQL003, secBean);
			}
			
		}
		return auditID;
	}
	
	/**
	 * Delete Sections
	 * @param param
	 */
	private void deleteSection(M_WLMR02Input param){
		
		SectionBean secBean = new SectionBean();
		secBean.setId_screen(param.getId_screen());
		secBean.setId_secgrp(param.getId_section_group());
		
		this.updateDAO.execute(DELETE_SQL001, secBean);
	}
	
	/**
	 * Delete Actions
	 * @param param
	 */
	private void deleteAction(M_WLMR02Input param){
		
		SectionBean secBean = new SectionBean();
		secBean.setId_screen(param.getId_screen());
		secBean.setId_secgrp(param.getId_section_group());
		
		this.updateDAO.execute(DELETE_SQL002, secBean);
	}
	
	/**
	 * Write Audit Trail Header
	 * @param param
	 * @return AuditID
	 */
	private Integer writeAuditHeader(M_WLMR02Input param){
				
		Integer retAuditID = CommonUtils.auditTrailBegin(param.getUvo().getId_user()
														 , BillingSystemConstants.MODULE_M
														 , BillingSystemConstants.SUB_MODULE_M_WLM
														 , String.format(PATTERN_ID_NAME, param.getId_screen(), param.getScreen_desc())
														 , null
														 , BillingSystemConstants.AUDIT_TRAIL_CREATED);
		return retAuditID;
	}
	
}