/**
 * @(#)CheckAlreadyDeleteAction.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created Jul 15, 2014
 * 
 */
package nttdm.bsys.b_trm.blogic;

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

/**
 * 
 * check whether TRM is already deleted
 * 
 * @author loamanma
 * 
 */
public class B_TRMS02_CheckDeleteStatusAction extends ActionEx {

    protected QueryDAO queryDAO;

    public QueryDAO getQueryDAO() {
        return queryDAO;
    }

    public void setQueryDAO(QueryDAO queryDAO) {
        this.queryDAO = queryDAO;
    }

    @Override
    public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        Gson googleSon = new Gson();

        String creditRef = request.getParameter("creditRef");
        String[] matchIds = request.getParameterValues("matchId");

        HashMap<String, Object> resultData = new HashMap<String, Object>();

        HashMap<String, Object> bLogicInput = new HashMap<String, Object>();
        bLogicInput.put("creditRef", creditRef);
        bLogicInput.put("matchIds", matchIds);

        //List<Map<String, Object>> debitInfos = queryDAO.executeForMapList("TRM.checkTrmExist", bLogicInput);
        resultData.put("resultFlg", "0");

        // get all current matchings of credit note
        List<Map<String, Object>> trms = queryDAO.executeForMapList("TRM.getMatchsOfCreditNo", creditRef);

        if (CommonUtils.isEmpty(trms)) {
            resultData.put("resultFlg", "1");
            resultData.put("msg", "Transaction matching for credit note: " + creditRef + " already deleted");
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
