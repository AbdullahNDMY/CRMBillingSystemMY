/**
 * @(#)R_SOA_S01InitBLogic_Test.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2012/09/10
 * 
 * Copyright (c) 2012 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.r_soa.blogic;

import java.util.ArrayList;
import java.util.List;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.c_cmn001.bean.UserAccess;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.AbstractUtilTest;
import nttdm.bsys.r_soa.dto.R_SOA_S01InitInput;

/**
 * @author gplai
 *
 */
public class R_SOA_S01InitBLogic_Test extends AbstractUtilTest{

    private R_SOA_S01InitBLogic blogic;
    private R_SOA_S01InitInput input;
    private BLogicResult result;
    
    @Override
    protected void setUpData() throws Exception {
        blogic = new R_SOA_S01InitBLogic();
        input = new R_SOA_S01InitInput();
    }

    public void testExecute01(){
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        
        List<UserAccess> userAccessList = new ArrayList<UserAccess>();
        UserAccess userAccess = new UserAccess();
        userAccess.setId_module("R");
        userAccess.setId_sub_module("R-SOA");
        userAccess.setAccess_type("2");
        userAccessList.add(userAccess);
        uvo.setUser_access(userAccessList);
        
        input.setUvo(uvo);
        result = blogic.execute(input);
        assertEquals("success", result.getResultString());
    }
}
