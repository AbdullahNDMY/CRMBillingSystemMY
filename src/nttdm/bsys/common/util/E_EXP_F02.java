/**
 * @(#)E_EXP_F02.java
 *
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import jp.terasoluna.fw.web.struts.action.GlobalMessageResources;
import nttdm.bsys.common.bean.T_SET_BATCH;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.dto.E_EXP_F02Output;
import nttdm.bsys.g_alt.AlertUserDto;
import nttdm.bsys.g_alt.G_ALT_P06Input;

/**
 * EOM - Statement of Accounts Export
 */
public class E_EXP_F02 {
    
    /**
     * Export file path
     */
    private String file_path;
    
    /**
     * SYSTEMBASE
     */
    private String systemBase;
    /**
     * <div>queryDAO</div>
     */
    private QueryDAO queryDAO;
    /**
     * <div>updateDAO</div>
     */
    private UpdateDAO updateDAO;
    /**
     * <div>batch_id</div>
     */
    private int batch_id;
    /**
     * <div>id_login</div>
     */
    private String id_login;
    
	public void setId_login(String id_login) {
		this.id_login = id_login;
	}

    /**
     * <div>eset_rundate</div>
     */
    private Date eset_rundate;
    /**
     * <div>CUR_STMT_MTH</div>
     */
    private String curStmtMonth;

    /**
     * <div>used to export error log</div>
     */
    ArrayList<String> custIds = new ArrayList<String>();
    ArrayList<String> billAccs = new ArrayList<String>();
    

    /** a list of inserted or updated statement no*/
    ArrayList<String> listStatementNo = new ArrayList<String>();
    /**
     * <div>Customer ID Array</div>
     */
    private String id_cust ;
    
    /**
     * <div>BATCH EXECUTE FLAG</div>
     */
    private String batch = "";
    
    /**
     * <div>idBillAccount</div>
     */
    private String idBillAccount;
    
    /**
     * <div>statement id </div>
     */
    private String[] id_stmts;
    
    /**
     * batchId
     */
    private Integer idBatch=0;

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
     * get statement id 
     * @return id_stmts
     */
    public String[] getId_stmts() {
		return id_stmts;
	}

    /**
     * set statement id 
     * @param id_stmts
     */
	public void setId_stmts(String[] id_stmts) {
		this.id_stmts = id_stmts;
	}

	/**
     * @return the id_cust
     */
    public String getId_cust() {
        return id_cust;
    }

    /**
     * Set Given ID_CUST array
     * @param id_cust
     */
    public void setId_cust(String id_cust) {
		this.id_cust = id_cust;
	}
	
    /**
     * Set batch execute flag
     * 
     * @param batch
     */
	public void setBatch(String batch) {
		this.batch = batch;
	}

    /**
     * @return the batch
     */
    public String getBatch() {
        return batch;
    }

	/**
     * @return the curStmtMonth
     */
    public String getCurStmtMonth() {
        return curStmtMonth;
    }

    /**
     * @param curStmtMonth the curStmtMonth to set
     */
    public void setCurStmtMonth(String curStmtMonth) {
        this.curStmtMonth = curStmtMonth;
    }

    /**
     * @return the eset_rundate
     */
    public Date getEset_rundate() {
        return eset_rundate;
    }

    /**
     * @param eset_rundate
     *            the eset_rundate to set
     */
    public void setEset_rundate(Date eset_rundate) {
        this.eset_rundate = eset_rundate;
    }

    /**
     * <div>getQueryDAO</div>
     * 
     * @return QueryDAO
     */
    public QueryDAO getQueryDAO() {
        return queryDAO;
    }

    /**
     * <div>setQueryDAO</div>
     * 
     * @param queryDAO
     *            QueryDAO
     */
    public void setQueryDAO(QueryDAO queryDAO) {
        this.queryDAO = queryDAO;
    }

    /**
     * <div>getUpdateDAO</div>
     * 
     * @return UpdateDAO
     */
    public UpdateDAO getUpdateDAO() {
        return updateDAO;
    }

    /**
     * <div>setUpdateDAO</div>
     * 
     * @param updateDAO
     *            UpdateDAO
     */
    public void setUpdateDAO(UpdateDAO updateDAO) {
        this.updateDAO = updateDAO;
    }

    /**
     * @return the idBillAccount
     */
    public String getIdBillAccount() {
        return idBillAccount;
    }

    /**
     * @param idBillAccount the idBillAccount to set
     */
    public void setIdBillAccount(String idBillAccount) {
        this.idBillAccount = idBillAccount;
    }

