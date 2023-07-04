/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : B_SSM
 * SERVICE NAME   : B_SSM_S02
 * FUNCTION       : Processing business logic from requests of B_SSM_S02v
 * FILE NAME      : B_SSM_S02v_BUtils.java
 *
 * Copyright (C) 2011 NTTDATA Corporation
 * 
**********************************************************/

package nttdm.bsys.b_ssm.s02.blogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nttdm.bsys.b_cpm_en.dto.B_CPM_CONSTANT;
import nttdm.bsys.b_ssm.s02.data.B_SSM_S02_FieldSet;
import nttdm.bsys.b_ssm.s03.data.B_SSM_S03_FieldSet;
import nttdm.bsys.b_ssm.utility.BLogicUtils;
import nttdm.bsys.common.bean.RAD_USERS_T;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.RadUserTUtil;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

/**
 * @author NTT Data Vietnam 
 * @Class Utilities processing business logic from requests of B_SSM_S02v
 * @param <P>
 */
public class B_SSM_S02v_BUtils {

	/**
	 * Evaluate infoId array
	 * 
	 * @param queryDAO
	 * @param logicInput
	 * @return List<Integer>
	 */
	public static List<Integer> evaluateInfoIDArray(QueryDAO queryDAO, HashMap<String, Object> logicInput) {
		List<Integer> infoIDArray = new ArrayList<Integer>();
		List<HashMap<String, Object>> serviceArrayInfo = new ArrayList<HashMap<String, Object>>();
		serviceArrayInfo = queryDAO.executeForObjectList("B_SSM_S02.getServiceArrayInfo", logicInput);
		
		if (serviceArrayInfo != null && serviceArrayInfo.size() > 0) {
			for (int i = 0; i < serviceArrayInfo.size(); i++) {
				HashMap<String, Object> info = serviceArrayInfo.get(i);
				if (info != null) {
					int infoID = Integer.valueOf(BLogicUtils.emptyValue(info.get(B_SSM_S02_FieldSet.FIELD_DB_INFOID), "-1"));
					// Add infoID into array
					if (infoID != -1) {
						infoIDArray.add(infoID);
					}
				}
			}
		}
		return infoIDArray;
	}

	/**
	 * Get and map view data to output
	 * 
	 * @param logicInput
	 * @param logicOutput
	 */
	public static void mappingViewData(QueryDAO queryDAO,
										 HashMap<String, Object> logicInput,
										 HashMap<String, Object> logicOutput) {
		// General tab
		mappingGeneralTabData(queryDAO, logicInput, logicOutput);

		// Mail Address tab
		mappingMailAddressTabData(queryDAO, logicInput, logicOutput);

		// Rack Equipment Location tab
		mappingRackEquipmentLocationTabData(queryDAO, logicInput, logicOutput);

		// IT Contact tab
		mappingITContactTabData(queryDAO, logicInput, logicOutput);

		// Server Info tab
		mappingServerInfoTabData(queryDAO, logicInput, logicOutput);

		// Firewall tab
		mappingFirewallTabData(queryDAO, logicInput, logicOutput);

		// CPE Configuration tab
		mappingCPEConfigurationTabData(queryDAO, logicInput, logicOutput);

		// DNS Zone tab
		mappingZoneTabData(queryDAO, logicInput, logicOutput);

		// Teamwork tab
		mappingTeamworkTabData(queryDAO, logicInput, logicOutput);
	}

	/**
	 * Get and map teamword view data to output
	 * 
	 * @param queryDAO
	 * @param logicInput
	 * @param logicOutput
	 */
	public static void mappingTeamworkTabData(QueryDAO queryDAO,
												HashMap<String, Object> logicInput,
												HashMap<String, Object> logicOutput) {
		// Get teamwork information with code
		List<HashMap<String, Object>> teamworkInfo = new ArrayList<HashMap<String, Object>>();
		teamworkInfo = queryDAO.executeForObjectList("B_SSM_S02.getTeamworkInfo", logicInput);
		// Add output
		if (teamworkInfo.size() > 0) {
			BLogicUtils.copyProperties(logicOutput, teamworkInfo.get(0));
		}
	}

	/**
	 * Get and map zone view data to output
	 * 
	 * @param queryDAO
	 * @param logicInput
	 * @param logicOutput
	 */
	public static void mappingZoneTabData(QueryDAO queryDAO,
											HashMap<String, Object> logicInput,
											HashMap<String, Object> logicOutput) {
		// DNS zone info & memo
		List<HashMap<String, Object>> zoneInfo = new ArrayList<HashMap<String, Object>>();
		zoneInfo = queryDAO.executeForObjectList("B_SSM_S02.getDNSZoneInformation", logicInput);
		if (zoneInfo.size() > 0) {
			BLogicUtils.copyProperties(logicOutput, zoneInfo.get(0));
		}
		
		// DNS zone record
		List<HashMap<String, Object>> zoneRecords = new ArrayList<HashMap<String, Object>>();
		zoneRecords = queryDAO.executeForObjectList("B_SSM_S02.getDNSZoneRecords", logicInput);
		if (zoneRecords.size() > 0) {
			logicOutput.put(B_SSM_S02_FieldSet.FIELD_DNSZONERECORDLIST, zoneRecords);
		}
	}

	/**
	 * Get and map cpe configuration view data to output
	 * 
	 * @param queryDAO
	 * @param logicInput
	 * @param logicOutput
	 */
	public static void mappingCPEConfigurationTabData(QueryDAO queryDAO,
														HashMap<String, Object> logicInput,
														HashMap<String, Object> logicOutput) {		
		// CPE Info
		List<HashMap<String, Object>> cPEInfo = new ArrayList<HashMap<String, Object>>();
		cPEInfo = queryDAO.executeForObjectList("B_SSM_S02.getCPEConfigurationInformation", logicInput);
		if (cPEInfo != null && cPEInfo.size() > 0) {
			BLogicUtils.copyProperties(logicOutput, cPEInfo.get(0));
		}
		
		// CPE Configuration		
		List<HashMap<String, Object>> cPEDetails = new ArrayList<HashMap<String, Object>>();
		cPEDetails = queryDAO.executeForObjectList("B_SSM_S02.getCPEConfigurationDetails", logicInput);		
		logicOutput.put(B_SSM_S02_FieldSet.FIELD_CPECONFIGURATIONDETAILLIST, cPEDetails);
	}

	

