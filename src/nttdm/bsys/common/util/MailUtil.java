package nttdm.bsys.common.util;

import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtil {

	private static Properties fMailServerConfig = new Properties();
	static {
		fetchConfig();
	}

	private static void fetchConfig() {
		ResourceBundle resourceBundle = null;
		//If possible, one should try to avoid hard-coding a path in this
		//manner; in a web application, one should place such a file in
		//WEB-INF, and access it using ServletContext.getResourceAsStream.
		//Another alternative is Class.getResourceAsStream.
		//This file contains the javax.mail config properties mentioned above.

		resourceBundle = ResourceBundle.getBundle("mail");

		fMailServerConfig.put("mail.transport.protocol", 
				resourceBundle.getString("mail.transport.protocol"));
		fMailServerConfig.put("mail.smtp.host", 
				resourceBundle.getString("mail.smtp.host"));
		fMailServerConfig.put("mail.smtp.port", 
				resourceBundle.getString("mail.smtp.port"));
		fMailServerConfig.put("mail.smtp.auth", 
				resourceBundle.getString("mail.smtp.auth"));
		fMailServerConfig.put("mail.debug", 
				resourceBundle.getString("mail.debug"));
		fMailServerConfig.put("mail.from", 
				resourceBundle.getString("mail.from"));
		fMailServerConfig.put("mail.password", 
				resourceBundle.getString("mail.password"));
		fMailServerConfig.put("mail.autobcc", 
				resourceBundle.getString("mail.autobcc"));
	}

	/**
	 * Allows the config to be refreshed at runtime, instead of
	 * requiring a restart.
	 */
	public static void refreshConfig() {
		fMailServerConfig.clear();
		fetchConfig();
	}

	public void postMail(String recipients[], String subject, String message)
			{
		boolean debug = false;

	
		Authenticator pa = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fMailServerConfig
						.getProperty("mail.from"), fMailServerConfig
						.getProperty("mail.password"));
			}
		};
		// create some properties and get the default Session		
		Session session = Session.getDefaultInstance(fMailServerConfig, pa);
		session.setDebug(debug);
		// create a message 
		MimeMessage msg = new MimeMessage(session);
		// set the from and to address
		try{
		InternetAddress addressFrom = new InternetAddress(fMailServerConfig
				.getProperty("mail.from"));
		msg.setFrom(addressFrom);

		InternetAddress[] addressTo = new InternetAddress[recipients.length];
		for (int i = 0; i < recipients.length; i++) {
			addressTo[i] = new InternetAddress(recipients[i]);
		}
		msg.setRecipients(Message.RecipientType.TO, addressTo);
		
		
		String bccAddressList = fMailServerConfig.getProperty("mail.autobcc");
		if (bccAddressList.trim().length()>0) {
			String[] bccAddressArr = bccAddressList.split(";");
			InternetAddress[] addressBcc = new InternetAddress[bccAddressArr.length];
			for (int i=0;i<bccAddressArr.length; i++)
			{
				addressBcc[i] = new InternetAddress(bccAddressArr[i]);
			}
			msg.setRecipients(Message.RecipientType.BCC, addressBcc);
		}

		// Optional : You can also set your custom headers in the Email if you Want
		msg.addHeader("MyHeaderName", "myHeaderValue");
		// Setting the Subject and Content Type
		msg.setSubject(subject);
		msg.setContent(message, "text/plain");
		Transport.send(msg);
		}
		catch (MessagingException ex){
			ex.printStackTrace();
			throw new MailException("Cannot send mail");
		}
	}

}
