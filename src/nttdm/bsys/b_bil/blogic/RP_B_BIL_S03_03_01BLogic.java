/*
 * @(#)RP_B_BIL_S03_03_01BLogic.java
 *
 *
 */
package nttdm.bsys.b_bil.blogic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.b_bil.bean.T_BIL_DBean;
import nttdm.bsys.b_bil.bean.T_BIL_DetailInfo;
import nttdm.bsys.b_bil.bean.T_BIL_HeaderInfo;
import nttdm.bsys.b_bil.dto.RP_B_BIL_S03_03_01Input;
import nttdm.bsys.b_bil.dto.RP_B_BIL_S03_03_01Output;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.CommonUtils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.util.LabelValueBean;

/**
 * BusinessLogic class.
 * 
 * @author khungl0ng
 */
public class RP_B_BIL_S03_03_01BLogic extends AbstractRP_B_BIL_S03_03_01BLogic {

    /**
     * 
     * @param param RP_B_BIL_S03_03_01Input
     * @return ビジ�?スロジック�?�実行�?果�?BLogicResultインスタンス。
     */
    public BLogicResult execute(RP_B_BIL_S03_03_01Input param) {
        BLogicResult result = new BLogicResult();
        RP_B_BIL_S03_03_01Output outputDTO = new RP_B_BIL_S03_03_01Output();
        
        BillingSystemUserValueObject uvo = param.getUvo();
        T_BIL_HeaderInfo bilHeaderInfo = param.getBilHeaderInfo();
        
        String idRef = CommonUtils.toString(bilHeaderInfo.getIdRef());
        B_BIL_CommonUtil b_bilCommmonUtil = new B_BIL_CommonUtil(queryDAO, updateDAO);
        
        //new model
        if (CommonUtils.isEmpty(idRef)) {
            //Billing Type
            if (!CommonUtils.isEmpty(param.getForward_iv())) {
                bilHeaderInfo.setBillType("IN");
            } else if (!CommonUtils.isEmpty(param.getForward_dn())) {
                bilHeaderInfo.setBillType("DN");
            } else {
                bilHeaderInfo.setBillType("CN");
            }
            //Attn combo box Data
            List<LabelValueBean> listContact = this.queryDAO.executeForObjectList("B_BIL.getAllContact", bilHeaderInfo);
            
            bilHeaderInfo.setListContact(listContact);
            //Model flag
            bilHeaderInfo.setMode("new");
            //Date
            bilHeaderInfo.setDateCreated(CommonUtils.formatDate(Calendar.getInstance().getTime(), "dd/MM/yyyy"));
            //PreparedBy Value
            bilHeaderInfo.setPreparedBy(uvo.getId_user());
            bilHeaderInfo.setPreparedByName(uvo.getUser_name());
            //GST Amount
            bilHeaderInfo.setGstAmount("0");
            //Billing Amount
            bilHeaderInfo.setBillAmount("0");
        } else {
        	// #194 Modiy Start
        	if ("1".equals(bilHeaderInfo.getIsDuplicate())) {
                // new model
                bilHeaderInfo.setMode("new");
        	} else {
                // edit model
                bilHeaderInfo.setMode("edit");
        	}
            // #194 Modiy End
            //init Edit model data
            initDataForEdit(bilHeaderInfo);
        }
        
        // Add #156 Start
        String billCnAmtNegative = queryDAO.executeForObject("B_BTH.getBillCnAmtNegative", null, String.class);
        outputDTO.setBillCnAmtNegative(billCnAmtNegative);
        // Add #156 End
        //get the Combox Box Data
        b_bilCommmonUtil.getComboBoxsData(bilHeaderInfo);
        
        String defCurrency = CommonUtils.toString(queryDAO.executeForObject("B_BIL.selectDEF_CURRENCY", null, String.class));
        String currencyStd = CommonUtils.toString(queryDAO.executeForObject("B_BIL.selectCURRENCY_STD", null, String.class));
        bilHeaderInfo.setDefCurrency(defCurrency);
        bilHeaderInfo.setCurrencyStd(currencyStd);
        
        //#154 start
        String gstCheck = CommonUtils.toString(queryDAO.executeForObject("B_BIL.getM_GSET_D_SetValue", null, String.class));
        outputDTO.setGstCheck(gstCheck);
        //#154 end
        
        // #174 Start
        String billingPeriod = queryDAO.executeForObject("B_BIL.Get_IsDisplay_BillingPeriod", null, String.class);
        bilHeaderInfo.setBillingPeriod(billingPeriod);
        // #174 End
        
        outputDTO.setBilHeaderInfo(bilHeaderInfo);
        result.setResultObject(outputDTO);
        result.setResultString("success");
        return result;
    }
    
