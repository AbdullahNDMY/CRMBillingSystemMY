/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : B_SSM
 * SERVICE NAME   : B_SSM_S02
 * FUNCTION       : Processing business logic from requests of B_SSM_S02e
 * FILE NAME      : B_SSM_S02e_BUtils.java
 *
 * Copyright (C) 2011 NTTDATA Corporation
 * 
**********************************************************/

package nttdm.bsys.b_ssm.s02.blogic;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nttdm.bsys.b_ssm.s02.data.B_SSM_S02_FieldSet;
import nttdm.bsys.b_ssm.utility.BLogicUtils;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

/**
 * @author NTT Data Vietnam 
 * Class Utilities processing business logic from requests of B_SSM_S02e
 * @param <P>
 */
public class B_SSM_S02e_BUtils {

	private static final String MODE_UPDATE = "update";
	private static final String MODE_INSERT = "insert";
	private static final String MODE_DELETE = "delete";
	private static final int MAILACCOUNTBOXSIZE_DEFAULT = 20;

	/**
	 * Save edited data
	 * 
	 * @param queryDAO
	 * @param logicInput
	 * @param logicOutput
	 */
	@SuppressWarnings("unchecked")
	public static void saveEditedData(QueryDAO queryDAO, UpdateDAO updateDAO,
										HashMap<String, Object> logicInput,
										HashMap<String, Object> logicOutput) {
		// Get info id array
		List<Integer> infoIDArray = logicInput.get(B_SSM_S02_FieldSet.FIELD_INFOIDARRAY) != null?
									(List<Integer>)logicInput.get(B_SSM_S02_FieldSet.FIELD_INFOIDARRAY):
									null;
		if (infoIDArray != null) {
			try {
				// General tab
				if (isGeneralTabEnable(infoIDArray)) {
					saveEditedGeneralTabData(queryDAO, updateDAO, logicInput, logicOutput);
				}
	
				// Mail Address tab
				if (isAddressTabEnable(infoIDArray)) {
					saveEditedMailAddressTabData(queryDAO, updateDAO, logicInput, logicOutput);
				}
	
				// Rack Equipment Location tab
				if (isRackEquipmentLocationTabEnable(infoIDArray)) {
					saveEditedRackEquipmentLocationTabData(queryDAO, updateDAO, logicInput, logicOutput);
				}
	
				// IT Contact tab
				if (isITContactTabEnable(infoIDArray)) {
					saveEditedITContactTabData(queryDAO, updateDAO, logicInput, logicOutput);
				}
	
				// Server Info tab
				if (isServerInfoTabEnable(infoIDArray)) {
					saveEditedServerInfoTabData(queryDAO, updateDAO, logicInput, logicOutput);
				}
	
				// Firewall tab
				if (isFirewallTabEnable(infoIDArray)) {
					saveEditedFirewallTabData(queryDAO, updateDAO, logicInput, logicOutput);
				}
	
				// CPE Configuration tab
				if (isCPEConfigurationTabEnable(infoIDArray)) {
					saveEditedCPEConfigurationTabData(queryDAO, updateDAO, logicInput, logicOutput);
				}
	
				// DNS Zone tab
				if (isDNSZoneTabEnable(infoIDArray)) {
					saveEditedZoneTabData(queryDAO, updateDAO, logicInput, logicOutput);
				}
	
				// Teamwork tab
				if (isTeamworkTabEnable(infoIDArray)) {
					saveEditedTeamworkTabData(queryDAO, updateDAO, logicInput, logicOutput);
				}
	
				// Set edited status
				setEditedStatus(logicOutput,B_SSM_S02_FieldSet.STATUS_EDITED_SUCCESS);
			} catch (Exception e) {
				e.printStackTrace();
				// Set edited status
				setEditedStatus(logicOutput, B_SSM_S02_FieldSet.STATUS_EDITED_FAIL);
			}
		}
	}

	/**
	 * Is teamwork tab enable?
	 * @param infoIDArray
	 * @return boolean
	 */
	public static boolean isTeamworkTabEnable(List<Integer> infoIDArray) {
		if (infoIDArray == null) {
			return false;
		}
		return infoIDArray.contains(27);
	}

	/**
	 * Is DNSZone tab enable?
	 * @param infoIDArray
	 * @return boolean
	 */
	public static boolean isDNSZoneTabEnable(List<Integer> infoIDArray) {
		if (infoIDArray == null) {
			return false;
		}
		return infoIDArray.contains(24);
	}

	/**
	 * Is CPEConfiguration tab enable?
	 * @param infoIDArray
	 * @return boolean
	 */
	public static boolean isCPEConfigurationTabEnable(List<Integer> infoIDArray) {
		if (infoIDArray == null) {
			return false;
		}
		return infoIDArray.contains(22) ||
		        infoIDArray.contains(23);
	}

	/**
	 * Is Firewall tab enable?
	 * @param infoIDArray
	 * @return boolean
	 */
	public static boolean isFirewallTabEnable(List<Integer> infoIDArray) {
		if (infoIDArray == null) {
			return false;
		}
		return infoIDArray.contains(25);
	}

	/**
	 * Is ServerInfo tab enable?
	 * @param infoIDArray
	 * @return boolean
	 */
	public static boolean isServerInfoTabEnable(List<Integer> infoIDArray) {
		if (infoIDArray == null) {
			return false;
		}
		return infoIDArray.contains(26);
	}

	/**
	 * Is ITContact tab enable?
	 * @param infoIDArray
	 * @return boolean
	 */
	public static boolean isITContactTabEnable(List<Integer> infoIDArray) {
		if (infoIDArray == null) {
			return false;
		}
		return infoIDArray.contains(14);
	}

	/**
	 * Is RackEquipmentLocation tab enable?
	 * @param infoIDArray
	 * @return boolean
	 */
	public static boolean isRackEquipmentLocationTabEnable(List<Integer> infoIDArray) {
		if (infoIDArray == null) {
			return false;
		}
		return infoIDArray.contains(20);
	}

	/**
	 * Is Address tab enable?
	 * @param infoIDArray
	 * @return
	 */
	public static boolean isAddressTabEnable(List<Integer> infoIDArray) {
		if (infoIDArray == null) {
			return false;
		}
		return infoIDArray.contains(16) ||
				infoIDArray.contains(17);
	}

	/**
	 * Is General tab enable?
	 * @param infoIDArray
	 * @return boolean
	 */
	public static boolean isGeneralTabEnable(List<Integer> infoIDArray) {		
		if (infoIDArray == null) {
			return false;
		}
		return infoIDArray.contains(0) ||
				infoIDArray.contains(1) ||
				infoIDArray.contains(2) ||
				infoIDArray.contains(3) ||
				infoIDArray.contains(4) ||
				infoIDArray.contains(5) ||
				infoIDArray.contains(6) ||
				infoIDArray.contains(7) ||
				infoIDArray.contains(8) ||
				infoIDArray.contains(9) ||
				infoIDArray.contains(10) ||
				infoIDArray.contains(11) ||
				infoIDArray.contains(12) ||
				infoIDArray.contains(13) ||
				infoIDArray.contains(15) ||
				infoIDArray.contains(18) ||
				infoIDArray.contains(19) ||
				infoIDArray.contains(21);
	}

	/**
	 * Set edited status
	 */
	public static void setEditedStatus(HashMap<String, Object> logicOutput, String status) {
		logicOutput.put(B_SSM_S02_FieldSet.FIELD_EDITEDSTATUS, status);
	}

	/**
	 * Save edited zone data
	 * 
	 * @param updateDAO
	 * @param logicInput
	 * @param logicOutput
	 */
	public static void saveEditedZoneTabData(QueryDAO queryDAO,
												UpdateDAO updateDAO, HashMap<String, Object> logicInput,
												HashMap<String, Object> logicOutput) {
		//////////////////////// Evaluate auditID ////////////////////////
		String userID = BLogicUtils.emptyValue(logicInput.get(B_SSM_S02_FieldSet.FIELD_LOGONUSERID), "");
		//Modify Reference and Status
		String customerplanid=logicInput.get("customerPlanID").toString();
		Map<String, Object> mapCustomerPlanH = queryDAO.executeForMap("B_SSM_S02.getCustomerPlanH", customerplanid);
        String planStatus = "";
        if (mapCustomerPlanH!=null) {
            planStatus = CommonUtils.toString(mapCustomerPlanH.get("PLAN_STATUS"));
        }
		int auditID = CommonUtils.auditTrailBegin(userID,
												  BillingSystemConstants.MODULE_B,
												  BillingSystemConstants.SUB_MODULE_B_SSM, customerplanid,
												  planStatus,
												  BillingSystemConstants.AUDIT_TRAIL_EDITED);
		logicInput.put(B_SSM_S02_FieldSet.FIELD_AUDITID, auditID);

		////////////////////// Check and save dns zone info /////////////////
		List<HashMap<String, Object>> dnsZoneInfo = new ArrayList<HashMap<String, Object>>();
		dnsZoneInfo = queryDAO.executeForObjectList("B_SSM_S02.getDNSZoneInformation", logicInput);
		if (dnsZoneInfo.size() > 0) {
			updateDAO.execute("B_SSM_S02.updateDNSZoneInformation", logicInput);
		} else {
			updateDAO.execute("B_SSM_S02.insertDNSZoneInformation", logicInput);
		}

		// Get dns zone id
		int dNSZoneID = 0;
		Integer dNSZoneIDInteger = queryDAO.executeForObject("B_SSM_S02.getDNSZoneID",logicInput, Integer.class);
		if (dNSZoneIDInteger!=null) {
		    dNSZoneID = dNSZoneIDInteger.intValue();
		}
		////////////////////////// Check and save dns zone records /////////////////
		// Get interface dns zone record list		
		List<HashMap<String, Object>> dNSZoneRecords = new ArrayList<HashMap<String, Object>>();
		dNSZoneRecords = queryDAO.executeForObjectList("B_SSM_S02.getDNSZoneRecords", logicInput);
		String[] receivedDNSZoneRecordIDs = logicInput.get(B_SSM_S02_FieldSet.FIELD_DNSZONERECORDIDS) == null ? 
											null: 
											(String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_DNSZONERECORDIDS);				
		if (dNSZoneRecords.size() > 0) {
			for (int i = 0; i < dNSZoneRecords.size(); i++) {
				HashMap<String, Object> dNSZoneRecord = dNSZoneRecords.get(i);
				String recordID = BLogicUtils.emptyValue(dNSZoneRecord.get(B_SSM_S02_FieldSet.FIELD_DNSZONERECORDID), null);
				if (recordID != null) {
					int recordArrayID = BLogicUtils.getIndex(receivedDNSZoneRecordIDs, recordID, 0);
					if (recordArrayID != -1) {						
						// Create params
						HashMap<String, Object> params = new HashMap<String, Object>();
						createDNSZoneRecordParams(params, logicInput, dNSZoneID, recordArrayID, MODE_UPDATE, false);
						// Update record
						updateDAO.execute("B_SSM_S02.updateDNSZoneRecord", params);
					} else {
						// Delete record
						HashMap<String, Object> params = new HashMap<String, Object>();
						params.put(B_SSM_S02_FieldSet.FIELD_DNSZONERECORDID, recordID);
						createDNSZoneRecordParams(params, logicInput, dNSZoneID, recordArrayID, MODE_DELETE, false);					
						updateDAO.execute("B_SSM_S02.updateDNSZoneRecordDeleted", params);
					}
				}
			}
			// Insert record with empty received record id
			for (int i = 0; i < receivedDNSZoneRecordIDs.length; i++) {
				String recordID = receivedDNSZoneRecordIDs[i];
				if (recordID == null || recordID.trim().equals("")) {
					HashMap<String, Object> params = new HashMap<String, Object>();
					createDNSZoneRecordParams(params, logicInput, dNSZoneID, i, MODE_INSERT, false);
					// Insert record
					updateDAO.execute("B_SSM_S02.insertDNSZoneRecord", params);
				}
			}
		} else {
			for (int i = 0; i < receivedDNSZoneRecordIDs.length; i++) {
				HashMap<String, Object> params = new HashMap<String, Object>();
				createDNSZoneRecordParams(params, logicInput, dNSZoneID, i, MODE_INSERT, false);
				// Insert record
				updateDAO.execute("B_SSM_S02.insertDNSZoneRecord", params);
			}
		}
	}

