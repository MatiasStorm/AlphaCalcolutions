package easyon.alphacalcolutions.repository;

import easyon.alphacalcolutions.model.Project;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.test.context.jdbc.Sql;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProjectDAOTest {
    private static Connection con;
    private ProjectDAO projectDAO;

    @BeforeAll
    static void setupAll() throws SQLException, ClassNotFoundException {
        String jdbcDriver = "org.h2.Driver";
        Class.forName(jdbcDriver);
        String dbUrl = "jdbc:h2:~/alpha_calcolutions";
        String user = "sa";
        String pass = "";
        con = DriverManager.getConnection(dbUrl, user, pass);
    }

    @BeforeEach
    void setup() throws FileNotFoundException {
        ScriptRunner sr = new ScriptRunner(con);
        sr.setLogWriter(null);
        Reader reader = new BufferedReader(new FileReader("src/test/resources/alpha_calcolutions.sql"));
        sr.runScript(reader);
        projectDAO = new ProjectDAO(con);
    }

    @AfterAll
    static void tearDown() throws SQLException {
        con.close();
    }




    @Test
    void createProject() throws ParseException {
        Project project = new Project();
        project.setTitle("Project2");
        project.setProjectLeaderId(2);
        project.setAssignedUserIds(new int[]{1,2});
        project.setStartDate("2020-12-12");
        project.setEndDate("2020-12-21");
        projectDAO.createProject(project);
        Project actualProject = projectDAO.getProject(2);
        assertNotNull(actualProject);
        assertEquals(project.getAssignedUserIds().length, actualProject.getAssignedUserIds().length);
    }

    @Test
    void getProjectList() {
        List<Project> projects = projectDAO.getProjectList();
        assertEquals(1, projects.size());
    }

    @Test
    void getProject() {
        Project project = projectDAO.getProject(1);
        assertNotNull(project);
    }
}