    /**
     * <div>excute</div>
     */
    public GlobalProcessResult excute(E_EXP_F02Output output) {
        GlobalMessageResources message_info = GlobalMessageResources.getInstance();
        GlobalProcessResult gResult = new GlobalProcessResult();
       
        // Calculate STMT_DATE
        Calendar c1 = Calendar.getInstance();
        if (eset_rundate != null) {
            c1.setTime(eset_rundate);
        }
        int day_num = c1.get(Calendar.DATE);
        // the process is for Previous month
        if ("0".equals(this.getCurStmtMonth())) {
            c1.add(Calendar.MONTH, -1);
            day_num = c1.getActualMaximum(Calendar.DAY_OF_MONTH);
        }
        int month = c1.get(Calendar.MONTH);
        int year = c1.get(Calendar.YEAR);
        c1.set(year, month, day_num);
        String DATE_FORMAT = "ddMMyyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        String stmt_date_str = sdf.format(c1.getTime());
        
        // CB_AUTO_OFFSET
        String offset = queryDAO.executeForObject("SELECT.BSYS.E_EXP_F02.getOffset", null, String.class);

        // Get SYSTEMBASE
        HashMap<String, String> m_gset_d = new HashMap<String, String>();
        m_gset_d = new HashMap<String, String>();
        m_gset_d.put("idSet", "SYSTEMBASE");
        m_gset_d.put("set_seq", "1");
        this.systemBase = queryDAO.executeForObject("SELECT.M_GSET_D", m_gset_d, String.class);
        
        // called by batch  process B
        if("E".equals(batch)){
	        id_login = message_info.getMessage("info.IDBatchLogin");
	        T_SET_BATCH tset = new T_SET_BATCH();
	        tset.setSTATUS("1");
	        tset.setBATCH_TYPE("SA");
	        tset.setID_LOGIN(id_login);
	        G_SET_P01 gset = new G_SET_P01(this.queryDAO, this.updateDAO);
	        batch_id = gset.G_SET_P01_GetIdBatch(tset).getBatch_id();
	        this.idBatch=batch_id;
	        
	        if (batch_id > 0) {
	            // Get Export Path from Global Setting
	            file_path = CommonUtils.toString(queryDAO.executeForObject(
	                            "SELECT.BSYS.E_EXP_F02.SUBCB.FILEPATH", null, String.class));
	
	            // Terminate Process if Global path not defined
	            if (file_path.equals("")) {
	                String message = message_info.getMessage("errors.ERR1SC070");
	                String ERR2SC034 = message_info.getMessage("info.ERR2SC034");
	                
	                // update batch_id by call g_set_p01.
	                T_SET_BATCH t_set = new T_SET_BATCH();
	                t_set.setBATCH_TYPE("SA");
	                t_set.setFILENAME(" ");
	                t_set.setID_BATCH(batch_id + "");
	                t_set.setSTATUS("3");
	                t_set.setMessage(message);
	                G_SET_P01 g_set = new G_SET_P01(this.queryDAO, this.updateDAO);
	                g_set.G_SET_P01_GetIdBatch(t_set);
	                Sub_Alert(batch_id + "", ERR2SC034, message);
	                return gResult;
	            }
	            // deleted by ticket 465
	            // Sub Delete Existing statement records of STMT_MONTH if any
	            // String DATE_FORMAT_MONTH = "yyyyMM";
	            // SimpleDateFormat sdf_month = new SimpleDateFormat(DATE_FORMAT_MONTH);
	            // String stmt_date_str_month = sdf_month.format(c1.getTime());
	            // HashMap<String, Object> mapInputInfo = new HashMap<String, Object>();
	            // mapInputInfo.put("date_str", stmt_date_str_month);
	            // subDel(mapInputInfo);
	            
	            try {
	            	List<Map<String, Object>> resultMaps = new ArrayList<Map<String,Object>>();
	            	HashMap<String, Object> param = new HashMap<String, Object>();
	            	if("BAC".equals(offset.trim())){
	            		resultMaps = queryDAO.executeForMapList("SELECT.BSYS.E_EXP_F02.OFFSET.SQL001", param);
	            	}else{
	            		resultMaps = queryDAO.executeForMapList("SELECT.BSYS.E_EXP_F02.OFFSET.SQL002", param);
	            	}
	            	
	            	Map<String, Object> result = null;
	            	String RID_CUST = "";
	            	String RBAC = "";
	            	for(int  i = 0;i<resultMaps.size();i++){
	            		result = resultMaps.get(i);
	            		RID_CUST = CommonUtils.toString(result.get("ID_CUST"));
	            		RBAC = CommonUtils.toString(result.get("ID_BILL_ACCOUNT"));
	            		param = new HashMap<String, Object>();
	            		param.put("idcust", RID_CUST);
	            		param.put("idbillaccount", RBAC);
	            		param.put("statementdate", stmt_date_str);
	            		
	            		String idStmt = queryDAO.executeForObject("SELECT.BSYS.E_EXP_F02.OFFSET.SQL003", param, String.class);
	            		
	            		procesStart(idStmt,param);
	            	}
	                // Generate alert
	                generateAlert(stmt_date_str);
	            } catch (Exception e) {
	                e.printStackTrace();
	                // when exception set failure
	                String batch_type = "SA";
	                String status = "3";
	                // update batch_id by call g_set_p01.
	                T_SET_BATCH t_set = new T_SET_BATCH();
	                t_set.setBATCH_TYPE(batch_type);
	                t_set.setFILENAME(" ");
	                t_set.setID_BATCH(batch_id + "");
	                t_set.setSTATUS(status);
	                t_set.setMessage(e.toString());
	                t_set.setID_LOGIN(id_login);
	                gset.G_SET_P01_GetIdBatch(t_set);
	                
	                String message = message_info.getMessage("info.ERR2SC036",stmt_date_str);
	                String ERR2SC034 = message_info.getMessage("info.ERR2SC034");
	                // call sub_alert(batch_id,Subject, message).
	                Sub_Alert(batch_id + "", ERR2SC034, message);
	            }finally{
					if (custIds.size() > 0 && billAccs.size() > 0) {
						
						String message="";
						for(int i = 0;i<custIds.size();i++){
							message = " is no successful to generate statement - error while generate statement.";
							if("BAC".equals(offset.trim())){
								message =  "Customer ID: " + custIds.get(i).trim()+", Billing Account: "+billAccs.get(i).trim()+message;
							}else{
								message = "Customer ID: " + custIds.get(i).trim() + message;
							}
			                String batch_type = "SA";
			                String status = "3";
							T_SET_BATCH t_set = new T_SET_BATCH();
			                t_set.setBATCH_TYPE(batch_type);
			                t_set.setFILENAME(" ");
			                t_set.setID_BATCH(batch_id + "");
			                t_set.setSTATUS(status);
			                t_set.setMessage(message);
			                t_set.setID_LOGIN(id_login);
			                gset.G_SET_P01_GetIdBatch(t_set);
					    }
					}
	            }
	        }
        }
        
        // called by B_BAC  process C
        if("B".equals(batch)){
        	ArrayList<String> messages = new ArrayList<String>();
        	String batchStatus = "1";
        	String message = "";
        	// Get Export Path from Global Setting
            file_path = CommonUtils.toString(queryDAO.executeForObject(
                            "SELECT.BSYS.E_EXP_F02.SUBCB.FILEPATH", null, String.class));

            // Terminate Process if Global path not defined
            if (file_path.equals("")) {
            	batchStatus = "3";
            	message = message_info.getMessage("errors.ERR1SC070");
            	output.setMsg(new String[]{message});
            	output.setBatchStatus(batchStatus);
            	output.setListStatementNo(listStatementNo);
                return gResult;
            }
            
            try{
            	List<Map<String, Object>> resultMaps = new ArrayList<Map<String,Object>>();
            	HashMap<String, Object> param = new HashMap<String, Object>();
            	param.put("idcust", id_cust);
            	param.put("idbillaccount", idBillAccount);
            	resultMaps = queryDAO.executeForMapList("SELECT.BSYS.E_EXP_F02.OFFSET.SQL001", param);
            	
            	Map<String, Object> result = null;
            	String RID_CUST = "";
            	String RBAC = "";
            	for(int i=0;i<resultMaps.size();i++){
            		result = resultMaps.get(i);
            		RID_CUST = CommonUtils.toString(result.get("ID_CUST"));
            		RBAC = CommonUtils.toString(result.get("ID_BILL_ACCOUNT"));
            		param = new HashMap<String, Object>();
            		param.put("idcust", RID_CUST);
            		param.put("idbillaccount", RBAC);
            		param.put("statementdate", stmt_date_str);
            		
            		String idStmt = queryDAO.executeForObject("SELECT.BSYS.E_EXP_F02.OFFSET.SQL003", param, String.class);
            		
            		procesStart(idStmt,param);
            	}
            }catch (Exception e) {
				e.printStackTrace();
				batchStatus = "3";
				messages.add(message_info.getMessage("info.ERR2SC036",stmt_date_str));
				messages.add(e.toString());
			}finally{
				if (custIds.size() > 0 && billAccs.size() > 0) {
					batchStatus = "3";
					
					for(int i = 0;i<custIds.size();i++){
						message = " is no successful to generate statement - error while generate statement.";
						if("BAC".equals(offset.trim())){
							message =  "Customer ID: " + custIds.get(i).trim()+", Billing Account: "+billAccs.get(i).trim()+message;
						}else{
							message = "Customer ID: " + custIds.get(i).trim() + message;
						}
						messages.add(message);
				    }
					output.setBatchStatus(batchStatus);
					output.setMsg(messages.toArray(new String[messages.size()]));
					output.setListStatementNo(listStatementNo);
					return gResult;
				}
			}
			
			if(listStatementNo.size()==0){
				output.setMsg(new String[]{"No Statement Generated."});
	        	output.setBatchStatus("3");
	        	output.setListStatementNo(listStatementNo);   
			}else{
	        	output.setMsg(messages.toArray(new String[messages.size()]));
	        	output.setBatchStatus(batchStatus);
	        	output.setListStatementNo(listStatementNo);
			}
        }
        
        // called by R_SOA  process D
        if("R".equals(batch)){
        	ArrayList<String> messages = new ArrayList<String>();
        	String batchStatus = "1";
        	String message = "";
        	// Get Export Path from Global Setting
            file_path = CommonUtils.toString(queryDAO.executeForObject(
                            "SELECT.BSYS.E_EXP_F02.SUBCB.FILEPATH", null, String.class));

            // Terminate Process if Global path not defined
            if (file_path.equals("")) {
            	batchStatus = "3";
            	message = message_info.getMessage("errors.ERR1SC070");
            	output.setMsg(new String[]{message});
            	output.setBatchStatus(batchStatus);
            	output.setListStatementNo(listStatementNo);
                return gResult;
            }
            try{  
	            HashMap<String, Object> param = new HashMap<String, Object>();
	            param.put("idStmts", id_stmts);
	            List<Map<String, Object>> resultMaps = queryDAO.executeForMapList("SELECT.BSYS.E_EXP_F02.OFFSET.SQL004", param);
            	
	            Map<String, Object> result = null;
            	String RID_CUST = "";
            	String RBAC = "";
	            for(int i = 0;i< resultMaps.size();i++){
	            	result = resultMaps.get(i);
            		RID_CUST = CommonUtils.toString(result.get("ID_CUST"));
            		RBAC = CommonUtils.toString(result.get("CUST_ACC_NO"));
            		param = new HashMap<String, Object>();
            		param.put("statementdate", stmt_date_str);
            		if("BAC".equals(offset.trim())){
                		param.put("idcust", RID_CUST);
                		param.put("idbillaccount", RBAC);
            		}else{
            			param.put("idcust", RID_CUST);
            		}
            		procesStart(CommonUtils.toString(result.get("ID_STMT")), param);
	            }
	            
	        }catch (Exception e) {
				e.printStackTrace();
				batchStatus = "3";
				messages.add(message_info.getMessage("info.ERR2SC036",stmt_date_str));
				messages.add(e.toString());
			}finally{
				if (custIds.size() > 0 && billAccs.size() > 0) {
					batchStatus = "3";
					
					for(int i = 0;i<custIds.size();i++){
						message = " is no successful to generate statement - error while generate statement.";
						if("BAC".equals(offset.trim())){
							message =  "Customer ID: " + custIds.get(i).trim()+", Billing Account: "+billAccs.get(i).trim()+message;
						}else{
							message = "Customer ID: " + custIds.get(i).trim() + message;
						}
						messages.add(message);
				    }
					output.setBatchStatus(batchStatus);
					output.setMsg(messages.toArray(new String[messages.size()]));
					output.setListStatementNo(listStatementNo);
					return gResult;
				}
			}
			if(listStatementNo.size()==0){
				output.setMsg(new String[]{"No Statement Generated."});
	        	output.setBatchStatus("3");
	        	output.setListStatementNo(listStatementNo); 
			}else{
	        	output.setMsg(messages.toArray( new String[messages.size()]));
	        	output.setBatchStatus(batchStatus);
	        	output.setListStatementNo(listStatementNo);   
			}
        }
        return gResult;
    }

