/**
 * @(#)B_BIL_CommonUtil.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2012/05/28
 * 
 * Copyright (c) 2012 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.b_bil.blogic;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import jp.terasoluna.fw.web.codelist.MappedCodeListLoader;
import nttdm.bsys.b_bil.bean.T_BIL_DetailInfo;
import nttdm.bsys.b_bil.bean.T_BIL_HeaderInfo;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.G_CUR_P01;
import nttdm.bsys.util.ApplicationContextProvider;

import org.apache.struts.util.LabelValueBean;
import org.springframework.context.ApplicationContext;

/**
 * @author gplai
 *
 */
public class B_BIL_CommonUtil {
    
    /**
     * comboBox change event type
     */
    public static final String EVENT_TYPE_COMBOBOX_CHANGE = "0";
    
    /**
     * Save button click event type
     */
    public static final String EVENT_TYPE_SAVA_BUTTON_CLICK = "1";
    
    /**
     * queryDAO
     */
    protected QueryDAO queryDAO;

    /**
     * updateDAO
     */
    protected UpdateDAO updateDAO;
    
    public B_BIL_CommonUtil(QueryDAO queryDAO, UpdateDAO updateDAO) {
        this.queryDAO = queryDAO;
        this.updateDAO = updateDAO;
    }

    /**
     * get the Combox Box Data
     * @param bilHeaderInfo
     */
    public void getComboBoxsData(T_BIL_HeaderInfo bilHeaderInfo) {
        //Customer Name combo box Data
        //List<LabelValueBean> listCust = this.queryDAO.executeForObjectList("B_BIL.getAllCust", null);
        //Billing Account No. combo box Data
        List<LabelValueBean> listBillingAccountNo =  this.queryDAO.executeForObjectList("B_BIL.getBillingAccountNo", bilHeaderInfo);
        //A/C Manager combo box Data
        List<LabelValueBean> listAcManager = this.queryDAO.executeForObjectList("B_BIL.getAllAcManager", null);
        //Footer Info Data
        List<Map<String, Object>> footerInfo = this.queryDAO.executeForMapList("B_BIL.getFooterInfo", null);
        // get bankFooterInfo
        List<Map<String, Object>> bankFooterInfo = this.queryDAO.executeForMapList("B_BIL.getBankFooterInfo", null);
        //GST Value
        Map<String, Object> gstAmount = this.queryDAO.executeForMap("B_BIL.getGstAmount", null);
        // get M_GSET_D info
        List<Object> mGSetDInfoList = (List<Object>) this.queryDAO.executeForObjectList("B_BIL.getMGSetDInfo", null);
        
        //bilHeaderInfo.setListCust(listCust);
        bilHeaderInfo.setListBillingAccountNo(listBillingAccountNo);
        bilHeaderInfo.setListAcMan(listAcManager);
        bilHeaderInfo.setGstPercent(CommonUtils.toString(gstAmount.get("SET_VALUE")));
        bilHeaderInfo.setFooterInfo(footerInfo);
        bilHeaderInfo.setBankFooterInfo(getBankFooterInfo(bilHeaderInfo.getBillCurrency(), bankFooterInfo));
        //Currency Combo Box enabled flag
        if (!CommonUtils.isEmpty(mGSetDInfoList) && mGSetDInfoList.contains("BAC")) {
            bilHeaderInfo.setCurrencyEnabledFlg("0");
        } else {
            bilHeaderInfo.setCurrencyEnabledFlg("1");
        }
        //Job Modules is display
        String jobModulesDisplayFlg = getIsJNMModulesDisplayFlg();
        bilHeaderInfo.setJobModulesDisplayFlg(jobModulesDisplayFlg);
    }
    
