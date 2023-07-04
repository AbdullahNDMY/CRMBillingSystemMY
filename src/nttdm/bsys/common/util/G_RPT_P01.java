package nttdm.bsys.common.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.web.codelist.DBCodeListLoader;
import jp.terasoluna.fw.web.codelist.MappedCodeListLoader;
import nttdm.bsys.common.dao.UpdateDAOiBatisNuked;
import nttdm.bsys.common.util.dto.G_RPT_P01_Input;
import nttdm.bsys.common.util.dto.G_RPT_P01_Output;
import nttdm.bsys.util.ApplicationContextProvider;

import org.apache.struts.action.ActionErrors;
import org.springframework.context.ApplicationContext;

public class G_RPT_P01 extends AbstractGlobalProcess<G_RPT_P01_Input, G_RPT_P01_Output> {
	
	private static final String GET_SystemBase = "SELECT.G_RPT_P01.001";
	private static final String GET_FileName = "SELECT.G_RPT_P01.002";
	
	// getting parameter passed by caller method
	private BLogicMessages errors = null;
	private Map<String, Object> parameter = null;
	private String reportType = null;
	private String sysBase = null;
	private String resourceId = null;
	private String fileName = null;
	private String fileNameCsv = null;
	private String sysdateStr = "";
	boolean writeCsv = false;
	private GlobalProcessResult result;

	/**
	 * Internal Dev revision 5.0
	 */
	
	public G_RPT_P01(QueryDAO queryDAO, UpdateDAO updateDAO, UpdateDAOiBatisNuked updateDAONuked){
		this.queryDAO = queryDAO;
		this.updateDAO = updateDAO;
		this.updateDAONuked = updateDAONuked;	
	}

	public G_RPT_P01(){}

	public GlobalProcessResult execute(G_RPT_P01_Input param, G_RPT_P01_Output outputDTO){
		this.errors = new BLogicMessages();
		this.result = new GlobalProcessResult();
		this.parameter = new HashMap<String, Object>();
		
		File returnFile = null;
		reportType = param.getReportType();
		parameter.put("reportType", reportType);
		
		// 3.0 Retrieve SystemBase = SG
		sysBase = this.queryDAO.executeForObject(GET_SystemBase, parameter, String.class);
		
		if(sysBase != null){
			// 4.0 Retrieve Filename
			resourceId = reportType + sysBase;
			parameter.put("RESOURCE_ID", resourceId);
			fileName =  this.queryDAO.executeForObject(GET_FileName, parameter, String.class);
			
			if(fileName != null){
				String tmpFolder = System.getProperty("java.io.tmpdir");
				if("BIL".equals(reportType) || "CSB".equals(reportType)){
				    fileNameCsv = tmpFolder + "/" + fileName.replace("yyyymmddhhmmss", this.getSysdateStr1());
				} else {
				    fileNameCsv = tmpFolder + "/" + fileName.replace("yymmddhhmmss", this.getSysdateStr());
				}
		
				try{
					returnFile = new File(fileNameCsv);
					writeCsv = true;
				}catch(NullPointerException e){
					writeCsv = false;
				}				
			} else{
				addError("errors.ERR1SC052", "");
			}
		} else{
			addError("errors.ERR1SC052", "");
		}
				
		// 5.0 and 6.0 Write into .csv file
		if(writeCsv == true){
			if(param.getListAgingReport() != null){
				try {	
					FileWriter fw = new FileWriter(fileNameCsv);
					
					if("SAL".equals(reportType)){
						fw = writeSALHeader(fw, fileNameCsv, param.getListAgingReport());
												
					} else if("RRR".equals(reportType)){					
						fw = writeRRRHeader(fw, fileNameCsv, param.getListAgingReport());
						
					} else if("AGR".equals(reportType)){				
						fw = writeAGRHeader(fw, fileNameCsv, param.getListAgingReport());
						
					} else if("ACR".equals(reportType)){				
						fw = writeACRHeader(fw, fileNameCsv, param.getListAgingReport());
						
					} else if("CNM".equals(reportType)){				
						fw = writeCNMHeader(fw, fileNameCsv, param.getListAgingReport());
						
					}else if("TTL".equals(reportType)){
						fw = writeTTLHeader(fw, fileNameCsv, param.getListAgingReport());
					}else if("BIL".equals(reportType)){
					    fw = writeBILHeader(fw, fileNameCsv, param.getListAgingReport());
					}else if("CSB".equals(reportType)){
					    fw = writeCSBHeader(fw, fileNameCsv, param.getListAgingReport());
					} else{
						addError("errors.ERR1BT014",fileName);
					}
					fw.flush();
					fw.close();
				} catch (IOException e) {							
					addError("errors.ERR1BT014", fileName);
				}
			} else{
				addError("errors.ERR1BT014", writeCsv);
			}	
		} else{
			addError("errors.ERR1BT014", fileName);
		}	
		// end of execute method
		result.setFile(returnFile);
		result.setErrors(errors);
		return result;
	}

