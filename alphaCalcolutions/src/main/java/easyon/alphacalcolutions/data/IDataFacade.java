package easyon.alphacalcolutions.data;

import easyon.alphacalcolutions.model.Project;
import easyon.alphacalcolutions.model.Task;
import easyon.alphacalcolutions.model.UserTitle;
import easyon.alphacalcolutions.model.User;
import easyon.alphacalcolutions.repository.exception.CreateTaskHasDependencyException;
import easyon.alphacalcolutions.repository.exception.CreateUserHasTaskException;

import java.util.ArrayList;
import java.util.List;

public interface IDataFacade {

    //----------------------------- USER -------------------------------------


    void createUser(User user);

    ArrayList<User> getUserList();

    User getUser(int employeeId);

    ArrayList<User> getUsersById(int[] userIds);

    //----------------------------- USER TITLE -------------------------------------

    ArrayList<UserTitle> getUserTitleList();

    //----------------------------- PROJECT -------------------------------------

    void createProject(Project project);

    ArrayList<Project> getProjectList();

    Project getProject(int projectId);

    //----------------------------- TASK -------------------------------------

    void createTask(Task task) throws CreateUserHasTaskException, CreateTaskHasDependencyException;
    ArrayList<Task> getTaskList(int projectId);
    Task getTaskById(int taskId);
}

