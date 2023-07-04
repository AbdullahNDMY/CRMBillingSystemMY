/*
 * @(#)A_USR_S02_InitDataBLogic.java
 *
 *
 */
package nttdm.bsys.a_usr.blogic;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.a_usr.dto.*;

import java.io.BufferedReader;
import java.util.*;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.Globals;

import nttdm.bsys.a_usr.blogic.AbstractA_USR_S02_InitDataBLogic;
import nttdm.bsys.common.util.dto.*;


/**
 * BusinessLogic class.
 * 
 * @author hungtm
 */
public class A_USR_S02_InitDataBLogic extends AbstractA_USR_S02_InitDataBLogic {

	public BLogicResult execute(A_USR_S02IO input) {
		BLogicResult result = new BLogicResult();
		A_USR_S02IO output = new A_USR_S02IO();
		String mode = input.getMode();
		String pageEvent = input.getPageEvent();
		User user = null;
		List<UserAccess> listUserAccess = null;
		List<SubModule> listSubModule = queryDAO.executeForObjectList("SELECT.A_USR.GET_LIST_SUBMODULE", null);
		//New mode
		if(mode.equals(Mode.NEW)){
			//If pageLoad, the data is default
			if(pageEvent.equals(PageEvent.LOAD)){
				//user has no data
				user = new User();
				user.setUserStatus("1");
				user.setAccessRight("R");
				//listUserAccess equals listSubModule and accessType is ReadOnly
				listUserAccess = new ArrayList<UserAccess>();
				for(int i = 0; i < listSubModule.size();i++){
					UserAccess access = new UserAccess();
					access.setAccessType(AccessType.READ_ONLY);
					access.setIdModule(listSubModule.get(i).getIdModule());
					access.setIdSubModule(listSubModule.get(i).getIdSubModule());
					listUserAccess.add(access);
				}
			} 
			//if postback, mean the data will be submited data
			else {
				user = input.getUser();
				listUserAccess = input.getListUserAccess();
			}
		}
		//Edit Mode, View mode
		else{
			if(input.getIsSaveSuccess().equals("1")){
				BLogicMessages msg = new BLogicMessages();
				msg.add(Globals.MESSAGE_KEY,new BLogicMessage("info.ERR2SC003"));
				result.setMessages(msg);
			}
			//If pageLoad, mean the first time, data will be got from data
			if(pageEvent.equals(PageEvent.LOAD)){
				String idUser = input.getIdUser();
				user = queryDAO.executeForObject("SELECT.A_USER.GET_USER_INFO", idUser, User.class);
				user.setPassword("BILLINGSYSTEM");
				user.setReEnterPassword("BILLINGSYSTEM");	//Re-enter passwrod will be the password in database
				listUserAccess = queryDAO.executeForObjectList("SELECT.A_USER.GET_LIST_USERACCESS", idUser);
			} 
			//if postback and edit mode, mean the data will be submited data
			//View mode never has event POSTBACK
			else if(pageEvent.equals(PageEvent.POSTBACK)){
				user = input.getUser();
				listUserAccess = input.getListUserAccess();
			}
		}
		String accessType = input.getAccessType("A", "A-USR");
		List<Department> listDepartment = queryDAO.executeForObjectList("SELECT.A_USR.GET_LIST_DEPARTMENT",null);
		List<Division> listDivision = queryDAO.executeForObjectList("SELECT.A_USR.GET_LIST_DIVISION",null);
		List<Role> listRole = queryDAO.executeForObjectList("SELECT.A_USR.GET_LIST_ROLE",null);
		List<Module> listModule = queryDAO.executeForObjectList("SELECT.A_USR.GET_LIST_MODULE",null);
		String email = user.getEmail();
		List<User> dispEmailList = new ArrayList<User>();
		if (!StringUtils.isEmpty(email)) {
			if (email.indexOf(";") >= 1) {
				String[] emails = email.split(";");
				for (String emailAdr : emails) {
					User tmp = new User();
					tmp.setEmail(emailAdr);
					dispEmailList.add(tmp);
				}
			} else {
				dispEmailList.add(user);
			}
		}
		user.setDispEmail(dispEmailList);

		output.setUser(user);
		output.setListUserAccess(listUserAccess);
		output.setAccessType(accessType);
		output.setListDepartment(listDepartment);
		output.setListDivision(listDivision);
		output.setListRole(listRole);
		output.setListSubModule(listSubModule);
		output.setListModule(listModule);
		output.setMode(mode);
		result.setResultObject(output);
		result.setResultString(mode);
		return result;
	}
}