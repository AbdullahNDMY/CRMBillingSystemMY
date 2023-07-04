package nttdm.bsys.bif.blogic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import nttdm.bsys.bif.bean.FileUploadInfo;
import nttdm.bsys.bif.bean.WF_DOC;
import nttdm.bsys.common.dao.UpdateDAOiBatisNuked;
import nttdm.bsys.common.util.BillingSystemSettings;

import org.apache.struts.upload.FormFile;

public class BIFAttachmentBLogic {
	private String filePath;
	private int maxFileSize ;
	private UpdateDAO updateDAO;
	private UpdateDAOiBatisNuked updateDAONuked;
	private QueryDAO queryDAO;
	private FormFile[] listFileSCBif;
	private FormFile[] listFileQPBif;
	private FormFile[] listFileOTBif;
	private String deletedAttachmentSC;
	private String deletedAttachmentQP;
	private String deletedAttachmentOT;
	private Integer idAudit = null;
	
	/**
	 * Insert attachment
	 */
	public void insertAttachment(String id_login,String id_ref){
		
		BillingSystemSettings systemSetting = new BillingSystemSettings(queryDAO);
		this.filePath = systemSetting.getFileLocation();
		
		//Get max file size (in MBytes)
		this.maxFileSize = systemSetting.getFileSizeUpload();
		
		//Section group QCS 		
		FileUploadInfo fileUploadInfo = new FileUploadInfo();
		this.uploadFile(this.filePath, this.maxFileSize, this.getListFileSCBif(), fileUploadInfo);
		insertFileToDB("BIF", id_login, id_ref, fileUploadInfo,"DSGC", this.queryDAO, this.updateDAO, this.updateDAONuked, this.idAudit);
		
		this.uploadFile(this.filePath, this.maxFileSize, this.getListFileQPBif(), fileUploadInfo);
		insertFileToDB("BIF", id_login, id_ref, fileUploadInfo,"DQCP", this.queryDAO, this.updateDAO, this.updateDAONuked, this.idAudit);
		
		this.uploadFile(this.filePath, this.maxFileSize, this.getListFileOTBif(), fileUploadInfo);
		insertFileToDB("BIF", id_login, id_ref, fileUploadInfo,"DOTH", this.queryDAO, this.updateDAO, this.updateDAONuked, this.idAudit);
	}
	
	public void deleteAttachment(String idRef){
		WF_DOC wf_doc = new WF_DOC();
		wf_doc.setId_ref(idRef);
		wf_doc.setDoc_type("DSGC");
		wf_doc.setIdAudit(idAudit);
		if(this.getDeletedAttachmentSC() != null){
			String deletedAttachmentSC[] = this.getDeletedAttachmentSC().split(";");
			for(String deletedSC: deletedAttachmentSC){
				if(deletedSC!= null && !deletedSC.equals("")){
					wf_doc.setSection_group("BIF");
					wf_doc.setId_doc(deletedSC);
					updateDAO.execute(B_BIFS02_05BLogic.DELETE_T_WF_DOC, wf_doc);
				}
			}
		}
		wf_doc.setDoc_type("DQCP");
		if(this.getDeletedAttachmentQP() != null){
			String deletedAttachmentQP[] = this.getDeletedAttachmentQP().split(";");
			for(String deletedQP: deletedAttachmentQP){
				if(deletedQP!= null && !deletedQP.equals("")){
					wf_doc.setSection_group("BIF");
					wf_doc.setId_doc(deletedQP);
					updateDAO.execute(B_BIFS02_05BLogic.DELETE_T_WF_DOC, wf_doc);
				}
			}
		}
		wf_doc.setDoc_type("DOTH");
		if(this.getDeletedAttachmentOT() != null){
			String deletedAttachmentOT[] = this.getDeletedAttachmentOT().split(";");
			for(String deletedOT: deletedAttachmentOT){
				if(deletedOT!= null && !deletedOT.equals("")){
					wf_doc.setSection_group("BIF");
					wf_doc.setId_doc(deletedOT);
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
	 */
	private void uploadFile(String filePath,int maxFileSize,FormFile [] listFile, FileUploadInfo fileUploadInfo){
		if (filePath == null || "".equals(filePath)) {         
            return;
        } else {
        	//Check path size
            pathCheck(filePath);
        }
		ArrayList<String> listFileName = new ArrayList<String>();
		ArrayList<String> listFilePath = new ArrayList<String>();
        for(FormFile fileList : listFile){
        	if (fileList != null) {
                String fileNameList = fileList.getFileName();
                int fileSizeList = fileList.getFileSize();
                if (fileNameList != null && !"".equals(fileNameList)) {
                	//valid file
                    if (0 <= fileSizeList && fileSizeList <= maxFileSize) {
                    	try{
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
                    	}catch(Exception ex){
                    		
                    	}
                    }
                }
            }
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
	public static synchronized void insertFileToDB(String section_group,String id_login,String id_ref,FileUploadInfo fileUploadInfo,String docType, QueryDAO queryDAO, UpdateDAO updateDAO, UpdateDAOiBatisNuked updateDAONuked, Integer idAudit){
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
			 param.put("idAudit", idAudit);
			 //Insert to T_DOC table
			 //updateDAO.execute(B_BIFS02_02BLogic.INSERT_T_DOC, param);
			 String strNewDocID = updateDAONuked.insert(B_BIFS02_02BLogic.INSERT_T_DOC, param).toString();
			 param.put("idDoc", strNewDocID);
			 //Get id_doc
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

	public QueryDAO getQueryDAO() {
		return queryDAO;
	}

	public UpdateDAOiBatisNuked getUpdateDAONuked() {
		return updateDAONuked;
	}

	public void setUpdateDAONuked(UpdateDAOiBatisNuked updateDAONuked) {
		this.updateDAONuked = updateDAONuked;
	}

	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}

	public FormFile[] getListFileSCBif() {
		return listFileSCBif;
	}

	public void setListFileSCBif(FormFile[] listFileSCBif) {
		this.listFileSCBif = listFileSCBif;
	}

	public FormFile[] getListFileQPBif() {
		return listFileQPBif;
	}

	public void setListFileQPBif(FormFile[] listFileQPBif) {
		this.listFileQPBif = listFileQPBif;
	}

	public FormFile[] getListFileOTBif() {
		return listFileOTBif;
	}

	public void setListFileOTBif(FormFile[] listFileOTBif) {
		this.listFileOTBif = listFileOTBif;
	}

	public String getDeletedAttachmentSC() {
		return deletedAttachmentSC;
	}

	public void setDeletedAttachmentSC(String deletedAttachmentSC) {
		this.deletedAttachmentSC = deletedAttachmentSC;
	}

	public String getDeletedAttachmentQP() {
		return deletedAttachmentQP;
	}

	public void setDeletedAttachmentQP(String deletedAttachmentQP) {
		this.deletedAttachmentQP = deletedAttachmentQP;
	}

	public String getDeletedAttachmentOT() {
		return deletedAttachmentOT;
	}

	public void setDeletedAttachmentOT(String deletedAttachmentOT) {
		this.deletedAttachmentOT = deletedAttachmentOT;
	}
	
	public Integer getIdAudit() {
		return idAudit;
	}

	public void setIdAudit(Integer idAudit) {
		this.idAudit = idAudit;
	}
}
