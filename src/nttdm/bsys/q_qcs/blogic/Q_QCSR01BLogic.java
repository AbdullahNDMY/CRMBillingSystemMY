/*
 * @(#)Q_QCSR01BLogic.java
 *
 *
 */
package nttdm.bsys.q_qcs.blogic;

import java.util.List;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.q_qcs.dto.Q_QCSR01Input;
import nttdm.bsys.q_qcs.dto.Q_QCSR01Output;

import nttdm.bsys.q_qcs.bean.QCSHeader;
import nttdm.bsys.q_qcs.blogic.AbstractQ_QCSR01BLogic;

/**
 * BusinessLogic class.
 * 
 * @author ss051
 */
public class Q_QCSR01BLogic extends AbstractQ_QCSR01BLogic {

	private static final String SQL_SEARCH_QCS = "SELECT.Q_QCS.SQL001";
	private static final String SQL_COUNT_QCS = "SELECT.Q_QCS.SQL002";
	private static final String PERCENT_SIGN = "%";
	public BLogicResult execute(Q_QCSR01Input param) {
		BLogicResult result = new BLogicResult();
		
		String strIndex = param.getStartIndex();
		int startIndex = 0;
        int row = 10;
        if (strIndex != null ) {
            startIndex = Integer.parseInt(strIndex);          
        }
        BillingSystemSettings systemSetting = new BillingSystemSettings(queryDAO);
        row = systemSetting.getResultRow();
        
        Q_QCSR01Output output = new Q_QCSR01Output();
        output.setPermission("1");//1: Edit, 0: View
        
        output.setRow(String.valueOf(row));
        
        //Add percentage to input parameters in case not null
        if(param.getCust_name()!= null && !param.getCust_name().equals("")){
        	param.setCust_name(PERCENT_SIGN.concat(param.getCust_name()).concat(PERCENT_SIGN));
        }
        if(param.getUser_name()!= null && !param.getUser_name().equals("")){
        	param.setUser_name(PERCENT_SIGN.concat(param.getUser_name()).concat(PERCENT_SIGN));
        }
        if(param.getId_ref()!= null && !param.getId_ref().equals("")){
        	param.setId_ref(PERCENT_SIGN.concat(param.getId_ref()).concat(PERCENT_SIGN));
        }
        if(param.getId_quo()!= null && !param.getId_quo().equals("")){
        	param.setId_quo(PERCENT_SIGN.concat(param.getId_quo()).concat(PERCENT_SIGN));
        }
        //Get total 
        String totalCount = queryDAO.executeForObject(SQL_COUNT_QCS, param, String.class);
        //Get list of qcs
        List<QCSHeader> qcsInfos = queryDAO.executeForObjectList(SQL_SEARCH_QCS, param,startIndex,row);
        output.setQcsInfos(qcsInfos);
        output.setTotalCount(totalCount);
        
        result.setResultObject(output);
		result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS);
		return result;
	}
}