	/**
	 * Get and map firewall view data to output
	 * 
	 * @param queryDAO
	 * @param logicInput
	 * @param logicOutput
	 */
	public static void mappingFirewallTabData(QueryDAO queryDAO,
												HashMap<String, Object> logicInput,
												HashMap<String, Object> logicOutput) {
		// Firewall information & memo
		List<HashMap<String, Object>> firewallInfo = new ArrayList<HashMap<String, Object>>();
		firewallInfo = queryDAO.executeForObjectList("B_SSM_S02.getFirewallInformation", logicInput);
		if (firewallInfo.size() > 0) {
			BLogicUtils.copyProperties(logicOutput, firewallInfo.get(0));
		}
		
		// Interface IP
		List<HashMap<String, Object>> interfaceIPs = new ArrayList<HashMap<String, Object>>();
		interfaceIPs = queryDAO.executeForObjectList("B_SSM_S02.getFirewallInterfaceIPs", logicInput);		
		logicOutput.put(B_SSM_S02_FieldSet.FIELD_FIREWALLINTERFACEIPLIST, interfaceIPs);
		
		// Firewall Policy
		List<HashMap<String, Object>> firewallPolicies = new ArrayList<HashMap<String, Object>>();
		firewallPolicies = queryDAO.executeForObjectList("B_SSM_S02.getFirewallPolicies", logicInput);		
		logicOutput.put(B_SSM_S02_FieldSet.FIELD_FIREWALLPOLICYLIST, firewallPolicies);
		
	}

