/**
 * @(#)B_CSBS02_NEBLogic_Test.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2011/09/14
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.b_csb.blogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.b_csb.dto.Debit_info_bean;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.AbstractUtilTest;
import nttdm.bsys.common.util.CommonUtils;

/**
 * @author gplai
 *
 */
public class B_CSBS02_NEBLogic_Test extends AbstractUtilTest {

    /**
     * B_CSBS02_NEBLogic
     */
    private B_CSBS02_NEBLogic logic;
    /**
     * the B_CSBR01BLogic's paramter
     */
    private Map<String, Object> input ;
    
    @Override
    protected void setUpData() throws Exception {
        logic = new B_CSBS02_NEBLogic();
        input = new HashMap<String,Object>();
        logic.setQueryDAO(queryDAO);
        logic.setUpdateDAO(updateDAO);
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        uvo.setUser_name("system admin");
        input.put("uvo", uvo);
    }

    public void testExecute1(){
        input.put("pattern", "CST");
        
        List<Debit_info_bean> debitInfos = new ArrayList<Debit_info_bean>();
        Debit_info_bean debit = new Debit_info_bean();
        debit.setDATE_REQ("05/01/2011");
        debit.setBILL_CURRENCY("MYR");
        debit.setBILL_AMOUNT("606");
        debit.setID_REF("1");
        debit.setAMT_PAID("51");
        debit.setAMT_DUE("0");
        debit.setFOREX_LOSS("1");
        debit.setFOREX_GAIN("21");
        debit.setAPPLIED("1");
        debitInfos.add(debit);
        
        Debit_info_bean debit1 = new Debit_info_bean();
        debit1.setDATE_REQ("05/01/2011");
        debit1.setBILL_CURRENCY("MYR");
        debit1.setBILL_AMOUNT("606");
        debit1.setID_REF("2");
        debit1.setAMT_PAID("aaa");
        debit1.setAMT_DUE("606");
        debit1.setFOREX_LOSS("aa");
        debit1.setFOREX_GAIN("bb");
        debit1.setAPPLIED("1");
        debitInfos.add(debit1);
        input.put("debitInfos", debitInfos);
        
        BLogicResult result = logic.execute(input);
        
        assertEquals("failer", result.getResultString());
    }
    
    public void testExecute15(){
        input.put("pattern", "CST");
        input.put("pmtMethod", "NT");
        input.put("payer", "3232");
        input.put("pattern", "CST");
        
        List<Debit_info_bean> debitInfos = new ArrayList<Debit_info_bean>();
        Debit_info_bean debit = new Debit_info_bean();
        debit.setDATE_REQ("05/01/2011");
        debit.setBILL_CURRENCY("MYR");
        debit.setBILL_AMOUNT("606");
        debit.setID_REF("1");
        debit.setAMT_PAID("51");
        debit.setAMT_DUE("0");
        debit.setFOREX_LOSS("1");
        debit.setFOREX_GAIN("21");
        debit.setAPPLIED("1");
        debitInfos.add(debit);
        
        Debit_info_bean debit1 = new Debit_info_bean();
        debit1.setDATE_REQ("05/01/2011");
        debit1.setBILL_CURRENCY("MYR");
        debit1.setBILL_AMOUNT("606");
        debit1.setID_REF("2");
        debit1.setAMT_PAID("aaa");
        debit1.setAMT_DUE("606");
        debit1.setFOREX_LOSS("aa");
        debit1.setFOREX_GAIN("bb");
        debit1.setAPPLIED("1");
        debitInfos.add(debit1);
        input.put("debitInfos", debitInfos);
        
        BLogicResult result = logic.execute(input);
        
        assertEquals("failer", result.getResultString());
    }
    public void testExecute16(){
        input.put("pattern", "CST");
        input.put("pmtMethod", "CC");
        input.put("pattern", "CST");
        
        List<Debit_info_bean> debitInfos = new ArrayList<Debit_info_bean>();
        Debit_info_bean debit = new Debit_info_bean();
        debit.setDATE_REQ("05/01/2011");
        debit.setBILL_CURRENCY("MYR");
        debit.setBILL_AMOUNT("606");
        debit.setID_REF("1");
        debit.setAMT_PAID("51");
        debit.setAMT_DUE("0");
        debit.setFOREX_LOSS("1");
        debit.setFOREX_GAIN("21");
        debit.setAPPLIED("1");
        debitInfos.add(debit);
        
        Debit_info_bean debit1 = new Debit_info_bean();
        debit1.setDATE_REQ("05/01/2011");
        debit1.setBILL_CURRENCY("MYR");
        debit1.setBILL_AMOUNT("606");
        debit1.setID_REF("2");
        debit1.setAMT_PAID("aaa");
        debit1.setAMT_DUE("606");
        debit1.setFOREX_LOSS("aa");
        debit1.setFOREX_GAIN("bb");
        debit1.setAPPLIED("1");
        debitInfos.add(debit1);
        input.put("debitInfos", debitInfos);
        
        BLogicResult result = logic.execute(input);
        
        assertEquals("failer", result.getResultString());
    }
    
    public void testExecute2(){
        input.put("pattern", "CST");
        input.put("payer", "-1");
        input.put("amtReceived", "dsa");
        input.put("bankCharge", "fdsf");
        
        BLogicResult result = logic.execute(input);
        assertEquals("failer", result.getResultString());
    }
    
    public void testExecute3(){
        input.put("pattern", "BAC");
        input.put("bankCharge", "");
        BLogicResult result = logic.execute(input);
        assertEquals("failer", result.getResultString());
    }
    
    public void testExecute4(){
        input.put("pattern", "BAC");
        input.put("amtReceived", "dsa");
        input.put("bankCharge", "fdsf");
        BLogicResult result = logic.execute(input);
        assertEquals("failer", result.getResultString());
    }
    
    public void testExecute5(){
        String receiptNo = "111238              ";
        input.put("receiptNo", receiptNo);
        input.put("pattern", "CST");
        input.put("action", "edit");
        input.put("bankCharge", "fdsf");
        BLogicResult result = logic.execute(input);
        assertEquals("failer", result.getResultString());
    }
    
