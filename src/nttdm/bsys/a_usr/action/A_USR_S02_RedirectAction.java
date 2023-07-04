/*
 * @(#)A_USR_S02_RedirectAction.java
 *
 *
 */
package nttdm.bsys.a_usr.action;

import javax.servlet.http.*;

import jp.terasoluna.fw.web.struts.actions.*;
import org.apache.struts.action.*;

/**
 * Action class.
 * 
 * @author Tran Manh Hung
 */
public class A_USR_S02_RedirectAction extends ActionEx {

	public ActionForward doExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) {
		String idUser = req.getAttribute("idUser").toString();
		//Get default success destination
		ActionForward success = mapping.findForward("success");
		String queryString = "?event=forward_view&mode=view&idUser=" + idUser + "&isSaveSuccess=1";
		String location = success.getPath();
		//Insert querystring into url
		ActionForward modifiedSuccess = new ActionForward();
		modifiedSuccess.setName(success.getName());
		modifiedSuccess.setPath(location + queryString);
		modifiedSuccess.setRedirect(true);
		return modifiedSuccess;
	}
}