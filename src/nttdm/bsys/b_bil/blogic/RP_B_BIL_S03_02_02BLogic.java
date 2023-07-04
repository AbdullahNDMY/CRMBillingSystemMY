/*
 * @(#)RP_B_BIL_S03_02_02BLogic.java
 *
 *
 */
package nttdm.bsys.b_bil.blogic;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import jp.terasoluna.fw.web.codelist.MappedCodeListLoader;
import nttdm.bsys.b_bil.dto.RP_B_BIL_S03_02_02Input;
import nttdm.bsys.b_bil.dto.RP_B_BIL_S03_02_02Output;
import nttdm.bsys.common.bean.T_BIL_H;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.util.ApplicationContextProvider;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessages;
import org.springframework.context.ApplicationContext;

/**
 * BusinessLogic class.
 * 
 * @author khungl0ng
 */
public class RP_B_BIL_S03_02_02BLogic extends AbstractRP_B_BIL_S03_02_02BLogic {

    /**
     * BAC
     */
    private static final String BAC = "BAC";
    /**
     * errors.ERR1SC005
     */
    private static final String ERROR_ERR1SC005 = "errors.ERR1SC005";
    /**
     * errors.ERR1SC068
     */
    private static final String ERROR_ERR1SC068 = "errors.ERR1SC068";
    /**
     * Billing Account No.
     */
    private static final String BILL_ACCOUNT_NO = "Billing Account No.";
    /**
     * fail
     */
    private static final String FAIL = "fail";