    /**
     * the Combo Box change event
     * @param bilHeaderInfo
     * @param eventType 0:comboBox change event,1:Save button click event
     */
    @SuppressWarnings("unchecked")
    public void comboBoxChangeEvt(T_BIL_HeaderInfo bilHeaderInfo, String eventType) {
        String idRef = CommonUtils.toString(bilHeaderInfo.getIdRef());
        String changeTypeFlag = CommonUtils.toString(bilHeaderInfo.getChangeTypeFlag());
        String billAcc = CommonUtils.toString(bilHeaderInfo.getBillAcc());
        
        HashMap<String, Object> t_bac_HInfo = null ;
        HashMap<String, Object> t_bac_DInfo = null ;
        if (!CommonUtils.isEmpty(billAcc)) {
            t_bac_HInfo = (HashMap<String, Object>)this.queryDAO.executeForMap("B_BIL.getBAC_HInfo", bilHeaderInfo);
            t_bac_DInfo = (HashMap<String, Object>)this.queryDAO.executeForMap("B_BIL.getBAC_DInfo", bilHeaderInfo);
        }
        //cust name combox change set Billing Account No Combo Box is null
        if ("changeBillAcc".equals(changeTypeFlag) || "changeCustName".equals(changeTypeFlag)){
            if (!CommonUtils.isEmpty(billAcc)) {
                bilHeaderInfo.setAdrType(CommonUtils.toString(t_bac_HInfo.get("CUST_ADR_TYPE")).trim());
                bilHeaderInfo.setContactType(CommonUtils.toString(t_bac_HInfo.get("CONTACT_TYPE")).trim());
                bilHeaderInfo.setContactDelivery(CommonUtils.toString(t_bac_HInfo.get("DELIVERY")).trim());
                bilHeaderInfo.setContactDeliveryEmail(CommonUtils.toString(t_bac_HInfo.get("DELIVERY_EMAIL")).trim());
                if(t_bac_DInfo != null){
	                bilHeaderInfo.setTerm(CommonUtils.toString(t_bac_DInfo.get("TERM")).trim());
	                bilHeaderInfo.setTermDays(CommonUtils.toString(t_bac_DInfo.get("TERM_DAY")).trim());
	                this.setDueDate(bilHeaderInfo);
                }
            } else {
                bilHeaderInfo.setAdrType("");
                bilHeaderInfo.setContactType("");
                bilHeaderInfo.setContactDelivery("");
                bilHeaderInfo.setContactDeliveryEmail("");
                bilHeaderInfo.setTerm("");
                bilHeaderInfo.setTermDays("");
            }
        }
        
        if ("changeBillAcc".equals(changeTypeFlag)) {
            if (!CommonUtils.isEmpty(billAcc)) {
                bilHeaderInfo.setContactType(CommonUtils.toString(t_bac_HInfo.get("CONTACT_TYPE")).trim());
            } else {
                bilHeaderInfo.setContactType("");
            }
        }
        
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("idRef", idRef);
        m.put("idCust", bilHeaderInfo.getIdCust());
        m.put("adrType", bilHeaderInfo.getAdrType());
        // generate address info
        HashMap<String, Object> address = (HashMap<String, Object>)queryDAO.executeForMap("B_BIL.getCustAdr", m);
        if (address != null) {
            bilHeaderInfo.setAddress1(CommonUtils.toString(address.get("ADR_LINE1")));
            bilHeaderInfo.setAddress2(CommonUtils.toString(address.get("ADR_LINE2")));
            bilHeaderInfo.setAddress3(CommonUtils.toString(address.get("ADR_LINE3")));
            // generate address4
            ApplicationContext context = ApplicationContextProvider.getApplicationContext();
            MappedCodeListLoader countryCodeList = (MappedCodeListLoader)context.getBean("LIST_COUNTRY");
            Map<String, Object> countryMap = countryCodeList.getCodeListMap();
            String country = "";
            if (countryMap.containsKey(address.get("COUNTRY"))){
                country = CommonUtils.toString(countryMap.get(address.get("COUNTRY"))).trim();
            }
            String zipCode = CommonUtils.toString(address.get("ZIP_CODE")).trim();
            if(CommonUtils.isEmpty(zipCode) && CommonUtils.isEmpty(country)) {
                bilHeaderInfo.setAddress4("");
            } else if(!CommonUtils.isEmpty(zipCode) && CommonUtils.isEmpty(country)) {
                bilHeaderInfo.setAddress4(zipCode);
            } else if(CommonUtils.isEmpty(zipCode) && !CommonUtils.isEmpty(country)) {
                bilHeaderInfo.setAddress4(country);
            } else {
                bilHeaderInfo.setAddress4(zipCode.concat(", ").concat(country));
            }
        }else{
            bilHeaderInfo.setAddress1("");
            bilHeaderInfo.setAddress2("");
            bilHeaderInfo.setAddress3("");
            bilHeaderInfo.setAddress4("");
        }
        // cust info
        HashMap<String, Object> custInfo = (HashMap<String, Object>)this.queryDAO.executeForMap("B_BIL.getSingleCustInfo", m);
        //CUSTOMER_TYPE
        String customerType = "";
        if (custInfo != null) {
            customerType = CommonUtils.toString(custInfo.get("CUSTOMER_TYPE"));
            if ("CORP".equals(customerType)) {
                bilHeaderInfo.setTelNo(CommonUtils.toString(custInfo.get("CO_TEL_NO")));
                bilHeaderInfo.setFaxNo(CommonUtils.toString(custInfo.get("CO_FAX_NO")));
            }else {
                bilHeaderInfo.setTelNo(CommonUtils.toString(custInfo.get("HOME_TEL_NO")));
                bilHeaderInfo.setFaxNo(CommonUtils.toString(custInfo.get("HOME_FAX_NO")));
            }
            bilHeaderInfo.setCustName(CommonUtils.toString(custInfo.get("CUST_NAME")));
            bilHeaderInfo.setSalutation(CommonUtils.toString(custInfo.get("SALUTATION")));
        } else {
            bilHeaderInfo.setTelNo("");
            bilHeaderInfo.setFaxNo("");
            bilHeaderInfo.setCustName("");
            bilHeaderInfo.setSalutation("");
        }
        bilHeaderInfo.setCustomerTypeFlag(customerType);
        // contact info
        List<LabelValueBean> listContact = null;
        // get list contact
        if ("CORP".equals(customerType)) {
            listContact = this.queryDAO.executeForObjectList("B_BIL.getAllContact", m);
        } else {
            listContact = this.queryDAO.executeForObjectList("B_BIL.getAllContact1", m);
        }
        bilHeaderInfo.setListContact(listContact);
        
        String contactType = bilHeaderInfo.getContactType();
        m.put("contactType", contactType);
        bilHeaderInfo.setContactType(contactType);
        String telFaxContactFlg = CommonUtils.toString(queryDAO.executeForObject("B_BIL.selectTEL_FAX_CONTACT", null, String.class));
        if ("CORP".equals(customerType)) {
            // get email info and contact name
            HashMap<String, Object> contactInfo = (HashMap<String, Object>)this.queryDAO.executeForMap("B_BIL.getSingleContact", m);
            if (contactInfo != null) {
                if("1".equals(telFaxContactFlg)) {
                    bilHeaderInfo.setTelNo(CommonUtils.toString(contactInfo.get("TEL_NO")));
                    bilHeaderInfo.setFaxNo(CommonUtils.toString(contactInfo.get("FAX_NO")));
                }
                bilHeaderInfo.setContactEmail(CommonUtils.toString(contactInfo.get("EMAIL")));
                bilHeaderInfo.setContactEmailCC(CommonUtils.toString(contactInfo.get("EMAIL_CC")));
                bilHeaderInfo.setContactName(CommonUtils.toString(contactInfo.get("CONTACT_NAME")));
            } else {
                if("1".equals(telFaxContactFlg)) {
                    bilHeaderInfo.setTelNo("");
                    bilHeaderInfo.setFaxNo("");
                }
                bilHeaderInfo.setContactEmail("");
                bilHeaderInfo.setContactEmailCC("");
                bilHeaderInfo.setContactName("");
            }
        } else {
            if (custInfo != null) {
                bilHeaderInfo.setContactEmail(CommonUtils.toString(custInfo.get("CO_EMAIL")));
                bilHeaderInfo.setContactEmailCC("");
                bilHeaderInfo.setContactName("");
            } else {
                bilHeaderInfo.setContactEmail("");
                bilHeaderInfo.setContactEmailCC("");
                bilHeaderInfo.setContactName("");
            }
        }
        if ("CORP".equals(customerType)) {
            if (CommonUtils.isEmpty(contactType)) {
                if("1".equals(telFaxContactFlg)) {
                    bilHeaderInfo.setTelNo("");
                    bilHeaderInfo.setFaxNo("");
                }
                bilHeaderInfo.setContactName("");
                bilHeaderInfo.setContactEmail("");
                bilHeaderInfo.setContactEmailCC("");
            }
        }
        
        //cust name combox change set Billing Account No Combo Box is null
        if ("changeCustName".equals(changeTypeFlag)){
            bilHeaderInfo.setBilDetail(new ArrayList<T_BIL_DetailInfo>());
            bilHeaderInfo.setGstAmount("0");
            bilHeaderInfo.setBillAmount("0");
        }
        //Billing Account No Combo Box selected
        if ("changeBillAcc".equals(changeTypeFlag) || "changeCustName".equals(changeTypeFlag)){
            if (!CommonUtils.isEmpty(billAcc)) {
                String paymentMethod = CommonUtils.toString(t_bac_HInfo.get("PAYMENT_METHOD")).trim();
                String currency = CommonUtils.toString(t_bac_HInfo.get("BILL_CURRENCY")).trim();
                bilHeaderInfo.setPayMethod(paymentMethod);
                bilHeaderInfo.setBillCurrency(currency);
                
                if (EVENT_TYPE_COMBOBOX_CHANGE.equals(eventType)) {
                    Map<String, Object> bacHInfo = queryDAO.executeForMap("B_BIL.getT_BAC_H_InfoByID", billAcc);
                    String bacBillingCurrency = "";
                    String bacExpCurrency = "";
                    String bacFixedForex = "";
                    String bacCurRate = "";
                    if (bacHInfo!=null) {
                        bacBillingCurrency = CommonUtils.toString(bacHInfo.get("BILL_CURRENCY")).trim();
                        bacExpCurrency = CommonUtils.toString(bacHInfo.get("EXPORT_CURRENCY")).trim();
                        if ("-".equals(bacExpCurrency)) {
                            bacExpCurrency = "";
                        }
                        bacFixedForex = CommonUtils.toString(bacHInfo.get("FIXED_FOREX")).trim();
                    }
                    if (!CommonUtils.isEmpty(bacFixedForex)) {
                        bacCurRate = bacFixedForex;
                    } else {
                        G_CUR_P01 gCurP01 = new G_CUR_P01(queryDAO);
                        Map<String, Object> curRateMap = gCurP01.convertCurrency(bacBillingCurrency, 0, bacExpCurrency, bacFixedForex);
                        bacCurRate = CommonUtils.toString(curRateMap.get(G_CUR_P01.CUR_RATE));
                    }
                    bilHeaderInfo.setBacBillingCurrency(bacBillingCurrency);
                    bilHeaderInfo.setExportCur(bacExpCurrency);
                    bilHeaderInfo.setFixedForex(bacFixedForex);
                    bilHeaderInfo.setCurRate(bacCurRate);
                    bilHeaderInfo.setExportAmount("0");
                }
            } else {
                if (EVENT_TYPE_COMBOBOX_CHANGE.equals(eventType)) {
                    bilHeaderInfo.setBacBillingCurrency("");
                    bilHeaderInfo.setExportCur("");
                    bilHeaderInfo.setFixedForex("");
                    bilHeaderInfo.setCurRate("");
                    bilHeaderInfo.setExportAmount("0");
                }
            }
        }
        if (CommonUtils.isEmpty(billAcc)) {
            bilHeaderInfo.setPayMethod("");
            bilHeaderInfo.setBillCurrency("");
        }
    }
    
