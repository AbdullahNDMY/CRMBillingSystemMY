/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_BNKS01
 * SERVICE NAME   : M_BNKS01
 * FUNCTION       : Get result search 
 * FILE NAME      : M_BNKS01Blogic.java
 * 
 * Copyright c 2011 NTTDATA Corporation
 *
 * History
 * 2011/09/15 [Hoang Duong] Update
 * 2011/09/30 [Hoang Duong] Update
 **********************************************************/
package nttdm.bsys.m_bnk.blogic;

import java.util.ArrayList;
import java.util.List;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.m_bnk.bean.M_BNK_bankbean;
import nttdm.bsys.m_bnk.dto.M_BNKS01Input;
import nttdm.bsys.m_bnk.dto.M_BNKS01Output;

import org.apache.struts.action.ActionMessages;

/**
 * M_BNKS01Blogic.class<br>
 * <ul>
 * <li>Get result search</li>
 * </ul>
 * <br>
 * 
 * @author NTTData Vietnam
 * @version 1.1
 */

public class M_BNKS01Blogic extends AbstractM_BNKS01 {

	public BLogicResult execute(M_BNKS01Input param) {
		BLogicResult result = new BLogicResult();
		M_BNKS01Output output = new M_BNKS01Output();
		if(param.getCheckpagetype() >= 1){
			int row;
			int startIndex = 0;
			startIndex = param.getStartIndex();
			BillingSystemSettings systemSetting = new BillingSystemSettings(
					queryDAO);
			row = systemSetting.getResultRow();
			output.setRow(row);

			if (param.getBank_fullname() == null
					|| param.getBank_fullname().equals("")) {
				String bankfullname = "";
				param.setBank_fullname(bankfullname);
			} else {
				param.setBank_fullname(param.getBank_fullname().trim());
			}

			if (param.getBank_code() == null || param.getBank_code().equals("")) {
				String bankcode = "";
				param.setBank_code(bankcode);
			} else {
				param.setBank_code(param.getBank_code().trim());

			}
			if (param.getBranch_code() == null || param.getBranch_code().equals("")) {
				String branchcode = "";
				param.setBranch_code(branchcode);
			} else {
				param.setBranch_code(param.getBranch_code().trim());

			}

			if (param.getBank_bic_code() == null || param.getBank_bic_code().equals("")) {
                String bankbiccode = "";
                param.setBank_bic_code(bankbiccode);
            } else {
                param.setBank_bic_code(param.getBank_bic_code().trim());
            }
			
			List<M_BNK_bankbean> listsearch = queryDAO.executeForObjectList(
					"SELECT.M_BNK.SQL001", param);
			if (startIndex < 0 || startIndex > listsearch.size()){
			    startIndex = 0;
			}
			output.setTotalCount(listsearch.size());
			listsearch = queryDAO.executeForObjectList("SELECT.M_BNK.SQL001",
					param, startIndex, row);

			output.setListsearch(listsearch);

			if (listsearch.size() > 0) {
				for (int i = 1; i <= listsearch.size(); i++) {
					listsearch.get(i - 1).setIdnum(i + startIndex);
				}
				output.setCheckpagetype(2);
				output.setStartIndex(startIndex);
			}
			
            if (output.getTotalCount() == 0) {
                // info.ERR2SC002
                BLogicMessages msgs = new BLogicMessages();
                BLogicMessage msg = new BLogicMessage("info.ERR2SC002", new Object());
                msgs.add(ActionMessages.GLOBAL_MESSAGE, msg);
                result.setMessages(msgs);
            }
			result.setResultString("success");
			result.setResultObject(output);
			
		} else if(param.getCheckpagetype()< 1 ) {
			List<M_BNK_bankbean> listsearch = new ArrayList<M_BNK_bankbean>();
			output.setListsearch(listsearch);
			output.setTotalCount(-1);
			result.setResultString("success");
		    result.setResultObject(output);
		}

		return result;
	}

}
