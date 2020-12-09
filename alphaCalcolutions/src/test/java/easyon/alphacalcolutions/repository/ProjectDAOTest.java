package easyon.alphacalcolutions.repository;

import easyon.alphacalcolutions.model.Project;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProjectDAOTest extends AbstractDAOTest{
    private ProjectDAO projectDAO = new ProjectDAO(con);

    @Test
    void createProject() {
        Project project = new Project();
        project.setTitle("Project2");
        project.setProjectLeaderId(2);
        project.setAssignedUserIds(new int[]{1,2});
        projectDAO.createProject(project);
        Project actualProject = projectDAO.getProject(2);
        assertNotNull(actualProject);
        assertEquals(project, actualProject);
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
    void updateProject() {
        Project project = projectDAO.getProject(1);
        project.setTitle("Updated Project");
        project.setProjectLeaderId(3);
        project.setAssignedUserIds(new int[]{1,2});
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