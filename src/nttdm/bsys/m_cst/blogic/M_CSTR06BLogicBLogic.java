
/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_CST
 * SERVICE NAME   : M_CST06
 * FUNCTION       : Export result search to csv file
 * FILE NAME      : M_CSTR06BLogicBLogic.java
 *
 * Copyright (C) 2011 NTTDATA Corporation
 * 
**********************************************************/
package nttdm.bsys.m_cst.blogic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicResult;
import jp.terasoluna.fw.web.struts.actions.DownloadFile;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.m_cst.bean.CustomerBean;
import nttdm.bsys.m_cst.common.CSVWriter;
import nttdm.bsys.m_cst.common.FileImportExportException;
import nttdm.bsys.m_cst.common.Util;
import nttdm.bsys.m_cst.dto.CustomerDto;
import nttdm.bsys.m_cst.dto.M_CSTR06BLogicInput;

/**
 * BusinessLogic class for Export Customer Information CSV
 * 
 * @author NTT Data VietNam
 */
public class M_CSTR06BLogicBLogic extends AbstractM_CSTR06BLogicBLogic {

	private int column = 37;

    /**
     * <div>BLANK</div>
     */
    private static final String BLANK = "";
    
    /**
     * <div>PERCENT_SIGN</div>
     */
    private static final String PERCENT_SIGN = "%";
    
    /**
     * <div>SQL_SEARCH_CUSTOMER FOR BASIC SEARCH</div>
     */
    private static final String SQL_SEARCH_CUSTOMER = "SELECT.M_CST.SQL061";
    /**
     * <div>SQL_SEARCH_CUSTOMER FOR ADVANCED SEARCH</div>
     */
    private static final String SQL_SEARCH_CUSTOMER_ADVANCED = "SELECT.M_CST.SQL015";
    

	/**
	 * Export Customer information into CSV file for download.
	 * 
	 * @param param
	 * 
	 */
	public BLogicResult execute(M_CSTR06BLogicInput param) {
		BLogicResult result = new BLogicResult();
		// generate download file 
		DownloadFile file = new DownloadFile(this.exportCSVFile(param));
		result.setResultObject(file);
		
		return result;
	}
	
