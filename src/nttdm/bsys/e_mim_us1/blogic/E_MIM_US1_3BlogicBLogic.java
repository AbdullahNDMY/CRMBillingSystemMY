package nttdm.bsys.e_mim_us1.blogic;

import java.io.File;

import jp.terasoluna.fw.service.thin.BLogicResult;
import jp.terasoluna.fw.web.struts.actions.DownloadFile;
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.e_mim_us1.dto.E_MIM_US1_3BlogicInput;

import nttdm.bsys.e_mim_us1.blogic.AbstractE_MIM_US1_3BlogicBLogic;

public class E_MIM_US1_3BlogicBLogic extends AbstractE_MIM_US1_3BlogicBLogic {
	
	private static String SELECT_LOCATION_PATH = "SELECT.E_MIM_US1.07"; 
	private static String ID_SET = "SELECT_LOCATION_PATH";

	public BLogicResult execute(E_MIM_US1_3BlogicInput param) {
		BLogicResult result = new BLogicResult();		
		// get download path from M_GSET_D
		BillingSystemSettings setting = new BillingSystemSettings(queryDAO);
//		String filePath = queryDAO.executeForObject(E_MIM_US1_3BlogicBLogic.SELECT_LOCATION_PATH,  E_MIM_US1_3BlogicBLogic.ID_SET, String.class);
		// file name = InitialData.M_GSET_D.BATCH_G_GIR_P02 +  <batchID>.csv
//		String fileName = filePath + param.getIdIpassImpBatch() + ".csv";
		String fileName = setting.getBatchGIpsP01() + param.getCsvFileName();
		// read file
		File file =  new File(fileName);
		// return file
		DownloadFile downloadfile = new DownloadFile(file);		
		result.setResultObject(downloadfile);		   
	   return result;
	}
}