	/**
	 * Get and map server info view data to output
	 * 
	 * @param queryDAO
	 * @param logicInput
	 * @param logicOutput
	 */
	public static void mappingServerInfoTabData(QueryDAO queryDAO,
												  HashMap<String, Object> logicInput,
												  HashMap<String, Object> logicOutput) {
		// Server Info Memo remarks
		List<HashMap<String, Object>> serverInfoMemoResult = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> serverInfoMemo = new HashMap<String, Object>();
		serverInfoMemoResult = queryDAO.executeForObjectList(
				"B_SSM_S02.getServerMemoInfo", logicInput);
		
		String[] id = new String[0];
		String[] alias = new String[0];
		String[] name = new String[0];
		String[] hardware = new String[0];
		String[] os = new String[0];
		String[] ip = new String[0];
		String[] gateway = new String[0];
		String[] subnetMask = new String[0];
		String[] mos = new String[0];
		String[] hostID = new String[0];
		String[] serialNo = new String[0];
		String[] userLicense = new String[0];
		String[] primaryDNS = new String[0];
		String[] secondDNS = new String[0];
		String[] modelNo = new String[0];
		String[] webHostSpace = new String[0];
		String[] sql = new String[0];
		String[] sqlDBName = new String[0];
		String[] sqlSize = new String[0];
		String[] sqlID = new String[0];
		String[] sqlDBPass = new String[0];
		String[] backup = new String[0];
		String[] backupSize = new String[0];
		String[] monitoring = new String[0];
		String[] installedApp = new String[0];
		
		if (serverInfoMemoResult.size() > 0) {
			// Rack Power detail
			serverInfoMemo = serverInfoMemoResult.get(0);
			Map<String, Object> serverDetailInput = new HashMap<String, Object>();
			serverDetailInput.put(B_SSM_S02_FieldSet.FIELD_SERVERINFO_HEAD_ID, serverInfoMemoResult.get(0).get(B_SSM_S02_FieldSet.FIELD_SERVERINFO_HEAD_ID));
			List<HashMap<String, Object>> serverInfoDetail = new ArrayList<HashMap<String, Object>>();
			serverInfoDetail = queryDAO.executeForObjectList("B_SSM_S02.getServerDetailInfo", serverDetailInput);
			int size = serverInfoDetail.size();
			if (size > 0)
			{
				id = new String[size];
				alias = new String[size];
				name = new String[size];
				hardware = new String[size];
				os = new String[size];
				ip = new String[size];
				gateway = new String[size];
				subnetMask = new String[size];
				mos = new String[size];
				hostID = new String[size];
				serialNo = new String[size];
				userLicense = new String[size];
				primaryDNS = new String[size];
				secondDNS = new String[size];
				modelNo = new String[size];
				webHostSpace = new String[size];
				sql = new String[size];
				sqlDBName = new String[size];
				sqlSize = new String[size];
				sqlID = new String[size];
				sqlDBPass = new String[size];
				backup = new String[size];
				backupSize = new String[size];
				monitoring = new String[size];
				installedApp = new String[size];
				for (int i=0; i<size; i++)
				{
					id[i] = (String)serverInfoDetail.get(i).get(B_SSM_S02_FieldSet.FIELD_SERVERINFO_DETAIL_ID);
					alias[i] = (String)serverInfoDetail.get(i).get(B_SSM_S02_FieldSet.FIELD_SERVERINFO_ALIAS);
					name[i] = (String)serverInfoDetail.get(i).get(B_SSM_S02_FieldSet.FIELD_SERVERINFO_NAME);
					hardware[i] = (String)serverInfoDetail.get(i).get(B_SSM_S02_FieldSet.FIELD_SERVERINFO_HARDWARE);
					os[i] = (String)serverInfoDetail.get(i).get(B_SSM_S02_FieldSet.FIELD_SERVERINFO_OS);
					ip[i] = (String)serverInfoDetail.get(i).get(B_SSM_S02_FieldSet.FIELD_SERVERINFO_IP);
					gateway[i] = (String)serverInfoDetail.get(i).get(B_SSM_S02_FieldSet.FIELD_SERVERINFO_GATEWAY);
					subnetMask[i] = (String)serverInfoDetail.get(i).get(B_SSM_S02_FieldSet.FIELD_SERVERINFO_SUBNETMASK);
					mos[i] = (String)serverInfoDetail.get(i).get(B_SSM_S02_FieldSet.FIELD_SERVERINFO_MOS);
					hostID[i] = (String)serverInfoDetail.get(i).get(B_SSM_S02_FieldSet.FIELD_SERVERINFO_HOSTID);
					serialNo[i] = (String)serverInfoDetail.get(i).get(B_SSM_S02_FieldSet.FIELD_SERVERINFO_SERIALNO);
					userLicense[i] = (String)serverInfoDetail.get(i).get(B_SSM_S02_FieldSet.FIELD_SERVERINFO_USERLICENSE);
					primaryDNS[i] = (String)serverInfoDetail.get(i).get(B_SSM_S02_FieldSet.FIELD_SERVERINFO_PRIMARYDNS);
					secondDNS[i] = (String)serverInfoDetail.get(i).get(B_SSM_S02_FieldSet.FIELD_SERVERINFO_SECONDDNS);
					modelNo[i] = (String)serverInfoDetail.get(i).get(B_SSM_S02_FieldSet.FIELD_SERVERINFO_MODELNO);
					webHostSpace[i] = (String)serverInfoDetail.get(i).get(B_SSM_S02_FieldSet.FIELD_SERVERINFO_WEBHOSTSPACE);
					sql[i] = (String)serverInfoDetail.get(i).get(B_SSM_S02_FieldSet.FIELD_SERVERINFO_SQL);
					sqlDBName[i] = (String)serverInfoDetail.get(i).get(B_SSM_S02_FieldSet.FIELD_SERVERINFO_SQLDBNAME);
					sqlSize[i] = (String)serverInfoDetail.get(i).get(B_SSM_S02_FieldSet.FIELD_SERVERINFO_SQLSIZE);
					sqlID[i] = (String)serverInfoDetail.get(i).get(B_SSM_S02_FieldSet.FIELD_SERVERINFO_SQLID);
					sqlDBPass[i] = (String)serverInfoDetail.get(i).get(B_SSM_S02_FieldSet.FIELD_SERVERINFO_SQLDBPASS);
					backup[i] = (String)serverInfoDetail.get(i).get(B_SSM_S02_FieldSet.FIELD_SERVERINFO_BACKUP);
					backupSize[i] = (String)serverInfoDetail.get(i).get(B_SSM_S02_FieldSet.FIELD_SERVERINFO_BACKUPSIZE);
					monitoring[i] = (String)serverInfoDetail.get(i).get(B_SSM_S02_FieldSet.FIELD_SERVERINFO_MONITORING);
					installedApp[i] = (String)serverInfoDetail.get(i).get(B_SSM_S02_FieldSet.FIELD_SERVERINFO_INSTALLEDAPP);
				}
			}
		}
		BLogicUtils.copyProperties(logicOutput, serverInfoMemo);
		logicOutput.put(B_SSM_S02_FieldSet.FIELD_SERVERINFO_DETAIL_ID, id);
		logicOutput.put(B_SSM_S02_FieldSet.FIELD_SERVERINFO_ALIAS, alias);
		logicOutput.put(B_SSM_S02_FieldSet.FIELD_SERVERINFO_NAME, name);
		logicOutput.put(B_SSM_S02_FieldSet.FIELD_SERVERINFO_HARDWARE, hardware);
		logicOutput.put(B_SSM_S02_FieldSet.FIELD_SERVERINFO_OS, os);
		logicOutput.put(B_SSM_S02_FieldSet.FIELD_SERVERINFO_IP, ip);
		logicOutput.put(B_SSM_S02_FieldSet.FIELD_SERVERINFO_GATEWAY, gateway);
		logicOutput.put(B_SSM_S02_FieldSet.FIELD_SERVERINFO_SUBNETMASK, subnetMask);
		logicOutput.put(B_SSM_S02_FieldSet.FIELD_SERVERINFO_MOS, mos);
		logicOutput.put(B_SSM_S02_FieldSet.FIELD_SERVERINFO_HOSTID, hostID);
		logicOutput.put(B_SSM_S02_FieldSet.FIELD_SERVERINFO_SERIALNO, serialNo);
		logicOutput.put(B_SSM_S02_FieldSet.FIELD_SERVERINFO_USERLICENSE, userLicense);
		logicOutput.put(B_SSM_S02_FieldSet.FIELD_SERVERINFO_PRIMARYDNS, primaryDNS);
		logicOutput.put(B_SSM_S02_FieldSet.FIELD_SERVERINFO_SECONDDNS, secondDNS);
		logicOutput.put(B_SSM_S02_FieldSet.FIELD_SERVERINFO_MODELNO, modelNo);
		logicOutput.put(B_SSM_S02_FieldSet.FIELD_SERVERINFO_WEBHOSTSPACE, webHostSpace);
		logicOutput.put(B_SSM_S02_FieldSet.FIELD_SERVERINFO_SQL, sql);
		logicOutput.put(B_SSM_S02_FieldSet.FIELD_SERVERINFO_SQLDBNAME, sqlDBName);
		logicOutput.put(B_SSM_S02_FieldSet.FIELD_SERVERINFO_SQLSIZE, sqlSize);
		logicOutput.put(B_SSM_S02_FieldSet.FIELD_SERVERINFO_SQLID, sqlID);
		logicOutput.put(B_SSM_S02_FieldSet.FIELD_SERVERINFO_SQLDBPASS, sqlDBPass);
		logicOutput.put(B_SSM_S02_FieldSet.FIELD_SERVERINFO_BACKUP, backup);
		logicOutput.put(B_SSM_S02_FieldSet.FIELD_SERVERINFO_BACKUPSIZE, backupSize);
		logicOutput.put(B_SSM_S02_FieldSet.FIELD_SERVERINFO_MONITORING, monitoring);
		logicOutput.put(B_SSM_S02_FieldSet.FIELD_SERVERINFO_INSTALLEDAPP, installedApp);
	}

