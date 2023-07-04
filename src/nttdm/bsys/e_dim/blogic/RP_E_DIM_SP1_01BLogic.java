/*
 * @(#)RP_E_DIM_SP1_01BLogic.java
 *
 *
 */
package nttdm.bsys.e_dim.blogic;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.util.LabelValueBean;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.bean.G_SET_ReturnValue;
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.common.util.G_SET_P03;
import nttdm.bsys.e_dim.dto.RP_E_DIM_SP1_01Input;
import nttdm.bsys.e_dim.dto.RP_E_DIM_SP1_01Output;
import nttdm.bsys.e_dim.blogic.AbstractRP_E_DIM_SP1_01BLogic;

public class RP_E_DIM_SP1_01BLogic extends AbstractRP_E_DIM_SP1_01BLogic {

    public BLogicResult execute(RP_E_DIM_SP1_01Input param) {
        BLogicResult result = new BLogicResult();

        RP_E_DIM_SP1_01Output outputDTO = new RP_E_DIM_SP1_01Output();

        // set default bank account
        List<LabelValueBean> cbBankAcc = queryDAO.executeForObjectList(
                "E_DIM.getBankAccount", null);
        outputDTO.setCbBankAcc(cbBankAcc);
        if (StringUtils.isEmpty(param.getBankAcc())) {
            outputDTO.setBankAcc("");
        } else {
            outputDTO.setBankAcc(param.getBankAcc());
        }

        // Paging
        BillingSystemSettings bss = new BillingSystemSettings(queryDAO);
        int row = bss.getResultRow();
        Integer startIndex = param.getStartIndex();
        // Get list history
        Integer totalHistories = queryDAO.executeForObject(
                "E_DIM.getTotalHistories", null, Integer.class);
        if (startIndex == null || startIndex < 0 || startIndex > totalHistories)
            startIndex = 0;
        List<HashMap> listHistories = queryDAO.executeForObjectList(
                "E_DIM.getHistories", null, startIndex, row);
        // Set paging parameter

        outputDTO.setRow(row);
        outputDTO.setTotalRow(totalHistories);
        outputDTO.setStartIndex(startIndex);
        // Set data
        outputDTO.setListHistories(listHistories);
        
        G_SET_P03 g_set_p03 = new G_SET_P03(queryDAO);
        try {
            G_SET_ReturnValue returnValue = g_set_p03.G_SET_P03_CheckStatus("G_SGP_P02");
            
            String retStatusStr = "";
            if (returnValue.getRetStatus() == -1) {
                retStatusStr = "N";
            } else {
                retStatusStr = "Y";
            }
            outputDTO.setRetStatusStr(retStatusStr);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        result.setResultObject(outputDTO);
        result.setResultString("success");
        return result;
    }
}