	/**
	 * Create dns zone record params
	 * @param params
	 * @param logicInput
	 * @param zoneID
	 * @param recordArrayID
	 * @param processMode
	 * @param isDeletedRecord
	 */
	private static void createDNSZoneRecordParams(HashMap<String, Object> params, 
													HashMap<String, Object> logicInput,
													int zoneID, 
													int arrayID, 
													String processMode,
													boolean isDeletedRecord) {
		// No deleted status
		if (!isDeletedRecord) {
			// ///////////// Get params from input //////////////
			// Zone ID
			params.put(B_SSM_S02_FieldSet.FIELD_DNSZONEID, zoneID);
			// Mode update
			if (processMode.equals(MODE_INSERT) || processMode.equals(MODE_UPDATE)) {
				String[] recordIDs = logicInput.get(B_SSM_S02_FieldSet.FIELD_DNSZONERECORDIDS) != null ?
									 (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_DNSZONERECORDIDS):
									 null;
				
				String[] domainNames = logicInput.get(B_SSM_S02_FieldSet.FIELD_DNSZONERECORDDOMAINNAMES) != null ?
									   (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_DNSZONERECORDDOMAINNAMES):
									   null;

				String[] types = logicInput.get(B_SSM_S02_FieldSet.FIELD_DNSZONERECORDTYPES) != null ?
								 (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_DNSZONERECORDTYPES): 
								 null;

				String[] iPAddresses = logicInput.get(B_SSM_S02_FieldSet.FIELD_DNSZONERECORDIPADDRESSES) != null ? 
									   (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_DNSZONERECORDIPADDRESSES):
									   null;
										      
		        String[] weights = logicInput.get(B_SSM_S02_FieldSet.FIELD_DNSZONERECORDWEIGHTS) != null ? 
        						   (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_DNSZONERECORDWEIGHTS):
        						   null;					      			 
				
				// DNS zone record ID
				if (processMode.equals(MODE_UPDATE)) {
					params.put(B_SSM_S02_FieldSet.FIELD_DNSZONERECORDID, recordIDs[arrayID].trim());
				}

				// DNS zone name
				params.put(B_SSM_S02_FieldSet.FIELD_DNSZONERECORDDOMAINNAME, domainNames[arrayID].trim());

				// DNS zone type
				params.put(B_SSM_S02_FieldSet.FIELD_DNSZONERECORDTYPE, types[arrayID].trim());
				
				// DNS ip address
				params.put(B_SSM_S02_FieldSet.FIELD_DNSZONERECORDIPADDRESS, iPAddresses[arrayID].trim());
				
				// DNS weight
				params.put(B_SSM_S02_FieldSet.FIELD_DNSZONERECORDWEIGHT, weights[arrayID].trim());
				
				// Is Deleted
				String isDel = "0";
				params.put(B_SSM_S02_FieldSet.FIELD_ISDELELETED, isDel);
			}
			// Mode delete
			if (processMode.equals(MODE_DELETE)) {
				// Is Deleted
				String isDel = "1";
				params.put(B_SSM_S02_FieldSet.FIELD_ISDELELETED, isDel);
			}
		}
		
		// Deleted status
		if (isDeletedRecord) {

		}

		// Logon UserID
		params.put(B_SSM_S02_FieldSet.FIELD_LOGONUSERID,
				   BLogicUtils.emptyValue(logicInput.get(B_SSM_S02_FieldSet.FIELD_LOGONUSERID), ""));

		// AuditID
		params.put(B_SSM_S02_FieldSet.FIELD_AUDITID, 
				   BLogicUtils.emptyValue(logicInput.get(B_SSM_S02_FieldSet.FIELD_AUDITID), ""));
		
	}

	/**
	 * Save edited teamwork tab data
	 * 
	 * @param updateDAO
	 * @param logicInput
	 * @param logicOutput
	 */
	public static void saveEditedTeamworkTabData(QueryDAO queryDAO,
													UpdateDAO updateDAO, HashMap<String, Object> logicInput,
													HashMap<String, Object> logicOutput) {
		String teamworkID = (String) logicInput.get(B_SSM_S02_FieldSet.FIELD_TEAMWORK_ID);
		auditBegin(logicInput);
		@SuppressWarnings("unused")
		int result = 0;
		if ((teamworkID == null) || (teamworkID.equals(""))) {
			result = updateDAO.execute("B_SSM_S02.insertTeamwork", logicInput);
		} else
			result = updateDAO.execute("B_SSM_S02.updateTeamwork", logicInput);
		auditEnd(logicInput);
	}

	/**
	 * Save edited CPE configuration tab data
	 * 
	 * @param updateDAO
	 * @param logicInput
	 * @param logicOutput
	 */
	public static void saveEditedCPEConfigurationTabData(QueryDAO queryDAO,
															UpdateDAO updateDAO, HashMap<String, Object> logicInput,
															HashMap<String, Object> logicOutput) {
		//////////////////////// Evaluate auditID ////////////////////////
		String userID = BLogicUtils.emptyValue(logicInput.get(B_SSM_S02_FieldSet.FIELD_LOGONUSERID), "");
		String customerplanid=logicInput.get("customerPlanID").toString();
		Map<String, Object> mapCustomerPlanH = queryDAO.executeForMap("B_SSM_S02.getCustomerPlanH", customerplanid);
        String planStatus = "";
        if (mapCustomerPlanH!=null) {
            planStatus = CommonUtils.toString(mapCustomerPlanH.get("PLAN_STATUS"));
        }
		int auditID = CommonUtils.auditTrailBegin(userID,
												  BillingSystemConstants.MODULE_B,
												  BillingSystemConstants.SUB_MODULE_B_SSM, customerplanid,
												  planStatus,
												  BillingSystemConstants.AUDIT_TRAIL_EDITED);
		logicInput.put(B_SSM_S02_FieldSet.FIELD_AUDITID, auditID);

		////////////////////// Check and save cpe configuration /////////////////
		// Get cpe info
		List<HashMap<String, Object>> cPEInfo = new ArrayList<HashMap<String, Object>>();
		cPEInfo = queryDAO.executeForObjectList("B_SSM_S02.getCPEConfigurationInformation", logicInput);
		if (cPEInfo != null && cPEInfo.size() > 0) {
			updateDAO.execute("B_SSM_S02.updateCPEConfigurationInformation", logicInput);
		} else {
			updateDAO.execute("B_SSM_S02.insertCPEConfigurationInformation", logicInput);
		}
		
		// Get cpe dIDs	
		List<HashMap<String, Object>> cPEDIDs = new ArrayList<HashMap<String, Object>>();
		cPEDIDs = queryDAO.executeForObjectList("B_SSM_S02.getCPEConfigurationDIDs", logicInput);
		
		// Get cpe hID
		int cPEConfigHID = 0;
		Integer cPEConfigHIDInteger = queryDAO.executeForObject("B_SSM_S02.getCPEConfigurationHID",logicInput, Integer.class);
		if (cPEConfigHIDInteger!=null) {
		    cPEConfigHID = cPEConfigHIDInteger.intValue();
		}
		// Get received cpe dIDs
		String[] receivedCPEDIDs = logicInput.get(B_SSM_S02_FieldSet.FIELD_CPECONFIGURATIONDIDS) == null ? 
								   null: 
								   (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_CPECONFIGURATIONDIDS);
		
		// Process updating cpe config
		if (cPEDIDs.size() > 0) {
			for (int i = 0; i < cPEDIDs.size(); i++) {
				HashMap<String, Object> cPEDID = cPEDIDs.get(i);
				String dID = BLogicUtils.emptyValue(cPEDID.get(B_SSM_S02_FieldSet.FIELD_CPECONFIGURATIONDID), null);
				if (dID != null) {
					int arrayID = BLogicUtils.getIndex(receivedCPEDIDs, dID, 0);
					if (arrayID != -1) {						
						// Create params
						HashMap<String, Object> params = new HashMap<String, Object>();
						createCPEConfigParams(params, logicInput, cPEConfigHID, arrayID + 1, MODE_UPDATE, false);
						// Update record
						updateDAO.execute("B_SSM_S02.updateCPEConfigurationDetails", params);
					} else {
						// Delete record
						HashMap<String, Object> params = new HashMap<String, Object>();
						params.put(B_SSM_S02_FieldSet.FIELD_CPECONFIGURATIONDID, dID);
						createCPEConfigParams(params, logicInput, cPEConfigHID, arrayID + 1, MODE_DELETE, false);					
						updateDAO.execute("B_SSM_S02.updateCPEConfigurationDetailsDeleted", params);
					}
				}
			}
			// Insert cpe config with empty received dID
			for (int i = 0; i < receivedCPEDIDs.length; i++) {
				String dID = receivedCPEDIDs[i];
				if (dID == null || dID.trim().equals("")) {
					HashMap<String, Object> params = new HashMap<String, Object>();
					createCPEConfigParams(params, logicInput, cPEConfigHID, i + 1, MODE_INSERT, false);
					// Insert cpe config
					updateDAO.execute("B_SSM_S02.insertCPEConfigurationDetails", params);
				}
			}
		} else {
			for (int i = 0; i < receivedCPEDIDs.length; i++) {
				HashMap<String, Object> params = new HashMap<String, Object>();
				createCPEConfigParams(params, logicInput, cPEConfigHID, i + 1, MODE_INSERT, false);
				// Insert cpe config
				updateDAO.execute("B_SSM_S02.insertCPEConfigurationDetails", params);
			}
		}
	}

	/**
	 * Create cpe config params
	 */
	private static void createCPEConfigParams(HashMap<String, Object> params,
												HashMap<String, Object> logicInput,
												int configHID, 
												int arrayID,
												String processMode, 
												boolean isDeletedCPE) {
		// No deleted status
		if (!isDeletedCPE) {
			// ///////////// Get params from input //////////////
			// CPE hID
			params.put(B_SSM_S02_FieldSet.FIELD_CPECONFIGURATIONHID, configHID);
			
			// Mode update
			if (processMode.equals(MODE_INSERT) || processMode.equals(MODE_UPDATE)) {
				String[] CPEConfigurationNames = logicInput.get(B_SSM_S02_FieldSet.FIELD_CPECONFIGURATIONNAMES) != null ?
												 (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_CPECONFIGURATIONNAMES):
												 null;
												 
			    String[] CPEDIDs = logicInput.get(B_SSM_S02_FieldSet.FIELD_CPECONFIGURATIONDIDS) != null ?
								   (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_CPECONFIGURATIONDIDS):
								   null;								 
				
				String[] CPEConfigurationISPEdgeRouterIPAddresses = logicInput.get(B_SSM_S02_FieldSet.FIELD_CPECONFIGURATIONISPEDGEROUTERIPADDRESSES) != null ?
																    (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_CPECONFIGURATIONISPEDGEROUTERIPADDRESSES):
																    null;

				String[] CPEConfigurationRouterWANInterfacesIPAddresses = logicInput.get(B_SSM_S02_FieldSet.FIELD_CPECONFIGURATIONROUTERWANINTERFACESIPADDRESSES) != null ?
																		  (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_CPECONFIGURATIONROUTERWANINTERFACESIPADDRESSES): 
																		  null;

				String[] CPEConfigurationNetworkAddressIDs = logicInput.get(B_SSM_S02_FieldSet.FIELD_CPECONFIGURATIONNETWORKADDRESSIDS) != null ? 
														     (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_CPECONFIGURATIONNETWORKADDRESSIDS):
														     null;
										      
		        String[] CPEConfigurationManagedCPEIsUseds = logicInput.get(B_SSM_S02_FieldSet.FIELD_CPECONFIGURATIONMANAGEDCPEISUSEDS) != null ? 
						        						     (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_CPECONFIGURATIONMANAGEDCPEISUSEDS):
						        						     null;		
			    String[] CPEConfigurationCPEModelInstalleds = logicInput.get(B_SSM_S02_FieldSet.FIELD_CPECONFIGURATIONCPEMODELINSTALLEDS) != null ? 
														     (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_CPECONFIGURATIONCPEMODELINSTALLEDS):
														     null;	
			    String[] CPEConfigurationLANs = logicInput.get(B_SSM_S02_FieldSet.FIELD_CPECONFIGURATIONLANS) != null ? 
											    (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_CPECONFIGURATIONLANS):
											    null;	
		        String[] CPEConfigurationWANs = logicInput.get(B_SSM_S02_FieldSet.FIELD_CPECONFIGURATIONWANS) != null ? 
												 (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_CPECONFIGURATIONWANS):
												 null;	
			    String[] CPEConfigurationSerialNumbers = logicInput.get(B_SSM_S02_FieldSet.FIELD_CPECONFIGURATIONSERIALNUMBERS) != null ? 
													     (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_CPECONFIGURATIONSERIALNUMBERS):
													     null;	
			    String[] CPEConfigurationEquipment_ItemsIncludedInManagedCPEPackages = logicInput.get(B_SSM_S02_FieldSet.FIELD_CPECONFIGURATIONEQUIPMENT_ITEMSINCLUDEDINMANAGEDCPEPACKAGES) != null ? 
																				       (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_CPECONFIGURATIONEQUIPMENT_ITEMSINCLUDEDINMANAGEDCPEPACKAGES):
																				       null;	
			    String[] CPEConfigurationCustomerSpecificConfigurationOnRouters = logicInput.get(B_SSM_S02_FieldSet.FIELD_CPECONFIGURATIONCUSTOMERSPECIFICCONFIGURATIONONROUTERS) != null ? 
																			      (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_CPECONFIGURATIONCUSTOMERSPECIFICCONFIGURATIONONROUTERS):
																			      null;					        						           						  
        				            
				// CPE dID
				if (processMode.equals(MODE_UPDATE)) {
					params.put(B_SSM_S02_FieldSet.FIELD_CPECONFIGURATIONDID, CPEDIDs[arrayID - 1].trim());
				}
				
				// CPE Name				
				params.put(B_SSM_S02_FieldSet.FIELD_CPECONFIGURATIONNAME, CPEConfigurationNames[arrayID - 1].trim());
				
				// ISPEdgeRouterIPAddress
				params.put(B_SSM_S02_FieldSet.FIELD_CPECONFIGURATIONISPEDGEROUTERIPADDRESS, 
						   CPEConfigurationISPEdgeRouterIPAddresses[arrayID].trim());
				
				// RouterWanInterfaceIPAddress
				params.put(B_SSM_S02_FieldSet.FIELD_CPECONFIGURATIONROUTERWANINTERFACESIPADDRESS, 
						   CPEConfigurationRouterWANInterfacesIPAddresses[arrayID].trim());

				// NetworkAddress
				params.put(B_SSM_S02_FieldSet.FIELD_CPECONFIGURATIONNETWORKADDRESSID, 
						   CPEConfigurationNetworkAddressIDs[arrayID].trim());
				
				// ManagedCPEIsUsed
				params.put(B_SSM_S02_FieldSet.FIELD_CPECONFIGURATIONMANAGEDCPEISUSED,
						   CPEConfigurationManagedCPEIsUseds[arrayID].trim());
				
				// CPEModelInstalled
				params.put(B_SSM_S02_FieldSet.FIELD_CPECONFIGURATIONCPEMODELINSTALLED, 
						   CPEConfigurationCPEModelInstalleds[arrayID].trim());
				
				// LAN
				params.put(B_SSM_S02_FieldSet.FIELD_CPECONFIGURATIONLAN, 
						   CPEConfigurationLANs[arrayID].trim());
				
				// WAN
				params.put(B_SSM_S02_FieldSet.FIELD_CPECONFIGURATIONWAN, 
						   CPEConfigurationWANs[arrayID].trim());
				
				// Serial number
				params.put(B_SSM_S02_FieldSet.FIELD_CPECONFIGURATIONSERIALNUMBER,
						   CPEConfigurationSerialNumbers[arrayID].trim());
				
				// CPEEquipmentAndItems
				params.put(B_SSM_S02_FieldSet.FIELD_CPECONFIGURATIONEQUIPMENT_ITEMSINCLUDEDINMANAGEDCPEPACKAGE, 
						   CPEConfigurationEquipment_ItemsIncludedInManagedCPEPackages[arrayID].trim());
				
				// CustomerSpecificConfigurationOnRouter
				params.put(B_SSM_S02_FieldSet.FIELD_CPECONFIGURATIONCUSTOMERSPECIFICCONFIGURATIONONROUTER, 
						   CPEConfigurationCustomerSpecificConfigurationOnRouters[arrayID].trim());
				
				// Is deleted
				String isDel = "0";
				params.put(B_SSM_S02_FieldSet.FIELD_ISDELELETED, isDel);
			}
			
			// Mode delete
			if (processMode.equals(MODE_DELETE)) {
				// Is Deleted
				String isDel = "1";
				params.put(B_SSM_S02_FieldSet.FIELD_ISDELELETED, isDel);
			}
		}
		
		// Deleted status
		if (isDeletedCPE) {

		}

		// Logon UserID
		params.put(B_SSM_S02_FieldSet.FIELD_LOGONUSERID,
				   BLogicUtils.emptyValue(logicInput.get(B_SSM_S02_FieldSet.FIELD_LOGONUSERID), ""));

		// AuditID
		params.put(B_SSM_S02_FieldSet.FIELD_AUDITID, 
				   BLogicUtils.emptyValue(logicInput.get(B_SSM_S02_FieldSet.FIELD_AUDITID), ""));
	}

