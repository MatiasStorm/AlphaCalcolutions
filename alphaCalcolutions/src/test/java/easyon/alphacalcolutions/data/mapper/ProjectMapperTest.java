package easyon.alphacalcolutions.data.mapper;

import easyon.alphacalcolutions.model.Project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProjectMapperTest {
    @Mock
    private ResultSet rs;
    int id = 1;
    String title = "First";
    String startDate = "2020-12-01";
    String endDate = "2020-12-21";
    int leaderId = 1;
    String assignUserIds = "1,2";

    @BeforeEach
    void setup() throws SQLException {
        Mockito.when(rs.getInt("project_id")).thenReturn(id);
        Mockito.when(rs.getString("project_title")).thenReturn(title);
        Mockito.when(rs.getString("project_start_date")).thenReturn(startDate);
        Mockito.when(rs.getString("project_end_date")).thenReturn(endDate);
        Mockito.when(rs.getInt("project_leader_id")).thenReturn(leaderId);
        Mockito.when(rs.getString("assigned_user_ids")).thenReturn(assignUserIds);
    }

    @Test
    void mapRow() throws SQLException, ParseException {
        ProjectMapper mapper = new ProjectMapper();
        Project project = mapper.mapRow(rs);
        assertEquals(id, project.getProjectId());
        assertEquals(title, project.getTitle());
        assertEquals(2, project.getAssignedUserIds().length);
    }
}