	/**
	 * Get and map it contact view data to output
	 * 
	 * @param queryDAO
	 * @param logicInput
	 * @param logicOutput
	 */
	public static void mappingITContactTabData(QueryDAO queryDAO,
												 HashMap<String, Object> logicInput,
												 HashMap<String, Object> logicOutput) {
		// IT Contact Memo remarks
		List<HashMap<String, Object>> iTContactMemoResult = new ArrayList<HashMap<String,Object>>();
		HashMap<String, Object> iTContactMemo = new HashMap<String, Object>();
		HashMap<String, Object> iTContactDetail1 = new HashMap<String,Object>();
		HashMap<String, Object> iTContactDetail2 = new HashMap<String,Object>();
		HashMap<String, Object> iTContactDetail3 = new HashMap<String,Object>();
		iTContactMemoResult = queryDAO.executeForObjectList("B_SSM_S02.getITContactMemoInfo", logicInput);
		if (iTContactMemoResult.size() > 0) {	
			iTContactMemo = iTContactMemoResult.get(0);
			Map<String, Object> iTContactDetailInput = new HashMap<String,Object>();
			iTContactDetailInput.put(B_SSM_S02_FieldSet.FIELD_ITCONTACT_HEAD_ID, iTContactMemoResult.get(0).get(B_SSM_S02_FieldSet.FIELD_ITCONTACT_HEAD_ID));
			List<HashMap<String, Object>> iTContactDetailOutput = new ArrayList<HashMap<String,Object>>();
			// IT Contact detail
			iTContactDetailOutput = queryDAO.executeForObjectList("B_SSM_S02.getITContactDetailInfo", iTContactDetailInput);
			for (int i=0; i<iTContactDetailOutput.size(); i++)
			{
				String contactType = (String)iTContactDetailOutput.get(i).get(B_SSM_S02_FieldSet.FIELD_ITCONTACT_DETAIL_TYPE);
				if ("C1".equals(contactType))
					iTContactDetail1 = iTContactDetailOutput.get(i);
				else if ("C2".equals(contactType))
					iTContactDetail2 = iTContactDetailOutput.get(i);
				else if ("C3".equals(contactType))
					iTContactDetail3 = iTContactDetailOutput.get(i);
			}
		}
		BLogicUtils.copyProperties(logicOutput, iTContactMemo);
		logicOutput.put(B_SSM_S02_FieldSet.FIELD_ITCONTACT_DETAIL1, iTContactDetail1);
		logicOutput.put(B_SSM_S02_FieldSet.FIELD_ITCONTACT_DETAIL2, iTContactDetail2);
		logicOutput.put(B_SSM_S02_FieldSet.FIELD_ITCONTACT_DETAIL3, iTContactDetail3);
	}

	/**
	 * Get and map rack equipment location view data to output
	 * 
	 * @param queryDAO
	 * @param logicInput
	 * @param logicOutput
	 */
	public static void mappingRackEquipmentLocationTabData(QueryDAO queryDAO,
															 HashMap<String, Object> logicInput,
															 HashMap<String, Object> logicOutput) {
		// Rack Power Memo remarks
		List<HashMap<String, Object>> rackPowerMemoResult = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> rackPowerMemo = new HashMap<String, Object>();
		rackPowerMemoResult = queryDAO.executeForObjectList(
				"B_SSM_S02.getRackPowerMemoInfo", logicInput);
		
		List<HashMap<String, Object>> rackDetail = new ArrayList<HashMap<String, Object>>();
		if (rackPowerMemoResult.size() > 0) {
			// Rack Power detail
			rackPowerMemo = rackPowerMemoResult.get(0);
			Map<String, Object> rackDetailInput = new HashMap<String, Object>();
			rackDetailInput.put(B_SSM_S02_FieldSet.FIELD_RACKPOWER_HEAD_ID, rackPowerMemoResult.get(0).get(B_SSM_S02_FieldSet.FIELD_RACKPOWER_HEAD_ID));
			rackDetail = queryDAO.executeForObjectList("B_SSM_S02.getRackPowerDetailInfo", rackDetailInput);			
		}
		BLogicUtils.copyProperties(logicOutput, rackPowerMemo);
		logicOutput.put(B_SSM_S02_FieldSet.FIELD_RACKPOWER_DETAIL_LIST, rackDetail);
	}

	/**
	 * Get and map Mail Address view data to output
	 * 
	 * @param queryDAO
	 * @param logicInput
	 * @param logicOutput
	 */
	public static void mappingMailAddressTabData(QueryDAO queryDAO,
												   HashMap<String, Object> logicInput,
												   HashMap<String, Object> logicOutput) {
		// Mail server information & auto update quantity & memo
		List<HashMap<String, Object>> mailServerInfo = new ArrayList<HashMap<String, Object>>();
		mailServerInfo = queryDAO.executeForObjectList("B_SSM_S02.getMailServerInformation", logicInput);
		if (mailServerInfo.size() > 0) {
		    HashMap<String, Object> mapMailServerInfo = mailServerInfo.get(0);
			BLogicUtils.copyProperties(logicOutput, mapMailServerInfo);
			//Auto Update Quantity 
			autoUpdateQtyInfo(queryDAO, mapMailServerInfo, logicOutput);
		}

		// Accounts
		List<HashMap<String, Object>> mailAccounts = new ArrayList<HashMap<String, Object>>();
		mailAccounts = queryDAO.executeForObjectList("B_SSM_S02.getMailAccounts", logicInput);
		if (mailAccounts.size() > 0) {
			logicOutput.put(B_SSM_S02_FieldSet.FIELD_MAILACCOUNTLIST,
					mailAccounts);
		}
		// Deleted mails
		List<HashMap<String, Object>> mailAccountsDeleted = new ArrayList<HashMap<String, Object>>();
		mailAccountsDeleted = queryDAO.executeForObjectList("B_SSM_S02.getMailAccountsDeleted", logicInput);
		if (mailAccountsDeleted.size() > 0) {
			logicOutput.put(B_SSM_S02_FieldSet.FIELD_MAILACCOUNTDELETEDLIST,
					mailAccountsDeleted);
		}
		// Totals
		List<HashMap<String, Object>> mailAccountTotals = new ArrayList<HashMap<String, Object>>();
		mailAccountTotals = queryDAO.executeForObjectList("B_SSM_S02.getMailAccountTotals", logicInput);
		if (mailAccountTotals.size() > 0) {
			BLogicUtils.copyProperties(logicOutput, mailAccountTotals.get(0));
		}
	}
	
