/**
 * @(#)G_CDM_P01_Test.java
 *  
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2011-7-27
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */

package nttdm.bsys.common.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * Test Class for nttdm.bsys.common.util.G_CDM_P01
 * 
 * @author bench
 */
public class G_CDM_P01_Test extends AbstractUtilTest {

	private static final String ID_CODE = "INVNO";
	private static final String INIT_VAL_06 = "IV";
	private static final String CURT_VAL_06 = "IV";
	private static final String INIT_VAL_05 = "000000";
	private static final String MINI_VAL_05 = "000001";
	private static final String CURT_VAL_05 = "000011";
	private static final String NEXT_VAL_05 = "000012";
	private static final String MAX_INI_05 = "00000000000000000000";
	private static final String MAX_VAL_05 = "9999999999999999999";
	private static final String OLD_YYYYMM = "201107";
	private static final String RESET_NO_YEAR = "1";
	private static final String RESET_NO_MONTH = "2";
    private static final String PREVOIS_MONTH = "0";
    private static final String CURRENT_MONTH = "1";
    private static final String SOANO = "SOANO";
    
	/**
	 * YY. System date is 2011/07/27, YY=11
	 */
	private static String TODAY_YY = "";

	/**
	 * YYYY. System date is 2011/07/27, YYYY=2011
	 */
	private static String TODAY_YYYY = "";

	/**
	 * YYYYMMDD. System date is 2011/07/27, YYYY=20110727
	 */
	private static String TODAY_YYYYMMDD = "";
	
    private static String TODAY_YYYYMM = "";
	/**
	 * YYMMDD. System date is 2011/07/27, YYYY=110727
	 */
	private static String TODAY_YYMMDD = "";
	
	/**
     * YYY. System date is 2011/07/27, YYY=211
     */
    private static String TODAY_YYY = "";
    
    /**
     * YYMM. System date is 2011/07/27, YYMM=1107
     */
    private static String TODAY_YYMM = "";

    /**
     * MM. System date is 2011/07/27, MM=07
     */
    private static String TODAY_MM = "";
    

    private static String PREVIOUS_YY = "";
    private static String PREVIOUS_YYYY = "";
    private static String PREVIOUS_YYYYMM = "";
    private static String PREVIOUS_YYYYMMDD = "";
    private static String PREVIOUS_YYMMDD = "";
    private static String PREVIOUS_YYY = "";
    private static String PREVIOUS_YYMM = "";
    private static String PREVIOUS_MM = "";
	/**
	 * G_CDM_P01
	 */
	private G_CDM_P01 gCdmP01 = null;

	/**
	 * Clean up exist data in M_CODE_HISTORY and M_CODE tables.<br>
	 * Get formatted string of current system date.
	 * 
	 * @see jp.terasoluna.utlib.spring.DaoTestCase#setUpData()
	 */
	@Override
	protected void setUpData() throws Exception {
		// delete all exist data in M_CODE and M_CODE_HISTORY
		super.deleteAllData("M_CODE_HISTORY");
		super.deleteAllData("M_CODE");

		// set system date string
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		String day = dateFormat.format(date);
		TODAY_YY = day.substring(2, 4);
		TODAY_YYYY = day.substring(0, 4);
		TODAY_YYYYMM = day.substring(0, 6);
		TODAY_YYYYMMDD = day;
		TODAY_YYMMDD = day.substring(2);
		TODAY_YYY = day.substring(0,1) + day.substring(2,4);
		TODAY_YYMM = day.substring(2,6);
		TODAY_MM = day.substring(4,6);
		
		// previous month
		Calendar c1 = Calendar.getInstance();
        c1.setTime(new Date());
        c1.add(Calendar.MONTH, -1);
        String previous = dateFormat.format(c1.getTime());

        PREVIOUS_YY = previous.substring(2, 4);
        PREVIOUS_YYYY = previous.substring(0, 4);
        PREVIOUS_YYYYMM = previous.substring(0, 6);
        PREVIOUS_YYYYMMDD = previous;
        PREVIOUS_YYMMDD = previous.substring(2);
        PREVIOUS_YYY = previous.substring(0,1) + previous.substring(2,4);
        PREVIOUS_YYMM = previous.substring(2,6);
        PREVIOUS_MM = previous.substring(4,6);
		// initialize G_CDM_P01 object for testing
		this.gCdmP01 = new G_CDM_P01(queryDAO, updateDAO, USER_ID);
	}

