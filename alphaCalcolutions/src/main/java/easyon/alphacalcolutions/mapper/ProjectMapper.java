package easyon.alphacalcolutions.mapper;

import easyon.alphacalcolutions.model.Project;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectMapper {

    public Project mapRow(ResultSet rs) throws SQLException {
        Project project = new Project();
        project.setProjectId(rs.getInt("project_id"));
        project.setTitle(rs.getString("project_title"));
//        project.setProjectLeader(rs.getInt("project_leader_id"));
//        project.setAssignedUsers();
//        project.setDeadline();


        return project;
    }
}
