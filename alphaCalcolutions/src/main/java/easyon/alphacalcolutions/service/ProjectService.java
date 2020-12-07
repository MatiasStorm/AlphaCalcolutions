package easyon.alphacalcolutions.service;

import easyon.alphacalcolutions.data.DataFacade;
import easyon.alphacalcolutions.model.Project;
import easyon.alphacalcolutions.model.Task;
import easyon.alphacalcolutions.model.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

@Service
public class ProjectService {

    private final DataFacade dataFacade;

    public ProjectService(DataFacade dataFacade) {
        this.dataFacade = dataFacade;
    }

    public void createProject(Project project){
        dataFacade.createProject(project);
    }

    public ArrayList<Project> getProjectList() {return dataFacade.getProjectList();}

    public Project getProject(int projectId) {return dataFacade.getProject(projectId);}

    public ArrayList<User> getAssignedUsersFromProject(int projectId){
        int[] assignedUsers = dataFacade.getProject(projectId).getAssignedUserIds();
        return dataFacade.getUsersById(assignedUsers);
    }

    public void updateProject(Project project) {
        dataFacade.updateProject(project);
    }

    public void deleteProject(int projectId) {
        dataFacade.deleteProject(projectId);
    }

    public int getProjectCost(int projectId){
        int projectTotalCost = 0;

        for(Task task : dataFacade.getTaskList(projectId)){
            LocalDate startDate = task.getStartDate();
            LocalDate endDate = task.getEndDate();
            int daysWorked = (int) ChronoUnit.DAYS.between(startDate, endDate);
            int totalHoursWorked = daysWorked * 8;
            for (User user : task.getAssignedUsers()){
                int hourlySalary = user.getHourlySalary();
                projectTotalCost += totalHoursWorked * hourlySalary;
            }
        }
        return projectTotalCost;
    }
}
