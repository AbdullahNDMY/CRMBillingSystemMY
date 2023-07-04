package nttdm.bsys.m_ppm.blogic;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.web.struts.actions.ActionEx;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.google.gson.Gson;
//add for #151
public class M_PPM_S02RateType2ChangeAjax  extends ActionEx{

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
		String EU = "A";
		String rateMode = request.getParameter("rateMode");
		String modeType = "M";
		if ("6".equals(rateMode)) {
			modeType = "O";
		}
		Map<String, String> param = new HashMap<String, String>();
		param.put("modeType", modeType);
		param.put("EU", EU);
		String rateType2 = queryDAO.executeForObject("SELECT.M_PPM.S02.RATE_TYPE2", param, String.class);
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
