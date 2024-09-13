package email;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import jakarta.activation.*;
import java.util.Properties;

public class EmailSender {

	public static void sendEmail(String recipient, String subject, String body, String attachmentPath,
			String senderEmail, String password) {
		String host = "smtp.gmail.com";
		int port = 587;

		Properties properties = new Properties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");

		Session session = Session.getInstance(properties, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(senderEmail, password);
			}
		});

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(senderEmail));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			message.setSubject(subject);

			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText(body);

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);

			if (attachmentPath != null && !attachmentPath.isEmpty()) {
				MimeBodyPart attachPart = new MimeBodyPart();
				DataSource source = new FileDataSource(attachmentPath);
				attachPart.setDataHandler(new DataHandler(source));
				attachPart.setFileName(source.getName());
				multipart.addBodyPart(attachPart);
			}

			message.setContent(multipart);

			Transport.send(message);
			System.out.println("Email with attachment sent successfully to: " + recipient);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
