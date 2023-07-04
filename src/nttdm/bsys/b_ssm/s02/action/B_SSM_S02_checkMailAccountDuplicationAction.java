package nttdm.bsys.b_ssm.s02.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.web.struts.actions.ActionEx;
import nttdm.bsys.common.util.CommonUtils;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.google.gson.Gson;

public class B_SSM_S02_checkMailAccountDuplicationAction extends ActionEx {

    private QueryDAO queryDAO;

    public void setQueryDAO(QueryDAO queryDAO) {
        this.queryDAO = queryDAO;
    }

    public QueryDAO getQueryDAO() {
        return queryDAO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * jp.terasoluna.fw.web.struts.actions.ActionEx#doExecute(org.apache.struts
     * .action.ActionMapping, org.apache.struts.action.ActionForm,
     * javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        Gson googleSon = new Gson();
        // cboCategory
        String subscriptionID = request.getParameter("subscriptionID");
        String[] mailAccount = request.getParameterValues("mailAccount");
        HashMap<String, Object> resultData = new HashMap<String, Object>();
        
        HashMap<String, Object> bLogicInput = new HashMap<String, Object>();
        bLogicInput.put("subscriptionID", subscriptionID);
        bLogicInput.put("mailAccountNames", mailAccount);
        
        List<Map<String, Object>> duplicateMailAccount = queryDAO.executeForMapList("B_SSM_S02.checkMailAccountDuplication", bLogicInput);
        if (!CommonUtils.isEmpty(duplicateMailAccount)) {
            resultData.put("resultFlg", "1");
            resultData.put("mailAccount", duplicateMailAccount.get(0).get("MAIL_ACCOUNT"));
            resultData.put("subscriptionID", duplicateMailAccount.get(0).get("ID_SUB_INFO"));
        }else{
            resultData.put("resultFlg", "0");
        }
        response.setContentType("application/json");

        String jsonString = googleSon.toJson(resultData);

        try {
            // write JSON string into the response
            response.getWriter().print(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }

}
