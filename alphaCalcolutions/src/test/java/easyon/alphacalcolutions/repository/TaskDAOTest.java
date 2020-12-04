package easyon.alphacalcolutions.repository;

import easyon.alphacalcolutions.model.Task;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskDAOTest extends AbstractDAOTest{
    private TaskDAO taskDAO = new TaskDAO(con);

    @Test
    void createTask() throws ParseException {
        Task t = new Task();
        t.setTitle("New Task");
        t.setTaskLeaderId(2);
        t.setAssignedUserIds(new int[]{1,2,3});
        t.setStartDate("2020-01-01");
        t.setEndDate("2020-01-01");
        t.setTaskDependencyIds(new int[]{3,4});
        t.setProjectId(1);

        Task actualTask = taskDAO.createTask(t);

        assertNotNull(actualTask);
        assertEquals(6, actualTask.getTaskId());

        actualTask = taskDAO.getTask(actualTask.getTaskId());
        assertNotNull(actualTask);
        assertEquals(t.getAssignedUserIds().length, actualTask.getAssignedUserIds().length);
        assertEquals(t.getTaskDependencyIds().length, actualTask.getTaskDependencyIds().length);
    }

    @Test
    void getTaskList() {
        List<Task> tasks = taskDAO.getTaskList();
        assertEquals(5, tasks.size());
    }

    @Test
    void getTask() {
        Task t = taskDAO.getTask(1);
        assertNotNull(t);
        assertEquals(1, t.getTaskId());
        assertEquals("Test Task", t.getTitle());
        assertEquals(3, t.getAssignedUserIds().length);
        assertEquals(2, t.getTaskDependencyIds().length);
    }
}