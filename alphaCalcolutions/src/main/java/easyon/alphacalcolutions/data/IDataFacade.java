package easyon.alphacalcolutions.data;

import easyon.alphacalcolutions.model.UserTitle;
import easyon.alphacalcolutions.model.User;

import java.util.ArrayList;

public interface IDataFacade {

    //----------------------------- EMPLOYEE -------------------------------------


    public void createUser(User user);

    public ArrayList<User> getUserList();

    public User getUser(int employeeId);

    //----------------------------- EMPLOYEE TITLE -------------------------------------

    public ArrayList<UserTitle> getUserTitleList();

}
