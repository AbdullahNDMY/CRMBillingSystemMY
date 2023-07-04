/**
 * @(#)E_EXP_F01.java
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import jp.terasoluna.fw.web.codelist.MappedCodeListLoader;
import jp.terasoluna.fw.web.struts.action.GlobalMessageResources;
import nttdm.bsys.common.bean.E_EXP_F01_SQL1_Bean;
import nttdm.bsys.common.bean.E_EXP_F01_SQL2_Bean;
import nttdm.bsys.common.bean.T_SET_BATCH;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.util.ApplicationContextProvider;

import org.springframework.context.ApplicationContext;
import org.springframework.util.CollectionUtils;

/**
 * PeopleSoft AR Report
 * 
 * @author lixinj
 */
public class E_EXP_F01 {
    /**
     * batch_id
     */
    private int batch_id = 0;

    /**
     * 
     */
    private String batch_type = "AR";

    /**
     * 
     */
    //private String tax_code = "SR";
    
    /**
     * status
     */
    private String status = "1";

    /**
     * message
     */
    private String message = " ";

    /**
     * filename
     */
    private String filename = " ";

    /**
     * alert_msg
     */
    private String alert_msg = " ";

    /**
     * id_login
     */
    private String id_login;

    /**
     * esetRundate
     */
    private Date esetRundate;

    /**
     * queryDAO
     */
    private QueryDAO queryDAO;

    /**
     * uvo
     */
    private BillingSystemUserValueObject uvo;
    
    /**
     * #239 - missing one cent wcbeh@20170405
     */    
    private BigDecimal DiffSumItemAmt;
    private BigDecimal DiffSumGstAmt;
    private BigDecimal DiffTotalHeaderAmt;
    private BigDecimal DiffAllSumDiffAmt;
    private BigDecimal SumDiffItemAmt;
    private BigDecimal SumDiffGstAmt;
    private BigDecimal SumDiffHDAmt;
    private String AdjHeader;
	private String AdjItem;
	private String AdjGst;
	private BigDecimal AdjHeaderAmt;
	private BigDecimal AdjItemAmt;
	private BigDecimal AjdGstAmt;
	private String firstService = "";
	private String SubAdj;
	private String SubAdjHeader;
	private String SubAdjHeadernGst;
	private String SubAdjGst;
	private String SubAdjItem;
	private String SubAdjItemNGst;
	private String firstSub;
	//private BigDecimal DocAmt;
	
    /**
     * error message id
     */
    private String ERR1BT012;
    // private String ERR2SC031;
    private String ERR2SC032;
    private String ERR2SC033;
    private String ERR2SC042;
    private String ERR1SC070;

    /**
     * originCodes
     */
    private Map<String, Object> originCodes = null;


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
     * <div>Get queryDAO</div>
     * 
     * @return queryDAO
     */
    public QueryDAO getQueryDAO() {
        return queryDAO;
    }

    /**
     * <div>setQueryDAO</div>
     */
    public void setQueryDAO(QueryDAO queryDAO) {
        this.queryDAO = queryDAO;
    }

    /**
     * <div>getUpdateDAO</div>
     * 
     * @return updateDAO
     */
    public UpdateDAO getUpdateDAO() {
        return updateDAO;
    }

    /**
     * <div>setUpdateDAO</div>
     * 
     * @param updateDAO
     */
    public void setUpdateDAO(UpdateDAO updateDAO) {
        this.updateDAO = updateDAO;
    }

    /**
     * updateDAO
     */
    private UpdateDAO updateDAO;

    /**
     * @param esetRundate
     *            the esetRundate to set
     */
    public void setEsetRundate(Date esetRundate) {

        this.esetRundate = esetRundate;
    }

    /**
     * @return the esetRundate
     */
    public Date getEsetRundate() {

        return esetRundate;
    }

    /**
     * @param uvo
     *            the uvo to set
     */
    public void setUvo(BillingSystemUserValueObject uvo) {

        this.uvo = uvo;
    }

    /**
     * @return the uvo
     */
    public BillingSystemUserValueObject getUvo() {

        return uvo;
    }

