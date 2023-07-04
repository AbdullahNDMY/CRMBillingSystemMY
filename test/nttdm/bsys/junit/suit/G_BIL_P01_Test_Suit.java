/**
 * @(#)G_BIL_P01_Suit_Test.java
 * 
 * Billing System
 * 
 * Version 1.00

 * Created 2011-8-19

 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.junit.suit;

import nttdm.bsys.common.util.G_BIL_P01_BI1_Test;
import nttdm.bsys.common.util.G_BIL_P01_BI2_Test;
import nttdm.bsys.common.util.G_BIL_P01_BI3_Test;
import nttdm.bsys.common.util.G_BIL_P01_BI4_Test;
import nttdm.bsys.common.util.G_BIL_P01_BI5A_Test;
import nttdm.bsys.common.util.G_BIL_P01_BI5B_Test;
import nttdm.bsys.common.util.G_BIL_P01_BI6_Test;
import nttdm.bsys.common.util.G_BIL_P01_Com_Test;
import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Test Suit for nttdm.bsys.common.util.G_BIL_P01
 * 
 * @author bench
 */
public class G_BIL_P01_Test_Suit {
	
	public static Test suite() {
		TestSuite suite = new TestSuite("Test suit for G_BIL_P01");
		
		// Test for G_BIL_P01 common
		suite.addTestSuite(G_BIL_P01_Com_Test.class);
		
		// Test for G_BIL_P01 BI=1
		suite.addTestSuite(G_BIL_P01_BI1_Test.class);
		
		// Test for G_BIL_P01 BI=2
		suite.addTestSuite(G_BIL_P01_BI2_Test.class);

		// Test for G_BIL_P01 BI=3
		suite.addTestSuite(G_BIL_P01_BI3_Test.class);

		// Test for G_BIL_P01 BI=4
		suite.addTestSuite(G_BIL_P01_BI4_Test.class);
		
        // Test for Bill After Use
        suite.addTestSuite(G_BIL_P01_BI5A_Test.class);

		// Test for G_BIL_P01 BI=5(Bill In Advance)
		suite.addTestSuite(G_BIL_P01_BI5B_Test.class);

		// Test for G_BIL_P01 BI=6
		suite.addTestSuite(G_BIL_P01_BI6_Test.class);
		
		return suite;
	}
}