	private FileWriter writeCNMHeader(FileWriter fw, String FileNameCsv,
			List<HashMap<String, Object>> listAgingReport) throws IOException {
		List<String> headNameList = queryDAO.executeForObjectList(
				"SELECT.G_RPT_P01.003", resourceId);
		for (int i = 0; i < headNameList.size(); i++) {
			writeCsv(fw, headNameList.get(i));
			if (i != headNameList.size() - 1) {
				fw.append(',');
			}
		}

		fw.append("\r\n");
		return writeCNM(fw, FileNameCsv, listAgingReport);
	}
	private FileWriter writeTTLHeader(FileWriter fw, String FileNameCsv,
			List<HashMap<String, Object>> listAgingReport) throws IOException {
		List<String> headNameList = queryDAO.executeForObjectList(
				"SELECT.G_RPT_P01.003", resourceId);
		for (int i = 0; i < headNameList.size(); i++) {
			writeCsv(fw, headNameList.get(i));
			if (i != headNameList.size() - 1) {
				fw.append(',');
			}
		}

		fw.append("\r\n");
		return writeTTL(fw, FileNameCsv, listAgingReport);
	}
	
	private FileWriter writeBILHeader(FileWriter fw, String FileNameCsv,
            List<HashMap<String, Object>> listAgingReport) throws IOException {
        List<String> headNameList = queryDAO.executeForObjectList(
                "SELECT.G_RPT_P01.003", resourceId);
        for (int i = 0; i < headNameList.size(); i++) {
            writeCsv(fw, headNameList.get(i));
            if (i != headNameList.size() - 1) {
                fw.append(',');
            }
        }

        fw.append("\r\n");
        return writeBIL(fw, FileNameCsv, listAgingReport);
    }
	
	private FileWriter writeCSBHeader(FileWriter fw, String FileNameCsv,
            List<HashMap<String, Object>> listAgingReport) throws IOException {
        List<String> headNameList = queryDAO.executeForObjectList(
                "SELECT.G_RPT_P01.003", resourceId);
        for (int i = 0; i < headNameList.size(); i++) {
            if(i==6) {
                String columnName = this.queryDAO.executeForObject("SELECT.G_RPT_P01.004", null, String.class);
                writeCsv(fw, columnName);
                fw.append(',');
            } else {
                writeCsv(fw, headNameList.get(i));
                if (i != headNameList.size() - 1) {
                    fw.append(',');
                }
            }
        }

        fw.append("\r\n");
        return writeCSB(fw, FileNameCsv, listAgingReport);
    }
	
	private FileWriter writeTTL(FileWriter fw, String FileNameCsv,
			List<HashMap<String, Object>> listAgingReport) throws IOException {
		for (HashMap<String, Object> record : listAgingReport) {
			// No
			writeCsv(fw, CommonUtils.toString(record.get("DATA_REQ")));
			fw.append(',');
			// Customer ID
			writeCsv(fw, CommonUtils.toString(record.get("ID_CUST")));
			fw.append(',');
			// Customer Name
			writeCsv(fw, CommonUtils.toString(record.get("CUST_NAME")));
			fw.append(',');
			// Billing Account
			writeCsv(fw, CommonUtils.toString(record.get("ID_BILL_ACCOUNT")).trim());
			fw.append(',');
			// Payment Method
			writeCsv(fw, CommonUtils.toString(record.get("PAYMENT_METHOD")));
			fw.append(',');
			// Billing Currency
			writeCsv(fw, CommonUtils.toString(record.get("BILL_CURRENCY")));
			fw.append(',');
			// Total Amount Due
			writeCsv(fw, CommonUtils.toString(record.get("TOTAL_AMT_DUE")));
			fw.append(',');
			// Actual Value
			writeCsv(fw, CommonUtils.toString(record.get("ACTUAL_AMOUNT")));
			fw.append(',');
			// Variance
			writeCsv(fw, CommonUtils.toString(record.get("VARIANCE_AMOUNT")));
			fw.append("\r\n");
		}
		return fw;
	}
	
