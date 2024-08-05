
package service;

import dao.UserDAO;
import model.User;

import javax.mail.MessagingException;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class NotificationService {


    private final UserDAO userDAO;
    private final EmailService emailService;

    private final SMSService smsService;
    private static final Logger logger = LogManager.getLogger(NotificationService.class);
    public NotificationService(UserDAO userDAO) {
        this.userDAO = userDAO;
        this.emailService = new EmailService();
        this.smsService = new SMSService();
    }

    public void notifyUsersAboutSurplusFood(String foodType, String zipcode) throws SQLException {
        try {
            List<User> users = userDAO.findUsersSubscribedTo(foodType, zipcode);
            for (User user : users) {
                if (user.isSubscribedToAlerts()) {
                    try {
                        emailService.sendEmail(user.getEmail(), "Surplus Food Available", "Surplus " + foodType + " available in your area (" + zipcode + ").");
                        logger.info("Email notification sent to {}", user.getEmail());

                        if (user.getPhoneNumber() != null && !user.getPhoneNumber().isEmpty()) {
                            smsService.sendSMS(user.getPhoneNumber(), "Surplus " + foodType + " available in your area (" + zipcode + ").");
                            logger.info("SMS notification sent to {}, Email: {}", user.getPhoneNumber(), user.getEmail());
                        }
                    } catch (MessagingException e) {
                        logger.error("Error sending notifications to user {}: {}", user.getEmail(), e.getMessage(), e);
                    }
                }
            }
        } catch (SQLException e) {
            logger.error("SQLException in notifyUsersAboutSurplusFood: {}", e.getMessage(), e);
            throw e;
        }
    }

}
