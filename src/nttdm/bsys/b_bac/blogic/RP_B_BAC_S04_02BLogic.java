/*
 * @(#)RP_B_BAC_S04_02BLogic.java
 */
package nttdm.bsys.b_bac.blogic;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.b_bac.dto.RP_B_BAC_S01_02Input;
import nttdm.bsys.b_bac.dto.RP_B_BAC_S01_02Output;
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.common.util.CommonUtils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

/**
 * previous B_BAC_S01<br>
 * B_BAC_S04 search logic
 * 
 * @author khungl0ng
 */
public class RP_B_BAC_S04_02BLogic extends AbstractRP_B_BAC_S01_02BLogic {

    /**
     * @param param
     * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
     */
    public BLogicResult execute(RP_B_BAC_S01_02Input param) {
        BLogicResult result = new BLogicResult();
        RP_B_BAC_S01_02Output outputDTO = new RP_B_BAC_S01_02Output();
        // get the number of row for paging
        BillingSystemSettings bss = new BillingSystemSettings(queryDAO);
        int row = bss.getResultRow();
        Integer startIndex = param.getStartIndex();
        // mapping param
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("cboCustomerName", param.getCboCustomerName());
        m.put("tbxBillingAccount", CommonUtils.escapeSQL(param.getTbxBillingAccount()));
        m.put("tbxSubscription", CommonUtils.escapeSQL(StringUtils.trim(param.getTbxSubscription())));
        m.put("chkStatus", param.getChkStatus());
        m.put("isDeleted", "0");

        // count total record
        Integer totalReport =
            queryDAO.executeForObject("B_BAC.countBillingAccount", m, Integer.class);

        if(totalReport<=0){
            outputDTO.setNoRecord("Y");
        }
        
        if (startIndex == null || startIndex < 0 || startIndex > totalReport)
            startIndex = 0;
        
        // get id_cust_plan  display result by this
        List<String> idCustPlanList = 
            queryDAO.executeForObjectList("B_BAC.getIdCustPlanGrp", m ,startIndex, row);
        
        List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String, Object>>();
        
        int count = startIndex+1;
        
        for (int i = 0; i < idCustPlanList.size(); i++) {
            String idCustPlan = idCustPlanList.get(i);
            
            HashMap<String,Object> bacInfo = (HashMap<String,Object>)queryDAO.executeForMap("B_BAC.getBillAccInfoByIdCustPlan", idCustPlan);
            
            // get Reference
            String reference = queryDAO.executeForObject("B_BAC.getReferenceByIdCustPlan", idCustPlan, String.class);
            bacInfo.put("ID_BIF", reference);
            
            // count
            bacInfo.put("COUNT", count);
            
            // get custPlanD info
            List<Map<String,Object>>  custPlanD = queryDAO.executeForMapList("B_BAC.getCustPlanDetailInfo", idCustPlan);
            bacInfo.put("CUST_PLAN_D_INFO", custPlanD);

            resultList.add(bacInfo);
            count++;
        }
        
        // get M_GSET_D info: FIXED_FOREX
        String fixedForex = queryDAO.executeForObject("B_BAC.getMGSetFixedForex", null, String.class);
        outputDTO.setFixedForex(fixedForex);

        try {
            BeanUtils.copyProperties(outputDTO, param);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        outputDTO.setListBillingAccount(resultList);
        outputDTO.setTotalRow(totalReport);
        outputDTO.setRow(row);
        outputDTO.setBss(bss);
        outputDTO.setNewFlg("1");
        result.setResultObject(outputDTO);
        result.setResultString("success");
        return result;
    }
}