package easyon.alphacalcolutions.repository;

import easyon.alphacalcolutions.model.Project;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@Sql(value= {"classpath:alpha_calcolutions.sql"})
class ProjectDAOTest {
    private ProjectDAO projectDAO = new ProjectDAO();

//    @Test
//    void createProject() {
//    }

    @Test
    void getProjectList() {
        List<Project> projects = projectDAO.getProjectList();
        assertEquals(1, projects.size());
    }

//    @Test
//    void getProject() {
//    }
}