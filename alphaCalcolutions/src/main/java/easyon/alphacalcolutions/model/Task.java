package easyon.alphacalcolutions.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task {
    private String title;
    private int taskLeaderId;
    private int[] assignedUserIds;
    private Date startDate;
    private Date endDate;
    private int[] taskDependencyIds;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTaskLeaderId() {
        return taskLeaderId;
    }

    public void setTaskLeaderId(int taskLeaderId) {
        this.taskLeaderId = taskLeaderId;
    }

    public int[] getAssignedUserIds() {
        return assignedUserIds;
    }

//    public void setAssignedUserIds(int[] assignedUserIds) {
//        this.assignedUserIds = assignedUserIds;
//    }

    public void setAssignedUserIds(String[] assignedUserIdStrings) {
        this.assignedUserIds = new int[assignedUserIdStrings.length];
        int id;
        for(int i = 0; i < assignedUserIdStrings.length; i++){
            id = Integer.parseInt(assignedUserIdStrings[i]);
            this.assignedUserIds[i] = id;
        }
    }

    public int[] getTaskDependencyIds() {
        return taskDependencyIds;
    }

    public void setTaskDependencyIds(int[] taskDependencyIds) {
        this.taskDependencyIds = taskDependencyIds;
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
