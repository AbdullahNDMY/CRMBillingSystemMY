package nttdm.bsys.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;

/***/
public class G_CSB_P01_Test extends AbstractUtilTest {

    private static final String SET_VALUE_BAC = "BAC";

    private static final String SET_VALUE_CST = "CST";

    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    Date date = new Date();

    private String nowDate = df.format(date) + " 00:00:00.0";

    /***/
    @Override
    protected void setUpData() {

        deleteAllData("M_GSET_D");
        deleteAllData("T_CSB_D");
        deleteAllData("T_CSB_H");
        deleteAllData("T_BIL_D");
        deleteAllData("T_BIL_H");
        // TODO Auto-generated method stub

        String[][] testDataMGsetD =
        {
            {"ID_SET" , "SET_SEQ" , "SET_VALUE" , "SET_DESC" ,
                "SET_APPLY" , "IS_DELETED" , "DATE_CREATED" ,
                "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
            {"BATCH_TIME_INTERVAL" , "1" , "1" , "AAA" , "1" , "0" ,
                "2011-08-30" , "2011-08-30" , "admin" , null}};
        super.insertData("M_GSET_D", testDataMGsetD);
    }

    /**
     * @param value
     * String
     */
    private void insertMGsetD(String value) {

        String[][] testDataMGsetD =
            {
                {"ID_SET" , "SET_SEQ" , "SET_VALUE" , "SET_DESC" ,
                    "SET_APPLY" , "IS_DELETED" , "DATE_CREATED" ,
                    "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"CB_AUTO_OFFSET" , "1" , value , "AAA" , "1" , "0" ,
                    "2011-08-30" , "2011-08-30" , "admin" , null}};
        super.insertData("M_GSET_D", testDataMGsetD);
    }

    /**
     * @param idBillAcc
     * String
     */
    private void insertTCsbH(String receiptNo, String idBillAcc) {

        String[][] testDataTCsbH =
            {
                {"RECEIPT_NO" , "ID_CUST" , "ID_COM_BANK" , "OTHER_PAYER" ,
                    "PMT_METHOD" , "ID_BILL_ACCOUNT" , "DATE_TRANS" ,
                    "RECEIPT_REF" , "CUR_CODE" , "AMT_RECEIVED" , "REMARK" ,
                    "PMT_STATUS" , "BALANCE_AMT" , "REFERENCE1" ,
                    "REFERENCE2" , "BANK_CHARGE" , "REJECT_DATE" ,
                    "REJECT_DESC" , "PAID_PRE_INVOICE" , "OVER_PAID" ,
                    "IS_EXPORT" , "IS_DELETED" , "IS_CLOSED" , "DATE_CREATED" ,
                    "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {receiptNo , "1" , "1" , "AAA" , "AB" , idBillAcc ,
                    "2011-08-30" , "ABC" , "CC1" , "1000" , "AAAAAAAAAAAAA" ,
                    "N" , "100" , "1234567890" , "1234567890" , "10000" ,
                    "2011-08-30" , "ABCDE" , "1" , "1" , "1" , "0" , "0" ,
                    "2011-08-30" , "2011-08-30" , "sysadmin" , null}};
        super.insertData("T_CSB_H", testDataTCsbH);
    }

    /**
     * @param string3
     * @param string2
     * @param string
     * @param paidAmount
     * String
     * @param billAcc
     * String
     */
    private void insertTBilH(String idRef, String paidAmount, String billAcc) {

        String[][] testDataTCsbH =
            {
                {"ID_REF" , "BILL_TYPE" , "IS_MANUAL" , "BILL_ACC" ,
                    "DATE_REQ" , "ID_CUST" , "ADR_TYPE" , "BILL_CURRENCY" ,
                    "GST_PERCENT" , "GST_AMOUNT" , "BILL_AMOUNT" ,
                    "PAID_AMOUNT" , "IS_SETTLED" , "IS_SINGPOST" ,
                    "IS_EXPORT" , "IS_DISPLAY_EXP_AMT" , "EXPORT_CUR" ,
                    "CUR_RATE" , "EXPORT_AMOUNT" , "FIXED_FOREX" ,
                    "NO_PRINTED" , "IS_CLOSED" , "IS_DELETED" , "PREPARED_BY" ,
                    "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN"} ,
                {idRef , "IN" , "1" , billAcc , "2011-08-30" , "1" , "CA" ,
                    "CC1" , "10" , "1000" , "150" , paidAmount , "1" , "1" ,
                    "1" , "1" , "ABC" , "1" , "1000" , "1000" , "1" , "1" ,
                    "0" , "ABC" , "2011-08-30" , "2011-08-30" , "admin"}};
        super.insertData("T_BIL_H", testDataTCsbH);

    }

    private void insertTCsbD() {

        String[][] testDataTCsbD =
            {
                {"RECEIPT_NO" , "ID_REF" , "AMT_PAID" , "AMT_DUE" ,
                    "FOREX_LOSS" , "FOREX_GAIN" , "IS_DELETED" ,
                    "DATE_CREATED" , "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
                {"001" , "001" , "10" , "10" , "10" , "10" , "0" ,
                    "2011-08-30" , "2011-08-30" , "admin" , null}};
        super.insertData("T_CSB_D", testDataTCsbD);

    }

    public void testExecute01() {

        /**
         * 【Table "M_GSET_D"'s records】
         * has one record in accordance with
         * SET_VALUE="BCA"
         * ID_SET="CB_AUTO_OFFSET"
         * SET_SEQ=1
         * SET_APPLY=1
         */
        insertMGsetD(SET_VALUE_BAC);

        /**
         * 【Table "T_CSB_H"'s records】
         * has one record in accordance with
         * BALANCE_AMT=100
         * ID_BILL_ACCOUNT=1
         * IS_DELETED=0
         * RECEIPT_NO=001
         * ID_CUST=1
         * CUR_CODE=CC1
         */
        insertTCsbH("001", "1");
        /**
         * 【Table "T_BIL_H"'s records】
         * has one record in accordance with
         * ID_CUST=1
         * BILL_CURRENCY=CC1
         * BILL_ACC=1
         * BILL_AMOUNT=150
         * PAID_AMOUNT=60
         * IS_DELETED=0
         * ID_REF=001
         */
        insertTBilH("001", "60", "1");
        /**
         * 【Table "T_CSB_D"'s records】
         * has one record in accordance with
         * ID_REF=001
         * AMT_PAID=10
         * DATE_UPDATED=2011-08-30
         * ID_LOGIN="admin"
         */
        insertTCsbD();
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        uvo.setUser_name("sysadmin");

        G_CSB_P01 gCsbP01 = new G_CSB_P01(queryDAO, updateDAO, uvo);

        gCsbP01.setBatchId("A");
        gCsbP01.execute();
        /**
         * 【Update table "T_CSB_D"】
         * AMT_PAID=AMT_PAID + $amt_due (100)
         * DATE_UPDATED=sysdate
         * ID_LOGIN=LoginUserID
         * WHERE ID_REF = $id_ref and RECEIPT_NO = $receipt_No 
         */
        Map[] tCsbD = super.select("T_CSB_D", "TRIM(ID_REF)='001' and TRIM(RECEIPT_NO)='001'", "");
        assertEquals("100", tCsbD[0].get("AMT_PAID"));
        assertEquals(nowDate, tCsbD[0].get("DATE_UPDATED"));
        assertEquals(SYS_ADMIN, tCsbD[0].get("ID_LOGIN"));
        /**
         * 【Update table "T_BIL_H"】
         * PAID_AMOUNT=PAID_AMOUNT + $amt_due
         * IS_SETTLED=0
         * DATE_UPDATED=sysdate
         * ID_LOGIN=LoginUserID
         * WHERE ID_REF = $id_ref
         */
        Map[] tBilH = super.select("T_BIL_H", "TRIM(ID_REF)='001'", "");
        assertEquals("150", tBilH[0].get("PAID_AMOUNT"));
        assertEquals(nowDate, tBilH[0].get("DATE_UPDATED"));
        assertEquals(SYS_ADMIN, tBilH[0].get("ID_LOGIN"));
        /**
         * 【Update table "T_CSB_H"】
         * LAST_PAYMENT_DATE=sysdate
         * DATE_UPDATED=sysdate
         * ID_LOGIN=LoginUserID
         * WHERE LAST_PAYMENT_DATE < T.CSB_H.DATE_TRANS or LAST_PAYMENT_DATE =
         * null
         * on T_CSB_H.RECEIPT_NO = $receipt_no and ID_REF = T_CSB_D.ID_REF
         */
        // TODO;
        /**
         * 【Update table "T_CSB_H"】
         * BALANCE_AMT=$balance_amt (10)
         * DATE_UPDATED=sysdate
         * ID_LOGIN=LoginUserID
         * WHERE RECEIPT_NO = $receipt_no
         */
        Map[] tCsbH = super.select("T_CSB_H", "TRIM(RECEIPT_NO)='001'", "");
        assertEquals("10", tCsbH[0].get("BALANCE_AMT"));
        assertEquals(nowDate, tCsbH[0].get("DATE_UPDATED"));
        assertEquals(SYS_ADMIN, tCsbH[0].get("ID_LOGIN"));
        
        gCsbP01.getBatchId();
        gCsbP01.setQueryDAO(queryDAO);
        gCsbP01.getQueryDAO();
        gCsbP01.setUpdateDAO(updateDAO);
        gCsbP01.getUpdateDAO();
        gCsbP01.getUvo();
        gCsbP01.setUvo(uvo);
        gCsbP01.getReceiptNos();
        gCsbP01.setReceiptNos(null);
        gCsbP01 = new G_CSB_P01();
        /**
         * 【Call the Module "G_ALT_P06"】
         * $BATCH_ID = "BC" $SUBJECT=ERR2SC045 $MSG = ERR2SC047
         */
        // TODO;
    }

    public void testExecute02() {

        /**
         * 【Table "M_GSET_D"'s records】
         * has one record in accordance with
         * SET_VALUE="BCA"
         * ID_SET="CB_AUTO_OFFSET"
         * SET_SEQ=1
         * SET_APPLY=1
         */
        insertMGsetD(SET_VALUE_BAC);

        /**
         * 【Table "T_CSB_H"'s records】
         * has one record in accordance with
         * BALANCE_AMT=100
         * ID_BILL_ACCOUNT=1
         * IS_DELETED=0
         * RECEIPT_NO=001
         * ID_CUST=1
         * CUR_CODE=CC1
         */
        insertTCsbH("001", "1");
        /**
         * 【Table "T_BIL_H"'s records】
         * has one record in accordance with
         * ID_CUST=1
         * BILL_CURRENCY=CC1
         * BILL_ACC=1
         * BILL_AMOUNT=150
         * PAID_AMOUNT=60
         * IS_DELETED=0
         * ID_REF=001
         */
        insertTBilH("001", "60", "1");
        /**
         * 【Table "T_CSB_D"'s records】
         * has one record in accordance with
         * ID_REF=001
         * AMT_PAID=10
         * DATE_UPDATED=2011-08-30
         * ID_LOGIN="admin"
         */
        insertTCsbD();
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        uvo.setUser_name("sysadmin");

        G_CSB_P01 gCsbP01 = new G_CSB_P01(queryDAO, updateDAO, uvo);

        gCsbP01.execute();
        Map[] tCsbD = super.select("T_CSB_D", "TRIM(ID_REF)='001' and TRIM(RECEIPT_NO)='001'", "");
        /**
         * 【Update table "T_CSB_D"】
         * AMT_PAID=AMT_PAID + $amt_due (100)
         * DATE_UPDATED=sysdate
         * ID_LOGIN=LoginUserID
         * WHERE ID_REF = $id_ref and RECEIPT_NO = $receipt_No 
         */
        assertEquals("100", tCsbD[0].get("AMT_PAID"));
        assertEquals(nowDate, tCsbD[0].get("DATE_UPDATED"));
        assertEquals(SYS_ADMIN, tCsbD[0].get("ID_LOGIN"));
        /**
         * 【Update table "T_BIL_H"】
         * PAID_AMOUNT=PAID_AMOUNT + $amt_due
         * IS_SETTLED=0
         * DATE_UPDATED=sysdate
         * ID_LOGIN=LoginUserID
         * WHERE ID_REF = $id_ref
         */
        Map[] tBilH = super.select("T_BIL_H", "TRIM(ID_REF)='001'", "");
        assertEquals("150", tBilH[0].get("PAID_AMOUNT"));
        assertEquals(nowDate, tBilH[0].get("DATE_UPDATED"));
        assertEquals(SYS_ADMIN, tBilH[0].get("ID_LOGIN"));
        /**
         * 【Update table "T_CSB_H"】
         * LAST_PAYMENT_DATE=sysdate
         * DATE_UPDATED=sysdate
         * ID_LOGIN=LoginUserID
         * WHERE LAST_PAYMENT_DATE < T.CSB_H.DATE_TRANS or LAST_PAYMENT_DATE =
         * null
         * on T_CSB_H.RECEIPT_NO = $receipt_no and ID_REF = T_CSB_D.ID_REF
         */
        // TODO;
        /**
         * 【Update table "T_CSB_H"】
         * BALANCE_AMT=$balance_amt (10)
         * DATE_UPDATED=sysdate
         * ID_LOGIN=LoginUserID
         */
        Map[] tCsbH = super.select("T_CSB_H", "TRIM(RECEIPT_NO)='001'", "");
        assertEquals("10", tCsbH[0].get("BALANCE_AMT"));
        assertEquals(nowDate, tCsbH[0].get("DATE_UPDATED"));
        assertEquals(SYS_ADMIN, tCsbH[0].get("ID_LOGIN"));
        /**
         * 【Call the Module "G_ALT_P06"】
         * $BATCH_ID = "BC" $SUBJECT=ERR2SC045 $MSG = ERR2SC047
         */
        // TODO;

    }

    public void testExecute03() {

        /**
         * 【Table "M_GSET_D"'s records】
         * has one record in accordance with
         * SET_VALUE="BCA"
         * ID_SET="CB_AUTO_OFFSET"
         * SET_SEQ=1
         * SET_APPLY=1
         */
        insertMGsetD(SET_VALUE_BAC);

        /**
         * 【Table "T_CSB_H"'s records】
         * has one record in accordance with
         * BALANCE_AMT=100
         * ID_BILL_ACCOUNT=1
         * IS_DELETED=0
         * RECEIPT_NO=001
         * ID_CUST=1
         * CUR_CODE=CC1
         */
        insertTCsbH("001", "1");
        /**
         * 【Table "T_BIL_H"'s records】
         * has one record in accordance with
         * ID_CUST=1
         * BILL_CURRENCY=CC1
         * BILL_ACC=1
         * BILL_AMOUNT=150
         * PAID_AMOUNT=60
         * IS_DELETED=0
         * ID_REF=001
         */
        insertTBilH("001", "60", "1");
        /**
         * 【Table "T_CSB_D"'s records】
         * has no record
         */
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        uvo.setUser_name("sysadmin");

        G_CSB_P01 gCsbP01 = new G_CSB_P01(queryDAO, updateDAO, uvo);

        gCsbP01.setBatchId("A");
        gCsbP01.execute();

        /**
         * 【Insert into table "T_CSB_D"】
         * ID_REF=$id_ref
         * RECEIPT_NO=$receipt_no
         * AMT_DUE=$amt_due
         * AMT_PAID=$amt_due
         * IS_DELETED=0
         * DATE_CREATED=sysdate
         * DATE_UPDATED=sysdate
         * ID_LOGIN=LoginUserID
         */
        Map[] tCsbD = super.select("T_CSB_D", "", "");
        assertEquals("001", tCsbD[0].get("ID_REF").toString().trim());
        assertEquals("001", tCsbD[0].get("RECEIPT_NO").toString().trim());
        assertEquals("90", tCsbD[0].get("AMT_DUE"));
        assertEquals("90", tCsbD[0].get("AMT_PAID"));
        assertEquals("0", tCsbD[0].get("IS_DELETED"));

        assertEquals(nowDate, tCsbD[0].get("DATE_CREATED"));
        assertEquals(nowDate, tCsbD[0].get("DATE_UPDATED"));
        assertEquals(SYS_ADMIN, tCsbD[0].get("ID_LOGIN"));
        /**
         * 【Update table "T_BIL_H"】
         * PAID_AMOUNT=PAID_AMOUNT + $amt_due
         * IS_SETTLED=0
         * DATE_UPDATED=sysdate
         * ID_LOGIN=LoginUserID
         * WHERE ID_REF = $id_ref
         */
        Map[] tBilH = super.select("T_BIL_H", "TRIM(ID_REF)='001'", "");
        assertEquals("150", tBilH[0].get("PAID_AMOUNT"));
        assertEquals(nowDate, tBilH[0].get("DATE_UPDATED"));
        assertEquals(SYS_ADMIN, tBilH[0].get("ID_LOGIN"));
        /**
         * 【Update table "T_CSB_H"】
         * LAST_PAYMENT_DATE=sysdate
         * DATE_UPDATED=sysdate
         * ID_LOGIN=LoginUserID
         * WHERE LAST_PAYMENT_DATE < T.CSB_H.DATE_TRANS or LAST_PAYMENT_DATE =
         * null
         * on T_CSB_H.RECEIPT_NO = $receipt_no and ID_REF = T_CSB_D.ID_REF
         */
        // TODO;
        /**
         * 【Update table "T_CSB_H"】
         * BALANCE_AMT=$balance_amt (10)
         * DATE_UPDATED=sysdate
         * ID_LOGIN=LoginUserID
         */
        Map[] tCsbH = super.select("T_CSB_H", "TRIM(RECEIPT_NO)='001'", "");
        assertEquals("10", tCsbH[0].get("BALANCE_AMT"));
        assertEquals(nowDate, tCsbH[0].get("DATE_UPDATED"));
        assertEquals(SYS_ADMIN, tCsbH[0].get("ID_LOGIN"));
        /**
         * 【Call the Module "G_ALT_P06"】
         * $BATCH_ID = "BC" $SUBJECT=ERR2SC045 $MSG = ERR2SC047
         */
        // TODO;
    }

    public void testExecute04() {

        /**
         * 【Table "M_GSET_D"'s records】
         * has one record in accordance with
         * SET_VALUE="BCA"
         * ID_SET="CB_AUTO_OFFSET"
         * SET_SEQ=1
         * SET_APPLY=1
         */
        insertMGsetD(SET_VALUE_BAC);

        /**
         * 【Table "T_CSB_H"'s records】
         * has one record in accordance with
         * BALANCE_AMT=100
         * ID_BILL_ACCOUNT=1
         * IS_DELETED=0
         * RECEIPT_NO=001
         * ID_CUST=1
         * CUR_CODE=CC1
         */
        insertTCsbH("001", "1");
        /**
         * 【Table "T_BIL_H"'s records】
         * has one record in accordance with
         * ID_CUST=1
         * BILL_CURRENCY=CC1
         * BILL_ACC=1
         * BILL_AMOUNT=150
         * PAID_AMOUNT=49
         * IS_DELETED=0
         * ID_REF=001
         */
        insertTBilH("001", "49", "1");
        /**
         * 【Table "T_CSB_D"'s records】
         * has one record in accordance with
         * ID_REF=001
         * AMT_PAID=10
         * DATE_UPDATED=2011-08-30
         * ID_LOGIN="admin"
         */
        insertTCsbD();
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        uvo.setUser_name("sysadmin");

        G_CSB_P01 gCsbP01 = new G_CSB_P01(queryDAO, updateDAO, uvo);

        gCsbP01.setBatchId("A");
        gCsbP01.execute();

        /**
         * 【Update table "T_CSB_D"】
         * AMT_PAID=AMT_PAID + $balance_amt(110)
         * DATE_UPDATED=sysdate
         * ID_LOGIN=LoginUserID
         * WHERE ID_REF = $id_ref and RECEIPT_NO = $receipt_No 
         */
        Map[] tCsbD = super.select("T_CSB_D", "TRIM(ID_REF)='001' and TRIM(RECEIPT_NO)='001'", "");
        assertEquals("110", tCsbD[0].get("AMT_PAID"));
        assertEquals(nowDate, tCsbD[0].get("DATE_UPDATED"));
        assertEquals(SYS_ADMIN, tCsbD[0].get("ID_LOGIN"));
        /**
         * 【Update table "T_BIL_H"】
         * PAID_AMOUNT=PAID_AMOUNT + $balance_amt
         * DATE_UPDATED=sysdate
         * ID_LOGIN=LoginUserID
         * WHERE ID_REF = $id_ref
         */
        Map[] tBilH = super.select("T_BIL_H", "TRIM(ID_REF)='001'", "");
        assertEquals("149", tBilH[0].get("PAID_AMOUNT"));
        assertEquals(nowDate, tBilH[0].get("DATE_UPDATED"));
        assertEquals(SYS_ADMIN, tBilH[0].get("ID_LOGIN"));
        /**
         * 【Update table "T_CSB_H"】
         * LAST_PAYMENT_DATE=sysdate
         * DATE_UPDATED=sysdate
         * ID_LOGIN=LoginUserID
         * WHERE LAST_PAYMENT_DATE < T.CSB_H.DATE_TRANS or LAST_PAYMENT_DATE =
         * null
         * on T_CSB_H.RECEIPT_NO = $receipt_no and ID_REF = T_CSB_D.ID_REF
         */
        // TODO;
        /**
         * 【Update table "T_CSB_H"】
         * BALANCE_AMT=$balance_amt (-1)
         * DATE_UPDATED=sysdate
         * ID_LOGIN=LoginUserID
         */
        Map[] tCsbH = super.select("T_CSB_H", "TRIM(RECEIPT_NO)='001'", "");
        assertEquals("0", tCsbH[0].get("BALANCE_AMT"));
        assertEquals(nowDate, tCsbH[0].get("DATE_UPDATED"));
        assertEquals(SYS_ADMIN, tCsbH[0].get("ID_LOGIN"));
        /**
         * 【Call the Module "G_ALT_P06"】
         * $BATCH_ID = "BC" $SUBJECT=ERR2SC045 $MSG = ERR2SC047
         */
        // TODO;

    }

    public void testExecute05() {

        /**
         * 【Table "M_GSET_D"'s records】
         * has one record in accordance with
         * SET_VALUE="BCA"
         * ID_SET="CB_AUTO_OFFSET"
         * SET_SEQ=1
         * SET_APPLY=1
         */
        insertMGsetD(SET_VALUE_BAC);

        /**
         * 【Table "T_CSB_H"'s records】
         * has one record in accordance with
         * BALANCE_AMT=100
         * ID_BILL_ACCOUNT=1
         * IS_DELETED=0
         * RECEIPT_NO=001
         * ID_CUST=1
         * CUR_CODE=CC1
         */
        insertTCsbH("001", "1");
        /**
         * 【Table "T_BIL_H"'s records】
         * has one record in accordance with
         * ID_CUST=1
         * BILL_CURRENCY=CC1
         * BILL_ACC=1
         * BILL_AMOUNT=150
         * PAID_AMOUNT=49
         * IS_DELETED=0
         * ID_REF=001
         */
        insertTBilH("001", "49", "1");
        /**
         * 【Table "T_CSB_D"'s records】
         * has no record
         */

        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        uvo.setUser_name("sysadmin");

        G_CSB_P01 gCsbP01 = new G_CSB_P01(queryDAO, updateDAO, uvo);

        gCsbP01.setBatchId("A");
        gCsbP01.execute();

        /**
         * 【Insert into table "T_CSB_D"】
         * ID_REF=$id_ref
         * RECEIPT_NO=$receipt_no
         * AMT_DUE=$amt_due
         * AMT_PAID=$balance_amt
         * IS_DELETED=0
         * DATE_CREATED=sysdate
         * DATE_UPDATED=sysdate
         * ID_LOGIN=LoginUserID
         */
        Map[] tCsbD = super.select("T_CSB_D", "", "");
        assertEquals("001", tCsbD[0].get("ID_REF").toString().trim());
        assertEquals("001", tCsbD[0].get("RECEIPT_NO").toString().trim());
        assertEquals("101", tCsbD[0].get("AMT_DUE"));
        assertEquals("100", tCsbD[0].get("AMT_PAID"));
        assertEquals("0", tCsbD[0].get("IS_DELETED"));

        assertEquals(nowDate, tCsbD[0].get("DATE_CREATED"));
        assertEquals(nowDate, tCsbD[0].get("DATE_UPDATED"));
        assertEquals(SYS_ADMIN, tCsbD[0].get("ID_LOGIN"));
        /**
         * 【Update table "T_BIL_H"】
         * PAID_AMOUNT=PAID_AMOUNT + $balance_amt
         * DATE_UPDATED=sysdate
         * ID_LOGIN=LoginUserID
         * WHERE ID_REF = $id_ref
         */
        Map[] tBilH = super.select("T_BIL_H", "TRIM(ID_REF)='001'", "");
        assertEquals("149", tBilH[0].get("PAID_AMOUNT"));
        assertEquals(nowDate, tBilH[0].get("DATE_UPDATED"));
        assertEquals(SYS_ADMIN, tBilH[0].get("ID_LOGIN"));
        /**
         * 【Update table "T_CSB_H"】
         * LAST_PAYMENT_DATE=sysdate
         * DATE_UPDATED=sysdate
         * ID_LOGIN=LoginUserID
         * WHERE LAST_PAYMENT_DATE < T.CSB_H.DATE_TRANS or LAST_PAYMENT_DATE =
         * null
         * on T_CSB_H.RECEIPT_NO = $receipt_no and ID_REF = T_CSB_D.ID_REF
         */
        // TODO;
        /**
         * 【Update table "T_CSB_H"】
         * BALANCE_AMT=$balance_amt (-1)
         * DATE_UPDATED=sysdate
         * ID_LOGIN=LoginUserID
         */
        Map[] tCsbH = super.select("T_CSB_H", "TRIM(RECEIPT_NO)='001'", "");
        assertEquals("0", tCsbH[0].get("BALANCE_AMT"));
        assertEquals(nowDate, tCsbH[0].get("DATE_UPDATED"));
        assertEquals(SYS_ADMIN, tCsbH[0].get("ID_LOGIN"));
        /**
         * 【Call the Module "G_ALT_P06"】
         * $BATCH_ID = "BC" $SUBJECT=ERR2SC045 $MSG = ERR2SC047
         */
        // TODO;

    }

    public void testExecute06() {

        /**
         * 【Table "M_GSET_D"'s records】
         * has one record in accordance with
         * SET_VALUE="CST"
         * ID_SET="CB_AUTO_OFFSET"
         * SET_SEQ=1
         * SET_APPLY=1
         */
        insertMGsetD(SET_VALUE_CST);

        /**
         * 【Table "T_CSB_H"'s records】
         * has one record in accordance with
         * BALANCE_AMT=100
         * ID_BILL_ACCOUNT=2
         * IS_DELETED=0
         * RECEIPT_NO=001
         * ID_CUST=1
         * CUR_CODE=1
         */
        insertTCsbH("001", "2");
        /**
         * 【Table "T_BIL_H"'s records】
         * has one record in accordance with
         * ID_CUST=1
         * BILL_CURRENCY=CC1
         * BILL_ACC=2
         * BILL_AMOUNT=150
         * PAID_AMOUNT=60
         * IS_DELETED=0
         * ID_REF=001
         */
        insertTBilH("001", "60", "2");
        /**
         * 【Table "T_CSB_D"'s records】
         * has one record in accordance with
         * ID_REF=001
         * AMT_PAID=10
         * DATE_UPDATED=2011-08-30
         * ID_LOGIN="admin"
         */
        insertTCsbD();
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        uvo.setUser_name("sysadmin");

        G_CSB_P01 gCsbP01 = new G_CSB_P01(queryDAO, updateDAO, uvo);

        gCsbP01.setBatchId("A");
        gCsbP01.execute();
        /**
         * 【Update table "T_CSB_D"】
         * AMT_PAID=AMT_PAID + $amt_due (100)
         * DATE_UPDATED=sysdate
         * ID_LOGIN=LoginUserID
         * WHERE ID_REF = $id_ref and RECEIPT_NO = $receipt_No 
         */
        Map[] tCsbD = super.select("T_CSB_D", "TRIM(ID_REF)='001' and TRIM(RECEIPT_NO)='001'", "");
        assertEquals("100", tCsbD[0].get("AMT_PAID"));
        assertEquals(nowDate, tCsbD[0].get("DATE_UPDATED"));
        assertEquals(SYS_ADMIN, tCsbD[0].get("ID_LOGIN"));
        /**
         * 【Update table "T_BIL_H"】
         * PAID_AMOUNT=PAID_AMOUNT + $amt_due
         * IS_SETTLED=0
         * DATE_UPDATED=sysdate
         * ID_LOGIN=LoginUserID
         * WHERE ID_REF = $id_ref
         */
        Map[] tBilH = super.select("T_BIL_H", "TRIM(ID_REF)='001'", "");
        assertEquals("150", tBilH[0].get("PAID_AMOUNT"));
        assertEquals(nowDate, tBilH[0].get("DATE_UPDATED"));
        assertEquals(SYS_ADMIN, tBilH[0].get("ID_LOGIN"));
        /**
         * 【Update table "T_CSB_H"】
         * LAST_PAYMENT_DATE=sysdate
         * DATE_UPDATED=sysdate
         * ID_LOGIN=LoginUserID
         * WHERE LAST_PAYMENT_DATE < T.CSB_H.DATE_TRANS or LAST_PAYMENT_DATE =
         * null
         * on T_CSB_H.RECEIPT_NO = $receipt_no and ID_REF = T_CSB_D.ID_REF
         */
        // TODO;
        /**
         * 【Update table "T_CSB_H"】
         * BALANCE_AMT=$balance_amt (10)
         * DATE_UPDATED=sysdate
         * ID_LOGIN=LoginUserID
         * WHERE RECEIPT_NO = $receipt_no
         */
        Map[] tCsbH = super.select("T_CSB_H", "TRIM(RECEIPT_NO)='001'", "");
        assertEquals("10", tCsbH[0].get("BALANCE_AMT"));
        assertEquals(nowDate, tCsbH[0].get("DATE_UPDATED"));
        assertEquals(SYS_ADMIN, tCsbH[0].get("ID_LOGIN"));
        /**
         * 【Call the Module "G_ALT_P06"】
         * $BATCH_ID = "BC" $SUBJECT=ERR2SC045 $MSG = ERR2SC047
         */
        // TODO;

    }

    public void testExecute07() {

        /**
         * 【Table "M_GSET_D"'s records】
         * has one record in accordance with
         * SET_VALUE="CST"
         * ID_SET="CB_AUTO_OFFSET"
         * SET_SEQ=1
         * SET_APPLY=1
         */
        insertMGsetD(SET_VALUE_CST);

        /**
         * 【Table "T_CSB_H"'s records】
         * has one record in accordance with
         * BALANCE_AMT=100
         * ID_BILL_ACCOUNT=2
         * IS_DELETED=0
         * RECEIPT_NO=001
         * ID_CUST=1
         * CUR_CODE=1
         */
        insertTCsbH("001", "2");
        /**
         * 【Table "T_BIL_H"'s records】
         * has one record in accordance with
         * ID_CUST=1
         * BILL_CURRENCY=CC1
         * BILL_ACC=1
         * BILL_AMOUNT=150
         * PAID_AMOUNT=60
         * IS_DELETED=0
         * ID_REF=001
         */
        insertTBilH("001", "60", "1");
        /**
         * 【Table "T_CSB_D"'s records】
         * has one record in accordance with
         * ID_REF=001
         * AMT_PAID=10
         * DATE_UPDATED=2011-08-30
         * ID_LOGIN="admin"
         */
        insertTCsbD();
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        uvo.setUser_name("sysadmin");

        G_CSB_P01 gCsbP01 = new G_CSB_P01(queryDAO, updateDAO, uvo);

        gCsbP01.setBatchId("A");
        gCsbP01.execute();
        /**
         * 【Update table "T_CSB_D"】
         * AMT_PAID=AMT_PAID + $amt_due (100)
         * DATE_UPDATED=sysdate
         * ID_LOGIN=LoginUserID
         * WHERE ID_REF = $id_ref and RECEIPT_NO = $receipt_No 
         */
        Map[] tCsbD = super.select("T_CSB_D", "TRIM(ID_REF)='001' and TRIM(RECEIPT_NO)='001'", "");
        assertEquals("100", tCsbD[0].get("AMT_PAID"));
        assertEquals(nowDate, tCsbD[0].get("DATE_UPDATED"));
        assertEquals(SYS_ADMIN, tCsbD[0].get("ID_LOGIN"));
        /**
         * 【Update table "T_BIL_H"】
         * PAID_AMOUNT=PAID_AMOUNT + $amt_due
         * IS_SETTLED=0
         * DATE_UPDATED=sysdate
         * ID_LOGIN=LoginUserID
         * WHERE ID_REF = $id_ref
         */
        Map[] tBilH = super.select("T_BIL_H", "TRIM(ID_REF)='001'", "");
        assertEquals("150", tBilH[0].get("PAID_AMOUNT"));
        assertEquals(nowDate, tBilH[0].get("DATE_UPDATED"));
        assertEquals(SYS_ADMIN, tBilH[0].get("ID_LOGIN"));
        /**
         * 【Update table "T_CSB_H"】
         * LAST_PAYMENT_DATE=sysdate
         * DATE_UPDATED=sysdate
         * ID_LOGIN=LoginUserID
         * WHERE LAST_PAYMENT_DATE < T.CSB_H.DATE_TRANS or LAST_PAYMENT_DATE =
         * null
         * on T_CSB_H.RECEIPT_NO = $receipt_no and ID_REF = T_CSB_D.ID_REF
         */
        // TODO;
        /**
         * 【Update table "T_CSB_H"】
         * BALANCE_AMT=$balance_amt (10)
         * DATE_UPDATED=sysdate
         * ID_LOGIN=LoginUserID
         * WHERE RECEIPT_NO = $receipt_no
         */
        Map[] tCsbH = super.select("T_CSB_H", "TRIM(RECEIPT_NO)='001'", "");
        assertEquals("10", tCsbH[0].get("BALANCE_AMT"));
        assertEquals(nowDate, tCsbH[0].get("DATE_UPDATED"));
        assertEquals(SYS_ADMIN, tCsbH[0].get("ID_LOGIN"));
        /**
         * 【Call the Module "G_ALT_P06"】
         * $BATCH_ID = "BC" $SUBJECT=ERR2SC045 $MSG = ERR2SC047
         */
        // TODO;

    }

    public void testExecute08() {

        /**
         * 【Table "M_GSET_D"'s records】
         * has one record in accordance with
         * SET_VALUE="CST"
         * ID_SET="CB_AUTO_OFFSET"
         * SET_SEQ=1
         * SET_APPLY=1
         */
        insertMGsetD(SET_VALUE_CST);

        /**
         * 【Table "T_CSB_H"'s records】
         * has one record in accordance with
         * BALANCE_AMT=100
         * ID_BILL_ACCOUNT=1
         * IS_DELETED=0
         * RECEIPT_NO=001
         * ID_CUST=1
         * CUR_CODE=1
         */
        insertTCsbH("001", "1");
        /**
         * 【Table "T_BIL_H"'s records】
         * has one record in accordance with
         * ID_CUST=1
         * BILL_CURRENCY=CC1
         * BILL_ACC=1
         * BILL_AMOUNT=150
         * PAID_AMOUNT=60
         * IS_DELETED=0
         * ID_REF=001
         */
        insertTBilH("001", "60", "1");
        /**
         * 【Table "T_CSB_D"'s records】
         * has one record in accordance with
         * ID_REF=001
         * AMT_PAID=10
         * DATE_UPDATED=2011-08-30
         * ID_LOGIN="admin"
         */
        insertTCsbD();
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        uvo.setUser_name("sysadmin");

        G_CSB_P01 gCsbP01 = new G_CSB_P01(queryDAO, updateDAO, uvo);

        gCsbP01.execute();
        /**
         * 【Update table "T_CSB_D"】
         * AMT_PAID=AMT_PAID + $amt_due (100)
         * DATE_UPDATED=sysdate
         * ID_LOGIN=LoginUserID
         * WHERE ID_REF = $id_ref and RECEIPT_NO = $receipt_No 
         */
        Map[] tCsbD = super.select("T_CSB_D", "TRIM(ID_REF)='001' and TRIM(RECEIPT_NO)='001'", "");
        assertEquals("100", tCsbD[0].get("AMT_PAID"));
        assertEquals(nowDate, tCsbD[0].get("DATE_UPDATED"));
        assertEquals(SYS_ADMIN, tCsbD[0].get("ID_LOGIN"));
        /**
         * 【Update table "T_BIL_H"】
         * PAID_AMOUNT=PAID_AMOUNT + $amt_due
         * IS_SETTLED=0
         * DATE_UPDATED=sysdate
         * ID_LOGIN=LoginUserID
         * WHERE ID_REF = $id_ref
         */
        Map[] tBilH = super.select("T_BIL_H", "TRIM(ID_REF)='001'", "");
        assertEquals("150", tBilH[0].get("PAID_AMOUNT"));
        assertEquals(nowDate, tBilH[0].get("DATE_UPDATED"));
        assertEquals(SYS_ADMIN, tBilH[0].get("ID_LOGIN"));
        /**
         * 【Update table "T_CSB_H"】
         * LAST_PAYMENT_DATE=sysdate
         * DATE_UPDATED=sysdate
         * ID_LOGIN=LoginUserID
         * WHERE LAST_PAYMENT_DATE < T.CSB_H.DATE_TRANS or LAST_PAYMENT_DATE =
         * null
         * on T_CSB_H.RECEIPT_NO = $receipt_no and ID_REF = T_CSB_D.ID_REF
         */
        // TODO;
        /**
         * 【Update table "T_CSB_H"】
         * BALANCE_AMT=$balance_amt (10)
         * DATE_UPDATED=sysdate
         * ID_LOGIN=LoginUserID
         * WHERE RECEIPT_NO = $receipt_no
         */
        Map[] tCsbH = super.select("T_CSB_H", "TRIM(RECEIPT_NO)='001'", "");
        assertEquals("10", tCsbH[0].get("BALANCE_AMT"));
        assertEquals(nowDate, tCsbH[0].get("DATE_UPDATED"));
        assertEquals(SYS_ADMIN, tCsbH[0].get("ID_LOGIN"));
        /**
         * 【Call the Module "G_ALT_P06"】
         * No call
         */
        // TODO;

    }

    public void testExecute09() {

        /**
         * 【Table "M_GSET_D"'s records】
         * has one record in accordance with
         * SET_VALUE="CST"
         * ID_SET="CB_AUTO_OFFSET"
         * SET_SEQ=1
         * SET_APPLY=1
         */
        insertMGsetD(SET_VALUE_CST);

        /**
         * 【Table "T_CSB_H"'s records】
         * has one record in accordance with
         * BALANCE_AMT=100
         * ID_BILL_ACCOUNT=1
         * IS_DELETED=0
         * RECEIPT_NO=001
         * ID_CUST=1
         * CUR_CODE=1
         */
        insertTCsbH("001", "1");
        /**
         * 【Table "T_BIL_H"'s records】
         * has one record in accordance with
         * ID_CUST=1
         * BILL_CURRENCY=CC1
         * BILL_ACC=1
         * BILL_AMOUNT=150
         * PAID_AMOUNT=60
         * IS_DELETED=0
         * ID_REF=001
         */
        insertTBilH("001", "60", "1");
        /**
         * 【Table "T_CSB_D"'s records】
         * has no record
         */

        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        uvo.setUser_name("sysadmin");

        G_CSB_P01 gCsbP01 = new G_CSB_P01(queryDAO, updateDAO, uvo);

        gCsbP01.execute();
        /**
         * 【Insert into table "T_CSB_D"】
         * ID_REF=$id_ref
         * RECEIPT_NO=$receipt_no
         * AMT_DUE=$amt_due
         * AMT_PAID=$amt_due
         * IS_DELETED=0
         * DATE_CREATED=sysdate
         * DATE_UPDATED=sysdate
         * ID_LOGIN=LoginUserID
         */
        Map[] tCsbD = super.select("T_CSB_D", "", "");
        assertEquals("001", tCsbD[0].get("ID_REF").toString().trim());
        assertEquals("001", tCsbD[0].get("RECEIPT_NO").toString().trim());
        assertEquals("90", tCsbD[0].get("AMT_DUE"));
        assertEquals("90", tCsbD[0].get("AMT_PAID"));
        assertEquals("0", tCsbD[0].get("IS_DELETED"));

        assertEquals(nowDate, tCsbD[0].get("DATE_CREATED"));
        assertEquals(nowDate, tCsbD[0].get("DATE_UPDATED"));
        assertEquals(SYS_ADMIN, tCsbD[0].get("ID_LOGIN"));
        /**
         * 【Update table "T_BIL_H"】
         * PAID_AMOUNT=PAID_AMOUNT + $amt_due
         * IS_SETTLED=0
         * DATE_UPDATED=sysdate
         * ID_LOGIN=LoginUserID
         * WHERE ID_REF = $id_ref
         */
        Map[] tBilH = super.select("T_BIL_H", "TRIM(ID_REF)='001'", "");
        assertEquals("150", tBilH[0].get("PAID_AMOUNT"));
        assertEquals(nowDate, tBilH[0].get("DATE_UPDATED"));
        assertEquals(SYS_ADMIN, tBilH[0].get("ID_LOGIN"));
        /**
         * 【Update table "T_CSB_H"】
         * LAST_PAYMENT_DATE=sysdate
         * DATE_UPDATED=sysdate
         * ID_LOGIN=LoginUserID
         * WHERE LAST_PAYMENT_DATE < T.CSB_H.DATE_TRANS or LAST_PAYMENT_DATE =
         * null
         * on T_CSB_H.RECEIPT_NO = $receipt_no and ID_REF = T_CSB_D.ID_REF
         */
        // TODO;
        /**
         * 【Update table "T_CSB_H"】
         * BALANCE_AMT=$balance_amt (10)
         * DATE_UPDATED=sysdate
         * ID_LOGIN=LoginUserID
         * WHERE RECEIPT_NO = $receipt_no
         */
        Map[] tCsbH = super.select("T_CSB_H", "TRIM(RECEIPT_NO)='001'", "");
        assertEquals("10", tCsbH[0].get("BALANCE_AMT"));
        assertEquals(nowDate, tCsbH[0].get("DATE_UPDATED"));
        assertEquals(SYS_ADMIN, tCsbH[0].get("ID_LOGIN"));
        /**
         * 【Call the Module "G_ALT_P06"】
         * $BATCH_ID = "BC" $SUBJECT=ERR2SC045 $MSG = ERR2SC047
         */
        // TODO;

    }

    public void testExecute10() {

        /**
         * 【Table "M_GSET_D"'s records】
         * has one record in accordance with
         * SET_VALUE="CST"
         * ID_SET="CB_AUTO_OFFSET"
         * SET_SEQ=1
         * SET_APPLY=1
         */
        insertMGsetD(SET_VALUE_CST);

        /**
         * 【Table "T_CSB_H"'s records】
         * has one record in accordance with
         * BALANCE_AMT=100
         * ID_BILL_ACCOUNT=1
         * IS_DELETED=0
         * RECEIPT_NO=001
         * ID_CUST=1
         * CUR_CODE=1
         */
        insertTCsbH("001", "1");
        /**
         * 【Table "T_BIL_H"'s records】
         * has one record in accordance with
         * ID_CUST=1
         * BILL_CURRENCY=CC1
         * BILL_AMOUNT=150
         * PAID_AMOUNT=49
         * IS_DELETED=0
         * ID_REF=001
         */
        insertTBilH("001", "49", "1");
        /**
         * 【Table "T_CSB_D"'s records】
         * has one record in accordance with
         * ID_REF=001
         * AMT_PAID=10
         * DATE_UPDATED=2011-08-30
         * ID_LOGIN="admin"
         */
        insertTCsbD();
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        uvo.setUser_name("sysadmin");

        G_CSB_P01 gCsbP01 = new G_CSB_P01(queryDAO, updateDAO, uvo);

        gCsbP01.execute();
        /**
         * 【Update table "T_CSB_D"】
         * AMT_PAID=AMT_PAID + $balance_amt
         * DATE_UPDATED=sysdate
         * ID_LOGIN=LoginUserID
         * WHERE ID_REF = $id_ref and RECEIPT_NO = $receipt_No 
         */
        Map[] tCsbD = super.select("T_CSB_D", "TRIM(ID_REF)='001' and TRIM(RECEIPT_NO)='001'", "");
        assertEquals("110", tCsbD[0].get("AMT_PAID"));
        assertEquals(nowDate, tCsbD[0].get("DATE_UPDATED"));
        assertEquals(SYS_ADMIN, tCsbD[0].get("ID_LOGIN"));
        /**
         * 【Update table "T_BIL_H"】
         * PAID_AMOUNT=PAID_AMOUNT + $balance_amt
         * DATE_UPDATED=sysdate
         * ID_LOGIN=LoginUserID
         * WHERE ID_REF = $id_ref
         */
        Map[] tBilH = super.select("T_BIL_H", "TRIM(ID_REF)='001'", "");
        assertEquals("149", tBilH[0].get("PAID_AMOUNT"));
        assertEquals(nowDate, tBilH[0].get("DATE_UPDATED"));
        assertEquals(SYS_ADMIN, tBilH[0].get("ID_LOGIN"));
        /**
         * 【Update table "T_CSB_H"】
         * LAST_PAYMENT_DATE=sysdate
         * DATE_UPDATED=sysdate
         * ID_LOGIN=LoginUserID
         * WHERE LAST_PAYMENT_DATE < T.CSB_H.DATE_TRANS or LAST_PAYMENT_DATE =
         * null
         * on T_CSB_H.RECEIPT_NO = $receipt_no and ID_REF = T_CSB_D.ID_REF
         */
        // TODO;
        /**
         * 【Update table "T_CSB_H"】
         * BALANCE_AMT=$balance_amt (10)
         * DATE_UPDATED=sysdate
         * ID_LOGIN=LoginUserID
         * WHERE RECEIPT_NO = $receipt_no
         */
        Map[] tCsbH = super.select("T_CSB_H", "TRIM(RECEIPT_NO)='001'", "");
        assertEquals("0", tCsbH[0].get("BALANCE_AMT"));
        assertEquals(nowDate, tCsbH[0].get("DATE_UPDATED"));
        assertEquals(SYS_ADMIN, tCsbH[0].get("ID_LOGIN"));
        /**
         * 【Call the Module "G_ALT_P06"】
         * $BATCH_ID = "BC" $SUBJECT=ERR2SC045 $MSG = ERR2SC047
         */
        // TODO;

    }

    public void testExecute11() {

        /**
         * 【Table "M_GSET_D"'s records】
         * has one record in accordance with
         * SET_VALUE="CST"
         * ID_SET="CB_AUTO_OFFSET"
         * SET_SEQ=1
         * SET_APPLY=1
         */
        insertMGsetD(SET_VALUE_CST);

        /**
         * 【Table "T_CSB_H"'s records】
         * has one record in accordance with
         * BALANCE_AMT=100
         * ID_BILL_ACCOUNT=1
         * IS_DELETED=0
         * RECEIPT_NO=001
         * ID_CUST=1
         * CUR_CODE=1
         */
        insertTCsbH("001", "1");
        /**
         * 【Table "T_BIL_H"'s records】
         * has one record in accordance with
         * ID_CUST=1
         * BILL_CURRENCY=CC1
         * BILL_AMOUNT=150
         * PAID_AMOUNT=49
         * IS_DELETED=0
         * ID_REF=001
         */
        insertTBilH("001", "49", "1");
        /**
         * 【Table "T_CSB_D"'s records】
         * has no record
         */

        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        uvo.setUser_name("sysadmin");

        G_CSB_P01 gCsbP01 = new G_CSB_P01(queryDAO, updateDAO, uvo);

        gCsbP01.execute();
        /**
         * 【Insert into table "T_CSB_D"】
         * ID_REF=$id_ref
         * RECEIPT_NO=$receipt_no
         * AMT_DUE=$amt_due
         * AMT_PAID=$balance_amt
         * IS_DELETED=0
         * DATE_CREATED=sysdate
         * DATE_UPDATED=sysdate
         * ID_LOGIN=LoginUserID
         */
        Map[] tCsbD = super.select("T_CSB_D", "", "");
        assertEquals("001", tCsbD[0].get("ID_REF").toString().trim());
        assertEquals("001", tCsbD[0].get("RECEIPT_NO").toString().trim());
        assertEquals("101", tCsbD[0].get("AMT_DUE"));
        assertEquals("100", tCsbD[0].get("AMT_PAID"));
        assertEquals("0", tCsbD[0].get("IS_DELETED"));

        assertEquals(nowDate, tCsbD[0].get("DATE_CREATED"));
        assertEquals(nowDate, tCsbD[0].get("DATE_UPDATED"));
        assertEquals(SYS_ADMIN, tCsbD[0].get("ID_LOGIN"));
        /**
         * 【Update table "T_BIL_H"】
         * PAID_AMOUNT=PAID_AMOUNT + $balance_amt
         * DATE_UPDATED=sysdate
         * ID_LOGIN=LoginUserID
         * WHERE ID_REF = $id_ref
         */
        Map[] tBilH = super.select("T_BIL_H", "TRIM(ID_REF)='001'", "");
        assertEquals("149", tBilH[0].get("PAID_AMOUNT"));
        assertEquals(nowDate, tBilH[0].get("DATE_UPDATED"));
        assertEquals(SYS_ADMIN, tBilH[0].get("ID_LOGIN"));
        /**
         * 【Update table "T_CSB_H"】
         * LAST_PAYMENT_DATE=sysdate
         * DATE_UPDATED=sysdate
         * ID_LOGIN=LoginUserID
         * WHERE LAST_PAYMENT_DATE < T.CSB_H.DATE_TRANS or LAST_PAYMENT_DATE =
         * null
         * on T_CSB_H.RECEIPT_NO = $receipt_no and ID_REF = T_CSB_D.ID_REF
         */
        // TODO;
        /**
         * 【Update table "T_CSB_H"】
         * BALANCE_AMT=$balance_amt (10)
         * DATE_UPDATED=sysdate
         * ID_LOGIN=LoginUserID
         * WHERE RECEIPT_NO = $receipt_no
         */
        Map[] tCsbH = super.select("T_CSB_H", "TRIM(RECEIPT_NO)='001'", "");
        assertEquals("0", tCsbH[0].get("BALANCE_AMT"));
        assertEquals(nowDate, tCsbH[0].get("DATE_UPDATED"));
        assertEquals(SYS_ADMIN, tCsbH[0].get("ID_LOGIN"));
        /**
         * 【Call the Module "G_ALT_P06"】
         * $BATCH_ID = "BC" $SUBJECT=ERR2SC045 $MSG = ERR2SC047
         */
        // TODO;

    }

    public void testExecute12() {

        /**
         * 【Table "M_GSET_D"'s records】
         * has one record in accordance with
         * SET_VALUE="BCA"
         * ID_SET="CB_AUTO_OFFSET"
         * SET_SEQ=1
         * SET_APPLY=1
         */
        insertMGsetD(SET_VALUE_BAC);

        /**
         * 【Table "T_CSB_H"'s records】
         * has one record in accordance with
         * BALANCE_AMT=100
         * ID_BILL_ACCOUNT=2
         * IS_DELETED=0
         * RECEIPT_NO=001
         * ID_CUST=1
         * CUR_CODE=1
         */
        insertTCsbH("001", "2");
        /**
         * 【Table "T_BIL_H"'s records】
         * has one record in accordance with
         * ID_CUST=1
         * BILL_CURRENCY=CC1
         * BILL_ACC=1
         * BILL_AMOUNT=150
         * PAID_AMOUNT=60
         * IS_DELETED=0
         * ID_REF=001
         */
        insertTBilH("001", "60", "2");
        /**
         * 【Table "T_CSB_D"'s records】
         * has one record in accordance with
         * ID_REF=001
         * AMT_PAID=10
         * DATE_UPDATED=2011-08-30
         * ID_LOGIN="admin"
         */
        insertTCsbD();
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        uvo.setUser_name("sysadmin");

        G_CSB_P01 gCsbP01 = new G_CSB_P01(queryDAO, updateDAO, uvo);

        gCsbP01.setBatchId("A");
        gCsbP01.execute();
        /**
         * 【Update table "T_CSB_H"】
         * BALANCE_AMT=$balance_amt (100)
         * DATE_UPDATED=sysdate
         * ID_LOGIN=LoginUserID
         * WHERE RECEIPT_NO = $receipt_no
         */
        Map[] tCsbH = super.select("T_CSB_H", "TRIM(RECEIPT_NO)='001'", "");
        // tCsbH.balanceAmt-(T_bil_H.BillAmount-T_bil_H.paidAmount) 100-(150-60)
        assertEquals("10", tCsbH[0].get("BALANCE_AMT"));
        assertEquals(nowDate, tCsbH[0].get("DATE_UPDATED"));
        assertEquals(SYS_ADMIN, tCsbH[0].get("ID_LOGIN"));
        /**
         * 【Call the Module "G_ALT_P06"】
         * $BATCH_ID = "BC" $SUBJECT=ERR2SC045 $MSG = ERR2SC046
         * $RECEIPT_NO=$RECEIPT_NO[]
         */
        // TODO;
    }

    public void testExecute13() {

        /**
         * 【Table "M_GSET_D"'s records】
         * has one record in accordance with
         * SET_VALUE="BCA"
         * ID_SET="CB_AUTO_OFFSET"
         * SET_SEQ=1
         * SET_APPLY=1
         */
        insertMGsetD(SET_VALUE_BAC);

        /**
         * 【Table "T_CSB_H"'s records】
         * has one record in accordance with
         * BALANCE_AMT=100
         * ID_BILL_ACCOUNT=2
         * IS_DELETED=0
         * RECEIPT_NO=001
         * ID_CUST=1
         * CUR_CODE=1
         */
        insertTCsbH("001", "2");
        /**
         * 【Table "T_BIL_H"'s records】
         * has one record in accordance with
         * ID_CUST=1
         * BILL_CURRENCY=CC1
         * BILL_ACC=1
         * BILL_AMOUNT=150
         * PAID_AMOUNT=60
         * IS_DELETED=0
         * ID_REF=001
         */
        insertTBilH("001", "60", "2");
        /**
         * 【Table "T_CSB_D"'s records】
         * has one record in accordance with
         * ID_REF=001
         * AMT_PAID=10
         * DATE_UPDATED=2011-08-30
         * ID_LOGIN="admin"
         */
        insertTCsbD();
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        uvo.setUser_name("sysadmin");

        G_CSB_P01 gCsbP01 = new G_CSB_P01(queryDAO, updateDAO, uvo);

        gCsbP01.setBatchId("A");
        gCsbP01.execute();

        /**
         * 【Update table "T_CSB_D"】
         * AMT_PAID=AMT_PAID + $amt_due (100)
         * DATE_UPDATED=sysdate
         * ID_LOGIN=LoginUserID
         * WHERE ID_REF = $id_ref and RECEIPT_NO = $receipt_No 
         */
        Map[] tCsbD = super.select("T_CSB_D", "TRIM(ID_REF)='001' and TRIM(RECEIPT_NO)='001'", "");
        assertEquals("100", tCsbD[0].get("AMT_PAID"));
        assertEquals(nowDate, tCsbD[0].get("DATE_UPDATED"));
        assertEquals(SYS_ADMIN, tCsbD[0].get("ID_LOGIN"));
        /**
         * 【Update table "T_BIL_H"】
         * PAID_AMOUNT=PAID_AMOUNT + $amt_due
         * IS_SETTLED=0
         * DATE_UPDATED=sysdate
         * ID_LOGIN=LoginUserID
         * WHERE ID_REF = $id_ref
         */
        Map[] tBilH = super.select("T_BIL_H", "TRIM(ID_REF)='001'", "");
        assertEquals("150", tBilH[0].get("PAID_AMOUNT"));
        assertEquals(nowDate, tBilH[0].get("DATE_UPDATED"));
        assertEquals(SYS_ADMIN, tBilH[0].get("ID_LOGIN"));
        /**
         * 【Update table "T_CSB_H"】
         * LAST_PAYMENT_DATE=sysdate
         * DATE_UPDATED=sysdate
         * ID_LOGIN=LoginUserID
         * WHERE LAST_PAYMENT_DATE < T.CSB_H.DATE_TRANS or LAST_PAYMENT_DATE =
         * null
         * on T_CSB_H.RECEIPT_NO = $receipt_no and ID_REF = T_CSB_D.ID_REF
         */
        // TODO;
        /**
         * 【Update table "T_CSB_H"】
         * BALANCE_AMT=$balance_amt (10)
         * DATE_UPDATED=sysdate
         * ID_LOGIN=LoginUserID
         * WHERE RECEIPT_NO = $receipt_no
         */
        Map[] tCsbH = super.select("T_CSB_H", "TRIM(RECEIPT_NO)='001'", "");
        assertEquals("10", tCsbH[0].get("BALANCE_AMT"));
        assertEquals(nowDate, tCsbH[0].get("DATE_UPDATED"));
        assertEquals(SYS_ADMIN, tCsbH[0].get("ID_LOGIN"));
        /**
         * 【Call the Module "G_ALT_P06"】
         * $BATCH_ID = "BC" $SUBJECT=ERR2SC045 $MSG = ERR2SC047
         */
        // TODO;

    }

    public void testExecute14() {

        /**
         * 【Table "M_GSET_D"'s records】
         * has one record in accordance with
         * SET_VALUE="BCA"
         * ID_SET="CB_AUTO_OFFSET"
         * SET_SEQ=1
         * SET_APPLY=1
         */
        insertMGsetD(SET_VALUE_BAC);

        /**
         * 【Table "T_CSB_H"'s records】
         * has 2 records
         */
        insertTCsbH("001", "1");
        insertTCsbH("002", "1");
        /**
         * 【Table "T_BIL_H"'s records】
         * has one record in accordance with
         * ID_CUST=1
         * BILL_CURRENCY=CC1
         * BILL_ACC=1
         * BILL_AMOUNT=150
         * PAID_AMOUNT=60
         * IS_DELETED=0
         * ID_REF=001
         */
        insertTBilH("001", "60", "1");
        /**
         * 【Table "T_CSB_D"'s records】
         * has one record in accordance with
         * ID_REF=001
         * AMT_PAID=10
         * DATE_UPDATED=2011-08-30
         * ID_LOGIN="admin"
         */
        insertTCsbD();
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        uvo.setUser_name("sysadmin");

        G_CSB_P01 gCsbP01 = new G_CSB_P01(queryDAO, updateDAO, uvo);

        gCsbP01.setBatchId("A");
        gCsbP01.execute();

        /**
         * 【Update table "T_CSB_D"】
         * AMT_PAID=AMT_PAID + $amt_due (100)
         * DATE_UPDATED=sysdate
         * ID_LOGIN=LoginUserID
         * WHERE ID_REF = $id_ref and RECEIPT_NO = $receipt_No 
         */
        Map[] tCsbD = super.select("T_CSB_D", "TRIM(ID_REF)='001' and TRIM(RECEIPT_NO)='001'", "");
        assertEquals("100", tCsbD[0].get("AMT_PAID"));
        assertEquals(nowDate, tCsbD[0].get("DATE_UPDATED"));
        assertEquals(SYS_ADMIN, tCsbD[0].get("ID_LOGIN"));
        /**
         * 【Update table "T_BIL_H"】
         * PAID_AMOUNT=PAID_AMOUNT + $amt_due
         * IS_SETTLED=0
         * DATE_UPDATED=sysdate
         * ID_LOGIN=LoginUserID
         * WHERE ID_REF = $id_ref
         */
        Map[] tBilH = super.select("T_BIL_H", "TRIM(ID_REF)='001'", "");
        assertEquals("150", tBilH[0].get("PAID_AMOUNT"));
        assertEquals(nowDate, tBilH[0].get("DATE_UPDATED"));
        assertEquals(SYS_ADMIN, tBilH[0].get("ID_LOGIN"));
        /**
         * 【Update table "T_CSB_H"】
         * LAST_PAYMENT_DATE=sysdate
         * DATE_UPDATED=sysdate
         * ID_LOGIN=LoginUserID
         * WHERE LAST_PAYMENT_DATE < T.CSB_H.DATE_TRANS or LAST_PAYMENT_DATE =
         * null
         * on T_CSB_H.RECEIPT_NO = $receipt_no and ID_REF = T_CSB_D.ID_REF
         */
        // TODO;
        /**
         * 【Update table "T_CSB_H"】
         * BALANCE_AMT=$balance_amt
         * DATE_UPDATED=sysdate
         * ID_LOGIN=LoginUserID
         * WHERE RECEIPT_NO = $receipt_no
         */
        Map[] tCsbH = super.select("T_CSB_H", "TRIM(RECEIPT_NO)='001'", "");
        assertEquals("10", tCsbH[0].get("BALANCE_AMT"));
        assertEquals(nowDate, tCsbH[0].get("DATE_UPDATED"));
        assertEquals(SYS_ADMIN, tCsbH[0].get("ID_LOGIN"));
        /**
         * 【Update table "T_CSB_H"】
         * BALANCE_AMT=$balance_amt
         * DATE_UPDATED=sysdate
         * ID_LOGIN=LoginUserID
         * WHERE RECEIPT_NO = $receipt_no
         */
        Map[] tCsbH2 = super.select("T_CSB_H", "TRIM(RECEIPT_NO)='002'", "");
        assertEquals("100", tCsbH2[0].get("BALANCE_AMT"));
        assertEquals("2011-08-30 00:00:00.0", tCsbH2[0].get("DATE_UPDATED"));
        assertEquals(SYS_ADMIN, tCsbH2[0].get("ID_LOGIN"));
        /**
         * 【Call the Module "G_ALT_P06"】
         * $BATCH_ID = "BC" $SUBJECT=ERR2SC045 $MSG = ERR2SC047
         */
        // TODO;

    }

    public void testExecute15() {

        /**
         * 【Table "M_GSET_D"'s records】
         * has one record in accordance with
         * SET_VALUE="BCA"
         * ID_SET="CB_AUTO_OFFSET"
         * SET_SEQ=1
         * SET_APPLY=1
         */
        insertMGsetD(SET_VALUE_BAC);

        /**
         * 【Table "T_CSB_H"'s records】
         * has 2 records
         */
        insertTCsbH("001", "1");
        insertTCsbH("002", "1");
        /**
         * 【Table "T_BIL_H"'s records】
         * has 2 records
         */
        insertTBilH("001", "60", "1");
        insertTBilH("002", "60", "1");
        /**
         * 【Table "T_CSB_D"'s records】
         * has one record in accordance with
         * ID_REF=001
         * AMT_PAID=10
         * DATE_UPDATED=2011-08-30
         * ID_LOGIN="admin"
         */
        insertTCsbD();
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        uvo.setUser_name("sysadmin");

        G_CSB_P01 gCsbP01 = new G_CSB_P01(queryDAO, updateDAO, uvo);

        gCsbP01.setBatchId("A");
        gCsbP01.execute();

        /**
         * 【Update table "T_CSB_D"】
         * AMT_PAID=AMT_PAID + $balance_amt
         * DATE_UPDATED=sysdate
         * ID_LOGIN=LoginUserID
         * WHERE ID_REF = $id_ref and RECEIPT_NO = $receipt_No 
         */
        Map[] tCsbD = super.select("T_CSB_D", "TRIM(ID_REF)='002' and TRIM(RECEIPT_NO)='001'", "");
        assertEquals("10", tCsbD[0].get("AMT_PAID"));
        assertEquals(nowDate, tCsbD[0].get("DATE_UPDATED"));
        assertEquals(SYS_ADMIN, tCsbD[0].get("ID_LOGIN"));
        /**
         * 【Update table "T_BIL_H"】
         * PAID_AMOUNT=PAID_AMOUNT + $amt_due
         * IS_SETTLED=0
         * DATE_UPDATED=sysdate
         * ID_LOGIN=LoginUserID
         * WHERE ID_REF = $id_ref
         */
        Map[] tBilH = super.select("T_BIL_H", "TRIM(ID_REF)='001'", "");
        assertEquals("150", tBilH[0].get("PAID_AMOUNT"));
        assertEquals(nowDate, tBilH[0].get("DATE_UPDATED"));
        assertEquals(SYS_ADMIN, tBilH[0].get("ID_LOGIN"));

        /**
         * 【Update table "T_BIL_H"】
         * PAID_AMOUNT=PAID_AMOUNT + $amt_due
         * IS_SETTLED=0
         * DATE_UPDATED=sysdate
         * ID_LOGIN=LoginUserID
         * WHERE ID_REF = $id_ref
         */
        Map[] tBilH2 = super.select("T_BIL_H", "TRIM(ID_REF)='002'", "");
        assertEquals("150", tBilH2[0].get("PAID_AMOUNT"));
        assertEquals(nowDate, tBilH2[0].get("DATE_UPDATED"));
        assertEquals(SYS_ADMIN, tBilH2[0].get("ID_LOGIN"));
        /**
         * 【Update table "T_CSB_H"】
         * LAST_PAYMENT_DATE=sysdate
         * DATE_UPDATED=sysdate
         * ID_LOGIN=LoginUserID
         * WHERE LAST_PAYMENT_DATE < T.CSB_H.DATE_TRANS or LAST_PAYMENT_DATE =
         * null
         * on T_CSB_H.RECEIPT_NO = $receipt_no and ID_REF = T_CSB_D.ID_REF
         */
        // TODO;
        /**
         * 【Update table "T_CSB_H"】
         * BALANCE_AMT=$balance_amt
         * DATE_UPDATED=sysdate
         * ID_LOGIN=LoginUserID
         * WHERE RECEIPT_NO = $receipt_no
         */
        Map[] tCsbH = super.select("T_CSB_H", "TRIM(RECEIPT_NO)='001'", "");
        assertEquals("0", tCsbH[0].get("BALANCE_AMT"));
        assertEquals(nowDate, tCsbH[0].get("DATE_UPDATED"));
        assertEquals(SYS_ADMIN, tCsbH[0].get("ID_LOGIN"));
        /**
         * 【Update table "T_CSB_H"】
         * BALANCE_AMT=$balance_amt
         * DATE_UPDATED=sysdate
         * ID_LOGIN=LoginUserID
         * WHERE RECEIPT_NO = $receipt_no
         */
        Map[] tCsbH2 = super.select("T_CSB_H", "TRIM(RECEIPT_NO)='002'", "");
        assertEquals("20", tCsbH2[0].get("BALANCE_AMT"));
        assertEquals(nowDate, tCsbH2[0].get("DATE_UPDATED"));
        assertEquals(SYS_ADMIN, tCsbH2[0].get("ID_LOGIN"));
        /**
         * 【Call the Module "G_ALT_P06"】
         * $BATCH_ID = "BC" $SUBJECT=ERR2SC045 $MSG = ERR2SC047
         */
        // TODO;

    }

    public void testExecute16() {

        /**
         * 【Table "M_GSET_D"'s records】
         * has one record in accordance with
         * SET_VALUE="BCA"
         * ID_SET="CB_AUTO_OFFSET"
         * SET_SEQ=1
         * SET_APPLY=1
         */
        insertMGsetD(SET_VALUE_BAC);

        /**
         * 【Table "T_CSB_H"'s records】
         * has 2 records
         */
        insertTCsbH("001", "1");
        insertTCsbH("002", "1");
        /**
         * 【Table "T_BIL_H"'s records】
         * no record
         */

        /**
         * 【Table "T_CSB_D"'s records】
         * has one record in accordance with
         * ID_REF=001
         * AMT_PAID=10
         * DATE_UPDATED=2011-08-30
         * ID_LOGIN="admin"
         */
        insertTCsbD();
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        uvo.setUser_name("sysadmin");

        G_CSB_P01 gCsbP01 = new G_CSB_P01(queryDAO, updateDAO, uvo);

        gCsbP01.setBatchId("A");
        gCsbP01.execute();

        /**
         * 【Update table "T_CSB_H"】
         * BALANCE_AMT=$balance_amt (100)
         * DATE_UPDATED=sysdate
         * ID_LOGIN=LoginUserID
         * WHERE RECEIPT_NO = $receipt_no
         */
        Map[] tCsbH = super.select("T_CSB_H", "TRIM(RECEIPT_NO)='001'", "");
        assertEquals("100", tCsbH[0].get("BALANCE_AMT"));
        //T_BIL_H no data  tCsbH will not be update
        assertEquals("2011-08-30 00:00:00.0", tCsbH[0].get("DATE_UPDATED"));
        assertEquals(SYS_ADMIN, tCsbH[0].get("ID_LOGIN"));
        /**
         * 【Call the Module "G_ALT_P06"】
         * $BATCH_ID = "BC"
         * $SUBJECT=ERR2SC045
         * $MSG = ERR2SC046
         * $RECEIPT_NO=$RECEIPT_NO[]
         */
        // TODO;

    }

    public void testExecute17() {

        /**
         * 【Table "M_GSET_D"'s records】
         * has one record in accordance with
         * SET_VALUE="BCA"
         * ID_SET="CB_AUTO_OFFSET"
         * SET_SEQ=1
         * SET_APPLY=1
         */
        insertMGsetD(SET_VALUE_BAC);

        /**
         * 【Table "T_CSB_H"'s records】
         * has no record
         */
        /**
         * 【Table "T_BIL_H"'s records】
         * has one record in accordance with
         * ID_CUST=1
         * BILL_CURRENCY=CC1
         * BILL_AMOUNT=150
         * PAID_AMOUNT=60
         * IS_DELETED=0
         * ID_REF=001
         * 【Table "T_BIL_H"'s records】
         * BILL_ACC=1
         */
        insertTBilH("001", "60", "1");
        /**
         * 【Table "T_CSB_D"'s records】
         * has one record in accordance with
         * ID_REF=001
         * AMT_PAID=10
         * DATE_UPDATED=2011-08-30
         * ID_LOGIN="admin"
         */
        insertTCsbH("001", "123");
        insertTCsbD();
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        uvo.setUser_name("sysadmin");

        G_CSB_P01 gCsbP01 = new G_CSB_P01(queryDAO, updateDAO, uvo);

        gCsbP01.setBatchId("A");
        gCsbP01.execute();

        /**
         * 【Call the Module "G_ALT_P06"】
         * $BATCH_ID = "BC"
         * $SUBJECT=ERR2SC045
         * $MSG = ERR2SC046
         * $RECEIPT_NO=$RECEIPT_NO[]
         */
        // TODO;

    }

    public void testExecute18() {

        /**
         * 【Table "M_GSET_D"'s records】
         * has no record
         */

        /**
         * 【Table "T_CSB_H"'s records】
         * has one record in accordance with
         * BALANCE_AMT=100
         * ID_BILL_ACCOUNT=1
         * IS_DELETED=0
         * RECEIPT_NO=001
         * ID_CUST=1
         * CUR_CODE=CC1
         */
        insertTCsbH("001", "1");
        /**
         * 【Table "T_BIL_H"'s records】
         * has one record in accordance with
         * ID_CUST=1
         * BILL_CURRENCY=CC1
         * BILL_ACC=1
         * BILL_AMOUNT=150
         * PAID_AMOUNT=60
         * IS_DELETED=0
         * ID_REF=001
         */
        insertTBilH("001", "60", "1");
        /**
         * 【Table "T_CSB_D"'s records】
         * has one record in accordance with
         * ID_REF=001
         * AMT_PAID=10
         * DATE_UPDATED=2011-08-30
         * ID_LOGIN="admin"
         */
        insertTCsbD();
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        uvo.setUser_name("sysadmin");

        G_CSB_P01 gCsbP01 = new G_CSB_P01(queryDAO, updateDAO, uvo);

        gCsbP01.setBatchId("A");
        gCsbP01.execute();
        /**
         * 【Update table "T_CSB_D"】
         * AMT_PAID=AMT_PAID + $amt_due (100)
         * DATE_UPDATED=sysdate
         * ID_LOGIN=LoginUserID
         * WHERE ID_REF = $id_ref and RECEIPT_NO = $receipt_No 
         */
        Map[] tCsbD = super.select("T_CSB_D", "TRIM(ID_REF)='001' and TRIM(RECEIPT_NO)='001'", "");
        assertEquals("100", tCsbD[0].get("AMT_PAID"));
        assertEquals(nowDate, tCsbD[0].get("DATE_UPDATED"));
        assertEquals(SYS_ADMIN, tCsbD[0].get("ID_LOGIN"));
        /**
         * 【Update table "T_BIL_H"】
         * PAID_AMOUNT=PAID_AMOUNT + $amt_due
         * IS_SETTLED=0
         * DATE_UPDATED=sysdate
         * ID_LOGIN=LoginUserID
         * WHERE ID_REF = $id_ref
         */
        Map[] tBilH = super.select("T_BIL_H", "TRIM(ID_REF)='001'", "");
        assertEquals("150", tBilH[0].get("PAID_AMOUNT"));
        assertEquals(nowDate, tBilH[0].get("DATE_UPDATED"));
        assertEquals(SYS_ADMIN, tBilH[0].get("ID_LOGIN"));
        /**
         * 【Update table "T_CSB_H"】
         * LAST_PAYMENT_DATE=sysdate
         * DATE_UPDATED=sysdate
         * ID_LOGIN=LoginUserID
         * WHERE LAST_PAYMENT_DATE < T.CSB_H.DATE_TRANS or LAST_PAYMENT_DATE =
         * null
         * on T_CSB_H.RECEIPT_NO = $receipt_no and ID_REF = T_CSB_D.ID_REF
         */
        // TODO;
        /**
         * 【Update table "T_CSB_H"】
         * BALANCE_AMT=$balance_amt (10)
         * DATE_UPDATED=sysdate
         * ID_LOGIN=LoginUserID
         * WHERE RECEIPT_NO = $receipt_no
         */
        Map[] tCsbH = super.select("T_CSB_H", "TRIM(RECEIPT_NO)='001'", "");
        assertEquals("10", tCsbH[0].get("BALANCE_AMT"));
        assertEquals(nowDate, tCsbH[0].get("DATE_UPDATED"));
        assertEquals(SYS_ADMIN, tCsbH[0].get("ID_LOGIN"));
        /**
         * 【Call the Module "G_ALT_P06"】
         * $BATCH_ID = "BC" $SUBJECT=ERR2SC045 $MSG = ERR2SC047
         */
        // TODO;

    }

    public void testExecute19() {

        /**
         * /**
         * 【Table "M_GSET_D"'s records】
         * has one record in accordance with
         * SET_VALUE="BCA"
         * ID_SET="CB_AUTO_OFFSET"
         * SET_SEQ=1
         * SET_APPLY=1
         */
        insertMGsetD(SET_VALUE_BAC);

        /**
         * 【Table "T_CSB_H"'s records】
         * has no record
         */
        /**
         * 【Table "T_BIL_H"'s records】
         * has no record
         */
        /**
         * 【Table "T_CSB_D"'s records】
         * has no record
         */
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        uvo.setUser_name("sysadmin");

        G_CSB_P01 gCsbP01 = new G_CSB_P01(queryDAO, updateDAO, uvo);

        gCsbP01.execute();

        /**
         * 【Call the Module "G_ALT_P06"】
         * no call
         */
        // TODO;

    }
}
