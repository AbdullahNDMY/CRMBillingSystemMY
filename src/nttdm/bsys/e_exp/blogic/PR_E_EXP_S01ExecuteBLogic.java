/**
 * @(#)PR_E_EXP_S01ExecuteBLogic.java
 * 
 * HMSB Online Service Booking System
 * 
 * Version 1.00
 * 
 * Created 2013/08/04
 * 
 * Copyright (c) 2013 Honda Malaysia. All rights reserved.
 */
package nttdm.bsys.e_exp.blogic;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.common.util.E_EXP_F01;
import nttdm.bsys.common.util.E_EXP_F01_NTTS_CONS;
import nttdm.bsys.common.util.E_EXP_F01_NTTS_CORP;
import nttdm.bsys.e_exp.dto.PR_E_EXP_S01ExecuteInput;
import nttdm.bsys.e_exp.dto.PR_E_EXP_S01ExecuteOutput;
import nttdm.bsys.e_set.bean.E_SET_S03Bean;
import nttdm.bsys.e_set.blogic.E_SET_S03Common;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionErrors;

/**
 * @author gplai
 *
 */
public class PR_E_EXP_S01ExecuteBLogic implements BLogic<PR_E_EXP_S01ExecuteInput> {

    private static final String AR = "AR";
    
    /**
     * queryDAO
     */
    private QueryDAO queryDAO;

    /**
     * updateDAO
     */
    private UpdateDAO updateDAO;
    
    private BLogicMessages errors = new BLogicMessages();
    private BLogicMessages messages = new BLogicMessages();
    
    public BLogicResult execute(PR_E_EXP_S01ExecuteInput input) {
        BLogicResult result = new BLogicResult();
        errors = new BLogicMessages();
        messages = new BLogicMessages();
        
        PR_E_EXP_S01ExecuteOutput output = new PR_E_EXP_S01ExecuteOutput();
        
        try {
            BeanUtils.copyProperties(output, input);
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        } catch (InvocationTargetException e1) {
            e1.printStackTrace();
        }
        
        // AR. PeopleSoft AR Export
        E_SET_S03Common e_set_s03Common = new E_SET_S03Common(queryDAO);
        E_SET_S03Bean e_set_s03Bean = e_set_s03Common.checkInProcessStatus(AR);
        String retStatus = e_set_s03Bean.getRetStatus();
        if (!E_SET_S03Common.RET_STATUS_0.equals(retStatus)) {
            String retMsg = e_set_s03Bean.getRetMsg();
            String batchId = e_set_s03Bean.getBatchId();
            if (E_SET_S03Common.ERRORS_ERR1BT022.equals(retMsg)) {
                errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage(retMsg, new Object[] { batchId }));
            } else if(E_SET_S03Common.ERRORS_ERR1BT020.equals(retMsg)){
                BillingSystemSettings setting = new BillingSystemSettings(queryDAO);
                errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage(retMsg,setting.getBatchTimeInterval() / 60));
            }
            result.setResultObject(output);
            result.setErrors(errors);
            result.setResultString("error");
            return result;
        }
        
        String closingMonth = input.getClosingMonth();
        String closingYear = input.getClosingYear();
        BillingSystemUserValueObject uvo = input.getUvo();
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date runDate = null;
        try {
            runDate = sdf.parse("01/" + closingMonth + "/" + closingYear);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // Add #146 Start
        String customerTypeDispFlg = input.getCustomerTypeDispFlg();
        String customerType = input.getCustomerType();
        // Add #146 End
        // Delete #146 Start
        // E_MEX_S01Thread t = new E_MEX_S01Thread(queryDAO, updateDAO, uvo, runDate);
        // t.start();
        // Delete #146 End
        // Add #146 Start
        if ("MY01".equals(customerTypeDispFlg)) {
            // Call E-EXP-F01-NTTMSC
            E_MEX_S01Thread t = new E_MEX_S01Thread(queryDAO, updateDAO, uvo, runDate);
            t.start();
        } else {
            if ("CORP".equals(customerType)) {
                // Call E-EXP-F01-NTTS_CORP
                E_MEX_S02Thread t = new E_MEX_S02Thread(queryDAO, updateDAO, uvo, runDate);
                t.start();
            } else {
                // Call E-EXP-F01-NTTS_CONS
                E_MEX_S03Thread t = new E_MEX_S03Thread(queryDAO, updateDAO, uvo, runDate);
                t.start();
            }
        }
        // Add #146 End
        messages.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("info.ERR2SC041"));
        
        output.setTypeFlg("execute");
        
        result.setMessages(messages);
        result.setResultObject(output);
        result.setResultString("success");
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
class E_MEX_S01Thread extends Thread {
    private BillingSystemUserValueObject uvo = null;
    private Date runDate = null;
    private QueryDAO queryDAO;
    private UpdateDAO updateDAO;
    
    public E_MEX_S01Thread(QueryDAO queryDAO, UpdateDAO updateDAO, BillingSystemUserValueObject uvo, Date runDate) {
        this.queryDAO = queryDAO;
        this.updateDAO = updateDAO;
        this.uvo = uvo;
        this.runDate = runDate;
    }

    public void run() {
        E_EXP_F01 eExpF01 = new E_EXP_F01();
        eExpF01.setQueryDAO(queryDAO);
        eExpF01.setUpdateDAO(updateDAO);
        eExpF01.setUvo(uvo);
        eExpF01.setEsetRundate(runDate);
        eExpF01.e_exp_execute();
    }
}
// Add #146 Start
class E_MEX_S02Thread extends Thread {
    private BillingSystemUserValueObject uvo = null;
    private Date runDate = null;
    private QueryDAO queryDAO;
    private UpdateDAO updateDAO;
    
    public E_MEX_S02Thread(QueryDAO queryDAO, UpdateDAO updateDAO, BillingSystemUserValueObject uvo, Date runDate) {
        this.queryDAO = queryDAO;
        this.updateDAO = updateDAO;
        this.uvo = uvo;
        this.runDate = runDate;
    }

    public void run() {
        E_EXP_F01_NTTS_CORP eExpF01NttsCorp = new E_EXP_F01_NTTS_CORP();
        eExpF01NttsCorp.setQueryDAO(queryDAO);
        eExpF01NttsCorp.setUpdateDAO(updateDAO);
        eExpF01NttsCorp.setUvo(uvo);
        eExpF01NttsCorp.setEsetRundate(runDate);
        eExpF01NttsCorp.e_exp_execute();
    }
}

class E_MEX_S03Thread extends Thread {
    private BillingSystemUserValueObject uvo = null;
    private Date runDate = null;
    private QueryDAO queryDAO;
    private UpdateDAO updateDAO;
    
    public E_MEX_S03Thread(QueryDAO queryDAO, UpdateDAO updateDAO, BillingSystemUserValueObject uvo, Date runDate) {
        this.queryDAO = queryDAO;
        this.updateDAO = updateDAO;
        this.uvo = uvo;
        this.runDate = runDate;
    }

    public void run() {
        E_EXP_F01_NTTS_CONS eExpF01NttsCons = new E_EXP_F01_NTTS_CONS();
        eExpF01NttsCons.setQueryDAO(queryDAO);
        eExpF01NttsCons.setUpdateDAO(updateDAO);
        eExpF01NttsCons.setUvo(uvo);
        eExpF01NttsCons.setEsetRundate(runDate);
        eExpF01NttsCons.e_exp_execute();
    }
}
// Add #146 End