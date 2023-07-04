
 /**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_COM
 * SERVICE NAME   : M_COMS01
 * FUNCTION       : Display Image BLogic
 * FILE NAME      : M_COMS01displayImageBlogicBLogic.java
 *
 * 
 * 
**********************************************************/
package nttdm.bsys.m_com.blogic;

import java.util.Map;

import oracle.sql.BLOB;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.m_com.dto.M_COMS01displayImageBlogicInput;
import nttdm.bsys.m_com.dto.M_COMS01displayImageBlogicOutput;

import nttdm.bsys.m_com.bean.M_COMS01Bean;


import nttdm.bsys.m_com.blogic.AbstractM_COMS01displayImageBlogicBLogic;

/**
 * BusinessLogic class<br>
 * @author NTT Data Vietnam	
 * <p>
*/

public class M_COMS01displayImageBlogicBLogic extends
		AbstractM_COMS01displayImageBlogicBLogic {

	/**
	 * 
	 * @param param
	 * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
	 */
	String sql_get_photo ="SELECT.COM.SQL005PHOTO";
	public BLogicResult execute(M_COMS01displayImageBlogicInput param) {
		BLogicResult result = new BLogicResult();
		M_COMS01displayImageBlogicOutput outputDTO = new M_COMS01displayImageBlogicOutput();

		// TODO
		// QueryDAO 記述例
		// SampleUVO uvo = queryDAO.executeForObject("namespace.sqlID",
		// params, SampleUVO().class);
		//
		// UpdateDAO 記述例
		// updateDAO.execute("namespace.sqlID", params);
		//		
		
		M_COMS01Bean bean = queryDAO.executeForObject(this.sql_get_photo, null, M_COMS01Bean.class);
		byte[] photo = null;
		if(bean!=null){
			photo = bean.getLOGO();
		}
		outputDTO.setPhoto(photo);		
		
		result.setResultObject(outputDTO);
		result.setResultString("success");
		return result;
	}
}