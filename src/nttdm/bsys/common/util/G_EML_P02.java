package nttdm.bsys.common.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.web.struts.action.GlobalMessageResources;
import nttdm.bsys.common.bean.G_SET_ReturnValue;
import nttdm.bsys.common.bean.T_SET_BATCH;
import nttdm.bsys.e_eml.bean.E_EMLSendMailBean;
import nttdm.bsys.e_eml.dto.E_EML03Input;
import nttdm.bsys.e_eml.dto.E_EMLInput;
import nttdm.bsys.e_eml.dto.E_EMLPdfCheckDto;
import nttdm.bsys.e_eml.dto.E_EML_MailTemplateDto;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionMessages;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * ���[���T�[�o�A���[����templateFolder��ݒ肷��N���X<br>
 * 
 * @author Jack �V�K�쐬
 * @version 1.00
 * @since 2009/10/12
 */
public class G_EML_P02 {
	/**
	 * <div>queryDAO</div>
	 */
	private QueryDAO queryDAO;

	/**
	 * <div>updateDAO</div>
	 */
	private UpdateDAO updateDAO;

	/**
	 * batchId
	 */
	private Integer idBatch = 0;

	/**
	 * <div>Constructor</div>
	 * 
	 * @param queryDAO
	 *            QueryDAO
	 * @param updateDAO
	 *            UpdateDAO
	 */
	private int maxFileSize = 0;

	public G_EML_P02(QueryDAO queryDAO, UpdateDAO updateDAO) {
		this.queryDAO = queryDAO;
		this.updateDAO = updateDAO;
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public GlobalProcessResult contextInitialized(E_EML03Input input,
			E_EMLInput emialAllInput) {

		BLogicMessages messages = new BLogicMessages();
		GlobalProcessResult gResult = new GlobalProcessResult();

		// id_batch
		T_SET_BATCH t_batch = new T_SET_BATCH();
		t_batch.setSTATUS("1");
		t_batch.setBATCH_TYPE("EM");
		t_batch.setFILENAME("[Email]");
		// Call G_SET_P01
		G_SET_P01 gsetp01 = new G_SET_P01(queryDAO, updateDAO);
		G_SET_ReturnValue returnValue = gsetp01.G_SET_P01_GetIdBatch(t_batch);
		int batchId = returnValue.getBatch_id();
		this.idBatch = batchId;
		if (batchId < 0) {
			BLogicMessage msg = new BLogicMessage("errors.ERR1SC112",
					new Object[] { returnValue.getRET_MSG() });
			messages.add(ActionMessages.GLOBAL_MESSAGE, msg);
			gResult.setErrors(messages);
			return gResult;
		} else {
			// maxFileSize
			this.maxFileSize = queryDAO.executeForObject("E_EML.maxFileSize",
					"", Integer.class);
			// emailInfo
			List<E_EML_MailTemplateDto> mailTemplateList = new ArrayList<E_EML_MailTemplateDto>();
			mailTemplateList = queryDAO.executeForObjectList(
					"SELECT.E_EML.GET_MAIL_TEMPLATE", "E-EML-S01");
			String always_cc = "";
			if (mailTemplateList.get(0).getAlwaysCc() != null) {
				always_cc = mailTemplateList.get(0).getAlwaysCc();
			}
			String content = mailTemplateList.get(0).getContent();
			// SUBJECT
			String subject = mailTemplateList.get(0).getSubject();
			// FROM NAME
			String fromName = mailTemplateList.get(0).getFromDisplayName();
			// ListBillDoc
			List<Map<String, Object>> billDocList = new ArrayList<Map<String, Object>>();
			if ("email".equals(input.getModuleId())) {
				if (input.getIdRefs() != null && input.getIdRefs().length != 0) {
					Map<String, Object> idref = new HashMap<String, Object>();
					idref.put("id_ref", input.getIdRefs());
					billDocList = queryDAO.executeForObjectList(
							"E_EML.selectSameCusInfo", idref);
				}
			} else {
				billDocList = queryDAO.executeForObjectList(
						"E_EML.selectEmailAllIdRef", emialAllInput);
			}

			// Pervious's parmas
			String idCustPervious = "";
			String emailPervious = "";
			String custAccNoPervious = "";
			String emailCcPervious = "";
			// errorLogTotal
			String errorLogTotal = "";
			// MailComposed
			boolean mailComposed = false;
			// emailCc
			String emailCc = always_cc;
			// attachment
			List<String> filePathList = new ArrayList<String>();
			String pdfPass = "";
			// countFailEmailSent
			int emailFailCount = 0;
			// countEmailSent
			int emailSuccessCount = 0;
			String userId = input.getUvo().getId_user();
			List<Map<String, Object>> updateEmailD = new ArrayList<Map<String, Object>>();
			if (billDocList != null && billDocList.size() > 0) {
				for (int i = 0; i < billDocList.size(); i++) {
					// idCustCurren
					String idCustCurren = "";
					// EmailCurren
					String emailCurren = "";
					// EmailCcCurren
					String emailCcCurren = "";
					// errorLog
					String errorLog = "";
					// search's param
					Map<String, Object> billDoc = billDocList.get(i);
					String id_ref = billDoc.get("ID_REF").toString();
					String contact_type = "";
					String custAccNo = "";
					if (billDoc.get("CONTACT_TYPE") != null) {
						contact_type = billDoc.get("CONTACT_TYPE").toString();
					}
					if (billDoc.get("CUST_ACC_NO") != null) {
						custAccNo = billDoc.get("CUST_ACC_NO").toString();
					}
					// compare's param
					if (billDoc.get("ID_CUST") != null) {
						idCustCurren = billDoc.get("ID_CUST").toString();
					}
					if (billDoc.get("CONTACT_EMAIL") != null) {
						emailCurren = billDoc.get("CONTACT_EMAIL").toString().replace(" ", "");
					}
					if (billDoc.get("CONTACT_EMAIL_CC") != null) {
						emailCcCurren = billDoc.get("CONTACT_EMAIL_CC").toString().replace(" ", "");
					}
					try {
						Map<String, Object> tmaildinfo = new HashMap<String, Object>();
						E_EMLSendMailBean mailBean = new E_EMLSendMailBean();
						// same idcust and emailAddress
						if (emailCurren.equals(emailPervious)
								&& idCustCurren.equals(idCustPervious)
								&& emailCcCurren.equals(emailCcPervious)) {
							// get attachment and pdf password
							mailBean = getAttachementAndPass(id_ref, errorLog,
									contact_type, errorLogTotal, emailCurren);
							// error
							if (mailBean.isBreakfor()) {
								errorLog = mailBean.getErrorLog();
								errorLogTotal = mailBean.getErrorLogTotal();
								emailFailCount++;
								continue;
							} else {
								if (!StringUtils.isEmpty(mailBean.getEmailCc())) {
									if (emailCc.indexOf(emailCcCurren) < 1) {
										if (!emailCc.contains(mailBean.getEmailCc())) {
											if (!StringUtils.isEmpty(emailCc)) {
												emailCc = emailCc + ";";
											}
											emailCc = emailCc + mailBean.getEmailCc();
										}
									}
								}
								// attachment
								String filepath = mailBean.getFilePathList();
								// filePathList.clear();
								if (!filePathList.contains(filepath)) {
									filePathList.add(filepath);
								}
								// send mail: update t_mail_d's param
								tmaildinfo.put("id_ref", id_ref);
								tmaildinfo.put("loginUser", userId);
								tmaildinfo.put("id_doc", mailBean.getId_doc());
								updateEmailD.add(tmaildinfo);
								// send mail or not
								mailComposed = true;
							}
						} else {
							// mailComposed is false
							if (mailComposed) {
								// sendMail & update email's db
								if (!StringUtils.isEmpty(emailCcPervious)) {
									if(!emailCc.contains(emailCcPervious)){
										if (StringUtils.isEmpty(emailCc)) {
											emailCc = emailCcPervious;
										} else {
											emailCc = emailCc + ";" + emailCcPervious;
										}
									}
								}
								sendMail(emailPervious, subject, content,
										filePathList, batchId, userId, pdfPass,
										custAccNoPervious, idCustPervious, emailCc, updateEmailD,
										fromName);
								emailSuccessCount++;
								// send mail's param init
								emailCc = always_cc;
								mailComposed = false;
								filePathList.clear();
								updateEmailD.clear();
							}
							// get attachment and password
							mailBean = getAttachementAndPass(id_ref, errorLog,
									contact_type, errorLogTotal, emailCurren);
							if (mailBean.isBreakfor()) {
								errorLog = mailBean.getErrorLog();
								errorLogTotal = mailBean.getErrorLogTotal();
								emailFailCount++;
								continue;
							} else {
								// send mail's param
								if (!StringUtils.isEmpty(mailBean.getEmailCc())) {
									if (!emailCc.contains(mailBean.getEmailCc())) {
										if (!StringUtils.isEmpty(emailCc)) {
											emailCc = emailCc + ";";
										}
										emailCc = emailCc + mailBean.getEmailCc();
									}
								}
								pdfPass = mailBean.getPdfPass();
								custAccNoPervious = custAccNo;
								filePathList.add(mailBean.getFilePathList());
								// send mail: update t_mail_d's param
								tmaildinfo.put("id_ref", id_ref);
								tmaildinfo.put("loginUser", userId);
								tmaildinfo.put("id_doc", mailBean.getId_doc());
								updateEmailD.add(tmaildinfo);
								// send mail or not
								mailComposed = true;
								// set param
								idCustPervious = idCustCurren;
								emailPervious = emailCurren;
								emailCcPervious = emailCcCurren;
							}
						}
						if (i == billDocList.size() - 1) {
							// mailComposed is false
							if (mailComposed) {
								if (!StringUtils.isEmpty(emailCcPervious)) {
									if(!emailCc.contains(emailCcPervious)){
										if (StringUtils.isEmpty(emailCc)) {
											emailCc = emailCcPervious;
										} else {
											emailCc = emailCc + ";" + emailCcPervious;
										}
									}
								}
								// sendMail
								sendMail(emailPervious, subject, content,
										filePathList, batchId, userId, pdfPass,
										custAccNoPervious, idCustPervious, emailCc, updateEmailD,
										fromName);
								emailSuccessCount++;
								// send mail's param init
								emailCc = always_cc;
								mailComposed = false;
								filePathList.clear();
								updateEmailD.clear();
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
						continue;
					}
				}
			}
			// id_batch
			t_batch.setSTATUS("2");
			GlobalMessageResources msgResource = GlobalMessageResources
					.getInstance();
			t_batch.setMessage(msgResource.getMessage("info.ERR2SC056",
					new Object[] { billDocList.size() - emailFailCount, emailSuccessCount, emailFailCount,
							errorLogTotal }));
			t_batch.setID_BATCH(String.valueOf(batchId));
			// Call G_SET_P01
			gsetp01.G_SET_P01_GetIdBatch(t_batch);

			return gResult;

		}

	}

	/**
	 * mailSent
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("null")
	public String mailSent(String to, String[] ccMail, String subject,
			String content, List<String> filePathList, String fromName)
			throws Exception {
		JavaMailSenderImpl mailSenderImpl = new JavaMailSenderImpl();
		MimeMessage msg = mailSenderImpl.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(msg, true);
		
		// serverInfo
		Map<String, Object> serverInfo = queryDAO.executeForMap(
				"SELECT.M_EML.SQL001", null);
		// host
		mailSenderImpl.setHost((String) serverInfo.get("MAIL_SERVER_NAME"));
		// userName
		mailSenderImpl.setUsername((String) serverInfo
				.get("MAIL_SERVER_USERNAME"));
		// password
		mailSenderImpl.setPassword((String) serverInfo
				.get("MAIL_SERVER_PASSWORD"));
		String from = (String) serverInfo.get("MAIL_SERVER_USERNAME");
		//#252 Batch Email Billing Document: generate PDF / email CT 09052017 ST
		/*mailSenderImpl.setPort(parseStringToInt((String) serverInfo
				.get("MAIL_SERVER_PORTNO")));*/
		//#252 Batch Email Billing Document: generate PDF / email CT 09052017 EN
		// helper.setFrom(from);
		helper.setFrom(from, fromName);
		String [] toMail = to.split(";");
		helper.setTo(toMail);
		//#252 Batch Email Billing Document: generate PDF / email CT 09052017 ST
		if (!isNullOrEmpty(ccMail)) {
			helper.setCc(ccMail);
		}
		//#252 Batch Email Billing Document: generate PDF / email CT 09052017 EN
		helper.setSubject(subject);
		helper.setText(content, true);
		// attachment
		if (filePathList != null) {
			for (String filePath : filePathList) {
				helper.addAttachment(new File(filePath).getName(), new File(
						filePath));
			}
		}
		//#252 Batch Email Billing Document: generate PDF / email CT 09052017 ST
		String ssltls = (String) serverInfo.get("MAIL_SERVER_SSLTLS");
		Properties properties = new Properties();
		if(ssltls.trim().equals("1")){
		    properties.put("mail.smtp.starttls.enable", "true");
		}
		if(!isNullOrEmpty(mailSenderImpl.getUsername()) && !isNullOrEmpty(mailSenderImpl.getPassword())){
	    	properties.put("mail.smtp.auth", "true");
	    }
		mailSenderImpl.setJavaMailProperties(properties);
		//#252 Batch Email Billing Document: generate PDF / email CT 09052017 EN
		
		// sent
		mailSenderImpl.send(msg);

		return from;
	}

	/**
	 * getAttachementAndPassword
	 * 
	 * 
	 */
	private E_EMLSendMailBean getAttachementAndPass(String id_ref,
			String errorLog, String contact_type, String errorLogTotal,
			String emailTo) {
		E_EMLSendMailBean mailBean = new E_EMLSendMailBean();
		boolean breakfor = false;
		// pdfExistCheck
		List<E_EMLPdfCheckDto> pdfCheck = queryDAO.executeForObjectList(
				"E_EML.selectPdfInfo", id_ref);
		// emailTo check
		if (StringUtils.isEmpty(emailTo)) {
			errorLog = id_ref + "- Invalid email address<br/>";
			// break;
			breakfor = true;
		}
		if (!breakfor) {
			// pdf is null
			if (pdfCheck == null || pdfCheck.isEmpty()) {
				if (errorLog.isEmpty()) {
					errorLog = errorLog + id_ref + "- No PDF<br/>";
				} else {
					errorLog = errorLog + ",No PDF<br/>";
				}
				breakfor = true;
			} else {
				String pdfPath = pdfCheck.get(0).getFileLocation()
						+ pdfCheck.get(0).getFileName();
				File file = new File(pdfPath);
				if (file.length() > this.maxFileSize * 1024) {
					if (errorLog.isEmpty()) {
						errorLog = errorLog + id_ref
								+ "attachement(s) file size exceed limit<br/>";
					} else {
						errorLog = errorLog
								+ ",attachement(s) file size exceed limit<br/>";
					}
					breakfor = true;
				}
			}

		}
		if (!breakfor) {
			String filePath = pdfCheck.get(0).getFileLocation()
					+ pdfCheck.get(0).getFileName();
			mailBean.setPdfPass(pdfCheck.get(0).getFilePass());
			mailBean.setId_doc(pdfCheck.get(0).getId_doc());
			// getCC
			String emailCc = queryDAO.executeForObject("E_EML.selectCC2",
					id_ref, String.class);
			mailBean.setEmailCc(emailCc);
			mailBean.setFilePathList(filePath);
		}
		mailBean.setBreakfor(breakfor);
		mailBean.setErrorLog(errorLog);
		mailBean.setErrorLogTotal(errorLogTotal + errorLog);
		return mailBean;
	}

	private void sendMail(String emailTo, String subject, String content,
			List<String> filePathList, Integer batchId, String userId,
			String pdfPass, String custAccNo, String custID, String emailCc,
			List<Map<String, Object>> updateEmailDList, String fromName)
			throws Exception {
		// subject
		if (subject.contains("<CustAccNo>")) {
			subject = subject.replace("<CustAccNo>", custAccNo);
		}
		// #271 [1-UAT] G-EML-P02 Send billing document via email add new parameter <CustID> CT 21062017
		if (subject.contains("<CustID>")) {
			subject = subject.replace("<CustID>", custID);
		}
		// #271 [1-UAT] G-EML-P02 Send billing document via email add new parameter <CustID> CT 21062017
		Matcher m = Pattern.compile("(<.*>)").matcher(subject);
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
			subject = subject.replace(date, documentDate);
		}
		// cc
		String[] cc = emailCc.split(";");
		// sentMail
		String from = mailSent(emailTo, cc, subject, content, filePathList,
				fromName);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sentdate = df.format(new Date());
		// sentPassword
		mailSent(emailTo, cc, subject, "Password: " + pdfPass, null, fromName);
		String sent2date = df.format(new Date());
		Map<String, Object> mailinfo = new HashMap<String, Object>();
		mailinfo.put("from", from);
		mailinfo.put("to", emailTo);
		mailinfo.put("cc", emailCc);
		mailinfo.put("subject", subject);
		mailinfo.put("sentdate", sentdate);
		mailinfo.put("sent2date", sent2date);
		mailinfo.put("loginUser", userId);
		mailinfo.put("id_batch", batchId);
		// insert T_EMAIL_H
		updateDAO.execute("E_EML.insertT_EMAIL_H", mailinfo);
		// insert T_EMAIL_D
		for (Map<String, Object> updateEmailD : updateEmailDList) {
			updateDAO.execute("E_EML.insertT_EMAIL_D", updateEmailD);
		}
	}

	/**
	 * @return the queryDAO
	 */
	public QueryDAO getQueryDAO() {
		return queryDAO;
	}

	/**
	 * @param queryDAO
	 *            the queryDAO to set
	 */
	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}

	/**
	 * @return the updateDAO
	 */
	public UpdateDAO getUpdateDAO() {
		return updateDAO;
	}

	/**
	 * @param updateDAO
	 *            the updateDAO to set
	 */
	public void setUpdateDAO(UpdateDAO updateDAO) {
		this.updateDAO = updateDAO;
	}

	/**
	 * @return the idBatch
	 */
	public Integer getIdBatch() {
		return idBatch;
	}

	/**
	 * @param idBatch
	 *            the idBatch to set
	 */
	public void setIdBatch(Integer idBatch) {
		this.idBatch = idBatch;
	}
	
	// //#252 Batch Email Billing Document: generate PDF / email CT 09052017 ST
	private boolean isNullOrEmpty(String[]arr){
		if(arr != null){
			if(arr.length > 0){
				if(!arr[0].trim().equals("")){
					return false;
				}
				
			}
		}
		return true;
	}
	
	private boolean isNullOrEmpty(String arr){
		if(arr != null){
			if(!arr.equals("")){
				return false;
			}
		}
		return true;
	}
	
	private int parseStringToInt(String arr){
		if(arr != null){
			int result = 0;
			try{
			result = Integer.parseInt(arr.trim());
			}catch(Exception e){
				return 0;
			}
			return result;
		}
		return 0;
	}
	// //#252 Batch Email Billing Document: generate PDF / email CT 09052017 EN
	
	private boolean testConnection(String hostName, int portNo, String userName, String pwd){
		
		return true;
	}
}
