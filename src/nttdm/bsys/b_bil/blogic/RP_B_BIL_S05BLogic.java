/**
 * @(#)RP_B_BIL_S05BLogic.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2012/05/25
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.b_bil.blogic;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.b_bil.dto.RP_B_BIL_S05Input;
import nttdm.bsys.b_bil.dto.RP_B_BIL_S05Output;
import nttdm.bsys.common.util.CommonUtils;

/**
 * BusinessLogic class.
 * 
 * @author i-leonzh
 * 
 */
public class RP_B_BIL_S05BLogic extends AbstractRP_B_BIL_S05BLogic {

	public BLogicResult execute(RP_B_BIL_S05Input input) {
		BLogicResult result = new BLogicResult();
		RP_B_BIL_S05Output output = new RP_B_BIL_S05Output();
		
		String idRef = CommonUtils.toString(input.getIdRef());
		String itemId = CommonUtils.toString(input.getItemId());
		String itemDesc = CommonUtils.toString(input.getItemDesc());
		
		output.setIdRef(idRef);
		output.setItemId(itemId);
		output.setItemDesc(itemDesc);
		
		result.setResultObject(output);
		result.setResultString("success");
		
		return result;
	}

}
