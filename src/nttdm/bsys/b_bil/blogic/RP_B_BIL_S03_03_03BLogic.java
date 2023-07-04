/*
 * @(#)RP_B_BIL_S03_03_03BLogic.java
 *
 *
 */
package nttdm.bsys.b_bil.blogic;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.b_bil.bean.T_BIL_HeaderInfo;
import nttdm.bsys.b_bil.dto.RP_B_BIL_S03_03_03Input;
import nttdm.bsys.b_bil.dto.RP_B_BIL_S03_03_03Output;
import nttdm.bsys.common.util.CommonUtils;

/**
 * BusinessLogic class.
 * 
 * @author khungl0ng
 */
public class RP_B_BIL_S03_03_03BLogic extends AbstractRP_B_BIL_S03_03_03BLogic {

    /**
     * 
     * @param param RP_B_BIL_S03_03_03Input
     * @return ビジ�?スロジック�?�実行�?果�?BLogicResultインスタンス。
     */
    public BLogicResult execute(RP_B_BIL_S03_03_03Input param) {
        BLogicResult result = new BLogicResult();
        RP_B_BIL_S03_03_03Output outputDTO = new RP_B_BIL_S03_03_03Output();
        
        //the page data
        T_BIL_HeaderInfo bilHeaderInfo = param.getBilHeaderInfo();
        B_BIL_CommonUtil b_bilCommmonUtil = new B_BIL_CommonUtil(queryDAO, updateDAO);
        //get the Combox Box Data
        b_bilCommmonUtil.getComboBoxsData(bilHeaderInfo);
        //the Combo Box change event
        b_bilCommmonUtil.comboBoxChangeEvt(bilHeaderInfo, B_BIL_CommonUtil.EVENT_TYPE_SAVA_BUTTON_CLICK);
        
        //#154 start
        String gstCheck = CommonUtils.toString(queryDAO.executeForObject("B_BIL.getM_GSET_D_SetValue", null, String.class));
        outputDTO.setGstCheck(gstCheck);
        //#154 end
        outputDTO.setBilHeaderInfo(bilHeaderInfo);
        result.setResultObject(outputDTO);
        result.setResultString("success");
        return result;
    }
}