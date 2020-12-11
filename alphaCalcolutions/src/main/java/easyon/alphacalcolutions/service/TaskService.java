package easyon.alphacalcolutions.service;

import easyon.alphacalcolutions.data.DataFacade;
import easyon.alphacalcolutions.model.Task;
import easyon.alphacalcolutions.model.exception.CreateTaskHasDependencyException;
import easyon.alphacalcolutions.model.exception.CreateUserHasTaskException;
import easyon.alphacalcolutions.model.TaskList;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class TaskService {

    private final DataFacade dataFacade;

    public TaskService(DataFacade dataFacade) {
        this.dataFacade = dataFacade;
    }

    public void createTask(Task task) throws CreateUserHasTaskException, CreateTaskHasDependencyException {
        dataFacade.createTask(task);
    }

    public TaskList getTaskList(int projectId){
        ArrayList<Task> tasks  = dataFacade.getTaskList(projectId);
        TaskList taskList = new TaskList(tasks);
        return taskList;
    }

    public Task getTaskById(int taskId){
        return dataFacade.getTaskById(taskId);
    }


    public void updateTask(Task task) throws CreateUserHasTaskException, CreateTaskHasDependencyException {
        dataFacade.updateTask(task);
    }

    public void deleteTask(int taskId) {
        dataFacade.deleteTask(taskId);
    }


}
