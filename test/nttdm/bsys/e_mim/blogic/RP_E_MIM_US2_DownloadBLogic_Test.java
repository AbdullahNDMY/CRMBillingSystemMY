/**
 * @(#)RP_E_MIM_US2_DownloadBLogic_Test.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2012/05/03
 * 
 * Copyright (c) 2012 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.e_mim.blogic;

import java.util.HashMap;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicResult;
import jp.terasoluna.fw.web.struts.actions.DownloadFile;
import nttdm.bsys.common.util.AbstractUtilTest;

/**
 * @author gplai
 *
 */
public class RP_E_MIM_US2_DownloadBLogic_Test extends AbstractUtilTest {

    private RP_E_MIM_US2_DownloadBLogic logic;
    
    @Override
    protected void setUpData() throws Exception {
        logic = new RP_E_MIM_US2_DownloadBLogic();
        logic.setQueryDAO(queryDAO);
        logic.setUpdateDAO(updateDAO);
    }

    public void testExecute01() {
        Map<String, Object> input = new HashMap<String, Object>();
        input.put("filename", "123");
        BLogicResult result = logic.execute(input);
        assertEquals("123", ((DownloadFile)result.getResultObject()).getName());
    }
}
