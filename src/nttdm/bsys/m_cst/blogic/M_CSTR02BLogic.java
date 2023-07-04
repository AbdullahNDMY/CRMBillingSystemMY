/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_CST
 * SERVICE NAME   : M_CST02
 * FUNCTION       : Set default value of new mode 
 * FILE NAME      : M_CSTR02BLogic.java
 *
 * Copyright (C) 2011 NTTDATA Corporation
 * 
**********************************************************/
package nttdm.bsys.m_cst.blogic;

// import com.sun.org.apache.xml.internal.serializer.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts.util.LabelValueBean;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.a_usr.bean.UserBean;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.m_cst.bean.M_CSTFormBean;
import nttdm.bsys.m_cst.common.Util;
import nttdm.bsys.m_cst.dto.M_CSTR02Input;
import nttdm.bsys.m_cst.dto.M_CSTR02Output;

/**
 * <div>
 * get detail customer blogic
 * </div> 
 * @author NTT Data VietNam
 *
 */
public class M_CSTR02BLogic extends AbstractM_CSTR02BLogic{
	
	/**
	 * <div>query dao class</div>
	 */
	private QueryDAO queryDAO = null;
	
	/**
	 * <div>SQL_GET_CUSTOMER_INFO</div>
	 */
	private static final String SQL_GET_CUSTOMER_INFO = "SELECT.M_CST.SQL003";
	
	/**
	 * <div>DEFAULT_OPTION value</div>
	 */
	private static final String DEFAULT_OPTION = "I";
	

	public BLogicResult execute(M_CSTR02Input param) {
		/* Original code
		 
		//declare blogicresult
		BLogicResult result = new BLogicResult();
		//delcare output object
		M_CSTR02Output outputDto=new M_CSTR02Output();
		//
		if(param.getMode()!=null && (String.valueOf(param.getMode()).equals(M_CSTFormBean.READONLY) 
				|| (String.valueOf(param.getMode()).equals(M_CSTFormBean.EDITMODE)))){
			//get detail customer from database
			outputDto = queryDAO.executeForObject(M_CSTR02BLogic.SQL_GET_CUSTOMER_INFO, param, M_CSTR02Output.class);
			
		}else{
			//set default value when mode is new
			outputDto.setBill_type(M_CSTR02BLogic.DEFAULT_OPTION);
		}
		//set return object
		result.setResultObject(outputDto);
		//set return string
		
		result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SCCESS);
		return result;
	*/
		// Handn For M_CSTR r13
		//declare blogicresult
		BLogicResult result = new BLogicResult();
		//delcare output object
		M_CSTR02Output outputDto=new M_CSTR02Output();
		//
		if(param.getMode()!=null && (String.valueOf(param.getMode()).equals(M_CSTFormBean.READONLY) 
				|| (String.valueOf(param.getMode()).equals(M_CSTFormBean.EDITMODE)))){
			//get detail customer from database
			outputDto = queryDAO.executeForObject(M_CSTR02BLogic.SQL_GET_CUSTOMER_INFO, param, M_CSTR02Output.class);
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
			outputDto.setBill_type(M_CSTR02BLogic.DEFAULT_OPTION);
		}
		
		List <UserBean> listUser = new ArrayList<UserBean>();
		listUser = queryDAO.executeForObjectList("SELECT.M_CST.USERLIST",null);
		String lst_user="";
		for (UserBean user : listUser) {
			lst_user = lst_user.concat(user.getUser_name()).concat(";");
		}
		outputDto.setListUser(lst_user);
		
		//set previous
		String previous = param.getPrevious();
		outputDto.setPrevious(previous);
		outputDto.setFromPopup(param.getFromPopup());
		outputDto.setMode(param.getMode());
		// Get CompanyBankInfo from table 'M_CUST_BANKINFO'&'M_BANK'
        List<LabelValueBean> companyBankInfoList = queryDAO.executeForObjectList("SELECT.M_CST.COMPANYBANKINFO", null);
        Map<String,Object> standardinfo=new HashMap<String,Object>();
        standardinfo.put("ST", "Standard");
        String label="Standard";
        String value="ST";
        companyBankInfoList.add(0,new org.apache.struts.util.LabelValueBean(label,value));
        outputDto.setCompanyBankInfoList(companyBankInfoList);
        
        //Add by Jing Start
    	Map<String, Object> set_valueMap = queryDAO.executeForMap("SELECT.M_CST.SET_VALUE", null);
    	outputDto.setCategory_enableFlg(set_valueMap.get("C_VALUE").toString());
    	outputDto.setSubCategory_enableFlg(set_valueMap.get("S_VALUE").toString());
    	outputDto.setBankInfo_enableFlg(set_valueMap.get("B_VALUE").toString());
        //Add by Jing End
    	//Add by MiffyAn Start
    	outputDto.setCompany_enableFlg(set_valueMap.get("D_VALUE").toString());
    	//Add by MiffyAn End
        
		//set return object
		result.setResultObject(outputDto);
		//set return string
		
		result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS);
		return result;
		// end Handn For M_CSTR r13		
		}
	
	public QueryDAO getQueryDAO() {
		return queryDAO;
	}

	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}

}
