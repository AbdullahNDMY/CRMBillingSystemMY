/**
 * @(#)E_EXP_F01_NTTS_CONS.java
 * 
 * Copyright (c) 2016 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import jp.terasoluna.fw.web.codelist.MappedCodeListLoader;
import jp.terasoluna.fw.web.struts.action.GlobalMessageResources;
import nttdm.bsys.common.bean.E_EXP_F01_CONS_Bean;
import nttdm.bsys.common.bean.T_SET_BATCH;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.util.ApplicationContextProvider;

import org.springframework.context.ApplicationContext;
import org.springframework.util.CollectionUtils;

/**
 * PeopleSoft AR Report
 * #146 E-EXP-F01_NTTS_CONS
 * 
 * @author davidd
 */
public class E_EXP_F01_NTTS_CONS {
    /**
     * batch_id
     */
    private int batch_id = 0;

    /**
     * 
     */
    private String batch_type = "AR";

    /**
     * 
     */
    private String tax_code = "T07";
    
    /**
     * status
     */
    private String status = "1";

    /**
     * message
     */
    private String message = " ";

    /**
     * filename
     */
    private String filename = " ";

    /**
     * alert_msg
     */
    private String alert_msg = " ";

    /**
     * id_login
     */
    private String id_login;

    /**
     * esetRundate
     */
    private Date esetRundate;

    /**
     * queryDAO
     */
    private QueryDAO queryDAO;

    /**
     * uvo
     */
    private BillingSystemUserValueObject uvo;

    /**
     * error message id
     */
    private String ERR1BT012;
    // private String ERR2SC031;
    private String ERR2SC032;
    private String ERR2SC033;
    private String ERR2SC042;
    private String ERR1SC070;

    /**
     * originCodes
     */
    private Map<String, Object> originCodes = null;


	/**
     * batchId
     */
    private Integer idBatch=0;

    /**
     * idBatch を取得する
     * @return idBatch
     */
    public Integer getIdBatch() {
        return idBatch;
    }

    /**
     * batchId を設定する
     * @param idBatch
     *            idBatch
     */
    public void setIdBatch(Integer idBatch) {
        this.idBatch = idBatch;
    }
	
    /**
     * <div>Get queryDAO</div>
     * 
     * @return queryDAO
     */
    public QueryDAO getQueryDAO() {
        return queryDAO;
    }

    /**
     * <div>setQueryDAO</div>
     */
    public void setQueryDAO(QueryDAO queryDAO) {
        this.queryDAO = queryDAO;
    }

    /**
     * <div>getUpdateDAO</div>
     * 
     * @return updateDAO
     */
    public UpdateDAO getUpdateDAO() {
        return updateDAO;
    }

    /**
     * <div>setUpdateDAO</div>
     * 
     * @param updateDAO
     */
    public void setUpdateDAO(UpdateDAO updateDAO) {
        this.updateDAO = updateDAO;
    }

    /**
     * updateDAO
     */
    private UpdateDAO updateDAO;

    /**
     * @param esetRundate
     *            the esetRundate to set
     */
    public void setEsetRundate(Date esetRundate) {

        this.esetRundate = esetRundate;
    }

    /**
     * @return the esetRundate
     */
    public Date getEsetRundate() {

        return esetRundate;
    }

    /**
     * @param uvo
     *            the uvo to set
     */
    public void setUvo(BillingSystemUserValueObject uvo) {

        this.uvo = uvo;
    }

    /**
     * @return the uvo
     */
    public BillingSystemUserValueObject getUvo() {

        return uvo;
    }

