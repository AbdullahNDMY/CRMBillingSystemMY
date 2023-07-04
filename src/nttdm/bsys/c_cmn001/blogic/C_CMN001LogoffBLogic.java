package nttdm.bsys.c_cmn001.blogic;

import jp.terasoluna.fw.web.struts.actions.LogoffAction;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class C_CMN001LogoffBLogic extends LogoffAction {

	public ActionForward doExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) {
		BillingSystemUserValueObject uvo = (BillingSystemUserValueObject)req.getSession().getAttribute("USER_VALUE_OBJECT");
		if(uvo != null) {
			/**
			 * Audit Trail
			 */
			Integer idAudit = CommonUtils.auditTrailBegin(uvo.getId_user(), BillingSystemConstants.MODULE_A, 
					BillingSystemConstants.SUB_MODULE_A_USR, null, null, 
					BillingSystemConstants.AUDIT_TRAIL_LOGOUT);
			CommonUtils.auditTrailEnd(idAudit);//End Audit Trail
		}
		return super.doExecute(mapping, form, req, res);
	}	
}