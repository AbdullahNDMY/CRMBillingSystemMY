/**
 * @(#)Util.java
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.m_cst.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.dao.QueryDAO;
import nttdm.bsys.a_usr.bean.UserAccessBean;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.m_cst.bean.CustomerBean;
import nttdm.bsys.m_cst.blogic.M_CSTR01BLogic;

/**
 * Common methods for Util
 * 
 * @author p-minzh
 */
public class Util {
	/**
	 * <div>INSERT_M_CUST sql</div>
	 */
	public static final String INSERT_M_CUST = "SELECT.M_CST.SQL004";

	/**
	 * <div>UPDATE_M_CUST sql</div>
	 */
	public static final String UPDATE_M_CUST = "SELECT.M_CST.SQL005";

	/**
	 * <div>INSERT_UPDATE_M_CUST_ADR_RA sql</div>
	 */
	public static final String INSERT_UPDATE_M_CUST_ADR_RA = "SELECT.M_CST.SQL006";

	/**
	 * <div>INSERT_UPDATE_M_CUST_ADR_BA sql</div>
	 */
	public static final String INSERT_UPDATE_M_CUST_ADR_BA = "SELECT.M_CST.SQL007";

	/**
     * <div>INSERT_UPDATE_M_CUST_ADR_BA2 sql</div>
     */
    public static final String INSERT_UPDATE_M_CUST_ADR_BA2 = "SELECT.M_CST.SQL007_2";
	
    /**
     * <div>INSERT_UPDATE_M_CUST_ADR_BA3 sql</div>
     */
    public static final String INSERT_UPDATE_M_CUST_ADR_BA3 = "SELECT.M_CST.SQL007_3";
    
    /**
     * <div>INSERT_UPDATE_M_CUST_ADR_BA4 sql</div>
     */
    public static final String INSERT_UPDATE_M_CUST_ADR_BA4 = "SELECT.M_CST.SQL007_4";
    
	/**
	 * <div>INSERT_UPDATE_M_CUST_ADR_CA sql</div>
	 */
	public static final String INSERT_UPDATE_M_CUST_ADR_CA = "SELECT.M_CST.SQL008";

	/**
	 * <div>INSERT_UPDATE_M_CUST_ADR_TA sql</div>
	 */
	public static final String INSERT_UPDATE_M_CUST_ADR_TA = "SELECT.M_CST.SQL019";

	/**
	 * <div>INSERT_UPDATE_M_CUST_CTC_PC sql</div>
	 */
	public static final String INSERT_UPDATE_M_CUST_CTC_PC = "SELECT.M_CST.SQL009";

	/**
	 * <div>INSERT_UPDATE_M_CUST_CTC_BC sql</div>
	 */
	public static final String INSERT_UPDATE_M_CUST_CTC_BC = "SELECT.M_CST.SQL010";

	/**
     * <div>INSERT_UPDATE_M_CUST_CTC_BC2 sql</div>
     */
    public static final String INSERT_UPDATE_M_CUST_CTC_BC2 = "SELECT.M_CST.SQL010_2";

    /**
     * <div>INSERT_UPDATE_M_CUST_CTC_BC3 sql</div>
     */
    public static final String INSERT_UPDATE_M_CUST_CTC_BC3 = "SELECT.M_CST.SQL010_3";
    
    /**
     * <div>INSERT_UPDATE_M_CUST_CTC_BC4 sql</div>
     */
    public static final String INSERT_UPDATE_M_CUST_CTC_BC4 = "SELECT.M_CST.SQL010_4";
	
	/**
	 * <div>INSERT_UPDATE_M_CUST_CTC_TC sql</div>
	 */
	public static final String INSERT_UPDATE_M_CUST_CTC_TC = "SELECT.M_CST.SQL011";

	/**
	 * <div>INSERT_UPDATE_M_CUST_CTC_OC sql</div>
	 */
	public static final String INSERT_UPDATE_M_CUST_CTC_OC = "SELECT.M_CST.SQL012";

