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
import java.util.Map;

import nttdm.bsys.b_cpm_en.dto.B_CPM_CONSTANT;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.comman_source;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;

/**
 * AbstractB_CPM_S02AddPlanBLogic.class<br>
 * <ul>
 * <li>Add Plan BLogic</li>
 * </ul>
 * <br>
 * @author  NTTData Vietnam
 * @version 1.1
 */
public class M_SVTS02_AddPlanDetailOptionBLogic extends AbstractM_SVT02_AddPlanDetailOptionBLogic{
    
    public BLogicResult execute(HashMap<String, Object> param) {
        BLogicResult result = new BLogicResult();
        //BLogicMessages errors = new BLogicMessages();
        String txtPlan=CommonUtils.toString(param.get("txtPlan"));
        String tbxDescAbbrSI=CommonUtils.toString(param.get("tbxDescAbbrSI"));
        String lblCategory=CommonUtils.toString(param.get("lblCategory"));
        String idSvcLevel=CommonUtils.toString(param.get("svcLevel1Val"));
        
        if(lblCategory.equals("")){
            lblCategory=CommonUtils.toString(queryDAO.executeForObject("M_SVT.GET_SVC_GRP_NAME",idSvcLevel, String.class));
        }
        //set result object
        Map<String, Object> resultObject = new HashMap<String, Object>();
        resultObject.put("txtPlan",txtPlan);
        resultObject.put("tbxDescAbbrSI",tbxDescAbbrSI);
        resultObject.put("lblCategory",lblCategory);
        resultObject.put("svcLevel1Val",idSvcLevel);
        
        result.setResultObject(resultObject);
        result.setResultString("success");
        return result;
    }
    
    
    /**
     * queryDAO
     */
    protected QueryDAO queryDAO;

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

}