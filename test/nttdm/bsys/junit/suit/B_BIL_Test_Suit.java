package nttdm.bsys.junit.suit;

import nttdm.bsys.b_bil.blogic.RP_B_BIL_S01_01BLogic_Test;
import nttdm.bsys.b_bil.blogic.RP_B_BIL_S01_02BLogic_Test;
import nttdm.bsys.b_bil.blogic.RP_B_BIL_S02_01BLogic_Test;
import nttdm.bsys.b_bil.blogic.RP_B_BIL_S02_02_01BLogic_Test;
import nttdm.bsys.b_bil.blogic.RP_B_BIL_S02_02_02BLogic_Test;
import nttdm.bsys.b_bil.blogic.RP_B_BIL_S02_03BLogic_Test;
import nttdm.bsys.b_bil.blogic.RP_B_BIL_S03_01BLogic_Test;
import nttdm.bsys.b_bil.blogic.RP_B_BIL_S03_02BLogic_Test;
import nttdm.bsys.b_bil.blogic.RP_B_BIL_S03_02_01BLogic_Test;
import nttdm.bsys.b_bil.blogic.RP_B_BIL_S03_02_02BLogic_Test;
import nttdm.bsys.b_bil.blogic.RP_B_BIL_S03_02_03BLogic_Test;
import nttdm.bsys.b_bil.blogic.RP_B_BIL_S03_03BLogic_Test;
import nttdm.bsys.b_bil.blogic.RP_B_BIL_S03_03_01BLogic_Test;
import nttdm.bsys.b_bil.blogic.RP_B_BIL_S03_03_02BLogic_Test;
import nttdm.bsys.b_bil.blogic.RP_B_BIL_S03_03_03BLogic_Test;
import nttdm.bsys.b_bil.blogic.RP_B_BIL_S03_04BLogic_Test;
import junit.framework.Test;
import junit.framework.TestSuite;

public class B_BIL_Test_Suit {
    public static Test suite() {
        TestSuite suite = new TestSuite("Test suit for B-BIL Screen.");
        
        suite.addTestSuite(RP_B_BIL_S01_01BLogic_Test.class);
        suite.addTestSuite(RP_B_BIL_S01_02BLogic_Test.class);
        suite.addTestSuite(RP_B_BIL_S02_01BLogic_Test.class);
        suite.addTestSuite(RP_B_BIL_S02_02_01BLogic_Test.class);
        suite.addTestSuite(RP_B_BIL_S02_02_02BLogic_Test.class);
        suite.addTestSuite(RP_B_BIL_S02_03BLogic_Test.class);
        suite.addTestSuite(RP_B_BIL_S03_01BLogic_Test.class);
        suite.addTestSuite(RP_B_BIL_S03_02BLogic_Test.class);
        suite.addTestSuite(RP_B_BIL_S03_02_01BLogic_Test.class);
        suite.addTestSuite(RP_B_BIL_S03_02_02BLogic_Test.class);
        suite.addTestSuite(RP_B_BIL_S03_02_03BLogic_Test.class);
        suite.addTestSuite(RP_B_BIL_S03_03BLogic_Test.class);
        suite.addTestSuite(RP_B_BIL_S03_03_01BLogic_Test.class);
        suite.addTestSuite(RP_B_BIL_S03_03_02BLogic_Test.class);
        suite.addTestSuite(RP_B_BIL_S03_03_03BLogic_Test.class);
        suite.addTestSuite(RP_B_BIL_S03_04BLogic_Test.class);
        
        return suite;
    }
}
