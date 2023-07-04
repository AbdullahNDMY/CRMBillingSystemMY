/**
 * @(#)G_CLC_P01_Test.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2012/04/26
 * 
 * Copyright (c) 2012 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.util;

import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.e_mim.dto.RP_E_MIM_US2_02Input;
import nttdm.bsys.e_mim.dto.RP_E_MIM_US2_02Output;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts.upload.FormFile;

/**
 * @author gplai
 *
 */
public class G_CLC_P01_Test extends AbstractUtilTest {

    private G_CLC_P01 gClcP01;
    
    @Override
    protected void setUpData() throws Exception {
        super.deleteAllData("T_CLC_IMP_MONTHLY_SUM");
        super.deleteAllData("T_CLC_IMP_HD");
        super.deleteAllData("M_GSET_D");
        
        //delete T_SUBSCRIPTION_INFO
        super.deleteAllData("T_TEAMWORK");
        super.deleteAllData("T_MAIL_ACCOUNT");
        super.deleteAllData("T_MAIL_ADDRESS");
        super.deleteAllData("T_CPE_CONF_D");
        super.deleteAllData("T_CPE_CONF_H");
        super.deleteAllData("T_SERVER_INFO_D");
        super.deleteAllData("T_SERVER_INFO_H");
        super.deleteAllData("T_FIREWALL_POLICY");
        super.deleteAllData("T_FIREWALL_INT_IP");
        super.deleteAllData("T_FIREWALL");
        super.deleteAllData("T_MRTG");
        super.deleteAllData("T_INST_ADR");
        super.deleteAllData("T_IT_CONTACT_D");
        super.deleteAllData("T_IT_CONTACT_H");
        super.deleteAllData("T_DNS_ZONE_REC");
        super.deleteAllData("T_DNS_ZONE");
        super.deleteAllData("T_RACK_POWER_D");
        super.deleteAllData("T_RACK_POWER_H");
        super.deleteAllData("T_FTP_INT");
        super.deleteAllData("T_SUBSCRIPTION_INFO");
        
        //delete T_CUST_PLAN_H Info
        super.deleteAllData("T_CUST_PLAN_SVC");
        super.deleteAllData("T_CUST_PLAN_LINK");
        super.deleteAllData("T_CUST_PLAN_D");
        super.deleteAllData("T_CUST_PLAN_H");
        
        //delete M_PLAN_BATCH_MAPPING
        super.deleteAllData("M_PLAN_BATCH_MAPPING");
        
        gClcP01 = new G_CLC_P01();
        gClcP01.setQueryDAO(queryDAO);
        gClcP01.setUpdateDAO(updateDAO);
    }

    /**
     * case1:auditId is null
     * @throws Exception 
     */
    
    public void testExecute01(){
        RP_E_MIM_US2_02Input input = new RP_E_MIM_US2_02Input();
        RP_E_MIM_US2_02Output output = new RP_E_MIM_US2_02Output();
        
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        input.setUvo(uvo);
        input.setMonth("01");
        input.setYear("2012");
        
        gClcP01.setAuditIdModule("E");
        gClcP01.setAuditIdSubModule("");
        gClcP01.setAuditReference("");
        gClcP01.setAuditStatus("1");
        
        GlobalProcessResult result = gClcP01.execute(input, output);
        gClcP01.reset();
        
        assertEquals("errors.ERR1SC074",
                (result.getErrors().get().next()).getKey());
    }
    
