/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : Customer Plan Management (Add Standard Plan)
 * SERVICE NAME   : B_CPM_S02
 * FUNCTION       : Add selected plan in M-PPM to Standard Plan
 * FILE NAME      : B_CPM_S02AddPlanBLogic.java
 * 
 * Copyright c 2011 NTTDATA Corporation
 *
 * History
 * 2011/07/26 [Duong Nguyen] Created
 * 
**********************************************************/
package nttdm.bsys.b_cpm.blogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts.util.LabelValueBean;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.b_cpm.common.B_CPM_S02Util;
import nttdm.bsys.b_cpm_en.dto.CustomerPlan;
import nttdm.bsys.b_cpm_en.dto.CustomerPlanService;
import nttdm.bsys.b_cpm_en.dto.CustomerSubPlan;
import nttdm.bsys.b_cpm_en.dto.CustomerSubPlanDetail;
import nttdm.bsys.common.util.CommonUtils;

/**
 * B_CPM_S02AddPlanBLogic.class<br>
 * <ul>
 * <li>process add standard plan</li>
 * </ul>
 * <br>
 * @author  NTTData Vietnam
 * @version 1.1
 */
public class B_CPM_S02AddPlanBLogic extends AbstractB_CPM_S02AddPlanBLogic {

	/**
	 * 
	 * @param param
	 * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
	 */
	public BLogicResult execute(HashMap<String, Object> param) {
		BLogicResult result = new BLogicResult();

		// TODO
		// QueryDAO 記述例
		// SampleUVO uvo = queryDAO.executeForObject("namespace.sqlID",
		// params, SampleUVO().class);
		//
		// UpdateDAO 記述例
		// updateDAO.execute("namespace.sqlID", params);
		//
		B_CPM_S02Util util = new B_CPM_S02Util(this.queryDAO);
		String strIdPlanGrpList = param.get("idPlanGrpList").toString();
		String idCust = param.get("idCust").toString();
		String serviceMultiPln = CommonUtils.toString(param.get("serviceMultiPln"));
		String[] idPlanGrpList = strIdPlanGrpList.split(",");
		List<Map<String, Object>> svcLevel2List=util.getSVCLevel2();
		List<Map<String, Object>> svcLevel3List=util.getSVCLevel3();
		String jobNoAllChk=param.get("jobNoAllChk").toString();
		String jobNoAllVal=param.get("jobNoAllVal").toString();
		
		//get sub plan list
		List<CustomerSubPlan> subPlans = util.getSubPlanByIdList(idPlanGrpList);
		for (CustomerSubPlan subPlan : subPlans) {
		    if ("0".equals(serviceMultiPln)) {
		        subPlan.setItemDesc(subPlan.getItemGrpName());
		    }
			subPlan.setSubPlanDetails(util.getSubPlanDetailByPlan(subPlan));
			subPlan.setQuantity("1");
			subPlan.setUnitPrice(subPlan.getRate());
			subPlan.setIsDiscountOneTime("O");
			if(jobNoAllChk.equals("allchk")){
			    subPlan.setJobNo(jobNoAllVal);
			}
			String svcLevel2Id=CommonUtils.toString(subPlan.getSvcLevel2());
			String svcLevel2DescAbbr=getSvcDescAbbr(svcLevel2List,svcLevel2Id);
			//set LineDesc in details
			for (CustomerSubPlanDetail subPlanDetail : subPlan.getSubPlanDetails()) {
			    String svcLevel3Id=CommonUtils.toString(subPlanDetail.getSvcLevel3());
			    String svcLevel3DescAbbr=getSvcDescAbbr(svcLevel3List,svcLevel3Id);
			    subPlanDetail.setLineDesc(svcLevel2DescAbbr+"-"+svcLevel3DescAbbr);
            }
			//subPlanDetails() is empty
			if(subPlan.getSubPlanDetails().size()==0){
			    subPlan.setPlanLineDesc(svcLevel2DescAbbr+"-");
			}
			
			// rateType2
            Map<String, String> rateType2 = new HashMap<String, String>();
            String rateMode = subPlan.getRateMode();
            String modeType = "M";
            if ("6".equals(rateMode)) {
    			modeType = "O";
    		}
            rateType2.put("modeType", modeType);
            rateType2.put("idCust", idCust);
            subPlan.setRateType2(util.getRateTypeVal(rateType2));
		}
		
		//add subPlans to service
		CustomerPlanService service = new CustomerPlanService();
		service.setSubPlans(subPlans);
		
		//add service to customerPlan
		CustomerPlan customerPlan = new CustomerPlan();
		customerPlan.getServices().add(service);
		
		//load common data
		Map<String, List<LabelValueBean>> resutlMapSvc2=setSvcLevelToMap(svcLevel2List);
        Map<String, List<LabelValueBean>> resutlMapSvc3=setSvcLevelToMap(svcLevel3List);
        Map<String, List<LabelValueBean>> resutlMapSvc4=setSvcLevelToMap(util.getSVCLevel4());
		Map<String, Object> loadObject = new HashMap<String, Object>();
		loadObject.put("SVC_LEVEL1", util.getSVCLevel1());
		loadObject.put("SVC_LEVEL2", svcLevel2List);
		loadObject.put("SVC_LEVEL3", svcLevel3List);
		loadObject.put("SVC_LEVEL4", util.getSVCLevel4());
		loadObject.put("MLABELVALUEBEAN2", resutlMapSvc2);
        loadObject.put("MLABELVALUEBEAN3", resutlMapSvc3);
        loadObject.put("MLABELVALUEBEAN4", resutlMapSvc4);
		loadObject.put("VENDOR", util.getVendor());
		loadObject.put("JOB_NO", util.getJobNo(idCust));
		
		//M_JNM Display flag
        customerPlan.setM_jnmDisplayFlg(util.getIsJNMModulesDisplayFlg());
		
        //#200, #201 wcbeh@20160926 - Get Master Location Display condition
        customerPlan.setMasterLocationDisplayFlg(util.getIsMasterLocationDisplayFlg());
        
        // custPo display
        String custPoDis = util.getCustPoDisplay();
        loadObject.put("CustPoDisplay", custPoDis);
        
        // rateType2
        String rateType2Flg = util.getRateType();
        loadObject.put("RateType2Flg", rateType2Flg);
        
		String taxWord = CommonUtils
				.toString(queryDAO.executeForObject("B_CPM.GET_CPM_TAX_WORD", null, String.class));
		customerPlan.setTaxWord(taxWord);
		String taxIdDefault = CommonUtils
				.toString(queryDAO.executeForObject("B_CPM.GET_CPM_TAX_ID_DEFAULT", null, String.class));
		customerPlan.setTaxIdDefault(taxIdDefault);
        
		//set result object
		Map<String, Object> resultObject = new HashMap<String, Object>();
		resultObject.put("customerPlan", customerPlan);
		resultObject.put("LOAD_OBJECT", loadObject);
		
		result.setResultObject(resultObject);
		result.setResultString("success");
		return result;
	}
	
