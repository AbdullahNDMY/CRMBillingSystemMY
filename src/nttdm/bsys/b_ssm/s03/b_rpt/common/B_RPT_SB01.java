/**
 * Billing System
 * 
 * SUBSYSTEM NAME : Subscription Information
 * SERVICE NAME : B_SSM_S03
 * FUNCTION : processing reports from B_SSM_S03
 * FILE NAME : B_RPT_SB01.java
 * 
 * Copyright (C) 2011 NTTDATA Corporation
 * 
 * History
 * 2011/09/12 [Duoc Nguyen] change to Jasper template
 */
package nttdm.bsys.b_ssm.s03.b_rpt.common;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.web.codelist.MappedCodeListLoader;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import nttdm.bsys.b_ssm.s03.b_rpt.data.B_RPT_Output;
import nttdm.bsys.b_ssm.s03.b_rpt.data.B_RPT_SB01_Input;
import nttdm.bsys.b_ssm.s03.b_rpt.data.ContentType;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.MessageUtil;
import nttdm.bsys.common.util.ZipUtil;
import nttdm.bsys.util.ApplicationContextProvider;
import nttdm.bsys.util.exception.ReportException;

import org.springframework.context.ApplicationContext;

/**
 * B_RPT_SB01 class use to export MS Word<br/>
 * @author  NTTData Vietnam
 * @version 1.1
 */
public class B_RPT_SB01 extends AB_RPT<B_RPT_SB01_Input> {
    private Map<String, Object> currencyMap = null;
    private Map<String, Object> parameter = new HashMap<String, Object>();
    private String template;
    
    private List<String> arrayInfos;
    private Map<String, Object> compInfo;
    private Map<String, Object> custInfo;
    private Map<String, Object> comInfo;
    private Map<String, Object> comCtcInfo1;
    private Map<String, Object> comCtcInfo2;
    private Map<String, Object> subcriptionInfo;
    private List<Map<String, Object>> operationAddress;
    private Map<String, Object> ftpInfo;
    private Map<String, Object> mailAddress;
    private List<Map<String, Object>> mailAccount;
    private List<Map<String, Object>> rackPowers;
    private List<Map<String, Object>> itContacts;
    private Map<String, Object> teamwork;
    
    public B_RPT_SB01(QueryDAO queryDAO) {
        super(queryDAO);
    }

    public B_RPT_Output export(B_RPT_SB01_Input params) throws ReportException {
        try {
            /**
             * init parameter
             */
            parameter.put("idCust", params.getIdCust());
            parameter.put("idCustPlan", params.getIdCustPlan());
            parameter.put("idSubInfo", params.getIdSubInfo());
            parameter.put("addType", params.getAddType());
            this.template = params.getReportPath();
            
            /**
             * prepare report information
             */
            this.compInfo = queryDAO.executeForObject("B_RPT_SB01.getCompanyInfo", null, HashMap.class);
            this.custInfo = queryDAO.executeForObject("B_RPT_SB01.getCustInfo", parameter, HashMap.class);
            this.comInfo = queryDAO.executeForObject("B_RPT_SB01.getComInfo", null, HashMap.class);
            this.comCtcInfo1 = queryDAO.executeForObject("B_RPT_SB01.getComCtcInfo", "SC", HashMap.class);
            this.comCtcInfo2 = queryDAO.executeForObject("B_RPT_SB01.getComCtcInfo", "TC", HashMap.class);
            this.subcriptionInfo = queryDAO.executeForMap("B_RPT_SB01.getSubscriptionInfo", parameter);
            this.operationAddress = queryDAO.executeForMapList("B_RPT_SB01.getOperationAddress", parameter);
            this.ftpInfo = queryDAO.executeForMap("B_RPT_SB01.getFTPInfo", parameter);
            this.mailAddress = queryDAO.executeForMap("B_RPT_SB01.getMailAddress", parameter);
            this.mailAccount = queryDAO.executeForMapList("B_RPT_SB01.getMailAccount", parameter);
            this.rackPowers = queryDAO.executeForMapList("B_RPT_SB01.getRackPower", parameter);
            this.itContacts = queryDAO.executeForMapList("B_RPT_SB01.getItContact", parameter);
            this.teamwork = queryDAO.executeForObject("B_RPT_SB01.getTeamwork", parameter, HashMap.class);

            String[] idCustPlanGrps = params.getIdCustPlanGrp();
            File[] genFiles = new File[idCustPlanGrps.length];
            String[] zipFileNames = new String[idCustPlanGrps.length];
            String sessionDirectory = params.getSessionDirectory() + File.separator;
            
            String[] selectedServiceIds = params.getSelectedServiceIds();
            String[] serviceDescs = params.getServiceDescs();
            String noticeMode = params.getNoticeMode();
            
            //get current date
            SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmss");
            String today = format.format(Calendar.getInstance().getTime());
            
            String moduleId = params.getModuleId();
            if(moduleId != null && moduleId.equals(BillingSystemConstants.SUB_MODULE_B_SSM)) {
                String idCustPlanGrp;
                List<List<String>> allReport = new ArrayList<List<String>>();
                for(int i = 0; i < idCustPlanGrps.length; i++) {
                    idCustPlanGrp = idCustPlanGrps[i];
                    parameter.put("idCustPlanGrp", idCustPlanGrp);
                    List<String> arrInfo = queryDAO.executeForObjectList("B_RPT_SB01.getSSM", parameter);
                    allReport.add(arrInfo);
                }
                arrayInfos = getArrayInfos(allReport);
                
                for(int i = 0; i < idCustPlanGrps.length; i++) {
                    idCustPlanGrp = idCustPlanGrps[i];
                    parameter.put("idCustPlanGrp", idCustPlanGrp);
                    String serviceDesc = "";
                    for (int j = 0; j < selectedServiceIds.length; j++) {
                        if (idCustPlanGrp.equals(selectedServiceIds[j])) {
                            serviceDesc = serviceDescs[j];
                            break;
                        }
                    }
                    
                    Map<String, Object> planInfo = queryDAO.executeForObject("B_RPT_SB01.getPlanInfo", parameter, Map.class);
                    if(planInfo == null) {
                        String errorMsg = MessageUtil.get("info.ERR2SC002");
                        throw new ReportException(errorMsg);
                    }
                    String fileName = "NL_" + params.getIdCust() + "_" + params.getIdSubInfo() + "_" + today + "_" + addPadding(String.valueOf(i + 1), "0", 2) + ".pdf";
                    File file = generateDoc(sessionDirectory + fileName, planInfo,noticeMode,serviceDesc);
                    genFiles[i] = file;
                    zipFileNames[i] = fileName;
                }
            }// this branch current is unused
            else if(moduleId != null && moduleId.equals(BillingSystemConstants.SUB_MODULE_B_CPM)) {
                parameter.remove("idCustPlanLinks");
                
                this.arrayInfos = queryDAO.executeForObjectList("B_RPT_SB01.getSSM", parameter);
                List<Map<String, Object>> planInfos = queryDAO.executeForMapList("B_RPT_SB01.getPlanInfo", parameter);
                if(planInfos.isEmpty()) {
                    String errorMsg = MessageUtil.get("info.ERR2SC002");
                    throw new ReportException(errorMsg);
                }
                for(int i = 0; i < planInfos.size(); i++) {
                    String fileName = "NL_" + params.getIdCust() + "_" + params.getIdSubInfo() + "_" + today + "_" + addPadding(String.valueOf(i + 1), "0", 2) + ".pdf";
                    File file = generateDoc(sessionDirectory + fileName, planInfos.get(i),noticeMode,"");
                    genFiles[i] = file;
                    zipFileNames[i] = fileName;
                }
            }
            else {
                throw new ReportException("Invalid module caller !");
            }
            
            /**
             * generate report
             */
            String zipFileName = ZipUtil.zip(genFiles, zipFileNames, sessionDirectory);
            InputStream in = new BufferedInputStream(new FileInputStream(zipFileName));
            
            /**
             * download
             */
            B_RPT_Output output = new B_RPT_Output();
            output.setContentType(ContentType.OCTET_STREAM);
            output.setFileName("NL_" + params.getIdCust() + "_" + params.getIdSubInfo() + "_" + today + ".zip");
            output.setInputStream(in);
            return output;
        } catch (Exception e) {
            throw new ReportException(e.getMessage());
        }
    }
    
