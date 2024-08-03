package util;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link DatabaseUtil}.
 * Has tests to ensure that the Database Utility is functioning correctly,
 * primarily focusing on the connection to the database.
 */
class DatabaseUtilTest {

    /**
     * Validates that the database connection is not null and is valid.
     */
    @Test
    void whenGetConnection_thenConnectionIsEstablished() {
        DatabaseUtil dbUtil = DatabaseUtil.getInstance();

        try (Connection conn = dbUtil.getConnection()) {
            assertNotNull(conn, "Connection should not be null");
            assertTrue(conn.isValid(1), "Connection should be valid");
        } catch (Exception e) {
            fail("Exception should not be thrown: " + e.getMessage());
        }
    }
}
