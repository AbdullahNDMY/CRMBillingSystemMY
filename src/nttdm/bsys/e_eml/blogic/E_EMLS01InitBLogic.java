package nttdm.bsys.e_eml.blogic;

import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.e_eml.dto.E_EMLInput;
import nttdm.bsys.e_eml.dto.E_EMLOutput;
import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import jp.terasoluna.fw.service.thin.BLogicResult;

public class E_EMLS01InitBLogic implements BLogic<E_EMLInput> {
	private QueryDAO queryDAO;

	public BLogicResult execute(E_EMLInput input) {
		BLogicResult result = new BLogicResult();
		E_EMLOutput out = new E_EMLOutput(); 
		String nontaxinvoiceFlg=CommonUtils.toString(queryDAO.executeForObject("B_CPM.getNonTaxInvoiceShowFlg",null, String.class));
		out.setNonTaxInvoiceShowFlg(nontaxinvoiceFlg);
		out.setPdfGen( new String[] { "1", "0" });
		out.setEmailSend(new String[] { "1", "0" });
		out.setSameEmail(new String[] { "1", "0" });
		out.setDeliveryEmail(new String[] { "1", "0" });
		out.setPeopleAccNo(new String[] { "1", "0" });
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
