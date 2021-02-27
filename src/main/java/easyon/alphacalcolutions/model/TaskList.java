package easyon.alphacalcolutions.model;

import easyon.alphacalcolutions.util.DateUtil;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskList {
    private ArrayList<Task> tasks;
    private final int workHoursPerDay = 8;
    private HashMap<UserTitle, Integer> titleHours = null;
    private HashMap<User, Integer> userHours = null;
    private Integer grandTotalHours = null;

    public TaskList(ArrayList<Task> tasks){
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks(){
        return tasks;
    }

    public HashMap<UserTitle, Integer> getTitleHours() {
        if(titleHours != null){
            return titleHours;
        }
        titleHours = new HashMap<>();

        for (Task task : tasks) {
            for (User user : task.getAssignedUsers()) {
                if (!titleHours.containsKey(user.getTitle())){
                    titleHours.put(user.getTitle(), 0);
                }
                int oldValue = titleHours.get(user.getTitle());
                int hours = DateUtil.daysBetween(task.getStartDate(), task.getEndDate()) * workHoursPerDay;
                titleHours.replace(user.getTitle(), oldValue + hours);
            }
        }
        return titleHours;
    }


    public HashMap<User, Integer> getUserHours() {
        if(userHours != null){
            return userHours;
        }
        userHours = new HashMap<>();

        for (Task task : tasks) {
            for (User user : task.getAssignedUsers()) {
                if(!userHours.containsKey(user)){
                    userHours.put(user, 0);
                }
                int oldValue = userHours.get(user);
                int hours = DateUtil.daysBetween(task.getStartDate(), task.getEndDate()) * workHoursPerDay;
                userHours.replace(user, oldValue + hours);
            }
        }
        return userHours;
    }

    public int getGrandTotalHours(){
        if(grandTotalHours != null){
            return grandTotalHours;
        }
        grandTotalHours = 0;
        for (Task task : tasks) {
            grandTotalHours += DateUtil.daysBetween(task.getStartDate(), task.getEndDate()) * workHoursPerDay * task.getAssignedUsers().size();
        }
        return grandTotalHours;
    }
}
