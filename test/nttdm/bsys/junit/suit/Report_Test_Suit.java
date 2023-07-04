package nttdm.bsys.junit.suit;

import nttdm.bsys.r_acr.blogic.R_ACR_R01BLogic_Test;
import nttdm.bsys.r_acr.blogic.R_ACR_R02BLogic_Test;
import nttdm.bsys.r_acr.blogic.R_ACR_R03BLogic_Test;
import nttdm.bsys.r_agr.blogic.R_AGR_R01BLogic_Test;
import nttdm.bsys.r_agr.blogic.R_AGR_R02BLogic_Test;
import nttdm.bsys.r_agr.blogic.R_AGR_R03BLogic_Test;
import nttdm.bsys.r_bac.blogic.R_BAC_R01BLogic_Test;
import nttdm.bsys.r_bac.blogic.R_BAC_R02BLogic_Test;
import nttdm.bsys.r_bac.blogic.R_BAC_R03BLogic_Test;
import nttdm.bsys.r_rrr.blogic.R_RRR_R01BLogic_Test;
import nttdm.bsys.r_rrr.blogic.R_RRR_R02BLogic_Test;
import nttdm.bsys.r_rrr.blogic.R_RRR_R03BLogic_Test;
import nttdm.bsys.r_sal.blogic.R_SAL_R01BLogic_Test;
import nttdm.bsys.r_sal.blogic.R_SAL_R02BLogic_Test;
import nttdm.bsys.r_sal.blogic.R_SAL_R03BLogic_Test;
import nttdm.bsys.r_soa.blogic.R_SOA_S01InitBLogic_Test;
import nttdm.bsys.r_soa.blogic.R_SOA_S01PrintBLogic_Test;
import nttdm.bsys.r_soa.blogic.R_SOA_S01SearchBLogic_Test;
import junit.framework.Test;
import junit.framework.TestSuite;

public class Report_Test_Suit {
    public static Test suite() {
        TestSuite suite = new TestSuite("Test suit for Report Screen.");

        // R_ACR
        suite.addTestSuite(R_ACR_R01BLogic_Test.class);
        suite.addTestSuite(R_ACR_R02BLogic_Test.class);
        suite.addTestSuite(R_ACR_R03BLogic_Test.class);
        
        // R_AGR
        suite.addTestSuite(R_AGR_R01BLogic_Test.class);
        suite.addTestSuite(R_AGR_R02BLogic_Test.class);
        suite.addTestSuite(R_AGR_R03BLogic_Test.class);
        
        // R_RRR
        suite.addTestSuite(R_RRR_R01BLogic_Test.class);
        suite.addTestSuite(R_RRR_R02BLogic_Test.class);
        suite.addTestSuite(R_RRR_R03BLogic_Test.class);
        
        // R_SAL
        suite.addTestSuite(R_SAL_R01BLogic_Test.class);
        suite.addTestSuite(R_SAL_R02BLogic_Test.class);
        suite.addTestSuite(R_SAL_R03BLogic_Test.class);
        
        // R_BAC
        suite.addTestSuite(R_BAC_R01BLogic_Test.class);
        suite.addTestSuite(R_BAC_R02BLogic_Test.class);
        suite.addTestSuite(R_BAC_R03BLogic_Test.class);
        
        // R_SOA
        suite.addTestSuite(R_SOA_S01InitBLogic_Test.class);
        suite.addTestSuite(R_SOA_S01PrintBLogic_Test.class);
        suite.addTestSuite(R_SOA_S01SearchBLogic_Test.class);
        
        return suite;
    }
}
