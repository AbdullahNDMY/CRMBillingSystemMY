/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : Customer Plan Management
 * SERVICE NAME   : B_CPM_S02
 * FUNCTION       : Abstract class blogic
 * FILE NAME      : AbstractB_CPM_S02AddPlanBLogic.java
 * 
 * Copyright c 2011 NTTDATA Corporation
 *
 * History
 * 2011/08/05 [Duong Nguyen] Created
 * 
**********************************************************/
package nttdm.bsys.m_svt.blogic;

import java.util.HashMap;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.dao.UpdateDAOiBatisNuked;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.CommonUtils;

import org.apache.struts.Globals;

/**
 * AbstractB_CPM_S02AddPlanBLogic.class<br>
 * <ul>
 * <li>Add Plan BLogic</li>
 * </ul>
 * <br>
 * @author  NTTData Vietnam
 * @version 1.1
 */
public class M_SVTS02_SavePlanDetailOptionBLogic extends AbstractM_SVT02_SavePlanDetailOptionBLogic{
    
    public BLogicResult execute(HashMap<String, Object> param) {
        BLogicResult result = new BLogicResult();
        BLogicMessages errors = new BLogicMessages();
        BLogicMessages messages = new BLogicMessages();
        String resultString = "success";
        String txtPlan=CommonUtils.toString(param.get("txtPlan"));
        String tbxDescAbbrSI=CommonUtils.toString(param.get("tbxDescAbbrSI"));
        String lblCategory=CommonUtils.toString(param.get("lblCategory"));
        String svcLevel1Val=CommonUtils.toString(param.get("svcLevel1Val"));
        BillingSystemUserValueObject uvo =  (BillingSystemUserValueObject)param.get("uvo");
        HashMap<String, Object> outputData = new HashMap<String, Object>();
        HashMap<String, Object> inputData = new HashMap<String, Object>();
        inputData.put("txtPlan", txtPlan);
        inputData.put("tbxDescAbbrSI", tbxDescAbbrSI);
        inputData.put("lblCategory", lblCategory);
        inputData.put("svcGrp", svcLevel1Val);
        inputData.put("idLogin", uvo.getId_user());
        inputData.put("idService",null);
        Integer idService=0;
        // txtPlan maxlength=300 
        if (txtPlan.trim().length() > 300) {
            errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.maxlength", new Object[] { "Plan", 300 }));
            resultString="failure";
        }
        // tbxDescAbbrSI maxlength=10 
        if (tbxDescAbbrSI.trim().length() > 10) {
            errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.maxlength", new Object[] { "Description Abbreviation", 10 }));
            resultString="failure";
        }
        //If X<>0 (duplicate data exist), prompt ERR1SC006 
        Integer isExistDuplData=queryDAO.executeForObject("M_SVT.GET_SVC_ISEXIST",inputData, Integer.class);
        if(isExistDuplData.intValue()>0){
            errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC006", new Object[] { "Plan"}));
            resultString="failure";
        }
        if (errors.isEmpty()) {
            // insert
            idService= this.updateDAONuked.insert("M_SVT.M_SVTS02_INSERT_M_SVC", inputData);
            messages.add(Globals.MESSAGE_KEY, new BLogicMessage("info.ERR2SC003"));
            outputData.put("isSaveFlg", "1");
        }
        //Set Current  ID_SERVICE
        outputData.put("idService",idService.intValue()+"");
        outputData.put("txtPlan", txtPlan);
        outputData.put("tbxDescAbbrSI", tbxDescAbbrSI);
        outputData.put("lblCategory", lblCategory);
        outputData.put("svcLevel1Val", svcLevel1Val);
        //result.setMessages(messages);
        result.setResultObject(outputData);
        result.setErrors(errors);
        result.setResultString(resultString);
        return result;
    }
    
    
    /**
     * queryDAO
     */
    protected QueryDAO queryDAO;

    /**
     * updateDAO
     */
    private UpdateDAO updateDAO;
    
    /**
     * updateDAONuked
     */
    private UpdateDAOiBatisNuked updateDAONuked;
    
    /**
     * queryDAO を取得する
     * 
     * @return queryDAO
     */
    public QueryDAO getQueryDAO() {
        return queryDAO;
    }

    /**
     * queryDAO を設定する
     * 
     * @param queryDAO
     *            queryDAO
     */
    public void setQueryDAO(QueryDAO queryDAO) {
        this.queryDAO = queryDAO;
    }

    /**
     * updateDAO を取得すめE  * 
     * @return updateDAO
     */
    public UpdateDAO getUpdateDAO() {
        return updateDAO;
    }

    /**
     * updateDAO を設定すめE  * 
     * @param updateDAO
     *            updateDAO
     */
    public void setUpdateDAO(UpdateDAO updateDAO) {
        this.updateDAO = updateDAO;
    }
    
    /**
     * updateDAONuked を取得する
     * 
     * @return updateDAONuked
     */
    public UpdateDAOiBatisNuked getUpdateDAONuked() {
        return updateDAONuked;
    }

    /**
     * updateDAONuked を設定する
     * 
     * @param updateDAONuked
     *            updateDAONuked
     */
    public void setUpdateDAONuked(UpdateDAOiBatisNuked updateDAONuked) {
        this.updateDAONuked = updateDAONuked;
    }
}