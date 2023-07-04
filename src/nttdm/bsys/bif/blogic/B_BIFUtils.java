package nttdm.bsys.bif.blogic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import nttdm.bsys.common.util.CommonUtils;

public class B_BIFUtils {
    
    private QueryDAO query;
    private UpdateDAO update;
    
    public B_BIFUtils(QueryDAO query){
        this.query = query;
        this.update = null;
    }
    public B_BIFUtils(UpdateDAO update){
        this.update = update;
        this.query = null;
    }
    public B_BIFUtils(QueryDAO query, UpdateDAO update){
        this.query = query;
        this.update = update;
    }
	
	public static void get_Amount(Map<String, Object> x, Map<String, Object> w, Map<String, Object> u) {
		/*Double inAmt0 = Double.parseDouble(x.get("UNIT_PRICE").toString());
		Double inAmt1 = Double.parseDouble(x.get("TOTAL_AMOUNT").toString());
		int mMode = -1;
		int mBI = -1;
		if(u.get("RATE_MODE").toString().equals("6")) {
			// no thing
		}
		else {
			mMode = get_No_Of_Month(u.get("RATE_MODE").toString());
			if(w.get("BILL_INSTRUCT").toString().equals("6")) {
				mBI = 1;
			}
			else {
				mBI = get_No_Of_Month(w.get("BILL_INSTRUCT").toString());
			}		
			inAmt0 = (inAmt0 / mMode) * mBI;
			inAmt1 = (inAmt1 / mMode) * mBI;
		}
		x.put("UNIT_PRICE", inAmt0);
		x.put("TOTAL_AMOUNT", inAmt1);*/
	}
	
	private static int get_No_Of_Month(String xMode) {
		int xMonth = 1;
		try {
			Integer n = Integer.parseInt(xMode);
			switch(n) {
			case 1: {
				xMonth = 12;
				break;
			}
			case 2: {
				xMonth = 6;
				break;
			}
			case 3: {
				xMonth = 3;
				break;
			}
			case 4: {
				xMonth = 2;
				break;
			}
			default: {
				xMonth = 1;
				break;
			}
			}
		} catch(Exception e) {}
		return xMonth;
	}
	
	public static String getBifStatus(QueryDAO queryDAO, String idRef) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("ID_REF", idRef);
		Map<String, Object> bifObject = queryDAO.executeForMap("SELECT.B_BIF.S03_01.SQL001", parameter);
		return CommonUtils.toString(bifObject.get("ID_STATUS"));
	}
	
	public List<Map<String, Object>> getPlanBatchMappingByIdCustPlan(String idCustPlan) {
        return query.executeForMapList("BIF.GET_BATCH_MAPPING_BY_ID_CUST_PLAN", idCustPlan);
    }
	
	public Map<String, Object> getSubScriptionInfoByIdCustPlan(String idCustPlan) {
        return query.executeForMap("BIF.GET_SUBSCIPTION_BY_ID_CUST_PLAN", idCustPlan);
    }
}