	private FileWriter writeCNM(FileWriter fw, String FileNameCsv,
			List<HashMap<String, Object>> listAgingReport) throws IOException {
		for (HashMap<String, Object> record : listAgingReport) {
		    // No
            writeCsv(fw, CommonUtils.toString(record.get("NO")));
            fw.append(',');
            // Transaction Date
            writeCsv(fw, CommonUtils.toString(record.get("DATE_UPDATED")));
            fw.append(',');
            // Customer ID
            writeCsv(fw, CommonUtils.toString(record.get("ID_CUST")));
            fw.append(',');
			// Customer Name
			writeCsv(fw, CommonUtils.toString(record.get("CUST_NAME")));
			fw.append(',');
			
			// Customer TPYE
			//#263 [T11] Add customer type at inquiry screen and export result CT 06062017 ST
			String customerType = CommonUtils.toString(record.get("CUSTOMER_TYPE"));
			if("CORP".equals(customerType)){
            	customerType = "Corporate";
            }else{
            	customerType = "Consumer";
            }
			writeCsv(fw, customerType);
			fw.append(',');
			//#263 [T11] Add customer type at inquiry screen and export result CT 06062017 ST
			
            // Billing Account No.
            writeCsv(fw, CommonUtils.toString(record.get("BILL_ACC")));
            fw.append(',');
            // Billing Currency
            writeCsv(fw, CommonUtils.toString(record.get("BILL_CURRENCY")));
            fw.append(',');
			// Credit Reference
			writeCsv(fw, CommonUtils.toString(record.get("CREDIT_REF")));
			fw.append(',');
			// Original amount
			writeCsv(fw, CommonUtils.toString(new BigDecimal(record.get("BILL_AMOUNT").toString()).negate()));
			fw.append(',');
			// Credit Balance
			writeCsv(fw, CommonUtils.toString(new BigDecimal(record.get("AMT_DUE").toString()).negate()));
			fw.append(',');
			// Debit reference
			writeCsv(fw, CommonUtils.toString(record.get("DEBIT_REF")));
			fw.append(',');
			// Payment
			writeCsv(fw, CommonUtils.toString(record.get("AMT_PAID")));
			fw.append("\r\n");
		}
		return fw;
	}
    private FileWriter writeSALHeader(FileWriter fw, String FileNameCsv,
            List<HashMap<String, Object>> listAgingReport) throws IOException {
        List<String> headNameList = queryDAO.executeForObjectList("SELECT.G_RPT_P01.003", resourceId);
        writeCsv(fw, headNameList.get(0));
        fw.append(',');
        writeCsv(fw, headNameList.get(1));
        fw.append(',');
        writeCsv(fw, headNameList.get(2));
        fw.append(',');
        writeCsv(fw, headNameList.get(3));
        fw.append(',');
        writeCsv(fw, headNameList.get(4));
        fw.append(',');
        writeCsv(fw, headNameList.get(5));
        fw.append(',');
        writeCsv(fw, headNameList.get(6));
        //fw.append(',');
        //writeCsv(fw, reportHeader("reports.GRPTP01.PAYMENTMETHOD", value));
        fw.append(',');
        writeCsv(fw, headNameList.get(7));
        fw.append(',');
        writeCsv(fw, headNameList.get(8));
        fw.append(',');
        writeCsv(fw, headNameList.get(9));
        fw.append(',');
        writeCsv(fw, headNameList.get(10));
        fw.append(',');
        writeCsv(fw, headNameList.get(11));
        fw.append(',');
        writeCsv(fw, headNameList.get(12));
        fw.append(',');
        writeCsv(fw, headNameList.get(13));
        fw.append(',');
        // Rate Mode
        writeCsv(fw, headNameList.get(14));
        fw.append(',');
        // Service Start
        writeCsv(fw, headNameList.get(15));
        fw.append(',');
        // Service End
        writeCsv(fw, headNameList.get(16));
        fw.append("\r\n");

        return writeSAL(fw, fileNameCsv, listAgingReport);
    }

    private FileWriter writeRRRHeader(FileWriter fw, String FileNameCsv,
            List<HashMap<String, Object>> listAgingReport) throws IOException {
        List<String> headNameList = queryDAO.executeForObjectList("SELECT.G_RPT_P01.003", resourceId);
        writeCsv(fw, headNameList.get(0));
        fw.append(',');
        writeCsv(fw, headNameList.get(1));
        fw.append(',');
        writeCsv(fw, headNameList.get(2));
        fw.append(',');
        writeCsv(fw, headNameList.get(3));
        fw.append(',');
        writeCsv(fw, headNameList.get(4));
        fw.append(',');
        writeCsv(fw, headNameList.get(5));
        fw.append(',');
        writeCsv(fw, headNameList.get(6));
        fw.append(',');
        writeCsv(fw, headNameList.get(7));
        fw.append(',');
        writeCsv(fw, headNameList.get(8));
        fw.append(',');
        writeCsv(fw, headNameList.get(9));
        fw.append(',');
        writeCsv(fw, headNameList.get(10));
        fw.append("\r\n");

        return writeRRR(fw, fileNameCsv, listAgingReport);
    }

    private FileWriter writeAGRHeader(FileWriter fw, String FileNameCsv,
            List<HashMap<String, Object>> listAgingReport) throws IOException {
        List<String> headNameList = queryDAO.executeForObjectList("SELECT.G_RPT_P01.003", resourceId);
        writeCsv(fw, headNameList.get(0));
        fw.append(',');
        writeCsv(fw, headNameList.get(1));
        fw.append(',');
        writeCsv(fw, headNameList.get(2));
        fw.append(',');
        writeCsv(fw, headNameList.get(3));
        fw.append(',');
        writeCsv(fw, headNameList.get(4));
        fw.append(',');
        writeCsv(fw, headNameList.get(5));
        fw.append(',');
        writeCsv(fw, headNameList.get(6));
        fw.append(',');
        writeCsv(fw, headNameList.get(7));
        fw.append(',');
        writeCsv(fw, headNameList.get(8));
        fw.append("\r\n");

        return writeAGR(fw, fileNameCsv, listAgingReport);
    }

    private FileWriter writeACRHeader(FileWriter fw, String FileNameCsv,
            List<HashMap<String, Object>> listAgingReport) throws IOException {
        List<String> headNameList = queryDAO.executeForObjectList("SELECT.G_RPT_P01.003", resourceId);
        for(int i=0; i<headNameList.size(); i++){
            writeCsv(fw, headNameList.get(i));
            if (i != headNameList.size()-1) {
                fw.append(',');
            }
        }
        
        fw.append("\r\n");

        return writeACR(fw, fileNameCsv, listAgingReport);
    }
	
