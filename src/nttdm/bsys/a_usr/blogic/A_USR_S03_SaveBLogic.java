/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : S_USR
 * SERVICE NAME   : S_USR_S03
 * FUNCTION       : Save BLogic
 * FILE NAME      : A_USR_S03_SaveBLogic.java
 * 
 * Copyright © 2014 NTTDATA Corporation
 *
 * History
 * 
 * 
 **********************************************************/
package nttdm.bsys.a_usr.blogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.a_usr.dto.A_USR_S03IO;
import nttdm.bsys.a_usr.dto.PlanService;
import nttdm.bsys.a_usr.dto.RoleAccess;
import nttdm.bsys.common.util.CommonUtils;

import org.apache.struts.Globals;

/**
 * A_USR_S03_SaveBLogic<br>
 * <br>
 * 
 * @author i-yang
 * @version 1.0
 */
public class A_USR_S03_SaveBLogic implements BLogic<A_USR_S03IO> {
    private QueryDAO queryDAO;
    private UpdateDAO updateDAO;

    public BLogicResult execute(A_USR_S03IO input) {

        BLogicResult result = new BLogicResult();
        A_USR_S03IO output = new A_USR_S03IO();
        BLogicMessages message = new BLogicMessages();
        BLogicMessages error = new BLogicMessages();

        String idLogin = input.getIdLogin();
        String choosed = input.getChoosed();

        int maxIdLength = 4;

        if ("Role".equals(choosed)) {
            maxIdLength = 10;
        }

        List<PlanService> listPlanService = input.getListPlanService();

        validate(listPlanService, error, maxIdLength);

        if (error.isEmpty()) {
            // screen data
            Map<String, String> screenIdMap = new HashMap<String, String>();

            // DB data
            Map<String, String> databaseIdMap = new HashMap<String, String>();

            for (PlanService ps : listPlanService) {
                ps.setIdLogin(idLogin);
                screenIdMap.put(ps.getId().trim(), ps.getId());
            }

            // get current DB data
            List<PlanService> listPlanServiceAll = getListPlanServiceAll(choosed);
            for (PlanService ps : listPlanServiceAll) {
                databaseIdMap.put(ps.getId().trim(), ps.getId());
            }

            // Object need to update
            Map<String, PlanService> updateObject = new HashMap<String, PlanService>();

            // db data check
            for (String trimId : databaseIdMap.keySet()) {

                // db data match screen data ,then update
                if (screenIdMap.containsKey(trimId)) {
                    PlanService ps = getPlanServiceById(listPlanService, screenIdMap.get(trimId));
                    updateObject.put(trimId, ps);
                } else {

                    // db data miss in screen data ,then delete
                    PlanService ps = new PlanService();
                    ps.setId(databaseIdMap.get(trimId));
                    updateOrDeletePlanService(ps, choosed, true);
                }

            }

            // screen data check
            for (String trimId : screenIdMap.keySet()) {

                PlanService ps = getPlanServiceById(listPlanService, screenIdMap.get(trimId));

                // if screen match db , then update
                if (databaseIdMap.containsKey(trimId)) {
                    updateObject.put(trimId, ps);
                } else {
                    // if screen miss in db ,then new insert
                    ps.setId(ps.getId().trim());
                    insertPlanService(ps, choosed);
                }
            }

            // loop object needed to update
            for (String key : updateObject.keySet()) {

                PlanService ps = updateObject.get(key);
                if (!choosed.equals("Role")) {
                    String newId = ps.getId().trim();
                    while (newId.length() < maxIdLength) {
                        newId += " ";
                    }
                    ps.setId(newId);
                }
                updateOrDeletePlanService(ps, choosed, false);
            }

            message.add(Globals.MESSAGE_KEY, new BLogicMessage("info.ERR2SC003"));
            result.setMessages(message);
        } else {
            listPlanService = new ArrayList<PlanService>();
        }
        output.setChoosed(choosed);
        output.setListPlanService(listPlanService);
        result.setErrors(error);
        result.setResultObject(output);
        result.setResultString("success");
        return result;
    }

    private PlanService getPlanServiceById(List<PlanService> pslist, String id) {
        for (int i = 0; i < pslist.size(); i++) {
            if (pslist.get(i).getId().equals(id)) {
                return pslist.get(i);
            }
        }
        return null;
    }

