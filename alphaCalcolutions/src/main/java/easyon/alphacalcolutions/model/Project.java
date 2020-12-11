package easyon.alphacalcolutions.model;

import easyon.alphacalcolutions.util.DateUtil;

import java.time.LocalDate;
import java.util.Arrays;

public class Project{
    private int projectId;
    private String title;
    private int projectLeaderId;
    private int[] assignedUserIds;
    private LocalDate startDate;
    private LocalDate endDate;
    private int projectCost;

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

    public int[] getAssignedUserIds() {
        return assignedUserIds;
    }

    public void setAssignedUserIds(int[] assignedUserIds) {
        this.assignedUserIds = assignedUserIds;
    }

    public void setAssignedUserIds(String[] assignedUserIdStrings) {
        this.assignedUserIds = new int[assignedUserIdStrings.length];
        int id;
        for(int i = 0; i < assignedUserIdStrings.length; i++){
            id = Integer.parseInt(assignedUserIdStrings[i]);
            this.assignedUserIds[i] = id;
        }
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate == null ? null : LocalDate.parse(startDate);
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate == null ? null : LocalDate.parse(endDate);
    }

    public int getProjectCost() {
        return projectCost;
    }

    public void setProjectCost(int projectCost) {
        this.projectCost = projectCost;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project p = (Project) o;
        return projectId == p.getProjectId()
                && title.equals(p.getTitle())
                && projectLeaderId == p.getProjectLeaderId()
                && Arrays.equals(assignedUserIds, p.getAssignedUserIds())
                && compareEndDate(p)
                && compareStartDate(p);
    }

    private boolean compareStartDate(Project p){
        if(startDate == null) return p.getStartDate() == null;
        return startDate.equals(p.getStartDate());
    }

    private boolean compareEndDate(Project p){
        if(endDate == null) return p.getEndDate() == null;
        return endDate.equals(p.getEndDate());
    }

    public int getProjectDuration() {
        if (startDate == null || endDate == null) return 0;
        return DateUtil.businessDaysBetween(startDate, endDate);
    }

}
