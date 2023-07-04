package nttdm.bsys.r_soa.blogic;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.RoundingMode;
import java.text.DateFormat;
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
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import nttdm.bsys.batch.CustomDataSource_e_expf02;
import nttdm.bsys.batch.detail_info_e_exp_f02;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.GlobalProcessResult;
import nttdm.bsys.common.util.ZipException;
import nttdm.bsys.common.util.ZipUtil;

public class SOA_EXP_F01 {

	private UpdateDAO updateDAO;
	private QueryDAO queryDAO;

	private String systemBase;
	
	private String sessiondictionary = "";
	
	public void setSessiondictionary(String sessiondictionary) {
		this.sessiondictionary = sessiondictionary;
	}

	/**
	 * 
	 * @param idStmts
	 *            the data to print
	 * @param printType
	 *            1 print 2 print all
	 * @return
	 */
	public GlobalProcessResult execute(String[] idStmts, String printType) {

		if ("2".equals(printType)) {
			idStmts = new String[]{};
		}
		
		ArrayList<File> reports=new ArrayList<File>();
		// Get SYSTEMBASE
        HashMap<String, String> m_gset_d = new HashMap<String, String>();
        m_gset_d = new HashMap<String, String>();
        m_gset_d.put("idSet", "SYSTEMBASE");
        m_gset_d.put("set_seq", "1");
        this.systemBase = queryDAO.executeForObject("SELECT.M_GSET_D", m_gset_d, String.class);
        
		// for header pdf file info.
		// get stmt_id list
		Map<String, Object> e_exp_pdf_info = new HashMap<String, Object>();

		List<HashMap<String, Object>> header_stmt_no_list = new ArrayList<HashMap<String, Object>>();
	    m_gset_d = new HashMap<String, String>();
		m_gset_d.put("idSet", "CB_AUTO_OFFSET");
		String mGsetDValue = queryDAO.executeForObject("SELECT.M_GSET_D",
				m_gset_d, String.class);
		HashMap<String, Object> id_stmts_param = new HashMap<String, Object>();
		id_stmts_param.put("idStmts", idStmts);
		if ("BAC".equals(mGsetDValue)) {
			header_stmt_no_list = queryDAO.executeForObjectList(
					"SELECT.BSYS.E_EXP_F02.SUBCB.HEADER.SQL002", id_stmts_param);
		} else {
			header_stmt_no_list = queryDAO.executeForObjectList(
					"SELECT.BSYS.E_EXP_F02.SUBCB.HEADER.SQL001", id_stmts_param);
		}

		// get info of company name - name and address line.
		HashMap<String, Object> companyHeaderInfo_Add = (HashMap<String, Object>) queryDAO
				.executeForMap("SELECT.BSYS.E_EXP_F02.SUBCB.COMPANYHEADERINFO_ADD",null);
		
		if (companyHeaderInfo_Add != null) {
			e_exp_pdf_info
					.put("com_name", CommonUtils.toString(companyHeaderInfo_Add
							.get("COM_NAME")));
			e_exp_pdf_info
			.put("com_website", CommonUtils.toString(companyHeaderInfo_Add
					.get("COM_WEBSITE")));
			CommonUtils.fixAddress4n(companyHeaderInfo_Add, "COM_ADR_LINE4");
			
			ArrayList<String> addr = new ArrayList<String>();
			if (!CommonUtils.toString(companyHeaderInfo_Add.get("COM_ADR_LINE1")).trim().equals("")) {
				addr.add(CommonUtils.toString(companyHeaderInfo_Add.get("COM_ADR_LINE1")).trim());
			}
			if (!CommonUtils.toString(companyHeaderInfo_Add.get("COM_ADR_LINE2")).trim().equals("")) {
				addr.add(CommonUtils.toString(companyHeaderInfo_Add.get("COM_ADR_LINE2")).trim());
			}
			if (!CommonUtils.toString(companyHeaderInfo_Add.get("COM_ADR_LINE3")).trim().equals("")) {
				addr.add(CommonUtils.toString(companyHeaderInfo_Add.get("COM_ADR_LINE3")).trim());
			}
			if (!CommonUtils.toString(companyHeaderInfo_Add.get("COM_ADR_LINE4")).trim().equals("")) {
				addr.add(CommonUtils.toString(companyHeaderInfo_Add.get("COM_ADR_LINE4")).trim());
			}
			for (int j = 0; j < addr.size(); j++) {
				e_exp_pdf_info.put("add_line_com" + (j + 1), addr.get(j));
			}
			e_exp_pdf_info.put("registration_no", CommonUtils
					.toString(companyHeaderInfo_Add.get("COM_REGNO")));
			e_exp_pdf_info.put("gstRegistration_no", CommonUtils
                    .toString(companyHeaderInfo_Add.get("COM_GST_REG_NO")));
		} else {
			e_exp_pdf_info.put("com_name", "");
			e_exp_pdf_info.put("add_line_com1", "");
			e_exp_pdf_info.put("add_line_com2", "");
			e_exp_pdf_info.put("add_line_com3", "");
			e_exp_pdf_info.put("add_line_com4", "");
			e_exp_pdf_info.put("registration_no", "");
			e_exp_pdf_info.put("gstRegistration_no", "");
			e_exp_pdf_info.put("com_website", "");
		}

		// get info of company tell
		HashMap<String, Object> companyHeaderTell = (HashMap<String, Object>) queryDAO
				.executeForMap("SELECT.BSYS.E_EXP_F02.SUBCB.COMPANYHEADERINFO_TEL",null);
		if (companyHeaderTell != null) {
			e_exp_pdf_info.put("tel_com",
					CommonUtils.toString(companyHeaderTell.get("COM_TEL_NO")));
			e_exp_pdf_info.put("fax_com",
					CommonUtils.toString(companyHeaderTell.get("COM_FAX_NO")));
		} else {
			e_exp_pdf_info.put("tel_com", "");
			e_exp_pdf_info.put("fax_com", "");
		}
        
//		String companyAddr1 = CommonUtils.toString(e_exp_pdf_info.get("add_line_com1"));
//		String companyAddr2 = CommonUtils.toString(e_exp_pdf_info.get("add_line_com2"));
//		String companyAddr3 = CommonUtils.toString(e_exp_pdf_info.get("add_line_com3"));
//		String companyAddr4 = CommonUtils.toString(e_exp_pdf_info.get("add_line_com4"));
//		String companyAddr5 = "Tel : " + CommonUtils.toString(e_exp_pdf_info.get("tel_com")) +
//		                       " Fax : " + CommonUtils.toString(e_exp_pdf_info.get("fax_com"));
//		String companyAddr6 = "Reg. No : " + CommonUtils.toString(e_exp_pdf_info.get("registration_no"));
//		String companyAddr7 = "GST Reg. No. : " + CommonUtils.toString(e_exp_pdf_info.get("gstRegistration_no"));
//		String companyAddr8 = "Website : " + CommonUtils.toString(e_exp_pdf_info.get("com_website"));
//		
//		List<String> companyAddressList = addressDisplayChange(companyAddr1, companyAddr2, companyAddr3, companyAddr4, companyAddr5, companyAddr6, companyAddr7, companyAddr8);
//		companyAddr1 = companyAddressList.get(0);
//		companyAddr2 = companyAddressList.get(1);
//		companyAddr3 = companyAddressList.get(2);
//		companyAddr4 = companyAddressList.get(3);
//		companyAddr5 = companyAddressList.get(4);
//		companyAddr6 = companyAddressList.get(5);
//		companyAddr7 = companyAddressList.get(6);
//		companyAddr8 = companyAddressList.get(7);
//        
//		StringBuffer cominfo = new StringBuffer("");
//		if(!CommonUtils.isEmpty(companyAddr1)){
//			cominfo.append(companyAddr1);
//		}
//		if(!CommonUtils.isEmpty(companyAddr2)){
//		    cominfo.append("\r\n");
//            cominfo.append(companyAddr2);
//        }
//		if(!CommonUtils.isEmpty(companyAddr3)){
//            cominfo.append("\r\n");
//            cominfo.append(companyAddr3);
//        }
//		if(!CommonUtils.isEmpty(companyAddr4)){
//            cominfo.append("\r\n");
//            cominfo.append(companyAddr4);
//        }
//		if(!CommonUtils.isEmpty(companyAddr5)){
//            cominfo.append("\r\n");
//            cominfo.append(companyAddr5);
//        }
//		if(!CommonUtils.isEmpty(companyAddr6)){
//            cominfo.append("\r\n");
//            cominfo.append(companyAddr6);
//        }
//		if(!CommonUtils.isEmpty(companyAddr7)){
//            cominfo.append("\r\n");
//            cominfo.append(companyAddr7);
//        }
//		if(!CommonUtils.isEmpty(companyAddr8)){
//            cominfo.append("\r\n");
//            cominfo.append(companyAddr8);
//        }
		StringBuffer cominfo = new StringBuffer("");
        if(!CommonUtils.isEmpty(e_exp_pdf_info.get("add_line_com1"))){
            cominfo.append(CommonUtils.toString(e_exp_pdf_info.get("add_line_com1")));
        }else{
            cominfo.append("    ");
        }
		if(!CommonUtils.isEmpty(e_exp_pdf_info.get("add_line_com2"))){
			cominfo.append("\r\n");
			cominfo.append(CommonUtils.toString(e_exp_pdf_info.get("add_line_com2")));
		}else{
			cominfo.append("\r\n");
			cominfo.append("    ");
		}
		if(!CommonUtils.isEmpty(e_exp_pdf_info.get("add_line_com3"))){
			cominfo.append("\r\n");
			cominfo.append(CommonUtils.toString(e_exp_pdf_info.get("add_line_com3")));
		}else{
			cominfo.append("\r\n");
			cominfo.append("    ");
		}
		if(!CommonUtils.isEmpty(e_exp_pdf_info.get("add_line_com4"))){
			cominfo.append("\r\n");
			cominfo.append(CommonUtils.toString(e_exp_pdf_info.get("add_line_com4")));
		}else{
			cominfo.append("\r\n");
			cominfo.append("    ");
		}
		cominfo.append("\r\n");
		cominfo.append("Tel : " + CommonUtils.toString(e_exp_pdf_info.get("tel_com")));
		cominfo.append("   Fax : " + CommonUtils.toString(e_exp_pdf_info.get("fax_com")));
		cominfo.append("\r\n");
		//cominfo.append("Reg. No          : " + CommonUtils.toString(e_exp_pdf_info.get("registration_no")));
		//cominfo.append("\r\n");
		cominfo.append("GST Reg. No. : " + CommonUtils.toString(e_exp_pdf_info.get("gstRegistration_no")));
        cominfo.append("\r\n");
		cominfo.append("Website          : " + CommonUtils.toString(e_exp_pdf_info.get("com_website")));
		e_exp_pdf_info.put("cominfo", cominfo.toString());

//        comparam =  new HashMap<String, String>();
//        comparam.put("title", "Registration No.");
//        comparam.put("colon", ":");
//        comparam.put("cominfo", CommonUtils.toString(e_exp_pdf_info.get("registration_no")));
//        cominfolist.add(comparam);
        
		if (header_stmt_no_list.size() > 0) {
			List<String> yyyy_mm_list = new ArrayList<String>();
			for (int k = 0; k < header_stmt_no_list.size(); k++) {

				try {
					DateFormat dfm = new SimpleDateFormat("yyyy-MM-dd");
					Date getdate = dfm.parse(CommonUtils
							.toString(header_stmt_no_list.get(k).get(
									"STMT_DATE")));
					Calendar calen2 = Calendar.getInstance();
					calen2.setTime(getdate);
					int year_num = calen2.get(Calendar.YEAR);
					int month_num = calen2.get(Calendar.MONTH) + 1;
					String yyyy_mm_str = "";
					if (month_num < 10) {
						yyyy_mm_str = year_num + "_" + "0" + month_num + "";
					} else {
						yyyy_mm_str = year_num + "_" + month_num + "";
					}

					boolean addnew = true;
					for (int l = 0; l < yyyy_mm_list.size(); l++) {
						if (yyyy_mm_str.equals(yyyy_mm_list.get(l))) {
							addnew = false;
						}
					}

					if (addnew) {
						yyyy_mm_list.add(yyyy_mm_str);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			for (int m = 0; m < yyyy_mm_list.size(); m++) {
				JasperPrint jasperPrint1 = new JasperPrint();

				for (int i = 0; i < header_stmt_no_list.size(); i++) {
					this.resetParameter(e_exp_pdf_info);
					// get type of stmt_date
					String yyyy_mm_str = "";
					try {
						DateFormat dfm = new SimpleDateFormat("yyyy-MM-dd");
						Date getdate = dfm.parse(CommonUtils
								.toString(header_stmt_no_list.get(i).get(
										"STMT_DATE")));
						Calendar calen2 = Calendar.getInstance();
						calen2.setTime(getdate);
						int year_num = calen2.get(Calendar.YEAR);
						int month_num = calen2.get(Calendar.MONTH) + 1;

						if (month_num < 10) {
							yyyy_mm_str = year_num + "_" + "0" + month_num + "";
						} else {
							yyyy_mm_str = year_num + "_" + month_num + "";
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

					if (header_stmt_no_list.get(i).get("ID_CUST") != null
							&& yyyy_mm_str.equals(yyyy_mm_list.get(m))) {
						
						if (header_stmt_no_list.get(i).get("ID_STMT") != null) {
							e_exp_pdf_info.put("stmt_num", CommonUtils.toString(header_stmt_no_list.get(i).get("ID_STMT")));
						}
						if (header_stmt_no_list.get(i).get("STMT_DATE") != null) {
							e_exp_pdf_info.put("stmt_date", CommonUtils.toString(header_stmt_no_list.get(i).get("STMT_DATE_PRINT")));
						}
						String billAcc = CommonUtils.toString(header_stmt_no_list.get(i).get("CUST_ACC_NO"));
						if (!CommonUtils.isEmpty(billAcc)) {
							e_exp_pdf_info.put("acc_num", CommonUtils.toString(header_stmt_no_list.get(i).get("CUST_ACC_NO")));
						} else {
							e_exp_pdf_info.put("acc_num", "");
						}

					    // get the customer info  from T_AR_STMT_H
					    String custname = CommonUtils.getCodeMapListNameByValue("LIST_SALUTATION",CommonUtils.toString(header_stmt_no_list.get(i).get("SALUTATION")))
					    +" "+ CommonUtils.toString(header_stmt_no_list.get(i).get("CUST_NAME"));
						e_exp_pdf_info.put("cus_name", custname.trim());
						
						String country = CommonUtils.toString(header_stmt_no_list.get(i).get("COUNTRY"));
						String country_Code = country+ " "+ CommonUtils.toString(header_stmt_no_list.get(i).get("ZIP_CODE"));
						header_stmt_no_list.get(i).put("ADDRESS4", country_Code.trim());
//						if (header_stmt_no_list.get(i).get("ADDRESS4") != null) {
//							// change countyCd to country name
//							CommonUtils.fixAddress4n(header_stmt_no_list.get(i),"ADDRESS4");
//						}

						ArrayList<String> cusaddr = new ArrayList<String>();
						if (!CommonUtils.toString(header_stmt_no_list.get(i).get("ADDRESS1")).trim().equals("")) {
							cusaddr.add(CommonUtils.toString(header_stmt_no_list.get(i).get("ADDRESS1")).trim());
						}
						if (!CommonUtils.toString(header_stmt_no_list.get(i).get("ADDRESS2")).trim().equals("")) {
							cusaddr.add(CommonUtils.toString(header_stmt_no_list.get(i).get("ADDRESS2")).trim());
						}
						if (!CommonUtils.toString(header_stmt_no_list.get(i).get("ADDRESS3")).trim().equals("")) {
							cusaddr.add(CommonUtils.toString(header_stmt_no_list.get(i).get("ADDRESS3")).trim());
						}
						if (!CommonUtils.toString(header_stmt_no_list.get(i).get("ADDRESS4")).trim().equals("")) {
							cusaddr.add(CommonUtils.toString(header_stmt_no_list.get(i).get("ADDRESS4")).trim());
						}
						for (int j = 0; j < cusaddr.size(); j++) {
							e_exp_pdf_info.put("add_line_cus" + (j + 1),
									cusaddr.get(j));
						}
						
				        StringBuffer custinfo = new StringBuffer("");
				        custinfo.append(custname.trim());
				        if(!CommonUtils.isEmpty(e_exp_pdf_info.get("add_line_cus1"))){
				        	custinfo.append("\r\n");
				        	custinfo.append(CommonUtils.toString(e_exp_pdf_info.get("add_line_cus1")));
				        }
				        if(!CommonUtils.isEmpty(e_exp_pdf_info.get("add_line_cus2"))){
				        	custinfo.append("\r\n");
				        	custinfo.append(CommonUtils.toString(e_exp_pdf_info.get("add_line_cus2")));
				        }
				        if(!CommonUtils.isEmpty(e_exp_pdf_info.get("add_line_cus3"))){
				        	custinfo.append("\r\n");
				        	custinfo.append(CommonUtils.toString(e_exp_pdf_info.get("add_line_cus3")));
				        }
				        if(!CommonUtils.isEmpty(e_exp_pdf_info.get("add_line_cus4"))){
				        	custinfo.append("\r\n");
				        	custinfo.append(CommonUtils.toString(e_exp_pdf_info.get("add_line_cus4")));
				        }
                        e_exp_pdf_info.put("custinfo", custinfo.toString());
						e_exp_pdf_info.put("currency",CommonUtils.toString(header_stmt_no_list.get(i).get("STMT_CURRENCY"))
											+ " Statement Total");
						
						HashMap<String, String> detail_pdf_in = new HashMap<String, String>();
						detail_pdf_in.put("id_stmt", CommonUtils
								.toString(header_stmt_no_list.get(i).get("ID_STMT")));

						List<HashMap<String, Object>> list_detail_info = queryDAO
								.executeForObjectList("SELECT.BSYS.E_EXP_F02.SUBCB.DETAIL.SQL001",detail_pdf_in);

						List<detail_info_e_exp_f02> list_detail = new ArrayList<detail_info_e_exp_f02>();

						// FOR SUMARY INFO.
						int count_030 = 0;
						int count_3160 = 0;
						int count_6190 = 0;
						int count_91120 = 0;
						int count_m120 = 0;
						int count_total = 0;
						double sum_030 = 0;
						double sum_3160 = 0;
						double sum_6190 = 0;
						double sum_91120 = 0;
						double sum_m120 = 0;
						double sum_total = 0;

						if (list_detail_info.size() > 0) {

							//count_total = list_detail_info.size();
							for (int j = 0; j < list_detail_info.size(); j++) {
								detail_info_e_exp_f02 detail = new detail_info_e_exp_f02();
								if (list_detail_info.get(j).get("DOC_DATE") != null) {
									detail.setInVoiceDate(CommonUtils.toString(list_detail_info.get(j).get("DOC_DATE")));
								}

								if (list_detail_info.get(j).get("ID_REF") != null) {
									detail.setInVoiceNumber(CommonUtils.toString(list_detail_info.get(j).get("ID_REF")));
								}

								if (list_detail_info.get(j).get("ENTRY_TYPE") != null) {
									detail.setEntryType(CommonUtils.toString(list_detail_info.get(j).get("ENTRY_TYPE")));
								}

								if (list_detail_info.get(j).get("AMOUNT") != null) {
									detail.setAmount_due(Double.parseDouble(CommonUtils.toString(list_detail_info.get(j).get("AMOUNT"))));
								}
								if (list_detail_info.get(j).get("ITEM_ACTIVITY") != null) {
									detail.setItem_activity(Double.parseDouble(CommonUtils.toString(list_detail_info.get(j).get("ITEM_ACTIVITY"))));
								}
								if (list_detail_info.get(j).get("PO") != null) {
									detail.setPo(CommonUtils.toString(list_detail_info.get(j).get("PO")));
								}
								if (list_detail_info.get(j).get("PAYMENT_INFO") != null) {
									detail.setPaymentid(CommonUtils.toString(list_detail_info.get(j).get("PAYMENT_INFO")));
								}
								detail.setDoc_type(CommonUtils.toString(list_detail_info.get(j).get("DOC_TYPE")));
								DateFormat dfm = new SimpleDateFormat("yyyy-MM-dd");
								// Calendar cal = Calendar.getInstance();
								long hourdiff = 0;

								try {
									Date today = dfm.parse(CommonUtils.toString(header_stmt_no_list.get(i).get("STMT_DATE")));

									// if not use DOC_DATE2 there will be a
									// exception because of wrong date format
									// Date testday = dfm.parse(detail
									// .getInVoiceDate());
									Date testday = dfm.parse(CommonUtils.toString(list_detail_info.get(j).get("DOC_DATE2")));
									Calendar c1 = Calendar.getInstance();
									c1.clear();
									c1.setTime(testday);
									c1.add(Calendar.MONTH, 1);
									testday = c1.getTime();
									/*
									 * Calendar calen2=Calendar.getInstance();
									 * calen2.setTime(testday); int
									 * year_list=calen2.get(Calendar.YEAR); int
									 * month_list=calen2.get(Calendar.MONTH);
									 */
									long diff = today.getTime()- testday.getTime();
									hourdiff = diff / 3600000 / 24;
								} catch (Exception e) {
									hourdiff = 0;
									e.printStackTrace();
								}

								if(detail.getAmount_due()>0&&(detail.getDoc_type().equals("IN")||detail.getDoc_type().equals("DN"))){
									count_total++;
									if (hourdiff <= 30) {
										count_030 = count_030 + 1;
										sum_030 = sum_030 + detail.getAmount_due();
										sum_total = sum_total + detail.getAmount_due();
									}
									if (hourdiff > 30 && hourdiff <= 60) {
										count_3160 = count_3160 + 1;
										sum_3160 = sum_3160 + detail.getAmount_due();
										sum_total = sum_total + detail.getAmount_due();
									}
									if (hourdiff <= 90 && hourdiff > 60) {
										count_6190 = count_6190 + 1;
										sum_6190 = sum_6190 + detail.getAmount_due();
										sum_total = sum_total + detail.getAmount_due();
									}
									if (hourdiff <= 120 && hourdiff > 90) {
										count_91120 = count_91120 + 1;
										sum_91120 = sum_91120 + detail.getAmount_due();
										sum_total = sum_total + detail.getAmount_due();
									}
									if (hourdiff > 120) {
										count_m120 = count_m120 + 1;
										sum_m120 = sum_m120 + detail.getAmount_due();
										sum_total = sum_total + detail.getAmount_due();
									}
								}
								list_detail.add(detail);
							}

							DecimalFormat formater = new DecimalFormat("#,##0.00;-#,##0.00");
							formater.setRoundingMode(RoundingMode.HALF_UP);
							e_exp_pdf_info.put("count_030", "" + count_030);
							e_exp_pdf_info.put("count_6190", "" + count_6190);
							e_exp_pdf_info.put("count_3160", "" + count_3160);
							e_exp_pdf_info.put("count_91120", "" + count_91120);
							e_exp_pdf_info.put("count_m120", "" + count_m120);
							e_exp_pdf_info.put("count_total", "" + count_total);
							e_exp_pdf_info.put("sum_030", formater.format(sum_030));
							e_exp_pdf_info.put("sum_6190", formater.format(sum_6190));
							e_exp_pdf_info.put("sum_3160", formater.format(sum_3160));
							e_exp_pdf_info.put("sum_91120",formater.format(sum_91120));
							e_exp_pdf_info.put("sum_m120",formater.format(sum_m120));
							// E.STMT_TOTAL
							e_exp_pdf_info.put("sum_total",formater.format(header_stmt_no_list.get(i).get("STMT_TOTAL"))+ 
									" " + CommonUtils.toString(header_stmt_no_list.get(i).get("STMT_CURRENCY")));

							if ("SG".equals(this.systemBase)) {
								e_exp_pdf_info.put("remarks", "");
							} else {
								e_exp_pdf_info.put("remarks", "Interest at 1.5% per month will be imposed on all overdue invoices.");
							}

							// create templet for jasper report.
							CustomDataSource_e_expf02 cust_souce = new CustomDataSource_e_expf02();
							cust_souce.setData(list_detail);

							try {
								String path = "";

								ClassLoader classLoader = getClass()
										.getClassLoader();
								// when test by Junit, the URL should be changed
								// to "rp.jsper" and this file should be copy to
								// root of WEB-INF
								File file = new File(classLoader.getResource(
										"../report_pdf/rp.jasper").getFile());
								path = file.getAbsolutePath();

								jasperPrint1 = JasperFillManager.fillReport(
										path.replaceAll("%20", " "),
										e_exp_pdf_info, cust_souce);
								
							    String fileoutput= sessiondictionary + File.separator + CommonUtils.toString(header_stmt_no_list.get(i).get("ID_STMT")).trim() +".pdf";            
							    File outputFile = new File(fileoutput);
							    
							    JRPdfExporter exporterPDF = new JRPdfExporter();
							    exporterPDF.setParameter(JRPdfExporterParameter.JASPER_PRINT, jasperPrint1);
							    exporterPDF.setParameter(JRPdfExporterParameter.OUTPUT_FILE, outputFile);
							    exporterPDF.exportReport();
							    reports.add(outputFile);
								
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		} 
		
		File[] files=new File[reports.size()];
		reports.toArray(files);
		String filePath="";
		try {
			filePath = ZipUtil.zip(files,sessiondictionary);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ZipException e) {
			e.printStackTrace();
		}
		GlobalProcessResult gResult = new GlobalProcessResult();
		File file = new File(filePath);
		/* file . reName */
	    gResult.setFile(file);
		return gResult;
	}
	
	private void resetParameter(Map<String, Object> e_exp_pdf_info) {
	        e_exp_pdf_info.put("stmt_num", "");
	        e_exp_pdf_info.put("stmt_date", "");
	        e_exp_pdf_info.put("acc_num", "");
	        e_exp_pdf_info.put("cus_name", "");
	        e_exp_pdf_info.put("add_line_cus1", "");
	        e_exp_pdf_info.put("add_line_cus2", "");
	        e_exp_pdf_info.put("add_line_cus3", "");
	        e_exp_pdf_info.put("add_line_cus4", "");
	        e_exp_pdf_info.put("currency", "");
	        e_exp_pdf_info.put("count_030", "");
	        e_exp_pdf_info.put("count_3160", "");
	        e_exp_pdf_info.put("count_6190", "");
	        e_exp_pdf_info.put("count_91120", "");
	        e_exp_pdf_info.put("count_m120", "");
	        e_exp_pdf_info.put("count_total", "");
	        e_exp_pdf_info.put("sum_030", "");
	        e_exp_pdf_info.put("sum_3160", "");
	        e_exp_pdf_info.put("sum_6190", "");
	        e_exp_pdf_info.put("sum_91120", "");
	        e_exp_pdf_info.put("sum_m120", "");
	        e_exp_pdf_info.put("sum_total", "");
	        e_exp_pdf_info.put("remarks", "");
	    }
	
	public UpdateDAO getUpdateDAO() {
		return updateDAO;
	}

	public void setUpdateDAO(UpdateDAO updateDAO) {
		this.updateDAO = updateDAO;
	}

	public QueryDAO getQueryDAO() {
		return queryDAO;
	}

	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}
}
