/**
 * @(#)BillingSystemSettings.java
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */

package nttdm.bsys.common.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.dao.QueryDAO;

/**
 * Get Global Setting from M_GSET_D
 * 
 * @author i-jankw
 * 
 */
public class BillingSystemSettings {

	private static final String SELECT_SETTING_SQL_ID = "SELECT.BSYS.SQL001";

	private static final String SQL_OUT_ID_SET = "ID_SET";
	private static final String SQL_OUT_SET_SEQ = "SET_SEQ";
	private static final String SQL_OUT_SET_VALUE = "SET_VALUE";

	private static final String COLON = ":";
	/**
	 * ChangePwdDay
	 */
	private static final String PWDCH_KEY = "PWDCH:1";
	/**
	 * NumberOfRowsInDB(dashboard)
	 */
	private static final String DASHBD_KEY = "DASHBD:1";
	/**
	 * NumberOfLogin
	 */
	private static final String TIME_LOGIN_KEY = "LOGINAT:1";
	/**
	 * MinuteToLogin
	 */
	private static final String MINUTE_LOGIN_KEY = "LOGINAT:2";
	/**
	 * FileLocation
	 */
	private static final String FILELOC = "FILELOC:1";
	/**
	 * File size upload
	 */
	private static final String USR_MNT = "USRMNT:1";
	/**
	 * File size upload
	 */
	private static final String FILE_SIZE_UPLOAD = "FILESIZEUPLOAD:1";
	/**
	 * Resuslt row
	 */
	private static final String RESULT_ROW = "RESULTROW:1";
	/**
	 * default currency
	 */
	private static final String DEF_CURRENCY = "DEF_CURRENCY:1";
	private static final String BATCH_G_SGP_P01 = "BATCH_G_SGP_P01:1";
	private static final String BATCH_G_SGP_P02 = "BATCH_G_SGP_P02:1";
	private static final String BATCH_G_GIR_P01 = "BATCH_G_GIR_P01:1";
	private static final String BATCH_G_GIR_P02 = "BATCH_G_GIR_P02:1";
	private static final String BATCH_G_CIT_P01 = "BATCH_G_CIT_P01:1";
	private static final String BATCH_G_CLC_P01 = "BATCH_G_CLC_P01:1";
	private static final String BATCH_G_IPS_P01 = "BATCH_G_IPS_P01:1";
	private static final String BATCH_G_CSB_P01 = "BATCH_G_CSB_P01:1";
	private static final String BATCH_MSG1 = "BATCH_MSG:1";
    private static final String BATCH_MSG2 = "BATCH_MSG:2";
    private static final String BATCH_MSG3 = "BATCH_MSG:3";
	/**
	 * Invoice Due Period
	 */
	private static final String INVOICE_DUE_PERIOD = "INVOICE_DUE_PERIOD:1";
	/**
	 * The number of hours before the subsequent batch can be executed
	 */
	private static final String BATCH_TIME_INTERVAL = "BATCH_TIME_INTERVAL:1";
	/**
	 * Number of characters to display on the result screen before
	 */
	private static final String DESC_LENGTH = "DESC_LENGTH:1";

	private Map<String, Object> setting;
	/**
	 * <div>QueryDAO</div>
	 */
	private QueryDAO queryDAO;
	/**
	 * Number of rows to be displayed on dashboard
	 */
	private int NumberOfRowsInDB = 0;

	/**
	 * Number of days before next password change.
	 */
	private int ChangePwdDay = 0;

	/**
	 * Time between login attempts in minutes
	 */
	private int MinuteToLogin = 0;

	/**
	 * Number of allowed login attempts.
	 */
	private int NumberOfLogin = 0;
	/**
	 * The number of hours before the subsequent batch can be executed
	 */
	private int batchTimeInterval = 0;

	/**
	 * Location to upload file
	 */
	private String fileLocation = "";
	/**
	 * Max image file size
	 */
	private int usrmnt = 0;	
	/**
	 * Max file size
	 */
	private int fileSizeUpload = 0;

	/**
	 * Number of rows of result to be display.
	 */
	private int resultRow = 0;

	/**
	 * Default currency to use in the system.
	 */
	private String defCurrency;

	private String batchGGirP01;

	private String batchGGirP02;

	private String batchGIpsP01;

	/**
	 * Number of characters to display on the result screen before it is
	 * truncated
	 */
	private int descLength = 0;

	private String batchGSgpP01;

	private String batchGSgpP02;

	/**
	 * Process to Import IPASS Usage and Amount
	 */
	private String batchGCitP01;

