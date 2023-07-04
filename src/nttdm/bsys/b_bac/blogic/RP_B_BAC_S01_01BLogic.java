/*
 * @(#)RP_B_BAC_S01_01BLogic.java
 *
 *
 */
package nttdm.bsys.b_bac.blogic;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.b_bac.dto.RP_B_BAC_S01_01Input;
import nttdm.bsys.b_bac.dto.RP_B_BAC_S01_01Output;
import nttdm.bsys.common.util.CommonUtils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.util.LabelValueBean;

/**
 * BusinessLogic class.
 * 
 * @author khungl0ng
 */
public class RP_B_BAC_S01_01BLogic extends AbstractRP_B_BAC_S01_01BLogic {

    /**
     * Billing Accounts B_BAC_S01 Initial Screen
     * 
     * @param param
     * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
     */
    public BLogicResult execute(RP_B_BAC_S01_01Input param) {
        BLogicResult result = new BLogicResult();
        RP_B_BAC_S01_01Output outputDTO = new RP_B_BAC_S01_01Output();
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("idUser", param.getUvo().getId_user());
        HashMap<String, Object> userAccess = (HashMap<String, Object>) this.queryDAO.executeForMap("B_BAC.getAccessType", m);
        if (!CommonUtils.isEmpty(userAccess)) {
            outputDTO.setAccessType(userAccess.get("ACCESS_TYPE").toString());
        }
        try {
            BeanUtils.copyProperties(outputDTO, param);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        // set default value for combo box
        String[] chkBySingPost = new String[] { "1", "0" };
        String[] chkStatus = new String[] { "N", "A", "B" };
        String[] chkTotalAmountDue = new String[] { "A", "B", "C", "D" };
        String[] chkEmail = new String[] { "1", "0" };
        String[] chkDeliveries = new String[] { "1", "2", "4" };

        outputDTO.setChkBySingPost(chkBySingPost);
        outputDTO.setChkStatus(chkStatus);
        outputDTO.setChkTotalAmountDue(chkTotalAmountDue);
        outputDTO.setChkEMail(chkEmail);
        outputDTO.setChkDeliveries(chkDeliveries);

        // CB_AUTO_OFFSET 
        String cbAutoOffset = this.queryDAO.executeForObject("B_BAC.getMGSetDInfo", null, String.class);
        outputDTO.setCbAutoOffset(cbAutoOffset);

        // payment currency combo box list
        List<LabelValueBean> cboPaymentCurrencyList = new ArrayList<LabelValueBean>();
        cboPaymentCurrencyList = this.queryDAO.executeForObjectList("B_BAC.getGrandTotal", null);
        outputDTO.setCboPaymentCurrencyList(cboPaymentCurrencyList);
        
        // #191 Start
        String singPostValue = this.queryDAO.executeForObject("B_BAC.getSingPostValue", null, String.class);
        outputDTO.setSingPostValue(singPostValue);
        // #191 End

        // List<LabelValueBean> listCust =
        // queryDAO.executeForObjectList("B_BAC.getAllCust", null);
        // outputDTO.setListCust(listCust);
        outputDTO.setListCust(null);
        result.setResultObject(outputDTO);
        result.setResultString("success");
        return result;
    }
}