    /**
     * to insert or update data in T_TRM
     * DATE_FORMAT = "ddMMyyyy";
     * @param stmt_date_str
     */
    private void procesStart(String idStmt,Map<String, Object> param){
    	
    	if(idStmt == null ||"".equals(idStmt.trim())){    		
    	}else{
    		updateDAO.execute("DELETE.BSYS.E_EXP_F02.OFFSET.SQL001", idStmt);
            
    		HashMap<String, Object> updateParam = new HashMap<String, Object>();
            updateParam.put("stmtTotal", 0);
            updateParam.put("idStmt", idStmt);
            updateDAO.execute("UPDATE.BSYS.E_EXP_F02_F_subupdate.stmtTotal",
           		 updateParam);
    	}
    	
        // Sub Check Bill Document
        this.chkBillDoc(param);

        // Sub Check No Outstanding
        this.chkNoOutstanding(param);
        
        // Sub Check CaseBook = 0 CaseBook
        this.chkCaseBook0(param);
        
        // Sub Check CaseBook <>0
        this.chkCaseBook1(param);
        
        // Sub Check CaseBook = 0 BIL
        this.chkCaseBook2(param);
        
        // refund cash book
        this.chkCaseBookRefund(param);
    }
    
    /**
     * cash book calculate for refund book
     * 
     *  stmt_date_str STMT DATE in ddMMyyyy format
     * @param idBillaccount and idCust and statement_date
     */
    private void chkCaseBookRefund(Map<String, Object> param) {
        List<HashMap<String, Object>> list_e_exp02output = queryDAO.executeForObjectList("SELECT.BSYS.E_EXP_F02.CHB.SQL004", param);
        loopResult4(list_e_exp02output, param.get("statementdate").toString());
    }
    
