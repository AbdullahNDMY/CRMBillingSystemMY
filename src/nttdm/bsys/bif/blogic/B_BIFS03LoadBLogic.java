/*
 * @(#)B_BIFS03LoadBLogic.java
 *
 *
 */
package nttdm.bsys.bif.blogic;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.bif.bean.CustomerPlanBean;
import nttdm.bsys.bif.bean.CustomerPlanServiceBean;
import nttdm.bsys.bif.bean.CustomerPlanSubPlanBean;
import nttdm.bsys.bif.dto.B_BIFS03LoadInput;
import nttdm.bsys.bif.dto.B_BIFS03LoadOutput;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.G_CUR_P01;

import org.apache.struts.Globals;

/**
 * ビジネスロジッククラス。
 * 
 * @author duongnld
 */
public class B_BIFS03LoadBLogic extends AbstractB_BIFS03LoadBLogic {

	private static final String SELECT_BIF_CUS_NAME = "SELECT.B_BIF.S02_01.SQL001";
	private static final String SELECT_BIF_CUS_ADR = "SELECT.B_BIF.S02_01.SQL009";
	private static final String SELECT_BIF_QCS_REFER = "SELECT.B_BIF.S02_01.SQL002";
	private static final String SELECT_BIF_QUO_REFER = "SELECT.B_BIF.S02_01.SQL003";
	private static final String SELECT_BIF_COLS_NAME = "SELECT.B_BIF.S02_01.SQL004";
	private static final String SELECT_BIF_JOB_NO = "SELECT.B_BIF.S02_01.SQL010";
	private static final String SELECT_BIF_ATTN = "SELECT.B_BIF.S02_01.SQL011";
	private static final String SELECT_BIF_PLAN = "SELECT.B_BIF.S02_01.SQL012_01";
	private static final String SELECT_BIF_PLAN_S = "SELECT.B_BIF.S02_01.SQL013";
	private static final String SELECT_BIF_CURRENCY = "SELECT.B_BIF.S02_01.SQL005";	
	//private static final String SELECT_BIF_SERV_TYPE = "SELECT.B_BIF.S02_01.SQL006";
	//private static final String SELECT_BIF_SERV_ITEM = "SELECT.B_BIF.S02_01.SQL007";
	private static final String SELECT_BIF_SUPPLIER = "SELECT.B_BIF.S02_01.SQL008";
	private static final String SELECT_BIF_GST = "SELECT.B_BIF.S02_01.SQL015";
	private static final String SELECT_TEL_FAX_CONTACT = "SELECT.B_BIF.SQL_TEL_FAX_CONTACT";
    private static final String SELECT_BILL_ACC = "SELECT.B_BIF.SQL_BILL_ACC";
	private static final String SELECT_BILL_ACC_TAX_TYPE = "SELECT.B_BIF.SQL_BILL_ACC_TAX_TYPE";
	
	static final String SAVE_ERROR_MSG = "info.ERR2SC004";
	
