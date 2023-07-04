package nttdm.bsys.m_svt.blogic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import jp.terasoluna.fw.web.struts.actions.DownloadFile;
import nttdm.bsys.common.util.CSVWriter;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.m_svt.dto.M_SVT01_SearchInput;
import nttdm.bsys.m_svt.dto.PlanService;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.util.MessageResources;

public class M_SVT01_ExportBLogic implements BLogic<M_SVT01_SearchInput> {

	private QueryDAO queryDAO;
	private MessageResources msgResource = MessageResources
			.getMessageResources("M_SVT-messages");

	public BLogicResult execute(M_SVT01_SearchInput input) {

		BLogicResult result = new BLogicResult();

		List<String[]> exportData = new ArrayList<String[]>();
		// add header to export list.
		exportData.add(getExportHeaderItems());
		List<PlanService> dataList = new ArrayList<PlanService>();

		dataList = queryDAO.executeForObjectList("SELECT.M_SVT.GET_LIST_PLANSERVICE", input);

		for (int i = 0; i < dataList.size(); i++) {
			exportData.add(getExportContentItems(dataList.get(i), i + 1));
		}

		String fileName = "ServicePlanInfo_YYMMDDHHMMSS.csv".replace("YYMMDDHHMMSS", getSysdateStr());
		String tmpFolder = System.getProperty("java.io.tmpdir");
		if (!tmpFolder.endsWith(File.separator)) {
			tmpFolder = tmpFolder + File.separator;
		}
		String filePathName = tmpFolder + fileName;

		try {
			FileWriter fw = new FileWriter(filePathName);
			CSVWriter writer = new CSVWriter(fw, ',', '\"');
			writer.writeAll(exportData);
			writer.close();
		} catch (IOException e) {
			BLogicMessages errors = new BLogicMessages();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage(
					"errors.ERR1BT014", fileName));
			result.setErrors(errors);
		}

		// Set download file
		DownloadFile file = new DownloadFile(new File(filePathName));
		result.setResultObject(file);
		return result;
	}

	/**
	 * Get CSV file header items.
	 * 
	 */
	private String[] getExportHeaderItems() {

		List<String> items = new ArrayList<String>();

		// No
		items.add(msgResource.getMessage("screen.m_svt.no"));

		// Catetory
		items.add(msgResource.getMessage("screen.m_svt.svGroup"));

		// Type
		items.add(msgResource.getMessage("screen.m_svt.type"));

		// Description
		items.add(msgResource.getMessage("screen.m_svt.description"));

		// Abbreviation
		items.add(msgResource.getMessage("screen.m_svt.abbreviation"));

		// Account Code
		items.add(msgResource.getMessage("screen.m_svt.accountCode"));

		// Product Code
		items.add(msgResource.getMessage("screen.m_svt.produceCode"));

		// Reference 1
		items.add(msgResource.getMessage("screen.m_svt.reference1"));

		// Reference 2
		items.add(msgResource.getMessage("screen.m_svt.reference2"));

		return items.toArray(new String[items.size()]);
	}

	/**
	 * Generate exported content.
	 * 
	 * @param item
	 * @return exported item value
	 */
	private String[] getExportContentItems(PlanService item, int no) {
		List<String> items = new ArrayList<String>();

		// No
		items.add(CommonUtils.toString(no));

		// Catetory
		items.add(CommonUtils.toString(item.getSvcGrpName()));

		// Type
		items.add(CommonUtils.toString(item.getType()));

		// Description
		items.add(CommonUtils.toString(item.getServiceDescription()));

		// Abbreviation
		items.add(CommonUtils.toString(item.getDescAbbr()));

		// Account Code
		items.add(CommonUtils.toString(item.getAccCode()));

		// Product Code
		items.add(CommonUtils.toString(item.getProductCode()));

		// Reference 1
		items.add(CommonUtils.toString(item.getReference1()));

		// Reference 2
		items.add(CommonUtils.toString(item.getReference2()));

		return items.toArray(new String[items.size()]);
	}

	/**
	 * YY MMDDHHMMSS
	 * 
	 * @return
	 */
	private String getSysdateStr() {
		Calendar now = Calendar.getInstance();
		String yy = CommonUtils.formatDate(now.getTime(), "yy");
		String MM = CommonUtils.formatDate(now.getTime(), "MM");
		String dd = CommonUtils.formatDate(now.getTime(), "dd");
		String HH = CommonUtils.formatDate(now.getTime(), "HH");
		String mm = CommonUtils.formatDate(now.getTime(), "mm");
		String ss = CommonUtils.formatDate(now.getTime(), "ss");

		return yy + "" + MM + "" + dd + "" + HH + "" + mm + ss;
	}

	/**
	 * @return the queryDAO
	 */
	public QueryDAO getQueryDAO() {
		return queryDAO;
	}

	/**
	 * @param queryDAO
	 *            the queryDAO to set
	 */
	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}

}