    /**
     * init Edit model data
     * @param bilHeaderInfo
     */
    private void initDataForEdit(T_BIL_HeaderInfo bilHeaderInfo) {
        String idRef = bilHeaderInfo.getIdRef();
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("idRef", idRef);
        HashMap<String, Object> headerInfo = (HashMap<String, Object>) queryDAO.executeForMap("B_BIL.getHeaderInfoForEdit", m);
        CommonUtils.fixAddress4(headerInfo);
        m.put("idCust", headerInfo.get("ID_CUST"));
        
        // get detail info
        List<HashMap<String, Object>> detailInfo = queryDAO.executeForObjectList("B_BIL.getDetailInfo", m);
        for(HashMap<String, Object> temp : detailInfo) {
        	String itemDesc = clobToString((Clob)temp.get("ITEM_DESC_TEXT"));
        	temp.put("ITEM_DESC_TEXT", itemDesc);
        }
        
        // get list contact
        String customerType = CommonUtils.toString(headerInfo.get("CUSTOMER_TYPE"));
        List<LabelValueBean> listContact = null;
        if ("CORP".equals(customerType)) {
            listContact = queryDAO.executeForObjectList("B_BIL.getAllContact", m);
            String contactType = CommonUtils.toString(headerInfo.get("CONTACT_TYPE")).trim();
            for(LabelValueBean bean: listContact) {
                if(contactType.equals(bean.getValue())){
                    headerInfo.put("CONTACT_NAME", bean.getLabel());
                    break;
                }
            }
        } else {
            listContact = queryDAO.executeForObjectList("B_BIL.getAllContact1", m);
            String contactName = CommonUtils.toString(headerInfo.get("CONTACT_NAME")).trim();
            if(!CommonUtils.isEmpty(contactName)){
                headerInfo.put("CONTACT_TYPE", CommonUtils.toString(headerInfo.get("ID_CUST")).trim());
            }
        }
        //Attn combo box Data
        bilHeaderInfo.setListContact(listContact);
        
        headerInfo.put("ADDRESS1", headerInfo.get("ADR_LINE1"));
        headerInfo.put("ADDRESS2", headerInfo.get("ADR_LINE2"));
        headerInfo.put("ADDRESS3", headerInfo.get("ADR_LINE3"));
        headerInfo.put("ADDRESS4", headerInfo.get("ADR_LINE4"));
        headerInfo.put("ZIP_CODE", headerInfo.get("M_CUST_ADR_ZIP_CODE"));
        headerInfo.put("COUNTRY", headerInfo.get("M_CUST_ADR_COUNTRY"));
        if ("CORP".equals(customerType)) {
            String telFaxContactFlg = CommonUtils.toString(queryDAO.executeForObject("B_BIL.selectTEL_FAX_CONTACT", null, String.class));
            if("1".equals(telFaxContactFlg)) {
                headerInfo.put("TEL_NO", headerInfo.get("M_CUST_CTC_TEL_NO"));
                headerInfo.put("FAX_NO", headerInfo.get("M_CUST_CTC_FAX_NO"));
            } else {
                headerInfo.put("TEL_NO", headerInfo.get("CO_TEL_NO"));
                headerInfo.put("FAX_NO", headerInfo.get("CO_FAX_NO"));
            }
            headerInfo.put("CONTACT_NAME", headerInfo.get("M_CUST_CTC_CONTACT_NAME"));
            headerInfo.put("CONTACT_EMAIL", headerInfo.get("M_CUST_CTC_EMAIL"));
            headerInfo.put("CONTACT_EMAILCC", headerInfo.get("M_CUST_CTC_EMAILCC"));
        } else {
            headerInfo.put("TEL_NO", headerInfo.get("HOME_TEL_NO"));
            headerInfo.put("FAX_NO", headerInfo.get("HOME_FAX_NO"));
            headerInfo.put("CONTACT_NAME", headerInfo.get(""));
            headerInfo.put("CONTACT_EMAIL", headerInfo.get("CO_EMAIL"));
            headerInfo.put("CONTACT_EMAILCC", headerInfo.get("CO_EMAILCC"));
        }
        //#143 start
        headerInfo.put("CONTACT_DUE_DATE", headerInfo.get("DUE_DATE"));
        headerInfo.put("CONTACT_DELIVERY", headerInfo.get("DELIVERY"));
        headerInfo.put("CONTACT_DELIVERY_EMAIL", headerInfo.get("DELIVERY_EMAIL"));
        headerInfo.put("CONTACT_TERM", headerInfo.get("TERM"));
        headerInfo.put("CONTACT_TERM_DAY", headerInfo.get("TERM_DAY"));
        //#143 end
        
        CommonUtils.fixAddress4(headerInfo, "COUNTRY");
        CommonUtils.fixAddress4(headerInfo);
        
        //init T_BIL_H Data
        initT_BIL_H(bilHeaderInfo, headerInfo);
        
        List<T_BIL_DetailInfo> bilDetail = new ArrayList<T_BIL_DetailInfo>();
        //initT_BIL_D data
        if (detailInfo!=null && 0<detailInfo.size()) {
            List<Map<String, Object>> sameServiceSubPlanInfo = new ArrayList<Map<String, Object>>();
            //loop record,one record include service and sub_plan info
            for (int i=0;i<detailInfo.size();i++) {
                Map<String, Object> serviceAndSubPlanInfo = detailInfo.get(i);
                String itemLevel = CommonUtils.toString(serviceAndSubPlanInfo.get("ITEM_LEVEL"));
                if(i==0) {
                    sameServiceSubPlanInfo.add(serviceAndSubPlanInfo);
                } else {
                    if("0".equals(itemLevel)) {
                        initT_BIL_D(sameServiceSubPlanInfo, bilDetail, bilHeaderInfo);
                        sameServiceSubPlanInfo = new ArrayList<Map<String, Object>>();
                        sameServiceSubPlanInfo.add(serviceAndSubPlanInfo);
                    } else {
                        sameServiceSubPlanInfo.add(serviceAndSubPlanInfo);
                    }
                }
            }
            initT_BIL_D(sameServiceSubPlanInfo, bilDetail, bilHeaderInfo);
        }
        
        bilHeaderInfo.setBilDetail(bilDetail);
    }
    
