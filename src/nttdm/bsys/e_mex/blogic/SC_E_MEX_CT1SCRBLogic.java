package nttdm.bsys.e_mex.blogic;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.bean.G_SET_ReturnValue;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.common.util.G_SET_P03;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.util.LabelValueBean;

public class SC_E_MEX_CT1SCRBLogic implements BLogic<Map> {
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
            parameter.put("idModule", RP_E_MEX_CT1BLogic.ID_MODULE);
            parameter.put("idSubModule", RP_E_MEX_CT1BLogic.ID_SUB_MODULE);
            parameter.put("idBatchType", RP_E_MEX_CT1BLogic.ID_BATCH_TYPE);
            Map<String, Object> setting = queryDAO.executeForMap(
                    "E_MEX.getSchedulerSetting", parameter);
            if (setting == null) {
                setting = new HashMap<String, Object>();
            }
            String scheduleStatus = "0";
            String scheduleDay = "0";
            String scheduleHour = "0";
            String scheduleMinute = "0";
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
            setting.put("scheduleStatus", scheduleStatus);
            setting.put("scheduleDay", scheduleDay);
            setting.put("scheduleHour", scheduleHour);
            setting.put("scheduleMinute", scheduleMinute);

            // set default bank account
            String bankAcc = String.valueOf(setting.get("ID_COM_BANK"));
            List<LabelValueBean> cbBankAcc = queryDAO.executeForObjectList(
                    "E_MEX.getBankAccount", null);
            setting.put("cbBankAcc", cbBankAcc);
            setting.put("bankAcc", bankAcc);

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
                    "E_MEX.CT1_countHistory", parameter, Integer.class);
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
                    "E_MEX.CT1_getHistory", parameter, startIndex, row);
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
            G_SET_ReturnValue returnValue = g_set_p03.G_SET_P03_CheckStatus(RP_E_MEX_CT1BLogic.ID_BATCH_TYPE);
            
            String retStatusStr = "";
            if (returnValue.getRetStatus() == -1) {
                retStatusStr = "N";
            } else {
                retStatusStr = "Y";
            }
            setting.put("retStatusStr", retStatusStr);
            
            result.setResultObject(setting);
            result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS);
        } catch (Exception e) {
            result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
        }
        return result;
    }
}
