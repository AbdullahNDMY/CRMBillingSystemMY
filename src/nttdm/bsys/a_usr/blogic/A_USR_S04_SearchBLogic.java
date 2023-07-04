/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : A_USR
 * SERVICE NAME   : A_USR_S04
 * FUNCTION       : Search BLogic
 * FILE NAME      : A_USR_S04_SearchBLogic.java
 * 
 * Copyright © 2014 NTTDATA Corporation
 *
 * History
 * 
 * 
 **********************************************************/
package nttdm.bsys.a_usr.blogic;

import java.util.ArrayList;
import java.util.List;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.a_usr.dto.A_USR_S04IO;
import nttdm.bsys.a_usr.dto.Module;
import nttdm.bsys.a_usr.dto.Role;
import nttdm.bsys.a_usr.dto.RoleAccess;
import nttdm.bsys.a_usr.dto.SubModule;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.dto.Mode;

/**
 * A_USR_S04_SearchBLogic<br>
 * <ul>
 * <li>A BLogic class to process showing services and plans
 * </ul>
 * <br>
 * 
 * @author i-yang
 * @version 1.0
 */
public class A_USR_S04_SearchBLogic implements BLogic<A_USR_S04IO> {

    private QueryDAO queryDAO;

    public BLogicResult execute(A_USR_S04IO input) {
        BLogicResult result = new BLogicResult();
        A_USR_S04IO output = new A_USR_S04IO();
        String mode = "";
        String choosed = CommonUtils.toString(input.getChoosed());
        
        // get mode
        String accessType = input.getAccessType("A", "A-USR");
        if ("1".equals(accessType)) {
            mode = Mode.VIEW;
        } else if ("2".equals(accessType)) {
            mode = Mode.EDIT;
        }
        
        List<Role> listRole = queryDAO.executeForObjectList("SELECT.A_USR.GET_LIST_ROLE", null);
        List<Module> listModule = new ArrayList<Module>();
        List<SubModule> listSubModule = new ArrayList<SubModule>();
        List<RoleAccess> listRoleAccess = new ArrayList<RoleAccess>();
        
        if (!choosed.equals("")) {
            listModule = queryDAO.executeForObjectList("SELECT.A_USR.GET_LIST_MODULE", null);
            listSubModule = queryDAO.executeForObjectList("SELECT.A_USR.GET_LIST_SUBMODULE", null);
            listRoleAccess = queryDAO.executeForObjectList("SELECT.A_USR.GET_LIST_ROLEACCESS", choosed);
        }
        output.setListRole(listRole);
        output.setChoosed(choosed);
        output.setListModule(listModule);
        output.setListSubModule(listSubModule);
        output.setListRoleAccess(listRoleAccess);
        result.setResultObject(output);
        result.setResultString(mode);
        return result;
    }

    public QueryDAO getQueryDAO() {
        return queryDAO;
    }

    public void setQueryDAO(QueryDAO queryDAO) {
        this.queryDAO = queryDAO;
    }
}