    /**
     * get M-JNM is used flag
     * @return 0:not display,1:display
     */
    public String getIsJNMModulesDisplayFlg(){
        String isJNMDisplayFlg = queryDAO.executeForObject("B_BIL.select_M_JNM_STATUS", null, String.class);
        isJNMDisplayFlg = CommonUtils.toString(isJNMDisplayFlg);
        if(CommonUtils.isEmpty(isJNMDisplayFlg)) {
            return "0";
        } else {
            return isJNMDisplayFlg;
        }
    }
    
    /**
     * get bank info to display
     * @param bankFooterInfo
     * @return
     */
    public List<Map<String, Object>> getBankFooterInfo(String billCurrency, List<Map<String, Object>> bankFooterInfo) {

        Map<String, Object> curBank1 = null;
        Map<String, Object> defaultBank1 = null;
        Map<String, Object> curBank2 = null;
        Map<String, Object> defaultBank2 = null;
        Map<String, Object> curBank3 = null;
        Map<String, Object> defaultBank3 = null;

        if (CommonUtils.isEmpty(bankFooterInfo)) {
            return new ArrayList<Map<String, Object>>();
        }
        for (Map<String, Object> bank : bankFooterInfo) {
            String COM_CUR = CommonUtils.toString(bank.get("COM_CUR"));
            String DISPLAY_ORDER = CommonUtils.toString(bank.get("DISPLAY_ORDER"));
            String DEFAULT_ACC = CommonUtils.toString(bank.get("DEFAULT_ACC"));

            if ("1".equals(DISPLAY_ORDER) || "0".equals(DISPLAY_ORDER)) {
                if (COM_CUR.equals(billCurrency) && curBank1 == null) {
                    curBank1 = bank;
                }
                if ("1".equals(DEFAULT_ACC)) {
                    defaultBank1 = bank;
                }
            } else if ("2".equals(DISPLAY_ORDER)) {
                if (COM_CUR.equals(billCurrency) && curBank2 == null) {
                    curBank2 = bank;
                }
                if ("1".equals(DEFAULT_ACC)) {
                    defaultBank2 = bank;
                }
            } else if ("3".equals(DISPLAY_ORDER)) {
                if (COM_CUR.equals(billCurrency) && curBank3 == null) {
                    curBank3 = bank;
                }
                if ("1".equals(DEFAULT_ACC)) {
                    defaultBank3 = bank;
                }
            }
        }
        List<Map<String, Object>> bankInfo = new ArrayList<Map<String, Object>>();

        if (curBank1 == null) {
            if (defaultBank1 != null) {
                bankInfo.add(defaultBank1);
            }
        } else {
            bankInfo.add(curBank1);
        }

        if (curBank2 == null) {
            if (defaultBank2 != null) {
                bankInfo.add(defaultBank2);
            }
        } else {
            bankInfo.add(curBank2);
        }

        if (curBank3 == null) {
            if (defaultBank3 != null) {
                bankInfo.add(defaultBank3);
            }
        } else {
            bankInfo.add(curBank3);
        }
        return bankInfo;
    }
    
    public String getCustPoDisplay() {
		return queryDAO.executeForObject("B_BIL.GET_IS_CUST_PO_DISPLAY", null, String.class);
	} 
    
    private void setDueDate(T_BIL_HeaderInfo bilHeaderInfo) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date invoiceDate = sdf.parse(bilHeaderInfo.getInvoiceDate());
			Calendar c = Calendar.getInstance();
			c.setTime(invoiceDate);
			c.add(Calendar.DAY_OF_MONTH,
					Integer.parseInt(bilHeaderInfo.getTermDays()));
			bilHeaderInfo.setContactDueDate(sdf.format(c.getTime()));
		} catch (Exception e) {
			
		}
    }
}
