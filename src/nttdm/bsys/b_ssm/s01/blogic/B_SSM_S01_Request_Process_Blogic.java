/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : B_SSM
 * SERVICE NAME   : B_SSM_S01
 * FUNCTION       : processing business logic from requests of B_SSM_S01
 * FILE NAME      : B_SSM_S01_Request_Process_Blogic.java
 *
 * Copyright (C) 2011 NTTDATA Corporation
 * 
**********************************************************/

package nttdm.bsys.b_ssm.s01.blogic;

import java.util.HashMap;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.b_ssm.bean.data.BeanDataCache;
import nttdm.bsys.b_ssm.s01.data.B_SSM_S01_FieldSet;
import nttdm.bsys.b_ssm.s01.process.B_SSM_S01_Address_Processor;
import nttdm.bsys.b_ssm.s01.process.B_SSM_S01_CoLocation_Processor;
import nttdm.bsys.b_ssm.s01.process.B_SSM_S01_Email_Processor;
import nttdm.bsys.b_ssm.s01.process.B_SSM_S01_General_Processor;
import nttdm.bsys.b_ssm.s01.process.B_SSM_S01_ISP_Processor;
import nttdm.bsys.b_ssm.utility.BLogicUtils;
import nttdm.bsys.common.util.CommonUtils;

import org.apache.struts.action.ActionMessages;

/**
 * @author NTT Data Vietnam	
 * Class Blogic processing business logic from requests of B_SSM_S01
 * @param <P>
 */
public class B_SSM_S01_Request_Process_Blogic<P> implements BLogic<P> {
	
	// Contants 	
	public static final String STR_OUTPUT_SUCCESS = "success";
	public static final String STR_OUTPUT_FAIL = "fail";
	public static final String DIGIT_PATTERN_REGEX_STR = "\\d+";	
	
	// Privates properties
	private QueryDAO queryDAO;
	private B_SSM_S01_General_Processor generalProcessor = new B_SSM_S01_General_Processor(queryDAO);
	private B_SSM_S01_ISP_Processor iSPProcessor = new B_SSM_S01_ISP_Processor(queryDAO);
	private B_SSM_S01_CoLocation_Processor coLocationProcessor = new B_SSM_S01_CoLocation_Processor(queryDAO);
	private B_SSM_S01_Email_Processor emailProcessor = new B_SSM_S01_Email_Processor(queryDAO);
	private B_SSM_S01_Address_Processor addressProcessor = new B_SSM_S01_Address_Processor(queryDAO);
	private String sessionID = null;
	
	/**
	 * Initialize
	 * @param sessionID 
	 */
	public B_SSM_S01_Request_Process_Blogic(String sessionID) {
		// Initialize processors
	    generalProcessor = new B_SSM_S01_General_Processor(queryDAO);
		iSPProcessor = new B_SSM_S01_ISP_Processor(queryDAO);
		coLocationProcessor = new B_SSM_S01_CoLocation_Processor(queryDAO);
		emailProcessor = new B_SSM_S01_Email_Processor(queryDAO);
		addressProcessor = new B_SSM_S01_Address_Processor(queryDAO);
		// Set sessionID
		this.sessionID = sessionID;
	}
	
	/**
	 * Initialize 
	 */
	public B_SSM_S01_Request_Process_Blogic() {
		// Initialize processors
	    generalProcessor = new B_SSM_S01_General_Processor(queryDAO);
		iSPProcessor = new B_SSM_S01_ISP_Processor(queryDAO);
		coLocationProcessor = new B_SSM_S01_CoLocation_Processor(queryDAO);
		emailProcessor = new B_SSM_S01_Email_Processor(queryDAO);
		addressProcessor = new B_SSM_S01_Address_Processor(queryDAO);
	}
	
	/**
	 * Reset processors
	 */
	public void resetProcessors() {
	    generalProcessor = new B_SSM_S01_General_Processor(queryDAO);
		iSPProcessor = new B_SSM_S01_ISP_Processor(queryDAO);
		coLocationProcessor = new B_SSM_S01_CoLocation_Processor(queryDAO);
		emailProcessor = new B_SSM_S01_Email_Processor(queryDAO);
		addressProcessor = new B_SSM_S01_Address_Processor(queryDAO);
	}

