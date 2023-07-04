/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : Alert
 * SERVICE NAME   : Alert
 * FUNCTION       : Alert handling BLogic
 * FILE NAME      : M_ALTR01BLogic.java
 *
 * Copyright (C) 2011 NTTDATA Corporation
 *
 * History
 * 2011/07/26 Tuyen Fixed for error of checking style
 * 
**********************************************************/

package nttdm.bsys.m_alt.blogic;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.a_usr.bean.UserBean;
import nttdm.bsys.c_cmn001.bean.UserAccess;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.G_DOC_P02;
import nttdm.bsys.m_alt.dto.M_ALTR01Input;
import nttdm.bsys.m_alt.dto.M_ALTR01Output;
import nttdm.bsys.m_alt.bean.FileInfo;
import nttdm.bsys.m_alt.bean.Notice;
import nttdm.bsys.m_alt.blogic.AbstractM_ALTR01BLogic;

/**
 * Alert Handling BLogic
 * <ul>
 * <li>Alert Handling BLogic
 * </ul>
 * @author  NTTData Vietnam
 * @version 1.1 
 */

public class M_ALTR01BLogic extends AbstractM_ALTR01BLogic {
	/**
	 * <div>EDIT_MODE</div>
	 */
	static final String EDIT_MODE = "0";
	/**
	 * <div>VIEW_MODE</div>
	 */
	static final String VIEW_MODE = "1";
	/**
	 * <div>SQL_GET_NOTICE_INFO</div>
	 */
	static final String SQL_GET_NOTICE_INFO = "SELECT.M_ALT.SQL001";
	/**
	 * <div>SQL_GET_USER_INFO</div>
	 */
	static final String SQL_GET_USER_INFO = "SELECT.M_ALT.SQL005";
	/**
	 * <div>SQL_GET_FILE_INFO</div>
	 */
	static final String SQL_GET_FILE_INFO = "SELECT.M_ALT.SQL009";
	/**
	 * <div>SUBJECT_REFIX</div>
	 */
	static final String SUBJECT_REFIX = "RE:";
	