	/**
	 * Case 1:
	 * 
	 * Condition: <br>
	 * ID_SUB_CODE 1: TYPE_VAL=06, INIT_VAL=IV; <br>
	 * ID_SUB_CODE 2: TYPE_VAL=01, CUR_VAL=2011; <br>
	 * ID_SUB_CODE 3: TYPE_VAL=05, CUR_VAL=000011;<br>
	 * 
	 * Result: GenerateCode = INIT_VAL_06 + TODAY_YY + NEXT_VAL_05
	 * 
	 * @throws Exception
	 */
	public void testGetGenerateCode01() throws Exception {
		String[][] testDataMCODE = {
				{ "ID_CODE", "ID_SUB_CODE", "TYPE_VAL", "INIT_VAL", "CUR_VAL",
				    "RESET_NO", "IS_DELETED", "ID_LOGIN" },
				{ ID_CODE, "1", "06", INIT_VAL_06, CURT_VAL_06, "0", "0", USER_ID },
				{ ID_CODE, "2", "01", TODAY_YYYY, TODAY_YYYY, "0", "0", USER_ID },
				{ ID_CODE, "3", "05", INIT_VAL_05, CURT_VAL_05, "1", "0", USER_ID } };
		super.insertData("M_CODE", testDataMCODE);

		// assert the return value by the test method
		String expectedValue = INIT_VAL_06 + TODAY_YY + NEXT_VAL_05;
		String realValue = this.gCdmP01.getGenerateCode(ID_CODE, USER_ID);
		assertEquals(expectedValue, realValue);

		// assert the M_CODE table's new value
		@SuppressWarnings("unchecked")
		Map<String, Object>[] data = super.select("M_CODE", "id_code='" + ID_CODE
				+ "' AND id_sub_code='3'", null);
		String curval = (String) data[0].get("CUR_VAL");
		assertEquals(NEXT_VAL_05, curval);
	}

	/**
	 * Case 2:
	 * 
	 * Condition: <br>
	 * ID_SUB_CODE 1: TYPE_VAL=06, INIT_VAL=IV; <br>
	 * ID_SUB_CODE 2: TYPE_VAL=02, CUR_VAL=2011; <br>
	 * ID_SUB_CODE 3: TYPE_VAL=05, CUR_VAL=000011;<br>
	 * 
	 * Result: GenerateCode = INIT_VAL_06 + TODAY_YYYY + NEXT_VAL_05
	 * 
	 * @throws Exception
	 */
	public void testGetGenerateCode02() throws Exception {
		String[][] testDataMCODE = {
				{ "ID_CODE", "ID_SUB_CODE", "TYPE_VAL", "INIT_VAL", "CUR_VAL",
						"RESET_NO", "IS_DELETED", "ID_LOGIN" },
				{ ID_CODE, "1", "06", INIT_VAL_06, CURT_VAL_06, "0", "0", USER_ID },
				{ ID_CODE, "2", "02", TODAY_YYYY, TODAY_YYYY, "0", "0", USER_ID },
				{ ID_CODE, "3", "05", INIT_VAL_05, CURT_VAL_05, "2", "0", USER_ID } };
		super.insertData("M_CODE", testDataMCODE);

		// assert the return value by the test method
		String expectedValue = INIT_VAL_06 + TODAY_YYYY + NEXT_VAL_05;
		String realValue = this.gCdmP01.getGenerateCode(ID_CODE, USER_ID);
		assertEquals(expectedValue, realValue);

		// assert the M_CODE table's new value
		@SuppressWarnings("unchecked")
		Map<String, Object>[] data = super.select("M_CODE", "id_code='" + ID_CODE
				+ "' AND id_sub_code='3'", null);
		String curval = (String) data[0].get("CUR_VAL");
		assertEquals(NEXT_VAL_05, curval);
	}

