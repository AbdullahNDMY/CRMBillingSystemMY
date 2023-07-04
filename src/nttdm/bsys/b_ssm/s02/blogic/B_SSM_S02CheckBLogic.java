/**
 * @(#)B_SSM_S02CheckBLogic.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2013/06/04
 * 
 * Copyright (c) 2013 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.b_ssm.s02.blogic;

import java.util.HashMap;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.b_ssm.s02.data.B_SSM_S02_FieldSet;
import nttdm.bsys.b_ssm.utility.BLogicUtils;
import nttdm.bsys.common.bean.RAD_USERS_T;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.RadUserTUtil;

import org.apache.struts.action.ActionErrors;

/**
 * @author gplai
 *
 */
public class B_SSM_S02CheckBLogic implements BLogic<HashMap<String, Object>> {

    private QueryDAO queryDAO;
    private QueryDAO radiusQueryDAO;
    private UpdateDAO radiusUpdateDAO;
    private UpdateDAO updateDAO;    
    
    private BLogicMessages errors;
    
    /* (non-Javadoc)
     * @see jp.terasoluna.fw.service.thin.BLogic#execute(java.lang.Object)
     */
    public BLogicResult execute(HashMap<String, Object> input) {
        BLogicResult result = new BLogicResult();
        errors = new BLogicMessages();
        HashMap<String, Object> output = new HashMap<String, Object>();
        BLogicUtils.copyProperties(output, input);
        
        String resultStr = "";
        
        String processMode = CommonUtils.toString(input.get(B_SSM_S02_FieldSet.FIELD_PROCESSMODE));
        
        // Mode Test Start         
        if (processMode != null && !"".equals(processMode) 
                && String.valueOf(B_SSM_S02_FieldSet.PROCESSMODE_TEST_START).equals(CommonUtils.toString(processMode).trim())) {
            String accessAccount = CommonUtils.toString(input.get(B_SSM_S02_FieldSet.FIELD_ACCESS_ACCOUNT)).trim();
            String accessPw = CommonUtils.toString(input.get(B_SSM_S02_FieldSet.FIELD_ACCESS_PASSWORD)).trim();
            
            if(CommonUtils.isEmpty(accessAccount)) {
                errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC069", new Object[] {}));
            } else if(CommonUtils.isEmpty(accessPw)) {
                errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC069", new Object[] {}));
            } else {
                RadUserTUtil radUserTUtil = new RadUserTUtil(radiusQueryDAO, radiusUpdateDAO);
                RAD_USERS_T radUserST = radUserTUtil.selectByPK(accessAccount);
                if (radUserST!=null) {
                    errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC067", new Object[] {}));
                }
            }
        }
        // Mode Test Complete         
        if (processMode != null && !"".equals(processMode)
                && String.valueOf(B_SSM_S02_FieldSet.PROCESSMODE_TEST_COMPLETE).equals(CommonUtils.toString(processMode).trim())) {
            String accessAccount = CommonUtils.toString(input.get(B_SSM_S02_FieldSet.FIELD_ACCESS_ACCOUNT)).trim();
            if (!CommonUtils.isEmpty(accessAccount)) {
                RadUserTUtil radUserTUtil = new RadUserTUtil(radiusQueryDAO, radiusUpdateDAO);
                RAD_USERS_T radUserST = radUserTUtil.selectByPK(accessAccount);
                if (radUserST==null) {
                    errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC106", new Object[] {}));
                }
            }
        }
        if (errors.isEmpty()) {
            resultStr = "success";
            output.put(B_SSM_S02_FieldSet.FIELD_CHECK_ERROR_FLAG, B_SSM_S02_FieldSet.FIELD_CHECK_ERROR_FLAG_NO);
        } else {
            resultStr = "fail";
            output.put(B_SSM_S02_FieldSet.FIELD_CHECK_ERROR_FLAG, B_SSM_S02_FieldSet.FIELD_CHECK_ERROR_FLAG_YES);
        }
        
        result.setResultObject(output);
        result.setErrors(errors);
        result.setResultString(resultStr);
        return result;
    }

    /**
     * @return the queryDAO
     */
    public QueryDAO getQueryDAO() {
        return queryDAO;
    }

    /**
     * @param queryDAO the queryDAO to set
     */
    public void setQueryDAO(QueryDAO queryDAO) {
        this.queryDAO = queryDAO;
    }

    /**
     * @return the radiusQueryDAO
     */
    public QueryDAO getRadiusQueryDAO() {
        return radiusQueryDAO;
    }

    /**
     * @param radiusQueryDAO the radiusQueryDAO to set
     */
    public void setRadiusQueryDAO(QueryDAO radiusQueryDAO) {
        this.radiusQueryDAO = radiusQueryDAO;
    }

    /**
     * @return the radiusUpdateDAO
     */
    public UpdateDAO getRadiusUpdateDAO() {
        return radiusUpdateDAO;
    }

    /**
     * @param radiusUpdateDAO the radiusUpdateDAO to set
     */
    public void setRadiusUpdateDAO(UpdateDAO radiusUpdateDAO) {
        this.radiusUpdateDAO = radiusUpdateDAO;
    }

    /**
     * @return the updateDAO
     */
    public UpdateDAO getUpdateDAO() {
        return updateDAO;
    }

    /**
     * @param updateDAO the updateDAO to set
     */
    public void setUpdateDAO(UpdateDAO updateDAO) {
        this.updateDAO = updateDAO;
    }
}
