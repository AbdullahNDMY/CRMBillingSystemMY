/**
 * @(#)B_CSB_S03ErrorForward.java
 * 
 * Version 1.00
 * 
 * Created 2014-3-10
 */
package nttdm.bsys.b_csb.blogic;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.web.struts.actions.ForwardAction;
import nttdm.bsys.b_csb.bean.B_CSB_S03FormBean;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

/**
 * @author loamanma
 * 
 */
public class B_CSB_S03ErrorForward extends ForwardAction {

    protected QueryDAO queryDAO;

    public void setQueryDAO(QueryDAO queryDAO) {
        this.queryDAO = queryDAO;
    }

    @Override
    public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) {

        // get bank info
        List<LabelValueBean> cbBankAccList = queryDAO.executeForObjectList("SELECT.BSYS.BCSB.SQL003", null);
        ((B_CSB_S03FormBean) form).setCbBankAccList(cbBankAccList);
        return super.doExecute(mapping, form, req, res);
    }

}