	/**
	 * 
	 * @param queryDAO
	 * @param mapMailServerInfo
	 * @param logicOutput
	 */
	private static void autoUpdateQtyInfo(QueryDAO queryDAO, HashMap<String, Object> mapMailServerInfo, HashMap<String, Object> logicOutput){
	  //Auto Update Quantity 
        String mailAccount = CommonUtils.toString(mapMailServerInfo.get("mailAccount"));
        String additionalOption = CommonUtils.toString(mapMailServerInfo.get("additionalOption"));
        String mailBoxQty = CommonUtils.toString(mapMailServerInfo.get("mailBoxQty"));
        String optionVirusScan = CommonUtils.toString(mapMailServerInfo.get("optionVirusScan"));
        String optionAntiSpam = CommonUtils.toString(mapMailServerInfo.get("optionAntiSpam"));
        String optionJunkManagement = CommonUtils.toString(mapMailServerInfo.get("optionJunkManagement"));
        
        List<String> idCustPlanLinkList = new ArrayList<String>();
        if (!CommonUtils.isEmpty(mailAccount)) {
            idCustPlanLinkList.add(mailAccount);
        }
        if (!CommonUtils.isEmpty(additionalOption)) {
            idCustPlanLinkList.add(additionalOption);
        }
        if (!CommonUtils.isEmpty(mailBoxQty)) {
            idCustPlanLinkList.add(mailBoxQty);
        }
        if (!CommonUtils.isEmpty(optionVirusScan)) {
            idCustPlanLinkList.add(optionVirusScan);
        }
        if (!CommonUtils.isEmpty(optionAntiSpam)) {
            idCustPlanLinkList.add(optionAntiSpam);
        }
        if (!CommonUtils.isEmpty(optionJunkManagement)) {
            idCustPlanLinkList.add(optionJunkManagement);
        }
        if (0<idCustPlanLinkList.size()) {
            Map<String, Object> conditionMap = new HashMap<String, Object>();
            conditionMap.put("idCustPlanLinkList", idCustPlanLinkList);
            List<Map<String, Object>> mapCustomerPlanLinkInfoList = queryDAO.executeForMapList("B_SSM_S02.getCustomerPlanLinkInfo", conditionMap);
            //Mail Account(Sub Plan)
            Map<String, Object> mailAccountCustomerPlanLinkInfo = getCustomerPlanLinkInfoByKey(mailAccount, mapCustomerPlanLinkInfoList);
            String[] mailAccountPutMapKey = {"mailAccountPlanName","mailAccountItemPlanGrpName","mailAccountServiceStatus"};
            String[] getMapKey = {"PLAN_NAME","ITEM_GRP_NAME","SERVICES_STATUS"};
            setValueToMap(mailAccountPutMapKey, getMapKey, mailAccountCustomerPlanLinkInfo, logicOutput);
            //Additional Option 
            Map<String, Object> additionalOptionCustomerPlanLinkInfo = getCustomerPlanLinkInfoByKey(additionalOption, mapCustomerPlanLinkInfoList);
            String[] additionalOptionPutMapKey = {"additionalOptionPlanName","additionalOptionPlanGrpName","additionalOptionServiceStatus"};
            setValueToMap(additionalOptionPutMapKey, getMapKey, additionalOptionCustomerPlanLinkInfo, logicOutput);
            //mailBox Qty
            Map<String, Object> mailBoxQtyCustomerPlanLinkInfo = getCustomerPlanLinkInfoByKey(mailBoxQty, mapCustomerPlanLinkInfoList);
            String[] mailBoxQtyPutMapKey = {"mailBoxQtyPlanName","mailBoxQtyPlanGrpName","mailBoxQtyServiceStatus"};
            setValueToMap(mailBoxQtyPutMapKey, getMapKey, mailBoxQtyCustomerPlanLinkInfo, logicOutput);
            //Virus Scan
            Map<String, Object> virusScanCustomerPlanLinkInfo = getCustomerPlanLinkInfoByKey(optionVirusScan, mapCustomerPlanLinkInfoList);
            String[] virusScanPutMapKey = {"virusScanPlanName","virusScanPlanGrpName","virusScanServiceStatus"};
            setValueToMap(virusScanPutMapKey, getMapKey, virusScanCustomerPlanLinkInfo, logicOutput);
            //AntiSpam
            Map<String, Object> antiSpamCustomerPlanLinkInfo = getCustomerPlanLinkInfoByKey(optionAntiSpam, mapCustomerPlanLinkInfoList);
            String[] antiSpamPutMapKey = {"antiSpamPlanName","antiSpamPlanGrpName","antiSpamServiceStatus"};
            setValueToMap(antiSpamPutMapKey, getMapKey, antiSpamCustomerPlanLinkInfo, logicOutput);
            //JunkManagement
            Map<String, Object> junkManagementCustomerPlanLinkInfo = getCustomerPlanLinkInfoByKey(optionJunkManagement, mapCustomerPlanLinkInfoList);
            String[] junkManagementPutMapKey = {"junkManagementPlanName","junkManagementPlanGrpName","junkManagementServiceStatus"};
            setValueToMap(junkManagementPutMapKey, getMapKey, junkManagementCustomerPlanLinkInfo, logicOutput);
        }
	}
	/**
	 * 
	 * @param key
	 * @param mapCustomerPlanLinkInfoList
	 * @return
	 */
	private static Map<String, Object> getCustomerPlanLinkInfoByKey(String key, List<Map<String, Object>> mapCustomerPlanLinkInfoList) {
	    if (mapCustomerPlanLinkInfoList!=null && 0<mapCustomerPlanLinkInfoList.size()) {
            for(Map<String, Object> mapCustomerPlanLinkInfo : mapCustomerPlanLinkInfoList) {
                String idCustPlanLink = CommonUtils.toString(mapCustomerPlanLinkInfo.get("ID_CUST_PLAN_LINK"));
                if(key.equals(idCustPlanLink)) {
                    return mapCustomerPlanLinkInfo;
                }
            }
        }
	    return null;
	}
	
