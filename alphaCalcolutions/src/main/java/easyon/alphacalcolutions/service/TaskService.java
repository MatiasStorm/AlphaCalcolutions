package easyon.alphacalcolutions.service;

import easyon.alphacalcolutions.data.DataFacade;
import easyon.alphacalcolutions.model.Task;
import easyon.alphacalcolutions.data.repository.exception.CreateTaskHasDependencyException;
import easyon.alphacalcolutions.data.repository.exception.CreateUserHasTaskException;
import easyon.alphacalcolutions.model.TaskList;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


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

    public TaskList getTaskList(int projectId){
        ArrayList<Task> tasks  = dataFacade.getTaskList(projectId);
        TaskList taskList = new TaskList(tasks);
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
