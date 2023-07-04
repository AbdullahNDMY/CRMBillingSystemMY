/**
 * @(#)G_CPM_P01.java
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.util;

import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import nttdm.bsys.common.bean.RAD_USERS_T;
import nttdm.bsys.common.util.dto.G_CPM_P01Input;
import nttdm.bsys.common.util.dto.T_CUST_PLAN_H;

/**
 * Process to Approve Customer Plan
 * 
 * @author lixinj
 */
public class G_CPM_P01 {

    /**
     * B_BIF_S02s
     */
    public static final String B_BIF_S02s = "B_BIF_S02s";

    /**
     * B_CPM_S02v
     */
    public static final String B_CPM_S02v = "B_CPM_S02v";

    /**
     * <div>queryDAO</div>
     */
    private QueryDAO queryDAO;

    /**
     * <div>updateDAO</div>
     */
    private UpdateDAO updateDAO;
    
    /**
     * <div>queryDAO</div>
     */
    private QueryDAO radiusQueryDAO;

    /**
     * <div>updateDAO</div>
     */
    private UpdateDAO radiusUpdateDAO;

    /**
     * <div>id_user</div>
     */
    private String id_user;

    /**
     * <div>SELECT_SQL_1_0</div>
     */
    private static final String SELECT_SQL_1_0 = "SELECT.BSYS.G_CMP01.SQL001";

    /**
     * <div>INSERT_SQL_2_0</div>
     */
    private static final String INSERT_SQL_2_0 = "INSERT.BSYS.G_CMP01.SQL002";

    /**
     * <div>INSERT_SQL_3_0</div>
     */
    private static final String INSERT_SQL_3_0 = "INSERT.BSYS.G_CMP01.SQL003";

    /**
     * <div>UPDATE_SQL_4_0</div>
     */
    private static final String UPDATE_SQL_4_0 = "UPDATE.BSYS.G_CMP01.SQL004";

    /**
     * <div>SELECT_SQL_5_0</div>
     */
    private static final String SELECT_SQL_5_0 = "SELECT.BSYS.G_CMP01.SQL005";

    /**
     * <div>UPDATE_SQL_6_0</div>
     */
    private static final String UPDATE_SQL_6_0 = "UPDATE.BSYS.G_CMP01.SQL006";

    /**
     * <div>SELECT_SQL_7_0</div>
     */
    private static final String SELECT_SQL_7_0 = "SELECT.BSYS.G_CMP01.SQL007";

    /**
     * <div>get QueryDAO</div>
     * 
     * @return queryDAO QueryDAO
     */
    public QueryDAO getQueryDAO() {

        return queryDAO;
    }

    /**
     * <div>get Id_user</div>
     * 
     * @return id_user
     * String
     */
    public String getId_user() {

        return id_user;
    }

    /**
     * <div>set Id_user</div>
     * 
     * @param id_user
     * String
     */
    public void setId_user(String id_user) {

        this.id_user = id_user;
    }

    /**
     * <div>set QueryDAO</div>
     * 
     * @param queryDAO
     * QueryDAO
     */
    public void setQueryDAO(QueryDAO queryDAO) {

        this.queryDAO = queryDAO;
    }

    /**
     * <div>get UpdateDAO</div>
     * 
     * @return updateDAO
     * UpdateDAO
     */
    public UpdateDAO getUpdateDAO() {

        return updateDAO;
    }

    /**
     * <div>set UpdateDAO</div>
     * 
     * @param updateDAO
     * UpdateDAO
     */
    public void setUpdateDAO(UpdateDAO updateDAO) {

        this.updateDAO = updateDAO;
    }

    /**
     * <div>G_CPM_P01 constructor</div>
     * 
     * @param queryDAO
     * QueryDAO
     * @param updateDAO
     * updateDAO
     * @param id_user
     * String
     */
    public G_CPM_P01(QueryDAO queryDAO, UpdateDAO updateDAO, String id_user) {

        // pass required parameters
        this.queryDAO = queryDAO;
        this.updateDAO = updateDAO;
        this.id_user = id_user;
    }
    
