package easyon.alphacalcolutions.data.mapper;

import easyon.alphacalcolutions.model.Project;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

public class ProjectMapper {

    public Project mapRow(ResultSet rs) throws SQLException, ParseException {
        Project project = new Project();
        project.setProjectId(rs.getInt("project_id"));
        project.setTitle(rs.getString("project_title"));
        project.setStartDate(rs.getString("project_start_date"));
        project.setEndDate(rs.getString("project_end_date"));
        project.setProjectLeaderId(rs.getInt("project_leader_id"));
        project.setAssignedUserIds(rs.getString("assigned_user_ids").split(","));
        return project;
    }
}
