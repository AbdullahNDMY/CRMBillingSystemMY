/**
 * @(#)G_BAC_P01.java
 * 
 * Copyright (c) 2012 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */

package nttdm.bsys.common.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.web.struts.action.GlobalMessageResources;
import nttdm.bsys.common.bean.T_SET_BATCH;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.dto.G_BAC_P01_Input;
import nttdm.bsys.common.util.dto.G_BAC_P01_Output;
import nttdm.bsys.g_alt.AlertUserDto;
import nttdm.bsys.g_alt.G_ALT_P06Input;

/**
 * Update Total Amount Due
 * 
 * @author bench
 */
public class G_BAC_P01 extends AbstractGlobalProcess<G_BAC_P01_Input, G_BAC_P01_Output> {
    
    /**
     * batchId
     */
    private Integer idBatch=0;

    /**
     * idBatch を取得する
     * @return idBatch
     */
    public Integer getIdBatch() {
        return idBatch;
    }

    /**
     * batchId を設定する
     * @param idBatch
     *            idBatch
     */
    public void setIdBatch(Integer idBatch) {
        this.idBatch = idBatch;
    }
    
	private Integer auditId = null;
	
    @Override
	public GlobalProcessResult execute(G_BAC_P01_Input input, G_BAC_P01_Output outputDTO) {
		GlobalProcessResult gResult = new GlobalProcessResult();
		GlobalMessageResources message_info = GlobalMessageResources.getInstance();

        // Audit Trail
        if (!CommonUtils.isEmpty(input.getAuditIdModule())) {
            this.auditId = CommonUtils.auditTrailBegin(input.getId_login(),
            		input.getAuditIdModule(), input.getAuditIdSubModule(),
            		input.getAuditReference(), input.getAuditStatus(),
                    BillingSystemConstants.AUDIT_TRAIL_EDITED);
        }
        
		String type= input.getType();
		// called by batch
		if("E".equalsIgnoreCase(type)){
			String ERR2SC052 = message_info.getMessage("info.ERR2SC052");
            String ERR2SC053 = message_info.getMessage("info.ERR2SC053");
            String ERR2SC054 = message_info.getMessage("info.ERR2SC054");
            
			String id_login = message_info.getMessage("info.IDBatchLogin");
			T_SET_BATCH tset = new T_SET_BATCH();
			tset.setSTATUS("1");
			tset.setBATCH_TYPE("BA");
			tset.setID_LOGIN(id_login);
			G_SET_P01 gset = new G_SET_P01(this.queryDAO, this.updateDAO);
			int batch_id = gset.G_SET_P01_GetIdBatch(tset).getBatch_id();
			this.idBatch=batch_id;
			if (batch_id > 0) {
				try{
					List<Map<String, Object>> T_BAC_results = queryDAO.executeForMapList("SELECT.G_BAC.GETBILLACCOUNT", null);
					Map<String, Object> result = null;
					BigDecimal amountA = BigDecimal.ZERO;
					BigDecimal amountC = BigDecimal.ZERO;
					BigDecimal amountD = BigDecimal.ZERO;
					BigDecimal totalamtdue = BigDecimal.ZERO;
					BigDecimal actual_amount=BigDecimal.ZERO;
		            DecimalFormat formater = new DecimalFormat("0.00");
		            formater.setRoundingMode(RoundingMode.HALF_UP);
		            HashMap<String, Object> updateparam = null;
					if(T_BAC_results!=null && T_BAC_results.size()>0){
						for (int i = 0; i < T_BAC_results.size(); i++) {
							result = T_BAC_results.get(i);
							String id_bill_account = CommonUtils.toString(result.get("ID_BILL_ACCOUNT"));
							amountA = queryDAO.executeForObject("SELECT.G_BAC.GETAMOUNT.SQL001", id_bill_account, BigDecimal.class);
							amountC = queryDAO.executeForObject("SELECT.G_BAC.GETAMOUNT.SQL002", id_bill_account, BigDecimal.class);
							amountD = queryDAO.executeForObject("SELECT.G_BAC.GETAMOUNT.SQL003", id_bill_account, BigDecimal.class);
							
							actual_amount = amountA.subtract(amountC).subtract(amountD);
							totalamtdue = new BigDecimal(result.get("TOTAL_AMT_DUE").toString());
							if (totalamtdue.compareTo(actual_amount) != 0) {
    							updateparam = new HashMap<String, Object>(); 
    							updateparam.put("actual_amount",formater.format(actual_amount));
    							updateparam.put("id_login",id_login);
    							updateparam.put("auditId", auditId);
    							updateparam.put("id_bill_account",id_bill_account);
    							updateDAO.execute("UPDATE.G_BAC.TOTALDUE", updateparam);
							}
						}
					}
				}catch (Exception e) {
					e.printStackTrace();
					// when exception set failure
	                String batch_type = "BA";
	                String status = "3";
	                // update batch_id by call g_set_p01.
	                T_SET_BATCH t_set = new T_SET_BATCH();
	                t_set.setBATCH_TYPE(batch_type);
	                t_set.setFILENAME(" ");
	                t_set.setID_BATCH(batch_id + "");
	                t_set.setSTATUS(status);
	                t_set.setMessage(e.toString());
	                t_set.setID_LOGIN(id_login);
	                gset.G_SET_P01_GetIdBatch(t_set);
	                
	                // call sub_alert
	                subAlert(""+batch_id, ERR2SC052,ERR2SC054, id_login);
	                return gResult;
				}
				subAlert(""+batch_id, ERR2SC052, ERR2SC053, id_login);
				// when exception set failure
                String batch_type = "BA";
                String status = "2";
                // update batch_id by call g_set_p01.
                T_SET_BATCH t_set = new T_SET_BATCH();
                t_set.setBATCH_TYPE(batch_type);
                t_set.setFILENAME(" ");
                t_set.setID_BATCH(batch_id + "");
                t_set.setSTATUS(status);
                t_set.setMessage(ERR2SC053);
                t_set.setID_LOGIN(id_login);
                gset.G_SET_P01_GetIdBatch(t_set);
			}
		}
		// called by b_bac
		if ("B".equalsIgnoreCase(type)) {
			String MSG = "Total Amount Due successfully updated";
			String status = "0";
			String id_bill_account = input.getId_bill_account();
			
			BigDecimal amountA = BigDecimal.ZERO;
			BigDecimal amountC = BigDecimal.ZERO;
			BigDecimal amountD = BigDecimal.ZERO;
			BigDecimal totalamtdue = BigDecimal.ZERO;
			BigDecimal actual_amount = BigDecimal.ZERO;
			
			DecimalFormat formater = new DecimalFormat("0.00");
			formater.setRoundingMode(RoundingMode.HALF_UP);
			try {
				HashMap<String, Object> updateparam = null;
				HashMap<String, Object> param = new HashMap<String, Object>();
				param.put("id_bill_account", id_bill_account);
				Map<String, Object> T_BAC_record = queryDAO.executeForMap("SELECT.G_BAC.GETBILLACCOUNT", param);
				
				amountA = queryDAO.executeForObject("SELECT.G_BAC.GETAMOUNT.SQL001", id_bill_account, BigDecimal.class);
				amountC = queryDAO.executeForObject("SELECT.G_BAC.GETAMOUNT.SQL002", id_bill_account, BigDecimal.class);
				amountD = queryDAO.executeForObject("SELECT.G_BAC.GETAMOUNT.SQL003", id_bill_account, BigDecimal.class);
				
				actual_amount = amountA.subtract(amountC).subtract(amountD);
				totalamtdue = new BigDecimal(T_BAC_record.get("TOTAL_AMT_DUE").toString());
				if (totalamtdue.compareTo(actual_amount) != 0) {
					updateparam = new HashMap<String, Object>();
					updateparam.put("actual_amount", formater.format(actual_amount));
					updateparam.put("id_login", input.getId_login());
					updateparam.put("id_bill_account", id_bill_account);
					updateparam.put("auditId", auditId);
					updateDAO.execute("UPDATE.G_BAC.TOTALDUE", updateparam);
				}
			} catch (Exception e) {
				e.printStackTrace();
				MSG = "Error Calculating Total Amount Due";
				status = "1";
			}
			outputDTO.setMSG(MSG);
			outputDTO.setStatus(status);
		}
		
		// End Audit Trail
		if (!CommonUtils.isEmpty(input.getAuditIdModule())) {
			CommonUtils.auditTrailEnd(this.auditId);
		}
		
		return gResult;
	}
    
    /**
     * sub alert
     */
    private void subAlert(String Batch_id, String Subject, String MSG,String id_login){
        /**
         * get list user to sendmail(up to 5)
         */
        List<AlertUserDto> alertUsers = queryDAO.executeForObjectList(
                "SELECT.BSYS.SQL053", "BA");
        G_ALT_P06 g_alt_p06 = new G_ALT_P06(queryDAO, updateDAO);
        G_ALT_P06Input inp = new G_ALT_P06Input();
        inp.setBatchId("BA");
        inp.setMsg(MSG);
        inp.setSubject(Subject);
        inp.setListAlertUser(alertUsers);
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user(id_login);
        g_alt_p06.execute(inp, uvo);
    }
}
