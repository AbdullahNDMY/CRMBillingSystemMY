/*
 * @(#)RP_B_BIL_S02_01BLogic.java
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
import nttdm.bsys.b_bil.dto.RP_B_BIL_S02_01Input;
import nttdm.bsys.b_bil.dto.RP_B_BIL_S02_01Output;
import nttdm.bsys.common.util.CommonUtils;

import org.apache.commons.beanutils.BeanUtils;
import org.aspectj.weaver.ast.Test;

/**
 * BusinessLogic class.
 * 
 * @author khungl0ng
 */
public class RP_B_BIL_S02_01BLogic extends AbstractRP_B_BIL_S02_01BLogic {

    /**
     * 
     * @param param RP_B_BIL_S02_01Input
     * @return ビジ�?スロジック�?�実行�?果�?BLogicResultインスタンス。
     */
    public BLogicResult execute(RP_B_BIL_S02_01Input param) {
        BLogicResult result = new BLogicResult();
        RP_B_BIL_S02_01Output outputDTO = new RP_B_BIL_S02_01Output();
        String idRef = param.getIdRef();
        // String mode = param.getMode();
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("idRef", idRef);
        // get header info
        HashMap<String, Object> headerInfo = (HashMap<String, Object>) queryDAO.
        executeForMap("B_BIL.getHeaderInfo", m);
        String paymentMethod = CommonUtils.toString(headerInfo.get("PAY_METHOD")).trim();
        headerInfo.put("PAY_METHOD", paymentMethod);
        //CUST_NAME = SALUTATION + ' ' + CUST_NAME
        String headCustName = CommonUtils.toString(headerInfo.get("CUST_NAME")).trim();
        String salutation = CommonUtils.getCodeMapListNameByValue("LIST_SALUTATION", CommonUtils.toString(headerInfo.get("SALUTATION")).trim());
        headerInfo.put("CUST_NAME", (salutation+" "+headCustName).trim());
        // BILLING ADDRESS 
        String idcust=CommonUtils.toString(headerInfo.get("ID_CUST"));
        List<Map<String, Object>> cusAdr = queryDAO.executeForMapList("SELECT.B_BIL.ADDRESSINFO", idcust);
        headerInfo.put("cusAdr", cusAdr);
        //Attns
        List<Map<String, Object>> attns = queryDAO.executeForMapList("SELECT.B_BIF.ATTNS", idcust);
        headerInfo.put("attns", attns);
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
            String custName1 = this.queryDAO.executeForObject("B_BIL.getMCustName",
                    idCust, String.class);
            custName.append(CommonUtils.toString(custName1));
        } else if (1 < idConsultLen) {
            // more than one ID_CONSULT
            for (int i = 0; i < idConsultLen - 1; i++) {
                idCust = listIdConsult.get(i);
                custName.append(
                        CommonUtils.toString(this.queryDAO.executeForObject(
                        "B_BIL.getMCustName", idCust, String.class))).
                        append(";");
            }
            idCust = listIdConsult.get(idConsultLen - 1);
            custName.append(CommonUtils.toString(this.queryDAO.executeForObject(
                            "B_BIL.getMCustName", idCust, String.class)));
        }
        headerInfo.put("ID_CONSULT_NAME", custName);
        
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
        
        // get user access type
        Map<String, Object> m1 = new HashMap<String, Object>();
        m1.put("idUser", param.getUvo().getId_user());
        HashMap<String, Object> userAccess = (HashMap<String, Object>) queryDAO.
        executeForMap("B_BIL.getAccessType", m1);
        List<Map<String, Object>> footerInfo = this.queryDAO.executeForMapList(
                "B_BIL.getFooterInfo", null);
        
