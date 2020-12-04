package easyon.alphacalcolutions.data;

import easyon.alphacalcolutions.model.Project;
import easyon.alphacalcolutions.model.Task;
import easyon.alphacalcolutions.model.UserTitle;
import easyon.alphacalcolutions.model.User;

import java.util.ArrayList;

public interface IDataFacade {

    //----------------------------- USER -------------------------------------


    void createUser(User user);

    ArrayList<User> getUserList();

    User getUser(int employeeId);

    ArrayList<User> getUsersById(ArrayList<Integer> userIds);

    //----------------------------- USER TITLE -------------------------------------

    ArrayList<UserTitle> getUserTitleList();

    //----------------------------- PROJECT -------------------------------------

    void createProject(Project project);

    ArrayList<Project> getProjectList();

    Project getProject(int projectId);

    //----------------------------- TASK -------------------------------------

    void createTask(Task task);
    ArrayList<Task> getTaskList();
}

