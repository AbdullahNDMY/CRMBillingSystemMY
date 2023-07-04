/**
 * @(#)G_BIL_P01.java
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */

package nttdm.bsys.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Clob;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import jp.terasoluna.fw.web.codelist.MappedCodeListLoader;
import jp.terasoluna.fw.web.struts.action.GlobalMessageResources;
import nttdm.bsys.common.bean.G_BIL_P01Bean;
import nttdm.bsys.common.bean.G_BIL_P01_Input;
import nttdm.bsys.common.bean.G_BIL_P01_Output;
import nttdm.bsys.common.bean.T_SET_BATCH;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.g_alt.AlertUserDto;
import nttdm.bsys.g_alt.G_ALT_P06Input;
import nttdm.bsys.util.ApplicationContextProvider;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;

/**
 * Process To Generate Billing Document
 * 
 * @author bench
 */
public class G_BIL_P01 {

    /**
     * SELECT.BSYS.SQL040
     */
    private static final String GET_GSETD_BY_ID_SET = "SELECT.BSYS.SQL040";
    
    /**
     * SELECT.BSYS.SQL041
     */
    private static final String GET_TEMP_R = "SELECT.BSYS.SQL041";
    
    /**
     * SELECT.BSYS.SQL041
     */
    private static final String GET_TEMP_R_BY_BILLACCID = "SELECT.BSYS.SQL041";
    
    /**
     * SELECT.BSYS.SQL042
     */
    private static final String GET_CUST_INFO = "SELECT.BSYS.SQL042";
    
    /**
     * SELECT.BSYS.SQL045
     */
    private static final String GET_NO_IMPORT = "SELECT.BSYS.SQL045";
    
    /**
     * SELECT.BSYS.SQL047
     */
    private static final String GET_CLEAR_CALL = "SELECT.BSYS.SQL047";
    
    /**
     * SELECT.BSYS.SQL050
     */
    private static final String GET_RESULT_S1 = "SELECT.BSYS.SQL050";
    
    /**
     * SELECT.BSYS.SQL051
     */
    private static final String GET_RESULT_U1 = "SELECT.BSYS.SQL051";
    
    /**
     * SELECT.BSYS.SQL052
     */
    private static final String GET_INVOICE_SUMMARY = "SELECT.BSYS.SQL052";
    
    /**
     * SELECT.BSYS.SQL049
     */
    private static final String GET_LAST_PAYMENT = "SELECT.BSYS.SQL049";
    
    /**
     * SELECT.BSYS.SQL054
     */
    private static final String GET_PREVIOUS_OVER_PAID = "SELECT.BSYS.SQL054";
    
    /**
     * SELECT.BSYS.SQL055
     */
    private static final String GET_REJECTED_PAYMENT = "SELECT.BSYS.SQL055";
    
    /**
     * SELECT.BSYS.SQL056
     */
    private static final String GET_PAID_PRE_INVOICE = "SELECT.BSYS.SQL056";
    
    /**
     * SELECT.BSYS.SQL057
     */
    private static final String GET_REVISION_BILL_DOC = "SELECT.BSYS.SQL057";
    
    /**
     * SELECT.BSYS.SQL058
     */
    private static final String GET_BILACC_TOTAL_AMTDUE = "SELECT.BSYS.SQL058";
    
    /**
     * INSERT.BSYS.SQL010
     */
    private static final String INSERT_T_BIL_H = "INSERT.BSYS.SQL010";
    
    /**
     * INSERT.BSYS.SQL011
     */
    private static final String INSERT_T_BIL_D = "INSERT.BSYS.SQL011";
    
    /**
     * INSERT.BSYS.SQL012
     */
    private static final String INSERT_T_BIL_S = "INSERT.BSYS.SQL012";
    
    /**
     * UPDATE.BSYS.SQL014
     */
    private static final String UPDATE_T_BAC_D = "UPDATE.BSYS.SQL014";
    
    /**
     * UPDATE.BSYS.SQL015
     */
    private static final String UPDATE_T_CUST_PLAN_D = "UPDATE.BSYS.SQL015";
    
    /**
     * UPDATE.BSYS.SQL016
     */
    private static final String UPDATE_T_CLC_IMP_MONTH = "UPDATE.BSYS.SQL016";
    
    /**
     * UPDATE.BSYS.SQL017"
     */
    private static final String UPDATE_T_CUST_PLAN_H = "UPDATE.BSYS.SQL017";
    
    /**
     * UPDATE.BSYS.SQL018
     */
    private static final String UPDATE_T_BAC_H = "UPDATE.BSYS.SQL018";
    
    /**
     * UPDATE.BSYS.SQL019
     */
    private static final String UPDATE_EXPIRED_SERVICE = "UPDATE.BSYS.SQL019";
    
    /**
     * UPDATE.BSYS.SQL020
     */
    private static final String UPDATE_T_CSB_H_IS_EXPORT = "UPDATE.BSYS.SQL020";
    
    /**
     * UPDATE.BSYS.SQL021
     */
    private static final String UPDATE_T_BIL_H_IS_EXPORT = "UPDATE.BSYS.SQL021";

    /**
     * UPDATE.BSYS.SQL022
     */
    private static final String UPDATE_BILLING_STATUS = "UPDATE.BSYS.SQL022";
    
    /**
     * PRORATE_SVCSTART
     */
    private static final String PRORATE_SVCSTART = "PRORATE_SVCSTART";
    
    /**
     * PRORATE_SVCEND
     */
    private static final String PRORATE_SVCEND = "PRORATE_SVCEND";
    
    /**
     * FULLPERIOD_FULLAMT
     */
    private static final String FULLPERIOD_FULLAMT = "FULLPERIOD_FULLAMT";
    
    /**
     * FULLPERIOD_MODE
     */
    private static final String FULLPERIOD_MODE = "FULLPERIOD_MODE";

    /**
     * yyyyMMdd
     */
    private static final String FORMAT_SORT_DATE = "yyyyMMdd";
    
    /**
     * yyyyMM
     */
    private static final String FORMAT_SORT_MONTH = "yyyyMM";

    /**
     * No billing need to be generated
     */
    private static String ERROR_MESSAGE = "No billing need to be generated";
    
    /**
     * Error while generating billing
     */
    private static String ERROR_EXCEPTION = "Billing Account: <BILL_ACC>, Customer Plan ID: <ID_CUST_PLAN> is no successful to generate billing - error while generate billing.";
    
    /**
     * Error for negative billing amount in Batch
     */
    private static String ERROR_MESSAGE_BAC="Billing Account fail to generate billing document due to negative billing amount: <BILL_ACC>.";

    /**
     * BillCurrecy for Japan
     */
    private static String BILLCURRENCY_JAPAN="JPY";
    
    /**
     * Error for negative billing amount in Screen
     */
    private static String SCRREN_ERROR_MESSAGE_BAC="Fail to generate billing document due to negative billing amount.";
    /**
     * Generate Billing Document Batch Type
     */
    private static String BATCH_TYPE = "GB";

    /**
     * BS2 - First Bill Generation: billing status = In Progress 
     */
    private static String BILLING_STATUS_IN_PROCESS = "BS2";
    
    /**
     * BS3 - Last Bill Generation: billing status = Completed
     */
    private static String BILLING_STATUS_COMPLETED = "BS3";
    
    /**
     * DISC_TYPE = "O"
     */
    private static String BILLING_DISC_TYPE="O";
    
    /**
     * BILLING_DISC_CAL_YES = "Yes"
     */
    private static String BILLING_DISC_CAL_YES="Yes";
    
    /**
     * BILLING_DISC_CAL_NO = "No"
     */
    private static String BILLING_DISC_CAL_NO="No";
    
    /**
     * QueryDAO
     */
    private QueryDAO queryDAO;
    
    /**
     * UpdateDAO
     */
    private UpdateDAO updateDAO;

    /**
     * Billing Information list
     */
    List<Map<String, Object>> TEMP_BIL_D = null;

    /**
     * Index for item id of TEMP_BIL_D
     */
    private int index = 0;
    
    /**
     * T_BAC_D collection fields
     */
    List<String> mField = null;

    /**
     * sysdate
     */
    private Date running_date = null;

    /**
     * sysdate(yyyyMM)
     */
    private String billing_month = null;

    /**
     * sysdate(yyyyMMdd)
     */
    private String billing_date = null;

    /**
     * the current month's last day(yyyyMMdd)
     */
    private String billing_date_last_day = null;

    /**
     * Government Service Tax
     */
    private String pGST = null;

    /**
     * SYSTEMBASE
     */
    private String pSysBase = null;
    
    /**
     * CB_AUTO_OFFSET
     */
    private String pAutoOffSet = null;
    
    /**
     * Generated documents count
     */
    private int countGenDoc;

    /**
     * Generated documents' Billing Account list
     */
    private List<String> listBillDoc;

    /**
     * Login User ID
     */
    private String loginUserId;
    
    /**
     * auditIdModule
     */
    private String auditIdModule = null;
    
    /**
     * auditIdSubModule
     */
    private String auditIdSubModule = null;
    
    /**
     * auditReference
     */
    private String auditReference = null;
    
    /**
     * auditStatus
     */
    private String auditStatus = null;
    
    /**
     * auditId
     */
    private Integer auditId = null;

    /**
     * GlobalMessageResources
     */
    private GlobalMessageResources msgResource;
    
    /**
     * error message info
     */
    private List<Map<String,Object>> listErrorInfo = new ArrayList<Map<String,Object>>();
    
    /**
     * error billAcc info
     */
    private List<String> billAccList=new ArrayList<String>();
    
    /**
     * error billAccMap info
     */
    private  Map<String, Object> mapp = new HashMap<String, Object>();
    
    /**
     * batchId
     */
    private Integer idBatch=0;

    // #192 Start
    private String billingOption;
    // #192 End
    
    //wcbeh@20161209 #217 - G_BIL Item Discount Display Flag
    private String displayDiscFlag;
    
    /*#594: [B1] Customer Maintenance CT 20190604*/
	private List<String> taxExemptedList = new ArrayList<String>();
	/*#594: [B1] Customer Maintenance CT 20190604*/
    
    /**
     * idBatch を取得する
     * @return idBatch
     */
    public Integer getIdBatch() {
        return idBatch;
    }

    /**
     * batchId を設定する
     * @param idBatch
     *            idBatch
     */
    public void setIdBatch(Integer idBatch) {
        this.idBatch = idBatch;
    }

    /**
     * Constructor
     * 
     * @param queryDAO
     *            QueryDAO
     * @param updateDAO
     *            UpdateDAO
     */
    public G_BIL_P01(QueryDAO queryDAO, UpdateDAO updateDAO) {
        this.queryDAO = queryDAO;
        this.updateDAO = updateDAO;

        this.TEMP_BIL_D = new ArrayList<Map<String, Object>>();

        // initialize data collection fields
        this.mField = new ArrayList<String>();
        this.mField.add(0, "ID_BIF");
        this.mField.add(1, "ID_QCS");
        this.mField.add(2, "ID_QUO");
        this.mField.add(3, "CUST_PO");
        this.mField.add(4, "AC_MANAGER");
        this.mField.add(5, "TERM");
        this.mField.add(6, "TERM_DAY");

        // generate billing date
        this.running_date = new Date();
        
        // initialize count of generated doc
        this.countGenDoc = 0;
        this.listBillDoc = new ArrayList<String>();

        // initialize resource
        this.msgResource = GlobalMessageResources.getInstance();
        
        // #192 Start
		this.billingOption = "0";
        // #192 End
    }

