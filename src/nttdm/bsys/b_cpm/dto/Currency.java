package nttdm.bsys.b_cpm.dto;

public class Currency {
	private String rate_date;
	private String cur_code;
	private String cur_name;		//This attribute is got from RESOURCE FILE
	
	public String getCur_name() {
		return cur_name;
	}
	public void setCur_name(String cur_name) {
		this.cur_name = cur_name;
	}
	public String getRate_date() {
		return rate_date;
	}
	public void setRate_date(String rate_date) {
		this.rate_date = rate_date;
	}
	public String getCur_code() {
		return cur_code;
	}
	public void setCur_code(String cur_code) {
		this.cur_code = cur_code;
	}

	
}
