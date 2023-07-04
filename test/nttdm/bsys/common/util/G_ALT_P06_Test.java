package nttdm.bsys.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.g_alt.AlertUserDto;
import nttdm.bsys.g_alt.G_ALT_P06Input;

public class G_ALT_P06_Test extends AbstractUtilTest{

	private G_ALT_P06 G_ALT_P06 = null;
	G_ALT_P06Input input = null;
	BillingSystemUserValueObject uvo = null;
	
	@Override
	protected void setUpData() throws Exception {
		G_ALT_P06 = new G_ALT_P06(queryDAO, updateDAO);
		input = new G_ALT_P06Input();
		uvo =  new BillingSystemUserValueObject();
		uvo.setId_user("xiaoJTest");
		this.deleteAllData("M_BATCH_USER_ALERT");
		this.deleteAllData("M_BATCH_MAINTENANCE");
		this.deleteAllData("M_ALT_H");
		this.deleteAllData("M_ALT_D");
		this.deleteAllData("M_USER_ACCESS");
		this.deleteAllData("M_USER");
	}
    
	/**
	 * userAlert is java.util.HashMap
	 */
	public void testExecute1(){

		String[][] mBatch = {
				{ "BATCH_ID", "ALERT_MODE", "FREQUENCY_MODE", "NO_OF_MONTH",
						"DAY_OF_MONTH", "TIME_HOUR", "TIME_MINUTE",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"IS_DELETED", "ID_AUDIT" },
				{ "CC", "1", "Monthly", null, null, null, null,
						TEST_DAY_TODAY, TEST_DAY_TODAY, USER_ID, "0", null } };
		super.insertData("M_BATCH_MAINTENANCE", mBatch);

		// set batch alter user into M_BATCH_USER_ALERT
		String[][] mAlterUser = {
				{ "BATCH_USER_ID", "BATCH_ID", "ALERT_USER", "USER_TYPE",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"IS_DELETED", "ID_AUDIT" },
				{ "9999", "CC", SYS_ADMIN, "USER", TEST_DAY_TODAY,
						TEST_DAY_TODAY, USER_ID, "0", null } };
		super.insertData("M_BATCH_USER_ALERT", mAlterUser);

		List<Map<String, Object>> alertList = new ArrayList<Map<String,Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ALERT_USER", "sysadmin");
		map.put("NO_OF_MONTH", "2");
		alertList.add(map);
		input.setListAlertUser(alertList);
		input.setBatchId("CC");
		input.setMsg("msg");
		input.setSubject("subject");
		G_ALT_P06.execute(input,uvo);
		
		HashMap<String, Object> m_alt_h = (HashMap<String,Object>)G_ALT_P06.getQueryDAO().executeForMap("TEST.G_ALT_P06.SELECT.SQL001", null);
		assertEquals("xiaoJTest", m_alt_h.get("ID_LOGIN"));
		assertEquals("NOTICE", m_alt_h.get("MSG_TYPE"));
		assertEquals("1", m_alt_h.get("STATUS"));
		assertEquals("0", m_alt_h.get("REPEAT_MODE"));
		assertEquals("sysadmin", m_alt_h.get("TO_MSG"));
		assertEquals("subject", m_alt_h.get("SUBJECT"));
		assertEquals("msg", m_alt_h.get("MSG_SRING"));
		
		HashMap<String, Object> m_alt_D = (HashMap<String,Object>)G_ALT_P06.getQueryDAO().executeForMap("TEST.G_ALT_P06.SELECT.SQL002", null);
		assertEquals("sysadmin", m_alt_D.get("ID_USER"));
		assertEquals("1", m_alt_D.get("IS_NEW"));
		assertEquals("xiaoJTest", m_alt_D.get("ID_LOGIN"));
	}
	