    public void testExecute6(){
        String receiptNo = "111238              ";
        input.put("receiptNo", receiptNo);
        input.put("pattern", "CST");
        input.put("action", "edit");
        input.put("amtReceived", "dsa");
        BLogicResult result = logic.execute(input);
        assertEquals("failer", result.getResultString());
    }
    
    public void testExecute7(){
        
        //
        insertData7();
        
        input.put("pattern", "CST");
        input.put("pmtMethod", "CQ");
        input.put("payer", "229743");
        input.put("curCode", "MYR");
        input.put("transDate", "05/09/2011");
        input.put("amtReceived", "2331");
        
        List<Debit_info_bean> debitInfos = new ArrayList<Debit_info_bean>();
        Debit_info_bean debit = new Debit_info_bean();
        debit.setDATE_REQ("05/01/2011");
        debit.setBILL_CURRENCY("MYR");
        debit.setBILL_AMOUNT("606");
        debit.setID_REF("1");
        debit.setAMT_PAID("606");
        debit.setAMT_DUE("606");
        debit.setFOREX_LOSS("1");
        debit.setFOREX_GAIN("21");
        debit.setAPPLIED("1");
        debitInfos.add(debit);

        input.put("debitInfos", debitInfos);
        
        BLogicResult result = logic.execute(input);
        assertEquals("failer", result.getResultString());
    }
    
    public void testExecute8(){
        
        //
        insertData8();
        
        input.put("pattern", "BAC");
        input.put("pmtMethod", "CQ");
        input.put("payer", "229743");
        input.put("curCode", "MYR");
        input.put("transDate", "05/09/2011");
        input.put("amtReceived", "2331");
        input.put("bankAcc", "1234");
        input.put("other", "test");
        input.put("idBillAccount", "3");
        input.put("receiptRef", "ewe11111");
        input.put("remark", "remark");
        input.put("paymentStatus", "N");
        input.put("balanceAmt", "45");
        input.put("paymentRef1", "paymentRef1 info");
        input.put("paymentRef2", "paymentRef2 info");
        input.put("bankCharge", "12.1");
        input.put("overPaidText", "1");
        input.put("paidPreInvoiceText", "1");
        
        List<Debit_info_bean> debitInfos = new ArrayList<Debit_info_bean>();
        Debit_info_bean debit = new Debit_info_bean();
        debit.setDATE_REQ("05/01/2011");
        debit.setBILL_CURRENCY("MYR");
        debit.setBILL_AMOUNT("606");
        debit.setID_REF("1");
        debit.setAMT_PAID("51");
        debit.setAMT_DUE("606");
        debit.setFOREX_LOSS("1");
        debit.setFOREX_GAIN("21");
        debit.setAPPLIED("1");
        debitInfos.add(debit);

        input.put("debitInfos", debitInfos);
        
        BLogicResult result = logic.execute(input);
        assertEquals("success", result.getResultString());
        Map<String,Object> csbHData =  queryDAO.executeForMap("TEST.BSYS.CSB_S02.SQL004", null);
       
        assertEquals("229743", csbHData.get("ID_CUST"));
        assertEquals("1234", CommonUtils.toString(csbHData.get("ID_COM_BANK")));
        assertEquals("test", csbHData.get("OTHER_PAYER"));
        assertEquals("CQ", csbHData.get("PMT_METHOD"));
        assertEquals("2011-09-05", CommonUtils.toString(csbHData.get("DATE_TRANS")));
        assertEquals("ewe11111", csbHData.get("RECEIPT_REF"));
        assertEquals("MYR", csbHData.get("CUR_CODE"));
        assertEquals("2331", CommonUtils.toString(csbHData.get("AMT_RECEIVED")));
        assertEquals("remark", csbHData.get("REMARK"));
        assertEquals("N", csbHData.get("PMT_STATUS"));
        assertEquals("2300", CommonUtils.toString(csbHData.get("BALANCE_AMT")));
        assertEquals("paymentRef1 info", csbHData.get("REFERENCE1"));
        assertEquals("paymentRef2 info", csbHData.get("REFERENCE2"));
        assertEquals("N", csbHData.get("PMT_STATUS"));
        assertEquals("12.1", CommonUtils.toString(csbHData.get("BANK_CHARGE")));
        assertEquals("1", csbHData.get("PAID_PRE_INVOICE"));
        assertEquals("1", csbHData.get("OVER_PAID"));
        assertEquals("0", csbHData.get("IS_EXPORT"));
        assertEquals("0", csbHData.get("IS_DELETED"));
        assertEquals("0", csbHData.get("IS_CLOSED"));
        
        Map<String,Object> csbDData =  queryDAO.executeForMap("TEST.BSYS.CSB_S02.SQL005", null);
        assertEquals("51", CommonUtils.toString(csbDData.get("AMT_PAID")));
        assertEquals("1", CommonUtils.toString(csbDData.get("ID_REF")).trim());
        assertEquals("606", CommonUtils.toString(csbDData.get("AMT_DUE")));
        assertEquals("1", CommonUtils.toString(csbDData.get("FOREX_LOSS")));
        assertEquals("21", CommonUtils.toString(csbDData.get("FOREX_GAIN")));
        
        Map<String,Object> bilHDData =  queryDAO.executeForMap("TEST.BSYS.CSB_S02.SQL002", "1                   ");
        assertEquals(TEST_DAY_TODAY, CommonUtils.toString(bilHDData.get("LAST_PAYMENT_DATE")));
        //assertEquals("102", CommonUtils.toString(bilHDData.get("PAID_AMOUNT")));
        assertEquals("0", CommonUtils.toString(bilHDData.get("IS_SETTLED")));
        
        Map<String,Object> bacHDData =  queryDAO.executeForMap("TEST.BSYS.CSB_S02.SQL003", "3");
        assertEquals("1000", CommonUtils.toString(bacHDData.get("TOTAL_AMT_DUE")));
    }
    
