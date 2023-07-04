package nttdm.bsys.e_dim_gr1.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class E_DIM_GR1_2BlogicOutput implements Serializable{
	private Integer row;
	private Integer totalCount;
	private List<TBatchLogDto> logList = new ArrayList<TBatchLogDto>();
	private String alertMsg;
	private Integer startIndex=0;
	private List<TGirImpHdDto> girImpHdDtoList = new ArrayList<TGirImpHdDto>();
	

	public List<TGirImpHdDto> getGirImpHdDtoList() {
		return girImpHdDtoList;
	}
	public void setGirImpHdDtoList(List<TGirImpHdDto> girImpHdDtoList) {
		this.girImpHdDtoList = girImpHdDtoList;
	}
	public String getAlertMsg() {
		return alertMsg;
	}
	public void setAlertMsg(String alertMsg) {
		this.alertMsg = alertMsg;
	}
	public Integer getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}
	public Integer getRow() {
		return row;
	}
	public void setRow(Integer row) {
		this.row = row;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public List<TBatchLogDto> getLogList() {
		return logList;
	}
	public void setLogList(List<TBatchLogDto> logList) {
		this.logList = logList;
	}
	
	
	
	
	
}
