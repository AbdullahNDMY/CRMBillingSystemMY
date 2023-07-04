/*
 * @(#)RP_B_BIL_S03_01BLogic.java
 *
 *
 */
package nttdm.bsys.b_bil.blogic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.b_bil.blogic.AbstractRP_B_BIL_S03_01BLogic;
import nttdm.bsys.b_bil.dto.RP_B_BIL_S03_01Input;
import nttdm.bsys.b_bil.dto.RP_B_BIL_S03_01Output;
import nttdm.bsys.common.util.CommonUtils;

import org.apache.commons.beanutils.BeanUtils;

/**
 * BusinessLogic class.
 * 
 * @author khungl0ng
 */
public class RP_B_BIL_S03_01BLogic extends AbstractRP_B_BIL_S03_01BLogic {

	/**
	 * 
	 * @param param
	 * @return ビジ�?スロジック�?�実行�?果�?BLogicResultインスタンス。
	 */
	public BLogicResult execute(RP_B_BIL_S03_01Input param) {
		BLogicResult result = new BLogicResult();
		RP_B_BIL_S03_01Output outputDTO = new RP_B_BIL_S03_01Output();
		String idRef = param.getIdRef();
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("idRef", idRef);
		// get header info
		HashMap<String, Object> headerInfo = (HashMap<String, Object>) this.queryDAO.executeForMap("B_BIL.getHeaderInfo", m);
		CommonUtils.fixAddress4(headerInfo);
		// get detail info
		List<HashMap<String, Object>> detailInfo = this.queryDAO.executeForObjectList("B_BIL.getDetailInfo", m);
		for(HashMap<String, Object> temp : detailInfo) {
        	String itemDesc = clobToString((Clob)temp.get("ITEM_DESC_TEXT"));
        	temp.put("ITEM_DESC_TEXT", itemDesc);
        }
		// get user access type
		Map<String, Object> m1 = new HashMap<String, Object>();
		m1.put("idUser", param.getUvo().getId_user());
		HashMap<String, Object> userAccess = (HashMap<String, Object>) 
												this.queryDAO.executeForMap("B_BIL.getAccessType", m1);
		List<Map<String, Object>> footerInfo = this.queryDAO.executeForMapList("B_BIL.getFooterInfo", null);
		try {
			BeanUtils.copyProperties(outputDTO, param);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		//print times
        int printCount = 0 ;
        String strPrintCount = CommonUtils.toString(headerInfo.get("NO_PRINTED"));
        if (!CommonUtils.isEmpty(strPrintCount)){
            printCount = Integer.parseInt(strPrintCount) ;
        }
        //permission print times
        int permisPrintCount = 0 ;
        String strPermisPrintCount = CommonUtils.toString(queryDAO.
                executeForObject("B_BIL.selectPermisTimes", null, String.class));
        if (!CommonUtils.isEmpty(strPermisPrintCount)){
            permisPrintCount = Integer.parseInt(strPermisPrintCount) ;
        }
        //allow print
        if(printCount<permisPrintCount){
            headerInfo.put("PRINT_FLAG", "1");
        }
		
		// get bankFooterInfo
        List<Map<String, Object>> bankFooterInfo = this.queryDAO.executeForMapList("B_BIL.getBankFooterInfo", null);
        String billCurrency = CommonUtils.toString(headerInfo.get("BILL_CURRENCY")).trim();
        B_BIL_CommonUtil bilUtil = new B_BIL_CommonUtil(queryDAO, updateDAO);
        bankFooterInfo = bilUtil.getBankFooterInfo(billCurrency, bankFooterInfo);
        
        //fromPageFlag is 1 means page from B_BIL_S01
        headerInfo.put("fromPageFlag", param.getFromPageFlag());
        headerInfo.put("model", param.getMode());
		outputDTO.setHeaderInfo(headerInfo);
		outputDTO.setDetailInfo(detailInfo);
		outputDTO.setFooterInfo(footerInfo);
		outputDTO.setBankFooterInfo(bankFooterInfo);
		outputDTO.setAccessType(userAccess.get("ACCESS_TYPE").toString());
		result.setResultObject(outputDTO);
		result.setResultString("success");
		return result;
	}
	private String clobToString(Clob data) {
        StringBuilder sb = new StringBuilder();
        try {
            Reader reader = data.getCharacterStream();
            BufferedReader br = new BufferedReader(reader);

            String line;
            while(null != (line = br.readLine())) {
                sb.append(line);
                sb.append("\n");
            }
            br.close();
        } catch (SQLException e) {
            // handle this exception
        } catch (IOException e) {
            // handle this exception
        }
        return sb.toString();
    }
}