	/**
	 * Case 3:
	 * 
	 * Condition: <br>
	 * ID_SUB_CODE 1: TYPE_VAL=06, INIT_VAL=IV; <br>
	 * ID_SUB_CODE 2: TYPE_VAL=02, CUR_VAL=2011; <br>
	 * ID_SUB_CODE 3: TYPE_VAL=05, CUR_VAL=000011;<br>
	 * 
	 * Result: GenerateCode = INIT_VAL_06 + TODAY_YYYYMMDD + NEXT_VAL_05
	 * 
	 * @throws Exception
	 */
	public void testGetGenerateCode03() throws Exception {
		String[][] testDataMCODE = {
				{ "ID_CODE", "ID_SUB_CODE", "TYPE_VAL", "INIT_VAL", "CUR_VAL",
						"RESET_NO", "IS_DELETED", "ID_LOGIN" },
				{ ID_CODE, "1", "06", INIT_VAL_06, CURT_VAL_06, "0", "0", USER_ID },
				{ ID_CODE, "2", "03", TODAY_YYYY, TODAY_YYYY, "0", "0", USER_ID },
				{ ID_CODE, "3", "05", INIT_VAL_05, CURT_VAL_05, "0", "0", USER_ID } };
		super.insertData("M_CODE", testDataMCODE);

		// assert the return value by the test method
		String expectedValue = INIT_VAL_06 + TODAY_YYYYMMDD + NEXT_VAL_05;
		String realValue = this.gCdmP01.getGenerateCode(ID_CODE, USER_ID);
		assertEquals(expectedValue, realValue);

		// assert the M_CODE table's new value
		@SuppressWarnings("unchecked")
		Map<String, Object>[] data = super.select("M_CODE", "id_code='" + ID_CODE
				+ "' AND id_sub_code='3'", null);
		String curval = (String) data[0].get("CUR_VAL");
		assertEquals(NEXT_VAL_05, curval);
	}

	/**
	 * Case 4:
	 * 
	 * Condition: <br>
	 * ID_SUB_CODE 1: TYPE_VAL=06, INIT_VAL=IV; <br>
	 * ID_SUB_CODE 2: TYPE_VAL=04, CUR_VAL=YEAR(sysdate); <br>
	 * ID_SUB_CODE 3: TYPE_VAL=05, CUR_VAL=999999, max integer value;<br>
	 * 
	 * Result: GenerateCode = INIT_VAL_06 + TODAY_YYMMDD + "1000000";
	 * 
	 * @throws Exception
	 */
	public void testGetGenerateCode04() throws Exception {
		String[][] testDataMCODE = {
				{ "ID_CODE", "ID_SUB_CODE", "TYPE_VAL", "INIT_VAL", "CUR_VAL",
						"RESET_NO", "IS_DELETED", "ID_LOGIN" },
				{ ID_CODE, "1", "06", INIT_VAL_06, CURT_VAL_06, "0", "0", USER_ID },
				{ ID_CODE, "2", "04", TODAY_YYYY, TODAY_YYYY, "0", "0", USER_ID },
				{ ID_CODE, "3", "05", INIT_VAL_05, "999999", "0", "0", USER_ID } };
		super.insertData("M_CODE", testDataMCODE);

		String expectedValue = INIT_VAL_06 + TODAY_YYMMDD + "1000000";
		String realValue = this.gCdmP01.getGenerateCode(ID_CODE, USER_ID);

		assertEquals(expectedValue, realValue);
	}

