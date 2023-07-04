/**
 * @(#)B_BIL_CheckDeleteStatusAction.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created Jul 16, 2014
 */
package nttdm.bsys.b_bil.blogic;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nttdm.bsys.common.util.CommonUtils;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.google.gson.Gson;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.web.struts.actions.ActionEx;

/**
 * @author loamanma
 * 
 */
public class B_BIL_CheckDeleteStatusAction extends ActionEx {

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

        String idRef = request.getParameter("idRef");

        HashMap<String, Object> resultData = new HashMap<String, Object>();

        Map<String, Object> debitInfo = queryDAO.executeForMap("B_BIL.getBillForCheckByIdref", idRef);
        resultData.put("resultFlg", "0");

        if (debitInfo == null || debitInfo.isEmpty()) {
            resultData.put("resultFlg", "1");
            resultData.put("msg", "Invoice: " + idRef + " already deleted");
        } else {
            if ("1".equals(CommonUtils.toString(debitInfo.get("IS_DELETED")))) {
                resultData.put("resultFlg", "1");
                resultData.put("msg", "Invoice: " + idRef + " already deleted");

            }
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
