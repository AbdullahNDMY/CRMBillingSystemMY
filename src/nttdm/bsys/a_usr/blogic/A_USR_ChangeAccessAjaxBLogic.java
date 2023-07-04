/*
 * @(#)A_USR_ChangeAccessAjaxBLogic.java
 *
 *
 */
package nttdm.bsys.a_usr.blogic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.a_usr.dto.Module;
import nttdm.bsys.a_usr.dto.SubModule;
import nttdm.bsys.a_usr.dto.UserAccess;
import nttdm.bsys.common.util.CommonUtils;

/**
 * BusinessLogic class.
 * 
 * @author hungtm
 */
public class A_USR_ChangeAccessAjaxBLogic implements BLogic<Map<String, Object>> {

    /**
     * queryDAO
     */
    protected QueryDAO queryDAO;

    public BLogicResult execute(Map<String, Object> input) {
        BLogicResult result = new BLogicResult();
        String idRole = CommonUtils.toString(input.get("idRole"));
        Map<String, Object> output = new HashMap<String, Object>();
        if (!"".equals(idRole)) {
            List<UserAccess> listUserAccess = queryDAO.executeForObjectList("SELECT.A_USER.GET_LIST_ROLEACCESS", idRole);;
            output.put("listUserAccess", listUserAccess);
        }
        List<Module> listModule = queryDAO.executeForObjectList("SELECT.A_USR.GET_LIST_MODULE",null);
        output.put("listModule", listModule);
        List<SubModule> listSubModule = queryDAO.executeForObjectList("SELECT.A_USR.GET_LIST_SUBMODULE", null);
        output.put("listSubModule", listSubModule);
        result.setResultObject(output);
        result.setResultString("success");
        return result;
    }

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