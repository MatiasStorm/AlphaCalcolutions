package easyon.alphacalcolutions.service;

import easyon.alphacalcolutions.data.IDataFacade;
import easyon.alphacalcolutions.model.Project;
import easyon.alphacalcolutions.model.Task;
import easyon.alphacalcolutions.model.User;
import easyon.alphacalcolutions.model.UserTitle;
import easyon.alphacalcolutions.model.exception.CreateTaskHasDependencyException;
import easyon.alphacalcolutions.model.exception.CreateUserHasTaskException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


class ProjectServiceTest {

    private ProjectService projectService = new ProjectService(new StubDataFacade());

    @Test
    void getProjectList() {
        ArrayList<Project> projects = projectService.getProjectList();
        assertEquals(1, projects.size());
        assertEquals(11200, projects.get(0).getProjectCost());
    }
}


class StubDataFacade implements IDataFacade {

    @Override
    public ArrayList<Project> getProjectList() {
        ArrayList<Project> projects = new ArrayList<>();
        Project p = new Project();
        p.setProjectId(1);
        projects.add(p);
        return projects;
    }

    @Override
    public ArrayList<Task> getTaskList(int projectId) {
        ArrayList<Task> tasks = new ArrayList<>();
        User u = new User();
        u.setHourlySalary(100);

        Task t = new Task();
        t.setStartDate("2020-12-10");
        t.setEndDate("2020-12-15");
        t.setAssignedUsers(new ArrayList<>(){{add(u);}});
        tasks.add(t);

        t = new Task();
        t.setStartDate("2020-12-15");
        t.setEndDate("2020-12-21");
        t.setAssignedUsers(new ArrayList<>(){{add(u); add(u);}});
        tasks.add(t);
        return tasks;
    }

    @Override
    public void createUser(User user) {

    }

    @Override
    public ArrayList<User> getUserList() {
        return null;
    }

    @Override
    public User getUserById(int employeeId) {
        return null;
    }

    @Override
    public ArrayList<User> getUsersById(int[] userIds) {
        return null;
    }

    @Override
    public ArrayList<User> getUserSearch(String string) {
        return null;
    }

    @Override
    public ArrayList<UserTitle> getUserTitleList() {
        return null;
    }

    @Override
    public void createProject(Project project) {

    }


    @Override
    public Project getProject(int projectId) {
        return null;
    }

    @Override
    public void createTask(Task task) throws CreateUserHasTaskException, CreateTaskHasDependencyException {

    }

    @Override
    public Task getTaskById(int taskId) {
        return null;
    }

    @Override
    public void updateProject(Project project) {

    }

    @Override
    public void deleteProject(int projectId) {

    }
}