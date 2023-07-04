/**
 * @(#)M_SVT01_SaveBLogic_Test.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created May 11, 2012
 * 
 * Copyright (c) 2012 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.m_svt.blogic;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.AbstractUtilTest;
import nttdm.bsys.m_svt.dto.M_SVT01IO;

/**
 * @author Joel Chin Yat Leng
 * 
 */
public class M_SVT01_SaveBLogic_Test extends AbstractUtilTest {

    private M_SVT01_SaveBLogic blogic01;
    private M_SVT01IO input;
    BLogicResult result;

    @Override
    protected void setUpData() throws Exception {

        blogic01 = new M_SVT01_SaveBLogic();
        input = new M_SVT01IO();
        result = new BLogicResult();
    }

    public void testExecute() throws Exception {

        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        input.setUvo(uvo);
        input.getUvo().setId_user("joel.chin");

        input.setTabValue("");
        input.setChoosed("");

    }

}