	/**
	 * 
	 * @param putMapKey
	 * @param getValueKey
	 * @param map
	 * @param logicOutput
	 */
	private static void setValueToMap(String[] putMapKey, String[] getValueKey, Map<String, Object> map, HashMap<String, Object> logicOutput) {
	    if (map!=null) {
	        for(int i=0;i<putMapKey.length;i++) {
	            logicOutput.put(putMapKey[i], map.get(getValueKey[i]));
	        }
	    } else {
	        for(int i=0;i<putMapKey.length;i++) {
                logicOutput.put(putMapKey[i], "");
            }
	    }
	}

	/**
	 * Get and map general view data to output
	 * 
	 * @param queryDAO
	 * @param logicInput
	 * @param logicOutput
	 */
	public static void mappingGeneralTabData(QueryDAO queryDAO,
												HashMap<String, Object> logicInput,
												HashMap<String, Object> logicOutput) {
	    String idSubInfo = CommonUtils.toString(logicInput.get("subscriptionID"));
	    
	    List<Map<String, Object>> radiusServiceList = getRadiusServiceByIdSubInfo(queryDAO, idSubInfo);
	    String isRadiusFlag = "";
	    String planStatus = "";
	    String isActiveRadiusFlag = "";
	    if (radiusServiceList!=null && 0<radiusServiceList.size()) {
	        isRadiusFlag = B_SSM_S02_FieldSet.FIELD_IS_RADIUS_FLAG_YES;
	        planStatus = CommonUtils.toString(radiusServiceList.get(0).get("PLAN_STATUS"));
	        
	        boolean hasActiveRadiusService = false;
	        for(Map<String, Object> map : radiusServiceList) {
	            String serviceStatus = CommonUtils.toString(map.get("PLAN_STATUS"));
	            if("PS3".equals(serviceStatus)) {
	                hasActiveRadiusService = true;
	                break;
	            }
	        }
	        if (hasActiveRadiusService) {
	            isActiveRadiusFlag = B_SSM_S02_FieldSet.FIELD_IS_RADIUS_FLAG_YES;
	        } else {
	            isActiveRadiusFlag = B_SSM_S02_FieldSet.FIELD_IS_RADIUS_FLAG_NO;
	        }
	    } else {
	        isRadiusFlag = B_SSM_S02_FieldSet.FIELD_IS_RADIUS_FLAG_NO;
	        isActiveRadiusFlag = B_SSM_S02_FieldSet.FIELD_IS_RADIUS_FLAG_NO;
	        
	        Map<String, Object> customerPlanMap = getPlanStatusByIdSubInfo(queryDAO, idSubInfo);
	        if (customerPlanMap!=null) {
	            planStatus = CommonUtils.toString(customerPlanMap.get("PLAN_STATUS"));
	        }
	    }
	    logicOutput.put(B_SSM_S02_FieldSet.FIELD_IS_RADIUS_FLAG, isRadiusFlag);
	    logicOutput.put(B_SSM_S02_FieldSet.FIELD_IS_ACTIVE_RADIUS_FLAG, isActiveRadiusFlag);
	    logicOutput.put(B_SSM_S02_FieldSet.FIELD_PLAN_STATUS, planStatus);
	    
		// Router Setting Information
		List<HashMap<String, Object>> routerSettingInfo = new ArrayList<HashMap<String, Object>>();
		routerSettingInfo = queryDAO.executeForObjectList("B_SSM_S02.getRouterSettingInformation", logicInput);
		if (routerSettingInfo.size() > 0) {
			BLogicUtils.copyProperties(logicOutput, routerSettingInfo.get(0));
		}
		// Browser Information
		List<HashMap<String, Object>> browserInfo = new ArrayList<HashMap<String, Object>>();
		browserInfo = queryDAO.executeForObjectList("B_SSM_S02.getBrowserInformation", logicInput);
		if (browserInfo.size() > 0) {
			BLogicUtils.copyProperties(logicOutput, browserInfo.get(0));
		}
		// Installation Address
		// Installation Address 1
		List<HashMap<String, Object>> installationAddress1 = new ArrayList<HashMap<String, Object>>();
		installationAddress1 = queryDAO.executeForObjectList("B_SSM_S02.getInstallationAddress1", logicInput);
		if (installationAddress1.size() > 0) {
			BLogicUtils.copyProperties(logicOutput, installationAddress1.get(0));
		}
		// Installation Address 2
		List<HashMap<String, Object>> installationAddress2 = new ArrayList<HashMap<String, Object>>();
		installationAddress2 = queryDAO.executeForObjectList("B_SSM_S02.getInstallationAddress2", logicInput);
		if (installationAddress2.size() > 0) {
			BLogicUtils.copyProperties(logicOutput, installationAddress2.get(0));
		}
		// FTP Interface Information
		List<HashMap<String, Object>> FTPInterfaceInformation = new ArrayList<HashMap<String, Object>>();
		FTPInterfaceInformation = queryDAO.executeForObjectList(
				"B_SSM_S02.getFTPInterfaceInformation", logicInput);
		if (FTPInterfaceInformation.size() > 0) {
			BLogicUtils.copyProperties(logicOutput, FTPInterfaceInformation.get(0));
		}
		// MRTG Monitoring
		List<HashMap<String, Object>> MRTGMonitor = new ArrayList<HashMap<String, Object>>();
		MRTGMonitor = queryDAO.executeForObjectList("B_SSM_S02.getMRTGMonitoring", logicInput);
		if (MRTGMonitor.size() > 0) {
			BLogicUtils.copyProperties(logicOutput, MRTGMonitor.get(0));
		}
		// Memo
		List<HashMap<String, Object>> memo = new ArrayList<HashMap<String, Object>>();
		memo = queryDAO.executeForObjectList("B_SSM_S02.getSubscriptionInfoMemo", logicInput);
		if (memo.size() > 0) {
			BLogicUtils.copyProperties(logicOutput, memo.get(0));
		}
	}

