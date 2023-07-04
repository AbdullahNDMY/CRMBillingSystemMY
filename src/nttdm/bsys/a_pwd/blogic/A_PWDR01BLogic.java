/*
 * @(#)A_PWDS01R01BLogic.java
 *
 *
 */
package nttdm.bsys.a_pwd.blogic;
import java.util.Map;

import org.apache.struts.Globals;
import org.springframework.context.ApplicationContext;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import jp.terasoluna.fw.util.PropertyUtil;
import nttdm.bsys.a_pwd.dto.A_PWDS01R01Input;
import nttdm.bsys.a_pwd.dto.A_PWDS01R01Output;

import nttdm.bsys.a_pwd.blogic.AbstractA_PWDR01BLogic;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.SecurityUtil;

/**
 * ビジネスロジッククラス。
 * 
 * @author ss042
 */
public class A_PWDR01BLogic extends AbstractA_PWDR01BLogic {

	/**
	 * 
	 * @param param
	 * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
	 */
	public BLogicResult execute(A_PWDS01R01Input param) {
		BLogicResult result = new BLogicResult();
		A_PWDS01R01Output output = new A_PWDS01R01Output();
		PropertyUtil.addPropertyFile("A_PWD-messages");
		if(!param.getUvo().getPassword().equals(SecurityUtil.md5(param.getOldPassword())))
		{
			
			BLogicMessages errors = new BLogicMessages();
			
			//GlobalMessageResources msg = GlobalMessageResources.getInstance();
			
			String key = PropertyUtil.getProperty("screen.a_pwds01.txtOldPassword");
			
			errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC002",key));
			result.setErrors(errors);
			result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
			return result;
		}		
		
		if(!param.getReEnterPassword().equals(param.getNewPassword()))
		{
			Object[] items=new Object[2];
			 
			items[0]=PropertyUtil.getProperty("screen.a_pwds01.txtNewPassword");
			items[1]=PropertyUtil.getProperty("screen.a_pwds01.txtReEnterNewPassword");
			
			BLogicMessages errors = new BLogicMessages();
			errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC004",items));
			
			result.setErrors(errors);
			result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
			return result;
		}
		
		if(param.getUvo().getPassword().equals(SecurityUtil.md5(param.getNewPassword())))
		{
			BLogicMessages errors = new BLogicMessages();
			errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC003",PropertyUtil.getProperty("screen.a_pwds01.password")));
			result.setErrors(errors);
			result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
			return result;
		}		
		
		if(param.getNewPassword().length()<6)
		{
			Object[] items=new Object[3];
			items[0]=PropertyUtil.getProperty("screen.a_pwds01.password");
			items[1]=PropertyUtil.getProperty("screen.a_pwds01.password");
			items[2]="6";
		
			BLogicMessages errors = new BLogicMessages();
			errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC017",items));
		
			result.setErrors(errors);
			result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
			return result;
		}		
		/**
		 * Audit Trail
		 */
		Map<String, Object> user = queryDAO.executeForMap("SELECT.A_PWD.SQL001", param);
		//Modify "M_AUDIT_TRAIL_H.STATUS":1,M_USER.USER_STATUS = 1　"Active" 2,M_USER.USER_STATUS= 0　"Inactive"
        String userstatus="";
        if("1".equals(CommonUtils.toString(user.get("USER_STATUS")))){
            userstatus="Active";
        }else {
            userstatus="Inactive";
        }
		Integer idAudit = CommonUtils.auditTrailBegin(param.getUserID(), BillingSystemConstants.MODULE_A, 
				BillingSystemConstants.SUB_MODULE_A_PWD, user.get("ID_USER")
						+ ":" + user.get("USER_NAME"), userstatus,
						BillingSystemConstants.AUDIT_TRAIL_EDITED);
		param.setIdAudit(idAudit);
		param.setNewPassword(SecurityUtil.md5(param.getNewPassword()));
		updateDAO.execute("UPDATE.A_PWD.SQL001", param);
		CommonUtils.auditTrailEnd(idAudit);//End Audit Trail
		param.getUvo().setUser_status("1");
		output.setUvo(param.getUvo());
		result.setResultObject(output);
		result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS);
		param.getUvo().setPassword(param.getNewPassword());
		return result;
	}
}