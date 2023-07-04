/**
 * @(#)CommonUtil_Test_Suit.java
 * 
 * Billing System
 * 
 * Version 1.00

 * Created 2012-5-2

 * Copyright (c) 2012 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.junit.suit;

import nttdm.bsys.common.util.E_EXP_F01_Test;
import nttdm.bsys.common.util.E_EXP_F02_Test;
import nttdm.bsys.common.util.G_ADT_P01_Test;
import nttdm.bsys.common.util.G_ALT_P04_Test;
import nttdm.bsys.common.util.G_ALT_P05_Test;
import nttdm.bsys.common.util.G_ALT_P06_Test;
import nttdm.bsys.common.util.G_BTH_P01_2_Test;
import nttdm.bsys.common.util.G_BTH_P01_Test;
import nttdm.bsys.common.util.G_CDM_P01_Test;
import nttdm.bsys.common.util.G_CIT_P01_Test;
import nttdm.bsys.common.util.G_CLC_P01_Test;
import nttdm.bsys.common.util.G_CMN_P01_Test;
import nttdm.bsys.common.util.G_CMN_P02_Test;
import nttdm.bsys.common.util.G_CPM_P01_Test;
import nttdm.bsys.common.util.G_CPM_P03_Test;
import nttdm.bsys.common.util.G_CPM_P04_Test;
import nttdm.bsys.common.util.G_CSB_P01_Test;
import nttdm.bsys.common.util.G_CSB_P02_Test;
import nttdm.bsys.common.util.G_CUR_P01_Test;
import nttdm.bsys.common.util.G_DOC_P01_Test;
import nttdm.bsys.common.util.G_DOC_P02_Test;
import nttdm.bsys.common.util.G_GIR_P01_Test;
import nttdm.bsys.common.util.G_RPT_P01_Test;
import nttdm.bsys.common.util.G_SET_P01_Test;
import nttdm.bsys.common.util.G_SET_P02_Test;
import nttdm.bsys.common.util.G_SGP_P01_Test;
import nttdm.bsys.common.util.G_SGP_P02_Test;
import nttdm.bsys.common.util.SecurityUtil_Test;
import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Test Suit for nttdm.bsys.common.util
 * 
 * @author bench
 */
public class CommonUtil_Test_Suit {
    public static Test suite() {
        TestSuite suite = new TestSuite("Test suit for Common Util");

        suite.addTestSuite(SecurityUtil_Test.class);
        
        suite.addTestSuite(G_CDM_P01_Test.class);
        suite.addTestSuite(G_CMN_P01_Test.class);
        suite.addTestSuite(G_CMN_P02_Test.class);
        suite.addTestSuite(G_CPM_P01_Test.class);
        suite.addTestSuite(G_CPM_P03_Test.class);
        suite.addTestSuite(G_CPM_P04_Test.class);
        suite.addTestSuite(G_CSB_P01_Test.class);
        suite.addTestSuite(G_CSB_P02_Test.class);
        suite.addTestSuite(G_CUR_P01_Test.class);
        suite.addTestSuite(G_DOC_P01_Test.class);
        suite.addTestSuite(G_DOC_P02_Test.class);
        suite.addTestSuite(G_RPT_P01_Test.class);
        suite.addTestSuite(G_SET_P01_Test.class);
        suite.addTestSuite(G_SET_P02_Test.class);
        
        // 2012-05-03 release
        suite.addTestSuite(G_CLC_P01_Test.class);
        suite.addTestSuite(G_ADT_P01_Test.class);
        suite.addTestSuite(G_ALT_P04_Test.class);
        suite.addTestSuite(G_ALT_P05_Test.class);
        suite.addTestSuite(G_ALT_P06_Test.class);
        
        // 2012-05-11 release
        suite.addTestSuite(E_EXP_F01_Test.class);
        suite.addTestSuite(E_EXP_F02_Test.class);
        
        suite.addTestSuite(G_BTH_P01_Test.class);
        suite.addTestSuite(G_BTH_P01_2_Test.class);
        
        suite.addTestSuite(G_SGP_P01_Test.class);
        suite.addTestSuite(G_SGP_P01_Test.class);
        
        suite.addTestSuite(G_CIT_P01_Test.class);
        suite.addTestSuite(G_GIR_P01_Test.class);
        
        return suite;
    }
}
