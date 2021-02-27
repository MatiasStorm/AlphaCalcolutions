package easyon.alphacalcolutions.data;

import easyon.alphacalcolutions.model.Project;
import easyon.alphacalcolutions.model.Task;
import easyon.alphacalcolutions.model.UserTitle;
import easyon.alphacalcolutions.model.User;
import easyon.alphacalcolutions.data.repository.ProjectDAO;
import easyon.alphacalcolutions.data.repository.TaskDAO;
import easyon.alphacalcolutions.data.repository.UserDAO;
import easyon.alphacalcolutions.model.exception.CreateTaskHasDependencyException;
import easyon.alphacalcolutions.model.exception.CreateUserHasTaskException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class DataFacade implements IDataFacade {
    private final UserDAO USER_DAO = new UserDAO(DBManager.getConnection());
    private final ProjectDAO PROJECT_DAO = new ProjectDAO(DBManager.getConnection());
    private final TaskDAO TASK_DAO = new TaskDAO(DBManager.getConnection());


    //----------------------------- USER -------------------------------------

   public User login(String username, String password){
      return USER_DAO.login(username, password);
   }

    public void createUser(User user) {
        USER_DAO.createUser(user);
    }

    public ArrayList<User> getUserList() {
        return USER_DAO.getUserList();
    }

    public User getUserById(int userId) {
        return USER_DAO.getUserById(userId);
    }

    public ArrayList<User> getUsersByIds(int[] userIds) {
        return USER_DAO.getUsersByIds(userIds);
    }

    public boolean updateUser(User user) {
        return USER_DAO.updateUser(user);
    }

    public boolean deleteUser(int userId){
        return USER_DAO.deleteUser(userId);
    }


    //----------------------------- USER TITLE -------------------------------------

    public ArrayList<UserTitle> getUserTitleList() {
        return USER_DAO.getUserTitleList();
    }

    //----------------------------- PROJECT -------------------------------------

    public void createProject(Project project) {
        PROJECT_DAO.createProject(project);
    }

    public ArrayList<Project> getProjectList() {
        ArrayList<Project> projects = PROJECT_DAO.getProjectList();
        return projects;
    }


    public Project getProject(int projectId) {
        return PROJECT_DAO.getProject(projectId);
    }




    //----------------------------- TASK -------------------------------------

    public void createTask(Task task) throws CreateUserHasTaskException, CreateTaskHasDependencyException {
        TASK_DAO.createTask(task);
    }

    public ArrayList<Task> getTaskList(int projectId) {
        ArrayList<Task> taskList = TASK_DAO.getTaskList(projectId);
        for (Task task : taskList) {
            task.setTaskLeader(getUserById(task.getTaskLeaderId()));
            task.setAssignedUsers(getUsersByIds(task.getAssignedUserIds()));
        }
        return taskList;
    }


    public Task getTaskById(int taskId) {
        return TASK_DAO.getTaskById(taskId);
    }


    public void updateProject(Project project) {
        PROJECT_DAO.updateProject(project);
    }

    public void deleteProject(int projectId) {
        PROJECT_DAO.deleteProject(projectId);
    }

    public void updateTask(Task task) throws CreateUserHasTaskException, CreateTaskHasDependencyException {
        TASK_DAO.updateTask(task);
    }

    public void deleteTask(int taskId) {
        TASK_DAO.deleteTask(taskId);
    }

    public ArrayList<User> getUserSearch(String search) {
        return USER_DAO.getUserSearch(search);
    }
}