	/**
	 * Save edited firewall tab data
	 * 
	 * @param updateDAO
	 * @param logicInput
	 * @param logicOutput
	 */
	public static void saveEditedFirewallTabData(QueryDAO queryDAO,
												   UpdateDAO updateDAO, HashMap<String, Object> logicInput,
												   HashMap<String, Object> logicOutput) {
		//////////////////////// Evaluate auditID ////////////////////////
		String userID = BLogicUtils.emptyValue(logicInput.get(B_SSM_S02_FieldSet.FIELD_LOGONUSERID), "");
		String customerplanid=logicInput.get("customerPlanID").toString();
		Map<String, Object> mapCustomerPlanH = queryDAO.executeForMap("B_SSM_S02.getCustomerPlanH", customerplanid);
        String planStatus = "";
        if (mapCustomerPlanH!=null) {
            planStatus = CommonUtils.toString(mapCustomerPlanH.get("PLAN_STATUS"));
        }
		int auditID = CommonUtils.auditTrailBegin(userID,
												  BillingSystemConstants.MODULE_B,
												  BillingSystemConstants.SUB_MODULE_B_SSM, customerplanid,
												  planStatus,
												  BillingSystemConstants.AUDIT_TRAIL_EDITED);
		logicInput.put(B_SSM_S02_FieldSet.FIELD_AUDITID, auditID);

		////////////////////// Check and save firewall info /////////////////
		List<HashMap<String, Object>> firewallInfo = new ArrayList<HashMap<String, Object>>();
		firewallInfo = queryDAO.executeForObjectList("B_SSM_S02.getFirewallInformation", logicInput);
		if (firewallInfo.size() > 0) {
			updateDAO.execute("B_SSM_S02.updateFirewallInformation", logicInput);
		} else {
			updateDAO.execute("B_SSM_S02.insertFirewallInformation", logicInput);
		}

		// Get firewall id
		int firewallID = 0;
		Integer firewallIDInteger = queryDAO.executeForObject("B_SSM_S02.getFirewallID",logicInput, Integer.class);
		if (firewallIDInteger!=null) {
		    firewallID = firewallIDInteger.intValue();
		}

		////////////////////////// Check and save firewall interface /////////////////
		// Get interface ip id list		
		List<HashMap<String, Object>> interfaceIPs = new ArrayList<HashMap<String, Object>>();
		interfaceIPs = queryDAO.executeForObjectList("B_SSM_S02.getFirewallInterfaceIPs", logicInput);
		String[] receivedInterfaceIPIDs = logicInput.get(B_SSM_S02_FieldSet.FIELD_FIREWALLINTERFACEIPIDS) == null ? 
														 null: 
														 (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_FIREWALLINTERFACEIPIDS);				
		if (interfaceIPs.size() > 0) {
			for (int i = 0; i < interfaceIPs.size(); i++) {
				HashMap<String, Object> interfaceIP = interfaceIPs.get(i);
				String iPID = BLogicUtils.emptyValue(interfaceIP.get(B_SSM_S02_FieldSet.FIELD_FIREWALLINTERFACEIPID), null);
				if (iPID != null) {
					int iPArrayID = BLogicUtils.getIndex(receivedInterfaceIPIDs, iPID, 0);
					if (iPArrayID != -1) {						
						// Create params
						HashMap<String, Object> params = new HashMap<String, Object>();
						createFirewallInterfaceParams(params, logicInput, firewallID, iPArrayID, MODE_UPDATE, false);
						// Update interface
						updateDAO.execute("B_SSM_S02.updateFirewallInterfaceIP", params);
					} else {
						// Delete interface
						HashMap<String, Object> params = new HashMap<String, Object>();
						params.put(B_SSM_S02_FieldSet.FIELD_FIREWALLINTERFACEIPID, iPID);
						createFirewallInterfaceParams(params, logicInput, firewallID, iPArrayID, MODE_DELETE, false);
						// Insert mail account
						updateDAO.execute("B_SSM_S02.updateFirewallInterfaceIPDeleted", params);
					}
				}
			}
			// Insert interface ip with empty received interface id
			for (int i = 0; i < receivedInterfaceIPIDs.length; i++) {
				String interfaceID = receivedInterfaceIPIDs[i];
				if (interfaceID == null || interfaceID.trim().equals("")) {
					HashMap<String, Object> params = new HashMap<String, Object>();
					createFirewallInterfaceParams(params, logicInput, firewallID, i, MODE_INSERT, false);
					// Insert interface
					updateDAO.execute("B_SSM_S02.insertFirewallInterfaceIP", params);
				}
			}
		} else {
			for (int i = 0; i < receivedInterfaceIPIDs.length; i++) {
				HashMap<String, Object> params = new HashMap<String, Object>();
				createFirewallInterfaceParams(params, logicInput, firewallID, i, MODE_INSERT, false);
				// Insert interface
				updateDAO.execute("B_SSM_S02.insertFirewallInterfaceIP", params);
			}
		}
		
		/////////////////////////// Check and save firewall policy //////////////////////////
		// Get interface ip id list		
		List<HashMap<String, Object>> policies = new ArrayList<HashMap<String, Object>>();
		policies = queryDAO.executeForObjectList("B_SSM_S02.getFirewallPolicies", logicInput);
		String[] receivedPolicyIDs = logicInput.get(B_SSM_S02_FieldSet.FIELD_FIREWALLPOLICYIDS) == null ? 
									 null: 
									 (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_FIREWALLPOLICYIDS);				
		if (policies.size() > 0) {
			for (int i = 0; i < policies.size(); i++) {
				HashMap<String, Object> policy = policies.get(i);
				String policyID = BLogicUtils.emptyValue(policy.get(B_SSM_S02_FieldSet.FIELD_FIREWALLPOLICYID), null);
				if (policyID != null) {
					int policyArrayID = BLogicUtils.getIndex(receivedPolicyIDs, policyID, 0);
					if (policyArrayID != -1) {						
						// Create params
						HashMap<String, Object> params = new HashMap<String, Object>();
						createFirewallPolicyParams(params, logicInput, firewallID, policyArrayID, MODE_UPDATE, false);
						// Update policy
						updateDAO.execute("B_SSM_S02.updateFirewallPolicy", params);
					} else {
						// Delete policy
						HashMap<String, Object> params = new HashMap<String, Object>();
						params.put(B_SSM_S02_FieldSet.FIELD_FIREWALLPOLICYID, policyID);
						createFirewallPolicyParams(params, logicInput, firewallID, policyArrayID, MODE_DELETE, false);
						// Insert policy
						updateDAO.execute("B_SSM_S02.updateFirewallPolicyDeleted", params);
					}
				}
			}
			// Insert policy with empty received policy id
			for (int i = 0; i < receivedPolicyIDs.length; i++) {
				String policyID = receivedPolicyIDs[i];
				if (policyID == null || policyID.trim().equals("")) {
					HashMap<String, Object> params = new HashMap<String, Object>();
					createFirewallPolicyParams(params, logicInput, firewallID, i, MODE_INSERT, false);
					// Insert policy
					updateDAO.execute("B_SSM_S02.insertFirewallPolicy", params);
				}
			}
		} else {
			for (int i = 0; i < receivedPolicyIDs.length; i++) {
				HashMap<String, Object> params = new HashMap<String, Object>();
				createFirewallPolicyParams(params, logicInput, firewallID, i, MODE_INSERT, false);
				// Insert policy
				updateDAO.execute("B_SSM_S02.insertFirewallPolicy", params);
			}
		}

		////////////////////////// End audit //////////////////////////
		CommonUtils.auditTrailEnd(auditID);
	}

