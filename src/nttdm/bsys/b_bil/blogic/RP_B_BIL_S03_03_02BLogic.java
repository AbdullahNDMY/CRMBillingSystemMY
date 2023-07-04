/*
 * @(#)RP_B_BIL_S03_03_02BLogic.java
 *
 *
 */
package nttdm.bsys.b_bil.blogic;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import jp.terasoluna.fw.web.codelist.MappedCodeListLoader;
import nttdm.bsys.b_bil.bean.T_BIL_DBean;
import nttdm.bsys.b_bil.bean.T_BIL_DetailInfo;
import nttdm.bsys.b_bil.bean.T_BIL_HeaderInfo;
import nttdm.bsys.b_bil.dto.RP_B_BIL_S03_03_02Input;
import nttdm.bsys.b_bil.dto.RP_B_BIL_S03_03_02Output;
import nttdm.bsys.common.bean.G_CSB_P01_CheckInput;
import nttdm.bsys.common.bean.G_CSB_P01_CheckOutput;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.G_CDM_P01;
import nttdm.bsys.common.util.G_CSB_P01_Check;
import nttdm.bsys.common.util.G_CUR_P01;
import nttdm.bsys.util.ApplicationContextProvider;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessages;
import org.springframework.context.ApplicationContext;

/**
 * BusinessLogic class.
 * 
 * @author khungl0ng
 */
public class RP_B_BIL_S03_03_02BLogic extends AbstractRP_B_BIL_S03_03_02BLogic {

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
     * errors.ERR1SC068
     */
    private static final String ERROR_ERR1SC093 = "errors.ERR1SC093";
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
     * @param param RP_B_BIL_S03_03_02Input
     * @return ビジ�?スロジック�?�実行�?果�?BLogicResultインスタンス。
     */
    public BLogicResult execute(RP_B_BIL_S03_03_02Input param) {
        BLogicResult result = new BLogicResult();
        T_BIL_HeaderInfo inputHeaderInfo = param.getBilHeaderInfo();
        if("".equals(inputHeaderInfo.getTermDays())){
    		inputHeaderInfo.setTermDays("0");
    	}
		String tremDays = inputHeaderInfo.getTermDays();
        if(!"0".equals(tremDays)){
        	inputHeaderInfo.setTerm(tremDays + " Days");
        }else {
        	inputHeaderInfo.setTerm("");
        }
        if(inputHeaderInfo.getContactDeliveryEmail() == null){
        	inputHeaderInfo.setContactDeliveryEmail("0");
        }
        // ERROR MESSAGE
        BLogicMessages errors = new BLogicMessages();
        RP_B_BIL_S03_03_02Output outputDTO = new RP_B_BIL_S03_03_02Output();
        //check
        checkMandatoryField(errors, param);
        result.setErrors(errors);
        if (!result.getErrors().isEmpty()) {
            outputDTO.setBilHeaderInfo(inputHeaderInfo);
            result.setResultString(FAIL);
            result.setResultObject(outputDTO);
            return result;
        }
        
        try {
            HashMap<String, Object> inputData = new HashMap<String, Object>();
            // generate by Generated Running No. [G_CDM_P01]
            String idUser = param.getUvo().getId_user();
            String idRef = "";
            Integer idAudit = null;
            
            String mode = inputHeaderInfo.getMode();
            if("new".equals(mode)) {
                G_CDM_P01 gCdmP01 = new G_CDM_P01(this.queryDAO, this.updateDAO, idUser);
                String idCode = "";
                if ("IN".equals(inputHeaderInfo.getBillType())) {
                    // invoice INVNO
                    idCode = "INVNO";
                } else if ("DN".equals(inputHeaderInfo.getBillType())) {
                    // debit note DBTNO
                    idCode = "DBTNO";
                } else {
                    // credit note CDTNO
                    idCode = "CDTNO";
                }
                try {
                    idRef = gCdmP01.getGenerateCode(idCode, idUser);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
                idRef = paddingRightSpace(idRef, 20);
                
                idAudit = CommonUtils.auditTrailBegin(idUser,
                        BillingSystemConstants.MODULE_B,
                        BillingSystemConstants.SUB_MODULE_B_BIL, idRef, "OPEN",
                        BillingSystemConstants.AUDIT_TRAIL_CREATED);
                //set data to inputData
                setInputDataValueForNew(inputHeaderInfo, inputData, idAudit, idRef, idUser);
                // end mapping
                // insert header info
                updateDAO.execute("B_BIL.insertHeaderInfo", inputData);
                
                //Insert T_BIL_D
                updateT_BIL_D(inputHeaderInfo, false, idRef, idUser, idAudit);
                
                //update T_BAC_H info
                updateTBACHForNew(inputData);
                
                inputHeaderInfo.setIdRef(idRef);
            } else {
                idRef = inputHeaderInfo.getIdRef();
                String status="";
                if(inputHeaderInfo.getIsClosed().equals("0")){
                    status="OPEN";
                }
                else{
                    status="CLOSED";
                }
                idAudit = CommonUtils.auditTrailBegin(idUser,
                        BillingSystemConstants.MODULE_B,
                        BillingSystemConstants.SUB_MODULE_B_BIL, idRef, 
                        status,
                        BillingSystemConstants.AUDIT_TRAIL_EDITED);
                //get before update T_BIL_H data
                HashMap<String, Object> preBilHData = (HashMap<String, Object>)queryDAO.
                            executeForMap("B_BIL.selectPreBillAmount", idRef);
                
                //set data to inputData for edit model
                setinputDataValueForEdit(inputHeaderInfo, inputData, idAudit, idUser);
                //Insert T_BIL_D
                updateT_BIL_D(inputHeaderInfo, true, idRef, idUser, idAudit);
                //update T_BAC_H info
                updateTBACHForEdit(inputData, preBilHData);
            }
            
            CommonUtils.auditTrailEnd(idAudit);
            
            outputDTO.setIdRef(idRef);
            outputDTO.setMode("view");
            outputDTO.setFromPageFlag("BIL");
            outputDTO.setBilHeaderInfo(inputHeaderInfo);
            // End Audit Trail
            // success
            BLogicMessages msgs = new BLogicMessages();
            BLogicMessage msg = new BLogicMessage("info.ERR2SC003", 
                   new Object[] {});
            msgs.add(ActionMessages.GLOBAL_MESSAGE, msg);
            result.setMessages(msgs);
            result.setResultObject(outputDTO);
            result.setResultString("success");
            return result;
        } catch(Exception e) {
            outputDTO.setBilHeaderInfo(inputHeaderInfo);
            errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("info.ERR2SC004", new Object[] {}));
            result.setErrors(errors);
            result.setResultString(FAIL);
            result.setResultObject(outputDTO);
            return result;
        }
    }
    