	/* (non-Javadoc)
	 * @see jp.terasoluna.fw.service.thin.BLogic#execute(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public BLogicResult execute(P input) {
		resetProcessors();
		BLogicResult result = new BLogicResult();
		HashMap<String, Object> bLogicOutput = new HashMap<String, Object>();
		String resultStr = "";
		if (input instanceof HashMap) {
			HashMap<String, Object> bLogicInput = (HashMap<String, Object>) input;			
			
			// Copy properties
			BLogicUtils.copyProperties(bLogicOutput, bLogicInput);
			
			// Get TabPosition
			int tabPosition = 0;
			try {
				tabPosition = Integer.parseInt(bLogicInput.get(B_SSM_S01_FieldSet.FIELD_TAB_POSITION).toString());
			} catch(Exception ex) {
				tabPosition = 0;
			}
			
			// Get process mode
			int processMode = 0;
			try {
				processMode = Integer.parseInt(bLogicInput.get(B_SSM_S01_FieldSet.FIELD_PROCESSMODE).toString());
			} catch(Exception ex) {
				processMode = 0;
			}
			
			// Get and set access type
			String accessType = queryDAO.executeForObject("B_SSM_S01.getUserAccess",
												  	   	  bLogicInput,
												  	   	  String.class);
			bLogicOutput.put(B_SSM_S01_FieldSet.FIELD_ACCESSTYPE, accessType);
			
			// Get and set access type
            String bifIsDisplay = queryDAO.executeForObject("B_SSM_S01.getBIFDisplay",
                                                          null,
                                                          String.class);
            bLogicOutput.put("bifIsDisplay", bifIsDisplay);
			
            // Get and set access type
            String exportBtnIsDisplay = queryDAO.executeForObject("B_SSM_S01.getExportDisplay", null,String.class);
            bLogicOutput.put("exportBtnIsDisplay", CommonUtils.toString(exportBtnIsDisplay));
            
			///////////////////////// Map data /////////////////////////////////////////////
			B_SSM_S01_Utils.mapViewData(queryDAO, bLogicInput, bLogicOutput);
			
			///////////////////////// Handling basing on process mode //////////////////////
			// Mode search or export
			if (processMode == B_SSM_S01_FieldSet.PROCESSMODE_SEARCH ||
				processMode == B_SSM_S01_FieldSet.PROCESSMODE_EXPORT) {
				// Handling basing on tabPosition
			    // General Tab
                if (tabPosition == B_SSM_S01_FieldSet.TABPOS_GENERAL) {
                    generalProcessor.handlingGeneralTab(bLogicOutput, bLogicInput, processMode);                
                    
                    if("0".equals(bLogicOutput.get(B_SSM_S01_FieldSet.FIELD_GENERALTOTALROW).toString())){
                        // info.ERR2SC002
                        BLogicMessages msgs = new BLogicMessages();
                        BLogicMessage msg = new BLogicMessage("info.ERR2SC002", new Object());
                        msgs.add(ActionMessages.GLOBAL_MESSAGE, msg);
                        result.setMessages(msgs);
                    }
                }
				// ISP Tab
				if (tabPosition == B_SSM_S01_FieldSet.TABPOS_ISP) {
					iSPProcessor.handlingISPTab(bLogicOutput, bLogicInput, processMode);				
                    
					if("0".equals(bLogicOutput.get(B_SSM_S01_FieldSet.FIELD_TOTALROW).toString())){
                        // info.ERR2SC002
                        BLogicMessages msgs = new BLogicMessages();
                        BLogicMessage msg = new BLogicMessage("info.ERR2SC002", new Object());
                        msgs.add(ActionMessages.GLOBAL_MESSAGE, msg);
                        result.setMessages(msgs);
                    }
				}
				// CoLocation Tab
				if (tabPosition == B_SSM_S01_FieldSet.TABPOS_COLOCATION) {
					coLocationProcessor.handlingCoLocationTab(bLogicOutput, bLogicInput, processMode);
                    
					if("0".equals(bLogicOutput.get(B_SSM_S01_FieldSet.FIELD_COLOCATIONTOTALROW).toString())){
                        // info.ERR2SC002
                        BLogicMessages msgs = new BLogicMessages();
                        BLogicMessage msg = new BLogicMessage("info.ERR2SC002", new Object());
                        msgs.add(ActionMessages.GLOBAL_MESSAGE, msg);
                        result.setMessages(msgs);
                    }
				}
				// Email Tab
				if (tabPosition == B_SSM_S01_FieldSet.TABPOS_EMAIL) {
					emailProcessor.handlingEmailTab(bLogicOutput, bLogicInput, processMode);
				
                    if("0".equals(bLogicOutput.get(B_SSM_S01_FieldSet.FIELD_EMAILTOTALROW).toString())){
                        // info.ERR2SC002
                        BLogicMessages msgs = new BLogicMessages();
                        BLogicMessage msg = new BLogicMessage("info.ERR2SC002", new Object());
                        msgs.add(ActionMessages.GLOBAL_MESSAGE, msg);
                        result.setMessages(msgs);
                    }
				}
				// Address Tab
				if (tabPosition == B_SSM_S01_FieldSet.TABPOS_ADDRESS) {
					addressProcessor.handlingAddressTab(bLogicOutput, bLogicInput, processMode);
				
                    if("0".equals(bLogicOutput.get(B_SSM_S01_FieldSet.FIELD_ADDRESSTOTALROW).toString())){
                        // info.ERR2SC002
                        BLogicMessages msgs = new BLogicMessages();
                        BLogicMessage msg = new BLogicMessage("info.ERR2SC002", new Object());
                        msgs.add(ActionMessages.GLOBAL_MESSAGE, msg);
                        result.setMessages(msgs);
                    }
				}
				
			} 
			
			// Mode page list
			if (processMode == B_SSM_S01_FieldSet.PROCESSMODE_PAGELIST) {
				HashMap<String, Object> previousInput = (HashMap<String, Object>) BeanDataCache.get(sessionID, String.valueOf(tabPosition))!=null?
													    (HashMap<String, Object>) BeanDataCache.get(sessionID, String.valueOf(tabPosition)):
													    bLogicInput;
				int startIndex = getSearchStartIndex(tabPosition, bLogicInput);									    
				// Handling basing on tabPosition
				// General Tab
                if (tabPosition == B_SSM_S01_FieldSet.TABPOS_GENERAL) {
                    previousInput.put(B_SSM_S01_FieldSet.FIELD_GENERALSTARTINDEX, startIndex);                 
                    BLogicUtils.copyProperties(bLogicOutput, previousInput);
                    generalProcessor.handlingGeneralTab(bLogicOutput, previousInput, processMode);
                }
				// ISP Tab
				if (tabPosition == B_SSM_S01_FieldSet.TABPOS_ISP) {
					previousInput.put(B_SSM_S01_FieldSet.FIELD_STARTINDEX, startIndex);					
					BLogicUtils.copyProperties(bLogicOutput, previousInput);
					iSPProcessor.handlingISPTab(bLogicOutput, previousInput, processMode);				
				}
				// CoLocation Tab
				if (tabPosition == B_SSM_S01_FieldSet.TABPOS_COLOCATION) {
					previousInput.put(B_SSM_S01_FieldSet.FIELD_COLOCATIONSTARTINDEX, startIndex);
					BLogicUtils.copyProperties(bLogicOutput, previousInput);
					coLocationProcessor.handlingCoLocationTab(bLogicOutput, previousInput, processMode);
				}
				// Email Tab
				if (tabPosition == B_SSM_S01_FieldSet.TABPOS_EMAIL) {
					previousInput.put(B_SSM_S01_FieldSet.FIELD_EMAILSTARTINDEX, startIndex);
					BLogicUtils.copyProperties(bLogicOutput, previousInput);
					emailProcessor.handlingEmailTab(bLogicOutput, previousInput, processMode);
				}
				// Address Tab
				if (tabPosition == B_SSM_S01_FieldSet.TABPOS_ADDRESS) {
					previousInput.put(B_SSM_S01_FieldSet.FIELD_ADDRESSSTARTINDEX, startIndex);
					BLogicUtils.copyProperties(bLogicOutput, previousInput);
					addressProcessor.handlingAddressTab(bLogicOutput, previousInput, processMode);
				}									    
			}
			
			resultStr = STR_OUTPUT_SUCCESS; 
		} else {
			resultStr = STR_OUTPUT_FAIL;
		}
		
		/****** Assign values to result *************/
		result.setResultString(resultStr);
		result.setResultObject(bLogicOutput);
	
