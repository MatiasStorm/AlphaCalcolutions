package easyon.alphacalcolutions.service;

import easyon.alphacalcolutions.data.DataFacade;
import easyon.alphacalcolutions.model.Task;
import easyon.alphacalcolutions.model.User;
import easyon.alphacalcolutions.repository.exception.CreateTaskHasDependencyException;
import easyon.alphacalcolutions.repository.exception.CreateUserHasTaskException;
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

    public boolean createTask(Task task){
        try {
            dataFacade.createTask(task);
            return true;
        }
        catch (CreateUserHasTaskException ex){
            return false;
        }
        catch (CreateTaskHasDependencyException ex){
            return false;
        }
    }

    public ArrayList<Task> getTaskList(int projectId){
        ArrayList<Task> taskList  = dataFacade.getTaskList(projectId);
        return taskList;
    }

    public Task getTaskById(int taskId){
        return dataFacade.getTaskById(taskId);
    }


    public boolean updateTask(Task task) {
        try {
            dataFacade.updateTask(task);
            return true;
        }
        catch (CreateUserHasTaskException ex){
            return false;
        }
        catch (CreateTaskHasDependencyException ex){
            return false;
        }
    }

    public void deleteTask(int taskId) {
        dataFacade.deleteTask(taskId);
    }


}
