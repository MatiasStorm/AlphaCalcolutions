/* Matias Storm */
package easyon.alphacalcolutions.data.repository;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class AbstractDAOTest {
    protected static Connection con;
    @BeforeAll
    static void setupAll() throws SQLException{
        createConnectionToH2();
    }

    private static void createConnectionToH2() throws SQLException{
        String dbUrl = "jdbc:h2:~/alpha_calcolutions";
        String user = "sa";
        String pass = "";
        con = DriverManager.getConnection(dbUrl, user, pass);
    }

    @BeforeEach
    void setup() throws FileNotFoundException {
        this.runSqlScript();
    }

    public void runSqlScript() throws FileNotFoundException {
        ScriptRunner sr = new ScriptRunner(con);
        sr.setLogWriter(null);
        Reader reader = new BufferedReader(new FileReader("src/test/resources/alpha_calcolutions.sql"));
        sr.runScript(reader);
    }

    @AfterAll
    static void tearDown() throws SQLException {
        con.close();
    }
}