	public BLogicResult execute(B_BIFS03LoadInput input) {
		BLogicMessages errors = new BLogicMessages();
		
		BLogicResult result = new BLogicResult();		
		try{ 
			B_BIFS03LoadOutput out = new B_BIFS03LoadOutput();
			
			String idCust = input.getIdCust();
			
			String idCustPlan = input.getIdCustPlan();
			
			//G_CDM_P01 cmd_p01 = new G_CDM_P01(queryDAO,getUpdateDAO(),input.getUvo().getId_user());

			String idRef = "";
			//idRef = cmd_p01.getGenerateCode("BIFNO", input.getUvo().getId_user());
			out.setIdRef(idRef);
			out.setIdCust(input.getIdCust());
			out.setIdCustPlan(input.getIdCustPlan());
			out.setBifType(input.getBifType());
			
			Map<String, Object> cusInfo = queryDAO.executeForMap(SELECT_BIF_CUS_NAME, idCust);
			List<Map<String, Object>> cusAdr = queryDAO.executeForMapList(SELECT_BIF_CUS_ADR, idCust);
			List<Map<String, Object>> qscRefers = queryDAO.executeForMapList(SELECT_BIF_QCS_REFER, idCust);
			List<Map<String, Object>> quoRefers = queryDAO.executeForMapList(SELECT_BIF_QUO_REFER, idCust);
			List<Map<String, Object>> colsNames = queryDAO.executeForMapList(SELECT_BIF_COLS_NAME, null);
			List<Map<String, Object>> jobNos = queryDAO.executeForMapList(SELECT_BIF_JOB_NO, idCust);
			List<Map<String, Object>> attns = queryDAO.executeForMapList(SELECT_BIF_ATTN, idCust);
			List<Map<String, Object>> currencies = queryDAO.executeForMapList(SELECT_BIF_CURRENCY, null);
			List<Map<String, Object>> suppliers = queryDAO.executeForMapList(SELECT_BIF_SUPPLIER, null);
						
			List<CustomerPlanServiceBean> plans = queryDAO.executeForObjectList(SELECT_BIF_PLAN, idCustPlan);
			
			BigDecimal totalAmount = new BigDecimal("0");
            BigDecimal totalGst = new BigDecimal("0");
            BigDecimal subtotalTaxable = new BigDecimal("0");
			BigDecimal subtotalNonTaxable = new BigDecimal("0");
			String currency = "";
			String exportCurrency = "";
			String fixedForex = "";
			int itemNo = 0;
            if (plans!=null && 0<plans.size()) {
                CustomerPlanServiceBean firstService = plans.get(0);
                //currency = firstService.getBillCurrency();
                //exportCurrency = CommonUtils.toString(firstService.getExportCurrency()).trim();
                fixedForex = CommonUtils.toString(firstService.getFixedForex()).trim();
                for(CustomerPlanServiceBean service : plans){
                    String idCustPlanGrp = service.getIdCustPlanGrp();
                    String serivceIsLumpSum = service.getIsLumpSum();
                    if ("1".equals(serivceIsLumpSum)) {
                        itemNo = itemNo + 1;
                        service.setItemNo(CommonUtils.toString(itemNo));
                    }
                    List<CustomerPlanSubPlanBean> subPlanList = queryDAO.executeForObjectList(SELECT_BIF_PLAN_S, idCustPlanGrp);
                    BigDecimal serviceAmt = new BigDecimal("0");
                    BigDecimal serviceDiscAmt=new BigDecimal("0");
                    BigDecimal taxAmount = new BigDecimal("0");
                    BigDecimal taxAmountLamsup = new BigDecimal("0");
                    if (subPlanList!=null && 0<subPlanList.size()) {
                    	service.setItemGst(subPlanList.get(0).getItemGst());
                        for(CustomerPlanSubPlanBean subPlan : subPlanList){
                            String subPlanIsLumpSum = subPlan.getIsLumpSum();
                            if ("0".equals(subPlanIsLumpSum)) {
                                itemNo = itemNo + 1;
                                subPlan.setItemNo(CommonUtils.toString(itemNo));
                            }
                            subPlan.setDiscAmount(new BigDecimal(subPlan.getDiscAmount()==null?"0":subPlan.getDiscAmount()).abs().toString());
                            serviceAmt = serviceAmt.add(new BigDecimal(subPlan.getItemAmt()));
                            serviceDiscAmt=serviceDiscAmt.add(new BigDecimal(subPlan.getDiscAmount()));
                            totalAmount = totalAmount.add(new BigDecimal(subPlan.getItemAmt()).subtract(new BigDecimal(subPlan.getDiscAmount())));
//                            //is gst
//                            if("1".equals(subPlan.getApplyGst())) {
//                                totalGst = totalGst.add(new BigDecimal(subPlan.getItemAmt()));
//                            }
                            subPlan.setTaxRate(subPlan.getTaxRate()==null?"0":subPlan.getTaxRate());
                            BigDecimal taxRate=new BigDecimal(subPlan.getTaxRate()).divide(new BigDecimal(100));
                            BigDecimal subToatalAfterAmount=new BigDecimal(subPlan.getItemAmt()).subtract(new BigDecimal(subPlan.getDiscAmount()));

                            //wcbeh@20161207 #212 - JPY GST Calculation Rounding Issue
                            BigDecimal subTotalAfterGST = new BigDecimal(0);
                            subTotalAfterGST = subToatalAfterAmount.multiply(taxRate);
                            subPlan.setTaxAmount(subTotalAfterGST);
                            taxAmountLamsup = taxAmountLamsup.add(subTotalAfterGST);
                            if (service.getBillCurrency().equals("JPY"))
                            	subTotalAfterGST = subTotalAfterGST.setScale(0, RoundingMode.HALF_UP);
                            else
                            	subTotalAfterGST = subTotalAfterGST.setScale(2, RoundingMode.HALF_UP);
                            
                            totalGst = totalGst.add(subTotalAfterGST);
                            
                            if(taxRate.compareTo(BigDecimal.ZERO) == 0) {
                            	subtotalNonTaxable = subtotalNonTaxable.add(new BigDecimal(subPlan.getItemAmt()).subtract(new BigDecimal(subPlan.getDiscAmount())));
                            }else {
                            	subtotalTaxable = subtotalTaxable.add(new BigDecimal(subPlan.getItemAmt()).subtract(new BigDecimal(subPlan.getDiscAmount())));
                            }
                        }
                        service.setTaxAmount(taxAmountLamsup);
                    }
                    service.setSubPlanList(subPlanList);
                    service.setItemQty("1");
                    service.setItemUprice(serviceAmt.toString());
                    service.setItemAmt(serviceAmt.toString());
                    service.setItemDiscAmt(serviceDiscAmt.toString());
                }
            }
			
//            String gst = queryDAO.executeForObject(SELECT_BIF_GST, null, String.class);
//            totalGst = totalGst.multiply(new BigDecimal(Double.parseDouble(gst) / 100));
            
			List<Map<String, Object>> taxType = queryDAO.executeForMapList(SELECT_BILL_ACC_TAX_TYPE, idCustPlan);
	        String tax_type = CommonUtils.toString(taxType.get(0).get("TAX_TYPE"));
			out.setTaxType(tax_type);
			
			String taxWord = CommonUtils.toString(queryDAO.executeForObject("SELECT.B_BIF.CPM_TAX_WORD", null, String.class)).trim();
			out.setTaxStr(taxWord);
            
            CustomerPlanBean customerPlan = new CustomerPlanBean();
            customerPlan.setServiceList(plans);
            out.setCustomerPlan(customerPlan);
			
            List<Map<String, Object>> billAccInfoList = queryDAO.executeForMapList(SELECT_BILL_ACC, idCustPlan);
            currency=CommonUtils.toString(billAccInfoList.get(0).get("BILL_CURRENCY"));
			exportCurrency=CommonUtils.toString(billAccInfoList.get(0).get("EXPORT_CURRENCY"));
            
			Map<String, Object> totalPlan = new HashMap<String, Object>();
//			totalPlan.put("gst", gst);
			String taxRate = CommonUtils.toString(queryDAO.executeForObject("SELECT.B_BIF.TAX_RATE", null, String.class)).trim();
			totalPlan.put("taxRate",taxRate);
			totalPlan.put("currency", currency);
			totalPlan.put("exportCurrency", exportCurrency);
			G_CUR_P01 gCurP01 = new G_CUR_P01(this.queryDAO);
			Map<String, Object> convertCur;
			if(!"".equals(exportCurrency)){
				convertCur = gCurP01.convertCurrency(currency, (totalGst.add(totalAmount)).doubleValue(), exportCurrency, fixedForex);
				totalPlan.put("rateCurrency", convertCur.get(G_CUR_P01.CUR_RATE));
			}
			if(!"".equals(fixedForex)) {
				convertCur = gCurP01.convertCurrency(currency, (totalGst.add(totalAmount)).doubleValue(), exportCurrency, fixedForex);
				fixedForex = CommonUtils.toString(convertCur.get(G_CUR_P01.CUR_RATE));
			}
			convertCur = gCurP01.convertCurrency(currency, (totalGst.add(totalAmount)).doubleValue(), exportCurrency, fixedForex);
			totalPlan.put("exportAmount", convertCur.get(G_CUR_P01.EXPORT_AMOUNT));
			
			String defCurrency = CommonUtils.toString(queryDAO.executeForObject("BIF.selectDEF_CURRENCY", null, String.class));
		    String currencyStd = CommonUtils.toString(queryDAO.executeForObject("BIF.selectCURRENCY_STD", null, String.class));
		    totalPlan.put("currencyStd", currencyStd);
		    totalPlan.put("defCurrency", defCurrency);
			
			totalPlan.put("fixedForex", fixedForex);
			totalPlan.put("totalAmount", totalAmount);
			totalPlan.put("totalGst", totalGst);
			totalPlan.put("grandTotal", (totalGst.add(totalAmount)).doubleValue());
			totalPlan.put("sysDate", new Date());
			totalPlan.put("subtotalTaxable", subtotalTaxable);
			totalPlan.put("subtotalNonTaxable", subtotalNonTaxable);
			
			Map<String, Object> bifObject = new HashMap<String, Object>();
            String telFaxContactFlg = CommonUtils.toString(queryDAO.executeForObject(SELECT_TEL_FAX_CONTACT, null, String.class));
            String customerType = CommonUtils.toString(cusInfo.get("CUSTOMER_TYPE"));
            if ("CORP".equals(customerType)) {
                if("0".equals(telFaxContactFlg)) {
                    if (attns!=null && 0<attns.size()) {
                        for (Map<String, Object> map: attns) {
                            map.put("TEL_NO", cusInfo.get("CO_TEL_NO"));
                            map.put("FAX_NO", cusInfo.get("CO_FAX_NO"));
                        }
                    }
                }
            }
            bifObject.put("telFaxContactFlg", telFaxContactFlg);
            
           
			if (billAccInfoList!=null && 0<billAccInfoList.size()) {
				long bacDCount = CommonUtils.toLong(billAccInfoList.get(0).get("BAC_D_COUNT"));
				if (0==bacDCount) {
					bifObject.put("ADR_TYPE", billAccInfoList.get(0).get("CUST_ADR_TYPE"));
					bifObject.put("CONTACT_TYPE", billAccInfoList.get(0).get("CONTACT_TYPE"));
					bifObject.put("IS_BAC_EXIST", "0");
				} else {
					bifObject.put("ADR_TYPE", billAccInfoList.get(0).get("CUST_ADR_TYPE"));
					bifObject.put("CONTACT_TYPE", billAccInfoList.get(0).get("CONTACT_TYPE"));
					bifObject.put("IS_BAC_EXIST", "1");
				}
				bifObject.put("DELIVERY", billAccInfoList.get(0).get("DELIVERY"));
				bifObject.put("DELIVERY_EMAIL", billAccInfoList.get(0).get("DELIVERY_EMAIL"));
			} else {
				bifObject.put("ADR_TYPE", "BA");
				bifObject.put("CONTACT_TYPE", "BC");
				bifObject.put("IS_BAC_EXIST", "0");
			}
            
            out.setBifObject(bifObject);
			out.setUserInfo(input.getUvo());
			out.setTotalPlan(totalPlan);
			out.setCusInfo(cusInfo);
			out.setCusAdr(cusAdr);
			out.setQscRefers(qscRefers);
			out.setQuoRefers(quoRefers);
			out.setColsNames(colsNames);
			out.setJobNos(jobNos);
			out.setAttns(attns);
			out.setCurrencies(currencies);
			out.setSuppliers(suppliers);
			//return object
			result.setResultObject(out);
			
			//return message
			result.setResultString("success");
			//result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SCCESS);		
			return result;  
		} catch(Exception ex){
			ex.printStackTrace();
			errors.add(Globals.MESSAGE_KEY,new BLogicMessage(SAVE_ERROR_MSG));
			result.setErrors(errors);
			result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
			return result;
		}
	}
}