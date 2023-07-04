package nttdm.bsys.m_eml.blogic;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.m_eml.dto.M_EML02Input;

public class M_EML_S02CheckErrorSaveBLogic implements BLogic<M_EML02Input>{
	
	private QueryDAO queryDAO;

	public BLogicResult execute(M_EML02Input input) {
		BLogicResult result = new BLogicResult();
		if("New".equals(input.getModelFlg())){
			result.setResultString("success_new");
		}else{
			result.setResultString("success_edit");
		}
		result.setResultObject(input);
		
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