    /**
     * Loop T_CSB Records.
     * 
     * @param result T_CSB Records list
     * @param stmt_date_str STMT DATE in ddMMyyyy format
     */
    private void loopResult4(List<HashMap<String, Object>> result,String stmt_date_str){
        // Loop Billing Document Records
        if (result.size() > 0) {
            BigDecimal BALANCE_AMT;
            String ID_CUST="";
            String DATE_TRANS;
            String ID_REF;
            String STMT_CURRENCY;
            String bill_acc="";
            BigDecimal amount_due;
            BigDecimal item_act;
            String entry_type = " ";
            String type = "cashbook";
            String payment_info = "";
            String docType = "CB";
            String receiptNo = "";
            String REFERENCE2 = "";
            for (int i = 0; i < result.size(); i++) {
                try{
                    HashMap<String, Object> e_exp_subcb = result.get(i);
                    receiptNo=CommonUtils.toString(e_exp_subcb.get("RECEIPT_NO"));
                    // get balance_amt for $amount_due
                    BALANCE_AMT = new BigDecimal(CommonUtils.toString(e_exp_subcb.get("BALANCE_AMT")));
                    // set amount_due value
                    amount_due = BALANCE_AMT;
                    
                    // get sum amt_paid for $item_act
                    item_act = new BigDecimal(CommonUtils.toString(e_exp_subcb.get("AMT_PAID")));
                    
                    // for $id_cust parameter sub_insert function.
                    ID_CUST = CommonUtils.toString(e_exp_subcb.get("ID_CUST"));
    
                    // get Cur_code for parameter $stmt_currency - sub_insert function.
                    STMT_CURRENCY = CommonUtils.toString(e_exp_subcb.get("CUR_CODE"));
    
                    // get DATE_TRANS for parameter $doc_date - sub_insert function.
                    // Date Format "YYYY-MM-DD"
                    DATE_TRANS = CommonUtils.toString(e_exp_subcb.get("DATE_TRANS"));
                    // get $id_ref for sub_insert function.
                    ID_REF = CommonUtils.toString(e_exp_subcb.get("RECEIPT_NO"));
            
                    // get $bac_no
                    bill_acc = CommonUtils.toString(e_exp_subcb.get("ID_BILL_ACCOUNT"));
                    
                    // get Payment reference 2 200
                    REFERENCE2 = CommonUtils.toString(e_exp_subcb.get("REFERENCE2"));
                    payment_info = REFERENCE2.equals("")? "" : REFERENCE2 +";";
                    List<String> idrefs = queryDAO.executeForObjectList("SELECT.BSYS.E_EXP_F02.getIdRefByReceiptno", receiptNo);
                    for(int j =0;j<idrefs.size();j++){
                        if((payment_info+idrefs.get(j)).length()<=200){
                            if((payment_info + idrefs.get(j)).length() == 200){
                                payment_info = payment_info + idrefs.get(j);
                            }else{
                                payment_info = payment_info + idrefs.get(j) + ";";
                            }
                        }else{
                            break;
                        }
                    }
                    if (payment_info.endsWith(";")){
                        payment_info = payment_info.substring(0, payment_info.length()-1);
                    }
                    
                    sub_insert(type, entry_type, amount_due, payment_info, stmt_date_str,
                            ID_CUST, STMT_CURRENCY, "", DATE_TRANS, ID_REF, id_login,
                            bill_acc, item_act,docType);
                }catch (Exception e) {
                    e.printStackTrace();
                    custIds.add(ID_CUST);
                    billAccs.add(bill_acc);
                }
            }            
        }
    }
    /**
     * Check billing document in T_BIL_H
     * 
     * @param stmt_date_str STMT DATE in ddMMyyyy format
     */
    private void chkBillDoc(Map<String, Object> param) {
        List<HashMap<String, Object>> list_e_exp02output1 = 
            queryDAO.executeForObjectList("SELECT.BSYS.E_EXP_F02.CHKBILLDOC.SQL001", param);
        loopResult1(list_e_exp02output1, param.get("statementdate").toString());
    }
    
    /**
     * Check No Outstanding 
     * 
     * @param stmt_date_str STMT DATE in ddMMyyyy format
     */
    private void chkNoOutstanding(Map<String, Object> param) {
        List<HashMap<String, Object>> list_e_exp02outputA3 = 
            queryDAO.executeForObjectList("SELECT.BSYS.E_EXP_F02.CHKNOOUTSTANDING.SQL001", param);
        loopResult1(list_e_exp02outputA3, param.get("statementdate").toString());
    }
    
