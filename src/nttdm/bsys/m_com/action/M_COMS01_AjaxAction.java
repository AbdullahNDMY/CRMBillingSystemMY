
/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_COM
 * SERVICE NAME   : M_COMS01
 * FUNCTION       : Get Bank information
 * FILE NAME      : M_COMS01_AjaxAction.java
 *
 * 
 * 
**********************************************************/
package nttdm.bsys.m_com.action;

import javax.servlet.http.*;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.web.struts.actions.ActionEx;
import jp.terasoluna.fw.web.struts.form.DynaValidatorActionFormEx;

import nttdm.bsys.m_com.dto.Bank;

import org.apache.struts.action.*;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
/**
 * Action class.
 
 *@author NTT Data Vietnam	
 */
public class M_COMS01_AjaxAction extends ActionEx {
	
	protected QueryDAO queryDAO;

	public QueryDAO getQueryDAO() {
		return queryDAO;
	}

	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}
	
	private static final String JSON = "application/json";
	private static final String PROCESS_GET_BANK_INFORMATION = "1";
	public ActionForward doExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String process = req.getParameter("process").toString();
		Gson googleSon = new Gson();
		String jsonString = "";
		if(process.equals(PROCESS_GET_BANK_INFORMATION)){
			String idBank = req.getParameter("idBank");
			Bank bank = queryDAO.executeForObject("SELECT.M_COM.GET_BANK_INFO", idBank, Bank.class);
			jsonString = googleSon.toJson(bank);
		}
		
		res.setContentType(JSON);
		res.getWriter().print(jsonString);
		return null;
	}

}