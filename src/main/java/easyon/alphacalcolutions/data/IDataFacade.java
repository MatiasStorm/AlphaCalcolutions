package easyon.alphacalcolutions.data;

import easyon.alphacalcolutions.model.Project;
import easyon.alphacalcolutions.model.Task;
import easyon.alphacalcolutions.model.UserTitle;
import easyon.alphacalcolutions.model.User;
import easyon.alphacalcolutions.model.exception.CreateTaskHasDependencyException;
import easyon.alphacalcolutions.model.exception.CreateUserHasTaskException;

import java.util.ArrayList;

public interface IDataFacade {

    //----------------------------- USER -------------------------------------


    void createUser(User user);

    ArrayList<User> getUserList();

    User getUserById(int userId);

    ArrayList<User> getUsersByIds(int[] userIds);

    ArrayList<User> getUserSearch(String string);

    //----------------------------- USER TITLE -------------------------------------

    ArrayList<UserTitle> getUserTitleList();

    //----------------------------- PROJECT -------------------------------------

    void createProject(Project project);

    ArrayList<Project> getProjectList();

    Project getProject(int projectId);

    void updateProject(Project project);

    void deleteProject(int projectId);

    //----------------------------- TASK -------------------------------------

    void createTask(Task task) throws CreateUserHasTaskException, CreateTaskHasDependencyException;

    ArrayList<Task> getTaskList(int projectId);

    Task getTaskById(int taskId);
}