	/**
	 * userAlert is AlertUserDto
	 */
	public void testExecute2(){

		String[][] mBatch = {
				{ "BATCH_ID", "ALERT_MODE", "FREQUENCY_MODE", "NO_OF_MONTH",
						"DAY_OF_MONTH", "TIME_HOUR", "TIME_MINUTE",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"IS_DELETED", "ID_AUDIT" },
				{ "CC", "1", "Monthly", null, null, null, null,
						TEST_DAY_TODAY, TEST_DAY_TODAY, USER_ID, "0", null } };
		super.insertData("M_BATCH_MAINTENANCE", mBatch);

		// set batch alter user into M_BATCH_USER_ALERT
		String[][] mAlterUser = {
				{ "BATCH_USER_ID", "BATCH_ID", "ALERT_USER", "USER_TYPE",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"IS_DELETED", "ID_AUDIT" },
				{ "9999", "CC", SYS_ADMIN, "USER", TEST_DAY_TODAY,
						TEST_DAY_TODAY, USER_ID, "0", null } };
		super.insertData("M_BATCH_USER_ALERT", mAlterUser);
	    List<AlertUserDto> alertList = new ArrayList<AlertUserDto>();
		AlertUserDto dto = new AlertUserDto();
		dto.setAlertUser("sysadmin");
		alertList.add(dto);
		input.setListAlertUser(alertList);
		input.setBatchId("CC");
		input.setMsg("msg");
		input.setSubject("subject");
		G_ALT_P06.execute(input,uvo);
		
		HashMap<String, Object> m_alt_h = (HashMap<String,Object>)G_ALT_P06.getQueryDAO().executeForMap("TEST.G_ALT_P06.SELECT.SQL001", null);
		assertEquals("xiaoJTest", m_alt_h.get("ID_LOGIN"));
		assertEquals("NOTICE", m_alt_h.get("MSG_TYPE"));
		assertEquals("1", m_alt_h.get("STATUS"));
		assertEquals("0", m_alt_h.get("REPEAT_MODE"));
		assertEquals("sysadmin", m_alt_h.get("TO_MSG"));
		assertEquals("subject", m_alt_h.get("SUBJECT"));
		assertEquals("msg", m_alt_h.get("MSG_SRING"));
		
		HashMap<String, Object> m_alt_D = (HashMap<String,Object>)G_ALT_P06.getQueryDAO().executeForMap("TEST.G_ALT_P06.SELECT.SQL002", null);
		assertEquals("sysadmin", m_alt_D.get("ID_USER"));
		assertEquals("1", m_alt_D.get("IS_NEW"));
		assertEquals("xiaoJTest", m_alt_D.get("ID_LOGIN"));

	}
	
	/**
	 * userAlert is HashMap and hava the recode with no ALERT_USER
	 */
	public void testExecute3(){

		String[][] mBatch = {
				{ "BATCH_ID", "ALERT_MODE", "FREQUENCY_MODE", "NO_OF_MONTH",
						"DAY_OF_MONTH", "TIME_HOUR", "TIME_MINUTE",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"IS_DELETED", "ID_AUDIT" },
				{ "CC", "1", "Monthly", null, null, null, null,
						TEST_DAY_TODAY, TEST_DAY_TODAY, USER_ID, "0", null } };
		super.insertData("M_BATCH_MAINTENANCE", mBatch);

		// set batch alter user into M_BATCH_USER_ALERT
		String[][] mAlterUser = {
				{ "BATCH_USER_ID", "BATCH_ID", "ALERT_USER", "USER_TYPE",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"IS_DELETED", "ID_AUDIT" },
				{ "9999", "CC", SYS_ADMIN, "USER", TEST_DAY_TODAY,
						TEST_DAY_TODAY, USER_ID, "0", null } };
		super.insertData("M_BATCH_USER_ALERT", mAlterUser);
		
		List<Map<String, Object>> alertList = new ArrayList<Map<String,Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ALERT_USER", null);
		map.put("NO_OF_MONTH", "2");
		alertList.add(map);
		
		map = new HashMap<String, Object>();
		map.put("ALERT_USER", "sysadmin");
		map.put("NO_OF_MONTH", "2");
		alertList.add(map);
		
		input.setListAlertUser(alertList);
		input.setBatchId("CC");
		input.setMsg("msg");
		input.setSubject("subject");
		G_ALT_P06.execute(input,uvo);
		
		HashMap<String, Object> m_alt_h = (HashMap<String,Object>)G_ALT_P06.getQueryDAO().executeForMap("TEST.G_ALT_P06.SELECT.SQL001", null);
		assertEquals("xiaoJTest", m_alt_h.get("ID_LOGIN"));
		assertEquals("NOTICE", m_alt_h.get("MSG_TYPE"));
		assertEquals("1", m_alt_h.get("STATUS"));
		assertEquals("0", m_alt_h.get("REPEAT_MODE"));
		assertEquals("sysadmin", m_alt_h.get("TO_MSG"));
		assertEquals("subject", m_alt_h.get("SUBJECT"));
		assertEquals("msg", m_alt_h.get("MSG_SRING"));
		
		HashMap<String, Object> m_alt_D = (HashMap<String,Object>)G_ALT_P06.getQueryDAO().executeForMap("TEST.G_ALT_P06.SELECT.SQL002", null);
		assertEquals("sysadmin", m_alt_D.get("ID_USER"));
		assertEquals("1", m_alt_D.get("IS_NEW"));
		assertEquals("xiaoJTest", m_alt_D.get("ID_LOGIN"));

	}
	
