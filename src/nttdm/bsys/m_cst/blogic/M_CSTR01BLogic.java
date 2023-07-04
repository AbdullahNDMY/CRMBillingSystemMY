/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_CST
 * SERVICE NAME   : M_CST01
 * FUNCTION       : Set display Result of searching 
 * FILE NAME      : M_CSTR01BLogic.java
 *
 * Copyright (C) 2011 NTTDATA Corporation
 * 
 *  History
 * 2011/09/13 [Hoang Duong] Update
**********************************************************/
package nttdm.bsys.m_cst.blogic;

import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.m_cst.bean.CustomerBean;
import nttdm.bsys.m_cst.common.Util;
import nttdm.bsys.m_cst.dto.M_CSTR01Input;
import nttdm.bsys.m_cst.dto.M_CSTR01Output;

import org.apache.struts.action.ActionMessages;

/**
 * M_CSTR01BLogic.class<br>
 * <ul>
 * <li>customer searching blogic</li>
 * </ul>
 * <br>
 *  * @author NTT Data Vietnam	
 *   @version 1.1
 *
 */
public class M_CSTR01BLogic extends AbstractM_CSTR01BLogic{

	/**
	 * <div>queryDAO</div>
	 */
	private QueryDAO queryDAO = null;
	
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
	private static final String SQL_SEARCH_CUSTOMER = "SELECT.M_CST.SQL001";
	/**
     * <div>SQL_SEARCH_CUSTOMER FOR ADVANCED SEARCH</div>
     */
	private static final String SQL_SEARCH_CUSTOMER_ADVANCED = "SELECT.M_CST.SQL015";
	
	public static final String SQL_ACCESS_TYPE = "SELECT.M_CST.SQL017";
	
	public static final String SQL_SUB_MODULE = "SELECT.M_CST.SQL020";
	
	private static final String SQL_DISPLAY_BUTTONS = "SELECT.M_CST.SQL018";
	/**
	 * <div>SQL_GET_CUSTOMER_COUNT FOR BASIC SEARCH</div>
	 */
	private static final String SQL_GET_CUSTOMER_COUNT = "SELECT.M_CST.SQL002";
	/**
     * <div>SQL_GET_CUSTOMER_COUNT FOR ADVANCED SEARCH</div>
     */
	private static final String SQL_GET_CUSTOMER_COUNT_ADVANCED = "SELECT.M_CST.SQL016";

    private static final String SQL_SELECT_ACCOUNTMANAGER = "SELECT.M_CST.SQL022";
	/**
	 * <div>Keep submitted event</div>
	 */
	private static final String FORWARD_SEARCH = "forward_search";

	private static final String ADVANCED_FORWARD_SEARCH = "advancedSearch";
	
	private static final String BASIC_FORWARD_SEARCH = "basicSearch";
	
	/**
	 * <div>Keep submitted event</div>
	 */	
	private static final String FORWARD_DELETE = "forward_delete";
	
	/**
	 * <div>Keep submitted event</div>
	 */	
	private static final String FORWARD_SAVE = "forward_save";
	
	/**
	 * get queryDAO
	 * 
	 * @return queryDAO
	 */
	public QueryDAO getQueryDAO() {
		return queryDAO;
	}
	
