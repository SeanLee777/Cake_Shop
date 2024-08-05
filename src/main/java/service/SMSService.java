

package service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class SMSService {


    private static final String T_MOBILE_DOMAIN = "tmomail.net";


    private static String SMTP_USERNAME;


    private static String SMTP_PASSWORD;


    private final Session emailSession;


    private static final Logger logger = LogManager.getLogger(SMSService.class);


    public SMSService() {
        if (!loadEmailCredentials()) {
            throw new IllegalStateException("Failed to load email credentials. SMS service cannot be initialized.");
        }
        this.emailSession = createEmailSession();
    }


    private boolean loadEmailCredentials() {
        Properties mailProps = new Properties();
        try (InputStream input = SMSService.class.getClassLoader().getResourceAsStream("mail.properties")) {
            if (input == null) {
                logger.error("Unable to find mail.properties");
                return false;
            }
            mailProps.load(input);
            SMTP_USERNAME = mailProps.getProperty("mail.smtp.username");
            SMTP_PASSWORD = mailProps.getProperty("mail.smtp.password");

            if (SMTP_USERNAME == null || SMTP_PASSWORD == null) {
                logger.error("Email credentials are missing in mail.properties");
                return false;
            }
            return true;
        } catch (IOException ex) {
            logger.error("Error reading mail.properties", ex);
            return false;
        }
    }


    private Session createEmailSession() {
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        return Session.getInstance(prop, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SMTP_USERNAME, SMTP_PASSWORD);
            }
        });
    }


    public void sendSMS(String phoneNumber, String messageText) throws MessagingException {
        sendSMSMessage(phoneNumber, messageText);
        logger.info("Preparing to send SMS to {}", phoneNumber);
    }


    private void sendSMSMessage(String phoneNumber, String messageText) throws MessagingException {
        String toAddress = phoneNumber + "@" + T_MOBILE_DOMAIN;
        Message message = new MimeMessage(emailSession);
        message.setFrom(new InternetAddress(SMTP_USERNAME)); // Sender's email
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress));
        message.setSubject("");
        message.setText(messageText);
        Transport.send(message);
        logger.info("SMS sent successfully to {}", phoneNumber);
    }
}
