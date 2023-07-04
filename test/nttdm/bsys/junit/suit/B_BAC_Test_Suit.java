package nttdm.bsys.junit.suit;

import nttdm.bsys.b_bac.blogic.RP_B_BAC_S01_01BLogic_Test;
import nttdm.bsys.b_bac.blogic.RP_B_BAC_S01_02BLogic_Test;
import nttdm.bsys.b_bac.blogic.RP_B_BAC_S02_01_01BLogic_Test;
import nttdm.bsys.b_bac.blogic.RP_B_BAC_S02_01_02BLogic_Test;
import nttdm.bsys.b_bac.blogic.RP_B_BAC_S02_01_03BLogic_Test;
import nttdm.bsys.b_bac.blogic.RP_B_BAC_S02_02_01BLogic_Test;
import nttdm.bsys.b_bac.blogic.RP_B_BAC_S02_03_01BLogic_Test;
import nttdm.bsys.b_bac.blogic.RP_B_BAC_S02_03_02BLogic_Test;
import nttdm.bsys.b_bac.blogic.RP_B_BAC_S02_03_03BLogic_Test;
import nttdm.bsys.b_bac.blogic.RP_B_BAC_S02_04_01BLogic_Test;
import nttdm.bsys.b_bac.blogic.RP_B_BAC_S02_04_02BLogic_Test;
import nttdm.bsys.b_bac.blogic.RP_B_BAC_S02_05_01BLogic_Test;
import junit.framework.Test;
import junit.framework.TestSuite;

public class B_BAC_Test_Suit {
    public static Test suite() {
        TestSuite suite = new TestSuite("Test suit for B-BAC Screen.");
        
        suite.addTestSuite(RP_B_BAC_S01_01BLogic_Test.class);
        suite.addTestSuite(RP_B_BAC_S01_02BLogic_Test.class);
        suite.addTestSuite(RP_B_BAC_S02_01_01BLogic_Test.class);
        suite.addTestSuite(RP_B_BAC_S02_01_02BLogic_Test.class);
        suite.addTestSuite(RP_B_BAC_S02_01_03BLogic_Test.class);
        suite.addTestSuite(RP_B_BAC_S02_02_01BLogic_Test.class);
        suite.addTestSuite(RP_B_BAC_S02_03_01BLogic_Test.class);
        suite.addTestSuite(RP_B_BAC_S02_03_02BLogic_Test.class);
        suite.addTestSuite(RP_B_BAC_S02_03_03BLogic_Test.class);
        suite.addTestSuite(RP_B_BAC_S02_04_01BLogic_Test.class);
        suite.addTestSuite(RP_B_BAC_S02_04_02BLogic_Test.class);
        suite.addTestSuite(RP_B_BAC_S02_05_01BLogic_Test.class);
        
        return suite;
    }
}
