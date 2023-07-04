package nttdm.bsys.junit.suit;

import nttdm.bsys.b_cpm.blogic.B_CPM_S01CheckExportBLogic_Test;
import nttdm.bsys.b_cpm.blogic.B_CPM_S01DownloadBLogic_Test;
import junit.framework.Test;
import junit.framework.TestSuite;

public class B_CPM_Test_Suit {
    public static Test suite() {
        TestSuite suite = new TestSuite("Test suit for B-CPM Screen.");
        
        suite.addTestSuite(B_CPM_S01CheckExportBLogic_Test.class);
        suite.addTestSuite(B_CPM_S01DownloadBLogic_Test.class);
        
        return suite;
    }
}
