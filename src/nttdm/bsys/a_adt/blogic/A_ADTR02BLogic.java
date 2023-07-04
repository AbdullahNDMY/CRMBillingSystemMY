/*
 * @(#)A_ADTR02BLogic.java
 *
 *
 */
package nttdm.bsys.a_adt.blogic;

import java.util.ArrayList;
import java.util.List;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.a_adt.dto.A_ADTR02Input;
import nttdm.bsys.a_adt.dto.A_ADTR02Output;
import nttdm.bsys.a_adt.bean.AuditDetail;
import nttdm.bsys.a_adt.bean.AuditHeader;
import nttdm.bsys.a_adt.blogic.AbstractA_ADTR02BLogic;
import nttdm.bsys.common.util.BillingSystemConstants;

/**
 * ビジネスロジッククラス。
 * 
 * @author ss042
 */
public class A_ADTR02BLogic extends AbstractA_ADTR02BLogic {	
	
	// Select audit header SQL
	private static final String SQL_SELECT_AUDIT_HEADER = "SELECT.A_ADT.SQL003";
	
	// Select audit detail SQL
	private static final String SQL_SELECT_AUDIT_DETAIL = "SELECT.A_ADT.SQL004";
	
	/**
	 * 
	 * @param param
	 * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
	 */
	public BLogicResult execute(A_ADTR02Input param) {
		BLogicResult result = new BLogicResult();
		A_ADTR02Output output = new A_ADTR02Output();
		
        //Get audit header info
		AuditHeader  auditHeader = queryDAO.executeForObject(SQL_SELECT_AUDIT_HEADER,param,AuditHeader.class);
		
		//Get audit detail info
		//TODO
		//String module=auditHeader.getModuleName();
		String submodule=auditHeader.getSubModuleName();
		String action=auditHeader.getAction();
		List<AuditDetail>  auditDetailList=new ArrayList<AuditDetail>();
		output.setAuditHeader(auditHeader);
		output.setAuditDetailList(auditDetailList);
		result.setResultObject(output);
		result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS);
		if(submodule.equals("EOM - Data Import (ClearCall Usage)")&&action.equals(BillingSystemConstants.AUDIT_TRAIL_CREATED)){

			return result;
		
		}
		if(submodule.equals("EOM - Data Export (SingPort Invoice Data)")&&action.equals(BillingSystemConstants.AUDIT_TRAIL_CREATED)){

			return result;
		
		}
		if(submodule.equals("EOM - Data Export (SMBC Giro Data)")&&action.equals(BillingSystemConstants.AUDIT_TRAIL_CREATED)){

		
			return result;
		
		}
		if(submodule.equals("EOM - Data Export (Citibank Credit Card Data)")&&action.equals(BillingSystemConstants.AUDIT_TRAIL_CREATED)){

			return result;
		
		}
		if(submodule.equals("EOM - Data Import (SingPost Collection Data)")&&action.equals(BillingSystemConstants.AUDIT_TRAIL_CREATED)){

			return result;
		
		}
		
		auditDetailList = queryDAO.executeForObjectList(SQL_SELECT_AUDIT_DETAIL,param);
		//output.setAuditHeader(auditHeader);
		output.setAuditDetailList(auditDetailList);

		result.setResultObject(output);
		result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS);
		return result;
	}
}