	/**
	 * set queryDAO
	 * 
	 * @param queryDAO
	 */
	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}

	
	/**
	 * execute customer searching blogic
	 * @param inputDto
	 * @return BLogicResult
	 */
	public BLogicResult execute(M_CSTR01Input inputDto) {
		//in case be back from s02
//		if(inputDto.getEvent()!=null && !String.valueOf(inputDto.getEvent()).equals(M_CSTR01BLogic.FORWARD_SEARCH)){
//			inputDto.setCust_acc_no(inputDto.getTemp_cust_acc_no());
//			inputDto.setCust_name(inputDto.getTemp_cust_name());
//			inputDto.setCo_regno(inputDto.getTemp_co_regno());
//			inputDto.setCountry(inputDto.getTemp_country());
//		}
		
		//declare output blogic for Basic Search
		M_CSTR01Output outputDto = new M_CSTR01Output();
		this.setOutputDTO(inputDto, outputDto);
		
		//get start index for paging
		Integer startIndex = inputDto.getStartIndex();
		if("1".equals(inputDto.getClickSearchButton())){
		    startIndex = 0;
        }

        this.changeInputMap(inputDto);
        //set total items from searching result  
        outputDto.setTotalCount(this.getCustomerCount(inputDto));
        if (outputDto.getTotalCount() < startIndex){
            startIndex = 0;
        }
        
		//get the number of row for paging 0
		BillingSystemSettings systemSetting = new BillingSystemSettings(queryDAO);	
		Integer row = systemSetting.getResultRow();
		//Integer row = inputDto.getRow();
		//declare blogic result
		BLogicResult result = new BLogicResult();
		List<CustomerBean> cusBean = null;
		//get searching result from database
		if (inputDto.getSearchType()!= null && inputDto.getSearchType().equals(M_CSTR01BLogic.ADVANCED_FORWARD_SEARCH)){
	        CustomerBean inputBean = new CustomerBean();
	        inputBean.setCriticalForSearch(inputDto);
			cusBean = queryDAO.executeForObjectList(M_CSTR01BLogic.SQL_SEARCH_CUSTOMER_ADVANCED, inputBean,startIndex,row);
		}else{
	        //handle percent sign
			cusBean = queryDAO.executeForObjectList(M_CSTR01BLogic.SQL_SEARCH_CUSTOMER, inputDto,startIndex,row);
		}
		// Handn end For M_CST_r11
/*		
		// Processing save checkbox in Advance Search
		if (inputDto.getSearchType()!= null && inputDto.getSearchType().equals(M_CSTR01BLogic.ADVANCED_FORWARD_SEARCH)) {
			String[] chkCheckExportSaved = inputDto.getChkCheckExport();
			if("1".equals(inputDto.getClickSearchButton())){
				chkCheckExportSaved = new String[]{};
			}
			List<String> tmpList = new ArrayList<String>();
			if(chkCheckExportSaved != null) {
				for(String chk : chkCheckExportSaved) {
					boolean add = true;
					for(CustomerBean cust : cusBean) {
						if(cust.getId_cust().equals(chk)) {
							add = false;
							cust.setChecked(true);
							break;
						}
					}
					if(add)
						tmpList.add(chk);
				}
			}
			if(tmpList.isEmpty()) {
				outputDto.setChkCheckExport(new String[] {});
			}
			else {
				outputDto.setChkCheckExport(tmpList.toArray(new String[0]));
			}
		}
		else {
			// Processing save checkbox in Basic Search
			String[] chkCheckExport1Saved = inputDto.getChkCheckExport1();
			if("1".equals(inputDto.getClickSearchButton())){
				chkCheckExport1Saved = new String[]{};
			}
			List<String> listChk = new ArrayList<String>();
			if(chkCheckExport1Saved != null) {
				for(String chk : chkCheckExport1Saved) {
					boolean add = true;
					for(CustomerBean cust : cusBean) {
						if(cust.getId_cust().equals(chk)) {
							add = false;
							cust.setChecked(true);
							break;
						}
					}
					if(add)
						listChk.add(chk);
				}
			}
			if(listChk.isEmpty()) {
				outputDto.setChkCheckExport1(new String[] {});
			}
			else {
				outputDto.setChkCheckExport1(listChk.toArray(new String[0]));
			}
		}
*/		
		//set searching result to cusBeans			
		outputDto.setCusBeans(cusBean);
		outputDto.setRow(row);
		outputDto.setStartIndex(startIndex);
		if(outputDto.getTotalCount()==0){
		    // info.ERR2SC002
            BLogicMessages msgs = new BLogicMessages();
            BLogicMessage msg = new BLogicMessage("info.ERR2SC002", new Object());
            msgs.add(ActionMessages.GLOBAL_MESSAGE, msg);
            result.setMessages(msgs);
		}
		//outputDto.setChkCheckExport1(inputDto.getChkCheckExport1());
        // display or not display buttons
        this.checkDisplayButtons(inputDto, outputDto);
        this.setAccountManager(inputDto, outputDto);
        this.setAccountManagerString(inputDto, outputDto);
		//set blogic result object
		result.setResultObject(outputDto);
		//set blogic result object
		result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS);
		//return
		
		return result;
	}
	
	/**
	 * Get Total Count Number of Searched Customers
	 * 
	 * @param M_CSTR01Input inputDto
	 * @return Integer count
	 */
	private Integer getCustomerCount(M_CSTR01Input inputDto){
		/* Handn - old version
		//count total items from searching result 
		String count = queryDAO.executeForObject(M_CSTR01BLogic.SQL_GET_CUSTOMER_COUNT, inputDto, String.class);
		*/
		// Handn for M_CST_r11
		//count total items from searching result 
		String count = null;
		if (inputDto.getSearchType()!= null && inputDto.getSearchType().equals(M_CSTR01BLogic.ADVANCED_FORWARD_SEARCH)){
			CustomerBean inputBean = new CustomerBean();
			inputBean.setCriticalForSearch(inputDto);
			count = queryDAO.executeForObject(M_CSTR01BLogic.SQL_GET_CUSTOMER_COUNT_ADVANCED, inputBean, String.class);
		}else{
			count = queryDAO.executeForObject(M_CSTR01BLogic.SQL_GET_CUSTOMER_COUNT, inputDto, String.class);
		}
		// end Handn
		
		//when count is null
		if (count == null || "".equals(count)) {
			//set count=0
			count = "0";
		}
		//return
		return Integer.parseInt(count);
	}
	
	/**
	 * add percent sign into searching string
	 * @param M_CSTR01Input inputDto
	 */
	private void changeInputMap(M_CSTR01Input inputDto){
		//when searching condition is not null
		if(inputDto.getCust_acc_no()!=null && !String.valueOf(inputDto.getCust_acc_no()).equals(M_CSTR01BLogic.BLANK)){
			//add % condition %
			inputDto.setCust_acc_no(M_CSTR01BLogic.PERCENT_SIGN + inputDto.getCust_acc_no() + M_CSTR01BLogic.PERCENT_SIGN);
		}
		//when searching condition is not null
		if(inputDto.getCust_name()!=null && !String.valueOf(inputDto.getCust_name()).equals(M_CSTR01BLogic.BLANK)){
			//add % condition %
			inputDto.setCust_name(M_CSTR01BLogic.PERCENT_SIGN + inputDto.getCust_name() + M_CSTR01BLogic.PERCENT_SIGN);
		}
		//when searching condition is not null
		if(inputDto.getCo_regno()!=null && !String.valueOf(inputDto.getCo_regno()).equals(M_CSTR01BLogic.BLANK)){
			//add % condition %
			inputDto.setCo_regno(M_CSTR01BLogic.PERCENT_SIGN + inputDto.getCo_regno() + M_CSTR01BLogic.PERCENT_SIGN);
		}
		//when searching condition is not null
		if(inputDto.getCountry()!=null && !String.valueOf(inputDto.getCountry()).equals(M_CSTR01BLogic.BLANK)){
			//add % condition %
			inputDto.setCountry(M_CSTR01BLogic.PERCENT_SIGN + inputDto.getCountry() + M_CSTR01BLogic.PERCENT_SIGN);
		}
        //when searching condition is not null
        if(inputDto.getCust_id()!=null && !String.valueOf(inputDto.getCust_id()).equals(M_CSTR01BLogic.BLANK)){
            //add % condition %
            inputDto.setCust_id(M_CSTR01BLogic.PERCENT_SIGN + inputDto.getCust_id() + M_CSTR01BLogic.PERCENT_SIGN);
        }
        //when searching condition is not null
//        if(inputDto.getCust_type()!=null && !String.valueOf(inputDto.getCust_type()).equals(M_CSTR01BLogic.BLANK)){
//            //add % condition %
//            inputDto.setCust_type(M_CSTR01BLogic.PERCENT_SIGN + inputDto.getCust_type() + M_CSTR01BLogic.PERCENT_SIGN);
//        }
	}
	
    /**
     * Handn for M_CST_r11: keep properties value from input
     * 
     * @param input
     * @param output
     */
    private void setOutputDTO(M_CSTR01Input input, M_CSTR01Output output) {
        // set original searching values
        output.setCust_acc_no(input.getCust_acc_no());
        output.setCust_name(input.getCust_name());
        output.setCo_regno(input.getCo_regno());
        output.setCountry(input.getCountry());
        output.setCust_id(input.getCust_id());
        output.setCust_type(input.getCust_type());
        // Advanced Search
        output.setAddress(input.getAddress());
        output.setEmailAddress(input.getEmailAddress());
        output.setContactNo(input.getContactNo());
        output.setContactPerson(input.getContactPerson());
        output.setCoRegNo(input.getCoRegNo());
        output.setCustomerAccountNo(input.getCustomerAccountNo());
        output.setCustomerName(input.getCustomerName());
        output.setZipCode(input.getZipCode());
        output.setOtherReferenceNo(input.getOtherReferenceNo());
        output.setBillingCountry(input.getBillingCountry());
        output.setSearchType(input.getSearchType());
        output.setId_cust(input.getId_cust());
        output.setAccountManager(input.getAccountManager());
    }

	private void checkDisplayButtons(M_CSTR01Input inputDto, M_CSTR01Output outputDto){
		// get ACCESS_TYPE
		String accessType = Util.getAccessType(inputDto.getUvo().getId_user(), queryDAO);
		// get sub module
		// String subModule = Util.checkSubModule(inputDto.getUvo().getId_user(),Util.SUB_MODULE_BI, queryDAO);
		// default of buttons are non-display
		outputDto.setDisplayConsumerCust("0");
		outputDto.setDisplayExport("0");
		outputDto.setAccessType("0");
		// outputDto.setSub_module(subModule);
		// check condition to display of buttons
		if (accessType.equals("2") || accessType.equals("1")){
			outputDto.setAccessType(accessType);
			// get second condition to display of New Consumer Customer & Export button
			// CustomBean bean = queryDAO.executeForObject(M_CSTR01BLogic.SQL_DISPLAY_BUTTONS, inputDto.getUvo().getId_user(), CustomBean.class);
			//if (bean != null && bean.getId_set().equals("SYSTEMBASE") && bean.getSet_seq().equals("1") && bean.getSet_value().equals("SG")){
				outputDto.setDisplayConsumerCust("1");
			//}
			
			if (outputDto.getTotalCount() != null && outputDto.getTotalCount() > 0 ) {
                outputDto.setDisplayExport("1");
			}
		}
		
	}

    /**
     * Get all Account Manager List.
     * @param inputDto
     * @param outputDto
     */
    private void setAccountManager(M_CSTR01Input inputDto, M_CSTR01Output outputDto) {
        List<Map<String, Object>> accountManagerList = queryDAO.executeForMapList(SQL_SELECT_ACCOUNTMANAGER, null);
        outputDto.setAccountManagerList(accountManagerList);
    }
    
    /**
	 * Get all Account Manager String.
	 * @param param
	 * @param outputDTO
	 */
    private void setAccountManagerString(M_CSTR01Input inputDto,
			M_CSTR01Output outputDto) {
	    String accountManagerString = "";
        for(Map<String, Object> map : outputDto.getAccountManagerList()){
        	accountManagerString += CommonUtils.toString(map.get("ACC_MGR_NAME"))+";";
        }
        outputDto.setAccountManagerString(accountManagerString);
	}
}