	/**
	 * Case 5:
	 * 
	 * Condition: <br>
	 * ID_SUB_CODE 1: TYPE_VAL=06, INIT_VAL=IV; <br>
	 * ID_SUB_CODE 2: TYPE_VAL=04, CUR_VAL=YEAR(sysdate); <br>
	 * ID_SUB_CODE 3: TYPE_VAL=05, CUR_VAL=9999999999999999999, max integer
	 * value for 20 character;<br>
	 * 
	 * Result: Exception because of CUR_VAL's length > 20;
	 * 
	 * @throws Exception
	 */
	public void testGetGenerateCode05() throws Exception {
		String[][] testDataMCODE = {
				{ "ID_CODE", "ID_SUB_CODE", "TYPE_VAL", "INIT_VAL", "CUR_VAL",
						"RESET_NO", "IS_DELETED", "ID_LOGIN" },
				{ ID_CODE, "1", "06", INIT_VAL_06, CURT_VAL_06, "0", "0", USER_ID },
				{ ID_CODE, "2", "04", TODAY_YYYY, TODAY_YYYY, "0", "0", USER_ID },
				{ ID_CODE, "3", "05", MAX_INI_05, MAX_VAL_05, "0", "0", USER_ID } };
		super.insertData("M_CODE", testDataMCODE);

		try {
			this.gCdmP01.getGenerateCode(ID_CODE, USER_ID);
		} catch (Exception e) {
			assertTrue("Exception happened", true);
		}
	}

	/**
	 * Case 6:
	 * 
	 * Condition: <br>
	 * There is no record of ID_CODE in M_CODE table
	 * 
	 * Result: GenerateCode = "";
	 * 
	 * @throws Exception
	 */
	public void testGetGenerateCode06() throws Exception {
		String expectedValue = "";
		String realValue = this.gCdmP01.getGenerateCode(ID_CODE, USER_ID);

		assertEquals(expectedValue, realValue);
	}

	/**
	 * Case 7:
	 * 
	 * Condition: <br>
	 * ID_SUB_CODE 1: TYPE_VAL=06, INIT_VAL=IV;<br>
	 * ID_SUB_CODE 2: TYPE_VAL=03, CUR_VAL=YEAR(sysdate);<br>
	 * ID_SUB_CODE 3: TYPE_VAL=05, CUR_VAL="0"<br>
	 * 
	 * Result: GenerateCode = INIT_VAL_06 + TODAY_YYYYMMDD + "1"
	 * 
	 * @throws Exception
	 */
	public void testGetGenerateCode07() throws Exception {
		String[][] testDataMCODE = {
				{ "ID_CODE", "ID_SUB_CODE", "TYPE_VAL", "INIT_VAL", "CUR_VAL",
						"RESET_NO", "IS_DELETED", "ID_LOGIN" },
				{ ID_CODE, "1", "06", INIT_VAL_06, CURT_VAL_06, "0", "0", USER_ID },
				{ ID_CODE, "2", "03", TODAY_YYYY, TODAY_YYYY, "0", "0", USER_ID },
				{ ID_CODE, "3", "05", "0", "0", "0", "0", USER_ID } };
		super.insertData("M_CODE", testDataMCODE);

		String expectedValue = INIT_VAL_06 + TODAY_YYYYMMDD + "1";
		String realValue = this.gCdmP01.getGenerateCode(ID_CODE, USER_ID);

		assertEquals(expectedValue, realValue);
	}

