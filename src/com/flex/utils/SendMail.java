package com.flex.utils;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {
	private String mail_smtp_server;
	private int mail_starttls_enable;
	private int mail_smtp_port;
	private String mail_user_name;
	private String mail_password;
	private String mail_from;

	public SendMail(String mail_smtp_server, int mail_starttls_enable, int mail_smtp_port, String mail_user_name,
			String mail_password, String mail_from) {
		this.mail_smtp_server = mail_smtp_server;
		this.mail_starttls_enable = mail_starttls_enable;
		this.mail_smtp_port = mail_smtp_port;
		this.mail_user_name = mail_user_name;
		this.mail_password = mail_password;
		this.mail_from = mail_from;
	}
	public boolean send(String mail_to, String mail_subject, String mail_body) {
		boolean flg;
		try {
			// java.security.Security.addProvider(new
			// com.sun.net.ssl.internal.ssl.Provider());
			Properties props = System.getProperties();
			// -- Attaching to default Session, or we could start a new one --
			props.put("mail.smtp.host", mail_smtp_server);
			Authenticator pa = null; // default: no authentication
			int tls_enable=mail_starttls_enable;
			if(tls_enable==1){
				props.put("mail.smtp.port", mail_smtp_port);
				props.put("mail.smtp.starttls.enable", "true");
				final String login = Lib.safeTrim(mail_user_name);
				final String pwd = mail_password;
				
				if (login != null && pwd != null) { // authentication required?
					props.put("mail.smtp.auth", "true");
					pa = new Authenticator() {
						public PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(login, pwd);
						}
					};
				}// else: no authentication
			}
			Session session = Session.getInstance(props, pa);
			// -- Create a new message --
			Message msg = new MimeMessage(session);
			// -- Set the FROM and TO fields --
			msg.setFrom(new InternetAddress(Lib.safeTrim(mail_from)));
			//msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
			 msg.addRecipient(Message.RecipientType.TO, new InternetAddress(mail_to));
			// -- Set the subject and body text --
			msg.setSubject(mail_subject);
		//	msg.setText(body);
			msg.setContent(mail_body, "text/html;charset=\"UTF-8\"");
			// -- Set some other header information --
			//msg.setHeader("X-Mailer", "LOTONtechEmail");
			msg.setSentDate(new Date());
			msg.saveChanges();
			// -- Send the message --
			Transport.send(msg);
			flg = true;
//			System.out.println("Message sent OK.");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			flg = false;
		}
		return flg;
	}

	/**
	 * @return the mail_smtp_server
	 */
	public String getMail_smtp_server() {
		return mail_smtp_server;
	}

	/**
	 * @param mail_smtp_server the mail_smtp_server to set
	 */
	public void setMail_smtp_server(String mail_smtp_server) {
		this.mail_smtp_server = mail_smtp_server;
	}

	/**
	 * @return the mail_starttls_enable
	 */
	public int getMail_starttls_enable() {
		return mail_starttls_enable;
	}

	/**
	 * @param mail_starttls_enable the mail_starttls_enable to set
	 */
	public void setMail_starttls_enable(int mail_starttls_enable) {
		this.mail_starttls_enable = mail_starttls_enable;
	}

	/**
	 * @return the mail_smtp_port
	 */
	public int getMail_smtp_port() {
		return mail_smtp_port;
	}

	/**
	 * @param mail_smtp_port the mail_smtp_port to set
	 */
	public void setMail_smtp_port(int mail_smtp_port) {
		this.mail_smtp_port = mail_smtp_port;
	}

	/**
	 * @return the mail_user_name
	 */
	public String getMail_user_name() {
		return mail_user_name;
	}

	/**
	 * @param mail_user_name the mail_user_name to set
	 */
	public void setMail_user_name(String mail_user_name) {
		this.mail_user_name = mail_user_name;
	}

	/**
	 * @return the mail_password
	 */
	public String getMail_password() {
		return mail_password;
	}

	/**
	 * @param mail_password the mail_password to set
	 */
	public void setMail_password(String mail_password) {
		this.mail_password = mail_password;
	}

	/**
	 * @return the mail_from
	 */
	public String getMail_from() {
		return mail_from;
	}

	/**
	 * @param mail_from the mail_from to set
	 */
	public void setMail_from(String mail_from) {
		this.mail_from = mail_from;
	}

//	public static void main(String[] args) {
//		String smtpServer = "smtp.gmail.com";
//		String to = "trinh.nguyen@fssvn.com";
//		String from = "fssvn.corp@gmail.com";
////		String smtpServer = "mail.fssvn.com";
////		String to = "trinh.nguyen@fssvn.com";
////		String from = "trinh.nguyen@fssvn.com";
//		String subject = "Test mail";
//		String body = "Test using java to send mail.";
//		//send(smtpServer, to, from, subject, body);
//		System.out.println("Finish!");
//	}
}
