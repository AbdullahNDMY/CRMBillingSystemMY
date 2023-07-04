/*
 * @(#)RP_B_BIL_S02_02BLogic.java
 *
 *
 */
package nttdm.bsys.b_bil.blogic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.b_bil.bean.T_BIL_HeaderInfo;
import nttdm.bsys.b_bil.dto.RP_B_BIL_S02_02_01Input;
import nttdm.bsys.b_bil.dto.RP_B_BIL_S02_02_01Output;
import nttdm.bsys.common.util.CommonUtils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.util.LabelValueBean;

/**
 * BusinessLogic class.
 * 
 * @author khungl0ng
 */
public class RP_B_BIL_S02_02_01BLogic extends AbstractRP_B_BIL_S02_02BLogic {

    /**
     * 
     * @param param RP_B_BIL_S02_02_01Input
     * @return ビジ�?スロジック�?�実行�?果�?BLogicResultインスタンス。
     */
    public BLogicResult execute(RP_B_BIL_S02_02_01Input param) {
        BLogicResult result = new BLogicResult();
        RP_B_BIL_S02_02_01Output outputDTO = new RP_B_BIL_S02_02_01Output();
        String idRef = param.getIdRef();
        // String mode = param.getMode();
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("idRef", idRef);
        // get invoice header
        HashMap<String, Object> headerInfo = (HashMap<String, Object>) queryDAO.
                executeForMap("B_BIL.getHeaderInfoForEdit", m);
        //CUST_NAME = SALUTATION + ' ' + CUST_NAME
        String headCustName = CommonUtils.toString(headerInfo.get("M_CUST_NAME")).trim();
        String salutation = CommonUtils.getCodeMapListNameByValue("LIST_SALUTATION", CommonUtils.toString(headerInfo.get("M_SALUTATION")).trim());
        headerInfo.put("CUST_NAME", (salutation+" "+headCustName).trim());
        String isManual = CommonUtils.toString(headerInfo.get("IS_MANUAL"));
        // BILLING ADDRESS 
        String idcust=CommonUtils.toString(headerInfo.get("ID_CUST"));
        List<Map<String, Object>> cusAdr = queryDAO.executeForMapList("SELECT.B_BIL.ADDRESSINFO", idcust);
        headerInfo.put("cusAdr", cusAdr);
        //Attns
        List<Map<String, Object>> attns = queryDAO.executeForMapList("SELECT.B_BIF.ATTNS", idcust);
        headerInfo.put("attns", attns);
        
        //add by #143-1
          String gstCheck = CommonUtils.toString(queryDAO.executeForObject("B_BIL.getM_GSET_D_SetValue", null, String.class));
          outputDTO.setGstCheck(gstCheck);
          
        //From Generate Billing
        if("0".equals(isManual)) {
            String paymentMethod = CommonUtils.toString(headerInfo.get("PAY_METHOD")).trim();
            headerInfo.put("PAY_METHOD", paymentMethod);
            // ID_CONSULT
            String idConsult = CommonUtils.toString(headerInfo.get("ID_CONSULT"));
            String[] arrIdConsult = idConsult.split(";");
            List<String> listIdConsult = new ArrayList<String>();
            for (int i = 0; i < arrIdConsult.length; i++) {
                if (!CommonUtils.isNull(arrIdConsult[i])
                        && !CommonUtils.isEmpty(arrIdConsult[i])) {
                    listIdConsult.add(arrIdConsult[i]);
                }
            }
            int idConsultLen = listIdConsult.size();
            StringBuffer custName = new StringBuffer();
            String idCust = "";
            // only one ID_CONSULT
            if (0 < idConsultLen && idConsultLen == 1) {
                idCust = listIdConsult.get(0);
                custName.append(idCust);
            } else if (1 < idConsultLen) {
                // more than one ID_CONSULT
                for (int i = 0; i < idConsultLen - 1; i++) {
                    idCust = listIdConsult.get(i);
                    custName.append(idCust).append(";");
                }
                idCust = listIdConsult.get(idConsultLen - 1);
                custName.append(idCust);
            }
            headerInfo.put("ID_CONSULT_NAME", custName.toString());

            headerInfo.put("ADDRESS1", headerInfo.get("ADR_LINE1"));
            headerInfo.put("ADDRESS2", headerInfo.get("ADR_LINE2"));
            headerInfo.put("ADDRESS3", headerInfo.get("ADR_LINE3"));
            headerInfo.put("ADDRESS4", headerInfo.get("ADR_LINE4"));
            headerInfo.put("ZIP_CODE", headerInfo.get("M_CUST_ADR_ZIP_CODE"));
            headerInfo.put("COUNTRY", headerInfo.get("M_CUST_ADR_COUNTRY"));
            
            String customerType = CommonUtils.toString(headerInfo.get("CUSTOMER_TYPE")).trim();
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
            headerInfo.put("CONTACT_TERM", headerInfo.get("CONTACT_TERM"));
            headerInfo.put("CONTACT_TERM_DAY", headerInfo.get("CONTACT_TERM_DAY"));
            //#143 end

            CommonUtils.fixAddress4(headerInfo, "COUNTRY");
            CommonUtils.fixAddress4(headerInfo);
            
            List<Map<String, Object>> taxType = queryDAO.executeForMapList("SELECT.B_BIL.TAX_TYPE", idRef);
            String tax_type = CommonUtils.toString(taxType.get(0).get("TAX_TYPE"));
            String taxWord = CommonUtils.toString(queryDAO.executeForObject("SELECT.B_BIL.CPM_TAX_WORD", null, String.class)).trim();
            String taxRate = CommonUtils.toString(queryDAO.executeForObject("SELECT.B_BIL.TAX_RATE", null, String.class)).trim();
            
            // get detail info
            List<HashMap<String, Object>> detailInfo = queryDAO.
            executeForObjectList("B_BIL.getDetailInfo", m);
            for(HashMap<String, Object> temp : detailInfo) {
            	String itemDesc = clobToString((Clob)temp.get("ITEM_DESC_TEXT"));
            	temp.put("ITEM_DESC_TEXT", itemDesc);
            }
            // get footer info
            List<Map<String, Object>> footerInfo = this.queryDAO.executeForMapList(
                    "B_BIL.getFooterInfo", null);
            //get AC Manager
            List<LabelValueBean> listAcManager=this.queryDAO.executeForObjectList("B_BIL.getAllAcManager", null);
            
            headerInfo.put("LIST_AC_MANAGER", listAcManager);
            
            // get bankFooterInfo
            List<Map<String, Object>> bankFooterInfo = this.queryDAO.executeForMapList("B_BIL.getBankFooterInfo", null);      
            String billCurrency = CommonUtils.toString(headerInfo.get("BILL_CURRENCY")).trim();
            B_BIL_CommonUtil bilUtil = new B_BIL_CommonUtil(queryDAO, updateDAO);
            bankFooterInfo = bilUtil.getBankFooterInfo(billCurrency, bankFooterInfo);
            
            try {
                BeanUtils.copyProperties(outputDTO, param);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            
            // #174 Start
            String billingPeriod = queryDAO.executeForObject("B_BIL.Get_IsDisplay_BillingPeriod", null, String.class);
            // #174 End
            
            // Add #156 Start
            String billCnAmtNegative = queryDAO.executeForObject("B_BTH.getBillCnAmtNegative", null, String.class);
            outputDTO.setBillCnAmtNegative(billCnAmtNegative);
            // Add #156 End
            
            // Add #156 comment7 Start
            for (int i = 0; i < detailInfo.size(); i++) {
            	HashMap<String, Object> detail = detailInfo.get(i);
    			if ("CN".equals(headerInfo.get("BILL_TYPE")) && "1".equals(billCnAmtNegative)) {
    				Double discAmt = CommonUtils.toDouble(detail.get("ITEM_DISC_AMT"));
    				detail.put("ITEM_DISC_AMT", Math.abs(discAmt));
    			}
    			// #174 Start
    			detail.put("ISDISPLAY_BILLINGPERIOD", billingPeriod);
    			// #174 End
    		}
            // Add #156 comment7 End
            
            //Job Modules is used flag
            B_BIL_CommonUtil util = new B_BIL_CommonUtil(queryDAO, updateDAO);
            String jobModulesDisplayFlg = util.getIsJNMModulesDisplayFlg();
            headerInfo.put("jobModulesDisplayFlg", jobModulesDisplayFlg);
            
            String defCurrency = CommonUtils.toString(queryDAO.executeForObject("B_BIL.selectDEF_CURRENCY", null, String.class));
            String currencyStd = CommonUtils.toString(queryDAO.executeForObject("B_BIL.selectCURRENCY_STD", null, String.class));
            headerInfo.put("defCurrency", defCurrency);
            headerInfo.put("currencyStd", currencyStd);
            
            outputDTO.setTaxType(tax_type);
            outputDTO.setTaxStr(taxWord);
            outputDTO.setTaxRate(taxRate);
            
            outputDTO.setMode("edit");
            outputDTO.setHeaderInfo(headerInfo);
            outputDTO.setDetailInfo(detailInfo);
            outputDTO.setFooterInfo(footerInfo);
            outputDTO.setBankFooterInfo(bankFooterInfo);
            result.setResultObject(outputDTO);
            result.setResultString("successGenerate");
        } else {
            // From B_BIL_S03 page
            T_BIL_HeaderInfo bilHeaderInfo = new T_BIL_HeaderInfo();
            bilHeaderInfo.setIdRef(idRef);
            bilHeaderInfo.setMode("edit");
            outputDTO.setHeaderInfo(headerInfo);
            outputDTO.setBilHeaderInfo(bilHeaderInfo);
            result.setResultObject(outputDTO);
            result.setResultString("successBilS03");
        }
        return result;
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