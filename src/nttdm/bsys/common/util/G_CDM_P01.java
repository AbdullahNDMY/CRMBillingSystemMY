/**
 * @(#)G_BIL_P01.java
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */

package nttdm.bsys.common.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import nttdm.bsys.common.bean.M_CODEBean;

/**
 * Process for Running No. generation
 * 
 * @author bench
 */
public class G_CDM_P01 {
	/**
	 * <div>queryDAO</div>
	 */
	private QueryDAO queryDAO;
	/**
	 * <div>updateDAO</div>
	 */
	private UpdateDAO updateDAO = null;
	/**
	 * <div>id_user</div>
	 */
	private String id_user;
	
	/**
	 * <div>Current Month Flag</div>
	 */
    private String executeMonth = "1"; 
	/**
	 * <div>SELECT_Running_No</div>
	 */
	private static final String SELECT_Running_No = "SELECT.BSYS.SQL002";
	/**
	 * <div>SELECT_NumberofRow</div>
	 */
	private static final String SELECT_NumberofRow = "SELECT.BSYS.SQL019";
	/**
	 * <div>UPDATE_CurVal</div>
	 */
	private static final String UPDATE_CurVal = "UPDATE.BSYS.SQL001";
	/**
	 * <div>INSERT_M_CODE_HISTORY</div>
	 */
	private static final String INSERT_M_CODE_HISTORY = "INSERT.BSYS.SQL006";

	/**
	 * <div>getQueryDAO</div>
	 */
	public QueryDAO getQueryDAO() {
		return queryDAO;
	}

	/**
	 * <div>getId_user</div>
	 */
	public String getId_user() {
		return id_user;
	}

	/**
	 * <div>setId_user</div>
	 */
	public void setId_user(String id_user) {
		this.id_user = id_user;
	}

