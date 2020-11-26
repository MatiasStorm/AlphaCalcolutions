package easyon.alphacalcolutions.data;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

class DBManagerTest {

    @Test
    void getConnection() {
        Connection con = DBManager.getConnection();
        assertNotNull(con);
    }
}