package nttdm.bsys.m_eml.blogic;

import java.sql.Clob;
import java.util.Map;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.m_eml.dto.M_EML02Input;
import nttdm.bsys.m_eml.dto.M_EML02Output;

public class M_EMLS03InitBLogic extends AbstractM_EMLS03BLogic{
	
	private QueryDAO queryDAO;

	public BLogicResult execute(M_EML02Input input) {
		BLogicResult result = new BLogicResult();
		M_EML02Output out = new M_EML02Output();
		String code = input.getCode();
		Map<String, Object> mailInfo = queryDAO.executeForMap("SELECT.M_EML.SQL005",code);
		out.setAttachpass((String)mailInfo.get("ATTACTMENT_PASS"));
		out.setCode((String)mailInfo.get("ID_CODE"));
		out.setContent(convertClobToString((Clob)mailInfo.get("CONTENT")));
		out.setDescription((String)mailInfo.get("DESCRIPTION"));
		out.setDisplay_name((String)mailInfo.get("FROM_DISPLAY_NAME"));
		out.setSubject((String)mailInfo.get("SUBJECT"));
		out.setAlwaysCc((String)mailInfo.get("ALWAYS_CC"));
		out.setAttactmen1((String)mailInfo.get("ATTACTMENT1"));
		out.setAttactmen2((String)mailInfo.get("ATTACTMENT2"));
		out.setAttactmen3((String)mailInfo.get("ATTACTMENT3"));
		out.setAttactmen4((String)mailInfo.get("ATTACTMENT4"));
		out.setZipFilename((String)mailInfo.get("ZIPFILENAME"));
		out.setRemark(convertClobToString((Clob)mailInfo.get("REMARK")));
		result.setResultObject(out);
		result.setResultString("success");
		return result;
	}
	
	/**
	 * 
	 * @param clob
	 * @return
	 */
	public String convertClobToString(Clob clob) {
		String toRet = "";
		if(clob!=null) {
			try {
				long length=clob.length();
				toRet=clob.getSubString(1, (int)length);          
			} catch(Exception ex) {
				ex.printStackTrace();
			}      
		}      
		return toRet; 
	}

	/**
	 * @return the queryDAO
	 */
	public QueryDAO getQueryDAO() {
		return queryDAO;
	}

	/**
	 * @param queryDAO the queryDAO to set
	 */
	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}

}