    /**
     * init T_BIL_H data
     * @param bilHeaderInfo
     * @param headerInfo
     */
    private void initT_BIL_H(T_BIL_HeaderInfo bilHeaderInfo, HashMap<String, Object> headerInfo) {
        //BILL_TYPE
        bilHeaderInfo.setBillType(CommonUtils.toString(headerInfo.get("BILL_TYPE")));
        //IS_MANUAL
        bilHeaderInfo.setIsManual(CommonUtils.toString(headerInfo.get("IS_MANUAL")));
        //BILL_ACC
        bilHeaderInfo.setBillAcc(CommonUtils.toString(headerInfo.get("BILL_ACC")));
        // #194 Edit Start
        if ("1".equals(bilHeaderInfo.getIsDuplicate())) {
        	//DATE_REQ
            bilHeaderInfo.setDateReq("");
        } else {
            //DATE_REQ
            bilHeaderInfo.setDateReq(CommonUtils.toString(headerInfo.get("DATE_REQ_FOM")));
        }
        // #194 Edit End
        //PAY_METHOD
        bilHeaderInfo.setPayMethod(CommonUtils.toString(headerInfo.get("PAY_METHOD")).trim());
        //ID_CUST
        bilHeaderInfo.setIdCust(CommonUtils.toString(headerInfo.get("ID_CUST")));
        //ADR_TYPE
        bilHeaderInfo.setAdrType(CommonUtils.toString(headerInfo.get("ADR_TYPE")));
        //CONTACT_TYPE
        bilHeaderInfo.setContactType(CommonUtils.toString(headerInfo.get("CONTACT_TYPE")));
        //ID_BIF
        bilHeaderInfo.setIdBif(CommonUtils.toString(headerInfo.get("ID_BIF_TXT")));
        //ID_QCS
        bilHeaderInfo.setIdQcs(CommonUtils.toString(headerInfo.get("ID_QCS_TXT")));
        //QUO_REF
        bilHeaderInfo.setQuoRef(CommonUtils.toString(headerInfo.get("QUO_REF_TXT")));
        //CUST_PO
        bilHeaderInfo.setCustPo(CommonUtils.toString(headerInfo.get("CUST_PO_TXT")));
        //ID_CONSULT
        bilHeaderInfo.setIdConsult(CommonUtils.toString(headerInfo.get("ID_CONSULT")));
        //TERM
        bilHeaderInfo.setTerm(CommonUtils.toString(headerInfo.get("TERM")));
        //BILL_CURRENCY
        bilHeaderInfo.setBillCurrency(CommonUtils.toString(headerInfo.get("BILL_CURRENCY")));
        //GST_PERCENT
        bilHeaderInfo.setGstPercent(CommonUtils.toString(headerInfo.get("GST_PERCENT")));
        //GST_AMOUNT
        bilHeaderInfo.setGstAmount(CommonUtils.toString(headerInfo.get("GST_AMOUNT")));
        //BILL_AMOUNT
        bilHeaderInfo.setBillAmount(CommonUtils.toString(headerInfo.get("BILL_AMOUNT")));
        //PAID_AMOUNT
        bilHeaderInfo.setPaidAmount(CommonUtils.toString(headerInfo.get("PAID_AMOUNT")));
        //LAST_PAYMENT_DATE
        //bilHeaderInfo.setLastPaymentDate(CommonUtils.formatDate(headerInfo.get("LAST_PAYMENT_DATE"), "dd/MM/yyyy"));
        //IS_SETTLED
        bilHeaderInfo.setIsSettled(CommonUtils.toString(headerInfo.get("IS_SETTLED")));
        //IS_DELETED
        bilHeaderInfo.setIsDeleted(CommonUtils.toString(headerInfo.get("IS_DELETED")));
        //IS_SINGPOST
        //bilHeaderInfo.setIsSingPost(CommonUtils.toString(headerInfo.get("IS_SINGPOST")));
        //IS_EXPORT
        //bilHeaderInfo.setIsExport(CommonUtils.toString(headerInfo.get("IS_EXPORT")));
        //IS_DISPLAY_EXP_AMT
        //bilHeaderInfo.setIsDisplayExpAmt(CommonUtils.toString(headerInfo.get("IS_DISPLAY_EXP_AMT")));
        //EXPORT_CUR
        bilHeaderInfo.setExportCur(CommonUtils.toString(headerInfo.get("EXPORT_CUR")));
        //CUR_RATE
        bilHeaderInfo.setCurRate(CommonUtils.toString(headerInfo.get("CUR_RATE")));
        //EXPORT_AMOUNT
        bilHeaderInfo.setExportAmount(CommonUtils.toString(headerInfo.get("EXPORT_AMOUNT")));
        //FIXED_FOREX
        bilHeaderInfo.setFixedForex(CommonUtils.toString(headerInfo.get("FIXED_FOREX")));
        //NO_PRINTED
        bilHeaderInfo.setNoPrinted(CommonUtils.toString(headerInfo.get("NO_PRINTED")));
        //IS_CLOSED
        bilHeaderInfo.setIsClosed(CommonUtils.toString(headerInfo.get("IS_CLOSED")));
        //ADDRESS1
        bilHeaderInfo.setAddress1(CommonUtils.toString(headerInfo.get("ADDRESS1")));
        //ADDRESS2
        bilHeaderInfo.setAddress2(CommonUtils.toString(headerInfo.get("ADDRESS2")));
        //ADDRESS3
        bilHeaderInfo.setAddress3(CommonUtils.toString(headerInfo.get("ADDRESS3")));
        //ADDRESS4
        bilHeaderInfo.setAddress4(CommonUtils.toString(headerInfo.get("ADDRESS4")));
        //TEL_NO
        bilHeaderInfo.setTelNo(CommonUtils.toString(headerInfo.get("TEL_NO")));
        //FAX_NO
        bilHeaderInfo.setFaxNo(CommonUtils.toString(headerInfo.get("FAX_NO")));
        //CONTACT_NAME
        bilHeaderInfo.setContactName(CommonUtils.toString(headerInfo.get("CONTACT_NAME")));
        //CONTACT_EMAIL
        bilHeaderInfo.setContactEmail(CommonUtils.toString(headerInfo.get("CONTACT_EMAIL")));
        //CONTACT_EMAILCC
        bilHeaderInfo.setContactEmailCC(CommonUtils.toString(headerInfo.get("CONTACT_EMAILCC")));
        // #194 Edit Start
        if ("1".equals(bilHeaderInfo.getIsDuplicate())) {
            //DUE_DATE
            bilHeaderInfo.setContactDueDate("");
        } else {
            //DUE_DATE
            bilHeaderInfo.setContactDueDate(CommonUtils.formatDate(headerInfo.get("CONTACT_DUE_DATE"), "dd/MM/yyyy"));
        }
        // #194 Edit End
        //DELIVERY
        bilHeaderInfo.setContactDelivery(CommonUtils.toString(headerInfo.get("CONTACT_DELIVERY")));
        //DELIVERYEMAIL
        bilHeaderInfo.setContactDeliveryEmail(CommonUtils.toString(headerInfo.get("CONTACT_DELIVERY_EMAIL")));
        //TERM
        bilHeaderInfo.setTerm(CommonUtils.toString(headerInfo.get("CONTACT_TERM")));
        //TERM_DAY
        bilHeaderInfo.setTermDays(CommonUtils.toString(headerInfo.get("CONTACT_TERM_DAY")));
        //Date
        bilHeaderInfo.setDateCreated(CommonUtils.toString(headerInfo.get("DATE_CREATED_FOM")));
        //PreparedBy Value
        bilHeaderInfo.setPreparedBy(CommonUtils.toString(headerInfo.get("PREPARED_BY")));
        bilHeaderInfo.setPreparedByName(CommonUtils.toString(headerInfo.get("PREPARED_BY_NAME")));
        //CUST_NAME
        bilHeaderInfo.setCustName(CommonUtils.toString(headerInfo.get("M_CUST_NAME")));
        //SALUTATION
        bilHeaderInfo.setSalutation(CommonUtils.toString(headerInfo.get("M_SALUTATION")));
        //CUSTOMER_TYPE
        bilHeaderInfo.setCustomerTypeFlag(CommonUtils.toString(headerInfo.get("CUSTOMER_TYPE")));
        
        //Bac info
//        Map<String, Object> bacHInfo = queryDAO.executeForMap("B_BIL.getT_BAC_H_InfoByID", bilHeaderInfo.getBillAcc());
//        String bacBillingCurrency = "";
//        String bacExpCurrency = "";
//        String bacFixedForex = "";
//        String bacCurRate = "";
//        if (bacHInfo!=null) {
//            bacBillingCurrency = CommonUtils.toString(bacHInfo.get("BILL_CURRENCY")).trim();
//            bacExpCurrency = CommonUtils.toString(bacHInfo.get("EXPORT_CURRENCY")).trim();
//            if ("-".equals(bacExpCurrency)) {
//                bacExpCurrency = "";
//            }
//            bacFixedForex = CommonUtils.toString(bacHInfo.get("FIXED_FOREX")).trim();
//        }
//        if (!CommonUtils.isEmpty(bacFixedForex)) {
//            bacCurRate = bacFixedForex;
//        } else {
//            G_CUR_P01 gCurP01 = new G_CUR_P01(queryDAO);
//            Map<String, Object> curRateMap = gCurP01.convertCurrency(bacBillingCurrency, 1, bacExpCurrency, bacFixedForex);
//            bacCurRate = CommonUtils.toString(curRateMap.get(G_CUR_P01.CUR_RATE));
//        }
//        bilHeaderInfo.setBacBillingCurrency(bacBillingCurrency);
//        bilHeaderInfo.setBacExpCurrency(bacExpCurrency);
//        bilHeaderInfo.setBacFixedForex(bacFixedForex);
//        bilHeaderInfo.setBacCurRate(bacCurRate);
    }
    