	/**
	 * Get SvcLevelInfo according to SvcLevel1 Then insert into Map, 
	 * @param paramSvcLevel
	 * @return resultMap
	 */
	private Map<String, List<LabelValueBean>> setSvcLevelToMap(List<Map<String, Object>> paramSvcLevel){
        Map<String, List<LabelValueBean>> resultMap=new  HashMap<String, List<LabelValueBean>>();
        
        List<LabelValueBean> resultList;
        for (int j = 0; j < paramSvcLevel.size(); j++) {
            Map<String, Object> paramsvcmap=paramSvcLevel.get(j);
            String paramkey=CommonUtils.toString(paramsvcmap.get("SVC_GRP"));
            if (resultMap.containsKey(paramkey)) {
                String label=CommonUtils.toString(paramsvcmap.get("SVC_NAME"));
                String value=CommonUtils.toString(paramsvcmap.get("ID_SERVICE"));
                resultList=resultMap.get(paramkey);
                resultList.add(new org.apache.struts.util.LabelValueBean(label,value));
            }else{
                resultList=new ArrayList<LabelValueBean>();
                String label=CommonUtils.toString(paramsvcmap.get("SVC_NAME"));
                String value=CommonUtils.toString(paramsvcmap.get("ID_SERVICE"));
                resultList.add(new org.apache.struts.util.LabelValueBean(label,value));
                resultMap.put(paramkey, resultList);
            }
        }
        return resultMap;
    }
	
	/**
     * Get DescAbbrInfo according to  idService
     * @param svclevelList
     * @return descAbbr
     */
	private String getSvcDescAbbr(List<Map<String, Object>> svclevelList,String idService){
	    String descAbbr="";
	    for (int i = 0; i < svclevelList.size(); i++) {
            Map<String, Object> map = svclevelList.get(i);
            String key=CommonUtils.toString(map.get("ID_SERVICE"));
            if (idService.equals(key)){
                descAbbr=CommonUtils.toString(map.get("DESC_ABBR"));
            }
        }
	    return descAbbr;
	}
}