	/**
	 * Generate download file.
	 * 
	 * @param param
	 * @return
	 */
	private File exportCSVFile(M_CSTR06BLogicInput param){
		List<String[]> exportList = new ArrayList<String[]>();
		
		String tmpFolder = System.getProperty("java.io.tmpdir");
		// CUST_INFO_DDMMYYYY_HHmmss.csv
		Date today = Calendar.getInstance().getTime();
		SimpleDateFormat sdf = new SimpleDateFormat(BillingSystemConstants.DATETIME_FORMAT_EXPORT);
		String date = sdf.format(today.getTime());
		System.out.println("date=" + date);

		String fileName = tmpFolder + File.separator
				+ "CUST_INFO_" + date
				+ ".csv";
		System.out.println("fileName=" + fileName);
		// Add header to this file
		exportList.add(this.initHeadingLine1());
		
		// Get customer id list from input parameter
		String[] rows = new String[0];

        List<CustomerBean> cusBean = null;
        
		if(param.getSearchType() != null) {
			if(param.getSearchType().trim().equals("basicSearch")) {
			    CustomerBean inputBean = this.changeInputMap(param);
	            cusBean = queryDAO.executeForObjectList(SQL_SEARCH_CUSTOMER, inputBean);
			} else if(param.getSearchType().trim().equals("advancedSearch")) {
			    CustomerBean inputBean = this.setCriticalForSearch(param);
                cusBean = queryDAO.executeForObjectList(SQL_SEARCH_CUSTOMER_ADVANCED, inputBean);
			}
		}
		if (rows != null && cusBean.size() > 0) {
			for (int i = 0; i < cusBean.size(); i++) { 
				String[] exportLine = new String[column];
				initData(exportLine);
				CustomerDto customer = queryDAO.executeForObject(Util.SELECT_EXPORT, cusBean.get(i).getId_cust(), CustomerDto.class);
				exportLine[0] = String.valueOf(i+1); // No
				
				if (customer.getId_cust()!= null) exportLine[1] = customer.getId_cust(); // Customer ID
				if (customer.getCust_name()!= null) exportLine[2] = customer.getCust_name(); // Customer Name
				if (customer.getCustomer_type()!= null) exportLine[3] = customer.getCustomer_type(); // Customer Type
				if (customer.getCo_tel_no()!= null) exportLine[4] = customer.getCo_tel_no(); // Office Telephone Number
				if (customer.getCo_fax_no()!= null) exportLine[5] = customer.getCo_fax_no(); // Office Fax Number
				if (customer.getHome_tel_no()!= null) exportLine[6] = customer.getHome_tel_no(); // Home Phone Number
				if (customer.getHome_fax_no()!= null) exportLine[7] = customer.getHome_fax_no(); // Home Fax Number
                exportLine[8] = customer.getgbcAccountManager(); // Account Manager
				if (customer.getPc_contact_name()!= null) exportLine[9] = customer.getPc_contact_name(); // Contact Name (PC)
				if (customer.getPc_tel_no()!= null) exportLine[10] = customer.getPc_tel_no(); // Contact No (PC)
				if (customer.getBc_contact_name()!= null) exportLine[11] = customer.getBc_contact_name(); // Contact Name (BC)
				if (customer.getBc_tel_no()!= null) exportLine[12] = customer.getBc_tel_no(); // Contact No (BC)
				if (customer.getTc_contact_name()!= null) exportLine[13] = customer.getTc_contact_name(); // Contact Name (TC)
				if (customer.getTc_tel_no()!= null) exportLine[14] = customer.getTc_tel_no(); // Contact No (TC)
				if (customer.getOc_contact_name()!= null) exportLine[15] = customer.getOc_contact_name(); // Contact Name (OC)
				if (customer.getOc_tel_no()!= null) exportLine[16] = customer.getOc_tel_no(); // Contact No (OC)
				if (customer.getRa_adr_line1()!= null) exportLine[17] = customer.getRa_adr_line1(); // Address Line1 (RA)
				if (customer.getRa_adr_line2()!= null) exportLine[18] = customer.getRa_adr_line2(); // Address Line2 (RA)
				if (customer.getRa_adr_line3()!= null) exportLine[19] = customer.getRa_adr_line3(); // Address Line3 (RA)
				if (customer.getRa_zip_code()!= null) exportLine[20] = customer.getRa_zip_code();   // Zip Code(RA)
				if (customer.getRa_country()!= null) exportLine[21] = customer.getRa_country();     // Country(RA)
				if (customer.getBa_adr_line1()!= null) exportLine[22] = customer.getBa_adr_line1(); // Address Line1 (BA)
				if (customer.getBa_adr_line2()!= null) exportLine[23] = customer.getBa_adr_line2(); // Address Line2 (BA)
				if (customer.getBa_adr_line3()!= null) exportLine[24] = customer.getBa_adr_line3(); // Address Line3 (BA)
				if (customer.getBa_zip_code()!= null) exportLine[25] = customer.getBa_zip_code();   // Zip Code(BA)
				if (customer.getBa_country()!= null) exportLine[26] = customer.getBa_country();     // Country(BA)
				if (customer.getCa_adr_line1()!= null) exportLine[27] = customer.getCa_adr_line1(); // Address Line1 (CA)
				if (customer.getCa_adr_line2()!= null) exportLine[28] = customer.getCa_adr_line2(); // Address Line2 (CA)
				if (customer.getCa_adr_line3()!= null) exportLine[29] = customer.getCa_adr_line3(); // Address Line3 (CA)
				if (customer.getCa_zip_code()!= null) exportLine[30] = customer.getCa_zip_code();   // Zip Code(CA)
				if (customer.getCa_country()!= null) exportLine[31] = customer.getCa_country();     // Country(CA)
				if (customer.getTa_adr_line1()!= null) exportLine[32] = customer.getTa_adr_line1(); // Address Line1 (TA)
				if (customer.getTa_adr_line2()!= null) exportLine[33] = customer.getTa_adr_line2(); // Address Line2 (TA)
				if (customer.getTa_adr_line3()!= null) exportLine[34] = customer.getTa_adr_line3(); // Address Line3 (TA)
				if (customer.getTa_zip_code()!= null) exportLine[35] = customer.getTa_zip_code();   // Zip Code(TA)
				if (customer.getTa_country()!= null) exportLine[36] = customer.getTa_country();     // Country(TA)
				exportList.add(exportLine);
			}
		}
		
		// Export to CSV file
		return exportFile(exportList, fileName);
	}

    /**
     * add percent sign into searching string (Basic Search)
     * @param M_CSTR06BLogicInput inputDto
     */
    private CustomerBean changeInputMap(M_CSTR06BLogicInput inputDto){
        CustomerBean bean = new CustomerBean();
        
        //when searching condition is not null
        if(inputDto.getCust_acc_no()!=null && !String.valueOf(inputDto.getCust_acc_no()).equals(BLANK)){
            //add % condition %
            bean.setCust_acc_no(PERCENT_SIGN + inputDto.getCust_acc_no() + PERCENT_SIGN);
        }
        //when searching condition is not null
        if(inputDto.getCust_name()!=null && !String.valueOf(inputDto.getCust_name()).equals(BLANK)){
            //add % condition %
            bean.setCust_name(PERCENT_SIGN + inputDto.getCust_name() + PERCENT_SIGN);
        }
        //when searching condition is not null
        if(inputDto.getCo_regno()!=null && !String.valueOf(inputDto.getCo_regno()).equals(BLANK)){
            //add % condition %
            bean.setCo_regno(PERCENT_SIGN + inputDto.getCo_regno() + PERCENT_SIGN);
        }
        //when searching condition is not null
        if(inputDto.getCountry()!=null && !String.valueOf(inputDto.getCountry()).equals(BLANK)){
            //add % condition %
            bean.setCountry(PERCENT_SIGN + inputDto.getCountry() + PERCENT_SIGN);
        }
        //when searching condition is not null
        if(inputDto.getCust_id()!=null && !String.valueOf(inputDto.getCust_id()).equals(BLANK)){
            //add % condition %
            bean.setId_cust(PERCENT_SIGN + inputDto.getCust_id() + PERCENT_SIGN);
        } 
        //when searching condition is not null
        if(inputDto.getCust_type()!=null && !String.valueOf(inputDto.getCust_type()).equals(BLANK)){
            //add % condition %
            bean.setCustomerType(inputDto.getCust_type());
        }
        
        return bean;
    }

