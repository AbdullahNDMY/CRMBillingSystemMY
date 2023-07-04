/*
 * @(#)RP_B_BIL_S03_02_01BLogic.java
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.b_bil.dto.RP_B_BIL_S03_02_01Input;
import nttdm.bsys.b_bil.dto.RP_B_BIL_S03_02_01Output;
import nttdm.bsys.common.util.CommonUtils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.util.LabelValueBean;

/**
 * BusinessLogic class.
 * 
 * @author khungl0ng
 */
public class RP_B_BIL_S03_02_01BLogic extends AbstractRP_B_BIL_S03_02_01BLogic {

    /**
     * 
     * @param param RP_B_BIL_S03_02_01Input
     * @return ビジ�?スロジック�?�実行�?果�?BLogicResultインスタンス。
     */
    public BLogicResult execute(RP_B_BIL_S03_02_01Input param) {
        BLogicResult result = new BLogicResult();
        RP_B_BIL_S03_02_01Output outputDTO = new RP_B_BIL_S03_02_01Output();
        String idRef = param.getIdRef();
        //String mode = param.getMode();
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("idRef", idRef);
        // get invoice header
        HashMap<String, Object> headerInfo = (HashMap<String, Object>) queryDAO.
        executeForMap("B_BIL.getHeaderInfo", m);
        CommonUtils.fixAddress4(headerInfo);
        m.put("idCust", headerInfo.get("ID_CUST"));
        // get detail info
        List<HashMap<String, Object>> detailInfo = queryDAO.
        executeForObjectList("B_BIL.getDetailInfo", m);
        for(HashMap<String, Object> temp : detailInfo) {
        	String itemDesc = clobToString((Clob)temp.get("ITEM_DESC_TEXT"));
        	temp.put("ITEM_DESC_TEXT", itemDesc);
        }
        List<LabelValueBean> listCust = queryDAO.
        executeForObjectList("B_BIL.getAllCust", m);
        // get list contact
        String customerType = CommonUtils.toString(headerInfo.get("CUSTOMER_TYPE"));
        List<LabelValueBean> listContact = null;
        if ("CORP".equals(customerType)) {
            listContact = queryDAO.executeForObjectList("B_BIL.getAllContact", m);
        } else {
            listContact = queryDAO.executeForObjectList("B_BIL.getAllContact1", m);
            String contactName = CommonUtils.toString(headerInfo.get("CONTACT_NAME")).trim();
            if(!CommonUtils.isEmpty(contactName)){
                headerInfo.put("CONTACT_TYPE", CommonUtils.toString(headerInfo.get("ID_CUST")).trim());
            }
        }
        //headerInfo.put("CONTACT_TYPE", CommonUtils.toString(headerInfo.get("CONTACT_TYPE")).trim());
        // get listAcManager
        List<LabelValueBean> listAcManager = queryDAO.
                               executeForObjectList("B_BIL.getAllAcManager", m);
        //Billing Account No.
        List<LabelValueBean> listBillingAccountNo =  queryDAO.
        executeForObjectList("B_BIL.getBillingAccountNo", m);
        
        List<Map<String, Object>> footerInfo = this.queryDAO.executeForMapList(
                "B_BIL.getFooterInfo", null);
        try {
            BeanUtils.copyProperties(outputDTO, param);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        
     // get bankFooterInfo
        List<Map<String, Object>> bankFooterInfo = this.queryDAO.executeForMapList("B_BIL.getBankFooterInfo", null);
        String billCurrency = CommonUtils.toString(headerInfo.get("BILL_CURRENCY")).trim();
        B_BIL_CommonUtil bilUtil = new B_BIL_CommonUtil(queryDAO, updateDAO);
        bankFooterInfo = bilUtil.getBankFooterInfo(billCurrency, bankFooterInfo);
        
        Map<String, Object> gstAmount = this.queryDAO.
        executeForMap("B_BIL.getGstAmount", null);
        outputDTO.setGstValue(CommonUtils.toString(gstAmount.get("SET_VALUE")));
        outputDTO.setMode("edit");
        outputDTO.setHeaderInfo(headerInfo);
        outputDTO.setDetailInfo(detailInfo);
        outputDTO.setListCust(listCust);
        outputDTO.setListContact(listContact);
        //outputDTO.setListQcsRef(listQcsRef);
        //outputDTO.setListQuoRef(listAllQuoRef);
        outputDTO.setListAcMan(listAcManager);
        //Billing Account No.
        outputDTO.setListBillingAccountNo(listBillingAccountNo);
        outputDTO.setFooterInfo(footerInfo);
        outputDTO.setBankFooterInfo(bankFooterInfo);
        //outputDTO.setListJobNo(listJobNo);
        result.setResultObject(outputDTO);
        result.setResultString("success");
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