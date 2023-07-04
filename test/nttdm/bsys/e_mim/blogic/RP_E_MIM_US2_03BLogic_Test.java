/**
 * @(#)RP_E_MIM_US2_03BLogic_Test.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2011/10/29
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.e_mim.blogic;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.AbstractUtilTest;
import nttdm.bsys.e_mim.dto.RP_E_MIM_US2_03Input;
import nttdm.bsys.e_mim.dto.RP_E_MIM_US2_03Output;

/**
 * @author gplai
 *
 */
public class RP_E_MIM_US2_03BLogic_Test extends AbstractUtilTest {

    private RP_E_MIM_US2_03BLogic logic;

    @Override
    protected void setUpData() throws Exception {
        logic = new RP_E_MIM_US2_03BLogic();
        logic.setQueryDAO(queryDAO);
        logic.setUpdateDAO(updateDAO);
        
        // delete all exist data
        super.deleteAllData("T_BATCH_LOG");
    }

    public void testExecute01() {
        // insert data to T_BATCH_LOG
        String[][] testDataTBatchLog = {
                { "ID_BATCH_LOG", "ID_BATCH_REF_NO", "ERROR_MSG",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "ID_BATCH_TYPE" },
                { "1", "44", "my errer messages", "2011-08-17 00:00:00",
                        "2011-08-17 00:00:00", "sysadmin", "G_CLC_P01" } };
        super.insertData("T_BATCH_LOG", testDataTBatchLog);
        
        RP_E_MIM_US2_03Input input = new RP_E_MIM_US2_03Input();
        input.setIdClcImpBatch(44);
        
        BLogicResult result = logic.execute(input);
        RP_E_MIM_US2_03Output output = (RP_E_MIM_US2_03Output)result.getResultObject();
        assertEquals(1, output.getListLog().size());
    }
}
