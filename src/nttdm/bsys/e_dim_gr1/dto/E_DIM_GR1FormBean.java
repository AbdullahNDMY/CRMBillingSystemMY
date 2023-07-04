package nttdm.bsys.e_dim_gr1.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;

import org.apache.struts.upload.FormFile;

public class E_DIM_GR1FormBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 65310543564733578L;
	private List<TGirImpHdDto> girImpHdDtoList = new ArrayList<TGirImpHdDto>();
	private FormFile fileName;
	private Integer totalCount=0;
	private Integer row=0;
	private Integer startIndex=0;
	private String errorMessage;
	private String logDate;
	private String paging;
	private String alertMsg;
	private String filename;
	
	
	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getAlertMsg() {
		return alertMsg;
	}

	public void setAlertMsg(String alertMsg) {
		this.alertMsg = alertMsg;
	}

	private List<TBatchLogDto> logList = new ArrayList<TBatchLogDto>();
	private String idGirImpBatch;
	

	public String getPaging() {
		return paging;
	}

	public void setPaging(String paging) {
		this.paging = paging;
	}

	public FormFile getFileName() {
		return fileName;
	}

	public void setFileName(FormFile fileName) {
		this.fileName = fileName;
	}

	public List<TBatchLogDto> getLogList() {
		return logList;
	}

	public void setLogList(List<TBatchLogDto> logList) {
		this.logList = logList;
	}

	public String getIdGirImpBatch() {
		return idGirImpBatch;
	}

	public void setIdGirImpBatch(String idGirImpBatch) {
		this.idGirImpBatch = idGirImpBatch;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getLogDate() {
		return logDate;
	}

	public void setLogDate(String logDate) {
		this.logDate = logDate;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getRow() {
		return row;
	}

	public void setRow(Integer row) {
		this.row = row;
	}

	public Integer getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

	public List<TGirImpHdDto> getGirImpHdDtoList() {
		if (this.girImpHdDtoList == null){
			return new ArrayList<TGirImpHdDto>();
		}
		return girImpHdDtoList;
	}

	public void setGirImpHdDtoList(List<TGirImpHdDto> girImpHdDtoList) {
		this.girImpHdDtoList = girImpHdDtoList;
	}

	
	

}
