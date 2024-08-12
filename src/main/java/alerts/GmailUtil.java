package alerts;

import java.util.Properties;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message;
import javax.mail.Address;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GmailUtil {
	
	private static final Logger log = LoggerFactory.getLogger(GmailUtil.class);
	
	public GmailUtil() {
		
	}

	/**
	 * Send an email to a recipient from the specified Gmail account.
	 * 
	 * @param to the recipient's address
	 * @param from the Gmail account to send from
	 * @param fromPassword the password to the Gmail account to send from
	 * @param subject the email's subject
	 * @param body the body of the email
	 * @param isBodyHTML a flag indicating if the body is HTML
	 * 
	 * @throws MessagingException if something goes wrong
	 */
	public void sendEmail(String to, String from, String fromPassword,
            String subject, String body, boolean isBodyHTML)
            throws MessagingException {
        
        // get a mail session
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtps");
        props.put("mail.smtps.host", "smtp.gmail.com");
        props.put("mail.smtps.port", 465);
        props.put("mail.smtps.auth", "true");
        props.put("mail.smtps.quitwait", "false");
        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);

        // create a message
        Message message = new MimeMessage(session);
        message.setSubject(subject);
        if (isBodyHTML) {
            message.setContent(body, "text/html");
        } else {
            message.setText(body);
        }

        // address the message
        Address toAddress = new InternetAddress(to);
        Address fromAddress = new InternetAddress(from);
        message.setFrom(fromAddress);
        message.setRecipient(Message.RecipientType.TO, toAddress);

        // send the message
        Transport transport = session.getTransport();
        transport.connect(from, fromPassword);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }

}