    /**
     * 
     * @param param RP_B_BIL_S03_02_02Input
     * @return ビジ�?スロジック�?�実行�?果�?BLogicResultインスタンス。
     */
    public BLogicResult execute(RP_B_BIL_S03_02_02Input param) {
        // header info
        T_BIL_H inputHeaderInfo = param.getInputHeaderInfo();
        // result logic
        BLogicResult result = new BLogicResult();

        // ERROR MESSAGE
        BLogicMessages errors = new BLogicMessages();
        //check
        checkMandatoryField(errors, param);
        result.setErrors(errors);
        if (!result.getErrors().isEmpty()) {
            result.setResultString(FAIL);
            return result;
        }
        
        HashMap<String, Object> preBilHData = (HashMap<String, Object>)queryDAO.
            executeForMap("B_BIL.selectPreBillAmount", param.getIdRef());
        //Audit Trail
        String status="";
        if(param.getInputHeaderInfo().getIsClosed().equals("0")){
            status="OPEN";
        }
        else{
            status="CLOSED";
        }
        Integer idAudit = CommonUtils.auditTrailBegin(param.getUvo().
                getId_user(), BillingSystemConstants.MODULE_B,
                BillingSystemConstants.SUB_MODULE_B_BIL, param.getIdRef(),
                status,
                BillingSystemConstants.AUDIT_TRAIL_EDITED);
        HashMap<String, Object> inputData = new HashMap<String, Object>();

        inputHeaderInfo.setIdRef(param.getIdRef());
        inputHeaderInfo.setIdLogin(param.getUvo().getId_user());

        //set data to inputData
        setinputDataValue(inputHeaderInfo , inputData , idAudit);

        Integer[] itemId = param.getItemId();
        StringBuffer itemIdStr = new StringBuffer();
        itemIdStr.append("|");
        for (Integer i : itemId) {
            if (i != null && 0 < i) {
                itemIdStr.append(i + "|");
            }
        }
        inputData.put("itemId", itemIdStr.toString());
        // end mapping
        // update header
        updateDAO.execute("B_BIL.updateHeaderInfo", inputData);
        // update detail
        updateDAO.execute("B_BIL.deleteDetail", inputData);
        // insert detail info

        String[] itemNo = param.getItemNo();
        String[] itemDesc = param.getItemDesc();
        String[] itemQty = param.getItemQty();
        String[] itemUprice = param.getItemUprice();
        String[] itemAtm = param.getItemAtm();
        // getGstAmount
        BigDecimal gstAmount = new BigDecimal(inputHeaderInfo.getGstAmount());
        // apply_gst
        String applyGst = "0";
        if (gstAmount.compareTo(new BigDecimal("0")) != 0) {
            applyGst = "1";
        }
        DecimalFormat formatter = new DecimalFormat("0.00");
        for (int i = 1; i < itemDesc.length; i++) {
            HashMap<String, Object> inputDetailData = 
                         new HashMap<String, Object>();
            // ID_REF
            inputDetailData.put("idRef", inputHeaderInfo.getIdRef());
            // ITEM_ID
            inputDetailData.put("itemId", itemId[i]);
            // ITEM_LEVEL
            inputDetailData.put("itemLevel", "1");
            // ITEM_NO
            inputDetailData.put("itemNo", itemNo[i]);
            // ITEM_DESC
            inputDetailData.put("itemDesc", itemDesc[i]);
            // ITEM_QTY
            inputDetailData.put("itemQty", CommonUtils.toLong(itemQty[i]));
            // ITEM_UPRICE
            inputDetailData.put("itemUprice",
                    formatter.format(new BigDecimal(itemUprice[i])));
            // ITEM_AMT
            inputDetailData.put("itemAtm", formatter.format(new BigDecimal(itemAtm[i])));
            
            // ITEM_GST
            Map<String, Object> gstAmountMap = queryDAO.executeForMap("B_BIL.getGstAmount", null);
            BigDecimal gstPercent = new BigDecimal(CommonUtils.toString(gstAmountMap.get("SET_VALUE")));
            gstPercent = gstPercent.divide(new BigDecimal ("100"));          
            inputDetailData.put("itemGst", formatter.format(gstPercent.multiply(new BigDecimal(itemAtm[i]))));
            
            // ITEM_EXPORT_AMT
            inputDetailData.put("itemExportAmt", null);
            // APPLY_GST
            inputDetailData.put("applyGst", applyGst);
            // IS_DISPLAY
            inputDetailData.put("isDisplay", "1");
            // ID_CUST_PLAN
            inputDetailData.put("idCustPlan", null);
            // ID_CUST_PLAN_GRP
            inputDetailData.put("idCustPlanGrp", null);
            // ID_CUST_PLAN_LINK
            inputDetailData.put("idCustPlanLink", null);
            // SVC_LEVEL1
            inputDetailData.put("svcLevel1", null);
            // SVC_LEVEL2
            inputDetailData.put("svcLevel2", null);
            // BILL_FROM
            inputDetailData.put("billFrom", null);
            // BILL_TO
            inputDetailData.put("billTo", null);
            // JOB_NO
            inputDetailData.put("jobNo", null);
            // IS_DELETED
            inputDetailData.put("isDeleted", "0");
            // DATE_CREATED
            inputDetailData.put("dateCreated", new java.sql.Date(Calendar.
                getInstance().getTimeInMillis()));
            // DATE_UPDATED
            inputDetailData.put("dateUpdated", new java.sql.Date(Calendar.
                getInstance().getTimeInMillis()));
            // ID_LOGIN
            inputDetailData.put("idLogin", inputHeaderInfo.getIdLogin());
            // ID_AUDIT
            inputDetailData.put("idAudit", idAudit);
            if (itemId[i] != null && 0 < itemId[i]) {
                updateDAO.execute("B_BIL.updateDetailInfo", inputDetailData);
            } else {
                updateDAO.execute("B_BIL.insertDetailInfo2", inputDetailData);
            }
        }
        //update T_BAC_H info
        updateTBACH(inputData , preBilHData);
        CommonUtils.auditTrailEnd(idAudit);
        RP_B_BIL_S03_02_02Output outputDTO = new RP_B_BIL_S03_02_02Output();
        outputDTO.setIdRef(param.getIdRef());
        outputDTO.setMode("view");
        outputDTO.setFromPageFlag("BIL");
        // End Audit Trail
        BLogicMessages msgs = new BLogicMessages();
        BLogicMessage msg = new BLogicMessage("info.ERR2SC003", 
                                 new Object[] {});
        msgs.add(ActionMessages.GLOBAL_MESSAGE, msg);
        result.setMessages(msgs);
        result.setResultObject(outputDTO);
        result.setResultString("success");
        return result;
    }
    