	/**
	 * Case 08:
	 * 
	 * Condition: <br>
	 * ID_SUB_CODE 1: TYPE_VAL=06, INIT_VAL=IV; <br>
	 * ID_SUB_CODE 2: TYPE_VAL=02, CUR_VAL=2001, not equals to YEAR(sysdate); <br>
	 * ID_SUB_CODE 3: TYPE_VAL=05, CUR_VAL=000011;<br>
	 * 
	 * Result: GenerateCode = INIT_VAL_06 + TODAY_YYY + "000001"
	 * 
	 * @throws Exception
	 */
	public void testGetGenerateCode08() throws Exception {
		String[][] testDataMCODE = {
				{ "ID_CODE", "ID_SUB_CODE", "TYPE_VAL", "INIT_VAL", "CUR_VAL",
						"RESET_NO", "IS_DELETED", "ID_LOGIN" },
				{ ID_CODE, "1", "06", INIT_VAL_06, CURT_VAL_06, "0", "0", USER_ID },
				{ ID_CODE, "2", "02", OLD_YYYYMM, OLD_YYYYMM, "0", "0", USER_ID },
				{ ID_CODE, "3", "05", INIT_VAL_05, CURT_VAL_05, "1", "0", USER_ID } };
		super.insertData("M_CODE", testDataMCODE);

		// assert the return value by the test method
		String expectedValue = INIT_VAL_06 + TODAY_YYYY + "000001";
		String realValue = this.gCdmP01.getGenerateCode(ID_CODE, USER_ID);
		assertEquals(expectedValue, realValue);

		// assert the M_CODE table's new value
		@SuppressWarnings("unchecked")
		Map<String, Object>[] data = super.select("M_CODE", "id_code='" + ID_CODE + "'",
				"ID_SUB_CODE ASC");
		String curval = (String) data[1].get("CUR_VAL");
		String inival = (String) data[1].get("INIT_VAL");
		assertEquals(TODAY_YYYYMM, curval);
		assertEquals(OLD_YYYYMM, inival);

		curval = (String) data[2].get("CUR_VAL");
		inival = (String) data[2].get("INIT_VAL");
		assertEquals(MINI_VAL_05, curval);
		assertEquals(INIT_VAL_05, inival);

		// assert the M_CODE_HISTORY table's new value
		@SuppressWarnings("unchecked")
		Map<String, Object>[] historyData = super.select("M_CODE_HISTORY", "id_code='"
				+ ID_CODE + "'", "ID_SUB_CODE ASC");
		// TYPE_VAL = 6
		curval = (String) historyData[0].get("CUR_VAL");
		inival = (String) historyData[0].get("INIT_VAL");
		assertEquals(CURT_VAL_06, curval);
		assertEquals(INIT_VAL_06, inival);

		// TYPE_VAL = {1,2,3,4}
		curval = (String) historyData[1].get("CUR_VAL");
		inival = (String) historyData[1].get("INIT_VAL");
		assertEquals(OLD_YYYYMM, curval);
		assertEquals(OLD_YYYYMM, inival);

		// TYPE_VAL = 5
		curval = (String) historyData[2].get("CUR_VAL");
		inival = (String) historyData[2].get("INIT_VAL");
		assertEquals(CURT_VAL_05, curval);
		assertEquals(INIT_VAL_05, inival);
	}
	
	 /**
     * Case 9:
     * 
     * Condition: <br>
     * ID_SUB_CODE 1: TYPE_VAL=06, INIT_VAL=IV; <br>
     * ID_SUB_CODE 2: TYPE_VAL=07, CUR_VAL=211; <br>
     * ID_SUB_CODE 3: TYPE_VAL=05, CUR_VAL=000011;<br>
     * 
     * Result: GenerateCode = INIT_VAL_06 + TODAY_YYY + NEXT_VAL_05
     * 
     * @throws Exception
     */
    public void testGetGenerateCode09() throws Exception {
        String[][] testDataMCODE = {
                { "ID_CODE", "ID_SUB_CODE", "TYPE_VAL", "INIT_VAL", "CUR_VAL",
                        "RESET_NO", "IS_DELETED", "ID_LOGIN" },
                { ID_CODE, "1", "06", INIT_VAL_06, CURT_VAL_06, "0", "0", USER_ID },
                { ID_CODE, "2", "07", TODAY_YYYY, TODAY_YYYY, "0", "0", USER_ID },
                { ID_CODE, "3", "05", INIT_VAL_05, CURT_VAL_05, "0", "0", USER_ID } };
        super.insertData("M_CODE", testDataMCODE);

        // assert the return value by the test method
        String expectedValue = INIT_VAL_06 + TODAY_YYY + NEXT_VAL_05;
        String realValue = this.gCdmP01.getGenerateCode(ID_CODE, USER_ID);
        assertEquals(expectedValue, realValue);

        // assert the M_CODE table's new value
        @SuppressWarnings("unchecked")
        Map<String, Object>[] data = super.select("M_CODE", "id_code='" + ID_CODE
                + "' AND id_sub_code='3'", null);
        String curval = (String) data[0].get("CUR_VAL");
        assertEquals(NEXT_VAL_05, curval);
    }