    public void testExecute9(){
        
        //
        insertData9();
        
        input.put("pattern", "BAC");
        input.put("pmtMethod", "CQ");
        input.put("payer", "229743");
        input.put("curCode", "MYR");
        input.put("transDate", "05/09/2011");
        input.put("amtReceived", "2331");
        input.put("bankAcc", "1234");
        input.put("other", "test");
        input.put("idBillAccount", "3");
        input.put("receiptRef", "ewe11111");
        input.put("remark", "remark");
        input.put("paymentStatus", "N");
        input.put("balanceAmt", "45");
        input.put("paymentRef1", "paymentRef1 info");
        input.put("paymentRef2", "paymentRef2 info");
        input.put("bankCharge", "12.1");
        input.put("overPaidText", "1");
        input.put("paidPreInvoiceText", "1");
        
        List<Debit_info_bean> debitInfos = new ArrayList<Debit_info_bean>();
        Debit_info_bean debit = new Debit_info_bean();
        debit.setDATE_REQ("05/01/2011");
        debit.setBILL_CURRENCY("MYR");
        debit.setBILL_AMOUNT("606");
        debit.setID_REF("1");
        debit.setAMT_PAID("555");
        debit.setAMT_DUE("606");
        debit.setFOREX_LOSS("1");
        debit.setFOREX_GAIN("21");
        debit.setAPPLIED("1");
        debitInfos.add(debit);

        input.put("debitInfos", debitInfos);
        
        BLogicResult result = logic.execute(input);
        assertEquals("success", result.getResultString());
        Map<String,Object> csbHData =  queryDAO.executeForMap("TEST.BSYS.CSB_S02.SQL004", null);
       
        assertEquals("229743", csbHData.get("ID_CUST"));
        assertEquals("1234", CommonUtils.toString(csbHData.get("ID_COM_BANK")));
        assertEquals("test", csbHData.get("OTHER_PAYER"));
        assertEquals("CQ", csbHData.get("PMT_METHOD"));
        assertEquals("2011-09-05", CommonUtils.toString(csbHData.get("DATE_TRANS")));
        assertEquals("ewe11111", csbHData.get("RECEIPT_REF"));
        assertEquals("MYR", csbHData.get("CUR_CODE"));
        assertEquals("2331", CommonUtils.toString(csbHData.get("AMT_RECEIVED")));
        assertEquals("remark", csbHData.get("REMARK"));
        assertEquals("N", csbHData.get("PMT_STATUS"));
        assertEquals("1796", CommonUtils.toString(csbHData.get("BALANCE_AMT")));
        assertEquals("paymentRef1 info", csbHData.get("REFERENCE1"));
        assertEquals("paymentRef2 info", csbHData.get("REFERENCE2"));
        assertEquals("N", csbHData.get("PMT_STATUS"));
        assertEquals("12.1", CommonUtils.toString(csbHData.get("BANK_CHARGE")));
        assertEquals("1", csbHData.get("PAID_PRE_INVOICE"));
        assertEquals("1", csbHData.get("OVER_PAID"));
        assertEquals("0", csbHData.get("IS_EXPORT"));
        assertEquals("0", csbHData.get("IS_DELETED"));
        assertEquals("0", csbHData.get("IS_CLOSED"));
        
        Map<String,Object> csbDData =  queryDAO.executeForMap("TEST.BSYS.CSB_S02.SQL005", null);
        assertEquals("555", CommonUtils.toString(csbDData.get("AMT_PAID")));
        assertEquals("1", CommonUtils.toString(csbDData.get("ID_REF")).trim());
        assertEquals("606", CommonUtils.toString(csbDData.get("AMT_DUE")));
        assertEquals("1", CommonUtils.toString(csbDData.get("FOREX_LOSS")));
        assertEquals("21", CommonUtils.toString(csbDData.get("FOREX_GAIN")));
        
        Map<String,Object> bilHDData =  queryDAO.executeForMap("TEST.BSYS.CSB_S02.SQL002", "1                   ");
        assertEquals("2011-09-05", CommonUtils.toString(bilHDData.get("LAST_PAYMENT_DATE")));
        //assertEquals("102", CommonUtils.toString(bilHDData.get("PAID_AMOUNT")));
        assertEquals("1", CommonUtils.toString(bilHDData.get("IS_SETTLED")));
        
        Map<String,Object> bacHDData =  queryDAO.executeForMap("TEST.BSYS.CSB_S02.SQL003", "3");
        assertEquals("1000", CommonUtils.toString(bacHDData.get("TOTAL_AMT_DUE")));
    }
    
    public void testExecute10(){
        
        //
        insertData10();
        String receiptNo = "111238              ";
        input.put("receiptNo", receiptNo);
        input.put("action", "edit");
        input.put("pattern", "BAC");
        input.put("pmtMethod", "CQ");
        input.put("payer", "229743");
        input.put("curCode", "MYR");
        input.put("transDate", "05/09/2011");
        input.put("amtReceived", "2331");
        input.put("bankAcc", "1234");
        input.put("other", "test");
        input.put("idBillAccount", "3");
        input.put("receiptRef", "");
        input.put("remark", "");
        input.put("paymentStatus", "N");
        input.put("balanceAmt", "45");
        input.put("paymentRef1", "");
        input.put("paymentRef2", "");
        input.put("bankCharge", "");
        input.put("overPaidText", "0");
        input.put("paidPreInvoiceText", "0");
        
        List<Debit_info_bean> debitInfos = new ArrayList<Debit_info_bean>();
        Debit_info_bean debit = new Debit_info_bean();
        debit.setDATE_REQ("05/01/2011");
        debit.setBILL_CURRENCY("MYR");
        debit.setBILL_AMOUNT("606");
        debit.setID_REF("1");
        debit.setAMT_PAID("51");
        debit.setAMT_DUE("606");
        debit.setFOREX_LOSS("1");
        debit.setFOREX_GAIN("21");
        debit.setAPPLIED("1");
        debitInfos.add(debit);
        
        Debit_info_bean debit1 = new Debit_info_bean();
        debit1.setDATE_REQ("27/01/2011");
        debit1.setBILL_CURRENCY("MYR");
        debit1.setBILL_AMOUNT("3500");
        debit1.setID_REF("1");
        debit1.setAMT_PAID("3");
        debit1.setAMT_DUE("3500");
        debit1.setFOREX_LOSS("1");
        debit1.setFOREX_GAIN("2");
        debit1.setAPPLIED("0");
        debitInfos.add(debit1);

        input.put("debitInfos", debitInfos);
        
        BLogicResult result = logic.execute(input);
        assertEquals("success", result.getResultString());
        Map<String,Object> csbHData =  queryDAO.executeForMap("TEST.BSYS.CSB_S02.SQL001", receiptNo);
       
        assertEquals("229743", csbHData.get("ID_CUST"));
        assertEquals("1234", CommonUtils.toString(csbHData.get("ID_COM_BANK")));
        assertEquals("test", csbHData.get("OTHER_PAYER"));
        assertEquals("CQ", csbHData.get("PMT_METHOD"));
        assertEquals(null, csbHData.get("RECEIPT_REF"));
        assertEquals("MYR", csbHData.get("CUR_CODE"));
        assertEquals("2331", CommonUtils.toString(csbHData.get("AMT_RECEIVED")));
        assertEquals(null, csbHData.get("REMARK"));
        assertEquals("N", csbHData.get("PMT_STATUS"));
        assertEquals("2300", CommonUtils.toString(csbHData.get("BALANCE_AMT")));
        assertEquals(null, csbHData.get("REFERENCE1"));
        assertEquals(null, csbHData.get("REFERENCE2"));
        assertEquals("N", csbHData.get("PMT_STATUS"));
        assertEquals("0", CommonUtils.toString(csbHData.get("BANK_CHARGE")));
        assertEquals("0", csbHData.get("PAID_PRE_INVOICE"));
        assertEquals("0", csbHData.get("OVER_PAID"));
        assertEquals("0", csbHData.get("IS_EXPORT"));
        assertEquals("0", csbHData.get("IS_DELETED"));
        assertEquals("0", csbHData.get("IS_CLOSED"));
        
        List<Map<String,Object>> csbDData =  queryDAO.executeForMapList("TEST.BSYS.CSB_S02.SQL005", null);
        
        Map<String,Object> bilHDData =  queryDAO.executeForMap("TEST.BSYS.CSB_S02.SQL002", "1                   ");
        
        Map<String,Object> bacHDData =  queryDAO.executeForMap("TEST.BSYS.CSB_S02.SQL003", "3");
        assertEquals("3331", CommonUtils.toString(bacHDData.get("TOTAL_AMT_DUE")));
    }
    
