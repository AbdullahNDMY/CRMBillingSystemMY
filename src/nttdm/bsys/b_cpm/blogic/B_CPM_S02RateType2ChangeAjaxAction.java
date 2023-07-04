package nttdm.bsys.b_cpm.blogic;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nttdm.bsys.b_cpm_en.dto.B_CPM_CONSTANT;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.google.gson.Gson;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.web.struts.actions.ActionEx;

public class B_CPM_S02RateType2ChangeAjaxAction extends ActionEx {

	/**
	 * queryDAO object
	 */
	protected QueryDAO queryDAO = null;

	private static String JSON = "application/json";

	@Override
	public ActionForward doExecute(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Gson googleSon = new Gson();
		String jsonString = "";

		String idCust = request.getParameter("idCust");
		String rateMode = request.getParameter("rateMode");
		String modeType = "M";
		if ("6".equals(rateMode)) {
			modeType = "O";
		}
		Map<String, String> param = new HashMap<String, String>();
		param.put("modeType", modeType);
		param.put("idCust", idCust);
		String rateType2 = queryDAO.executeForObject(B_CPM_CONSTANT.NAMESPACE + "GET_IS_RATE_TYPE2_CHANGE", param, String.class);
		jsonString = googleSon.toJson(rateType2);
		response.setContentType(JSON);
		try {
			// write JSON string into the response
			response.getWriter().print(jsonString);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
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