	/**
	 * userAlert is HashMap and hava the recode with no ALERT_USER
	 * userType == DEPARTMENT
	 */
	public void testExecute4(){

		String[][] mBatch = {
				{ "BATCH_ID", "ALERT_MODE", "FREQUENCY_MODE", "NO_OF_MONTH",
						"DAY_OF_MONTH", "TIME_HOUR", "TIME_MINUTE",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"IS_DELETED", "ID_AUDIT" },
				{ "CC", "1", "Monthly", null, null, null, null,
						TEST_DAY_TODAY, TEST_DAY_TODAY, USER_ID, "0", null } };
		super.insertData("M_BATCH_MAINTENANCE", mBatch);

		// set batch alter user into M_BATCH_USER_ALERT
		String[][] mAlterUser = {
				{ "BATCH_USER_ID", "BATCH_ID", "ALERT_USER", "USER_TYPE",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"IS_DELETED", "ID_AUDIT" },
				{ "9999", "CC", "1111", "DEPARTMENT", TEST_DAY_TODAY,
						TEST_DAY_TODAY, USER_ID, "0", null } };
		super.insertData("M_BATCH_USER_ALERT", mAlterUser);
		
        String[][] dataM_USER = {
                { "ID_USER", "USER_NAME", "ID_DIV", "ID_DEPT", "ID_ROLE",
                        "TEL_NO", "TEL_EXTNO", "DID_NO", "OFS_MOBILE_NO",
                        "EMAIL", "USER_STATUS", "PHOTO", "PASSWORD",
                        "LAST_PWD_CHANGE", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "PPLSOFT_ACC_ID",
                        "ID_AUDIT" },
                { "styap", "Yap", "0001", "1111", "0000000001", "", "", "", "", "", "1", null,
                        "default", TEST_DAY_TODAY, "0", TEST_DAY_TODAY,
                        TEST_DAY_TODAY, "sysadmin", "", null } };
        super.insertData("M_USER", dataM_USER);
        
		List<Map<String, Object>> alertList = new ArrayList<Map<String,Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ALERT_USER", null);
		map.put("NO_OF_MONTH", "2");
		alertList.add(map);
		
		map = new HashMap<String, Object>();
		map.put("ALERT_USER", "1111");
		map.put("NO_OF_MONTH", "2");
		alertList.add(map);
		
		input.setListAlertUser(alertList);
		input.setBatchId("CC");
		input.setMsg("msg");
		input.setSubject("subject");
		G_ALT_P06.execute(input,uvo);
		
		HashMap<String, Object> m_alt_h = (HashMap<String,Object>)G_ALT_P06.getQueryDAO().executeForMap("TEST.G_ALT_P06.SELECT.SQL001", null);
		assertEquals("xiaoJTest", m_alt_h.get("ID_LOGIN"));
		assertEquals("NOTICE", m_alt_h.get("MSG_TYPE"));
		assertEquals("1", m_alt_h.get("STATUS"));
		assertEquals("0", m_alt_h.get("REPEAT_MODE"));
		assertEquals("styap", m_alt_h.get("TO_MSG"));
		assertEquals("subject", m_alt_h.get("SUBJECT"));
		assertEquals("msg", m_alt_h.get("MSG_SRING"));
		
		HashMap<String, Object> m_alt_D = (HashMap<String,Object>)G_ALT_P06.getQueryDAO().executeForMap("TEST.G_ALT_P06.SELECT.SQL002", null);
		assertEquals("styap", m_alt_D.get("ID_USER"));
		assertEquals("1", m_alt_D.get("IS_NEW"));
		assertEquals("xiaoJTest", m_alt_D.get("ID_LOGIN"));

	}
	