    private List<PlanService> getListPlanServiceAll(String choosed) {
        List<PlanService> listPlanService = new ArrayList<PlanService>();
        if ("Division".equals(choosed)) {
            listPlanService = queryDAO.executeForObjectList("SELECT.A_USR.GET_LIST_DIVISION_ID", null);
        } else if ("Department".equals(choosed)) {
            listPlanService = queryDAO.executeForObjectList("SELECT.A_USR.GET_LIST_DEPARTMENT_ID", null);
        } else if ("Role".equals(choosed)) {
            listPlanService = queryDAO.executeForObjectList("SELECT.A_USR.GET_LIST_ROLE_ID", null);
        }
        return listPlanService;
    }

    private void insertPlanService(PlanService planService, String choosed) {
        if ("Division".equals(choosed)) {
            updateDAO.execute("INSERT.A_USR.DIVISION", planService);
        } else if ("Department".equals(choosed)) {
            updateDAO.execute("INSERT.A_USR.DEPARTMENT", planService);
        } else if ("Role".equals(choosed)) {
            updateDAO.execute("INSERT.A_USR.ROLE", planService);
            // insert into roleAccess
            List<RoleAccess> listRoleAccess = queryDAO.executeForObjectList("SELECT.A_USR.ROLE_INITACCESS", null);
            for (RoleAccess roleAccess : listRoleAccess) {
                roleAccess.setIdRole(planService.getId());
                roleAccess.setIdLogin(planService.getIdLogin());
                updateDAO.execute("INSERT.A_USR.ROLEACCESS", roleAccess);
            }
        }
    }

    private void updateOrDeletePlanService(PlanService planService, String choosed, boolean flag) {
        if ("Division".equals(choosed)) {
            if (flag) {
                updateDAO.execute("DELETE.A_USR.DIVISION", planService.getId());
            } else {
                updateDAO.execute("UPDATE.A_USR.DIVISION", planService);
            }
        } else if ("Department".equals(choosed)) {
            if (flag) {
                updateDAO.execute("DELETE.A_USR.DEPARTMENT", planService.getId());
            } else {
                updateDAO.execute("UPDATE.A_USR.DEPARTMENT", planService);
            }
        } else if ("Role".equals(choosed)) {
            if (flag) {
                updateDAO.execute("DELETE.A_USR.ROLEACCESS", planService.getId());
                updateDAO.execute("DELETE.A_USR.ROLE", planService.getId());
            } else {
                updateDAO.execute("UPDATE.A_USR.ROLE", planService);
            }
        }
    }

    private void validate(List<PlanService> listPlanService, BLogicMessages error, int maxIdLength) {

        List<String> idList = new ArrayList<String>();
        List<String> nameList = new ArrayList<String>();
        List<String> tempList = new ArrayList<String>();

        for (PlanService ps : listPlanService) {
            idList.add(ps.getId().trim());
            nameList.add(ps.getName().trim());
        }

        Iterator<String> idIterator = idList.iterator();
        Iterator<String> nameIterator = nameList.iterator();

        // id validate
        while (idIterator.hasNext()) {
            String idStr = idIterator.next();
            if (CommonUtils.isEmpty(idStr)) {
                // validate is null
                error.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC005", "ID"));
                break;
            }
            if (idStr.length() > maxIdLength) {
                error.add(Globals.ERROR_KEY, new BLogicMessage("errors.maxlength", new Object[] { "ID", maxIdLength + "" }));
                break;
            }

            if (tempList.contains(idStr)) {
                // validate Duplicate
                error.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC006", "ID-" + idStr));
                break;
            } else {
                tempList.add(idStr);
            }
        }

        tempList.clear();

        // name validate
        while (nameIterator.hasNext()) {
            String nameStr = nameIterator.next();
            if (CommonUtils.isEmpty(nameStr)) {
                // validate is null
                error.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC005", "Name"));
                break;
            }
            if (nameStr.length() > 14) {
                error.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC009", new Object[] { "Name", "14" }));
                break;
            }
            if (tempList.contains(nameStr)) {
                // validate Duplicate
                error.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC006", "Name-" + nameStr));
                break;
            } else {
                tempList.add(nameStr);
            }
        }
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