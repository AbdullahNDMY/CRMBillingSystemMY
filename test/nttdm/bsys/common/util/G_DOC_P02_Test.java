/**
 * @(#)G_DOC_P02_Test.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2012/04/23
 * 
 * Copyright (c) 2012 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.util;

import java.util.List;

import nttdm.bsys.m_alt.bean.FileInfo;
import nttdm.bsys.m_alt.dto.M_ALTR01Input;

/**
 * @author gplai
 *
 */
public class G_DOC_P02_Test extends AbstractUtilTest {

    private G_DOC_P02 gDocP02;
    
    @Override
    protected void setUpData() throws Exception {
        super.deleteAllData("M_ALT_ATC");
        super.deleteAllData("T_DOC");
    }

    /**
     * case1:
     */
    public void testExecute1() {
        Integer idDoc = queryDAO.executeForObject("TEST.SELECT.G_DOC_P02.SQL1", null, Integer.class);
        String[][] M_ALT_ATC = {
                { "ID_MSG", "ID_FILE", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                { "3", String.valueOf(idDoc+1), "0", 
                  TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin" } };
        
        String[][] T_DOC = {
                { "ID_DOC", "DOC_TYPE", "FILENAME", "FILELOCATION", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                { "3", "ALTN", "testing file 001.xlsx", "ddd", "0", 
                  TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin" } };
        
        super.insertData("T_DOC", T_DOC);
        super.insertData("M_ALT_ATC", M_ALT_ATC);
        
        gDocP02 = new G_DOC_P02();
        gDocP02.setQueryDAO(queryDAO);
        M_ALTR01Input input = new M_ALTR01Input();
        input.setId_msg("3");
        
        List<FileInfo> result = gDocP02.getFileName(input, queryDAO);
        assertEquals(1, result.size());
        assertEquals("testing file 001.xlsx", result.get(0).getFilename());
        assertEquals("ddd", result.get(0).getFilelocation());
    }
}
