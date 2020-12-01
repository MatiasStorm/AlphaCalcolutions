package easyon.alphacalcolutions.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Project {

    private int projectId;
    private String title;
    private int projectLeaderId;
//    private ArrayList<User> assignedUsers;
    private int assignedUsers;
    private Date startDate;
    private Date endDate;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getProjectLeaderId() {
        return projectLeaderId;
    }

    public void setProjectLeaderId(int projectLeaderId) {
        this.projectLeaderId = projectLeaderId;
    }

//    public ArrayList<User> getAssignedUsers() {
//        return assignedUsers;
//    }
//
//    public void setAssignedUsers(ArrayList<User> assignedUsers) {
//        this.assignedUsers = assignedUsers;
//    }


    public int getAssignedUsers() {
        return assignedUsers;
    }

    public void setAssignedUsers(int assignedUsers) {
        this.assignedUsers = assignedUsers;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) throws ParseException {
        this.startDate = dateFormat.parse(startDate);
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) throws ParseException {
        this.endDate = dateFormat.parse(endDate);
    }
}
