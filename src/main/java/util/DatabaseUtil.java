/**
 * Java Final Group 11 Project
 * Class: CST8319
 * Author: Shanghao Li
 * Student ID: 040903008
 */

package util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Utility class for managing database connections.
 * Implements Singleton pattern to ensure only one instance of the database connection is used throughout the application.
 */
public class DatabaseUtil {
    private static volatile DatabaseUtil instance;
    private Connection connection;

    private static final Logger logger = LogManager.getLogger(DatabaseUtil.class);

    /**
     * URL of the database.
     */
    private final String url;

    /**
     * Username for the database connection.
     */
    private final String username;

    /**
     * Password for the database connection.
     */
    private final String password;

    /**
     * Private constructor to initialize database connection parameters.
     * Reads database configuration from properties file.
     * @throws RuntimeException if unable to read the properties file or establish a database connection.
     */
    private DatabaseUtil() {
        try {
            Properties properties = new Properties();
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("db.properties");
            if (inputStream != null) {
                properties.load(inputStream);
                String host = properties.getProperty("db.host");
                String port = properties.getProperty("db.port");
                String dbName = properties.getProperty("db.name");
                url = "jdbc:mysql://" + host + ":" + port + "/" + dbName;
                username = properties.getProperty("db.username");
                password = properties.getProperty("db.password");
            } else {
                logger.error("db.properties file not found");
                throw new RuntimeException("db.properties file not found");
            }

            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(url, username, password);
            logger.info("Database connection established");
        } catch (ClassNotFoundException | SQLException | IOException e) {
            logger.error("Error connecting to the database", e);
            throw new RuntimeException("Error connecting to the database", e);
        }
    }

    /**
     * Gets the singleton instance of DatabaseUtil.
     * @return The singleton instance.
     */
    public static DatabaseUtil getInstance() {
        if (instance == null) {
            synchronized (DatabaseUtil.class) {
                if (instance == null) {
                    instance = new DatabaseUtil();
                }
            }
        }
        return instance;
    }

    /**
     * Provides a database connection.
     * Establishes a new connection if the existing one is closed or null.
     * @return A valid database Connection object.
     * @throws RuntimeException if unable to establish a database connection.
     */
    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(url, username, password);
                logger.info("Database connection (re)established");
            }
        } catch (SQLException e) {
            logger.error("Error re-establishing the database connection", e);
            throw new RuntimeException("Error re-establishing the database connection", e);
        }
        return connection;
    }

}