    /**
     * Entry process to generate billing document
     * 
     * @param input
     *            G_BIL_P01_Input
     * 
     * @return G_BIL_P01_Output
     */
    public G_BIL_P01_Output execute(G_BIL_P01_Input input) {
        // Audit Trail
        if (auditIdModule != null) {
            this.auditId = CommonUtils.auditTrailBegin(input.getUserId(),
                    this.auditIdModule, this.auditIdSubModule,
                    this.auditReference, this.auditStatus,
                    BillingSystemConstants.AUDIT_TRAIL_CREATED);
        }
        
        // executing batch id
        int id_batch = 0;

        // initialize output
        G_BIL_P01_Output out = new G_BIL_P01_Output();

        // reset value
        this.reset();

        this.initGlobalParamter(input);
        
        // get module ID from input and determine process type
        String moduleId = input.getModuleId();
        if (moduleId.equals("E")) {
            // process for batch
            
            // #192 Start
    		this.billingOption = input.getBillingOption();
            // #192 End
    		
            // call G_SET_P01 to check batch status and get batch id
            G_SET_P01 gSetP01 = new G_SET_P01(this.queryDAO, this.updateDAO);
            T_SET_BATCH t_batch = new T_SET_BATCH();
            t_batch.setSTATUS("1");
            t_batch.setBATCH_TYPE(BATCH_TYPE);
            id_batch = gSetP01.G_SET_P01_GetIdBatch(t_batch).getBatch_id();
            this.idBatch=id_batch;
            
            // check status of batch
            if (id_batch != -1) {
                if(input.getRunFrom().equals("BatchRun")){
                    // batch can execute
                    this.loginUserId = BillingSystemConstants.USER_SYSTEM_DEFAULT;
                }
                if(input.getRunFrom().equals("BatchScreen")){
                    // batchScreen can execute
                    this.loginUserId = input.getUserId();
                }
                // get temp table R information
                Map<String, Object> param = new HashMap<String, Object>();
                param.put("LAST_DAY", billing_date_last_day);
                List<Map<String, Object>> temp_R = this.queryDAO.executeForMapList(GET_TEMP_R, param);
                if (temp_R != null && 0 < temp_R.size()) {
                    // start process
                    this.start_process(temp_R, moduleId);
                }

                //Add Message
                String billaccmsg="";
                if(0<listErrorInfo.size()){
                    for(Map<String, Object> map: listErrorInfo) {
                        //For insert_update_table 1.5.1 ErrorMessage Start
                        if(map.containsKey("BILL_ACC_MULTI")){
                            billaccmsg+=ERROR_MESSAGE_BAC.replace("<BILL_ACC>", map.get("BILL_ACC_MULTI").toString());
                        }//For insert_update_table 1.5.1 ErrorMessage End
                    }
                }
                
                // generate batch execution success log
                t_batch.setID_BATCH("" + id_batch);
                t_batch.setID_LOGIN(this.loginUserId);
                t_batch.setSTATUS("2");
                String message = "";
                List<String> params = new ArrayList<String>();
                if (0 < this.countGenDoc) {
                    params.add(this.billing_month);
                    params.add("" + this.countGenDoc);
                    message = this.msgResource.getMessage("info.ERR2SC038", params.toArray());
                    if(!billaccmsg.isEmpty()){
                        message+="<br/>"+billaccmsg;
                    }
                    t_batch.setMessage(message);
                } else {
                    params.add(this.billing_month);
                    message = this.msgResource.getMessage("info.ERR2SC040", params.toArray());
                    if(!billaccmsg.isEmpty()){
                        message+="<br/>"+billaccmsg;
                    }
                    t_batch.setMessage(message);
                }
                // #192 Start
                if ("0".equals(this.billingOption)) {
					t_batch.setFILENAME("Billing Option: All");
				}
                if ("1".equals(this.billingOption)) {
					t_batch.setFILENAME("Billing Option: Prepaid");
				}
                if ("2".equals(this.billingOption)) {
					t_batch.setFILENAME("Billing Option: Postpaid");
				}
                // #192 End
                gSetP01.G_SET_P01_GetIdBatch(t_batch);
                if(0==listErrorInfo.size()){
                    // call G-ALT-P06 to send notification
                    G_ALT_P06 gAltP06 = new G_ALT_P06(this.queryDAO, this.updateDAO);
                    
                    // get notification user list
                    List<AlertUserDto> alertUsers = 
                        queryDAO.executeForObjectList("SELECT.BSYS.SQL053", BATCH_TYPE);

                    G_ALT_P06Input input_p06 = new G_ALT_P06Input();
                    String subject = this.msgResource.getMessage("info.ERR2SC037", null);
                    input_p06.setSubject(subject);
                    input_p06.setMsg(message);
                    input_p06.setBatchId(BATCH_TYPE);
                    input_p06.setListAlertUser(alertUsers);
                    BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
                    uvo.setId_user(this.loginUserId);
                    gAltP06.execute(input_p06, uvo);
                }
            }
        } else {
            // process for screen
            this.loginUserId = input.getUserId();

            // get all custom plan by billing account id
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("LAST_DAY", billing_date_last_day);
            params.put("ID_BILL_ACCOUNT", input.getBillAccountId());
            List<Map<String, Object>> temp_R = this.queryDAO.executeForMapList(GET_TEMP_R_BY_BILLACCID, params);
            if (temp_R != null && 0 < temp_R.size()) {
                // generate billing document
                this.start_process(temp_R, moduleId);

                if(0==listErrorInfo.size()){
                    if (this.listBillDoc != null && this.listBillDoc.size() > 0){
                        out.setBatchStatus("1");
                        out.setIdRefList(this.listBillDoc);
                    } else {
                        out.setBatchStatus("3");
                        out.setErrorMessage(ERROR_MESSAGE);
                    }
                }
            } else {
                out.setBatchStatus("3");
                out.setErrorMessage(ERROR_MESSAGE);
            }
        }
        //if error do this
        if(0<listErrorInfo.size()){
            // write log for batch
            if (input.getModuleId().equals("E")) {
                String msg = "";
                String gernermsg="";
                List<String> params = new ArrayList<String>();
                params.add(this.billing_month);
                params.add("" + this.countGenDoc);
                if (0 < this.countGenDoc ) {
                    msg = this.msgResource.getMessage("info.ERR2SC038", params.toArray());
                } else {
                    msg = this.msgResource.getMessage("info.ERR2SC039", params.toArray());
                }
                // call G_SET_P01 to set batch fail record
                G_SET_P01 gSetP01 = new G_SET_P01(this.queryDAO, this.updateDAO);
                T_SET_BATCH t_batch_error = new T_SET_BATCH();
                t_batch_error.setSTATUS("3");
                t_batch_error.setID_BATCH("" + id_batch);
                t_batch_error.setID_LOGIN(this.loginUserId);
                t_batch_error.setBATCH_TYPE(BATCH_TYPE);
                for(Map<String, Object> map: listErrorInfo) {
                    //For insert_update_table 1.5.1 ErrorMessage Start
                    if(map.containsKey("BILL_ACC_MULTI")){
                        //do nothing
                    }//For insert_update_table 1.5.1 ErrorMessage End
                    else{
                        String idBillingAccount = CommonUtils.toString(map.get("BILL_ACC"));
                        String idCustPlan =  CommonUtils.toString(map.get("ID_CUST_PLAN"));
                        gernermsg=ERROR_EXCEPTION.replace("<BILL_ACC>", idBillingAccount).replace("<ID_CUST_PLAN>", idCustPlan);
                        t_batch_error.setMessage(gernermsg);
                        gSetP01.G_SET_P01_GetIdBatch(t_batch_error);
                    }
                }
                
                // call G-ALT-P06 to send batch execution notification
                G_ALT_P06 gAltP06 = new G_ALT_P06(this.queryDAO, this.updateDAO);

                // get notification user list
                List<AlertUserDto> alertUsers = queryDAO.executeForObjectList(
                        "SELECT.BSYS.SQL053", BATCH_TYPE);

                String subject = this.msgResource.getMessage("info.ERR2SC037", null);
                G_ALT_P06Input input_p06 = new G_ALT_P06Input();
                input_p06.setSubject(subject);
                input_p06.setMsg(msg);
                input_p06.setBatchId(BATCH_TYPE);
                input_p06.setListAlertUser(alertUsers);
                BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
                uvo.setId_user(this.loginUserId);
                gAltP06.execute(input_p06, uvo);
            } else {
                String idBillingAccount="";
                String idCustPlan="";
                String billaccmulti="";
                String genermsg="";
                String negabllamt="";
                for(Map<String, Object> mapp: listErrorInfo) {
                    if(mapp.containsKey("BILL_ACC")&&mapp.containsKey("ID_CUST_PLAN")){
                        idBillingAccount = CommonUtils.toString(mapp.get("BILL_ACC"));
                        idCustPlan =  CommonUtils.toString(mapp.get("ID_CUST_PLAN"));
                    }
                    if(mapp.containsKey("BILL_ACC_MULTI")){
                        billaccmulti= CommonUtils.toString(mapp.get("BILL_ACC_MULTI"));
                    }
                }
                if(!idBillingAccount.isEmpty()&&!idCustPlan.isEmpty()){
                    genermsg=ERROR_EXCEPTION.replace("<BILL_ACC>", idBillingAccount).replace("<ID_CUST_PLAN>", idCustPlan)+"<BR/>";
                }
                if(!billaccmulti.isEmpty()){
                    negabllamt=SCRREN_ERROR_MESSAGE_BAC;
                }
                String totalmsg=genermsg+negabllamt;
                out.setBatchStatus("3"); 
                out.setErrorMessage(totalmsg);
                out.setIdRefList(new ArrayList<String>());
                //String idBillingAccount = CommonUtils.toString(listErrorInfo.get(0).get("BILL_ACC"));
                //String idCustPlan =  CommonUtils.toString(listErrorInfo.get(0).get("ID_CUST_PLAN"));
                //out.setBatchStatus("3");
                //out.setErrorMessage(ERROR_EXCEPTION.replace("<BILL_ACC>", idBillingAccount).replace("<ID_CUST_PLAN>", idCustPlan));
                //out.setIdRefList(new ArrayList<String>());
            }
        }

        // End Audit Trail
        if (this.auditIdModule != null) {
            CommonUtils.auditTrailEnd(this.auditId);
        }

        return out;
    }

    /**
     * clear generated billing document list.
     */
    private void reset() {
        countGenDoc = 0;
        this.listBillDoc.clear();
        this.listErrorInfo.clear();
    }

    /**
     * Main process to generate billing document.
     * 
     * @param table_R
     *            T_BAC_H/T_BAC_D/T_CUST_PLAN_D level data used to generate
     *            billing document
     * @param moduleId
     * @throws Exception
     */
    private void start_process(List<Map<String, Object>> table_R, String moduleId){
        // initialize TEMP_BIL_D
        this.TEMP_BIL_D = new ArrayList<Map<String, Object>>();

        // 1.1 Generate R2 information, group by ID_BILL_ACCOUNT
        List<List<Map<String, Object>>> R2 = new ArrayList<List<Map<String, Object>>>();

        List<Map<String, Object>> ListByBill = new ArrayList<Map<String, Object>>();
        String process_bill = "";
        for (Map<String, Object> record : table_R) {
            String record_bill = record.get("ID_BILL_ACCOUNT").toString();
            if (process_bill.equals("")) {
                process_bill = record_bill;
                ListByBill.add(record);
            } else {
                if (process_bill.equals(record_bill)) {
                    // append record for R1
                    ListByBill.add(record);
                } else {
                    // change to new ID_BILL_ACCOUNT
                    R2.add(ListByBill);
                    process_bill = record_bill;
                    ListByBill = new ArrayList<Map<String, Object>>();
                    ListByBill.add(record);
                }
            }
        }

        // check for last billing
        if (0 < ListByBill.size()) {
            // add last billing
            R2.add(ListByBill);
        }

        // 1.2 Loop R2 for each ID_BILL_ACCOUNT into result set R1
        for (List<Map<String, Object>> R1 : R2) {
            if (0 < R1.size()) {
                // 2.1 Variable assignment & 2.3 Retrieve customer information
                G_BIL_P01Bean billing_header =  this.getBillingHeader(R1.get(0));
                String idBillingAccount = CommonUtils.toString(billing_header.getIdBillingAccount()).trim();
                String idCustPlan =  CommonUtils.toString(billing_header.getIdCustPlan()).trim();
                try{
                 // 2.2 get ref
                    List<String> mRef = this.getRef(R1);

                    // process for R1
                    for (Map<String, Object> record : R1) {
                        // 3.0 Get custom plan and service information
                    	// #192 Start
                    	if (!"0".equals(this.billingOption) && !this.billingOption.equals(record.get("BILL_OPTION").toString())) {
							continue;
						}
                    	// #192 End
                        G_BIL_P01Bean billing_object = billing_header;
                        billing_object.setIdAudit(this.auditId);
                        billing_object.setIdLogin(this.loginUserId);
                        billing_object.setIdCust(record.get("ID_CUST").toString());
                        billing_object.setIdCustPlan(record.get("ID_CUST_PLAN").toString());
                        billing_object.setIdCustPlanGrp(record.get("ID_CUST_PLAN_GRP").toString());
                        billing_object.setIdBillingAccount(record.get("ID_BILL_ACCOUNT").toString());
                        // add for 248
                        billing_object.setBillType(record.get("TRANSACTION_TYPE").toString());
                        billing_object.setIsRecurring(record.get("IS_RECURRING").toString());
                        if ("E".equals(moduleId)) {
                            billing_object.setIsSingpost(record.get("IS_SINGPOST").toString());
                        } else {
                            billing_object.setIsSingpost("0");
                        }
                        billing_object.setPlanStatus(CommonUtils.toString(record.get("PLAN_STATUS")));
                        billing_object.setServiceStatus(CommonUtils.toString(record.get("SERVICES_STATUS")));
                        billing_object.setBillingStatus(CommonUtils.toString(record.get("BILLING_STATUS")));
                        billing_object.setLastBillFrom(record.get("BILL_FROM"));
                        billing_object.setLastBillTo(record.get("BILL_TO"));
                        billing_object.setSvcStart(record.get("SVC_START"));
                        billing_object.setSvcEnd(record.get("SVC_END"));
                        billing_object.setBI(CommonUtils.toString(record.get("BILL_INSTRUCT")));
                        billing_object.setBillDesc(CommonUtils.toString(record.get("BILL_DESC")));
                        billing_object.setItemDesc(CommonUtils.toString(record.get("BILL_DESC")));
                        billing_object.setUpdateStatus(0);
                        billing_object.setBatchId(null);

                        // using for calculation
                        billing_object.setProRateBase(record.get("PRO_RATE_BASE").toString());
                        billing_object.setProRateBaseNo(record.get("PRO_RATE_BASE_NO"));
                        billing_object.setIsLumpSum(record.get("IS_LUMP_SUM").toString());
                        billing_object.setIsLumpSumDisc(record.get("DISC_LUMP_SUM").toString());
                        
                        billing_object.setMinSvcStart(record.get("MIN_SVC_START"));
                        billing_object.setMinSvcEnd(record.get("MIN_SVC_END"));
                        billing_object.setDelicery(record.get("DELIVERY").toString());
                        billing_object.setDeliceryEmail(record.get("DELIVERY_EMAIL").toString());
                        // #192 Start
                        billing_object.setBillOption(record.get("BILL_OPTION").toString());
                        // #192 End

                        // 4.1 Retrieve customer service against plan and batch mapping information
                        Map<String, String> noImportParams = new HashMap<String, String>();
                        noImportParams.put("ID_CUST_PLAN_GRP", billing_object.getIdCustPlanGrp());
                        Integer nImportI = this.queryDAO.executeForObject(GET_NO_IMPORT, noImportParams, Integer.class);
                        int nImport = 0;
                        if (nImportI != null) {
                            nImport = nImportI.intValue();
                        }
                        billing_object.setNImport(nImport);

                        if (nImport != 0 && billing_object.getBI().equals("5")) {
                            // 4.4 Bill After Use
                            this.bill_after_use(billing_object);
                        } else {
							// #192 Add Start
							this.resetBillingMonth(billing_object
									.getBillOption(), CommonUtils
									.toString(record.get("BILL_INSTRUCT")));
							// #192 Add End
							// 4.3 Bill In Advance
							this.bill_in_advance(billing_object);
                        }
                    }

                    // 5.0 Create billing document data, update billing account data and other related tables
                    this.insert_update_table(billing_header, mRef);
                    // reset TEMP_BIL_D table
                    this.TEMP_BIL_D = new ArrayList<Map<String, Object>>();
                    this.index = 0;
                } catch (Exception e) {
                    e.printStackTrace();
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("BILL_ACC", idBillingAccount);
                    map.put("ID_CUST_PLAN", idCustPlan);
                    listErrorInfo.add(map);
                }
            }
        }

        // reset TEMP_BIL_D table to null
        this.TEMP_BIL_D = null;
    }

    /**
     * Get BIF reference as Billing document header information.<br>
     * ID_BIF/ID_QCS/ID_QUO/CUST_PO/AC_MANAGER/TERM/TERM DAY<br>
     * information into a string list, split by ;<br>
     * 
     * @param R1 custom plan list with the same ID_BILL_ACCOUNT
     * @return list of ID_BIF/ID_QCS/ID_QUO/CUST_PO/AC_MANAGER/TERM/TERM DAY information
     */
    private List<String> getRef(List<Map<String, Object>> R1) {
        List<String> returnValue = new ArrayList<String>();
        String[] mRef = { "", "", "", "", "", "", ""};

        // process to get list of value
        for (Map<String, Object> record : R1) {
            for (int i = 0; i < this.mField.size(); i++) {
                if (record.get(this.mField.get(i)) == null) {
                    continue;
                }
                
                String value = "|" + record.get(this.mField.get(i)).toString().trim() + "|";
                // check value is exist in list
                if (mRef[i].indexOf(value) < 0) {
                    mRef[i] += value + ";";
                }
            }
        }

        // update to correct format
        for (int i = 0; i < mRef.length; i++) {
            // remove | character
            String new_value = mRef[i].replaceAll("\\|", "");
            // remove ; in the last of string
            if (0 < new_value.length()) {
                new_value = new_value.substring(0, new_value.length() - 1);
            }
            
            // Only the first 100 characters will be used of AC_MANAGER/TERM.
            // As T_BIL_H.ID_CONSULT/TERM field is varchar2(100)
            if ("AC_MANAGER".equals(this.mField.get(i)) 
                    || "TERM".equals(this.mField.get(i))) {
                if (100 < new_value.length()) {
                    new_value = new_value.substring(0, 100);
                }
            }
            
            // add to returnValue
            returnValue.add(i, new_value);
        }
        
        return returnValue;
    }

