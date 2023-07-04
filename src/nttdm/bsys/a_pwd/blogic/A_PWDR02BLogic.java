/*
 * @(#)A_PWDS01R02BLogic.java
 *
 *
 */
package nttdm.bsys.a_pwd.blogic;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.a_pwd.dto.A_PWDS01R02Input;
import nttdm.bsys.a_pwd.dto.A_PWDS01R02Output;
import nttdm.bsys.a_pwd.blogic.AbstractA_PWDR02BLogic;
import nttdm.bsys.common.fw.*;
/**
 * ビジネスロジッククラス。
 * 
 * @author ss042
 */
public class A_PWDR02BLogic extends AbstractA_PWDR02BLogic {

	/**
	 * 
	 * @param param
	 * @param session 
	 * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
	 */
	public BLogicResult execute(A_PWDS01R02Input param) {
		// TODO Auto-generated method stub
		BLogicResult result = new BLogicResult();
		BillingSystemUserValueObject uvo =param.getUvo();
	
		A_PWDS01R02Output outputDTO = new A_PWDS01R02Output();
		outputDTO.setUserID(uvo.getId_user());
		outputDTO.setUserName(uvo.getUser_name());
		
		result.setResultObject(outputDTO);
		result.setResultString("success");
		return result;
	}
}