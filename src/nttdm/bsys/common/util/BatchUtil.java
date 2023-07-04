/**
 * @(#)BatchUtil.java
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.struts.upload.FormFile;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

/**
 * Utility class for batch process
 */
public class BatchUtil {

	/**
	 * <div>Constructor</div>
	 * 
	 * @param queryDAO
	 *            QueryDAO
	 * @param updateDAO
	 *            UpdateDAO
	 * @param uvo
	 *            BillingSystemUserValueObject
	 */
	public BatchUtil(QueryDAO queryDAO, UpdateDAO updateDAO,
			BillingSystemUserValueObject uvo) {
	}

	/**
	 * <div>readFile</div>
	 * 
	 * @param uploadFile
	 * 
	 * @return List<String[]> the detail of file
	 */
	public List<String[]> readFile(FormFile uploadFile) {
		List<String[]> lineList = null;

		CSVReader csvReader = null;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					uploadFile.getInputStream()));
			csvReader = new CSVReader(br);
			lineList = csvReader.readAll();
		} catch (IOException ex) {
			ex.printStackTrace();
			throw new FileImportExportException("Cannot read uploaded file");
		} finally {
			try {
				csvReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return lineList;
	}
}
