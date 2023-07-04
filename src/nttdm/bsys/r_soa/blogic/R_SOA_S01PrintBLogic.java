/**
 * @(#)R_SOA_S01PrintBLogic.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2012/07/03
 * 
 * Copyright (c) 2012 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.r_soa.blogic;

import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;

import jp.terasoluna.fw.service.thin.BLogicResult;
import jp.terasoluna.fw.web.struts.actions.DownloadFile;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.GlobalProcessResult;
import nttdm.bsys.r_soa.dto.R_SOA_S01PrintInput;
import nttdm.bsys.r_soa.dto.R_SOA_S01PrintOutput;

import org.apache.commons.beanutils.BeanUtils;

/**
 * @author gplai
 *
 */
public class R_SOA_S01PrintBLogic extends AbstractR_SOA_S01PrintBLogic {

    public BLogicResult execute(R_SOA_S01PrintInput params) {
        BLogicResult result = new BLogicResult();
        R_SOA_S01PrintOutput outputDTO = new R_SOA_S01PrintOutput();
        String printType = CommonUtils.toString(params.getPrintType());
        String[] selectedStatementNo = params.getSelectedStatementNo();
        String sessionDirectory = params.getUvo().getSESSION_DIRECTORY();
        SOA_EXP_F01 soaExpF01 = new SOA_EXP_F01();
        soaExpF01.setSessiondictionary(sessionDirectory);
        soaExpF01.setQueryDAO(queryDAO);
        soaExpF01.setUpdateDAO(updateDAO);
        GlobalProcessResult glPResult = null;
        //print button click
        if ("print".equals(printType)) {
            glPResult = soaExpF01.execute(selectedStatementNo, "1");
        } else {
            String fullStmtNo = CommonUtils.toString(params.getFullStmtNo());
            String[] fullStmtNoArr = fullStmtNo.split(",");
            glPResult = soaExpF01.execute(fullStmtNoArr, "1");
        }
        try {
            BeanUtils.copyProperties(outputDTO, params);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        // Set download file
        DownloadFile downloadFile = new DownloadFile(glPResult.getFile());
        Calendar cal = Calendar.getInstance();
        String zipName = "StatementOfAccount_" + CommonUtils.formatDate(cal, "yyyyMMddHHmmss") + ".zip";
        downloadFile.setName(zipName);
        result.setResultObject(downloadFile);
        result.setErrors(glPResult.getErrors());
        result.setMessages(glPResult.getMessages());
        return result;
    }

}