    /**
     * Loop T_BIL Records.
     * 
     * @param result T_BIL Records list
     * @param stmt_date_str STMT DATE in ddMMyyyy format
     */
    private void loopResult1(List<HashMap<String, Object>> result,String stmt_date_str){
        
    	// Loop Billing Document Records
    	if (result.size() > 0) {
            String BILL_TYPE;
            BigDecimal BILL_AMOUNT;
            BigDecimal PAID_AMOUNT;
            String CUST_PO;
            String ID_CUST="";
            String DATE_REQ;
            String ID_REF;
            String BILL_CURRENCY;
            String bill_acc="";
            BigDecimal item_act = BigDecimal.ZERO;
            BigDecimal amount_due = BigDecimal.ZERO;
            String entry_type = " ";
            String type = "";
            String payment_info="";
            String docType = "";
            
            for (int i = 0; i < result.size(); i++) {
            	try{
	                HashMap<String, Object> e_exp02map = (HashMap<String, Object>) result.get(i);
	                BILL_TYPE = CommonUtils.toString(e_exp02map.get("BILL_TYPE"));
	                ID_CUST = CommonUtils.toString(e_exp02map.get("ID_CUST"));
	                BILL_AMOUNT = new BigDecimal(e_exp02map.get("BILL_AMOUNT").toString());
	                PAID_AMOUNT = new BigDecimal(e_exp02map.get("PAID_AMOUNT").toString());
	                if (e_exp02map.get("CUST_PO") != null) {
	                    CUST_PO = CommonUtils.toString(e_exp02map.get("CUST_PO"));
	                } else {
	                    CUST_PO = " ";
	                }
	                
	                // Date Format "YYYY-MM-DD"
	                DATE_REQ = CommonUtils.toString(e_exp02map.get("DATE_REQ"));
	                ID_REF = CommonUtils.toString(e_exp02map.get("ID_REF"));
	                BILL_CURRENCY = CommonUtils.toString(e_exp02map.get("BILL_CURRENCY"));
	                bill_acc = CommonUtils.toString(e_exp02map.get("BILL_ACC"));
	                amount_due = BILL_AMOUNT.subtract(PAID_AMOUNT);
	                type = "billing";
	                docType = BILL_TYPE;
	            	if ("SG".equalsIgnoreCase(this.systemBase)) {
	            		if(BILL_TYPE.equalsIgnoreCase("CN")){
	            			entry_type = " ";
	            		}else{
	            			entry_type = CommonUtils.toString(e_exp02map.get("ENTRY_TYPE"));
	            		}
	            	}else{
	            		if(BILL_TYPE.equalsIgnoreCase("CN")){
	            			entry_type = "CreditMemo";
	            		}else{
	            			entry_type = "DebitMemo";
	            		}
	            	}
	            	if(BILL_TYPE.equalsIgnoreCase("CN")){
	                    // Display minus value for CN 
	                    amount_due = amount_due.negate();
	                    
	            		HashMap<String, String> parameter = new HashMap<String, String>();
	            		parameter.put("idRef", ID_REF);
	            		parameter.put("stmtDate", stmt_date_str);
	            		BigDecimal item_act_str = queryDAO.executeForObject("SELECT.BSYS.E_EXP_F02.LOOPRESULT1.SQL001", parameter, BigDecimal.class);
	            		item_act = item_act_str.negate();
	            	}else{
	            		 // $chb_amt_paid of sum(T_CSB_D.AMT_PAID)
	                    HashMap<String, String> sum_amt_pay_in = new HashMap<String, String>();
	                    sum_amt_pay_in.put("ID_REF", ID_REF);
	                    sum_amt_pay_in.put("STMT_DATE", stmt_date_str);
	                    BigDecimal chbAmtPaid = queryDAO.executeForObject("SELECT.BSYS.E_EXP_F02.LOOPRESULT1.SQL002", sum_amt_pay_in, BigDecimal.class);
	
	                    // $item_act
	                    BigDecimal trm_amt_paid =  queryDAO.executeForObject("SELECT.BSYS.E_EXP_F02.LOOPRESULT1.SQL003", sum_amt_pay_in, BigDecimal.class);
                        item_act = BILL_AMOUNT.subtract(chbAmtPaid).subtract(trm_amt_paid);
	            	}
	            	
	                sub_insert(type, entry_type, amount_due, payment_info,
	                        stmt_date_str, ID_CUST, BILL_CURRENCY,
	                        CUST_PO, DATE_REQ, ID_REF, id_login,
	                        bill_acc, item_act,docType);
            	}catch (Exception e) {
            		e.printStackTrace();
            		custIds.add(ID_CUST);
            		billAccs.add(bill_acc);
				}
            }
        }
    }
   
    /**
     * Check Case Book = 0 Case Book
     * 
     * @param stmt_date_str STMT DATE in ddMMyyyy format
     */
    private void chkCaseBook0(Map<String, Object> param) {
        List<HashMap<String, Object>> list_e_exp02output = queryDAO.executeForObjectList("SELECT.BSYS.E_EXP_F02.CHB.SQL003", param);
        loopResult2(list_e_exp02output, param.get("statementdate").toString());
    }
    
    /**
     * Check Case Book <> 0
     * 
     * @param stmt_date_str STMT DATE in ddMMyyyy format
     */
    private void chkCaseBook1(Map<String, Object> param) {
        List<HashMap<String, Object>> list_e_exp02output = queryDAO.executeForObjectList("SELECT.BSYS.E_EXP_F02.CHB.SQL001", param);
        loopResult2(list_e_exp02output, param.get("statementdate").toString());
    }
    
    /**
     * Loop T_CSB Records.
     * 
     * @param result T_CSB Records list
     * @param stmt_date_str STMT DATE in ddMMyyyy format
     */
    private void loopResult2(List<HashMap<String, Object>> result,String stmt_date_str){
        // Loop Billing Document Records
        if (result.size() > 0) {
            BigDecimal BALANCE_AMT;
            String ID_CUST="";
            String DATE_TRANS;
            String ID_REF;
            String STMT_CURRENCY;
            String bill_acc="";
            BigDecimal amount_due;
            BigDecimal item_act;
            String entry_type = " ";
            String type = "cashbook";
            String payment_info = "";
            String docType = "CB";
            String receiptNo = "";
            String REFERENCE2 = "";
            for (int i = 0; i < result.size(); i++) {
                try{
	            	HashMap<String, Object> e_exp_subcb = result.get(i);
	                receiptNo=CommonUtils.toString(e_exp_subcb.get("RECEIPT_NO"));
	                // get balance_amt for $amount_due
	                BALANCE_AMT = new BigDecimal(CommonUtils.toString(e_exp_subcb.get("BALANCE_AMT")));
	                // set amount_due value
	                amount_due = BALANCE_AMT.negate();
	                
	                // get sum amt_paid for $item_act
	                item_act = new BigDecimal(CommonUtils.toString(e_exp_subcb.get("AMT_PAID"))).negate();
	                
	                // for $id_cust parameter sub_insert function.
	                ID_CUST = CommonUtils.toString(e_exp_subcb.get("ID_CUST"));
	
	                // get Cur_code for parameter $stmt_currency - sub_insert function.
	                STMT_CURRENCY = CommonUtils.toString(e_exp_subcb.get("CUR_CODE"));
	
	                // get DATE_TRANS for parameter $doc_date - sub_insert function.
	                // Date Format "YYYY-MM-DD"
	                DATE_TRANS = CommonUtils.toString(e_exp_subcb.get("DATE_TRANS"));
	                // get $id_ref for sub_insert function.
	                ID_REF = CommonUtils.toString(e_exp_subcb.get("RECEIPT_NO"));
	        
	                // get $bac_no
	                bill_acc = CommonUtils.toString(e_exp_subcb.get("ID_BILL_ACCOUNT"));
	                
	                // get Payment reference 2 200
	                REFERENCE2 = CommonUtils.toString(e_exp_subcb.get("REFERENCE2"));
	                payment_info = REFERENCE2.equals("")? "" : REFERENCE2 +";";
	                List<String> idrefs = queryDAO.executeForObjectList("SELECT.BSYS.E_EXP_F02.getIdRefByReceiptno", receiptNo);
	                for(int j =0;j<idrefs.size();j++){
	                    if((payment_info+idrefs.get(j)).length()<=200){
	                        if((payment_info + idrefs.get(j)).length() == 200){
	                            payment_info = payment_info + idrefs.get(j);
	                        }else{
	                            payment_info = payment_info + idrefs.get(j) + ";";
	                        }
	                    }else{
	                        break;
	                    }
	                }
	                if (payment_info.endsWith(";")){
	                    payment_info = payment_info.substring(0, payment_info.length()-1);
	                }
	                
	                sub_insert(type, entry_type, amount_due, payment_info, stmt_date_str,
	                        ID_CUST, STMT_CURRENCY, "", DATE_TRANS, ID_REF, id_login,
	                        bill_acc, item_act,docType);
                }catch (Exception e) {
                	e.printStackTrace();
                    custIds.add(ID_CUST);
                    billAccs.add(bill_acc);
                }
            }            
        }
    }
    
