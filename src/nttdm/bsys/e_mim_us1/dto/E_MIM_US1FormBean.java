package nttdm.bsys.e_mim_us1.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.e_dim_gr1.dto.TBatchLogDto;

import org.apache.struts.upload.FormFile;

public class E_MIM_US1FormBean implements Serializable {
	// from E_MIM_US1BlogicInput

	private Integer totalCount=0;
	private Integer row=0;
	private Integer startIndex=0;
	
	
	private BillingSystemUserValueObject uvo;
	private String closingMonth;
	private String closingYear;
	


	public String getClosingMonth() {
		return closingMonth;
	}

	public void setClosingMonth(String closingMonth) {
		this.closingMonth = closingMonth;
	}

	public String getClosingYear() {
		return closingYear;
	}

	public void setClosingYear(String closingYear) {
		this.closingYear = closingYear;
	}

	// from E_MIM_US1BlogicOutput
	private List<TIpassImpHdDto> ipassList = new ArrayList<TIpassImpHdDto>();
	private String alertMsg;
	private FormFile fileName;
	private String idIpassImpBatch;
	private List<TBatchLogDto> logList = new ArrayList<TBatchLogDto>();
	private String csvFileName;
	
	
	public String getCsvFileName() {
		return csvFileName;
	}

	public void setCsvFileName(String csvFileName) {
		this.csvFileName = csvFileName;
	}

	public BillingSystemUserValueObject getUvo() {
		return uvo;
	}

	public void setUvo(BillingSystemUserValueObject uvo) {
		this.uvo = uvo;
	}

	public List<TBatchLogDto> getLogList() {
		return logList;
	}

	public void setLogList(List<TBatchLogDto> logList) {
		this.logList = logList;
	}

	public String getIdIpassImpBatch() {
		return idIpassImpBatch;
	}

	public void setIdIpassImpBatch(String idIpassImpBatch) {
		this.idIpassImpBatch = idIpassImpBatch;
	}

	public FormFile getFileName() {
		return fileName;
	}

	public void setFileName(FormFile fileName) {
		this.fileName = fileName;
	}

	public String getAlertMsg() {
		return alertMsg;
	}

	public void setAlertMsg(String alertMsg) {
		this.alertMsg = alertMsg;
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

	public List<TIpassImpHdDto> getIpassList() {
		return ipassList;
	}

	public void setIpassList(List<TIpassImpHdDto> ipassList) {
		this.ipassList = ipassList;
	}
	
	
}
