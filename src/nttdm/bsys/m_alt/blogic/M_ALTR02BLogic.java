package nttdm.bsys.m_alt.blogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.G_DOC_P01;
import nttdm.bsys.m_alt.bean.FileInfo;
import nttdm.bsys.m_alt.dto.M_ALTR02Input;

import org.apache.struts.Globals;
import org.apache.struts.upload.FormFile;

public class M_ALTR02BLogic extends AbstractM_ALTR02BLogic {

	/**
	 * <div>SQL_GET_MAX_ID_MSG</div>
	 */
	static final String SQL_GET_MAX_ID_MSG = "SELECT.M_ALT.SQL002";
	/**
	 * <div>SQL_INSERT_HEADER_MSG</div>
	 */
	static final String SQL_INSERT_HEADER_MSG = "INSERT.M_ALT.SQL003";
	
	static final String SQL_UPDATE_HEADER_MSG = "UPDATE.M_ALT.SQL0013";
	
	/**
	 * <div>SQL_INSERT_DETAIL_MSG</div>
	 */
	static final String SQL_INSERT_DETAIL_MSG = "INSERT.M_ALT.SQL004";
	/**
	 * <div>SQL_GET_MAX_ID_DOC</div>
	 */
	public static final String SQL_GET_MAX_ID_DOC = "SELECT.M_ALT.SQL006";
	/**
	 * <div>SQL_INSERT_T_DOC</div>
	 */
	static final String SQL_INSERT_T_DOC = "INSERT.M_ALT.SQL007";
	/**
	 * <div>SQL_INSERT_M_ALT_ATC</div>
	 */
	static final String SQL_INSERT_M_ALT_ATC= "INSERT.M_ALT.SQL008";
	
	/**
	 * <div>SAVE_SUCCESSFUL_MSG</div>
	 */
	static final String SAVE_SUCCESSFUL_MSG = "info.ERR2SC003";
	/**
	 * <div>SAVE_ERROR_MSG</div>
	 */
	static final String SAVE_ERROR_MSG = "info.ERR2SC004";
	
	private static final String ERR_FILE_SIZE_UPLOAD = "errors.ERR1SC027";

	private String filePath = "";
	private int maxFileSize = 0;
	
