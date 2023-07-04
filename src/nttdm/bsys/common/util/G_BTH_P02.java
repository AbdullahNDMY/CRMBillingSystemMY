/**
 * @(#)G_BTH_P02.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2015/08/05
 * 
 * Copyright (c) 2013 Billing System Malaysia. All rights reserved.
 */
package nttdm.bsys.common.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import nttdm.bsys.b_bth.dto.RP_B_BTH_P01_03Output;
import nttdm.bsys.e_eml.dto.E_EML03Input;
import nttdm.bsys.e_eml.dto.E_EML_MailTemplateDto;
import de.idyl.winzipaes.AesZipFileEncrypter;
import de.idyl.winzipaes.impl.AESEncrypterBC;

/**
 * @author binz
 * 
 */
public class G_BTH_P02 extends
		AbstractGlobalProcess<E_EML03Input, RP_B_BTH_P01_03Output> {

	private static byte[] buf = new byte[1024];

	public GlobalProcessResult execute(E_EML03Input param,
			RP_B_BTH_P01_03Output outputDTO) {
		return null;
	}

	public int generatePdf(E_EML03Input param, int batchId) {
		int sucPdfCount = 0;

		String pdfPath = queryDAO.executeForObject(
				"SELECT.E_EML.GET_PDF_LOCATION", null, String.class);

		Map<String, Object> mSystemBase = this.queryDAO.executeForMap(
				"B_BTH.getBILDOCPDF", null);
		String systembase = CommonUtils.toString(mSystemBase.get("VALUE"));

		// Get Pdf's config
		List<E_EML_MailTemplateDto> mailTemplateList = new ArrayList<E_EML_MailTemplateDto>();
		mailTemplateList = queryDAO.executeForObjectList(
				"SELECT.E_EML.GET_MAIL_TEMPLATE", "E-EML-S01");
		E_EML_MailTemplateDto mailTemplate = mailTemplateList.get(0);
		String tlnvFileName = mailTemplate.getAttactment1();
		String dnFileName = mailTemplate.getAttactment2();
		String cnFileName = mailTemplate.getAttactment3();
		String ntFileName = mailTemplate.getAttactment4();
		String attactmentPass = mailTemplate.getAttactmentPass();
		String zipFlieName = mailTemplate.getZipFlieName();
		// get idrefList
		List<Map<String, Object>> billDocList = param.getBillDocList();
		/*
		 * Map<String, Object> idref = new HashMap<String, Object>();
		 * idref.put("id_ref", param.getIdRefs()); billDocList =
		 * queryDAO.executeForObjectList("E_EML.selectPdfUserSql", idref);
		 */
		// pdfList
		List<Map<String, String>> pdfList = new ArrayList<Map<String, String>>();
		// loop idrefs
		for (int i = 0; i < billDocList.size(); i++) {
			try {
				// update db use
				Map<String, String> info = new HashMap<String, String>();
				// init compare param
				String nowIdCust = "";
				String nowEmail = "";
				// now Record
				Map<String, Object> nowRecord = billDocList.get(i);
				String nowIdRef = nowRecord.get("ID_REF").toString();
				String custAccNo = "";
				if (nowRecord.get("CUST_ACC_NO") != null) {
					custAccNo = nowRecord.get("CUST_ACC_NO").toString();
				}
				String datereq = nowRecord.get("DATEREQ").toString();
				if (nowRecord.get("ID_CUST") != null) {
					nowIdCust = nowRecord.get("ID_CUST").toString();
				}
				// get pdfFileNm
				String pdfFileNm = this.getPdfFielNm(nowIdRef, tlnvFileName,
						dnFileName, cnFileName, ntFileName, datereq);
				String pdfFilePath = pdfPath + pdfFileNm;
				
				// init psd
				String psd = "";
				boolean useZipFile = true;
				if ("0".equals(param.getPdfZipFile())) {
					psd = attactmentPass;
					useZipFile = false;
				}
				// create pdf (with password or not)
				createGeneratePdf(systembase, pdfFilePath, nowIdRef, param, psd);
				// set db update Map
				info.put("idRef", nowIdRef);
				info.put("pdfFilePath", pdfFilePath);
				info.put("pdfFileNm", pdfFileNm);
				// add pdfPath
				pdfList.add(info);

				// next Record
				if (i != billDocList.size() - 1) {
					String nextIdCust = "";
					String nextEmail = "";
					Map<String, Object> nextRecord = billDocList.get(i + 1);
					if (nextRecord.get("ID_CUST") != null) {
						nextIdCust = nextRecord.get("ID_CUST").toString();
					}
					if (nextRecord.get("EMAIL") != null) {
						nextEmail = nextRecord.get("EMAIL").toString();
					}

					if (nowIdCust.equals(nextIdCust)
							&& nowEmail.equals(nextEmail)) {
						sucPdfCount++;
						continue;
					} else {
						String updateFileNm = pdfFileNm;
						if (useZipFile) {
							// getzipFlieName
							String zipName = this.zipFlieName(zipFlieName, custAccNo, nowIdRef,nowIdCust);
							updateFileNm = this.AddtoZip(pdfList, pdfPath,
									attactmentPass, zipName);
						}
						for (Map<String, String> dbInfo : pdfList) {
							Map<String, Object> insertMap = new HashMap<String, Object>();
							insertMap.put("id_ref", dbInfo.get("idRef")
									.toString());
							insertMap.put("id_batch", batchId);
							if (useZipFile) {
								insertMap.put("filename", updateFileNm);
							} else {
								insertMap.put("filename",
										dbInfo.get("pdfFileNm").toString());
							}
							insertMap.put("filelocation", pdfPath);
							insertMap.put("filepass", attactmentPass);
							insertMap.put("id_login", param.getUvo()
									.getId_user());
							updateDAO.execute("INSERT.M_EML.T_DOC_EMAIL",
									insertMap);
						}
						// init param
						pdfList.clear();
					}
				}

				if (i == billDocList.size() - 1) {
					String updateFileNm = pdfFileNm;
					if (useZipFile) {
						// getzipFlieName
						String zipName = this.zipFlieName(zipFlieName, custAccNo, nowIdRef,nowIdCust);
						updateFileNm = this.AddtoZip(pdfList, pdfPath,
								attactmentPass, zipName);
					}
					for (Map<String, String> dbInfo : pdfList) {
						Map<String, Object> insertMap = new HashMap<String, Object>();
						insertMap.put("id_ref", dbInfo.get("idRef").toString());
						insertMap.put("id_batch", batchId);
						if (useZipFile) {
							insertMap.put("filename", updateFileNm);
						} else {
							insertMap.put("filename",
									dbInfo.get("pdfFileNm").toString());
						}
						insertMap.put("filelocation", pdfPath);
						insertMap.put("filepass", attactmentPass);
						insertMap.put("id_login", param.getUvo().getId_user());
						updateDAO
								.execute("INSERT.M_EML.T_DOC_EMAIL", insertMap);
					}
					// init param
					pdfList.clear();

				}
				sucPdfCount++;

			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
		}
		return sucPdfCount;
	}

	private void createGeneratePdf(String systembase, String pdfPath,
			String idRef, E_EML03Input param, String attactmentPass) {
		if ("SG01".equals(systembase)) {
			// #252 Batch Email Billing Document: generate PDF / email CT 09052017 ST
			G_BTH_P01_SG g_bth_p01_sg = new G_BTH_P01_SG(this.queryDAO, this.updateDAO);
			g_bth_p01_sg.emailGeneratePdf(pdfPath, idRef, param.getAutSign(), param.getUvo(), attactmentPass);
            //filePath = g_bth_p01_sg.generate(param.getIdRefs(), param.getUvo());
			// #252 Batch Email Billing Document: generate PDF / email CT 09052017 En
		} else if ("MY02".equals(systembase)) {
			G_BTH_P01_MY_2 g_bth_p01_my_2 = new G_BTH_P01_MY_2(this.queryDAO,
					this.updateDAO);
			g_bth_p01_my_2.emailGeneratePdf(pdfPath, idRef, param.getAutSign(),
					param.getUvo(), attactmentPass);
		} else {
			G_BTH_P01_MY g_bth_p01_my = new G_BTH_P01_MY(this.queryDAO,
					this.updateDAO);
			g_bth_p01_my.emailGeneratePdf(pdfPath, idRef, param.getUvo(),
					attactmentPass);
		}
	}

	private String getPdfFielNm(String idRef, String tlnvFileName,
			String dnFileName, String cnFileName, String ntFileName,
			String datereq) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		Date date = sdf.parse(datereq); 
		String fileNm = "";
		String billType = queryDAO.executeForObject(
				"SELECT.E_EML.GET_BILL_TYPE", idRef.trim(), String.class);
		if ("IN".equals(billType)) {
			fileNm = tlnvFileName;
		} else if ("DN".equals(billType)) {
			fileNm = dnFileName;
		} else if ("CN".equals(billType)) {
			fileNm = cnFileName;
		} else if ("NT".equals(billType)) {
			fileNm = ntFileName;
		}
		if (fileNm.indexOf("<DocumentDate") >= 1) {
			String formatStr = fileNm.split("<DocumentDate,")[1].split(">")[0];
			String format = formatStr.replace("D", "d").replace("Y", "y")
					.replace("m", "M").replace(" ", "");
			SimpleDateFormat sf = new SimpleDateFormat(format, Locale.ENGLISH);
			fileNm = fileNm.replace("<DocumentDate," + formatStr + ">",
					sf.format(date));
		}
		if (fileNm.indexOf("<DocumentNo>") >= 1) {
			fileNm = fileNm.replace("<DocumentNo>", idRef.trim());
		}

		int fileNum = queryDAO.executeForObject("SELECT.E_EML.GET_PDF_NUM",
				idRef.trim(), Integer.class);
		if (fileNum != 0) {
			fileNm = fileNm.trim() + "_" + (fileNum + 1);
		}
		return fileNm.trim() + ".pdf";
	}

	private String AddtoZip(List<Map<String, String>> paramList,
			String filePath, String psd, String zipName) throws Exception {
		List<String> sourcefilelist = new ArrayList<String>();

		for (Map<String, String> info : paramList) {
			sourcefilelist.add(info.get("pdfFilePath"));
		}

		String srcZipFile = filePath + "tmp.zip";
		ZipOutputStream jos = new ZipOutputStream(new FileOutputStream(
				new File(srcZipFile)));
		for (String sourcefile : sourcefilelist) {
			File file = new File(sourcefile);
			ZipEntry sourEntry = new ZipEntry(file.getName());
			FileInputStream fin = new FileInputStream(file);
			BufferedInputStream in = new BufferedInputStream(fin);
			jos.putNextEntry(sourEntry);
			int len;
			while ((len = in.read(buf)) >= 0)
				jos.write(buf, 0, len);
			in.close();
			jos.closeEntry();
		}
		jos.finish();
		jos.close();
		// set psd
		AesZipFileEncrypter.zipAndEncryptAll(new File(srcZipFile), new File(
				filePath += zipName + ".zip"), psd, new AESEncrypterBC());

		// delete files
		sourcefilelist.add(srcZipFile);
		for (String list : sourcefilelist) {
			File file = new File(list);
			if (file.isFile()) {
				file.delete();
			}
		}
		return zipName + ".zip";
	}
	
	private String zipFlieName(String zipFlieName,String custAccNo,String idRef, String custID){
		// zipFileName
		String zipName = zipFlieName;
		if (zipName.contains("<CustAccNo>")) {
			zipName = zipName.replace("<CustAccNo>", custAccNo);
		}
		
		// #271 [1-UAT] G-EML-P02 Send billing document via email add new parameter <CustID> CT 03072017
		if (zipName.contains("<CustID>")) {
			zipName = zipName.replace("<CustID>", custID);
		}
		// #271 [1-UAT] G-EML-P02 Send billing document via email add new parameter <CustID> CT 03072017
		
		Matcher m = Pattern.compile("(<.*>)").matcher(zipName);
		if (m.find()) {
			String date = m.group(1);
			String subDate = "";
			if (date.startsWith("<")) {
				subDate = new String(new StringBuffer(date).deleteCharAt(0));
			}
			if (subDate.endsWith(">")) {
				subDate = new String(
						new StringBuffer(subDate).deleteCharAt(subDate.length() - 1));
			}
			String[] dateformat = subDate.split(",");
			SimpleDateFormat df = new SimpleDateFormat(dateformat[1]);
			String documentDate = df.format(new Date());
			zipName = zipName.replace(date, documentDate);
		}
		int fileNum = queryDAO.executeForObject("SELECT.E_EML.GET_PDF_ZIP_NUM",
				zipName, Integer.class);
		if (fileNum != 0) {
			zipName = zipName + "_" + (fileNum + 1);
		}
		return zipName;
	}

}