    /**
     * Case 10:
     * 
     * Condition: <br>
     * ID_SUB_CODE 1: TYPE_VAL=06, INIT_VAL=IV; <br>
     * ID_SUB_CODE 2: TYPE_VAL=08, CUR_VAL=1107; <br>
     * ID_SUB_CODE 3: TYPE_VAL=05, CUR_VAL=000011;<br>
     * 
     * Result: GenerateCode = INIT_VAL_06 + TODAY_YYMM + NEXT_VAL_05
     * 
     * @throws Exception
     */
    public void testGetGenerateCode10() throws Exception {
        String[][] testDataMCODE = {
                { "ID_CODE", "ID_SUB_CODE", "TYPE_VAL", "INIT_VAL", "CUR_VAL",
                        "RESET_NO", "IS_DELETED", "ID_LOGIN" },
                { ID_CODE, "1", "06", INIT_VAL_06, CURT_VAL_06, "0", "0", USER_ID },
                { ID_CODE, "2", "08", TODAY_YYYY, TODAY_YYYY, "0", "0", USER_ID },
                { ID_CODE, "3", "05", INIT_VAL_05, CURT_VAL_05, "0", "0", USER_ID } };
        super.insertData("M_CODE", testDataMCODE);

        // assert the return value by the test method
        String expectedValue = INIT_VAL_06 + TODAY_YYMM + NEXT_VAL_05;
        String realValue = this.gCdmP01.getGenerateCode(ID_CODE, USER_ID);
        assertEquals(expectedValue, realValue);

        // assert the M_CODE table's new value
        @SuppressWarnings("unchecked")
        Map<String, Object>[] data = super.select("M_CODE", "id_code='" + ID_CODE
                + "' AND id_sub_code='3'", null);
        String curval = (String) data[0].get("CUR_VAL");
        assertEquals(NEXT_VAL_05, curval);
    }
    
    /**
     * Case 11:
     * 
     * Condition: <br>
     * ID_SUB_CODE 1: TYPE_VAL=06, INIT_VAL=IV; <br>
     * ID_SUB_CODE 2: TYPE_VAL=09, CUR_VAL=07; <br>
     * ID_SUB_CODE 3: TYPE_VAL=05, CUR_VAL=000011;<br>
     * 
     * Result: GenerateCode = INIT_VAL_06 + TODAY_YYMM + NEXT_VAL_05
     * 
     * @throws Exception
     */
    public void testGetGenerateCode11() throws Exception {
        String[][] testDataMCODE = {
                { "ID_CODE", "ID_SUB_CODE", "TYPE_VAL", "INIT_VAL", "CUR_VAL",
                        "RESET_NO", "IS_DELETED", "ID_LOGIN" },
                { ID_CODE, "1", "06", INIT_VAL_06, CURT_VAL_06, "0", "0", USER_ID },
                { ID_CODE, "2", "09", TODAY_YYYY, TODAY_YYYY, "0", "0", USER_ID },
                { ID_CODE, "3", "05", INIT_VAL_05, CURT_VAL_05, "0", "0", USER_ID } };
        super.insertData("M_CODE", testDataMCODE);

        // assert the return value by the test method
        String expectedValue = INIT_VAL_06 + TODAY_MM + NEXT_VAL_05;
        String realValue = this.gCdmP01.getGenerateCode(ID_CODE, USER_ID);
        assertEquals(expectedValue, realValue);

        // assert the M_CODE table's new value
        @SuppressWarnings("unchecked")
        Map<String, Object>[] data = super.select("M_CODE", "id_code='" + ID_CODE
                + "' AND id_sub_code='3'", null);
        String curval = (String) data[0].get("CUR_VAL");
        assertEquals(NEXT_VAL_05, curval);
    }    
    /**
     * Case 12: SOANO + CURRENT_MONTH
     * 
     * Condition: <br>
     * ID_SUB_CODE 1: TYPE_VAL=06, INIT_VAL=IV; <br>
     * ID_SUB_CODE 2: TYPE_VAL=09, CUR_VAL=07; <br>
     * ID_SUB_CODE 3: TYPE_VAL=05, CUR_VAL=000011;<br>
     * 
     * Result: GenerateCode = INIT_VAL_06 + TODAY_YYMM + NEXT_VAL_05
     * 
     * @throws Exception
     */
    public void testGetGenerateCode12() throws Exception {
        String[][] testDataMCODE = {
                { "ID_CODE", "ID_SUB_CODE", "TYPE_VAL", "INIT_VAL", "CUR_VAL",
                        "RESET_NO", "IS_DELETED", "ID_LOGIN" },
                { SOANO, "1", "06", INIT_VAL_06, CURT_VAL_06, "0", "0", USER_ID },
                { SOANO, "2", "09", TODAY_YYYYMM, TODAY_YYYYMM, "0", "0", USER_ID },
                { SOANO, "3", "05", INIT_VAL_05, CURT_VAL_05, "2", "0", USER_ID } };
        super.insertData("M_CODE", testDataMCODE);

        // assert the return value by the test method
        String expectedValue = INIT_VAL_06 + TODAY_MM + NEXT_VAL_05;
        this.gCdmP01.setExecuteMonth(CURRENT_MONTH);
        String realValue = this.gCdmP01.getGenerateCode(SOANO, USER_ID);
        assertEquals(expectedValue, realValue);

        // assert the M_CODE table's new value
        @SuppressWarnings("unchecked")
        Map<String, Object>[] data = super.select("M_CODE", "id_code='" + SOANO
                + "' AND id_sub_code='3'", null);
        String curval = (String) data[0].get("CUR_VAL");
        assertEquals(NEXT_VAL_05, curval);
    } 

