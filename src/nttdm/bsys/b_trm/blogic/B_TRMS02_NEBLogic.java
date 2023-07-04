package nttdm.bsys.b_trm.blogic;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.b_trm.bean.DebitInfoBean;
import nttdm.bsys.common.bean.AuditTrailHeaderBean;
import nttdm.bsys.common.bean.G_CSB_P01_CheckInput;
import nttdm.bsys.common.bean.G_CSB_P01_CheckOutput;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.G_CSB_P01_Check;
import nttdm.bsys.common.util.MessageUtil;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;

public class B_TRMS02_NEBLogic extends TRMBLogic<Map<String, Object>> {
	private static final String INSERT_T_TRM_D = "INSERT.B_TRM.SQL001";
	private static final String UPDATE_T_TRM_D = "UPDATE.B_TRM.SQL006";
	private static final String UPDATE_PAID_AMOUNT = "UPDATE.B_TRM.SQL008";
	private static final String UPDATE_LAST_PAYMENT_DATE = "UPDATE.B_TRM.SQL009";
	private static final String UPDATE_IS_SETTLED = "UPDATE.B_TRM.SQL010";
	private static final String UPDATE_TOTAL_PAYMENT = "UPDATE.B_TRM.SQL002";
	private static final String DELETE_T_TRM_D = "UPDATE.B_TRM.SQL004";
	
	private BLogicMessages messages;
	private BLogicMessages errors;
	
	private static final String APPLIED_TO = "1";

