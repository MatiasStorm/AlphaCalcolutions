package easyon.alphacalcolutions.repository;

import easyon.alphacalcolutions.model.Project;
import org.junit.jupiter.api.Test;
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
        assertEquals(project, actualProject);
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

    @Test
    void updateProject() throws ParseException {
        Project project = new Project();
        project.setProjectId(1);
        project.setTitle("Updated Project");
        project.setProjectLeaderId(2);
        project.setAssignedUserIds(new int[]{1,2});
        project.setStartDate("2020-12-12");
        project.setEndDate("2020-12-21");
        projectDAO.updateProject(project);
        Project actualProject = projectDAO.getProject(1);
        assertEquals(project, actualProject);
    }

    @Test
    void deleteProject() {
        projectDAO.deleteProject(1);
        Project p = projectDAO.getProject(1);
        assertNull(p);
    }
}