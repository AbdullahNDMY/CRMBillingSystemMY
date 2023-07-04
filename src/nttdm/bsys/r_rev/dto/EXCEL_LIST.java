package nttdm.bsys.r_rev.dto;

public class EXCEL_LIST {
	
	private String totalFile;
	private String fileDate;
	private String fileName;
	private String filePath;
	
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getTotalFile() {
		return totalFile;
	}
	public void setTotalFile(String totalFile) {
		this.totalFile = totalFile;
	}
	
	public String getFileDate() {
		return fileDate;
	}
	public void setFileDate(String fileDate) {
		this.fileDate = fileDate;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