    /**
     * Case 13: SOANO + PREVIOUS_MONTH
     * 
     * Condition: <br>
     * ID_SUB_CODE 1: TYPE_VAL=06, INIT_VAL=IV; <br>
     * ID_SUB_CODE 2: TYPE_VAL=09, CUR_VAL=07; <br>
     * ID_SUB_CODE 3: TYPE_VAL=05, CUR_VAL=000011;<br>
     * 
     * Result: GenerateCode = INIT_VAL_06 + TODAY_YYMM + NEXT_VAL_05
     * 
     * @throws Exception
     */
    public void testGetGenerateCode13() throws Exception {
        String[][] testDataMCODE = {
                { "ID_CODE", "ID_SUB_CODE", "TYPE_VAL", "INIT_VAL", "CUR_VAL",
                        "RESET_NO", "IS_DELETED", "ID_LOGIN" },
                { SOANO, "1", "06", INIT_VAL_06, CURT_VAL_06, "0", "0", USER_ID },
                { SOANO, "2", "09", TODAY_YYYYMM, TODAY_YYYYMM, "0", "0", USER_ID },
                { SOANO, "3", "05", "000099", CURT_VAL_05, "2", "0", USER_ID } };
        super.insertData("M_CODE", testDataMCODE);

        // assert the return value by the test method
        String expectedValue = INIT_VAL_06 + PREVIOUS_MM + "000100";
        this.gCdmP01.setExecuteMonth(PREVOIS_MONTH);
        String realValue = this.gCdmP01.getGenerateCode(SOANO, USER_ID);
        assertEquals(expectedValue, realValue);

        // assert the M_CODE table's new value
        @SuppressWarnings("unchecked")
        Map<String, Object>[] data = super.select("M_CODE", "id_code='" + SOANO
                + "' AND id_sub_code='3'", null);
        // Current value no change
        String curval = (String) data[0].get("CUR_VAL");
        assertEquals(CURT_VAL_05, curval);

        // Init value increased
        String initval = (String) data[0].get("INIT_VAL");
        assertEquals("000100", initval);
    } 
    
