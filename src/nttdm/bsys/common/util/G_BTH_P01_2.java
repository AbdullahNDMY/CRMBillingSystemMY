/**
 * Billing System
 * 
 * SUBSYSTEM NAME : G_BTH_P01_2 
 * SERVICE NAME :  B_BTH_P01
 * FUNCTION : Print Billing Document
 * FILE NAME : G_BTH_P01_2.java
 * 
 * Copyright (C) 2011 NTTDATA Corporation
 */
package nttdm.bsys.common.util;

import java.io.File;
import java.util.Calendar;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.web.struts.actions.DownloadFile;
import nttdm.bsys.b_bth.dto.RP_B_BTH_P01_04Input;
import nttdm.bsys.b_bth.dto.RP_B_BTH_P01_04Output;

/** 
 * BusinessLogic class.
 * @author NTT Data Vietnam	
 * Action export file Billing Document
 * 
 */

public class G_BTH_P01_2 extends AbstractGlobalProcess<RP_B_BTH_P01_04Input, RP_B_BTH_P01_04Output> {
	
	public GlobalProcessResult execute(RP_B_BTH_P01_04Input param, RP_B_BTH_P01_04Output outputDTO) {
		BLogicMessages errors = new BLogicMessages();
		BLogicMessages messages = new BLogicMessages();
		G_BTH_P01 bth = new G_BTH_P01();
		bth.setQueryDAO(this.queryDAO);
		bth.setUpdateDAO(this.updateDAO);
		bth.setUpdateDAONuked(this.updateDAONuked);
		String filePath = bth.genereateReport(param.getFullIdRefs().split(","), param.getAutSign(),param.getUvo(),param.getPrintOption());
		File file = new File(filePath);
		GlobalProcessResult gResult = new GlobalProcessResult();
		gResult.setFile(file);
		gResult.setErrors(errors);
		gResult.setMessages(messages);
		return gResult;
	}
	
	public DownloadFile getDownloadFile(File file) {
        Map<String,Object> mSystemBase=this.queryDAO.executeForMap("B_BTH.getBILDOCPDF", null);
        String systembase=CommonUtils.toString(mSystemBase.get("VALUE"));
        
        DownloadFile downloadFile = new DownloadFile(file);
        if ("SG01".equals(systembase)) {
            Calendar cal = Calendar.getInstance();
            String zipName = "BatchPrint_" + CommonUtils.formatDate(cal, "yyyyMMddHHmmss") + ".zip";
            downloadFile.setName(zipName);
        }
        return downloadFile;
    }
}