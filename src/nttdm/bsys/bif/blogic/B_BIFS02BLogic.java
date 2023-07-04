package nttdm.bsys.bif.blogic;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
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
import nttdm.bsys.bif.bean.FileInfo;
import nttdm.bsys.bif.bean.WF_DOC;
import nttdm.bsys.bif.dto.B_BIFS02Input;
import nttdm.bsys.bif.dto.B_BIFS02Output;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.G_CUR_P01;

import org.apache.struts.Globals;

public class B_BIFS02BLogic extends AbstractB_BIFS02BLogic{
	private static final String SELECT_BIF_H = "SELECT.B_BIF.S02_03.SQL001";
	//private static final String SELECT_BIF_ID_CUST_PLAN = "SELECT.B_BIF.S02_03.SQL004";
	private static final String SELECT_BIF_CUS_NAME = "SELECT.B_BIF.S02_01.SQL001";
	private static final String SELECT_BIF_CUS_ADR = "SELECT.B_BIF.S02_01.SQL009";
	private static final String SELECT_BIF_QCS_REFER = "SELECT.B_BIF.S02_01.SQL002";
	private static final String SELECT_BIF_QUO_REFER = "SELECT.B_BIF.S02_01.SQL003";
	private static final String SELECT_BIF_COLS_NAME = "SELECT.B_BIF.S02_01.SQL004";
	private static final String SELECT_BIF_JOB_NO = "SELECT.B_BIF.S02_01.SQL010";
	private static final String SELECT_BIF_ATTN = "SELECT.B_BIF.S02_01.SQL011";
	//private static final String SELECT_BIF_PLAN = "SELECT.B_BIF.S02_01.SQL012";
	//private static final String SELECT_BIF_PLAN_S = "SELECT.B_BIF.S02_01.SQL013";
	//private static final String SELECT_BIF_PLAN_O = "SELECT.B_BIF.S02_01.SQL014";
	private static final String SELECT_BIF_PLAN = "SELECT.B_BIF.S02_01.SQL017_01";
	private static final String SELECT_BIF_PLAN_S = "SELECT.B_BIF.S02_01.SQL018";
	private static final String SELECT_BIF_CURRENCY = "SELECT.B_BIF.S02_01.SQL005";	
	//private static final String SELECT_BIF_SERV_TYPE = "SELECT.B_BIF.S02_01.SQL006";
	//private static final String SELECT_BIF_SERV_ITEM = "SELECT.B_BIF.S02_01.SQL007";
	private static final String SELECT_BIF_SUPPLIER = "SELECT.B_BIF.S02_01.SQL008";
	private static final String SELECT_BIF_GST = "SELECT.B_BIF.S02_01.SQL015";
	private static final String SQL_SELECT_T_WF_DOC = "SELECT.B_BIF.S02_04.SQL001";
	private static final String SELECT_WK_INFO = "SELECT.B_BIF.S03_02.SQL001";
	private static final String SELECT_COMMENT_INFO = "SELECT.B_BIF.S03.SQL001";
	
	private static final String SELECT_BILL_ACCOUNT = "SELECT.B_BIF.S02_01.SQL020";
	
	private static final String SELECT_TEL_FAX_CONTACT = "SELECT.B_BIF.SQL_TEL_FAX_CONTACT";
    private static final String SELECT_BILL_ACC_BY_ID_REF = "SELECT.B_BIF.SQL_TAX_TYPE_BY_ID_REF";

	static final String SAVE_ERROR_MSG = "info.ERR2SC004";
	
