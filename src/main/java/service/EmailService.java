

package service;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class EmailService {

    /**
     * Logger for logging email service related information and errors.
     */
    private static final Logger logger = LogManager.getLogger(EmailService.class);

    /**
     * Session object for SMTP session.
     */
    private final Session emailSession;

    /**
     * The email address from which the emails are sent.
     */
    private static String EMAIL_FROM;

    /**
     * The password for the email account from which the emails are sent.
     */
    private static String PASSWORD;

    /**
     * Constructs a new EmailService, loading email credentials and creating an email session.
     *
     * @throws IllegalStateException if email credentials cannot be loaded.
     */
    public EmailService() {
        if (!loadEmailCredentials()) {
            throw new IllegalStateException("Failed to load email credentials. Email service cannot be initialized.");
        }
        this.emailSession = createEmailSession();
    }

    /**
     * Loads email credentials from a properties file.
     *
     * @return true if credentials are successfully loaded, false otherwise.
     */
    private boolean loadEmailCredentials() {
        Properties mailProps = new Properties();
        try (InputStream input = EmailService.class.getClassLoader().getResourceAsStream("mail.properties")) {
            if (input == null) {
                logger.error("Unable to find mail.properties");
                return false;
            }
            mailProps.load(input);
            EMAIL_FROM = mailProps.getProperty("mail.smtp.username");
            PASSWORD = mailProps.getProperty("mail.smtp.password");

            if (EMAIL_FROM == null || PASSWORD == null) {
                logger.error("Email credentials are missing in mail.properties");
                return false;
            }
            return true;
        } catch (IOException ex) {
            logger.error("Error reading mail.properties", ex);
            return false;
        }
    }

    /**
     * Creates a new email session for sending emails.
     *
     * @return A new Session object configured for SMTP.
     */
    private Session createEmailSession() {
        Properties gmailProps = getGmailProperties();
        return Session.getInstance(gmailProps, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL_FROM, PASSWORD);
            }
        });
    }

    /**
     * Gets SMTP properties for email session.
     *
     * @return A Properties object with SMTP settings.
     */
    private Properties getGmailProperties() {
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        return prop;
    }

    /**
     * Sends an email to the specified recipient.
     *
     * @param recipient   The recipient's email address.
     * @param subject     The subject of the email.
     * @param messageText The text of the email message.
     */
    public void sendEmail(String recipient, String subject, String messageText) {
        try {
            Message message = new MimeMessage(emailSession);
            message.setFrom(new InternetAddress(EMAIL_FROM));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setText(messageText);

            Transport.send(message);
            logger.info("Email sent successfully to {}", recipient);
        } catch (MessagingException e) {
            logger.error("Error sending email to {}: {}", recipient, e.getMessage(), e);
        }
    }
}