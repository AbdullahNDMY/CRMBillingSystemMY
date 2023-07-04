package nttdm.bsys.b_cpm.dto;
import java.util.*;
public class Tab {

	private String name;
	private String id;
	private String startIndex;
	private String totalCount;
	private String row;
	private List<String> listStatus;
	private Plan plan;
	public Tab(){
		plan = null;
		row = "1";		
	}
	public Tab(String id){
		this.id = id;
		List<String> listStatus = new ArrayList<String>();
		if(id.equals("newplan")){
			listStatus.add("PS1");
			listStatus.add("PS2");
		} else if(id.equals("activeplan")){
			listStatus.add("PS3");
			listStatus.add("PS6");		
		} else if(id.equals("terminatedplan")){
			listStatus.add("PS7");	
		} else if(id.equals("rejectedplan")){
			listStatus.add("PS8");
		} else if(id.equals("inactiveplan")){
			listStatus.add("PS4");
		} else if(id.equals("onetime")){
			listStatus.add("PS5");
		}
		this.listStatus = listStatus;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(String startIndex) {
		this.startIndex = startIndex;
	}
	public String getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}
	public String getRow() {
		return row;
	}
	public void setRow(String row) {
		this.row = row;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Plan getPlan() {
		return plan;
	}
	public void setPlan(Plan plan) {
		this.plan = plan;
	}
	public List<String> getListStatus() {
		return listStatus;
	}
	public void setListStatus(List<String> listStatus) {
		this.listStatus = listStatus;
	}
	
}
