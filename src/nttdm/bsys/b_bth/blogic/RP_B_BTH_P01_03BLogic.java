/**
 * Billing System
 * 
 * SUBSYSTEM NAME : RP_B_BTH_P01 
 * SERVICE NAME :  RP_B_BTH
 * FUNCTION : Print Billing Document
 * FILE NAME : RP_B_BTH_P01_03BLogic.java
 * 
 * Copyright (C) 2011 NTTDATA Corporation
 */

package nttdm.bsys.b_bth.blogic;

import java.lang.reflect.InvocationTargetException;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.b_bth.dto.RP_B_BTH_P01_03Input;
import nttdm.bsys.b_bth.dto.RP_B_BTH_P01_03Output;
import nttdm.bsys.common.util.G_BTH_P01;
import nttdm.bsys.common.util.GlobalProcessResult;
import nttdm.bsys.util.ApplicationContextProvider;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.context.ApplicationContext;

/** 
 * BusinessLogic class.
 * @author NTT Data Vietnam	
 * Action export file RP_B_BTH_P01
 * 
 */

public  class RP_B_BTH_P01_03BLogic extends AbstractRP_B_BTH_P01_03BLogic{
	public BLogicResult execute(RP_B_BTH_P01_03Input param) {	
		BLogicResult result = new BLogicResult();
		RP_B_BTH_P01_03Output outputDTO = new RP_B_BTH_P01_03Output();
		//Call G_BTH_P01 process
		ApplicationContext context = ApplicationContextProvider.getApplicationContext();
		G_BTH_P01 gBthP01 = (G_BTH_P01)context.getBean("G_BTH_P01");
		GlobalProcessResult glPResult = gBthP01.execute(param, outputDTO);
		result.setErrors(glPResult.getErrors());
		result.setMessages(glPResult.getMessages());
		try {
			BeanUtils.copyProperties(outputDTO, param);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		// Set download file
		result.setResultObject(gBthP01.getDownloadFile(glPResult.getFile()));
		return result;
	}
}