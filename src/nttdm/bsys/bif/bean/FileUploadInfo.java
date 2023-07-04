package nttdm.bsys.bif.bean;

import java.util.ArrayList;

public class FileUploadInfo {
	ArrayList<String> listFileName ;
	ArrayList<String> listFilePath ;
	public FileUploadInfo(){
		listFileName = new ArrayList<String>();
		listFilePath = new ArrayList<String>();
	}
	public ArrayList<String> getListFileName() {
		return listFileName;
	}
	public void setListFileName(ArrayList<String> listFileName) {
		this.listFileName = listFileName;
	}
	public ArrayList<String> getListFilePath() {
		return listFilePath;
	}
	public void setListFilePath(ArrayList<String> listFilePath) {
		this.listFilePath = listFilePath;
	}
	
}