    /**
     * add percent sign into searching string (Advanced Search)
     * @param M_CSTR06BLogicInput inputDto
     */
    public CustomerBean setCriticalForSearch(M_CSTR06BLogicInput inputDto){
        CustomerBean bean = new CustomerBean();
    
        // Customer name
        if (inputDto.getCustomerName() != null && !inputDto.getCustomerName().equals(BLANK)) {
            bean.setCustomerName(PERCENT_SIGN + inputDto.getCustomerName() + PERCENT_SIGN);
        }
        // Customer Reg. No
        if (inputDto.getCoRegNo() != null && !inputDto.getCoRegNo().equals(BLANK)) {
            bean.setCoRegNo(PERCENT_SIGN + inputDto.getCoRegNo() + PERCENT_SIGN);
        }
        // PepleSoft Acc. No
        if (inputDto.getCustomerAccountNo() != null && !inputDto.getCustomerAccountNo().equals(BLANK)) {
            bean.setCustomerAccountNo(PERCENT_SIGN + inputDto.getCustomerAccountNo() + PERCENT_SIGN);
        }
        // Customer ID
        if (inputDto.getId_cust() != null && !inputDto.getId_cust().equals(BLANK)) {
            bean.setId_cust(PERCENT_SIGN + inputDto.getId_cust() + PERCENT_SIGN);
        }
        // Address
        if (inputDto.getAddress() != null && !inputDto.getAddress().equals(BLANK)) {
            bean.setAddress(PERCENT_SIGN + inputDto.getAddress() + PERCENT_SIGN);
        }
        // Email Address
        if (inputDto.getEmailAddress() != null && !inputDto.getEmailAddress().equals(BLANK)) {
            bean.setEmailAddress(PERCENT_SIGN + inputDto.getEmailAddress()+ PERCENT_SIGN);
        }
        // Zip Code
        if (inputDto.getZipCode() != null && !inputDto.getZipCode().equals(BLANK)) {
            bean.setZipCode(PERCENT_SIGN + inputDto.getZipCode() + PERCENT_SIGN);
        }
        // Contract No.
        if (inputDto.getContactNo() != null && !inputDto.getContactNo().equals(BLANK)) {
            bean.setContactNo(PERCENT_SIGN + inputDto.getContactNo()+ PERCENT_SIGN);
        }
        // Billing Country
        if (inputDto.getBillingCountry() != null && !inputDto.getBillingCountry().equals(BLANK)) {
            bean.setBillingCountry(PERCENT_SIGN + inputDto.getBillingCountry() + PERCENT_SIGN);
        }
        // Contract Person
        if (inputDto.getContactPerson() != null && !inputDto.getContactPerson().equals(BLANK)) {
            bean.setContactPerson(PERCENT_SIGN + inputDto.getContactPerson()+ PERCENT_SIGN);
        }
        // Account Manager
        if (inputDto.getAccountManager() != null && !inputDto.getAccountManager().equals(BLANK)) {
            bean.setAccountManager(inputDto.getAccountManager());
        }
        
        return bean;
    }
	/**
	 * Retrieve CSV header items from RESOURCESMAINT
	 * 
	 * @return CSV header list
	 */
	private String[] initHeadingLine1(){
		String[] headingLine = new String[column];
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("CATEGORY", "HDR");
		parameter.put("RESOURCE_ID", "CST");
        List<String> headNameList = queryDAO.executeForObjectList("SELECT.M_CST.SQL032", parameter);
            
        for (int i = 0; i < headNameList.size(); i++) {
            if (i < column) {
                headingLine[i] = headNameList.get(i);
            }
        }

        return headingLine;
	}
	
	/**
	 * Initialize export item value to empty string
	 * 
	 * @param exportLine
	 * @return
	 */
	private String[] initData(String[] exportLine){
		for (int i = 0; i < exportLine.length; i++) {
			exportLine[i] = "";
		}
		return exportLine;
	}
	
	/**
	 * Write Export data into CSV file.
	 * 
	 * @param exportList export data
	 * @param fileName full file name
	 * @return generated file
	 */
	public File exportFile(List<String[]> exportList,String fileName) {
		FileWriter fwriter = null;
		CSVWriter csvWriter = null;
		try {
			File file =  new File(fileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			fwriter = new FileWriter(fileName, false);
			csvWriter = new CSVWriter(fwriter,',','\"');
			csvWriter.writeAll(exportList);	
			return file;
		} catch (IOException e) {
			e.printStackTrace();
			throw new FileImportExportException("Cannot export file.");
		}
		finally {
			try {
				csvWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}	
}