    /**
     * Billing in advance.
     * Determine billing period calculation and amount calculation method.
     * 
     * @param obj G_BIL_P01Bean
     */
    private void bill_in_advance(G_BIL_P01Bean obj) {
        // 1.0 Calculate Billing period by last billing period
        if (obj.getLastBillTo() != null) {
            if (BILLING_STATUS_COMPLETED.equals(obj.getBillingStatus())) {
                // terminate process when billing status is PS3:Completed.
                return;
            }
            if (obj.getSvcEnd() != null) {
                if (obj.getServiceStatus().equals("PS4")
                        || obj.getServiceStatus().equals("PS7")
                        || obj.getServiceStatus().equals("PS3")
                        || obj.getServiceStatus().equals("PS6")) {
                    if (obj.getServiceStatus().equals("PS3")) {
                        Date billFrom = BillingCalendar.addValue4Date(obj.getLastBillTo(), BillingCalendar.DATE, 1);
                        //SVC_END < sysdate
                        if (BillingCalendar.compare(obj.getSvcEnd(), billFrom) < 0) {
                            // CUST Plan finish
                            obj.setIsRecurring("0");
                            this.updateDAO.execute(UPDATE_EXPIRED_SERVICE, obj);

                            obj.setBillingStatus(BILLING_STATUS_COMPLETED); // Generate Billing Completed
                            this.updateDAO.execute(UPDATE_BILLING_STATUS, obj);
                            
                            return;
                        }
                    } else {
                        // CUST PLAN Inactive or Terminated
                        if (obj.getLastBillTo().compareTo(obj.getSvcEnd()) < 0) {
                            // pay from lastBillTo+1 to Service end day
                            obj.setIsRecurring("0");
                            obj.setBillFrom(BillingCalendar.addValue4Date(
                                    obj.getLastBillTo(), BillingCalendar.DATE, 1));
                            obj.setBillTo(obj.getSvcEnd());
                            obj.setCal(PRORATE_SVCEND);
                            obj.setBillingStatus(BILLING_STATUS_COMPLETED); // Generate Billing Completed
                            obj.setPostingMonth(this.billing_month);
                            this.bill_calculation(obj, true);
                            return;
                        } else {
                            // CUST Plan finish
                            obj.setIsRecurring("0");
                            this.updateDAO.execute(UPDATE_EXPIRED_SERVICE, obj);

                            obj.setBillingStatus(BILLING_STATUS_COMPLETED); // Generate Billing Completed
                            this.updateDAO.execute(UPDATE_BILLING_STATUS, obj);
                            
                            return;
                        }
                    }
                }
            }
            // case svcEnd is null or no process, need to pay from lastBillTo + 1
            obj.setBillFrom(BillingCalendar.addValue4Date(obj.getLastBillTo(),
                    BillingCalendar.DATE, 1));
        } else {
            // case last bill to is null, need to pay from service start day
            String serviceStartMonth = BillingCalendar.convertDateToString(obj.getSvcStart(), FORMAT_SORT_MONTH);
            if (serviceStartMonth.compareTo(this.billing_month) <= 0) {
                obj.setBillFrom(obj.getSvcStart());    
            } else {
                // Bug_Report_PTTest_NTTMSC_BillingSystemP1IS-58
                // #70 if Billing Type is credit note and billing instruction = One time (6), 
                // although services start date > billing month, need to generate this services for particular billing month
                if ("CN".equalsIgnoreCase(obj.getBillType()) && "6".equals(obj.getBI())){
                    obj.setBillFrom(obj.getSvcStart()); 
                //Ver3.06 #268 Generate Billing - Post paid issue fixing CT 20170707    
                } else if("2".equals(obj.getBillOption())){
                	obj.setBillFrom(obj.getSvcStart());
                //Ver3.06 #268 Generate Billing - Post paid issue fixing CT 20170707
                } else{
                    // when service start month > billing month, not need to process.
                    return;
                }
            }
        }

        // indicate whether need to process several times or not
        // because from service from to service to contains several months.
        boolean flag_1 = true;
        // process for while 1
        while (flag_1) {
            // just process only one in default
            flag_1 = false;
            // set Posting month
            obj.setPostingMonth(this.billing_month);
            // 1.2.2 Get Bill to
            Date billTo = this.get_bill_to(Integer.parseInt(obj.getBI()), obj.getBillFrom());
            obj.setBillTo(billTo);
            boolean iR = true;
            if (obj.getBI().equals("6")) {
                // case BI = 6 : One Time
                iR = false;
                if (obj.getSvcEnd() != null) {
                    obj.setBillTo(obj.getSvcEnd());
                }
                obj.setIsRecurring("0");
                obj.setBillingStatus(BILLING_STATUS_COMPLETED); // Generate Billing Completed
                obj.setCal(FULLPERIOD_FULLAMT);
                this.bill_calculation(obj, true);
            } else if (obj.getSvcEnd() != null) {
                iR = false;
                // get the current BillForm's billing month 
                String billMonth = BillingCalendar.convertDateToString(obj.getBillFrom(), FORMAT_SORT_MONTH);
                // setting posting month
                obj.setPostingMonth(billMonth);
                
                if (obj.getSvcEnd().compareTo(obj.getBillTo()) == 0) {
                    // CUST Plan finish
                    obj.setIsRecurring("0");
                    obj.setBillingStatus(BILLING_STATUS_COMPLETED); // Generate Billing Completed
                    // Bug_Report_PTTest_NTTMSC_BillingSystemP1IS-59
                    // #68 if svc_end <> null and svc_end = bill to, add a checking to determine calculation method is prorate_start
                    if (obj.getBI().equals("5")
                            && BillingCalendar.getValue4Date(obj.getBillFrom(), BillingCalendar.DATE) != 1) {
                        obj.setCal(PRORATE_SVCSTART);
                    } else {
                        obj.setCal(FULLPERIOD_MODE);
                    }
                    this.bill_calculation(obj, true);
                } else if (obj.getSvcEnd().compareTo(obj.getBillTo()) < 0) {
                    // CUST Plan finish
                    obj.setIsRecurring("0");
                    obj.setBillingStatus(BILLING_STATUS_COMPLETED); // Generate Billing Completed
                    obj.setBillTo(obj.getSvcEnd());
                    obj.setCal(PRORATE_SVCEND);
                    this.bill_calculation(obj, true);
                } else {
                    iR = true;
                }
            }
            
            if (iR) {
                // Service end day is null or larger than bill to day
                String bill_to = BillingCalendar.convertDateToString(obj.getBillTo(), FORMAT_SORT_DATE);
                String bill_from=BillingCalendar.convertDateToString(obj.getBillFrom(), FORMAT_SORT_DATE);
                String billMonth = BillingCalendar.convertDateToString(obj.getBillFrom(), FORMAT_SORT_MONTH);
                // #192 Edit Start
                int compareNum = 1;
                if (bill_to.compareTo(this.billing_date_last_day) < 0){
                	//Ver3.06 #268 Generate Billing - Post paid issue fixing CT 20170707
                	if ("2".equals(obj.getBillOption())) {
                	//Ver3.06 #268 Generate Billing - Post paid issue fixing CT 20170707	
                		compareNum = 0;
                	}
					if (billMonth.compareTo(this.billing_month) < compareNum) {
						// setting posting month
						obj.setPostingMonth(billMonth);
						// re process for while 1
						flag_1 = true;
					}
                }
                // #192 Edit End
                // Service end day is null
                if(obj.getSvcEnd() == null){
                    if ((bill_to.compareTo(this.billing_date_last_day) >= 0&&
                            bill_from.compareTo(this.billing_date_last_day)<0)
                            && billMonth.compareTo(this.billing_month) < 1) {
                        // setting posting month
                        obj.setPostingMonth(billMonth);
                    }
                }
                    
                if (obj.getBI().equals("5")
                        && obj.getLastBillTo() == null
                        && BillingCalendar.getValue4Date(obj.getSvcStart(), BillingCalendar.DATE) != 1) {
                    obj.setCal(PRORATE_SVCSTART);
                    obj.setBillingStatus(BILLING_STATUS_IN_PROCESS); // Bill Generation In Progress
                } else {
                    if (obj.getBI().equals("5")
                            && BillingCalendar.getValue4Date(obj.getBillFrom(), BillingCalendar.DATE) != 1) {
                        obj.setCal(PRORATE_SVCSTART);
                        obj.setBillingStatus(BILLING_STATUS_IN_PROCESS); // Bill Generation In Progress
                    } else {
                        obj.setCal(FULLPERIOD_MODE);
                        obj.setBillingStatus(BILLING_STATUS_IN_PROCESS); // Bill Generation In Progress
                    }
                }
                this.bill_calculation(obj, true);

                if (flag_1) {
                    // set last bill form and last bill to again
                    obj.setLastBillFrom(obj.getBillFrom());
                    obj.setLastBillTo(obj.getBillTo());
                    obj.setBillFrom(BillingCalendar.addValue4Date(obj.getLastBillTo(), BillingCalendar.DATE, 1));
                }
            }
        }
    }

    /**
     * Billing after use.
     * Determine billing period calculation and amount calculation method.
     * 
     * @param obj G_BIL_P01Bean
     */
    private void bill_after_use(G_BIL_P01Bean obj) {
        // 1.0 Calculate Billing period by last billing period
        if (obj.getLastBillTo() != null) {
            // case svcEnd is null or not process, bill from last bill to + 1
            obj.setBillFrom(BillingCalendar.addValue4Date(obj.getLastBillTo(), BillingCalendar.DATE, 1));
        } else {
            // case last bill to is null, bill from the service start day
            obj.setBillFrom(obj.getSvcStart());
        }

        boolean flag_1 = true;
        while (flag_1) {
            // set to run one time
            flag_1 = false;
            // call get bill to
            Date billto = this.get_bill_to(Integer.parseInt(obj.getBI()), obj.getBillFrom());
            if (obj.getSvcEnd() != null) {
                //obj.setBillFrom(BillingCalendar.addValue4Date(obj.getLastBillTo(), BillingCalendar.DATE, 1));
            	if (billto.compareTo(obj.getSvcEnd()) >= 0
	                        && (obj.getServiceStatus().equals("PS7") 
	                                || obj.getServiceStatus().equals("PS4"))) {
	                    // set PostMonth = Billing_Month - 1 Months
	                    //Date billDate = BillingCalendar.addValue4Date(this.running_date, BillingCalendar.MONTH, -1);
	                    String xBillingMonth = BillingCalendar.convertDateToString(obj.getBillFrom(), FORMAT_SORT_MONTH);
	                    
	                    obj.setPostingMonth(xBillingMonth);
	                    
	                    // the plan is in service time, but inactive or terminated
	                    obj.setIsRecurring("0");
	                    obj.setBillingStatus(BILLING_STATUS_COMPLETED); // Generate Billing Completed
	                    obj.setBillTo(obj.getSvcEnd());
	                    obj.setCal(PRORATE_SVCEND);
	                    // call calculate for service end
	                    this.bill_calculation(obj, false);
	                    return;
	            }
            	
            }
            Date billDate = BillingCalendar.addValue4Date(this.running_date, BillingCalendar.MONTH, -1);
            String xBillingDate = BillingCalendar.convertDateToString(billDate, FORMAT_SORT_DATE);
            String xBillingMonth = BillingCalendar.convertDateToString(billDate, FORMAT_SORT_MONTH);
            obj.setPostingMonth(xBillingMonth);

            String billMonth = BillingCalendar.convertDateToString(obj.getBillFrom(), FORMAT_SORT_MONTH);
            
            if (0 < billMonth.compareTo(obj.getPostingMonth())) {
                // when bill from month > post month, not need to process 
                return;
            }

            obj.setBillTo(billto);
            String bill_to = BillingCalendar.convertDateToString(obj.getBillTo(), FORMAT_SORT_DATE);
            if (bill_to.compareTo(xBillingDate) < 0
                    && billMonth.compareTo(xBillingMonth) < 0) {
                // set posting month again
                obj.setPostingMonth(billMonth);
                // process while 1 again
                flag_1 = true;
            }

            if (obj.getBI().equals("5")
                    && obj.getLastBillTo() == null
                    && BillingCalendar.getValue4Date(obj.getSvcStart(), BillingCalendar.DATE) != 1) {
                obj.setCal(PRORATE_SVCSTART);
                obj.setBillingStatus(BILLING_STATUS_IN_PROCESS); // Bill Generation In Progress
            } else {
                obj.setCal(FULLPERIOD_MODE);
                obj.setBillingStatus(BILLING_STATUS_IN_PROCESS); // Bill Generation In Progress
            }
            this.bill_calculation(obj, false);

            if (flag_1) {
                // set last bill form and last bill to again
                obj.setLastBillFrom(obj.getBillFrom());
                obj.setLastBillTo(obj.getBillTo());
                obj.setBillFrom(BillingCalendar.addValue4Date(obj.getLastBillTo(), BillingCalendar.DATE, 1));
            }
        }
    }

