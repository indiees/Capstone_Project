package dao.util;

import java.sql.Connection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DatabaseUtilsTest {
    @Test
    void testDatabaseConnection() throws Exception {
        Connection testConnect = DatabaseUtils.connectToDatabase();
        Assertions.assertNotNull(testConnect);
    }
}