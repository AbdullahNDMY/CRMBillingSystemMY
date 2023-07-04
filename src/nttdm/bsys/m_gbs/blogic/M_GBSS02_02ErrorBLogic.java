package nttdm.bsys.m_gbs.blogic;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogic;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.m_gbs.bean.ResultItem;

/**
 * M_SSMS02_02ErrorBLogic<br>
 * <ul>
 * <li>BusinessLogic class.
 * </ul>
 * <br>
 * 
 * @author NTTData Vietnam
 * @version 1.0
 */
public class M_GBSS02_02ErrorBLogic implements BLogic<Map<String, Object>> {

    /**
     * 
     * @param param
     * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
     */
    public BLogicResult execute(Map<String, Object> input) {

        BLogicResult result = new BLogicResult();

        String resultString = "success";

        Map<String, Object> output = new HashMap<String, Object>();

        List<ResultItem> listResult = (List<ResultItem>) input.get("listResult");

        String precolumnType = CommonUtils.toString(input.get("preSelItem"));
        if (!"CG".equals(precolumnType) && (!"EU".equals(precolumnType)) && (!"RT".equals(precolumnType))){
        	Collections.sort(listResult, new Comparator<ResultItem>() {
                public int compare(ResultItem o1, ResultItem o2) {
                	
                    return o1.getItemName().compareTo(o2.getItemName());
                }
            });
        }
        
        
        output.put("listResult", listResult);
        result.setResultString(resultString);
        result.setResultObject(output);
        return result;
    }
}
