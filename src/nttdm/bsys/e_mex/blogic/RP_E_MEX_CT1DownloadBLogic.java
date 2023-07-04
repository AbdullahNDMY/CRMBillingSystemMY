/*
 * @(#)RP_E_MEX_CT1DownloadBLogic.java
 *
 *
 */
package nttdm.bsys.e_mex.blogic;

import java.io.File;

import jp.terasoluna.fw.service.thin.BLogicResult;
import jp.terasoluna.fw.web.struts.actions.DownloadFile;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.e_mex.dto.RP_E_MEX_CT1DownloadInput;

import nttdm.bsys.e_mex.blogic.AbstractRP_E_MEX_CT1DownloadBLogic;

/**
 * BusinessLogic class.
 * 
 * @author thinhtv
 */
public class RP_E_MEX_CT1DownloadBLogic extends
		AbstractRP_E_MEX_CT1DownloadBLogic {

	/**
	 * 
	 * @param param
	 * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
	 */
	public BLogicResult execute(RP_E_MEX_CT1DownloadInput param) {
		BLogicResult result = new BLogicResult();
		BillingSystemSettings setting = new BillingSystemSettings(queryDAO);
		String exportPath = setting.getBatchGCitP01();
		
		/**
         * Audit Trail
         */
        Integer idAudit = CommonUtils.auditTrailBegin(param.getUvo().getId_user(),
                BillingSystemConstants.MODULE_E,
                BillingSystemConstants.SUB_MODULE_E_MEX_CT1, 
                param.getIdCitExpBatch()+" : "+param.getFilename(), "0",
                BillingSystemConstants.AUDIT_TRAIL_VIEWED);
		
		File export = new File(exportPath + File.separator + param.getFilename());
		DownloadFile downloader = new DownloadFile(export);
		
		CommonUtils.auditTrailEnd(idAudit);
		
		result.setResultObject(downloader);
		result.setResultString("success");
		return result;
	}
}