package easyon.alphacalcolutions.service;

import easyon.alphacalcolutions.data.DataFacade;
import easyon.alphacalcolutions.model.Task;
import easyon.alphacalcolutions.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class TaskService {

    private final DataFacade dataFacade;

    public TaskService(DataFacade dataFacade) {
        this.dataFacade = dataFacade;
    }

    public void createTask(Task task){
        dataFacade.createTask(task);
    }

    public ArrayList<Task> getTaskList(int projectId){
        ArrayList<Task> taskList  = dataFacade.getTaskList(projectId);
        for (Task task: taskList) {
            task.setTaskLeader(getTaskLeader(task.getTaskLeaderId()));
            task.setAssignedUsers(getAssignedUsersFromTask(task.getAssignedUserIds()));
        }
        return taskList;
    }

    public Task getTaskById(int taskId){
        return dataFacade.getTaskById(taskId);
    }

    public ArrayList<User> getAssignedUsersFromTask(int[] assignedUsers){
        return dataFacade.getUsersById(assignedUsers);
    }

    public User getTaskLeader(int taskLeaderId){
       return dataFacade.getUser(taskLeaderId);
    }



}