    /**
     * set data to inputData for new model
     * @param inputHeaderInfo T_BIL_HeaderInfo
     * @param inputData HashMap<String, Object>
     * @param idAudit Integer
     * @param idRef String
     * @param idUser String
     */
    private void setInputDataValueForNew(T_BIL_HeaderInfo inputHeaderInfo , 
            HashMap<String, Object> inputData , 
            Integer idAudit , String idRef , String idUser){
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
        String address4 = "";
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
            address4 = zipCode.concat(", ").concat(countryCd);
        }
        // ID_CUST
        inputData.put("idCust", inputHeaderInfo.getIdCust());
        // CUST_NAME
        inputData.put("custName", inputHeaderInfo.getCustName());
        // SALUTATION
        inputData.put("salutation", inputHeaderInfo.getSalutation());
        // BILL_TYPE
        inputData.put("billType", inputHeaderInfo.getBillType());
        // ADR_TYPE
        inputData.put("adrType", inputHeaderInfo.getAdrType());
        // ADDRESS1
        inputData.put("address1", inputHeaderInfo.getAddress1());
        // ADDRESS2
        inputData.put("address2", inputHeaderInfo.getAddress2());
        // ADDRESS3
        inputData.put("address3", inputHeaderInfo.getAddress3());
        // ADDRESS4
        inputData.put("address4", address4);
        // TEL_NO
        inputData.put("telNo", inputHeaderInfo.getTelNo());
        // FAX_NO
        inputData.put("faxNo", inputHeaderInfo.getFaxNo());
        // CONTACT_TYPE
        if ("CORP".equals(customerType)) {
            inputData.put("contactType", inputHeaderInfo.getContactType());
        } else {
            inputData.put("contactType", "");
        }
        // CONTACT_NAME
        inputData.put("contactName", inputHeaderInfo.getContactName());
        // CONTACT_EMAIL
        inputData.put("contactEmail", inputHeaderInfo.getContactEmail());
        // CONTACT_EMAILCC
        inputData.put("contactEmailCC", inputHeaderInfo.getContactEmailCC());
        //DUE_DATE
        inputData.put("contactDueDate", CommonUtils.parseToDate(inputHeaderInfo.getContactDueDate(), "dd/MM/yyyy"));
        //DELIVERY
        inputData.put("contactDelivery", inputHeaderInfo.getContactDelivery());
        //DELIVERYEMAIL
        inputData.put("contactDeliveryEmail", inputHeaderInfo.getContactDeliveryEmail());
        // ID_REF
        inputData.put("idRef", idRef);
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
        // TERM_DAY
        inputData.put("termDays", inputHeaderInfo.getTermDays());
        //GST_PERCENT
        inputData.put("gstPercent", inputHeaderInfo.getGstPercent());
        // GST_AMOUNT
        inputData.put("gstAmount", inputHeaderInfo.getGstAmount());
        // BILL_AMOUNT
        inputData.put("billAmount", inputHeaderInfo.getBillAmount());
        // IS_MANUAL
        inputData.put("isManual", "1");
        // PAY_METHOD
        inputData.put("payMethod", inputHeaderInfo.getPayMethod());
        // ID_BIF
        inputData.put("idBif", null);
        // PAID_AMOUNT
        inputData.put("paidAmount", 0);
        // LAST_PAYMENT_DATE
        inputData.put("lastPaymentDate", null);
        // IS_SETTLED
        inputData.put("isSettled", "0");
        // IS_SINGPOST
        inputData.put("isSingPost", "0");
        // IS_EXPORT
        inputData.put("isExport", "0");
        // IS_DISPLAY_EXP_AMT
        inputData.put("isDisplayExpAmt", "0");
        // NO_PRINTED
        inputData.put("noPrinted", 0);
        // DATE_PRINTED
        inputData.put("datePrinted", null);
        // USER_PRINTED
        inputData.put("userPrinted", null);
        // IS_CLOSED
        inputData.put("isClosed", "0");
        // ZIP_CODE
        inputData.put("zipCode", zipCode);
        // COUNTRY
        inputData.put("country", countryName);
        // PREPARED_BY
        inputData.put("prePared", idUser);
        // IS_DELETED
        inputData.put("isDeleted", "0");
        // ID_LOGIN
        inputData.put("idLogin", idUser);
        // ID_AUDIT
        inputData.put("idAudit", idAudit);
        
