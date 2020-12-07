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

        actualTask = taskDAO.getTaskById(actualTask.getTaskId());
        assertNotNull(actualTask);
        assertEquals(t.getAssignedUserIds().length, actualTask.getAssignedUserIds().length);
        assertEquals(t.getTaskDependencyIds().length, actualTask.getTaskDependencyIds().length);
    }

    @Test
    void getTaskList() {
        List<Task> tasks = taskDAO.getTaskList(1);
        assertEquals(5, tasks.size());
        for(int i = 0; i < tasks.size(); i++){
            assertEquals(i+1, tasks.get(i).getTaskId());
        }
    }

    @Test
    void getTask() {
        Task t = taskDAO.getTaskById(1);
        assertNotNull(t);
        assertEquals(1, t.getTaskId());
        assertEquals("Test Task", t.getTitle());
        assertEquals(3, t.getAssignedUserIds().length);
        assertEquals(2, t.getTaskDependencyIds().length);
    }

    @Test
    void updateTask(){
        Task expectedTask = new Task();
        expectedTask.setTaskId(1);
        expectedTask.setTitle("New Task");
        expectedTask.setTaskLeaderId(2);
        expectedTask.setAssignedUserIds(new int[]{4});
        expectedTask.setStartDate("2020-01-01");
        expectedTask.setEndDate("2020-01-01");
        expectedTask.setTaskDependencyIds(new int[]{3,4});
        expectedTask.setProjectId(1);
        taskDAO.updateTask(expectedTask);
        Task actualTask = taskDAO.getTaskById(expectedTask.getTaskId());
        assertEquals(expectedTask, actualTask);
    }

    @Test
    void deleteTask(){
        taskDAO.deleteTask(1);
        Task t = taskDAO.getTaskById(1);
        assertNull(t);
    }
}