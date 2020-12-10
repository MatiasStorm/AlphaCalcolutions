package easyon.alphacalcolutions.data;

import easyon.alphacalcolutions.model.Project;
import easyon.alphacalcolutions.model.Task;
import easyon.alphacalcolutions.model.UserTitle;
import easyon.alphacalcolutions.model.User;
import easyon.alphacalcolutions.data.repository.ProjectDAO;
import easyon.alphacalcolutions.data.repository.TaskDAO;
import easyon.alphacalcolutions.data.repository.UserDAO;
import easyon.alphacalcolutions.data.repository.exception.CreateTaskHasDependencyException;
import easyon.alphacalcolutions.data.repository.exception.CreateUserHasTaskException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Predicate;
import java.util.stream.Stream;

@Component
public class DataFacade implements IDataFacade {

    private final UserDAO USER_DAO = new UserDAO(DBManager.getConnection());
    private final ProjectDAO PROJECT_DAO = new ProjectDAO(DBManager.getConnection());
    private final TaskDAO TASK_DAO = new TaskDAO(DBManager.getConnection());


    //----------------------------- USER -------------------------------------

    public void createUser(User user) {
        USER_DAO.createUser(user);
    }

    public ArrayList<User> getUserList() {
        return USER_DAO.getUserList();
    }

    public User getUserById(int userId) {
        return USER_DAO.getUserById(userId);
    }

    public ArrayList<User> getUsersById(int[] userIds) {
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
        for (Project project : projects) {
            project.setProjectCost(getProjectCost(project.getProjectId()));
            project.setProjectDuration(getProjectDuration(project.getProjectId()));
        }
        return projects;
    }


    public Project getProject(int projectId) {
        return PROJECT_DAO.getProject(projectId);
    }

    public int getProjectCost(int projectId) {
        int projectTotalCost = 0;

        for (Task task : getTaskList(projectId)) {
            LocalDate startDate = task.getStartDate();
            LocalDate endDate = task.getEndDate();

            int businessDays = calcBusinessDays(startDate, endDate);

            int totalHoursWorked = businessDays * 8;

            System.out.println(businessDays);

            for (User user : task.getAssignedUsers()) {
                int hourlySalary = user.getHourlySalary();
                projectTotalCost += totalHoursWorked * hourlySalary;
            }
        }
        return projectTotalCost;
    }

    public int calcBusinessDays(LocalDate startDate, LocalDate endDate){
        int daysWorked = (int) ChronoUnit.DAYS.between(startDate, endDate) +1;

        Predicate<LocalDate> isWeekend = date -> date.getDayOfWeek() == DayOfWeek.SATURDAY
                || date.getDayOfWeek() == DayOfWeek.SUNDAY;

        long businessDays = Stream.iterate(startDate, date -> date.plusDays(1)).limit(daysWorked)
                .filter(isWeekend.negate()).count();
        return (int) businessDays;
    }

    public int getProjectDuration(int projectId) { // Flyt til project model
        Project project = getProject(projectId);
        if (project.getStartDate() == null || project.getEndDate() == null) return 0;
        LocalDate startDate = project.getStartDate();
        LocalDate endDate = project.getEndDate();
        return calcBusinessDays(startDate, endDate);
    }

    public HashMap<String, Integer> getTitleHours(int projectId) {

        ArrayList<Task> tasks = getTaskList(projectId);

        HashMap<String, Integer> titleHours = new HashMap<>();

        for (Task task : tasks) {
            for (User user : task.getAssignedUsers()) {
                if (!titleHours.containsKey(user.getTitle().getUserTitle())){
                    titleHours.put(user.getTitle().getUserTitle(), 0);
                }
                LocalDate startDate = task.getStartDate();
                LocalDate endDate = task.getEndDate();
                int oldValue = titleHours.get(user.getTitle().getUserTitle());
                int hours = (int) ChronoUnit.DAYS.between(startDate, endDate) * 8;
                titleHours.replace(user.getTitle().getUserTitle(), oldValue + hours);

            }
        }
        return titleHours;
    }


    public HashMap<User, Integer> getUserHours(int projectId) {
        Project project = getProject(projectId);
        ArrayList<User> users = USER_DAO.getUsersByIds(project.getAssignedUserIds());
        ArrayList<Task> tasks = getTaskList(projectId);
        HashMap<User, Integer> userHours = new HashMap<>();

        for (User user : users){
            userHours.put(user, 0);
        }

        for (Task task : tasks) {
            for (User user : task.getAssignedUsers()) {
                LocalDate startDate = task.getStartDate();
                LocalDate endDate = task.getEndDate();
                int oldValue = userHours.get(user);
                int hours = (int) ChronoUnit.DAYS.between(startDate, endDate) * 8;
                userHours.replace(user, oldValue + hours);
            }
        }
        return userHours;
    }

    //----------------------------- TASK -------------------------------------

    public void createTask(Task task) throws CreateUserHasTaskException, CreateTaskHasDependencyException {
        TASK_DAO.createTask(task);
    }

    public ArrayList<Task> getTaskList(int projectId) {

        ArrayList<Task> taskList = TASK_DAO.getTaskList(projectId);
        for (Task task : taskList) {
            task.setTaskLeader(getUserById(task.getTaskLeaderId()));
            task.setAssignedUsers(getUsersById(task.getAssignedUserIds()));
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