        List<Map<String, Object>> bankFooterInfo;
        //get company Bank info
        // String accNo=CommonUtils.toString(headerInfo.get("CUST_ACC_NO"));
        String companyBankInfo = queryDAO.executeForObject("B_BIL.getCompanyBankInfo", idcust,String.class);
        // get Bank info
        if("ST".equals(CommonUtils.toString(companyBankInfo))){
            bankFooterInfo = this.queryDAO.executeForMapList("B_BIL.getBankFooterInfo", null);
        }
        else{
            Map<String, Object> companybankinfo=new HashMap<String, Object>();
            companybankinfo.put("idBank", CommonUtils.toString(companyBankInfo));
            bankFooterInfo = this.queryDAO.executeForMapList("B_BIL.getSelectedBankFooterInfo", companybankinfo);
        }
        //get the acc_no what is not been set defalt
        Map<String, List<String>> accAllMap=new HashMap<String, List<String>>();
        B_BIL_CommonUtil bilUtil = new B_BIL_CommonUtil(queryDAO, updateDAO);
        String billCurrency = CommonUtils.toString(headerInfo.get("BILL_CURRENCY")).trim();
        List<Map<String, Object>> firstBankFooterInfo = bilUtil.getBankFooterInfo(billCurrency, bankFooterInfo);
        accAllMap=getAccNo(firstBankFooterInfo, bankFooterInfo);
        List<String> accno1=accAllMap.get("accNoOne");
        List<String> accno2=accAllMap.get("accNoTwo");
        List<String> accno3=accAllMap.get("accNoThree");
        int rowsnum=getMaxSize(accno1.size(),accno2.size(),accno3.size());
        Map<String, Object> loadObject = new HashMap<String, Object>();
        loadObject.put("accno1", accno1);
        loadObject.put("accno2", accno2);
        loadObject.put("accno3", accno3);
        loadObject.put("rowsnum", rowsnum);
        //loadObject
        /*// get bankFooterInfo
        List<Map<String, Object>> bankFooterInfo = this.queryDAO.executeForMapList("B_BIL.getBankFooterInfo", null);*/
        
        bankFooterInfo = firstBankFooterInfo;

        //print times
        int printCount = 0 ;
        String strPrintCount = CommonUtils.toString(headerInfo.get("NO_PRINTED"));
        if (!CommonUtils.isEmpty(strPrintCount)){
            printCount = Integer.parseInt(strPrintCount) ;
        }
        //permission print times
        int permisPrintCount = 0 ;
        String strPermisPrintCount = CommonUtils.toString(queryDAO.
                executeForObject("B_BIL.selectPermisTimes", null, String.class));
        if (!CommonUtils.isEmpty(strPermisPrintCount)){
            permisPrintCount = Integer.parseInt(strPermisPrintCount) ;
        }
        //allow print
        if(printCount<permisPrintCount){
            headerInfo.put("PRINT_FLAG", "1");
        }
        
        try {
            BeanUtils.copyProperties(outputDTO, param);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        
        //Job Modules is used flag
        B_BIL_CommonUtil util = new B_BIL_CommonUtil(queryDAO, updateDAO);
        String jobModulesDisplayFlg = util.getIsJNMModulesDisplayFlg();
        headerInfo.put("jobModulesDisplayFlg", jobModulesDisplayFlg);
        
        //cash book button only can display when cash book module exist (m_sub_module = b-csb and is_display = 1) and auto offset is  by BAC
        Map<String, Object> b_csbIsDisplayMap = queryDAO.executeForMap("B_BIL.selectID_SUB_MODULE_ISDisplay", "B-CSB");
        //Transacting Matching button only can display when cash book module exist (m_sub_module = b-csb and is_display = 1) and auto offset is  by BAC
        Map<String, Object> b_trmIsDisplayMap = queryDAO.executeForMap("B_BIL.selectID_SUB_MODULE_ISDisplay", "B-TRM");
        Map<String, Object> cbAutoOffsetMap = queryDAO.executeForMap("B_BIL.selectCB_AUTO_OFFSET", null);
        if (b_csbIsDisplayMap!=null) {
            headerInfo.put("b_csbIsDisplayFlg", "1");
        } else {
            headerInfo.put("b_csbIsDisplayFlg", "0");
        }
        if (b_trmIsDisplayMap!=null) {
            headerInfo.put("b_trmIsDisplayFlg", "1");
        } else {
            headerInfo.put("b_trmIsDisplayFlg", "0");
        }
        String cbAutoOffset = "";
        if (cbAutoOffsetMap!=null) {
            cbAutoOffset = CommonUtils.toString(cbAutoOffsetMap.get("SET_VALUE"));
        }
        headerInfo.put("cbAutoOffsetValue", cbAutoOffset);
        
        String defCurrency = CommonUtils.toString(queryDAO.executeForObject("B_BIL.selectDEF_CURRENCY", null, String.class));
        String currencyStd = CommonUtils.toString(queryDAO.executeForObject("B_BIL.selectCURRENCY_STD", null, String.class));
        headerInfo.put("defCurrency", defCurrency);
        headerInfo.put("currencyStd", currencyStd);
        //prompt confirmation for authorised signature printing,when G.VALUE=MY02
        Map<String,Object> mSystemBase=this.queryDAO.executeForMap("B_BTH.getBILDOCPDF", null);
        String systembase=CommonUtils.toString(mSystemBase.get("VALUE"));
        outputDTO.setSysBaseVal(systembase);
        
        //add by #143-1
        // Add #156 Start
        String billCnAmtNegative = queryDAO.executeForObject("B_BTH.getBillCnAmtNegative", null, String.class);
        outputDTO.setBillCnAmtNegative(billCnAmtNegative);
        // Add #156 End
        
        // #174 Start
        String billingPeriod = queryDAO.executeForObject("B_BIL.Get_IsDisplay_BillingPeriod", null, String.class);
        // #174 End
        
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
        
        String gstCheck = CommonUtils.toString(queryDAO.executeForObject("B_BIL.getM_GSET_D_SetValue", null, String.class));
        outputDTO.setGstCheck(gstCheck);
        //fromPageFlag is 1 means page from B_BIL_S01
        headerInfo.put("fromPageFlag", param.getFromPageFlag());
		// #194 Start
		if ("0".equals(CommonUtils.toString(headerInfo.get("IS_DELETED")))
				&& "1".equals(CommonUtils.toString(headerInfo.get("IS_MANUAL")))
				&& "2".equals(userAccess.get("ACCESS_TYPE").toString())) {
			headerInfo.put("dispDuplicate", "1");
		} else {
			headerInfo.put("dispDuplicate", "0");
		}
        // #194 End
        outputDTO.setHeaderInfo(headerInfo);
        outputDTO.setDetailInfo(detailInfo);
        outputDTO.setFooterInfo(footerInfo);
        outputDTO.setBankFooterInfo(bankFooterInfo);
        outputDTO.setAccessType(userAccess.get("ACCESS_TYPE").toString());
        outputDTO.setLoadObject(loadObject);
        outputDTO.setTaxType(tax_type);
        outputDTO.setTaxStr(taxWord);
        outputDTO.setTaxRate(taxRate);
        result.setResultObject(outputDTO);
        result.setResultString("success");
        return result;
    }
    
