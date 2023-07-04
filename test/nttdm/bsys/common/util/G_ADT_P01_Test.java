package nttdm.bsys.common.util;

import java.util.HashMap;
import java.util.Map;

import nttdm.bsys.common.bean.AuditTrailHeaderBean;

public class G_ADT_P01_Test extends AbstractUtilTest{

	private G_ADT_P01 G_ADT_P01;
	
	@Override
	protected void setUpData() throws Exception {
		
		G_ADT_P01 = new G_ADT_P01();
		G_ADT_P01.setUpdateDAO(updateDAO);
		G_ADT_P01.setUpdateDAONuked(updateDAONuked);
		G_ADT_P01.setQueryDAO(queryDAO);
		
	}
	
	/**
	 * test method writeLog()
	 */
    public void testExecutewriteLog() {
    	super.deleteAllData("M_AUDIT_TRAIL_D");
    	super.deleteAllData("M_AUDIT_TRAIL_H");
    	AuditTrailHeaderBean auditHeader = new AuditTrailHeaderBean();
    	auditHeader.setIdModule("IdModule");
    	auditHeader.setIdUser("testUT");
    	auditHeader.setIdSubModule("idSubModul");
    	auditHeader.setReference("ref");
    	auditHeader.setStatus("niubi");
    	auditHeader.setAction("/to/be/niubi.do");
    	G_ADT_P01.writeLog(auditHeader);
    	Map<String,Object> result = new HashMap<String,Object>();
    	result = G_ADT_P01.getQueryDAO().executeForMap("TEST.G_ADT_P01.SELECT.SQL001", null);
    	assertEquals("IdModule", result.get("ID_MODULE"));
    	assertEquals("testUT", result.get("ID_USER"));
    	assertEquals("idSubModul", result.get("ID_SUB_MODULE"));
    	assertEquals("ref", result.get("REFERENCE"));
    	assertEquals("niubi", result.get("STATUS"));
    	assertEquals("/to/be/niubi.do", result.get("ACTION"));
    }
    
	/**
	 * test method updateLog()
	 */
    public void testExecuteupdateLog() {
    	super.deleteAllData("M_AUDIT_TRAIL_D");
    	super.deleteAllData("M_AUDIT_TRAIL_H");
    	AuditTrailHeaderBean auditHeader = new AuditTrailHeaderBean();
    	auditHeader.setIdModule("IdModule");
    	auditHeader.setIdUser("testUT");
    	auditHeader.setIdSubModule("idSubModul");
    	auditHeader.setReference("ref");
    	auditHeader.setStatus("niubi");
    	auditHeader.setAction("/to/be/niubi.do");
    	Integer id =  G_ADT_P01.writeLog(auditHeader);
    	
    	auditHeader = new AuditTrailHeaderBean();
    	auditHeader.setIdModule("11111");
    	auditHeader.setIdUser("22222");
    	auditHeader.setIdSubModule("33333");
    	auditHeader.setReference("44444");
    	auditHeader.setStatus("55555");
    	auditHeader.setAction("66666.do");
    	auditHeader.setIdAudit(id);
    	G_ADT_P01.updateLog(auditHeader);
    	
    	
    	Map<String,Object> result = new HashMap<String,Object>();
    	result = G_ADT_P01.getQueryDAO().executeForMap("TEST.G_ADT_P01.SELECT.SQL002", id);
    	assertEquals("11111", result.get("ID_MODULE"));
    	assertEquals("22222", result.get("ID_USER"));
    	assertEquals("33333", result.get("ID_SUB_MODULE"));
    	assertEquals("44444", result.get("REFERENCE"));
    	assertEquals("55555", result.get("STATUS"));
    	assertEquals("66666.do", result.get("ACTION"));
    }

    /**
     * test method endLog() when emptyRollback is false
     */
    public void testExecuteendLog1(){
    	super.deleteAllData("M_AUDIT_TRAIL_D");
    	super.deleteAllData("M_AUDIT_TRAIL_H");
    	AuditTrailHeaderBean auditHeader = new AuditTrailHeaderBean();
    	auditHeader.setIdModule("IdModule");
    	auditHeader.setIdUser("testUT");
    	auditHeader.setIdSubModule("idSubModul");
    	auditHeader.setReference("ref");
    	auditHeader.setStatus("niubi");
    	auditHeader.setAction("/to/be/niubi.do");
    	Integer id =  G_ADT_P01.writeLog(auditHeader);
    	G_ADT_P01.endLog(id, false);
    	Map<String,Object> result = new HashMap<String,Object>();
    	result = G_ADT_P01.getQueryDAO().executeForMap("TEST.G_ADT_P01.SELECT.SQL002", id);
    	assertEquals(7, result.size());
    }
    
    /**
     * test method endLog() when M_AUDIT_TRAIL_D have no data
     */
    public void testExecuteendLog2(){
    	super.deleteAllData("M_AUDIT_TRAIL_D");
    	super.deleteAllData("M_AUDIT_TRAIL_H");
    	AuditTrailHeaderBean auditHeader = new AuditTrailHeaderBean();
    	auditHeader.setIdModule("IdModule");
    	auditHeader.setIdUser("testUT");
    	auditHeader.setIdSubModule("idSubModul");
    	auditHeader.setReference("ref");
    	auditHeader.setStatus("niubi");
    	auditHeader.setAction("/to/be/niubi.do");
    	Integer id =  G_ADT_P01.writeLog(auditHeader);
    	G_ADT_P01.endLog(id, true);
    	Map<String,Object> result = new HashMap<String,Object>();
    	result = G_ADT_P01.getQueryDAO().executeForMap("TEST.G_ADT_P01.SELECT.SQL002", id);
    	assertEquals(null, result);
    }
    
    /**
     * test method endLog() when M_AUDIT_TRAIL_D have data
     */
    public void testExecuteendLog3(){
    	super.deleteAllData("M_AUDIT_TRAIL_D");
    	super.deleteAllData("M_AUDIT_TRAIL_H");
    	AuditTrailHeaderBean auditHeader = new AuditTrailHeaderBean();
    	auditHeader.setIdModule("IdModule");
    	auditHeader.setIdUser("testUT");
    	auditHeader.setIdSubModule("idSubModul");
    	auditHeader.setReference("ref");
    	auditHeader.setStatus("niubi");
    	auditHeader.setAction("/to/be/niubi.do");
    	Integer id =  G_ADT_P01.writeLog(auditHeader);
    	G_ADT_P01.getUpdateDAO().execute("TEST.G_ADT_P01.INSERT.SQL001", id);
    	G_ADT_P01.endLog(id, true);
    	Map<String,Object> result = new HashMap<String,Object>();
    	result = G_ADT_P01.getQueryDAO().executeForMap("TEST.G_ADT_P01.SELECT.SQL002", id);
    	assertEquals(7, result.size());
    }
}