	/**
	 * Create firewall policy params
	 * @param params
	 * @param logicInput
	 * @param firewallID
	 * @param policyArrayID
	 * @param processMode
	 * @param isDeletedPolicy
	 */
	private static void createFirewallPolicyParams(HashMap<String, Object> params, 
													 HashMap<String, Object> logicInput,
													 int firewallID, 
													 int arrayID, 
													 String processMode, 
													 boolean isDeletedPolicy) {
		// No deleted status
		if (!isDeletedPolicy) {
			// ///////////// Get params from input //////////////
			// Mail firewall ID
			params.put(B_SSM_S02_FieldSet.FIELD_FIREWALLID, firewallID);
			// Mode update
			if (processMode.equals(MODE_INSERT) || processMode.equals(MODE_UPDATE)) {
				String[] policyIDs = logicInput.get(B_SSM_S02_FieldSet.FIELD_FIREWALLPOLICYIDS) != null ?
									 (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_FIREWALLPOLICYIDS):
									 null;

				String[] policySrcs = logicInput.get(B_SSM_S02_FieldSet.FIELD_FIREWALLPOLICYSOURCES) != null ?
									  (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_FIREWALLPOLICYSOURCES): 
									  null;

				String[] policyDestinations = logicInput.get(B_SSM_S02_FieldSet.FIELD_FIREWALLPOLICYDESTINATIONS) != null ? 
										      (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_FIREWALLPOLICYDESTINATIONS):
										      null;
										      
		        String[] policyProtocols = logicInput.get(B_SSM_S02_FieldSet.FIELD_FIREWALLPOLICYPROTOCOLS) != null ? 
		        						   (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_FIREWALLPOLICYPROTOCOLS):
		        						   null;	
				      
			    String[] policyActions = logicInput.get(B_SSM_S02_FieldSet.FIELD_FIREWALLPOLICYACTIONS) != null ? 
						      		     (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_FIREWALLPOLICYACTIONS):
						      		     null;
						      
				String[] policyRemarks = logicInput.get(B_SSM_S02_FieldSet.FIELD_FIREWALLPOLICYREMARKS) != null ? 
								      	 (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_FIREWALLPOLICYREMARKS):
								      	 null;
				
				// Firewall policy ID
				if (processMode.equals(MODE_UPDATE)) {
					params.put(B_SSM_S02_FieldSet.FIELD_FIREWALLPOLICYID, policyIDs[arrayID].trim());
				}

				// Firewall policy src
				params.put(B_SSM_S02_FieldSet.FIELD_FIREWALLPOLICYSOURCE, policySrcs[arrayID].trim());

				// Firewall policy destination
				params.put(B_SSM_S02_FieldSet.FIELD_FIREWALLPOLICYDESTINATION, policyDestinations[arrayID].trim());
				
				// Firewall policy protocol
				params.put(B_SSM_S02_FieldSet.FIELD_FIREWALLPOLICYPROTOCOL, policyProtocols[arrayID].trim());
				
				// Firewall policy action
				params.put(B_SSM_S02_FieldSet.FIELD_FIREWALLPOLICYACTION, policyActions[arrayID].trim());
				
				// Firewall policy remark
				params.put(B_SSM_S02_FieldSet.FIELD_FIREWALLPOLICYREMARK, policyRemarks[arrayID].trim());

				// Is Deleted
				String isDel = "0";
				params.put(B_SSM_S02_FieldSet.FIELD_ISDELELETED, isDel);
			}
			// Mode delete
			if (processMode.equals(MODE_DELETE)) {
				// Is Deleted
				String isDel = "1";
				params.put(B_SSM_S02_FieldSet.FIELD_ISDELELETED, isDel);
			}
		}
		
		// Deleted status
		if (isDeletedPolicy) {

		}

		// Logon UserID
		params.put(B_SSM_S02_FieldSet.FIELD_LOGONUSERID,
				   BLogicUtils.emptyValue(logicInput.get(B_SSM_S02_FieldSet.FIELD_LOGONUSERID), ""));

		// AuditID
		params.put(B_SSM_S02_FieldSet.FIELD_AUDITID, 
				   BLogicUtils.emptyValue(logicInput.get(B_SSM_S02_FieldSet.FIELD_AUDITID), ""));
	}

	/**
	 * Create firewall interface param
	 * @param params
	 * @param logicInput
	 * @param firewallID
	 * @param arrayID
	 * @param processMode
	 * @param isDeletedAccount
	 */
	private static void createFirewallInterfaceParams(HashMap<String, Object> params, 
														HashMap<String, Object> logicInput,
														int firewallID,
														int arrayID, 
														String processMode,
														boolean isDeletedInterface) {
		// No deleted status
		if (!isDeletedInterface) {
			// ///////////// Get params from input //////////////
			// Mail firewall ID
			params.put(B_SSM_S02_FieldSet.FIELD_FIREWALLID, firewallID);
			// Mode update
			if (processMode.equals(MODE_INSERT) || processMode.equals(MODE_UPDATE)) {
				String[] interfaceIDs = logicInput.get(B_SSM_S02_FieldSet.FIELD_FIREWALLINTERFACEIPIDS) != null ?
										(String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_FIREWALLINTERFACEIPIDS):
										null;

				String[] interfaceIPs = logicInput.get(B_SSM_S02_FieldSet.FIELD_FIREWALLINTERFACEIPS) != null ?
										(String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_FIREWALLINTERFACEIPS): 
										null;

				String[] interfaceZones = logicInput.get(B_SSM_S02_FieldSet.FIELD_FIREWALLINTERFACEZONES) != null ? 
										  (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_FIREWALLINTERFACEZONES):
										  null;
				
				// Firewall interface ID
				if (processMode.equals(MODE_UPDATE)) {
					params.put(B_SSM_S02_FieldSet.FIELD_FIREWALLINTERFACEIPID, interfaceIDs[arrayID].trim());
				}

				// Firewall interface IP
				params.put(B_SSM_S02_FieldSet.FIELD_FIREWALLINTERFACEIP, interfaceIPs[arrayID].trim());

				// Firewall interface zone
				params.put(B_SSM_S02_FieldSet.FIELD_FIREWALLINTERFACEZONE, interfaceZones[arrayID].trim());

				// Is Deleted
				String isDel = "0";
				params.put(B_SSM_S02_FieldSet.FIELD_ISDELELETED, isDel);
			}
			// Mode delete
			if (processMode.equals(MODE_DELETE)) {			
				String isDel = "1";
				params.put(B_SSM_S02_FieldSet.FIELD_ISDELELETED, isDel);
			}
		}
		
		// Deleted status
		if (isDeletedInterface) {

		}

		// Logon UserID
		params.put(B_SSM_S02_FieldSet.FIELD_LOGONUSERID,
				   BLogicUtils.emptyValue(logicInput.get(B_SSM_S02_FieldSet.FIELD_LOGONUSERID), ""));

		// AuditID
		params.put(B_SSM_S02_FieldSet.FIELD_AUDITID, 
				   BLogicUtils.emptyValue(logicInput.get(B_SSM_S02_FieldSet.FIELD_AUDITID), ""));
		
	}

	/**
	 * Save edited server info data
	 * 
	 * @param updateDAO
	 * @param logicInput
	 * @param logicOutput
	 */
	public static void saveEditedServerInfoTabData(QueryDAO queryDAO,
													 UpdateDAO updateDAO, 
													 HashMap<String, Object> logicInput,
													 HashMap<String, Object> logicOutput) {
		// Server info Memo remarks
		Map<String, Object> serverInfoMemoInput = new HashMap<String, Object>();
		String serverHeadID = (String) logicInput.get(B_SSM_S02_FieldSet.FIELD_SERVERINFO_HEAD_ID);
		serverInfoMemoInput.put(B_SSM_S02_FieldSet.FIELD_SERVERINFO_HEAD_ID, serverHeadID);
		serverInfoMemoInput.put(B_SSM_S02_FieldSet.FIELD_SUBSCRIPTIONID, logicInput.get(B_SSM_S02_FieldSet.FIELD_SUBSCRIPTIONID));
		serverInfoMemoInput.put(B_SSM_S02_FieldSet.FIELD_SERVERINFO_MEMO, logicInput.get(B_SSM_S02_FieldSet.FIELD_SERVERINFO_MEMO));
		serverInfoMemoInput.put(B_SSM_S02_FieldSet.FIELD_LOGONUSERID, logicInput.get(B_SSM_S02_FieldSet.FIELD_LOGONUSERID));
		auditBegin(serverInfoMemoInput);
		@SuppressWarnings("unused")
		int result = 0;
		if ((serverHeadID == null) || (serverHeadID.equals(""))) {
			result = updateDAO.execute("B_SSM_S02.insertServerInfoHead", serverInfoMemoInput);
			List<HashMap<String, Object>> serverInfoMemo = new ArrayList<HashMap<String, Object>>();
			serverInfoMemo = queryDAO.executeForObjectList("B_SSM_S02.getServerMemoInfo", logicInput);
			if (serverInfoMemo.size() > 0) {
				serverHeadID = (String) serverInfoMemo.get(0).get(B_SSM_S02_FieldSet.FIELD_SERVERINFO_HEAD_ID);
			}
		} else
			result = updateDAO.execute("B_SSM_S02.updateServerInfoHead", serverInfoMemoInput);
		auditEnd(serverInfoMemoInput);
		String[] serverDetailID = (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_SERVERINFO_DETAIL_ID);
		String[] serverDetailRemoveID = (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_SERVERINFO_DETAIL_REMOVE_ID);
		String[] alias = (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_SERVERINFO_ALIAS);
		String[] name = (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_SERVERINFO_NAME);
		String[] hardware = (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_SERVERINFO_HARDWARE);
		String[] os = (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_SERVERINFO_OS);
		String[] ip = (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_SERVERINFO_IP);
		String[] gateway = (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_SERVERINFO_GATEWAY);
		String[] subnetMask = (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_SERVERINFO_SUBNETMASK);
		String[] mos = (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_SERVERINFO_MOS);
		String[] hostID = (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_SERVERINFO_HOSTID);
		String[] setialNo = (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_SERVERINFO_SERIALNO);		
		String[] userLicense = (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_SERVERINFO_USERLICENSE);
		String[] primaryDNS = (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_SERVERINFO_PRIMARYDNS);
		String[] secondDNS = (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_SERVERINFO_SECONDDNS);
		String[] modelNo = (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_SERVERINFO_MODELNO);
		String[] webHostSpace = (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_SERVERINFO_WEBHOSTSPACE);
		String[] sql = (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_SERVERINFO_SQL);
		String[] sqlDBName = (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_SERVERINFO_SQLDBNAME);
		String[] sqlSize = (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_SERVERINFO_SQLSIZE);
		String[] sqlID = (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_SERVERINFO_SQLID);
		String[] sqlDBPass = (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_SERVERINFO_SQLDBPASS);
		String[] backup = (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_SERVERINFO_BACKUP);
		String[] backupSize = (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_SERVERINFO_BACKUPSIZE);
		String[] monitoring = (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_SERVERINFO_MONITORING);
		String[] installedApp = (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_SERVERINFO_INSTALLEDAPP);		
		
		if (serverDetailID != null) {
			for (int i = 0; i < serverDetailID.length; i++) {
				Map<String, Object> serverDetailInput = new HashMap<String, Object>();
				serverDetailInput.put(B_SSM_S02_FieldSet.FIELD_SERVERINFO_DETAIL_ID, serverDetailID[i]);
				serverDetailInput.put(B_SSM_S02_FieldSet.FIELD_SERVERINFO_HEAD_ID, serverHeadID);
				serverDetailInput.put(B_SSM_S02_FieldSet.FIELD_SERVERINFO_ALIAS, alias[i]);
				serverDetailInput.put(B_SSM_S02_FieldSet.FIELD_SERVERINFO_NAME, name[i]);
				serverDetailInput.put(B_SSM_S02_FieldSet.FIELD_SERVERINFO_HARDWARE, hardware[i]);
				serverDetailInput.put(B_SSM_S02_FieldSet.FIELD_SERVERINFO_OS, os[i]);
				serverDetailInput.put(B_SSM_S02_FieldSet.FIELD_SERVERINFO_IP, ip[i]);
				serverDetailInput.put(B_SSM_S02_FieldSet.FIELD_SERVERINFO_GATEWAY, gateway[i]);
				serverDetailInput.put(B_SSM_S02_FieldSet.FIELD_SERVERINFO_SUBNETMASK, subnetMask[i]);
				serverDetailInput.put(B_SSM_S02_FieldSet.FIELD_SERVERINFO_MOS, mos[i]);
				serverDetailInput.put(B_SSM_S02_FieldSet.FIELD_SERVERINFO_HOSTID, hostID[i]);
				serverDetailInput.put(B_SSM_S02_FieldSet.FIELD_SERVERINFO_SERIALNO, setialNo[i]);
				serverDetailInput.put(B_SSM_S02_FieldSet.FIELD_SERVERINFO_USERLICENSE, userLicense[i]);
				serverDetailInput.put(B_SSM_S02_FieldSet.FIELD_SERVERINFO_PRIMARYDNS, primaryDNS[i]);
				serverDetailInput.put(B_SSM_S02_FieldSet.FIELD_SERVERINFO_SECONDDNS, secondDNS[i]);
				serverDetailInput.put(B_SSM_S02_FieldSet.FIELD_SERVERINFO_MODELNO, modelNo[i]);
				serverDetailInput.put(B_SSM_S02_FieldSet.FIELD_SERVERINFO_WEBHOSTSPACE, webHostSpace[i]);
				serverDetailInput.put(B_SSM_S02_FieldSet.FIELD_SERVERINFO_SQL, sql[i]);
				serverDetailInput.put(B_SSM_S02_FieldSet.FIELD_SERVERINFO_SQLDBNAME, sqlDBName[i]);
				serverDetailInput.put(B_SSM_S02_FieldSet.FIELD_SERVERINFO_SQLSIZE, sqlSize[i]);
				serverDetailInput.put(B_SSM_S02_FieldSet.FIELD_SERVERINFO_SQLID, sqlID[i]);
				serverDetailInput.put(B_SSM_S02_FieldSet.FIELD_SERVERINFO_SQLDBPASS, sqlDBPass[i]);
				serverDetailInput.put(B_SSM_S02_FieldSet.FIELD_SERVERINFO_BACKUP, backup[i]);
				serverDetailInput.put(B_SSM_S02_FieldSet.FIELD_SERVERINFO_BACKUPSIZE, backupSize[i]);
				serverDetailInput.put(B_SSM_S02_FieldSet.FIELD_SERVERINFO_MONITORING, monitoring[i]);
				serverDetailInput.put(B_SSM_S02_FieldSet.FIELD_SERVERINFO_INSTALLEDAPP, installedApp[i]);
				serverDetailInput.put(B_SSM_S02_FieldSet.FIELD_LOGONUSERID, logicInput.get(B_SSM_S02_FieldSet.FIELD_LOGONUSERID));
				auditBegin(serverDetailInput);
				@SuppressWarnings("unused")
				int detailResult = 0;
				if ((serverDetailID[i] == null) || (serverDetailID[i].equals("")))
					detailResult = updateDAO.execute("B_SSM_S02.insertServerInfoDetail", serverDetailInput);
				else
					detailResult = updateDAO.execute("B_SSM_S02.updateServerInfoDetail", serverDetailInput);
				auditEnd(serverDetailInput);
			}
		}
		if (serverDetailRemoveID != null) {
			for (int i = 0; i < serverDetailRemoveID.length; i++) {
				Map<String, Object> serverDetailRemoveInput = new HashMap<String, Object>();
				serverDetailRemoveInput.put(B_SSM_S02_FieldSet.FIELD_SERVERINFO_DETAIL_REMOVE_ID, serverDetailRemoveID[i]);
				serverDetailRemoveInput.put(B_SSM_S02_FieldSet.FIELD_LOGONUSERID,	logicInput.get(B_SSM_S02_FieldSet.FIELD_LOGONUSERID));
				auditBegin(serverDetailRemoveInput);
				@SuppressWarnings("unused")
				int detailRemoveResult = updateDAO.execute("B_SSM_S02.removeServerInfoDetail", serverDetailRemoveInput);
				auditEnd(serverDetailRemoveInput);
			}
		}
	}