    /**
     * PeopleSoft AR Report
     */
    public void e_exp_execute() {
        GlobalMessageResources message_info = GlobalMessageResources.getInstance();

        batch_id = 0;
        batch_type = "AR";
        status = "1";
        id_login = message_info.getMessage("info.IDBatchLogin");
        T_SET_BATCH tset = new T_SET_BATCH();
        tset.setBATCH_TYPE(batch_type);
        tset.setID_BATCH(batch_id + "");
        tset.setFILENAME(filename);
        tset.setMessage(message);
        tset.setID_LOGIN(id_login);
        tset.setSTATUS(status);
        tset.setMonthyear(new SimpleDateFormat("MM/yyyy").format(esetRundate) + " - Consumer"); // add invoice period after the monthYear
        G_SET_P01 gset = new G_SET_P01(this.queryDAO, this.updateDAO);
        String DATE_FORMAT = "yyyyMM";
        String NOW_FORMAT = "yyyyMMddHHmmss";
        Date now = new Date();
        SimpleDateFormat nowFormat = new SimpleDateFormat(NOW_FORMAT);
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        // Calendar c1 = Calendar.getInstance();
        String date_STR = sdf.format(esetRundate);
        // get batch_id to process.
        batch_id = gset.G_SET_P01_GetIdBatch(tset).getBatch_id();
        this.idBatch=batch_id;
        ERR2SC033 = message_info.getMessage("info.ERR2SC033", date_STR);

        if (batch_id >= 0) {
            String export_path = "", defCur = "", accRev = "", advAcc = "", busUnit = "";
            int projectLen = 0;
            List<Map<String, Object>> listValue = queryDAO.executeForMapList("SELECT.BSYS.E_EXP_F01HV.SQL001", null);
            List<Map<String, Object>> projectValue = queryDAO.executeForMapList("SELECT.BSYS.E_EXP_F01HV.SQL002", null);
            if (listValue != null && listValue.size() > 0) {
                HashMap<String, Object> tempMap;
                String key, value;
                for (int i = 0; i < listValue.size(); ++i) {
                    tempMap = (HashMap<String, Object>) listValue.get(i);
                    key = (String) tempMap.get("ID_SET");
                    value = (String) tempMap.get("SET_VALUE");
                    if ("BATCH_E_EXP_F01".equals(key)) {
                        export_path = value;
                    } else if ("BIL_DEBTER_ACC".equals(key)) {
                        accRev = value;
                    } else if ("DEF_CURRENCY".equals(key)) {
                        defCur = value;
                    } else if ("BIL_ADVANCE_ACC".equals(key)) {
                        advAcc = value;
                    }
                }
            }
            if (projectValue != null && projectValue.size() > 0) {
                HashMap<String, Object> tempMap;
                String key;
                for (int i = 0; i < projectValue.size(); ++i) {
                    tempMap = (HashMap<String, Object>) projectValue.get(i);
                    key = (String) tempMap.get("SET_SEQ");
                    if ("1".equals(key)) {
                        projectLen = Integer.valueOf(CommonUtils.toString(tempMap.get("SET_VALUE")));
                    } else if ("2".equals(key)) {
                        busUnit = (String) tempMap.get("SET_VALUE");
                    }
                }
            }
            if (!CommonUtils.isEmpty(export_path)) {
                if (new File(export_path).exists()) {
                    String CSVFilename = "";
                    String CSVFilenameNotIncludePath = "";
                    ERR2SC042 = message_info.getMessage("info.ERR2SC042", date_STR);
                    StringBuffer sBFileName = new StringBuffer(export_path).append("/PeopleSoft_Billing_CONS_") // 1.file name add : _CONS
                            .append(date_STR).append("_").append(nowFormat.format(now)).append("_").append(batch_id)
                            .append(".txt");
                    StringBuffer sBFileNameNotIncludePath = new StringBuffer().append("/PeopleSoft_Billing_CONS_")  // 1.file name add : _CONS
                            .append(date_STR).append("_").append(nowFormat.format(now)).append("_").append(batch_id)
                            .append(".txt");
                    CSVFilename = sBFileName.toString();
                    CSVFilenameNotIncludePath = sBFileNameNotIncludePath.toString();

                    Calendar closingDate = Calendar.getInstance();
                    closingDate.setTime(esetRundate);
                    int last = closingDate.getActualMaximum(Calendar.DAY_OF_MONTH);
                    closingDate.set(Calendar.DAY_OF_MONTH, last);
                    boolean haveRecord1 = false;
                    boolean haveRecord2 = false;
                    FileWriter fw;
                    try {
                        fw = new FileWriter(CSVFilename);
                        List<E_EXP_F01_CONS_Bean> headerListInOrDn = queryDAO.executeForObjectList(
                            "SELECT.BSYS.E_EXP_F01_NTTS_CONS.SQL001", date_STR);

                        if (headerListInOrDn != null && headerListInOrDn.size() > 0) {
                            haveRecord1 = true;
                            // do Main_Sub for the data which billType is IN OR DN
                            mainSub(headerListInOrDn, busUnit, accRev, advAcc, projectLen, closingDate, defCur, fw);
                        }

                        List<E_EXP_F01_CONS_Bean> headerListCN= queryDAO.executeForObjectList(
                            "SELECT.BSYS.E_EXP_F01_NTTS_CONS.SQL002", date_STR);

                        if (headerListCN != null && headerListCN.size() > 0) {
                            haveRecord2 = true;
                            // do Main_Sub for the data which billType is CN
                            mainSub(headerListCN, busUnit, accRev, advAcc, projectLen, closingDate, defCur, fw);
                        }

                        fw.flush();
                        fw.close();
                        // if there is no data
                        if (haveRecord1 == false && haveRecord2 == false) {
                            alert_msg = ERR2SC042;

                            T_SET_BATCH tbatch = new T_SET_BATCH();
                            tbatch.setID_BATCH(batch_id + "");
                            tbatch.setBATCH_TYPE("AR");
                            tbatch.setSTATUS("2");
                            tbatch.setMessage("No PeopleSoft AR file to be generated.");
                            tbatch.setFILENAME(" ");
                            tbatch.setID_LOGIN(uvo.getId_user());
                            G_SET_P01 g_set_update = new G_SET_P01(this.queryDAO, this.updateDAO);
                            g_set_update.G_SET_P01_GetIdBatch(tbatch);
                        } else {
                            ERR2SC032 = message_info.getMessage("info.ERR2SC032", export_path,
                                    CSVFilenameNotIncludePath, date_STR);
                            alert_msg = ERR2SC032;

                            T_SET_BATCH tbatch = new T_SET_BATCH();
                            tbatch.setID_BATCH(batch_id + "");
                            tbatch.setBATCH_TYPE("AR");
                            tbatch.setSTATUS("2");
                            tbatch.setMessage(ERR2SC032);
                            tbatch.setFILENAME(CSVFilenameNotIncludePath);
                            tbatch.setID_LOGIN(uvo.getId_user());
                            G_SET_P01 g_set_update = new G_SET_P01(this.queryDAO, this.updateDAO);
                            g_set_update.G_SET_P01_GetIdBatch(tbatch);   
                        }
                    } catch (Exception e) {
                        alert_msg = ERR2SC033;
                        e.printStackTrace();
                        T_SET_BATCH tbatch = new T_SET_BATCH();

                        tbatch.setID_BATCH(batch_id + "");
                        tbatch.setBATCH_TYPE("AR");
                        tbatch.setSTATUS("3");
                        tbatch.setMessage(CommonUtils.escapeSQL(e.toString()));
                        tbatch.setFILENAME(CSVFilenameNotIncludePath);
                        tbatch.setID_LOGIN(uvo.getId_user());
                        G_SET_P01 g_set_update = new G_SET_P01(this.queryDAO, this.updateDAO);
                        g_set_update.G_SET_P01_GetIdBatch(tbatch);
                    } 
                } else {
                    ERR1BT012 = message_info.getMessage("errors.ERR1BT012", export_path);

                    alert_msg = ERR2SC033;
                    message = ERR1BT012;
                    T_SET_BATCH tbatch = new T_SET_BATCH();

                    tbatch.setID_BATCH(batch_id + "");
                    tbatch.setBATCH_TYPE("AR");
                    tbatch.setSTATUS("3");
                    tbatch.setMessage(message);
                    tbatch.setFILENAME(" ");
                    tbatch.setID_LOGIN(this.id_login);
                    G_SET_P01 g_set_update = new G_SET_P01(this.queryDAO, this.updateDAO);
                    g_set_update.G_SET_P01_GetIdBatch(tbatch);
                }
            } else {
                ERR1SC070 = message_info.getMessage("errors.ERR1SC070");
                message = ERR1SC070;
                alert_msg = message;
                T_SET_BATCH tbatch = new T_SET_BATCH();
                tbatch.setID_BATCH(batch_id + "");
                tbatch.setBATCH_TYPE("AR");
                tbatch.setSTATUS("3");
                tbatch.setMessage(message);
                tbatch.setFILENAME(" ");
                tbatch.setID_LOGIN(this.id_login);
                G_SET_P01 g_set_update = new G_SET_P01(this.queryDAO, this.updateDAO);
                g_set_update.G_SET_P01_GetIdBatch(tbatch);
            }
        } else if (batch_id < 0) {
        }
    }

    /**
     * get billType
     * 
     * @param billType
     * @return
     */
    private String billTypeTrans(String billType) {
        if (billType != null) {
            if (billType.equals("IN") || billType.equals("DN"))
                return "DR";
            else if (billType.equals("CN"))
                return "CR";
        }
        return "";
    }

    /**
     * append to FileWriter
     * 
     * @param fw
     * @param param
     * @throws IOException
     */
    private void insertDetails(FileWriter fw, HashMap<String, String> param) throws IOException {

        char delimiter = ',';
        char quotation = '\"';
        String lineSeparator = "\r\n";

        fw.append(quotation);
        fw.append(param.get("A"));// A
        fw.append(quotation);
        fw.append(delimiter);

        fw.append(quotation);
        fw.append(param.get("B"));// B
        fw.append(quotation);
        fw.append(delimiter);

        fw.append(quotation);
        fw.append(param.get("C"));// C
        fw.append(quotation);
        fw.append(delimiter);

        fw.append(quotation);
        fw.append(param.get("D"));// D
        fw.append(quotation);
        fw.append(delimiter);

        fw.append(quotation);
        fw.append(param.get("E"));// E
        fw.append(quotation);
        fw.append(delimiter);

        fw.append(quotation);
        fw.append(param.get("F"));// F
        fw.append(quotation);
        fw.append(delimiter);

        fw.append(quotation);
        fw.append(param.get("G"));// G
        fw.append(quotation);
        fw.append(delimiter);

        fw.append(quotation);
        fw.append(param.get("H"));// H
        fw.append(quotation);
        fw.append(delimiter);

        fw.append(quotation);
        fw.append(param.get("I"));// I
        fw.append(quotation);
        fw.append(delimiter);

        fw.append(quotation);
        fw.append(param.get("J"));// J
        fw.append(quotation);
        fw.append(delimiter);

        fw.append(quotation);
        fw.append(param.get("K"));// K
        fw.append(quotation);
        fw.append(delimiter);

        fw.append(quotation);
        fw.append(param.get("L"));// L
        fw.append(quotation);
        fw.append(delimiter);

        fw.append(quotation);
        fw.append(param.get("M"));// M
        fw.append(quotation);
        fw.append(delimiter);

        fw.append(quotation);
        fw.append(param.get("N"));// N
        fw.append(quotation);
        fw.append(delimiter);

        fw.append(quotation);
        fw.append(param.get("O"));// O
        fw.append(quotation);
        fw.append(delimiter);

        fw.append(quotation);
        fw.append(param.get("P"));// P
        fw.append(quotation);
        fw.append(delimiter);

        fw.append(quotation);
        fw.append(param.get("Q"));// Q
        fw.append(quotation);
        fw.append(delimiter);

        fw.append(quotation);
        fw.append(param.get("R"));// R
        fw.append(quotation);
        fw.append(delimiter);

        fw.append(quotation);
        fw.append(param.get("S"));// S
        fw.append(quotation);
        fw.append(delimiter);

        fw.append(quotation);
        fw.append(param.get("T"));// T
        fw.append(quotation);
        fw.append(delimiter);

        fw.append(quotation);
        fw.append(param.get("U"));// U
        fw.append(quotation);
        fw.append(delimiter);

        fw.append(quotation);
        fw.append(param.get("V"));// V
        fw.append(quotation);
        fw.append(delimiter);

        fw.append(quotation);
        fw.append(param.get("W"));// W
        fw.append(quotation);
        fw.append(delimiter);

        fw.append(quotation);
        fw.append(param.get("X"));// X
        fw.append(quotation);
        fw.append(delimiter);

        fw.append(quotation);
        fw.append(param.get("Y"));// Y
        fw.append(quotation);
        fw.append(delimiter);

        fw.append(quotation);
        fw.append(param.get("Z"));// Z
        fw.append(quotation);
        fw.append(delimiter);

        fw.append(quotation);
        fw.append(param.get("AA"));// AA
        fw.append(quotation);
        fw.append(delimiter);

        fw.append(quotation);
        fw.append(param.get("AB"));// AB
        fw.append(quotation);
        fw.append(delimiter);

        fw.append(quotation);
        fw.append(param.get("AC"));// AC
        fw.append(quotation);
        fw.append(delimiter);
        
        fw.append(quotation);
        fw.append(param.get("AD"));// AD
        fw.append(quotation);
        fw.append(delimiter);
        
        fw.append(quotation);
        fw.append(param.get("AE"));// AE
        fw.append(quotation);
        fw.append(delimiter);
        
        fw.append(quotation);
        fw.append(param.get("AF"));// AF
        fw.append(quotation);
        fw.append(delimiter);
        
        fw.append(quotation);
        fw.append(param.get("AG"));// AG
        fw.append(quotation);
        fw.append(delimiter);
        
        fw.append(quotation);
        fw.append(param.get("AH"));// AH
        fw.append(quotation);
        fw.append(lineSeparator);
    }