    private FileWriter writeSAL(FileWriter fw, String FileNameCsv,
            List<HashMap<String, Object>> listAgingReport) throws IOException {

        for (HashMap<String, Object> record : listAgingReport) {
            // BILLMONTHYEAR
            String dateTrans = CommonUtils.toString(record.get("DATE_REQ"));
            writeCsv(fw, dateTrans == null ? "" : dateTrans);
            fw.append(',');

            // CUSTOMER_NAME
            String custName = CommonUtils.toString(record.get("CUST_NAME"));
            writeCsv(fw, custName == null ? "" : custName);
            fw.append(',');
            
            // CUSTOMER_TYPE
            String customerType = CommonUtils.toString(record.get("CUSTOMER_TYPE"));
            String customerTypeName = CommonUtils.getCodeMapListNameByValue("LIST_CUSTOMERTYPE", customerType);
            writeCsv(fw, customerTypeName == null ? "" : customerTypeName);
            fw.append(',');

            // AFFILIATE_CODE
            String affiliateCode = CommonUtils.toString(record.get("AFFILIATE_CODE"));
            String customerGroup = getCodeMapListNameByValue2("AFFILIATE_CODE", affiliateCode);
            writeCsv(fw, customerGroup == null ? "" : customerGroup);
            fw.append(',');

            // INVOICE_NO
            String idRef = CommonUtils.toString(record.get("ID_REF"));
            writeCsv(fw, idRef == null ? "" : idRef);
            fw.append(',');

            // SUBSCRIPTION_ID
            String idCustPlan = CommonUtils.toString(record.get("ID_SUB_INFO"));
            writeCsv(fw, idCustPlan == null ? "" : idCustPlan);
            fw.append(',');

            // Category
            String category = CommonUtils.toString(record.get("SVC_GRP_NAME"));
            writeCsv(fw, category == null ? "" : category);
            fw.append(',');
            
            // SERVICE_NAME
            String svcDesc = CommonUtils.toString(record.get("SVC_DESC"));
            writeCsv(fw, svcDesc == null ? "" : svcDesc);
            fw.append(',');
            
            /*#263 [T11] Add customer type at inquiry screen and export result CT 13062017*/
            // Plan
            String plan = CommonUtils.toString(record.get("Plan"));
            writeCsv(fw, plan == null ? "" : plan);
            fw.append(',');
            /*#263 [T11] Add customer type at inquiry screen and export result CT 13062017*/
            
            // Revenue Code
            String revenueCode = CommonUtils.toString(record.get("ACC_CODE"));
            writeCsv(fw, revenueCode == null ? "" : revenueCode);
            fw.append(',');
            
            // ISSUE_TYPE
            String isSingPost = CommonUtils.toString(record.get("IS_SINGPOST"));
            String isManual = CommonUtils.toString(record.get("IS_MANUAL"));
            String issueTypeStr = "";
            //Singpost
            if("1".equals(isSingPost) && "0".equals(isManual)) {
                issueTypeStr = "Singpost";
            } else if("0".equals(isSingPost) && "0".equals(isManual)) {
                //NTTS (automated)
                issueTypeStr = "NTTS (automated)";
            } else if("0".equals(isSingPost) && "1".equals(isManual)) {
                //NTTS (manual)
                issueTypeStr = "NTTS (manual)";
            }
            writeCsv(fw, issueTypeStr);
            fw.append(',');

            // PAYMENT_METHOD
//            String payMethod = CommonUtils.toString(record.get("PAY_METHOD"));
//            writeCsv(fw, payMethod == null ? "" : payMethod);
//            fw.append(',');

            // CURRENCY
            String billCurrency = CommonUtils.toString(record.get("BILL_CURRENCY"));
            writeCsv(fw, billCurrency == null ? "" : billCurrency);
            fw.append(',');

            // INVOICEAMTBEFOREGST
            String invoiceAmt = CommonUtils.toString(record.get("INVOICE_AMT"));
            writeCsv(fw, invoiceAmt == null ? "" : invoiceAmt);
            fw.append(',');

            // GSTAMOUNT
            String gstAmount = CommonUtils.toString(record.get("GST_AMOUNT"));
            writeCsv(fw, gstAmount == null ? "" : gstAmount);
            fw.append(',');

            // INVOICEAMTAFTERGST
            String billAmount = CommonUtils.toString(record.get("BILL_AMOUNT"));
            writeCsv(fw, billAmount == null ? "" : billAmount);
            fw.append(',');

            // RATE_MODE
            String rateMode = CommonUtils.toString(record.get("RATE_MODE"));
            String rateModeName = CommonUtils.getCodeMapListNameByValue("LIST_RATEMODE", rateMode);
            writeCsv(fw, rateModeName == null ? "" : rateModeName);
            fw.append(',');

            // SERVICE_START
            String serviceStart = CommonUtils.toString(record.get("SERVICE_START"));
            writeCsv(fw, serviceStart == null ? "" : serviceStart);
            fw.append(',');

            // SERVICE_END
            String serviceEnd = CommonUtils.toString(record.get("SERVICE_END"));
            writeCsv(fw, serviceEnd == null ? "" : serviceEnd);
            
            fw.append("\r\n");
        }
        return fw;
    }

