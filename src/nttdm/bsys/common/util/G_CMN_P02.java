package nttdm.bsys.common.util;

import nttdm.bsys.c_cmn002.bean.MessageBean;
import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

public class G_CMN_P02 {
	private static final String DELETE_message1 = "SELECT.C_CMN002.SQL005";
	private static final String DELETE_message2 = "SELECT.C_CMN002.SQL006";
	private QueryDAO queryDAO = null;
	private UpdateDAO updateDAO = null;
	/**
	 * <div>SAVE_ERROR_MSG</div>
	 */
	static final String SAVE_ERROR_MSG = "info.ERR2SC004";
	public UpdateDAO getUpdateDAO() {
		return updateDAO;
	}
	public void setUpdateDAO(UpdateDAO updateDAO) {
		this.updateDAO = updateDAO;
	} 
	public G_CMN_P02(QueryDAO queryDAO, UpdateDAO updateDAO)
	{
		this.queryDAO = queryDAO;
		this.updateDAO = updateDAO;
	}
	public void execute(MessageBean params) throws Exception
	{
		try
		{
			params.setId_login(params.getUvo().getId_user());
			if (params.getMsg_type().equals("NOTICE") || params.getMsg_type().equals("TASK"))
			{
				updateDAO.execute(DELETE_message2, params);
			}
			else
			{
				//Integer detailCount = queryDAO.executeForObject("SELECT.C_CMN002.SQL008", params, Integer.class);
				//if(detailCount <= 0)
				updateDAO.execute(DELETE_message1, params);
			}
		}
		catch(Exception ex)
		{
			throw ex;
		}
	}
}
