package easyon.alphacalcolutions.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {
    @Test
    void getTitleHours() {
        TaskList taskList = getTaskList();
        HashMap<UserTitle, Integer> titleHours = taskList.getTitleHours();
        assertEquals(72, titleHours.get(getProgrammerTitle()));
        assertEquals(48, titleHours.get(getDesignerTitle()));
    }

    @Test
    void getUserHours() {
        TaskList taskList = getTaskList();
        HashMap<User, Integer> userHours = taskList.getUserHours();
        assertEquals(72, userHours.get(getJane()));
        assertEquals(48, userHours.get(getJohn()));
    }

    @Test
    void getGrandTotalHours() {
        TaskList taskList = getTaskList();
        assertEquals(120, taskList.getGrandTotalHours());
    }

    UserTitle getDesignerTitle(){
        UserTitle designer = new UserTitle();
        designer.setUserTitleId(1);
        designer.setUserTitle("Designer");
        return designer;
    }

    UserTitle getProgrammerTitle(){
        UserTitle programmer = new UserTitle();
        programmer.setUserTitleId(2);
        programmer.setUserTitle("Programmer");
        return programmer;
    }

    User getJohn(){
        User john = new User();
        john.setUserId(1);
        john.setTitle(getDesignerTitle());
        john.setFirstName("John");
        john.setLastName("Dow");
        john.setUsername("John123");
        john.setPassword("John123");
        return john;
    }

    User getJane(){
        User jane = new User();
        jane.setUserId(2);
        jane.setTitle(getProgrammerTitle());
        jane.setFirstName("Jane");
        jane.setLastName("Doe");
        jane.setUsername("Jane123");
        jane.setPassword("Jane123");
        return jane;
    }

    TaskList getTaskList(){
        User jane = getJane();
        User john = getJohn();
        ArrayList<Task> tasks = new ArrayList<>();
        Task t = new Task();
        t.setStartDate("2020-12-07");
        t.setEndDate("2020-12-11");
        t.setAssignedUsers(new ArrayList<User>(){{add(jane);}});
        tasks.add(t);

        t = new Task();
        t.setStartDate("2020-12-07");
        t.setEndDate("2020-12-08");
        t.setAssignedUsers(new ArrayList<User>(){{add(john);}});
        tasks.add(t);

        t = new Task();
        t.setStartDate("2020-12-07");
        t.setEndDate("2020-12-10");
        t.setAssignedUsers(new ArrayList<User>(){{add(jane);add(john);}});
        tasks.add(t);

        return new TaskList(tasks);
    }
}