    /**
     * case2:not BATCH_TIME_INTERVAL
     * @throws Exception 
     */
    public void testExecute02(){
        String[][] T_CLC_IMP_HD = {
                { "ID_CLC_IMP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
                        "DATE_UPLOADED", "STATUS", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                { "1", "1.xls", "01/2012", 
                   TEST_DAY_TODAY, "2",TEST_DAY_TODAY,TEST_DAY_TOMORROW, "sysadmin" } };
        String[][] M_GSET_D1 = {
                { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "SET_VALUE", "ID_AUDIT", "SET_APPLY" },
                {
                        "BATCH_TIME_INTERVAL",
                        "1",
                        "The number of hours before the subsequent batch can be executed",
                        "0", "2010-11-24 16:50:44", "2010-11-24 16:50:44",
                        "sysadmin", "0", AUDIT_VALUE, "1" } };
        
        super.insertData("T_CLC_IMP_HD", T_CLC_IMP_HD);
        super.insertData("M_GSET_D", M_GSET_D1);
        
        RP_E_MIM_US2_02Input input = new RP_E_MIM_US2_02Input();
        RP_E_MIM_US2_02Output output = new RP_E_MIM_US2_02Output();
        
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        input.setUvo(uvo);
        input.setMonth("01");
        input.setYear("2012");
        
        gClcP01.setAuditIdModule(BillingSystemConstants.MODULE_E);
        gClcP01.setAuditIdSubModule(BillingSystemConstants.SUB_MODULE_E_MIM_US1);
        gClcP01.setAuditReference("");
        gClcP01.setAuditStatus("1");
        
        GlobalProcessResult result = gClcP01.execute(input, output);
        gClcP01.reset();
        
        assertEquals("errors.ERR1SC059",
                (result.getErrors().get().next()).getKey());
    }
    
    /**
     * case3:export file path is null
     * @throws Exception 
     */
    public void testExecute03() throws Exception{
        String[][] T_CLC_IMP_HD = {
                { "ID_CLC_IMP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
                        "DATE_UPLOADED", "STATUS", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                { "1", "1.xls", "01/2012", 
                   TEST_DAY_TODAY, "2",TEST_DAY_TODAY,TEST_DAY_TODAY, "sysadmin" } };
        String[][] M_GSET_D1 = {
                { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "SET_VALUE", "ID_AUDIT", "SET_APPLY" },
                {
                        "BATCH_TIME_INTERVAL",
                        "1",
                        "The number of hours before the subsequent batch can be executed",
                        "0", "2010-11-24 16:50:44", "2010-11-24 16:50:44",
                        "sysadmin", "2", AUDIT_VALUE, "1" },
                { "BATCH_G_CLC_P01", "1",
                    "EOD - Data Import (SingPost Collection Data)", "0",
                    "2010-11-24 16:50:44", "2011-07-13 11:00:34",
                    "USERFULL", "", AUDIT_VALUE, "1" }};
        super.insertData("T_CLC_IMP_HD", T_CLC_IMP_HD);
        super.insertData("M_GSET_D", M_GSET_D1);
        
        RP_E_MIM_US2_02Input input = new RP_E_MIM_US2_02Input();
        RP_E_MIM_US2_02Output output = new RP_E_MIM_US2_02Output();
        
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        
        List<Map<String, Object>> listData = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("idClcCust","0001");
        map.put("invoiceTotal","200");
        listData.add(map);
        FormFile formFile = getFormFile(true, listData,"testExcel.xlsx");
        
        input.setFormFile(formFile);
        input.setUvo(uvo);
        input.setMonth("01");
        input.setYear("2012");
        
        gClcP01.setAuditIdModule(BillingSystemConstants.MODULE_E);
        gClcP01.setAuditIdSubModule(BillingSystemConstants.SUB_MODULE_E_MIM_US1);
        gClcP01.setAuditReference("");
        gClcP01.setAuditStatus("1");
        
        GlobalProcessResult result = gClcP01.execute(input, output);
        gClcP01.reset();
        
        assertEquals("errors.ERR1SC079",
                (result.getErrors().get().next()).getKey());
    }
    
    /**
     * case4:export file path is not read
     * @throws Exception 
     */
    public void testExecute04() throws Exception{
        String[][] T_CLC_IMP_HD = {
                { "ID_CLC_IMP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
                        "DATE_UPLOADED", "STATUS", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                { "1", "1.xls", "01/2012", 
                   TEST_DAY_TODAY, "2",TEST_DAY_TODAY,TEST_DAY_TODAY, "sysadmin" } };
        String[][] M_GSET_D1 = {
                { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "SET_VALUE", "ID_AUDIT", "SET_APPLY" },
                {
                        "BATCH_TIME_INTERVAL",
                        "1",
                        "The number of hours before the subsequent batch can be executed",
                        "0", "2010-11-24 16:50:44", "2010-11-24 16:50:44",
                        "sysadmin", "2", AUDIT_VALUE, "1" },
                { "BATCH_G_CLC_P01", "1",
                    "EOD - Data Import (SingPost Collection Data)", "0",
                    "2010-11-24 16:50:44", "2011-07-13 11:00:34",
                    "USERFULL", "F:\\aab\\ac", AUDIT_VALUE, "1" }};
        super.insertData("T_CLC_IMP_HD", T_CLC_IMP_HD);
        super.insertData("M_GSET_D", M_GSET_D1);
        
        RP_E_MIM_US2_02Input input = new RP_E_MIM_US2_02Input();
        RP_E_MIM_US2_02Output output = new RP_E_MIM_US2_02Output();
        
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        
        List<Map<String, Object>> listData = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("idClcCust","0001");
        map.put("invoiceTotal","200");
        listData.add(map);
        FormFile formFile = getFormFile(false, listData,"testExcel.xls");
        
        input.setFormFile(formFile);
        input.setUvo(uvo);
        input.setMonth("01");
        input.setYear("2012");
        
        gClcP01.setAuditIdModule(BillingSystemConstants.MODULE_E);
        gClcP01.setAuditIdSubModule(BillingSystemConstants.SUB_MODULE_E_MIM_US1);
        gClcP01.setAuditReference("");
        gClcP01.setAuditStatus("1");
        
        GlobalProcessResult result = gClcP01.execute(input, output);
        gClcP01.reset();
        
        assertEquals("errors.ERR1SC080",
                (result.getErrors().get().next()).getKey());
    }
    
    /**
     * case5:check import file format is error
     * @throws Exception 
     */
    public void testExecute05() throws Exception{
        String[][] T_CLC_IMP_HD = {
                { "ID_CLC_IMP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
                        "DATE_UPLOADED", "STATUS", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                { "1", "1.xls", "01/2012", 
                   TEST_DAY_TODAY, "2",TEST_DAY_TODAY,TEST_DAY_TODAY, "sysadmin" } };
        String[][] M_GSET_D1 = {
                { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "SET_VALUE", "ID_AUDIT", "SET_APPLY" },
                {
                        "BATCH_TIME_INTERVAL",
                        "1",
                        "The number of hours before the subsequent batch can be executed",
                        "0", "2010-11-24 16:50:44", "2010-11-24 16:50:44",
                        "sysadmin", "2", AUDIT_VALUE, "1" },
                { "BATCH_G_CLC_P01", "1",
                    "EOD - Data Import (SingPost Collection Data)", "0",
                    "2010-11-24 16:50:44", "2011-07-13 11:00:34",
                    "USERFULL", "C:\\", AUDIT_VALUE, "1" }};
        super.insertData("T_CLC_IMP_HD", T_CLC_IMP_HD);
        super.insertData("M_GSET_D", M_GSET_D1);
        
        RP_E_MIM_US2_02Input input = new RP_E_MIM_US2_02Input();
        RP_E_MIM_US2_02Output output = new RP_E_MIM_US2_02Output();
        
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        
        List<Map<String, Object>> listData = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("idClcCust","0001");
        map.put("invoiceTotal","200");
        listData.add(map);
        FormFile formFile = getFormFile(false, listData,"testExcel.xlss");
        
        input.setFormFile(formFile);
        input.setUvo(uvo);
        input.setMonth("01");
        input.setYear("2012");
        
        gClcP01.setAuditIdModule(BillingSystemConstants.MODULE_E);
        gClcP01.setAuditIdSubModule(BillingSystemConstants.SUB_MODULE_E_MIM_US1);
        gClcP01.setAuditReference("");
        gClcP01.setAuditStatus("1");
        
        GlobalProcessResult result = gClcP01.execute(input, output);
        gClcP01.reset();
        
        assertEquals("errors.ERR1SC082",
                (result.getErrors().get().next()).getKey());
    }
    
    /**
     * case6:check import file format is error
     * @throws Exception 
     */
    public void testExecute06() throws Exception{
        String[][] T_CLC_IMP_HD = {
                { "ID_CLC_IMP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
                        "DATE_UPLOADED", "STATUS", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                { "1", "1.xls", "01/2012", 
                   TEST_DAY_TODAY, "2",TEST_DAY_TODAY,TEST_DAY_TODAY, "sysadmin" } };
        String[][] M_GSET_D1 = {
                { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "SET_VALUE", "ID_AUDIT", "SET_APPLY" },
                {
                        "BATCH_TIME_INTERVAL",
                        "1",
                        "The number of hours before the subsequent batch can be executed",
                        "0", "2010-11-24 16:50:44", "2010-11-24 16:50:44",
                        "sysadmin", "2", AUDIT_VALUE, "1" },
                { "BATCH_G_CLC_P01", "1",
                    "EOD - Data Import (SingPost Collection Data)", "0",
                    "2010-11-24 16:50:44", "2011-07-13 11:00:34",
                    "USERFULL", "C:\\", AUDIT_VALUE, "1" }};
        super.insertData("T_CLC_IMP_HD", T_CLC_IMP_HD);
        super.insertData("M_GSET_D", M_GSET_D1);
        
        RP_E_MIM_US2_02Input input = new RP_E_MIM_US2_02Input();
        RP_E_MIM_US2_02Output output = new RP_E_MIM_US2_02Output();
        
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        
        List<Map<String, Object>> listData = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("idClcCust","");
        map.put("invoiceTotal","200");
        listData.add(map);
        
        FormFile formFile = getFormFile(true, listData,"testExcel.xls");
        
        input.setFormFile(formFile);
        input.setUvo(uvo);
        input.setMonth("01");
        input.setYear("2012");
        
        gClcP01.setAuditIdModule(BillingSystemConstants.MODULE_E);
        gClcP01.setAuditIdSubModule(BillingSystemConstants.SUB_MODULE_E_MIM_US1);
        gClcP01.setAuditReference("");
        gClcP01.setAuditStatus("1");
        
        GlobalProcessResult result = gClcP01.execute(input, output);
        gClcP01.reset();
        
        Iterator<BLogicMessage>  error = result.getErrors().get();
        assertEquals("errors.ERR1SC082",(error.next()).getKey());
    }
    
    /**
     * case7:check import file format is error
     * @throws Exception 
     */
    public void testExecute07() throws Exception{
        String[][] T_CLC_IMP_HD = {
                { "ID_CLC_IMP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
                        "DATE_UPLOADED", "STATUS", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                { "1", "1.xls", "01/2012", 
                   TEST_DAY_TODAY, "2",TEST_DAY_TODAY,TEST_DAY_TODAY, "sysadmin" } };
        String[][] M_GSET_D1 = {
                { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "SET_VALUE", "ID_AUDIT", "SET_APPLY" },
                {
                        "BATCH_TIME_INTERVAL",
                        "1",
                        "The number of hours before the subsequent batch can be executed",
                        "0", "2010-11-24 16:50:44", "2010-11-24 16:50:44",
                        "sysadmin", "2", AUDIT_VALUE, "1" },
                { "BATCH_G_CLC_P01", "1",
                    "EOD - Data Import (SingPost Collection Data)", "0",
                    "2010-11-24 16:50:44", "2011-07-13 11:00:34",
                    "USERFULL", "C:\\", AUDIT_VALUE, "1" }};
        super.insertData("T_CLC_IMP_HD", T_CLC_IMP_HD);
        super.insertData("M_GSET_D", M_GSET_D1);
        
        RP_E_MIM_US2_02Input input = new RP_E_MIM_US2_02Input();
        RP_E_MIM_US2_02Output output = new RP_E_MIM_US2_02Output();
        
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        
        List<Map<String, Object>> listData = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("idClcCust","");
        map.put("invoiceTotal","200");
        listData.add(map);
        
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("idClcCust","00001");
        map1.put("invoiceTotal","");
        listData.add(map1);
        
        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("idClcCust","");
        map2.put("invoiceTotal","");
        listData.add(map2);
        
        Map<String, Object> map3 = new HashMap<String, Object>();
        map3.put("idClcCust","00001");
        map3.put("invoiceTotal","ad");
        listData.add(map3);
        
        FormFile formFile = getFormFile(true, listData,"testExcel.xlsx");
        
        input.setFormFile(formFile);
        input.setUvo(uvo);
        input.setMonth("01");
        input.setYear("2012");
        
        gClcP01.setAuditIdModule(BillingSystemConstants.MODULE_E);
        gClcP01.setAuditIdSubModule(BillingSystemConstants.SUB_MODULE_E_MIM_US1);
        gClcP01.setAuditReference("");
        gClcP01.setAuditStatus("1");
        
        GlobalProcessResult result = gClcP01.execute(input, output);
        gClcP01.reset();
        
        Iterator<BLogicMessage>  error = result.getErrors().get();
        assertEquals("errors.ERR1SC081",(error.next()).getKey());
        assertEquals("errors.ERR1SC081",(error.next()).getKey());
        //assertEquals("errors.ERR1SC081",(error.next()).getKey());
        assertEquals("errors.ERR1SC084",(error.next()).getKey());
    }
    
    /**
     * case8:check import file format is error
     * @throws Exception 
     */
    public void testExecute08() throws Exception{
        String[][] T_CLC_IMP_HD = {
                { "ID_CLC_IMP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
                        "DATE_UPLOADED", "STATUS", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                { "1", "1.xls", "01/2012", 
                   TEST_DAY_TODAY, "2",TEST_DAY_TODAY,TEST_DAY_TODAY, "sysadmin" } };
        String[][] M_GSET_D1 = {
                { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "SET_VALUE", "ID_AUDIT", "SET_APPLY" },
                {
                        "BATCH_TIME_INTERVAL",
                        "1",
                        "The number of hours before the subsequent batch can be executed",
                        "0", "2010-11-24 16:50:44", "2010-11-24 16:50:44",
                        "sysadmin", "2", AUDIT_VALUE, "1" },
                { "BATCH_G_CLC_P01", "1",
                    "EOD - Data Import (SingPost Collection Data)", "0",
                    "2010-11-24 16:50:44", "2011-07-13 11:00:34",
                    "USERFULL", "C:\\", AUDIT_VALUE, "1" }};
        super.insertData("T_CLC_IMP_HD", T_CLC_IMP_HD);
        super.insertData("M_GSET_D", M_GSET_D1);
        
        RP_E_MIM_US2_02Input input = new RP_E_MIM_US2_02Input();
        RP_E_MIM_US2_02Output output = new RP_E_MIM_US2_02Output();
        
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        
        List<Map<String, Object>> listData = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("idClcCust",true);
        map.put("invoiceTotal",new SimpleDateFormat("dd/MM/yyyy").parse(new SimpleDateFormat("dd/MM/yyyy").format(new Date())));
        listData.add(map);
        
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("idClcCust","11");
        map1.put("invoiceTotal","a");
        listData.add(map1);
        
        
        FormFile formFile = getFormFile1(true, listData,"testExcel.xlsx");
        
        input.setFormFile(formFile);
        input.setUvo(uvo);
        input.setMonth("01");
        input.setYear("2012");
        
        gClcP01.setAuditIdModule(BillingSystemConstants.MODULE_E);
        gClcP01.setAuditIdSubModule(BillingSystemConstants.SUB_MODULE_E_MIM_US1);
        gClcP01.setAuditReference("");
        gClcP01.setAuditStatus("1");
        
        GlobalProcessResult result = gClcP01.execute(input, output);
        gClcP01.reset();
        
        Iterator<BLogicMessage>  error = result.getErrors().get();
        assertEquals("errors.ERR1SC081",(error.next()).getKey());
    }
    
    /**
     * case9:check import file format is error
     * @throws Exception 
     */
    public void testExecute9() throws Exception{
        String[][] T_CLC_IMP_HD = {
                { "ID_CLC_IMP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
                        "DATE_UPLOADED", "STATUS", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                { "1", "1.xls", "01/2012", 
                   TEST_DAY_TODAY, "2",TEST_DAY_TODAY,TEST_DAY_TODAY, "sysadmin" } };
        String[][] M_GSET_D1 = {
                { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "SET_VALUE", "ID_AUDIT", "SET_APPLY" },
                {
                        "BATCH_TIME_INTERVAL",
                        "1",
                        "The number of hours before the subsequent batch can be executed",
                        "0", "2010-11-24 16:50:44", "2010-11-24 16:50:44",
                        "sysadmin", "2", AUDIT_VALUE, "1" },
                { "BATCH_G_CLC_P01", "1",
                    "EOD - Data Import (SingPost Collection Data)", "0",
                    "2010-11-24 16:50:44", "2011-07-13 11:00:34",
                    "USERFULL", "C:\\", AUDIT_VALUE, "1" }};
        super.insertData("T_CLC_IMP_HD", T_CLC_IMP_HD);
        super.insertData("M_GSET_D", M_GSET_D1);
        
        RP_E_MIM_US2_02Input input = new RP_E_MIM_US2_02Input();
        RP_E_MIM_US2_02Output output = new RP_E_MIM_US2_02Output();
        
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        
        List<Map<String, Object>> listData = new ArrayList<Map<String, Object>>();
        
        FormFile formFile = getFormFile(true, listData,"testExcel.xlsx");
        
        input.setFormFile(formFile);
        input.setUvo(uvo);
        input.setMonth("01");
        input.setYear("2012");
        
        gClcP01.setAuditIdModule(BillingSystemConstants.MODULE_E);
        gClcP01.setAuditIdSubModule(BillingSystemConstants.SUB_MODULE_E_MIM_US1);
        gClcP01.setAuditReference("");
        gClcP01.setAuditStatus("1");
        
        GlobalProcessResult result = gClcP01.execute(input, output);
        gClcP01.reset();
        
        Iterator<BLogicMessage>  error = result.getErrors().get();
        assertEquals("errors.ERR1SC081",(error.next()).getKey());
    }
    
    /**
     * case10:check import file format is error
     * @throws Exception 
     */
    public void testExecute10() throws Exception{
        String[][] T_CLC_IMP_HD = {
                { "ID_CLC_IMP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
                        "DATE_UPLOADED", "STATUS", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                { "1", "1.xls", "01/2012", 
                   TEST_DAY_TODAY, "2",TEST_DAY_TODAY,TEST_DAY_TODAY, "sysadmin" } };
        String[][] M_GSET_D1 = {
                { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "SET_VALUE", "ID_AUDIT", "SET_APPLY" },
                {
                        "BATCH_TIME_INTERVAL",
                        "1",
                        "The number of hours before the subsequent batch can be executed",
                        "0", "2010-11-24 16:50:44", "2010-11-24 16:50:44",
                        "sysadmin", "2", AUDIT_VALUE, "1" },
                { "BATCH_G_CLC_P01", "1",
                    "EOD - Data Import (SingPost Collection Data)", "0",
                    "2010-11-24 16:50:44", "2011-07-13 11:00:34",
                    "USERFULL", "C:\\", AUDIT_VALUE, "1" }};
        super.insertData("T_CLC_IMP_HD", T_CLC_IMP_HD);
        super.insertData("M_GSET_D", M_GSET_D1);
        
        RP_E_MIM_US2_02Input input = new RP_E_MIM_US2_02Input();
        RP_E_MIM_US2_02Output output = new RP_E_MIM_US2_02Output();
        
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        
        List<Map<String, Object>> listData = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("idClcCust","00001");
        map.put("invoiceTotal","200");
        listData.add(map);
        
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("idClcCust","00001");
        map1.put("invoiceTotal","100");
        listData.add(map1);
        
        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("idClcCust","00002");
        map2.put("invoiceTotal","10");
        listData.add(map2);
        
        Map<String, Object> map3 = new HashMap<String, Object>();
        map3.put("idClcCust","00002");
        map3.put("invoiceTotal","50");
        listData.add(map3);
        
        FormFile formFile = getFormFile(true, listData,"testExcel.xlsx");
        
        input.setFormFile(formFile);
        input.setUvo(uvo);
        input.setMonth("01");
        input.setYear("2012");
        
        gClcP01.setAuditIdModule(BillingSystemConstants.MODULE_E);
        gClcP01.setAuditIdSubModule(BillingSystemConstants.SUB_MODULE_E_MIM_US1);
        gClcP01.setAuditReference("");
        gClcP01.setAuditStatus("1");
        
        GlobalProcessResult result = gClcP01.execute(input, output);
        gClcP01.reset();
        
        Iterator<BLogicMessage>  error = result.getErrors().get();
        assertEquals("errors.ERR1SC087",(error.next()).getKey());
    }
    
    /**
     * case11:check All Subscription_id Exist
     * @throws Exception 
     */
    public void testExecute11() throws Exception{
        String[][] T_CLC_IMP_HD = {
                { "ID_CLC_IMP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
                        "DATE_UPLOADED", "STATUS", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                { "1", "1.xls", "01/2012", 
                   TEST_DAY_TODAY, "2",TEST_DAY_TODAY,TEST_DAY_TODAY, "sysadmin" } };
        String[][] M_GSET_D1 = {
                { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "SET_VALUE", "ID_AUDIT", "SET_APPLY" },
                {
                        "BATCH_TIME_INTERVAL",
                        "1",
                        "The number of hours before the subsequent batch can be executed",
                        "0", "2010-11-24 16:50:44", "2010-11-24 16:50:44",
                        "sysadmin", "2", AUDIT_VALUE, "1" },
                { "BATCH_G_CLC_P01", "1",
                    "EOD - Data Import (SingPost Collection Data)", "0",
                    "2010-11-24 16:50:44", "2011-07-13 11:00:34",
                    "USERFULL", "C:\\", AUDIT_VALUE, "1" }};
        
        super.insertData("T_CLC_IMP_HD", T_CLC_IMP_HD);
        super.insertData("M_GSET_D", M_GSET_D1);
        
        RP_E_MIM_US2_02Input input = new RP_E_MIM_US2_02Input();
        RP_E_MIM_US2_02Output output = new RP_E_MIM_US2_02Output();
        
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        
        List<Map<String, Object>> listData = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("idClcCust","00001");
        map.put("invoiceTotal","200");
        listData.add(map);
        
        FormFile formFile = getFormFile(true, listData,"testExcel.xlsx");
        
        input.setFormFile(formFile);
        input.setUvo(uvo);
        input.setMonth("01");
        input.setYear("2012");
        
        gClcP01.setAuditIdModule(BillingSystemConstants.MODULE_E);
        gClcP01.setAuditIdSubModule(BillingSystemConstants.SUB_MODULE_E_MIM_US1);
        gClcP01.setAuditReference("");
        gClcP01.setAuditStatus("1");
        
        GlobalProcessResult result = gClcP01.execute(input, output);
        gClcP01.reset();
        
        Iterator<BLogicMessage>  error = result.getErrors().get();
        assertEquals("errors.ERR1SC083",(error.next()).getKey());
    }
    
    /**
     * case12:check All Subscription_id Exist
     * @throws Exception 
     */
    public void testExecute12() throws Exception{
        String[][] T_CLC_IMP_HD = {
                { "ID_CLC_IMP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
                        "DATE_UPLOADED", "STATUS", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                { "1", "1.xls", "01/2012", 
                   TEST_DAY_TODAY, "0",TEST_DAY_TODAY,TEST_DAY_TODAY, "sysadmin" } };
        String[][] T_CLC_IMP_MONTHLY_SUM = {
                { "ID_CLC_IMP_BATCH", "ID_CLC_CUST", "MONTH_YEAR", "IS_INVOICED",
                        "INVOICE_TOTAL", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                { "1", "00001", "01/2012", "1",
                   "100", TEST_DAY_TODAY,TEST_DAY_TODAY, "sysadmin" } };
        String[][] M_GSET_D1 = {
                { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "SET_VALUE", "ID_AUDIT", "SET_APPLY" },
                {
                        "BATCH_TIME_INTERVAL",
                        "1",
                        "The number of hours before the subsequent batch can be executed",
                        "0", "2010-11-24 16:50:44", "2010-11-24 16:50:44",
                        "sysadmin", "2", AUDIT_VALUE, "1" },
                { "BATCH_G_CLC_P01", "1",
                    "EOD - Data Import (SingPost Collection Data)", "0",
                    "2010-11-24 16:50:44", "2011-07-13 11:00:34",
                    "USERFULL", "C:\\", AUDIT_VALUE, "1" }};
        String[][] T_CUST_PLAN_H = {
                { "ID_CUST_PLAN", "ID_CUST", "PLAN_STATUS", "PLAN_TYPE",
                        "APPROVE_TYPE", "TRANSACTION_TYPE", "REFERENCE_PLAN",
                        "ID_BILL_ACCOUNT", "BILL_ACC_ALL", "SVC_LEVEL1",
                        "SVC_LEVEL2", "BILL_INSTRUCT", "PAYMENT_METHOD",
                        "BILL_CURRENCY", "EXPORT_CURRENCY", "FIXED_FOREX",
                        "IS_DISPLAY_EXP_AMT", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN" },
                { "43", "229743", "PS1", "NP", null, "IN", null, "2", "1",
                        "268", "357", "4", "CQ", "MYR", "BAM", "12", "0", "0",
                        "2011-08-02 14:13:46", "2011-08-02 14:13:46",
                        "USERFULL"}, };
        String[][] T_SUBSCRIPTION_INFO = {
                { "ID_CUST_PLAN","DATE_CREATED", "DATE_UPDATED", "ID_LOGIN","IS_DELETED","ID_SUB_INFO"},
                { "43", "2011-8-22  16:38:04", "2011-8-22  16:38:04", "sysadmin", "0",  "00001" } };
        
        String[][] testDataTCustPlanD =
        {
            {"ID_CUST_PLAN_GRP" , "ID_CUST_PLAN" , "SERVICES_STATUS" ,
                "SVC_START" , "SVC_END" , "AUTO_RENEW" , "MIN_SVC_PERIOD" ,
                "MIN_SVC_START" , "MIN_SVC_END" , "CONTRACT_TERM" ,
                "CONTRACT_TERM_NO" , "PRO_RATE_BASE" , "PRO_RATE_BASE_NO" ,
                "IS_LUMP_SUM" , "BILL_DESC" , "IS_DELETED" ,
                "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
            {"123" , "43" , "PS3" , "2011-08-20" , "2011-08-23" , "0" , "0" ,
                "2011-08-20" , "2011-08-23" , "M" , "1" , "S" , "1" , "0" ,
                null , "0" , "2011-07-28" , "2011-07-28" , "sysadmin" , null}};
        String[][] custPlanLink = {
                { "ID_CUST_PLAN_LINK", "ID_CUST_PLAN_GRP", "ITEM_TYPE",
                        "ITEM_DESC", "QUANTITY", "UNIT_PRICE", "TOTAL_AMOUNT",
                        "JOB_NO", "ID_PLAN", "PLAN_NAME", "PLAN_DESC",
                        "ID_PLAN_GRP", "ITEM_GRP_NAME", "SVC_LEVEL1",
                        "SVC_LEVEL2", "RATE_TYPE", "RATE_MODE", "RATE",
                        "APPLY_GST", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT" },
                { "1", "123", "S", null, "1", "1200", "1200", "JOBNO1", "1",
                        "IDC-DRCenter-001 ", "Disaster Recovery Center", "123",
                        "Yearly Fee", "1", "2", "RA", "1", "1", "0", "0",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, SYS_ADMIN, null } };
        String[][] testM_PLAN_BATCH_MAPPING =
        {
            {"ID_PLAN_GRP" , "ID_PLAN" , "BATCH_ID" , "USAGE_BASE" ,
                "UOM" , "CODE_VALUE" ,"ID_LOGIN","ID_AUDIT"} ,
            {"123" , "123" , "RD" , "0" ,"","0",
                "sysadmin" , "0" }};
        
        super.insertData("T_CLC_IMP_HD", T_CLC_IMP_HD);
        super.insertData("T_CLC_IMP_MONTHLY_SUM", T_CLC_IMP_MONTHLY_SUM);
        super.insertData("M_GSET_D", M_GSET_D1);
        super.insertData("T_CUST_PLAN_H", T_CUST_PLAN_H);
        super.insertData("T_SUBSCRIPTION_INFO", T_SUBSCRIPTION_INFO);
        super.insertData("T_CUST_PLAN_D", testDataTCustPlanD);
        super.insertData("T_CUST_PLAN_LINK", custPlanLink);
        super.insertData("M_PLAN_BATCH_MAPPING", testM_PLAN_BATCH_MAPPING);
        
        RP_E_MIM_US2_02Input input = new RP_E_MIM_US2_02Input();
        RP_E_MIM_US2_02Output output = new RP_E_MIM_US2_02Output();
        
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        
        List<Map<String, Object>> listData = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("idClcCust","00001");
        map.put("invoiceTotal","200");
        listData.add(map);
        
        FormFile formFile = getFormFile(true, listData,"testExcel.xlsx");
        
        input.setFormFile(formFile);
        input.setUvo(uvo);
        input.setMonth("01");
        input.setYear("2012");
        
        gClcP01.setAuditIdModule(BillingSystemConstants.MODULE_E);
        gClcP01.setAuditIdSubModule(BillingSystemConstants.SUB_MODULE_E_MIM_US1);
        gClcP01.setAuditReference("");
        gClcP01.setAuditStatus("1");
        
        GlobalProcessResult result = gClcP01.execute(input, output);
        gClcP01.reset();
        
        Iterator<BLogicMessage>  error = result.getErrors().get();
        assertEquals("errors.ERR1SC085",(error.next()).getKey());
    }
    
    /**
     * case13:test ok
     * @throws Exception 
     */
    public void testExecute13() throws Exception{
        String[][] T_CLC_IMP_HD = {
                { "ID_CLC_IMP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
                        "DATE_UPLOADED", "STATUS", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                { "1", "1.xls", "01/2012", 
                   TEST_DAY_TODAY, "0",TEST_DAY_TODAY,TEST_DAY_TODAY, "sysadmin" } };
        String[][] T_CLC_IMP_MONTHLY_SUM = {
                { "ID_CLC_IMP_BATCH", "ID_CLC_CUST", "MONTH_YEAR", "IS_INVOICED",
                        "INVOICE_TOTAL", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                { "1", "00001", "01/2012", "0",
                   "100", TEST_DAY_TODAY,TEST_DAY_TODAY, "sysadmin" } };
        String[][] M_GSET_D1 = {
                { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "SET_VALUE", "ID_AUDIT", "SET_APPLY" },
                {
                        "BATCH_TIME_INTERVAL",
                        "1",
                        "The number of hours before the subsequent batch can be executed",
                        "0", "2010-11-24 16:50:44", "2010-11-24 16:50:44",
                        "sysadmin", "2", AUDIT_VALUE, "1" },
                { "BATCH_G_CLC_P01", "1",
                    "EOD - Data Import (SingPost Collection Data)", "0",
                    "2010-11-24 16:50:44", "2011-07-13 11:00:34",
                    "USERFULL", "C:\\", AUDIT_VALUE, "1" }};
        String[][] T_CUST_PLAN_H = {
                { "ID_CUST_PLAN", "ID_CUST", "PLAN_STATUS", "PLAN_TYPE",
                        "APPROVE_TYPE", "TRANSACTION_TYPE", "REFERENCE_PLAN",
                        "ID_BILL_ACCOUNT", "BILL_ACC_ALL", "SVC_LEVEL1",
                        "SVC_LEVEL2", "BILL_INSTRUCT", "PAYMENT_METHOD",
                        "BILL_CURRENCY", "EXPORT_CURRENCY", "FIXED_FOREX",
                        "IS_DISPLAY_EXP_AMT", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN" },
                { "43", "229743", "PS1", "NP", null, "IN", null, "2", "1",
                        "268", "357", "4", "CQ", "MYR", "BAM", "12", "0", "0",
                        "2011-08-02 14:13:46", "2011-08-02 14:13:46",
                        "USERFULL"}, };
        String[][] T_SUBSCRIPTION_INFO = {
                { "ID_CUST_PLAN","DATE_CREATED", "DATE_UPDATED", "ID_LOGIN","IS_DELETED","ID_SUB_INFO"},
                { "43", "2011-8-22  16:38:04", "2011-8-22  16:38:04", "sysadmin", "0",  "00001" } };
        
        String[][] testDataTCustPlanD =
        {
            {"ID_CUST_PLAN_GRP" , "ID_CUST_PLAN" , "SERVICES_STATUS" ,
                "SVC_START" , "SVC_END" , "AUTO_RENEW" , "MIN_SVC_PERIOD" ,
                "MIN_SVC_START" , "MIN_SVC_END" , "CONTRACT_TERM" ,
                "CONTRACT_TERM_NO" , "PRO_RATE_BASE" , "PRO_RATE_BASE_NO" ,
                "IS_LUMP_SUM" , "BILL_DESC" , "IS_DELETED" ,
                "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
            {"123" , "43" , "PS3" , "2011-08-20" , "2011-08-23" , "0" , "0" ,
                "2011-08-20" , "2011-08-23" , "M" , "1" , "S" , "1" , "0" ,
                null , "0" , "2011-07-28" , "2011-07-28" , "sysadmin" , null}};
        String[][] custPlanLink = {
                { "ID_CUST_PLAN_LINK", "ID_CUST_PLAN_GRP", "ITEM_TYPE",
                        "ITEM_DESC", "QUANTITY", "UNIT_PRICE", "TOTAL_AMOUNT",
                        "JOB_NO", "ID_PLAN", "PLAN_NAME", "PLAN_DESC",
                        "ID_PLAN_GRP", "ITEM_GRP_NAME", "SVC_LEVEL1",
                        "SVC_LEVEL2", "RATE_TYPE", "RATE_MODE", "RATE",
                        "APPLY_GST", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT" },
                { "1", "123", "S", null, "1", "1200", "1200", "JOBNO1", "1",
                        "IDC-DRCenter-001 ", "Disaster Recovery Center", "123",
                        "Yearly Fee", "1", "2", "RA", "1", "1", "0", "0",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, SYS_ADMIN, null } };
        String[][] testM_PLAN_BATCH_MAPPING =
        {
            {"ID_PLAN_GRP" , "ID_PLAN" , "BATCH_ID" , "USAGE_BASE" ,
                "UOM" , "CODE_VALUE" ,"ID_LOGIN","ID_AUDIT"} ,
            {"123" , "123" , "RD" , "0" ,"","0",
                "sysadmin" , "0" }};
        
        super.insertData("T_CLC_IMP_HD", T_CLC_IMP_HD);
        super.insertData("T_CLC_IMP_MONTHLY_SUM", T_CLC_IMP_MONTHLY_SUM);
        super.insertData("M_GSET_D", M_GSET_D1);
        super.insertData("T_CUST_PLAN_H", T_CUST_PLAN_H);
        super.insertData("T_SUBSCRIPTION_INFO", T_SUBSCRIPTION_INFO);
        super.insertData("T_CUST_PLAN_D", testDataTCustPlanD);
        super.insertData("T_CUST_PLAN_LINK", custPlanLink);
        super.insertData("M_PLAN_BATCH_MAPPING", testM_PLAN_BATCH_MAPPING);
        
        RP_E_MIM_US2_02Input input = new RP_E_MIM_US2_02Input();
        RP_E_MIM_US2_02Output output = new RP_E_MIM_US2_02Output();
        
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        
        List<Map<String, Object>> listData = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("idClcCust","00001");
        map.put("invoiceTotal","200");
        listData.add(map);
        
        FormFile formFile = getFormFile(true, listData,"testExcel.xlsx");
        
        input.setFormFile(formFile);
        input.setUvo(uvo);
        input.setMonth("01");
        input.setYear("2012");
        
        gClcP01.setAuditIdModule(BillingSystemConstants.MODULE_E);
        gClcP01.setAuditIdSubModule(BillingSystemConstants.SUB_MODULE_E_MIM_US1);
        gClcP01.setAuditReference("");
        gClcP01.setAuditStatus("1");
        
        GlobalProcessResult result = gClcP01.execute(input, output);
        gClcP01.reset();
        
        assertEquals("info.ERR2SC048",(result.getMessages().get().next()).getKey());
    }
    
    /**
     * case14:test ok
     * @throws Exception 
     */
    public void testExecute14() throws Exception{
        String[][] T_CLC_IMP_HD = {
                { "ID_CLC_IMP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
                        "DATE_UPLOADED", "STATUS", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                { "1", "1.xls", "01/2012", 
                   TEST_DAY_TODAY, "0",TEST_DAY_TODAY,TEST_DAY_TODAY, "sysadmin" } };
        String[][] M_GSET_D1 = {
                { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "SET_VALUE", "ID_AUDIT", "SET_APPLY" },
                {
                        "BATCH_TIME_INTERVAL",
                        "1",
                        "The number of hours before the subsequent batch can be executed",
                        "0", "2010-11-24 16:50:44", "2010-11-24 16:50:44",
                        "sysadmin", "2", AUDIT_VALUE, "1" },
                { "BATCH_G_CLC_P01", "1",
                    "EOD - Data Import (SingPost Collection Data)", "0",
                    "2010-11-24 16:50:44", "2011-07-13 11:00:34",
                    "USERFULL", "C:\\", AUDIT_VALUE, "1" }};
        String[][] T_CUST_PLAN_H = {
                { "ID_CUST_PLAN", "ID_CUST", "PLAN_STATUS", "PLAN_TYPE",
                        "APPROVE_TYPE", "TRANSACTION_TYPE", "REFERENCE_PLAN",
                        "ID_BILL_ACCOUNT", "BILL_ACC_ALL", "SVC_LEVEL1",
                        "SVC_LEVEL2", "BILL_INSTRUCT", "PAYMENT_METHOD",
                        "BILL_CURRENCY", "EXPORT_CURRENCY", "FIXED_FOREX",
                        "IS_DISPLAY_EXP_AMT", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN" },
                { "43", "229743", "PS1", "NP", null, "IN", null, "2", "1",
                        "268", "357", "4", "CQ", "MYR", "BAM", "12", "0", "0",
                        "2011-08-02 14:13:46", "2011-08-02 14:13:46",
                        "USERFULL"}, };
        String[][] T_SUBSCRIPTION_INFO = {
                { "ID_CUST_PLAN","DATE_CREATED", "DATE_UPDATED", "ID_LOGIN","IS_DELETED","ID_SUB_INFO"},
                { "43", "2011-8-22  16:38:04", "2011-8-22  16:38:04", "sysadmin", "0",  "00001" } };
        String[][] testDataTCustPlanD =
        {
            {"ID_CUST_PLAN_GRP" , "ID_CUST_PLAN" , "SERVICES_STATUS" ,
                "SVC_START" , "SVC_END" , "AUTO_RENEW" , "MIN_SVC_PERIOD" ,
                "MIN_SVC_START" , "MIN_SVC_END" , "CONTRACT_TERM" ,
                "CONTRACT_TERM_NO" , "PRO_RATE_BASE" , "PRO_RATE_BASE_NO" ,
                "IS_LUMP_SUM" , "BILL_DESC" , "IS_DELETED" ,
                "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
            {"123" , "43" , "PS3" , "2011-08-20" , "2011-08-23" , "0" , "0" ,
                "2011-08-20" , "2011-08-23" , "M" , "1" , "S" , "1" , "0" ,
                null , "0" , "2011-07-28" , "2011-07-28" , "sysadmin" , null}};
        String[][] custPlanLink = {
                { "ID_CUST_PLAN_LINK", "ID_CUST_PLAN_GRP", "ITEM_TYPE",
                        "ITEM_DESC", "QUANTITY", "UNIT_PRICE", "TOTAL_AMOUNT",
                        "JOB_NO", "ID_PLAN", "PLAN_NAME", "PLAN_DESC",
                        "ID_PLAN_GRP", "ITEM_GRP_NAME", "SVC_LEVEL1",
                        "SVC_LEVEL2", "RATE_TYPE", "RATE_MODE", "RATE",
                        "APPLY_GST", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT" },
                { "1", "123", "S", null, "1", "1200", "1200", "JOBNO1", "1",
                        "IDC-DRCenter-001 ", "Disaster Recovery Center", "123",
                        "Yearly Fee", "1", "2", "RA", "1", "1", "0", "0",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, SYS_ADMIN, null } };
        String[][] testM_PLAN_BATCH_MAPPING =
        {
            {"ID_PLAN_GRP" , "ID_PLAN" , "BATCH_ID" , "USAGE_BASE" ,
                "UOM" , "CODE_VALUE" ,"ID_LOGIN","ID_AUDIT"} ,
            {"123" , "123" , "RD" , "0" ,"","0",
                "sysadmin" , "0" }};
        
        super.insertData("T_CLC_IMP_HD", T_CLC_IMP_HD);
        super.insertData("M_GSET_D", M_GSET_D1);
        super.insertData("T_CUST_PLAN_H", T_CUST_PLAN_H);
        super.insertData("T_SUBSCRIPTION_INFO", T_SUBSCRIPTION_INFO);
        super.insertData("T_CUST_PLAN_D", testDataTCustPlanD);
        super.insertData("T_CUST_PLAN_LINK", custPlanLink);
        super.insertData("M_PLAN_BATCH_MAPPING", testM_PLAN_BATCH_MAPPING);
        
        RP_E_MIM_US2_02Input input = new RP_E_MIM_US2_02Input();
        RP_E_MIM_US2_02Output output = new RP_E_MIM_US2_02Output();
        
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        
        List<Map<String, Object>> listData = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("idClcCust","00001");
        map.put("invoiceTotal","200");
        listData.add(map);
        
        FormFile formFile = getFormFile(true, listData,"testExcel.xlsx");
        
        input.setFormFile(formFile);
        input.setUvo(uvo);
        input.setMonth("01");
        input.setYear("2012");
        
        gClcP01.setAuditIdModule(BillingSystemConstants.MODULE_E);
        gClcP01.setAuditIdSubModule(BillingSystemConstants.SUB_MODULE_E_MIM_US1);
        gClcP01.setAuditReference("");
        gClcP01.setAuditStatus("1");
        
        GlobalProcessResult result = gClcP01.execute(input, output);
        gClcP01.reset();
        
        assertEquals("info.ERR2SC048",(result.getMessages().get().next()).getKey());
    }
    
    /**
     * case15:test ok
     * @throws Exception 
     */
    public void testExecute15() throws Exception{
        String[][] T_CLC_IMP_HD = {
                { "ID_CLC_IMP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
                        "DATE_UPLOADED", "STATUS", "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                { "1", "1.xls", "01/2012", 
                   TEST_DAY_TODAY, "0",TEST_DAY_TODAY,TEST_DAY_TODAY, "sysadmin" } };
        String[][] M_GSET_D1 = {
                { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "SET_VALUE", "ID_AUDIT", "SET_APPLY" },
                {
                        "BATCH_TIME_INTERVAL",
                        "1",
                        "The number of hours before the subsequent batch can be executed",
                        "0", "2010-11-24 16:50:44", "2010-11-24 16:50:44",
                        "sysadmin", "2", AUDIT_VALUE, "1" },
                { "BATCH_G_CLC_P01", "1",
                    "EOD - Data Import (SingPost Collection Data)", "0",
                    "2010-11-24 16:50:44", "2011-07-13 11:00:34",
                    "USERFULL", "C:\\", AUDIT_VALUE, "1" }};
        String[][] T_CUST_PLAN_H = {
                { "ID_CUST_PLAN", "ID_CUST", "PLAN_STATUS", "PLAN_TYPE",
                        "APPROVE_TYPE", "TRANSACTION_TYPE", "REFERENCE_PLAN",
                        "ID_BILL_ACCOUNT", "BILL_ACC_ALL", "SVC_LEVEL1",
                        "SVC_LEVEL2", "BILL_INSTRUCT", "PAYMENT_METHOD",
                        "BILL_CURRENCY", "EXPORT_CURRENCY", "FIXED_FOREX",
                        "IS_DISPLAY_EXP_AMT", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN" },
                { "43", "229743", "PS1", "NP", null, "IN", null, "2", "1",
                        "268", "357", "4", "CQ", "MYR", "BAM", "12", "0", "0",
                        "2011-08-02 14:13:46", "2011-08-02 14:13:46",
                        "USERFULL"}, };
        String[][] T_SUBSCRIPTION_INFO = {
                { "ID_CUST_PLAN","DATE_CREATED", "DATE_UPDATED", "ID_LOGIN","IS_DELETED","ID_SUB_INFO"},
                { "43", "2011-8-22  16:38:04", "2011-8-22  16:38:04", "sysadmin", "0",  "00001" } };
        String[][] testDataTCustPlanD =
        {
            {"ID_CUST_PLAN_GRP" , "ID_CUST_PLAN" , "SERVICES_STATUS" ,
                "SVC_START" , "SVC_END" , "AUTO_RENEW" , "MIN_SVC_PERIOD" ,
                "MIN_SVC_START" , "MIN_SVC_END" , "CONTRACT_TERM" ,
                "CONTRACT_TERM_NO" , "PRO_RATE_BASE" , "PRO_RATE_BASE_NO" ,
                "IS_LUMP_SUM" , "BILL_DESC" , "IS_DELETED" ,
                "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
            {"123" , "43" , "PS3" , "2011-08-20" , "2011-08-23" , "0" , "0" ,
                "2011-08-20" , "2011-08-23" , "M" , "1" , "S" , "1" , "0" ,
                null , "0" , "2011-07-28" , "2011-07-28" , "sysadmin" , null}};
        String[][] custPlanLink = {
                { "ID_CUST_PLAN_LINK", "ID_CUST_PLAN_GRP", "ITEM_TYPE",
                        "ITEM_DESC", "QUANTITY", "UNIT_PRICE", "TOTAL_AMOUNT",
                        "JOB_NO", "ID_PLAN", "PLAN_NAME", "PLAN_DESC",
                        "ID_PLAN_GRP", "ITEM_GRP_NAME", "SVC_LEVEL1",
                        "SVC_LEVEL2", "RATE_TYPE", "RATE_MODE", "RATE",
                        "APPLY_GST", "IS_DELETED", "DATE_CREATED",
                        "DATE_UPDATED", "ID_LOGIN", "ID_AUDIT" },
                { "1", "123", "S", null, "1", "1200", "1200", "JOBNO1", "1",
                        "IDC-DRCenter-001 ", "Disaster Recovery Center", "123",
                        "Yearly Fee", "1", "2", "RA", "1", "1", "0", "0",
                        TEST_DAY_TODAY, TEST_DAY_TODAY, SYS_ADMIN, null } };
        String[][] testM_PLAN_BATCH_MAPPING =
        {
            {"ID_PLAN_GRP" , "ID_PLAN" , "BATCH_ID" , "USAGE_BASE" ,
                "UOM" , "CODE_VALUE" ,"ID_LOGIN","ID_AUDIT"} ,
            {"123" , "123" , "RD" , "0" ,"","0",
                "sysadmin" , "0" }};
        
        super.insertData("T_CLC_IMP_HD", T_CLC_IMP_HD);
        super.insertData("M_GSET_D", M_GSET_D1);
        super.insertData("T_CUST_PLAN_H", T_CUST_PLAN_H);
        super.insertData("T_SUBSCRIPTION_INFO", T_SUBSCRIPTION_INFO);
        super.insertData("T_CUST_PLAN_D", testDataTCustPlanD);
        super.insertData("T_CUST_PLAN_LINK", custPlanLink);
        super.insertData("M_PLAN_BATCH_MAPPING", testM_PLAN_BATCH_MAPPING);
        
        RP_E_MIM_US2_02Input input = new RP_E_MIM_US2_02Input();
        RP_E_MIM_US2_02Output output = new RP_E_MIM_US2_02Output();
        
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        
        List<Map<String, Object>> listData = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("idClcCust","00001");
        map.put("invoiceTotal","200");
        listData.add(map);
        
        FormFile formFile = getFormFile(true, listData,"testExcel.xlsx");
        
        input.setFormFile(formFile);
        input.setUvo(uvo);
        input.setMonth("02");
        input.setYear("2012");
        
        gClcP01.setAuditIdModule(BillingSystemConstants.MODULE_E);
        gClcP01.setAuditIdSubModule(BillingSystemConstants.SUB_MODULE_E_MIM_US1);
        gClcP01.setAuditReference("");
        gClcP01.setAuditStatus("1");
        
        GlobalProcessResult result = gClcP01.execute(input, output);
        gClcP01.reset();
        
        assertEquals("info.ERR2SC048",(result.getMessages().get().next()).getKey());
    }
    
    /**
     * case16:test readFile function
     * @throws Exception 
     */
    public void testExecute16() throws Exception{
        
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        
        List<Map<String, Object>> listData = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("idClcCust","00001");
        map.put("invoiceTotal","200");
        listData.add(map);
        
        FormFile formFile = getFormFile(true, listData,"testExcel.xlsx");
        
        gClcP01.readFile(formFile, ',');
        //assertEquals(3, result.size());
    }
    
    @SuppressWarnings("rawtypes")
    private FormFile getFormFile(boolean isExcel2007,List<Map<String, Object>> listData, String fileName) throws Exception {
        Class parentClass = Class.forName("org.apache.struts.upload.CommonsMultipartRequestHandler");
        Class childClass = parentClass.getDeclaredClasses()[0];
        Constructor c = childClass.getConstructors()[0];
        c.setAccessible(true);
        FileItemFactory factory = new DiskFileItemFactory(16, null);
        FileItem fileItem = factory.createItem("excelField", "application/vnd.ms-excel", true,fileName);
        OutputStream os = fileItem.getOutputStream();
        printExcel(isExcel2007, listData).write(os);
        os.flush();
        os.close();
        FormFile file = (FormFile) c.newInstance(new Object[] {fileItem });
        return file;
    }
    
    @SuppressWarnings("rawtypes")
    private FormFile getFormFile1(boolean isExcel2007,List<Map<String, Object>> listData, String fileName) throws Exception {
        Class parentClass = Class.forName("org.apache.struts.upload.CommonsMultipartRequestHandler");
        Class childClass = parentClass.getDeclaredClasses()[0];
        Constructor c = childClass.getConstructors()[0];
        c.setAccessible(true);
        FileItemFactory factory = new DiskFileItemFactory(16, null);
        FileItem fileItem = factory.createItem("excelField", "application/vnd.ms-excel", true,fileName);
        OutputStream os = fileItem.getOutputStream();
        printExcel1(isExcel2007, listData).write(os);
        os.flush();
        os.close();
        FormFile file = (FormFile) c.newInstance(new Object[] {fileItem });
        return file;
    }
    
    private Workbook printExcel(boolean isExcel2007,List<Map<String, Object>> listData){
        Workbook workbook = null;
        try{
              if(isExcel2007){
                  workbook = new XSSFWorkbook();
              } else {
                  workbook = new HSSFWorkbook();
              }
              Sheet sheet = workbook.createSheet("testSheet"); 
              Row row = sheet.createRow(0);
              this.createCell(row, 0, null, Cell.CELL_TYPE_STRING, "ID_CLC_CUST");
              this.createCell(row, 1, null, Cell.CELL_TYPE_STRING, "INVOICE_TOTAL");
              for(int i=0; i<listData.size(); i++) {
                  Row rowData1 = sheet.createRow(i+1);
                  Map<String, Object> map = listData.get(i);
                  this.createCell(rowData1, 0, null, Cell.CELL_TYPE_STRING, map.get("idClcCust"));
                  String invoiceTotal = CommonUtils.toString(map.get("invoiceTotal"));
                  if (CommonUtils.isEmpty(invoiceTotal)) {
                      this.createCell(rowData1, 1, null, Cell.CELL_TYPE_STRING, map.get("invoiceTotal"));
                  } else {
                      try{
                          Double.parseDouble(invoiceTotal);
                          this.createCell(rowData1, 1, null, Cell.CELL_TYPE_NUMERIC, map.get("invoiceTotal"));
                      }catch(Exception e) {
                          this.createCell(rowData1, 1, null, Cell.CELL_TYPE_STRING, map.get("invoiceTotal"));
                      }
                  }
              }
         }catch(Exception e){
                     e.printStackTrace();
         }
        return workbook;
    }
    
    private Workbook printExcel1(boolean isExcel2007,List<Map<String, Object>> listData){
        Workbook workbook = null;
        try{
              if(isExcel2007){
                  workbook = new XSSFWorkbook();
              } else {
                  workbook = new HSSFWorkbook();
              }
              Sheet sheet = workbook.createSheet("testSheet"); 
              Row row = sheet.createRow(0);
              this.createCell1(row, 0, null, Cell.CELL_TYPE_STRING, "ID_CLC_CUST");
              this.createCell1(row, 1, null, Cell.CELL_TYPE_STRING, "INVOICE_TOTAL");
              
              Row rowData1 = sheet.createRow(1);
              this.createCell1(rowData1, 0, null, Cell.CELL_TYPE_BOOLEAN, true);
              Cell cell = rowData1.createCell(1); 
              cell.setCellType(Cell.CELL_TYPE_NUMERIC); 
              cell.setCellValue(Calendar.getInstance().getTime());
              
              Row rowData2 = sheet.createRow(2);
              this.createCell1(rowData2, 0, null, Cell.CELL_TYPE_FORMULA, "");
              this.createCell1(rowData2, 1, null, Cell.CELL_TYPE_BLANK, "");
         }catch(Exception e){
                     e.printStackTrace();
         }
        return workbook;
    }
  
    private void createCell(Row row, int column, CellStyle style,int cellType,Object value) { 
         Cell cell = row.createCell(column); 
         if (style != null) { 
              cell.setCellStyle(style); 
         }   
         switch(cellType){ 
              case Cell.CELL_TYPE_BLANK: {} break; 
              case Cell.CELL_TYPE_STRING: {cell.setCellValue(value.toString());} break; 
              case Cell.CELL_TYPE_NUMERIC: {
                  cell.setCellType(Cell.CELL_TYPE_NUMERIC); 
                  cell.setCellValue(Double.parseDouble(value.toString()));
              }break; 
              default: break; 
        }  
    }
    
    private void createCell1(Row row, int column, CellStyle style,int cellType,Object value) { 
        Cell cell = row.createCell(column); 
        if (style != null) { 
             cell.setCellStyle(style); 
        }   
        switch(cellType){ 
             case Cell.CELL_TYPE_BLANK: {} break; 
             case Cell.CELL_TYPE_STRING: {cell.setCellValue(value.toString());} break; 
             case Cell.CELL_TYPE_NUMERIC: {
                 cell.setCellType(Cell.CELL_TYPE_NUMERIC); 
                 cell.setCellValue(Double.parseDouble(value.toString()));
             }break; 
             case Cell.CELL_TYPE_BOOLEAN: {
                 cell.setCellType(Cell.CELL_TYPE_BOOLEAN); 
                 cell.setCellValue(value.toString());} break; 
             case Cell.CELL_TYPE_FORMULA: {
                 cell.setCellType(Cell.CELL_TYPE_FORMULA); 
                 cell.setCellValue(value.toString());} break; 
             default: break; 
       }  
   }
}
