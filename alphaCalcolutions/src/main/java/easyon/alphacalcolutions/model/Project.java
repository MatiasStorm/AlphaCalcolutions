package easyon.alphacalcolutions.model;

import java.util.ArrayList;
import java.util.Date;

public class Project {

    private String title;
    private String projectLeader;
    private ArrayList<User> assignedUsers;
    private Date deadline;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProjectLeader() {
        return projectLeader;
    }

    public void setProjectLeader(String projectLeader) {
        this.projectLeader = projectLeader;
    }

    public ArrayList<User> getAssignedUsers() {
        return assignedUsers;
    }

    public void setAssignedUsers(ArrayList<User> assignedUsers) {
        this.assignedUsers = assignedUsers;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }
}
