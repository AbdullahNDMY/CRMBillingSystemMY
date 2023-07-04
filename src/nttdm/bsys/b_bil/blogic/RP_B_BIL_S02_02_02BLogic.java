/*
 * @(#)RP_B_BIL_S02_02_02BLogic.java
 *
 *
 */
package nttdm.bsys.b_bil.blogic;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.b_bil.dto.RP_B_BIL_S02_02_02Input;
import nttdm.bsys.b_bil.dto.RP_B_BIL_S02_02_02Output;
import nttdm.bsys.common.bean.G_CSB_P01_CheckInput;
import nttdm.bsys.common.bean.G_CSB_P01_CheckOutput;
import nttdm.bsys.common.bean.T_BIL_H;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.G_CSB_P01_Check;
import nttdm.bsys.common.util.G_CUR_P01;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionMessages;

/**
 * BusinessLogic class.
 * 
 * @author khungl0ng
 */
public class RP_B_BIL_S02_02_02BLogic extends AbstractRP_B_BIL_S02_02_02BLogic {

	/**
	 * 
	 * @param param
	 * @return ビジ�?スロジック�?�実行�?果�?BLogicResultインスタンス。
	 */
	public BLogicResult execute(RP_B_BIL_S02_02_02Input param) {
		BLogicResult result = new BLogicResult();
		T_BIL_H headerData = param.getHeaderData();
		RP_B_BIL_S02_02_02Output outputDTO = new RP_B_BIL_S02_02_02Output();
		
		String tremDays = headerData.getTermDays();
        if(!"0".equals(tremDays)){
        	headerData.setTerm(tremDays + " Days");
        }else {
        	headerData.setTerm("");
        }
		// BILL_TYPE
        String billType = headerData.getBillType();
        String typeName = "";
        if ("IN".equals(billType)) {
            // invoice INVNO
            typeName = "Invoice";
        } else if ("DN".equals(billType)) {
            // debit note DBTNO
            typeName = "Debit Note";
        } else {
            // credit note CDTNO
            typeName = "Credit Note";
        }
        
		G_CSB_P01_Check gCsbP01Check = new G_CSB_P01_Check(queryDAO);
        G_CSB_P01_CheckInput gCsbP01CheckInput = new G_CSB_P01_CheckInput();
        gCsbP01CheckInput.setMessageParam1("save");
        gCsbP01CheckInput.setMessageParam2(typeName);
        gCsbP01CheckInput.setMessageParam3("Batch Auto Offset Cash Book");
        G_CSB_P01_CheckOutput gCsbP01CheckOutput = gCsbP01Check.execute(gCsbP01CheckInput);
        boolean resultFlg = gCsbP01CheckOutput.isResultFlg();
        
        if (!resultFlg) {
            String errorMsg = gCsbP01CheckOutput.getMessageContext();
            outputDTO.setLastMsg(errorMsg);
            outputDTO.setIdRef(param.getIdRef());
            outputDTO.setMode("view");
            result.setResultString("fail");
        } else {
            HashMap<String, Object> inputData = new HashMap<String, Object>();
            inputData.put("idRef", param.getIdRef());
            inputData.put("dateReq", CommonUtils.parseToDate(headerData.getDateReq(), "dd/MM/yyyy"));
            inputData.put("idLogin", param.getUvo().getId_user());
            inputData.put("dateUpdated", new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
            /**
             * Audit Trail
             */
            //Modify Status
            String status="";
            if(headerData.getIsClosed().equals("0")){
                status="OPEN";
            }
            else{
                status="CLOSED";
            }
            Integer idAudit = CommonUtils.auditTrailBegin(param.getUvo().getId_user(), BillingSystemConstants.MODULE_B, 
                    BillingSystemConstants.SUB_MODULE_B_BIL, 
                    param.getIdRef(), status, BillingSystemConstants.AUDIT_TRAIL_EDITED);
            inputData.put("idAudit", idAudit);
            inputData.put("idCust", headerData.getIdCust());
            // cust info
            HashMap<String, Object> custInfo = (HashMap<String, Object>)this.queryDAO.executeForMap("B_BIL.getSingleCustInfo", inputData);
            inputData.put("custName", custInfo.get("CUST_NAME"));
            inputData.put("salutation", custInfo.get("SALUTATION"));
            inputData.put("address1", headerData.getAddress1());
            inputData.put("address2", headerData.getAddress2());
            inputData.put("address3", headerData.getAddress3());
            inputData.put("address4", headerData.getAddress4());
            inputData.put("zipCode", headerData.getZipCode());
            inputData.put("country", headerData.getCountry());
            inputData.put("telNo", headerData.getTelNo());
            inputData.put("faxNo", headerData.getFaxNo());
            inputData.put("contactName", headerData.getContactName());
            inputData.put("contactEmail", headerData.getContactEmail());
            inputData.put("idQcs",headerData.getIdQcs());
            inputData.put("quoRef", headerData.getQuoRef());
            inputData.put("custPo", headerData.getCustPo());
            inputData.put("term", headerData.getTerm());
            inputData.put("idConsult", headerData.getIdConsult());
            inputData.put("termDay", headerData.getTermDays());
    		inputData.put("dueDate", CommonUtils.parseToDate(headerData.getDueDate(), "dd/MM/yyyy"));
    		inputData.put("delivery", headerData.getDelivery());
    		String deliverymail=headerData.getDeliverymail();
            if(CommonUtils.isEmpty(deliverymail)){
                deliverymail="0";
            }
            inputData.put("deliverymail", deliverymail);
            inputData.put("adrType", headerData.getAdrType());
            inputData.put("emailTo", headerData.getEmailToAdd());
            inputData.put("emailCc", headerData.getEmailCcAdd());
            inputData.put("contactType", headerData.getContactType());
            inputData.put("contactName", headerData.getContactName());
    		
            String billCur = headerData.getBillCurrency();
            String expCur = headerData.getExportCur();
            if (!CommonUtils.isEmpty(billCur)
                    && !CommonUtils.isEmpty(expCur)
                    && !"-".equals(expCur)
                    && !billCur.equals(expCur)) {
                String curRate = CommonUtils.toString(headerData.getCurRate()).trim();
                //$CurRate = null or $CurRate = 0, $CurRate=1
                if (CommonUtils.isEmpty(curRate)) {
                    curRate = "1";
                } else {
                    if(Double.parseDouble(curRate)==0.0) {
                        curRate = "1";
                    }
                }
                //calculated Export Amount
                DecimalFormat formatter = new DecimalFormat("0.00");
                formatter.setRoundingMode(RoundingMode.HALF_UP);
                //calculated Export GST
                DecimalFormat formattergst = new DecimalFormat("0.0000");
                formattergst.setRoundingMode(RoundingMode.HALF_UP);
                G_CUR_P01 gCurP01 = new G_CUR_P01(queryDAO);
//                String gstAmountStr = headerData.getGstAmount();
//                String subTotalAmtStr = headerData.getSubTotalAmt();
//                double gstAmount = Double.parseDouble(gstAmountStr);
//                double subTotalAmt = Double.parseDouble(subTotalAmtStr);
//                BigDecimal exportAmtValue = new BigDecimal("0");
//                G_CUR_P01 gCurP01 = new G_CUR_P01(queryDAO);
//                Map<String, Object> expAmtMap = gCurP01.convertCurrency(billCur, gstAmount, expCur, curRate);
//                String exportAmt = CommonUtils.toString(expAmtMap.get(G_CUR_P01.EXPORT_AMOUNT));
//                exportAmtValue = exportAmtValue.add(new BigDecimal(exportAmt));
//                exportAmtValue = new BigDecimal(formatter.format(exportAmtValue));
//                expAmtMap = gCurP01.convertCurrency(billCur, subTotalAmt, expCur, curRate);
//                exportAmt = CommonUtils.toString(expAmtMap.get(G_CUR_P01.EXPORT_AMOUNT));
//                exportAmtValue = exportAmtValue.add(new BigDecimal(exportAmt));
//                exportAmtValue = new BigDecimal(formatter.format(exportAmtValue));
                //inputData.put("exportAmount", exportAmtValue);
                inputData.put("curRate", curRate);
                inputData.put("exportAmountChangeFlg", "1");
                
                BigDecimal expAmt = new BigDecimal("0");
                //GST Value
                List<Map<String, Object>> tBilDList = queryDAO.executeForMapList("B_BIL.selectT_BIL_D_BY_ID_REF", param.getIdRef());
                if (tBilDList!=null && 0<tBilDList.size()) {
                   for (Map<String, Object> tBilDMap: tBilDList) {
                       double gst = Double.parseDouble(CommonUtils.toString(tBilDMap.get("TAX_RATE")));
                       String itemAmtStr = CommonUtils.toString(tBilDMap.get("ITEM_SUBTOTAL"));
                       double itemAmt = Double.parseDouble(itemAmtStr);
                       double itemGstAmount = itemAmt * (gst / 100);
                       BigDecimal itemExportAmtValue = new BigDecimal("0");
                       BigDecimal itemAmtValue = new BigDecimal("0");
                       BigDecimal itemGstValue = new BigDecimal("0");
                       String itemExportAmt = "0";
                       String itemExportGst = "0";
                       Map<String, Object> itemExpAmtMap;
                       Map<String, Object> itemExpGstMap;
     
                       //T_BIL_D=> ItemExportAmt
                       itemExpAmtMap = gCurP01.convertCurrency(billCur, itemAmt, expCur, curRate);
                       itemExportAmt = CommonUtils.toString(itemExpAmtMap.get(G_CUR_P01.EXPORT_AMOUNT));
                       itemAmtValue = itemAmtValue.add(new BigDecimal(itemExportAmt));
                       itemAmtValue = new BigDecimal(formattergst.format(itemAmtValue));
                       //T_BIL_D=> ItemExportGst
                       itemExpGstMap=gCurP01.convertCurrency(billCur, itemGstAmount, expCur, curRate);
                       itemExportGst = CommonUtils.toString(itemExpGstMap.get(G_CUR_P01.EXPORT_AMOUNT));
                       itemGstValue = itemGstValue.add(new BigDecimal(itemExportGst));
                       itemGstValue = new BigDecimal(formattergst.format(itemGstValue));
                       //T_BIL_H ExportAmt
                       itemExportAmtValue = itemExportAmtValue.add(new BigDecimal(itemExportAmt));
                       itemExportAmtValue = itemExportAmtValue.add(new BigDecimal(itemExportGst));

                       Map<String, Object> sqlParam = new HashMap<String, Object>();
                       sqlParam.put("idRef", tBilDMap.get("ID_REF"));
                       sqlParam.put("itemId", tBilDMap.get("ITEM_ID"));
                       sqlParam.put("itemExportGst", itemGstValue);
                       sqlParam.put("itemExportAmt", itemAmtValue);
                       sqlParam.put("idLogin", param.getUvo().getId_user());
                       sqlParam.put("idAudit", idAudit);
                       
                       updateDAO.execute("B_BIL.updateMiniT_BIL_D", sqlParam);
                       
                       expAmt = expAmt.add(itemExportAmtValue);
                   }
                }
                inputData.put("exportAmount", formatter.format(expAmt));
            } else {
                inputData.put("exportAmountChangeFlg", "");
            }
            
            updateDAO.execute("B_BIL.updateMiniT_BIL_H", inputData);
            CommonUtils.auditTrailEnd(idAudit);//End Audit Trail
            
            try {
                BeanUtils.copyProperties(outputDTO, param);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            outputDTO.setMode("view");
            outputDTO.setFromPageFlag("BIL");
            // success message
            BLogicMessages msgs = new BLogicMessages();
            BLogicMessage msg = new BLogicMessage("info.ERR2SC003", new Object[]{});
            msgs.add(ActionMessages.GLOBAL_MESSAGE, msg);
            result.setMessages(msgs);
            result.setResultString("success");
        }
        result.setResultObject(outputDTO);
        return result;
    }
}