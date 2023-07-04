/*
 * @(#)RP_B_BAC_S01_02BLogic.java
 */
package nttdm.bsys.b_bac.blogic;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import jp.terasoluna.fw.web.codelist.MappedCodeListLoader;
import nttdm.bsys.b_bac.dto.RP_B_BAC_S01_02Input;
import nttdm.bsys.b_bac.dto.RP_B_BAC_S01_02Output;
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.util.ApplicationContextProvider;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.LabelValueBean;
import org.springframework.context.ApplicationContext;

/**
 * BusinessLogic class.
 * 
 * @author khungl0ng
 */
public class RP_B_BAC_S01_02BLogic extends AbstractRP_B_BAC_S01_02BLogic {

    /**
     * @param inputDto
     * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
     */
    public BLogicResult execute(RP_B_BAC_S01_02Input inputDto) {
        BLogicResult result = new BLogicResult();
        RP_B_BAC_S01_02Output outputDTO = new RP_B_BAC_S01_02Output();
        try {
            BeanUtils.copyProperties(outputDTO, inputDto);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        
        // get access type
        Map<String, Object> p = new HashMap<String, Object>();
        p.put("idUser", inputDto.getUvo().getId_user());
        HashMap<String, Object> userAccess = (HashMap<String, Object>) this.queryDAO.executeForMap("B_BAC.getAccessType", p);
        if (!CommonUtils.isEmpty(userAccess)) {
            outputDTO.setAccessType(userAccess.get("ACCESS_TYPE").toString());
        }

        // payment currency combo box list
        List<LabelValueBean> cboPaymentCurrencyList = new ArrayList<LabelValueBean>();
        cboPaymentCurrencyList = this.queryDAO.executeForObjectList("B_BAC.getGrandTotal", null);
        outputDTO.setCboPaymentCurrencyList(cboPaymentCurrencyList);
        
        // CB_AUTO_OFFSET 
        String cbAutoOffset = this.queryDAO.executeForObject("B_BAC.getMGSetDInfo", null, String.class);
        outputDTO.setCbAutoOffset(cbAutoOffset);

        // get input value 
        Map<String,Object> param = new HashMap<String,Object>();
        
        param.put("tbxCustomerName", StringUtils.trim(inputDto.getTbxCustomerName()));
        param.put("tbxCustomerId", StringUtils.trim(inputDto.getTbxCustomerId()));
        param.put("cboPaymentMethod", inputDto.getCboPaymentMethod());
        param.put("cboBillingCurrency", inputDto.getCboBillingCurrency());
        /*#263 [T11] Add customer type at inquiry screen and export result CT 06062017 ST*/
        param.put("cboCustomerType", inputDto.getCboCustomerType());
        /*#263 [T11] Add customer type at inquiry screen and export result CT 06062017 EN*/
        param.put("tbxBillingAccountNo", StringUtils.trim(inputDto.getTbxBillingAccountNo()));
        param.put("cboPaymentCurrency", inputDto.getCboPaymentCurrency());
        param.put("chkBySingPost", inputDto.getChkBySingPost());
        param.put("chkStatus", inputDto.getChkStatus());
        param.put("chkTotalAmountDue", inputDto.getChkTotalAmountDue());
        param.put("chkEmail", inputDto.getChkEmail());
        if(inputDto.getChkDeliveries().length == 3){
        	param.put("chkDeliveries", new String[] { "1", "2", "3", "4" });
        }else{
        	param.put("chkDeliveries", inputDto.getChkDeliveries());
        }
        

        // status array
        String[] chkStatus = inputDto.getChkStatus();
        
        for (int i = 0; i < chkStatus.length; i++) {
            
            if ("N".equalsIgnoreCase(chkStatus[i])) {
                // New
                param.put("statusN", "N");
                
            } else if ("A".equalsIgnoreCase(chkStatus[i])) {
                // Active
                param.put("statusA", "A");
                
            } else if ("B".equalsIgnoreCase(chkStatus[i])) {
                // Bill Finished
                param.put("statusB", "B");
            }
        }

        // Total Amount Due array
        String[]  chkTotalAmountDue = inputDto.getChkTotalAmountDue();

        for (int i = 0; i < chkTotalAmountDue.length; i++) {

            if ("A".equalsIgnoreCase(chkTotalAmountDue[i])) {
                // =0
                param.put("amountDueA", "A");
                
            } else if ("B".equalsIgnoreCase(chkTotalAmountDue[i])) {
                // <>0
                param.put("amountDueB", "B");
                
            } else if ("C".equalsIgnoreCase(chkTotalAmountDue[i])) {
                // <0
                param.put("amountDueC", "C");
                
            } else if ("D".equalsIgnoreCase(chkTotalAmountDue[i])) {
                // >0
                param.put("amountDueD", "D");
            }
        }
        
        // get the number of row for paging
        BillingSystemSettings bss = new BillingSystemSettings(queryDAO);
        int row = bss.getResultRow();
        Integer startIndex = inputDto.getStartIndex();
        
        // #191 Start
        String singPostValue = this.queryDAO.executeForObject("B_BAC.getSingPostValue", null, String.class);
        outputDTO.setSingPostValue(singPostValue);
        // #191 End
        
        // get result total count
        Integer totalCount = queryDAO.executeForObject("B_BAC.getBillAccTotalCount", param, Integer.class);
        
        if(totalCount<=0){
            // info.ERR2SC002
            BLogicMessages msgs = new BLogicMessages();
            BLogicMessage msg = new BLogicMessage("info.ERR2SC002", new Object());
            msgs.add(ActionMessages.GLOBAL_MESSAGE, msg);
            result.setMessages(msgs);
            outputDTO.setTotalRow(totalCount);
            outputDTO.setRow(row);
            result.setResultObject(outputDTO);
            result.setResultString("success");
            return result;
        }
        if (startIndex == null || startIndex < 0 || startIndex > totalCount)
            startIndex = 0;
        
        // get result list
        List<Map<String,Object>> searchResultList = queryDAO.executeForMapList("B_BAC.getBillAccSearchResult", param,startIndex, row);
        
        // for generate address4
        ApplicationContext context = ApplicationContextProvider.getApplicationContext();
        MappedCodeListLoader countryCodeList = (MappedCodeListLoader) context.getBean("LIST_COUNTRY");
        Map<String, Object> countryMap = countryCodeList.getCodeListMap();
        
        // loop the list to prepare 
        for (int i = 0; i < searchResultList.size(); i++) {

            Map<String,Object> item = searchResultList.get(i);
            
            String idCust = CommonUtils.toString(item.get("ID_CUST"));
            // CONTACT_TYPE
            String contactType = CommonUtils.toString(item.get("CONTACT_TYPE"));
            // CUST_ADR_TYPE
            String custAdrType = CommonUtils.toString(item.get("CUST_ADR_TYPE"));
            // CUSTOMER_TYPE
            String customerType = CommonUtils.toString(item.get("CUSTOMER_TYPE"));
            
            // address
            item.put("idCust", idCust);
            item.put("adrType", custAdrType);
            Map<String,Object> address = this.queryDAO.executeForMap("B_BAC.getCustAdr", item);

            if (address != null) {
                item.put("address1", CommonUtils.toString(address.get("ADR_LINE1")));
                item.put("address2", CommonUtils.toString(address.get("ADR_LINE2")));
                item.put("address3", CommonUtils.toString(address.get("ADR_LINE3")));

                String country = "";
                if (countryMap.containsKey(address.get("COUNTRY"))) {
                    country = countryMap.get(address.get("COUNTRY")).toString();
                }
                item.put("address4", CommonUtils.toString(address.get("ZIP_CODE")) + ", " + country);
            }
            
            //email
            item.put("contactType", contactType);
            Map<String, Object> contactInfo = this.queryDAO.executeForMap("B_BAC.getSingleContact", item);
            
            // attention
            if(contactInfo != null && !CommonUtils.isEmpty(contactInfo.get("CONTACT_NAME"))){
                item.put("attention", "["+contactType+"]"+contactInfo.get("CONTACT_NAME"));
            }
            
            if (!"CONS".equals(customerType)) {
                // get email info
                if (contactInfo != null) {
                    // email
                    item.put("email", CommonUtils.toString(contactInfo.get("EMAIL")));
                }
            } else {
                item.put("email", CommonUtils.toString(item.get("CO_EMAIL")).trim());
            }
        }

        outputDTO.setSearchResultList(searchResultList);
        outputDTO.setTotalRow(totalCount);
        outputDTO.setRow(row);
        result.setResultObject(outputDTO);
        result.setResultString("success");
        return result;
    }
}