package easyon.alphacalcolutions.data;

import easyon.alphacalcolutions.model.Project;
import easyon.alphacalcolutions.model.UserTitle;
import easyon.alphacalcolutions.model.User;

import java.util.ArrayList;

public interface IDataFacade {

    //----------------------------- USER -------------------------------------


    public void createUser(User user);

    public ArrayList<User> getUserList();

    public User getUser(int employeeId);

    //----------------------------- USER TITLE -------------------------------------

    public ArrayList<UserTitle> getUserTitleList();

    //----------------------------- PROJECT -------------------------------------

    public void createProject(Project project);

    public ArrayList<Project> getProjectList();

    public Project getProject(int projectId);
}