    public void testExecute11(){
        
        //
        insertData11();
        String receiptNo = "111238              ";
        input.put("receiptNo", receiptNo);
        input.put("action", "edit");
        input.put("pattern", "BAC");
        input.put("pmtMethod", "CQ");
        input.put("payer", "229743");
        input.put("curCode", "MYR");
        input.put("transDate", "05/09/2011");
        input.put("amtReceived", "2331");
        input.put("bankAcc", "1234");
        input.put("other", "test");
        input.put("idBillAccount", "3");
        input.put("receiptRef", "");
        input.put("remark", "");
        input.put("paymentStatus", "N");
        input.put("balanceAmt", "45");
        input.put("paymentRef1", "");
        input.put("paymentRef2", "");
        input.put("bankCharge", "");
        input.put("overPaidText", "0");
        input.put("paidPreInvoiceText", "0");
        
        List<Debit_info_bean> debitInfos = new ArrayList<Debit_info_bean>();
        Debit_info_bean debit = new Debit_info_bean();
        debit.setDATE_REQ("05/01/2011");
        debit.setBILL_CURRENCY("MYR");
        debit.setBILL_AMOUNT("606");
        debit.setID_REF("1");
        debit.setAMT_PAID("51");
        debit.setAMT_DUE("606");
        debit.setFOREX_LOSS("1");
        debit.setFOREX_GAIN("21");
        debit.setAPPLIED("1");
        debitInfos.add(debit);
        
        Debit_info_bean debit1 = new Debit_info_bean();
        debit1.setDATE_REQ("27/01/2011");
        debit1.setBILL_CURRENCY("MYR");
        debit1.setBILL_AMOUNT("3500");
        debit1.setID_REF("1");
        debit1.setAMT_PAID("3");
        debit1.setAMT_DUE("3500");
        debit1.setFOREX_LOSS("1");
        debit1.setFOREX_GAIN("2");
        debit1.setAPPLIED("0");
        debitInfos.add(debit1);

        input.put("debitInfos", debitInfos);
        
        BLogicResult result = logic.execute(input);
        assertEquals("success", result.getResultString());
        Map<String,Object> csbHData =  queryDAO.executeForMap("TEST.BSYS.CSB_S02.SQL001", receiptNo);
       
        assertEquals("229743", csbHData.get("ID_CUST"));
        assertEquals("1234", CommonUtils.toString(csbHData.get("ID_COM_BANK")));
        assertEquals("test", csbHData.get("OTHER_PAYER"));
        assertEquals("CQ", csbHData.get("PMT_METHOD"));
        assertEquals(null, csbHData.get("RECEIPT_REF"));
        assertEquals("MYR", csbHData.get("CUR_CODE"));
        assertEquals("2331", CommonUtils.toString(csbHData.get("AMT_RECEIVED")));
        assertEquals(null, csbHData.get("REMARK"));
        assertEquals("N", csbHData.get("PMT_STATUS"));
        assertEquals("2300", CommonUtils.toString(csbHData.get("BALANCE_AMT")));
        assertEquals(null, csbHData.get("REFERENCE1"));
        assertEquals(null, csbHData.get("REFERENCE2"));
        assertEquals("N", csbHData.get("PMT_STATUS"));
        assertEquals("0", CommonUtils.toString(csbHData.get("BANK_CHARGE")));
        assertEquals("0", csbHData.get("PAID_PRE_INVOICE"));
        assertEquals("0", csbHData.get("OVER_PAID"));
        assertEquals("0", csbHData.get("IS_EXPORT"));
        assertEquals("0", csbHData.get("IS_DELETED"));
        assertEquals("0", csbHData.get("IS_CLOSED"));
        
        List<Map<String,Object>> csbDData =  queryDAO.executeForMapList("TEST.BSYS.CSB_S02.SQL005", null);
        assertEquals(1, csbDData.size());
        
        Map<String,Object> bilHDData =  queryDAO.executeForMap("TEST.BSYS.CSB_S02.SQL002", "1                   ");
        
        Map<String,Object> bacHDData =  queryDAO.executeForMap("TEST.BSYS.CSB_S02.SQL003", "3");
        assertEquals("3331", CommonUtils.toString(bacHDData.get("TOTAL_AMT_DUE")));
    }
    
