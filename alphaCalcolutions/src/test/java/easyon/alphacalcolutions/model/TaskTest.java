package easyon.alphacalcolutions.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    Task getTask(){
        Task t = new Task();
        t.setTaskId(1);
        t.setTitle("Title");
        t.setTaskLeaderId(1);
        t.setAssignedUserIds(new int[] {1,2,3});
        t.setStartDate("2020-12-01");
        t.setEndDate("2020-12-12");
        t.setTaskDependencyIds(new int[] {4,3,1});
        t.setProjectId(2);
        return t;
    }

    @Test
    void testEquals() {
        Task t1 = getTask();
        Task t2 = getTask();
        assertTrue(t1.equals(t2));
        assertTrue(t1.equals(t1));
        assertFalse(t1.equals(null));
    }
}