	/**
	 * Invoice Due Period By default 14 days
	 */
	private int invoiceDuePeriod;

	/**
	 * Process to Import CLEAR CALL Usage and Compute Amount
	 */
	private String batchGClcP01;
	
	private String batchGCsbP01;

	/**
	 * <div>get the Number of characters to display on the result screen before
	 * it is truncated</div>
	 * 
	 * @return the Number of characters
	 */
	public int getDescLength() {
		return descLength;
	}

	/**
	 * <div>set the Number of characters to display on the result screen before
	 * it is truncated</div>
	 * 
	 * @param descLength
	 */
	public void setDescLength(int descLength) {
		this.descLength = descLength;
	}

	/**
	 * <div>Process to Import IPASS Usage and Amount</div>
	 * 
	 * @return batchGIpsP01
	 * 
	 */
	public String getBatchGIpsP01() {
		return batchGIpsP01;
	}

	/**
	 * <div>Process to Import IPASS Usage and Amount</div>
	 * 
	 * @param String
	 *            batchGIpsP01
	 */
	public void setBatchGIpsP01(String batchGIpsP01) {
		this.batchGIpsP01 = batchGIpsP01;
	}

	/**
	 * The message1 of batch
	 */
	private String batchMsg1;

	/**
	 * <div>get the message1 of batch </div>
	 * 
	 * @return batch message1
	 */
	public String getBatchMsg1() {
		return batchMsg1;
	}

	/**
	 * <div>The message1 of batch </div>
	 * 
	 * @param String
	 *            batchMsg1
	 */
	public void setBatchMsg1(String batchMsg1) {
		this.batchMsg1 = batchMsg1;
	}
	
    /**
     * The message2 of batch
     */
    private String batchMsg2;

    /**
     * <div>get the message2 of batch </div>
     * 
     * @return batch message2
     */
    public String getBatchMsg2() {
        return batchMsg2;
    }

    /**
     * <div>The messag2 of batch </div>
     * 
     * @param String
     *            batchMsg2
     */
    public void setBatchMsg2(String batchMsg2) {
        this.batchMsg2 = batchMsg2;
    }
    
    /**
     * The message3 of batch
     */
    private String batchMsg3;

    /**
     * <div>get the message3 of batch </div>
     * 
     * @return batch message3
     */
    public String getBatchMsg3() {
        return batchMsg3;
    }

    /**
     * <div>The messag3 of batch </div>
     * 
     * @param String
     *            batchMsg3
     */
    public void setBatchMsg3(String batchMsg3) {
        this.batchMsg3 = batchMsg3;
    }

	/**
	 * EOD - Data Import (SMBC Giro Collection Data)
	 * 
	 * @return batchGGirP02
	 */

	public String getBatchGGirP02() {
		return batchGGirP02;
	}

	/**
	 * EOD - Data Import (SMBC Giro Collection Data)
	 * 
	 * @param String
	 *            batchGGirP02
	 */
	public void setBatchGGirP02(String batchGGirP02) {
		this.batchGGirP02 = batchGGirP02;
	}

	/**
	 * EOD - Data Import (SingPost Collection Data)
	 * 
	 * @return batchGSgpP02
	 */
	public String getBatchGSgpP02() {
		return batchGSgpP02;
	}

	/**
	 * EOD - Data Import (SingPost Collection Data)
	 * 
	 * @param String
	 *            batchGSgpP02
	 */
	public void setBatchGSgpP02(String batchGSgpP02) {
		this.batchGSgpP02 = batchGSgpP02;
	}

	/**
	 * Process to Import CLEAR CALL Usage and Compute Amount
	 * 
	 * @return batchGClcP01
	 */
	public String getBatchGClcP01() {
		return batchGClcP01;
	}

	/**
	 * Process to Import CLEAR CALL Usage and Compute Amount
	 * 
	 * @param String
	 *            batchGClcP01
	 */
	public void setBatchGClcP01(String batchGClcP01) {
		this.batchGClcP01 = batchGClcP01;
	}

	/**
	 * The number of hours before the subsequent batch can be executed
	 * 
	 * @return batchTimeInterval
	 */
	public int getBatchTimeInterval() {
		return batchTimeInterval;
	}

	/**
	 * The number of hours before the subsequent batch can be executed
	 * 
	 * @param int batchTimeInterval
	 */
	public void setBatchTimeInterval(int batchTimeInterval) {
		this.batchTimeInterval = batchTimeInterval;
	}