	/**
	 * Save edited IT contact data
	 * 
	 * @param updateDAO
	 * @param logicInput
	 * @param logicOutput
	 */
	public static void saveEditedITContactTabData(QueryDAO queryDAO,
													UpdateDAO updateDAO,
													HashMap<String, Object> logicInput,
													HashMap<String, Object> logicOutput) {
		// IT Contact Memo remark
		Map<String, Object> iTContactMemoInput = new HashMap<String, Object>();
		String iTContactID = (String) logicInput.get(B_SSM_S02_FieldSet.FIELD_ITCONTACT_HEAD_ID);
		iTContactMemoInput.put(B_SSM_S02_FieldSet.FIELD_ITCONTACT_HEAD_ID, iTContactID);
		iTContactMemoInput.put(B_SSM_S02_FieldSet.FIELD_SUBSCRIPTIONID, logicInput.get(B_SSM_S02_FieldSet.FIELD_SUBSCRIPTIONID));
		iTContactMemoInput.put(B_SSM_S02_FieldSet.FIELD_ITCONTACT_MEMO, logicInput.get(B_SSM_S02_FieldSet.FIELD_ITCONTACT_MEMO));
		iTContactMemoInput.put(B_SSM_S02_FieldSet.FIELD_LOGONUSERID, logicInput.get(B_SSM_S02_FieldSet.FIELD_LOGONUSERID));
		auditBegin(iTContactMemoInput);
		@SuppressWarnings("unused")
		int result = 0;
		if ((iTContactID == null) || (iTContactID.equals(""))) {
			result = updateDAO.execute("B_SSM_S02.insertITContactHead", iTContactMemoInput);
			List<HashMap<String, Object>> iTContactMemo = new ArrayList<HashMap<String, Object>>();
			iTContactMemo = queryDAO.executeForObjectList("B_SSM_S02.getITContactMemoInfo", logicInput);
			if (iTContactMemo.size() > 0) {
				iTContactID = (String) iTContactMemo.get(0).get(B_SSM_S02_FieldSet.FIELD_ITCONTACT_HEAD_ID);
			}
		} else
			result = updateDAO.execute("B_SSM_S02.updateITContactHead", iTContactMemoInput);
		auditEnd(iTContactMemoInput);
		
		// IT Contact detail
		String[] contactType = (String[])logicInput.get(B_SSM_S02_FieldSet.FIELD_ITCONTACT_DETAIL_TYPE);
		String[] contactName = (String[])logicInput.get(B_SSM_S02_FieldSet.FIELD_ITCONTACT_NAME);
		String[] contactDesination = (String[])logicInput.get(B_SSM_S02_FieldSet.FIELD_ITCONTACT_DESIGNATION);
		String[] contactEmail = (String[])logicInput.get(B_SSM_S02_FieldSet.FIELD_ITCONTACT_EMAIL);
		String[] contactTelno = (String[])logicInput.get(B_SSM_S02_FieldSet.FIELD_ITCONTACT_TELNO);
		String[] contactFaxno = (String[])logicInput.get(B_SSM_S02_FieldSet.FIELD_ITCONTACT_FAXNO);
		Map<String, Object> iTContactDetail = new HashMap<String, Object>();
		iTContactDetail.put(B_SSM_S02_FieldSet.FIELD_ITCONTACT_HEAD_ID, iTContactID);
		if (contactType!=null)
		{
			for (int i=0; i<contactType.length; i++)
			{
				Map<String, Object> contactDetailInput = new HashMap<String, Object>();
				contactDetailInput.put(B_SSM_S02_FieldSet.FIELD_ITCONTACT_HEAD_ID, iTContactID);
				contactDetailInput.put(B_SSM_S02_FieldSet.FIELD_ITCONTACT_DETAIL_TYPE, contactType[i]);
				contactDetailInput.put(B_SSM_S02_FieldSet.FIELD_ITCONTACT_NAME, contactName[i]);
				contactDetailInput.put(B_SSM_S02_FieldSet.FIELD_ITCONTACT_DESIGNATION, contactDesination[i]);
				contactDetailInput.put(B_SSM_S02_FieldSet.FIELD_ITCONTACT_EMAIL, contactEmail[i]);
				contactDetailInput.put(B_SSM_S02_FieldSet.FIELD_ITCONTACT_TELNO, contactTelno[i]);
				contactDetailInput.put(B_SSM_S02_FieldSet.FIELD_ITCONTACT_FAXNO, contactFaxno[i]);
				contactDetailInput.put(B_SSM_S02_FieldSet.FIELD_LOGONUSERID, logicInput.get(B_SSM_S02_FieldSet.FIELD_LOGONUSERID));
				auditBegin(contactDetailInput);
				List<Map<String, Object>> iTContactDetailOutput = queryDAO.executeForObjectList("B_SSM_S02.getITContactDetailInfo", contactDetailInput);
				@SuppressWarnings("unused")
				int updateDetailResult = 0;
				if (iTContactDetailOutput.size() > 0)
					updateDetailResult = updateDAO.execute("B_SSM_S02.updateITContactDetail", contactDetailInput);
				else
					updateDetailResult = updateDAO.execute("B_SSM_S02.insertITContactDetail", contactDetailInput);
				auditEnd(contactDetailInput);
			}
		}
	}

	/**
	 * Save edited rack equipment location data
	 * 
	 * @param updateDAO
	 * @param logicInput
	 * @param logicOutput
	 */
	public static void saveEditedRackEquipmentLocationTabData(QueryDAO queryDAO, 
																UpdateDAO updateDAO,
																HashMap<String, Object> logicInput,
																HashMap<String, Object> logicOutput) {
		// Rack Power Memo remarks
		Map<String, Object> rackMemoInput = new HashMap<String, Object>();
		String rackHeadID = (String) logicInput.get(B_SSM_S02_FieldSet.FIELD_RACKPOWER_HEAD_ID);
		rackMemoInput.put(B_SSM_S02_FieldSet.FIELD_RACKPOWER_HEAD_ID, rackHeadID);
		rackMemoInput.put(B_SSM_S02_FieldSet.FIELD_SUBSCRIPTIONID, logicInput.get(B_SSM_S02_FieldSet.FIELD_SUBSCRIPTIONID));
		rackMemoInput.put(B_SSM_S02_FieldSet.FIELD_RACKPOWER_MEMO, logicInput.get(B_SSM_S02_FieldSet.FIELD_RACKPOWER_MEMO));
		rackMemoInput.put(B_SSM_S02_FieldSet.FIELD_LOGONUSERID, logicInput.get(B_SSM_S02_FieldSet.FIELD_LOGONUSERID));
		auditBegin(rackMemoInput);
		@SuppressWarnings("unused")
		int result = 0;
		if ((rackHeadID == null) || (rackHeadID.equals(""))) {
			result = updateDAO.execute("B_SSM_S02.insertRackPowerHead", rackMemoInput);
			List<HashMap<String, Object>> rackPowerMemo = new ArrayList<HashMap<String, Object>>();
			rackPowerMemo = queryDAO.executeForObjectList("B_SSM_S02.getRackPowerMemoInfo", logicInput);
			if (rackPowerMemo.size() > 0) {
				rackHeadID = (String) rackPowerMemo.get(0).get(B_SSM_S02_FieldSet.FIELD_RACKPOWER_HEAD_ID);
			}
		} else
			result = updateDAO.execute("B_SSM_S02.updateRackPowerHead", rackMemoInput);
		auditEnd(rackMemoInput);
		String[] rackDetailID = (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_RACKPOWER_DETAIL_ID);
		String[] rackDetailRemoveID = (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_RACKPOWER_DETAIL_REMOVE_ID);
		String[] rackDetailNo = (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_RACKPOWER_DETAIL_NO);
		String[] rackDetailMaxPower = (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_RACKPOWER_DETAIL_MAXPOWER);
		String[] rackDetailEquipmentLocation = (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_RACKPOWER_DETAIL_EQUIPMENTLOCATION);
		String[] rackDetaiEquipmentSuite = (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_RACKPOWER_DETAIL_EQUIPMENTSUITE);
		if (rackDetailID != null) {
			for (int i = 0; i < rackDetailID.length; i++) {
				Map<String, Object> rackDetailInput = new HashMap<String, Object>();
				rackDetailInput.put(B_SSM_S02_FieldSet.FIELD_RACKPOWER_DETAIL_ID, rackDetailID[i]);
				rackDetailInput.put(B_SSM_S02_FieldSet.FIELD_RACKPOWER_HEAD_ID, rackHeadID);
				rackDetailInput.put(B_SSM_S02_FieldSet.FIELD_RACKPOWER_DETAIL_NO, rackDetailNo[i]);
				rackDetailInput.put(B_SSM_S02_FieldSet.FIELD_RACKPOWER_DETAIL_MAXPOWER, rackDetailMaxPower[i]);
				rackDetailInput.put(B_SSM_S02_FieldSet.FIELD_RACKPOWER_DETAIL_EQUIPMENTLOCATION, rackDetailEquipmentLocation[i]);
				rackDetailInput.put(B_SSM_S02_FieldSet.FIELD_RACKPOWER_DETAIL_EQUIPMENTSUITE, rackDetaiEquipmentSuite[i]);
				rackDetailInput.put(B_SSM_S02_FieldSet.FIELD_LOGONUSERID, logicInput.get(B_SSM_S02_FieldSet.FIELD_LOGONUSERID));
				auditBegin(rackDetailInput);
				@SuppressWarnings("unused")
				int detailResult = 0;
				if ((rackDetailID[i] == null) || (rackDetailID[i].equals("")))
					detailResult = updateDAO.execute("B_SSM_S02.insertRackPowerDetail", rackDetailInput);
				else
					detailResult = updateDAO.execute("B_SSM_S02.updateRackPowerDetail", rackDetailInput);
				auditEnd(rackDetailInput);
			}
		}
		if (rackDetailRemoveID != null) {
			for (int i = 0; i < rackDetailRemoveID.length; i++) {
				Map<String, Object> rackDetailRemoveInput = new HashMap<String, Object>();
				rackDetailRemoveInput.put(B_SSM_S02_FieldSet.FIELD_RACKPOWER_DETAIL_REMOVE_ID, rackDetailRemoveID[i]);
				rackDetailRemoveInput.put(B_SSM_S02_FieldSet.FIELD_LOGONUSERID,	logicInput.get(B_SSM_S02_FieldSet.FIELD_LOGONUSERID));
				auditBegin(rackDetailRemoveInput);
				@SuppressWarnings("unused")
				int detailRemoveResult = updateDAO.execute("B_SSM_S02.removeRackPowerDetail", rackDetailRemoveInput);
				auditEnd(rackDetailRemoveInput);
			}
		}
	}