	static final String SUBJECT_FWFIX = "Fw:";
	/**
	 * <div>ID_USER_DELIMITER</div>
	 */
	static final String ID_USER_DELIMITER = ";";
	/**
	 * <div>CHECKBOX_ON</div>
	 */
	static final String CHECKBOX_ON = "1";
	/**
	 * <div>CHECKBOX_OFF</div>
	 */
	static final String CHECKBOX_OFF = "0";
	/**
	 * <div>PRIORITY_DEFAULT_VALUE</div>
	 */
	static final String PRIORITY_DEFAULT_VALUE = "-1";
	/**
	 * 
	 * @param param
	 * @return BLogicResult
	 */
	public BLogicResult execute(M_ALTR01Input param) {
		BLogicResult result = new BLogicResult();
		M_ALTR01Output output = new M_ALTR01Output();	
		
		//Get click event
		int clickEvent = 0;
		if(param.getClick_event() != null){
			clickEvent = Integer.parseInt(param.getClick_event());
		}
		//add list user for autocomplete textbox
		List <UserBean> arr_usr = new ArrayList<UserBean>();
		arr_usr = queryDAO.executeForObjectList(M_ALTR01BLogic.SQL_GET_USER_INFO,null);
		String lst_user="";
		for (UserBean formBean : arr_usr) {
			lst_user = lst_user.concat(formBean.getId_user()).concat(M_ALTR01BLogic.ID_USER_DELIMITER);
		}
		output.setLst_user(lst_user);
		
		//Get notification in any screen except New screen
		if(clickEvent != 1){
			Notice notice = queryDAO.executeForObject(M_ALTR01BLogic.SQL_GET_NOTICE_INFO,param.getId_msg() , Notice.class);
			if(notice != null){
				/*
				 * set notice id_msg " Id_new = false"
				 */
				HashMap<String, String>update_notice_msg_in= new HashMap<String, String>();
				update_notice_msg_in.put("id_msg", param.getId_msg());
				update_notice_msg_in.put("id_login",param.getUvo().getId_user().toString());
				updateDAO.execute("UPDATE.M_ALT.SET_NEWSTATUS.SQL0012", update_notice_msg_in);
				
				output.setCc_msg(notice.getCc_msg());
				if(notice.getCc_msg() != null) {
					String cc_msg = notice.getCc_msg();
					String[] cc = cc_msg.split(";");
					String cc_name = "";
					for(String s : cc) {
						cc_name += (CommonUtils.getUserName(queryDAO, s, s) + ";");
					}
					output.setCc_msg_name(cc_name);
				}
				output.setEnd_date(notice.getEnd_date());
				output.setId_creator(notice.getId_creator());
				if(notice.getId_creator() != null)
					output.setCreator_name(CommonUtils.getUserName(queryDAO, notice.getId_creator(), notice.getId_creator()));
				output.setId_msg(notice.getId_msg());
				
				String msg =notice.getMsg();
				if (msg != null) {
				    msg = msg.replaceAll("<br>", "\r\n");
				    notice.setMsg(msg);
				}
				output.setMsg(notice.getMsg());
				output.setMsg_type(notice.getMsg_type());
				output.setPriority(notice.getPriority());
				output.setReminder_date(notice.getReminder_date());
				output.setStart_date(notice.getStart_date());
				output.setSubject(notice.getSubject());
				output.setTo_msg(notice.getTo_msg());
				if(notice.getTo_msg() != null) {
					String to_msg = notice.getTo_msg();
					String[] to = to_msg.split(";");
					String to_name = "";
					for(String s : to) {
						to_name += (CommonUtils.getUserName(queryDAO, s, s) + ";");
					}
					output.setTo_msg_name(to_name);
				}
				
				//Set hidden fields for checking any changes on screen
				output.setCc_msg_hidden(notice.getCc_msg());							
				output.setMsg_hidden(notice.getMsg());
				output.setMsg_type_hidden(notice.getMsg_type());
				output.setPriority_hidden(notice.getPriority());				
				output.setSubject_hidden(notice.getSubject());
								
				if(notice.getPriority() == null ){
					output.setImportance_chk(M_ALTR01BLogic.CHECKBOX_OFF);
					output.setImportance_chk_hidden(M_ALTR01BLogic.CHECKBOX_OFF);
					output.setPriority(M_ALTR01BLogic.PRIORITY_DEFAULT_VALUE);
					output.setPriority_hidden(M_ALTR01BLogic.PRIORITY_DEFAULT_VALUE);
				}else{
					output.setImportance_chk(M_ALTR01BLogic.CHECKBOX_ON);
					output.setImportance_chk_hidden(M_ALTR01BLogic.CHECKBOX_ON);
				}
				
				if(notice.getReminder_date() == null){
					output.setReminder_chk(M_ALTR01BLogic.CHECKBOX_OFF);
					output.setReminder_chk_hidden(M_ALTR01BLogic.CHECKBOX_OFF);
				}else{
					output.setReminder_chk(M_ALTR01BLogic.CHECKBOX_ON);
					output.setReminder_chk_hidden(M_ALTR01BLogic.CHECKBOX_ON);
				}
			}
		}
		List<FileInfo> fileInfos = new ArrayList<FileInfo>();
		//call G_DOC_P02 process
		G_DOC_P02 g_doc = new G_DOC_P02();
		fileInfos = g_doc.getFileName(param, queryDAO);
		//fileInfos = queryDAO.executeForObjectList(M_ALTR01BLogic.SQL_GET_FILE_INFO,Long.parseLong(param.getId_msg()));
		if(fileInfos == null){
			fileInfos = new ArrayList<FileInfo>();
		}
		output.setFileInfos(fileInfos);
		String acctype="";
		for(int i =0;i<param.getUvo().getUser_access().size();i++)
		{
			UserAccess usac= new UserAccess();
			usac=param.getUvo().getUser_access().get(i);
			if(usac.getId_sub_module().equals("M-ALT"))
				acctype=usac.getAccess_type();
			
		}
		if(!acctype.equals(""));
		output.setUser_access(Integer.parseInt(acctype));
		
		switch (clickEvent){
		//View screen
		case 0:
			output.setScreen_mod(M_ALTR01BLogic.VIEW_MODE);
			break;
		//New screen
		case 1:
			output.setScreen_mod(M_ALTR01BLogic.EDIT_MODE);
			output.setMsg_type(param.getMsg_type());
			output.setMsg_type_label(param.getMsg_type_label());			
			output.setImportance_chk(M_ALTR01BLogic.CHECKBOX_OFF);
			output.setPriority(M_ALTR01BLogic.PRIORITY_DEFAULT_VALUE);
			output.setReminder_chk(M_ALTR01BLogic.CHECKBOX_OFF);
			output.setPriority_hidden(M_ALTR01BLogic.PRIORITY_DEFAULT_VALUE);
			output.setMsg_type_hidden(param.getMsg_type());
			output.setId_msg(CommonUtils.toString(0));
			
			
			
			
			output.setFileInfos(new ArrayList<FileInfo>());
			try {
				BeanUtils.copyProperties(output, param);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			break;
			
		//Reply screen
		case 2:
			output.setScreen_mod(M_ALTR01BLogic.EDIT_MODE);
//			output.setStart_date("");
//			output.setEnd_date("");
			output.setReminder_date("");	
			output.setScreen_mode_forward("1");
			output.setCc_msg("");			
			output.setTo_msg(output.getId_creator());
			output.setSubject(M_ALTR01BLogic.SUBJECT_REFIX.concat(CommonUtils.toString(output.getSubject())));
			output.setStart_date_hidden(output.getStart_date());
			output.setEnd_date_hidden(output.getEnd_date());
			output.setReminder_date_hidden("");
			output.setCc_msg_hidden("");
			output.setTo_msg_hidden(output.getId_creator());			
			output.setSubject_hidden(output.getSubject());
			break;
		//Reply To All screen
		case 3:
			output.setScreen_mod(M_ALTR01BLogic.EDIT_MODE);
			if(output.getId_creator() != null && !output.getId_creator().equals("")){
				if(output.getTo_msg() != null && !output.getTo_msg().equals("")){
					output.setTo_msg(output.getId_creator().concat(M_ALTR01BLogic.ID_USER_DELIMITER).concat(output.getTo_msg()));
				}else{
					output.setTo_msg(output.getId_creator());
				}
			}			
//			output.setStart_date("");
			output.setScreen_mode_forward("1");
//			output.setEnd_date("");
			output.setReminder_date("");
			output.setSubject(M_ALTR01BLogic.SUBJECT_REFIX.concat(CommonUtils.toString(output.getSubject())));
			output.setStart_date_hidden(output.getStart_date());
			output.setEnd_date_hidden(output.getEnd_date());
			output.setReminder_date_hidden("");
			output.setTo_msg_hidden(output.getTo_msg());			
			output.setSubject_hidden(output.getSubject());		
			break;
		//Forward screen
		case 4:
			//forward screen screen_mode_forward = 1. another is 0
			output.setScreen_mod(M_ALTR01BLogic.EDIT_MODE);			
			output.setScreen_mode_forward("1");
//			output.setStart_date("");
//			output.setEnd_date("");
			output.setReminder_date("");
			output.setTo_msg("");
			output.setCc_msg("");
			output.setSubject(M_ALTR01BLogic.SUBJECT_FWFIX.concat(CommonUtils.toString(output.getSubject())));
			output.setStart_date_hidden(output.getStart_date());
			output.setEnd_date_hidden(output.getEnd_date());
			output.setReminder_date_hidden("");
			output.setTo_msg_hidden("");
			output.setCc_msg_hidden("");
			break;

		//Edit screen
		case 5:
			output.setScreen_mod(M_ALTR01BLogic.EDIT_MODE);
//			output.setStart_date("");
			output.setStart_date_hidden(output.getStart_date());
//			output.setEnd_date("");
			output.setEnd_date_hidden(output.getEnd_date());
			output.setReminder_date("");	
//			output.setScreen_mode_forward("1");
			output.setCc_msg("");			
			output.setTo_msg(output.getId_creator());
			output.setSubject(CommonUtils.toString(output.getSubject()));
			output.setReminder_date_hidden("");
			output.setCc_msg_hidden("");
			output.setTo_msg_hidden(output.getId_creator());			
			output.setSubject_hidden(output.getSubject());
			break;
			default:
				break;
		}
		result.setResultObject(output);
		result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS);
		return result;
	}
}