
package nttdm.bsys.batch;
import java.util.Calendar;

import nttdm.bsys.common.dao.UpdateDAOiBatisNukedImpl;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.G_RAD_P01;
import nttdm.bsys.e_mim.dto.RP_E_MIM_US3_04Input;
import nttdm.bsys.e_mim.dto.RP_E_MIM_US3_04Output;
import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

public class G_RAD_P01_Thread implements Runnable{

	private QueryDAO queryDAO;
	private UpdateDAO updateDAO;
	private UpdateDAOiBatisNukedImpl updateDAONuked;
	
	private BillingSystemUserValueObject uvo;
	private String sch_day;
	private String sch_hours;
	private String sch_mim;
	private String sch_month;
	private String sch_year;
	
		public QueryDAO getQueryDAO() {
		return queryDAO;
	}
		
	public G_RAD_P01_Thread(QueryDAO query, UpdateDAO update,UpdateDAOiBatisNukedImpl updateNuked,BillingSystemUserValueObject ov,String day,String hour,String minute,String month, String year)
	{
		queryDAO=query;
		updateDAO=update;
		updateDAONuked = updateNuked;
		uvo=ov;	
		sch_day=day;
		sch_hours=hour;
		sch_mim=minute;
		sch_month=month;
		sch_year=year;
	}
		
	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}	public UpdateDAO getUpdateDAO() {
		return updateDAO;
	}
	public void setUpdateDAO(UpdateDAO updateDAO) {
		this.updateDAO = updateDAO;
	}
	public UpdateDAOiBatisNukedImpl getUpdateDAONuked() {
		return updateDAONuked;
	}

	public void setUpdateDAONuked(UpdateDAOiBatisNukedImpl updateDAONuked) {
		this.updateDAONuked = updateDAONuked;
	}

	public String getSch_day() {
		return sch_day;
	}

	public String getSch_month() {
		return sch_month;
	}

	public void setSch_month(String sch_month) {
		this.sch_month = sch_month;
	}

	public String getSch_year() {
		return sch_year;
	}

	public void setSch_year(String sch_year) {
		this.sch_year = sch_year;
	}

	public void setSch_day(String sch_day) {
		this.sch_day = sch_day;
	}

	public String getSch_hours() {
		return sch_hours;
	}

	public void setSch_hours(String sch_hours) {
		this.sch_hours = sch_hours;
	}

	public String getSch_mim() {
		return sch_mim;
	}

	public void setSch_mim(String sch_mim) {
		this.sch_mim = sch_mim;
	}

	public BillingSystemUserValueObject getUvo() {
		return uvo;
	}

	public void setUvo(BillingSystemUserValueObject uvo) {
		this.uvo = uvo;
	}
	public void run() {
		RP_E_MIM_US3_04Input input= new RP_E_MIM_US3_04Input();
		
		Calendar calen= Calendar.getInstance();
		//Date current=calen.getTime();
		int current_month=calen.get(Calendar.MONTH)+1;
		int current_year=calen.get(Calendar.YEAR);
		input.setMonth(current_month+"");
		input.setYear(current_year+"");		
		input.setUvo(uvo);
		RP_E_MIM_US3_04Output output=new RP_E_MIM_US3_04Output();
		
		G_RAD_P01 g_radp01= new G_RAD_P01();
		
		g_radp01.setQueryDAO(queryDAO);
		g_radp01.setUpdateDAO(updateDAO);
		g_radp01.setUpdateDAONuked(updateDAONuked);
		g_radp01.execute(input,output);
		
	}
	

}