	/**
	 * <div>GET_SEQ_ID sql</div>
	 */
	public static final String GET_SEQ_ID = "SELECT.M_CST.SQL014";

	/**
	 * <div>ERRORS_ERR2SC004 error message</div>
	 */
	public static final String ERRORS_ERR2SC004 = "info.ERR2SC004";

	/**
	 * <div>ERRORS_ERR2SC003 Error message</div>
	 */
	public static final String ERRORS_ERR2SC003 = "info.ERR2SC003";

	/**
	 * <div>ERRORS_ERR1SC006 Error message</div>
	 */
	public static final String ERRORS_ERR1SC006 = "errors.ERR1SC006";

	/**
	 * <div>ADR_TYPE_RA address type</div>
	 */
	public static final String ADR_TYPE_RA = "RA";

	/**
	 * <div>ADR_TYPE_BA address type</div>
	 */
	public static final String ADR_TYPE_BA = "BA";

	/**
     * <div>ADR_TYPE_BA2 address type</div>
     */
    public static final String ADR_TYPE_BA2 = "BA2";

    /**
     * <div>ADR_TYPE_BA3 address type</div>
     */
    public static final String ADR_TYPE_BA3 = "BA3";

    /**
     * <div>ADR_TYPE_BA4 address type</div>
     */
    public static final String ADR_TYPE_BA4 = "BA4";
    
	/**
	 * <div>ADR_TYPE_CA address type</div>
	 */
	public static final String ADR_TYPE_CA = "CA";

	/**
	 * <div>ADR_TYPE_CA address type</div>
	 */
	public static final String ADR_TYPE_TA = "TA";

	/**
	 * <div>CONTACT_TYPE_PC contact type</div>
	 */
	public static final String CONTACT_TYPE_PC = "PC";

	/**
	 * <div>CONTACT_TYPE_BC contact type</div>
	 */
	public static final String CONTACT_TYPE_BC = "BC";
	
	/**
     * <div>CONTACT_TYPE_BC2 contact type</div>
     */
    public static final String CONTACT_TYPE_BC2 = "BC2";
    
    /**
     * <div>CONTACT_TYPE_BC3 contact type</div>
     */
    public static final String CONTACT_TYPE_BC3 = "BC3";

    /**
     * <div>CONTACT_TYPE_BC4 contact type</div>
     */
    public static final String CONTACT_TYPE_BC4 = "BC4";
    
	/**
	 * <div>CONTACT_TYPE_TC contact type</div>
	 */
	public static final String CONTACT_TYPE_TC = "TC";

	/**
	 * <div>CONTACT_TYPE_OC contact type</div>
	 */
	public static final String CONTACT_TYPE_OC = "OC";

	/**
	 * <div>MODULE</div>
	 */
	public static final String MODULE = "M";

	/**
	 * <div>SUB_MODULE_BI</div>
	 */
	public static String SUB_MODULE_BI = "M-CST-BI";

	/**
	 * <div>SUB_MODULE_ACC</div>
	 */
	public static String SUB_MODULE_ACC = "M-CST-AC";

	/**
	 * <div>SQL_GET_CONSUMER_CUSTOMER_INFO sql</div>
	 */
	public static String SQL_GET_CONSUMER_CUSTOMER_INFO = "SELECT.M_CST.SQL028";

	/**
	 * <div>DEFAULT_OPTION</div>
	 */
	public static final String DEFAULT_OPTION = "I";

	/**
	 * <div>SELECT_COUNT_CUST_ACC_NO sql</div>
	 */
	public static final String SELECT_COUNT_CUST_ACC_NO = "SELECT.M_CST.SQL021";

	/**
	 * <div>CUSTOMER_TYPE_CORP</div>
	 */
	public static final String CUSTOMER_TYPE_CORP = "CORP";

	/**
	 * <div>CUSTOMER_TYPE_CONS</div>
	 */
	public static final String CUSTOMER_TYPE_CONS = "CONS";