    /**
     * Create Billing document detail information.
     * Calculate bill days, and unit price and amount.
     * 
     * @param obj G_BIL_P01Bean
     */
    private void bill_calculation(G_BIL_P01Bean obj, boolean isBillInAdvance) {
		if (isBillInAdvance) {
			if ("2".equals(obj.getBillOption())) {
				try {
					SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
					Date bilDate = sf.parse(this.billing_date);
					if (Integer.parseInt(obj.getBI()) <= 5
							&& obj.getBillTo().compareTo(bilDate) >= 0) {
						return;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
        long p = 1;      // billing pro rate
        long nDays = 0;  // billing calculation days
        int nMonths = 0; // billing calculation months
        
        // 1.0 Get Item Header
        // 1.1 Variable assignment
        obj.setItemLevel(0);
        obj.setIdCustPlanLink(null);
        obj.setSvcLevel1(null);
        obj.setSvcLevel2(null);
        obj.setJobNo(null);
        obj.setIsDisplayJobNo("0");
        // #203 Start
        obj.setLocation(null);
        // #203 End
        
        // Add 148 Cust_Po Start
        //obj.setCustPo2(null);
        String custPoNo = queryDAO.executeForObject("GET_CUST_PO", obj.getIdCustPlanGrp(), String.class);
        obj.setCustPo2(custPoNo);
        // Add 148 Cust_Po End
        obj.setApplyGST(0);
        obj.setUpPrice(0);
        obj.setAmount(0);
        obj.setExpAmount(0);
        obj.setQuantity(0);
        obj.setDiscAmt(0);
        obj.setSubtotal(0);
        obj.setTaxRate(0);
        obj.setTaxCode("");
        obj.setExpGst(0);
        obj.setIdPlanGrp(null);
        obj.setItemType("N");
        obj.setItemCat("1");
        obj.setIsDisplayMinSvc("0");
        obj.setItemDesc(obj.getBillDesc());

        // 1.2 Get isDisplay to determine billing item apply lump sum amount
        // or itemised amount
        if ("1".equals(obj.getIsLumpSum())) {
            // lump sum amount
            // item header display quantity, unit price and amount
            obj.setIsDisplay("1");
        } else {
            obj.setIsDisplay("0");
        }
        // DisplayDisc to determine billing item apply lump sum discount
        // or itemised discount
        if("1".equals(obj.getIsLumpSumDisc())){
            // lump sum discount
            // item header display quantity, unit price and discount
            obj.setDisplayDisc("1");
        }else{
            obj.setDisplayDisc("0");
        }
        
        // 1.3 Insert item header record
        this.insert_temp(obj);
        
        // determine item header id that need to update the quantity, 
        // unit price and total amount after get all item details
        int itemHeaderId = this.index;
        
        // 2.0 Get Item detail
        // 2.1 Variable assignment
        obj.setItemLevel(1);
        obj.setItemHeaderAmt(0);
        obj.setItemHeaderDisc(0);
        obj.setItemTaxAmt(0);
        obj.setItemTaxCode("");
        obj.setItemHeaderSubtotal(0);
        //#594: [B1] Customer Maintenance CT 20190604
        obj.setTaxExemptedAmout(0);
        obj.setTotalTaxExemptedAmout(0);
        //#594: [B1] Customer Maintenance CT 20190604
        // 2.2 Get isDisplay
        if ("1".equals(obj.getIsLumpSum())) {
            obj.setIsDisplay("0");
        } else {
            // itemised amount
            // Item details display quantity, unit price and amount
            obj.setIsDisplay("1");
        }
        //DisplayDisc
        //wcbeh@20161209 #217 - G_BIL Item Discount Display Flag
        if ("1".equals(obj.getIsLumpSumDisc())) {
            //obj.setDisplayDisc2("0");
        	displayDiscFlag = "0";
        } else {
            // itemised discount
            // Item details display discount amount
            //obj.setDisplayDisc2("1");
        	displayDiscFlag = "1";
        }
        
        // 2.3 Get p
        if ("S".equals(obj.getProRateBase())) {
            // number of days based on calendar month(PostingMonth)
            try {
                String postMonthDay = obj.getPostingMonth() + "01";
                SimpleDateFormat dateFormat = new SimpleDateFormat();
                dateFormat.applyPattern(FORMAT_SORT_DATE);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(dateFormat.parse(postMonthDay));
                p = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            }catch(Exception e){
                e.printStackTrace();
            }
        } else {
            p = obj.getProRateBaseNo();
        }
        obj.setP(p);
        
        // 2.4 Get nDays and nMonths
        if (obj.getCal().equals(PRORATE_SVCSTART)) {
            nDays = BillingCalendar.getDistance(obj.getBillTo(),
                    BillingCalendar.addValue4Date(obj.getBillFrom(), BillingCalendar.DATE, -1), BillingCalendar.DATE);
        } else if (obj.getCal().equals(PRORATE_SVCEND)) {
            // calculate how many months between bill from and bill to
            Date nFrom = obj.getBillFrom();
            Date nTo = BillingCalendar.addValue4Date(nFrom, BillingCalendar.MONTH, 1);
            nTo = BillingCalendar.addValue4Date(nTo, BillingCalendar.DATE, -1);
            while (nTo.compareTo(obj.getBillTo()) < 0) {
                nMonths += 1;
                nFrom = BillingCalendar.addValue4Date(nTo, BillingCalendar.DATE, 1);
                nTo = BillingCalendar.addValue4Date(nFrom, BillingCalendar.MONTH, 1);
                nTo = BillingCalendar.addValue4Date(nTo, BillingCalendar.DATE, -1);
            }
            
            SimpleDateFormat dateFormat = new SimpleDateFormat();
            dateFormat.applyPattern(FORMAT_SORT_MONTH);
            String billToMonth = dateFormat.format(obj.getBillTo());
            String nFromMonth = dateFormat.format(nFrom);
            if (billToMonth.equals(nFromMonth)) {
                nDays = BillingCalendar.getDistance(obj.getBillTo(),
                        BillingCalendar.addValue4Date(nFrom, BillingCalendar.DATE, -1), BillingCalendar.DATE);
            } else {
                long x1 = BillingCalendar.getValue4Date(nFrom, Calendar.DATE);
                long x2 = BillingCalendar.getValue4Date(obj.getBillTo(), Calendar.DATE);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(nFrom);
                int m = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
                nDays = m - x1 + 1 + x2;
            }
        }
        obj.setNMonths(nMonths);
        obj.setNDays(nDays);
        
        // 2.5 Get mBI
        int xMonth = this.get_no_of_month(Integer.parseInt(obj.getBI()));
        
        // 2.6 Get Item details
        this.get_item(obj, xMonth);
        
        // 2.7 Update item header
        Map<String, Object> headerInfo = this.TEMP_BIL_D.get(itemHeaderId - 1);
        headerInfo.put("ITEM_QTY", "1");
        headerInfo.put("ITEM_UPRICE", obj.getItemHeaderAmt());
        headerInfo.put("ITEM_AMOUNT", obj.getItemHeaderAmt());
        headerInfo.put("ITEM_DISC_AMT", obj.getItemHeaderDisc());
        headerInfo.put("ITEM_SUBTOTAL",obj.getItemHeaderSubtotal());
        headerInfo.put("TAX_CODE", obj.getItemTaxCode());
        headerInfo.put("ITEM_GST", obj.getItemTaxAmt());
        //#163 start
        headerInfo.put("TAX_RATE", obj.getTaxRate());
        headerInfo.put("APPLY_GST", obj.getApplyGST());
        //#163 end
        
        //#594: [B1] Customer Maintenance CT 20190604
        headerInfo.put("TAX_EXEMPTED_AMT",obj.getTotalTaxExemptedAmout());
        //#594: [B1] Customer Maintenance CT 20190604
        
        //if the  ITEM_DISC_AMT's value ==0; then Discount does not display
        if(obj.getItemHeaderDisc()==0){
            headerInfo.put("DISPLAY_DISC", "0");
        }
    }

    /**
     * Get Bill To date based on Bill From and BILL_INSTRUCT.
     * 
     * @param type BILL_INSTRUCT or RATE_MODE
     * @param from_date Bill From date
     * @return Bill To date
     */
    private Date get_bill_to(int type, Date from_date) {
        Date to_date = null;

        switch (type) {
        case 1:
            // 1: 12 (Yearly). Bill To = Bill From + 12 months - 1 day
            to_date = BillingCalendar.addValue4Date(from_date,
                    BillingCalendar.MONTH, 12);
            to_date = BillingCalendar.addValue4Date(to_date,
                    BillingCalendar.DATE, -1);
            break;
        case 2:
            // 2: 6 (Bi-Annually). Bill To = Bill From + 6 months - 1 day
            to_date = BillingCalendar.addValue4Date(from_date,
                    BillingCalendar.MONTH, 6);
            to_date = BillingCalendar.addValue4Date(to_date,
                    BillingCalendar.DATE, -1);
            break;
        case 3:
            // 3: 3 (Quarterly). Bill To = Bill From + 3 months - 1 day
            to_date = BillingCalendar.addValue4Date(from_date,
                    BillingCalendar.MONTH, 3);
            to_date = BillingCalendar.addValue4Date(to_date,
                    BillingCalendar.DATE, -1);
            break;
        case 4:
            // 4: 2 (Bi-Monthly). Bill To = Bill From + 2 months - 1 day
            to_date = BillingCalendar.addValue4Date(from_date,
                    BillingCalendar.MONTH, 2);
            to_date = BillingCalendar.addValue4Date(to_date,
                    BillingCalendar.DATE, -1);
            break;
        case 5:
            // 5: 1 (Monthly). Bill To = Last day of the month(Bill From)
            to_date = BillingCalendar.getMaximunDateByField(from_date,
                    BillingCalendar.DATE);
            break;
        default:
            // 6: 0 (One Time) or others. Bill From
            to_date = from_date;
            break;
        }
        
        return to_date;
    }

    /**
     * Get Rate Mode or BILL_INSTRUCT's months.
     * 
     * @param xMode Rate Mode or BILL_INSTRUCT
     * @return month number
     */
    private int get_no_of_month(int xMode) {
        int xMonth = -1;

        switch (xMode) {
        case 1:
            // Yearly
            xMonth = 12;
            break;
        case 2:
            // Bi Annually
            xMonth = 6;
            break;
        case 3:
            // Quarterly
            xMonth = 3;
            break;
        case 4:
            // Bi-Monthly
            xMonth = 2;
            break;
        default:
            xMonth = 1;
            break;
        }

        return xMonth;
    }
    

    /**
     * Get all custom plan's service item
     * 
     * @param obj G_BIL_P01Bean
     * @param mBI BI months
     */
    private void get_item(G_BIL_P01Bean obj, int mBI) {
        // 1.0 Get Service details from customer services
        // 1.1 Retrieve customer services details into result set S1
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("ID_CUST_PLAN_GRP", obj.getIdCustPlanGrp());
        List<Map<String, Object>> result_S1 = this.queryDAO.executeForMapList(GET_RESULT_S1, params);
        //Fix Item_desc length over 4000 character 20180404 CT
        for(Map<String, Object> temp : result_S1) {
        	String itemDesc = clobToString((Clob)temp.get("ITEM_DESC"));
        	temp.put("ITEM_DESC", itemDesc);
        }
      //Fix Item_desc length over 4000 character 20180404 CT
        List<String> taxcodeList=new ArrayList<String>();
        for (Map<String, Object> record_S1 : result_S1) {
        	//wcbeh@20161209 #217 - G_BIL Item Discount Display Flag
        	obj.setDisplayDisc(displayDiscFlag); //reset to original flag value
        	
            String rate_mode = record_S1.get("RATE_MODE").toString();
            if (obj.getLastBillTo() != null && rate_mode.equals("6")) {
                // do nothing
                continue;
            } else {
                //1.2 Get DiscCal
                String discamoutstr=CommonUtils.toString(record_S1.get("DISC_AMOUNT").toString().trim());
                double discamout=0;
                //if "DISC_AMOUNT" is null or "",then set value '0'
                if (!"".equals(discamoutstr)){
                    discamout=Double.parseDouble(discamoutstr);
                }
                if(discamout==0){
                //No need calculate discount amount(DiscAmt=0)
                    obj.setDiscCal(BILLING_DISC_CAL_NO);
                    obj.setDiscAmt(0);
                    //if the  ITEM_DISC_AMT's value ==0; then Discount does not display
                    obj.setDisplayDisc("0");
                    
                }else{
                    String discType=record_S1.get("DISC_TYPE").toString();
                   //Calculate discount amout 
                    if(BILLING_DISC_TYPE.equals(discType)){
                        // One Time Discount
                        if(obj.getLastBillTo()== null){
                            // One time discount to be given at 1st billing
                            obj.setDiscCal(BILLING_DISC_CAL_NO);
                            obj.setDiscAmt(discamout);
                        }else{
                           
                            // No discount because discount already given at 1st bill
                            obj.setDiscCal(BILLING_DISC_CAL_NO);
                            obj.setDiscAmt(0);
                        }
                    }else{
                        // Recurring Discount Calculated at GetAmount Process
                        obj.setDiscCal(BILLING_DISC_CAL_YES);
                        obj.setDiscAmt(discamout);
                    }
                }
                
                // 1.3 Variables assignment
                obj.setItemLevel(1);
                obj.setIdCustPlanLink(CommonUtils.toString(record_S1.get("ID_CUST_PLAN_LINK")));
                obj.setSvcLevel1(CommonUtils.toString(record_S1.get("SVC_LEVEL1")));
                obj.setSvcLevel2(CommonUtils.toString(record_S1.get("SVC_LEVEL2")));
                obj.setJobNo(CommonUtils.toString(record_S1.get("JOB_NO")));
                obj.setIsDisplayJobNo(CommonUtils.toString(record_S1.get("IS_DISPLAY_JOBNO")));
                obj.setCustPo2(CommonUtils.toString(record_S1.get("CUST_PO")));
                
                //set value for billing object
                obj.setApplyGST(Integer.parseInt(record_S1.get("APPLY_GST").toString()));
                obj.setUpPrice(Double.parseDouble(record_S1.get("UNIT_PRICE").toString()));
                obj.setAmount(Double.parseDouble(record_S1.get("TOTAL_AMOUNT").toString()));
                obj.setExpAmount(0);
                obj.setQuantity(Long.parseLong(record_S1.get("QUANTITY").toString()));
                double calSubtotal=Double.parseDouble(CommonUtils.toString(record_S1.get("TOTAL_AMOUNT")))-Math.abs(Double.parseDouble(CommonUtils.toString(record_S1.get("DISC_AMOUNT"))));
                obj.setSubtotal(calSubtotal);
                obj.setItemDesc(CommonUtils.toString(record_S1.get("ITEM_DESC")));
                obj.setItemType(CommonUtils.toString(record_S1.get("ITEM_TYPE")));
                obj.setBatchId(null);
                
                obj.setIdPlanGrp(CommonUtils.toString(record_S1.get("ID_PLAN_GRP")));
                obj.setRateMode(record_S1.get("RATE_MODE").toString());
                obj.setTaxCode(CommonUtils.toString(record_S1.get("TAX_CODE")));
                obj.setTaxRate(0);
                // #203 Start
                obj.setLocation(CommonUtils.toString(record_S1.get("LOCATION")));
                // #203 End
                
                // 1.4 Calculate amount by calculation method or batch import data
                int nImport = obj.getNImport();
                if (nImport != 0 && obj.getBI().equals("5")) {
                    // get batch mapping info of custom plan by ID_CUST_PLAN_GRP/ID_PLAN_GRP
                    params.put("ID_PLAN_GRP", record_S1.get("ID_PLAN_GRP"));
                    List<Map<String, Object>> result_U1 = this.queryDAO.executeForMapList(GET_RESULT_U1, params);

                    if (0 < result_U1.size()) {
                        String batchId = CommonUtils.toString(result_U1.get(0).get("BATCH_ID"));
                        obj.setBatchId(batchId);
                        this.get_usage(obj, result_S1);
//                        boolean insertItem = this.get_usage(obj, result_S1);
//                        if (!insertItem) {
//                            // Skip to next sub service if no batch record
//                            continue;
//                        }
                    } else {
                        // If sub service rate mode is one time charge, no calculation required.
                        if (!rate_mode.equals("6")) {
                            this.get_amount(obj, mBI);
                        }
                    }
                } else {
                    // If sub service rate mode is one time charge, no calculation required.
                    if (!rate_mode.equals("6")) {
                        this.get_amount(obj, mBI);
                    }
                }
                
                // 1.6 Sum into item header amount
                DecimalFormat formatter = new DecimalFormat("0.00");
                formatter.setRoundingMode(RoundingMode.HALF_UP);
                BigDecimal itemHeaderAmt = new BigDecimal(formatter.format(obj.getItemHeaderAmt()));
                BigDecimal amount = new BigDecimal(formatter.format(obj.getAmount()));
                itemHeaderAmt = itemHeaderAmt.add(amount);
                obj.setItemHeaderAmt(itemHeaderAmt.doubleValue());
                //Sum into item header Disc
                BigDecimal itemHeaderDisc= new BigDecimal(formatter.format(obj.getItemHeaderDisc()));
                BigDecimal discamount = new BigDecimal(formatter.format(obj.getDiscAmt()));
                itemHeaderDisc=itemHeaderDisc.add(discamount);
                obj.setItemHeaderDisc(itemHeaderDisc.doubleValue());
                //Sum into item header aGst
                DecimalFormat formatteragst = new DecimalFormat("0.0000");
                formatteragst.setRoundingMode(RoundingMode.HALF_UP);
                double aGST = 0;
                double subtotalGst=Double.parseDouble(formatter.format(obj.getSubtotal()));
                int applyGSTInt=obj.getApplyGST();
                if(applyGSTInt!=0){
                    if(!"".equals(CommonUtils.toString(record_S1.get("TAX_CODE")))){
                       if(!taxcodeList.contains(CommonUtils.toString(record_S1.get("TAX_CODE")))){
                        taxcodeList.add(CommonUtils.toString(record_S1.get("TAX_CODE")));
                       }
                    }
                    String taxratestr=CommonUtils.toString(record_S1.get("TAX_RATE"));
                    if (!"".equals(taxratestr)){
                        int taxRate=Integer.parseInt(taxratestr);
                        obj.setTaxRate(taxRate);
                        String billCurrency = CommonUtils.toString(obj.getBillCurrency());
                        if(BILLCURRENCY_JAPAN.equals(billCurrency)){
                            aGST=Math.round((subtotalGst*taxRate)/100);
                        }else{
                            aGST=(subtotalGst*taxRate)/100;
                        }
                    }
                }
                BigDecimal itemTaxAmt=new BigDecimal(formatteragst.format(obj.getItemTaxAmt()));
                BigDecimal aaGst=new  BigDecimal(formatteragst.format(aGST));
                itemTaxAmt=itemTaxAmt.add(aaGst);
                obj.setItemTaxAmt(itemTaxAmt.doubleValue());
                //Sum into item header subtotal
                BigDecimal itemHeadersubtotal=new BigDecimal(formatter.format(obj.getItemHeaderSubtotal()));
                BigDecimal subtotal=new  BigDecimal(formatter.format(obj.getSubtotal()));
                itemHeadersubtotal=itemHeadersubtotal.add(subtotal);
                obj.setItemHeaderSubtotal(itemHeadersubtotal.doubleValue());
                
                //#594: [B1] Customer Maintenance CT 20190604
                BigDecimal TotalTaxExemptedAmout =new BigDecimal(formatter.format(obj.getTotalTaxExemptedAmout()));
                if("1".equals(obj.getTaxType())) {
                	BigDecimal taxExemptedAmout = (subtotal.multiply(new BigDecimal(this.pGST))).divide(new BigDecimal(100));
                	obj.setTaxExemptedAmout(taxExemptedAmout.doubleValue());
                	if(!"".equals(CommonUtils.toString(record_S1.get("TAX_CODE")))){
                		if(taxExemptedList.contains(CommonUtils.toString(record_S1.get("TAX_CODE")))) {
	                    	TotalTaxExemptedAmout=TotalTaxExemptedAmout.add(taxExemptedAmout);
	                    	obj.setTotalTaxExemptedAmout(TotalTaxExemptedAmout.doubleValue());
	                    }
                	}
                }
                //#594: [B1] Customer Maintenance CT 20190604
                
                // 1.7 Insert item detail record
                this.insert_temp(obj);
            }
        }
        //Sum into item header TaxCode
        String temptaxcode="";
        if(taxcodeList.size()>0){
            for(int m=0;m<taxcodeList.size();m++){
                temptaxcode+=taxcodeList.get(m)+",";
            }
            temptaxcode=temptaxcode.substring(0,temptaxcode.length()-1);
        }
        obj.setItemTaxCode(temptaxcode);
    }

    /**
     * Insert calculated record to TEMP_BIL_D for generating document.
     * 
     * @param obj G_BIL_P01Bean
     */
    private void insert_temp(G_BIL_P01Bean obj) {
        // 1.0 Get item GST amount
        DecimalFormat formatter = new DecimalFormat("0.00");
        DecimalFormat gstformatter=new DecimalFormat("0.0000");
        formatter.setRoundingMode(RoundingMode.HALF_UP);
        gstformatter.setRoundingMode(RoundingMode.HALF_UP);
        double aGST = 0;
        String taxCode="";
        int taxRate=0;
        double aTaxExemptedAmout = 0;
        //double amount = Double.parseDouble(formatter.format(obj.getAmount()));
        double subtotal=Double.parseDouble(formatter.format(obj.getSubtotal()));
        /*if (obj.getApplyGST() == 1) {
            aGST = (amount * Integer.parseInt(this.pGST)) / 100;
        }*/
        //1.1 Get Tax Code and Tax Rate
        int applyGSTInt=obj.getApplyGST();
        if(applyGSTInt!=0){
            taxCode=CommonUtils.toString(obj.getTaxCode());
            if (obj.getTaxRate()!=0){
                taxRate=obj.getTaxRate();
                //1.3 if Billcurrency='JPY' then aGST=round(Subtotal*(TaxRate/100),0) 
                // else aGST=Subtotal*(TaxRate/100)
                String billCurrency = CommonUtils.toString(obj.getBillCurrency());
                if(BILLCURRENCY_JAPAN.equals(billCurrency)){
                    aGST=Double.parseDouble(gstformatter.format(Math.round((subtotal*taxRate)/100)));
                }else{
                    aGST=Double.parseDouble(gstformatter.format((subtotal*taxRate)/100));
                }
            }
        }
        
        // 1.5 Calculate item export amount and export GST
        if (obj.getExpCur() == null) {
            obj.setExpAmount(Double.parseDouble(gstformatter.format(subtotal)));
            obj.setExpGst(Double.parseDouble(gstformatter.format(aGST)));
            
        } else {
            String billCurrency = obj.getBillCurrency();
            double billAmt = subtotal;
            String exportCur = obj.getExpCur().toString();
            Object fixedForex = obj.getFixedForex();
            G_CUR_P01 gCurP01 = new G_CUR_P01(this.queryDAO);
            Map<String, Object> result;
            BigDecimal exportAmount1 = new BigDecimal(0);
            //set ExportAmt
            result = gCurP01.convertCurrency(billCurrency, billAmt, exportCur, fixedForex);
            exportAmount1 = exportAmount1.add(new BigDecimal(CommonUtils.toString(result.get(G_CUR_P01.EXPORT_AMOUNT))));
            obj.setExpAmount(CommonUtils.toDouble(gstformatter.format(exportAmount1)));
            //set ExportGST
            BigDecimal exportAmount2 = new BigDecimal(0);
            result = gCurP01.convertCurrency(billCurrency, aGST, exportCur, fixedForex);
            exportAmount2 = exportAmount2.add(new BigDecimal(CommonUtils.toString(result.get(G_CUR_P01.EXPORT_AMOUNT))));
            obj.setExpGst(CommonUtils.toDouble(gstformatter.format(exportAmount2)));
            /*if(obj.getApplyGST()==1){
                result = gCurP01.convertCurrency(billCurrency, aGST, exportCur, fixedForex);
                exportAmount = exportAmount.add(new BigDecimal(CommonUtils.toString(result.get(G_CUR_P01.EXPORT_AMOUNT))));
            }
            result = gCurP01.convertCurrency(billCurrency, billAmt, exportCur, fixedForex);
            exportAmount = exportAmount.add(new BigDecimal(CommonUtils.toString(result.get(G_CUR_P01.EXPORT_AMOUNT))));
            obj.setExpAmount(CommonUtils.toDouble(formatter.format(exportAmount)));*/
        }
        
        // increase item ID 
        this.index += 1;
        
        Map<String, Object> record = new HashMap<String, Object>();
        record.put("ID_BILL_ACCOUNT", obj.getIdBillingAccount());
        record.put("BILL_TYPE", obj.getBillType());
        record.put("BILLING_MONTH", obj.getPostingMonth());

        record.put("ITEM_ID", this.index);
        record.put("ITEM_LEVEL", obj.getItemLevel());
        record.put("ITEM_DESC", obj.getItemDesc());
        record.put("ITEM_QTY", obj.getQuantity());
        record.put("ITEM_UPRICE", obj.getUpPrice());
        record.put("ITEM_AMOUNT", obj.getAmount());
        record.put("ITEM_EXPORT_AMT", obj.getExpAmount());
        record.put("ITEM_GST", aGST);
        record.put("IS_DISPLAY", obj.getIsDisplay());
        record.put("APPLY_GST", obj.getApplyGST());
        record.put("ITEM_TYPE", obj.getItemType());
        
        record.put("ID_CUST_PLAN", obj.getIdCustPlan());
        record.put("ID_CUST_PLAN_GRP", obj.getIdCustPlanGrp());
        record.put("ID_CUST_PLAN_LINK", obj.getIdCustPlanLink());
        
        record.put("ID_PLAN_GRP", obj.getIdPlanGrp());
        record.put("ID_CUST", obj.getIdCust());
        
        record.put("SVC_LEVEL1", obj.getSvcLevel1());
        record.put("SVC_LEVEL2", obj.getSvcLevel2());
        record.put("BILL_FROM", obj.getBillFrom());
        record.put("BILL_TO", obj.getBillTo());
        record.put("JOB_NO", obj.getJobNo());
        record.put("IS_DISPLAY_JOBNO", obj.getIsDisplayJobNo());
        record.put("CUST_PO", obj.getCustPo2());

        record.put("IS_RECURRING", obj.getIsRecurring());
        record.put("UPDATE_STATUS", obj.getUpdateStatus());
        record.put("PLAN_STATUS", obj.getPlanStatus());
        record.put("SERVICES_STATUS", obj.getServiceStatus());
        record.put("BILLING_STATUS", obj.getBillingStatus());

        record.put("ITEM_CAT", obj.getItemCat());
        record.put("IS_DISPLAY_MIN_SVC", obj.getIsDisplayMinSvc());
        record.put("MIN_SVC_FROM", obj.getMinSvcStart());
        record.put("MIN_SVC_TO", obj.getMinSvcEnd());
        record.put("DISPLAY_DISC", obj.getDisplayDisc());
        record.put("ITEM_DISC_AMT", obj.getDiscAmt());
        record.put("ITEM_SUBTOTAL", obj.getSubtotal());
        record.put("TAX_CODE", taxCode);
        record.put("TAX_RATE", taxRate);
        record.put("ITEM_EXPORT_GST", obj.getExpGst());
        
        record.put("BATCH_ID", obj.getBatchId());
        
        // #203 Start
        record.put("LOCATION", obj.getLocation());
        // #203 End
        
      //#594: [B1] Customer Maintenance CT 20190604
        record.put("TAX_EXEMPTED_AMT",obj.getTaxExemptedAmout());
      //#594: [B1] Customer Maintenance CT 20190604
        
        this.TEMP_BIL_D.add(record);
    }

    /**
     * Calculate service item's unit price and amount.
     * 
     * @param obj G_BIL_P01Bean
     * @param mBI Custom Plan BILL_INSTRUCT Months
     */
    private void get_amount(G_BIL_P01Bean obj, int mBI) {
        obj.setItemLevel(1);
        obj.setBatchId(null);

        // rate mode (months) of service item.
        //int xMonth = this.get_no_of_month(Integer.parseInt(obj.getRateMode()));
        if (obj.getCal().equals(FULLPERIOD_MODE)) {
            // calculate for unit price
            double unit_price = obj.getUpPrice();
            //unit_price = unit_price * mBI / xMonth;
            obj.setUpPrice(unit_price);
            // calculate for amount
            double amount = obj.getAmount();
            //amount = amount * mBI / xMonth;
            obj.setAmount(amount);
            // calculate for discAmt
            if(obj.getDiscCal().equals(BILLING_DISC_CAL_YES)){
                obj.setDiscAmt(obj.getDiscAmt());
            }
            // calculate for subtotal
            obj.setSubtotal(obj.getAmount()-Math.abs(obj.getDiscAmt()));
        } else if (obj.getCal().equals(PRORATE_SVCSTART)
                || (obj.getCal().equals(PRORATE_SVCEND) && obj.getNMonths() == 0)) {
            // calculate for unit price
            double unit_price = obj.getUpPrice();
            double unit_price_tmp = unit_price / mBI;
            obj.setUpPrice(unit_price_tmp * obj.getNDays() / obj.getP());
            // calculate for amount
            double amount = obj.getAmount();
            double amount_tmp = amount / mBI;
            obj.setAmount(amount_tmp * obj.getNDays() / obj.getP());
            // calculate for discAmt
            if(obj.getDiscCal().equals(BILLING_DISC_CAL_YES)){
                double discamt=Math.abs(obj.getDiscAmt());
                double DAmt=discamt/ mBI;
                obj.setDiscAmt(Double.parseDouble("-"+(DAmt * obj.getNDays() / obj.getP())));
            }
            // calculate for subtotal
            obj.setSubtotal(obj.getAmount()-Math.abs(obj.getDiscAmt()));
        } else if (obj.getCal().equals(PRORATE_SVCEND)
                && obj.getNMonths() != 0) {
            // calculate for unit price
            double amt1 = obj.getUpPrice() / mBI;
            double amt2 = amt1 * obj.getNMonths();
            double amt3 = amt1 * obj.getNDays() / obj.getP();
            obj.setUpPrice(amt2 + amt3);
            // calculate for amount
            amt1 = obj.getAmount() / mBI;
            amt2 = amt1 * obj.getNMonths();
            amt3 = amt1 * obj.getNDays() / obj.getP();
            obj.setAmount(amt2 + amt3);
            // calculate for discAmt
            if(obj.getDiscCal().equals(BILLING_DISC_CAL_YES)){
                double discamt=Math.abs(obj.getDiscAmt());
                double damt1=discamt / mBI;
                double damt2=damt1*obj.getNMonths();
                double Damt3=damt1 * obj.getNDays() / obj.getP();
                obj.setDiscAmt(Double.parseDouble("-"+(damt2+Damt3)));
            }
            // calculate for subtotal
            obj.setSubtotal(obj.getAmount()-Math.abs(obj.getDiscAmt()));
        }
    }

    /**
     * Calculate unit price and amount from batch import data.
     * 
     * @param obj G_BIL_P01Bean
     * @param S1 custom plan's service item info
     * 
     * @return boolean true if contains Clear Call batch record
     */
    private boolean get_usage(G_BIL_P01Bean obj, List<Map<String, Object>> S1) {
        boolean hasCCRecord = false;
         
        String batchId = obj.getBatchId();
        if ("CC".equals(batchId)) {
            String monthYear = obj.getPostingMonth();
            try {
                monthYear = CommonUtils.formatDate(obj.getPostingMonth(), "yyyyMM", "MM/yyyy");
            } catch (Exception e) {
                e.printStackTrace();
            }
            // get clear call info
            Map<String, Object> params = new HashMap<String, Object>();
            //params.put("ID_CUST", obj.getIdCust());
            params.put("MONTH_YEAR", monthYear);
            params.put("ID_CUST_PLAN", obj.getIdCustPlan());
            Map<String, Object> result_M1 = this.queryDAO.executeForMap(GET_CLEAR_CALL, params);
            
            if (result_M1 != null) {
                obj.setItemLevel(1);
                // set unit price and amount
                obj.setUpPrice(result_M1.get("INVOICE_TOTAL"));
                obj.setAmount(obj.getUpPrice());
                obj.setQuantity(1);
                obj.setDiscAmt(0);
                obj.setSubtotal(obj.getUpPrice());
                
                hasCCRecord = true;
            } else {
                obj.setItemLevel(1);
                // set unit price and amount
                obj.setUpPrice(0);
                obj.setAmount(0);
                obj.setQuantity(1);
                obj.setDiscAmt(0);
                obj.setSubtotal(0);
                
                hasCCRecord = false;
                
            }
        }
        
        return hasCCRecord;
    }

    /**
     * Insert billing calculated info for billing document generation.
     * 
     * @param obj_header  Billing header info
     * @param mRef
     * @throws Exception
     */
    private void insert_update_table(G_BIL_P01Bean obj_header, List<String> mRef)
            throws Exception {
        // 1.1 Retrieve Billing details data group by billing type and billing month into result set K2
        SortedMap<String, List<Map<String, Object>>> K2 = new TreeMap<String, List<Map<String, Object>>>();
        String multiBillPeriod = "0";
        String billAccStrs="";
        if(this.TEMP_BIL_D!=null && 0<this.TEMP_BIL_D.size()) {
            String idBillAcc = CommonUtils.paddingRightSpace(CommonUtils.toString(TEMP_BIL_D.get(0).get("ID_BILL_ACCOUNT")), 20);
            billAccStrs=idBillAcc;
            Map<String, Object> bacHMap = queryDAO.executeForMap("SELECT.BSYS.SQL059", idBillAcc);
            multiBillPeriod = CommonUtils.toString(bacHMap.get("MULTI_BILL_PERIOD"));
        }
        // loop to get all record in TEMP_BIL_D and insert to K2
        for (Map<String, Object> record : this.TEMP_BIL_D) {
            // generate key. ID_BILL_ACCOUNT/BILL_TYPE/BILLING_MONTH
            String key = "<" + record.get("ID_BILL_ACCOUNT") + ">";
            key += "<" + record.get("BILL_TYPE") + ">";
            if ("0".equals(multiBillPeriod)) {
                key += "<" + record.get("BILLING_MONTH") + ">";
            }
            if (K2.get(key) == null) {
                K2.put(key, new ArrayList<Map<String, Object>>());
            }
            List<Map<String, Object>> list = K2.get(key);
            list.add(record);
        }

        // 1.2 Loop through the result set K2
        Iterator<Map.Entry<String, List<Map<String, Object>>>> iterator = K2.entrySet().iterator();
        // navigate iterator
        DecimalFormat formatter = new DecimalFormat("0.00");
        formatter.setRoundingMode(RoundingMode.HALF_UP);
        DecimalFormat formattergst = new DecimalFormat("0.0000");
        formattergst.setRoundingMode(RoundingMode.HALF_UP);
        boolean isBillAccNegFlg=false;
        while (iterator.hasNext()) {
            // 1.4 Retrieve each group billing details data to generate 1 billing document in to K1
            Map.Entry<String, List<Map<String, Object>>> entry = iterator.next();
            List<Map<String, Object>> K1 = entry.getValue();
            // check K1 record
            if (0 < K1.size()) {
                // 1.4.1 Check if there are any billing details for the billing document
                /* delete for 2012/01/26 change Remove 1.4.1 section */
                // int detailCount = 0;
                // for (Map<String, Object> record_K1 : K1) {
                // String itemLevel =
                // CommonUtils.toString(record_K1.get("ITEM_LEVEL"));
                // if ("1".equals(itemLevel)) {
                // detailCount++;
                // }
                // }
                // if (detailCount == 0) {
                // // Go to next record if no billing document detail.
                // continue;
                // }
                
                Comparator<Map<String,Object>> comparator = new Comparator<Map<String,Object>>(){
                       public int compare(Map<String,Object> param1, Map<String,Object> param2) {
                           double billingMonth1 = Double.parseDouble(CommonUtils.toString(param1.get("BILLING_MONTH")).trim());
                           double billingMonth2 = Double.parseDouble(CommonUtils.toString(param2.get("BILLING_MONTH")).trim());
                           
                           double itemId1 = Double.parseDouble(CommonUtils.toString(param1.get("ITEM_ID")).trim());
                           double itemId2 = Double.parseDouble(CommonUtils.toString(param2.get("ITEM_ID")).trim());
                           
                           if (billingMonth1 != billingMonth2) {
                               return Double.compare(billingMonth1, billingMonth2);
                           } else {
                               return Double.compare(itemId1, itemId2);
                           }
                       }
                };
                Collections.sort(K1,comparator);
                
                // 1.3 Variable assignment
                String idCust = CommonUtils.toString(K1.get(0).get("ID_CUST"));
                String billAccount = CommonUtils.toString(K1.get(0).get("ID_BILL_ACCOUNT"));
                String billType = CommonUtils.toString(K1.get(0).get("BILL_TYPE"));
                
                // 1.5 Calculate SubTotal, TotalGST, BillAmount
                BigDecimal subTotal = new BigDecimal(0);
                BigDecimal totalGST = new BigDecimal(0);
                BigDecimal exportAmount = new BigDecimal(0);
                for (Map<String, Object> record_K1 : K1) {
                    // only calculate ITEM_LEVEL=1 detail data
                    if ("1".equals(record_K1.get("ITEM_LEVEL").toString())) {
                        subTotal = subTotal.add(new BigDecimal(formatter.format(record_K1.get("ITEM_SUBTOTAL"))));
                        totalGST = totalGST.add(new BigDecimal(formattergst.format(record_K1.get("ITEM_GST"))));
                        exportAmount = exportAmount.add(new BigDecimal(CommonUtils.toString(record_K1.get("ITEM_EXPORT_AMT"))));
                        exportAmount = exportAmount.add(new BigDecimal(CommonUtils.toString(record_K1.get("ITEM_EXPORT_GST"))));
                        //exportAmount = new BigDecimal(formatter.format(exportAmount));
                    }
                }
                BigDecimal billAmount = subTotal.add(totalGST);
                //String fpGST = "0";
                //TotalGST != 0
                /*if (totalGST.compareTo(BigDecimal.ZERO)!=0) {
                    fpGST = this.pGST;
                }*/
                // Add checking after 1.5, if BillAmount = 0, update billing period and customer plan status 
                //negative bill amount flag
                if(billAmount.compareTo(BigDecimal.ZERO) == 0){
                    Map<String, Object> params = new HashMap<String, Object>();
                    for (Map<String, Object> record_K1 : K1) {
                        Object batchId = record_K1.get("BATCH_ID");
                        // 2.2 Update billing account information and custom service status
                        if (record_K1.get("ITEM_LEVEL").toString().equals("0")) {
                            // 2.2.1 Update billing account last billing period and recurring status
                            params = new HashMap<String, Object>();
                            params.put("ID_BILL_ACCOUNT", record_K1.get("ID_BILL_ACCOUNT"));
                            params.put("ID_CUST_PLAN_GRP", record_K1.get("ID_CUST_PLAN_GRP"));
                            params.put("IS_RECURRING", record_K1.get("IS_RECURRING"));
                            params.put("BILL_FROM", record_K1.get("BILL_FROM"));
                            params.put("BILL_TO", record_K1.get("BILL_TO"));
                            params.put("ID_LOGIN", this.loginUserId);
                            params.put("ID_AUDIT", this.auditId);
                            this.updateDAO.execute(UPDATE_T_BAC_D, params);
                            
                            // 2.2.2 Update customer service status
                            params = new HashMap<String, Object>();
                            params.put("ID_CUST_PLAN_GRP", record_K1.get("ID_CUST_PLAN_GRP"));
                            params.put("BILLING_STATUS", record_K1.get("BILLING_STATUS"));
                            params.put("ID_LOGIN", this.loginUserId);
                            params.put("ID_AUDIT", this.auditId);
                            this.updateDAO.execute(UPDATE_T_CUST_PLAN_D, params);
                        } else if (record_K1.get("ITEM_LEVEL").toString().equals("1")
                                && batchId != null) {
                            // 2.3 Update batch import clear call invoiced status
                            if (batchId.equals("CC")) {
                                // update T_CLC_IMP_MONTHLY_SUM
                                params = new HashMap<String, Object>();
                                params.put("IS_INVOICED", 1);
                                params.put("ID_LOGIN", this.loginUserId);
                                //params.put("ID_CUST", record_K1.get("ID_CUST"));
                                params.put("ID_CUST_PLAN", record_K1.get("ID_CUST_PLAN"));
                                params.put("BILLING_MONTH", record_K1.get("BILLING_MONTH"));
                                params.put("ID_AUDIT", this.auditId);
                                this.updateDAO.execute(UPDATE_T_CLC_IMP_MONTH, params);
                            }
                        }
                    }
                    
                    // 1.9 Update customer plan status
                    List<String> idCustPlanList = new ArrayList<String>();
                    for (Map<String, Object> record_K1 : K1) {
                        if ("1".equals(record_K1.get("UPDATE_STATUS").toString())) {
                            String idCustPlan = record_K1.get("ID_CUST_PLAN").toString();
                            if (!idCustPlanList.contains(idCustPlan)) {
                                params = new HashMap<String, Object>();
                                params.put("ID_CUST_PLAN", idCustPlan);
                                params.put("PLAN_STATUS", record_K1.get("PLAN_STATUS"));
                                params.put("ID_LOGIN", this.loginUserId);
                                params.put("ID_AUDIT", this.auditId);
                                this.updateDAO.execute(UPDATE_T_CUST_PLAN_H, params);
                                idCustPlanList.add(idCustPlan);
                            }
                        }
                    }
                    
                    continue;
                }
                // Add ciheckng after 1.5.1, if BillAmount < 0, update billing period and customer plan status
                if(billAmount.compareTo(BigDecimal.ZERO) <0){
                    isBillAccNegFlg=true;
                    if(!billAccList.contains(billAccStrs.trim())){
                        billAccList.add(billAccStrs.trim());
                    }
                    continue;
                }
                
                // 1.6 Calculate export amount and rate
                String exportCur = "";
                BigDecimal curRate = new BigDecimal(0);
                // check export currency
                String expCur = CommonUtils.toString(obj_header.getExpCur()).trim();
                if (expCur.equals("") || expCur.equals("-")) {
                    //exportAmount = billAmount;
                    exportCur = obj_header.getBillCurrency();
                    curRate = new BigDecimal(1);
                } else {
                    // call G_CUR_P01 to calculate  rate
                    G_CUR_P01 gCurP01 = new G_CUR_P01(this.queryDAO);
                    String bill_currency = obj_header.getBillCurrency();
                    exportCur = obj_header.getExpCur().toString();
                    Object fixed_forex = obj_header.getFixedForex();
                    curRate = new BigDecimal(CommonUtils.toString(gCurP01.convertCurrency(bill_currency, subTotal.doubleValue(), exportCur, fixed_forex).get(G_CUR_P01.CUR_RATE)));
                }
                
                // 1.7 Get Billing document running no
                String gstApplyFlag = "";
                if ("0".equals(obj_header.getTaxType())) {
                	gstApplyFlag = "1";
                } else if ("1".equals(obj_header.getTaxType())) {
                	gstApplyFlag = "2";
                } else {
                	gstApplyFlag = "0";
                }
                
                String code = "";
                if("MY".equals(this.pSysBase) & gstApplyFlag.equals("1")) {
                	if ("CN".equals(billType)) {
	                    code = "CDTN2";
	                } else if ("DN".equals(billType)) {
	                    code = "DBTN2";
	                } else if("NT".equals(billType)) {
	                    code = "NTIN2";
	                }else{
	                    code = "INVN2";
	                }
                }else {
                	if ("CN".equals(billType)) {
	                    code = "CDTNO";
	                } else if ("DN".equals(billType)) {
	                    code = "DBTNO";
	                } else if("NT".equals(billType)) {
	                    code = "NTINV";
	                }else{
	                    code = "INVNO";
	                }
                }

                // call G_CDM_P01 to generate code for bill
                G_CDM_P01 gCdmP01 = new G_CDM_P01(this.queryDAO, this.updateDAO, this.loginUserId);
                String bill_code = gCdmP01.getGenerateCode(code, this.loginUserId);

                // 1.8 Insert billing document header data into T_BIL_H
                Map<String, Object> params = new HashMap<String, Object>();
                params = new HashMap<String, Object>();
                params.put("ID_REF", bill_code);
                params.put("BILL_TYPE", billType);
                params.put("IS_MANUAL", 0);
                params.put("BILL_ACC", obj_header.getIdBillingAccount());
                params.put("DATE_REQ", this.billing_date);
                params.put("PAY_METHOD", obj_header.getPayMethod());
                params.put("ID_CUST", obj_header.getIdCust());
                params.put("ADR_TYPE", obj_header.getAdrType());
                params.put("CONTACT_TYPE", obj_header.getContactType());
                params.put("ID_BIF", mRef.get(0));
                params.put("ID_QCS", mRef.get(1));
                params.put("QUO_REF", mRef.get(2));
                params.put("CUST_PO", mRef.get(3));
                params.put("ID_CONSLT", mRef.get(4));
                params.put("DELIVERY", obj_header.getDelicery());
                params.put("DELIVERY_EMAIL", obj_header.getDeliceryEmail());
                //IF Billtype=CN, mRef[5]=<Blank>
                if ("CN".equals(billType)) {
                    params.put("TERM","");
                }
                //ELSE mRef[5] = mRef[5]
                else{
                    params.put("TERM", mRef.get(5));
                }
                params.put("BILL_CURRENCY", obj_header.getBillCurrency());
                params.put("GST_PERCENT", 0);
                params.put("GST_AMOUNT", formatter.format(totalGST));
                params.put("BILL_AMOUNT", formatter.format(billAmount));
                params.put("PAID_AMOUNT", 0);
                params.put("IS_SETTLED", 0);
                params.put("IS_SINGPOST", obj_header.getIsSingpost());
                params.put("IS_EXPORT", 0);
                params.put("IS_DISPLAY_EXP_AMT", obj_header.getIsDisplayExpAMT());
                params.put("EXPORT_CUR", exportCur);
                params.put("CUR_RATE", curRate);
                params.put("EXPORT_AMOUNT", formatter.format(exportAmount));
                params.put("FIXED_FOREX", obj_header.getFixedForex());
                params.put("NO_PRINTED", 0);
                params.put("IS_CLOSED", 0);
                params.put("ADDRESS1", obj_header.getAddress1());
                params.put("ADDRESS2", obj_header.getAddress2());
                params.put("ADDRESS3", obj_header.getAddress3());
                params.put("ADDRESS4", obj_header.getAddress4());
                params.put("ZIP_CODE", obj_header.getZipCode());
                params.put("COUNTRY", obj_header.getCountry());
                params.put("TEL_NO", obj_header.getTelNo());
                params.put("FAX_NO", obj_header.getFaxNo());
                params.put("CONTACT_NAME", obj_header.getContractName());
                params.put("CONTACT_EMAIL", obj_header.getEmail());
                params.put("CONTACT_EMAIL_CC", obj_header.getEmailCc());
                params.put("CUST_NAME", obj_header.getCustName());
                params.put("SALUTATION", obj_header.getSalutation());
                params.put("IS_DELETED", 0);
                params.put("PREPARED_BY", this.loginUserId);
                params.put("ID_LOGIN", this.loginUserId);
                params.put("ID_AUDIT", this.auditId);
                params.put("GSTAPPLYFLAG", gstApplyFlag);
                /*if ("0".equals(obj_header.getTaxType())) {
                	params.put("GSTAPPLYFLAG", "1");
                } else if ("1".equals(obj_header.getTaxType())) {
                	params.put("GSTAPPLYFLAG", "2");
                } else {
                	params.put("GSTAPPLYFLAG", "0");
                }*/
                
                //Set 'TERM_DAY' and 'DUE_DATE' value
                int termDay=0;
                if(!mRef.get(6).equals("")){
                    termDay=Integer.parseInt(mRef.get(6));
                }
                //IF Billtype=CN, mRef[6]=0
                if("CN".equals(billType)){
                    termDay=0;
                    params.put("TERM_DAY",0);
                }
                //ELSE  mRef[6]=mRef[6]
                else{
                    params.put("TERM_DAY", termDay);
                }
                
                SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
                Date billDate=sf.parse(this.billing_date);
                Calendar c = Calendar.getInstance();
                c.setTime(billDate);
                //Billing_date + mRef[6]
                c.add(Calendar.DAY_OF_MONTH, termDay);
                String dueDate=sf.format(c.getTime());
                params.put("DUE_DATE", dueDate);
                this.updateDAO.execute(INSERT_T_BIL_H, params);

                // 2.0 Create Billing Document Detail Data
                int i = 0;
                for (Map<String, Object> record_K1 : K1) {
//                  // Add checking before 2.1, if K.ITEM_AMT>0 
//                  String itemAmount = CommonUtils.toString(record_K1.get("ITEM_AMOUNT")).trim();
//                  if(Double.valueOf(itemAmount.equals("")?"0":itemAmount).doubleValue()<=0.0){
//                      continue;
//                  }

                    // 2.1 Insert billing document detail data into T_BIL_D
                    params = new HashMap<String, Object>();
                    params.put("ID_REF", bill_code);
                    params.put("ITEM_ID", "" + ++i);
                    params.put("ITEM_LEVEL", record_K1.get("ITEM_LEVEL"));
                    params.put("ITEM_DESC", record_K1.get("ITEM_DESC"));
                    params.put("ITEM_QTY", record_K1.get("ITEM_QTY"));
                    params.put("ITEM_UPRICE", formatter.format(record_K1.get("ITEM_UPRICE")));
                    params.put("ITEM_AMOUNT", formatter.format(record_K1.get("ITEM_AMOUNT")));
                    params.put("ITEM_GST", formattergst.format(record_K1.get("ITEM_GST")));
                    params.put("ITEM_EXPORT_AMT", formattergst.format(record_K1.get("ITEM_EXPORT_AMT")));
                    params.put("APPLY_GST", record_K1.get("APPLY_GST"));
                    params.put("IS_DISPLAY", record_K1.get("IS_DISPLAY"));
                    params.put("ID_CUST_PLAN", record_K1.get("ID_CUST_PLAN"));
                    params.put("ID_CUST_PLAN_GRP", record_K1.get("ID_CUST_PLAN_GRP"));
                    params.put("ID_CUST_PLAN_LINK", record_K1.get("ID_CUST_PLAN_LINK"));
                    params.put("SVC_LEVEL1", record_K1.get("SVC_LEVEL1"));
                    params.put("SVC_LEVEL2", record_K1.get("SVC_LEVEL2"));
                    params.put("BILL_FROM", record_K1.get("BILL_FROM"));
                    params.put("BILL_TO", record_K1.get("BILL_TO"));
                    params.put("JOB_NO", record_K1.get("JOB_NO"));
                    params.put("IS_DISPLAY_JOBNO", record_K1.get("IS_DISPLAY_JOBNO"));
                    params.put("CUST_PO", record_K1.get("CUST_PO"));
                    params.put("IS_DELETED", 0);
                    params.put("ID_LOGIN", this.loginUserId);
                    params.put("ID_AUDIT", this.auditId);
                    params.put("ITEM_TYPE", record_K1.get("ITEM_TYPE"));
                    params.put("ITEM_CAT", record_K1.get("ITEM_CAT"));
                    params.put("IS_DISPLAY_MIN_SVC", record_K1.get("IS_DISPLAY_MIN_SVC"));
                    params.put("MIN_SVC_FROM", record_K1.get("MIN_SVC_FROM"));
                    params.put("MIN_SVC_TO", record_K1.get("MIN_SVC_TO"));
                    params.put("DISPLAY_DISC", record_K1.get("DISPLAY_DISC"));
                    params.put("ITEM_DISC_AMT", record_K1.get("ITEM_DISC_AMT"));
                    params.put("ITEM_SUBTOTAL", record_K1.get("ITEM_SUBTOTAL"));
                    params.put("TAX_CODE", record_K1.get("TAX_CODE"));
                    params.put("TAX_RATE", record_K1.get("TAX_RATE"));
                    params.put("ITEM_EXPORT_GST", record_K1.get("ITEM_EXPORT_GST"));
                    // #203 Start
                    params.put("LOCATION", record_K1.get("LOCATION"));
                    // #203 End
                    
                    //#594: [B1] Customer Maintenance CT 20190604
                    params.put("TAX_EXEMPTED_AMT", record_K1.get("TAX_EXEMPTED_AMT"));
                    //#594: [B1] Customer Maintenance CT 20190604
                    
                    this.updateDAO.execute(INSERT_T_BIL_D, params);

                    Object batchId = record_K1.get("BATCH_ID");
                    // 2.2 Update billing account information and custom service status
                    if (record_K1.get("ITEM_LEVEL").toString().equals("0")) {
                        // 2.2.1 Update billing account last billing period and recurring status
                        params = new HashMap<String, Object>();
                        params.put("ID_BILL_ACCOUNT", record_K1.get("ID_BILL_ACCOUNT"));
                        params.put("ID_CUST_PLAN_GRP", record_K1.get("ID_CUST_PLAN_GRP"));
                        params.put("IS_RECURRING", record_K1.get("IS_RECURRING"));
                        params.put("BILL_FROM", record_K1.get("BILL_FROM"));
                        params.put("BILL_TO", record_K1.get("BILL_TO"));
                        params.put("ID_LOGIN", this.loginUserId);
                        params.put("ID_AUDIT", this.auditId);
                        this.updateDAO.execute(UPDATE_T_BAC_D, params);
                        
                        // 2.2.2 Update customer service status
                        params = new HashMap<String, Object>();
                        params.put("ID_CUST_PLAN_GRP", record_K1.get("ID_CUST_PLAN_GRP"));
                        params.put("BILLING_STATUS", record_K1.get("BILLING_STATUS"));
                        params.put("ID_LOGIN", this.loginUserId);
                        params.put("ID_AUDIT", this.auditId);
                        this.updateDAO.execute(UPDATE_T_CUST_PLAN_D, params);
                    } else if (record_K1.get("ITEM_LEVEL").toString().equals("1")
                            && batchId != null) {
                        // 2.3 Update batch import clear call invoiced status
                        if (batchId.equals("CC")) {
                            // update T_CLC_IMP_MONTHLY_SUM
                            params = new HashMap<String, Object>();
                            params.put("IS_INVOICED", 1);
                            params.put("ID_LOGIN", this.loginUserId);
                            //params.put("ID_CUST", record_K1.get("ID_CUST"));
                            params.put("ID_CUST_PLAN", record_K1.get("ID_CUST_PLAN"));
                            params.put("BILLING_MONTH", record_K1.get("BILLING_MONTH"));
                            params.put("ID_AUDIT", this.auditId);
                            this.updateDAO.execute(UPDATE_T_CLC_IMP_MONTH, params);
                        }
                    }
                }
                
                // 1.9 Update customer plan status
                List<String> idCustPlanList = new ArrayList<String>();
                for (Map<String, Object> record_K1 : K1) {
                    if ("1".equals(record_K1.get("UPDATE_STATUS").toString())) {
                        String idCustPlan = record_K1.get("ID_CUST_PLAN").toString();
                        if (!idCustPlanList.contains(idCustPlan)) {
                            params = new HashMap<String, Object>();
                            params.put("ID_CUST_PLAN", idCustPlan);
                            params.put("PLAN_STATUS", record_K1.get("PLAN_STATUS"));
                            params.put("ID_LOGIN", this.loginUserId);
                            params.put("ID_AUDIT", this.auditId);
                            this.updateDAO.execute(UPDATE_T_CUST_PLAN_H, params);
                            idCustPlanList.add(idCustPlan);
                        }
                    }
                }
                
                // 1.10 Get Invoice Summary
                if ("SG".equals(this.pSysBase) && 
                        "IN".equals(billType)) {
                    obj_header.setRefId(bill_code);
                    obj_header.setAmount(billAmount);
                    this.getInvoiceSummary(obj_header);
                }
                
                // 1.11 Update Billing Account:Total Amount Due
                if ("BAC".equals(this.pAutoOffSet)) {
                    BigDecimal bacBillAmount = new BigDecimal(0);
                    if ("CN".equals(billType)) {
                        bacBillAmount = bacBillAmount.subtract(billAmount);
                    } else {
                        bacBillAmount = billAmount;
                    }
                    params = new HashMap<String, Object>();
                    params.put("BACBillAmount", bacBillAmount);
                    params.put("ID_CUST", idCust);
                    params.put("ID_BILL_ACCOUNT", billAccount);
                    params.put("ID_LOGIN", this.loginUserId);
                    params.put("ID_AUDIT", this.auditId);
                    this.updateDAO.execute(UPDATE_T_BAC_H, params);
                }
                
                // 1.12 Update billing document no idref into listBillDoc
                this.listBillDoc.add(bill_code);
                // increase count of generated doc
                this.countGenDoc += 1;
            }
        }
        //1.5.1 Error message for negative bill amount
        if(isBillAccNegFlg){
            String billAccStrTotal="";
            for(int h=0;h<billAccList.size();h++){
                billAccStrTotal+="BAC"+billAccList.get(h)+",";
            }
            billAccStrTotal=billAccStrTotal.trim().substring(0,billAccStrTotal.length()-1);
            if(mapp.containsKey("BILL_ACC_MULTI")){
                mapp.remove("BILL_ACC_MULTI");
            }
            mapp.put("BILL_ACC_MULTI", billAccStrTotal);
            //mapp.put("BILL_ACC_MULTI", billAccStrTotal);
            if(listErrorInfo.size()>0){
                for(int k=0;k<listErrorInfo.size();k++){
                    Map<String, Object> tempmap=listErrorInfo.get(k);
                    if(tempmap.containsKey("BILL_ACC_MULTI")){
                        listErrorInfo.remove(k);
                    }
                }
            }
            listErrorInfo.add(mapp);
        }
    }

    /**
     * Get Invoice Summary.
     * 
     * @param headerBean G_BIL_P01Bean
     */
    private void getInvoiceSummary(G_BIL_P01Bean headerBean) {
        BigDecimal previousBal = new BigDecimal(0);
        String lastPayType = null;
        String lastPayDate = null;
        BigDecimal lastPayAmt = new BigDecimal(0);
        String rejectPayType = null;
        String rejectPayDesc = null;
        String rejectDate = null;
        BigDecimal rejectPayAmt = new BigDecimal(0);
        BigDecimal totalOutBal = new BigDecimal(0);
        BigDecimal totalAmtDue = new BigDecimal(0);
        int cbUpdate = 0;
        int bilUpdate = 0;
        
        SimpleDateFormat dateFomart = new SimpleDateFormat("dd/MM/yyyy");
        
        String billingAccountId = headerBean.getIdBillingAccount();
        String custId = headerBean.getIdCust();
        
        // 1.2 Retrieve Billing Summary into result set T1
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("BILL_ACC", billingAccountId);
        List<Map<String, Object>> resultT1 = this.queryDAO.executeForMapList(GET_INVOICE_SUMMARY, params);
        
        // 1.3 Get Balance Brought Forward from previous billing
        if (resultT1 != null && 0 < resultT1.size()) {
            Map<String, Object> latestInvoice = resultT1.get(resultT1.size()-1);
            previousBal = new BigDecimal(latestInvoice.get("TOTAL_AMT_DUE").toString());
        }
        
        // 1.4 Get B Statement
        // 1.4.1 Retrieve last payment from cash book into result set T2
        params= new HashMap<String, Object>();
        params.put("ID_CUST", custId);
        params.put("ID_BILL_ACCOUNT", billingAccountId);
        List<Map<String, Object>> resultT2 = this.queryDAO.executeForMapList(GET_LAST_PAYMENT, params);
        if (resultT2 != null && 0 < resultT2.size()) {
            // 1.4.2 Go to last record of result set T2
            Map<String, Object> latestCashbook = resultT2.get(resultT2.size()-1);

            // 1.4.3 Get payment method description from context
            ApplicationContext context = ApplicationContextProvider.getApplicationContext();
            MappedCodeListLoader codelist = (MappedCodeListLoader)context.getBean("LIST_PAYMENT_METHOD");
            @SuppressWarnings("unchecked")
            Map<String, Object> values = codelist.getCodeListMap();
            String paymentMethod = CommonUtils.toString(values.get(latestCashbook.get("PMT_METHOD")));
            lastPayType = paymentMethod.trim();
            lastPayDate = dateFomart.format(latestCashbook.get("DATE_TRANS"));
            for (Map<String, Object> record : resultT2) {
                lastPayAmt = lastPayAmt.add(new BigDecimal(record.get("AMT_RECEIVED").toString()));
            }
            cbUpdate = 1;
        } else {
            // 1.4.4 Retrieve previous over paid payment from cash book into result set T3
            params= new HashMap<String, Object>();
            params.put("ID_CUST", custId);
            params.put("ID_BILL_ACCOUNT", billingAccountId);
            List<Map<String, Object>> resultT3 = this.queryDAO.executeForMapList(GET_PREVIOUS_OVER_PAID, params);
            if (resultT3 != null && 0 < resultT3.size()) {
                // 1.4.5 Go to last record of result set T3
                Map<String, Object> latestCashbook = resultT3.get(resultT3.size()-1);
                lastPayType = "ADVANCE RECEIPT";
                lastPayDate = dateFomart.format(latestCashbook.get("DATE_TRANS"));
                lastPayAmt = new BigDecimal(0);
                cbUpdate = 1;
            } else {
                // No payment case
                lastPayType = "-";
                lastPayDate = null;
                lastPayAmt = new BigDecimal(0);
            }
        }
        
        // 1.5 Get C Statement
        // 1.5.1 Retrieve rejected payment from cash book into result set T4
        params= new HashMap<String, Object>();
        params.put("ID_CUST", custId);
        params.put("ID_BILL_ACCOUNT", billingAccountId);
        List<Map<String, Object>> resultT4 = this.queryDAO.executeForMapList(GET_REJECTED_PAYMENT, params);
        if (resultT4 != null && 0 < resultT4.size()) {
            // 1.5.2 Go to last record of result set T4
            Map<String, Object> latestRejected = resultT4.get(resultT4.size()-1);
            
            // 1.5.3 Get payment method description from context
            ApplicationContext context = ApplicationContextProvider.getApplicationContext();
            MappedCodeListLoader codelist = (MappedCodeListLoader)context.getBean("LIST_PAYMENT_METHOD");
            @SuppressWarnings("unchecked")
            Map<String, Object> values = codelist.getCodeListMap();
            String paymentMethod = CommonUtils.toString(values.get(latestRejected.get("PMT_METHOD")));
            rejectPayType = "Reject (" + paymentMethod + ") - ";
            rejectPayDesc = CommonUtils.toString(latestRejected.get("REJECT_DESC"));
            rejectDate = dateFomart.format(latestRejected.get("REJECT_DATE"));
            for (Map<String, Object> record : resultT4) {
                rejectPayAmt = rejectPayAmt.add(new BigDecimal(record.get("AMT_RECEIVED").toString()));
            }
            rejectPayAmt = new BigDecimal(0).subtract(rejectPayAmt);
            cbUpdate = 1;
        } else {
            // 1.5.4 Retrieve paid previous invoice payment from cash book into result set T5
            params= new HashMap<String, Object>();
            params.put("ID_CUST", custId);
            params.put("ID_BILL_ACCOUNT", billingAccountId);
            List<Map<String, Object>> resultT5 = this.queryDAO.executeForMapList(GET_PAID_PRE_INVOICE, params);
            if (resultT5 != null && 0 < resultT5.size()) {
                // 1.5.5 Go to last record of result set T5
                Map<String, Object> latestPaiedPre = resultT5.get(resultT5.size()-1);
                rejectPayType = "Paid Pre Invoice - ";
                rejectPayDesc = CommonUtils.toString(latestPaiedPre.get("REFERENCE1")).trim();
                rejectDate = dateFomart.format(latestPaiedPre.get("DATE_TRANS"));
                for (Map<String, Object> record : resultT5) {
                    rejectPayAmt = rejectPayAmt.add(new BigDecimal(record.get("AMT_RECEIVED").toString()));
                }
            } else {
                // 1.5.6 Retrieve revision billing document into result set T6
                params= new HashMap<String, Object>();
                params.put("ID_CUST", custId);
                params.put("BILL_ACC", billingAccountId);
                List<Map<String, Object>> resultT6 = this.queryDAO.executeForMapList(GET_REVISION_BILL_DOC, params);
                if (resultT6 != null && 0 < resultT6.size()) {
                    BigDecimal dnAmt = new BigDecimal(0);
                    BigDecimal cnAmt = new BigDecimal(0);
                    for (Map<String, Object> record: resultT6) {
                        if ("DN".equals(record.get("BILL_TYPE").toString())){
                            dnAmt = dnAmt.add(new BigDecimal(record.get("BILL_AMOUNT").toString()));
                        } else if ("CN".equals(record.get("BILL_TYPE").toString())){
                            cnAmt = cnAmt.add(new BigDecimal(record.get("BILL_AMOUNT").toString()));
                        }
                    }
                        
                    // 1.5.9 Go to last record of result set T6
                    Map<String, Object> latestBillDoc = resultT6.get(resultT6.size()-1);
                    rejectPayType = "Revision Previous Invoice - ";
                    rejectPayDesc = CommonUtils.toString(latestBillDoc.get("ID_REF")).trim();
                    rejectDate = dateFomart.format(latestBillDoc.get("DATE_REQ"));
                    rejectPayAmt = dnAmt.subtract(cnAmt);
                    bilUpdate = 1;
                }
            }
        }
        
        // 1.5 Get Outstanding Balance
        params= new HashMap<String, Object>();
        params.put("ID_CUST", custId);
        params.put("ID_BILL_ACCOUNT", billingAccountId);
        Map<String, Object> totalAmt = this.queryDAO.executeForMap(GET_BILACC_TOTAL_AMTDUE, params);
        if (totalAmt != null) {
            totalOutBal = new BigDecimal(totalAmt.get("TOTAL_AMT_DUE").toString());
        }
        
        // 1.6 Get Total Amount Due
        totalAmtDue = totalOutBal.add(new BigDecimal(headerBean.getAmount()));
        
        // 1.7 Insert Invoice Summary
        params = new HashMap<String, Object>();
        params.put("ID_REF", headerBean.getRefId());
        params.put("PREVIOUS_AMT", previousBal);
        params.put("LAST_PAY_TYPE", lastPayType);
        params.put("LAST_PAY_DATE", lastPayDate);
        params.put("LAST_PAY_AMT", lastPayAmt);
        params.put("REJECT_PAY_TYPE", rejectPayType);
        params.put("REJECT_DESC", rejectPayDesc);
        params.put("REJECT_DATE", rejectDate);
        params.put("REJECT_PAY_AMT", rejectPayAmt);
        params.put("TOTAL_OUTSTANDING", totalOutBal);
        params.put("TOTAL_AMT_DUE", totalAmtDue);
        params.put("IS_DELETED", 0);
        params.put("ID_LOGIN", headerBean.getIdLogin());
        params.put("ID_AUDIT", headerBean.getIdAudit());
        this.updateDAO.execute(INSERT_T_BIL_S, params);
        
        // 2.0 Update cash book record and revision previous invoice record
        if (cbUpdate == 1) {
            // 2.1 Update cash book IS_EXPORT = 1 for those record IS_EXPORT = 0
            params = new HashMap<String, Object>();
            params.put("ID_CUST", custId);
            params.put("ID_BILL_ACCOUNT", billingAccountId);
            params.put("ID_LOGIN", headerBean.getIdLogin());
            params.put("ID_AUDIT", headerBean.getIdAudit());
            this.updateDAO.execute(UPDATE_T_CSB_H_IS_EXPORT, params);
        }
        
        if (bilUpdate == 1) {
            // 2.2 Update debit note or credit note IS_EXPORT = 1 for those record IS_EXPORT = 0
            params = new HashMap<String, Object>();
            params.put("ID_CUST", custId);
            params.put("BILL_ACC", billingAccountId);
            params.put("ID_LOGIN", headerBean.getIdLogin());
            params.put("ID_AUDIT", headerBean.getIdAudit());
            this.updateDAO.execute(UPDATE_T_BIL_H_IS_EXPORT, params);
        }
    }
    
    /**
     * Initialize system paramter.
     * 
     * @param input parameter passed to the process.
     */
    private void initGlobalParamter(G_BIL_P01_Input input) {

        // set current month as billing month, and current date as billing date
        if (input.getRunning_date() != null) {
            this.running_date = input.getRunning_date();
        } else {
            this.running_date = new Date();
        }
        SimpleDateFormat date_format = new SimpleDateFormat();
        date_format.applyPattern(FORMAT_SORT_MONTH);
        this.billing_month = date_format.format(this.running_date);
        date_format.applyPattern(FORMAT_SORT_DATE);
        this.billing_date = date_format.format(this.running_date);

        // set the last day of current month
        Date last_date = this.running_date;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(last_date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        last_date = calendar.getTime();
        this.billing_date_last_day = date_format.format(last_date);
        
        // get GST
        Object gstValue = this.queryDAO.executeForObject(GET_GSETD_BY_ID_SET, "GST", String.class);
        if (gstValue == null) {
            this.pGST = "0";
        } else {
            this.pGST = gstValue.toString();
        }
        // get SYSBASE
        Object sysBase = this.queryDAO.executeForObject(GET_GSETD_BY_ID_SET, "SYSTEMBASE", String.class);
        if (sysBase == null) {
            this.pSysBase = "";
        } else {
            this.pSysBase = sysBase.toString();
        }
        
        // get CB_AUTO_OFFSET
        Object autoOffSet = this.queryDAO.executeForObject(GET_GSETD_BY_ID_SET, "CB_AUTO_OFFSET", String.class);
        if (autoOffSet == null) {
            this.pAutoOffSet = "";
        } else {
            this.pAutoOffSet = autoOffSet.toString();
        }
        
        /*#594: [B1] Customer Maintenance CT 20190604*/
        String taxExemptedListStr = queryDAO.executeForObject(GET_GSETD_BY_ID_SET, "BILL_DOC_TAX_EXEMPTED", String.class);
        if(taxExemptedListStr != null || "".equals(taxExemptedListStr.trim())) {
        	this.taxExemptedList =  Arrays.asList(taxExemptedListStr.split(","));
        }
        /*#594: [B1] Customer Maintenance CT 20190604*/
    }
    
    /**
     * Generate billing header information.
     * 
     * @param record
     *            Billing Account and Custom Plan information
     * @return billing header
     */
    private G_BIL_P01Bean getBillingHeader(Map<String, Object> record) {
        G_BIL_P01Bean billingObject = new G_BIL_P01Bean();
        billingObject.setBillType(CommonUtils.toString(record.get("TRANSACTION_TYPE")));
        billingObject.setPayMethod(CommonUtils.toString(record.get("PAYMENT_METHOD")));
        billingObject.setIdCust(record.get("ID_CUST").toString());
        billingObject.setAdrType(record.get("CUST_ADR_TYPE").toString());
        billingObject.setContactType(record.get("CONTACT_TYPE").toString());
        billingObject.setBillCurrency(record.get("BILL_CURRENCY").toString());
        billingObject.setExpCur(record.get("EXPORT_CURRENCY"));
        billingObject.setFixedForex(record.get("FIXED_FOREX"));
        if (record.get("IS_DISPLAY_EXP_AMT") == null) {
            billingObject.setIsDisplayExpAMT("0");
        } else {
            billingObject.setIsDisplayExpAMT(record.get("IS_DISPLAY_EXP_AMT").toString());
        }

        // get data for custom information
        Map<String, String> custParams = new HashMap<String, String>();
        custParams.put("ID_CUST", billingObject.getIdCust());
        custParams.put("CUST_ADR_TYPE", billingObject.getAdrType());
        custParams.put("CONTACT_TYPE", billingObject.getContactType());
        Map<String, Object> V1 = this.queryDAO.executeForMap(GET_CUST_INFO, custParams);

        if (V1 != null) {
            if ("CORP".equals(V1.get("CUSTOMER_TYPE"))) {
                String telFaxContactFlg = CommonUtils.toString(queryDAO.executeForObject("B_BIL.selectTEL_FAX_CONTACT", null, String.class));
                if("1".equals(telFaxContactFlg)) {
                    billingObject.setTelNo(V1.get("M_CUST_CTC_TEL_NO"));
                    billingObject.setFaxNo(V1.get("M_CUST_CTC_FAX_NO"));
                } else {
                    billingObject.setTelNo(V1.get("CO_TEL_NO"));
                    billingObject.setFaxNo(V1.get("CO_FAX_NO"));
                }
                billingObject.setContractName(V1.get("CONTACT_NAME"));
                billingObject.setEmail(V1.get("EMAIL"));
            } else {
                billingObject.setTelNo(V1.get("HOME_TEL_NO"));
                billingObject.setFaxNo(V1.get("HOME_FAX_NO"));
                billingObject.setContractName(V1.get("CUST_NAME"));
                billingObject.setEmail(V1.get("CO_EMAIL"));
            }
            //EMAIL_CC
            billingObject.setEmailCc(V1.get("EMAIL_CC"));
            //CUST_NAME
            billingObject.setCustName(CommonUtils.toString(V1.get("CUST_NAME")).trim());
            //SALUTATION
            billingObject.setSalutation(CommonUtils.toString(V1.get("SALUTATION")).trim());
            // set ZipCode
            String zipCode = CommonUtils.toString(V1.get("ZIP_CODE"));
            billingObject.setZipCode(zipCode);
            // get country information from context
            ApplicationContext context = ApplicationContextProvider.getApplicationContext();
            MappedCodeListLoader codelist = (MappedCodeListLoader)context.getBean("LIST_COUNTRY");
            @SuppressWarnings("unchecked")
            Map<String, Object> values = codelist.getCodeListMap();
            String contry = CommonUtils.toString(values.get(V1.get("COUNTRY"))).trim();
            billingObject.setCountry(contry);
            billingObject.setAddress1(V1.get("ADR_LINE1"));
            billingObject.setAddress2(V1.get("ADR_LINE2"));
            billingObject.setAddress3(V1.get("ADR_LINE3"));
            // Bug_Report_PTTest_NTTMSC_BillingSystemP1 IS-71
            // #62 For Address4, if zipCode is empty, no ",".
            if ("".equals(zipCode)){
                billingObject.setAddress4(contry);
            } else {
                billingObject.setAddress4(zipCode.concat(", ").concat(contry));
            }
        }

        billingObject.setIdCustPlan(record.get("ID_CUST_PLAN").toString());
        billingObject.setIdCustPlanGrp(record.get("ID_CUST_PLAN_GRP").toString());
        billingObject.setIdBillingAccount(record.get("ID_BILL_ACCOUNT").toString());
        billingObject.setIdLogin(this.loginUserId);
        billingObject.setIdAudit(this.auditId);
        billingObject.setTaxType(CommonUtils.toString(record.get("TAX_TYPE")));
        
        return billingObject;
    }

    
    /**
     * Set auditIdModule
     * 
     * @param auditIdModule
     *            the auditIdModule to set
     */
    public void setAuditIdModule(String auditIdModule) {
        this.auditIdModule = auditIdModule;
    }

    /**
     * Set auditIdSubModule
     * 
     * @param auditIdSubModule
     *            the auditIdSubModule to set
     */
    public void setAuditIdSubModule(String auditIdSubModule) {
        this.auditIdSubModule = auditIdSubModule;
    }

    /**
     * Set auditReference
     * 
     * @param auditReference
     *            the auditReference to set
     */
    public void setAuditReference(String auditReference) {
        this.auditReference = auditReference;
    }

    /**
     * Set auditStatus
     * 
     * @param auditStatus
     *            the auditStatus to set
     */
    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }
    
	// #192 Start
	private void resetBillingMonth(String billOption, String billInstruct) {
		//Ver3.06 #268 Generate Billing - Post paid issue fixing CT 20170707
		if ("2".equals(billOption)) {
		//Ver3.06 #268 Generate Billing - Post paid issue fixing CT 20170707
			if (StringUtils.isNotEmpty(billInstruct)) {
				int nMonth = 0;
				switch (Integer.parseInt(billInstruct)) {
				case 1:
					// Yearly
					nMonth = -12;
					break;
				case 2:
					// Annually
					nMonth = -6;
					break;
				case 3:
					// Quarterly
					nMonth = -3;
					break;
				case 4:
					// Bi-Monthly
					nMonth = -2;
					break;
				case 5:
					// Monthly
					nMonth = -1;
					break;
				default:
					nMonth = 0;
					break;
				}
				try {
					SimpleDateFormat df = new SimpleDateFormat();
					df.applyPattern(FORMAT_SORT_MONTH);
					Calendar c = Calendar.getInstance();
					c.setTime(this.running_date);
					c.add(Calendar.MONTH, nMonth);
					this.billing_month = df.format(c.getTime());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {
			SimpleDateFormat df = new SimpleDateFormat();
			df.applyPattern(FORMAT_SORT_MONTH);
			this.billing_month = df.format(this.running_date);
		}
	}
	// #192 End
	//Fix Item_desc length over 4000 character 20180404 CT
	private String clobToString(Clob data) {
        StringBuilder sb = new StringBuilder();
        try {
            Reader reader = data.getCharacterStream();
            BufferedReader br = new BufferedReader(reader);

            String line;
            while(null != (line = br.readLine())) {
                sb.append(line);
                sb.append("\n");
            }
            br.close();
        } catch (SQLException e) {
            // handle this exception
        } catch (IOException e) {
            // handle this exception
        }
        return sb.toString();
    }
	//Fix Item_desc length over 4000 character 20180404 CT
}

