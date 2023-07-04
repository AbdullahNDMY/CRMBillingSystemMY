/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : A_USR
 * SERVICE NAME   : A_USR_S03
 * FUNCTION       : Showing BLogic
 * FILE NAME      : A_USR_S03_SearchBLogic.java
 * 
 * Copyright © 2014 NTTDATA Corporation
 *
 * History
 * 
 * 
 **********************************************************/
package nttdm.bsys.a_usr.blogic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.a_usr.dto.A_USR_S03IO;
import nttdm.bsys.a_usr.dto.PlanService;
import nttdm.bsys.common.util.dto.Mode;
import nttdm.bsys.common.util.dto.PageEvent;

/**
 * A_USR_S03_SearchBLogic<br>
 * <br>
 * 
 * @author i-yang
 * @version 1.0
 */
public class A_USR_S03_SearchBLogic implements BLogic<A_USR_S03IO> {
    private QueryDAO queryDAO;

    public BLogicResult execute(A_USR_S03IO input) {
        BLogicResult result = new BLogicResult();
        A_USR_S03IO output = new A_USR_S03IO();
        String mode = "";
        String choosed = input.getChoosed();
        String pageEvent = input.getPageEvent();
        List<PlanService> listPlanService = null;
        // get mode
        String accessType = input.getAccessType("A", "A-USR");
        if ("1".equals(accessType)) {
            mode = Mode.VIEW;
        } else if ("2".equals(accessType)) {
            mode = Mode.EDIT;
        }
        // get listPlanService
        if (pageEvent.equals(PageEvent.LOAD)) {
            if ("DEPARTMENT".equals(choosed.trim().toUpperCase())) {
                listPlanService = queryDAO.executeForObjectList("SELECT.A_USR.GET_LISTPLAN_DEPARTMENT", null);
            } else if ("DIVISION".equals(choosed.trim().toUpperCase())) {
                listPlanService = queryDAO.executeForObjectList("SELECT.A_USR.GET_LISTPLAN_DIVISION", null);
            } else if ("ROLE".equals(choosed.trim().toUpperCase())) {
                listPlanService = queryDAO.executeForObjectList("SELECT.A_USR.GET_LISTPLAN_ROLE", null);
            }
        } else {
            listPlanService = input.getListPlanService();
        }

        if (listPlanService != null) {
            // show data Remover blank and order by
            for (PlanService ps : listPlanService) {
                ps.setId(ps.getId().trim());
                ps.setName(ps.getName().trim());
            }

            Collections.sort(listPlanService, getComparator(listPlanService));
        }
        output.setChoosed(choosed);
        output.setListPlanService(listPlanService);
        result.setResultObject(output);
        result.setResultString(mode);
        return result;
    }

    private Comparator<PlanService> getComparator(List<PlanService> listPlanService) {
        List<String> nameList = new ArrayList<String>();
        List<String> idList = new ArrayList<String>();

        boolean isNameDup = false;
        boolean isIdDup = false;
        for (int i = 0; i < listPlanService.size(); i++) {
            PlanService ps = listPlanService.get(i);
            String name = ps.getName();
            String id = ps.getId();

            if (idList.contains(id)) {
                isIdDup = true;
                break;
            } else {
                idList.add(id);
            }

            if (nameList.contains(name)) {
                isNameDup = true;
            } else {
                nameList.add(name);
            }
        }
        // only name duplicate
        if (!isIdDup && isNameDup) {
            return new Comparator<PlanService>() {
                public int compare(PlanService p1, PlanService p2) {
                    return p1.getName().compareTo(p2.getName().trim());
                }
            };
        } else {
            // default is order by id
            return new Comparator<PlanService>() {
                public int compare(PlanService p1, PlanService p2) {
                    return p1.getId().compareTo(p2.getId().trim());
                }
            };
        }
    }

    public QueryDAO getQueryDAO() {
        return queryDAO;
    }

    public void setQueryDAO(QueryDAO queryDAO) {
        this.queryDAO = queryDAO;
    }

}