    /**
     * PeopleSoft AR Report
     */
    public void e_exp_execute() {
        GlobalMessageResources message_info = GlobalMessageResources.getInstance();

        batch_id = 0;
        batch_type = "AR";
        status = "1";
        id_login = message_info.getMessage("info.IDBatchLogin");
        T_SET_BATCH tset = new T_SET_BATCH();
        tset.setBATCH_TYPE(batch_type);
        tset.setID_BATCH(batch_id + "");
        tset.setFILENAME(filename);
        tset.setMessage(message);
        tset.setID_LOGIN(id_login);
        tset.setSTATUS(status);
        tset.setMonthyear(new SimpleDateFormat("MM/yyyy").format(esetRundate));
        G_SET_P01 gset = new G_SET_P01(this.queryDAO, this.updateDAO);
        String DATE_FORMAT = "yyyyMM";
        String NOW_FORMAT = "yyyyMMddHHmmss";
        Date now = new Date();
        SimpleDateFormat nowFormat = new SimpleDateFormat(NOW_FORMAT);
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        // Calendar c1 = Calendar.getInstance();
        String date_STR = sdf.format(esetRundate);
        // get batch_id to process.
        batch_id = gset.G_SET_P01_GetIdBatch(tset).getBatch_id();
        this.idBatch=batch_id;
        ERR2SC033 = message_info.getMessage("info.ERR2SC033", date_STR);

        if (batch_id >= 0) {
        	/*#436: [B2-2][REQ003]NewTaxCode Start*/
            String export_path = "", defCur = "", accRev = "", advAcc = "", busUnit = "", revAmtCalSet = "",sOneMonthAmount = "";
			String notZeroTaxRate[] = null, zeroTaxRate[] = null;
			/*#436: [B2-2][REQ003]NewTaxCode End*/
            int projectLen = 0;
            List<Map<String, Object>> listValue = queryDAO.executeForMapList("SELECT.BSYS.E_EXP_F01HV.SQL001", null);
            List<Map<String, Object>> projectValue = queryDAO.executeForMapList("SELECT.BSYS.E_EXP_F01HV.SQL002", null);
            if (listValue != null && listValue.size() > 0) {
                HashMap<String, Object> tempMap;
                String key, value;
                for (int i = 0; i < listValue.size(); ++i) {
                    tempMap = (HashMap<String, Object>) listValue.get(i);
                    key = (String) tempMap.get("ID_SET");
                    value = (String) tempMap.get("SET_VALUE");
                    if ("BATCH_E_EXP_F01".equals(key)) {
                        export_path = value;
                    } else if ("BIL_DEBTER_ACC".equals(key)) {
                        accRev = value;
                    } else if ("DEF_CURRENCY".equals(key)) {
                        defCur = value;
                    } else if ("BIL_ADVANCE_ACC".equals(key)) {
                        advAcc = value;
                    } else if ("REV_AMT_CAL".equals(key)) {
                    	revAmtCalSet = value;
                    }
                }
            }
            if (projectValue != null && projectValue.size() > 0) {
                HashMap<String, Object> tempMap;
                String key;
                for (int i = 0; i < projectValue.size(); ++i) {
                    tempMap = (HashMap<String, Object>) projectValue.get(i);
                    key = (String) tempMap.get("SET_SEQ");
                    if ("1".equals(key)) {
                        projectLen = Integer.valueOf(CommonUtils.toString(tempMap.get("SET_VALUE")));
                    } else if ("2".equals(key)) {
                        busUnit = (String) tempMap.get("SET_VALUE");
                    }  else if ("3".equals(key)) {
						notZeroTaxRate = (CommonUtils.toString(tempMap.get("SET_VALUE"))).split(",");
					} else if ("4".equals(key)) {
						zeroTaxRate = (CommonUtils.toString(tempMap.get("SET_VALUE"))).split(",");
					} else if ("5".equals(key)) {
						sOneMonthAmount = CommonUtils.toString(tempMap.get("SET_VALUE"));
					}
                }
            }
            if (!CommonUtils.isEmpty(export_path)) {
                if (new File(export_path).exists()) {
                    String CSVFilename = "";
                    String CSVFilenameNotIncludePath = "";
                    ERR2SC042 = message_info.getMessage("info.ERR2SC042", date_STR);
                    StringBuffer sBFileName = new StringBuffer(export_path).append("/PeopleSoft_Billing_")
                            .append(date_STR).append("_").append(nowFormat.format(now)).append("_").append(batch_id)
                            .append(".txt");
                    StringBuffer sBFileNameNotIncludePath = new StringBuffer().append("/PeopleSoft_Billing_")
                            .append(date_STR).append("_").append(nowFormat.format(now)).append("_").append(batch_id)
                            .append(".txt");
                    CSVFilename = sBFileName.toString();
                    CSVFilenameNotIncludePath = sBFileNameNotIncludePath.toString();

                    HashMap<String, String> sql2_input = new HashMap<String, String>();

                    List<E_EXP_F01_SQL1_Bean> listheader = queryDAO.executeForObjectList(
                            "SELECT.BSYS.E_EXP_F01.SQL002", date_STR);
                    Calendar closingDate = Calendar.getInstance();
                    closingDate.setTime(esetRundate);
                    int last = closingDate.getActualMaximum(Calendar.DAY_OF_MONTH);
                    closingDate.set(Calendar.DAY_OF_MONTH, last);
                    /*String A_BusUnit = busUnit;
                    String B_Origin;
                    String C_CustomerId;
                    String E_Entry_Type;
                    String F_Reason = "SALS1";
                    String G_Revalue = "N";
                    String H_InvoiceDate;
                    String I_DueDate;
                    String M_Salesperson;
                    String N_CreditAnalyst;
                    String O_Collector;
                    String P_PONbr;
                    String X_AffiliateNTT;*/
                    if (listheader != null && listheader.size() > 0) {
                        try {
                            FileWriter fw = new FileWriter(CSVFilename);
                            /*Date billFrom, billTo;
                            Calendar caFrom = Calendar.getInstance();
                            Calendar caTo = Calendar.getInstance();
                            String space = " ";
                            HashMap<String, String> param = new HashMap<String, String>();*/
                            for (int i = 0; i < listheader.size(); i++) {
                                E_EXP_F01_SQL1_Bean header = new E_EXP_F01_SQL1_Bean();
                                header = listheader.get(i);
                                //#239 >> Missing One Cent wcbeh@20170404
                                int calCase = this.getQRSCase(header.getBILL_CURRENCY(), header.getExport_cur(), header.getIS_DISPLAY_EXP_AMT(), defCur);
                                
                                BigDecimal SumItemAmt1 = new BigDecimal("0.00");
                                BigDecimal SumItemAmt2 = new BigDecimal("0.00");
                                BigDecimal SumGstAmt1 = new BigDecimal("0.00");
                                BigDecimal SumGstAmt2 = new BigDecimal("0.00");
                                BigDecimal SumTotalDetailAmt = new BigDecimal("0.00");
                                BigDecimal SumHeaderAmt1 = new BigDecimal("0.00");
                                BigDecimal SumHeaderAmt2 = new BigDecimal("0.00");
                                this.SumDiffItemAmt = new BigDecimal("0.00");
                                this.SumDiffGstAmt = new BigDecimal("0.00");
                                this.SumDiffHDAmt = new BigDecimal("0.00");                                
                                //BigDecimal oneHeaderAmt = new BigDecimal("0.00");
                                String idRef = header.getID_REF().toString();                                
                                //Result Set R5, SQL 3.0
                                List<Map<String, Object>> itemAmtList = queryDAO.executeForObjectList("SELECT.BSYS.E_EXP_F01.ITEM_TOTAL_AMT", idRef);
                                
                                if(String.valueOf(calCase).equals("1") || String.valueOf(calCase).equals("2")){
                                	for(Map<String, Object> itemList : itemAmtList){
                                		SumItemAmt1 = SumItemAmt1.add(new BigDecimal(itemList.get("ITEMAMT1B").toString())); 
                                		SumItemAmt2 = SumItemAmt2.add(new BigDecimal(itemList.get("ITEMAMT2B").toString())); 
                                		SumGstAmt1 = SumGstAmt1.add(new BigDecimal(itemList.get("GSTAMT1B").toString())); 
                                		SumGstAmt2 = SumGstAmt2.add(new BigDecimal(itemList.get("GSTAMT2B").toString())); 
                                		SumTotalDetailAmt = SumTotalDetailAmt.add(new BigDecimal(itemList.get("TOTALDETAILAMTB").toString())); 
                                		SumHeaderAmt1 = SumHeaderAmt1.add(new BigDecimal(itemList.get("HEADERAMT1B").toString())); 
                                		SumHeaderAmt2 = SumHeaderAmt2.add(new BigDecimal(itemList.get("HEADERAMT2B").toString())); 
                                		this.SumDiffItemAmt = this.SumDiffItemAmt.add(new BigDecimal(itemList.get("DIFFITEMAMTB").toString())); 
                                		this.SumDiffGstAmt = this.SumDiffGstAmt.add(new BigDecimal(itemList.get("DIFFGSTAMTB").toString())); 
                                		this.SumDiffHDAmt = this.SumDiffHDAmt.add(new BigDecimal(itemList.get("DIFFHDAMTB").toString())); 
                                	}
                                }else{ //calCase = 3 or 4
                                	for(Map<String, Object> itemList : itemAmtList){
                                		SumItemAmt1 = SumItemAmt1.add(new BigDecimal(itemList.get("ITEMAMT1A").toString())); 
                                		SumItemAmt2 = SumItemAmt2.add(new BigDecimal(itemList.get("ITEMAMT2A").toString())); 
                                		SumGstAmt1 = SumGstAmt1.add(new BigDecimal(itemList.get("GSTAMT1A").toString())); 
                                		SumGstAmt2 = SumGstAmt2.add(new BigDecimal(itemList.get("GSTAMT2A").toString())); 
                                		SumTotalDetailAmt = SumTotalDetailAmt.add(new BigDecimal(itemList.get("TOTALDETAILAMTA").toString())); 
                                		SumHeaderAmt1 = SumHeaderAmt1.add(new BigDecimal(itemList.get("HEADERAMT1A").toString())); 
                                		SumHeaderAmt2 = SumHeaderAmt2.add(new BigDecimal(itemList.get("HEADERAMT2A").toString())); 
                                		this.SumDiffItemAmt = this.SumDiffItemAmt.add(new BigDecimal(itemList.get("DIFFITEMAMTA").toString())); 
                                		this.SumDiffGstAmt = this.SumDiffGstAmt.add(new BigDecimal(itemList.get("DIFFGSTAMTA").toString())); 
                                		this.SumDiffHDAmt = this.SumDiffHDAmt.add(new BigDecimal(itemList.get("DIFFHDAMTA").toString())); 
                                	}
                                }
                                
                                SumItemAmt1 = SumItemAmt1.setScale(2, BigDecimal.ROUND_HALF_UP);
                        		SumGstAmt1 = SumGstAmt1.setScale(2, BigDecimal.ROUND_HALF_UP);
                    			SumHeaderAmt1 = SumHeaderAmt1.setScale(2, BigDecimal.ROUND_HALF_UP);

                    			this.DiffSumItemAmt = SumItemAmt1.subtract(SumItemAmt2);
                    			this.DiffSumGstAmt = SumGstAmt1.subtract(SumGstAmt2);
                    			this.DiffTotalHeaderAmt = SumHeaderAmt1.subtract(SumHeaderAmt2);
                    			this.DiffAllSumDiffAmt = this.SumDiffHDAmt.add(this.SumDiffItemAmt);
                                
                    			this.SubAdj = "N";
                    			this.SubAdjHeader = "N";
                    			this.SubAdjHeadernGst = "N";
                    			this.SubAdjGst = "N";
                    			this.SubAdjItem = "N";
                    			this.SubAdjItemNGst = "N";
                    			this.firstSub ="N";
                    			
                    			if(this.DiffTotalHeaderAmt.doubleValue() != 0){
                    				if(this.DiffSumItemAmt.doubleValue() == 0 && this.DiffSumGstAmt.doubleValue() != 0){
                    					this.SubAdjHeadernGst = "Y";
                    					this.firstSub ="Y";
                    				}
                    				if(this.DiffSumItemAmt.doubleValue() == 0 && this.DiffSumGstAmt.doubleValue() == 0)
                    					this.SubAdjHeader = "Y";
                    				if(this.DiffSumItemAmt.doubleValue() != 0)
                    					this.SubAdj = "Y";
                    			}else{
                    				if(this.DiffSumItemAmt.doubleValue() == 0 && this.DiffSumGstAmt.doubleValue() == 0)
	                    				this.SubAdjHeader = "Y";
                    				
                    				if(this.DiffSumItemAmt.doubleValue() != 0 && this.DiffSumGstAmt.doubleValue() != 0)
	                    				this.SubAdjItemNGst = "Y";
                    				
	                    			if(this.DiffSumItemAmt.doubleValue() == 0 && this.DiffSumGstAmt.doubleValue() != 0)
	                    				this.SubAdjGst = "Y";
	                    			
	                    			if(this.DiffSumItemAmt.doubleValue() != 0 && this.DiffSumGstAmt.doubleValue() == 0)
	                    				this.SubAdjItem = "Y";
	                    			
                    			}
                    			//#239 << Missing One Cent wcbeh@20170404
                                sql2_input.put("ID_REF", header.getID_REF());
                                sql2_input.put("ID_USER", header.getID_CONSULT());
                                // write to file
                                List<E_EXP_F01_SQL2_Bean> detail_List;
                                detail_List = queryDAO.executeForObjectList("SELECT.BSYS.E_EXP_F01.SQL003", sql2_input);
                                
                                //Get Tax_Code and PorductCode
                                List<String> taxCodeList=new ArrayList<String>();
                                
                                DecimalFormat formatter = new DecimalFormat("0.0000");
                                formatter.setRoundingMode(RoundingMode.HALF_UP);
                               
                                if (detail_List != null && detail_List.size() > 0) {
                                    for (int m = 0; m < detail_List.size(); m++) {
                                        //Z=Select Distinct(R2.TAX_CODE)
                                        String taxCode=CommonUtils.toString(detail_List.get(m).getTAX_CODE());
                                        if(!taxCodeList.contains(taxCode)){
                                            taxCodeList.add(taxCode);
                                        }
                                    }
                                }
                                //Define SubBillNo,Init value=1
                                int subBillNo=1;
                                boolean isTaxCodeSR=false;
                                if(taxCodeList.size()>1){
                                    //Loop through Z(tax code)
                                    for (int n = 0; n < taxCodeList.size(); n++) {
                                       //IS Z.TAX_CODE more than 1
                                        //IS Z.TAX_CODE='SR',Sub Billing Document By Product Code
                                    	/*#436: [B2-2][REQ003]NewTaxCode Start*/
                                        if(Arrays.asList(notZeroTaxRate).contains(taxCodeList.get(n))){
                                        /*#436: [B2-2][REQ003]NewTaxCode End*/
                                        	//Y=Select Distinct(SUBSTR(R2.PRODUCT_CODE,3,0))
                                            List<String> proCodeList=new ArrayList<String>();
                                            proCodeList=getProductCodeList(taxCodeList.get(n),detail_List);
                                            //LOOP through Y(PRODUCT_CODE)
                                            for (int k = 0; k < proCodeList.size(); k++) {
                                               isTaxCodeSR=true;
                                                //SUM(R2.ITEM_SUBTOTAL+R2.ITEM_GST) as BillAmt And SUM(R2.ITEM_EXPORT_AMT+R2.ITEM_EXPORT_GST) as ExpAMt Where R2.PRODUCT_CODE=Y.PRODUCT_CODE
                                                /*#436: [B2-2][REQ003]NewTaxCode Start*/
                                               	Map<String, Object> billAmtMap=getBillAmt(isTaxCodeSR,Arrays.asList(notZeroTaxRate),proCodeList.get(k),detail_List);
                                               	/*#436: [B2-2][REQ003]NewTaxCode End*/
                                               	String billRef=header.getID_REF().toString().trim()+"="+subBillNo;
                                                String billAmount=billAmtMap.get("billamt").toString();
                                                String exportAmt=billAmtMap.get("expamt").toString();
                                                String originCode=billAmtMap.get("originCode").toString();
                                                BigDecimal itemGST= new BigDecimal(formatter.format(0));
                                                BigDecimal itemExportGST= new BigDecimal(formatter.format(0));
                                                String U_Product="";
                                                String Z_Project="";
                                                
                                                //#239 - Missing One Cent wcbeh@20170411                                         
                                                this.subBillingDocumentAdjustment(itemAmtList, proCodeList.get(k).substring(0,3), calCase);
                                                
                                                //Set Header
                                                subBillDocumentHeader(header,busUnit,accRev,
                                                        defCur,calCase,exportAmt,billAmount,billRef,originCode,taxCodeList.get(n),fw);
                                                //Select * from R2 where SUBSTR(R.PRODUCT_CODE,3)=Y.PRODUCT_CODE
                                                E_EXP_F01_SQL2_Bean detail = new E_EXP_F01_SQL2_Bean();
                                                for(int s=0;s<detail_List.size();s++){
                                                    String procode=CommonUtils.toString(detail_List.get(s).getPRODUCT_CODE());
                                                    if(procode.length()>3){
                                                        procode=procode.substring(0,3);
                                                    }
                                                    if(proCodeList.get(k).toString().equals(procode)&&
                                                    		Arrays.asList(notZeroTaxRate).contains(CommonUtils.toString(detail_List.get(s).getTAX_CODE()))){
                                                        //Get detail
                                                        detail =detail_List.get(s);
                                                        U_Product=isNull(detail.getPRODUCT_CODE());
                                                        Z_Project=isNull(detail.getJOB_NO());
                                                        //SUM itemGST and itemExportGST
                                                        itemGST=getItemGST(itemGST,CommonUtils.toString(detail.getITEM_GST()));
                                                        itemExportGST=getItemGST(itemExportGST,CommonUtils.toString(detail.getITEM_EXPORT_GST()));
                                                        //resutl_set_detail(R3)
                                                        subBillDocumentDetail(header,detail, busUnit,defCur,calCase,accRev,advAcc,revAmtCalSet,projectLen,closingDate,billRef,fw,sOneMonthAmount);
                                                    }
                                                }
                                                //Set TaxLine
                                                String itemgst=itemGST.toString();
                                                String itemexportgst=itemExportGST.toString();
                                                subBillDocumentTaxLine( header,detail,busUnit,defCur,calCase, projectLen,billRef,itemexportgst,itemgst,U_Product,Z_Project,fw);
                                                subBillNo+=1;
                                            }
                                        }
                                        else{
                                            //IS Z.TAX_CODE not equal 'SR',Sub Billing Document By Others Tax Code
                                            isTaxCodeSR=false;
                                            /*#436: [B2-2][REQ003]NewTaxCode Start*/
                                            List<String> specifyTaxCodeInList = new ArrayList<String>();
                                            specifyTaxCodeInList.add(taxCodeList.get(n));
                                            Map<String, Object> billAmtMap=getBillAmt(isTaxCodeSR,specifyTaxCodeInList,"",detail_List);
                                            /*#436: [B2-2][REQ003]NewTaxCode End*/
                                            String billRef=header.getID_REF().toString().trim()+"="+subBillNo;
                                            String billAmount=billAmtMap.get("billamt").toString();
                                            String exportAmt=billAmtMap.get("expamt").toString();
                                            String originCode=billAmtMap.get("originCode").toString();
                                            BigDecimal itemGST= new BigDecimal(formatter.format(0));
                                            BigDecimal itemExportGST= new BigDecimal(formatter.format(0));
                                            String U_Product="";
                                            String Z_Project="";
                                            
                                            //#239 - Missing One Cent wcbeh@20170417                                         
                                            //this.subBillingDocumentAdjustment(itemAmtList, null, "T", calCase);
                                            this.taxBillingDocumentAdjustment();
                                           
                                            //Set Header
                                            subBillDocumentHeader(header,busUnit,accRev,
                                                    defCur,calCase,exportAmt,billAmount,billRef,originCode,taxCodeList.get(n),fw);
                                            //Select * from R2 where SUBSTR(R.PRODUCT_CODE,3)=Y.PRODUCT_CODE
                                            E_EXP_F01_SQL2_Bean detail = new E_EXP_F01_SQL2_Bean(); 
                                            for(int s=0;s<detail_List.size();s++){
                                                if(taxCodeList.get(n).toString().equals(CommonUtils.toString(detail_List.get(s).getTAX_CODE()))){
                                                    //Get detail
                                                    detail =detail_List.get(s);
                                                    U_Product=isNull(detail.getPRODUCT_CODE());
                                                    Z_Project=isNull(detail.getJOB_NO());
                                                    //SUM itemGST and itemExportGST
                                                    itemGST=getItemGST(itemGST,CommonUtils.toString(detail.getITEM_GST()));
                                                    itemExportGST=getItemGST(itemExportGST,CommonUtils.toString(detail.getITEM_EXPORT_GST()));
                                                    //resutl_set_detail(R3)
                                                    subBillDocumentDetail(header,detail, busUnit,defCur,calCase,accRev,advAcc,revAmtCalSet,projectLen,closingDate,billRef,fw,sOneMonthAmount);
                                                }
                                            }
                                            //Set TaxLine
                                            String itemgst=itemGST.toString();
                                            String itemexportgst=itemExportGST.toString();
                                            subBillDocumentTaxLine(header,detail,busUnit,defCur,calCase, projectLen,billRef,itemexportgst,itemgst,U_Product,Z_Project,fw);
                                            subBillNo+=1;
                                        }
                                    }
                                }
                                //One Tax Code Only
                                if(taxCodeList.size()==1){
                                    //IS Z.TAX_CODE='SR'
                                    if(Arrays.asList(notZeroTaxRate).contains(taxCodeList.get(0))){
                                       //Y=Select Distinct(SUBSTR(R2.PRODUCT_CODE,3,0))
                                        List<String> proCodeList=new ArrayList<String>();
                                        proCodeList=getProductCodeList(taxCodeList.get(0),detail_List);
                                        //IS Y.PRODUCT_CODE more than 1
                                        if(proCodeList.size()>1){
                                          //IS Z.TAX_CODE more than 1
                                          for(int p=0;p<proCodeList.size();p++){
                                              isTaxCodeSR=true;
                                              //SUM(R2.ITEM_SUBTOTAL+R2.ITEM_GST) as BillAmt And SUM(R2.ITEM_EXPORT_AMT+R2.ITEM_EXPORT_GST) as ExpAMt Where R2.PRODUCT_CODE=Y.PRODUCT_CODE
                                              /*#436: [B2-2][REQ003]NewTaxCode Start*/
                                              Map<String, Object> billAmtMap=getBillAmt(isTaxCodeSR,Arrays.asList(notZeroTaxRate),proCodeList.get(p),detail_List);
                                              /*#436: [B2-2][REQ003]NewTaxCode End*/
                                              String billRef=header.getID_REF().toString().trim()+"="+subBillNo;
                                              String billAmount=billAmtMap.get("billamt").toString();
                                              String exportAmt=billAmtMap.get("expamt").toString();
                                              String originCode=billAmtMap.get("originCode").toString();
                                              BigDecimal itemGST= new BigDecimal(formatter.format(0));
                                              BigDecimal itemExportGST= new BigDecimal(formatter.format(0));
                                              String U_Product="";
                                              String Z_Project="";
                                              
                                              //#239 - Missing One Cent wcbeh@20170411                                         
                                              this.subBillingDocumentAdjustment(itemAmtList, proCodeList.get(p).substring(0,3), calCase);
                                             
                                              //Set Header
                                              subBillDocumentHeader(header,busUnit,accRev,
                                                      defCur,calCase,exportAmt,billAmount,billRef,originCode,taxCodeList.get(0),fw);
                                              //Select * from R2 where SUBSTR(R.PRODUCT_CODE,3)=Y.PRODUCT_CODE
                                              E_EXP_F01_SQL2_Bean detail = new E_EXP_F01_SQL2_Bean(); 
                                              for(int s=0;s<detail_List.size();s++){
                                                  String procode=CommonUtils.toString(detail_List.get(s).getPRODUCT_CODE());
                                                  if(procode.length()>3){
                                                      procode=procode.substring(0,3);
                                                  }
                                                  /*#436: [B2-2][REQ003]NewTaxCode Start*/
                                                  if(proCodeList.get(p).toString().equals(procode)&&
                                                		  Arrays.asList(notZeroTaxRate).contains(CommonUtils.toString(detail_List.get(s).getTAX_CODE()))){
                                                  /*#436: [B2-2][REQ003]NewTaxCode End*/    
                                                	  //Get detail
                                                      detail =detail_List.get(s);
                                                      U_Product=isNull(detail.getPRODUCT_CODE());
                                                      Z_Project=isNull(detail.getJOB_NO());
                                                      //SUM itemGST and itemExportGST
                                                      itemGST=getItemGST(itemGST,CommonUtils.toString(detail.getITEM_GST()));
                                                      itemExportGST=getItemGST(itemExportGST,CommonUtils.toString(detail.getITEM_EXPORT_GST()));
                                                      //resutl_set_detail(R3)
                                                      subBillDocumentDetail(header,detail, busUnit,defCur,calCase,accRev,advAcc,revAmtCalSet,projectLen,closingDate,billRef,fw,sOneMonthAmount);
                                                  }
                                              }
                                              //Set TaxLine
                                              String itemgst=itemGST.toString();
                                              String itemexportgst=itemExportGST.toString();
                                              subBillDocumentTaxLine( header,detail,busUnit,defCur,calCase, projectLen,billRef,itemexportgst,itemgst,U_Product,Z_Project,fw);
                                              subBillNo+=1;
                                          }
                                        }
                                        //Is PRODUCT_CODE ==1
                                        if(proCodeList.size()==1){
                                            //One Billing Document
                                            String billRef=header.getID_REF().toString();
                                            String billAmount=CommonUtils.toString(header.getBILL_AMOUNT());
                                            String exportAmt=CommonUtils.toString(header.getEXPORT_AMOUNT());
                                            String originCode = " ";
                                            
                                            if (detail_List != null && detail_List.size() > 0) {
                                                 originCode=isNull(detail_List.get(0).getOrigin_code());
                                            }
                                            BigDecimal itemGST= new BigDecimal(formatter.format(0));
                                            BigDecimal itemExportGST= new BigDecimal(formatter.format(0));
                                            String U_Product="";
                                            String Z_Project="";
                                            
                                            //#239 - Missing One Cent wcbeh@20170411                                         
                                            this.oneBillingDocumentAdjustment(new BigDecimal(exportAmt),SumTotalDetailAmt);
                                            
                                            //Set Header
                                            subBillDocumentHeader(header,busUnit,accRev,
                                                    defCur,calCase,exportAmt,billAmount,billRef,originCode,taxCodeList.get(0),fw);
                                            E_EXP_F01_SQL2_Bean detail = new E_EXP_F01_SQL2_Bean(); 
                                            for(int s=0;s<detail_List.size();s++){
                                             //Get detail
                                             detail =detail_List.get(s);
                                             U_Product=isNull(detail.getPRODUCT_CODE());
                                             Z_Project=isNull(detail.getJOB_NO());
                                             //SUM itemGST and itemExportGST
                                             itemGST=getItemGST(itemGST,CommonUtils.toString(detail.getITEM_GST()));
                                             itemExportGST=getItemGST(itemExportGST,CommonUtils.toString(detail.getITEM_EXPORT_GST()));
                                             //resutl_set_detail(R2)
                                             subBillDocumentDetail(header,detail, busUnit,defCur,calCase,accRev,advAcc,revAmtCalSet,projectLen,closingDate,billRef,fw,sOneMonthAmount);
                                            }
                                            //Set TaxLine
                                            String itemgst=itemGST.toString();
                                            String itemexportgst=itemExportGST.toString();
                                            subBillDocumentTaxLine( header,detail,busUnit,defCur,calCase, projectLen,billRef,itemexportgst,itemgst,U_Product,Z_Project,fw);
                                        }
                                    }else{
                                        //Is Y.PRODUCT_CODE ==1,One Billing Document
                                        String billRef=header.getID_REF().toString();
                                        String billAmount=CommonUtils.toString(header.getBILL_AMOUNT());
                                        String exportAmt=CommonUtils.toString(header.getEXPORT_AMOUNT());
                                        String originCode = " ";
                                        if (detail_List != null && detail_List.size() > 0) {
                                             originCode=isNull(detail_List.get(0).getOrigin_code());
                                        }
                                        BigDecimal itemGST= new BigDecimal(formatter.format(0));
                                        BigDecimal itemExportGST= new BigDecimal(formatter.format(0));
                                        String U_Product="";
                                        String Z_Project="";
                                        
                                        //#239 - Missing One Cent wcbeh@20170411                                         
                                        this.oneBillingDocumentAdjustment(new BigDecimal(exportAmt),SumTotalDetailAmt);
                                        
                                        //Set Header
                                        subBillDocumentHeader(header,busUnit,accRev,
                                                defCur,calCase,exportAmt,billAmount,billRef,originCode,taxCodeList.get(0),fw);
                                        E_EXP_F01_SQL2_Bean detail = new E_EXP_F01_SQL2_Bean(); 
                                        for(int s=0;s<detail_List.size();s++){
                                         //Get detail
                                         detail =detail_List.get(s);
                                         U_Product=isNull(detail.getPRODUCT_CODE());
                                         Z_Project=isNull(detail.getJOB_NO());
                                         //SUM itemGST and itemExportGST
                                         itemGST=getItemGST(itemGST,CommonUtils.toString(detail.getITEM_GST()));
                                         itemExportGST=getItemGST(itemExportGST,CommonUtils.toString(detail.getITEM_EXPORT_GST()));
                                         //resutl_set_detail(R2)
                                         subBillDocumentDetail(header,detail, busUnit,defCur,calCase,accRev,advAcc,revAmtCalSet,projectLen,closingDate,billRef,fw,sOneMonthAmount);
                                        }
                                        //Set TaxLine
                                        String itemgst=itemGST.toString();
                                        String itemexportgst=itemExportGST.toString();
                                        subBillDocumentTaxLine( header,detail,busUnit,defCur,calCase,projectLen,billRef,itemexportgst,itemgst,U_Product,Z_Project,fw);
                                    }
                                }
                            }

                            fw.flush();
                            fw.close();

                            ERR2SC032 = message_info.getMessage("info.ERR2SC032", export_path,
                                    CSVFilenameNotIncludePath, date_STR);
                            alert_msg = ERR2SC032;

                            T_SET_BATCH tbatch = new T_SET_BATCH();
                            tbatch.setID_BATCH(batch_id + "");
                            tbatch.setBATCH_TYPE("AR");
                            tbatch.setSTATUS("2");
                            tbatch.setMessage(ERR2SC032);
                            tbatch.setFILENAME(CSVFilenameNotIncludePath);
                            tbatch.setID_LOGIN(uvo.getId_user());
                            G_SET_P01 g_set_update = new G_SET_P01(this.queryDAO, this.updateDAO);
                            g_set_update.G_SET_P01_GetIdBatch(tbatch);
                        } catch (Exception e) {
                            alert_msg = ERR2SC033;
                            e.printStackTrace();
                            T_SET_BATCH tbatch = new T_SET_BATCH();

                            tbatch.setID_BATCH(batch_id + "");
                            tbatch.setBATCH_TYPE("AR");
                            tbatch.setSTATUS("3");
                            tbatch.setMessage(CommonUtils.escapeSQL(e.toString()));
                            tbatch.setFILENAME(CSVFilenameNotIncludePath);
                            tbatch.setID_LOGIN(uvo.getId_user());
                            G_SET_P01 g_set_update = new G_SET_P01(this.queryDAO, this.updateDAO);
                            g_set_update.G_SET_P01_GetIdBatch(tbatch);
                        }
                    } else if (listheader == null || listheader.size() == 0) {
                        alert_msg = ERR2SC042;

                        T_SET_BATCH tbatch = new T_SET_BATCH();
                        tbatch.setID_BATCH(batch_id + "");
                        tbatch.setBATCH_TYPE("AR");
                        tbatch.setSTATUS("2");
                        tbatch.setMessage("No PeopleSoft AR file to be generated.");
                        tbatch.setFILENAME(" ");
                        tbatch.setID_LOGIN(uvo.getId_user());
                        G_SET_P01 g_set_update = new G_SET_P01(this.queryDAO, this.updateDAO);
                        g_set_update.G_SET_P01_GetIdBatch(tbatch);
                    }
                } else {
                    ERR1BT012 = message_info.getMessage("errors.ERR1BT012", export_path);

                    alert_msg = ERR2SC033;
                    message = ERR1BT012;
                    T_SET_BATCH tbatch = new T_SET_BATCH();

                    tbatch.setID_BATCH(batch_id + "");
                    tbatch.setBATCH_TYPE("AR");
                    tbatch.setSTATUS("3");
                    tbatch.setMessage(message);
                    tbatch.setFILENAME(" ");
                    tbatch.setID_LOGIN(this.id_login);
                    G_SET_P01 g_set_update = new G_SET_P01(this.queryDAO, this.updateDAO);
                    g_set_update.G_SET_P01_GetIdBatch(tbatch);
                }
    
                // get list user to sendmail(up to 5)
                /* Obsoleted in #33
                List<AlertUserDto> alertUsers = queryDAO.executeForObjectList("SELECT.BSYS.SQL053", "AR");

                G_ALT_P06Input sendmail_In = new G_ALT_P06Input();
                sendmail_In.setBatchId("AR");
                sendmail_In.setSubject(ERR2SC031);
                sendmail_In.setMsg(alert_msg);
                sendmail_In.setListAlertUser(alertUsers);
                G_ALT_P06 sendmail = new G_ALT_P06(this.queryDAO, this.updateDAO);
                BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
                uvo.setId_user(this.id_login);
                sendmail.execute(sendmail_In, uvo);
                */
            } else {
                ERR1SC070 = message_info.getMessage("errors.ERR1SC070");
                message = ERR1SC070;
                alert_msg = message;
                T_SET_BATCH tbatch = new T_SET_BATCH();
                tbatch.setID_BATCH(batch_id + "");
                tbatch.setBATCH_TYPE("AR");
                tbatch.setSTATUS("3");
                tbatch.setMessage(message);
                tbatch.setFILENAME(" ");
                tbatch.setID_LOGIN(this.id_login);
                G_SET_P01 g_set_update = new G_SET_P01(this.queryDAO, this.updateDAO);
                g_set_update.G_SET_P01_GetIdBatch(tbatch);
            }
        } else if (batch_id < 0) {
        }
    }

