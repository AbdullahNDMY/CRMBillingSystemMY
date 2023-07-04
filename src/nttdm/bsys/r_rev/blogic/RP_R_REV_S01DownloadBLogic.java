package nttdm.bsys.r_rev.blogic;

import java.io.File;

import jp.terasoluna.fw.service.thin.BLogicResult;
import jp.terasoluna.fw.web.struts.actions.DownloadFile;
import nttdm.bsys.r_rev.dto.RP_R_REV_S01DownloadInput;

public class RP_R_REV_S01DownloadBLogic extends AbstractRP_R_REV_S01DownloadBLogic{

		public BLogicResult execute (RP_R_REV_S01DownloadInput param) {
			BLogicResult result = new BLogicResult();
			
			String filePath = param.getFilePath();
			
			File downloadFile = new File(filePath);
			
			DownloadFile file = new DownloadFile(downloadFile);
			
			result.setResultObject(file);
			return result;
		}
}