    private FileWriter writeRRR(FileWriter fw, String FileNameCsv,
            List<HashMap<String, Object>> listAgingReport) throws IOException {

        for (HashMap<String, Object> record : listAgingReport) {
            // BILLMONTHYEAR
            String dateTrans = CommonUtils.toString(record.get("DATE_TRANS"));
            writeCsv(fw, dateTrans == null ? "" : dateTrans);
            fw.append(',');

            // CUSTOMER_NAME
            String custName = CommonUtils.toString(record.get("CUST_NAME"));
            writeCsv(fw, custName == null ? "" : custName);
            fw.append(',');

            // AFFILIATE_CODE
            String affiliateCode = CommonUtils.toString(record.get("AFFILIATE_CODE"));
            String customerGroup = getCodeMapListNameByValue2("AFFILIATE_CODE", affiliateCode);
            writeCsv(fw, customerGroup == null ? "" : customerGroup);
            fw.append(',');
            
            // INVOICE_NO
            String idRef = CommonUtils.toString(record.get("ID_REF"));
            writeCsv(fw, idRef == null ? "" : idRef);
            fw.append(',');

            // CHEQUENO
            //String pmtMethodCode = CommonUtils.toString(record.get("PMT_METHOD"));
            String reference = "";
            //if ("CQ".equals(pmtMethodCode)) {
                reference = CommonUtils.toString(record.get("REFERENCE2"));
            //} else {
            //    reference = "-";
            //}
            writeCsv(fw, reference == null ? "" : reference);
            fw.append(',');
            
            // RECEIPTNO
            String receptNo = CommonUtils.toString(record.get("RECEIPT_NO"));
            writeCsv(fw, receptNo == null ? "" : receptNo);
            fw.append(',');

            // BANK_IN_NAME
            String comAcctName = CommonUtils.toString(record.get("COM_ACCT_NAME"));
            writeCsv(fw, comAcctName == null ? "" : comAcctName);
            fw.append(',');
            
            // PAYMENT_METHOD
            String pmtMethod = getCodeMapListNameByValue("LIST_PAYMENT_METHOD", CommonUtils.toString(record.get("PMT_METHOD")));
            writeCsv(fw, pmtMethod == null ? "" : pmtMethod);
            fw.append(',');

            // CURRENCY
            String curCode = CommonUtils.toString(record.get("CUR_CODE"));
            writeCsv(fw, curCode == null ? "" : curCode);
            fw.append(',');

            String pmtStatus = CommonUtils.toString(record.get("PMT_STATUS"));
            // INVOICE_AMOUNT
            String billAmount = CommonUtils.toString(record.get("BILL_AMOUNT"));
            String amtReceived = CommonUtils.toString(record.get("REFUND_AMT_RECEIVED"));
            billAmount = billAmount == null ? "0" : billAmount;
            amtReceived = amtReceived == null ? "0" : amtReceived;
            writeCsv(fw, "N".equalsIgnoreCase(pmtStatus) ? billAmount : new BigDecimal(amtReceived).negate().toString());
            fw.append(',');

            // PAID_AMOUNT
            String amtPaid = CommonUtils.toString(record.get("AMT_PAID"));
            amtPaid = amtPaid == null ? "0" : amtPaid;
            writeCsv(fw, "N".equalsIgnoreCase(pmtStatus) ? amtPaid : new BigDecimal(amtPaid).negate().toString());
            fw.append("\r\n");
        }
        
        return fw;
    }

    private FileWriter writeAGR(FileWriter fw, String FileNameCsv,
            List<HashMap<String, Object>> listAgingReport) throws IOException {

        for (HashMap<String, Object> record : listAgingReport) {
            // BILLMONTHYEAR
            String dateReq = CommonUtils.toString(record.get("DATE_REQ"));
            writeCsv(fw, dateReq == null ? "" : dateReq);
            fw.append(',');

            // CUSTOMER_NAME
            String custName = CommonUtils.toString(record.get("CUST_NAME"));
            writeCsv(fw, custName == null ? "" : custName);
            fw.append(',');

            // AFFILIATE_CODE
            String affiliateCode = CommonUtils.toString(record.get("AFFILIATE_CODE"));
            String customerGroup = getCodeMapListNameByValue2("AFFILIATE_CODE", affiliateCode);
            writeCsv(fw, customerGroup == null ? "" : customerGroup);
            fw.append(',');

            // Billing Account
            String billingAccount = CommonUtils.toString(record.get("BILL_ACC"));
            writeCsv(fw, billingAccount == null ? "" : billingAccount);
            fw.append(',');
            
            // INVOICE_NO
            String idRef = CommonUtils.toString(record.get("ID_REF"));
            writeCsv(fw, idRef == null ? "" : idRef);
            fw.append(',');
            
            // PAYMENT_METHOD
            String payMethod = getCodeMapListNameByValue("LIST_PAYMENT_METHOD", CommonUtils.toString(record.get("PAY_METHOD")));
            writeCsv(fw, payMethod == null ? "" : payMethod);
            fw.append(',');

            // CURRENCY
            String billCurrency = CommonUtils.toString(record.get("BILL_CURRENCY"));
            writeCsv(fw, billCurrency == null ? "" : billCurrency);
            fw.append(',');

            // INVOICE_AMOUNT
            String billAmount = CommonUtils.toString(record.get("BILL_AMOUNT"));
            writeCsv(fw, billAmount == null ? "" : billAmount);
            fw.append(',');

            // OUTSTANDING_AMOUNT
            String outstandingAmt = CommonUtils.toString(record.get("OUTSTANDING_AMOUNT"));
            writeCsv(fw, outstandingAmt == null ? "" : outstandingAmt);
            fw.append("\r\n");
        }
        
        return fw;
    }
	
