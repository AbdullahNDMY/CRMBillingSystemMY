/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : E_SET
 * SERVICE NAME   : E_SET_S02
 * FUNCTION       : Showing Batch execute history
 * FILE NAME      : RP_E_SET_S02BLogic.java
 * 
 * Copyright © 2011 NTTDATA Corporation
 *
 * History
 * 
 * 
 **********************************************************/
package nttdm.bsys.e_set.blogic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.e_set.dto.PR_E_SET_S02Output;
import nttdm.bsys.e_set.dto.RP_E_SET_S02Input;

/**
 * RP_E_SET_S02BLogic<br>
 * <ul>
 * <li>A BLogic class to process displaying Batch maintenance Settings History
 * </ul>
 * <br>
 * 
 * @author NTTData
 * @version 1.0
 */
public class RP_E_SET_S02BLogic extends AbstractRP_E_SET_S02BLogic {

    public BLogicResult execute(RP_E_SET_S02Input input) {
        BLogicResult result = new BLogicResult();
        PR_E_SET_S02Output output = new PR_E_SET_S02Output();

        String SCR_ID = input.getSCR_ID();
        String FUNC_ID = input.getFUNC_ID();
        String TITLE_HDR = input.getTITLE_HDR();

        // Paging
        BillingSystemSettings bss = new BillingSystemSettings(queryDAO);
        int row = bss.getResultRow();
        Integer startIndex = input.getStartIndex();

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("BATCH_TYPE", FUNC_ID);

        Integer taotalRow = this.getQueryDAO().executeForObject(
                "E_SET.getHistorieCount", param, Integer.class);

        if (startIndex == null || startIndex < 0 || startIndex > taotalRow) {
            startIndex = 0;
        }

        List<Map<String, Object>> histories = this.getQueryDAO()
                .executeForMapList("E_SET.getHistories", param, startIndex, row);

        output.setSCR_ID(SCR_ID);
        output.setFUNC_ID(FUNC_ID);
        output.setTITLE_HDR(TITLE_HDR);

        output.setRow(row);
        output.setStartIndex(startIndex);
        output.setTotalRow(taotalRow);
        output.setListHistories(histories);

        result.setResultObject(output);
        result.setResultString("success");
        return result;
    }
}
