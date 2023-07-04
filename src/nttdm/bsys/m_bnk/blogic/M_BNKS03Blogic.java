package nttdm.bsys.m_bnk.blogic;

import java.util.ArrayList;
import java.util.List;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.m_bnk.bean.M_BNK_bankbean;
import nttdm.bsys.m_bnk.dto.M_BNKS03Input;
import nttdm.bsys.m_bnk.dto.M_BNKS03Output;

/**
 * bank info search for m_com_01_1
 * 
 * @author loamanma
 * 
 */
public class M_BNKS03Blogic implements BLogic<M_BNKS03Input> {

    protected QueryDAO queryDAO;

    public QueryDAO getQueryDAO() {
        return queryDAO;
    }

    public void setQueryDAO(QueryDAO queryDAO) {
        this.queryDAO = queryDAO;
    }

    public BLogicResult execute(M_BNKS03Input param) {
        BLogicResult result = new BLogicResult();
        M_BNKS03Output output = new M_BNKS03Output();

        if (param.getCheckpagetype() >= 1) {
            int row;
            int startIndex = 0;
            startIndex = param.getStartIndex();
            BillingSystemSettings systemSetting = new BillingSystemSettings(queryDAO);
            row = systemSetting.getResultRow();
            output.setRow(row);

            param.setBank_fullname(CommonUtils.toString(param.getBank_fullname()).trim());
            param.setBank_code(CommonUtils.toString(param.getBank_code()).trim());
            param.setBranch_code(CommonUtils.toString(param.getBranch_code()).trim());

            List<M_BNK_bankbean> listsearch = queryDAO.executeForObjectList("SELECT.M_BNK.SQL018", param);
            if (startIndex < 0 || startIndex > listsearch.size()) {
                startIndex = 0;
            }
            output.setTotalCount(listsearch.size());
            listsearch = queryDAO.executeForObjectList("SELECT.M_BNK.SQL018", param, startIndex, row);

            output.setListsearch(listsearch);

            if (listsearch.size() > 0) {
                for (int i = 1; i <= listsearch.size(); i++) {
                    listsearch.get(i - 1).setIdnum(i + startIndex);
                }
                output.setCheckpagetype(2);
                output.setStartIndex(startIndex);
            }
            output.setIndex(param.getIndex());
            result.setResultString("success");
            result.setResultObject(output);

        } else if (param.getCheckpagetype() < 1) {
            List<M_BNK_bankbean> listsearch = new ArrayList<M_BNK_bankbean>();
            output.setListsearch(listsearch);
            output.setTotalCount(-1);
            output.setIndex(param.getIndex());
            result.setResultString("success");
            result.setResultObject(output);
        }

        return result;
    }

}