        String billCur = CommonUtils.toString(inputHeaderInfo.getBillCurrency()).trim();
        String expCur = CommonUtils.toString(inputHeaderInfo.getExportCur()).trim();
        if (!CommonUtils.isEmpty(billCur)
                && !CommonUtils.isEmpty(expCur)
                && !"-".equals(expCur)
                && !billCur.equals(expCur)) {
            String curRate = CommonUtils.toString(inputHeaderInfo.getCurRate()).trim();
            //$CurRate = null or $CurRate = 0, $CurRate=1
            if (CommonUtils.isEmpty(curRate)) {
                curRate = "1";
            } else {
                if(Double.parseDouble(curRate)==0.0) {
                    curRate = "1";
                }
            }
            //calculated Export Amount
            DecimalFormat formatter = new DecimalFormat("0.00");
            formatter.setRoundingMode(RoundingMode.HALF_UP);
            BigDecimal exportAmtValue = new BigDecimal("0");
            G_CUR_P01 gCurP01 = new G_CUR_P01(queryDAO);
            List<T_BIL_DetailInfo> billDetail = inputHeaderInfo.getBilDetail();
            if(billDetail!=null&&billDetail.size()>0){
                Double gstPercentDouble = CommonUtils.toDouble(inputHeaderInfo.getGstPercent())/100;
                String tempAmt;
                List<T_BIL_DBean> tempSubPlan;
                for(T_BIL_DetailInfo tempT_BIL_DetailInfo:billDetail){
                    tempSubPlan = tempT_BIL_DetailInfo.getSubPlanBean();
                    if(tempSubPlan!=null&&tempSubPlan.size()>0){
                        for(T_BIL_DBean tempPlan:tempSubPlan){
                            String itemAmtString = tempPlan.getItemAmt();
                            double itemAmtDouble = CommonUtils.toDouble(itemAmtString);
                            if("1".equals(tempPlan.getApplyGst())){
                                tempAmt = CommonUtils.toString(gCurP01.convertCurrency(billCur, itemAmtDouble *gstPercentDouble, expCur, curRate).get(G_CUR_P01.EXPORT_AMOUNT));
                                exportAmtValue = exportAmtValue.add(new BigDecimal(tempAmt));
                            }
                            tempAmt = CommonUtils.toString(gCurP01.convertCurrency(billCur, itemAmtDouble, expCur, curRate).get(G_CUR_P01.EXPORT_AMOUNT));
                            exportAmtValue = exportAmtValue.add(new BigDecimal(tempAmt));
                            exportAmtValue = new BigDecimal(formatter.format(exportAmtValue));
                        }
                    }
                }
            }
            /*
            DecimalFormat formatter = new DecimalFormat("0.00");
            formatter.setRoundingMode(RoundingMode.HALF_UP);
            String gstAmountStr = inputHeaderInfo.getGstAmount();
            String subTotalAmtStr = inputHeaderInfo.getSubTotalAmt();
            double gstAmount = Double.parseDouble(gstAmountStr);
            double subTotalAmt = Double.parseDouble(subTotalAmtStr);
            BigDecimal exportAmtValue = new BigDecimal("0");
            G_CUR_P01 gCurP01 = new G_CUR_P01(queryDAO);
            Map<String, Object> expAmtMap = gCurP01.convertCurrency(billCur, gstAmount, expCur, curRate);
            String exportAmt = CommonUtils.toString(expAmtMap.get(G_CUR_P01.EXPORT_AMOUNT));
            exportAmtValue = exportAmtValue.add(new BigDecimal(exportAmt));
            exportAmtValue = new BigDecimal(formatter.format(exportAmtValue));
            expAmtMap = gCurP01.convertCurrency(billCur, subTotalAmt, expCur, curRate);
            exportAmt = CommonUtils.toString(expAmtMap.get(G_CUR_P01.EXPORT_AMOUNT));
            exportAmtValue = exportAmtValue.add(new BigDecimal(exportAmt));
            exportAmtValue = new BigDecimal(formatter.format(exportAmtValue));*/
            
            // EXPORT_CUR
            inputData.put("exportCur", expCur);
            // EXPORT_AMOUNT
            inputData.put("exportAmount", exportAmtValue);
            // CUR_RATE
            inputData.put("curRate", curRate);
            // FIXED_FOREX
            inputData.put("fixedForex", inputHeaderInfo.getFixedForex());
        } else {
            // EXPORT_CUR
            inputData.put("exportCur", inputHeaderInfo.getBillCurrency());
            // EXPORT_AMOUNT
            inputData.put("exportAmount", inputHeaderInfo.getBillAmount());
            // CUR_RATE
            inputData.put("curRate", 1);
            // FIXED_FOREX
            inputData.put("fixedForex", 0);
        }
    }
    
    
    /**
     * set data to inputData for edit model
     * @param inputHeaderInfo
     * @param inputData
     * @param idAudit
     * @param idUser
     */
    private void setinputDataValueForEdit(T_BIL_HeaderInfo inputHeaderInfo , 
            HashMap<String, Object> inputData , 
            Integer idAudit , String idUser){
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
        // CUST_NAME
        inputData.put("custName", inputHeaderInfo.getCustName());
        // SALUTATION
        inputData.put("salutation", inputHeaderInfo.getSalutation());
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
        // CONTACT_NAME
        inputData.put("contactName", inputHeaderInfo.getContactName());
        // CONTACT_EMAIL
        inputData.put("contactEmail", inputHeaderInfo.getContactEmail());
     // CONTACT_EMAILCC
        inputData.put("contactEmailCC", inputHeaderInfo.getContactEmailCC());
        //DUE_DATE
        inputData.put("contactDueDate", CommonUtils.parseToDate(inputHeaderInfo.getContactDueDate(), "dd/MM/yyyy"));
        //DELIVERY
        inputData.put("contactDelivery", inputHeaderInfo.getContactDelivery());
        //DELIVERYEMAIL
        inputData.put("contactDeliveryEmail", inputHeaderInfo.getContactDeliveryEmail());
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
        // TERM_DAY
        inputData.put("termDays", inputHeaderInfo.getTermDays());
        // GST_PERCENT
        inputData.put("gstPercent", inputHeaderInfo.getGstPercent());
        // GST_AMOUNT
        inputData.put("gstAmount", inputHeaderInfo.getGstAmount());
        // BILL_AMOUNT
        inputData.put("billAmount", inputHeaderInfo.getBillAmount());
        // ID_LOGIN
        inputData.put("idLogin", idUser);
        // ID_AUDIT
        inputData.put("idAudit", idAudit);
        // PAY_METHOD
        inputData.put("payMethod", inputHeaderInfo.getPayMethod());
        
        String billCur = CommonUtils.toString(inputHeaderInfo.getBillCurrency()).trim();
        String expCur = CommonUtils.toString(inputHeaderInfo.getExportCur()).trim();
        if (!CommonUtils.isEmpty(billCur)
                && !CommonUtils.isEmpty(expCur)
                && !"-".equals(expCur)
                && !billCur.equals(expCur)) {
            String curRate = CommonUtils.toString(inputHeaderInfo.getCurRate()).trim();
            //$CurRate = null or $CurRate = 0, $CurRate=1
            if (CommonUtils.isEmpty(curRate)) {
                curRate = "1";
            } else {
                if(Double.parseDouble(curRate)==0.0) {
                    curRate = "1";
                }
            }
            //calculated Export Amount
            
            DecimalFormat formatter = new DecimalFormat("0.00");
            formatter.setRoundingMode(RoundingMode.HALF_UP);
            BigDecimal exportAmtValue = new BigDecimal("0");
            G_CUR_P01 gCurP01 = new G_CUR_P01(queryDAO);
            List<T_BIL_DetailInfo> billDetail = inputHeaderInfo.getBilDetail();
            if(billDetail!=null&&billDetail.size()>0){
                Double gstPercentDouble = CommonUtils.toDouble(inputHeaderInfo.getGstPercent())/100;
                String tempAmt;
                List<T_BIL_DBean> tempSubPlan;
                for(T_BIL_DetailInfo tempT_BIL_DetailInfo:billDetail){
                    tempSubPlan = tempT_BIL_DetailInfo.getSubPlanBean();
                    if(tempSubPlan!=null&&tempSubPlan.size()>0){
                        for(T_BIL_DBean tempPlan:tempSubPlan){
                            String itemAmtString = tempPlan.getItemAmt();
                            double itemAmtDouble = CommonUtils.toDouble(itemAmtString);
                            if("1".equals(tempPlan.getApplyGst())){
                                tempAmt = CommonUtils.toString(gCurP01.convertCurrency(billCur, itemAmtDouble *gstPercentDouble, expCur, curRate).get(G_CUR_P01.EXPORT_AMOUNT));
                                exportAmtValue = exportAmtValue.add(new BigDecimal(tempAmt));
                            }
                            tempAmt = CommonUtils.toString(gCurP01.convertCurrency(billCur, itemAmtDouble, expCur, curRate).get(G_CUR_P01.EXPORT_AMOUNT));
                            exportAmtValue = exportAmtValue.add(new BigDecimal(tempAmt));
                            exportAmtValue = new BigDecimal(formatter.format(exportAmtValue));
                        }
                    }
                }
            }
            /*String gstAmountStr = inputHeaderInfo.getGstAmount();
            String subTotalAmtStr = inputHeaderInfo.getSubTotalAmt();
            double gstAmount = Double.parseDouble(gstAmountStr);
            double subTotalAmt = Double.parseDouble(subTotalAmtStr);
            Map<String, Object> expAmtMap = gCurP01.convertCurrency(billCur, gstAmount, expCur, curRate);
            String exportAmt = CommonUtils.toString(expAmtMap.get(G_CUR_P01.EXPORT_AMOUNT));
            exportAmtValue = exportAmtValue.add(new BigDecimal(exportAmt));
            exportAmtValue = new BigDecimal(formatter.format(exportAmtValue));
            expAmtMap = gCurP01.convertCurrency(billCur, subTotalAmt, expCur, curRate);
            exportAmt = CommonUtils.toString(expAmtMap.get(G_CUR_P01.EXPORT_AMOUNT));
            exportAmtValue = exportAmtValue.add(new BigDecimal(exportAmt));
            exportAmtValue = new BigDecimal(formatter.format(exportAmtValue));
            */
            // EXPORT_CUR
            inputData.put("exportCur", expCur);
            // EXPORT_AMOUNT
            inputData.put("exportAmount", exportAmtValue);
            // CUR_RATE
            inputData.put("curRate", curRate);
            // FIXED_FOREX
            inputData.put("fixedForex", inputHeaderInfo.getFixedForex());
        } else {
            // EXPORT_CUR
            inputData.put("exportCur", inputHeaderInfo.getBillCurrency());
            // EXPORT_AMOUNT
            inputData.put("exportAmount", inputHeaderInfo.getBillAmount());
            // CUR_RATE
            inputData.put("curRate", 1);
            // FIXED_FOREX
            inputData.put("fixedForex", 0);
        }
        
        // update header
        updateDAO.execute("B_BIL.updateHeaderInfo", inputData);
    }
    /**
     * update T_BIL_D
     * @param inputHeaderInfo
     */
    private void updateT_BIL_D(T_BIL_HeaderInfo inputHeaderInfo,boolean isEditFlg, String idRef, String idUser, Integer idAudit) {
        if (isEditFlg) {
            updateDAO.execute("B_BIL.deleteT_BIL_D", idRef);
        }
        List<T_BIL_DetailInfo> services = inputHeaderInfo.getBilDetail();
        if (services!=null && 0<services.size()) {
            for(T_BIL_DetailInfo service : services) {
                String serviceItemCat = service.getItemCat();
                HashMap<String, Object> inputDetailData = new HashMap<String, Object>();
                // ID_REF
                inputDetailData.put("idRef", idRef);
                // ID_LOGIN
                inputDetailData.put("idLogin", idUser);
                // ID_AUDIT
                inputDetailData.put("idAudit", idAudit);
                
                //Not Billing Item
                if ("0".equals(serviceItemCat)) {
                    //not Billing Item set value
                    notBillItemT_BIL_D(inputDetailData, service);
                    //insert T_BIL_D
                    updateDAO.execute("B_BIL.insertDetailInfo1", inputDetailData);
                } else {
                    //Billing Item Level is 0 value
                    billItemServiceT_BIL_D(inputDetailData, service);
                    //insert T_BIL_D
                    updateDAO.execute("B_BIL.insertDetailInfo1", inputDetailData);
                    
                    //insert Sub Plan(ITEM_LEVEL is 1)
                    List<T_BIL_DBean> subPlans = service.getSubPlanBean();
                    if(subPlans!=null && 0<subPlans.size()) {
                        for(T_BIL_DBean subPlan : subPlans) {
                            inputDetailData = new HashMap<String, Object>();
                            // ID_REF
                            inputDetailData.put("idRef", idRef);
                            // ID_LOGIN
                            inputDetailData.put("idLogin", idUser);
                            // ID_AUDIT
                            inputDetailData.put("idAudit", idAudit);
                            // Billing Item Level is 1 value
                            billItemSubPlanT_BIL_D(inputDetailData, subPlan, inputHeaderInfo);
                            //insert T_BIL_D
                            updateDAO.execute("B_BIL.insertDetailInfo1", inputDetailData);
                        }
                    }
                }
            }
        }
    }
    
    /**
     * not Billing Item set value
     * @param inputDetailData
     * @param service
     */
    private void notBillItemT_BIL_D(HashMap<String, Object> inputDetailData, T_BIL_DetailInfo service){
        // ITEM_ID
        inputDetailData.put("itemId", service.getItemId());
        // ITEM_LEVEL
        inputDetailData.put("itemLevel", "0");
        // ITEM_NO
        inputDetailData.put("itemNo", service.getItemNo());
        // ITEM_DESC
        inputDetailData.put("itemDesc", service.getItemDesc());
        // ITEM_QTY
        inputDetailData.put("itemQty", "0");
        // ITEM_UPRICE
        inputDetailData.put("itemUprice", "0");
        // ITEM_AMT
        inputDetailData.put("itemAtm", "0");
        inputDetailData.put("itemGst", "0");
        // ITEM_EXPORT_AMT
        inputDetailData.put("itemExportAmt", "0");
        // APPLY_GST
        inputDetailData.put("applyGst", "0");
        // IS_DISPLAY
        inputDetailData.put("isDisplay", "0");
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
        //IS_DISPLAY_JOBNO
        inputDetailData.put("isDisplayJobNo", "0");
        // IS_DELETED
        inputDetailData.put("isDeleted", "0");
        //ITEM_CAT
        inputDetailData.put("itemCat", "0");
        //ITEM_TYPE
        inputDetailData.put("itemType", "N");
        //IS_DISPLAY_MIN_SVC
        inputDetailData.put("isDisplayMinSvc", "0");
        //MIN_SVC_FROM
        inputDetailData.put("minSvcFrom", null);
        //MIN_SVC_TO
        inputDetailData.put("minSvcTo", null);
        //#154 start
        //ITEM_DISC_AMT
        inputDetailData.put("itemDisc", "0");
        //ITEM_SUBTOTAL
        inputDetailData.put("itemSubTotal", "0");
        //TAX_RATE
        inputDetailData.put("taxRate", "0");
        //TAX_CODE
        inputDetailData.put("taxCode", "");
        //ITEM_EXPORT_GST
        inputDetailData.put("itemExportGST", "0");
        //DISPLAY_DISC
        inputDetailData.put("displayDiscount", "0");
        //CUST_PO
        inputDetailData.put("poNo", null);
        //#154 end
    }
    
    /**
     * Billing Item Level is 0 value
     * @param inputDetailData
     * @param service
     */
    private void billItemServiceT_BIL_D(HashMap<String, Object> inputDetailData, T_BIL_DetailInfo service) {
        DecimalFormat formatter = new DecimalFormat("0.00");
        // ITEM_ID
        inputDetailData.put("itemId", service.getItemId());
        // ITEM_LEVEL
        inputDetailData.put("itemLevel", "0");
        // ITEM_NO
        inputDetailData.put("itemNo", service.getItemNo());
        // ITEM_DESC
        inputDetailData.put("itemDesc", service.getItemDesc());
        // ITEM_QTY
        inputDetailData.put("itemQty", CommonUtils.toLong(service.getItemQty()));
        // ITEM_UPRICE
        inputDetailData.put("itemUprice",
                formatter.format(new BigDecimal(service.getItemUprice())));
        // ITEM_AMT
        inputDetailData.put("itemAtm", formatter.format(new BigDecimal(service.getItemAmt())));
        // ITEM_GST
        //inputDetailData.put("itemGst", "0");
        inputDetailData.put("itemGst", service.getItemGst());
        
        // ITEM_EXPORT_AMT
        inputDetailData.put("itemExportAmt", "0");
        // #164 start
        // APPLY_GST
        //inputDetailData.put("applyGst", "0");
        inputDetailData.put("applyGst", service.getApplyGst());
        // #164 end
        // IS_DISPLAY
        inputDetailData.put("isDisplay", service.getIsDisplay());
        // ID_CUST_PLAN
        inputDetailData.put("idCustPlan", service.getIdCustPlan());
        // ID_CUST_PLAN_GRP
        inputDetailData.put("idCustPlanGrp", service.getIdCustPlanGrp());
        // ID_CUST_PLAN_LINK
        inputDetailData.put("idCustPlanLink", service.getIdCustPlanLink());
        // SVC_LEVEL1
        inputDetailData.put("svcLevel1", null);
        // SVC_LEVEL2
        inputDetailData.put("svcLevel2", null);
        // BILL_FROM
        String billFrom = CommonUtils.toString(service.getBillFrom());
        if(!CommonUtils.isEmpty(billFrom)) {
            inputDetailData.put("billFrom", CommonUtils.parseToDate(billFrom, "dd/MM/yyyy"));
        } else {
            inputDetailData.put("billFrom", null);
        }
        // BILL_TO
        String billTo = CommonUtils.toString(service.getBillTo());
        if(!CommonUtils.isEmpty(billTo)) {
            inputDetailData.put("billTo", CommonUtils.parseToDate(billTo, "dd/MM/yyyy"));
        } else {
            inputDetailData.put("billTo", null);
        }
        // JOB_NO
        inputDetailData.put("jobNo", null);
        //IS_DISPLAY_JOBNO
        inputDetailData.put("isDisplayJobNo", "0");
        // IS_DELETED
        inputDetailData.put("isDeleted", "0");
        //ITEM_CAT
        inputDetailData.put("itemCat", service.getItemCat());
        //ITEM_TYPE
        inputDetailData.put("itemType", service.getItemType());
        //IS_DISPLAY_MIN_SVC
        inputDetailData.put("isDisplayMinSvc", service.getIsDisplayMinSvc());
        //MIN_SVC_FROM
        String minSvcFrom = CommonUtils.toString(service.getMinSvcFrom());
        if(!CommonUtils.isEmpty(minSvcFrom)) {
            inputDetailData.put("minSvcFrom", CommonUtils.parseToDate(minSvcFrom, "dd/MM/yyyy"));
        } else {
            inputDetailData.put("minSvcFrom", null);
        }
        //MIN_SVC_TO
        String minSvcTo = CommonUtils.toString(service.getMinSvcTo());
        if(!CommonUtils.isEmpty(minSvcTo)) {
            inputDetailData.put("minSvcTo", CommonUtils.parseToDate(minSvcTo, "dd/MM/yyyy"));
        } else {
            inputDetailData.put("minSvcTo", null);
        }
        
      //#154 start
        //ITEM_DISC_AMT
        inputDetailData.put("itemDisc", service.getItemDisc());
        //ITEM_SUBTOTAL
        inputDetailData.put("itemSubTotal", service.getItemSubTotal());
        //TAX_RATE
        inputDetailData.put("taxRate", service.getTaxRate());
        //TAX_CODE
        inputDetailData.put("taxCode", service.getTaxCode());
        //ITEM_EXPORT_GST
        inputDetailData.put("itemExportGST", service.getItemExportGST());
        //DISPLAY_DISC
        inputDetailData.put("displayDiscount", service.getDisplayDiscount());
        //CUST_PO
        inputDetailData.put("poNo", service.getPoNo());
        //#154 end
    }
    
    /**
     * Billing Item Level is 1 value
     * @param inputDetailData
     * @param subPlan
     */
    private void billItemSubPlanT_BIL_D(HashMap<String, Object> inputDetailData, T_BIL_DBean subPlan, T_BIL_HeaderInfo inputHeaderInfo) {
        DecimalFormat formatter = new DecimalFormat("0.00");
        formatter.setRoundingMode(RoundingMode.HALF_UP);
        // ITEM_ID
        inputDetailData.put("itemId", subPlan.getItemId());
        // ITEM_LEVEL
        inputDetailData.put("itemLevel", "1");
        // ITEM_NO
        inputDetailData.put("itemNo", subPlan.getItemNo());
        // ITEM_DESC
        inputDetailData.put("itemDesc", subPlan.getItemDesc());
        // ITEM_QTY
        inputDetailData.put("itemQty", CommonUtils.toLong(subPlan.getItemQty()));
        // ITEM_UPRICE
        inputDetailData.put("itemUprice", formatter.format(new BigDecimal(subPlan.getItemUprice())));
        // ITEM_AMT
        inputDetailData.put("itemAtm", formatter.format(new BigDecimal(subPlan.getItemAmt())));
        // ITEM_GST
        String applyGst = CommonUtils.toString(subPlan.getApplyGst()).trim();
        if (!"0".equals(applyGst)) {
            inputDetailData.put("itemGst", formatter.format(new BigDecimal(subPlan.getItemGst())));
        } else {
            inputDetailData.put("itemGst", formatter.format(new BigDecimal("0")));
        }
        
        String billCur = CommonUtils.toString(inputHeaderInfo.getBillCurrency()).trim();
        String expCur = CommonUtils.toString(inputHeaderInfo.getExportCur()).trim();
        BigDecimal exportAmtValue = new BigDecimal("0");
        if (!CommonUtils.isEmpty(billCur)
                && !CommonUtils.isEmpty(expCur)
                && !"-".equals(expCur)
                && !billCur.equals(expCur)) {
             String curRate = CommonUtils.toString(inputHeaderInfo.getCurRate()).trim();
             //$CurRate = null or $CurRate = 0, $CurRate=1
             if (CommonUtils.isEmpty(curRate)) {
                 curRate = "1";
             } else {
                 if(Double.parseDouble(curRate)==0.0) {
                     curRate = "1";
                 }
             }
            String itemAmtString = subPlan.getItemAmt();
            double itemAmtDouble = CommonUtils.toDouble(itemAmtString);
            String tempAmt;
            G_CUR_P01 gCurP01 = new G_CUR_P01(queryDAO);
            Double gstPercentDouble = CommonUtils.toDouble(inputHeaderInfo.getGstPercent())/100;
            if ("1".equals(applyGst)) {
                tempAmt = CommonUtils.toString(gCurP01.convertCurrency(billCur, itemAmtDouble * gstPercentDouble, expCur, curRate).get(G_CUR_P01.EXPORT_AMOUNT));
                exportAmtValue = exportAmtValue.add(new BigDecimal(tempAmt));
            }
            tempAmt = CommonUtils.toString(gCurP01.convertCurrency(billCur, itemAmtDouble, expCur, curRate).get(G_CUR_P01.EXPORT_AMOUNT));
            exportAmtValue = exportAmtValue.add(new BigDecimal(tempAmt));
            exportAmtValue = new BigDecimal(formatter.format(exportAmtValue));
        } else {
            if ("1".equals(applyGst)) {
                exportAmtValue = exportAmtValue.add(new BigDecimal(subPlan.getItemAmt())).add(new BigDecimal(subPlan.getItemGst()));
            } else {
                exportAmtValue = exportAmtValue.add(new BigDecimal(subPlan.getItemAmt()));
            }
        }
        // ITEM_EXPORT_AMT
        //inputDetailData.put("itemExportAmt", formatter.format(exportAmtValue));
        inputDetailData.put("itemExportAmt", subPlan.getItemExportAmt());
        // APPLY_GST
        inputDetailData.put("applyGst", subPlan.getApplyGst());
        // IS_DISPLAY
        inputDetailData.put("isDisplay", subPlan.getIsDisplay());
        // ID_CUST_PLAN
        inputDetailData.put("idCustPlan", subPlan.getIdCustPlan());
        // ID_CUST_PLAN_GRP
        inputDetailData.put("idCustPlanGrp", subPlan.getIdCustPlanGrp());
        // ID_CUST_PLAN_LINK
        inputDetailData.put("idCustPlanLink", subPlan.getIdCustPlanLink());
        // SVC_LEVEL1
        inputDetailData.put("svcLevel1", subPlan.getSvcLevel1());
        // SVC_LEVEL2
        inputDetailData.put("svcLevel2", subPlan.getSvcLevel2());
        // BILL_FROM
        String billFrom = CommonUtils.toString(subPlan.getBillFrom());
        if(!CommonUtils.isEmpty(billFrom)) {
            inputDetailData.put("billFrom", CommonUtils.parseToDate(billFrom, "dd/MM/yyyy"));
        } else {
            inputDetailData.put("billFrom", null);
        }
        // BILL_TO
        String billTo = CommonUtils.toString(subPlan.getBillTo());
        if(!CommonUtils.isEmpty(billTo)) {
            inputDetailData.put("billTo", CommonUtils.parseToDate(billTo, "dd/MM/yyyy"));
        } else {
            inputDetailData.put("billTo", null);
        }
        // JOB_NO
        inputDetailData.put("jobNo", subPlan.getJobNo());
        String isDisplayJobNo = CommonUtils.toString(subPlan.getIsDisplayJobNo());
        if (CommonUtils.isEmpty(isDisplayJobNo)) {
            isDisplayJobNo = "0";
        }
        //IS_DISPLAY_JOBNO
        inputDetailData.put("isDisplayJobNo", isDisplayJobNo);
        // IS_DELETED
        inputDetailData.put("isDeleted", "0");
        //ITEM_CAT
        inputDetailData.put("itemCat", subPlan.getItemCat());
        //ITEM_TYPE
        inputDetailData.put("itemType", subPlan.getItemType());
        //IS_DISPLAY_MIN_SVC
        inputDetailData.put("isDisplayMinSvc", subPlan.getIsDisplayMinSvc());
        //MIN_SVC_FROM
        String minSvcFrom = CommonUtils.toString(subPlan.getMinSvcFrom());
        if(!CommonUtils.isEmpty(minSvcFrom)) {
            inputDetailData.put("minSvcFrom", CommonUtils.parseToDate(minSvcFrom, "dd/MM/yyyy"));
        } else {
            inputDetailData.put("minSvcFrom", null);
        }
        //MIN_SVC_TO
        String minSvcTo = CommonUtils.toString(subPlan.getMinSvcTo());
        if(!CommonUtils.isEmpty(minSvcTo)) {
            inputDetailData.put("minSvcTo", CommonUtils.parseToDate(minSvcTo, "dd/MM/yyyy"));
        } else {
            inputDetailData.put("minSvcTo", null);
        }
        //#154 start
        //ITEM_DISC_AMT
        inputDetailData.put("itemDisc", subPlan.getItemDisc());
        //ITEM_SUBTOTAL
        inputDetailData.put("itemSubTotal", subPlan.getItemSubTotal());
        //TAX_RATE
        inputDetailData.put("taxRate", subPlan.getTaxRate());
        //TAX_CODE
        inputDetailData.put("taxCode", subPlan.getTaxCode());
        //ITEM_EXPORT_GST
        inputDetailData.put("itemExportGST", subPlan.getItemExportGST());
        //DISPLAY_DISC
        inputDetailData.put("displayDiscount", subPlan.getDisplayDiscount());
        //CUST_PO
        inputDetailData.put("poNo", subPlan.getPoNo());
        //#154 end
    }
    /**
     * update T_BAC_H TOTAL_AMT_DUE For new model
     * 
     * @param inputData inputData
     */
    private void updateTBACHForNew(HashMap<String, Object> inputData){
        // get M_GSET_D info
        List<Object> mGSetDInfoList = (List<Object>)this.queryDAO
                .executeForObjectList("B_BIL.getMGSetDInfo", null);
        if (!CommonUtils.isEmpty(mGSetDInfoList) && mGSetDInfoList.contains(BAC)) {
            DecimalFormat formatter = new DecimalFormat("0.00");
            // BILL_TYPE
            String billType = CommonUtils.toString(inputData.get("billType"));
            //BILL_AMOUNT
            BigDecimal cBillAmount =  new BigDecimal(CommonUtils.toString(inputData.get("billAmount")));
            BigDecimal pBillAmount = new BigDecimal("0");
            if ("CN".equals(billType)) {
                pBillAmount = new BigDecimal("0").subtract(pBillAmount);
                cBillAmount = new BigDecimal("0").subtract(cBillAmount);
            }
            inputData.put("pBillAmount", formatter.format(pBillAmount));
            inputData.put("cBillAmount", formatter.format(cBillAmount));
            updateDAO.execute("B_BIL.updateBAC", inputData);
            
            String totalAmountDue = queryDAO.executeForObject("B_BIL.getTotalAmount", inputData, String.class);
            inputData.put("totalAmountDue", totalAmountDue);
            
            updateDAO.execute("B_BIL.insertBilS", inputData);
        }
    }
   
    /**
     * update T_BAC_H info For edit model
     * @param inputData inputData
     * @param preBilHData pre T_BIL_H data
     */
    private void updateTBACHForEdit(HashMap<String, Object> inputData ,
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
            
            String totalAmountDue = queryDAO.executeForObject("B_BIL.getTotalAmount", inputData, String.class);
            inputData.put("totalAmountDue", totalAmountDue);
            
            updateDAO.execute("B_BIL.updateBilS", inputData);
        }
    }
    
    /**
     * check info
     * @param errors BLogicMessages info
     * @param param input value
     */
    private void checkMandatoryField(BLogicMessages errors, RP_B_BIL_S03_03_02Input param) {
        // header info
        T_BIL_HeaderInfo inputHeaderInfo = param.getBilHeaderInfo();
        
        String billTypeName = "";
        if ("IN".equals(inputHeaderInfo.getBillType())) {
            // invoice INVNO
            billTypeName = "Invoice";
        } else if ("DN".equals(inputHeaderInfo.getBillType())) {
            // debit note DBTNO
            billTypeName = "Debit Note";
        } else {
            // credit note CDTNO
            billTypeName = "Credit Note";
        }
        
        G_CSB_P01_Check gCsbP01Check = new G_CSB_P01_Check(queryDAO);
        G_CSB_P01_CheckInput gCsbP01CheckInput = new G_CSB_P01_CheckInput();
        gCsbP01CheckInput.setMessageParam1("save");
        gCsbP01CheckInput.setMessageParam2(billTypeName);
        gCsbP01CheckInput.setMessageParam3("Batch Auto Offset Cash Book");
        G_CSB_P01_CheckOutput gCsbP01CheckOutput = gCsbP01Check.execute(gCsbP01CheckInput);
        boolean resultFlg = gCsbP01CheckOutput.isResultFlg();
        
        if (!resultFlg) {
            String errorMsg = gCsbP01CheckOutput.getMessageContext();
            errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage(errorMsg, false));
        } else {
          //idCust
            String idCust = CommonUtils.toString(inputHeaderInfo.getIdCust()).trim();
            //adrType
            String adrType = CommonUtils.toString(inputHeaderInfo.getAdrType()).trim();
            //dateReq
            String dateReq = CommonUtils.toString(inputHeaderInfo.getDateReq()).trim();
            //billCurrency
            String billCurrency = CommonUtils.toString(inputHeaderInfo.getBillCurrency()).trim();
            //address1
            String address1 = CommonUtils.toString(inputHeaderInfo.getAddress1()).trim();
            //address2
            String address2 = CommonUtils.toString(inputHeaderInfo.getAddress2()).trim();
            //address3
            String address3 = CommonUtils.toString(inputHeaderInfo.getAddress3()).trim();
            //curRate
            String curRate = CommonUtils.toString(inputHeaderInfo.getCurRate()).trim();
            //idCust check
            if (CommonUtils.isEmpty(idCust)) {
                errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage(ERROR_ERR1SC005, new Object[] {"Customer Name"}));
            }
            //adrType check
            if (CommonUtils.isEmpty(adrType)) {
                errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage(ERROR_ERR1SC005, new Object[] {"Billing Address"}));
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
            List<Object> mGSetDInfoList = (List<Object>) queryDAO.executeForObjectList("B_BIL.getMGSetDInfo", null);
            if (!CommonUtils.isEmpty(mGSetDInfoList)) {
                if (mGSetDInfoList.contains(BAC)) {
                    // Billing Account No
                    String billAcc = CommonUtils.toString(inputHeaderInfo.getBillAcc());
                    // Billing Account No is empty check
                    if (CommonUtils.isEmpty(billAcc)) {
                        errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage(ERROR_ERR1SC005, new Object[] { BILL_ACCOUNT_NO }));
                    }
                }
            }
            //billCurrency check
            if (CommonUtils.isEmpty(billCurrency)) {
                errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage(
                        ERROR_ERR1SC005, new Object[] {"Currency"}));
            }
            
            //address check
            if (CommonUtils.isEmpty(address1)
                    &&CommonUtils.isEmpty(address2)
                    &&CommonUtils.isEmpty(address3)) {
                errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage(
                        ERROR_ERR1SC093, new Object[] {"Billing Address"}));
            }
            
            if (!Pattern.matches("^\\d{0,4}([.]\\d{0,8})?$", curRate)) {
                errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage(
                        "errors.ERR1SC012", new Object[] {"CurRate"}));
            }
            //check Detail
            List<T_BIL_DetailInfo> bilDetail = inputHeaderInfo.getBilDetail();
            if (bilDetail==null || bilDetail.size()==0){
                errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage(
                        ERROR_ERR1SC068, new Object[] {""}));
            }else {
                boolean detailErrorFlg = false;
                for(T_BIL_DetailInfo serviceDetail : bilDetail) {
                    if(!detailErrorFlg) {
                        String serviceItemDesc = CommonUtils.toString(serviceDetail.getItemDesc()).trim();
                        String serviceIsDisplay = CommonUtils.toString(serviceDetail.getIsDisplay()).trim();
                        String serviceItemCat = CommonUtils.toString(serviceDetail.getItemCat()).trim();
                        if (CommonUtils.isEmpty(serviceItemDesc)) {
                            detailErrorFlg = true;
                            errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage(ERROR_ERR1SC005, new Object[] {"Billing Description"}));
                        }
                        if ("1".equals(serviceItemCat)) {
                            String serviceBillFrom = CommonUtils.toString(serviceDetail.getBillFrom()).trim();
                            String serviceBillTo = CommonUtils.toString(serviceDetail.getBillTo()).trim();
                            if (CommonUtils.isEmpty(serviceBillFrom)) {
                                detailErrorFlg = true;
                                errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage(ERROR_ERR1SC005, new Object[] {"Billing Period From"}));
                            }
                            if (CommonUtils.isEmpty(serviceBillTo)) {
                                detailErrorFlg = true;
                                errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage(ERROR_ERR1SC005, new Object[] {"Billing Period To"}));
                            }
                        }
                        if("1".equals(serviceIsDisplay)) {
                            String serviceItemQty = CommonUtils.toString(serviceDetail.getItemQty()).trim();
                            String serviceItemUprice = CommonUtils.toString(serviceDetail.getItemUprice()).trim();
                            if (CommonUtils.isEmpty(serviceItemQty)) {
                                detailErrorFlg = true;
                                errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage(ERROR_ERR1SC005, new Object[] {"Quantity"}));
                            }
                            if (CommonUtils.isEmpty(serviceItemUprice)) {
                                detailErrorFlg = true;
                                errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage(ERROR_ERR1SC005, new Object[] {"Unit Price"}));
                            }
                        }
                        //sub Plan item check
                        if(!detailErrorFlg) {
                            List<T_BIL_DBean> subPlanDetails = serviceDetail.getSubPlanBean();
                            if(subPlanDetails!=null && 0<subPlanDetails.size()) {
                                for(T_BIL_DBean subPlanDetail : subPlanDetails) {
                                    if (!detailErrorFlg) {
                                        String subPlanItemDesc = CommonUtils.toString(subPlanDetail.getItemDesc()).trim();
                                        String subPlanIsDisplay = CommonUtils.toString(subPlanDetail.getIsDisplay()).trim();
                                        if (CommonUtils.isEmpty(subPlanItemDesc)) {
                                            detailErrorFlg = true;
                                            errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage(ERROR_ERR1SC005, new Object[] {"Billing Description"}));
                                        }
                                        if("1".equals(subPlanIsDisplay)) {
                                            String subPlanItemQty = CommonUtils.toString(subPlanDetail.getItemQty()).trim();
                                            String subPlanItemUprice = CommonUtils.toString(subPlanDetail.getItemUprice()).trim();
                                            if (CommonUtils.isEmpty(subPlanItemQty)) {
                                                detailErrorFlg = true;
                                                errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage(ERROR_ERR1SC005, new Object[] {"Quantity"}));
                                            }
                                            if (CommonUtils.isEmpty(subPlanItemUprice)) {
                                                detailErrorFlg = true;
                                                errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage(ERROR_ERR1SC005, new Object[] {"Unit Price"}));
                                            }
                                        }
                                    } else {
                                        return;
                                    }
                                }
                            }
                        }
                    } else {
                        return;
                    }
                }
            }
        }
    }
    
    /**
     * padding right space
     * @param str
     * @param len
     * @return
     */
    private static String paddingRightSpace(String str, int len) {
        StringBuffer sb = new StringBuffer();
        str = CommonUtils.toString(str);
        sb.append(str);
        for(int i=str.length();i<len;i++) {
            sb.append(" ");
        }
        return sb.toString();
    }
}