	/**
	 * Save edited data
	 * 
	 * @param updateDAO
	 * @param logicInput
	 * @param logicOutput
	 */
	public static void saveEditedMailAddressTabData(QueryDAO queryDAO,
													  UpdateDAO updateDAO, 
													  HashMap<String, Object> logicInput,
													  HashMap<String, Object> logicOutput) {
		///////////////////////// Evaluate auditID ////////////////////
		String userID = BLogicUtils.emptyValue(logicInput.get(B_SSM_S02_FieldSet.FIELD_LOGONUSERID), "");
		String customerplanid=logicInput.get("customerPlanID").toString();
		Map<String, Object> mapCustomerPlanH = queryDAO.executeForMap("B_SSM_S02.getCustomerPlanH", customerplanid);
        String planStatus = "";
        if (mapCustomerPlanH!=null) {
            planStatus = CommonUtils.toString(mapCustomerPlanH.get("PLAN_STATUS"));
        }
		int auditID = CommonUtils.auditTrailBegin(userID,
												  BillingSystemConstants.MODULE_B,
												  BillingSystemConstants.SUB_MODULE_B_SSM, customerplanid, planStatus,
												  BillingSystemConstants.AUDIT_TRAIL_EDITED);
		logicInput.put(B_SSM_S02_FieldSet.FIELD_AUDITID, auditID);

		//////////////////////// Check and save mail address /////////////////////
		List<HashMap<String, Object>> mailServerInfo = new ArrayList<HashMap<String, Object>>();
		mailServerInfo = queryDAO.executeForObjectList("B_SSM_S02.getMailServerInformation", logicInput);
		HashMap<String, Object> params = new HashMap<String, Object>();
		createMailAddressParams(params, logicInput);
		if (mailServerInfo.size() > 0) {
			updateDAO.execute("B_SSM_S02.updateMailAddressInformation", params);
		} else {
			updateDAO.execute("B_SSM_S02.insertMailAddressInformation", params);
		}		
		///////////////////////////// Check and save Auto Update Quantity  /////////////////////
		String[] mailAccountTotalArr = logicInput.get(B_SSM_S02_FieldSet.FIELD_MAILACCOUNTIDS) == null ? 
		                                null: (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_MAILACCOUNTIDS);
		//checkVirusScans
		String[] checkVirusScans = logicInput.get(B_SSM_S02_FieldSet.FIELD_MAILACCOUNTVIRUSSCANTEXTS) != null ? 
                (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_MAILACCOUNTVIRUSSCANTEXTS): null;
        //checkAntiSpams
        String[] checkAntiSpams = logicInput.get(B_SSM_S02_FieldSet.FIELD_MAILACCOUNTANTISPAMTEXTS) != null ? 
                       (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_MAILACCOUNTANTISPAMTEXTS): null;
        //checkJunkMngt
        String[] checkJunkMngts = logicInput.get(B_SSM_S02_FieldSet.FIELD_MAILACCOUNTJUNKMANAGEMENTTEXTS) != null ?
                      (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_MAILACCOUNTJUNKMANAGEMENTTEXTS): null;
        //Mail Box(Qty) 
        String[] mailAccountAddMailBoxSizes = logicInput.get(B_SSM_S02_FieldSet.FIELD_MAILACCOUNTADDMAILBOXSIZES) != null ?
                  (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_MAILACCOUNTADDMAILBOXSIZES):
                  null; 
		int mailAccountTotal = mailAccountTotalArr.length;
		int totalMailQty = 0;
		int totalVirusScan = 0;
		int totalAntiSpam  = 0;
		int totalJunkMgt  = 0;
		for (int i = 0; i < mailAccountTotalArr.length; i++) {
		    String mailBoxQty = CommonUtils.toString(mailAccountAddMailBoxSizes[i]).trim();
            if(!CommonUtils.isEmpty(mailBoxQty)) {
                totalMailQty = totalMailQty + Integer.parseInt(mailBoxQty);
            }
            String isCheckVirusScans = BLogicUtils.emptyValue(checkVirusScans[i].trim(), "0");
            if("1".equals(isCheckVirusScans)) {
                totalVirusScan = totalVirusScan + 1;
            }
            String isCheckAntiSpams = BLogicUtils.emptyValue(checkAntiSpams[i].trim(), "0");
            if("1".equals(isCheckAntiSpams)) {
                totalAntiSpam = totalAntiSpam + 1;
            }
            String isCheckJunkMngts = BLogicUtils.emptyValue(checkJunkMngts[i].trim(), "0");
            if("1".equals(isCheckJunkMngts)) {
                totalJunkMgt = totalJunkMgt + 1;
            }
		}
		
		// get autoMailAcc
        String autoMailAcc = BLogicUtils.emptyValue(logicInput.get(B_SSM_S02_FieldSet.FIELD_MAILAUTOMAILACC), "0");
        DecimalFormat formatter = new DecimalFormat("0.00");
        formatter.setRoundingMode(RoundingMode.HALF_UP);
        
        //Mail Account checked
        if("1".equals(autoMailAcc)){
            // get customerPlanLinkID   
            String additionalOption = BLogicUtils.emptyValue(logicInput.get(B_SSM_S02_FieldSet.FIELD_MAILADDITIONALOPTION), "");
            if(!CommonUtils.isEmpty(additionalOption)) {
                HashMap<String, Object> paramAutoMail = new HashMap<String, Object>();
                paramAutoMail.put("customerPlanLinkID", additionalOption);
                // Retrieve Service Name
                Map<String, Object> custPlanLinkInfo = queryDAO.executeForMap("B_SSM_S02.getServiceNameRecord", additionalOption);
                if (custPlanLinkInfo!=null) {
                    BigDecimal unitPrice = new BigDecimal(CommonUtils.toString(custPlanLinkInfo.get("UNIT_PRICE")));
                    int baseQty = 0;
                    String baseQtyStr = CommonUtils.toString(logicInput.get("baseQty")).trim();
                    if(!CommonUtils.isEmpty(baseQtyStr)) {
                        baseQty = Integer.parseInt(baseQtyStr);
                    }
                    int newQty = mailAccountTotal - baseQty;
                    if (newQty<0) {
                        newQty = 0;
                    }
                    //totalAmount = unitPrice*newQty
                    BigDecimal totalAmount =  unitPrice.multiply(new BigDecimal(newQty));
                    paramAutoMail.put("newQuantity", CommonUtils.toString(newQty));
                    paramAutoMail.put("totalAmount", formatter.format(totalAmount));
                    updateDAO.execute("B_SSM_S02.updateAutoMailAcc", paramAutoMail);
                }
            }
        }
        // get chkMailBoxQty
        String chkMailBoxQty = BLogicUtils.emptyValue(logicInput.get(B_SSM_S02_FieldSet.FIELD_MAILAUTOMAILBOXQTY), "0");
        // get customerPlanLinkID of chkMailBoxQty  
        String custPlanLinkIDMailBoxQty = BLogicUtils.emptyValue(logicInput.get(B_SSM_S02_FieldSet.FIELD_MAILBOXQTY), "");
        
        // get chkVirusScan
        String chkVirusScan = BLogicUtils.emptyValue(logicInput.get(B_SSM_S02_FieldSet.FIELD_MAILAUTOVIRUSSCAN), "0");
        // get customerPlanLinkID for   chkVirusScan
        String custPlanLinkIDVirusScan = BLogicUtils.emptyValue(logicInput.get(B_SSM_S02_FieldSet.FIELD_MAILOPTIONVIRUSSCAN), "");

        // get chkAntiSpam
        String chkAntiSpam = BLogicUtils.emptyValue(logicInput.get(B_SSM_S02_FieldSet.FIELD_MAILAUTOANTISPAM), "0");
        // get customerPlanLinkID for   chkAntiSpam
        String custPlanLinkIDAntiSpam = BLogicUtils.emptyValue(logicInput.get(B_SSM_S02_FieldSet.FIELD_MAILOPTIONANTISPAM), "");
        
        // get chkJunkManageme
        String chkJunkManagement = BLogicUtils.emptyValue(logicInput.get(B_SSM_S02_FieldSet.FIELD_MAILAUTOJUNKMANAGEMENT), "0");
        // get customerPlanLinkID   for chkJunkManageme
        String custPlanLinkIDJunkManagement = BLogicUtils.emptyValue(logicInput.get(B_SSM_S02_FieldSet.FIELD_MAILOPTIONJUNKMANAGEMENT), "");
        
        String [] arrChkMailAcc =  {chkMailBoxQty, chkVirusScan, chkAntiSpam, chkJunkManagement};
        String [] arrOptMailAcc = {custPlanLinkIDMailBoxQty, custPlanLinkIDVirusScan, custPlanLinkIDAntiSpam, custPlanLinkIDJunkManagement};
        int[] arrTotalMailAcc = {totalMailQty, totalVirusScan, totalAntiSpam, totalJunkMgt};
        for(int i = 0; i< arrChkMailAcc.length; i++){
            if("1".equals(arrChkMailAcc[i])) {
                String custPlanLinkID = arrOptMailAcc[i];
                if(!CommonUtils.isEmpty(custPlanLinkID)) {
                    HashMap<String, Object> paramAutoMail = new HashMap<String, Object>();
                    paramAutoMail.put("customerPlanLinkID", custPlanLinkID);
                    // Retrieve Service Name
                    Map<String, Object> custPlanLinkInfo = queryDAO.executeForMap("B_SSM_S02.getServiceNameRecord", custPlanLinkID);
                    if (custPlanLinkInfo!=null) {
                        BigDecimal unitPrice = new BigDecimal(CommonUtils.toString(custPlanLinkInfo.get("UNIT_PRICE")));
                        int newQty = arrTotalMailAcc[i];
                        //totalAmount = unitPrice*newQty
                        BigDecimal totalAmount =  unitPrice.multiply(new BigDecimal(newQty));
                        paramAutoMail.put("newQuantity", CommonUtils.toString(newQty));
                        paramAutoMail.put("totalAmount", formatter.format(totalAmount));
                        updateDAO.execute("B_SSM_S02.updateAutoMailAcc", paramAutoMail);
                    }
                }
            }
        }
		
		// get autoMailAcc
		//String autoMailAcc = BLogicUtils.emptyValue(logicInput.get(B_SSM_S02_FieldSet.FIELD_MAILAUTOMAILACC), "0");
	
//		if(autoMailAcc.equals("1")){
//			
//			// get customerPlanLinkID	
//			String customerPlanLinkID = BLogicUtils.emptyValue(logicInput.get(B_SSM_S02_FieldSet.FIELD_MAILADDITIONALOPTION), "");
//			
//			HashMap<String, Object> paramAutoMail = new HashMap<String, Object>();
//			paramAutoMail.put("customerPlanLinkID", customerPlanLinkID);
//		
//			// Retrieve Service Name
//			String svName = queryDAO.executeForObject("B_SSM_S02.getServiceNameRecord", customerPlanLinkID, String.class);
//			
//			if(svName != null){
//				// Retrieve Unit Price
//				Integer unitPrice = (Integer)queryDAO.executeForObject("B_SSM_S02.getUnitPriceRecord", customerPlanLinkID, Integer.class);
//				// get baseQty
//				Integer baseQty = Integer.parseInt( (CommonUtils.isEmpty(logicInput.get("baseQty")) || CommonUtils.isNull((Object)logicInput.get("baseQty")) ) ? 
//						new String("0") : logicInput.get("baseQty").toString() ); 					
//			
//				// get mailAccountTotal
//				Integer mailAccountTotal = Integer.parseInt( ( CommonUtils.isEmpty(logicInput.get("totalMaillAccount")) || CommonUtils.isNull(logicInput.get("totalMaillAccount"))) ? 
//						new String("0") : logicInput.get("totalMaillAccount").toString()); 
//				
//				boolean isGreater = (mailAccountTotal - baseQty > 0) ? true : false;
//				Integer newQuantity = new Integer(0);
//				Integer totalAmount = new Integer(0);
//				
//				if(isGreater) {							
//					newQuantity = mailAccountTotal - baseQty;
//					paramAutoMail.put("newQuantity", newQuantity.toString());
//					
//					totalAmount = newQuantity * unitPrice;
//					paramAutoMail.put("totalAmount", totalAmount.toString());
//				} else {
//					paramAutoMail.put("newQuantity", newQuantity.toString());
//					paramAutoMail.put("totalAmount", totalAmount.toString());
//				}
//				// update updateAutoMailAcc
//				updateDAO.execute("B_SSM_S02.updateAutoMailAcc", paramAutoMail);
//			}	
//		} else {
//			// get chkMailBoxQty
//			String chkMailBoxQty = BLogicUtils.emptyValue(logicInput.get(B_SSM_S02_FieldSet.FIELD_MAILAUTOMAILBOXQTY), "0");
//			// get customerPlanLinkID of chkMailBoxQty	
//			String custPlanLinkIDMailBoxQty = BLogicUtils.emptyValue(logicInput.get(B_SSM_S02_FieldSet.FIELD_MAILBOXQTY), "");
//			
//			// get chkVirusScan
//			String chkVirusScan = BLogicUtils.emptyValue(logicInput.get(B_SSM_S02_FieldSet.FIELD_MAILAUTOVIRUSSCAN), "0");
//			// get customerPlanLinkID for 	chkVirusScan
//			String custPlanLinkIDVirusScan = BLogicUtils.emptyValue(logicInput.get(B_SSM_S02_FieldSet.FIELD_MAILOPTIONVIRUSSCAN), "");
//
//			// get chkAntiSpam
//			String chkAntiSpam = BLogicUtils.emptyValue(logicInput.get(B_SSM_S02_FieldSet.FIELD_MAILAUTOANTISPAM), "0");
//			// get customerPlanLinkID for 	chkAntiSpam
//			String custPlanLinkIDAntiSpam = BLogicUtils.emptyValue(logicInput.get(B_SSM_S02_FieldSet.FIELD_MAILOPTIONANTISPAM), "");
//			
//			// get chkJunkManageme
//			String chkJunkManagement = BLogicUtils.emptyValue(logicInput.get(B_SSM_S02_FieldSet.FIELD_MAILAUTOJUNKMANAGEMENT), "0");
//			// get customerPlanLinkID	for chkJunkManageme
//			String custPlanLinkIDJunkManagement = BLogicUtils.emptyValue(logicInput.get(B_SSM_S02_FieldSet.FIELD_MAILOPTIONJUNKMANAGEMENT), "");
//			
//			String [] arrChkMailAcc =  {chkMailBoxQty, chkVirusScan, chkAntiSpam, chkJunkManagement};
//			String [] arrOptMailAcc = {custPlanLinkIDMailBoxQty, custPlanLinkIDVirusScan, custPlanLinkIDAntiSpam, custPlanLinkIDJunkManagement};
//			for(int i = 0; i< arrChkMailAcc.length; i++){
//				if(arrChkMailAcc[i].equals("1")){
//					HashMap<String, Object> paramAutoMail = new HashMap<String, Object>();
//					paramAutoMail.put("customerPlanLinkID", arrOptMailAcc[i]);
//					
//					// Retrieve Service Name
//					String svName = queryDAO.executeForObject("B_SSM_S02.getServiceNameRecord", arrOptMailAcc[i], String.class);
//					
//					if(svName != null){
//						// Retrieve Unit Price
//						Integer unitPrice = (Integer)queryDAO.executeForObject("B_SSM_S02.getUnitPriceRecord", arrOptMailAcc[i], Integer.class);
//						// get baseQty
//						Integer baseQty = Integer.parseInt( (CommonUtils.isEmpty(logicInput.get("baseQty")) || CommonUtils.isNull((Object)logicInput.get("baseQty")) ) ? 
//													new String("0") : logicInput.get("baseQty").toString() ); 					
//						// get mailAccountTotal
//						Integer mailAccountTotal = Integer.parseInt( ( CommonUtils.isEmpty(logicInput.get("totalMaillAccount")) || CommonUtils.isNull(logicInput.get("totalMaillAccount"))) ? 
//													new String("0") : logicInput.get("totalMaillAccount").toString()); 
//						
//						boolean isGreater = (mailAccountTotal - baseQty > 0) ? true : false;
//						Integer newQuantity = new Integer(0);
//						Integer totalAmount = new Integer(0);
//						
//						if(isGreater) {							
//							newQuantity = mailAccountTotal - baseQty;
//							paramAutoMail.put("newQuantity", newQuantity.toString());
//							
//							totalAmount = newQuantity * unitPrice;
//							paramAutoMail.put("totalAmount", totalAmount.toString());
//						} else {
//							paramAutoMail.put("newQuantity", newQuantity.toString());
//							paramAutoMail.put("totalAmount", totalAmount.toString());
//						}
//						// update updateAutoMailAcc
//						updateDAO.execute("B_SSM_S02.updateAutoMailAcc", paramAutoMail);
//					}
//				}
//			}
//		}
		
		///////////////////////////// Check and save mail accounts /////////////////////	
		// Get mail id
        int mailID = 0;
        Integer mailIDInteger = queryDAO.executeForObject("B_SSM_S02.getMailAddressID",logicInput, Integer.class);
        if (mailIDInteger!=null) {
            mailID = mailIDInteger.intValue();
        }

		// Get mail account list and deleted list 
		List<HashMap<String, Object>> mailAccountsDeleted = new ArrayList<HashMap<String, Object>>();
		mailAccountsDeleted = queryDAO.executeForObjectList("B_SSM_S02.getMailAccountsDeleted", logicInput);
		String[] mailDeletedReceivedIDs = logicInput.get(B_SSM_S02_FieldSet.FIELD_MAILACCOUNTDELETEDIDS) == null ? 
										  null: 
										  (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_MAILACCOUNTDELETEDIDS);
		List<HashMap<String, Object>> mailAccounts = new ArrayList<HashMap<String, Object>>();
		mailAccounts = queryDAO.executeForObjectList("B_SSM_S02.getMailAccounts", logicInput);
		String[] receivedMailAccountIDs = logicInput.get(B_SSM_S02_FieldSet.FIELD_MAILACCOUNTIDS) == null ? 
														 null: 
														 (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_MAILACCOUNTIDS);
		String[] mailAccountNames = logicInput.get(B_SSM_S02_FieldSet.FIELD_MAILACCOUNTNAMES) != null ? 
									(String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_MAILACCOUNTNAMES):
									null;
		if (mailAccounts.size() > 0) {
			for (int i = 0; i < mailAccounts.size(); i++) {
				HashMap<String, Object> mailAccount = mailAccounts.get(i);
				String mailAccID = BLogicUtils.emptyValue(mailAccount.get(B_SSM_S02_FieldSet.FIELD_MAILACCOUNTID), null);
				if (mailAccID != null) {
					int mailReceivedArrayID = BLogicUtils.getIndex(receivedMailAccountIDs, mailAccID, 0);
					if (mailReceivedArrayID != -1) {
						//String mailReceivedID = receivedMailAccountIDs[mailReceivedArrayID];
						// Create params
						params = new HashMap<String, Object>();
						createMailAccountParams(params, logicInput, mailID, mailReceivedArrayID, MODE_UPDATE, false);
						// Update mail account
						updateDAO.execute("B_SSM_S02.updateMailAccount", params);
					} else {
						// Delete mail account
						params = new HashMap<String, Object>();
						params.put(B_SSM_S02_FieldSet.FIELD_MAILACCOUNTID, mailAccID);
						createMailAccountParams(params, logicInput, mailID, mailReceivedArrayID, MODE_DELETE, false);
						// Insert mail account
						updateDAO.execute("B_SSM_S02.updateMailAccountDeleted", params);
					}
				}
			}
			// Insert mail account with empty received mail account id
			for (int i = 0; i < receivedMailAccountIDs.length; i++) {
				String accID = receivedMailAccountIDs[i];
				if (accID == null || accID.trim().equals("")) {
					params = new HashMap<String, Object>();
					createMailAccountParams(params, logicInput, mailID, i, MODE_INSERT, false);
					// Insert mail account
					updateDAO.execute("B_SSM_S02.insertMailAccount", params);
				}
			}
		} else {
			for (int i = 0; i < mailAccountNames.length; i++) {
				params = new HashMap<String, Object>();
				createMailAccountParams(params, logicInput, mailID, i, MODE_INSERT, false);
				// Insert mail account
				updateDAO.execute("B_SSM_S02.insertMailAccount", params);
			}
		}

		////////////////////////////// Check and save deleted mails	////////////////////////////	
		if (mailAccountsDeleted.size() > 0) {
			for (int i = 0; i < mailAccountsDeleted.size(); i++) {
				HashMap<String, Object> mailAccount = mailAccountsDeleted.get(i);
				String deletedMailAccID = BLogicUtils.emptyValue(mailAccount.get(B_SSM_S02_FieldSet.FIELD_MAILACCOUNTID), null);
				if (deletedMailAccID != null) {
					int mailDelReceivedArrayID = BLogicUtils.getIndex(mailDeletedReceivedIDs, deletedMailAccID, 0);
					if (mailDelReceivedArrayID == -1) {
						// Create params
						params = new HashMap<String, Object>();
						params.put(B_SSM_S02_FieldSet.FIELD_MAILACCOUNTID, deletedMailAccID);
						createMailAccountParams(params, logicInput, mailID, mailDelReceivedArrayID, MODE_UPDATE, true);
						// Update mail account deleted
						updateDAO.execute("B_SSM_S02.updateMailAccountDeletedWithNoDisplay", params);
					}
				}
			}
		}

		// End audit
		CommonUtils.auditTrailEnd(auditID);
	}

