package easyon.alphacalcolutions.data;

import easyon.alphacalcolutions.model.UserTitle;
import easyon.alphacalcolutions.model.User;
import easyon.alphacalcolutions.repository.UserDAO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class DataFacade implements IDataFacade{

    private static final UserDAO USER_DAO = new UserDAO();


    //----------------------------- USER -------------------------------------

    public void createUser(User user){
        USER_DAO.createUser(user);
    }

    public ArrayList<User> getUserList(){
       return USER_DAO.getUserList();
    }

    public User getUser(int userId){
        return USER_DAO.getUser(userId);
    }

    //----------------------------- USER TITLE -------------------------------------

    public ArrayList<UserTitle> getUserTitleList(){
        return USER_DAO.getUserTitleList();
    }



}