    /**
     * Check Case Book = 0 BIL
     * 
     * @param stmt_date_str STMT DATE in ddMMyyyy format
     */
    private void chkCaseBook2(Map<String, Object> param) {
        List<HashMap<String, Object>> list_e_exp02output = queryDAO.executeForObjectList("SELECT.BSYS.E_EXP_F02.CHB.SQL002", param);
        loopResult3(list_e_exp02output, param.get("statementdate").toString());
    }
    
    /**
     * Loop T_CSB/T_BIL Records.
     * 
     * @param result T_BIL Records list
     * @param stmt_date_str STMT DATE in ddMMyyyy format
     */
    private void loopResult3(List<HashMap<String, Object>> result,String stmt_date_str){
    	// Loop Billing Document Records
        if (result.size() > 0) {
            BigDecimal BALANCE_AMT;
            String ID_CUST="";
            String DATE_TRANS;
            String ID_REF;
            String STMT_CURRENCY;
            String bill_acc="";
            BigDecimal amount_due;
            BigDecimal item_act;
            String entry_type = " ";
            String type = "cashbook";
            String payment_info = "";
            String docType = "CB";
            String REFERENCE2 = "";
            String receiptNo = "";
            for (int i = 0; i < result.size(); i++) {
            	try{
	                HashMap<String, Object> e_exp_subcb = result.get(i);
	                // get balance_amt for $amount_due
	                BALANCE_AMT = new BigDecimal(CommonUtils.toString(e_exp_subcb.get("BALANCE_AMT")));
	                // set amount_due value
	                amount_due = BALANCE_AMT.negate();
	                
	                // get sum amt_paid for $item_act
	                item_act = new BigDecimal(CommonUtils.toString(e_exp_subcb.get("AMT_PAID"))).negate();
	                
	                // for $id_cust parameter sub_insert function.
	                ID_CUST = CommonUtils.toString(e_exp_subcb.get("ID_CUST"));
	
	                // get Cur_code for parameter $stmt_currency - sub_insert function.
	                STMT_CURRENCY = CommonUtils.toString(e_exp_subcb.get("CUR_CODE"));
	
	                // get DATE_TRANS for parameter $doc_date - sub_insert function.
	                // Date Format "YYYY-MM-DD"
	                DATE_TRANS = CommonUtils.toString(e_exp_subcb.get("DATE_TRANS"));
	                // get $id_ref for sub_insert function.
	                ID_REF = CommonUtils.toString(e_exp_subcb.get("RECEIPT_NO"));
	  
	                // get $bac_no
	                bill_acc = CommonUtils.toString(e_exp_subcb.get("ID_BILL_ACCOUNT"));
	                
	                receiptNo = CommonUtils.toString(e_exp_subcb.get("RECEIPT_NO"));
	
	                // get Payment reference 2 200
	                REFERENCE2 = CommonUtils.toString(e_exp_subcb.get("REFERENCE2"));
	                payment_info = REFERENCE2.equals("")? "": REFERENCE2+";";
	                List<String> idrefs = queryDAO.executeForObjectList("SELECT.BSYS.E_EXP_F02.getIdRefByReceiptno", receiptNo);
	                for(int j =0;j<idrefs.size();j++){
	                	if((payment_info + idrefs.get(j)).length() <= 200){
	                		if((payment_info + idrefs.get(j)).length() == 200){
	                			payment_info = payment_info + idrefs.get(j);
	                		}else{
	                			payment_info = payment_info + idrefs.get(j) + ";";
	                		}
	                	}else{
	                		break;
	                	}
	                }
	                if (payment_info.endsWith(";")){
	                    payment_info = payment_info.substring(0, payment_info.length()-1);
	                }
	
	                sub_insert(type, entry_type, amount_due, payment_info, stmt_date_str,
	                        ID_CUST, STMT_CURRENCY, "", DATE_TRANS, ID_REF, id_login,
	                        bill_acc, item_act,docType);
            	}catch (Exception e) {
            		e.printStackTrace();
                    custIds.add(ID_CUST);
                    billAccs.add(bill_acc);
				}
            }            
        }
    }
    
