package nttdm.bsys.bif.blogic;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.Math;
import java.math.BigDecimal;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.bif.bean.FileInfo;
import nttdm.bsys.bif.bean.WF_DOC;
import nttdm.bsys.bif.dto.B_BIFS02_03Input;
import nttdm.bsys.bif.dto.B_BIFS02_03Output;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.G_CUR_P01;

import org.apache.struts.Globals;

public class B_BIFS02_03BLogic extends AbstractB_BIFS02_03BLogic{
	
	
	private static final String SELECT_BIF_H = "SELECT.B_BIF.S02_03.SQL001";
	private static final String SELECT_BIF_CUS_NAME = "SELECT.B_BIF.S02_01.SQL001";
	private static final String SELECT_BIF_CUS_ADR = "SELECT.B_BIF.S02_03.SQL002";

	private static final String SELECT_BIF_COLS_NAME = "SELECT.B_BIF.S02_03.SQL003";
	
	private static final String SELECT_BIF_PLAN = "SELECT.B_BIF.S02_01.SQL017_01";
	private static final String SELECT_BIF_PLAN_S = "SELECT.B_BIF.S02_01.SQL018";
	private static final String SELECT_BIF_PLAN_O = "SELECT.B_BIF.S02_01.SQL019";
	private static final String SELECT_BIF_GST = "SELECT.B_BIF.S02_01.SQL015";
	private static final String SELECT_BIF_RATE_CURRENCY = "SELECT.B_BIF.S02_01.SQL016";
	private static final String SQL_SELECT_T_WF_DOC = "SELECT.B_BIF.S02_04.SQL001";
	private static final String SELECT_BILL_ACC_TAX_TYPE_02 = "SELECT.B_BIF.SQL_BILL_ACC_TAX_TYPE";
	