    /**
     * <div>G_CPM_P01 constructor</div>
     * 
     * @param queryDAO
     * QueryDAO
     * @param updateDAO
     * updateDAO
     * @param id_user
     * String
     */
    public G_CPM_P01(QueryDAO queryDAO, UpdateDAO updateDAO, 
            QueryDAO radiusQueryDAO, UpdateDAO radiusUpdateDAO, String id_user) {

        // pass required parameters
        this.queryDAO = queryDAO;
        this.updateDAO = updateDAO;
        this.radiusQueryDAO = radiusQueryDAO;
        this.radiusUpdateDAO = radiusUpdateDAO;
        this.id_user = id_user;
    }

    /** newBAC */
    private String newBAC = "";

    /**
     * <div>Process to Approve Customer Plan</div>
     * 
     * @param input
     * G_CPM_P01Input
     * @param screenName
     * String
     * @return isSuccess
     * Boolean
     */
    public Boolean execute(G_CPM_P01Input input, String screenName) {

        Boolean isSuccess = true;
        // from B_BIF_S02s
        if (B_BIF_S02s.equals(screenName)) {
            // list of plans
            Map<String, Object> bifCust =
                this.queryDAO.executeForMap(SELECT_SQL_7_0, input);
            // bif
            if (bifCust != null) {
                if (bifCust.size() != 0) {
                    input.setApprove_type("B");
                    input.setId_cust(bifCust.get("ID_CUST").toString());
                    if (bifCust.get("BILL_CUR") != null) {
                        input.setBill_currency(bifCust.get("BILL_CUR").toString());
                    }
                    if (bifCust.get("ID_QCS") != null) {
                        input.setId_qcs(bifCust.get("ID_QCS").toString());
                    }
                    if (bifCust.get("ID_QUO") != null) {
                        input.setId_quo(bifCust.get("ID_QUO").toString());
                    }
                    if (bifCust.get("CUST_PO") != null) {
                        input.setCust_po(bifCust.get("CUST_PO").toString());
                    }
                    input.setAc_manager(CommonUtils.toString(bifCust.get("ID_CONSLT")));
                    input.setJob_no(CommonUtils.toString(bifCust.get("JOB_NO")));
                    if (bifCust.get("CTTERM3_DAY") != null) {
                        input.setTerm(bifCust.get("CTTERM3_DAY").toString());
                    }
                    if (bifCust.get("TERM_DAY") != null) {
                        input.setTermDay(bifCust.get("TERM_DAY").toString());
                    }
                    input.setId_cust_plan(bifCust.get("ID_CUST_PLAN").toString());
                    // customer
                    if (bifCust.get("PAYMENT_METHOD") != null) {
                        input.setPayment_method(bifCust.get("PAYMENT_METHOD").toString());
                    }
                    if (bifCust.get("EXPORT_CURRENCY") != null) {
                        input.setExport_currency(bifCust.get("EXPORT_CURRENCY").toString());
                    }
                    if (bifCust.get("FIXED_FOREX") != null) {
                        input.setFixed_forex(bifCust.get("FIXED_FOREX").toString());
                    }
                    if (bifCust.get("CONTACT_TYPE") != null) {
                        input.setContact_type(bifCust.get("CONTACT_TYPE").toString());
                    }
                    if (bifCust.get("ADR_TYPE") != null) {
                        input.setCust_adr_type(bifCust.get("ADR_TYPE").toString());
                    }
                    if (bifCust.get("IS_DISPLAY_EXP_AMT") != null) {
                        input.setIs_display_exp_amt(bifCust.get("IS_DISPLAY_EXP_AMT").toString());
                    }
                    if (bifCust.get("MULTI_BILL_PERIOD") != null) {
                        input.setMulti_bill_period(bifCust.get("MULTI_BILL_PERIOD").toString());
                    }
                    if (bifCust.get("DELIVERY") != null){
                    	input.setDelicery(bifCust.get("DELIVERY").toString());
                    }
                    if (bifCust.get("DELIVERY_EMAIL") != null){
                    	input.setDeliceryEmail(bifCust.get("DELIVERY_EMAIL").toString());
                    }
                    input.setUpadteT_BAC_H(true);
                    isSuccess &= approveSub1(input);
                } else {
                    isSuccess = false;
                }

            } else {
                isSuccess = false;
            }
        }
        // from B_CPM_S02v
        if (B_CPM_S02v.equals(screenName)) {
            input.setApprove_type("S");

            isSuccess &= approveSub1(input);
        }
        return isSuccess;
    }