		return result;
	}
	
	/**
	 * Get search start index
	 * @param tabPosition
	 * @param logicInput
	 */
	private int getSearchStartIndex(int tabPosition, HashMap<String, Object> logicInput) {
		int startIndex = 0;
		
		// General Tab
        if (tabPosition == B_SSM_S01_FieldSet.TABPOS_GENERAL) {
            try {
                startIndex = Integer.parseInt(logicInput.get(B_SSM_S01_FieldSet.FIELD_GENERALSTARTINDEX).toString());
            } catch(Exception ex) {
                startIndex = 0;
            }           
        }
		// ISP Tab
		if (tabPosition == B_SSM_S01_FieldSet.TABPOS_ISP) {
			try {
				startIndex = Integer.parseInt(logicInput.get(B_SSM_S01_FieldSet.FIELD_STARTINDEX).toString());
			} catch(Exception ex) {
				startIndex = 0;
			}			
		}
		// CoLocation Tab
		if (tabPosition == B_SSM_S01_FieldSet.TABPOS_COLOCATION) {
			try {
				startIndex = Integer.parseInt(logicInput.get(B_SSM_S01_FieldSet.FIELD_COLOCATIONSTARTINDEX).toString());
			} catch(Exception ex) {
				startIndex = 0;
			}
		}
		// Email Tab
		if (tabPosition == B_SSM_S01_FieldSet.TABPOS_EMAIL) {
			try {
				startIndex = Integer.parseInt(logicInput.get(B_SSM_S01_FieldSet.FIELD_EMAILSTARTINDEX).toString());
			} catch(Exception ex) {
				startIndex = 0;
			}
		}
		// Address Tab
		if (tabPosition == B_SSM_S01_FieldSet.TABPOS_ADDRESS) {
			try {
				startIndex = Integer.parseInt(logicInput.get(B_SSM_S01_FieldSet.FIELD_ADDRESSSTARTINDEX).toString());
			} catch(Exception ex) {
				startIndex = 0;
			}
		}
		return startIndex;
	}

	/**
	 * Set value queryDao
	 * @param queryDao
	 */
	public void setQueryDAO(QueryDAO queryDao) {
		this.queryDAO = queryDao;
	}

	/**
	 * Get queryDAO
	 * @return queryDAO
	 */
	public QueryDAO getQueryDAO() {
		return queryDAO;
	}

	/**
	 * Set ISP Processor
	 * @param iSPProcessor
	 */
	public void setISP_Processor(B_SSM_S01_ISP_Processor iSPProcessor) {
		this.iSPProcessor = iSPProcessor;
	}

	/**
	 * Get ISP Processor
	 * @return iSPProcessor
	 */
	public B_SSM_S01_ISP_Processor getISP_Processor() {
		return iSPProcessor;
	}

	/**
	 * Set colocation processor
	 * @param coLocationProcessor
	 */
	public void setCoLocationProcessor(B_SSM_S01_CoLocation_Processor coLocationProcessor) {
		this.coLocationProcessor = coLocationProcessor;
	}

	/**
	 * Get colocation processor
	 * @return coLocationProcessor
	 */
	public B_SSM_S01_CoLocation_Processor getCoLocationProcessor() {
		return coLocationProcessor;
	}

	/**
	 * Set email processor
	 * @param emailProcessor
	 */
	public void setEmailProcessor(B_SSM_S01_Email_Processor emailProcessor) {
		this.emailProcessor = emailProcessor;
	}

	/**
	 * Get email processor
	 * @return emailProcessor
	 */
	public B_SSM_S01_Email_Processor getEmailProcessor() {
		return emailProcessor;
	}

	/**
	 * Set address processor
	 * @param addressProcessor
	 */
	public void setAddressProcessor(B_SSM_S01_Address_Processor addressProcessor) {
		this.addressProcessor = addressProcessor;
	}

	/**
	 * Get address processor
	 * @return addressProcessor
	 */
	public B_SSM_S01_Address_Processor getAddressProcessor() {
		return addressProcessor;
	}

	/**
	 * Set session id
	 * @param sessionID
	 */
	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

}
