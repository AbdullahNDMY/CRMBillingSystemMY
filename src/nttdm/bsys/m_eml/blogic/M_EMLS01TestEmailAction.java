/**
 * @(#)CheckAlreadyDeleteAction.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created Jul 15, 2014
 * 
 */
package nttdm.bsys.m_eml.blogic;

import java.io.File;
import java.io.IOException;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.web.struts.actions.ActionEx;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.e_eml.bean.E_EMLSendMailBean;
import nttdm.bsys.e_eml.dto.E_EML_MailTemplateDto;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.google.gson.Gson;

/**
 * 
 * check whether TRM is already deleted
 * 
 * @author loamanma
 * 
 */
public class M_EMLS01TestEmailAction extends ActionEx {

    protected QueryDAO queryDAO;
    
    /*private String hostName = "";
    private String portNo = "";
    private boolean sslTls = false;
    private String userName = "";
    private String password = "";
    private String fileSize = "";
    private String templataCode = "";*/
    private String testEmail = "";

    public QueryDAO getQueryDAO() {
        return queryDAO;
    }

    public void setQueryDAO(QueryDAO queryDAO) {
        this.queryDAO = queryDAO;
    }

    @Override
    public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	boolean flag = false;
        Gson googleSon = new Gson();
        System.out.println("ajax call successful!");
        HashMap<String, Object> resultData = new HashMap<String, Object>();
        /*hostName = (String) request.getParameter("hostName");
        portNo = (String) request.getParameter("portNo");
        sslTls = ((String) request.getParameter("sslTls")).equals("1")?true:false;
        userName = (String) request.getParameter("userName");
        password = (String) request.getParameter("password");
        fileSize = (String) request.getParameter("fileSize");
        templataCode = (String) request.getParameter("templataCode");*/
        testEmail = (String) request.getParameter("testEmail");
        
        /*System.out.println("hostName: "+hostName);
        System.out.println("portNo: "+portNo);
        System.out.println("sslTls: "+sslTls);
        System.out.println("userName: "+userName);
        System.out.println("password: "+password);
        System.out.println("fileSize: "+fileSize);
        System.out.println("templataCode: "+templataCode);*/
        System.out.println("testEmail: "+testEmail);
        
        List<E_EML_MailTemplateDto> mailTemplateList = new ArrayList<E_EML_MailTemplateDto>();
		mailTemplateList = queryDAO.executeForObjectList("SELECT.E_EML.GET_MAIL_TEMPLATE", "E-EML-S01");
        
		String content = mailTemplateList.get(0).getContent();
		// SUBJECT
		String subject = mailTemplateList.get(0).getSubject();
		// FROM NAME
		String fromName = mailTemplateList.get(0).getFromDisplayName();

		String id_ref = "[Id Ref]";
		String contact_type = "[Contact Type]";
		String custAccNo = "[Customer Account No]";
		String custID = "[Customer ID]";
		List<Map<String, Object>> updateEmailDList = null;
		try {
			sendMail(testEmail, subject, content, custAccNo, fromName,custID);
		} catch (Exception e) {
			System.out.println("Error: "+e);
			resultData.put("resultFlg", "0");
			resultData.put("msg", "Fail sent email. Please make sure the information is correct.");
			flag = true;
		}
		if(!flag){
	        resultData.put("resultFlg", "1");
	        resultData.put("msg", "Email sent successful to "+testEmail);
	        
		}
		response.setContentType("application/json");
        String jsonString = googleSon.toJson(resultData);

        try {
            // write JSON string into the response
            response.getWriter().print(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    private void sendMail(String emailTo, String subject, String content, String custAccNo, String fromName, String custID)
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
		
		String from = mailSent(emailTo, null, subject, content, fromName);
		
	}
    
    public String mailSent(String to, String[] ccMail, String subject,
			String content, String fromName)
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
		mailSenderImpl.setPort(parseStringToInt((String) serverInfo
				.get("MAIL_SERVER_PORTNO")));
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
		
		/*TestConnection testConnection = new TestConnection();
		testConnection.TestConnection(mailSenderImpl.getHost(), mailSenderImpl.getPort(),
				mailSenderImpl.getUsername(), mailSenderImpl.getPassword(),
				mailSenderImpl.getHost().equals("smtp.gmail.com"));
		testConnection.TestConnection(mailSenderImpl.getHost(), mailSenderImpl.getPort(),
				"","",
				mailSenderImpl.getHost().equals("smtp.gmail.com"));*/
		
		// sent
		mailSenderImpl.send(msg);

		return from;
	}
    
    //#252 Batch Email Billing Document: generate PDF / email CT 09052017 ST
 	private boolean isNullOrEmpty(String[]arr){
 		if(arr != null){
 			if(arr.length > 0){
 				return false;
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
 	//#252 Batch Email Billing Document: generate PDF / email CT 09052017 EN
}