	public BLogicResult execute(Map<String, Object> input) {
		BLogicResult result = new BLogicResult();
		messages = new BLogicMessages();
		errors = new BLogicMessages();
		Map<String, Object> output = new HashMap<String, Object>();
		Map<String, Object> parameter = new HashMap<String, Object>();
		BillingSystemUserValueObject uvo = (BillingSystemUserValueObject) input.get("uvo");
		parameter.put("idLogin", uvo.getId_user());
		this.copyProperties(input, output);
		this.copyProperties(input, parameter);
		BigDecimal totalPaid = BigDecimal.ZERO;
		Map<String,BigDecimal> m = new HashMap<String, BigDecimal>();
		m.put("totalPaid", totalPaid);
		if(this.validate(input,m)) {
			try {
				List<DebitInfoBean> debitInfos = (List<DebitInfoBean>) input.get("debitInfos");
				int count = 0;
				if(debitInfos != null)
					for(DebitInfoBean debitInfo : debitInfos) {
						if(debitInfo.getChkAppliedTo().equals(APPLIED_TO)) {
							count++;
							break;
						}
					}
				if(count == 0) {
					//throw new Exception("Record not allow empty.");
					String msg = MessageUtil.get("errors.ERR1SC033", new Object[]{"Debit Information","Billing Document"});
					errors.add(Globals.MESSAGE_KEY, new BLogicMessage(msg,false));
					result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
					output.put("billAccount", input.get("billAccount"));
					result.setResultObject(output);
					result.setErrors(errors);
					result.setMessages(messages);
					return result;
				} else {

				    BigDecimal payment = BigDecimal.ZERO;
					BigDecimal paymentPrev = BigDecimal.ZERO;
					boolean appliedTo = false;
					parameter.put("creditRef", input.get("creditRef"));
					output.put("creditRef", input.get("creditRef"));
					/**
					 * Audit Trail
					 */
					boolean editedMode = input.get("action") != null && "edit".equals(input.get("action").toString());
					String dbRefs = "";
					for(DebitInfoBean debitInfo : debitInfos) {
						appliedTo = debitInfo.getChkAppliedTo().equals(APPLIED_TO) ? true : false;
						if(!editedMode) {//new mode
							if(appliedTo) {
								dbRefs += ("," + debitInfo.getGdcDebitReference());
							}
						}
					}
					Integer idAudit = CommonUtils.auditTrailBegin(uvo.getId_user(), 
							BillingSystemConstants.MODULE_B, BillingSystemConstants.SUB_MODULE_B_TRM, 
							CommonUtils.toString(input.get("creditRef")).trim()+":"+ (dbRefs.equals("")? "" : dbRefs.substring(1).trim()), null, 
							editedMode ? BillingSystemConstants.AUDIT_TRAIL_EDITED : BillingSystemConstants.AUDIT_TRAIL_CREATED);
					parameter.put("idAudit", idAudit);

                    for (DebitInfoBean debitInfo : debitInfos) {
                        payment = new BigDecimal(debitInfo.getTbxPayment() == null ? "0" : debitInfo.getTbxPayment());
                        paymentPrev = BigDecimal.ZERO;
                        appliedTo = debitInfo.getChkAppliedTo().equals(APPLIED_TO) ? true : false;

                        parameter.put("payment", payment);
                        parameter.put("amtDue", debitInfo.getGdcAmountDue());
                        parameter.put("debitRef", debitInfo.getGdcDebitReference());
                        parameter.put("matchID", debitInfo.getMatchID());

                        // edit mode
                        if (editedMode) {
                            // if checkbox is checked
                            if (appliedTo) {
                                Map<String, Object> matchinfo = queryDAO.executeForMap("TRM.getTrmMatchInfo", parameter);
                                if (matchinfo == null || matchinfo.isEmpty()) {
                                    // insert TRM_D
                                    Integer matchID = queryDAO.executeForObject("SELECT.B_TRM.SQL014", null, Integer.class);
                                    parameter.put("matchID", matchID);
                                    updateDAO.execute(INSERT_T_TRM_D, parameter);

                                } else {
                                    paymentPrev = new BigDecimal(matchinfo.get("AMT_PAID").toString());
                                    parameter.put("matchID", matchinfo.get("MATCH_ID"));
                                    // update TRM_D
                                    updateDAO.execute(UPDATE_T_TRM_D, parameter);
                                }

                                // update BIL
                                parameter.put("newPaidAmt", payment.subtract(paymentPrev));

                                // update T_BIL_H.PAID_AMOUNT
                                updateDAO.execute(UPDATE_PAID_AMOUNT, parameter);

                                // update T_BIL_H.LAST_PAYMENT_DATE
                                parameter.put("lastPaymentDate", input.get("date"));
                                updateDAO.execute(UPDATE_LAST_PAYMENT_DATE, parameter);

                                // update T_BIL_H.IS_SETTLED
                                updateDAO.execute(UPDATE_IS_SETTLED, parameter);

                                dbRefs += ("," + debitInfo.getGdcDebitReference());
                            } else {

                                // check box is uncheck delete
                                Map<String, Object> matchinfo = queryDAO.executeForMap("TRM.getTrmMatchInfo", parameter);

                                if (matchinfo != null) {
                                    paymentPrev = new BigDecimal(matchinfo.get("AMT_PAID").toString());

                                    parameter.put("matchID", matchinfo.get("MATCH_ID"));

                                    // delete TRM
                                    updateDAO.execute(DELETE_T_TRM_D, parameter);

                                    // update bill
                                    parameter.put("newPaidAmt", paymentPrev.negate());

                                    // update T_BIL_H.PAID_AMOUNT
                                    updateDAO.execute(UPDATE_PAID_AMOUNT, parameter);

                                    // update T_BIL_H.LAST_PAYMENT_DATE
                                    parameter.put("lastPaymentDate", input.get("date"));
                                    updateDAO.execute(UPDATE_LAST_PAYMENT_DATE, parameter);

                                    // update T_BIL_H.IS_SETTLED
                                    updateDAO.execute(UPDATE_IS_SETTLED, parameter);

                                    dbRefs += ("," + debitInfo.getGdcDebitReference());
                                }
                            }

                            // new mode
                        } else {
                            if (appliedTo) {

                                // insert TRM_D
                                Integer matchID = queryDAO.executeForObject("SELECT.B_TRM.SQL014", null, Integer.class);
                                parameter.put("matchID", matchID);
                                updateDAO.execute(INSERT_T_TRM_D, parameter);

                                parameter.put("newPaidAmt", payment.subtract(paymentPrev));

                                // update T_BIL_H.PAID_AMOUNT
                                updateDAO.execute(UPDATE_PAID_AMOUNT, parameter);

                                // update T_BIL_H.LAST_PAYMENT_DATE
                                parameter.put("lastPaymentDate", input.get("date"));
                                updateDAO.execute(UPDATE_LAST_PAYMENT_DATE, parameter);

                                // update T_BIL_H.IS_SETTLED
                                updateDAO.execute(UPDATE_IS_SETTLED, parameter);
                            }
                        }
                    }
					/**
					 * update total payment
					 */
					parameter.put("totalPayment",m.get("totalPaid"));
					parameter.put("debitRef", parameter.get("creditRef"));
					parameter.put("lastPaymentDate", input.get("date"));
					updateDAO.execute(UPDATE_TOTAL_PAYMENT, parameter);
					updateDAO.execute(UPDATE_LAST_PAYMENT_DATE, parameter);
					updateDAO.execute(UPDATE_IS_SETTLED, parameter);
					//update Audit Trail reference for edit mode
					if(editedMode) {
						AuditTrailHeaderBean auditHeader = new AuditTrailHeaderBean();
						auditHeader.setIdAudit(idAudit);
						auditHeader.setReference(CommonUtils.toString(input.get("creditRef")).trim()+":"+ (dbRefs.equals("") ? "" : dbRefs.substring(1).trim()));
						CommonUtils.auditTrailUpdate(auditHeader);
					}
					CommonUtils.auditTrailEnd(idAudit);//End Audit Trail
				} //else of if(count == 0)
				output.put("action", "view");
				messages.add(Globals.MESSAGE_KEY, new BLogicMessage("info.ERR2SC003"));
				result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS);
			} catch(Exception e) {
			    e.printStackTrace();
				errors.add(Globals.MESSAGE_KEY, new BLogicMessage("info.ERR2SC004"));
				result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
			}
		} else { //validate(input)
			result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
		}
		
