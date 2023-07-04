/**
 * @(#)G_DOC_P01_Test.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2012/04/24
 * 
 * Copyright (c) 2012 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.util;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.util.ArrayList;

import nttdm.bsys.m_alt.dto.M_ALTR02Input;

import org.apache.commons.fileupload.DefaultFileItemFactory;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.struts.upload.FormFile;

/**
 * @author gplai
 *
 */
public class G_DOC_P01_Test extends AbstractUtilTest {
    
    private G_DOC_P01 gDocP01;
    
    @Override
    protected void setUpData() throws Exception {
        super.deleteAllData("M_GSET_D");
        super.deleteAllData("T_DOC");
    }

    /**
     * case1:FilePath is null
     * @throws Exception 
     */
    public void testExecute1() throws Exception {
        String[][] testDataMGsetD1 =
        {
            {"ID_SET" , "SET_SEQ" , "SET_VALUE" , "SET_DESC" ,
                "SET_APPLY" , "IS_DELETED" , "DATE_CREATED" ,
                "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
            {"FILELOC" , "1" , "" ,
                "Path to export PeopleSoft CSV file" , "1" , "0" ,
                "2011-11-24" , "2011-07-13" , "USERFULL" , null}};
        String[][] testDataMGsetD2 =
        {
            {"ID_SET" , "SET_SEQ" , "SET_VALUE" , "SET_DESC" ,
                "SET_APPLY" , "IS_DELETED" , "DATE_CREATED" ,
                "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
            {"FILESIZEUPLOAD" , "1" , "100000000" ,
                "Path to export PeopleSoft CSV file" , "1" , "0" ,
                "2011-11-24" , "2011-07-13" , "USERFULL" , null}};
        
        super.insertData("M_GSET_D", testDataMGsetD1);
        super.insertData("M_GSET_D", testDataMGsetD2);
        
        gDocP01 = new G_DOC_P01(updateDAO, queryDAO);
        ArrayList<ArrayList<String>> result = gDocP01.uploadFile(null, null);
        assertEquals(0, result.size());
    }
    
    /**
     * case1:import 1 file 
     * @throws ClassNotFoundException 
     * @throws IOException 
     */
    public void testExecute2() throws Exception {
        String[][] testDataMGsetD1 =
        {
            {"ID_SET" , "SET_SEQ" , "SET_VALUE" , "SET_DESC" ,
                "SET_APPLY" , "IS_DELETED" , "DATE_CREATED" ,
                "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
            {"FILELOC" , "1" , "C:\\OfficeSystem\\UploadFile\\" ,
                "Path to export PeopleSoft CSV file" , "1" , "0" ,
                "2011-11-24" , "2011-07-13" , "USERFULL" , null}};
        String[][] testDataMGsetD2 =
        {
            {"ID_SET" , "SET_SEQ" , "SET_VALUE" , "SET_DESC" ,
                "SET_APPLY" , "IS_DELETED" , "DATE_CREATED" ,
                "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
            {"FILESIZEUPLOAD" , "1" , "100000000" ,
                "Path to export PeopleSoft CSV file" , "1" , "0" ,
                "2011-11-24" , "2011-07-13" , "USERFULL" , null}};
        
        super.insertData("M_GSET_D", testDataMGsetD1);
        super.insertData("M_GSET_D", testDataMGsetD2);
        
        gDocP01 = new G_DOC_P01(updateDAO, queryDAO);
        
        M_ALTR02Input input = new M_ALTR02Input();
        input.setMsg_type("NOTICE");
        input.setId_msg("message id");
        input.setId_login("sysadmin");
        
        FormFile[] formFileArray = new FormFile[1];
        
        Class parentClass = Class.forName("org.apache.struts.upload.CommonsMultipartRequestHandler");
        Class childClass = parentClass.getDeclaredClasses()[0];
        Constructor c = childClass.getConstructors()[0];
        c.setAccessible(true);
        FileItemFactory factory = new DefaultFileItemFactory(16, null);
        String textFieldName = "textField";
        FileItem item = factory.createItem(textFieldName, "text/plain", true, "20110915.txt");
        OutputStream os = item.getOutputStream();
        String detai;
        detai = "H20110916Filler\r\n";
        os.write(detai.getBytes());
        os.close();
        FormFile formFile = (FormFile) c.newInstance(new Object[] { item });
        formFileArray[0] = formFile;
        
        gDocP01.setUpdateDAO(updateDAO);
        
        ArrayList<ArrayList<String>> result = gDocP01.uploadFile(formFileArray, input);
        assertEquals("20110915.txt", result.get(0).get(0));
    }
    
    /**
     * case3:import 3 file 
     * @throws ClassNotFoundException 
     * @throws IOException 
     */
    public void testExecute3() throws Exception {
        String[][] testDataMGsetD1 =
        {
            {"ID_SET" , "SET_SEQ" , "SET_VALUE" , "SET_DESC" ,
                "SET_APPLY" , "IS_DELETED" , "DATE_CREATED" ,
                "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
            {"FILELOC" , "1" , "C:\\OfficeSystem\\UploadFile\\" ,
                "Path to export PeopleSoft CSV file" , "1" , "0" ,
                "2011-11-24" , "2011-07-13" , "USERFULL" , null}};
        String[][] testDataMGsetD2 =
        {
            {"ID_SET" , "SET_SEQ" , "SET_VALUE" , "SET_DESC" ,
                "SET_APPLY" , "IS_DELETED" , "DATE_CREATED" ,
                "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
            {"FILESIZEUPLOAD" , "1" , "100000000" ,
                "Path to export PeopleSoft CSV file" , "1" , "0" ,
                "2011-11-24" , "2011-07-13" , "USERFULL" , null}};
        
        super.insertData("M_GSET_D", testDataMGsetD1);
        super.insertData("M_GSET_D", testDataMGsetD2);
        
        gDocP01 = new G_DOC_P01(updateDAO, queryDAO);
        
        M_ALTR02Input input = new M_ALTR02Input();
        input.setMsg_type("NOTICE");
        input.setId_msg("message id");
        input.setId_login("sysadmin");
        
        FormFile[] formFileArray = new FormFile[3];
        
        Class parentClass = Class.forName("org.apache.struts.upload.CommonsMultipartRequestHandler");
        Class childClass = parentClass.getDeclaredClasses()[0];
        Constructor c = childClass.getConstructors()[0];
        c.setAccessible(true);
        FileItemFactory factory = new DefaultFileItemFactory(16, null);
        String textFieldName = "textField";
        FileItem item = factory.createItem(textFieldName, "text/plain", true, "20110915.txt");
        OutputStream os = item.getOutputStream();
        String detai;
        detai = "H20110916Filler\r\n";
        os.write(detai.getBytes());
        os.close();
        FormFile formFile = (FormFile) c.newInstance(new Object[] { item });
        
        FileItem item1 = factory.createItem(textFieldName, "text/plain", true, "20110916.txt");
        OutputStream os1 = item1.getOutputStream();
        String detai1;
        detai1 = "H20110916Filler\r\n";
        os1.write(detai1.getBytes());
        os1.close();
        FormFile formFile1 = (FormFile) c.newInstance(new Object[] { item1 });
        
        FileItem item2 = factory.createItem(textFieldName, "text/plain", true, "20110917.txt");
        OutputStream os2 = item2.getOutputStream();
        String detai2;
        detai2 = "H20110916Filler\r\n";
        os2.write(detai2.getBytes());
        os2.close();
        FormFile formFile2 = (FormFile) c.newInstance(new Object[] { item2 });
        
        formFileArray[0] = formFile;
        formFileArray[1] = formFile1;
        formFileArray[2] = formFile2;
        
        ArrayList<ArrayList<String>> result = gDocP01.uploadFile(formFileArray, input);
        assertEquals("20110915.txt", result.get(0).get(0));
        assertEquals("20110916.txt", result.get(0).get(1));
        assertEquals("20110917.txt", result.get(0).get(2));
    }
    
    /**
     * case4:import 1 file the filePath not exite
     * @throws Exception 
     */
    public void testExecute4() throws Exception {
        String[][] testDataMGsetD1 =
        {
            {"ID_SET" , "SET_SEQ" , "SET_VALUE" , "SET_DESC" ,
                "SET_APPLY" , "IS_DELETED" , "DATE_CREATED" ,
                "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
            {"FILELOC" , "1" , "C:\\OfficeSystem\\UploadFile\\" ,
                "Path to export PeopleSoft CSV file" , "1" , "0" ,
                "2011-11-24" , "2011-07-13" , "USERFULL" , null}};
        String[][] testDataMGsetD2 =
        {
            {"ID_SET" , "SET_SEQ" , "SET_VALUE" , "SET_DESC" ,
                "SET_APPLY" , "IS_DELETED" , "DATE_CREATED" ,
                "DATE_UPDATED" , "ID_LOGIN" , "ID_AUDIT"} ,
            {"FILESIZEUPLOAD" , "1" , "100000000" ,
                "Path to export PeopleSoft CSV file" , "1" , "0" ,
                "2011-11-24" , "2011-07-13" , "USERFULL" , null}};
        
        super.insertData("M_GSET_D", testDataMGsetD1);
        super.insertData("M_GSET_D", testDataMGsetD2);
        
        gDocP01 = new G_DOC_P01(updateDAO, queryDAO);
        
        M_ALTR02Input input = new M_ALTR02Input();
        input.setMsg_type("NOTICE");
        input.setId_msg("message id");
        input.setId_login("sysadmin");
        
        FormFile[] formFileArray = new FormFile[1];
        
        Class parentClass = Class.forName("org.apache.struts.upload.CommonsMultipartRequestHandler");
        Class childClass = parentClass.getDeclaredClasses()[0];
        Constructor c = childClass.getConstructors()[0];
        c.setAccessible(true);
        FileItemFactory factory = new DefaultFileItemFactory(16, null);
        String textFieldName = "textField";
        FileItem item = factory.createItem(textFieldName, "text/plain", true, "20110915.txt");
        OutputStream os = item.getOutputStream();
        String detai;
        detai = "H20110916Filler\r\n";
        os.write(detai.getBytes());
        os.close();
        FormFile formFile = (FormFile) c.newInstance(new Object[] { item });
        formFileArray[0] = formFile;
        
        gDocP01.setUpdateDAO(updateDAO);
        gDocP01.getUpdateDAO();
        
        ArrayList<ArrayList<String>> result = gDocP01.uploadFile(formFileArray, input);
        assertEquals("20110915.txt", result.get(0).get(0));
    }
}