    private FileWriter writeACR(FileWriter fw, String FileNameCsv,
            List<HashMap<String, Object>> listAgingReport) throws IOException {

        for (HashMap<String, Object> record : listAgingReport) {
            // USAGEMONTHYEAR
            String dateReq = CommonUtils.toString(record.get("DATE_REQ"));
            writeCsv(fw, dateReq);
            fw.append(',');

            // CUSTOMER_NAME
            String custName = CommonUtils.toString(record.get("CUST_NAME"));
            writeCsv(fw, custName);
            fw.append(',');

            // Sub ID
            String subID = CommonUtils.toString(record.get("ID_SUB_INFO"));
            writeCsv(fw, subID);
            fw.append(',');
            
            // SERVICE
            String svcDesc = CommonUtils.toString(record.get("SVC_DESC"));
            writeCsv(fw, svcDesc);
            fw.append(',');
            
            // Service Status
            String svcStatus = CommonUtils.toString(record.get("SERVICES_STATUS"));
            writeCsv(fw, svcStatus);
            fw.append(',');
            
            // Service Start Date
            String svcStartDate = CommonUtils.toString(record.get("SVC_START"));
            writeCsv(fw, svcStartDate);
            fw.append(',');

            // CURRENCY
            String billCurrency = CommonUtils.toString(record.get("BILL_CURRENCY"));
            writeCsv(fw, billCurrency);
            fw.append(',');

            // UNBILL_AMOUNT(EXCLUDE_GST)
            String itemAmt = CommonUtils.toString(record.get("TOTAL_AMOUNT"));
            writeCsv(fw, itemAmt);
            fw.append(',');
            
            // Imported Amount
            String importedAmt = CommonUtils.toString(record.get("INVOICE_TOTAL"));
            writeCsv(fw, importedAmt.equals("") ? "0" : importedAmt);
            fw.append(',');
            
            // Item Description
            String itemdesc = CommonUtils.toString(record.get("ITEM_DESC"));
            writeCsv(fw, itemdesc);
            fw.append("\r\n");   
        }
        
        return fw;
    }
    
