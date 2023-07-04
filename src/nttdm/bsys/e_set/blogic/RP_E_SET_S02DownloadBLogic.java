/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : E_SET
 * SERVICE NAME   : E_SET_S02
 * FUNCTION       : Download log file
 * FILE NAME      : RP_E_SET_S02DownloadBLogic.java
 * 
 * Copyright © 2011 NTTDATA Corporation
 *
 * History
 * 
 * 
 **********************************************************/
package nttdm.bsys.e_set.blogic;

import java.io.File;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicResult;
import jp.terasoluna.fw.web.struts.actions.DownloadFile;
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.common.util.CommonUtils;

/**
 * RP_E_SET_S02DownloadBLogic<br>
 * <ul>
 * <li>A BLogic class to download log file by batch run.
 * </ul>
 * <br>
 * 
 * @author NTTData
 * @version 1.0
 */
public class RP_E_SET_S02DownloadBLogic extends AbstractRP_E_SET_S02DownloadBLogic {

    public BLogicResult execute(Map<String, Object> input) {
        BLogicResult result = new BLogicResult();
        String fileName = CommonUtils.toString(input.get("filename"));
        String exportPath = "";
        String batchType = CommonUtils.toString(input.get("FUNC_ID"));

        if ("CB".equalsIgnoreCase(batchType)) {
            // Download <filename> from Auto offset cashbook directory
            exportPath = CommonUtils.toString(queryDAO.executeForObject("E_SET.getARfilepath", null, String.class));
        } else if ("CC".equalsIgnoreCase(batchType)) {
            // Download <filename> from alert notification directory
            BillingSystemSettings systemSetting = new BillingSystemSettings(queryDAO);
            exportPath = systemSetting.getFileLocation();

            if (!exportPath.endsWith(File.separator)) {
                exportPath = exportPath + File.separator;
            }
            exportPath = exportPath + "Notification";
        }

        if (!exportPath.endsWith(File.separator)) {
            exportPath = exportPath + File.separator;
        }
        File export = new File(exportPath + fileName);
        DownloadFile downloader = new DownloadFile(export);

        result.setResultObject(downloader);
        result.setResultString("success");
        return result;
    }
}
