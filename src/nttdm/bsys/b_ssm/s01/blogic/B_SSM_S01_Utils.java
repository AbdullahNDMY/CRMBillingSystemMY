/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : B_SSM
 * SERVICE NAME   : B_SSM_S01
 * FUNCTION       : Utilities processing business logic from requests of B_SSM_S01
 * FILE NAME      : B_SSM_S01_Utils.java
 *
 * Copyright (C) 2011 NTTDATA Corporation
 * 
**********************************************************/

package nttdm.bsys.b_ssm.s01.blogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.context.ApplicationContext;

import nttdm.bsys.b_ssm.s01.data.B_SSM_S01_FieldSet;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.util.ApplicationContextProvider;
import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.web.codelist.MappedCodeListLoader;

/**
 * @author NTT Data Vietnam	
 * Utilities processing business logic from requests of B_SSM_S01
 * @param <P>
 */
public class B_SSM_S01_Utils {
	
	/**
	 * Map view data
	 * @param queryDAO 
	 * @param logicInput
	 * @param logicOutput
	 */
	public static void mapViewData(QueryDAO queryDAO,
									 HashMap<String, Object> logicInput,
									 HashMap<String, Object> logicOutput) {
		// Category Code List
		List<HashMap<String, Object>> categoryCodeList = new ArrayList<HashMap<String, Object>>();
		categoryCodeList = queryDAO.executeForObjectList("B_SSM_S01.getCategoryCodeList", logicInput);
		logicOutput.put(B_SSM_S01_FieldSet.FIELD_CATEGORYCODELIST, categoryCodeList);
		
		// Service Code List
		List<HashMap<String, Object>> serviceCodeList = new ArrayList<HashMap<String, Object>>();
		serviceCodeList = queryDAO.executeForObjectList("B_SSM_S01.getServiceCodeList", logicInput);
		logicOutput.put(B_SSM_S01_FieldSet.FIELD_SERVICECODELIST, serviceCodeList);
		
		// Plan Code List
		List<HashMap<String, Object>> planCodeList = new ArrayList<HashMap<String, Object>>();
		planCodeList = queryDAO.executeForObjectList("B_SSM_S01.getPlanCodeList", logicInput);
		logicOutput.put(B_SSM_S01_FieldSet.FIELD_PLANCODELIST, planCodeList);
		
		// Plan detail Code List
		List<HashMap<String, Object>> planDetailCodeList = new ArrayList<HashMap<String, Object>>();
		planDetailCodeList = queryDAO.executeForObjectList("B_SSM_S01.getPlanDetailCodeList", logicInput);
		logicOutput.put(B_SSM_S01_FieldSet.FIELD_PLANDETAILCODELIST, planDetailCodeList);
		
		// Carrier Code List
		List<HashMap<String, Object>> carrierCodeList = new ArrayList<HashMap<String, Object>>();
		carrierCodeList = queryDAO.executeForObjectList("B_SSM_S01.getCarrierCodeList", logicInput);
		logicOutput.put(B_SSM_S01_FieldSet.FIELD_CARRIERCODELIST, carrierCodeList);
		
		// Equipment Location Code List
		List<HashMap<String, Object>> equipmentLocationCodeList = new ArrayList<HashMap<String, Object>>();
		equipmentLocationCodeList = queryDAO.executeForObjectList("B_SSM_S01.getEquipmentLocationCodeList", logicInput);
		logicOutput.put(B_SSM_S01_FieldSet.FIELD_EQUIPMENTLOCATIONCODELIST, equipmentLocationCodeList);
		
		// Equipment Suite Code List
		List<HashMap<String, Object>> equipmentSuiteCodeList = new ArrayList<HashMap<String, Object>>();
		equipmentSuiteCodeList = queryDAO.executeForObjectList("B_SSM_S01.getEquipmentSuiteCodeList", logicInput);
		logicOutput.put(B_SSM_S01_FieldSet.FIELD_EQUIPMENTSUITECODELIST, equipmentSuiteCodeList);
		
	}
	
	/**
     * get code name by code value from code map list
     * @param codeMapListName
     * @param codeValue
     * @return
     */
    @SuppressWarnings("unchecked")
    public static String getCodeMapListNameByValue(String codeMapListName, String codeValue) {
        String codeName = "";
        ApplicationContext context = ApplicationContextProvider.
            getApplicationContext();
        MappedCodeListLoader codeMapList = (MappedCodeListLoader) 
            context.getBean(codeMapListName);
        Map<String, Object> codeMap = codeMapList.getCodeListMap();
        if (codeMap.containsKey(codeValue)){
            codeName = codeMap.get(codeValue).toString();
        }
        return codeName;
    }
	
	/**
     * change contractTermCode to name
     * @param contractTermCode
     * @return
     */
    public static String contractTermCodeToName(String contractTermCode) {
        String contractTermName = "";
        contractTermCode = CommonUtils.toString(contractTermCode).trim();
        if (!CommonUtils.isEmpty(contractTermCode)) {
            //Month
            if ("M".equals(contractTermCode)) {
                contractTermName = "Month";
            } else if ("Y".equals(contractTermCode)) {
                contractTermName = "Year";
            }
        }
        return contractTermName;
    }
    
    /**
     * trim the map value
     * @param map
     */
    @SuppressWarnings("rawtypes")
    public static void trimInputMapValue(HashMap<String, Object> map) {
        if (map != null) {
           Set set =map.keySet();
           Iterator it=set.iterator();
           while(it.hasNext()){
               String key= (String) it.next();
               Object value = map.get(key);
               if (value instanceof String) {
                   map.put(key, CommonUtils.toString(value).trim());
               }
           }
        }
    }
}