	/**
	 * Map country code in output
	 * 
	 * @param queryDAO
	 * @param logicInput
	 * @param logicOutput
	 */
	public static void mappingCountryCodeData(QueryDAO queryDAO,
												HashMap<String, Object> logicInput,
												HashMap<String, Object> logicOutput) {
		String countryName1 = BLogicUtils.emptyValue(logicOutput.get(B_SSM_S02_FieldSet.FIELD_INSTALLATION1COUNTRYNAME), "");
		String countryName2 = BLogicUtils.emptyValue(logicOutput.get(B_SSM_S02_FieldSet.FIELD_INSTALLATION2COUNTRYNAME), "");
		HashMap<String, String> params = new HashMap<String, String>();
		// Get countryCode1
		params.put(B_SSM_S02_FieldSet.FIELD_INSTALLATIONCOUNTRYNAME, countryName1);
		String countryCode1 = queryDAO.executeForObject("B_SSM_S02.getCountryCode", params, String.class);
		// Get countryCode2
		params.put(B_SSM_S02_FieldSet.FIELD_INSTALLATIONCOUNTRYNAME, countryName2);
		String countryCode2 = queryDAO.executeForObject("B_SSM_S02.getCountryCode", params, String.class);
		// Set country 1
		if (countryCode1 != null && !countryCode1.equals("")) {
			logicOutput.put(B_SSM_S02_FieldSet.FIELD_INSTALLATION1COUNTRYNAME, countryCode1);
		}
		// Set country 2
		if (countryCode2 != null && !countryCode2.equals("")) {
			logicOutput.put(B_SSM_S02_FieldSet.FIELD_INSTALLATION2COUNTRYNAME, countryCode2);
		}
	}
	
	/**
	 * Map carrier code in output
	 * @param queryDAO
	 * @param logicInput
	 * @param logicOutput
	 */
	public static void mappingCarrierCodeData(QueryDAO queryDAO,
												HashMap<String, Object> logicInput,
												HashMap<String, Object> logicOutput) {
		String carrierCode = BLogicUtils.emptyValue(logicOutput.get(B_SSM_S02_FieldSet.FIELD_CARRIER), "");		
		HashMap<String, String> params = new HashMap<String, String>();
		// Get carrier name
		params.put(B_SSM_S02_FieldSet.FIELD_CARRIERID, carrierCode);
		String carrierName = queryDAO.executeForObject("B_SSM_S02.getCarrierName", params, String.class);		
		// Set carrier name
		if (carrierName != null && !carrierName.equals("")) {
			logicOutput.put(B_SSM_S02_FieldSet.FIELD_CARRIER, carrierName);
		}
	}

	/**
	 * Map auto update quantity plan in output
	 * 
	 * @param queryDAO
	 * @param logicInput
	 * @param logicOutput
	 */
	public static void mappingAutoUpdateQuantityPlanData(QueryDAO queryDAO,
														   HashMap<String, Object> logicInput,
														   HashMap<String, Object> logicOutput) {
		// Get auto update plan data
		List<HashMap<String, Object>> autoUpdatePlan = new ArrayList<HashMap<String, Object>>();
		autoUpdatePlan = queryDAO.executeForObjectList("B_SSM_S02.getAutoUpdateQuantityPlan", logicInput);
		// Add output
		if (autoUpdatePlan.size() > 0) {
			logicOutput.put(B_SSM_S02_FieldSet.FIELD_AUTOUPDATEQUANTITYPLANLIST, autoUpdatePlan);
		}
	}
	
	/**
	 * Map modem no data
	 * 
	 * @param queryDAO
	 * @param logicInput
	 * @param logicOutput
	 */
	public static void mappingModemNoData(QueryDAO queryDAO,
										    HashMap<String, Object> logicInput,
										    HashMap<String, Object> logicOutput) {
		// Get modem no data
		List<HashMap<String, Object>> modemNoList = new ArrayList<HashMap<String, Object>>();
		modemNoList = queryDAO.executeForObjectList("B_SSM_S02.getModemNoData", logicInput);
		// Add output
		if (modemNoList.size() > 0) {
			logicOutput.put(B_SSM_S02_FieldSet.FIELD_MODEMNOLIST, modemNoList);
		}
	}

	/**
	 * Map auto update quantity plan in output
	 * 
	 * @param queryDAO
	 * @param logicInput
	 * @param logicOutput
	 */
	public static void mappingMailServerInformationWithCodeData(QueryDAO queryDAO, 
																   HashMap<String, Object> logicInput,
																   HashMap<String, Object> logicOutput) {
		// Get mail server information with code
		List<HashMap<String, Object>> info = new ArrayList<HashMap<String, Object>>();
		info = queryDAO.executeForObjectList("B_SSM_S02.getMailServerInformationWithCode", logicInput);
		// Add output
		if (info.size() > 0) {
			BLogicUtils.copyProperties(logicOutput, info.get(0));
		}
	}