    /**
     * append to FileWriter
     * 
     * @param fw
     * @throws IOException
     */
    private void insertSpaceLine(FileWriter fw) throws IOException {

        String lineSeparator = "\r\n";
        fw.append(lineSeparator);
    }

    /**
     * get LineDesc(row AC)
     * 
     * @param caFrom
     *            From Date
     * @param caTo
     *            To Date
     * @param desc2List
     *            DESC_ABBR of SVC_LEVEL2
     * @param desc3List
     *            DESC_ABBR of SVC_LEVEL3
     * @param flag
     * @return
     */
    private String getLineDesc(Calendar caFrom, Calendar caTo, List<String> desc2List, List<String> desc3List, int flag) {
        SimpleDateFormat acFormatSameMonth = new SimpleDateFormat("yyMM");
        SimpleDateFormat acFormatDiffMonth = new SimpleDateFormat("yyMMdd");
        StringBuffer desc1 = new StringBuffer();
        String desc2 = "";
        String desc3 = "";
        StringBuffer desc4 = new StringBuffer();
        boolean isFullMonth = false;

        Calendar temp = Calendar.getInstance();
        temp.setTime(caFrom.getTime());
        if (flag == 1) {
            temp.add(Calendar.DAY_OF_MONTH, 1);
        }
        
        int caFromDay = BillingCalendar.getValue4Date(temp.getTime(), BillingCalendar.DATE);
        int caToDay = BillingCalendar.getValue4Date(caTo.getTime(), BillingCalendar.DATE);
        int lastMonthDay = caTo.getActualMaximum(Calendar.DAY_OF_MONTH);
        
        if (caFromDay == 1 && caToDay == lastMonthDay) {
            isFullMonth = true;
        }
        
        int xLength;
        if (isFullMonth && temp.get(Calendar.YEAR) == caTo.get(Calendar.YEAR) && temp.get(Calendar.MONTH) == caTo.get(Calendar.MONTH)) {
            desc1.append(acFormatSameMonth.format(temp.getTime())).append("/");
            xLength = 25;
        } else {
            desc1.append(acFormatDiffMonth.format(temp.getTime())).append("-")
                    .append(acFormatDiffMonth.format(caTo.getTime())).append("/");
            xLength = 16;
        }
        
        // DESC_ARRB of SVC_LEVEL2
        if (!CollectionUtils.isEmpty(desc2List)) {
            if (!CommonUtils.isEmpty(desc2List.get(0))){
                desc2 = desc2List.get(0);
            }
        }
        
        // DESC_ARRB of SVC_LEVEL3
        if (desc3List != null && desc3List.size() > 0) {
            if (!CommonUtils.isEmpty(desc3List.get(0))) {
                desc3 = desc3List.get(0);
            }
        }

        desc4.append(desc2);
        if (!CommonUtils.isEmpty(desc2) && !CommonUtils.isEmpty(desc3)) {
            desc4.append("-");
        }
        desc4.append(desc3);
        
        desc1.append(desc4.length() > xLength ? desc4.substring(0, xLength) : desc4);

        return desc1.toString();
    }

    /**
     * get originCode
     * 
     * @param originCode
     * @return
     */
    @SuppressWarnings("unchecked")
    private String getOrignValue(String originCode) {
        if (CollectionUtils.isEmpty(this.originCodes)) {
            ApplicationContext context = ApplicationContextProvider.getApplicationContext();
            MappedCodeListLoader originCodeList = (MappedCodeListLoader) context.getBean("LIST_ORIGIN_CODE");
            this.originCodes = originCodeList.getCodeListMap();
        }

        if (this.originCodes.containsKey(originCode)) {
            return this.originCodes.get(originCode).toString();
        }

        return "";
    }