	/**
	 * Invoice Due Period By default 14 days
	 * 
	 * @return invoiceDuePeriod
	 */
	public int getInvoiceDuePeriod() {
		return invoiceDuePeriod;
	}

	/**
	 * Invoice Due Period By default 14 days
	 * 
	 * @param int invoiceDuePeriod
	 */
	public void setInvoiceDuePeriod(int invoiceDuePeriod) {
		this.invoiceDuePeriod = invoiceDuePeriod;
	}

	/**
	 * EOM - Data Export (SMBC GIRO Invoice Data)
	 * 
	 * @return batchGGirP01
	 */
	public String getBatchGGirP01() {
		return batchGGirP01;
	}

	/**
	 * EOM - Data Export (SMBC GIRO Invoice Data)
	 * 
	 * @param String
	 *            batchGGirP01
	 */
	public void setBatchGGirP01(String batchGGirP01) {
		this.batchGGirP01 = batchGGirP01;
	}

	/**
	 * Process to generate and export SingPost and update Collection Data
	 * 
	 * @return batchGSgpP01
	 */
	public String getBatchGSgpP01() {
		return batchGSgpP01;
	}

	/**
	 * Process to generate and export SingPost and update Collection Data
	 * 
	 * @param String
	 *            batchGSgpP01
	 */
	public void setBatchGSgpP01(String batchGSgpP01) {
		this.batchGSgpP01 = batchGSgpP01;
	}

	/**
	 * Process to Generate and Export Citibank Creadit Data
	 * 
	 * @return batchGCitP01
	 */
	public String getBatchGCitP01() {
		return batchGCitP01;
	}

	/**
	 * Process to Generate and Export Citibank Creadit Data
	 * 
	 * @param String
	 *            batchGCitP01
	 */
	public void setBatchGCitP01(String batchGCitP01) {
		this.batchGCitP01 = batchGCitP01;
	}

	/**
	 * Default currency to use in the system.
	 * 
	 * @return defCurrency
	 */
	public String getDefCurrency() {
		return defCurrency;
	}

	/**
	 * Default currency to use in the system.
	 * 
	 * @param String
	 *            defCurrency
	 */
	public void setDefCurrency(String defCurrency) {
		this.defCurrency = defCurrency;
	}

	/**
	 * Number of rows of result to be display.
	 * 
	 * @return resultRow
	 */
	public int getResultRow() {
		return resultRow;
	}

	/**
	 * Number of rows of result to be display.
	 * 
	 * @param int resultRow
	 */
	public void setResultRow(int resultRow) {
		this.resultRow = resultRow;
	}
	
	/**
	 * Get image size upload
	 * 
	 * @return usrmnt
	 */
	public int getUsrMnt() {
		return usrmnt;
	}

	/**
	 * Set image size upload
	 * 
	 * @param int usrmnt
	 */
	public void setUsrMnt(int usrmnt) {
		this.usrmnt = usrmnt;
	}
	
	/**
	 * Get file size upload
	 * 
	 * @return fileSizeUpload
	 */
	public int getFileSizeUpload() {
		return fileSizeUpload;
	}

	/**
	 * Set file size upload
	 * 
	 * @param int fileSizeUpload
	 */
	public void setFileSizeUpload(int fileSizeUpload) {
		this.fileSizeUpload = fileSizeUpload;
	}

	/**
	 * Location for uploaded file
	 * 
	 * @return fileLocation
	 */
	public String getFileLocation() {
		return fileLocation;
	}

