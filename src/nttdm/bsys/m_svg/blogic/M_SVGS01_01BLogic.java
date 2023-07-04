/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_SVG
 * SERVICE NAME   : M_SVG_S01
 * FUNCTION       : Showing BLogic
 * FILE NAME      : M_SVGS01_01BLogic.java
 * 
* Copyright © 2011 NTTDATA Corporation
*
 * History
 * 
 * 
**********************************************************/
package nttdm.bsys.m_svg.blogic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.c_cmn001.bean.UserAccess;
import nttdm.bsys.m_svg.dto.M_SVGS01_01Input;
import nttdm.bsys.m_svg.dto.M_SVGS01_01Output;
import nttdm.bsys.m_svg.bean.ServiceGroupBean;
import nttdm.bsys.m_svg.blogic.AbstractM_SVGS01_01BLogic;

/**
 * M_SVGS01_01BLogic<br>
 * <ul>
 * <li>A BLogic class to process displaying Global Settings
 * </ul>
 * <br>
* @author  tuyenpv
* @version 1.0
 */
public class M_SVGS01_01BLogic extends AbstractM_SVGS01_01BLogic {
	public static String ID_MODULE="M";
	public static String ID_SUB_MODULE="M-SVG";
	public static String ACTION_AUTHENTICATION_FAILED="failed";
	public static String ACTION_EDIT="edit";
	public static String ACTION_VIEW="view";
	/**
	 * 
	 * @param param
	 * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
	 */
	public BLogicResult execute(M_SVGS01_01Input param) {
		BLogicResult result = new BLogicResult();

		UserAccess user_access=UserAccess.findAccessFunction(param.getUvo().getUser_access(),M_SVGS01_01BLogic.ID_MODULE,M_SVGS01_01BLogic.ID_SUB_MODULE);
		
		if(user_access==null || user_access.getAccess_type().equals("0"))
		{
			result.setResultString(M_SVGS01_01BLogic.ACTION_AUTHENTICATION_FAILED);
		}
		else
		{
			//Ver3.06 #278 [SIT] Issues found and fix: 1.M-CST, 2.M-SGV, 3.M-SVT CT 13072017
			java.util.List<String> checkRRev = this.queryDAO.executeForObjectList("SELECT.M_SUB_MODULES",null);
			String selectSql = "SELECT.M_SVG.SQL001";
			if(checkRRev != null && checkRRev.size() > 0 && "0".equals(checkRRev.get(0).trim())){
				selectSql = "SELECT.M_SVG.SQL001_2";
			}
			M_SVGS01_01Output outputDTO = new M_SVGS01_01Output();
			java.util.List<ServiceGroupBean> lst=this.queryDAO.executeForObjectList(selectSql,null);
			//Ver3.06 #278 [SIT] Issues found and fix: 1.M-CST, 2.M-SGV, 3.M-SVT CT 13072017
			outputDTO.setServiceGrpItem(lst);
			
			//wcbeh #231 - Category Maintenance Add Journal No
			List<Map<String, String>> journal_list = this.queryDAO.executeForObjectList("SELECT.M_SVG.SQL003", null);
			outputDTO.setJournal_list(journal_list);
			
			result.setResultObject(outputDTO);
			if(user_access.getAccess_type().equals("2"))
			{
				result.setResultString(M_SVGS01_01BLogic.ACTION_EDIT);
			}
			else if(user_access.getAccess_type().equals("1"))
			{
				result.setResultString(M_SVGS01_01BLogic.ACTION_VIEW);
			}			
		}
		return result;
	}
}