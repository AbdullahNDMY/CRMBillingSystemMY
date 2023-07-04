package nttdm.bsys.e_eml.blogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import jp.terasoluna.fw.web.struts.action.GlobalMessageResources;
import nttdm.bsys.common.bean.G_SET_ReturnValue;
import nttdm.bsys.common.bean.T_SET_BATCH;
import nttdm.bsys.common.util.G_BTH_P02;
import nttdm.bsys.common.util.G_SET_P01;
import nttdm.bsys.e_eml.dto.E_EML03Input;

import org.apache.struts.action.ActionMessages;

public class E_EMLS04PdfBLogic implements BLogic<E_EML03Input> {

	protected QueryDAO queryDAO;
	protected UpdateDAO updateDAO;

	public UpdateDAO getUpdateDAO() {
		return updateDAO;
	}

	public void setUpdateDAO(UpdateDAO updateDAO) {
		this.updateDAO = updateDAO;
	}

	public QueryDAO getQueryDAO() {
		return queryDAO;
	}

	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}

	public BLogicResult execute(E_EML03Input input) {

		BLogicResult result = new BLogicResult();
		BLogicMessages msgs = new BLogicMessages();
		String pdfLocation = queryDAO.executeForObject(
				"SELECT.E_EML.GET_PDF_LOCATION", null, String.class);
		// 3.1
		T_SET_BATCH t_batch = new T_SET_BATCH();
		t_batch.setSTATUS("1");
		t_batch.setBATCH_TYPE("EM");
		t_batch.setFILENAME("[Generate PDF]");

		// 3.2 Call G_SET_P01
		G_SET_P01 gsetp01 = new G_SET_P01(queryDAO, updateDAO);
		G_SET_ReturnValue returnValue = gsetp01.G_SET_P01_GetIdBatch(t_batch);
		// 3.3
		int batchId = returnValue.getBatch_id();

		// batch_id is -1
		if (batchId == -1) {
			
			BLogicMessage msg = new BLogicMessage("errors.ERR1SC112",
					new Object[] { returnValue.getRET_MSG() });
			msgs.add(ActionMessages.GLOBAL_MESSAGE, msg);
			result.setErrors(msgs);
		}
		// batch_id is not -1
		else {
			int sucPdfCount = 0;
			if (input.getIdRefs() != null && input.getIdRefs().length != 0) {
				// get idrefList
				List<Map<String, Object>> billDocList = new ArrayList<Map<String, Object>>();
				Map<String, Object> idref = new HashMap<String, Object>();
				idref.put("id_ref", input.getIdRefs());
				billDocList = queryDAO.executeForObjectList("E_EML.selectPdfUserSql",
						idref);
				input.setBillDocList(billDocList);
				G_BTH_P02 gbp02 = new G_BTH_P02();
				gbp02.setQueryDAO(this.queryDAO);
				gbp02.setUpdateDAO(this.updateDAO);
				sucPdfCount = gbp02.generatePdf(input, batchId);
			}
			t_batch.setSTATUS("2");
			GlobalMessageResources msgResource = GlobalMessageResources
					.getInstance();
			t_batch.setMessage(msgResource.getMessage("info.ERR2SC055",
					new Object[] { sucPdfCount }));
			t_batch.setID_BATCH(String.valueOf(batchId));
			t_batch.setFILENAME("[Generate PDF]" + pdfLocation);
			gsetp01.G_SET_P01_GetIdBatch(t_batch);
		}
		result.setResultString("success");
		return result;
	}
}