	/**
	 * Create mail address params
	 * @param params
	 * @param logicInput
	 */
	private static void createMailAddressParams(HashMap<String, Object> params,
												   HashMap<String, Object> logicInput) {
		// SubscriptionID
		params.put(B_SSM_S02_FieldSet.FIELD_SUBSCRIPTIONID, 
				   BLogicUtils.emptyValue(logicInput.get(B_SSM_S02_FieldSet.FIELD_SUBSCRIPTIONID), ""));
		// DomainName
		params.put(B_SSM_S02_FieldSet.FIELD_MAILDOMAINNAME, 
				   BLogicUtils.emptyValue(logicInput.get(B_SSM_S02_FieldSet.FIELD_MAILDOMAINNAME), ""));
		
		// POPServerName
		params.put(B_SSM_S02_FieldSet.FIELD_MAILPOPSERVERNAME, 
				   BLogicUtils.emptyValue(logicInput.get(B_SSM_S02_FieldSet.FIELD_MAILPOPSERVERNAME), ""));
		
		// SMTPServerName
		params.put(B_SSM_S02_FieldSet.FIELD_MAILSMTPSERVERNAME, 
				   BLogicUtils.emptyValue(logicInput.get(B_SSM_S02_FieldSet.FIELD_MAILSMTPSERVERNAME), ""));
		
		// AutoMailAcc
		params.put(B_SSM_S02_FieldSet.FIELD_MAILAUTOMAILACC, 
				   BLogicUtils.emptyValue(logicInput.get(B_SSM_S02_FieldSet.FIELD_MAILAUTOMAILACC), "0"));
		
		// AutoMailBoxQty
		params.put(B_SSM_S02_FieldSet.FIELD_MAILAUTOMAILBOXQTY, 
				   BLogicUtils.emptyValue(logicInput.get(B_SSM_S02_FieldSet.FIELD_MAILAUTOMAILBOXQTY), "0"));
		
		// AutoVirusScan
		params.put(B_SSM_S02_FieldSet.FIELD_MAILAUTOVIRUSSCAN, 
				   BLogicUtils.emptyValue(logicInput.get(B_SSM_S02_FieldSet.FIELD_MAILAUTOVIRUSSCAN), "0"));
		
		// AutoAntiSpam
		params.put(B_SSM_S02_FieldSet.FIELD_MAILAUTOANTISPAM, 
				   BLogicUtils.emptyValue(logicInput.get(B_SSM_S02_FieldSet.FIELD_MAILAUTOANTISPAM), "0"));
		
		// AutoJunkManagement
		params.put(B_SSM_S02_FieldSet.FIELD_MAILAUTOJUNKMANAGEMENT, 
				   BLogicUtils.emptyValue(logicInput.get(B_SSM_S02_FieldSet.FIELD_MAILAUTOJUNKMANAGEMENT), "0"));
		
		// MailAccount
		params.put(B_SSM_S02_FieldSet.FIELD_MAILACCOUNT, 
				   BLogicUtils.emptyValue(logicInput.get(B_SSM_S02_FieldSet.FIELD_MAILACCOUNT), ""));
		
		// BaseQty
		params.put(B_SSM_S02_FieldSet.FIELD_MAILBASEQTY, 
				   BLogicUtils.emptyValue(logicInput.get(B_SSM_S02_FieldSet.FIELD_MAILBASEQTY), ""));
		
		// AdditionalOption
		params.put(B_SSM_S02_FieldSet.FIELD_MAILADDITIONALOPTION, 
				   BLogicUtils.emptyValue(logicInput.get(B_SSM_S02_FieldSet.FIELD_MAILADDITIONALOPTION), ""));
		
		// MailBoxQty
		params.put(B_SSM_S02_FieldSet.FIELD_MAILBOXQTY, 
				   BLogicUtils.emptyValue(logicInput.get(B_SSM_S02_FieldSet.FIELD_MAILBOXQTY), ""));
		
		// OptionVirusScan
		params.put(B_SSM_S02_FieldSet.FIELD_MAILOPTIONVIRUSSCAN, 
				   BLogicUtils.emptyValue(logicInput.get(B_SSM_S02_FieldSet.FIELD_MAILOPTIONVIRUSSCAN), ""));
		
		// OptionAntiSpam
		params.put(B_SSM_S02_FieldSet.FIELD_MAILOPTIONANTISPAM, 
				   BLogicUtils.emptyValue(logicInput.get(B_SSM_S02_FieldSet.FIELD_MAILOPTIONANTISPAM), ""));
		
		// OptionJunkManagement
		params.put(B_SSM_S02_FieldSet.FIELD_MAILOPTIONJUNKMANAGEMENT, 
				   BLogicUtils.emptyValue(logicInput.get(B_SSM_S02_FieldSet.FIELD_MAILOPTIONJUNKMANAGEMENT), ""));
		
		// MailAddressMemoRemarks
		params.put(B_SSM_S02_FieldSet.FIELD_MAILADDRESSMEMOREMARKS, 
				   BLogicUtils.emptyValue(logicInput.get(B_SSM_S02_FieldSet.FIELD_MAILADDRESSMEMOREMARKS), ""));
		
		// LogonUserID
		params.put(B_SSM_S02_FieldSet.FIELD_LOGONUSERID, 
				   BLogicUtils.emptyValue(logicInput.get(B_SSM_S02_FieldSet.FIELD_LOGONUSERID), ""));
		
		// AuditID
		params.put(B_SSM_S02_FieldSet.FIELD_AUDITID, 
				   BLogicUtils.emptyValue(logicInput.get(B_SSM_S02_FieldSet.FIELD_AUDITID), ""));
		
	    // webmailURL
        params.put(B_SSM_S02_FieldSet.FILD_WEBMAILURL, 
                   BLogicUtils.emptyValue(logicInput.get(B_SSM_S02_FieldSet.FILD_WEBMAILURL), ""));
	}

