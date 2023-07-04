package nttdm.bsys.junit.suit;

import nttdm.bsys.b_csb.blogic.B_CSBR01BLogic_Test;
import nttdm.bsys.b_csb.blogic.B_CSBS02_ADBLogic_Test;
import nttdm.bsys.b_csb.blogic.B_CSBS02_CheckErrorBLogic_Test;
import nttdm.bsys.b_csb.blogic.B_CSBS02_DBLogic_Test;
import nttdm.bsys.b_csb.blogic.B_CSBS02_ESCRBLogic_Test;
import nttdm.bsys.b_csb.blogic.B_CSBS02_NEBLogic_Test;
import nttdm.bsys.b_csb.blogic.B_CSBS02_SCRBLogic_Test;
import nttdm.bsys.b_csb.blogic.B_CSBS02_VBLogic_Test;
import junit.framework.Test;
import junit.framework.TestSuite;

public class B_CSB_Test_Suit {
    public static Test suite() {
        TestSuite suite = new TestSuite("Test suit for B-CSB Screen.");
        
        suite.addTestSuite(B_CSBR01BLogic_Test.class);
        suite.addTestSuite(B_CSBS02_ADBLogic_Test.class);
        suite.addTestSuite(B_CSBS02_CheckErrorBLogic_Test.class);
        suite.addTestSuite(B_CSBS02_DBLogic_Test.class);
        suite.addTestSuite(B_CSBS02_ESCRBLogic_Test.class);
        suite.addTestSuite(B_CSBS02_NEBLogic_Test.class);
        suite.addTestSuite(B_CSBS02_SCRBLogic_Test.class);
        suite.addTestSuite(B_CSBS02_VBLogic_Test.class);
        
        return suite;
    }
}