    /**
     * get billType
     * 
     * @param billType
     * @return
     */
    private String billTypeTrans(String billType) {
        if (billType != null) {
            if (billType.equals("IN") || billType.equals("DN"))
                return "DR";
            else if (billType.equals("CN"))
                return "CR";
        }
        return "";
    }

    /**
     * append to FileWriter
     * 
     * @param fw
     * @param param
     * @throws IOException
     */
    private void insertDetails(FileWriter fw, HashMap<String, String> param) throws IOException {

        char delimiter = ',';
        char quotation = '\"';
        String lineSeparator = "\r\n";

        fw.append(quotation);
        fw.append(param.get("A"));// A
        fw.append(quotation);
        fw.append(delimiter);

        fw.append(quotation);
        fw.append(param.get("B"));// B
        fw.append(quotation);
        fw.append(delimiter);

        fw.append(quotation);
        fw.append(param.get("C"));// C
        fw.append(quotation);
        fw.append(delimiter);

        fw.append(quotation);
        fw.append(param.get("D"));// D
        fw.append(quotation);
        fw.append(delimiter);

        fw.append(quotation);
        fw.append(param.get("E"));// E
        fw.append(quotation);
        fw.append(delimiter);

        fw.append(quotation);
        fw.append(param.get("F"));// F
        fw.append(quotation);
        fw.append(delimiter);

        fw.append(quotation);
        fw.append(param.get("G"));// G
        fw.append(quotation);
        fw.append(delimiter);

        fw.append(quotation);
        fw.append(param.get("H"));// H
        fw.append(quotation);
        fw.append(delimiter);

        fw.append(quotation);
        fw.append(param.get("I"));// I
        fw.append(quotation);
        fw.append(delimiter);

        fw.append(quotation);
        fw.append(param.get("J"));// J
        fw.append(quotation);
        fw.append(delimiter);

        fw.append(quotation);
        fw.append(param.get("K"));// K
        fw.append(quotation);
        fw.append(delimiter);

        fw.append(quotation);
        fw.append(param.get("L"));// L
        fw.append(quotation);
        fw.append(delimiter);

        fw.append(quotation);
        fw.append(param.get("M"));// M
        fw.append(quotation);
        fw.append(delimiter);

        fw.append(quotation);
        fw.append(param.get("N"));// N
        fw.append(quotation);
        fw.append(delimiter);

        fw.append(quotation);
        fw.append(param.get("O"));// O
        fw.append(quotation);
        fw.append(delimiter);

        fw.append(quotation);
        fw.append(param.get("P"));// P
        fw.append(quotation);
        fw.append(delimiter);

        fw.append(quotation);
        fw.append(param.get("Q"));// Q
        fw.append(quotation);
        fw.append(delimiter);

        fw.append(quotation);
        fw.append(param.get("R"));// R
        fw.append(quotation);
        fw.append(delimiter);

        fw.append(quotation);
        fw.append(param.get("S"));// S
        fw.append(quotation);
        fw.append(delimiter);

        fw.append(quotation);
        fw.append(param.get("T"));// T
        fw.append(quotation);
        fw.append(delimiter);

        fw.append(quotation);
        fw.append(param.get("U"));// U
        fw.append(quotation);
        fw.append(delimiter);

        fw.append(quotation);
        fw.append(param.get("V"));// V
        fw.append(quotation);
        fw.append(delimiter);

        fw.append(quotation);
        fw.append(param.get("W"));// W
        fw.append(quotation);
        fw.append(delimiter);

        fw.append(quotation);
        fw.append(param.get("X"));// X
        fw.append(quotation);
        fw.append(delimiter);

        fw.append(quotation);
        fw.append(param.get("Y"));// Y
        fw.append(quotation);
        fw.append(delimiter);

        fw.append(quotation);
        fw.append(param.get("Z"));// Z
        fw.append(quotation);
        fw.append(delimiter);

        fw.append(quotation);
        fw.append(param.get("AA"));// AA
        fw.append(quotation);
        fw.append(delimiter);

        fw.append(quotation);
        fw.append(param.get("AB"));// AB
        fw.append(quotation);
        fw.append(delimiter);

        fw.append(quotation);
        fw.append(param.get("AC"));// AC
        fw.append(quotation);
        fw.append(delimiter);
        
        fw.append(quotation);
        fw.append(param.get("AD"));// AD
        fw.append(quotation);
        fw.append(delimiter);
        
        fw.append(quotation);
        fw.append(param.get("AE"));// AE
        fw.append(quotation);
        fw.append(delimiter);
        
        fw.append(quotation);
        fw.append(param.get("AF"));// AF
        fw.append(quotation);
        fw.append(delimiter);
        
        fw.append(quotation);
        fw.append(param.get("AG"));// AG
        fw.append(quotation);
        fw.append(delimiter);
        
        fw.append(quotation);
        fw.append(param.get("AH"));// AH
        fw.append(quotation);
        fw.append(lineSeparator);
    }