    /**
     * set data to inputData
     * @param inputHeaderInfo T_BIL_H data
     * @param inputData HashMap<String, Object> data
     * @param idAudit Integer
     */
    private void setinputDataValue(T_BIL_H inputHeaderInfo , 
            HashMap<String, Object> inputData , Integer idAudit){
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("idCust", inputHeaderInfo.getIdCust());
        m.put("adrType", inputHeaderInfo.getAdrType());
        // generate address info
        HashMap<String, Object> address = (HashMap<String, Object>) queryDAO.
        executeForMap("B_BIL.getCustAdr", m);
        // cust info
        HashMap<String, Object> custInfo = (HashMap<String, Object>) queryDAO.
                                  executeForMap("B_BIL.getSingleCustInfo", m);
        //CUSTOMER_TYPE
        String customerType = CommonUtils.toString(custInfo.get("CUSTOMER_TYPE"));
        // ZIP_CODE
        String zipCode = "";
        // COUNTY
        String countryName = "";
        // ADDRESS4
        StringBuffer address4 = new StringBuffer();
        if (address != null) {
            // generate address4
            ApplicationContext context = ApplicationContextProvider.
            getApplicationContext();
            MappedCodeListLoader countryCodeList = (MappedCodeListLoader) 
            context.getBean("LIST_COUNTRY");
            @SuppressWarnings("unchecked")
            Map<String, Object> countryMap = countryCodeList.getCodeListMap();
            // CountryCd
            String countryCd = CommonUtils.toString(address.get("COUNTRY"));
            // ZIP_CODE
            zipCode = CommonUtils.toString(address.get("ZIP_CODE"));
            if (countryMap.containsKey(address.get("COUNTRY"))) {
                countryName = countryMap.get(countryCd).toString();
            }
            address4.append(zipCode).append(", ").append(countryCd);
        }
        // mapping for saving
        // ID_CUST
        // m.put("idCust", inputHeaderInfo.getIdCust());
        // m.put("contactType", inputHeaderInfo.getContactType());
        // ID_CUST
        inputData.put("idCust", inputHeaderInfo.getIdCust());
        // ADR_TYPE
        inputData.put("adrType", inputHeaderInfo.getAdrType());
        // ADDRESS1
        inputData.put("address1", inputHeaderInfo.getAddress1());
        // ADDRESS2
        inputData.put("address2", inputHeaderInfo.getAddress2());
        // ADDRESS3
        inputData.put("address3", inputHeaderInfo.getAddress3());
        // ADDRESS4
        inputData.put("address4", address4.toString());
        // CommonUtils.fixAddress4(inputData, "address4");
        // TEL_NO
        inputData.put("telNo", inputHeaderInfo.getTelNo());
        // FAX_NO
        inputData.put("faxNo", inputHeaderInfo.getFaxNo());
        // ZIP_CODE
        inputData.put("zipCode", zipCode);
        // COUNTRY
        inputData.put("country", countryName);
        // CONTACT_TYPE
        if ("CORP".equals(customerType)) {
            inputData.put("contactType", inputHeaderInfo.getContactType());
        } else {
            inputData.put("contactType", "");
        }
        inputData.put("contactName", inputHeaderInfo.getContactName());
        // get contact info
        // HashMap<String, Object> contactInfo = (HashMap<String, Object>)
        // this.queryDAO
        // .executeForMap("B_BIL.getSingleContact", m);
        // CONTACT_NAME
        // inputData.put(
        // "contactName",
        // !CommonUtils.isEmpty(contactInfo) ? contactInfo
        // .get("CONTACT_NAME") : "");
        // CONTACT_EMAIL
        inputData.put("contactEmail", inputHeaderInfo.getContactEmail());
        // inputData.put("contactEmail",
        // !CommonUtils.isEmpty(contactInfo) ? contactInfo.get("EMAIL")
        // : "");
        // ID_REF
        inputData.put("idRef", inputHeaderInfo.getIdRef());
        // DATE_REQ
        inputData.put("dateReq", CommonUtils.parseToDate(
                inputHeaderInfo.getDateReq(), "dd/MM/yyyy"));
        // BILL_ACC
        inputData.put("billAcc", inputHeaderInfo.getBillAcc());
        // ID_QCS
        inputData.put("idQcs", inputHeaderInfo.getIdQcs());
        // QUO_REF
        inputData.put("quoRef", inputHeaderInfo.getQuoRef());
        // CUST_PO
        inputData.put("custPo", inputHeaderInfo.getCustPo());
        // ID_CONSULT
        inputData.put("idConsult", inputHeaderInfo.getIdConsult());
        // BILL_CURRENCY
        inputData.put("billCurrency", inputHeaderInfo.getBillCurrency());
        // TERM
        inputData.put("term", inputHeaderInfo.getTerm());
        // GST_PERCENT
        inputData.put("gstPercent",
                queryDAO.executeForMap("B_BIL.getGstAmount", null).get("SET_VALUE"));
        // GST_AMOUNT
        inputData.put("gstAmount", inputHeaderInfo.getGstAmount());
        // BILL_AMOUNT
        inputData.put("billAmount", inputHeaderInfo.getBillAmount());
        // EXPORT_CUR
        inputData.put("exportCur", inputHeaderInfo.getBillCurrency());
        // EXPORT_AMOUNT
        inputData.put("exportAmount", inputHeaderInfo.getBillAmount());

        // DATE_UPDATED
        inputData.put("dateUpdated", new java.sql.Date(Calendar.getInstance().
        getTimeInMillis()));
        // ID_LOGIN
        inputData.put("idLogin", inputHeaderInfo.getIdLogin());
        // ID_AUDIT
        inputData.put("idAudit", idAudit);
    }
    
