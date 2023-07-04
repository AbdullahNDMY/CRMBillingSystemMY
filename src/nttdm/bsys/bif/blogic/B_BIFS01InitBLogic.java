/*
 * @(#)RP_B_BIL_S01_01BLogic.java
 *
 *
 */
package nttdm.bsys.bif.blogic;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.b_cpm_en.dto.B_CPM_CONSTANT;
import nttdm.bsys.bif.dto.B_BIFInput;
import nttdm.bsys.bif.dto.B_BIFOutput;
import nttdm.bsys.common.util.CommonUtils;

/**
 * BusinessLogic class.
 * 
 * @author khungl0ng
 */
public class B_BIFS01InitBLogic extends AbstractB_BIFBLogic {

    private QueryDAO queryDAO;
    
	/**
	 * 
	 * @param param
	 * @return ビジ�?スロジック�?�実行�?果�?BLogicResultインスタンス。
	 */
	public BLogicResult execute(B_BIFInput param) {
		BLogicResult result = new BLogicResult();
		B_BIFOutput out = new B_BIFOutput();
		String nontaxinvoiceFlg=CommonUtils.toString(queryDAO.executeForObject("B_CPM.getNonTaxInvoiceShowFlg",null, String.class));
		out.setNonTaxInvoiceShowFlg(nontaxinvoiceFlg);
		
		result.setResultObject(out);
		result.setResultString("success");
		return result;
	}

    /**
     * @return the queryDAO
     */
    public QueryDAO getQueryDAO() {
        return queryDAO;
    }

    /**
     * @param queryDAO the queryDAO to set
     */
    public void setQueryDAO(QueryDAO queryDAO) {
        this.queryDAO = queryDAO;
    }
}