    /**
     * get LineDesc(row AC)
     * 
     * @param caFrom
     *            From Date
     * @param caTo
     *            To Date
     * @param desc2List
     *            DESC_ABBR of SVC_LEVEL2
     * @param desc3List
     *            DESC_ABBR of SVC_LEVEL3
     * @param flag
     * @return
     */
    private String getLineDesc(Calendar caFrom, Calendar caTo, List<String> desc2List, List<String> desc3List, int flag) {
        SimpleDateFormat acFormatSameMonth = new SimpleDateFormat("yyMM");
        SimpleDateFormat acFormatDiffMonth = new SimpleDateFormat("yyMMdd");
        StringBuffer desc1 = new StringBuffer();
        String desc2 = "";
        String desc3 = "";
        StringBuffer desc4 = new StringBuffer();
        boolean isFullMonth = false;

        Calendar temp = Calendar.getInstance();
        temp.setTime(caFrom.getTime());
        if (flag == 1) {
            temp.add(Calendar.DAY_OF_MONTH, 1);
        }
        
        int caFromDay = BillingCalendar.getValue4Date(temp.getTime(), BillingCalendar.DATE);
        int caToDay = BillingCalendar.getValue4Date(caTo.getTime(), BillingCalendar.DATE);
        int lastMonthDay = caTo.getActualMaximum(Calendar.DAY_OF_MONTH);
        
        if (caFromDay == 1 && caToDay == lastMonthDay) {
            isFullMonth = true;
        }
        
        int xLength;
        if (isFullMonth && temp.get(Calendar.YEAR) == caTo.get(Calendar.YEAR) && temp.get(Calendar.MONTH) == caTo.get(Calendar.MONTH)) {
            desc1.append(acFormatSameMonth.format(temp.getTime())).append("/");
            xLength = 25;
        } else {
            desc1.append(acFormatDiffMonth.format(temp.getTime())).append("-")
                    .append(acFormatDiffMonth.format(caTo.getTime())).append("/");
            xLength = 16;
        }
        
        // DESC_ARRB of SVC_LEVEL2
        if (!CollectionUtils.isEmpty(desc2List)) {
            if (!CommonUtils.isEmpty(desc2List.get(0))){
                desc2 = desc2List.get(0);
            }
        }
        
        // DESC_ARRB of SVC_LEVEL3
        if (desc3List != null && desc3List.size() > 0) {
            if (!CommonUtils.isEmpty(desc3List.get(0))) {
                desc3 = desc3List.get(0);
            }
        }

        desc4.append(desc2);
        if (!CommonUtils.isEmpty(desc2) && !CommonUtils.isEmpty(desc3)) {
            desc4.append("-");
        }
        desc4.append(desc3);
        
        desc1.append(desc4.length() > xLength ? desc4.substring(0, xLength) : desc4);

        return desc1.toString();
    }

    /**
     * get originCode
     * 
     * @param originCode
     * @return
     */
    @SuppressWarnings("unchecked")
    private String getOrignValue(String originCode) {
        if (CollectionUtils.isEmpty(this.originCodes)) {
            ApplicationContext context = ApplicationContextProvider.getApplicationContext();
            MappedCodeListLoader originCodeList = (MappedCodeListLoader) context.getBean("LIST_ORIGIN_CODE");
            this.originCodes = originCodeList.getCodeListMap();
        }

        if (this.originCodes.containsKey(originCode)) {
            return this.originCodes.get(originCode).toString();
        }

        return "";
    }

