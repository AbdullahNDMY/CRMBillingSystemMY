
/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_CST
 * SERVICE NAME   : M_CST07
 * FUNCTION       : Set default value of new mode
 * FILE NAME      : M_CSTR07BLogic.java
 *
 * Copyright (C) 2011 NTTDATA Corporation
 * 
**********************************************************/
package nttdm.bsys.m_cst.blogic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts.util.LabelValueBean;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.m_cst.bean.M_CSTFormBean;
import nttdm.bsys.m_cst.common.Util;
import nttdm.bsys.m_cst.dto.M_CSTR07BLogicInput;
import nttdm.bsys.m_cst.dto.M_CSTR07BLogicOutput;

/**
 * BusinessLogic class.
 * 
 * @author NTT Data VietNam
 */
public class M_CSTR07BLogic extends AbstractM_CSTR07BLogic {

	/**
	 * 
	 * @param param
	 * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
	 */
	public BLogicResult execute(M_CSTR07BLogicInput param) {
		BLogicResult result = new BLogicResult();
		//delcare output object
		M_CSTR07BLogicOutput outputDto=new M_CSTR07BLogicOutput();
		//
		if(param.getMode()!=null && (String.valueOf(param.getMode()).equals(M_CSTFormBean.READONLY) 
				|| (String.valueOf(param.getMode()).equals(M_CSTFormBean.EDITMODE)))){
			//get detail customer from database
			outputDto = queryDAO.executeForObject(Util.SQL_GET_CONSUMER_CUSTOMER_INFO, param, M_CSTR07BLogicOutput.class);
			// get accessType
			outputDto.setAccessType(Util.getAccessType(param.getUvoObject().getId_user(), queryDAO));
//			outputDto.setAc_sub_module(Util.checkSubModule(param.getUvoObject().getId_user(), Util.SUB_MODULE_ACC, queryDAO));
//			outputDto.setBi_sub_module(Util.checkSubModule(param.getUvoObject().getId_user(), Util.SUB_MODULE_BI, queryDAO));
			outputDto.setAc_sub_module(Util.checkSubModule(param.getUvoObject().getId_user(), Util.SUB_MODULE_ACC, queryDAO));
			outputDto.setBi_sub_module(param.getUvoObject().getUserAccessInfo(Util.MODULE, Util.SUB_MODULE_BI).getAccess_type());
		}else{
			if(param.getMode()!=null && (String.valueOf(param.getMode()).equals(M_CSTFormBean.NEWMODE)||String.valueOf(param.getMode()).equals(""))){
				outputDto.setPrint_statement("on");
			}
			//set default value when mode is new
			outputDto.setBill_type(Util.DEFAULT_OPTION);
		}
		//set return object
		outputDto.setMode(param.getMode());
		
		//add by Jing Start
		// Get CompanyBankInfo from table 'M_CUST_BANKINFO'&'M_BANK'
        List<LabelValueBean> companyBankInfoList = queryDAO.executeForObjectList("SELECT.M_CST.COMPANYBANKINFO", null);
        Map<String,Object> standardinfo=new HashMap<String,Object>();
        standardinfo.put("ST", "Standard");
        String label="Standard";
        String value="ST";
        companyBankInfoList.add(0,new org.apache.struts.util.LabelValueBean(label,value));
        outputDto.setCompanyBankInfoList(companyBankInfoList);
    	
    	Map<String, Object> set_valueMap = queryDAO.executeForMap("SELECT.M_CST.SET_VALUE", null);
    	outputDto.setCategory_enableFlg(set_valueMap.get("C_VALUE").toString());
    	outputDto.setSubCategory_enableFlg(set_valueMap.get("S_VALUE").toString());
    	outputDto.setBankInfo_enableFlg(set_valueMap.get("B_VALUE").toString());
    	outputDto.setCompany_enableFlg(set_valueMap.get("D_VALUE").toString());
		//add by Jing end
		
		result.setResultObject(outputDto);
		//set return string
		
		result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS);
		return result;
	}
}