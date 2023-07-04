/**
 * @(#)G_DOC_P01.java
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import org.apache.struts.upload.FormFile;

import nttdm.bsys.m_alt.bean.FileInfo;
import nttdm.bsys.m_alt.blogic.M_ALTR02BLogic;
import nttdm.bsys.m_alt.dto.M_ALTR02Input;
import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

/**
 * Upload File Process
 * 
 * @author lixinj
 */
public class G_DOC_P01 {
	/**
	 * <div>SQL_INSERT_T_DOC</div>
	 */
	private static final String SQL_INSERT_T_DOC = "INSERT.M_ALT.SQL007";
	/**
	 * <div>SQL_INSERT_M_ALT_ATC</div>
	 */
	private static final String SQL_INSERT_M_ALT_ATC = "INSERT.M_ALT.SQL008";

	/**
	 * Notification
	 */
	private static final String STR_NOTIFICATION = "Notification";

	/**
	 * type of message :NOTICE
	 */
	private static final String MSG_TYPE_NOTICE = "NOTICE";
	/**
	 * type of message :TASK
	 */
	private static final String MSG_TYPE_TASK = "TASK";

	/**
	 * updateDAO
	 */
	private UpdateDAO updateDAO;
	
	/**
	 * <div>queryDAO</div>
	 */
	private QueryDAO queryDAO = null;

	/**
	 * Upload file path
	 */
	private String filePath = "";
	
	/**
	 * Upload file's max size
	 */
	private int maxFileSize = 0;

	/**
	 * <div>Get updateDAO</div>
	 * 
	 * @return updateDAO
	 */
	public UpdateDAO getUpdateDAO() {
		return updateDAO;
	}

	/**
	 * <div>Set updateDAO</div>
	 * 
	 * @param updateDAO
	 */
	public void setUpdateDAO(UpdateDAO updateDAO) {
		this.updateDAO = updateDAO;
	}

	/**
	 * <div>Constructor</div>
	 * 
	 * @param updateDAO
	 * 
	 * @param queryDAO
	 */
	public G_DOC_P01(UpdateDAO updateDAO, QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
		this.updateDAO = updateDAO;
		// Get file path
		BillingSystemSettings systemSetting = new BillingSystemSettings(queryDAO);
		this.filePath = systemSetting.getFileLocation();
		// Get max file size (in MBytes)
		this.maxFileSize = systemSetting.getFileSizeUpload();
	}

	/**
	 * Insert file into database
	 * 
	 * @param fileInfo
	 *            FileInfo
	 * @throws Exception
	 * */
	private void insertFileToDB(FileInfo fileInfo) throws Exception {
		try {
			// Insert file info to t_doc table
			updateDAO.execute(SQL_INSERT_T_DOC, fileInfo);
			// Insert file info to m_alt_atc table
			updateDAO.execute(SQL_INSERT_M_ALT_ATC, fileInfo);
		} catch (Exception ex) {
			throw ex;
		}
	}

	/**
	 * Upload file to server
	 * 
	 * @param listFile
	 *            FormFile []
	 * @param param
	 *            M_ALTR02Input
	 * @throws Exception
	 */
	public ArrayList<ArrayList<String>> uploadFile(FormFile[] listFile,
			M_ALTR02Input param) throws Exception {
		ArrayList<ArrayList<String>> ret = new ArrayList<ArrayList<String>>();
		if (this.filePath == null || "".equals(this.filePath)) {
			return ret;
		} else {
			// Check path size
			pathCheck(filePath);
		}

		// Get length of form file
		int formFileLength = 0;
		if (listFile != null) {
			formFileLength = listFile.length;
		}

		ArrayList<String> listFileName = new ArrayList<String>();
		ArrayList<String> listFilePath = new ArrayList<String>();
		pathAssemble(param.getMsg_type());
		for (int i = 0; i < formFileLength; i++) {

			FormFile fileList = listFile[i];
			Long idDocL = queryDAO.executeForObject(
					M_ALTR02BLogic.SQL_GET_MAX_ID_DOC, null, Long.class);
			long idDoc = 0;
			if (idDocL != null) {
			    idDoc = idDocL.longValue();
			}
			idDoc++;
			if (fileList != null) {

				String fileNameList = fileList.getFileName();
				int fileSizeList = fileList.getFileSize();
				if (fileNameList != null && !"".equals(fileNameList)) {
					// valid file
					String realPath = filePath + "/" + idDoc + "_" + fileList.getFileName();
					if (0 <= fileSizeList && fileSizeList <= this.maxFileSize) {
						// Upload file to server
						InputStream streamList = fileList.getInputStream();
						OutputStream bosList = new FileOutputStream(realPath);

						int bytesRead = 0;
						byte[] buffer = new byte[8192];

						while ((bytesRead = streamList.read(buffer, 0, 8192)) != -1) {
							bosList.write(buffer, 0, bytesRead);
						}

						listFileName.add(fileNameList);
						listFilePath.add(realPath);

						bosList.close();
						streamList.close();
						fileList.destroy();

						// insert to db
						FileInfo fileInfo = new FileInfo();
						fileInfo.setId_login(param.getId_login());
						fileInfo.setId_msg(CommonUtils.toLong(param.getId_msg()));
						fileInfo.setFilename(fileNameList);
						fileInfo.setFilelocation(realPath);
						fileInfo.setId_doc(idDoc);
						insertFileToDB(fileInfo);
					}
				}
			}
		}
		ret.add(listFileName);
		ret.add(listFilePath);
		return ret;
	}

	/**
	 * check the path of file exists
	 * 
	 * @param path
	 *            file path
	 */
	private void pathCheck(String path) {
		File pfile = null;
		pfile = new File(path);
		if (!pfile.exists()) {
			pfile.mkdirs();
		}
	}

	/**
	 * assemble path
	 * 
	 * @param msgType
	 *            Message Type
	 */
	private void pathAssemble(String msgType) {

		if (MSG_TYPE_NOTICE.equals(msgType) || MSG_TYPE_TASK.equals(msgType)) {
			filePath = filePath + "/" + STR_NOTIFICATION;
		}
		pathCheck(filePath);
	}
}