    /**
     * <div>insert BILL data t_bac_h and t_bac_d</div>
     * 
     * @param input
     * G_CPM_P01Input
     * @return isSuccess
     * boolean
     */

    private boolean approveSub1(G_CPM_P01Input input) {

        Boolean isSuccess = false;
        int count = 0;
        
        List<T_CUST_PLAN_H> result =
            this.queryDAO.executeForObjectList(SELECT_SQL_1_0, input);
        if (result == null || result.size() <= 0
            || result.get(0).getIdBillAccount() == null) {
            return false;
        }
        String idBillAccount = result.get(0).getIdBillAccount();
		input.setId_bill_account(idBillAccount);
		/*add #152 Start*/
		String termDay;
        String term;
		termDay= this.queryDAO.executeForObject("GET_TERAMDAY", null, String.class);
		term = termDay + " Days";
		Map<String, Object> termMap = this.queryDAO.executeForMap(
				"SELECT.BSYS.G_CMP01.GET_TERM", input.getId_bill_account());
		if (termMap != null && termMap.get("TERM") != null) {
			term = termMap.get("TERM").toString();
			//input.setTerm(termMap.get("TERM").toString());
		}
		if (termMap != null && termMap.get("TERM_DAY") != null) {
			termDay = termMap.get("TERM_DAY").toString();
			//input.setTerm(termMap.get("TERM").toString());
		}
		input.setTerm(term);
		input.setTermDay(termDay);
		/*add #152 End*/
		
		// Insert to T_BAC_D
		count = this.updateDAO.execute(INSERT_SQL_3_0, input);
        if (0 < count) {
            // Update to T_CUST_PLAN_H
            count = this.updateDAO.execute(UPDATE_SQL_6_0, input);
        }
        if (0 < count) {
            if (input.isUpadteT_BAC_H()) {
                count = this.updateDAO.execute("UPDATE.BSYS.G_CMP01.SQL010", input);
            }
            // Update to T_CUST_PLAN_D
            this.updateDAO.execute(UPDATE_SQL_4_0, input);
            if(input.isRadius()){
                List<Map<String, Object>> servicePlanBatchPapping = queryDAO.executeForMapList("SELECT.BSYS.G_CMP01.SQL003", input);
                if(servicePlanBatchPapping!=null && 0<servicePlanBatchPapping.size()) {
                    List<Map<String, Object>> listCustPlanD = queryDAO.executeForMapList("SELECT.BSYS.G_CMP01.SQL002", input);
                    if(listCustPlanD==null || listCustPlanD.size()<=0) {
                        RadUserTUtil radUserTUtil = new RadUserTUtil(radiusQueryDAO, radiusUpdateDAO);
                RAD_USERS_T radUserT = new RAD_USERS_T();
                //USERNAME
                        radUserT.setUserName(input.getAccessAccount());
                //PASSWORD
                        radUserT.setPassword(input.getAccessPw());
                //EXPIRE_DATE
                radUserT.setExpireDate("1970-01-01 09:00:00");
                //CREDIT_TIME
                radUserT.setCreditTime("-1");
                //STATUS
                radUserT.setStatus("0");
                //PLAN_ID
                radUserT.setPlanId("0");
                //AUTH_METHOD
                radUserT.setAuthMethod("0");
                //ATTRBUTE_RULE
                radUserT.setAttrbuteRule("0");
                //MODIFY_COUNTER
                radUserT.setModifyCounter("0");
                //insert RAD_USERS_T
                radUserTUtil.insert(radUserT);
            }
        }
            }
        }
        if (count == 0) {
            isSuccess = false;
        } else {
            isSuccess = true;
        }

        return isSuccess;
    }

    /**
     * <div>Get Billing Account ID</div>
     * 
     * @return Billing Account ID
     */
    public String getNewBAC() {

        return newBAC;
    }

    /**
     * <div>Set Billing Account ID</div>
     * 
     * @param newBAC
     * Billing Account ID
     */
    public void setNewBAC(String newBAC) {

        this.newBAC = newBAC;
    }
}