    /**
     * cast one number(15,2) - to positive number if it is negative number - to
     * negative number if it is positive number
     */
    private static String castToPositiveNegative(String str) {
        try {
            return cast(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * cast one number(15,2) to positive number if it is negative number
     */
    private static String castToPositive(String str) {
        try {
            double d = Double.parseDouble(str);
            if (d < 0)
                return cast(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * Cast double to String.
     * 
     * @param d
     *            double value
     * @return String value
     */
    private static String cast(String str) {
        BigDecimal d = new BigDecimal(str);
        BigDecimal zero = new BigDecimal(0);
        return (zero.subtract(d)).toPlainString();
    }

    /**
     * 
     * @param header
     * @param QRSCase
     * @return
     */
    private HashMap<String, Object> getQRS_Header(E_EXP_F01_SQL1_Bean header, int QRSCase,String exportAmt,String billingAmount) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        String billCur = header.getBILL_CURRENCY();
        String exportCur = header.getExport_cur();
        String exportAmount = exportAmt;
        String billType = header.getBILL_TYPE();
        String curRate = header.getCUR_RATE();
        String billAmount = billingAmount;
        int formatQ = 2;
        int formatR = 8;
        switch (QRSCase) {
        case 1:
            // Q //CASE 1
            result.put("Q", this.formatQ(exportAmount, formatQ, billType, 'H'));
            // R
            result.put("R", this.formatNumber(curRate, formatR));
            // S
            result.put("S", exportCur);
            break;
        case 2:
            // Q //CASE 2
            result.put("Q", this.formatQ(exportAmount, formatQ, billType, 'H'));
            // R
            result.put("R", "1.00000000");
            // S
            result.put("S", exportCur);
            break;
        case 3:
            // Q //CASE 3
            result.put("Q", this.formatQ(billAmount, formatQ, billType, 'H'));
            // R
            result.put("R", this.formatNumber(curRate, formatR));
            // S
            result.put("S", billCur);
            break;
        case 4:
            // Q //CASE 4
            result.put("Q", this.formatQ(billAmount, formatQ, billType, 'H'));
            // R
            result.put("R", "1.00000000");
            // S
            result.put("S", billCur);
            break;
        // Delete #146 E-EXP-F01_NTTS_CORP Start
        // case 5 and case 1 do the same thing, to match the DD, delete case 5
        //case 5:
            //// Q //CASE 5
            //result.put("Q", this.formatQ(exportAmount, formatQ, billType, 'H'));
            //// R
            //result.put("R", this.formatNumber(curRate, formatR));
            //// S
            //result.put("S", exportCur);
            //break;
        // Delete #146 E-EXP-F01_NTTS_CORP End
        }
        
        //#239 - Missing One Cent
        if(this.AdjHeader.equals("Y")){
        	BigDecimal headerAmt = new BigDecimal(result.get("Q").toString());
        	headerAmt = headerAmt.add(this.AdjHeaderAmt);
            result.put("Q", this.formatNumber(headerAmt.toPlainString(), 2));
        }
        
        return result;
    }

    /**
     * 
     * @param header
     * @param detail
     * @param QRSCase
     * @return
     */
    private HashMap<String, Object> getQRS_Detail(E_EXP_F01_SQL1_Bean header, E_EXP_F01_SQL2_Bean detail,int QRSCase) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        String billCur = header.getBILL_CURRENCY();
        String exportCur = header.getExport_cur();
        String billType = header.getBILL_TYPE();
        String curRate = header.getCUR_RATE();
        String itemExportAmt = "";
        String itemSubtotal = "";
        int formatQ = 2;
        int formatR = 8;
        itemExportAmt = detail.getITEM_EXPORT_AMT();
        itemSubtotal = detail.getITEM_SUBTOTAL();
        if (CommonUtils.isEmpty(itemExportAmt)) {
            itemExportAmt = "0";
        }
        if (CommonUtils.isEmpty(itemSubtotal)) {
        	itemSubtotal = "0";
        }
        switch (QRSCase) {
        case 1:
            // Q
            result.put("Q", this.formatQ(itemExportAmt, formatQ, billType, 'D'));
            // R
            result.put("R", this.formatNumber(curRate, formatR));
            // S
            result.put("S", exportCur);
            break;
        case 2:
            // Q
            result.put("Q", this.formatQ(itemExportAmt, formatQ, billType, 'D'));
            // R
            result.put("R", "1.00000000");
            // S
            result.put("S", exportCur);
            break;
        case 3:
            // Q
            result.put("Q", this.formatQ(itemSubtotal, formatQ, billType, 'D'));
            // R
            result.put("R", this.formatNumber(curRate, formatR));
            // S
            result.put("S", billCur);
            break;
        case 4:
            // Q
            result.put("Q", this.formatQ(itemSubtotal, formatQ, billType, 'D'));
            // R
            result.put("R", "1.00000000");
            // S
            result.put("S", billCur);
            break;
        // Delete #146 E-EXP-F01_NTTS_CORP Start
        // case 5 and case 1 do the same thing, to match the DD, delete case 5
        //case 5:
            //// Q
            //result.put("Q", this.formatQ(itemExportAmt, formatQ, billType, 'D'));
            //// R
            //result.put("R", this.formatNumber(curRate, formatR));
            //// S
            //result.put("S", exportCur);
            //break;
        // Delete #146 E-EXP-F01_NTTS_CORP End
        }
        
        //#239 - Missing One Cent
        if(this.AdjItem.equals("Y") && this.firstService.equals("Y")){
        	BigDecimal lineAmt = new BigDecimal(result.get("Q").toString());
        	lineAmt = lineAmt.subtract(this.AdjItemAmt);
            result.put("Q", this.formatNumber(lineAmt.toPlainString(), 2));
            this.firstService = "N";
        }
        
        return result;
    }

    /**
     * 
     * @param header
     * @param detail
     * @param QRSCase
     * @return
     */
    private HashMap<String, Object> getQRS_DetailTax(E_EXP_F01_SQL1_Bean header, E_EXP_F01_SQL2_Bean detail,String itemExportGSTStr,String itemGSTStr,int QRSCase) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        String billType = header.getBILL_TYPE();
        String itemExportGst = "";
        String itemGst = "";
        int formatQ = 2;
        itemExportGst = itemExportGSTStr;
        itemGst = itemGSTStr;
        if (CommonUtils.isEmpty(itemExportGst)) {
        	itemExportGst = "0";
        }
        if (CommonUtils.isEmpty(itemGst)) {
        	itemGst = "0";
        }
        switch (QRSCase) {
        case 1:
            // Q
            result.put("Q", this.formatQ(itemExportGst, formatQ, billType, 'D'));
            break;
        case 2:
            // Q
            result.put("Q", this.formatQ(itemExportGst, formatQ, billType, 'D'));
            break;
        case 3:
            // Q
            result.put("Q", this.formatQ(itemGst, formatQ, billType, 'D'));
            break;
        case 4:
            // Q
            result.put("Q", this.formatQ(itemGst, formatQ, billType, 'D'));
            break;
        // Delete #146 E-EXP-F01_NTTS_CORP Start
        // case 5 and case 1 do the same thing, to match the DD, delete case 5
        //case 5:
            //// Q
            //result.put("Q", this.formatQ(itemExportGst, formatQ, billType, 'D'));
            //break;
        // Delete #146 E-EXP-F01_NTTS_CORP End
        }
        
        //#239 - Missing One Cent
        if (this.AdjGst.equals("Y")){
        	BigDecimal taxAmt = new BigDecimal(result.get("Q").toString());
        	taxAmt = taxAmt.add(this.AjdGstAmt);
        	result.put("Q", this.formatNumber(taxAmt.toPlainString(), 2));
        }
        
        return result;
    }
    
    /**
     * 
     * @param s
     * @return
     */
    private String isNull(String s) {
        if (s == null || s.length() == 0) {
            s = " ";
        }
        return s;
    }

    /**
     * 
     * @param s
     * @param format
     * @return
     */
    private String formatNumber(String s, int scale) {
        if (s == null || s.length() == 0) {
            s = "0";
        }
        BigDecimal big = new BigDecimal(s);
        big = big.setScale(scale, BigDecimal.ROUND_HALF_UP);
        return big.toPlainString();
    }

    /**
     * 
     * @param billCur
     * @param exportCur
     * @param isDisplayExpAmt
     * @param defCur
     * @return
     */
    private int getQRSCase(String billCur, String exportCur, String isDisplayExpAmt, String defCur) {
        int result = 0;
        // CASE 1
        if (billCur.equals(exportCur)) {
            result = 1;
        } else if (defCur.equals(exportCur)) {
            // CASE 2
            if ("1".equals(isDisplayExpAmt)) {
                result = 2;
            }
            // CASE 3
            else {
                result = 3;
            }
        } else {
            // Modify #146 E-EXP-F01_NTTS_CORP Start
            //// CASE 5
            //if ("1".equals(isDisplayExpAmt)) {
            //    result = 5;
            //}
            // CASE 1(case 5 and case 1 do the same thing, to match the DD, modify case 5 to 1)
            if ("1".equals(isDisplayExpAmt)) {
                result = 1;
            }
            // Modify #146 E-EXP-F01_NTTS_CORP End
            // CASE 4
            else {
                result = 4;
            }
        }
        return result;
    }

    /**
     * 
     * @param input
     * @param format
     * @param billType
     * @return
     */
    private String formatQ(String input, int format, String billType, char HorD) {
        if ("CN".equals(billType)) {
            if ('H' == HorD) {
                return this.formatNumber(castToPositiveNegative(input), format);
            } else {
                return this.formatNumber(castToPositive(input), format);
            }

        } else {
            if ('H' == HorD) {
                return this.formatNumber(castToPositive(input), format);
            } else {
                return this.formatNumber(castToPositiveNegative(input), format);
            }
        }
    }
     
    /**
     * Set Billing Document Details
     */
    private void subBillDocumentDetail(E_EXP_F01_SQL1_Bean header,E_EXP_F01_SQL2_Bean detail,String busUnit,String defCur,int calCase,
                                 String accRev,String advAcc,String revAmtCalSet,int projectLen,Calendar closingDate,String billRef,FileWriter fw,String sOneMonthAmount){
        
        try {
            
            String A_BusUnit = busUnit;
            String B_Origin;
            String C_CustomerId;
            String E_Entry_Type;
            String F_Reason = "SALS1";
            String G_Revalue = "N";
            String H_InvoiceDate;
            String I_DueDate;
            String M_Salesperson;
            String N_CreditAnalyst;
            String O_Collector;
            String P_PONbr;
            String X_AffiliateNTT;
            String space = " ";
            String AC_Category;
            String AD_SubCategory;

            Date billFrom, billTo;
            Calendar caFrom = Calendar.getInstance();
            Calendar caTo = Calendar.getInstance();

            //int calCase = this.getQRSCase(header.getBILL_CURRENCY(), header.getExport_cur(), header.getIS_DISPLAY_EXP_AMT(), defCur);
            C_CustomerId = this.isNull(header.getCUST_ACC_NO());

            E_Entry_Type = this.billTypeTrans(header.getBILL_TYPE());
            H_InvoiceDate = header.getDATE_REQ();
            I_DueDate = header.getDUE_DATE();
            M_Salesperson = this.isNull(header.getPPLSOFT_ACC_ID());
            N_CreditAnalyst = "DEFAULT";
            O_Collector = "DEFAULT";
            P_PONbr = this.isNull(header.getCUST_PO());
            X_AffiliateNTT = this.isNull(header.getAFFILIATE_CODE());
            AC_Category=this.isNull(header.getCO_CATEGORY());
            AD_SubCategory=this.isNull(header.getCO_SUB_CATEGORY());
            
            HashMap<String, String> param = new HashMap<String, String>();
            String D_InvoiceNbr = space;
            String J_VATApplicable = space;
            String K_VATTxnType = space;
            String L_VATTaxCd = space;
            String Q_LineAmount = space;
            String R_CurrRate = space;
            String S_Currency = space;
            String T_Account = space;
            String U_Product = space;
            String V_Process = space;
            String W_Dept = space;
            String Z_Project = space;
            String Z_CF7 = space;
            String AF_CF8 = space;
            String AG_CF9 = space;
            String AH_LineDesc = space;
            String AA_Activity = space;
            String AB_Source = space;

            D_InvoiceNbr = billRef;
            J_VATApplicable = "T";
            K_VATTxnType = "DT";
            L_VATTaxCd = detail.getTAX_CODE();

            HashMap<String, Object> qrsDetail = this.getQRS_Detail(header, detail,calCase);
            Q_LineAmount = (String) qrsDetail.get("Q");
            R_CurrRate = (String) qrsDetail.get("R");
            S_Currency = (String) qrsDetail.get("S");

            if (detail.getACC_CODE() == null || detail.getACC_CODE().length() == 0) {
                T_Account = space;
            } else {
                T_Account = detail.getACC_CODE().trim();
            }

            U_Product = detail.getPRODUCT_CODE().trim();
            V_Process = space;

            W_Dept = this.isNull(header.getPPLSOFT_DEPT_ID());

            if (CommonUtils.isEmpty(detail.getJOB_NO())) {
                Z_Project = space;
            } else {
                Z_Project = detail.getJOB_NO().length() >= projectLen ? detail.getJOB_NO().substring(0, projectLen) : detail.getJOB_NO();
            }

            Z_CF7 = space;
            AF_CF8 = space;
            AG_CF9 = space;

            billFrom = detail.getBILL_FROM();
            billTo = detail.getBILL_TO();
            caFrom = Calendar.getInstance();
            caTo = Calendar.getInstance();
            caFrom.setTime(billFrom);
            caTo.setTime(billTo);
            List<String> desc2List = this.queryDAO.executeForObjectList("SELECT.BSYS.E_EXP_F01.getSVC_LEVEL2", detail.getID_CUST_PLAN_LINK());
            List<String> desc3List = this.queryDAO.executeForObjectList("SELECT.BSYS.E_EXP_F01.getSVC_LEVEL3", detail.getID_CUST_PLAN_LINK());
            AF_CF8= this.queryDAO.executeForObject("SELECT.BSYS.GET_RATE_TYPE2", detail.getID_CUST_PLAN_LINK(),String.class);
            
            AH_LineDesc = this.getLineDesc(caFrom, caTo, desc2List, desc3List, 0);

            param.put("A", A_BusUnit);

            String originCode = detail.getOrigin_code();
            B_Origin = this.isNull(originCode);
            param.put("B", B_Origin);

            param.put("C", C_CustomerId);

            param.put("D", D_InvoiceNbr);

            param.put("E", E_Entry_Type);

            param.put("F", F_Reason);

            param.put("G", G_Revalue);

            param.put("H", H_InvoiceDate);

            param.put("I", I_DueDate);

            param.put("J", J_VATApplicable);

            param.put("K", K_VATTxnType);

            param.put("L", L_VATTaxCd);

            param.put("M", M_Salesperson);

            param.put("N", N_CreditAnalyst);

            param.put("O", O_Collector);

            param.put("P", P_PONbr);

            param.put("Q", Q_LineAmount);

            param.put("R", R_CurrRate);

            param.put("S", S_Currency);

            param.put("T", T_Account);

            param.put("U", U_Product);

            param.put("V", V_Process);

            param.put("W", W_Dept);

            param.put("X", X_AffiliateNTT);

            param.put("Y", A_BusUnit);
            
            param.put("Z", Z_Project);
            
            param.put("AA", AA_Activity);
            
            param.put("AB", AB_Source);
            
            param.put("AC", AC_Category );
            
            param.put("AD", AD_SubCategory);
            
            param.put("AE", Z_CF7);

            param.put("AF", AF_CF8);

            param.put("AG", AG_CF9);

            param.put("AH", AH_LineDesc);

            if (caTo.compareTo(closingDate) > 0) {
                BigDecimal totalDays = new BigDecimal((caTo.getTimeInMillis() - caFrom.getTimeInMillis()) / (1000 * 60 * 60 * 24) + 1);
                BigDecimal lineDays1 = new BigDecimal((closingDate.getTimeInMillis() - caFrom.getTimeInMillis()) / (1000 * 60 * 60 * 24) + 1);
                BigDecimal lineAmt = new BigDecimal(Q_LineAmount);
                BigDecimal oneDayAmt = lineAmt.divide(totalDays, 10, BigDecimal.ROUND_HALF_UP);
                BigDecimal oneMonthAmt = oneDayAmt.multiply(new BigDecimal("31"));
                BigDecimal lineAmt1 = oneDayAmt.multiply(lineDays1);
                BigDecimal lineAmt2 = lineAmt.subtract(lineAmt1);
                lineAmt1 = lineAmt1.setScale(2, BigDecimal.ROUND_HALF_UP);
                lineAmt2 = lineAmt2.setScale(2, BigDecimal.ROUND_HALF_UP);
                
                //#240 >> One Month Amount *20170330@wcbeh
                /*Calendar bug fix CT 4 May 2017 ST*/
                /*Calendar billToPlusOne = caTo;
                billToPlusOne.add(Calendar.DATE, 1);*/
                Calendar billToPlusOne = Calendar.getInstance();
                billToPlusOne.setTime(caTo.getTime());
                billToPlusOne.add(Calendar.DATE, 1);
                /*Calendar bug fix CT 4 May 2017 EN*/
                
                BigDecimal periodMth = new BigDecimal((billToPlusOne.get(Calendar.YEAR) - caFrom.get(Calendar.YEAR)) * 12 + billToPlusOne.get(Calendar.MONTH) - caFrom.get(Calendar.MONTH));
                BigDecimal dayBillFrom = new BigDecimal(caFrom.get(Calendar.DAY_OF_MONTH));
                BigDecimal dayBillTo = new BigDecimal(billToPlusOne.get(Calendar.DAY_OF_MONTH));
                BigDecimal oneMonthAmt2 = new BigDecimal("0");
                
                if(dayBillFrom.abs().compareTo(dayBillTo.abs())==0)
                	oneMonthAmt2 = lineAmt.divide(periodMth,10,BigDecimal.ROUND_HALF_UP);
                else{
                	Calendar advLastMthFrom = Calendar.getInstance();
                	advLastMthFrom.setTime(billFrom);
                	advLastMthFrom.add(Calendar.MONTH, periodMth.intValueExact());
                	Calendar advLastMthTo = Calendar.getInstance();
                	//#288 [MSC-LiveIssues] [E-EXP-F01] PPS Upload - missing revenue line CT 20170927
                	//advLastMthTo.setTime(billTo);
                	advLastMthTo.setTime(billFrom);
                	advLastMthTo.add(Calendar.MONTH, periodMth.add(new BigDecimal("1")).intValueExact());
                	advLastMthTo.add(Calendar.DATE, -1);
                	
                	BigDecimal advLastMthETday = new BigDecimal(((advLastMthTo.getTimeInMillis() - advLastMthFrom.getTimeInMillis()) / (1000 * 60 * 60 * 24)) + 1);
                	BigDecimal advLastMthATday = new BigDecimal(((billToPlusOne.getTimeInMillis() - advLastMthFrom.getTimeInMillis()) / (1000 * 60 * 60 * 24)));
                	//BigDecimal advLastMthETday = 
                	//BigDecimal advLastMthATday = 
                	BigDecimal day = (advLastMthATday.divide(advLastMthETday,0,BigDecimal.ROUND_HALF_UP)).add(periodMth);
                	if(day.equals(BigDecimal.ZERO)){
                		oneMonthAmt2 = lineAmt;
                	}else{
                		oneMonthAmt2 = lineAmt.divide(day,10,BigDecimal.ROUND_HALF_UP);
                	}
                	
                	//#288 [MSC-LiveIssues] [E-EXP-F01] PPS Upload - missing revenue line CT 20170927
                }
                
                oneMonthAmt = (revAmtCalSet.equals("0")) ? oneMonthAmt : oneMonthAmt2;
                //#240 << One Month Amount *20170330@wcbeh
                
                if (oneMonthAmt.abs().compareTo(new BigDecimal(sOneMonthAmount)) > 0) {
                	
                	//#240 >> One Month Amount *20170330@wcbeh
                	Calendar closingDatePlusOne = Calendar.getInstance(); //(Calendar) closingDate.clone(); //-- CT 3/5/2017
                	//closingDatePlusOne.set(closingDate.get(Calendar.YEAR), closingDate.get(Calendar.MONTH), closingDate.get(Calendar.DAY_OF_MONTH), closingDate.get(Calendar.HOUR_OF_DAY), closingDate.get(Calendar.MINUTE));	
                	closingDatePlusOne.setTime(closingDate.getTime());
                	closingDatePlusOne.add(Calendar.DATE, 1); //-- CT 3/5/2017
                	
                	BigDecimal revTotalMonth = new BigDecimal((closingDatePlusOne.get(Calendar.YEAR) - caFrom.get(Calendar.YEAR)) * 12 + closingDatePlusOne.get(Calendar.MONTH) - caFrom.get(Calendar.MONTH));
                	BigDecimal detailAmt = new BigDecimal("0");
                	BigDecimal advanceAmt = new BigDecimal("0");
                	
                	/* Calendar bug fix CT 4 May 2017 ST
                	 * Calendar caToPlusOne = caTo;
                	 * */
                	Calendar caToPlusOne = Calendar.getInstance(); 
                	caToPlusOne.setTime(caTo.getTime());
                	caToPlusOne.add(Calendar.DATE, 1);
                	/*Calendar bug fix CT 4 May 2017 EN*/
                	if(revAmtCalSet.equals("1") && (caFrom.get(Calendar.DAY_OF_MONTH)==1 || caFrom.get(Calendar.DAY_OF_MONTH)==(caToPlusOne.get(Calendar.DAY_OF_MONTH)))){
                		detailAmt = oneMonthAmt2.multiply(revTotalMonth);
                		detailAmt = detailAmt.setScale(2, BigDecimal.ROUND_HALF_UP);
                	}else{
                		detailAmt = lineAmt1.setScale(2, BigDecimal.ROUND_HALF_UP);
                	}
                	
                	advanceAmt = lineAmt.subtract(detailAmt);
                	advanceAmt = advanceAmt.setScale(2, BigDecimal.ROUND_HALF_UP);
                	//#240 << One Month Amount *20170330@wcbeh
                	
                    AH_LineDesc = this.getLineDesc(caFrom, closingDate, desc2List, desc3List, 0);

                    param.put("AH", AH_LineDesc);
                    //param.put("Q", lineAmt1.toPlainString());
                    param.put("Q", detailAmt.toPlainString());

                    this.insertDetails(fw, param);

                    param.put("T", advAcc);
                    param.put("U", space);
                    param.put("V", space);
                    param.put("W", W_Dept);
                    //param.put("Q", CommonUtils.toString(lineAmt2));
                    param.put("Q", CommonUtils.toString(advanceAmt));

                    AH_LineDesc = this.getLineDesc(closingDate, caTo, desc2List, desc3List, 1);
                    param.put("AH", AH_LineDesc);
                    this.insertDetails(fw, param);
                } else {
                    this.insertDetails(fw, param);
                }
            } else {
                this.insertDetails(fw, param);
            }
            param.clear();
           } catch (Exception e) {
        	   System.out.println("error> "+e);
        }
    }
    
    /**
     * Set Billing Document Header
     */
    private void subBillDocumentHeader(E_EXP_F01_SQL1_Bean header,String busUnit,String accRev,String defCur,int calCase,String exportAmt,String billAmount,String billRef,String originCode,String taxcode,FileWriter fw){
        try {
            
        String A_BusUnit = busUnit;
        String B_Origin;
        String C_CustomerId;
        String E_Entry_Type;
        String F_Reason = "SALS1";
        String G_Revalue = "N";
        String H_InvoiceDate;
        String I_DueDate;
        String M_Salesperson;
        String N_CreditAnalyst;
        String O_Collector;
        String P_PONbr;
        String X_AffiliateNTT;
        String space = " ";
        String W_DeptlDa;
        
        HashMap<String, String> param = new HashMap<String, String>();

        //int calCase = this.getQRSCase(header.getBILL_CURRENCY(), header.getExport_cur(), header.getIS_DISPLAY_EXP_AMT(), defCur);
        C_CustomerId = this.isNull(header.getCUST_ACC_NO());

        E_Entry_Type = this.billTypeTrans(header.getBILL_TYPE());
        H_InvoiceDate = header.getDATE_REQ();
        I_DueDate = header.getDUE_DATE();
        M_Salesperson = this.isNull(header.getPPLSOFT_ACC_ID());
        N_CreditAnalyst = "DEFAULT";
        O_Collector = "DEFAULT";
        P_PONbr = this.isNull(header.getCUST_PO());
        X_AffiliateNTT =this.isNull(header.getAFFILIATE_CODE());
        W_DeptlDa=this.isNull(header.getPPLSOFT_DEPT_ID());  
        // Bus Unit
        param.put("A", A_BusUnit);

        // Origin
        B_Origin = originCode;
        
        param.put("B", B_Origin);

        // Customer ID
        param.put("C", C_CustomerId);

        // Invoice Nbr
        param.put("D", billRef);

        // Entry Type
        param.put("E", E_Entry_Type);

        // Reason
        param.put("F", F_Reason);

        // Revalue
        param.put("G", G_Revalue);

        // Invoice Date
        param.put("H", H_InvoiceDate);

        // Due Date
        param.put("I", I_DueDate);

        // VAT Applicable
        param.put("J", "T");

        // VAT Txn Type
        param.put("K", "DT");

        // VAT Tax Cd
        String taxCodeHead = space;
        taxCodeHead = taxcode;
        param.put("L", taxCodeHead);

        // Salesperson
        param.put("M", M_Salesperson);

        // Credit Analyst
        param.put("N", N_CreditAnalyst);

        // Collector
        param.put("O", O_Collector);

        // PO Nbr
        param.put("P", P_PONbr);

        // Q R S #33
        HashMap<String, Object> qrsHeader = this.getQRS_Header(header, calCase,exportAmt,billAmount);

        // Line Amount
        param.put("Q", (String) qrsHeader.get("Q"));

        // Curr Rate
        param.put("R", (String) qrsHeader.get("R"));

        // Currency
        param.put("S", (String) qrsHeader.get("S"));

        // Account
        if (accRev == null || accRev.length() == 0) {
            param.put("T", space);
        } else {
            param.put("T", accRev.trim());
        }

        // Product
        param.put("U", space);

        // Process
        param.put("V", space);

        // Dept
        param.put("W", W_DeptlDa);

        // Affiliate NTT
        param.put("X", X_AffiliateNTT);

        //PC BUS Unit
        param.put("Y", busUnit);
        
        // Project
        param.put("Z", space);

        //Activity
        param.put("AA", space);
        
        //Source
        param.put("AB", space);
        
        //Category
        param.put("AC", space);
        
       //Sub Category
        param.put("AD", space );
        
        // CF7
        param.put("AE", space);

        // CF8
        param.put("AF", space);

        // CF9
        param.put("AG", space);

        // Line Descr
        param.put("AH", space);

        this.insertDetails(fw, param);

        param.clear();
            } catch (Exception e) {
        }

    }
    
    /**
     * Set Billing Document TaxLine
     */
    private void subBillDocumentTaxLine(E_EXP_F01_SQL1_Bean header,E_EXP_F01_SQL2_Bean detail,String busUnit,String defCur,int calCase, int projectLen,String billRef,String itemExportGST,String itemGST,String Product,String Project,FileWriter fw){
        try {
            String A_BusUnit = busUnit;
            String B_Origin;
            String C_CustomerId;
            String E_Entry_Type;
            String F_Reason = "SALS1";
            String G_Revalue = "N";
            String H_InvoiceDate;
            String I_DueDate;
            String M_Salesperson;
            String N_CreditAnalyst;
            String O_Collector;
            String P_PONbr;
            String X_AffiliateNTT;
            String space = " ";
            String AC_Category;
            String AD_SubCategory;

            Date billFrom, billTo;
            Calendar caFrom = Calendar.getInstance();
            Calendar caTo = Calendar.getInstance();

            //int calCase = this.getQRSCase(header.getBILL_CURRENCY(), header.getExport_cur(), header.getIS_DISPLAY_EXP_AMT(), defCur);
            C_CustomerId = this.isNull(header.getCUST_ACC_NO());

            E_Entry_Type = this.billTypeTrans(header.getBILL_TYPE());
            H_InvoiceDate = header.getDATE_REQ();
            I_DueDate = header.getDUE_DATE();
            M_Salesperson = this.isNull(header.getPPLSOFT_ACC_ID());
            N_CreditAnalyst = "DEFAULT";
            O_Collector = "DEFAULT";
            P_PONbr = this.isNull(header.getCUST_PO());
            X_AffiliateNTT = this.isNull(header.getAFFILIATE_CODE());
            AC_Category=this.isNull(header.getCO_CATEGORY());
            AD_SubCategory=this.isNull(header.getCO_SUB_CATEGORY());
            
            HashMap<String, String> param = new HashMap<String, String>();
            String D_InvoiceNbr = space;
            String J_VATApplicable = space;
            String K_VATTxnType = space;
            String L_VATTaxCd = space;
            String Q_LineAmount = space;
            String R_CurrRate = space;
            String S_Currency = space;
            String T_Account = space;
            String U_Product = space;
            String V_Process = space;
            String W_Dept = space;
            String Z_Project = Project;
            String Z_CF7 = space;
            String AF_CF8 = space;
            String AG_CF9 = space;
            String AH_LineDesc = space;
            String AA_Activity = space;
            String AB_Source = space;

            D_InvoiceNbr =billRef;
            J_VATApplicable = "T";
            K_VATTxnType = "DT";
            L_VATTaxCd = detail.getTAX_CODE();
            
            HashMap<String, Object> qrsDetail = this.getQRS_Detail(header, detail,calCase);
            HashMap<String, Object> qrsTax = this.getQRS_DetailTax(header,detail,itemExportGST,itemGST,calCase);
            Q_LineAmount = (String) qrsTax.get("Q");
            R_CurrRate = (String) qrsDetail.get("R");
            S_Currency = (String) qrsDetail.get("S");

            if (detail.getACC_CODE1() == null || detail.getACC_CODE1().length() == 0) {
                T_Account = space;
            } else {
                T_Account = detail.getACC_CODE1().trim();
            }

            U_Product = Product;
            V_Process = space;
            W_Dept =this.isNull(header.getPPLSOFT_DEPT_ID()) ;

            Z_CF7 = space;
            AF_CF8 = space;
            AG_CF9 = space;

            billFrom = detail.getBILL_FROM();
            billTo = detail.getBILL_TO();
            caFrom = Calendar.getInstance();
            caTo = Calendar.getInstance();
            caFrom.setTime(billFrom);
            caTo.setTime(billTo);
            List<String> desc2List = this.queryDAO.executeForObjectList("SELECT.BSYS.E_EXP_F01.getSVC_LEVEL2", detail.getID_CUST_PLAN_LINK());
            List<String> desc3List = this.queryDAO.executeForObjectList("SELECT.BSYS.E_EXP_F01.getSVC_LEVEL3", detail.getID_CUST_PLAN_LINK());
            AF_CF8= this.queryDAO.executeForObject("SELECT.BSYS.GET_RATE_TYPE2", detail.getID_CUST_PLAN_LINK(),String.class);
            
            AH_LineDesc = this.getLineDesc(caFrom, caTo, desc2List, desc3List, 0);

            param.put("A", A_BusUnit);
            
            String originCode = detail.getOrigin_code();
            B_Origin = this.isNull(originCode);
            
            param.put("B", B_Origin);

            param.put("C", C_CustomerId);

            param.put("D", D_InvoiceNbr);

            param.put("E", E_Entry_Type);

            param.put("F", F_Reason);

            param.put("G", G_Revalue);

            param.put("H", H_InvoiceDate);

            param.put("I", I_DueDate);

            param.put("J", J_VATApplicable);

            param.put("K", K_VATTxnType);

            param.put("L", L_VATTaxCd);

            param.put("M", M_Salesperson);

            param.put("N", N_CreditAnalyst);

            param.put("O", O_Collector);

            param.put("P", P_PONbr);

            param.put("Q", Q_LineAmount);

            param.put("R", R_CurrRate);

            param.put("S", S_Currency);

            param.put("T", T_Account);

            param.put("U", U_Product);

            param.put("V", V_Process);

            param.put("W", W_Dept);

            param.put("X", X_AffiliateNTT);

            param.put("Y", A_BusUnit);
            
            param.put("Z", Z_Project);

            param.put("AA", AA_Activity);
            
            param.put("AB", AB_Source);
            
            param.put("AC", AC_Category);
            
            param.put("AD", AD_SubCategory);
            
            param.put("AE", Z_CF7);

            param.put("AF", AF_CF8);

            param.put("AG", AG_CF9);

            param.put("AH", AH_LineDesc);

            this.insertDetails(fw, param);

            param.clear();
        
            } catch (Exception e) {
        }
    }
    
    /**
     * SUM(R2.ITEM_SUBTOTAL+R2.ITEM_GST) as BillAmt And 
     * SUM(R2.ITEM_EXPORT_AMT+R2.ITEM_EXPORT_GST) as ExpAMt
     * @param proCode 
     * @param detailsLists
     * @return BillAmt、ExpAMt
     */
    private Map<String, Object> getBillAmt(boolean isTaxCodeSR,List<String> taxCode,String proCode,List<E_EXP_F01_SQL2_Bean> detailsLists){
        Map<String, Object> resutlMap=new HashMap<String, Object>();
        DecimalFormat formatter = new DecimalFormat("0.0000");
        formatter.setRoundingMode(RoundingMode.HALF_UP);
        BigDecimal billamt = new BigDecimal(formatter.format(0));
        BigDecimal expamt = new BigDecimal(formatter.format(0));
        String originCode=" ";
        for(int i=0;i<detailsLists.size();i++){
            String productCode="";
            //TaxCode=='SR' SUM billamt by ProductCode
            if(isTaxCodeSR){
                productCode=CommonUtils.toString(detailsLists.get(i).getPRODUCT_CODE());
                if(!"".equals(productCode)){
                    if(productCode.length()>3){
                        productCode=productCode.substring(0,3);
                    }
                }
                if(proCode.equals(productCode)&&
                     taxCode.contains(CommonUtils.toString(detailsLists.get(i).getTAX_CODE()))){
                    //ITEM_SUBTOTAL
                    if(!"".equals(CommonUtils.toString(detailsLists.get(i).getITEM_SUBTOTAL()))){
                        BigDecimal subtotal = new BigDecimal(formatter.format(Double.parseDouble(detailsLists.get(i).getITEM_SUBTOTAL())));
                        billamt=billamt.add(subtotal);
                        //billamt=billamt.setScale(2, BigDecimal.ROUND_HALF_UP); //#239 - Missing One Cent
                    }
                    //ITEM_GST
                    if(!"".equals(CommonUtils.toString(detailsLists.get(i).getITEM_GST()))){
                        BigDecimal gst = new BigDecimal(formatter.format(Double.parseDouble(detailsLists.get(i).getITEM_GST())));
                        billamt=billamt.add(gst);
                        //billamt=billamt.setScale(2, BigDecimal.ROUND_HALF_UP); //#239 - Missing One Cent
                    }
                    //ITEM_EXPORT_AMT
                    if(!"".equals(CommonUtils.toString(detailsLists.get(i).getITEM_EXPORT_AMT()))){
                        BigDecimal exptotal = new BigDecimal(formatter.format(Double.parseDouble(detailsLists.get(i).getITEM_EXPORT_AMT())));
                        expamt=expamt.add(exptotal);
                        //expamt=expamt.setScale(2, BigDecimal.ROUND_HALF_UP); //#239 - Missing One Cent
                    }
                    //ITEM_EXPORT_GST
                    if(!"".equals(CommonUtils.toString(detailsLists.get(i).getITEM_EXPORT_GST()))){
                        BigDecimal expgst = new BigDecimal(formatter.format(Double.parseDouble(detailsLists.get(i).getITEM_EXPORT_GST())));
                        expamt=expamt.add(expgst);
                        //expamt=expamt.setScale(2, BigDecimal.ROUND_HALF_UP); //#239 - Missing One Cent
                    }
                   //only get the first originCode,when loop the detailsLists by ProductCode and TaxCode=='SR' 
                    if(" ".equals(originCode)){
                        originCode=isNull(detailsLists.get(i).getOrigin_code());
                    }
                }
            }
            //TaxCode not equal 'SR' SUM billamt by TaxCode
            else {
                if (taxCode.contains(CommonUtils.toString(detailsLists.get(i).getTAX_CODE()))) {
                    // ITEM_SUBTOTAL
                    if (!"".equals(CommonUtils.toString(detailsLists.get(i).getITEM_SUBTOTAL()))) {
                        BigDecimal subtotal = new BigDecimal(formatter.format(Double.parseDouble(detailsLists.get(i).getITEM_SUBTOTAL())));
                        billamt = billamt.add(subtotal);
                        //billamt = billamt.setScale(2, BigDecimal.ROUND_HALF_UP); //#239 - Missing One Cent
                    }
                    // ITEM_GST
                    if (!"".equals(CommonUtils.toString(detailsLists.get(i).getITEM_GST()))) {
                        BigDecimal gst = new BigDecimal(formatter.format(Double.parseDouble(detailsLists.get(i).getITEM_GST())));
                        billamt = billamt.add(gst);
                        //billamt = billamt.setScale(2, BigDecimal.ROUND_HALF_UP); //#239 - Missing One Cent
                    }
                    // ITEM_EXPORT_AMT
                    if (!"".equals(CommonUtils.toString(detailsLists.get(i).getITEM_EXPORT_AMT()))) {
                        BigDecimal exptotal = new BigDecimal(formatter.format(Double.parseDouble(detailsLists.get(i).getITEM_EXPORT_AMT())));
                        expamt = expamt.add(exptotal);
                        //expamt = expamt.setScale(2, BigDecimal.ROUND_HALF_UP); //#239 - Missing One Cent
                    }
                    // ITEM_EXPORT_GST
                    if (!"".equals(CommonUtils.toString(detailsLists.get(i).getITEM_EXPORT_GST()))) {
                        BigDecimal expgst = new BigDecimal(formatter.format(Double.parseDouble(detailsLists.get(i).getITEM_EXPORT_GST())));
                        expamt = expamt.add(expgst);
                        //expamt = expamt.setScale(2, BigDecimal.ROUND_HALF_UP); //#239 - Missing One Cent
                    }
                   //only get the first originCode,when loop the detailsLists by taxCode and TaxCode not equal 'SR' 
                    if(" ".equals(originCode)){
                        originCode=isNull(detailsLists.get(i).getOrigin_code());
                    }
                }
            }
        }
                
        resutlMap.put("billamt", billamt);
        resutlMap.put("expamt", expamt);
        resutlMap.put("originCode", originCode);
        return resutlMap;
    }
    
    /**
     * SUM(ItemGST) & SUM(ItemExportGST)
     * @param proCode 
     * @param detailsLists
     * @return BillAmt、ExpAMt
     */
    private  BigDecimal getItemGST(BigDecimal totalItemGST,String itemGST){
        //Sum itemGST
        DecimalFormat formatter = new DecimalFormat("0.0000");
        formatter.setRoundingMode(RoundingMode.HALF_UP);
        BigDecimal totalGST = new BigDecimal(formatter.format(totalItemGST));
        if(!"".equals(itemGST)){
            double itemgst=Double.parseDouble(itemGST);
            BigDecimal subGST = new BigDecimal(formatter.format(itemgst));
            totalGST=totalGST.add(subGST);
        }
        return totalGST;
    }
    
    /**
     * get ProductCodeList
     * @param proCode 
     * @param detailsLists
     * @return BillAmt、ExpAMt
     */
    private List<String> getProductCodeList(String taxcode,List<E_EXP_F01_SQL2_Bean> detail_List){
        //get ProductCode GroupBy taxCode
        List<String> productCodeList=new ArrayList<String>();
        for (int i=0;i<detail_List.size();i++) {
            E_EXP_F01_SQL2_Bean expf01=new E_EXP_F01_SQL2_Bean();
            expf01=detail_List.get(i);
            String expf01TaxCode=CommonUtils.toString(expf01.getTAX_CODE());
            String expf01ProductCode=CommonUtils.toString(expf01.getPRODUCT_CODE());
            if(taxcode.equals(expf01TaxCode)){
                if(!"".equals(expf01ProductCode)){
                    if(expf01ProductCode.length()>3){
                        expf01ProductCode=expf01ProductCode.substring(0,3);
                    }
                }
                if(!productCodeList.contains(expf01ProductCode)){
                    productCodeList.add(expf01ProductCode);
                }
            }
        }
        return productCodeList;
        
    }
    
    /**
     * #239 - Missing One Cent wcbeh@20170411
     * One Billing Document Set Adjustment
     */
	private void oneBillingDocumentAdjustment(BigDecimal DocAmt,BigDecimal SumTotalDetailAmt){
    	this.AdjHeader = "N";
    	this.AdjGst = "N";
    	this.AdjItem = "N";
    	this.AdjHeaderAmt = new BigDecimal("0.00");
    	this.AdjItemAmt = new BigDecimal("0.00");
    	this.AjdGstAmt = new BigDecimal("0.00");   	

    	if(this.SumDiffItemAmt.doubleValue() == 0 && this.SumDiffGstAmt.doubleValue() == 0 && this.DiffAllSumDiffAmt.doubleValue() != 0 && DocAmt.doubleValue() != SumTotalDetailAmt.doubleValue()){
    	    this.AdjGst = "Y";
    	    this.AjdGstAmt = this.SumDiffHDAmt;
    	}

    	if(this.SumDiffItemAmt.doubleValue() != 0){
    		this.AdjItem = "Y";
    	   this.AdjItemAmt = this.SumDiffItemAmt;
    	}

    	if(this.SumDiffItemAmt.doubleValue() != 0 && this.DiffAllSumDiffAmt.doubleValue() != 0){
    		this.AdjGst = "Y";
    	   this.AjdGstAmt = this.DiffAllSumDiffAmt;
    	}

    	this.firstService = "Y";            
    }
    
    /**
     * #239 - Missing One Cent wcbeh@20170411
     * Sub Billing Document Set Adjustment
     * @param itemAmtList - R5 Result Set
     * @param proCode
     * @param calCase
     */
    @SuppressWarnings("static-access")
	private void subBillingDocumentAdjustment(List<Map<String, Object>> itemAmtList, String proCode, int calCase){
    	
		BigDecimal diffHDAmtA = new BigDecimal("0.00");
		BigDecimal diffItemAmtA = new BigDecimal("0.00");
		BigDecimal diffGSTAmtA = new BigDecimal("0.00");
		BigDecimal diffHDAmtB = new BigDecimal("0.00");
		BigDecimal diffItemAmtB = new BigDecimal("0.00");
		BigDecimal diffGSTAmtB = new BigDecimal("0.00");
		
    	for(Map<String, Object> itemList : itemAmtList){
    		if(proCode.equalsIgnoreCase(itemList.get("PRODUCT_CODE").toString())){
    			diffHDAmtA = new BigDecimal(itemList.get("DIFFHDAMTA").toString());
    			diffItemAmtA = new BigDecimal(itemList.get("DIFFITEMAMTA").toString()); 
    			diffGSTAmtA = new BigDecimal(itemList.get("DIFFGSTAMTA").toString()); 
    			diffHDAmtB = new BigDecimal(itemList.get("DIFFHDAMTB").toString());
    			diffItemAmtB = new BigDecimal(itemList.get("DIFFITEMAMTB").toString());    
    			diffGSTAmtA = new BigDecimal(itemList.get("DIFFGSTAMTB").toString());     			
    		}
    	}
    	
    	BigDecimal DiffItemAmt = new BigDecimal("0.00");
    	BigDecimal DiffGstAmt = new BigDecimal("0.00");
    	BigDecimal DiffHDAmt = new BigDecimal("0.00");
    	
        if(String.valueOf(calCase).equals("1") || String.valueOf(calCase).equals("2")){
        	DiffItemAmt = new BigDecimal(diffItemAmtB.toPlainString());
        	DiffGstAmt = new BigDecimal(diffGSTAmtB.toPlainString());
        	DiffHDAmt = new BigDecimal(diffHDAmtB.toPlainString());
        }else{
        	DiffItemAmt = new BigDecimal(diffItemAmtA.toPlainString());
        	DiffGstAmt = new BigDecimal(diffGSTAmtA.toPlainString());
        	DiffHDAmt = new BigDecimal(diffHDAmtA.toPlainString());
        }
        
        this.AdjHeader = "N";
        this.AdjGst = "N";
        this.AdjItem = "N";
        this.AdjHeaderAmt = new BigDecimal("0.00");
        this.AdjItemAmt = new BigDecimal("0.00");
        this.AjdGstAmt = new BigDecimal("0.00");
        
        this.firstService = "Y";
        
        if(this.SubAdj.equals("Y")){
        	//#289 [MSC-LiveIssues] [E-EXP-F01] PPS Upload - one cent different CT 28092017
        	if(DiffItemAmt.doubleValue() == 0 && DiffGstAmt.doubleValue() == 0 && DiffHDAmt.doubleValue() != 0){
        		this.AdjHeader = "Y";
        		this.AdjHeaderAmt = DiffHDAmt;
        	}
        	if(DiffItemAmt.doubleValue() != 0 && DiffGstAmt.doubleValue() == 0 && DiffHDAmt.doubleValue() == 0){
        		this.AdjHeader = "Y";
        		this.AdjHeaderAmt = DiffItemAmt;
        	}
        	//#289 [MSC-LiveIssues] [E-EXP-F01] PPS Upload - one cent different CT 28092017
        	if(DiffItemAmt.doubleValue() != 0){
        		this.AdjItem = "Y";
        		this.AdjItemAmt = DiffItemAmt;
        	}
        }
        
        if(this.SubAdjHeadernGst.equals("Y") && this.firstSub.equals("Y")){
        	this.AdjHeader = "Y";
        	this.AdjHeaderAmt = DiffTotalHeaderAmt;
        	this.AdjGst = "Y";
        	this.AjdGstAmt = new BigDecimal(this.castToPositiveNegative(DiffSumGstAmt.toPlainString()));
        	this.firstSub = "N";
        }
        
        if(this.SubAdjHeader.equals("Y") && DiffHDAmt.doubleValue() != 0){
        	this.AdjHeader = "Y";
        	this.AdjHeaderAmt = DiffHDAmt;
        }
        
        if(this.SubAdjGst.equals("Y")){
        	if(DiffItemAmt.doubleValue() == 0 && DiffGstAmt.doubleValue() == 0 && DiffHDAmt.doubleValue() != 0){
        		this.AdjGst = "Y";
        		this.AjdGstAmt = DiffHDAmt;
        	}
        }
        
        if(this.SubAdjItem.equals("Y")){
        	if(DiffHDAmt.doubleValue() != 0){
        		this.AdjItem = "Y";
        		this.AdjItemAmt = new BigDecimal(this.castToPositiveNegative(DiffHDAmt.toPlainString()));
        	}
        }
        
        if(this.SubAdjItemNGst.equals("Y")){
        	if(DiffHDAmt.doubleValue() != 0){
	        	if(DiffItemAmt.doubleValue() == 0){
	        		 this.AdjGst = "Y";
	        		 this.AjdGstAmt = DiffHDAmt;
	        		 //this.AjdGstAmt = new BigDecimal(this.castToPositiveNegative(DiffHDAmt.toPlainString()));
	        	}/*else{
	        		this.AdjItem = "Y";
	        		this.AdjItemAmt = DiffItemAmt;
	        	}*/
        	}
        }
    }
    
    /**
     * #239 - Missing One Cent wcbeh@20170417
     * Tax Billing Document Set Adjustment
     */
	private void taxBillingDocumentAdjustment(){
		//Tax - No Adjustment
    	this.AdjHeader = "N";
    	this.AdjItem = "N";
    	this.AdjGst = "N";
    	this.firstService = "N";
    	this.SubAdj = "N";
    	this.SubAdjHeader = "N";
    	this.SubAdjHeadernGst = "N";
    	this.SubAdjGst = "N";
    	this.SubAdjItem = "N";
    	this.SubAdjItemNGst = "N";
    	this.firstSub = "N";
    }
}
