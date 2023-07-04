/*
 * @(#)RP_B_BIL_S02_02BLogic.java
 *
 *
 */
package nttdm.bsys.b_bil.blogic;

import java.util.HashMap;
import java.util.Map;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.b_bil.bean.T_BIL_HeaderInfo;
import nttdm.bsys.b_bil.dto.RP_B_BIL_S02_02_01Input;
import nttdm.bsys.b_bil.dto.RP_B_BIL_S02_02_01Output;

/**
 * BusinessLogic class.
 * 
 * @author khungl0ng
 */
public class RP_B_BIL_S02_06BLogic extends AbstractRP_B_BIL_S02_02BLogic {

    /**
     * 
     * @param param RP_B_BIL_S02_02_01Input
     * @return ビジ�?スロジック�?�実行�?果�?BLogicResultインスタンス。
     */
    public BLogicResult execute(RP_B_BIL_S02_02_01Input param) {
        BLogicResult result = new BLogicResult();
        RP_B_BIL_S02_02_01Output outputDTO = new RP_B_BIL_S02_02_01Output();
        String idRef = param.getIdRef();
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("idRef", idRef);
        T_BIL_HeaderInfo bilHeaderInfo = new T_BIL_HeaderInfo();
        bilHeaderInfo.setIdRef(idRef);
        bilHeaderInfo.setMode("edit");
        bilHeaderInfo.setIsDuplicate("1");
        outputDTO.setBilHeaderInfo(bilHeaderInfo);
        result.setResultObject(outputDTO);
        result.setResultString("successBilS03");
        return result;
    }
}