    /**
     * update T_BAC_H info
     * @param inputData inputData
     * @param preBilHData pre T_BIL_H data
     */
    private void updateTBACH(HashMap<String, Object> inputData ,
            HashMap<String, Object> preBilHData){
        // get M_GSET_D info
        List<Object> mGSetDInfoList = (List<Object>) this.queryDAO.
            executeForObjectList("B_BIL.getMGSetDInfo", null);
        if (!CommonUtils.isEmpty(mGSetDInfoList) && mGSetDInfoList.contains(BAC)) {
            // Current BILL_ACC
            String cBillAcc = CommonUtils.toString(inputData.get("billAcc")).trim();
            // previous BILL_ACC
            String pBillAcc = CommonUtils.toString(preBilHData.get("BILL_ACC")).trim();
            // previous BILL_AMOUNT
            String pBillAmountStr = CommonUtils.toString(preBilHData.get("BILL_AMOUNT"));
            // BILL_TYPE
            String billType = CommonUtils.toString(preBilHData.get("BILL_TYPE"));
            //Current BILL_AMOUNT
            BigDecimal cBillAmount = new BigDecimal(CommonUtils.toString(inputData.get("billAmount")));
            BigDecimal pBillAmount = new BigDecimal(pBillAmountStr);
            DecimalFormat formatter = new DecimalFormat("0.00");
            if(cBillAcc.equals(pBillAcc)){
                // Billing Account has not been changed.
                if ("CN".equals(billType)) {
                    pBillAmount = new BigDecimal("0").subtract(pBillAmount);
                    cBillAmount = new BigDecimal("0").subtract(cBillAmount);
                }
                inputData.put("pBillAmount", formatter.format(pBillAmount));
                inputData.put("cBillAmount", formatter.format(cBillAmount));
                updateDAO.execute("B_BIL.updateBAC", inputData);
            }else{
                //previous BILL_ACC's previous Bill_Amount
                BigDecimal pBillAccPBillAmount = pBillAmount;
                //previous BILL_ACC's Current Bill_Amount
                BigDecimal pBillAccCBillAmount = new BigDecimal("0");
                
                //Current BILL_ACC's previous Bill_Amount
                BigDecimal cBillAccPBillAmount = new BigDecimal("0");
                //Current BILL_ACC's Current Bill_Amount
                BigDecimal cBillAccCBillAmount = cBillAmount;
                
                if ("CN".equals(billType)) {
                    pBillAccPBillAmount = new BigDecimal("0").subtract(pBillAccPBillAmount);
                    pBillAccCBillAmount = new BigDecimal("0").subtract(pBillAccCBillAmount);
                    
                    cBillAccPBillAmount = new BigDecimal("0").subtract(cBillAccPBillAmount);
                    cBillAccCBillAmount = new BigDecimal("0").subtract(cBillAccCBillAmount);
                }

                //Update previous BILL_ACC T_BAC_H
                inputData.put("pBillAmount", formatter.format(pBillAccPBillAmount));
                inputData.put("cBillAmount", formatter.format(pBillAccCBillAmount));
                inputData.put("billAcc", pBillAcc);
                updateDAO.execute("B_BIL.updateBAC", inputData);

                //Update current BILL_ACC T_BAC_H
                inputData.put("pBillAmount", formatter.format(cBillAccPBillAmount));
                inputData.put("cBillAmount", formatter.format(cBillAccCBillAmount));
                inputData.put("billAcc", cBillAcc);
                updateDAO.execute("B_BIL.updateBAC", inputData);
            }
        }
    }
    