	/**
	 * Create params of mail account
	 * 
	 * @param params
	 * @param logicInput
	 * @param b
	 * @param mode_update2
	 * @param mailReceivedArrayID
	 * @param mailReceivedArrayID2
	 */
	private static void createMailAccountParams(HashMap<String, Object> params,
												   HashMap<String, Object> logicInput,
												   int mailID, 
												   int arrayID,
												   String processMode,
												   boolean isDeletedAccount) {
		// No deleted status
		if (!isDeletedAccount) {
			/////////////// Get params from input //////////////
			// Mail Address ID
			params.put(B_SSM_S02_FieldSet.FIELD_MAILADDRESSID, mailID);
			// Mode update
			if (processMode.equals(MODE_INSERT) || processMode.equals(MODE_UPDATE)) {
				String[] mailAccountIDs = logicInput.get(B_SSM_S02_FieldSet.FIELD_MAILACCOUNTIDS) != null ?
										  (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_MAILACCOUNTIDS):
										  null;

				String[] mailAccountNames = logicInput.get(B_SSM_S02_FieldSet.FIELD_MAILACCOUNTNAMES) != null ?
											(String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_MAILACCOUNTNAMES): 
											null;

				String[] mailAccountPasswords = logicInput.get(B_SSM_S02_FieldSet.FIELD_MAILACCOUNTPASSWORDS) != null ? 
												(String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_MAILACCOUNTPASSWORDS):
												null;
				
				String[] popAccountNames = logicInput.get(B_SSM_S02_FieldSet.FIELD_MAILPOPACCOUNTNAMES) != null ?
											(String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_MAILPOPACCOUNTNAMES): 
											null;								

				String[] mailAccountQtys = logicInput.get(B_SSM_S02_FieldSet.FIELD_MAILACCOUNTQTYS) != null ?
										   (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_MAILACCOUNTQTYS):
										   null;
			    String[] mailAccountAddMailBoxSizes = logicInput.get(B_SSM_S02_FieldSet.FIELD_MAILACCOUNTADDMAILBOXSIZES) != null ?
													  (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_MAILACCOUNTADDMAILBOXSIZES):
													  null;						   				

				String[] checkVirusScans = logicInput.get(B_SSM_S02_FieldSet.FIELD_MAILACCOUNTVIRUSSCANTEXTS) != null ? 
										   (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_MAILACCOUNTVIRUSSCANTEXTS):
										   null;

				String[] checkAntiSpams = logicInput.get(B_SSM_S02_FieldSet.FIELD_MAILACCOUNTANTISPAMTEXTS) != null ? 
										  (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_MAILACCOUNTANTISPAMTEXTS):
										  null;

				String[] checkJunkMngt = logicInput.get(B_SSM_S02_FieldSet.FIELD_MAILACCOUNTJUNKMANAGEMENTTEXTS) != null ?
									     (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_MAILACCOUNTJUNKMANAGEMENTTEXTS):
									     null;

				// Mail Account ID
				if (processMode.equals(MODE_UPDATE)) {
					params.put(B_SSM_S02_FieldSet.FIELD_MAILACCOUNTID, mailAccountIDs[arrayID].trim());
				}

				// Mail Account Name
				params.put(B_SSM_S02_FieldSet.FIELD_MAILACCOUNTNAME, mailAccountNames[arrayID].trim());

				// Mail Account Password
				params.put(B_SSM_S02_FieldSet.FIELD_MAILACCOUNTPASSWORD, mailAccountPasswords[arrayID].trim());

				// Mail Pop Account Name
				params.put(B_SSM_S02_FieldSet.FIELD_MAILPOPACCOUNTNAME, popAccountNames[arrayID].trim());

				// Mail Account Add Box 
				params.put(B_SSM_S02_FieldSet.FIELD_MAILACCOUNTADDBOXSIZE, mailAccountAddMailBoxSizes[arrayID].trim());

				// Mail Account Add Box Size
//				String mailAccountAddBox = mailAccountAddMailBoxSizes[arrayID];
//				String mailBoxQty = mailAccountAddBox != null && !mailAccountAddBox.trim().equals("") ?
//											String.valueOf(Integer.parseInt(mailAccountAddBox) * 10 + 20):
//											String.valueOf(MAILACCOUNTBOXSIZE_DEFAULT);	
				String mailBoxQty = CommonUtils.toString(mailAccountQtys[arrayID]);
				params.put(B_SSM_S02_FieldSet.FIELD_MAILACCOUNTBOXQTY, mailBoxQty);

				// Mail Account Is Deleted
				String isDel = "0";
				params.put(B_SSM_S02_FieldSet.FIELD_ISDELELETED, isDel);

				// Mail Account Check Virus Scan
				params.put(B_SSM_S02_FieldSet.FIELD_MAILACCOUNTCHECKVIRUSSCAN, BLogicUtils.emptyValue(checkVirusScans[arrayID].trim(), "0"));

				// Mail Account Check Anti Spam
				params.put(B_SSM_S02_FieldSet.FIELD_MAILACCOUNTCHECKANTISPAM, BLogicUtils.emptyValue(checkAntiSpams[arrayID].trim(), "0"));

				// Mail Account Check Junk Management
				params.put(B_SSM_S02_FieldSet.FIELD_MAILACCOUNTCHECKJUNKMANAGEMENT, BLogicUtils.emptyValue(checkJunkMngt[arrayID].trim(), "0"));

			}
			// Mode delete
			if (processMode.equals(MODE_DELETE)) {
				// Mail Account Is Deleted
				String isDel = "1";
				params.put(B_SSM_S02_FieldSet.FIELD_ISDELELETED, isDel);
			}

		}
		// Deleted status
		if (isDeletedAccount) {

		}

		// Logon UserID
		params.put(B_SSM_S02_FieldSet.FIELD_LOGONUSERID,
				   BLogicUtils.emptyValue(logicInput.get(B_SSM_S02_FieldSet.FIELD_LOGONUSERID), ""));

		// AuditID
		params.put(B_SSM_S02_FieldSet.FIELD_AUDITID, 
				   BLogicUtils.emptyValue(logicInput.get(B_SSM_S02_FieldSet.FIELD_AUDITID), ""));
	}

	/**
	 * Save edited data
	 * 
	 * @param updateDAO
	 * @param logicInput
	 * @param logicOutput
	 */
	public static void saveEditedGeneralTabData(QueryDAO queryDAO,
												   UpdateDAO updateDAO, HashMap<String, Object> logicInput,
												   HashMap<String, Object> logicOutput) {
	    
		// Evaluate auditID
		String userID = BLogicUtils.emptyValue(logicInput.get(B_SSM_S02_FieldSet.FIELD_LOGONUSERID), "");
		String customerplanid=logicInput.get("customerPlanID").toString();
		Map<String, Object> mapCustomerPlanH = queryDAO.executeForMap("B_SSM_S02.getCustomerPlanH", customerplanid);
        String planStatus = "";
        if (mapCustomerPlanH!=null) {
            planStatus = CommonUtils.toString(mapCustomerPlanH.get("PLAN_STATUS"));
        }
		int auditID = CommonUtils.auditTrailBegin(userID,
												   BillingSystemConstants.MODULE_B,
												   BillingSystemConstants.SUB_MODULE_B_SSM, customerplanid, planStatus,
												   BillingSystemConstants.AUDIT_TRAIL_EDITED);
		logicInput.put(B_SSM_S02_FieldSet.FIELD_AUDITID, auditID);

		// Router Setting Information & Memo
		updateDAO.execute("B_SSM_S02.updateRouterSettingAndMemoInformation",logicInput);

		// Installation Address
		// Installation Address 1
		int instAddress1Count = 0;
		Integer instAddress1CountInteger = queryDAO.executeForObject("B_SSM_S02.getInstallationAddress1Count", logicInput, Integer.class);
		if (instAddress1CountInteger!=null) {
		    instAddress1Count = instAddress1CountInteger.intValue();
		}
		if (instAddress1Count > 0) {
			updateDAO.execute("B_SSM_S02.updateInstallationAddress1", logicInput);
		} else {
			updateDAO.execute("B_SSM_S02.insertInstallationAddress1", logicInput);
		}

		// Installation Address 2
		int instAddress2Count = 0;
		Integer instAddress2CountInteger = queryDAO.executeForObject("B_SSM_S02.getInstallationAddress2Count", logicInput, Integer.class);
		if (instAddress2CountInteger!=null) {
		    instAddress2Count = instAddress2CountInteger.intValue();
		}
		if (instAddress2Count > 0) {
			updateDAO.execute("B_SSM_S02.updateInstallationAddress2", logicInput);
		} else {
			updateDAO.execute("B_SSM_S02.insertInstallationAddress2", logicInput);
		}

		// FTP Interface Information
		int fTPInterfaceCount = 0;
		Integer fTPInterfaceCountInteger = queryDAO.executeForObject("B_SSM_S02.getFTPInterfaceInformationCount", logicInput, Integer.class);
		if (fTPInterfaceCountInteger!=null) {
		    fTPInterfaceCount = fTPInterfaceCountInteger.intValue();
		}
		if (fTPInterfaceCount > 0) {
			updateDAO.execute("B_SSM_S02.updateFTPInterfaceInformation", logicInput);
		} else {
			updateDAO.execute("B_SSM_S02.insertFTPInterfaceInformation", logicInput);
		}

		// MRTG Monitoring
		int mRTGMonitoringCount = 0;
		Integer mRTGMonitoringCountInteger = queryDAO.executeForObject("B_SSM_S02.getMRTGMonitoringCount", logicInput, Integer.class);
		if (mRTGMonitoringCountInteger!=null) {
		    mRTGMonitoringCount = mRTGMonitoringCountInteger.intValue();
		}
		if (mRTGMonitoringCount > 0) {
			updateDAO.execute("B_SSM_S02.updateMRTGMonitoring", logicInput);
		} else {
			updateDAO.execute("B_SSM_S02.insertMRTGMonitoring", logicInput);
		}
		// End audit
		CommonUtils.auditTrailEnd(auditID);
	}

	/**
	 * Begin Audit Trail
	 */
	private static void auditBegin(Map<String, Object> updateObject) {
		Integer idAudit = CommonUtils.auditTrailBegin((String) updateObject.get(B_SSM_S02_FieldSet.FIELD_LOGONUSERID),
																				BillingSystemConstants.MODULE_M,
																				BillingSystemConstants.SUB_MODULE_B_SSM, "", null,
																				BillingSystemConstants.AUDIT_TRAIL_EDITED);
		updateObject.put(B_SSM_S02_FieldSet.FIELD_AUDITID, idAudit);
	}

	/**
	 * End Audit Trail
	 */
	private static void auditEnd(Map<String, Object> updateObject) {
		CommonUtils.auditTrailEnd((Integer) updateObject
				.get(B_SSM_S02_FieldSet.FIELD_AUDITID), true);
	}

	/**
	 * Update printed date of mail account
	 * @param mailIDs
	 */
	public static void updateMailAccountPrintedDate(QueryDAO queryDAO, UpdateDAO updateDAO, String[] mailIDs) {
		if (mailIDs == null) {
			return;
		}
		
		for (int i = 0; i < mailIDs.length; i++) {
			String mailID = mailIDs[i];
			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put(B_SSM_S02_FieldSet.FIELD_MAILACCOUNTID, mailID);
			updateDAO.execute("B_SSM_S02.updateMailAccountPrintedDate", params);
		}
	}
}
