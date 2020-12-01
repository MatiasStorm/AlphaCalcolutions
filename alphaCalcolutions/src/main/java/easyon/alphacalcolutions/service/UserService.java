package easyon.alphacalcolutions.service;

import easyon.alphacalcolutions.data.DataFacade;
import easyon.alphacalcolutions.model.User;
import easyon.alphacalcolutions.model.UserTitle;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {
    private final DataFacade dataFacade;

    public UserService(DataFacade dataFacade) {
        this.dataFacade = dataFacade;
    }

    public void createUser(User user){
        dataFacade.createUser(user);
    }

    public ArrayList<User> getUserList(){
        return dataFacade.getUserList();
    }

    public User getUser(int employeeId){
        return dataFacade.getUser(employeeId);
    }

    public ArrayList<UserTitle> getUserTitleList(){
        return dataFacade.getUserTitleList();
    }
}