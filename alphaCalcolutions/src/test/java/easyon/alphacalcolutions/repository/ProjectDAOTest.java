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

class ProjectDAOTest extends AbstractDAOTest{
    private ProjectDAO projectDAO = new ProjectDAO(con);

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