		output.put("billAccount", input.get("billAccount"));
		result.setResultObject(output);
		result.setErrors(errors);
		result.setMessages(messages);
		return result;
	}
	
	private boolean validate(Map<String, Object> input,Map<String,BigDecimal> m) {
	    G_CSB_P01_Check gCsbP01Check = new G_CSB_P01_Check(queryDAO);
        G_CSB_P01_CheckInput gCsbP01CheckInput = new G_CSB_P01_CheckInput();
        gCsbP01CheckInput.setMessageParam1("save");
        gCsbP01CheckInput.setMessageParam2("Transaction Matching");
        gCsbP01CheckInput.setMessageParam3("Batch Auto Offset Cash Book");
        G_CSB_P01_CheckOutput gCsbP01CheckOutput = gCsbP01Check.execute(gCsbP01CheckInput);
        boolean resultFlg = gCsbP01CheckOutput.isResultFlg();
        
        if (!resultFlg) {
            String errorMsg = gCsbP01CheckOutput.getMessageContext();
            errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage(errorMsg, false));
        } else {
            //check mandatory in new mode
            Object cusName = input.get("customer");
            Object billAccount = input.get("billAccount");
            Object creditRef = input.get("creditRef");
            Object transactionDate = input.get("transationDate");
            if(CommonUtils.isEmpty(cusName))
                errors.add(Globals.MESSAGE_KEY, new BLogicMessage("errors.ERR1SC005", new Object[] {"Customer Name"}));
            // check G.SET_VALUE
            String setValue = queryDAO.executeForObject("SELECT.B_TRM.IS_DISPLAY_BIL_ACC", null, String.class);
            if(!setValue.equals("CST")){
                if(CommonUtils.isEmpty(billAccount))
                    errors.add(Globals.MESSAGE_KEY, new BLogicMessage("errors.ERR1SC005", new Object[] {"Billing Account No"}));
            }   
            if(CommonUtils.isEmpty(transactionDate))
                    errors.add(Globals.MESSAGE_KEY, new BLogicMessage("errors.ERR1SC005", new Object[] {"Transaction Date"}));
            if(CommonUtils.isEmpty(creditRef))
                errors.add(Globals.MESSAGE_KEY, new BLogicMessage("errors.ERR1SC005", new Object[] {"Credit Note No."}));

            //check decimal value
            List<DebitInfoBean> debitInfos = (List<DebitInfoBean>) input.get("debitInfos");
            if(this.errors.isEmpty()) {
                if(debitInfos != null) {
                    for(DebitInfoBean debitInfo : debitInfos) {
                        if(debitInfo.getChkAppliedTo().equals(APPLIED_TO))
                            if(!isDecimal(debitInfo.getTbxPayment())) {
                                this.errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC012", new Object[] {"Amount of Offset"}));
                                break;
                            }
                    }
                }
            }
            //check invalid amount
            if(this.errors.isEmpty()) {
                BigDecimal totalAmt = new BigDecimal("0");
                BigDecimal origAmt = new BigDecimal(CommonUtils.toString(input.get("origAmt")));
                BigDecimal payment = new BigDecimal("0");
                if(debitInfos != null) {
                    for(DebitInfoBean debitInfo : debitInfos) {
                        if(debitInfo.getChkAppliedTo().equals(APPLIED_TO)) {
                            payment = new BigDecimal(debitInfo.getTbxPayment());
                            totalAmt = totalAmt.add(payment);
                            if(payment.compareTo(new BigDecimal("0")) == 0) {
                                this.errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC036", new Object[] {"Amount of Offset"}));
                                break;
                            }
                        }
                    }
                }
                if(totalAmt.compareTo(origAmt) > 0) {
                    this.errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC036", new Object[] {"Amount of Offset"}));
                }
            }
            
            //check Payment & Amt Due
            if(this.errors.isEmpty()) {
                for(DebitInfoBean debitInfo : debitInfos) {
                    if(debitInfo.getChkAppliedTo().equals(APPLIED_TO)) {
                        if(new BigDecimal(debitInfo.getTbxPayment()).compareTo(new BigDecimal(debitInfo.getGdcAmountDue())) > 0) {
                            this.errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC041", new Object[] {"Amount of Offset", "Amt. Due"}));
                            break;
                        }
                    }
                }
            }
            
            //check Payment & Amt Due  refer to DB
            if (this.errors.isEmpty()) {
                String model = CommonUtils.toString(input.get("action"));

                // get credit invoice info
                Map<String, Object> creditInfo = queryDAO.executeForMap("TRM.getTrmBillInfo", creditRef.toString());

                // get all current matchings of credit note
                List<Map<String, Object>> trms = queryDAO.executeForMapList("TRM.getMatchsOfCreditNo", creditRef.toString());

                if ("edit".equals(model) && CommonUtils.isEmpty(trms)) {
                    // if edit model and trms is null
                    this.errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC105", "Transaction matching for credit note: "
                            + input.get("creditRef") + " already deleted"));
                } else if (!"edit".equals(model) && !CommonUtils.isEmpty(trms)) {
                    // if new model and trms not null
                    this.errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC105", "Transaction Matching for Credit Note "
                            + creditRef.toString() + " already exist. " + System.getProperty("line.separator")
                            + "Kindly proceed to transaction matching editing for amendment."));
                } else {

                    BigDecimal billAmount = new BigDecimal(creditInfo.get("BILL_AMOUNT").toString());

                    BigDecimal totalPaid = BigDecimal.ZERO;
                    Map<String, Object> param = new HashMap<String, Object>();
                    param.put("creditRef", creditRef.toString());

                    for (DebitInfoBean debitInfo : debitInfos) {

                        // current debit checkbox is ticked
                        if (debitInfo.getChkAppliedTo().equals(APPLIED_TO)) {

                            // amt paid in db
                            BigDecimal AMT_PAID = BigDecimal.ZERO;
                            param.put("debitRef", debitInfo.getGdcDebitReference());
                            Map<String, Object> matchinfo = queryDAO.executeForMap("TRM.getTrmMatchInfo", param);
                            if (matchinfo != null) {
                                AMT_PAID = new BigDecimal(matchinfo.get("AMT_PAID").toString());
                            }

                            // get debit info in db
                            Map<String, Object> billInfo = queryDAO.executeForMap("TRM.getTrmBillInfo", debitInfo.getGdcDebitReference());
                            BigDecimal OUTSTANDING = billInfo == null ? BigDecimal.ZERO : new BigDecimal(billInfo.get("OUTSTANDING").toString());

                            if (new BigDecimal(debitInfo.getTbxPayment()).compareTo(OUTSTANDING.add(AMT_PAID)) > 0) {
                                this.errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC102", debitInfo.getGdcDebitReference()));
                                break;
                            }

                            totalPaid = totalPaid.add(new BigDecimal(debitInfo.getTbxPayment()));
                        }
                    }
                    if (this.errors.isEmpty()) {
                        m.put("totalPaid", totalPaid);
                        if (totalPaid.compareTo(billAmount) > 0) {
                            this.errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC036", new Object[] { "Amount of Offset" }));
                        }
                    }
                }
            }
        }
		
		if(this.errors.isEmpty())
			return true;
		else
			return false;
	}
	
	private static boolean isDecimal(Object obj) {
		if(obj != null) {
			if(obj instanceof String) {
				String regexNumber15_2 = "\\d{1,15}([\\.]\\d{0,2})?$";
				return ((String) obj).matches(regexNumber15_2);
			}
		}
		return false;
	}
}