    private FileWriter writeBIL(FileWriter fw, String FileNameCsv,
            List<HashMap<String, Object>> listAgingReport) throws IOException {
        long numNo = 0;
        NumberFormat formatter = new DecimalFormat("###,##0.00");
        formatter.setRoundingMode(RoundingMode.HALF_UP);
        String issueTypeSingpost = "";
        String issueTypeAuto = "";
        String issueTypeManual = "";
        //#227 - wcbeh@20170206 Billing Document Issue Type Value
        List<Map<String, Object>> singPost = this.queryDAO.executeForMapList("B_BIL.getSinPostValue", null);
        for (int i = 0; i < singPost.size(); i++) {
			if ("BIL01".equals(singPost.get(i).get("RESOURCE_ID").toString())) {
				issueTypeSingpost = singPost.get(i).get("VALUE").toString();
			}else if ("BIL02".equals(singPost.get(i).get("RESOURCE_ID").toString())) {
				issueTypeAuto = singPost.get(i).get("VALUE").toString();
			}else if ("BIL03".equals(singPost.get(i).get("RESOURCE_ID").toString())) {
				issueTypeManual = singPost.get(i).get("VALUE").toString();
			}
		}
        //#227 - wcbeh@20170206 Billing Document Issue Type Value
        for (HashMap<String, Object> record : listAgingReport) {
            numNo = numNo + 1;
            // No
            writeCsv(fw, CommonUtils.toString(numNo));
            fw.append(',');

            // Date
            String date = CommonUtils.toString(record.get("DATE_REQ_FOM"));
            writeCsv(fw, date);
            fw.append(',');
            
            // Billing Document No.
            String idRef = CommonUtils.toString(record.get("ID_REF")).trim();
            writeCsv(fw, idRef);
            fw.append(',');
            
            // Customer Name
            String customerName = CommonUtils.toString(record.get("CUST_NAME"));
            String salutation = CommonUtils.toString(record.get("SALUTATION"));
            if (!CommonUtils.isEmpty(salutation)) {
                customerName = getCodeMapListNameByValue("LIST_SALUTATION", salutation) + " " + customerName;
            }
            writeCsv(fw, customerName);
            fw.append(',');
            
            // Customer Type
            //#263 [T11] Add customer type at inquiry screen and export result CT 06062017 ST
            String customerType = CommonUtils.toString(record.get("CUSTOMER_TYPE"));
            if("CORP".equals(customerType)){
            	customerType = "Corporate";
            }else{
            	customerType = "Consumer";
            }
            writeCsv(fw, customerType);
            fw.append(',');
            //#263 [T11] Add customer type at inquiry screen and export result CT 06062017 ST
            
            
            // TransType
            String billType = CommonUtils.toString(record.get("BILL_TYPE"));
            writeCsv(fw, billType);
            fw.append(',');
            
            // BillingAccountNo
            String billAcc = CommonUtils.toString(record.get("BILL_ACC")).trim();
            writeCsv(fw, billAcc);
            fw.append(',');
            
            // Bill Currency
            String billCurrency = CommonUtils.toString(record.get("BILL_CURRENCY"));
            writeCsv(fw, billCurrency);
            fw.append(',');
            
            // Original Amount
            //String billAmount = formatter.format(record.get("BILL_AMOUNT"));
            BigDecimal billAmount = new BigDecimal(record.get("BILL_AMOUNT").toString());
            if ("CN".equals(billType)) {
                writeCsv(fw, formatter.format(billAmount));
            } else {
                writeCsv(fw, formatter.format(billAmount));
            }
            fw.append(',');
            
            // Total Amount Due
            String totalAmtDue = formatter.format(record.get("TOTAL_AMT_DUE"));
            writeCsv(fw, totalAmtDue);
            fw.append(',');
            
            // Outstanding Amount
            //String outstandingAmount = formatter.format(record.get("OUTSTANDING_AMOUNT"));
            BigDecimal outstandingAmount = new BigDecimal(record.get("OUTSTANDING_AMOUNT").toString());
            if ("CN".equals(billType)) {
                writeCsv(fw, formatter.format(outstandingAmount));
            } else {
                writeCsv(fw, formatter.format(outstandingAmount));
            }
            fw.append(',');
            
            // Settlement Status
            String settlementStatusName = "";
            String isSettled = CommonUtils.toString(record.get("IS_SETTLED"));
            if ("1".equals(isSettled)) {
                settlementStatusName = "FULLY SETTLED";
            } else {
                double paidAmount = CommonUtils.toDouble(record.get("PAID_AMOUNT"));
                if(paidAmount==0.0) {
                    settlementStatusName = "OUTSTANDING";
                } else {
                    settlementStatusName = "PARTIAL SETTLED";
                }
            }
            writeCsv(fw, settlementStatusName);
            fw.append(',');

            //Issue Type
            //#227 - wcbeh@20170206 Billing Document Issue Type Value
            String issueTypeName = "";
            String isSingpost = CommonUtils.toString(record.get("IS_SINGPOST"));
            String isManual = CommonUtils.toString(record.get("IS_MANUAL"));            
            if ("1".equals(isSingpost) && "0".equals(isManual)) {
                //issueTypeName = MessageUtil.get("reports.issueTypeSingpost");
            	issueTypeName = issueTypeSingpost;
            } else if ("0".equals(isSingpost) && "0".equals(isManual)) {
                //issueTypeName = MessageUtil.get("reports.issueTypeAuto");
            	issueTypeName = issueTypeAuto;
            } else if ("0".equals(isSingpost) && "1".equals(isManual)) {
                //issueTypeName = MessageUtil.get("reports.issueTypeManual");
            	issueTypeName = issueTypeManual;
            }
            //#227 - wcbeh@20170206 Billing Document Issue Type Value
            writeCsv(fw, issueTypeName);
            fw.append(',');
            
            // Document Status
            String documentStatusName = "";
            String isClose = CommonUtils.toString(record.get("IS_CLOSED"));
            if ("0".equals(isClose)) {
                documentStatusName = "OPEN";
            } else {
                documentStatusName = "CLOSED";
            }
            writeCsv(fw, documentStatusName);
            fw.append(',');
            
            //Status
            String statusName = "";
            String isDeleted = CommonUtils.toString(record.get("IS_DELETED"));
            if ("1".equals(isDeleted)) {
                statusName = "Cancelled";
            } else {
                statusName = "Normal";
            }
            writeCsv(fw, statusName);
            fw.append(',');
            
            // Category
            //#263 [T11] Add customer type at inquiry screen and export result CT 06062017 ST
            String category = CommonUtils.toString(record.get("CATEGORY"));
            writeCsv(fw, category);
            //#263 [T11] Add customer type at inquiry screen and export result CT 06062017 ST
            
            fw.append("\r\n");
        }
        
        return fw;
    }
    
