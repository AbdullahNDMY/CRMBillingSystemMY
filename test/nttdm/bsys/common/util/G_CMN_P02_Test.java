/**
 * @(#)G_CMN_P02_Test.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2012/04/25
 * 
 * Copyright (c) 2012 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.util;

import java.util.Map;

import nttdm.bsys.c_cmn002.bean.MessageBean;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;

/**
 * @author gplai
 *
 */
public class G_CMN_P02_Test extends AbstractUtilTest {

    private G_CMN_P02 gCmnP02;
    
    @Override
    protected void setUpData() throws Exception {
        super.deleteAllData("M_ALT_D");
        super.deleteAllData("M_ALT_H");
    }
    
    /**
     * case 1:MESSAGE_TYPE is NOTICE
     */
    public void testExecute01() throws Exception {
        String[][] mAltD =
        {
            {"ID_MSG", "ID_USER", "DISPLAY_DATE", "IS_NEW", "DATE_CREATED" , "DATE_UPDATED" ,
                "ID_LOGIN" , "IS_DELETED"} ,
            {"1", "sysadmin", "2011-07-28", "0", "2011-07-28" , "2011-07-28" ,
                "sysadmin" , "0"}};
        super.insertData("M_ALT_D", mAltD);
        
        gCmnP02 = new G_CMN_P02(queryDAO, updateDAO);
        
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        
        MessageBean input = new MessageBean();
        input.setMsg_type("NOTICE");
        input.setId_login("sysadmin");
        input.setId_msg("1");
        input.setUvo(uvo);
        
        gCmnP02.execute(input);
        
        Map<String, Object> map = queryDAO.executeForMap("TEST.SELECT.G_CMN_P02.SQL1", "1");
        assertEquals("1", map.get("IS_DELETED"));
        
        gCmnP02.getUpdateDAO();
        gCmnP02.setUpdateDAO(updateDAO);
    }
    
    /**
     * case 2:MESSAGE_TYPE is not NOTICE
     */
    public void testExecute02() throws Exception {
        String[][] mAltH =
        {
            {"ID_MSG", "MSG_TYPE", "SUBJECT", "ID_CREATOR", "DATE_CREATED" , "DATE_UPDATED" ,
                "ID_LOGIN" , "IS_DELETED"} ,
            {"1", "11", "abc", "0", "2011-07-28" , "2011-07-28" ,
                "sysadmin" , "0"}};
        String[][] mAltD =
        {
            {"ID_MSG", "ID_USER", "DISPLAY_DATE", "IS_NEW", "DATE_CREATED" , "DATE_UPDATED" ,
                "ID_LOGIN" , "IS_DELETED"} ,
            {"1", "sysadmin", "2011-07-28", "0", "2011-07-28" , "2011-07-28" ,
                "sysadmin" , "0"}};
        super.insertData("M_ALT_H", mAltH);
        super.insertData("M_ALT_D", mAltD);
        
        gCmnP02 = new G_CMN_P02(queryDAO, updateDAO);
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        
        MessageBean input = new MessageBean();
        input.setMsg_type("");
        input.setId_login("sysadmin");
        input.setId_msg("1");
        input.setUvo(uvo);
        
        gCmnP02.execute(input);
        
        Map<String, Object> map = queryDAO.executeForMap("TEST.SELECT.G_CMN_P02.SQL2", "1");
        assertEquals("1", map.get("IS_DELETED"));
    }
    
    /**
     * case 3:throw exception
     */
    public void testExecute03() {
        gCmnP02 = new G_CMN_P02(queryDAO, updateDAO);
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        
        MessageBean input = new MessageBean();
        
        try {
            gCmnP02.execute(input);
        } catch (Exception e) {
            
        }
    }
}
