/*
 * @(#)RP_E_MEX_CT1DownloadBLogic.java
 *
 *
 */
package nttdm.bsys.e_exp.blogic;

import java.io.File;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import jp.terasoluna.fw.service.thin.BLogicResult;
import jp.terasoluna.fw.web.struts.actions.DownloadFile;
import nttdm.bsys.e_exp.dto.RP_E_EXP_S01DownloadInput;

/**
 * BusinessLogic class.
 * 
 * @author thinhtv
 */
public class RP_E_EXP_S01DownloadBLogic implements BLogic<RP_E_EXP_S01DownloadInput>{

    /**
     * queryDAO
     */
    protected QueryDAO queryDAO;

    /**
     * updateDAO
     */
    protected UpdateDAO updateDAO;
    
	/**
	 * 
	 * @param param
	 * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
	 */
	public BLogicResult execute(RP_E_EXP_S01DownloadInput input) {
		BLogicResult result = new BLogicResult();
		String exportPath = queryDAO.executeForObject("E_EXP.getE_EXP_F01FilePath", null, String.class);;
		
		File export = new File(exportPath + File.separator + input.getFilename());
		DownloadFile downloader = new DownloadFile(export);
		
		result.setResultObject(downloader);
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

    /**
     * @return the updateDAO
     */
    public UpdateDAO getUpdateDAO() {
        return updateDAO;
    }

    /**
     * @param updateDAO the updateDAO to set
     */
    public void setUpdateDAO(UpdateDAO updateDAO) {
        this.updateDAO = updateDAO;
    }
}