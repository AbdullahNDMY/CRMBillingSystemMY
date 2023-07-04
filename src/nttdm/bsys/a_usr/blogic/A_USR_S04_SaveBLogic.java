/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : A_USR
 * SERVICE NAME   : A_USR_S04
 * FUNCTION       : Save BLogic
 * FILE NAME      : A_USR_S04_SaveBLogic.java
 * 
 * Copyright © 2014 NTTDATA Corporation
 *
 * History
 * 
 * 
 **********************************************************/
package nttdm.bsys.a_usr.blogic;

import java.util.List;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.a_usr.dto.A_USR_S04IO;
import nttdm.bsys.a_usr.dto.RoleAccess;
import nttdm.bsys.common.util.dto.MessageString;

import org.apache.struts.Globals;

/**
 * A_USR_S04_SaveBLogic<br>
 * <br>
 * 
 * @author i-yang
 * @version 1.0
 */
public class A_USR_S04_SaveBLogic implements BLogic<A_USR_S04IO> {

    private QueryDAO queryDAO;

    private UpdateDAO updateDAO;

    public BLogicResult execute(A_USR_S04IO input) {
        BLogicResult result = new BLogicResult();
        BLogicMessages message = new BLogicMessages();
        BLogicMessages error = new BLogicMessages();
        String idLogin = input.getIdLogin();
        String choosed = input.getChoosed();
        List<RoleAccess> listRoleAccess = input.getListRoleAccess();
        String messageString = MessageString.SUCCESS;
        try {
            if (!choosed.equals("")) {
                for (RoleAccess ra : listRoleAccess) {
                    ra.setIdRole(choosed);
                    ra.setIdLogin(idLogin);
                    updateDAO.execute("UPDATE.A_USR.ROLEACCESS", ra);
                    updateDAO.execute("UPDATE.A_USR.USERACCESS_BYROLE", ra);
                }
            }
            message.add(Globals.MESSAGE_KEY, new BLogicMessage("info.ERR2SC003"));
        } catch (Exception e) {
            // set error message and the destination for error case
            if (!error.get().hasNext()) {
                error.add(Globals.MESSAGE_KEY, new BLogicMessage("info.ERR2SC004"));
            }
            messageString = MessageString.FAILURE;
        }

        result.setMessages(message);
        result.setErrors(error);
        result.setResultString(messageString);
        result.setResultObject(input);
        return result;
    }

    public QueryDAO getQueryDAO() {
        return queryDAO;
    }

    public void setQueryDAO(QueryDAO queryDAO) {
        this.queryDAO = queryDAO;
    }

    public UpdateDAO getUpdateDAO() {
        return updateDAO;
    }

    public void setUpdateDAO(UpdateDAO updateDAO) {
        this.updateDAO = updateDAO;
    }
}