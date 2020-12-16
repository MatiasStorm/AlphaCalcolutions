package easyon.alphacalcolutions.service;

import easyon.alphacalcolutions.data.DataFacade;
import easyon.alphacalcolutions.model.User;
import easyon.alphacalcolutions.model.UserTitle;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public User getUserById(int userId){
        return dataFacade.getUserById(userId);
    }

    public ArrayList<UserTitle> getUserTitleList(){
        return dataFacade.getUserTitleList();
    }

    public boolean updateUser(User user) {
        return dataFacade.updateUser(user);
    }

    public boolean deleteUser(int userId){
        return dataFacade.deleteUser(userId);
    }

    public List<User> searchUser(String search) {
        return dataFacade.getUserSearch(search);
    }

    public User login(String email, String pwd) {
        return null;
    }
}
