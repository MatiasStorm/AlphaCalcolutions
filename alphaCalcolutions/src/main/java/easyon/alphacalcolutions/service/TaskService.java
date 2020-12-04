package easyon.alphacalcolutions.service;

import easyon.alphacalcolutions.data.DataFacade;
import easyon.alphacalcolutions.model.Task;
import easyon.alphacalcolutions.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class TaskService {

    private final DataFacade dataFacade;

    public TaskService(DataFacade dataFacade) {
        this.dataFacade = dataFacade;
    }

    public void createTask(Task task){
        dataFacade.createTask(task);
    }
    public ArrayList<Task> getTaskList(){
        return dataFacade.getTaskList();
    }

    public ArrayList<User> getAssignedUsersFromTask(int taskId){

    }

}