	/**
	 * Location for uploaded file
	 * 
	 * @param String
	 *            fileLocation
	 */
	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}

	/**
	 * Set QueryDAO
	 * 
	 * @param queryDAO QueryDAO
	 */
	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}

	/**
	 * Get QueryDAO
	 * 
	 * @return QueryDAO
	 */
	public QueryDAO getQueryDAO() {
		return queryDAO;
	}

	/**
	 * Number of rows to be displayed on dashboard.
	 * 
	 * @param int numberOfRowsInDB
	 */
	public void setNumberOfRowsInDB(int numberOfRowsInDB) {
		NumberOfRowsInDB = numberOfRowsInDB;
	}

	/**
	 * Number of rows to be displayed on dashboard.
	 * 
	 * @return NumberOfRowsInDB
	 */
	public int getNumberOfRowsInDB() {
		return NumberOfRowsInDB;
	}

	/**
	 * Time between login attempts in minutes.
	 * 
	 * @return MinuteToLogin
	 */
	public int getMinuteToLogin() {
		return MinuteToLogin;
	}

	/**
	 * Time between login attempts in minutes.
	 * 
	 * @param int minuteToLogin
	 */
	public void setMinuteToLogin(int minuteToLogin) {
		MinuteToLogin = minuteToLogin;
	}

	/***
	 * Number of allowed login attempts.
	 * 
	 * @return NumberOfLogin
	 */
	public int getNumberOfLogin() {
		return NumberOfLogin;
	}

	/**
	 * Number of allowed login attempts.
	 * 
	 * @param int numberOfLogin
	 */
	public void setNumberOfLogin(int numberOfLogin) {
		NumberOfLogin = numberOfLogin;
	}

	/**
	 * Number of days before next password change
	 * 
	 * @param int changePwdDay
	 */
	public void setChangePwdDay(int changePwdDay) {
		ChangePwdDay = changePwdDay;
	}

	/**
	 * Number of days before next password change
	 * 
	 * @return ChangePwDay
	 */
	public int getChangePwdDay() {
		return ChangePwdDay;
	}

	/**
	 * <div>Constructor</div>
	 * 
	 * @param queryDAO
	 *            QueryDAO
	 */
	public BillingSystemSettings(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
		this.setting = new HashMap<String, Object>();

		List<Map<String, Object>> settings = queryDAO.executeForMapList(
				SELECT_SETTING_SQL_ID, null);
		if (settings != null) {
			StringBuilder key = new StringBuilder();

			for (Map<String, Object> setting : settings) {
				String idSet = setting.get(SQL_OUT_ID_SET).toString();
				String setSeq = setting.get(SQL_OUT_SET_SEQ).toString();
				Object setValue = setting.get(SQL_OUT_SET_VALUE);

				key.setLength(0);
				key.append(idSet).append(COLON).append(setSeq);
				if (this.setting.get(key.toString()) == null) {
					this.setting.put(key.toString(), setValue);
				}
			}

			// Get Number of rows in Dashboard
			setNumberOfRowsInDB(this.getInt(DASHBD_KEY));

			// Get Time between login attempts in minutes.
			setMinuteToLogin(this.getInt(MINUTE_LOGIN_KEY));

			// Get Number of allowed login attempts.
			setNumberOfLogin(this.getInt(TIME_LOGIN_KEY));

			// Get Days to change password
			setChangePwdDay(this.getInt(PWDCH_KEY));

			// Set file location to upload
			this.setFileLocation(this.get(FILELOC).toString());

			// Set image size upload
			this.setUsrMnt(this.getInt(USR_MNT));
			
			// Set file size upload
			this.setFileSizeUpload(this.getInt(FILE_SIZE_UPLOAD));

			// Set result row
			this.setResultRow(this.getInt(RESULT_ROW));

			// Set default currency
			this.setDefCurrency(this.get(DEF_CURRENCY));

			this.setBatchGCitP01(this.get(BATCH_G_CIT_P01));
			this.setBatchGGirP01(this.get(BATCH_G_GIR_P01));
			this.setBatchGGirP02(this.get(BATCH_G_GIR_P02));
			this.setBatchGSgpP01(this.get(BATCH_G_SGP_P01));
			this.setBatchGSgpP02(this.get(BATCH_G_SGP_P02));
			this.setBatchGClcP01(this.get(BATCH_G_CLC_P01));
			this.setBatchGCsbP01(this.get(BATCH_G_CSB_P01));
			this.setInvoiceDuePeriod(this.getInt(INVOICE_DUE_PERIOD));
			this.setBatchTimeInterval(this.getInt(BATCH_TIME_INTERVAL));
			this.setBatchMsg1(this.get(BATCH_MSG1));
            this.setBatchMsg2(this.get(BATCH_MSG2));
            this.setBatchMsg3(this.get(BATCH_MSG3));
			this.setBatchGIpsP01(this.get(BATCH_G_IPS_P01));
			this.setDescLength(this.getInt(DESC_LENGTH));
		}
	}

	/**
	 * get from map
	 * 
	 * @param key
	 * @return String
	 */
	private String get(String key) {
		Object value = this.setting.get(key);
		if (value == null) {
			return "";
		}
		return value.toString();
	}

	/**
	 * get int
	 * 
	 * @param key
	 * @return int
	 */
	private int getInt(String key) {
		return CommonUtils.toInteger(this.get(key));
	}

    /**
     * @return the batchGCsbP01
     */
    public String getBatchGCsbP01() {
        return batchGCsbP01;
    }

    /**
     * @param batchGCsbP01 the batchGCsbP01 to set
     */
    public void setBatchGCsbP01(String batchGCsbP01) {
        this.batchGCsbP01 = batchGCsbP01;
    }
}