    /**
     * init T_BIL_D data
     * @param bilHeaderInfo
     * @param detailInfo
     */
	private void initT_BIL_D(List<Map<String, Object>> serviceSubPlanInfo,
			List<T_BIL_DetailInfo> bilDetail, T_BIL_HeaderInfo bilHeaderInfo) {
        if (serviceSubPlanInfo!=null && 0<serviceSubPlanInfo.size()) {
            //add Service
            T_BIL_DBean serviceDetail = mapInfoToBean(serviceSubPlanInfo.get(0), bilHeaderInfo);
            T_BIL_DetailInfo serviceBean = new T_BIL_DetailInfo();
            List<T_BIL_DBean> subPlanList = new ArrayList<T_BIL_DBean>();
            if(1<serviceSubPlanInfo.size()) {
                for(int i=1;i<serviceSubPlanInfo.size();i++) {
                    //Add Sub Plan
                    T_BIL_DBean subPlanDetail = mapInfoToBean(serviceSubPlanInfo.get(i), bilHeaderInfo);
                    subPlanList.add(subPlanDetail);
                }
            }
            //add subplan
            serviceBean.setSubPlanBean(subPlanList); 
            try {
                BeanUtils.copyProperties(serviceBean, serviceDetail);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //add service
            bilDetail.add(serviceBean);
        }
    }
   
    /**
     * copy map value to T_BIL_DBean
     * @param map
     * @return
     */
	private T_BIL_DBean mapInfoToBean(Map<String, Object> map,
			T_BIL_HeaderInfo bilHeaderInfo) {
        T_BIL_DBean tbilDBean = new T_BIL_DBean();
        //ITEM_ID
        tbilDBean.setItemId(CommonUtils.toString(map.get("ITEM_ID")));
        //ITEM_LEVEL
        tbilDBean.setItemLevel(CommonUtils.toString(map.get("ITEM_LEVEL")));
        //ITEM_NO
        tbilDBean.setItemNo(CommonUtils.toString(map.get("ITEM_NO_TEXT")));
        //ITEM_DESC
        tbilDBean.setItemDesc(CommonUtils.toString(map.get("ITEM_DESC_TEXT")));
        //ITEM_QTY
        tbilDBean.setItemQty(CommonUtils.toString(map.get("ITEM_QTY")));
        //ITEM_UPRICE
        tbilDBean.setItemUprice(CommonUtils.toString(map.get("ITEM_UPRICE")));
        //ITEM_AMT
        tbilDBean.setItemAmt(CommonUtils.toString(map.get("ITEM_AMT")));
        //ITEM_GST
        tbilDBean.setItemGst(CommonUtils.toString(map.get("ITEM_GST")));
        //APPLY_GST
        tbilDBean.setApplyGst(CommonUtils.toString(map.get("APPLY_GST")));
        //IS_DISPLAY
        tbilDBean.setIsDisplay(CommonUtils.toString(map.get("IS_DISPLAY")));
        //ID_CUST_PLAN
        tbilDBean.setIdCustPlan(CommonUtils.toString(map.get("ID_CUST_PLAN")));
        //ID_CUST_PLAN_GRP
        tbilDBean.setIdCustPlanGrp(CommonUtils.toString(map.get("ID_CUST_PLAN_GRP")));
        //ID_CUST_PLAN_LINK
        tbilDBean.setIdCustPlanLink(CommonUtils.toString(map.get("ID_CUST_PLAN_LINK")));
        //SVC_LEVEL1
        tbilDBean.setSvcLevel1(CommonUtils.toString(map.get("SVC_LEVEL1")));
        //SVC_LEVEL2
        tbilDBean.setSvcLevel2(CommonUtils.toString(map.get("SVC_LEVEL2")));
        // #194 Edit Start
        if ("1".equals(bilHeaderInfo.getIsDuplicate())) {
            //BILL_FROM
            tbilDBean.setBillFrom("");
            //BILL_TO
            tbilDBean.setBillTo("");
        } else {
            //BILL_FROM
            tbilDBean.setBillFrom(CommonUtils.formatDate(map.get("BILL_FROM"), "dd/MM/yyyy"));
            //BILL_TO
            tbilDBean.setBillTo(CommonUtils.formatDate(map.get("BILL_TO"), "dd/MM/yyyy"));
        }
        // #194 Edit End
        //JOB_NO
        tbilDBean.setJobNo(CommonUtils.toString(map.get("JOB_NO")));
        //IS_DISPLAY_JOBNO
        tbilDBean.setIsDisplayJobNo(CommonUtils.toString(map.get("IS_DISPLAY_JOBNO")));
        //ITEM_EXPORT_AMT
        tbilDBean.setItemExportAmt(CommonUtils.toString(map.get("ITEM_EXPORT_AMT")));
        //ITEM_TYPE
        tbilDBean.setItemType(CommonUtils.toString(map.get("ITEM_TYPE")));
        //IS_DISPLAY_MIN_SVC
        tbilDBean.setIsDisplayMinSvc(CommonUtils.toString(map.get("IS_DISPLAY_MIN_SVC")));
        //MIN_SVC_FROM
        tbilDBean.setMinSvcFrom(CommonUtils.formatDate(map.get("MIN_SVC_FROM"), "dd/MM/yyyy"));
        //MIN_SVC_TO
        tbilDBean.setMinSvcTo(CommonUtils.formatDate(map.get("MIN_SVC_TO"), "dd/MM/yyyy"));
        //ITEM_CAT
        tbilDBean.setItemCat(CommonUtils.toString(map.get("ITEM_CAT")));
        //BILL_FROM_TEXT
        tbilDBean.setBillFromDisplay(CommonUtils.toString(map.get("BILL_FROM_TEXT")));
        //BILL_TO_TEXT
        tbilDBean.setBillToDisplay(CommonUtils.toString(map.get("BILL_TO_TEXT")));
        //MIN_SVC_FROM_TEXT
        tbilDBean.setMinSvcFromDisplay(CommonUtils.toString(map.get("MIN_SVC_FROM_TEXT")));
        //MIN_SVC_TO_TEXT
        tbilDBean.setMinSvcToDisplay(CommonUtils.toString(map.get("MIN_SVC_TO_TEXT")));
        //#154 start
        //DISPLAY_DISC
        tbilDBean.setDisplayDiscount(CommonUtils.toString(map.get("DISPLAY_DISC")));
        //ITEM_DISC_AMT
        tbilDBean.setItemDisc(CommonUtils.toString(map.get("ITEM_DISC_AMT")));
        //ITEM_EXPORT_GST
        tbilDBean.setItemExportGST(CommonUtils.toString(map.get("ITEM_EXPORT_GST")));
        //ITEM_SUBTOTAL
        tbilDBean.setItemSubTotal(CommonUtils.toString(map.get("ITEM_SUBTOTAL")));
        //CUST_PO
        tbilDBean.setPoNo(CommonUtils.toString(map.get("CUST_PO")));
        //TAX_CODE
        tbilDBean.setTaxCode(CommonUtils.toString(map.get("TAX_CODE")));
        //TAX_RATE
        tbilDBean.setTaxRate(CommonUtils.toString(map.get("TAX_RATE")));
        //#154 end
        
        return tbilDBean;
    }
	
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
}