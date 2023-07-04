package nttdm.bsys.e_mim.blogic;

import java.io.File;
import java.util.Map;

import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.BillingSystemSettings;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import jp.terasoluna.fw.service.thin.BLogicResult;
import jp.terasoluna.fw.web.struts.actions.DownloadFile;

public class RP_E_MIM_US2_DownloadBLogic implements BLogic<Map<String, Object>> {
	
	private QueryDAO queryDAO;
	
	private UpdateDAO updateDAO;

	public QueryDAO getQueryDAO() {
		return queryDAO;
	}

	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}

	public UpdateDAO getUpdateDAO() {
		return updateDAO;
	}

	public void setUpdateDAO(UpdateDAO updateDAO) {
		this.updateDAO = updateDAO;
	}

	public BLogicResult execute(Map<String, Object> map) {
		BLogicResult result = new BLogicResult();
		BillingSystemSettings setting = new BillingSystemSettings(queryDAO);
		String exportPath = setting.getBatchGClcP01();
		
		File export = new File(exportPath + File.separator + map.get("filename"));
		DownloadFile downloader = new DownloadFile(export);
		
		result.setResultObject(downloader);
		result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS);
		return result;
	}

}
