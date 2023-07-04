package nttdm.bsys.m_gbs.blogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.m_gbs.bean.ResultItem;

/**
 * M_SSMS02_01BLogic<br>
 * <ul>
 * <li>BusinessLogic class.
 * </ul>
 * <br>
 * 
 * @author ws
 * @version 1.0
 */
public class M_GBSS02_01BLogic implements BLogic<Map<String, Object>> {

    /**
     * queryDAO
     */
    protected QueryDAO queryDAO;

    /**
     * 
     * @param param
     * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
     */
    public BLogicResult execute(Map<String, Object> input) {
        BLogicResult result = new BLogicResult();
        Map<String, Object> output = new HashMap<String, Object>();

        // column name
        String columnType = CommonUtils.toString(input.get("columnType"));
        List<ResultItem> listResult = new ArrayList<ResultItem>();

        if ("CA".equals(columnType)) {

            // Carrier
            listResult = queryDAO.executeForObjectList("M_GBS.getCarriers", null);
            for (ResultItem item : listResult) {
                Integer c = queryDAO.executeForObject("M_GBS.getCarrierIsUsed", item.getItemId(), Integer.class);
                if (c == 0) {
                    item.setIsUsed("N");
                }
            }

        } 
        //Add by MiffyAn Start
        else if ("CT".equals(columnType)) {
            // ComplayTypeGroup
            listResult = queryDAO.executeForObjectList("M_GBS.getComplayType", null);
        }else if ("CC".equals(columnType)) {
            // ComplayCategoryGroup
            listResult = queryDAO.executeForObjectList("M_GBS.getComplayCategory", null);
        }else if ("CS".equals(columnType)) {

            // ComplaySubCategoryGroup
            listResult = queryDAO.executeForObjectList("M_GBS.getComplaySubCategory", null);
        }else if ("PR".equals(columnType)) {
            // PrpductGroup
            listResult = queryDAO.executeForObjectList("M_GBS.getComplayPrpduct", null);
        }else if ("JN".equals(columnType)) {
            // JOURNAL_NO
            listResult = queryDAO.executeForObjectList("M_GBS.getJournalNo", null);
        }
        //Add by MiffyAn End
        
        else if ("CG".equals(columnType)) {
            // CustomerGroup
            listResult = queryDAO.executeForObjectList("M_GBS.getCustomerGroup", null);
        } else if ("EU".equals(columnType)) {

            // End User
            listResult = queryDAO.executeForObjectList("M_GBS.getEndUser", null);
            for (ResultItem item : listResult) {
                Integer c = queryDAO.executeForObject("M_GBS.getEndUserCount", item.getItemCode(), Integer.class);
                Integer d = queryDAO.executeForObject("M_GBS.getRTEndUserCount", item.getItemCode(), Integer.class);
                if (c + d == 0) {
                    item.setIsUsed("N");
                }
            }

        } else if ("LO".equals(columnType)) {
        	
            // Location
            listResult = queryDAO.executeForObjectList("M_GBS.getEquipLocations", null);
            for (ResultItem item : listResult) {
                Integer c = queryDAO.executeForObject("M_GBS.getLocationIsUsed", item.getItemId(), Integer.class);
                if (c == 0) {
                    item.setIsUsed("N");
                }
            }

        } else if ("ML".equals(columnType)) {
        	//wcbeh@20160920 - Add Master Location
            // Master Location
            listResult = queryDAO.executeForObjectList("M_GBS.getMasterLocation", null);
            for (ResultItem item : listResult) {
                Integer c = queryDAO.executeForObject("M_GBS.getMasterLocationIsUsed", item.getItemName(), Integer.class);
                if (c == 0) {
                    item.setIsUsed("N");
                }
            }

        } else if ("FT".equals(columnType)) {

            // Firewall Type
            listResult = queryDAO.executeForObjectList("M_GBS.getFirewallTypes", null);
            for (ResultItem item : listResult) {
                Integer c = queryDAO.executeForObject("M_GBS.getFirewallTypesIsUsed", item.getItemName(), Integer.class);
                if (c == 0) {
                    item.setIsUsed("N");
                }
            }

        } else if ("FM".equals(columnType)) {
            // Firewall Model
            listResult = queryDAO.executeForObjectList("M_GBS.getFirewallModels", null);
            for (ResultItem item : listResult) {
                Integer c = queryDAO.executeForObject("M_GBS.getFirewallModelsIsUsed", item.getItemName(), Integer.class);
                if (c == 0) {
                    item.setIsUsed("N");
                }
            }
        } else if ("RT".equals(columnType)) {

            // Rate Type 2
            listResult = queryDAO.executeForObjectList("M_GBS.getRateType", null);
            for (ResultItem item : listResult) {
                Integer c = queryDAO.executeForObject("M_GBS.getCustPlanLink", item.getItemCode(), Integer.class);                
                if (c == 0) {
                    item.setIsUsed("N");
                }
            }

        }else if("TC".equals(columnType)){
            //Tax Code
            listResult = queryDAO.executeForObjectList("M_GBS.getTaxCode", null);
            for (ResultItem item : listResult) {
                //ScreenID:PPM --> If (M_PLAN_D.APPLY_GST = M_TAX.ID_TAX)  result count>0 then set'Y' else set'N'
                Integer c1 = queryDAO.executeForObject("M_GBS.getPPMTaxCodeIsUsed", item.getTaxId(), Integer.class);
                //ScreenID:CPM --> If (T_CUST_PLAN_LINK.APPLY_GST = M_TAX.ID_TAX)  result count>0 then set'Y' else set'N'
                Integer c2 = queryDAO.executeForObject("M_GBS.getCPMTaxCodeIsUsed", item.getTaxId(), Integer.class);
                
                if (c1.intValue()==0&&c2.intValue()==0) {
                    item.setIsTaxUsed("N");
                }
            }
        }  

        output.put("preSelItem", columnType);
        output.put("listResult", listResult);
        output.put("columnType", columnType);
        result.setResultString("success");
        result.setResultObject(output);
        return result;
    }

    public QueryDAO getQueryDAO() {
        return queryDAO;
    }

    public void setQueryDAO(QueryDAO queryDAO) {
        this.queryDAO = queryDAO;
    }
}