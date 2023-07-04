/**
 * @(#)M_SVGS01_03BLogic_Test.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created May 8, 2012
 * 
 * Copyright (c) 2012 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.m_svg.blogic;

import java.util.List;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.AbstractUtilTest;
import nttdm.bsys.m_svg.bean.ServiceGroupBean;
import nttdm.bsys.m_svg.dto.M_SVGS01_03Input;

/**
 * @author Joel Chin Yat Leng
 *
 */
public class M_SVGS01_03BLogic_Test extends AbstractUtilTest {
	
	private M_SVGS01_03BLogic blogic03;
	private M_SVGS01_03Input param;
	BLogicResult result;
	
	@Override
	protected void setUpData() throws Exception {
		
		// init test object
		blogic03 = new M_SVGS01_03BLogic();
		param = new M_SVGS01_03Input();
		result = new BLogicResult(); 
	}
	
	public void testExecute() throws Exception{
		
		BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
		param.setUvo(uvo);
		param.getUvo().setId_user("joel.chin");
		
		param.setAction("");
		
		List<ServiceGroupBean> lst = null;
		lst = this.queryDAO.executeForObjectList("SELECT.M_SVG.SQL001",null);
		param.setServiceGrpItem(lst);
		
		for(int i=0;i<param.getServiceGrpItem().size();i++)
		{
			param.getServiceGrpItem().get(i).setId_login("joel.chin");
		}
				
		//result = blogic03.execute(param);
		System.out.println("M_SVG_S01 : Edit / New Category : " + result.getResultString());
	}
}