    private File generateDoc(String fileName, Map<String, Object> planInfo,String noticeMode,String serviceDesc) throws FileNotFoundException, JRException {
        Map<String, Object> data = new HashMap<String, Object>();
        /**
         * Header - company information
         */
        Map<String, Object> company = new HashMap<String, Object>();        
        company.put("name", compInfo.get("COM_NAME"));
        company.put("addLine1", CommonUtils.toString(compInfo.get("COM_ADR_LINE1")));
        company.put("addLine2", CommonUtils.toString(compInfo.get("COM_ADR_LINE2")));
        company.put("addLine3", CommonUtils.toString(compInfo.get("COM_ADR_LINE3")));
        String companyZipCode = CommonUtils.toString(compInfo.get("ZIP_CODE"));
        String companyCountry = CommonUtils.toString(compInfo.get("COUNTRY"));
        if(!CommonUtils.isEmpty(companyZipCode)&&!CommonUtils.isEmpty(companyCountry)) {
            company.put("addLine4", getCurWording(companyCountry) + " " + companyZipCode);
        } else if(CommonUtils.isEmpty(companyZipCode)&&!CommonUtils.isEmpty(companyCountry)) {
            company.put("addLine4", getCurWording(companyCountry));
        } else if(!CommonUtils.isEmpty(companyZipCode)&&CommonUtils.isEmpty(companyCountry)) {
            company.put("addLine4", companyZipCode);
        } else {
            company.put("addLine4", "");
        }
        company.put("tel", compInfo.get("COM_TEL_NO"));
        company.put("fax", compInfo.get("COM_FAX_NO"));
        company.put("regis", compInfo.get("COM_REGNO"));
        company.put("gstRegis", compInfo.get("COM_GST_REG_NO"));
        company.put("website", compInfo.get("COM_WEBSITE"));
        
        company.put("addLine5", "Tel : " + CommonUtils.toString(compInfo.get("COM_TEL_NO"))
                + " Fax : " + CommonUtils.toString(compInfo.get("COM_FAX_NO")));
        company.put("addLine6", "Reg. No : " + CommonUtils.toString(compInfo.get("COM_REGNO")));
        //company.put("addLine7", "GST Reg. No. : " + CommonUtils.toString(compInfo.get("COM_GST_REG_NO")));
        company.put("addLine7", "Website : " + CommonUtils.toString(compInfo.get("COM_WEBSITE")));
        //change the display address
        addressDisplayChange(company, "addLine");
        data.put("company", company);
        
        /**
         * Header - customer information
         */
        Map<String, Object> customer = new HashMap<String, Object>();       
        customer.put("name", custInfo.get("CUST_NAME"));
        customer.put("addLine1", custInfo.get("ADR_LINE1"));
        customer.put("addLine2", custInfo.get("ADR_LINE2"));
        customer.put("addLine3", custInfo.get("ADR_LINE3"));
        String custZipCode = CommonUtils.toString(custInfo.get("ZIP_CODE"));
        String custCountry = CommonUtils.toString(custInfo.get("COUNTRY"));
        if(!CommonUtils.isEmpty(custZipCode)&&!CommonUtils.isEmpty(custCountry)) {
            customer.put("addLine4", this.getCurWording(custCountry)+" "+custZipCode);
        } else if(CommonUtils.isEmpty(custZipCode)&&!CommonUtils.isEmpty(custCountry)) {
            customer.put("addLine4", this.getCurWording(custCountry));
        } else if(!CommonUtils.isEmpty(custZipCode)&&CommonUtils.isEmpty(custCountry)) {
            customer.put("addLine4", custZipCode);
        } else {
            customer.put("addLine4", "");
        }
        
        String customerType = CommonUtils.toString(custInfo.get("CUSTOMER_TYPE")).trim();
        String custTelNo = "";
        String custFaxNo = "";
        if ("CORP".equals(customerType)){
            custTelNo = CommonUtils.toString(custInfo.get("CO_TEL_NO"));
            custFaxNo = CommonUtils.toString(custInfo.get("CO_FAX_NO"));
        } else if ("CONS".equals(customerType)){
            custTelNo = CommonUtils.toString(custInfo.get("HOME_TEL_NO"));
            custFaxNo = CommonUtils.toString(custInfo.get("HOME_FAX_NO"));
        }
        customer.put("tel", custTelNo);
        customer.put("fax", custFaxNo);
        
        //customer.put("addLine5", "Tel                          : " + custTelNo);
        //customer.put("addLine6", "Fax                         : " + custFaxNo);
        //change the display address
        addressDisplayChange(customer, "addLine");
        data.put("customer", customer);
        
        /**
         * Header
         */
        data.put("date", getToday());
        data.put("customerID", parameter.get("idCust"));
        data.put("subscriptionID", parameter.get("idSubInfo"));
        data.put("billDesc", planInfo.get("BILL_DESC"));
        data.put("applyDate", planInfo.get("DATE_CREATED_STR"));
        
        data.put("billDescAndPlan", serviceDesc);
        /**
         * notice mode
         */
        data.put("noticeMode", noticeMode);

        /**
         * Router Settings 
         */
        if((arrayInfos.contains("1") || arrayInfos.contains("2") || arrayInfos.contains("3") || arrayInfos.contains("4") || 
                arrayInfos.contains("5") || arrayInfos.contains("6") || arrayInfos.contains("7") || arrayInfos.contains("8") || 
                arrayInfos.contains("9") || arrayInfos.contains("10") || arrayInfos.contains("11") || arrayInfos.contains("12") || 
                arrayInfos.contains("13")) && subcriptionInfo != null) {
            List<Map<String, Object>> routerSetting = new ArrayList<Map<String, Object>>();
            //Access Account
            if(arrayInfos.contains("1")) {
                Map<String, Object> rs_accessAccount = new HashMap<String, Object>();
                rs_accessAccount.put("name", "Access Account");
                rs_accessAccount.put("value", subcriptionInfo.get("ACCESS_ACCOUNT"));
                routerSetting.add(rs_accessAccount);
            }
            //Access Password
            if(arrayInfos.contains("2")) {
                Map<String, Object> rs_accessPassword = new HashMap<String, Object>();
                rs_accessPassword.put("name", "Access Password");
                rs_accessPassword.put("value", subcriptionInfo.get("ACCESS_PW"));
                routerSetting.add(rs_accessPassword);
            }
            //Access Telephone Number
            if(arrayInfos.contains("3")) {
                Map<String, Object> rs_accessTelephone = new HashMap<String, Object>();
                rs_accessTelephone.put("name", "Access Telephone Number");
                rs_accessTelephone.put("value", subcriptionInfo.get("ACCESS_TEL"));
                routerSetting.add(rs_accessTelephone);
            }
            //SSID
            if(arrayInfos.contains("4")) {
                Map<String, Object> rs_ssid = new HashMap<String, Object>();
                rs_ssid.put("name", "SSID");
                rs_ssid.put("value", subcriptionInfo.get("SSID"));
                routerSetting.add(rs_ssid);
            }
            //Wep Key
            if(arrayInfos.contains("5")) {
                Map<String, Object> rs_wepKey = new HashMap<String, Object>();
                //#263 [T11] Add customer type at inquiry screen and export result CT 06062017
                rs_wepKey.put("name", "SSID Key");
                //#263 [T11] Add customer type at inquiry screen and export result CT 06062017
                rs_wepKey.put("value", subcriptionInfo.get("WEP_KEY"));
                routerSetting.add(rs_wepKey);
            }
            //Router Password
            if(arrayInfos.contains("6")) {
                Map<String, Object> rs_routePassword = new HashMap<String, Object>();
                rs_routePassword.put("name", "Router Password");
                rs_routePassword.put("value", subcriptionInfo.get("ROUTER_PW"));
                routerSetting.add(rs_routePassword);
            }
            //Router No.
            if(arrayInfos.contains("7")) {
                Map<String, Object> rs_routeNo = new HashMap<String, Object>();
                rs_routeNo.put("name", "Router No.");
                rs_routeNo.put("value", subcriptionInfo.get("ROUTER_NO"));
                routerSetting.add(rs_routeNo);
            }
            //Router Type
            if(arrayInfos.contains("8")) {
                Map<String, Object> rs_routeType = new HashMap<String, Object>();
                rs_routeType.put("name", "Router Type");
                rs_routeType.put("value", subcriptionInfo.get("ROUTER_TYPE"));
                routerSetting.add(rs_routeType);
            }
            //Modem No.
            if(arrayInfos.contains("9")) {
                Map<String, Object> rs_modemNo = new HashMap<String, Object>();
                rs_modemNo.put("name", "Modem No.");
                rs_modemNo.put("value", subcriptionInfo.get("MODEM_NO"));
                routerSetting.add(rs_modemNo);
            }
            //MAC ID
            if(arrayInfos.contains("10")) {
                Map<String, Object> rs_macID = new HashMap<String, Object>();
                rs_macID.put("name", "MAC ID");
                rs_macID.put("value", subcriptionInfo.get("MAC_ID"));
                routerSetting.add(rs_macID);
            }
            //ADSL / DEL No.
            if(arrayInfos.contains("11")) {
                Map<String, Object> rs_adsl = new HashMap<String, Object>();
                rs_adsl.put("name", "ADSL / DEL No.");
                rs_adsl.put("value", subcriptionInfo.get("ADSL_DEL_NO"));
                routerSetting.add(rs_adsl);
            }
            //Circuit ID
            if(arrayInfos.contains("12")) {
                Map<String, Object> rs_circuitID = new HashMap<String, Object>();
                rs_circuitID.put("name", "Circuit ID");
                rs_circuitID.put("value", subcriptionInfo.get("CIRCUIT_ID"));
                routerSetting.add(rs_circuitID);
            }
            //Carrier
            if(arrayInfos.contains("13")) {
                Map<String, Object> rs_carrier = new HashMap<String, Object>();
                rs_carrier.put("name", "Carrier");
                rs_carrier.put("value", subcriptionInfo.get("CARRIER_NAME"));
                routerSetting.add(rs_carrier);
            }
            data.put("routerSetting", new JRMapCollectionDataSource(routerSetting));
        }
        
        /**
         * Browser Information
         */
        if(arrayInfos.contains("15")) {
            List<Map<String, Object>> browserInfos = new ArrayList<Map<String, Object>>();
            Map<String, Object> browserInfo = new HashMap<String, Object>();        
            browserInfo.put("proxyServer", comInfo == null ? "" : comInfo.get("PROXSERV_NAME"));
            browserInfo.put("port", comInfo == null ? "" : comInfo.get("PORT_NO"));
            browserInfos.add(browserInfo);
            data.put("browserInfo", new JRMapCollectionDataSource(browserInfos));
        }
        
        /**
         * Operation Address
         */
        if(arrayInfos.contains("18")) {
            List<Map<String, Object>> operationAddressDS = new ArrayList<Map<String, Object>>();
            for(Map<String, Object> operationAdd : operationAddress) {
                Map<String, Object> addressDS = new HashMap<String, Object>();
                StringBuffer addressAllInfo = new StringBuffer();
                if (operationAdd!=null) {
                    addressAllInfo.append(CommonUtils.toString(operationAdd.get("ADR_LINE1"))).append("\r\n")
                                  .append(CommonUtils.toString(operationAdd.get("ADR_LINE2"))).append("\r\n")
                                  .append(CommonUtils.toString(operationAdd.get("ADR_LINE3")));
                    String operationCountry = CommonUtils.toString(operationAdd.get("COUNTRY")).trim();
                    String operationZip = CommonUtils.toString(operationAdd.get("ZIP_CODE")).trim();
                    if(!CommonUtils.isEmpty(operationCountry)&&!CommonUtils.isEmpty(operationZip)) {
                        addressAllInfo.append("\r\n").append(getCurWording(operationCountry)).append(" ").append(operationZip);
                    } else if(CommonUtils.isEmpty(operationCountry)&&!CommonUtils.isEmpty(operationZip)) {
                        addressAllInfo.append("\r\n").append(operationZip);
                    } else if(!CommonUtils.isEmpty(operationCountry)&&CommonUtils.isEmpty(operationZip)) {
                        addressAllInfo.append("\r\n").append(getCurWording(operationCountry));
                    }
                }
                addressDS.put("address1", addressAllInfo.toString().trim());
                operationAddressDS.add(addressDS);
            }
            data.put("operationAdd", new JRMapCollectionDataSource(operationAddressDS));
        }
        
        /**
         * FTP Interface Information
         */
        if(arrayInfos.contains("19")) {
            List<Map<String, Object>> ftpInfoDS = new ArrayList<Map<String, Object>>();
            Map<String, Object> ftpDS = new HashMap<String, Object>();
            ftpDS.put("titleName", "FTP IP Address");
            ftpDS.put("colon", ":");
            ftpDS.put("value", CommonUtils.toString(ftpInfo.get("FTP_IP")));
            ftpInfoDS.add(ftpDS);
            
            ftpDS = new HashMap<String, Object>();
            ftpDS.put("titleName", "FTP Account Name");
            ftpDS.put("colon", ":");
            ftpDS.put("value", CommonUtils.toString(ftpInfo.get("FTP_ACC_NAME")));
            ftpInfoDS.add(ftpDS);
            
            ftpDS = new HashMap<String, Object>();
            ftpDS.put("titleName", "FTP Password");
            ftpDS.put("colon", ":");
            ftpDS.put("value", CommonUtils.toString(ftpInfo.get("FTP_PW")));
            ftpInfoDS.add(ftpDS);
            
            ftpDS = new HashMap<String, Object>();
            ftpDS.put("titleName", "Capacity of Web Server");
            ftpDS.put("colon", ":");
            ftpDS.put("value", CommonUtils.toString(ftpInfo.get("SERVER_CAPACITY")));
            ftpInfoDS.add(ftpDS);
            
            ftpDS = new HashMap<String, Object>();
            ftpDS.put("titleName", "Default Page");
            ftpDS.put("colon", ":");
            ftpDS.put("value", CommonUtils.toString(ftpInfo.get("DEFAULT_PAGE")));
            ftpInfoDS.add(ftpDS);
            
            ftpDS = new HashMap<String, Object>();
            ftpDS.put("titleName", "URL");
            ftpDS.put("colon", ":");
            ftpDS.put("value", CommonUtils.toString(ftpInfo.get("FTP_URL")));
            ftpInfoDS.add(ftpDS);
            data.put("ftpInfo", new JRMapCollectionDataSource(ftpInfoDS));
        }

        /**
         * Mail Account Information
         */
        if(arrayInfos.contains("16") && subcriptionInfo != null) {
            List<Map<String, Object>> mailAccountDS = new ArrayList<Map<String, Object>>();
            Map<String, Object> mailDS = new HashMap<String, Object>();
            if (mailAddress!=null) {
                mailDS.put("titleName", "Domain Name");
                mailDS.put("colon", ":");
                mailDS.put("value", CommonUtils.toString(mailAddress.get("DOMAIN_NAME")));
                mailAccountDS.add(mailDS);
            }
            if (comInfo!=null) {
                mailDS = new HashMap<String, Object>();
                mailDS.put("titleName", "Primary Domain Name");
                mailDS.put("colon", ":");
                mailDS.put("value", CommonUtils.toString(comInfo.get("PRIMDOMAIN_NO")));
                mailAccountDS.add(mailDS); 
                
                mailDS = new HashMap<String, Object>();
                mailDS.put("titleName", "Second Domain Name");
                mailDS.put("colon", ":");
                mailDS.put("value", CommonUtils.toString(comInfo.get("SECDOMAIN_NO")));
                mailAccountDS.add(mailDS);
            }
            if (mailAddress!=null) {
                mailDS = new HashMap<String, Object>();
                mailDS.put("titleName", "SMTP Server Name");
                mailDS.put("colon", ":");
                mailDS.put("value", CommonUtils.toString(mailAddress.get("SMTP_SERVER_NAME")));
                mailAccountDS.add(mailDS);
                
                mailDS = new HashMap<String, Object>();
                mailDS.put("titleName", "POP Server Name");
                mailDS.put("colon", ":");
                mailDS.put("value", CommonUtils.toString(mailAddress.get("POP_SERVER_NAME")));
                mailAccountDS.add(mailDS);
                
                mailDS = new HashMap<String, Object>();
                mailDS.put("titleName", "Webmail URL");
                mailDS.put("colon", ":");
                mailDS.put("value", CommonUtils.toString(mailAddress.get("WEBMAIL_URL")));
                mailAccountDS.add(mailDS);
            }
            data.put("mailAccount", new JRMapCollectionDataSource(mailAccountDS));
        }
        
        /**
         * Mail Address
         */
        if(arrayInfos.contains("17") && mailAccount != null) {
            List<Map<String, Object>> mailAddressDS = new ArrayList<Map<String, Object>>();
            Map<String, Object> mailAcc = null;
            for(int i = 0; i < mailAccount.size(); i++) {
                mailAcc = mailAccount.get(i);
                Map<String, Object> mailDS = new HashMap<String, Object>();
                mailDS.put("no", String.valueOf(i + 1));
                mailDS.put("mailAccount", mailAcc.get("MAIL_ACCOUNT"));
                mailDS.put("mailPassword", mailAcc.get("MAIL_PW"));
                mailDS.put("popAccount", mailAcc.get("POP_ACCOUNT"));
                mailDS.put("mailBox", toString(mailAcc.get("MAILBOX_SIZE")));
                mailDS.put("boxQty", toString(mailAcc.get("MAILBOX_QTY")));
                String virusScan = CommonUtils.toString(mailAcc.get("VIRUS_SCAN")).trim();
                if ("1".equals(virusScan)) {
                    mailDS.put("virusScan", "Yes");
                } else {
                    mailDS.put("virusScan", "No");
                }
                String junkMgt = CommonUtils.toString(mailAcc.get("JUNK_MGT")).trim();
                if ("1".equals(junkMgt)) {
                    mailDS.put("junkMgt", "Yes");
                } else {
                    mailDS.put("junkMgt", "No");
                }
                String antiSpam = CommonUtils.toString(mailAcc.get("ANTI_SPAM")).trim();
                if ("1".equals(antiSpam)) {
                    mailDS.put("antiSpam", "Yes");
                } else {
                    mailDS.put("antiSpam", "No");
                }
                mailAddressDS.add(mailDS);
            }
            data.put("mailAddress", new JRMapCollectionDataSource(mailAddressDS));
        }
        
        /**
         * Rack No. and Max Power
         */
        if(arrayInfos.contains("20") && rackPowers != null) {
            List<Map<String, Object>> rackPowerDS = new ArrayList<Map<String, Object>>();
            for(int i = 0; i < rackPowers.size(); i++) {
                Map<String, Object> rpDS = new HashMap<String, Object>();
                Map<String, Object> rackPower = rackPowers.get(i);
                rpDS.put("no", String.valueOf(i + 1));
                rpDS.put("rackNo", rackPower.get("RACK_NO"));
                rpDS.put("maxPower", rackPower.get("MAX_POWER"));
                rpDS.put("equipLoc", CommonUtils.toString(rackPower.get("EQUIP_LOCATION")));
                rpDS.put("equipSuite", CommonUtils.toString(rackPower.get("EQUIP_SUITE")));
                rackPowerDS.add(rpDS);
            }
            data.put("rackPower", new JRMapCollectionDataSource(rackPowerDS));
        }
        
        /**
         * IT Contact
         */
        if(arrayInfos.contains("14") && itContacts != null) {
            List<Map<String, Object>> itContactDS = new ArrayList<Map<String, Object>>();
            for(int i = 0; i < itContacts.size(); i++) {
                Map<String, Object> contactDS = new HashMap<String, Object>();
                Map<String, Object> itContact = itContacts.get(i);
                contactDS.put("no", String.valueOf(i + 1));
                contactDS.put("name", itContact.get("NAME"));
                contactDS.put("designation", itContact.get("DESIGNATION"));
                contactDS.put("email", itContact.get("EMAIL"));
                contactDS.put("tel", itContact.get("TEL_NO"));
                contactDS.put("fax", itContact.get("FAX_NO"));
                itContactDS.add(contactDS);
            }
            data.put("itContact", new JRMapCollectionDataSource(itContactDS));
        }

        /**
         * Teamwork
         */
        if(arrayInfos.contains("27") && teamwork != null) {
            List<Map<String, Object>> teamworkDS = new ArrayList<Map<String, Object>>();
            List<Map<String, Object>> teamworkClientDS = new ArrayList<Map<String, Object>>();
            Map<String, Object> teamDS = new HashMap<String, Object>();
            teamDS.put("titleName", "URL");
            teamDS.put("value", CommonUtils.toString(teamwork.get("URL")));
            teamworkDS.add(teamDS);
            
            teamDS = new HashMap<String, Object>();
            teamDS.put("titleName", "User ID");
            teamDS.put("value", CommonUtils.toString(teamwork.get("ADMIN_ID")));
            teamworkDS.add(teamDS);
            
            teamDS = new HashMap<String, Object>();
            teamDS.put("titleName", "Password");
            teamDS.put("value", CommonUtils.toString(teamwork.get("ADMIN_PW")));
            teamworkDS.add(teamDS);
            
            teamDS = new HashMap<String, Object>();
            teamDS.put("titleName", "Email Address");
            teamDS.put("value", CommonUtils.toString(teamwork.get("EMAIL_DOMAIN_ADR")));
            teamworkClientDS.add(teamDS);
            
            teamDS = new HashMap<String, Object>();
            teamDS.put("titleName", "Incoming Mail Server (POP3)");
            teamDS.put("value", CommonUtils.toString(teamwork.get("SERVER_POP3")));
            teamworkClientDS.add(teamDS);
            
            teamDS = new HashMap<String, Object>();
            teamDS.put("titleName", "Incoming Mail Server (IMAP)");
            teamDS.put("value", CommonUtils.toString(teamwork.get("SERVER_IMAP")));
            teamworkClientDS.add(teamDS);
            
            teamDS = new HashMap<String, Object>();
            teamDS.put("titleName", "Outgoing Mail Server (SMTP)");
            teamDS.put("value", CommonUtils.toString(teamwork.get("SERVER_SMTP")));
            teamworkClientDS.add(teamDS);
            
            teamDS = new HashMap<String, Object>();
            teamDS.put("titleName", "Account Name");
            teamDS.put("value", CommonUtils.toString(teamwork.get("ACCOUNT_NAME")));
            teamworkClientDS.add(teamDS);
            
            teamDS = new HashMap<String, Object>();
            teamDS.put("titleName", "Password");
            teamDS.put("value", CommonUtils.toString(teamwork.get("ACCOUNT_PW")));
            teamworkClientDS.add(teamDS);
            
            data.put("teamwork", new JRMapCollectionDataSource(teamworkDS));
            data.put("teamworkClient", new JRMapCollectionDataSource(teamworkClientDS));
        }
        
        /**
         * Footer
         */
        Map<String, Object> support = new HashMap<String, Object>();    
        if (comInfo != null) {
            support.put("homepage", comInfo.get("COM_WEBSITE"));
            support.put("tel", comInfo.get("COM_TEL_NO"));
        } else {
            support.put("homepage", "");
            support.put("tel", "");
        }
        if (comCtcInfo1 != null) {
            support.put("email_info", comCtcInfo1.get("COM_EMAIL"));
        } else {
            support.put("email_info", "");
        }
        if (comCtcInfo2 != null) {
            support.put("email_technical", comCtcInfo2.get("COM_EMAIL"));
        } else {
            support.put("email_technical", "");
        }
        
        data.put("support", support);
        
        //init header info
        initHeaderInfo(data);
        
        File file = new File(fileName);
        OutputStream outputFile = new FileOutputStream(file);
        JasperPrint jasperPrint = JasperFillManager.fillReport(template, data, new JREmptyDataSource());
        
        JRPdfExporter exporter = new JRPdfExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputFile);
        exporter.exportReport();
        return file;
    }
    
    /**
     * init header info
     * @param data
     */
    private void initHeaderInfo(Map<String, Object> data) {
        Map<String, Object> company = (Map<String, Object>)data.get("company");
        Map<String, Object> customer = (Map<String, Object>)data.get("customer");
        List<Map<String, Object>> headerInfoList = new ArrayList<Map<String, Object>>();
        Map<String, Object> headerInfo = new HashMap<String, Object>();
        headerInfo.put("companyName", CommonUtils.toString(company.get("name")));
        headerInfo.put("companyAddLine1", CommonUtils.toString(company.get("addLine1")));
        headerInfo.put("companyAddLine2", CommonUtils.toString(company.get("addLine2")));
        headerInfo.put("companyAddLine3", CommonUtils.toString(company.get("addLine3")));
        headerInfo.put("companyAddLine4", CommonUtils.toString(company.get("addLine4")));
        headerInfo.put("companyAddLine5", CommonUtils.toString(company.get("addLine5")));
        headerInfo.put("companyAddLine6", CommonUtils.toString(company.get("addLine6")));
        headerInfo.put("companyAddLine7", CommonUtils.toString(company.get("addLine7")));
        headerInfo.put("companyAddLine8", CommonUtils.toString(company.get("addLine8")));
        headerInfo.put("colon", ":");
//        headerInfo.put("companyTelTitle", "Tel : " + CommonUtils.toString(company.get("tel"))
//                                            + " Fax : " + CommonUtils.toString(company.get("fax")));
//        headerInfo.put("companyTel", CommonUtils.toString(company.get("tel")));
//        headerInfo.put("companyFaxTitle", "Fax");
//        headerInfo.put("companyFax", CommonUtils.toString(company.get("fax")));
//        headerInfo.put("companyRegistTitle", "Reg. No");
//        headerInfo.put("companyRegist", CommonUtils.toString(company.get("regis")));
//        headerInfo.put("companyGstRegistTitle", "GST Reg. No.");
//        headerInfo.put("companyGstRegist", CommonUtils.toString(company.get("gstRegis")));
//        headerInfo.put("websiteTitle", "Website");
//        headerInfo.put("website", CommonUtils.toString(company.get("website")));
        headerInfo.put("customerNameTitle", "To");
        headerInfo.put("customerName", CommonUtils.toString(customer.get("name")));
//                                        +"\r\n"+CommonUtils.toString(customer.get("addLine1"))
//                                        +"\r\n"+CommonUtils.toString(customer.get("addLine2"))
//                                        +"\r\n"+CommonUtils.toString(customer.get("addLine3"))
//                                        +"\r\n"+CommonUtils.toString(customer.get("addLine4")));
        headerInfo.put("customerAddLine1", CommonUtils.toString(customer.get("addLine1")));
        headerInfo.put("customerAddLine2", CommonUtils.toString(customer.get("addLine2")));
        headerInfo.put("customerAddLine3", CommonUtils.toString(customer.get("addLine3")));
        headerInfo.put("customerAddLine4", CommonUtils.toString(customer.get("addLine4")));
//        headerInfo.put("customerAddLine5", CommonUtils.toString(customer.get("addLine5")));
//        headerInfo.put("customerAddLine6", CommonUtils.toString(customer.get("addLine6")));
        headerInfo.put("customerTelTitle", "Tel");
        headerInfo.put("customerTel", CommonUtils.toString(customer.get("tel")));
        headerInfo.put("customerFaxTitle", "Fax");
        headerInfo.put("customerFax", CommonUtils.toString(customer.get("fax")));
        headerInfo.put("dateTitle", "Date");
        headerInfo.put("date", CommonUtils.toString(data.get("date")));
        headerInfo.put("customerIDTitle", "Customer ID");
        headerInfo.put("customerID", CommonUtils.toString(data.get("customerID")));
        headerInfo.put("subScriptionIDTitle", "Subscription ID");
        headerInfo.put("subScriptionID", CommonUtils.toString(data.get("subscriptionID")));
        headerInfoList.add(headerInfo);
        
        JRMapCollectionDataSource headerInfoDS = new JRMapCollectionDataSource(headerInfoList);
        data.put("headerInfo", headerInfoDS);
    }
    
    @SuppressWarnings("unchecked")
    private String getCurWording(Object curCode) {
        if(curCode == null) return "";
        if(currencyMap == null) {
            ApplicationContext context = ApplicationContextProvider.getApplicationContext();
            MappedCodeListLoader currencyCodeList = (MappedCodeListLoader) context.getBean("LIST_COUNTRY");
            currencyMap = currencyCodeList.getCodeListMap();
        }
        if(currencyMap.containsKey(curCode.toString().trim()))
            return currencyMap.get(curCode.toString().trim()).toString().trim();
        return "";
    }
    
    private String getToday() {
        Calendar car = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String today = format.format(car.getTime());
        return today;
    }
    
    private static String toString(Object obj) {
        if (obj == null)
            return " ";
        return obj.toString();
    }
    
    private static String addPadding(Object obj, String padding, int length) {
        String str = obj.toString();
        while(str.length() < length) {
            str = padding + str;
        }
        return str;
    }
    
    /**
     * change the display address
     * @param map
     * @param addrName
     */
    private void addressDisplayChange(Map<String, Object> map,String addrName){
        ArrayList<String> addrList = new ArrayList<String>();
        
        String addressName1 = addrName+"1";
        String addressName2 = addrName+"2";
        String addressName3 = addrName+"3";
        String addressName4 = addrName+"4";
//        String addressName5 = addrName+"5";
//        String addressName6 = addrName+"6";
//        String addressName7 = addrName+"7";
//        String addressName8 = addrName+"8";
        if(!"".equals(CommonUtils.toString(map.get(addressName1)).trim())){
            addrList.add(CommonUtils.toString(map.get(addressName1)).trim());
        }
        if(!"".equals(CommonUtils.toString(map.get(addressName2)).trim())){
            addrList.add(CommonUtils.toString(map.get(addressName2)).trim());
        }
        if(!"".equals(CommonUtils.toString(map.get(addressName3)).trim())){
            addrList.add(CommonUtils.toString(map.get(addressName3)).trim());
        }
        if(!"".equals(CommonUtils.toString(map.get(addressName4)).trim())){
            addrList.add(CommonUtils.toString(map.get(addressName4)).trim());
        }
//        if(!"".equals(CommonUtils.toString(map.get(addressName5)).trim())){
//            addrList.add(CommonUtils.toString(map.get(addressName5)).trim());
//        }
//        if(!"".equals(CommonUtils.toString(map.get(addressName6)).trim())){
//            addrList.add(CommonUtils.toString(map.get(addressName6)).trim());
//        }
//        if(!"".equals(CommonUtils.toString(map.get(addressName7)).trim())){
//            addrList.add(CommonUtils.toString(map.get(addressName7)).trim());
//        }
//        if(!"".equals(CommonUtils.toString(map.get(addressName8)).trim())){
//            addrList.add(CommonUtils.toString(map.get(addressName8)).trim());
//        }
        if(addrList.size()==0) {
            map.put(addressName1,"");
            map.put(addressName2,"");
            map.put(addressName3,"");
            map.put(addressName4,"");
//            map.put(addressName5,"");
//            map.put(addressName6,"");
//            map.put(addressName7,"");
//            map.put(addressName8,"");
        } else if(addrList.size()==1) {
            map.put(addressName1,addrList.get(0));
            map.put(addressName2,"");
            map.put(addressName3,"");
            map.put(addressName4,"");
//            map.put(addressName5,"");
//            map.put(addressName6,"");
//            map.put(addressName7,"");
//            map.put(addressName8,"");
        } else if(addrList.size()==2) {
            map.put(addressName1,addrList.get(0));
            map.put(addressName2,addrList.get(1));
            map.put(addressName3,"");
            map.put(addressName4,"");
//            map.put(addressName5,"");
//            map.put(addressName6,"");
//            map.put(addressName7,"");
//            map.put(addressName8,"");
        } else if(addrList.size()==3) {
            map.put(addressName1,addrList.get(0));
            map.put(addressName2,addrList.get(1));
            map.put(addressName3,addrList.get(2));
            map.put(addressName4,"");
//            map.put(addressName5,"");
//            map.put(addressName6,"");
//            map.put(addressName7,"");
//            map.put(addressName8,"");
        } else if(addrList.size()==4) {
            map.put(addressName1,addrList.get(0));
            map.put(addressName2,addrList.get(1));
            map.put(addressName3,addrList.get(2));
            map.put(addressName4,addrList.get(3));
//            map.put(addressName5,"");
//            map.put(addressName6,"");
//            map.put(addressName7,"");
//            map.put(addressName8,"");
        } 
//        else if(addrList.size()==5) {
//            map.put(addressName1,addrList.get(0));
//            map.put(addressName2,addrList.get(1));
//            map.put(addressName3,addrList.get(2));
//            map.put(addressName4,addrList.get(3));
//            map.put(addressName5,addrList.get(4));
//            map.put(addressName6,"");
//            map.put(addressName7,"");
//            map.put(addressName8,"");
//        } else if(addrList.size()==6) {
//            map.put(addressName1,addrList.get(0));
//            map.put(addressName2,addrList.get(1));
//            map.put(addressName3,addrList.get(2));
//            map.put(addressName4,addrList.get(3));
//            map.put(addressName5,addrList.get(4));
//            map.put(addressName6,addrList.get(5));
//            map.put(addressName7,"");
//            map.put(addressName8,"");
//        } else if(addrList.size()==7) {
//            map.put(addressName1,addrList.get(0));
//            map.put(addressName2,addrList.get(1));
//            map.put(addressName3,addrList.get(2));
//            map.put(addressName4,addrList.get(3));
//            map.put(addressName5,addrList.get(4));
//            map.put(addressName6,addrList.get(5));
//            map.put(addressName7,addrList.get(6));
//            map.put(addressName8,"");
//        } else if(addrList.size()==8) {
//            map.put(addressName1,addrList.get(0));
//            map.put(addressName2,addrList.get(1));
//            map.put(addressName3,addrList.get(2));
//            map.put(addressName4,addrList.get(3));
//            map.put(addressName5,addrList.get(4));
//            map.put(addressName6,addrList.get(5));
//            map.put(addressName7,addrList.get(6));
//            map.put(addressName8,addrList.get(7));
//        }
    }
    
    /**
     * get report id list
     * @param servicesReport
     * @return
     */
    private List<String> getArrayInfos(List<List<String>> servicesReport){
        List<String> arrayInfo = new ArrayList<String>();
        List<String> allSubArrayInfo = queryDAO.executeForObjectList("B_RPT_SB01.selectServiceBasicInfo", null);
        for(String type : allSubArrayInfo) {
            boolean allAllowReportFlg = true;
            if (servicesReport!=null && 0<servicesReport.size()) {
                for(List<String> allowReportArrayInfo : servicesReport) {
                    if(!allowReportArrayInfo.contains(type)) {
                        allAllowReportFlg = false;
                        break;
                    }
                }
            } else {
                continue;
            }
            if (allAllowReportFlg) {
                arrayInfo.add(type);
            }
        }
        return arrayInfo;
    }
}