    /**
     * cast one number(15,2) - to positive number if it is negative number - to
     * negative number if it is positive number
     */
    private static String castToPositiveNegative(String str) {
        try {
            return cast(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * cast one number(15,2) to positive number if it is negative number
     */
    private static String castToPositive(String str) {
        try {
            double d = Double.parseDouble(str);
            if (d < 0)
                return cast(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * Cast double to String.
     * 
     * @param d
     *            double value
     * @return String value
     */
    private static String cast(String str) {
        BigDecimal d = new BigDecimal(str);
        BigDecimal zero = new BigDecimal(0);
        return (zero.subtract(d)).toPlainString();
    }

    /**
     * 
     * @param header
     * @param QRSCase
     * @return
     */
    private HashMap<String, Object> getQRS_Header(E_EXP_F01_CONS_Bean header, int QRSCase,String exportAmt,String billingAmount) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        String billCur = header.getBILL_CURRENCY();
        String exportCur = header.getEXPORT_CUR();
        String exportAmount = exportAmt;
        String billType = header.getBILL_TYPE();
        String curRate = header.getCUR_RATE();
        String billAmount = billingAmount;
        int formatQ = 2;
        int formatR = 8;
        switch (QRSCase) {
        case 1:
            // Q //CASE 1
            result.put("Q", this.formatQ(exportAmount, formatQ, billType, 'H'));
            // R
            result.put("R", this.formatNumber(curRate, formatR));
            // S
            result.put("S", exportCur);
            break;
        case 2:
            // Q //CASE 2
            result.put("Q", this.formatQ(exportAmount, formatQ, billType, 'H'));
            // R
            result.put("R", "1.00000000");
            // S
            result.put("S", exportCur);
            break;
        case 3:
            // Q //CASE 3
            result.put("Q", this.formatQ(billAmount, formatQ, billType, 'H'));
            // R
            result.put("R", this.formatNumber(curRate, formatR));
            // S
            result.put("S", billCur);
            break;
        case 4:
            // Q //CASE 4
            result.put("Q", this.formatQ(billAmount, formatQ, billType, 'H'));
            // R
            result.put("R", "1.00000000");
            // S
            result.put("S", billCur);
            break;
        }
        return result;
    }

    /**
     * 
     * @param header
     * @param detail
     * @param QRSCase
     * @return
     */
    private HashMap<String, Object> getQRS_Detail(E_EXP_F01_CONS_Bean header, E_EXP_F01_CONS_Bean detail,int QRSCase) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        String billCur = header.getBILL_CURRENCY();
        String exportCur = header.getEXPORT_CUR();
        String billType = header.getBILL_TYPE();
        String curRate = header.getCUR_RATE();
        String itemExportAmt = "";
        String itemSubtotal = "";
        int formatQ = 2;
        int formatR = 8;
        itemExportAmt = detail.getITEM_EXPORT_AMT();
        itemSubtotal = detail.getITEM_SUBTOTAL();
        if (CommonUtils.isEmpty(itemExportAmt)) {
            itemExportAmt = "0";
        }
        if (CommonUtils.isEmpty(itemSubtotal)) {
            itemSubtotal = "0";
        }
        switch (QRSCase) {
        case 1:
            // Q
            result.put("Q", this.formatQ(itemExportAmt, formatQ, billType, 'D'));
            // R
            result.put("R", this.formatNumber(curRate, formatR));
            // S
            result.put("S", exportCur);
            break;
        case 2:
            // Q
            result.put("Q", this.formatQ(itemExportAmt, formatQ, billType, 'D'));
            // R
            result.put("R", "1.00000000");
            // S
            result.put("S", exportCur);
            break;
        case 3:
            // Q
            result.put("Q", this.formatQ(itemSubtotal, formatQ, billType, 'D'));
            // R
            result.put("R", this.formatNumber(curRate, formatR));
            // S
            result.put("S", billCur);
            break;
        case 4:
            // Q
            result.put("Q", this.formatQ(itemSubtotal, formatQ, billType, 'D'));
            // R
            result.put("R", "1.00000000");
            // S
            result.put("S", billCur);
            break;
        }
        return result;
    }

    /**
     * 
     * @param header
     * @param detail
     * @param QRSCase
     * @return
     */
    private HashMap<String, Object> getQRS_DetailTax(E_EXP_F01_CONS_Bean header, E_EXP_F01_CONS_Bean detail,String itemExportGSTStr,String itemGSTStr,int QRSCase) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        String billType = header.getBILL_TYPE();
        String itemExportGst = "";
        String itemGst = "";
        int formatQ = 2;
        itemExportGst = itemExportGSTStr;
        itemGst = itemGSTStr;
        if (CommonUtils.isEmpty(itemExportGst)) {
        	itemExportGst = "0";
        }
        if (CommonUtils.isEmpty(itemGst)) {
        	itemGst = "0";
        }
        switch (QRSCase) {
        case 1:
            // Q
            result.put("Q", this.formatQ(itemExportGst, formatQ, billType, 'D'));
            break;
        case 2:
            // Q
            result.put("Q", this.formatQ(itemExportGst, formatQ, billType, 'D'));
            break;
        case 3:
            // Q
            result.put("Q", this.formatQ(itemGst, formatQ, billType, 'D'));
            break;
        case 4:
            // Q
            result.put("Q", this.formatQ(itemGst, formatQ, billType, 'D'));
            break;
        }
        return result;
    }
    
    /**
     * 
     * @param s
     * @return
     */
    private String isNull(String s) {
        if (s == null || s.length() == 0) {
            s = " ";
        }
        return s;
    }

    /**
     * 
     * @param s
     * @param format
     * @return
     */
    private String formatNumber(String s, int scale) {
        if (s == null || s.length() == 0) {
            s = "0";
        }
        BigDecimal big = new BigDecimal(s);
        big = big.setScale(scale, BigDecimal.ROUND_HALF_UP);
        return big.toPlainString();
    }

    /**
     * 
     * @param billCur
     * @param exportCur
     * @param isDisplayExpAmt
     * @param defCur
     * @return
     */
    private int getQRSCase(String billCur, String exportCur, String isDisplayExpAmt, String defCur) {
        int result = 0;
        // CASE 1
        if (billCur.equals(exportCur)) {
            result = 1;
        } else if (defCur.equals(exportCur)) {
            // CASE 2
            if ("1".equals(isDisplayExpAmt)) {
                result = 2;
            }
            // CASE 3
            else {
                result = 3;
            }
        } else {
            // CASE 1
            if ("1".equals(isDisplayExpAmt)) {
                result = 1;
            }
            // CASE 4
            else {
                result = 4;
            }
        }
        return result;
    }

    /**
     * 
     * @param input
     * @param format
     * @param billType
     * @return
     */
    private String formatQ(String input, int format, String billType, char HorD) {
        if ("CN".equals(billType)) {
            if ('H' == HorD) {
                return this.formatNumber(castToPositiveNegative(input), format);
            } else {
                return this.formatNumber(castToPositive(input), format);
            }

        } else {
            if ('H' == HorD) {
                return this.formatNumber(castToPositive(input), format);
            } else {
                return this.formatNumber(castToPositiveNegative(input), format);
            }
        }
    }
     
    /**
     * Set Billing Document Details
     */
	private void subBillDocumentDetail(E_EXP_F01_CONS_Bean header,E_EXP_F01_CONS_Bean detail, int cal_Case, String busUnit,String defCur,
                                 String accRev,String advAcc,int projectLen,Calendar closingDate,String billRef,FileWriter fw){
        
        try {
            
            String A_BusUnit = busUnit;
            String B_Origin;
            String C_CustomerId;
            String E_Entry_Type;
            String F_Reason = " "; // IN CORP CHANGE TO " "
            String G_Revalue = "Y"; // IN CORP CHANGE TO "Y"
            String H_InvoiceDate;
            String I_DueDate;
            String M_Salesperson;
            String N_CreditAnalyst;
            String O_Collector;
            String P_PONbr;
            String X_AffiliateNTT;
            String space = " ";
            String AC_Category;
            String AD_SubCategory;

            Date billFrom, billTo;
            Calendar caFrom = Calendar.getInstance();
            Calendar caTo = Calendar.getInstance();

            int calCase = cal_Case;
            C_CustomerId = this.isNull(header.getCUST_ACC_NO());

            E_Entry_Type = this.billTypeTrans(header.getBILL_TYPE());
            H_InvoiceDate = header.getDATE_REQ();
            I_DueDate = header.getDUE_DATE();
            M_Salesperson = space; // IN CORP CHANGE TO " "
            N_CreditAnalyst = "DEFAULT";
            O_Collector = "DEFAULT";
            P_PONbr = this.isNull(detail.getCUST_PO2()); // IN CORP CHANGE TO R2.CUST_PO
            X_AffiliateNTT = this.isNull(header.getAFFILIATE_CODE());
            //AC_Category=this.isNull(header.getCO_CATEGORY());
            AC_Category = space; // IN CORP CHANGE TO " "
            //AD_SubCategory=this.isNull(header.getCO_SUB_CATEGORY());
            AD_SubCategory = space; // IN CORP CHANGE TO " "
            
            HashMap<String, String> param = new HashMap<String, String>();
            String D_InvoiceNbr = space;
            String J_VATApplicable = space;
            String K_VATTxnType = space;
            String L_VATTaxCd = space;
            String Q_LineAmount = space;
            String R_CurrRate = space;
            String S_Currency = space;
            String T_Account = space;
            String U_Product = space;
            String V_Process = space;
            String W_Dept = space;
            String Z_Project = space;
            String Z_CF7 = space;
            String AF_CF8 = space;
            String AG_CF9 = space;
            String AH_LineDesc = space;
            String AA_Activity = space;
            String AB_Source = space;

            D_InvoiceNbr = detail.getID_REF(); // IN CONS BillNo only be set in detail record
            J_VATApplicable = "T";
            K_VATTxnType = "DT";
            L_VATTaxCd = detail.getTAX_CODE();

            HashMap<String, Object> qrsDetail = this.getQRS_Detail(header, detail,calCase);
            Q_LineAmount = (String) qrsDetail.get("Q");
            R_CurrRate = (String) qrsDetail.get("R");
            S_Currency = (String) qrsDetail.get("S");

            if (detail.getACC_CODE() == null || detail.getACC_CODE().length() == 0) {
                T_Account = space;
            } else {
                T_Account = detail.getACC_CODE().trim();
            }

            U_Product = this.isNull(detail.getPRODUCT_CODE()).trim();
            V_Process = "0110"; // IN CORP CHANGE TO "0110"

            W_Dept = this.isNull(detail.getREFERENCE1()); // IN CORP CHANGE TO R2.REFERENCE1

            if (CommonUtils.isEmpty(detail.getJOB_NO())) {
                Z_Project = space;
            } else {
                Z_Project = detail.getJOB_NO().length() >= projectLen ? detail.getJOB_NO().substring(0, projectLen) : detail.getJOB_NO();
            }

            Z_CF7 = this.isNull(detail.getREFERENCE2()); // IN CORP CHANGE TO R2.REFERENCE2
            AF_CF8 = space;
            AG_CF9 = this.isNull(header.getOTHERS_REF_NO()); // IN CORP CHANGE TO R1.OTHERS_REF_NO

            billFrom = detail.getBILL_FROM();
            billTo = detail.getBILL_TO();
            caFrom = Calendar.getInstance();
            caTo = Calendar.getInstance();
            caFrom.setTime(billFrom);
            caTo.setTime(billTo);
            List<String> desc2List = this.queryDAO.executeForObjectList("SELECT.BSYS.E_EXP_F01.getSVC_LEVEL2", detail.getID_CUST_PLAN_LINK());
            List<String> desc3List = this.queryDAO.executeForObjectList("SELECT.BSYS.E_EXP_F01.getSVC_LEVEL3", detail.getID_CUST_PLAN_LINK());
            AF_CF8= this.queryDAO.executeForObject("SELECT.BSYS.GET_RATE_TYPE2", detail.getID_CUST_PLAN_LINK(),String.class);
            
            AH_LineDesc = this.getLineDesc(caFrom, caTo, desc2List, desc3List, 0);

            param.put("A", A_BusUnit);

            String originCode = detail.getORIGIN_CODE();
            B_Origin = this.isNull(originCode);
            param.put("B", B_Origin);

            param.put("C", C_CustomerId);

            param.put("D", D_InvoiceNbr);

            param.put("E", E_Entry_Type);

            param.put("F", F_Reason); // IN CORP CHANGE TO " "

            param.put("G", G_Revalue); // IN CORP CHANGE TO "Y" 

            param.put("H", H_InvoiceDate);

            param.put("I", I_DueDate);

            param.put("J", J_VATApplicable);

            param.put("K", K_VATTxnType);

            param.put("L", L_VATTaxCd);

            param.put("M", M_Salesperson); // IN CORP CHANGE TO " "

            param.put("N", N_CreditAnalyst);

            param.put("O", O_Collector);

            param.put("P", P_PONbr); // IN CORP CHANGE TO R2.CUST_PO

            param.put("Q", Q_LineAmount);

            param.put("R", space); // IN CORP CHANGE TO " "

            param.put("S", S_Currency);

            param.put("T", T_Account);

            param.put("U", U_Product);

            param.put("V", V_Process); // IN CORP CHANGE TO "0110"

            param.put("W", W_Dept); // IN CORP CHANGE TO R2.REFERENCE1

            param.put("X", X_AffiliateNTT);

            param.put("Y", A_BusUnit);
            
            param.put("Z", space); // IN CORP CHANGE TO " "(Project)
            
            param.put("AA", AA_Activity);
            
            param.put("AB", AB_Source);
            
            param.put("AC", AC_Category ); // IN CORP CHANGE TO " "
            
            param.put("AD", AD_SubCategory); // IN CORP CHANGE TO " "
            
            param.put("AE", Z_CF7); // IN CORP CHANGE TO R2.REFERENCE2

            param.put("AF", AF_CF8);

            param.put("AG", AG_CF9); // // IN CORP CHANGE TO R1.OTHERS_REF_NO

            // IN CORP if CN(when billType=CN, EntryType=CR), add CN in front of LineDesc
            if ("CR".equals(E_Entry_Type)) {
               AH_LineDesc = "CN" + AH_LineDesc;
            }
            param.put("AH", AH_LineDesc);

            if (caTo.compareTo(closingDate) > 0) {
                BigDecimal totalDays = new BigDecimal((caTo.getTimeInMillis() - caFrom.getTimeInMillis()) / (1000 * 60 * 60 * 24) + 1);
                BigDecimal lineDays1 = new BigDecimal((closingDate.getTimeInMillis() - caFrom.getTimeInMillis()) / (1000 * 60 * 60 * 24) + 1);
                BigDecimal lineAmt = new BigDecimal(Q_LineAmount);
                BigDecimal oneDayAmt = lineAmt.divide(totalDays, 10, BigDecimal.ROUND_HALF_UP);
                BigDecimal oneMonthAmt = oneDayAmt.multiply(new BigDecimal("31"));
                BigDecimal lineAmt1 = oneDayAmt.multiply(lineDays1);
                BigDecimal lineAmt2 = lineAmt.subtract(lineAmt1);
                lineAmt1 = lineAmt1.setScale(2, BigDecimal.ROUND_HALF_UP);
                lineAmt2 = lineAmt2.setScale(2, BigDecimal.ROUND_HALF_UP);
                if (oneMonthAmt.abs().compareTo(new BigDecimal("1200.00")) > 0) { // IN CORP THE COMPARE VALUE CHANGE TO 1200

                    AH_LineDesc = this.getLineDesc(caFrom, closingDate, desc2List, desc3List, 0);
                    // IN CORP if CN(when billType=CN, EntryType=CR), add CN in front of LineDesc
                    if ("CR".equals(E_Entry_Type)) {
                        AH_LineDesc = "CN" + AH_LineDesc;
                    }
                    param.put("AH", AH_LineDesc);
                    param.put("Q", lineAmt1.toPlainString());

                    this.insertDetails(fw, param);

                    param.put("T", advAcc);
                    // Delete #160 Start
                    // product(U), process(V), department(W) should be outputted as same as previous record
                    // param.put("U", space);
                    // param.put("V", space);
                    // param.put("W", space); // IN CORP THE DeptId CHANGE TO " "
                    // Delete #160 End
                    param.put("Q", CommonUtils.toString(lineAmt2));
                    // Delete #160 Start
                    // CF7(AE) should be outputted as same as previous record
                    // param.put("AE", space); // IN CORP THE CF7 SET TO " "
                    // Delete #160 End

                    AH_LineDesc = this.getLineDesc(closingDate, caTo, desc2List, desc3List, 1);
                    // IN CORP if CN(when billType=CN, EntryType=CR), add CN in front of LineDesc
                    if ("CR".equals(E_Entry_Type)) {
                        AH_LineDesc = "CN" + AH_LineDesc;
                    }
                    param.put("AH", AH_LineDesc);
                    this.insertDetails(fw, param);
                } else {
                    this.insertDetails(fw, param);
                }
            } else {
                this.insertDetails(fw, param);
            }
            param.clear();
           } catch (Exception e) {
        }
    }
    
    /**
     * Set Billing Document Header
     */
    private void subBillDocumentHeader(E_EXP_F01_CONS_Bean header, E_EXP_F01_CONS_Bean lastRecord, int cal_Case, String busUnit,String accRev,String defCur,String exportAmt,String billAmount,String billRef,String originCode,String taxcode,FileWriter fw){
        try {
        String A_BusUnit = busUnit;
        String B_Origin;
        String C_CustomerId;
        String E_Entry_Type;
        String F_Reason = " "; // IN CORP CHANGE TO " "
        String G_Revalue = "Y"; // IN CORP CHANGE TO "Y"
        String H_InvoiceDate;
        String I_DueDate;
        String M_Salesperson;
        String N_CreditAnalyst;
        String O_Collector;
        String P_PONbr;
        String X_AffiliateNTT;
        String space = " ";
        String W_DeptlDa;
        
        HashMap<String, String> param = new HashMap<String, String>();

        int calCase = cal_Case;
        C_CustomerId = this.isNull(header.getCUST_ACC_NO());

        E_Entry_Type = this.billTypeTrans(header.getBILL_TYPE());
        H_InvoiceDate = header.getDATE_REQ();
        I_DueDate = header.getDUE_DATE();
        M_Salesperson = space; // IN CORP CHANGE TO " "
        N_CreditAnalyst = "DEFAULT";
        O_Collector = "DEFAULT";
        P_PONbr = this.isNull(header.getCUST_PO());
        X_AffiliateNTT =this.isNull(header.getAFFILIATE_CODE());
        W_DeptlDa=space; // IN CORP CHANGE TO " "

        // Get LineDesc
        String AH_LineDesc = space;
        Date billFrom, billTo;
        Calendar caFrom = Calendar.getInstance();
        Calendar caTo = Calendar.getInstance();
        billFrom = lastRecord.getBILL_FROM();
        billTo = lastRecord.getBILL_TO();
        caFrom.setTime(billFrom);
        caTo.setTime(billTo);
        List<String> desc2List = this.queryDAO.executeForObjectList("SELECT.BSYS.E_EXP_F01.getSVC_LEVEL2", lastRecord.getID_CUST_PLAN_LINK());
        List<String> desc3List = this.queryDAO.executeForObjectList("SELECT.BSYS.E_EXP_F01.getSVC_LEVEL3", lastRecord.getID_CUST_PLAN_LINK());
        AH_LineDesc = this.getLineDesc(caFrom, caTo, desc2List, desc3List, 0);

        // Bus Unit
        param.put("A", A_BusUnit);

        // Origin
        B_Origin = originCode;
        
        param.put("B", B_Origin);

        // Customer ID
        param.put("C", C_CustomerId);

        // Invoice Nbr
        param.put("D", billRef);

        // Entry Type
        param.put("E", E_Entry_Type);

        // Reason(IN CORP CHANGE TO " ")
        param.put("F", F_Reason);

        // Revalue(IN CORP CHANGE TO "Y")
        param.put("G", G_Revalue);

        // Invoice Date
        param.put("H", H_InvoiceDate);

        // Due Date
        param.put("I", I_DueDate);

        // VAT Applicable
        param.put("J", "T");

        // VAT Txn Type
        param.put("K", "DT");

        // VAT Tax Cd
        String taxCodeHead = space;
        taxCodeHead = taxcode;
        param.put("L", taxCodeHead);

        // Salesperson(IN CORP CHANGE TO " ")
        param.put("M", M_Salesperson);

        // Credit Analyst
        param.put("N", N_CreditAnalyst);

        // Collector
        param.put("O", O_Collector);

        // PO Nbr
        param.put("P", P_PONbr);

        // Q R S
        HashMap<String, Object> qrsHeader = this.getQRS_Header(header, calCase,exportAmt,billAmount);

        // Line Amount
        param.put("Q", (String) qrsHeader.get("Q"));

        // Curr Rate(IN CORP CHANGE TO " ")
        param.put("R", space);

        // Currency
        param.put("S", (String) qrsHeader.get("S"));

        // Account
        if (accRev == null || accRev.length() == 0) {
            param.put("T", space);
        } else {
            param.put("T", accRev.trim());
        }

        // Product
        param.put("U", space);

        // Process
        param.put("V", space);

        // Dept(IN CORP CHANGE TO " ")
        param.put("W", W_DeptlDa);

        // Affiliate NTT
        param.put("X", X_AffiliateNTT);

        //PC BUS Unit
        param.put("Y", busUnit);
        
        // Project
        param.put("Z", space);

        //Activity
        param.put("AA", space);
        
        //Source
        param.put("AB", space);
        
        //Category
        param.put("AC", space);
        
       //Sub Category
        param.put("AD", space );
        
        // CF7
        param.put("AE", space);

        // CF8
        param.put("AF", space);

        // CF9
        param.put("AG", space);

        // Line Descr
        // IN CORP if CN(when billType=CN, EntryType=CR), add CN in front of LineDesc
        if ("CR".equals(E_Entry_Type)) {
           AH_LineDesc = "CN" + AH_LineDesc;
        }
        param.put("AH", AH_LineDesc);

        this.insertDetails(fw, param);

        param.clear();
            } catch (Exception e) {
        }

    }
    
    /**
     * Set Billing Document TaxLine
     */
    private void subBillDocumentTaxLine(E_EXP_F01_CONS_Bean header,E_EXP_F01_CONS_Bean detail, int cal_Case, String busUnit,String defCur,int projectLen,String billRef,String itemExportGST,String itemGST,String Product,String Project,FileWriter fw){
        try {
            String A_BusUnit = busUnit;
            String B_Origin;
            String C_CustomerId;
            String E_Entry_Type;
            String F_Reason = " "; // IN CORP CHANGE T0 " "
            String G_Revalue = "Y"; // IN CORP CHANGE T0 "Y"
            String H_InvoiceDate;
            String I_DueDate;
            String M_Salesperson;
            String N_CreditAnalyst;
            String O_Collector;
            String P_PONbr;
            String X_AffiliateNTT;
            String space = " ";
            String AC_Category;
            String AD_SubCategory;

            Date billFrom, billTo;
            Calendar caFrom = Calendar.getInstance();
            Calendar caTo = Calendar.getInstance();

            int calCase = cal_Case;
            C_CustomerId = this.isNull(header.getCUST_ACC_NO());

            E_Entry_Type = this.billTypeTrans(header.getBILL_TYPE());
            H_InvoiceDate = header.getDATE_REQ();
            I_DueDate = header.getDUE_DATE();
            M_Salesperson = space; // IN CORP CHANGE T0 " "
            N_CreditAnalyst = "DEFAULT";
            O_Collector = "DEFAULT";
            P_PONbr = this.isNull(header.getCUST_PO());
            X_AffiliateNTT = this.isNull(header.getAFFILIATE_CODE());
            //AC_Category=this.isNull(header.getCO_CATEGORY());
            AC_Category=space; // IN CORP CHANGE T0 " "
            //AD_SubCategory=this.isNull(header.getCO_SUB_CATEGORY());
            AD_SubCategory=space; // IN CORP CHANGE T0 " "
            
            HashMap<String, String> param = new HashMap<String, String>();
            String D_InvoiceNbr = space;
            String J_VATApplicable = space;
            String K_VATTxnType = space;
            String L_VATTaxCd = space;
            String Q_LineAmount = space;
            String R_CurrRate = space;
            String S_Currency = space;
            String T_Account = space;
            String U_Product = space;
            String V_Process = space;
            String W_Dept = space;
            String Z_Project = space; // IN CORP CHANGE TO " "
            String Z_CF7 = space;
            String AF_CF8 = space;
            String AG_CF9 = space;
            String AH_LineDesc = space;
            String AA_Activity = space;
            String AB_Source = space;

            D_InvoiceNbr =billRef;
            J_VATApplicable = "T";
            K_VATTxnType = "DT";
            L_VATTaxCd = detail.getTAX_CODE();
            
            HashMap<String, Object> qrsDetail = this.getQRS_Detail(header, detail,calCase);
            HashMap<String, Object> qrsTax = this.getQRS_DetailTax(header,detail,itemExportGST,itemGST,calCase);
            Q_LineAmount = (String) qrsTax.get("Q");
            R_CurrRate = (String) qrsDetail.get("R");
            S_Currency = (String) qrsDetail.get("S");

            if (detail.getACC_CODE1() == null || detail.getACC_CODE1().length() == 0) {
                T_Account = space;
            } else {
                T_Account = detail.getACC_CODE1().trim();
            }

            U_Product = space; // IN CORP CHANGE TO " "
            V_Process = space;
            //W_Dept =this.isNull(header.getPPLSOFT_DEPT_ID()) ; // IN CORP CHANGE TO " "

            Z_CF7 = space;
            AF_CF8 = space;
            AG_CF9 = space;

            billFrom = detail.getBILL_FROM();
            billTo = detail.getBILL_TO();
            caFrom = Calendar.getInstance();
            caTo = Calendar.getInstance();
            caFrom.setTime(billFrom);
            caTo.setTime(billTo);
            List<String> desc2List = this.queryDAO.executeForObjectList("SELECT.BSYS.E_EXP_F01.getSVC_LEVEL2", detail.getID_CUST_PLAN_LINK());
            List<String> desc3List = this.queryDAO.executeForObjectList("SELECT.BSYS.E_EXP_F01.getSVC_LEVEL3", detail.getID_CUST_PLAN_LINK());
            //AF_CF8= this.queryDAO.executeForObject("SELECT.BSYS.GET_RATE_TYPE2", detail.getID_CUST_PLAN_LINK(),String.class); // IN CORP CHANGE TO " "
            
            AH_LineDesc = this.getLineDesc(caFrom, caTo, desc2List, desc3List, 0);

            param.put("A", A_BusUnit);
            
            String originCode = detail.getORIGIN_CODE();
            B_Origin = this.isNull(originCode);
            
            param.put("B", B_Origin);

            param.put("C", C_CustomerId);

            param.put("D", D_InvoiceNbr);

            param.put("E", E_Entry_Type);

            param.put("F", F_Reason); // IN CORP CHANGE TO " "

            param.put("G", G_Revalue); // IN CORP CHANGE TO "Y"

            param.put("H", H_InvoiceDate);

            param.put("I", I_DueDate);

            param.put("J", J_VATApplicable);

            param.put("K", K_VATTxnType);

            param.put("L", L_VATTaxCd);

            param.put("M", M_Salesperson); // IN CORP CHANGE T0 " "

            param.put("N", N_CreditAnalyst);

            param.put("O", O_Collector);

            param.put("P", P_PONbr);

            param.put("Q", Q_LineAmount);

            param.put("R", space); // IN CORP CHANGE T0 " "

            param.put("S", S_Currency);

            param.put("T", T_Account);

            param.put("U", U_Product);

            param.put("V", V_Process);

            param.put("W", W_Dept); // IN CORP CHANGE TO " "

            param.put("X", X_AffiliateNTT);

            param.put("Y", A_BusUnit);
            
            param.put("Z", Z_Project); // IN CORP CHANGE TO " "

            param.put("AA", AA_Activity);
            
            param.put("AB", AB_Source);
            
            param.put("AC", AC_Category); // IN CORP CHANGE TO " "
            
            param.put("AD", AD_SubCategory); // IN CORP CHANGE TO " "
            
            param.put("AE", Z_CF7);

            param.put("AF", AF_CF8); // IN CORP CHANGE TO " "

            param.put("AG", AG_CF9);

            // IN CORP if CN(when billType=CN, EntryType=CR), add CN in front of LineDesc
            if ("CR".equals(E_Entry_Type)) {
               AH_LineDesc = "CN" + AH_LineDesc;
            }
            param.put("AH", AH_LineDesc);

            this.insertDetails(fw, param);

            param.clear();

            // insert blank line
            this.insertSpaceLine(fw);

            } catch (Exception e) {
        }
    }
    
    /**
     * SUM(R2.ITEM_SUBTOTAL+R2.ITEM_GST) as BillAmt And 
     * SUM(R2.ITEM_EXPORT_AMT+R2.ITEM_EXPORT_GST) as ExpAMt
     * @param proCode 
     * @param detailsLists
     * @return BillAmt、ExpAMt
     */
    private Map<String, Object> getBillAmt(boolean isTaxCodeSR,String taxCode,String proCode,List<E_EXP_F01_CONS_Bean> detailsLists){
        Map<String, Object> resutlMap=new HashMap<String, Object>();
        DecimalFormat formatter = new DecimalFormat("0.0000");
        formatter.setRoundingMode(RoundingMode.HALF_UP);
        BigDecimal billamt = new BigDecimal(formatter.format(0));
        BigDecimal expamt = new BigDecimal(formatter.format(0));
        String originCode=" ";
        for(int i=0;i<detailsLists.size();i++){
            String productCode="";
            //TaxCode=='SR' SUM billamt by ProductCode
            if(isTaxCodeSR){
                productCode=CommonUtils.toString(detailsLists.get(i).getPRODUCT_CODE());
                if(!"".equals(productCode)){
                    if(productCode.length()>3){
                        productCode=productCode.substring(0,3);
                    }
                }
                if(proCode.equals(productCode)&&
                     CommonUtils.toString(detailsLists.get(i).getTAX_CODE()).equals(taxCode)){
                    //ITEM_SUBTOTAL
                    if(!"".equals(CommonUtils.toString(detailsLists.get(i).getITEM_SUBTOTAL()))){
                        BigDecimal subtotal = new BigDecimal(formatter.format(Double.parseDouble(detailsLists.get(i).getITEM_SUBTOTAL())));
                        billamt=billamt.add(subtotal);
                    }
                    //ITEM_GST
                    if(!"".equals(CommonUtils.toString(detailsLists.get(i).getITEM_GST()))){
                        BigDecimal gst = new BigDecimal(formatter.format(Double.parseDouble(detailsLists.get(i).getITEM_GST())));
                        billamt=billamt.add(gst);
                    }
                    //ITEM_EXPORT_AMT
                    if(!"".equals(CommonUtils.toString(detailsLists.get(i).getITEM_EXPORT_AMT()))){
                        BigDecimal exptotal = new BigDecimal(formatter.format(Double.parseDouble(detailsLists.get(i).getITEM_EXPORT_AMT())));
                        expamt=expamt.add(exptotal);
                    }
                    //ITEM_EXPORT_GST
                    if(!"".equals(CommonUtils.toString(detailsLists.get(i).getITEM_EXPORT_GST()))){
                        BigDecimal expgst = new BigDecimal(formatter.format(Double.parseDouble(detailsLists.get(i).getITEM_EXPORT_GST())));
                        expamt=expamt.add(expgst);
                    }
                   //only get the first originCode,when loop the detailsLists by ProductCode and TaxCode=='SR' 
                    if(" ".equals(originCode)){
                        originCode=isNull(detailsLists.get(i).getORIGIN_CODE());
                    }
                }
            }
            //TaxCode not equal 'SR' SUM billamt by TaxCode
            else {
                if ( CommonUtils.toString(detailsLists.get(i).getTAX_CODE()).equals(taxCode)) {
                    // ITEM_SUBTOTAL
                    if (!"".equals(CommonUtils.toString(detailsLists.get(i).getITEM_SUBTOTAL()))) {
                        BigDecimal subtotal = new BigDecimal(formatter.format(Double.parseDouble(detailsLists.get(i).getITEM_SUBTOTAL())));
                        billamt = billamt.add(subtotal);
                    }
                    // ITEM_GST
                    if (!"".equals(CommonUtils.toString(detailsLists.get(i).getITEM_GST()))) {
                        BigDecimal gst = new BigDecimal(formatter.format(Double.parseDouble(detailsLists.get(i).getITEM_GST())));
                        billamt = billamt.add(gst);
                    }
                    // ITEM_EXPORT_AMT
                    if (!"".equals(CommonUtils.toString(detailsLists.get(i).getITEM_EXPORT_AMT()))) {
                        BigDecimal exptotal = new BigDecimal(formatter.format(Double.parseDouble(detailsLists.get(i).getITEM_EXPORT_AMT())));
                        expamt = expamt.add(exptotal);
                    }
                    // ITEM_EXPORT_GST
                    if (!"".equals(CommonUtils.toString(detailsLists.get(i).getITEM_EXPORT_GST()))) {
                        BigDecimal expgst = new BigDecimal(formatter.format(Double.parseDouble(detailsLists.get(i).getITEM_EXPORT_GST())));
                        expamt = expamt.add(expgst);
                    }
                   //only get the first originCode,when loop the detailsLists by taxCode and TaxCode not equal 'SR' 
                    if(" ".equals(originCode)){
                        originCode=isNull(detailsLists.get(i).getORIGIN_CODE());
                    }
                }
            }
        }
        resutlMap.put("billamt", billamt);
        resutlMap.put("expamt", expamt);
        resutlMap.put("originCode", originCode);
        return resutlMap;
    }
    
    /**
     * SUM(ItemGST) & SUM(ItemExportGST)
     * @param proCode 
     * @param detailsLists
     * @return BillAmt、ExpAMt
     */
    private  BigDecimal getItemGST(BigDecimal totalItemGST,String itemGST){
        //Sum itemGST
        DecimalFormat formatter = new DecimalFormat("0.0000");
        formatter.setRoundingMode(RoundingMode.HALF_UP);
        BigDecimal totalGST = new BigDecimal(formatter.format(totalItemGST));
        if(!"".equals(itemGST)){
            double itemgst=Double.parseDouble(itemGST);
            BigDecimal subGST = new BigDecimal(formatter.format(itemgst));
            totalGST=totalGST.add(subGST);
        }
        return totalGST;
    }

    /**
     * Do Main_Sub
     */
    private void mainSub(List<E_EXP_F01_CONS_Bean> headerList, String busUnit, String accRev, 
        String advAcc, int projectLen, Calendar closingDate, String defCur, FileWriter fw) {
        //Get Tax_Code
        List<String> taxCodeList=new ArrayList<String>();

        for (int m = 0; m < headerList.size(); m++) {
            //Z=Select Distinct(R2.TAX_CODE)
            String taxCode=CommonUtils.toString(headerList.get(m).getTAX_CODE());
                if(!taxCodeList.contains(taxCode)){
                taxCodeList.add(taxCode);
            }
        }
        boolean isTaxCodeSR=false;
        if(taxCodeList.size()>=1){
            //Loop through Z(tax code)
            for (int n = 0; n < taxCodeList.size(); n++) {
                List<E_EXP_F01_CONS_Bean> detailList1 = new  ArrayList<E_EXP_F01_CONS_Bean>();
                List<E_EXP_F01_CONS_Bean> detailList2 = new  ArrayList<E_EXP_F01_CONS_Bean>();
                List<E_EXP_F01_CONS_Bean> detailList3 = new  ArrayList<E_EXP_F01_CONS_Bean>();
                List<E_EXP_F01_CONS_Bean> detailList4 = new  ArrayList<E_EXP_F01_CONS_Bean>();
                for (int s = 0; s < headerList.size(); s++) {
                    if(taxCodeList.get(n).toString().equals(CommonUtils.toString(headerList.get(s).getTAX_CODE()))){
                        E_EXP_F01_CONS_Bean header = new E_EXP_F01_CONS_Bean();
                        header = headerList.get(s);
                        String billCurrency = CommonUtils.toString(header.getBILL_CURRENCY());
                        String exportCur =  CommonUtils.toString(header.getEXPORT_CUR());
                        String isDisplayExpAmt = CommonUtils.toString(header.getIS_DISPLAY_EXP_AMT().toString());
                        int calCase = this.getQRSCase(billCurrency, exportCur, isDisplayExpAmt, defCur);
                        switch (calCase) {
                            case 1:
                                detailList1.add(header);
                                break;
                            case 2:
                                detailList2.add(header);
                                break;
                            case 3:
                                detailList3.add(header);
                                break;
                            case 4:
                                detailList4.add(header);
                                break;
                         }
                     }
                }

                int calCase = 1;

                if (detailList1.size() > 0) {
                    // One Billing Document for calCase1
                    calCase = 1;
                    oneBillingDocument(isTaxCodeSR, taxCodeList.get(n).toString(), detailList1, calCase, busUnit, accRev, 
                        advAcc, projectLen, closingDate, defCur, fw);
                }

                if (detailList2.size() > 0) {
                    // One Billing Document for calCase2
                    calCase = 2;
                    oneBillingDocument(isTaxCodeSR, taxCodeList.get(n).toString(), detailList2, calCase, busUnit, accRev, 
                        advAcc, projectLen, closingDate, defCur, fw);

                }

                if (detailList3.size() > 0) {
                    // One Billing Document for calCase3
                    calCase = 3;
                    oneBillingDocument(isTaxCodeSR, taxCodeList.get(n).toString(), detailList3, calCase, busUnit, accRev, 
                        advAcc, projectLen, closingDate, defCur, fw);
	
                }

                if (detailList4.size() > 0) {
                    // One Billing Document for calCase4
                    calCase = 4;
                    oneBillingDocument(isTaxCodeSR, taxCodeList.get(n).toString(), detailList4, calCase, busUnit, accRev, 
                        advAcc, projectLen, closingDate, defCur, fw);
                }
            }
        } 
    }

    /**
     * Do One Billing Document
     */
    private void oneBillingDocument(boolean isTaxCodeSR, String taxCode, List<E_EXP_F01_CONS_Bean> detailList, int calCase, String busUnit, String accRev, 
        String advAcc, int projectLen, Calendar closingDate, String defCur, FileWriter fw) {
        DecimalFormat formatter = new DecimalFormat("0.0000");
        formatter.setRoundingMode(RoundingMode.HALF_UP);
        //One Billing Document
        Map<String, Object> billAmtMap = getBillAmt(isTaxCodeSR, taxCode, "", detailList);
        String billRef= "";
        String billAmount=billAmtMap.get("billamt").toString();
        String exportAmt=billAmtMap.get("expamt").toString();
        String originCode=billAmtMap.get("originCode").toString();
        BigDecimal itemGST= new BigDecimal(formatter.format(0));
        BigDecimal itemExportGST= new BigDecimal(formatter.format(0));
        String U_Product="";
        String Z_Project="";
        //Set Header
        int lastRecordIndex = detailList.size() - 1;
        subBillDocumentHeader(detailList.get(0), detailList.get(lastRecordIndex), calCase, busUnit, accRev, defCur,exportAmt, billAmount, billRef, originCode, taxCode, fw);
        E_EXP_F01_CONS_Bean detail = new E_EXP_F01_CONS_Bean();
        for(int t=0;t<detailList.size();t++){
            //Get detail
            detail = detailList.get(t);
            U_Product=isNull(detail.getPRODUCT_CODE());
            Z_Project=isNull(detail.getJOB_NO());
            //SUM itemGST and itemExportGST
            itemGST=getItemGST(itemGST,CommonUtils.toString(detail.getITEM_GST()));
            itemExportGST=getItemGST(itemExportGST,CommonUtils.toString(detail.getITEM_EXPORT_GST()));
            //resutl_set_detail(R3)
            subBillDocumentDetail(detailList.get(0), detail, calCase, busUnit, defCur, accRev, advAcc, projectLen, closingDate, billRef, fw);
        }
        //Set TaxLine
        String itemgst=itemGST.toString();
        String itemexportgst=itemExportGST.toString();
        subBillDocumentTaxLine(detailList.get(0), detail, calCase, busUnit,defCur, projectLen, billRef, itemexportgst, itemgst, U_Product, Z_Project, fw);
     }
}