	/**
	 * <div>setQueryDAO</div>
	 */
	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}

	/**
	 * <div>getUpdateDAO</div>
	 */
	public UpdateDAO getUpdateDAO() {
		return updateDAO;
	}

	/**
	 * <div>setUpdateDAO</div>
	 */
	public void setUpdateDAO(UpdateDAO updateDAO) {
		this.updateDAO = updateDAO;
	}

    /**
     * @return the currentMonth
     */
    public String getExecuteMonth() {
        return executeMonth;
    }

    /**
     * @param currentMonth the currentMonth to set
     */
    public void setExecuteMonth(String executeMonth) {
        this.executeMonth = executeMonth;
    }

	/**
	 * <div>Constructor</div>
	 * 
	 * @param queryDAO QueryDAO
	 * @param updateDAO UpdateDAO
	 * @param id_user UserID
	 */
	public G_CDM_P01(QueryDAO queryDAO, UpdateDAO updateDAO, String id_user) {
		// pass required parameters
		this.queryDAO = queryDAO;
		this.updateDAO = updateDAO;
		this.id_user = id_user;
	}

    /**
     * <div>Init database data and set date to current year</div>
     * 
     * @param id_code CodeType
     * @param id_user UserID
     */
    private void sub1(String id_code, String id_user) {
        // declare new M_CODEBean
        M_CODEBean m_code = new M_CODEBean();
        // pass parameter for m_code
        m_code.setId_code(id_code);
        m_code.setId_login(id_user);
        // new array list for sql result
        List<M_CODEBean> codeBean = new ArrayList<M_CODEBean>();
        // get data from db
        codeBean = queryDAO.executeForObjectList(SELECT_Running_No, m_code);
        // get current date
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        String day = dateFormat.format(date);
        for (M_CODEBean bean : codeBean) {
            // insert data into m_code_history
            // INSERT_M_CODE_HISTORY
            updateDAO.execute(INSERT_M_CODE_HISTORY, bean);
            // update data to m_code
            String current_value = "";
            String initial_val = "";
            if (bean.getType_val().equalsIgnoreCase("01")
                || bean.getType_val().equalsIgnoreCase("02")
                || bean.getType_val().equalsIgnoreCase("03")
                || bean.getType_val().equalsIgnoreCase("04")
                || bean.getType_val().equalsIgnoreCase("07")
                || bean.getType_val().equalsIgnoreCase("08")) {
                current_value = day.substring(0, 6);
                initial_val = bean.getInit_val();
            } else if (bean.getType_val().equalsIgnoreCase("05")) {
                current_value = reset(bean.getCur_val());
                initial_val = current_value;
            } else if (bean.getType_val().equalsIgnoreCase("06")) {
                current_value = bean.getCur_val();
                initial_val = bean.getInit_val();
            } else if (bean.getType_val().equalsIgnoreCase("09")) {
                current_value = day.substring(0, 6);
                initial_val = bean.getInit_val();
            }
            // increase curval value
            bean.setCur_val(current_value);
            bean.setInit_val(initial_val);
            bean.setId_login(id_user);
            // update new curval into dbUPDATE_CurVal
            updateDAO.execute(UPDATE_CurVal, bean);
        }
    }
    
    /**
     * <div>For statement of account running no if reset by monthly.</div>
     * <div>Move current value to init vale for previous month, reset new month to zero.</div>
     * 
     * @param id_code CodeType
     * @param id_user UserID
     */
    private void sub2(String id_code, String id_user) {
        // declare new M_CODEBean
        M_CODEBean m_code = new M_CODEBean();
        // pass parameter for m_code
        m_code.setId_code(id_code);
        m_code.setId_login(id_user);
        // new array list for sql result
        List<M_CODEBean> codeBean = new ArrayList<M_CODEBean>();
        // get data from db
        codeBean = queryDAO.executeForObjectList(SELECT_Running_No, m_code);
        // get current date
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        String day = dateFormat.format(date);
        for (M_CODEBean bean : codeBean) {
            // insert data into m_code_history
            // INSERT_M_CODE_HISTORY
            updateDAO.execute(INSERT_M_CODE_HISTORY, bean);
            // update data to m_code
            String current_value = "";
            String initial_val = "";
            if (bean.getType_val().equalsIgnoreCase("01")
                || bean.getType_val().equalsIgnoreCase("02")
                || bean.getType_val().equalsIgnoreCase("07")
                || bean.getType_val().equalsIgnoreCase("08")
                || bean.getType_val().equalsIgnoreCase("09")) {
                current_value = day.substring(0, 6);
                initial_val = bean.getCur_val();
            } else if (bean.getType_val().equalsIgnoreCase("05")) {
                current_value = reset(bean.getCur_val());
                initial_val = bean.getCur_val();
            } else if (bean.getType_val().equalsIgnoreCase("06")) {
                current_value = bean.getCur_val();
                initial_val = bean.getInit_val();
            }
            
            // increase curval value
            bean.setCur_val(current_value);
            bean.setInit_val(initial_val);
            bean.setId_login(id_user);
            // update new curval into dbUPDATE_CurVal
            updateDAO.execute(UPDATE_CurVal, bean);
        }
    }
    
    /**
     * Generate Statement of Account No.
     * 
     * @param id_code CodeType
     * @param id_user UserID
     * @param arg additional parameters
     * @return
     */
    private String generateSOACode(String id_code, String id_user) {
        String ret = "";
        
        // get generated date
        Calendar c1 = Calendar.getInstance();
        c1.setTime(new Date());
        if (!"1".equals(executeMonth)){
            // previous month
            c1.add(Calendar.MONTH, -1);
        }
        Date date = c1.getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String day = dateFormat.format(date);
        
        // declare new M_CODEBean, and pass parameter for m_code
        M_CODEBean m_code = new M_CODEBean();
        m_code.setId_code(id_code);
        m_code.setId_login(id_user);
        // new array list for sql result
        List<M_CODEBean> codeBean = new ArrayList<M_CODEBean>();
        // get data from db
        codeBean = queryDAO.executeForObjectList(SELECT_Running_No, m_code);

        // loop for each row to get generate code
        for (M_CODEBean bean : codeBean) {
            if (bean.getType_val().equalsIgnoreCase("01")) {
                // YY
                ret = ret + day.substring(2, 4);
            } else if (bean.getType_val().equalsIgnoreCase("02")) {
                // YYYY
                ret = ret + day.substring(0, 4);
            } else if (bean.getType_val().equalsIgnoreCase("03")) {
                // YYYYMMDD
                ret = ret + day;
            } else if (bean.getType_val().equalsIgnoreCase("04")) {
                // YYMMDD
                ret = ret + day.substring(2);
            } else if (bean.getType_val().equalsIgnoreCase("05")) {
                if ("1".equals(executeMonth)) {
                    // Current Month: increase CUR_VAL value
                    bean.setId_login(id_user);
                    bean.setCur_val(increaseCurVal(bean.getCur_val().trim()));
                    // update new CUR_VAL into dbUPDATE_CurVal
                    updateDAO.execute(UPDATE_CurVal, bean);
                    ret = ret + bean.getCur_val();
                } else {
                    // Previous Month: increase INIT_VAL value
                    bean.setId_login(id_user);
                    bean.setInit_val(increaseCurVal(bean.getInit_val().trim()));
                    // update new INIT_VAL into dbUPDATE_CurVal
                    updateDAO.execute(UPDATE_CurVal, bean);
                    ret = ret + bean.getInit_val();
                }
            } else if (bean.getType_val().equalsIgnoreCase("06")) {
                // get init_val
                ret = ret + bean.getInit_val().trim();
            } else if (bean.getType_val().equalsIgnoreCase("07")) {
                // YYY
                ret = ret + day.substring(0, 1) + day.substring(2, 4);
            } else if (bean.getType_val().equalsIgnoreCase("08")) {
                // YYMM
                ret = ret + day.substring(2, 6);
            } else if (bean.getType_val().equalsIgnoreCase("09")) {
                // MM
                ret = ret + day.substring(4, 6);
            }
        }
        return ret;
    }

    /**
     * <div>Generate Running Code</div>
     * 
     * @param id_code code ID that needs to generate the running no.
     * @param id_user UserID
     * @return String
     */
    public String getGenerateCode(String id_code, String id_user) {
        // count total items that is not equal to current sysdate(year)
        List<Map<String, Object>> resetNolist = queryDAO.executeForMapList(SELECT_NumberofRow, id_code);
       
        String resetNo="";
        if(resetNolist!=null&&resetNolist.size()>0){
        	resetNo = CommonUtils.toString(resetNolist.get(0).get("RESET_NO"));
        }
        if("1".equals(resetNo)){
            // 1: Reset by Year
        	Integer count = queryDAO.executeForObject("SELECT.BSYS.G_CDM_P01_countYYYY", id_code,Integer.class);
        	if(count.compareTo(new Integer(0))!=0){
        		sub1(id_code, id_user);
        	}        	
        }else if("2".equals(resetNo)){
            // 2: Reset by Month
        	Integer count = queryDAO.executeForObject("SELECT.BSYS.G_CDM_P01_countYYYYMM", id_code,Integer.class);
        	if ("SOANO".equalsIgnoreCase(id_code)) {
        	    // if code = SOANO and reset by monthly, reset running no will be SUB2 
        	    // and generate running number will be SOA CDM (running no will be base on current month / previous month running no.)
        	    if(count.compareTo(new Integer(0))!=0){
                    sub2(id_code, id_user);
                }
        	    
        	    return this.generateSOACode(id_code, id_user);
        	} else {
            	if(count.compareTo(new Integer(0))!=0){
            		sub1(id_code, id_user);
            	}
        	}
        }
        
        // declare new M_CODEBean, and pass parameter for m_code
        M_CODEBean m_code = new M_CODEBean();
        m_code.setId_code(id_code);
        m_code.setId_login(id_user);
        // new array list for sql result
        List<M_CODEBean> codeBean = new ArrayList<M_CODEBean>();
        // get data from db
        codeBean = queryDAO.executeForObjectList(SELECT_Running_No, m_code);
        String ret = "";
        // get current date
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        String day = dateFormat.format(date);
        boolean curValUpdated = false;
        // loop for each row to get generate code
        for (M_CODEBean bean : codeBean) {
            if (bean.getType_val().equalsIgnoreCase("01")) {
                // YY
                ret = ret + day.substring(2, 4);
            } else if (bean.getType_val().equalsIgnoreCase("02")) {
                // YYYY
                ret = ret + day.substring(0, 4);
            } else if (bean.getType_val().equalsIgnoreCase("03")) {
                // YYYYMMDD
                ret = ret + day;
            } else if (bean.getType_val().equalsIgnoreCase("04")) {
                // YYMMDD
                ret = ret + day.substring(2);
            } else if (bean.getType_val().equalsIgnoreCase("05")) {
                // increase curval value
                bean.setCur_val(increaseCurVal(bean.getCur_val().trim()));
                bean.setId_login(id_user);
                // update new curval into dbUPDATE_CurVal
                updateDAO.execute(UPDATE_CurVal, bean);
                ret = ret + bean.getCur_val();
                curValUpdated = true;
            } else if (bean.getType_val().equalsIgnoreCase("06")) {
                String sysBaseCountry = queryDAO.executeForObject("SELECT.G_CDM_P01.001", null, String.class);
                sysBaseCountry = CommonUtils.toString(sysBaseCountry);
                if ("SG".equals(sysBaseCountry)) {
                    //Code is "BACNO"
                    if("BACNO".equals(id_code)) {
                        String runningNo = "";
                        for (M_CODEBean bean1 : codeBean) {
                            if (bean1.getType_val().equalsIgnoreCase("05")) {
                                if (curValUpdated) {
                                    runningNo = bean1.getCur_val().trim();
                                } else {
                                    runningNo = increaseCurVal(bean1.getCur_val().trim());
                                }
                                break;
                            }
                        }
                        ret = ret + generateBACPrefix(runningNo);
                    } else {
                        // get init_val
                        ret = ret + bean.getInit_val().trim();
                    }
                } else {
                    // get init_val
                    ret = ret + bean.getInit_val().trim();
                }
            } else if (bean.getType_val().equalsIgnoreCase("07")) {
                // YYY
                ret = ret + day.substring(0, 1) + day.substring(2, 4);
            } else if (bean.getType_val().equalsIgnoreCase("08")) {
                // YYMM
                ret = ret + day.substring(2, 6);
            } else if (bean.getType_val().equalsIgnoreCase("09")) {
                // MM
                ret = ret + day.substring(4, 6);
            }
        }
        return ret;
    }
    
    /**
     * Calculate BAC prefix.
     * 
     * @param current
     *            running no.
     * @return A-Z
     */
    private String generateBACPrefix(String curval) {
        String str = "";
        curval = CommonUtils.toString(curval).trim();
        if (!CommonUtils.isEmpty(curval)) {
            long sum = 0;
            char[] c_curval = curval.toCharArray();
            int index = 0;
            for (int i = 0; i < c_curval.length; i++) {
                index = index + 1;
                long digitASCII = (long) c_curval[i];
                // not odd change to -ASCII value
                if (index % 2 == 0) {
                    digitASCII = -digitASCII;
                }
                sum = sum + digitASCII;
            }
            sum = Math.abs(sum);
            long chkDigit = (sum + 75) * 74 + 83;
            long chkDigitVal = (chkDigit % 26) + 65;
            str = String.valueOf((char) chkDigitVal);
        }
        return str;
    }

	/**
	 * <div>Function to increase cur_val value</div>
	 * 
	 * @param curval CurrentValue
	 * @return Current Value plus 1
	 */
	private String increaseCurVal(String curval) {
		// get cur_val length
		int len = curval.length();
		// get and increase cur_val value
		int value = Integer.parseInt(curval) + 1;
		// get length of value(string)
		int len_value = Integer.toString(value).length();
		String ret = "";
		// insert "0" before new cur_val
		for (int i = len_value; i < len; i++) {
			ret = ret + "0";
		}
		// return new cur_val(string)
		return (ret + Integer.toString(value));
	}

	/**
	 * Reset currentValue to all 0 string. <br/>
	 * the string length is the same as currentValue
	 * 
	 * @param currentValue
	 * @return all 0 string
	 */
	private String reset(String currentValue) {
		if (currentValue == null || currentValue.equals("")) {
			return "0";
		}
		
		String str = "";
		for (int i = 0; i < currentValue.length(); i++) {
			str += "0";
		}
		
		return str;
	}
}