	/**
	 * <div>INSERT_M_CUST_BANKINFO sql</div>
	 */
	public static final String INSERT_M_CUST_BANKINFO = "INSERT.M_CST.SQL022";

	/**
	 * <div>GET_SEQ_ID_BANKINFO sql</div>
	 */
	public static final String GET_SEQ_ID_BANKINFO = "SELECT.M_CST.SQL023";

	/**
	 * <div>UPDATE_M_CUST_BANKINFO sql</div>
	 */
	public static final String UPDATE_M_CUST_BANKINFO = "UPDATE.M_CST.SQL024";

	/**
	 * <div>SELECT_ID_CUST_COUNT sql</div>
	 */
	public static final String SELECT_ID_CUST_COUNT = "SELECT.M_CST.SQL025";

	/**
	 * <div>SELECT_BANK_INFO sql</div>
	 */
	public static final String SELECT_BANK_INFO = "SELECT.M_CST.SQL026";

	/**
	 * <div>DELETE_M_CUST sql</div>
	 */
	public static final String DELETE_M_CUST = "UPDATE.M_CST.SQL027";

	/**
	 * <div>ERRORS_ERR2SC006 Error message</div>
	 */
	public static final String ERRORS_ERR2SC006 = "info.ERR2SC006";

	/**
	 * <div>ERRORS_ERR2SC005 Error message</div>
	 */
	public static final String ERRORS_ERR2SC005 = "info.ERR2SC005";

	/**
	 * <div>SELECT_EXPORT sql</div>
	 */
	public static final String SELECT_EXPORT = "SELECT.M_CST.SQL030";

	/**
	 * <div>SELECT_BANK_LIST sql</div>
	 */
	public static final String SELECT_BANK_LIST = "SELECT.M_CST.SQL031";

	/**
	 * <div>check SubModule</div>
	 * 
	 * @param idUser
	 *            String
	 * @param module
	 *            String
	 * @param queryDAO
	 *            QueryDAO
	 * 
	 * @return String
	 * 
	 */
	public static String checkSubModule(String idUser, String module,
			QueryDAO queryDAO) {

		// set param
		UserAccessBean param = new UserAccessBean();
		param.setId_user(idUser);
		param.setId_sub_module(module);

		// select ID_SUB_MODULE
		String subModule = "";
		subModule = queryDAO.executeForObject(M_CSTR01BLogic.SQL_SUB_MODULE,
				param, String.class);

		// if subModule is null then return "0" else "1";
		return (subModule == null ? "0" : "1");
	}

	/**
	 * <div>get AccessType</div>
	 * 
	 * @param idUser
	 *            String
	 * @param queryDAO
	 *            QueryDAO
	 * 
	 * @return String
	 * 
	 */
	public static String getAccessType(String idUser, QueryDAO queryDAO) {

		// return AccessType
		return queryDAO.executeForObject(M_CSTR01BLogic.SQL_ACCESS_TYPE,
				idUser, String.class);
	}

    /**
     * <div>check DuplicatedCustAccNo</div>
     * 
     * @param custAccNo
     *            String
     * @param queryDAO
     *            QueryDAO
     * @param mode
     *            String
     * 
     * @return boolean
     * 
     */
    public static List<CustomerBean> checkDuplicatedCustAccNo(String custAccNo, String idCust, QueryDAO queryDAO) {

        // Initializa
        List<CustomerBean> custList = new ArrayList<CustomerBean>();

        if (custAccNo != null && !custAccNo.equals("")) {

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("custAccNo", custAccNo);
            map.put("idCust", idCust);

            // select all customer with same cust_acc_no
            custList = queryDAO.executeForObjectList(Util.SELECT_COUNT_CUST_ACC_NO, map);
        }

        return custList;
    }

	public static String getIsCheckMulCharFlg(QueryDAO queryDAO) {
        String isCheckMulCharFlg = CommonUtils.toString(queryDAO.executeForObject("SELECT.M_CST.SQL040", null, String.class)).trim();
        
        return isCheckMulCharFlg;
    }
}
