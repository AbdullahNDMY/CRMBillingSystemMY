package nttdm.bsys.bif.blogic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import jp.terasoluna.fw.exception.SystemException;
import nttdm.bsys.bif.bean.FileUploadInfo;
import nttdm.bsys.bif.bean.WF_DOC;
import nttdm.bsys.common.dao.UpdateDAOiBatisNuked;
import nttdm.bsys.common.util.BillingSystemSettings;

import org.apache.struts.upload.FormFile;

public class BIFAttachmentProccessBLogic {
	private String filePath;
	private int maxFileSize ;
	private UpdateDAO updateDAO;
	private UpdateDAOiBatisNuked updateDAONuked;
	private QueryDAO queryDAO;
	private FormFile[] listFile;
	private String deletedList;
	private Integer idAudit = null;

	public BIFAttachmentProccessBLogic(QueryDAO query, UpdateDAO update, UpdateDAOiBatisNuked updateDAONuked) {
		this.queryDAO = query;
		this.updateDAO = update;
		this.updateDAONuked = updateDAONuked;
	}
	
	/**
	 * Insert attachment
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public void insertAttachment(String id_login,String id_ref, String fileType){
		
		BillingSystemSettings systemSetting = new BillingSystemSettings(queryDAO);
		this.filePath = systemSetting.getFileLocation();
		
		//Get max file size (in MBytes)
		this.maxFileSize = systemSetting.getFileSizeUpload();
		
		//Section group QCS 		
		FileUploadInfo fileUploadInfo = new FileUploadInfo();
		this.uploadFile(this.filePath, this.maxFileSize, this.getListFile(), fileUploadInfo);
		//this.insertFileToDB("BIF", id_login, id_ref, fileUploadInfo,fileType);
		BIFAttachmentBLogic.insertFileToDB("BIF", id_login, id_ref, fileUploadInfo,fileType, queryDAO, updateDAO, updateDAONuked, idAudit);
	}
	
	public void deleteAttachment(String idRef, String doc_type){
		WF_DOC wf_doc = new WF_DOC();
		wf_doc.setId_ref(idRef);
		wf_doc.setDoc_type(doc_type);
		if(this.getDeletedList() != null){
			String deletedAttachmentSC[] = this.getDeletedList().split(";");
			for(String deletedSC: deletedAttachmentSC){
				if(deletedSC!= null && !deletedSC.equals("")){
					wf_doc.setSection_group("BIF");
					wf_doc.setId_doc(deletedSC);
					updateDAO.execute(B_BIFS02_05BLogic.DELETE_T_WF_DOC, wf_doc);
				}
			}
		}
	}
	
	/**
	 * Save file to server
	 * @param filePath
	 * @param maxFileSize
	 * @param listFile
	 * @param listFileName
	 * @param listFilePath
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	private void uploadFile(String filePath,int maxFileSize,FormFile [] listFile, FileUploadInfo fileUploadInfo) {
		if (filePath == null || "".equals(filePath)) {         
            return;
        } else {
        	//Check path size
            this.pathCheck(filePath);
        }
		ArrayList<String> listFileName = new ArrayList<String>();
		ArrayList<String> listFilePath = new ArrayList<String>();
		try {
			
	        for(FormFile fileList : listFile){
	        	if (fileList != null) {
	                String fileNameList = fileList.getFileName();
	                int fileSizeList = fileList.getFileSize();
	                if (fileNameList != null && !"".equals(fileNameList)) {
	                	//valid file
	                    if (0 <= fileSizeList && fileSizeList <= maxFileSize) {
	                        //Upload file to server
	                    	InputStream streamList = fileList.getInputStream();
	                        OutputStream bosList = new FileOutputStream(filePath  + "/" + fileList.getFileName());	                        
	                        int bytesRead = 0;
	                        byte[] buffer = new byte[8192];	                        
	                        while ((bytesRead = streamList.read(buffer, 0, 8192)) != -1) {
	                        	bosList.write(buffer, 0, bytesRead);
	                        } 
	                        //Add file name, file size, file path
	                        listFileName.add(fileNameList);       
	                        fileNameList = this.determineFileName(filePath, fileNameList);
	                        listFilePath.add(filePath + "/" + fileNameList);	                                               
	                        bosList.close();
	                        streamList.close();
	                        fileList.destroy();
	                    }
	                }
	            }
	        }
		} catch(FileNotFoundException fne) {
			// do anything when have exception
			throw new SystemException(fne);
			
		} catch(IOException ioe) {
			throw new SystemException(ioe);
		}
        fileUploadInfo.setListFileName(listFileName);
        fileUploadInfo.setListFilePath(listFilePath);
	}
	/**
	 * Insert file info to DB
	 * @param section_group
	 * @param id_login
	 * @param id_ref
	 * @param listFileName
	 * @param listFilePath
	 */
	@SuppressWarnings("unchecked")
	private void insertFileToDB(String section_group,String id_login,String id_ref,FileUploadInfo fileUploadInfo,String docType){
		 ArrayList<String> listFileName = fileUploadInfo.getListFileName();
		 ArrayList<String> listFilePath = fileUploadInfo.getListFilePath(); 
		 
		 for(int i=0;i< listFileName.size();i++){
			 String id_doc = queryDAO.executeForObject(B_BIFS02_02BLogic.SQL_GET_MAX_ID_DOC, null, String.class);
			 
			 id_doc = (Integer.parseInt(id_doc) + 1) + "";
			 Map param = new HashMap();
			 param.put("idDoc", id_doc);
			 param.put("fileName", listFileName.get(i));
			 param.put("fileLocation", listFilePath.get(i));
			 param.put("idLogin", id_login);
			 param.put("docType", docType);		
			 param.put("sectionGroup", section_group);
			 param.put("idRef", id_ref);
			 //Insert to T_DOC table
			 updateDAO.execute(B_BIFS02_02BLogic.INSERT_T_DOC, param);
			 //Insert to T_WL_DOC table
			 updateDAO.execute(B_BIFS02_02BLogic.INSERT_T_WF_DOC, param);
		 }
	}
	/**
	 * Create path folder if it does not exist
	 * @param path
	 */
	private void pathCheck(String path) {
        File pfile = null;
        pfile = new File(path);
        if (!pfile.exists()) {
            pfile.mkdirs();
        }
    }
	private String determineFileName(String filePath,String fileName){
		String datePattern = "ddMMyyyyHHmmssS";
		String fName = filePath + "/" + fileName;
		File pfile = new File(fName);
		if (!pfile.exists()) {
            return fileName;
        }else{
        	SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
        	String dF = sdf.format(new Date());
        	int i = fileName.lastIndexOf(".");
        	String ext = fileName.substring(i,fileName.length());
        	String name = fileName.substring(0,i);
        	return name.concat(dF).concat(ext);
        }
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public int getMaxFileSize() {
		return maxFileSize;
	}

	public void setMaxFileSize(int maxFileSize) {
		this.maxFileSize = maxFileSize;
	}

	public UpdateDAO getUpdateDAO() {
		return updateDAO;
	}

	public void setUpdateDAO(UpdateDAO updateDAO) {
		this.updateDAO = updateDAO;
	}

	public UpdateDAOiBatisNuked getUpdateDAONuked() {
		return updateDAONuked;
	}

	public void setUpdateDAONuked(UpdateDAOiBatisNuked updateDAONuked) {
		this.updateDAONuked = updateDAONuked;
	}

	public QueryDAO getQueryDAO() {
		return queryDAO;
	}

	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}

	/**
	 * @return the listFile
	 */
	public FormFile[] getListFile() {
		return listFile;
	}

	/**
	 * @param listFile the listFile to set
	 */
	public void setListFile(FormFile[] listFile) {
		this.listFile = listFile;
	}

	/**
	 * @return the deletedList
	 */
	public String getDeletedList() {
		return deletedList;
	}

	/**
	 * @param deletedList the deletedList to set
	 */
	public void setDeletedList(String deletedList) {
		this.deletedList = deletedList;
	}
	
	public Integer getIdAudit() {
		return idAudit;
	}

	public void setIdAudit(Integer idAudit) {
		this.idAudit = idAudit;
	}
}
