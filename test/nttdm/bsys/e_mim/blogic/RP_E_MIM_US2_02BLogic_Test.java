/**
 * @(#)RP_E_MIM_US2_02BLogic_Test.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2011/10/29
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.e_mim.blogic;

import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.AbstractUtilTest;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.G_CLC_P01;
import nttdm.bsys.common.util.GlobalProcessResult;
import nttdm.bsys.e_mim.dto.RP_E_MIM_US2_02Input;
import nttdm.bsys.e_mim.dto.RP_E_MIM_US2_02Output;
import nttdm.bsys.util.ApplicationContextProvider;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts.upload.FormFile;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.springframework.context.ApplicationContext;

/**
 * @author gplai
 *
 */
public class RP_E_MIM_US2_02BLogic_Test extends AbstractUtilTest {

    private RP_E_MIM_US2_02BLogic logic;
    @Override
    protected void setUpData() throws Exception {
        logic = new RP_E_MIM_US2_02BLogic();
        
    }

    public void testExecute01() throws Exception {
        RP_E_MIM_US2_02Input input = new RP_E_MIM_US2_02Input();
        input.setMonth("01");
        input.setYear("2012");
        
        BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
        uvo.setId_user("myIdUser");
        input.setUvo(uvo);
        
        List<Map<String, Object>> listData = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("idClcCust","0001");
        map.put("invoiceTotal","200");
        listData.add(map);
        FormFile formFile = getFormFile(true, listData,"testExcel.xlsx");
        input.setFormFile(formFile);
        
//        
//        // declare a object for mock interface
//        Mockery mockImpl = new Mockery();
//
//        // declare a object for mock class
//        Mockery mockClass = new JUnit4Mockery() {
//            {
//                setImposteriser(ClassImposteriser.INSTANCE);
//            }
//        };
//
//        input.setFormFile(mockImpl.mock(FormFile.class));
//
//        // declare a GlobalProcessResult object and assignment to it
//        final GlobalProcessResult glPResult = new GlobalProcessResult();
//
//        BLogicMessages myErrors = new BLogicMessages();
//        BLogicMessages myMessages = new BLogicMessages();
//
//        glPResult.setErrors(myErrors);
//        glPResult.setMessages(myMessages);
//
//        // declare a G_CLC_P01 object by mock
//        final G_CLC_P01 g_clc_p01 = mockClass.mock(G_CLC_P01.class);
//        // set g_sgp_p02's expect method
//        mockClass.checking(new Expectations() {
//            {
//                oneOf(g_clc_p01).setAuditIdModule(BillingSystemConstants.MODULE_E);
//            }
//        });
//        mockClass.checking(new Expectations() {
//            {
//                oneOf(g_clc_p01).setAuditIdSubModule(BillingSystemConstants.SUB_MODULE_E_MIM_US2);
//            }
//        });
//        mockClass.checking(new Expectations() {
//            {
//                oneOf(g_clc_p01).execute(with(any(RP_E_MIM_US2_02Input.class)),
//                        with(any(RP_E_MIM_US2_02Output.class)));
//                will(returnValue(glPResult));
//            }
//        });
//        mockClass.checking(new Expectations() {
//            {
//                oneOf(g_clc_p01).reset();
//            }
//        });
//
//        // declare a ApplicationContext object by mock
//        final ApplicationContext context = mockImpl
//                .mock(ApplicationContext.class);
//        // set context's expect method
//        mockImpl.checking(new Expectations() {
//            {
//                oneOf(context).getBean("G_CLC_P01");
//                will(returnValue(g_clc_p01));
//            }
//        });
//
//        // set context for ApplicationContextProvider
//        ApplicationContextProvider provider = new ApplicationContextProvider();
//        provider.setApplicationContext(context);
        
        BLogicResult result = logic.execute(input);
        assertEquals("success", result.getResultString());
    }
    
    @SuppressWarnings("rawtypes")
    private FormFile getFormFile(boolean isExcel2007,List<Map<String, Object>> listData, String fileName) throws Exception {
        Class parentClass = Class.forName("org.apache.struts.upload.CommonsMultipartRequestHandler");
        Class childClass = parentClass.getDeclaredClasses()[0];
        Constructor c = childClass.getConstructors()[0];
        c.setAccessible(true);
        FileItemFactory factory = new DiskFileItemFactory(16, null);
        FileItem fileItem = factory.createItem("excelField", "application/vnd.ms-excel", true,fileName);
        OutputStream os = fileItem.getOutputStream();
        printExcel(isExcel2007, listData).write(os);
        os.flush();
        os.close();
        FormFile file = (FormFile) c.newInstance(new Object[] {fileItem });
        return file;
    }
    private Workbook printExcel(boolean isExcel2007,List<Map<String, Object>> listData){
        Workbook workbook = null;
        try{
              if(isExcel2007){
                  workbook = new XSSFWorkbook();
              } else {
                  workbook = new HSSFWorkbook();
              }
              Sheet sheet = workbook.createSheet("testSheet"); 
              Row row = sheet.createRow(0);
              this.createCell(row, 0, null, Cell.CELL_TYPE_STRING, "ID_CLC_CUST");
              this.createCell(row, 1, null, Cell.CELL_TYPE_STRING, "INVOICE_TOTAL");
              for(int i=0; i<listData.size(); i++) {
                  Row rowData1 = sheet.createRow(i+1);
                  Map<String, Object> map = listData.get(i);
                  this.createCell(rowData1, 0, null, Cell.CELL_TYPE_STRING, map.get("idClcCust"));
                  String invoiceTotal = CommonUtils.toString(map.get("invoiceTotal"));
                  if (CommonUtils.isEmpty(invoiceTotal)) {
                      this.createCell(rowData1, 1, null, Cell.CELL_TYPE_STRING, map.get("invoiceTotal"));
                  } else {
                      try{
                          Double.parseDouble(invoiceTotal);
                          this.createCell(rowData1, 1, null, Cell.CELL_TYPE_NUMERIC, map.get("invoiceTotal"));
                      }catch(Exception e) {
                          this.createCell(rowData1, 1, null, Cell.CELL_TYPE_STRING, map.get("invoiceTotal"));
                      }
                  }
              }
         }catch(Exception e){
                     e.printStackTrace();
         }
        return workbook;
    }
    
    private void createCell(Row row, int column, CellStyle style,int cellType,Object value) { 
        Cell cell = row.createCell(column); 
        if (style != null) { 
             cell.setCellStyle(style); 
        }   
        switch(cellType){ 
             case Cell.CELL_TYPE_BLANK: {} break; 
             case Cell.CELL_TYPE_STRING: {cell.setCellValue(value.toString());} break; 
             case Cell.CELL_TYPE_NUMERIC: {
                 cell.setCellType(Cell.CELL_TYPE_NUMERIC); 
                 cell.setCellValue(Double.parseDouble(value.toString()));
             }break; 
             default: break; 
       }  
   }
   
}