    /**
     * check info
     * @param errors BLogicMessages info
     * @param param input value
     */
    private void checkMandatoryField
                (BLogicMessages errors, RP_B_BIL_S03_02_02Input param) {
        // header info
        T_BIL_H inputHeaderInfo = param.getInputHeaderInfo();
        //idCust
        String idCust = CommonUtils.toString(
                inputHeaderInfo.getIdCust()).trim();
        //adrType
        String adrType = CommonUtils.toString(
                inputHeaderInfo.getAdrType()).trim();
        //dateReq
        String dateReq = CommonUtils.toString(
                inputHeaderInfo.getDateReq()).trim();
        //billCurrency
        String billCurrency = CommonUtils.toString(
                inputHeaderInfo.getBillCurrency()).trim();
        //gstAmount
        String gstAmount = CommonUtils.toString(
                inputHeaderInfo.getGstAmount()).trim();
       
        //idCust check
        if (CommonUtils.isEmpty(idCust)) {
            errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage(
                    ERROR_ERR1SC005, new Object[] {"Customer Name"}));
        }
        //adrType check
        if (CommonUtils.isEmpty(adrType)) {
            errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage(
                    ERROR_ERR1SC005, new Object[] {"Billing Address"}));
        }
        //dateReq check
        if (CommonUtils.isEmpty(dateReq)) {
            String typeName = "";
            if ("IN".equals(inputHeaderInfo.getBillType())) {
                // invoice INVNO
                typeName = "Invoice Date";
            } else if ("DN".equals(inputHeaderInfo.getBillType())) {
                // debit note DBTNO
                typeName = "Debit Note Date";
            } else {
                // credit note CDTNO
                typeName = "Credit Note Date";
            }
            errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage(
                    ERROR_ERR1SC005, new Object[] {typeName}));
        }
        // get M_GSET_D info
        List<Object> mGSetDInfoList = (List<Object>) this.queryDAO.
        executeForObjectList("B_BIL.getMGSetDInfo", null);
        if (!CommonUtils.isEmpty(mGSetDInfoList)) {
            if (mGSetDInfoList.contains(BAC)) {
                // Billing Account No
                String billAcc = CommonUtils.
                    toString(inputHeaderInfo.getBillAcc());
                // Billing Account No is empty check
                if (CommonUtils.isEmpty(billAcc)) {
                    errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage(
                            ERROR_ERR1SC005, new Object[] {BILL_ACCOUNT_NO}));
                }
            }
        }
        //billCurrency check
        if (CommonUtils.isEmpty(billCurrency)) {
            errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage(
                    ERROR_ERR1SC005, new Object[] {"Currency"}));
        }
             
        String[] itemQty = param.getItemQty();
        String[] itemDesc = param.getItemDesc();
        String[] itemUprice = param.getItemUprice();
     
        if (itemQty.length <= 1 && itemDesc.length <= 1 && itemUprice.length <= 1){
    		errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage(
                    ERROR_ERR1SC068, new Object[] {""}));
    	}else {
	        for (int i = 1; i < itemQty.length; i++) {
	        	
	        	//itemDesc check
	            if (CommonUtils.isEmpty(itemDesc[i])) {
	                errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage(
	                        ERROR_ERR1SC005, new Object[] {"Billing Description"}));
	            }
	            //itemQty check
	            if (CommonUtils.isEmpty(itemQty[i])) {
	                errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage(
	                        ERROR_ERR1SC005, new Object[] {"Quantity"}));
	            } 
	            //itemUprice check
	            if (CommonUtils.isEmpty(itemUprice[i])) {
	                errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage(
	                        ERROR_ERR1SC005, new Object[] {"Unit Price"}));               
	            }
	        }
    	}
        
        //gstAmount check
        if (CommonUtils.isEmpty(gstAmount)) {
            errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage(
                    ERROR_ERR1SC005, new Object[] {"GST"}));
        }
    }
}