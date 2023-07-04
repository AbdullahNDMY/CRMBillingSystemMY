package nttdm.bsys.junit.suit;
/**
 * @(#)Batch_Test_Suit.java
 * 
 * Billing System
 * 
 * Version 1.00

 * Created 2012-5-11

 * Copyright (c) 2012 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Batch Test Case Suit
 *  
 * @author bench
 */
public class Batch_Test_Suit {
    public static Test suite() {
        TestSuite suite = new TestSuite("Test suit for Batch Screen.");
        
        // 2012-05-11 release
        suite.addTestSuite(nttdm.bsys.e_dim.blogic.RP_E_DIM_SP1_01BLogic_Test.class);
        suite.addTestSuite(nttdm.bsys.e_dim.blogic.RP_E_DIM_SP1_02BLogic_Test.class);
        suite.addTestSuite(nttdm.bsys.e_dim.blogic.RP_E_DIM_SP1_03BLogic_Test.class);
        
        suite.addTestSuite(nttdm.bsys.e_mex.blogic.RP_E_MEX_CT1BLogic_Test.class);
        suite.addTestSuite(nttdm.bsys.e_mex.blogic.RP_E_MEX_CT1DownloadBLogic_Test.class);
        suite.addTestSuite(nttdm.bsys.e_mex.blogic.RP_E_MEX_CT1SubmitBLogic_Test.class);
        suite.addTestSuite(nttdm.bsys.e_mex.blogic.RP_E_MEX_CT1_2BLogic_Test.class);
        suite.addTestSuite(nttdm.bsys.e_mex.blogic.RP_E_MEX_GR1BLogic_Test.class);
        suite.addTestSuite(nttdm.bsys.e_mex.blogic.RP_E_MEX_GR1DownloadBLogic_Test.class);
        suite.addTestSuite(nttdm.bsys.e_mex.blogic.RP_E_MEX_GR1SubmitBLogic_Test.class);
        suite.addTestSuite(nttdm.bsys.e_mex.blogic.RP_E_MEX_GR1_2BLogic_Test.class);
        suite.addTestSuite(nttdm.bsys.e_mex.blogic.RP_E_MEX_SP1BLogic_Test.class);
        suite.addTestSuite(nttdm.bsys.e_mex.blogic.RP_E_MEX_SP1DownloadBLogic_Test.class);
        suite.addTestSuite(nttdm.bsys.e_mex.blogic.RP_E_MEX_SP1SubmitBLogic_Test.class);
        suite.addTestSuite(nttdm.bsys.e_mex.blogic.RP_E_MEX_SP1_2BLogic_Test.class);
        suite.addTestSuite(nttdm.bsys.e_mex.blogic.SC_E_MEX_CT1SCRBLogic_Test.class);
        suite.addTestSuite(nttdm.bsys.e_mex.blogic.SC_E_MEX_GR1SCRBLogic_Test.class);
        suite.addTestSuite(nttdm.bsys.e_mex.blogic.SC_E_MEX_SP1SCRBLogic_Test.class);

        suite.addTestSuite(nttdm.bsys.e_mim.blogic.RP_E_MIM_US2_01BLogic_Test.class);
        suite.addTestSuite(nttdm.bsys.e_mim.blogic.RP_E_MIM_US2_02BLogic_Test.class);
        suite.addTestSuite(nttdm.bsys.e_mim.blogic.RP_E_MIM_US2_03BLogic_Test.class);
        suite.addTestSuite(nttdm.bsys.e_mim.blogic.RP_E_MIM_US2_DownloadBLogic_Test.class);

        suite.addTestSuite(nttdm.bsys.e_set.blogic.RP_E_SET_S01BLogic_Test.class);
        suite.addTestSuite(nttdm.bsys.e_set.blogic.RP_E_SET_S01SubmitBLogic_Test.class);
        
        return suite;
    }
}
