/**
 * @(#)RP_E_DIM_SP1_02BLogic_Test.java
 * 
 * Billing System NTTS
 * 
 * Version 1.00
 * 
 * Created 2011/08/17
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.e_dim.blogic;

import java.lang.reflect.Constructor;

import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.AbstractUtilTest;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.G_SGP_P02;
import nttdm.bsys.common.util.GlobalProcessResult;
import nttdm.bsys.e_dim.dto.RP_E_DIM_SP1_02Input;
import nttdm.bsys.e_dim.dto.RP_E_DIM_SP1_02Output;
import nttdm.bsys.util.ApplicationContextProvider;

import org.apache.commons.fileupload.DefaultFileItemFactory;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.struts.upload.FormFile;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.springframework.context.ApplicationContext;

/**
 * Test Class for nttdm.bsys.e_dim.blogic.RP_E_DIM_SP1_02BLogic
 * 
 * @author leonzh
 */
public class RP_E_DIM_SP1_02BLogic_Test extends AbstractUtilTest {

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.terasoluna.utlib.spring.DaoTestCase#setUpData()
	 */
	@Override
	protected void setUpData() throws Exception {

	}

	/**
	 * 
	 * Case 1:test normal situation
	 * 
	 * Condition:<br>
	 * myErrors = new BLogicMessages() <br>
	 * myMessages = new BLogicMessages() <br>
	 * input.formFile = mockImpl.mock(FormFile.class) <br>
	 * input.month = "myMonth" <br>
	 * input.year = "myYear" <br>
	 * 
	 * Result: <br>
	 * a specific result <br>
	 * 
	 */
	public void testExecute01() {
//		// declare a object for mock interface
//		Mockery mockImpl = new Mockery();
//
//		// declare a object for mock class
//		Mockery mockClass = new JUnit4Mockery() {
//			{
//				setImposteriser(ClassImposteriser.INSTANCE);
//			}
//		};
//
//		// declare a input object and assignment to it
//		final RP_E_DIM_SP1_02Input input = new RP_E_DIM_SP1_02Input();
//
//		input.setFormFile(mockImpl.mock(FormFile.class));
//		input.setMonth("myMonth");
//		input.setYear("myYear");
//
//		// declare a GlobalProcessResult object and assignment to it
//		final GlobalProcessResult glPResult = new GlobalProcessResult();
//
//		BLogicMessages myErrors = new BLogicMessages();
//		BLogicMessages myMessages = new BLogicMessages();
//
//		glPResult.setErrors(myErrors);
//		glPResult.setMessages(myMessages);
//
//		// declare a G_SGP_P02 object by mock
//		final G_SGP_P02 g_sgp_p02 = mockClass.mock(G_SGP_P02.class);
//		// set g_sgp_p02's expect method
//		mockClass.checking(new Expectations() {
//			{
//				oneOf(g_sgp_p02).setAuditIdModule(BillingSystemConstants.MODULE_E);
//			}
//		});
//		mockClass.checking(new Expectations() {
//			{
//				oneOf(g_sgp_p02).setAuditIdSubModule(BillingSystemConstants.SUB_MODULE_E_DIM_SP1);
//			}
//		});
//		mockClass.checking(new Expectations() {
//			{
//				oneOf(g_sgp_p02).execute(with(any(RP_E_DIM_SP1_02Input.class)),
//						with(any(RP_E_DIM_SP1_02Output.class)));
//				will(returnValue(glPResult));
//			}
//		});
//		mockClass.checking(new Expectations() {
//			{
//				oneOf(g_sgp_p02).reset();
//			}
//		});
//
//		// declare a ApplicationContext object by mock
//		final ApplicationContext context = mockImpl
//				.mock(ApplicationContext.class);
//		// set context's expect method
//		mockImpl.checking(new Expectations() {
//			{
//				oneOf(context).getBean("G_SGP_P02");
//				will(returnValue(g_sgp_p02));
//			}
//		});
//
//		// set context for ApplicationContextProvider
//		ApplicationContextProvider provider = new ApplicationContextProvider();
//		provider.setApplicationContext(context);
	    final RP_E_DIM_SP1_02Input input = new RP_E_DIM_SP1_02Input();

        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("myIdUser");
        input.setUvo(uvo);
        input.setMonth("09");
        input.setYear("2011");
        
        try {
            Class parentClass = Class.forName("org.apache.struts.upload.CommonsMultipartRequestHandler");
            Class childClass = parentClass.getDeclaredClasses()[0];
            Constructor c = childClass.getConstructors()[0];
            c.setAccessible(true);
            FileItemFactory factory = new DefaultFileItemFactory(16, null);
            String textFieldName = "textField";
            FileItem item = factory.createItem(textFieldName, "text/plain", true, "20110915.txt");
            FormFile formFile = (FormFile) c.newInstance(new Object[] { item });
            input.setFormFile(formFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
		// test target object and return result
		RP_E_DIM_SP1_02BLogic blogic = new RP_E_DIM_SP1_02BLogic();
		BLogicResult result = blogic.execute(input);

		// get output by result
		RP_E_DIM_SP1_02Output output = (RP_E_DIM_SP1_02Output) result.getResultObject();

		assertEquals(input.getFormFile(), output.getFormFile());
		assertEquals(input.getMonth(), output.getMonth());
		assertEquals(input.getYear(), output.getYear());
		assertEquals("success", result.getResultString());
	}
}
