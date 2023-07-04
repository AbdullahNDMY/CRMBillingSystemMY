/**
 * @(#)G_BTH_P01.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2013/04/02
 * 
 * Copyright (c) 2013 Billing System Malaysia. All rights reserved.
 */
package nttdm.bsys.common.util;

import java.io.File;
import java.util.Calendar;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.web.struts.actions.DownloadFile;
import nttdm.bsys.b_bth.dto.RP_B_BTH_P01_03Input;
import nttdm.bsys.b_bth.dto.RP_B_BTH_P01_03Output;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;

/**
 * @author gplai
 *
 */
public class G_BTH_P01 extends AbstractGlobalProcess<RP_B_BTH_P01_03Input, RP_B_BTH_P01_03Output> {

    public GlobalProcessResult execute(RP_B_BTH_P01_03Input param, RP_B_BTH_P01_03Output outputDTO) {
        BLogicMessages errors = new BLogicMessages();
        BLogicMessages messages = new BLogicMessages();

        Map<String,Object> mSystemBase=this.queryDAO.executeForMap("B_BTH.getBILDOCPDF", null);
        String systembase=CommonUtils.toString(mSystemBase.get("VALUE"));
        String filePath="";
        
        if ("SG01".equals(systembase)) {
            G_BTH_P01_SG g_bth_p01_sg = new G_BTH_P01_SG(this.queryDAO, this.updateDAO);
            /*#270 B-BTH-S01 Billing Document Batch Print add print option CT 28062017*/
            if(param.getPrintOption() == 0){
            	filePath = g_bth_p01_sg.generate(param.getIdRefs(), param.getUvo());
            }else{
            	filePath = g_bth_p01_sg.generateMultipleDocInOnePDF(param.getIdRefs(), param.getUvo());
            }
            /*#270 B-BTH-S01 Billing Document Batch Print add print option CT 28062017*/
        } else if("MY02".equals(systembase)){
        	G_BTH_P01_MY_2 g_bth_p01_my_2 = new G_BTH_P01_MY_2(this.queryDAO, this.updateDAO);
        	/*#270 B-BTH-S01 Billing Document Batch Print add print option CT 28062017*/
        	if(param.getPrintOption() == 0){
        		filePath = g_bth_p01_my_2.generate(param.getIdRefs(),param.getAutSign(),param.getUvo());
        	}else{
        		filePath = g_bth_p01_my_2.generateSingleDocInOnePDF(param.getIdRefs(),param.getAutSign(),param.getUvo());
        	}
        	/*#270 B-BTH-S01 Billing Document Batch Print add print option CT 28062017*/
        }else {
            G_BTH_P01_MY g_bth_p01_my = new G_BTH_P01_MY(this.queryDAO, this.updateDAO);
            /*#270 B-BTH-S01 Billing Document Batch Print add print option CT 28062017*/
            if(param.getPrintOption() == 0){
            	filePath = g_bth_p01_my.generate(param.getIdRefs(),param.getUvo());
            }else{
            	filePath = g_bth_p01_my.generateSingleDocInOnePDF(param.getIdRefs(),param.getUvo());
            }
            /*#270 B-BTH-S01 Billing Document Batch Print add print option CT 28062017*/
        }
        
        File file = new File(filePath);
        GlobalProcessResult gResult = new GlobalProcessResult();
        /* file . reName*/
        gResult.setFile(file);
        gResult.setErrors(errors);
        gResult.setMessages(messages);
        return gResult;
    }
    
    public String genereateReport(String [] idRefs,String autSign,BillingSystemUserValueObject uvo, int printOption){
        Map<String,Object> mSystemBase=this.queryDAO.executeForMap("B_BTH.getBILDOCPDF", null);
        String systembase=CommonUtils.toString(mSystemBase.get("VALUE"));
        String filePath="";
        
        if ("SG01".equals(systembase)) {
            G_BTH_P01_SG g_bth_p01_sg = new G_BTH_P01_SG(this.queryDAO, this.updateDAO);
            if(printOption == 0){
            	filePath = g_bth_p01_sg.generate(idRefs, uvo);
            }else{
            	filePath = g_bth_p01_sg.generateMultipleDocInOnePDF(idRefs, uvo);
            }
        } else if ("MY02".equals(systembase)) {
        	G_BTH_P01_MY_2 g_bth_p01_my_2 = new G_BTH_P01_MY_2(this.queryDAO, this.updateDAO);
        	if(printOption == 1){
        		filePath = g_bth_p01_my_2.generate(idRefs,autSign,uvo);
        	}else{
        		filePath = g_bth_p01_my_2.generateSingleDocInOnePDF(idRefs,autSign,uvo);
        	}
        }else {
            G_BTH_P01_MY g_bth_p01_my = new G_BTH_P01_MY(this.queryDAO, this.updateDAO);
            if(printOption == 1){
            	filePath = g_bth_p01_my.generate(idRefs,uvo);
            }else{
            	filePath = g_bth_p01_my.generateSingleDocInOnePDF(idRefs,uvo);
            }
        }
        return filePath;
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