    /**
     * Insert statement of account into T_AR_STMT_H and T_AR_STMT_D.
     * 
     * @param type billing/case book
     * @param entry_type
     * @param amount_due
     * @param payment_info PAYMENT_INFO
     * @param stmt_date Statement Date.
     * @param id_cust_str ID_CUST
     * @param stm_currency_str Billing Currency
     * @param cust_po_str Customer PO.
     * @param doc_date_str Billing document date.(Cash Book DATE_TRANS)
     * @param id_ref_str Billing document reference ID. (include Receipt No.)
     * @param id_login
     * @param acc_no CUST_ACC_NO
     * @param item_act ITEM_ACTIVITY
     */
    public void sub_insert(String type, String entry_type, BigDecimal amount_due,
            String payment_info, String stmt_date, String id_cust_str,
            String stm_currency_str, String cust_po_str, String doc_date_str,
            String id_ref_str, String id_login, String acc_no, BigDecimal item_act,String docType){
        DecimalFormat formatter = new DecimalFormat("0.00");
        formatter.setRoundingMode(RoundingMode.HALF_UP);
        
        HashMap<String, String> id_cust_map = new HashMap<String, String>();
        id_cust_map.put("id_cust", id_cust_str);
        String id_cust = id_cust_str;
        String stmt_currency = stm_currency_str;
        String cust_po = cust_po_str;
        String doc_date = doc_date_str;
        String id_ref = id_ref_str;
        
        Map<String, Object> t_bac_h = queryDAO.executeForMap("SELECT.BSYS.E_EXP_F02_subinsert.T_BAC_H", acc_no);
        
        String adr_type = CommonUtils.toString(t_bac_h.get("CUST_ADR_TYPE"));
        String contact_type = CommonUtils.toString(t_bac_h.get("CONTACT_TYPE"));
        
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("adr_type", adr_type);
        param.put("contact_type", contact_type);
        param.put("id_cust", id_cust);
        
        Map<String, Object> cust_info = queryDAO.executeForMap("SELECT.BSYS.E_EXP_F02_subinsert.custInfo", param); 
        
        String custType = CommonUtils.toString(cust_info.get("CUSTOMER_TYPE"));
        String custName = CommonUtils.toString(cust_info.get("CUST_NAME"));
        String salunation = CommonUtils.toString(cust_info.get("SALUTATION"));
        
        String telNo = "";
        String faxNo = "";
        if("CORP".equals(custType)){
        	telNo = CommonUtils.toString(cust_info.get("CO_TEL_NO"));
        	faxNo = CommonUtils.toString(cust_info.get("CO_FAX_NO"));
        }else{
        	telNo = CommonUtils.toString(cust_info.get("HOME_TEL_NO"));
        	faxNo = CommonUtils.toString(cust_info.get("HOME_FAX_NO"));
        }
        String country = CommonUtils.getCountry(CommonUtils.toString(cust_info.get("COUNTRY")));
        String address1 = CommonUtils.toString(cust_info.get("ADR_LINE1"));
        String address2 = CommonUtils.toString(cust_info.get("ADR_LINE2"));
        String address3 = CommonUtils.toString(cust_info.get("ADR_LINE3"));
        String address4 = CommonUtils.toString(cust_info.get("ZIP_CODE")) + " " + country;
        address4 = address4.trim();
        BigDecimal statementTotal = BigDecimal.ZERO;
        
        HashMap<String, String> mapsql003in = new HashMap<String, String>();
        mapsql003in.put("id_cust", id_cust);
        mapsql003in.put("stmt_date", stmt_date);
        //mapsql003in.put("stmt_currency", stmt_currency);
        mapsql003in.put("cust_acc_no", acc_no);

        List<HashMap<String, Object>> id_stmt_e_list = queryDAO
                .executeForObjectList("SELECT.BSYS.E_EXP_F02_Sub.SQL003", mapsql003in);
        if (id_stmt_e_list.size() > 0) {
            for (int i = 0; i < id_stmt_e_list.size(); i++) {
                String id_stmt_sub = CommonUtils.toString(id_stmt_e_list.get(i).get("ID_STMT"));
            	// if idStmt not null then update T_AR_STMT_H and insert into T_AR_STMT_D
            	HashMap<String, Object> updateParam = new HashMap<String, Object>();
            	updateParam.put("id_stmt", id_stmt_sub);
            	updateParam.put("stmt_date", stmt_date);
                updateParam.put("stmt_currency", stmt_currency);
    			// CUST_NAME = CustName
            	updateParam.put("custName", custName);
    			// ADDRESS1 = Address1
            	updateParam.put("address1", address1);
    			// ADDRESS2 = Address2
            	updateParam.put("address2", address2);
    			// ADDRESS3 = Address3
            	updateParam.put("address3", address3);
    			// ADDRESS4 = Address4
            	updateParam.put("address4", address4);
    			// ZIP_CODE = Zipcode
            	updateParam.put("zipcode", CommonUtils.toString(cust_info.get("ZIP_CODE")));
    			// COUNTRY = Country
            	updateParam.put("country", country);
    			// TEL_NO = Telno
            	updateParam.put("telNo", telNo);
    			// FAX_NO = Faxno
                updateParam.put("faxNo", faxNo);
    			// CONTACT_NAME = Contactname
                updateParam.put("contactName", CommonUtils.toString(cust_info.get("CONTACT_NAME")));
    			// CONTACT_EMAIL = Email
                updateParam.put("email", CommonUtils.toString(cust_info.get("EMAIL")));
    			// SALUTATION = Salutation
                updateParam.put("salutation", salunation);
    			// ID_LOGIN = LoginUserID
                updateParam.put("id_login", id_login);
                updateDAO.execute("UPDATE.BSYS.E_EXP_F02.SUBINSERT.SQL001",updateParam); 
                if(!listStatementNo.contains(id_stmt_sub)){
                	listStatementNo.add(id_stmt_sub);
                }
                HashMap<String, String> f_stmt_in_for_list_f_sub = new HashMap<String, String>();
                f_stmt_in_for_list_f_sub.put("id_stmt", id_stmt_sub);
                f_stmt_in_for_list_f_sub.put("id_ref", id_ref);
               
                List<HashMap<String, String>> list_f_sub = queryDAO
                        .executeForObjectList("SELECT.BSYS.E_EXP_F02_SubINSERT.SQL004", f_stmt_in_for_list_f_sub);

                if (list_f_sub.size() <= 0) {
                    HashMap<String, String> f_insert_for_subinsertin = new HashMap<String, String>();
                    f_insert_for_subinsertin.put("id_stmt", id_stmt_sub);
                    f_insert_for_subinsertin.put("id_ref", id_ref);
                    f_insert_for_subinsertin.put("entry_type", entry_type);
                    f_insert_for_subinsertin.put("amount", amount_due + "");
                    if (cust_po == null) {
                        cust_po = " ";
                    }
                    f_insert_for_subinsertin.put("cust_po", cust_po);
                    f_insert_for_subinsertin.put("payment_info", payment_info);
                    f_insert_for_subinsertin.put("doc_date", doc_date);
                    f_insert_for_subinsertin.put("id_login", id_login);
                    f_insert_for_subinsertin.put("item_act", item_act + "");
                    f_insert_for_subinsertin.put("docType", docType);
                    updateDAO.execute("INSERT.BSYS.E_EXP_F02_F_SubINSERT.SQL001", f_insert_for_subinsertin);
                    
	                // sum($amount_due)
                    BigDecimal stmtTotal = BigDecimal.ZERO;
                    if (!CommonUtils.isEmpty(id_stmt_e_list.get(i).get("STMT_TOTAL"))){
                        stmtTotal = new BigDecimal(CommonUtils.toString(id_stmt_e_list.get(i).get("STMT_TOTAL")));
                    }
                    stmtTotal = stmtTotal.add(new BigDecimal(amount_due + ""));
	                updateParam = new HashMap<String, Object>();
	                updateParam.put("stmtTotal", formatter.format(stmtTotal));
	                updateParam.put("idStmt", id_stmt_sub);
	                updateDAO.execute("UPDATE.BSYS.E_EXP_F02_F_subupdate.stmtTotal",updateParam); 
                }
            }
        } else {
        	// if idStmt is null then just insert into T_AR_STMT_H and T_AR_STMT_D
        	statementTotal = amount_due;
            HashMap<String, String> e_insert_for_subinsert_in = new HashMap<String, String>();
           
            G_CDM_P01 gcdmp01 = new G_CDM_P01(queryDAO, updateDAO, id_login);
            gcdmp01.setExecuteMonth(curStmtMonth);
            String id_stmt = gcdmp01.getGenerateCode("SOANO", id_login);
            
            e_insert_for_subinsert_in.put("id_stmt", id_stmt);
            e_insert_for_subinsert_in.put("id_cust", id_cust);
            e_insert_for_subinsert_in.put("stmt_date", stmt_date);
            e_insert_for_subinsert_in.put("stmt_currency", stmt_currency);
            e_insert_for_subinsert_in.put("cust_acc_num", acc_no);
            e_insert_for_subinsert_in.put("id_login", id_login);
            // add for 445
            e_insert_for_subinsert_in.put("custName", custName);
            e_insert_for_subinsert_in.put("address1", address1);
            e_insert_for_subinsert_in.put("address2", address2);
            e_insert_for_subinsert_in.put("address3", address3);
            e_insert_for_subinsert_in.put("address4", address4);
            e_insert_for_subinsert_in.put("country", country);
            e_insert_for_subinsert_in.put("zipCode", CommonUtils.toString(cust_info.get("ZIP_CODE")));
            e_insert_for_subinsert_in.put("telNo", telNo);
            e_insert_for_subinsert_in.put("faxNo", faxNo);
            e_insert_for_subinsert_in.put("contactName", CommonUtils.toString(cust_info.get("CONTACT_NAME")));
            e_insert_for_subinsert_in.put("email", CommonUtils.toString(cust_info.get("EMAIL")));
            e_insert_for_subinsert_in.put("salutation", salunation);
            e_insert_for_subinsert_in.put("statementTotal", formatter.format(statementTotal));
            
            updateDAO.execute("INSERT.BSYS.E_EXP_F02_F_SubINSERT.SQL002", e_insert_for_subinsert_in);
            if(!listStatementNo.contains(id_stmt)){
            	listStatementNo.add(id_stmt);
            }
            String id_stmt_max = id_stmt;
            HashMap<String, String> f_insert_for_subinsertin = new HashMap<String, String>();
            f_insert_for_subinsertin.put("id_stmt", id_stmt_max);
            f_insert_for_subinsertin.put("id_ref", id_ref);
            f_insert_for_subinsertin.put("entry_type", entry_type);
            f_insert_for_subinsertin.put("amount", amount_due + " ");
            if (cust_po == null) {
                cust_po = " ";
            }
            f_insert_for_subinsertin.put("cust_po", cust_po);
            f_insert_for_subinsertin.put("payment_info", payment_info);
            f_insert_for_subinsertin.put("doc_date", doc_date);
            f_insert_for_subinsertin.put("id_login", id_login);
            f_insert_for_subinsertin.put("item_act", item_act + "");
            f_insert_for_subinsertin.put("docType", docType);
            updateDAO.execute("INSERT.BSYS.E_EXP_F02_F_SubINSERT.SQL001", f_insert_for_subinsertin);
        }
    }

