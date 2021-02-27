package easyon.alphacalcolutions.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProjectTest {

    Project getProject(){
        Project project = new Project();
        project.setTitle("Project2");
        project.setProjectLeaderId(2);
        project.setProjectCost(20000);
        project.setStartDate("2020-12-01");
        project.setEndDate("2020-12-24");
        project.setAssignedUserIds(new int[]{1,2});
        return project;
    }

    @Test
    void testEquals() {
        Project p1 = getProject();
        Project p2 = getProject();
        assertTrue(p1.equals(p2));
        assertFalse(p1.equals(null));
        assertTrue(p1.equals(p1));
    }

    @Test
    void getProjectDurationValidDates() {
        Project p1 = getProject();
        assertEquals(18, p1.getProjectDuration());
    }

    @Test
    void getProjectDurationNullStartDate() {
        Project p1 = getProject();
        p1.setStartDate(null);
        assertEquals(0, p1.getProjectDuration());
    }
    @Test
    void getProjectDurationNullEndDate() {
        Project p1 = getProject();
        p1.setEndDate(null);
        assertEquals(0, p1.getProjectDuration());
    }
    @Test
    void getProjectDurationNullDates() {
        Project p1 = getProject();
        p1.setEndDate(null);
        p1.setStartDate(null);
        assertEquals(0, p1.getProjectDuration());
    }
}