	/**
	 * 
	 * @param param
	 * @return BLogicResult
	 */
	public BLogicResult execute(M_ALTR02Input param) {
		BLogicResult result = new BLogicResult();
		BLogicMessages errors = new BLogicMessages();
		BLogicMessages messages = new BLogicMessages();
		boolean savedMode = false;
		try{
			
			//get from user click Edit button
			if(param.getClick_event() != null && param.getClick_event().equals("5")) {
				savedMode = true;
			}
			//Get file path
			BillingSystemSettings systemSetting = new BillingSystemSettings(queryDAO);			
			this.setFilePath(systemSetting.getFileLocation());
			//Get max file size (in MBytes)
			this.maxFileSize = systemSetting.getFileSizeUpload();
			param.setId_login(param.getUvo().getId_user());
			param.setId_creator(param.getUvo().getId_user());
			// check file size upload
			boolean fileExceed = false;
			
			FormFile[] files = param.getListFile();
			for(FormFile file : files) {
				if(file != null && file.getFileSize() > this.maxFileSize) {
					fileExceed = true;
				}
			}
			if(fileExceed) {
				Map<String, Object> reset = new HashMap<String, Object>();
				reset.put("attachment", "");
				result.setResultObject(reset);
				errors.add(Globals.MESSAGE_KEY, new BLogicMessage(ERR_FILE_SIZE_UPLOAD, new Object[] {this.maxFileSize, " byte(s)"}));
				result.setErrors(errors);
				result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
				return result;
			}
			//Get id_msg
			long old_id_msg = CommonUtils.toLong(param.getId_msg());
			//long id_msg = queryDAO.executeForObject(M_ALTR02BLogic.SQL_GET_MAX_ID_MSG, null, Long.class);
			//param.setId_msg(CommonUtils.toString(id_msg + 1));
			//Set priority,reminder date
			if(CommonUtils.isEmpty(param.getImportance_chk()) ||param.getImportance_chk().equals(M_ALTR01BLogic.CHECKBOX_OFF)){
				param.setPriority(null);
			}
			if(CommonUtils.isEmpty(param.getReminder_chk()) || param.getReminder_chk().equals(M_ALTR01BLogic.CHECKBOX_OFF)){
				param.setReminder_date(null);
			}
			
			if(savedMode) {
				//update header
				updateDAO.execute(M_ALTR02BLogic.SQL_UPDATE_HEADER_MSG, param);
			}
			else {
				//Insert header
				updateDAO.execute(M_ALTR02BLogic.SQL_INSERT_HEADER_MSG, param);	
			}
			// get ID_MSG
			Long id_msgL = queryDAO.executeForObject(M_ALTR02BLogic.SQL_GET_MAX_ID_MSG, null, Long.class);
			long id_msg = 0;
			if (id_msgL != null) {
			    id_msg = id_msgL.longValue();
			}
			param.setId_msg(CommonUtils.toString(id_msg ));
			//Set id_user = To_msg + cc_msg
			if(param.getTo_msg() != null && !param.getTo_msg().equals("")){
				if(param.getCc_msg() != null && !param.getCc_msg().equals("")){
					param.setId_user(param.getTo_msg().concat(M_ALTR01BLogic.ID_USER_DELIMITER).concat(param.getCc_msg()));
				}else{
					param.setId_user(param.getTo_msg());
				}
			}else{
				param.setId_user(param.getCc_msg());
			}
			String[] arrIdUser = new String[]{};
			
			if(param.getMsg_type().equals("TASK"))
				param.setId_user(param.getUvo().getId_user()+";");
			
			if(param.getId_user() != null && !param.getId_user().equals("")){
				arrIdUser = param.getId_user().split(M_ALTR01BLogic.ID_USER_DELIMITER);		
			}

			for(String idItem : arrIdUser){
				if(!idItem.equals("")){
					param.setId_user(idItem);
					//Insert detail
					try
					{
					updateDAO.execute(M_ALTR02BLogic.SQL_INSERT_DETAIL_MSG, param);
					}
					catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			
			if(savedMode) {
				//remove some file which removed by user
				String[] listFileIdOld = param.getListFileIdOld();
				FileInfo fileInfo = new FileInfo();
				fileInfo.setId_login(param.getId_login());
				fileInfo.setId_msg(Long.parseLong(param.getId_msg()));
				updateDAO.execute("UPDATE.M_ALT.SQL0014", fileInfo);
				if(listFileIdOld != null) {
					for(String idFile : listFileIdOld) {
						fileInfo.setId_doc(Long.parseLong(idFile));
						updateDAO.execute("UPDATE.M_ALT.SQL0015", fileInfo);
					}
				}
			}
			else {
				// get old file of previous msg and add with new msg
				// get older msgs
				List<FileInfo> olderFiles = queryDAO.executeForObjectList("SELECT.M_ALT.SQL009", old_id_msg);
				// save to new msg
				for(FileInfo fileInfo : olderFiles){
					fileInfo.setId_msg(CommonUtils.toLong(param.getId_msg()));
					updateDAO.execute(M_ALTR02BLogic.SQL_INSERT_M_ALT_ATC, fileInfo);
				}
			}
			//call G_DOC_P01 process to Upload file to server
			G_DOC_P01 g_doc = new G_DOC_P01(updateDAO,queryDAO);
			ArrayList<ArrayList<String>> ret = g_doc.uploadFile(param.getListFile(), param);
			if(ret!=null){
				if(ret.size()>0){
					param.setListFileName(ret.get(0));
			        param.setListFilePath(ret.get(1)); 
				}
			}
			messages.add(Globals.MESSAGE_KEY,new BLogicMessage(M_ALTR02BLogic.SAVE_SUCCESSFUL_MSG));
			result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS);
			result.setMessages(messages);
			
			return result;
		}catch(Exception ex){
			ex.printStackTrace();
			errors.add(Globals.MESSAGE_KEY,new BLogicMessage(M_ALTR02BLogic.SAVE_ERROR_MSG));
			result.setErrors(errors);
			result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
			return result;
		}
		
	}
	
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	public String getFilePath() {
		return filePath;
	}
}