	public BLogicResult execute(B_BIFS02Input input) {
		BLogicMessages errors = new BLogicMessages();
		BLogicResult result = new BLogicResult();		
		
		BLogicMessages messages = new BLogicMessages();
		result.setMessages(messages);
		
		try{ 
			B_BIFS02Output out = new B_BIFS02Output();
			
			//check idRef and action
			if (input.getIdRefR() != null) {
				input.setIdRef(input.getIdRefR().toString());
			}
			
			if (input.getActionR() != null) {
				input.setAction(input.getActionR().toString());
			}
			String idRef = input.getIdRef();			
			Map<String, Object> bifInfo = queryDAO.executeForMap(SELECT_BIF_H, idRef);
			Map<String, Object> deliveryEmailInfo = queryDAO.executeForMap("SELECT.B_BIF.EMAILDETAILS", idRef);
			if(deliveryEmailInfo!=null){
			    //Email(To)
			    String emailto=CommonUtils.toString(deliveryEmailInfo.get("EMAIL_TO"));
			    bifInfo.put("EMAIL_TO", emailto);
			    //Email(Cc)
			    String emailcc=CommonUtils.toString(deliveryEmailInfo.get("EMAIL_CC"));
			    bifInfo.put("EMAIL_CC", emailcc);
			}else{
			    bifInfo.put("EMAIL_TO", "");
			    bifInfo.put("EMAIL_CC", "");
			}
			
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("ID_REF", idRef);
			param.put("ID_USER", input.getUvo().getId_user());
			Map<String, Object> wfInfo = queryDAO.executeForMap(SELECT_WK_INFO, param);
			if (bifInfo.get("ID_REF") == null) {
				//record is deleted by another, return to main screen
				result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
				return result;
			}
			String idCust = (String) bifInfo.get("ID_CUST");
			//Integer idCustPlan = queryDAO.executeForObject(SELECT_BIF_ID_CUST_PLAN, idRef, Integer.class);
			
			//get billing account
			bifInfo.put("ID_BILL_ACCOUNT", this.generateBillingAccount(idRef));
			
			//get editable status
			Object req_user = bifInfo.get("REQ_USER");
			Object id_Consult = bifInfo.get("ID_CONSLT");
			Object id_user = bifInfo.get("ID_USER");
			Object login_user = input.getUvo().getId_user();
			if (input.getScreenObject() == null) {
				input.setScreenObject(new HashMap<String, Object>());
			}
			input.getScreenObject().put("isEditable", "false");
			input.getScreenObject().put("is_ObtainA", "1");
			input.getScreenObject().put("reqUserIsLoginUser", "1");
			input.getScreenObject().put("idConsltIsLoginUser", "1");
			if (id_user != null) {
				if (id_user.equals(login_user)) {
					input.getScreenObject().put("isEditable", "true");
				}
			}
			
			if(id_Consult != null){
				if (id_Consult.equals(login_user)) {
					input.getScreenObject().put("isEditable", "true");
					input.getScreenObject().put("is_ObtainA", "0");
					input.getScreenObject().put("idConsltIsLoginUser", "0");
				}
			} 
			
			if(req_user != null) {
				if (req_user.equals(login_user)) {
					input.getScreenObject().put("isEditable", "true");
					input.getScreenObject().put("is_ObtainA", "0");
					input.getScreenObject().put("reqUserIsLoginUser", "0");
				}
			}
			
			String is_ObtainV = CommonUtils.toString(bifInfo.get("OBTAIN_VERIFICATION"));
			input.getScreenObject().put("is_ObtainV", is_ObtainV);

			if(wfInfo.get("APPR_STATUS") != null && 
					bifInfo.get("ID_STATUS") != null &&
					wfInfo.get("SECTION_NO") != null) {
				if (wfInfo.get("APPR_STATUS").equals("AS2") &&
						bifInfo.get("ID_STATUS").equals("DS2") &&
						wfInfo.get("SECTION_NO").equals("B000")) {
					input.getScreenObject().put("isEditable", "true");
				}
			}
			if(wfInfo.get("APPR_STATUS") != null) {
				bifInfo.put("APPR_STATUS", wfInfo.get("APPR_STATUS"));
			}
			
			out.setIdRef(idRef);
			out.setIdCust(idCust);
			//out.setIdCustPlan(idCustPlan+"");
			out.setBifType(bifInfo.get("BIF_TYPE") + "");
			
			List<Map<String, Object>> billAccInfoList2 = queryDAO.executeForMapList(SELECT_BILL_ACC_BY_ID_REF, idRef);
			String taxType = billAccInfoList2.get(0).get("TAX_TYPE").toString();
			out.setTaxType(taxType);
			
			String taxWord = CommonUtils.toString(queryDAO.executeForObject("SELECT.B_BIF.CPM_TAX_WORD", null, String.class)).trim();
			out.setTaxStr(taxWord);
			
			Map<String, Object> cusInfo = queryDAO.executeForMap(SELECT_BIF_CUS_NAME, idCust);
			List<Map<String, Object>> cusAdr = queryDAO.executeForMapList(SELECT_BIF_CUS_ADR, idCust);
			List<Map<String, Object>> qscRefers = queryDAO.executeForMapList(SELECT_BIF_QCS_REFER, idCust);
			List<Map<String, Object>> quoRefers = queryDAO.executeForMapList(SELECT_BIF_QUO_REFER, idCust);
			List<Map<String, Object>> colsNames = queryDAO.executeForMapList(SELECT_BIF_COLS_NAME, null);
			List<Map<String, Object>> jobNos = queryDAO.executeForMapList(SELECT_BIF_JOB_NO, idCust);
			List<Map<String, Object>> attns = queryDAO.executeForMapList(SELECT_BIF_ATTN, idCust);
			List<Map<String, Object>> currencies = queryDAO.executeForMapList(SELECT_BIF_CURRENCY, null);
			List<Map<String, Object>> suppliers = queryDAO.executeForMapList(SELECT_BIF_SUPPLIER, null);
						
			List<CustomerPlanServiceBean> plans = queryDAO.executeForObjectList(SELECT_BIF_PLAN, idRef);
			
			param.put("SECTION_GROUP", "BIF-IDN");
			param.put("SECTION_NO", "B000");
			List<Map<String, Object>> commentBIF = queryDAO.executeForMapList(SELECT_COMMENT_INFO, param);
			param.put("SECTION_GROUP", "BOP-IDN");
			param.put("SECTION_NO", "B011");
			List<Map<String, Object>> commentBOP = queryDAO.executeForMapList(SELECT_COMMENT_INFO, param);
			
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
                            //is gst
                            //if("1".equals(subPlan.getApplyGst())) {
                            subPlan.setTaxRate(subPlan.getTaxRate()==null?"0":subPlan.getTaxRate());
                            BigDecimal taxRate=new BigDecimal(subPlan.getTaxRate()).divide(new BigDecimal(100));
                            BigDecimal subToatalAfterAmount=new BigDecimal(subPlan.getItemAmt()).subtract(new BigDecimal(subPlan.getDiscAmount()));
                            
                            //wcbeh@20161207 #212 - JPY GST Calculation Rounding Issue
                            BigDecimal subTotalAfterGST = new BigDecimal(0);
                            subTotalAfterGST = subToatalAfterAmount.multiply(taxRate);
                            if (service.getBillCurrency().equals("JPY"))
                            	subTotalAfterGST = subTotalAfterGST.setScale(0, RoundingMode.HALF_UP);
                            else
                            	subTotalAfterGST = subTotalAfterGST.setScale(2, RoundingMode.HALF_UP);
                            
                            totalGst = totalGst.add(subTotalAfterGST);
                            //}
                            subPlan.setTaxAmount(subTotalAfterGST);
                            taxAmountLamsup = taxAmountLamsup.add(subTotalAfterGST);
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
//		    String gst = queryDAO.executeForObject(SELECT_BIF_GST, null, String.class);
//            totalGst = totalGst.multiply(new BigDecimal(Double.parseDouble(gst) / 100));
			
//			if(currency.equals("JPY")){
//				totalGst = Math.round(totalGst);
//			}
			
            CustomerPlanBean customerPlan = new CustomerPlanBean();
            customerPlan.setServiceList(plans);
            out.setCustomerPlan(customerPlan);
            
			Map<String, Object> totalPlan = new HashMap<String, Object>();
			
			currency=CommonUtils.toString(bifInfo.get("BILL_CUR"));
			exportCurrency=CommonUtils.toString(bifInfo.get("EXPORT_CUR"));
			
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
			
			totalPlan.put("fixedForex", fixedForex);
			totalPlan.put("totalAmount", totalAmount);
			totalPlan.put("totalGst", totalGst);
			totalPlan.put("grandTotal", (totalGst.add(totalAmount)).doubleValue());
			totalPlan.put("sysDate", new Date());
			totalPlan.put("subtotalNonTaxable", subtotalNonTaxable);
			totalPlan.put("subtotalTaxable", subtotalTaxable);
			
			String defCurrency = CommonUtils.toString(queryDAO.executeForObject("BIF.selectDEF_CURRENCY", null, String.class));
		    String currencyStd = CommonUtils.toString(queryDAO.executeForObject("BIF.selectCURRENCY_STD", null, String.class));
		    totalPlan.put("currencyStd", currencyStd);
		    totalPlan.put("defCurrency", defCurrency);
		    
			param.put("idRef", input.getIdRef());
			/*List<Map<String, Object>> isDisplayExpAmts =  this.queryDAO.executeForMapList("SELECT.B_BIF.IS_DISPLAY_EXP_AMT", param);
			if(!isDisplayExpAmts.isEmpty()) {
			    String obj = CommonUtils.toString(isDisplayExpAmts.get(0).get("IS_DISPLAY_EXP_AMT"));
				if(!CommonUtils.isEmpty(obj)) {
				    out.setChkExportAmount(obj);
				} else {
				    out.setChkExportAmount("0");
				}
			} else {
				out.setChkExportAmount("0");
			}*/
			
			String obj = CommonUtils.toString(bifInfo.get("IS_DISPLAY_EXP_AMT"));
			if(!CommonUtils.isEmpty(obj)) {
			    out.setChkExportAmount(obj);
			} else {
			    out.setChkExportAmount("0");
			}
			
			//get data for uploaded file
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
            bifInfo.put("telFaxContactFlg", telFaxContactFlg);
            
            //in order to update the cust_name, cust_tel_no & cust_fax_no
			if("edit2".equals(input.getActionR())){
				bifInfo.put("CUST_NAME", cusInfo.get("CUST_NAME"));
				if ("CORP".equals(customerType)) {
					if("0".equals(telFaxContactFlg)) {
						String contype = (String) bifInfo.get("CONTACT_TYPE");
						if (attns!=null && 0<attns.size()) {
	                        for (Map<String, Object> map: attns) {
	                        	if(contype.equals(map.get("CONTACT_TYPE"))){
	                        		bifInfo.put("CONTACT_NAME", map.get("CONTACT_NAME_VALUE"));
	                        		break;
	                        	}
	                        }
	                    }
						bifInfo.put("TEL_NO", cusInfo.get("CO_TEL_NO"));
						bifInfo.put("FAX_NO", cusInfo.get("CO_FAX_NO"));
					}
					else{
						String contype = (String) bifInfo.get("CONTACT_TYPE");
						if (attns!=null && 0<attns.size()) {
	                        for (Map<String, Object> map: attns) {
	                        	if(contype.equals(map.get("CONTACT_TYPE"))){
	                        		bifInfo.put("TEL_NO", map.get("TEL_NO"));
	                        		bifInfo.put("FAX_NO", map.get("FAX_NO"));
	                        		bifInfo.put("CONTACT_NAME", map.get("CONTACT_NAME_VALUE"));
	                        		break;
	                        	}
	                        }
	                    }
					}
				}
				else{
					bifInfo.put("TEL_NO", cusInfo.get("HOME_TEL_NO"));
					bifInfo.put("FAX_NO", cusInfo.get("HOME_FAX_NO"));
				}
			}
			
            List<Map<String, Object>> billAccInfoList = queryDAO.executeForMapList(SELECT_BILL_ACC_BY_ID_REF, idRef);
            if (billAccInfoList!=null && 0<billAccInfoList.size()) {
                bifInfo.put("IS_BAC_EXIST", "1");
            } else {
                bifInfo.put("IS_BAC_EXIST", "0");
            }
			
			bifInfo.put("checkMultipleInOneInvoice", CommonUtils.toString(bifInfo.get("MULTI_BILL_PERIOD")));
            String loginId = input.getUvo().getId_user();
            String accessType = queryDAO.executeForObject("BIF.selectACCESS_TYPE", loginId, String.class);
            String uploadDisplay = queryDAO.executeForObject("BIF.uploadDisplay", loginId, String.class);
            String status = (String)bifInfo.get("ID_STATUS");
            String refurbish = "";
            if("DS3".equals(status)){
            	refurbish = "0";
            }else{
            	refurbish = "1";
            }
            
            out.setRefurbish(refurbish);
            out.setUploadDisplay(uploadDisplay);
            out.setAccessType(accessType);
			out.setBifInfo(bifInfo);
			out.setUserInfo(input.getUvo());
			out.setTotalPlan(totalPlan);
//			out.setPlans(plans);
//			out.setPlanSs(planSs);
//			out.setPlanOs(planOs);
			out.setCusInfo(cusInfo);
			out.setCusAdr(cusAdr);
			out.setQscRefers(qscRefers);
			out.setQuoRefers(quoRefers);
			out.setColsNames(colsNames);
			out.setJobNos(jobNos);
			out.setAttns(attns);
			out.setCurrencies(currencies);
			out.setSuppliers(suppliers);
			
			out.setScreenObject(input.getScreenObject());
			out.setWfInfo(wfInfo);
			out.setCommentBIF(commentBIF);
			out.setCommentBOP(commentBOP);
			out.setPagAction(input.getPagAction());
			out.setPictureId(input.getPictureId());
			//get display message
			if (input.getMessage() != null) {
				if (!input.getMessage().equals("")) {
					if (input.getMessageParameter() != null) {
						messages.add(Globals.MESSAGE_KEY, new BLogicMessage(input.getMessage(), input.getMessageParameter()));
					} else {
						messages.add(Globals.MESSAGE_KEY, new BLogicMessage(input.getMessage()));
					}
				}
			}
			
			//return object
			result.setResultObject(out);
			//return message
			//result.setResultString("success");
			result.setResultString(input.getAction());
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
	
	/**
	 * 
	 * @param idRef
	 * @return
	 */
	private List<Map<String, Object>> generateBillingAccount(String idRef) {
		/*String returnValue = "";*/
		//get billing account
		List<Map<String, Object>> result = this.queryDAO.executeForMapList(SELECT_BILL_ACCOUNT, idRef);
		/*
		for (Map<String, Object> map : result) {
			returnValue += map.get("ID_BILL_ACCOUNT").toString().trim() + ","; 
		}
		//remove last ,
		if (returnValue.length() > 0) {
			returnValue = returnValue.substring(0, returnValue.length() - 1);
		}
		*/
		//only first one is needed
		/*if(result.size()>0){
			returnValue=result.get(0).get("ID_BILL_ACCOUNT").toString().trim();
		}*/
		return result;
	}
}