	/**
	 * Get selected mail account ids
	 * @param logicInput
	 * @return String[]
	 */
	public static String[] getSelectedMailAccountIDs(HashMap<String, Object> logicInput) {
		String[] mailAccountIDs = logicInput.get(B_SSM_S02_FieldSet.FIELD_MAILACCOUNTIDS) != null?
								  (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_MAILACCOUNTIDS) :
								  null;
		String[] selectedCheckMails = logicInput.get(B_SSM_S02_FieldSet.FIELD_SELECTEDCHECKMAILS) != null?
									  (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_SELECTEDCHECKMAILS) :
									  null;	
		List<String> selectedIDs = new ArrayList<String>();							  
		
		// Get selected ids
		if (selectedCheckMails != null && mailAccountIDs.length > 0) {
		for (int i = 0; i < selectedCheckMails.length; i++) {
			String isSelectedStr = selectedCheckMails[i];
			if (isSelectedStr != null && isSelectedStr.equals("1")) {
				selectedIDs.add(mailAccountIDs[i]);
			}
		}
		}
		String[] selectedIDArray = new String[selectedIDs.size()];
		selectedIDs.toArray(selectedIDArray);
		return selectedIDArray;
	}

	/**
	 * Get selected deleted mail account ids
	 * @param logicInput
	 * @return String[]
	 */
	public static String[] getSelectedDeletedMailAccountIDs(HashMap<String, Object> logicInput) {
		String[] mailAccountDeletedIDs = logicInput.get(B_SSM_S02_FieldSet.FIELD_MAILACCOUNTDELETEDIDS) != null?
										 (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_MAILACCOUNTDELETEDIDS) :
										 null;
		String[] selectedCheckDeletedMails = logicInput.get(B_SSM_S02_FieldSet.FIELD_SELECTEDCHECKDELETEDMAILS) != null?
											  (String[]) logicInput.get(B_SSM_S02_FieldSet.FIELD_SELECTEDCHECKDELETEDMAILS) :
											  null;	
		List<String> selectedIDs = new ArrayList<String>();							  
		
		// Get selected ids
		if (selectedCheckDeletedMails != null && mailAccountDeletedIDs.length > 0) {
		for (int i = 0; i < selectedCheckDeletedMails.length; i++) {
				String isSelectedStr = selectedCheckDeletedMails[i];
				if (isSelectedStr != null && isSelectedStr.equals("1")) {
					selectedIDs.add(mailAccountDeletedIDs[i]);
				}
			}
		}
		String[] selectedIDArray = new String[selectedIDs.size()];
		selectedIDs.toArray(selectedIDArray);
		return selectedIDArray;
	}

	/**
	 * Get report template path from db
	 * @param queryDAO
	 * @param reportMode
	 * @return String
	 */
	public static String getDBReportTemplatePath(QueryDAO queryDAO,
												   String reportMode) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put(B_SSM_S03_FieldSet.FIELD_REPORTCODE, reportMode);
		String rptTemplatePath = queryDAO.executeForObject("B_RPT.getReportTemplate", params, String.class);
		return rptTemplatePath;
	}

	public static List<Map<String, Object>> getRadiusServiceByIdSubInfo(QueryDAO queryDAO, String idSubInfo) {
	    List<Map<String, Object>> mapList = queryDAO.executeForMapList("B_SSM_S02.getRadiusServiceByIdSubInfo", idSubInfo);
	    return mapList;
	}
	
	public static Map<String, Object> getPlanStatusByIdSubInfo(QueryDAO queryDAO, String idSubInfo) {
	    Map<String, Object> map = queryDAO.executeForMap("B_SSM_S02.getPlanStatusByIdSubInfo", idSubInfo);
	    return map;
	}
	
	public static void testStartBtnClick(QueryDAO radiusQueryDAO, UpdateDAO radiusUpdateDAO, String accessAccount, String accessPw) {
	    RadUserTUtil radUserTUtil = new RadUserTUtil(radiusQueryDAO, radiusUpdateDAO);
        RAD_USERS_T radUserT = new RAD_USERS_T();
        //USERNAME
        radUserT.setUserName(accessAccount);
        //PASSWORD
        radUserT.setPassword(accessPw);
        //EXPIRE_DATE
        radUserT.setExpireDate("1970-01-01 09:00:00");
        //CREDIT_TIME
        radUserT.setCreditTime("-1");
        //STATUS
        radUserT.setStatus("0");
        //PLAN_ID
        radUserT.setPlanId("0");
        //AUTH_METHOD
        radUserT.setAuthMethod("0");
        //ATTRBUTE_RULE
        radUserT.setAttrbuteRule("0");
        //MODIFY_COUNTER
        radUserT.setModifyCounter("0");
        //insert RAD_USERS_T
        radUserTUtil.insert(radUserT);
	}
	
	public static void testCompleteBtnClick(QueryDAO queryDAO, QueryDAO radiusQueryDAO, UpdateDAO radiusUpdateDAO, String accessAccount) {
	    RadUserTUtil radUserTUtil = new RadUserTUtil(radiusQueryDAO, radiusUpdateDAO);
	    RAD_USERS_T radUserST = radUserTUtil.selectByPK(accessAccount);
	    if (radUserST!=null) {
	        radUserTUtil.deleteByUserName(accessAccount);
	    }
	}
	
	public static void updateAccessAccountTest(UpdateDAO updateDAO, String idSubInfo, String accessAccountTest, String idLogin, int idAudit) {
	    Map<String, Object> sqlParam = new HashMap<String, Object>();
	    sqlParam.put("idSubInfo", idSubInfo);
	    sqlParam.put("accessAccountTest", accessAccountTest);
	    sqlParam.put("idLogin", idLogin);
	    sqlParam.put("idAudit", idAudit);
	    updateDAO.execute("B_SSM_S02.updateAccessAccountTest", sqlParam);
	}
	
	public static List<Map<String, Object>> getPlanBatchMappingByIdPlanGrp(QueryDAO queryDAO, String[] idPlanGrpList) {
        Map<String, Object> sqlParam = new HashMap<String, Object>();
        sqlParam.put("idPlanGrpList", idPlanGrpList);
        return queryDAO.executeForMapList("B_SSM_S02.GET_BATCH_MAPPING_BY_ID_PLAN_GRP", sqlParam);
    }
}