	/**
	 * userAlert is HashMap and hava the recode with no ALERT_USER
	 * userType == ROLE
	 */
	public void testExecute5(){

		String[][] mBatch = {
				{ "BATCH_ID", "ALERT_MODE", "FREQUENCY_MODE", "NO_OF_MONTH",
						"DAY_OF_MONTH", "TIME_HOUR", "TIME_MINUTE",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"IS_DELETED", "ID_AUDIT" },
				{ "CC", "1", "Monthly", null, null, null, null,
						TEST_DAY_TODAY, TEST_DAY_TODAY, USER_ID, "0", null } };
		super.insertData("M_BATCH_MAINTENANCE", mBatch);

		// set batch alter user into M_BATCH_USER_ALERT
		String[][] mAlterUser = {
				{ "BATCH_USER_ID", "BATCH_ID", "ALERT_USER", "USER_TYPE",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"IS_DELETED", "ID_AUDIT" },
				{ "9999", "CC", "1111", "ROLE", TEST_DAY_TODAY,
						TEST_DAY_TODAY, USER_ID, "0", null } };
		super.insertData("M_BATCH_USER_ALERT", mAlterUser);
		
        String[][] dataM_USER = {
                { "ID_USER", "USER_NAME", "ID_DIV", "ID_DEPT", "ID_ROLE",
                        "TEL_NO", "TEL_EXTNO", "DID_NO", "OFS_MOBILE_NO",
                        "EMAIL", "USER_STATUS", "PHOTO", "PASSWORD",
                        "LAST_PWD_CHANGE", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "PPLSOFT_ACC_ID",
                        "ID_AUDIT" },
                { "styap", "Yap", "0001", "123", "1111", "", "", "", "", "", "1", null,
                        "default", TEST_DAY_TODAY, "0", TEST_DAY_TODAY,
                        TEST_DAY_TODAY, "sysadmin", "", null } };
        super.insertData("M_USER", dataM_USER);
        
		List<Map<String, Object>> alertList = new ArrayList<Map<String,Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ALERT_USER", null);
		map.put("NO_OF_MONTH", "2");
		alertList.add(map);
		
		map = new HashMap<String, Object>();
		map.put("ALERT_USER", "1111");
		map.put("NO_OF_MONTH", "2");
		alertList.add(map);
		
		input.setListAlertUser(alertList);
		input.setBatchId("CC");
		input.setMsg("msg");
		input.setSubject("subject");
		G_ALT_P06.execute(input,uvo);
		
		HashMap<String, Object> m_alt_h = (HashMap<String,Object>)G_ALT_P06.getQueryDAO().executeForMap("TEST.G_ALT_P06.SELECT.SQL001", null);
		assertEquals("xiaoJTest", m_alt_h.get("ID_LOGIN"));
		assertEquals("NOTICE", m_alt_h.get("MSG_TYPE"));
		assertEquals("1", m_alt_h.get("STATUS"));
		assertEquals("0", m_alt_h.get("REPEAT_MODE"));
		assertEquals("styap", m_alt_h.get("TO_MSG"));
		assertEquals("subject", m_alt_h.get("SUBJECT"));
		assertEquals("msg", m_alt_h.get("MSG_SRING"));
		
		HashMap<String, Object> m_alt_D = (HashMap<String,Object>)G_ALT_P06.getQueryDAO().executeForMap("TEST.G_ALT_P06.SELECT.SQL002", null);
		assertEquals("styap", m_alt_D.get("ID_USER"));
		assertEquals("1", m_alt_D.get("IS_NEW"));
		assertEquals("xiaoJTest", m_alt_D.get("ID_LOGIN"));

	}
}
