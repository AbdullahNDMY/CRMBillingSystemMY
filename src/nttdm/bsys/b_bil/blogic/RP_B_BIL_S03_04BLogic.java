/**
 * @(#)RP_B_BIL_S03_04BLogic.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2011/08/18
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.b_bil.blogic;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.b_bil.dto.RP_B_BIL_S03_04Input;
import nttdm.bsys.b_bth.dto.RP_B_BTH_P01_03Input;
import nttdm.bsys.b_bth.dto.RP_B_BTH_P01_03Output;
import nttdm.bsys.common.util.G_BTH_P01;
import nttdm.bsys.common.util.GlobalProcessResult;
import nttdm.bsys.util.ApplicationContextProvider;

import org.springframework.context.ApplicationContext;

/**
 * BusinessLogic class.
 * 
 * @author gplai
 * 
 */
public class RP_B_BIL_S03_04BLogic extends AbstractRP_B_BIL_S03_04BLogic {

    /**
     * G_BTH_P01
     */
    private static final String G_BTH_P01 = "G_BTH_P01";

    /**
     * @param param RP_B_BIL_S03_04Input
     * @return BLogicResult businessLogic result
     */
    public BLogicResult execute(RP_B_BIL_S03_04Input param) {
        // BLogicResult object
        BLogicResult result = new BLogicResult();
        // RP_B_BTH_P01_03Output object
        RP_B_BTH_P01_03Output outputDTO = new RP_B_BTH_P01_03Output();
        // RP_B_BTH_P01_03Input object
        RP_B_BTH_P01_03Input p0103InputDto = new RP_B_BTH_P01_03Input();
        // idRef
        String idRef = param.getIdRef();
        // Array idRefs
        String[] idRefs = new String[1];
        idRefs[0] = idRef;
        // set idRefs
        p0103InputDto.setIdRefs(idRefs);
        // set uvo
        p0103InputDto.setUvo(param.getUvo());
        //AutSign
        String autsign=param.getAutSign();
        p0103InputDto.setAutSign(autsign);
        
        // Call G_BTH_P01 process
        ApplicationContext context = ApplicationContextProvider.
            getApplicationContext();
        // get G_BTH_P01 object
        G_BTH_P01 gBthP01 = (G_BTH_P01) context.getBean(G_BTH_P01);
        // The method execute is called G_BTH_P01
        GlobalProcessResult glPResult = gBthP01.execute(p0103InputDto,
                outputDTO);
        // error info
        result.setErrors(glPResult.getErrors());
        // message info
        result.setMessages(glPResult.getMessages());
        // Set download file
        result.setResultObject(gBthP01.getDownloadFile(glPResult.getFile()));

        return result;
    }

}
