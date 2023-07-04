package nttdm.bsys.e_mex.blogic;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.bean.G_SET_ReturnValue;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.common.util.G_SET_P03;

import org.apache.commons.lang.StringUtils;

public class SC_E_MEX_SP1SCRBLogic implements BLogic<Map> {

    private QueryDAO queryDAO;

    public QueryDAO getQueryDAO() {
        return queryDAO;
    }

    public void setQueryDAO(QueryDAO queryDAO) {
        this.queryDAO = queryDAO;
    }

    public BLogicResult execute(Map param) {
        BLogicResult result = new BLogicResult();

        try {
            Map<String, Object> parameter = new HashMap<String, Object>();
            parameter.put("idBatchType", RP_E_MEX_SP1BLogic.ID_BATCH_TYPE);
            Map<String, Object> setting = queryDAO.executeForMap(
                    "E_MEX.getSchedulerSetting", parameter);
            if (setting == null) {
                setting = new HashMap<String, Object>();
            }
            String scheduleStatus = "0";
            String scheduleDay = "0";
            String scheduleHour = "0";
            String scheduleMinute = "0";
            String deductionDate = "";
            if (StringUtils.isNumeric(String.valueOf(setting.get("IS_ACTIVE")))) {
                scheduleStatus = (String) setting.get("IS_ACTIVE");
            }
            if (StringUtils.isNumeric(String.valueOf(setting.get("EXEC_DAY")))) {
                scheduleDay = ((java.math.BigDecimal) setting.get("EXEC_DAY"))
                        .toString();
            }
            if (StringUtils.isNumeric(String.valueOf(setting.get("EXEC_HOUR")))) {
                scheduleHour = ((java.math.BigDecimal) setting.get("EXEC_HOUR"))
                        .toString();
            }
            if (StringUtils
                    .isNumeric(String.valueOf(setting.get("EXEC_MINUTE")))) {
                scheduleMinute = ((java.math.BigDecimal) setting
                        .get("EXEC_MINUTE")).toString();
            }
            if (StringUtils.isNumeric(String.valueOf(setting
                    .get("GIRO_DEDUCT_DAY")))) {
                deductionDate = ((java.math.BigDecimal) setting
                        .get("GIRO_DEDUCT_DAY")).toString();
            }
            setting.put("scheduleStatus", scheduleStatus);
            setting.put("scheduleDay", scheduleDay);
            setting.put("scheduleHour", scheduleHour);
            setting.put("scheduleMinute", scheduleMinute);
            setting.put("deductionDate", deductionDate);
            // current date
            Calendar cal = Calendar.getInstance();
            int month = cal.get(Calendar.MONTH) + 1;
            int year = cal.get(Calendar.YEAR);
            // preview month
//            if (month == 1) {
//                month = 12;
//                year -= 1;
//            } else {
//                month -= 1;
//            }
            setting.put("closingMonth", month + "");
            setting.put("closingYear", year + "");

            // get history
            Integer countHistory = queryDAO.executeForObject(
                    "E_MEX.SP1_countHistory", parameter, Integer.class);
            // pagination
            BillingSystemSettings bss = new BillingSystemSettings(queryDAO);
            int row = bss.getResultRow();
            int startIndex = 0;
            if (param.get("startIndex") == null) {
                startIndex = 0;
            } else {
                startIndex = (Integer) param.get("startIndex");
            }
            Object historyList = queryDAO.executeForMapList(
                    "E_MEX.SP1_getHistory", parameter, startIndex, row);
            setting.put("row", row);
            setting.put("totalRow", countHistory);
            setting.put("startIndex", startIndex);
            setting.put("historyList", historyList);
            if (scheduleStatus != null)
                if (scheduleStatus.equals("1")) // Active
                    setting.put("runtimeType", "1");
                else
                    // In-Active
                    setting.put("runtimeType", "2");

            G_SET_P03 g_set_p03 = new G_SET_P03(queryDAO);
            G_SET_ReturnValue returnValue = g_set_p03.G_SET_P03_CheckStatus(RP_E_MEX_SP1BLogic.ID_BATCH_TYPE);
            String retStatusStr = "";
            if (returnValue.getRetStatus() == -1) {
                retStatusStr = "N";
            } else {
                retStatusStr = "Y";
            }
            setting.put("retStatusStr", retStatusStr);
            result.setResultObject(setting);
            result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
            return result;
        }
    }
}