    private void insertData7(){
        super.deleteAllData("T_BIL_D");
        super.deleteAllData("T_BIL_H");
        
        String[][] dataT_BIL_H1 ={{ "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
            "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE",
            "ID_BIF", "ID_QCS", "QUO_REF", "CUST_PO", 
            "ID_CONSULT","TERM", "BILL_CURRENCY", "GST_PERCENT", 
            "GST_AMOUNT","BILL_AMOUNT", "PAID_AMOUNT", "LAST_PAYMENT_DATE",
            "IS_SETTLED", "IS_SINGPOST", "IS_EXPORT","IS_DISPLAY_EXP_AMT", 
            "EXPORT_CUR", "CUR_RATE","EXPORT_AMOUNT", "FIXED_FOREX", 
            "NO_PRINTED","DATE_PRINTED", "USER_PRINTED", "IS_CLOSED",
            "ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
            "ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
            "CONTACT_NAME", "CONTACT_EMAIL", "IS_DELETED","PREPARED_BY", 
            "DATE_CREATED", "DATE_UPDATED","ID_LOGIN", "ID_AUDIT" },
            { "1                   ", "IN", "1", "", "2011-01-05", 
                "CQ", "229743", "BA","PC", 
                null, null, null, null, 
                "BBBBBBBBBB", "30 Days","MYR", "5", 
                "1", "606", "51", TEST_DAY_TODAY, 
                "1", "0","0", "1", 
                "MYR", "3.124", "606", null, 
                "1","2011-01-05", "joeykan", "0",
                "Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
                "2000", "ddd", "11", "", 
                "Mr.Timothy Yap", "", "0","w.h.wong", 
                TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",null } };
        super.insertData("T_BIL_H", dataT_BIL_H1);
    }
    
    private void insertData8(){
        super.deleteAllData("M_CODE");
        
        super.deleteAllData("T_CSB_D");
        super.deleteAllData("T_CSB_H");
        
        super.deleteAllData("T_BIL_D");
        super.deleteAllData("T_BIL_H");
        
        super.deleteAllData("T_BAC_D");
        super.deleteAllData("T_BAC_H");
        
        String[][] dataM_CODE1 = {{
            "ID_CODE","ID_SUB_CODE","TYPE_VAL","INIT_VAL","CUR_VAL","IS_DELETED","DATE_CREATED","DATE_UPDATED","ID_LOGIN","ID_AUDIT"
        },{
            "RCPNO","1","01","11","2011","0","2010-11-26","2011-08-19","sysadmin",null
        }};
        String[][] dataM_CODE2 = {{
            "ID_CODE","ID_SUB_CODE","TYPE_VAL","INIT_VAL","CUR_VAL","IS_DELETED","DATE_CREATED","DATE_UPDATED","ID_LOGIN","ID_AUDIT"
        },{
            "RCPNO","2","06","","123456789012","0","2010-11-26","2011-08-19","sysadmin",null
        }};
        String[][] dataM_CODE3 = {{
            "ID_CODE","ID_SUB_CODE","TYPE_VAL","INIT_VAL","CUR_VAL","IS_DELETED","DATE_CREATED","DATE_UPDATED","ID_LOGIN","ID_AUDIT"
        },{
            "RCPNO","3","05","1234","1252","0","2010-11-26","2011-08-19","sysadmin",null
        }};
        
        String[][] dataT_BIL_H1 ={{ "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
            "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE",
            "ID_BIF", "ID_QCS", "QUO_REF", "CUST_PO", 
            "ID_CONSULT","TERM", "BILL_CURRENCY", "GST_PERCENT", 
            "GST_AMOUNT","BILL_AMOUNT", "PAID_AMOUNT", "LAST_PAYMENT_DATE",
            "IS_SETTLED", "IS_SINGPOST", "IS_EXPORT","IS_DISPLAY_EXP_AMT", 
            "EXPORT_CUR", "CUR_RATE","EXPORT_AMOUNT", "FIXED_FOREX", 
            "NO_PRINTED","DATE_PRINTED", "USER_PRINTED", "IS_CLOSED",
            "ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
            "ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
            "CONTACT_NAME", "CONTACT_EMAIL", "IS_DELETED","PREPARED_BY", 
            "DATE_CREATED", "DATE_UPDATED","ID_LOGIN", "ID_AUDIT" },
            { "1                   ", "IN", "1", "", "2011-01-05", 
                "CQ", "229743", "BA","PC", 
                null, null, null, null, 
                "BBBBBBBBBB", "30 Days","MYR", "5", 
                "1", "606", "51", TEST_DAY_TODAY, 
                "1", "0","0", "1", 
                "MYR", "3.124", "606", null, 
                "1","2011-01-05", "joeykan", "0",
                "Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
                "2000", "ddd", "11", "", 
                "Mr.Timothy Yap", "", "0","w.h.wong", 
                TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",null } };
        
        String[][] dataT_BAC_H ={{
            "ID_BILL_ACCOUNT","ID_CUST","PAYMENT_METHOD","BILL_CURRENCY",
            "IS_DISPLAY_EXP_AMT","EXPORT_CURRENCY","CUST_ADR_TYPE","CONTACT_TYPE",
            "IS_DELETED","DATE_CREATED","DATE_UPDATED","ID_LOGIN",
            "FIXED_FOREX","ID_AUDIT","IS_SINGPOST","TOTAL_AMT_DUE"},
            { "3                   ","229743","CQ","MYR",
               "0","","BA","PC",
               "0",TEST_DAY_TODAY,TEST_DAY_TODAY,"sysadmin",
               null,null,"1","3331"} };
        
        super.insertData("M_CODE", dataM_CODE1);
        super.insertData("M_CODE", dataM_CODE2);
        super.insertData("M_CODE", dataM_CODE3);
        
        super.insertData("T_BIL_H", dataT_BIL_H1);
        
        super.insertData("T_BAC_H", dataT_BAC_H);
    }
    
    private void insertData9(){
        super.deleteAllData("M_CODE");
        
        super.deleteAllData("T_CSB_D");
        super.deleteAllData("T_CSB_H");
        
        super.deleteAllData("T_BIL_D");
        super.deleteAllData("T_BIL_H");
        
        super.deleteAllData("T_BAC_D");
        super.deleteAllData("T_BAC_H");
        
        String[][] dataM_CODE1 = {{
            "ID_CODE","ID_SUB_CODE","TYPE_VAL","INIT_VAL","CUR_VAL","IS_DELETED","DATE_CREATED","DATE_UPDATED","ID_LOGIN","ID_AUDIT"
        },{
            "RCPNO","1","01","11","2011","0","2010-11-26","2011-08-19","sysadmin",null
        }};
        String[][] dataM_CODE2 = {{
            "ID_CODE","ID_SUB_CODE","TYPE_VAL","INIT_VAL","CUR_VAL","IS_DELETED","DATE_CREATED","DATE_UPDATED","ID_LOGIN","ID_AUDIT"
        },{
            "RCPNO","2","06","","123456789012","0","2010-11-26","2011-08-19","sysadmin",null
        }};
        String[][] dataM_CODE3 = {{
            "ID_CODE","ID_SUB_CODE","TYPE_VAL","INIT_VAL","CUR_VAL","IS_DELETED","DATE_CREATED","DATE_UPDATED","ID_LOGIN","ID_AUDIT"
        },{
            "RCPNO","3","05","1234","1252","0","2010-11-26","2011-08-19","sysadmin",null
        }};
        
        String[][] dataT_BIL_H1 ={{ "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
            "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE",
            "ID_BIF", "ID_QCS", "QUO_REF", "CUST_PO", 
            "ID_CONSULT","TERM", "BILL_CURRENCY", "GST_PERCENT", 
            "GST_AMOUNT","BILL_AMOUNT", "PAID_AMOUNT", "LAST_PAYMENT_DATE",
            "IS_SETTLED", "IS_SINGPOST", "IS_EXPORT","IS_DISPLAY_EXP_AMT", 
            "EXPORT_CUR", "CUR_RATE","EXPORT_AMOUNT", "FIXED_FOREX", 
            "NO_PRINTED","DATE_PRINTED", "USER_PRINTED", "IS_CLOSED",
            "ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
            "ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
            "CONTACT_NAME", "CONTACT_EMAIL", "IS_DELETED","PREPARED_BY", 
            "DATE_CREATED", "DATE_UPDATED","ID_LOGIN", "ID_AUDIT" },
            { "1                   ", "IN", "1", "", "2011-01-05", 
                "CQ", "229743", "BA","PC", 
                null, null, null, null, 
                "BBBBBBBBBB", "30 Days","MYR", "5", 
                "1", "606", "51", "2011-05-01", 
                "1", "0","0", "1", 
                "MYR", "3.124", "606", null, 
                "1","2011-01-05", "joeykan", "0",
                "Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
                "2000", "ddd", "11", "", 
                "Mr.Timothy Yap", "", "0","w.h.wong", 
                TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",null } };
        
        String[][] dataT_BAC_H ={{
            "ID_BILL_ACCOUNT","ID_CUST","PAYMENT_METHOD","BILL_CURRENCY",
            "IS_DISPLAY_EXP_AMT","EXPORT_CURRENCY","CUST_ADR_TYPE","CONTACT_TYPE",
            "IS_DELETED","DATE_CREATED","DATE_UPDATED","ID_LOGIN",
            "FIXED_FOREX","ID_AUDIT","IS_SINGPOST","TOTAL_AMT_DUE"},
            { "3                   ","229743","CQ","MYR",
               "0","","BA","PC",
               "0",TEST_DAY_TODAY,TEST_DAY_TODAY,"sysadmin",
               null,null,"1","3331"} };
        
        super.insertData("M_CODE", dataM_CODE1);
        super.insertData("M_CODE", dataM_CODE2);
        super.insertData("M_CODE", dataM_CODE3);
        
        super.insertData("T_BIL_H", dataT_BIL_H1);
        
        super.insertData("T_BAC_H", dataT_BAC_H);
    }
    
    private void insertData10(){
        super.deleteAllData("M_CODE");
        
        super.deleteAllData("T_CSB_D");
        super.deleteAllData("T_CSB_H");
        String receiptNo = "111238              ";
        
        super.deleteAllData("T_BIL_D");
        super.deleteAllData("T_BIL_H");
        
        super.deleteAllData("T_BAC_D");
        super.deleteAllData("T_BAC_H");
        
        String[][] dataM_CODE1 = {{
            "ID_CODE","ID_SUB_CODE","TYPE_VAL","INIT_VAL","CUR_VAL","IS_DELETED","DATE_CREATED","DATE_UPDATED","ID_LOGIN","ID_AUDIT"
        },{
            "RCPNO","1","01","11","2011","0","2010-11-26","2011-08-19","sysadmin",null
        }};
        String[][] dataM_CODE2 = {{
            "ID_CODE","ID_SUB_CODE","TYPE_VAL","INIT_VAL","CUR_VAL","IS_DELETED","DATE_CREATED","DATE_UPDATED","ID_LOGIN","ID_AUDIT"
        },{
            "RCPNO","2","06","","123456789012","0","2010-11-26","2011-08-19","sysadmin",null
        }};
        String[][] dataM_CODE3 = {{
            "ID_CODE","ID_SUB_CODE","TYPE_VAL","INIT_VAL","CUR_VAL","IS_DELETED","DATE_CREATED","DATE_UPDATED","ID_LOGIN","ID_AUDIT"
        },{
            "RCPNO","3","05","1234","1252","0","2010-11-26","2011-08-19","sysadmin",null
        }};
        String[][] dataT_CSB_H ={{
            "RECEIPT_NO","ID_CUST","ID_COM_BANK","OTHER_PAYER",
            "PMT_METHOD","DATE_TRANS","RECEIPT_REF","CUR_CODE",
            "REMARK","PMT_STATUS","REFERENCE1","REFERENCE2",
            "IS_CLOSED","IS_DELETED","DATE_CREATED","DATE_UPDATED",
            "ID_LOGIN","AMT_RECEIVED","BANK_CHARGE","BALANCE_AMT",
            "ID_AUDIT","ID_BILL_ACCOUNT","IS_EXPORT","PAID_PRE_INVOICE",
            "REJECT_DATE","REJECT_DESC","OVER_PAID"},
            {
                receiptNo,"229743","141","",
                "CQ","2011-04-12","4","MYR",
                "REMARK","N","Cheque No.","Bank-In Slip No.",
                "0","0","2011-07-07","2011-07-07",
                "BIF001","2331","3211","2267",
                null,"0","0","0",
                "2011-08-30","1111","0"}};
        String[][] dataT_CSB_D1 ={{
            "ID_REF","IS_DELETED","DATE_CREATED","DATE_UPDATED",
            "ID_LOGIN","RECEIPT_NO","AMT_DUE","AMT_PAID",
            "FOREX_LOSS","FOREX_GAIN","ID_AUDIT"},
            {"1                   ","0","2011-09-13","2011-09-13",
                "sysadmin",receiptNo,"606","51",
                "1","21",null}};
        String[][] dataT_CSB_D2 ={{
            "ID_REF","IS_DELETED","DATE_CREATED","DATE_UPDATED",
            "ID_LOGIN","RECEIPT_NO","AMT_DUE","AMT_PAID",
            "FOREX_LOSS","FOREX_GAIN","ID_AUDIT"},
            {"2                   ","0","2011-09-13","2011-09-13",
                "sysadmin",receiptNo,"3500","1",
                "2","1",null}};
        
        String[][] dataT_BIL_H1 ={{ "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
            "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE",
            "ID_BIF", "ID_QCS", "QUO_REF", "CUST_PO", 
            "ID_CONSULT","TERM", "BILL_CURRENCY", "GST_PERCENT", 
            "GST_AMOUNT","BILL_AMOUNT", "PAID_AMOUNT", "LAST_PAYMENT_DATE",
            "IS_SETTLED", "IS_SINGPOST", "IS_EXPORT","IS_DISPLAY_EXP_AMT", 
            "EXPORT_CUR", "CUR_RATE","EXPORT_AMOUNT", "FIXED_FOREX", 
            "NO_PRINTED","DATE_PRINTED", "USER_PRINTED", "IS_CLOSED",
            "ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
            "ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
            "CONTACT_NAME", "CONTACT_EMAIL", "IS_DELETED","PREPARED_BY", 
            "DATE_CREATED", "DATE_UPDATED","ID_LOGIN", "ID_AUDIT" },
            { "1                   ", "IN", "1", "", "2011-01-05", 
                "CQ", "229743", "BA","PC", 
                null, null, null, null, 
                "BBBBBBBBBB", "30 Days","MYR", "5", 
                "1", "606", "51", TEST_DAY_TODAY, 
                "1", "0","0", "1", 
                "MYR", "3.124", "606", null, 
                "1","2011-01-05", "joeykan", "0",
                "Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
                "2000", "ddd", "11", "", 
                "Mr.Timothy Yap", "", "0","w.h.wong", 
                TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",null } };
        String[][] dataT_BIL_H2 ={{ "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
            "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE",
            "ID_BIF", "ID_QCS", "QUO_REF", "CUST_PO", 
            "ID_CONSULT","TERM", "BILL_CURRENCY", "GST_PERCENT", 
            "GST_AMOUNT","BILL_AMOUNT", "PAID_AMOUNT", "LAST_PAYMENT_DATE",
            "IS_SETTLED", "IS_SINGPOST", "IS_EXPORT","IS_DISPLAY_EXP_AMT", 
            "EXPORT_CUR", "CUR_RATE","EXPORT_AMOUNT", "FIXED_FOREX", 
            "NO_PRINTED","DATE_PRINTED", "USER_PRINTED", "IS_CLOSED",
            "ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
            "ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
            "CONTACT_NAME", "CONTACT_EMAIL", "IS_DELETED","PREPARED_BY", 
            "DATE_CREATED", "DATE_UPDATED","ID_LOGIN", "ID_AUDIT" },
            { "2                   ", "IN", "0", "1", "2011-01-27", 
              "CQ", "229743", "BA","PC", 
              null, null, null, null, 
              "sysadmin;BIF001", "30 Days", "MYR", "6", 
              "0", "3500", "0", TEST_DAY_TODAY, 
              "1", "0","0", "1", 
              "USD", "3.124", "1120.36", "50", 
              "1","2011-01-05", "joeykan", "0",
              "Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
              "2000", "ddd", "11", "", 
              "Mr.Timothy Yap", "", "0","w.h.wong", 
              TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",null } };
        
        String[][] dataT_BAC_H ={{
            "ID_BILL_ACCOUNT","ID_CUST","PAYMENT_METHOD","BILL_CURRENCY",
            "IS_DISPLAY_EXP_AMT","EXPORT_CURRENCY","CUST_ADR_TYPE","CONTACT_TYPE",
            "IS_DELETED","DATE_CREATED","DATE_UPDATED","ID_LOGIN",
            "FIXED_FOREX","ID_AUDIT","IS_SINGPOST","TOTAL_AMT_DUE"},
            { "3                   ","229743","CQ","MYR",
               "0","","BA","PC",
               "0",TEST_DAY_TODAY,TEST_DAY_TODAY,"sysadmin",
               null,null,"1","3331"} };
        
        super.insertData("M_CODE", dataM_CODE1);
        super.insertData("M_CODE", dataM_CODE2);
        super.insertData("M_CODE", dataM_CODE3);
        
        super.insertData("T_BIL_H", dataT_BIL_H1);
        super.insertData("T_BIL_H", dataT_BIL_H2);
        
        super.insertData("T_BAC_H", dataT_BAC_H);
        
        super.insertData("T_CSB_H", dataT_CSB_H);
        super.insertData("T_CSB_D", dataT_CSB_D1);
        super.insertData("T_CSB_D", dataT_CSB_D2);
    }
    
    private void insertData11(){
        super.deleteAllData("M_CODE");
        
        super.deleteAllData("T_CSB_D");
        super.deleteAllData("T_CSB_H");
        String receiptNo = "111238              ";
        
        super.deleteAllData("T_BIL_D");
        super.deleteAllData("T_BIL_H");
        
        super.deleteAllData("T_BAC_D");
        super.deleteAllData("T_BAC_H");
        
        String[][] dataM_CODE1 = {{
            "ID_CODE","ID_SUB_CODE","TYPE_VAL","INIT_VAL","CUR_VAL","IS_DELETED","DATE_CREATED","DATE_UPDATED","ID_LOGIN","ID_AUDIT"
        },{
            "RCPNO","1","01","11","2011","0","2010-11-26","2011-08-19","sysadmin",null
        }};
        String[][] dataM_CODE2 = {{
            "ID_CODE","ID_SUB_CODE","TYPE_VAL","INIT_VAL","CUR_VAL","IS_DELETED","DATE_CREATED","DATE_UPDATED","ID_LOGIN","ID_AUDIT"
        },{
            "RCPNO","2","06","","123456789012","0","2010-11-26","2011-08-19","sysadmin",null
        }};
        String[][] dataM_CODE3 = {{
            "ID_CODE","ID_SUB_CODE","TYPE_VAL","INIT_VAL","CUR_VAL","IS_DELETED","DATE_CREATED","DATE_UPDATED","ID_LOGIN","ID_AUDIT"
        },{
            "RCPNO","3","05","1234","1252","0","2010-11-26","2011-08-19","sysadmin",null
        }};
        String[][] dataT_CSB_H ={{
            "RECEIPT_NO","ID_CUST","ID_COM_BANK","OTHER_PAYER",
            "PMT_METHOD","DATE_TRANS","RECEIPT_REF","CUR_CODE",
            "REMARK","PMT_STATUS","REFERENCE1","REFERENCE2",
            "IS_CLOSED","IS_DELETED","DATE_CREATED","DATE_UPDATED",
            "ID_LOGIN","AMT_RECEIVED","BANK_CHARGE","BALANCE_AMT",
            "ID_AUDIT","ID_BILL_ACCOUNT","IS_EXPORT","PAID_PRE_INVOICE",
            "REJECT_DATE","REJECT_DESC","OVER_PAID"},
            {
                receiptNo,"229743","141","",
                "CQ","2011-04-12","4","MYR",
                "REMARK","N","Cheque No.","Bank-In Slip No.",
                "0","0","2011-07-07","2011-07-07",
                "BIF001","2331","3211","2267",
                null,"0","0","0",
                "2011-08-30","1111","0"}};
        
        String[][] dataT_BIL_H1 ={{ "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
            "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE",
            "ID_BIF", "ID_QCS", "QUO_REF", "CUST_PO", 
            "ID_CONSULT","TERM", "BILL_CURRENCY", "GST_PERCENT", 
            "GST_AMOUNT","BILL_AMOUNT", "PAID_AMOUNT", "LAST_PAYMENT_DATE",
            "IS_SETTLED", "IS_SINGPOST", "IS_EXPORT","IS_DISPLAY_EXP_AMT", 
            "EXPORT_CUR", "CUR_RATE","EXPORT_AMOUNT", "FIXED_FOREX", 
            "NO_PRINTED","DATE_PRINTED", "USER_PRINTED", "IS_CLOSED",
            "ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
            "ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
            "CONTACT_NAME", "CONTACT_EMAIL", "IS_DELETED","PREPARED_BY", 
            "DATE_CREATED", "DATE_UPDATED","ID_LOGIN", "ID_AUDIT" },
            { "1                   ", "IN", "1", "", "2011-01-05", 
                "CQ", "229743", "BA","PC", 
                null, null, null, null, 
                "BBBBBBBBBB", "30 Days","MYR", "5", 
                "1", "606", "51", TEST_DAY_TODAY, 
                "1", "0","0", "1", 
                "MYR", "3.124", "606", null, 
                "1","2011-01-05", "joeykan", "0",
                "Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
                "2000", "ddd", "11", "", 
                "Mr.Timothy Yap", "", "0","w.h.wong", 
                TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",null } };
        String[][] dataT_BIL_H2 ={{ "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
            "PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE",
            "ID_BIF", "ID_QCS", "QUO_REF", "CUST_PO", 
            "ID_CONSULT","TERM", "BILL_CURRENCY", "GST_PERCENT", 
            "GST_AMOUNT","BILL_AMOUNT", "PAID_AMOUNT", "LAST_PAYMENT_DATE",
            "IS_SETTLED", "IS_SINGPOST", "IS_EXPORT","IS_DISPLAY_EXP_AMT", 
            "EXPORT_CUR", "CUR_RATE","EXPORT_AMOUNT", "FIXED_FOREX", 
            "NO_PRINTED","DATE_PRINTED", "USER_PRINTED", "IS_CLOSED",
            "ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
            "ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
            "CONTACT_NAME", "CONTACT_EMAIL", "IS_DELETED","PREPARED_BY", 
            "DATE_CREATED", "DATE_UPDATED","ID_LOGIN", "ID_AUDIT" },
            { "2                   ", "IN", "0", "1", "2011-01-27", 
              "CQ", "229743", "BA","PC", 
              null, null, null, null, 
              "sysadmin;BIF001", "30 Days", "MYR", "6", 
              "0", "3500", "0", TEST_DAY_TODAY, 
              "1", "0","0", "1", 
              "USD", "3.124", "1120.36", "50", 
              "1","2011-01-05", "joeykan", "0",
              "Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
              "2000", "ddd", "11", "", 
              "Mr.Timothy Yap", "", "0","w.h.wong", 
              TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",null } };
        
        String[][] dataT_BAC_H ={{
            "ID_BILL_ACCOUNT","ID_CUST","PAYMENT_METHOD","BILL_CURRENCY",
            "IS_DISPLAY_EXP_AMT","EXPORT_CURRENCY","CUST_ADR_TYPE","CONTACT_TYPE",
            "IS_DELETED","DATE_CREATED","DATE_UPDATED","ID_LOGIN",
            "FIXED_FOREX","ID_AUDIT","IS_SINGPOST","TOTAL_AMT_DUE"},
            { "3                   ","229743","CQ","MYR",
               "0","","BA","PC",
               "0",TEST_DAY_TODAY,TEST_DAY_TODAY,"sysadmin",
               null,null,"1","3331"} };
        
        super.insertData("M_CODE", dataM_CODE1);
        super.insertData("M_CODE", dataM_CODE2);
        super.insertData("M_CODE", dataM_CODE3);
        
        super.insertData("T_BIL_H", dataT_BIL_H1);
        super.insertData("T_BIL_H", dataT_BIL_H2);
        
        super.insertData("T_BAC_H", dataT_BAC_H);
        
        super.insertData("T_CSB_H", dataT_CSB_H);
    }
}
