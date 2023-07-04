/**
 * @(#)G_CPM_P02.java
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.util;

import java.text.SimpleDateFormat;
import java.util.*;

import jp.terasoluna.fw.dao.QueryDAO;
import nttdm.bsys.common.util.dto.G_CPM_ITContact;

/**
 * Process to Export IT Contact
 * 
 * @author lixinj
 */
public class G_CPM_P02 {
	/**
	 * CONTENT TYPE
	 */
	protected static final String CONTENT_TYPE = "text/csv";

	/**
	 * Export data
	 */
	private String exportData = "";

	/**
	 * Constructor. Set export data.
	 * 
	 * @param listIdCustPlan
	 *            Custom plan ID list
	 * @param queryDAO
	 *            QueryDAO
	 */
	public G_CPM_P02(List<String> listIdCustPlan, QueryDAO queryDAO) {
		List<G_CPM_ITContact> contacts;
		for (int i = 0; i < listIdCustPlan.size(); i++) {
			contacts = queryDAO.executeForObjectList(
					"SELECT.G_CPM.SEARCH_IT_CONTACTS",
					Integer.parseInt(listIdCustPlan.get(i)));
			for (int j = 0; j < contacts.size(); j++) {
				if (contacts.get(j) != null) {
					this.exportData += contacts.get(j).toString() + "\n";
				}
			}
		}
		if (this.exportData.length() > 0) {
			String columns = "Customer Name,Subscription ID"
					+ ",Contact Name 1,Designation 1,Email 1,Tel No 1, Fax No 1"
					+ ",Contact Name 2,Desgination 2,Email 2,Tel No 2, Fax No 2"
					+ ",Contact Name 3,Designation 3,Email 3,Tel No 3, Fax No 3"
					+ "\n";
			exportData = columns + exportData;
		}

	}

	/**
	 * Get export file name.
	 * 
	 * @return file name
	 */
	public String getFileName() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return "IT_Contact_" + sdf.format(cal.getTime()) + ".csv";
	}

	/**
	 * Get export content type.
	 * 
	 * @return CONTENT_TYPE
	 */
	public String getContentType() {
		return CONTENT_TYPE;
	}

	/**
	 * 
	 * 
	 * @return true: if export data is not empty.
	 */
	public Boolean isNoRecord() {
		return exportData.length() == 0;
	}

	/**
	 * Get export data
	 * 
	 * @return export data
	 */
	public String getExportData() {
		return exportData;
	}

	/**
	 * Set export data.
	 * 
	 * @param exportData
	 *            export data
	 */
	public void setExportData(String exportData) {
		this.exportData = exportData;
	}
}
