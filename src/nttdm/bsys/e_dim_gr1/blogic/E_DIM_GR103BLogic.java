package nttdm.bsys.e_dim_gr1.blogic;

import java.io.File;

import jp.terasoluna.fw.service.thin.BLogicResult;
import jp.terasoluna.fw.web.struts.actions.DownloadFile;
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.e_dim_gr1.common.E_DIM_GR1_Common;
import nttdm.bsys.e_dim_gr1.dto.E_DIM_GR103Input;

public class E_DIM_GR103BLogic extends AbstractE_DIM_GR103BLogic {

	public BLogicResult execute(E_DIM_GR103Input param) {
		BLogicResult result = new BLogicResult();
		
		BillingSystemSettings setting = new BillingSystemSettings(queryDAO);
		String exportPath = setting.getBatchGGirP02();
		
		// get download path from M_GSET_D
//		String filePath = queryDAO.executeForObject(E_DIM_GR1_Common.SQL_SELECT_DOWNLOAD_PATH,  E_DIM_GR1_Common.ID_SET, String.class);
		// file name = InitialData.M_GSET_D.BATCH_G_GIR_P02 +  <batchID>.csv
//		String fileName = filePath + param.getIdGirImpBatch() + ".csv";
		// read file
		File file =  new File(exportPath + File.separator + param.getFilename());
		// return file
		DownloadFile downloadfile = new DownloadFile(file);		
		result.setResultObject(downloadfile);		   
		result.setResultString("success");
	   return result;
	}
}