    private FileWriter writeCSB(FileWriter fw, String FileNameCsv,
            List<HashMap<String, Object>> listAgingReport) throws IOException {
        long numNo = 0;
        NumberFormat formatter = new DecimalFormat("###,##0.00");
        formatter.setRoundingMode(RoundingMode.HALF_UP);
        for (HashMap<String, Object> record : listAgingReport) {
            numNo = numNo + 1;
            // No
            writeCsv(fw, CommonUtils.toString(numNo));
            fw.append(',');
            
            // Date
            String dateTrans = CommonUtils.toString(record.get("DATE_TRANS"));
            writeCsv(fw, dateTrans);
            fw.append(',');
            
            // Payer
            String custName = CommonUtils.toString(record.get("CUST_NAME"));
            writeCsv(fw, custName);
            fw.append(',');
            
            ////#263 [T11] Add customer type at inquiry screen and export result CT 06062017 
            // Payer Type
            String customerType = CommonUtils.toString(record.get("CUSTOMER_TYPE"));
            if("CORP".equals(customerType)){
            	customerType = "Corporate";
            }else{
            	customerType = "Consumer";
            }
            writeCsv(fw, customerType);
            fw.append(',');
            ////#263 [T11] Add customer type at inquiry screen and export result CT 06062017 
            
            // Billing Account No. 
            String idBillAccount = CommonUtils.toString(record.get("ID_BILL_ACCOUNT")).trim();
            writeCsv(fw, idBillAccount);
            fw.append(',');
            
            // Receipt No.
            String receiptNo = CommonUtils.toString(record.get("RECEIPT_NO")).trim();
            writeCsv(fw, receiptNo);
            fw.append(',');
            
            // <Payment Ref 1_h>
            String reference1 = CommonUtils.toString(record.get("REFERENCE1")).trim();
            writeCsv(fw, reference1);
            fw.append(',');
            
            // Payment Currency
            String curCode = CommonUtils.toString(record.get("CUR_CODE")).trim();
            writeCsv(fw, curCode);
            fw.append(',');
            
            // Amount Received
            String amtReceived = formatter.format(record.get("AMT_RECEIVED"));
            writeCsv(fw, amtReceived);
            fw.append(',');
            
            // Balance
            String balanceAmt = formatter.format(record.get("BALANCE_AMT"));
            writeCsv(fw, balanceAmt);
            fw.append(',');
            
            // Payment Method
            String pmtMethodName = getCodeMapListNameByValue("LIST_PAYMENT_METHOD", CommonUtils.toString(record.get("PMT_METHOD")).trim());
            writeCsv(fw, pmtMethodName);
            fw.append(',');
            
            // Bank Account of Payee
            String bankAcc = CommonUtils.toString(record.get("BANK_ACC")).trim();
            writeCsv(fw, bankAcc);
            fw.append(',');
            
            // Payment Status
            String pmtStatusName = getCodeMapListNameByValue("LIST_PAYMENT_STATUS", CommonUtils.toString(record.get("PMT_STATUS")).trim());
            writeCsv(fw, pmtStatusName);
            fw.append("\r\n");
        }
        
        return fw;
    }

    private static void writeCsv(FileWriter fw, String content)
            throws IOException {
        if (content.contains(",")) {
            fw.append("\"" + content + "\"");
        } else {
            fw.append(content);
        }
    }

//    private String reportHeader(String key, Object args) {
//        return MessageUtil.get(key, args);
//    }

    private void addError(String msgID, Object... args) {
        errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage(msgID, args));
    }

    private String getSysdateStr() {
        Calendar now = Calendar.getInstance();
        String yy = CommonUtils.formatDate(now.getTime(), "yy");
        String MM = CommonUtils.formatDate(now.getTime(), "MM");
        String dd = CommonUtils.formatDate(now.getTime(), "dd");
        String HH = CommonUtils.formatDate(now.getTime(), "HH");
        String mm = CommonUtils.formatDate(now.getTime(), "mm");
        String ss = CommonUtils.formatDate(now.getTime(), "ss");
        sysdateStr = yy + "" + MM + "" + dd + "" + HH + "" + mm + ss;

        return sysdateStr;
    }
    
    private String getSysdateStr1() {
        Calendar now = Calendar.getInstance();
        String yyyy = CommonUtils.formatDate(now.getTime(), "yyyy");
        String MM = CommonUtils.formatDate(now.getTime(), "MM");
        String dd = CommonUtils.formatDate(now.getTime(), "dd");
        String HH = CommonUtils.formatDate(now.getTime(), "HH");
        String mm = CommonUtils.formatDate(now.getTime(), "mm");
        String ss = CommonUtils.formatDate(now.getTime(), "ss");
        sysdateStr = yyyy + "" + MM + "" + dd + "" + HH + "" + mm + ss;

        return sysdateStr;
    }
    
    /**
     * get code name by code value from code map list
     * @param codeMapListName
     * @param codeValue
     * @return
     */
    @SuppressWarnings("unchecked")
    public static String getCodeMapListNameByValue(String codeMapListName, String codeValue) {
        String codeName = "";
        ApplicationContext context = ApplicationContextProvider.
            getApplicationContext();
        MappedCodeListLoader codeMapList = (MappedCodeListLoader) 
            context.getBean(codeMapListName);
        Map<String, Object> codeMap = codeMapList.getCodeListMap();
        if (codeMap.containsKey(codeValue)){
            codeName = codeMap.get(codeValue).toString();
        }
        return codeName;
    }
    
    /**
     * get code name by code value from code map list
     * @param codeMapListName
     * @param codeValue
     * @return
     */
    public static String getCodeMapListNameByValue2(String codeMapListName, String codeValue) {
        String codeName = "";
        ApplicationContext context = ApplicationContextProvider.
            getApplicationContext();
        DBCodeListLoader codeMapList = (DBCodeListLoader) 
            context.getBean(codeMapListName);
        Map<String, Object> codeMap = new HashMap<String, Object>();
        for (int i = 0; i < codeMapList.getCodeBeans().length; i++) {
        	codeMap.put(codeMapList.getCodeBeans()[i].getId(), codeMapList.getCodeBeans()[i].getName());
		}
        if (codeMap.containsKey(codeValue)){
            codeName = codeMap.get(codeValue).toString();
        }
        return codeName;
    }
}	
