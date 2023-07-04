/**
 * @(#)B_CSBS02_CheckErrorBLogic_Test.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2011/09/19
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.b_csb.blogic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicResult;

import org.apache.struts.util.LabelValueBean;

import nttdm.bsys.b_csb.dto.Debit_info_bean;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.AbstractUtilTest;

/**
 * @author gplai
 *
 */
public class B_CSBS02_CheckErrorBLogic_Test extends AbstractUtilTest {

    /**
     * B_CSBS02_CheckErrorBLogic
     */
    private B_CSBS02_CheckErrorBLogic logic;
    /**
     * the B_CSBR01BLogic's paramter
     */
    private Map<String, Object> input ;
    
    @Override
    protected void setUpData() throws Exception {
        logic = new B_CSBS02_CheckErrorBLogic();
        input = new HashMap<String,Object>();
        logic.setQueryDAO(queryDAO);
        logic.setUpdateDAO(updateDAO);
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        uvo.setUser_name("system admin");
        input.put("uvo", uvo);
    }
    
    public void testExecute1(){
        
        input.put("receiptNo", "");
        input.put("action", "edit");
        input.put("pmtMethod", "NT");
        input.put("pattern", "CST");
        
        BLogicResult result = logic.execute(input);
        assertEquals("success", result.getResultString());
    }
    
    public void testExecute2(){
        
        input.put("receiptNo", "");
        input.put("action", "edit");
        input.put("pmtMethod", "CQ");
        
        BLogicResult result = logic.execute(input);
        assertEquals("success", result.getResultString());
    }

    public void testExecute3(){
    
        input.put("receiptNo", "");
        input.put("action", "edit");
        input.put("pmtMethod", "CC");
        
        BLogicResult result = logic.execute(input);
        assertEquals("success", result.getResultString());
    }
    
    public void testExecute4(){
        
        input.put("receiptNo", "");
        input.put("action", "edit");
        input.put("pmtMethod", "GR");
        
        BLogicResult result = logic.execute(input);
        assertEquals("success", result.getResultString());
    }
    
    public void testExecute5(){
        
        input.put("receiptNo", "");
        input.put("action", "edit");
        input.put("pattern", "CST");
        
        BLogicResult result = logic.execute(input);
        assertEquals("success", result.getResultString());
    }
    
    
    public void testExecute6(){
        
        input.put("receiptNo", "");
        input.put("action", "edit");
        input.put("pattern", "BAC");
        
        BLogicResult result = logic.execute(input);
        assertEquals("success", result.getResultString());
    }
    public void testExecute7(){
        
        input.put("receiptNo", "");
        input.put("action", "edit");
        input.put("payer", "12");
        input.put("pattern", "BAC");
        
        BLogicResult result = logic.execute(input);
        assertEquals("success", result.getResultString());
    }

    public void testExecute8(){
        
        input.put("receiptNo", "");
        input.put("action", "edit");
        input.put("pattern", "CST");
        input.put("pmtMethod", "GR");
        
        BLogicResult result = logic.execute(input);
        assertEquals("success", result.getResultString());
    }
}
