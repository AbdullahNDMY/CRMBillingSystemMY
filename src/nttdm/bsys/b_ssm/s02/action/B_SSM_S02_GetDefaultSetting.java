package nttdm.bsys.b_ssm.s02.action;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.service.thin.BLogicResult;
import jp.terasoluna.fw.web.struts.actions.AbstractBLogicAction;

public class B_SSM_S02_GetDefaultSetting<P> extends AbstractBLogicAction<P>{
    
    private QueryDAO queryDAO;

    public void setQueryDAO(QueryDAO queryDAO) {
        this.queryDAO = queryDAO;
    }

    public QueryDAO getQueryDAO() {
        return queryDAO;
    }

    @Override
    public BLogicResult doExecuteBLogic(P arg0) throws Exception {
        BLogicResult result = new BLogicResult();
        return result;
    }
    
    @Override
    protected void postDoExecuteBLogic(HttpServletRequest request, HttpServletResponse response, P params,
            BLogicResult result) throws Exception {
    
        response.setCharacterEncoding("UTF-8");
        
        Map<String,Object> defaultSetting =  queryDAO.executeForMap("B_SSM_S02.getDefaultSetting", null);
        
        Gson gson = new Gson();
        
        String json = gson.toJson(defaultSetting);
        
        PrintWriter out = response.getWriter();
        
        out.append(json);
        
        out.flush();
        
        out.close();
        
    }

}