    /**
     * Generate PDF 
     * 
     * @param stmt_date STMT DATE in ddMMyyyy format
     */
    public void generateAlert (String stmt_date) {
    	GlobalMessageResources message_info = GlobalMessageResources.getInstance();
    	String ERR2SC035 = "";
    	String status = "2";
    	String batch_type = "SA";
    	String ERR2SC034 = message_info.getMessage("info.ERR2SC034");
    	String message = "No Statement to be generated";
    	
    	List<HashMap<String, Object>> header_stmt_no_list = new ArrayList<HashMap<String, Object>>();
    	
    	header_stmt_no_list = queryDAO.executeForObjectList(
                "SELECT.BSYS.E_EXP_F02.HEADER.SQL001", stmt_date);
    	
    	if(header_stmt_no_list.size()>0){
    		 // if export _ for all recore
            ERR2SC035 = message_info.getMessage("info.ERR2SC035", stmt_date);
            batch_type = "SA";
            status = "2";
            if(custIds.size()>0){
            	ERR2SC035 = message_info.getMessage("info.ERR2SC036",stmt_date);
            }else{
	            // update batch_id by call g_set_p01.
	            T_SET_BATCH t_set = new T_SET_BATCH();
	            t_set.setBATCH_TYPE(batch_type);
	            t_set.setFILENAME(" ");
	            t_set.setID_BATCH(batch_id + "");
	            t_set.setSTATUS(status);
	            t_set.setMessage(ERR2SC035);
	            t_set.setID_LOGIN(id_login);
	            G_SET_P01 gset = new G_SET_P01(this.queryDAO, this.updateDAO);
	            gset.G_SET_P01_GetIdBatch(t_set);
            }
            // call sub_alert(batch_id,Subject, message).
            Sub_Alert(batch_id + "", ERR2SC034, ERR2SC035);
    	}else{
            // update batch_id by call g_set_p01.
            T_SET_BATCH t_set = new T_SET_BATCH();
            t_set.setBATCH_TYPE(batch_type);
            t_set.setFILENAME(" ");
            t_set.setID_BATCH(batch_id + "");
            t_set.setSTATUS(status);
            t_set.setMessage(message);
            G_SET_P01 gset = new G_SET_P01(this.queryDAO, this.updateDAO);
            gset.G_SET_P01_GetIdBatch(t_set);
            
            // call sub_alert(batch_id,Subject, message).
            Sub_Alert(batch_id + "", ERR2SC034, message);
    	}
    }

    /**
     * Delete existing AR records by month_year
     * 
     * @param e_expf02input1 format is ddMMyyyy
     */
    // deleted by ticket 465
//    private void subDel(HashMap<String, Object> e_expf02input1){
//        //Delete T_AR_STMT_D
//        updateDAO.execute("DELETE.BSYS.E_EXP_F02.SQL001", e_expf02input1);
//        //Delete T_AR_STMT_H
//        updateDAO.execute("DELETE.BSYS.E_EXP_F02.SQL002", e_expf02input1);
//    }
    
    private void Sub_Alert(String Batch_id, String Subject, String MSG) {
        /**
         * get list user to sendmail(up to 5)
         */
        List<AlertUserDto> alertUsers = queryDAO.executeForObjectList(
                "SELECT.BSYS.SQL053", "SA");
        G_ALT_P06 sent_mail = new G_ALT_P06(queryDAO, updateDAO);
        G_ALT_P06Input inp = new G_ALT_P06Input();
        inp.setBatchId(Batch_id);
        inp.setMsg(MSG);
        inp.setSubject(Subject);
        inp.setListAlertUser(alertUsers);
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user(id_login);
        sent_mail.execute(inp, uvo);
    }
}