	static final String SAVE_ERROR_MSG = "info.ERR2SC004";
	
	
	@SuppressWarnings("unchecked")
	public BLogicResult execute(B_BIFS02_03Input input) {
		// TODO Auto-generated method stub
		BLogicMessages errors = new BLogicMessages();
		BLogicResult result = new BLogicResult();		
		try{ 
			B_BIFS02_03Output out = new B_BIFS02_03Output();
			
			String idRef = input.getIdRef();			
			Map bifInfo = queryDAO.executeForMap(SELECT_BIF_H, idRef);
			
			String idCust = (String) bifInfo.get("ID_CUST");			
			Map cusInfo = queryDAO.executeForMap(SELECT_BIF_CUS_NAME, idCust);
			
			Map param = new HashMap();
			param.put("idCust", idCust);
			param.put("adrType", bifInfo.get("ADR_TYPE"));
			Map cusAdr = queryDAO.executeForMap(SELECT_BIF_CUS_ADR, param);
			
			
			String colsNames = queryDAO.executeForObject(SELECT_BIF_COLS_NAME, bifInfo.get("ID_CONSLT"), String.class);
			bifInfo.put("CONSLT_NAME", colsNames);
						
			List plans = queryDAO.executeForMapList(SELECT_BIF_PLAN, idRef);
			double totalAmount = 0;
			double totalGst = 0;
			BigDecimal subtotalTaxable = new BigDecimal("0");
			BigDecimal subtotalNonTaxable = new BigDecimal("0");
			String currency = "";
			String exportCurrency = "";
			String fixedForex = "";
			for(int i=0;i<plans.size();i++){
				Map<String, Object> plan = (Map<String, Object>) plans.get(i);
				if(currency.equals("")){
					if(plan.get("BILL_CURRENCY") != null){
						currency = plan.get("BILL_CURRENCY") + "";
					}
				}
				if(exportCurrency.equals("")){
					if(plan.get("EXPORT_CURRENCY") != null){
						exportCurrency = plan.get("EXPORT_CURRENCY") + "";
					}
				}
				if(fixedForex.equals("")){
					if(plan.get("FIXED_FOREX") != null){
						fixedForex = plan.get("FIXED_FOREX").toString();
					}
				}
				if (plan.get("IS_DISPLAY_DESC").equals("1")) {
					plan.put("item", i+1);
					totalAmount += Double.parseDouble(plan.get("AMOUNT").toString());
				}
			}						
			List<Map<String, Object>> planSs = queryDAO.executeForMapList(SELECT_BIF_PLAN_S, idRef);
			for(int i=0;i<planSs.size();i++){
				Map<String, Object> plan = (Map<String, Object>) planSs.get(i);
				plan.put("item", i+1+plans.size());
				if (plan.get("IS_DISPLAY_DESC").equals("0") && plan.get("TOTAL_AMOUNT") != null) {
					B_BIFUtils.get_Amount(plan, plan, plan);
					totalAmount += Double.parseDouble(plan.get("TOTAL_AMOUNT").toString());
					if(plan.get("APPLY_GST").equals("1")){
						if(currency.equals("JPY")){
							totalGst += Math.round(Double.parseDouble(plan.get("TOTAL_AMOUNT").toString()));
						}else {
							totalGst += Double.parseDouble(plan.get("TOTAL_AMOUNT").toString());
						}
						
					}
				}
			}	
			List<Map<String, Object>> planOs = queryDAO.executeForMapList(SELECT_BIF_PLAN_O, idRef);
			for(int i=0;i<planOs.size();i++){
				Map<String, Object> plan = (Map<String, Object>) planOs.get(i);
				plan.put("item", i+1+plans.size()+planOs.size());
				if (plan.get("IS_DISPLAY_DESC").equals("0") && plan.get("TOTAL_AMOUNT") != null) {
					B_BIFUtils.get_Amount(plan, plan, plan);
					totalAmount += Integer.parseInt(plan.get("TOTAL_AMOUNT").toString());
					if(plan.get("APPLY_GST").equals("1")){
						if(currency.equals("JPY")){
							totalGst += Math.round(Double.parseDouble(plan.get("TOTAL_AMOUNT").toString()));
						}else {
							totalGst += Double.parseDouble(plan.get("TOTAL_AMOUNT").toString());
						}
					}
				}
			}
			Double gst = Double.parseDouble(queryDAO.executeForObject(SELECT_BIF_GST, null, Object.class).toString());
			totalGst *= (gst / 100);
			
			if(currency.equals("JPY")){
				totalGst = Math.round(totalGst);
			}
			
			Map totalPlan = new HashMap();
			totalPlan.put("gst", gst);
			totalPlan.put("currency", currency);
			totalPlan.put("exportCurrency", exportCurrency);
			G_CUR_P01 gCurP01 = new G_CUR_P01(this.queryDAO);
			Map<String, Object> convertCur;
			if(exportCurrency != ""){
				convertCur = gCurP01.convertCurrency(currency, totalGst + totalAmount, exportCurrency, fixedForex);
				totalPlan.put("rateCurrency", convertCur.get(G_CUR_P01.CUR_RATE));
			}
			if(fixedForex != "") {
				convertCur = gCurP01.convertCurrency(currency, totalGst + totalAmount, exportCurrency, fixedForex);
				fixedForex = CommonUtils.toString(convertCur.get(G_CUR_P01.CUR_RATE));
			}
			convertCur = gCurP01.convertCurrency(currency, totalGst + totalAmount, exportCurrency, fixedForex);
			totalPlan.put("exportAmount", convertCur.get(G_CUR_P01.EXPORT_AMOUNT));
			
			totalPlan.put("fixedForex", fixedForex);
			totalPlan.put("totalAmount", totalAmount);
			totalPlan.put("totalGst", totalGst);
			totalPlan.put("grandTotal", totalGst + totalAmount);
			totalPlan.put("sysDate", new Date());
			
			WF_DOC wf_doc = new WF_DOC();
			wf_doc.setDoc_type("DSGC");
			wf_doc.setId_ref(idRef);
			wf_doc.setSection_group("BIF");
			List<FileInfo> attachmentsSCBif = queryDAO.executeForObjectList(SQL_SELECT_T_WF_DOC, wf_doc);
			attachmentsSCBif = attachmentsSCBif != null ? attachmentsSCBif : new ArrayList<FileInfo>();
			
			wf_doc.setDoc_type("DQCP");
			List<FileInfo> attachmentsQPBif = queryDAO.executeForObjectList(SQL_SELECT_T_WF_DOC, wf_doc);
			attachmentsQPBif = attachmentsQPBif != null ? attachmentsQPBif : new ArrayList<FileInfo>();
			
			wf_doc.setDoc_type("DOTH");
			List<FileInfo> attachmentsOTBif = queryDAO.executeForObjectList(SQL_SELECT_T_WF_DOC, wf_doc);
			attachmentsOTBif = attachmentsOTBif != null ? attachmentsOTBif : new ArrayList<FileInfo>();
			
			out.setAttachmentsSCBif(attachmentsSCBif);
			out.setAttachmentsQPBif(attachmentsQPBif);
			out.setAttachmentsOTBif(attachmentsOTBif);
			
			out.setBifInfo(bifInfo);
			out.setUserInfo(input.getUvo());
			out.setTotalPlan(totalPlan);
			out.setPlans(plans);
			out.setPlanSs(planSs);
			out.setPlanOs(planOs);
			out.setCusInfo(cusInfo);
			out.setCusAdr(cusAdr);
			//return object
			result.setResultObject(out);
			
			//return message
			//result.setResultString("success");
			result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS);		
			return result;  
		}
		catch(Exception ex){
			ex.printStackTrace();
			errors.add(Globals.MESSAGE_KEY,new BLogicMessage(SAVE_ERROR_MSG));
			result.setErrors(errors);
			result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
			return result;
		}
	}
	
}