    /**
     * Case 14: SOANO + PREVIOUS_MONTH + CHANGE MONTH
     * 
     * Condition: <br>
     * ID_SUB_CODE 1: TYPE_VAL=06, INIT_VAL=IV; <br>
     * ID_SUB_CODE 2: TYPE_VAL=09, CUR_VAL=07; <br>
     * ID_SUB_CODE 3: TYPE_VAL=05, CUR_VAL=000011;<br>
     * 
     * Result: GenerateCode = INIT_VAL_06 + TODAY_YYMM + NEXT_VAL_05
     * 
     * @throws Exception
     */
    public void testGetGenerateCode14() throws Exception {
        String[][] testDataMCODE = {
                { "ID_CODE", "ID_SUB_CODE", "TYPE_VAL", "INIT_VAL", "CUR_VAL",
                        "RESET_NO", "IS_DELETED", "ID_LOGIN" },
                { SOANO, "1", "06", INIT_VAL_06, CURT_VAL_06, "0", "0", USER_ID },
                { SOANO, "2", "09", PREVIOUS_YYYYMM, PREVIOUS_YYYYMM, "0", "0", USER_ID },
                { SOANO, "3", "05", INIT_VAL_05, CURT_VAL_05, "2", "0", USER_ID } };
        super.insertData("M_CODE", testDataMCODE);

        // assert the return value by the test method
        String expectedValue = INIT_VAL_06 + PREVIOUS_MM + NEXT_VAL_05;
        this.gCdmP01.setExecuteMonth(PREVOIS_MONTH);
        String realValue = this.gCdmP01.getGenerateCode(SOANO, USER_ID);
        assertEquals(expectedValue, realValue);

        // assert the M_CODE table's new value
        @SuppressWarnings("unchecked")
        Map<String, Object>[] data = super.select("M_CODE", "id_code='" + SOANO
                + "' AND id_sub_code='3'", null);
        // Current value no change
        String curval = (String) data[0].get("CUR_VAL");
        assertEquals(INIT_VAL_05, curval);

        // Init value increased
        String initval = (String) data[0].get("INIT_VAL");
        assertEquals(NEXT_VAL_05, initval);
    } 
    
    /**
     * Case 15: SOANO + PREVIOUS_MONTH + CHANGE YEAR
     * 
     * Condition: <br>
     * ID_SUB_CODE 1: TYPE_VAL=06, INIT_VAL=IV; <br>
     * ID_SUB_CODE 2: TYPE_VAL=09, CUR_VAL=07; <br>
     * ID_SUB_CODE 3: TYPE_VAL=05, CUR_VAL=000011;<br>
     * 
     * Result: GenerateCode = INIT_VAL_06 + TODAY_YYMM + NEXT_VAL_05
     * 
     * @throws Exception
     */
    public void testGetGenerateCode15() throws Exception {
        String[][] testDataMCODE = {
                { "ID_CODE", "ID_SUB_CODE", "TYPE_VAL", "INIT_VAL", "CUR_VAL",
                        "RESET_NO", "IS_DELETED", "ID_LOGIN" },
                { SOANO, "1", "06", INIT_VAL_06, CURT_VAL_06, "0", "0", USER_ID },
                { SOANO, "2", "07", OLD_YYYYMM, OLD_YYYYMM, "0", "0", USER_ID },
                { SOANO, "3", "05", INIT_VAL_05, CURT_VAL_05, "1", "0", USER_ID } };
        super.insertData("M_CODE", testDataMCODE);

        // assert the return value by the test method
        String expectedValue = INIT_VAL_06 + TODAY_YYY + MINI_VAL_05;
        this.gCdmP01.setExecuteMonth(PREVOIS_MONTH);
        String realValue = this.gCdmP01.getGenerateCode(SOANO, USER_ID);
        assertEquals(expectedValue, realValue);

        // assert the M_CODE table's new value
        @SuppressWarnings("unchecked")
        Map<String, Object>[] data = super.select("M_CODE", "id_code='" + SOANO
                + "' AND id_sub_code='3'", null);
        // Current value no change
        String curval = (String) data[0].get("CUR_VAL");
        assertEquals(MINI_VAL_05, curval);

        // Init value increased
        String initval = (String) data[0].get("INIT_VAL");
        assertEquals(INIT_VAL_05, initval);
    } 
}