    //get accno what is not set defalut
    private Map<String, List<String>> getAccNo(List<Map<String, Object>> firstBankFooterInfo, List<Map<String, Object>> bankFooterInfo){
        Map<String, List<String>> accnomap=new HashMap<String, List<String>>();
        List<String> acclist1=new ArrayList<String>();
        List<String> acclist2=new ArrayList<String>();
        List<String> acclist3=new ArrayList<String>();
        
        for (Map<String, Object> bank : bankFooterInfo) {
            String DISPLAY_ORDER = CommonUtils.toString(bank.get("DISPLAY_ORDER"));
            String DEFAULT_ACC = CommonUtils.toString(bank.get("DEFAULT_ACC"));
            String accno = CommonUtils.toString(bank.get("COM_ACCT_NO"));
            
            for(Map<String, Object> firstBank : firstBankFooterInfo) {
            	String firstAccno = CommonUtils.toString(firstBank.get("COM_ACCT_NO"));
            	String firstDisplayOrder = CommonUtils.toString(firstBank.get("DISPLAY_ORDER"));
                
            	if ("1".equals(DISPLAY_ORDER)) {
            		 if (firstDisplayOrder.equals(DISPLAY_ORDER)) {
	                     if (!firstAccno.equals(accno)) {
	                         acclist1.add(accno);
	                     }
            		 }
                 } else if ("2".equals(DISPLAY_ORDER)) {
                	 if (firstDisplayOrder.equals(DISPLAY_ORDER)) {
	                     if (!firstAccno.equals(accno)) {
	                         acclist2.add(accno);
	                     }
                	 }
                 }else if ("3".equals(DISPLAY_ORDER)) {
                	 if (firstDisplayOrder.equals(DISPLAY_ORDER)) {
	                	 if (!firstAccno.equals(accno)) {
	                         acclist3.add(accno);
	                     }
                	 }
                 }
            }
        }
        accnomap.put("accNoOne", acclist1);
        accnomap.put("accNoTwo", acclist2);
        accnomap.put("accNoThree", acclist3);
        
        return accnomap;
    }
    
    private int getMaxSize(int a,int b,int c){
        int maxno=0;
        if(a>b&&a>c){
            maxno=a;
        }
        if(b>a&&b>c){
            maxno=b;
        }
        if(c>b&&c>a){
            maxno=c;
        }
        if(a==b&&a==c){
            maxno=a;
        }
        return maxno;
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